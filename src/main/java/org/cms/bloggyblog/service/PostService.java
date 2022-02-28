package org.cms.bloggyblog.service;

import lombok.extern.slf4j.Slf4j;
import org.cms.bloggyblog.model.entity.Post;
import org.cms.bloggyblog.model.entity.User;
import org.cms.bloggyblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }

    public Post upsert(Long id, String title, String body, User user) {
        return postRepository.save(Post.builder().id(id).title(title).body(body).user(user).build());
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
