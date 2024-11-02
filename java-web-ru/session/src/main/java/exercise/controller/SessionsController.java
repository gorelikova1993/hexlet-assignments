package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.Generator;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        var page = new LoginPage();
        ctx.render("build.jte", model("page", page));
    }

    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var nickname = ctx.formParam("name");
        var password = ctx.formParam("password");

        if (UsersRepository.existsByName(nickname) &&
                UsersRepository.findByName(nickname).get().getPassword().equals(Security.encrypt(password))) {
                ctx.sessionAttribute("currentUser", nickname);
                ctx.redirect("/");

        } else {
            var page = new LoginPage(nickname, "Wrong username or password.");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
    // END
}
