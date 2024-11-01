package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.Generator;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        ctx.render("build.jte");
    }

    public static void create(Context ctx) {
        var nickname = ctx.formParam("name");
        var password = ctx.formParam("password");

        if (UsersRepository.existsByName(nickname)) {
            var user = UsersRepository.findByName(nickname).get();
            if (user.getPassword().equals(Security.encrypt(password))) {
                ctx.sessionAttribute("currentUser", nickname);
                ctx.redirect("/");
            }
        } else {
            ctx.redirect("/sessions/build");
        }
        // Тут должна быть проверка пароля
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
    // END
}
