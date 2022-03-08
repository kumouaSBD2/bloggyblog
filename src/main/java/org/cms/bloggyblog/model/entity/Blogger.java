package org.cms.bloggyblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BLOGGER")
public class Blogger {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "BLOGGER_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;
}
