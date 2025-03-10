package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> index(@PathVariable int id) {
        return posts.stream().filter(post -> post.getUserId() == id)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create(@PathVariable int id,
                                        @RequestBody Post data) {
        var post = new Post();
        post.setBody(data.getBody());
        post.setTitle(data.getTitle());
        post.setSlug(data.getSlug());
        post.setUserId(id);
        posts.add(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

}
// END
