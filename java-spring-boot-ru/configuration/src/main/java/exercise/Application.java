package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    @Autowired
    private UserProperties userProperties;

    // BEGIN
    @GetMapping("/admins")
    public List<String> getAdmins() {
        List<String> admins = new ArrayList<>();

        for (var user : users) {
            for (var admin : userProperties.getAdmins()) {
                if(admin.equals(user.getEmail())) {
                    admins.add(user.getName());
                }
            }
        }
        Collections.sort(admins);
        return admins;
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
