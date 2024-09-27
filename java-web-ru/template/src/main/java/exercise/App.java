package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var user = USERS.stream().filter(x ->
                    x.getId() == id).findFirst().orElseThrow(() -> new NotFoundResponse("User not found"));
            if (user == null) {
                ctx.status(404);
                ctx.result("User not found");
            } else {
                var page = new UserPage(user);
                ctx.render("users/show.jte", model("page", page));
            }
        });

        app.get("/users", ctx -> {
            var users = USERS;
            var header = "USERS";
            var page = new UsersPage(users, header);
            ctx.render("users/index.jte", model("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
