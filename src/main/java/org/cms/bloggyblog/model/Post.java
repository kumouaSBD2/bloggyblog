package org.cms.bloggyblog.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
public class Post{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "POST_ID")
  private Long id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "BODY")
  private String body;

  @Column(name = "TIME_STAMP")
  private LocalDateTime timeStamp;

  @Column(name = "USER")
  private User user;
}
