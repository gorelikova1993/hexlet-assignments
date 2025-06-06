package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Scope(value = "singleton")
    public Daytime daytimeBean () {
        LocalDateTime now = LocalDateTime.now();
        int currentHour = now.getHour();

        if(currentHour >= 6 && currentHour < 22) {
            return new Day();
        } else {
            return new Night();
        }
    }



}
