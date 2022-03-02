package org.cms.bloggyblog.service;

import lombok.extern.slf4j.Slf4j;
import org.cms.bloggyblog.model.entity.Entry;
import org.cms.bloggyblog.model.entity.User;
import org.cms.bloggyblog.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EntryService {

  private final EntryRepository postRepository;

  @Autowired
  public EntryService(EntryRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Entry> getAllPosts() {
    return postRepository.findAll();
  }

  public Entry save(Entry post) {
    return postRepository.save(post);
  }

  public Entry upsert(Long id, String title, String body, User user) {
    return postRepository.save(Entry.builder().id(id).title(title).body(body).user(user).build());
  }

  public void deleteById(Long id) {
    postRepository.deleteById(id);
  }
}
