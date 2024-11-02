package exercise.dto.posts;

import java.util.List;
import java.util.Map;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BEGIN
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class EditPostPage {
    private String name;

    public EditPostPage(Long id, String name, String body) {
        this.name = name;
        this.body = body;
        this.id = id;
    }

    private String body;
    private Long id;
    private Map<String, List<ValidationError<Object>>> errors;
}
// END
