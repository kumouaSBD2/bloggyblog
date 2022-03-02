package org.cms.bloggyblog.model.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cms.bloggyblog.model.entity.User;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Entry {

  private String title;

  private String body;

  private User user;
}
