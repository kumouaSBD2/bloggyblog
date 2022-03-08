package org.cms.bloggyblog.service;

import org.cms.bloggyblog.model.entity.Blogger;
import org.cms.bloggyblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloggerService {

  private final BloggerRepository bloggerRepository;

  @Autowired
  public BloggerService(BloggerRepository bloggerRepository) {
    this.bloggerRepository = bloggerRepository;
  }

  public List<Blogger> getAllUsers() {
    return bloggerRepository.findAll();
  }

  public Blogger save(Blogger blogger) {
    return bloggerRepository.save(blogger);
  }

  public Blogger upsert(Long id, String name) {
    return bloggerRepository.save(Blogger.builder().id(id).name(name).build());
  }

  public void deleteById(Long id) {
    bloggerRepository.deleteById(id);
  }
}
