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
public class PostDTO {

  private String postTitle;

  private String postBody;

  private User postUser;
}
