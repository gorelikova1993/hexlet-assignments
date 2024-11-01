package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;

import java.util.Objects;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        var name = ctx.formParam("firstName");
        var surname = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = Security.encrypt(Objects.requireNonNull(ctx.formParam("password")));
        var token = Security.generateToken();

        var user = new User(name, surname, email, password, token);
        UserRepository.save(user);
        ctx.cookie("token", token);
        ctx.redirect("/users/" + user.getId());
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));
        var usrToken = user.getToken();
        var pageToken = ctx.cookie("token");
        if (usrToken.equals(pageToken)) {
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.redirect("/users/build");
        }
    }

    // END
}
