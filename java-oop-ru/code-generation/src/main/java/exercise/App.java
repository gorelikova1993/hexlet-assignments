package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path path, Car car) throws IOException {
        String content = car.serialize();
        Files.writeString(path, content);
    }

    public static Car extract(Path path) throws IOException {
        String json = Files.readString(path);
        return Car.unserialize(json);
    }
}
// END
