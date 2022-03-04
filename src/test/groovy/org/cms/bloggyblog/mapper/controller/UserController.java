package org.cms.bloggyblog.mapper.controller;

import org.cms.bloggyblog.model.entity.User;
import org.cms.bloggyblog.service.UserService;
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
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = {"/", ""})
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PutMapping(value = "/{id}")
  public User updateUser(@PathVariable(value = "id") Long id, @RequestBody String name) {
    return userService.upsert(id, name);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/{name}")
  public User addUser(@PathVariable(value = "name") String name) {
    return userService.save(User.builder().name(name).build());
  }

  @DeleteMapping(value = "/{id}")
  public void deletePost(@PathVariable(value = "id") Long id) {
    userService.deleteById(id);
  }
}
