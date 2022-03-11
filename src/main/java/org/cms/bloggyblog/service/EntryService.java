package org.cms.bloggyblog.service;

import org.cms.bloggyblog.model.entity.Entry;
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

  public Entry save(Entry entry) {
    return entryRepository.save(entry);
  }

  public void deleteById(Long id) {
    entryRepository.deleteById(id);
  }
}
