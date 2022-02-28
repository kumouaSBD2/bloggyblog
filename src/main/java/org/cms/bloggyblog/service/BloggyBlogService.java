package org.cms.bloggyblog.service;

import lombok.extern.slf4j.Slf4j;
import org.cms.bloggyblog.model.Post;
import org.cms.bloggyblog.model.User;
import org.cms.bloggyblog.repository.BloggyBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BloggyBlogService {

    private final BloggyBlogRepository bloggyBlogRepository;

    @Autowired

    public BloggyBlogService(BloggyBlogRepository bloggyBlogRepository) {
        this.bloggyBlogRepository = bloggyBlogRepository;
    }

    public List<Post> getAllPosts() {
        return bloggyBlogRepository.findAll();
    }

    public Post save(Post post) {
        return bloggyBlogRepository.saveAndFlush(post);
    }

    public Post upsert(Long id, String title, String body, User user) {
        return bloggyBlogRepository.save(Post.builder().id(id).title(title).body(body).user(user).build());
    }

    public void deleteById(Long id) {
        bloggyBlogRepository.deleteById(id);
    }
}
