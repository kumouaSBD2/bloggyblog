package org.cms.bloggyblog.controller;

import org.cms.bloggyblog.annotation.NoHtml;
import org.cms.bloggyblog.mapper.EntryMapper;
import org.cms.bloggyblog.model.transfer.Entry;
import org.cms.bloggyblog.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/blog-entries")
public class EntryController {

  private final EntryService entryService;

  @Autowired
  public EntryController(EntryService entryService) {
    this.entryService = entryService;
  }

  @GetMapping(path = {"/", ""})
  public List<Entry> getAllEntries() {
    return entryService.getAllEntries().stream().map(EntryMapper.INSTANCE::map).collect(Collectors.toList());
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Entry addEntry(@RequestBody @Validated Entry entry) {
    return EntryMapper.INSTANCE.map(entryService.save(EntryMapper.INSTANCE.map(entry)));
  }

  @DeleteMapping(value = "/{id}")
  public void deleteEntry(@PathVariable(value = "id") Long id) {
    entryService.deleteById(id);
  }
}
