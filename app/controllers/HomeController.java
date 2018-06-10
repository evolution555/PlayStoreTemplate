package controllers;

import play.api.Environment;
import play.mvc.*;

import views.html.*;
import play.data.*;

import java.util.*;

import javax.inject.Inject;

import models.users.*;
import models.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private FormFactory formFactory;
    private Environment env;

    @Inject
    public HomeController(FormFactory f, Environment e) {
        this.formFactory = f;
        this.env = e;
    }

    public Result index() {
        User u = getUserFromSession();
        List<Testimony> allTest = Testimony.findAll();
        return ok(index.render(u, env, allTest));
    }

    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class);
        return ok(login.render(loginForm));
    }

    public Result store() {
        List<Item> allItems = Item.findAll();
        return ok(store.render(allItems, env));
    }

    public Result signUp() {
        Form<User> adduserForm = formFactory.form(User.class);
        return ok(signUp.render(adduserForm, null));
    }

    public static User getUserFromSession() {
        return User.getUserById(session().get("email"));
    }

    public Result addUserSubmit() {
        DynamicForm newUserForm = formFactory.form().bindFromRequest();
        Form errorForm = formFactory.form().bindFromRequest();
        if (newUserForm.hasErrors()) {
            return badRequest(signUp.render(errorForm, "Error with form."));
        }
        if (newUserForm.get("email").equals("") || newUserForm.get("name").equals("")) {
            return badRequest(signUp.render(errorForm, "Please enter an email and a name."));
        }
        if (newUserForm.get("role").equals("select")) {
            return badRequest(signUp.render(errorForm, "Please enter a role."));
        }
        if (!newUserForm.get("password").equals(newUserForm.get("passwordConfirm"))) {
            return badRequest(signUp.render(errorForm, "Passwords do not match."));
        }
        if (newUserForm.get("password").length() < 6) {
            return badRequest(signUp.render(errorForm, "Password must be at least six characters."));
        }
        List<User> allusers = User.findAll();
        for (User a : allusers) {
            if (a.getEmail().equals(newUserForm.get("email"))) {
                return badRequest(signUp.render(errorForm, "Email already exists in system."));
            }
        }
        User.create(newUserForm.get("email"), newUserForm.get("name"), newUserForm.get("role"), newUserForm.get("password"));
        flash("success", "Your account has been successfully created. Please sign in with your details.");
        return redirect(controllers.routes.HomeController.index());
    }

    public Result product(String id){
        User u = getUserFromSession();
        Item item = Item.find.byId(id);
        List<Item> allItems = Item.findAll();
        return ok(product.render(u, allItems,item ,env));
    }

}
