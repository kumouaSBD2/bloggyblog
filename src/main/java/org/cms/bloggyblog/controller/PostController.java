package org.cms.bloggyblog.controller;

import org.cms.bloggyblog.model.entity.Post;
import org.cms.bloggyblog.model.entity.User;
import org.cms.bloggyblog.model.transfer.PostDTO;
import org.cms.bloggyblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/blog-posts")
public class PostController {

    private final PostService postService;
    private Mapper mapper;

    @Autowired
    public PostController(PostService postService, Mapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

//    @GetMapping(path = {"/", ""})
//    public List<Post> getAllPosts() {
//        return postService.getAllPosts();
//    }

    @GetMapping (path = {"/", ""})
    @ResponseBody
    public List<PostDTO> getPosts() {
        return postService.getAllPosts()
                .stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    @PutMapping(value = "/{id}/{title}")
    public Post updatePost(@PathVariable(value = "id") Long id, @PathVariable(value = "title") String title, @RequestBody String body, User user) {
        return postService.upsert(id, title, body, user);
    }

//    @PostMapping(value = "/{title}")
//    public Post addPost(@PathVariable(name = "title") String title, @RequestBody String body) {
//    return postService.save(Post.builder().title(title).body(body).build());
//    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable(value = "id") Long id) {
        postService.deleteById(id);
    }

}
