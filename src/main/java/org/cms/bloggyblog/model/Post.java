package org.cms.bloggyblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POST_TABLE")
public class Post{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "POST_ID")
  private Long id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "BODY")
  private String body;

  @CreationTimestamp
  @Column(name = "TIME_STAMP")
  private LocalDateTime timeStamp;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;
}
