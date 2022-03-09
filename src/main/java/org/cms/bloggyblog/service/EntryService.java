package org.cms.bloggyblog.service;

import org.cms.bloggyblog.model.entity.Entry;
import org.cms.bloggyblog.repository.EntryRepository;
import org.cms.bloggyblog.sanitizer.Sanitizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EntryService {

  private final EntryRepository entryRepository;
  public final Sanitizer sanitizer;

  @Autowired
  public EntryService(EntryRepository entryRepository, Sanitizer sanitizer) {
    this.entryRepository = entryRepository;
    this.sanitizer = sanitizer;
  }

  public List<Entry> getAllEntries() {
    return entryRepository.findAll();
  }

  public Entry save(Entry entry) {
    return entryRepository.save(sanitizer.sanitize(entry));
  }

  public void deleteById(Long id) {
    entryRepository.deleteById(id);
  }
}
