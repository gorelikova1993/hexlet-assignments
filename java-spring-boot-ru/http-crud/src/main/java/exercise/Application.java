package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    /*
    реализуйте вывод списка постов с помощью пейджинга. Номер страницы и количество постов на странице передаются в качестве параметров
    строки запроса — например /posts?page=2&limit=10_. По умолчанию должна выводиться первая страница.
     */
    @GetMapping("/posts") //список постов
    public List<Post> index(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().skip((page - 1) * limit).limit(limit).toList();
    }

    @PostMapping("/posts") //Созданре поста
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @GetMapping("/posts/{id}") //вывод 1го поста
    public Optional<Post> show(@PathVariable String id) {
        var post = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        return post;
    }

    @PutMapping("/posts/{id}") //обновление поста
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream().filter( p -> p.getId().equals(id)).findFirst();
        if (maybePost.isPresent()) {
            var post = maybePost.get();
            post.setBody(data.getBody());
            post.setTitle(data.getTitle());
            post.setId(data.getId());
        }
        return data;
    }

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }

    // END
}
