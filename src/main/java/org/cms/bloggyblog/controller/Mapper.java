package org.cms.bloggyblog.controller;

import org.cms.bloggyblog.model.entity.Post;
import org.cms.bloggyblog.model.entity.User;
import org.cms.bloggyblog.model.transfer.PostDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

  public PostDTO toDto(Post post) {
    String title = post.getTitle();
    String body = post.getBody();
    User user = post.getUser();

    return new PostDTO(title, body, user);
  }
}
