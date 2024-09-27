package exercise;

import io.javalin.Javalin;

import java.util.List;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        List<String> phones = Data.getPhones();
        List<String> domains = Data.getDomains();
        var app = Javalin.create().get("/phones", ctx -> {
            ctx.json(phones);
        });
        // END
        app.get("/domains", ctx -> {
            ctx.json(domains);
        });
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
