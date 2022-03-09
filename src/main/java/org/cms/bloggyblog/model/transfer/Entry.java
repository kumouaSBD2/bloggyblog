package org.cms.bloggyblog.model.transfer;

import lombok.Data;
import org.cms.bloggyblog.model.entity.Blogger;

@Data
public class Entry {

  private String title;

  private String body;

  private Blogger blogger;
}
