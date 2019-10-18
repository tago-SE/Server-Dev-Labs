package controllers;
import models.Post;
import models.PostList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.PostService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(final PostService userService) {
        this.postService = userService;
    }

    /**
     * Echo response to indicate that the server is running.
     * @return "Posts"
     */
    @RequestMapping("")
    public String index() {
        return "Posts";
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post Post) {
        return ResponseEntity.ok(postService.insert(Post));
    }

   @PutMapping("/update")
   public ResponseEntity<Post> updatePost(@Valid @RequestBody Post Post) {
       return ResponseEntity.ok(postService.update(Post));
    }

    @PostMapping("/delete")
    public Map<String, Boolean> deletePost(@Valid @RequestBody Post Post) {
        boolean result = postService.delete(Post) != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", result);
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/get/user/{username}")
    public ResponseEntity<PostList> getPostsByName(@PathVariable("username") String name) {
        return ResponseEntity.ok(new PostList(postService.getPostsByName(name)));
    }

}
