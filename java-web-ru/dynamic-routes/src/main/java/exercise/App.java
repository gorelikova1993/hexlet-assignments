package exercise;

import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var id = ctx.pathParam("id");
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < COMPANIES.size(); i++) {
                if (COMPANIES.get(i).get("id").equals(id)) {
                    map = COMPANIES.get(i);
                }
            }
            if (map.isEmpty()) {
                ctx.status(404);
                ctx.result("Company not found");
            } else {
                ctx.json(map);
            }
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
