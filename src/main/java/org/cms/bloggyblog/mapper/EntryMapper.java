package org.cms.bloggyblog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntryMapper {

  EntryMapper INSTANCE = Mappers.getMapper(EntryMapper.class);

  org.cms.bloggyblog.model.transfer.Entry map(org.cms.bloggyblog.model.entity.Entry entry);

  org.cms.bloggyblog.model.entity.Entry map(org.cms.bloggyblog.model.transfer.Entry entry);
}
