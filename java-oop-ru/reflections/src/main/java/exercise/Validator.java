package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static Map<String, List<String>> advancedValidate(Object instance) {
        List<Field> fields = List.of(instance.getClass().getDeclaredFields());
        Map<String, List<String>> validationErrors = new HashMap<>();
        fields.stream()
                .filter(field -> field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(MinLength.class))
                .forEach(field -> {
                    String fieldName = field.getName();
                    List<String> errors = getErrorsForField(field, instance);
                    if (!errors.isEmpty()) {
                        validationErrors.put(fieldName, errors);
                    }
                });
        return validationErrors;
    }

    public static List<String> getErrorsForField(Field field, Object instance) {
        List<String> errors = new ArrayList<>();
        String value;

        try {
            field.setAccessible(true);
            value = (String) field.get(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (field.isAnnotationPresent(NotNull.class) && value == null) {
            errors.add("can not be null");
        }

        if (field.isAnnotationPresent(MinLength.class)) {
            int minLength = field.getAnnotation(MinLength.class).minLength();
            if (value == null || value.length() < minLength) {
                errors.add("length less than " + minLength);
            }
        }
        return errors;
    }

}
// END
