package org.cms.bloggyblog.mapper;

import org.cms.bloggyblog.model.transfer.Entry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntryMapper {

  EntryMapper INSTANCE = Mappers.getMapper(EntryMapper.class);

  Entry map(org.cms.bloggyblog.model.entity.Entry post);

  org.cms.bloggyblog.model.entity.Entry map(Entry entry);
}
