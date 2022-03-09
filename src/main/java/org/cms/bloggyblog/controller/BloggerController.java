package org.cms.bloggyblog.controller;

import org.cms.bloggyblog.model.entity.Blogger;
import org.cms.bloggyblog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/blog-users")
public class BloggerController {

  private final BloggerService bloggerService;

  @Autowired
  public BloggerController(BloggerService bloggerService) {
    this.bloggerService = bloggerService;
  }

  @GetMapping(path = {"/", ""})
  public List<Blogger> getAllUsers() {
    return bloggerService.getAllUsers();
  }

  @PutMapping(value = "/{id}")
  public Blogger updateUser(@PathVariable(value = "id") Long id, @RequestBody String name) {
    return bloggerService.upsert(id, name);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = {"/", ""})
  public Blogger addUser(@RequestBody String name) {
    return bloggerService.save(Blogger.builder().name(name).build());
  }

  @DeleteMapping(value = "/{id}")
  public void deletePost(@PathVariable(value = "id") Long id) {
    bloggerService.deleteById(id);
  }
}
