package org.cms.bloggyblog.repository;

import org.cms.bloggyblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggyBlogRepository extends JpaRepository<Post, Long> {

}
