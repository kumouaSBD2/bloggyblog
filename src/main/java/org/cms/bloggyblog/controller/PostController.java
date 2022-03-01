package org.cms.bloggyblog.controller;

import org.cms.bloggyblog.mapper.PostMapper;
import org.cms.bloggyblog.model.entity.Post;
import org.cms.bloggyblog.model.entity.User;
import org.cms.bloggyblog.service.PostService;
import org.cms.bloggyblog.model.transfer.PostDTO;
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

  @PutMapping(value = "/{id}/{title}")
  public PostDTO updatePost(
      @PathVariable(value = "id") Long id,
      @PathVariable(value = "title") String title,
      @RequestBody String body,
      User user) {
    return PostMapper.INSTANCE.map(postService.upsert(id, title, body, user));
  }

  @PostMapping
  public PostDTO addPost(@RequestBody PostDTO postDTO) {
    return PostMapper.INSTANCE.map(postService.save(PostMapper.INSTANCE.map(postDTO)));
  }

  @DeleteMapping(value = "/{id}")
  public void deletePost(@PathVariable(value = "id") Long id) {
    postService.deleteById(id);
  }
}
