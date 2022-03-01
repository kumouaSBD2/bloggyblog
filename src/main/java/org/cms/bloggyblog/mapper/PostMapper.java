package org.cms.bloggyblog.mapper;

import org.cms.bloggyblog.model.entity.Post;
import org.cms.bloggyblog.model.transfer.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  @Mapping(source = "title", target = "postTitle")
  @Mapping(source = "body", target = "postBody")
  @Mapping(source = "user", target = "postUser")
  PostDTO map(Post post);

  @Mapping(source = "postTitle", target = "title")
  @Mapping(source = "postBody", target = "body")
  @Mapping(source = "postUser", target = "user")
  Post map(PostDTO postDTO);
}
