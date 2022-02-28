package org.cms.bloggyblog.controller;

import org.cms.bloggyblog.model.Post;
import org.cms.bloggyblog.model.User;
import org.cms.bloggyblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/blog-posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = {"/", ""})
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping(value = "/{id}")
    public Post updatePost(@PathVariable(value = "id") Long id, String title, @RequestBody String body, User user) {
        return postService.upsert(id, title, body, user);
    }

    @PostMapping(value = "/{title}")
    public Post addPost(@PathVariable(name = "title") String title, @RequestBody String body) {
    return postService.save(Post.builder().title(title).body(body).build());
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable(value = "id") Long id) {
        postService.deleteById(id);
    }

}
