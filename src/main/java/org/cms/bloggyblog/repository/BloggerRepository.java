package org.cms.bloggyblog.repository;

import org.cms.bloggyblog.model.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {}
