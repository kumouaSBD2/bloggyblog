package org.cms.bloggyblog.controller;

import org.cms.bloggyblog.model.Post;
import org.cms.bloggyblog.model.User;
import org.cms.bloggyblog.service.BloggyBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/blog")
public class BloggyBlogController {

    private final BloggyBlogService bloggyBlogService;

    @Autowired
    public BloggyBlogController(BloggyBlogService bloggyBlogService) {
        this.bloggyBlogService = bloggyBlogService;
    }

    @GetMapping(path = {"/", ""})
    public List<Post> getAllPosts() {
        return bloggyBlogService.getAllPosts();
    }

    @PutMapping(value = "/{id}")
    public Post updatePost(@PathVariable(value = "id") Long id, @RequestBody String title, @RequestBody String body, @RequestBody User user) {
        return bloggyBlogService.upsert(id, title, body, user);
    }

    @PostMapping(value = "/{titleName}")
    public Post addPost(@PathVariable(value = "id") Long id, @RequestBody String title, @RequestBody String body, @RequestBody User user) {
        return bloggyBlogService.upsert(id, title, body, user);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable(value = "id") Long id) {
        bloggyBlogService.deleteById(id);
    }

}
