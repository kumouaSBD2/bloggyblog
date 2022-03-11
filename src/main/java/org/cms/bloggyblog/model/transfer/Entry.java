package org.cms.bloggyblog.model.transfer;

import lombok.Data;
import org.cms.bloggyblog.annotation.NoHtml;
import org.cms.bloggyblog.model.entity.Blogger;

@Data
public class Entry {

  @NoHtml
  private String title;

  @NoHtml
  private String body;

  private Blogger blogger;
}
