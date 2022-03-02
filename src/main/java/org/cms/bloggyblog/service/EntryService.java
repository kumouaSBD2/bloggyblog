package org.cms.bloggyblog.service;

import lombok.extern.slf4j.Slf4j;
import org.cms.bloggyblog.model.entity.Entry;
import org.cms.bloggyblog.model.entity.User;
import org.cms.bloggyblog.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {

  private final EntryRepository entryRepository;

  @Autowired
  public EntryService(EntryRepository entryRepository) {
    this.entryRepository = entryRepository;
  }

  public List<Entry> getAllEntries() {
    return entryRepository.findAll();
  }

  public Entry save(Entry post) {
    return entryRepository.save(post);
  }

  public Entry upsert(Long id, String title, String body, User user) {
    return entryRepository.save(Entry.builder().id(id).title(title).body(body).user(user).build());
  }

  public void deleteById(Long id) {
    entryRepository.deleteById(id);
  }
}
