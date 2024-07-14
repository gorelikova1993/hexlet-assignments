package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// BEGIN
public class Validator {

    public static List<String> validate(Address address)   {
        List<String> list = new ArrayList<>();
        for (Field field : address.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
               field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        list.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }


}
// END
