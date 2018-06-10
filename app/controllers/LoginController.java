package controllers;

import controllers.*;
import play.api.Environment;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import models.users.*;

public class LoginController extends Controller {
    private FormFactory formFactory;
    private Environment env;

    @Inject
    public LoginController(Environment e, FormFactory f) {
        this.env = e;
        this.formFactory = f;
    }

    public Result loginSubmit() {
        DynamicForm newLoginForm = formFactory.form().bindFromRequest();
        Form<Login> errorForm = formFactory.form(Login.class).bindFromRequest();
        User login = User.authenticate(newLoginForm.get("email"), newLoginForm.get("password"));

        if (login != null) {
            session().clear();
            session("email", login.getEmail());
            session("role", login.getRole());

        } else {
            flash("error", "Invalid Username or Password");
            return redirect(routes.HomeController.login());
        }

        if (session().get("role").equals("Admin")) {
            return redirect(routes.AdminController.adminHome());
        }
        if (session().get("role").equals("Customer")) {
            return redirect(routes.HomeController.index());
        }
        return redirect(routes.HomeController.index());
    }

    public Result logout() {
        session().clear();
        flash("success", "You have been logged out");
        return redirect(routes.HomeController.index());
    }
}