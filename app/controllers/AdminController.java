package controllers;

import play.api.Environment;
import play.mvc.*;
import views.html.AdminPages.*;
import play.data.*;
import models.users.*;
import models.*;
import javax.inject.Inject;
import java.util.*;
import play.mvc.Http.MultipartFormData.FilePart;
import views.html.*;
import views.html.AdminPages.adminAddItem;
import views.html.AdminPages.adminHome;
import views.html.AdminPages.adminItems;
import views.html.AdminPages.adminUpdateItem;

import java.io.*;

@Security.Authenticated(Secured.class)
@With(AuthAdmin.class)
public class AdminController extends Controller {
    private FormFactory formFactory;
    private Environment env;

    @Inject
    public AdminController(FormFactory f, Environment e) {
        this.formFactory = f;
        this.env = e;
    }

    public Result adminHome() {
        User u = HomeController.getUserFromSession();
        return ok(adminHome.render(u, env));
    }

    public Result adminItems() {
        User u = HomeController.getUserFromSession();
        List<Item> allItems = Item.findAll();
        return ok(adminItems.render(u, env, allItems));
    }

    public Result adminAddItem() {
        Item i = new Item();
        User u = HomeController.getUserFromSession();
        return ok(adminAddItem.render(i, u, null));
    }

    public Result addItemSubmit() {
        DynamicForm df = formFactory.form().bindFromRequest();
        Item i = new Item();
        i.setTitle(df.get("title"));
        i.setDescription(df.get("description"));
        i.setCatagory(df.get("catagory"));
        try {
            i.setCost(Double.parseDouble(df.get("cost")));
        } catch (NumberFormatException e) {
            return badRequest(adminAddItem.render(i, HomeController.getUserFromSession(), "Cost must be a number"));
        }

        String saveImageMsg;

        List<Item> allItems = Item.findAll();
        for (Item item : allItems) {
            if (item.getTitle().equals(i.getTitle())) {
                return badRequest(adminAddItem.render(i, HomeController.getUserFromSession(), "Item already in database."));
            }
        }
        i.save();

        Http.MultipartFormData data = request().body().asMultipartFormData();
        FilePart image = data.getFile("upload");

        flash("success", saveFile(i.getItemId(), image));
        return redirect(routes.AdminController.adminItems());
    }


    public Result updateItem(String i){
        Item item = Item.find.byId(i);
        return ok(adminUpdateItem.render(item, HomeController.getUserFromSession(), null));
    }

    public Result updateItemSubmit(){
        DynamicForm df = formFactory.form().bindFromRequest();
        String title = df.get("title");
        String description = df.get("description");
        String catagory = df.get("catagory");
        String id = df.get("id");
        String imag = df.get("upload");

        Item item = Item.find.byId(id);
        double cost = 0;
        try {
            cost= (Double.parseDouble(df.get("cost")));
        } catch (NumberFormatException e) {
            return badRequest(adminAddItem.render(item, HomeController.getUserFromSession(), "Cost must be a number"));
        }


        item.setTitle(title);
        item.setDescription(description);
        item.setCost(cost);
        item.setCatagory(catagory);
        item.setImage(imag);
        item.update(imag);


        flash("success", "Item Updated");
        return redirect(routes.AdminController.adminItems());
    }


    public Result deleteItem (String id) {
        Item i = Item.find.byId(id);
        Item.find.ref(id).delete();
        flash("success", "Item has been deleted.");

        //Deleting image from folder.
        File file = new File("public/images/Item/" + i.getItemId() + ".jpg");
        file.delete();
        return redirect(routes.AdminController.adminItems());
    }


    //Image Save
    public String saveFile(String title, FilePart<File> uploaded) {
        if (uploaded != null) {
            String filename = uploaded.getFilename();
            String extension = "";

            String mimeType = uploaded.getContentType();

            if (mimeType.startsWith("image/")) {
                int i = filename.lastIndexOf('.');
                if (i >= 0) {
                    extension = filename.substring(i + 1);
                }

                File file = uploaded.getFile();
                file.renameTo(new File("public/images/" + title + "." + extension));
            }
            return "Item Added";
        }
        return "no file";
    }
}