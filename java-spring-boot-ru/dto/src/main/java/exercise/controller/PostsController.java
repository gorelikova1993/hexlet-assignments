package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        var posts = postRepository.findAll();
        var result = posts.stream()
                .map(this::toDTO)
                .toList();
        return result;
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return toDTO(post);
    }

    private PostDTO toDTO(Post post) {
            var dto = new PostDTO();
            dto.setId(post.getId());
            dto.setTitle(post.getTitle());
            dto.setBody(post.getBody());
            dto.setComments(commentToDTO(post.getId()));
            return dto;
    }

    private List<CommentDTO> commentToDTO(Long id){
        return commentRepository.findByPostId(id).stream()
                .map(comment -> {
                    var dto = new CommentDTO();
                    dto.setId(comment.getId());
                    dto.setBody(comment.getBody());
                    return dto;
                })
                .toList();
    }


}
// END
