package org.cms.bloggyblog.service;

import lombok.extern.slf4j.Slf4j;
import org.cms.bloggyblog.model.Post;
import org.cms.bloggyblog.model.User;
import org.cms.bloggyblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User upsert(Long id, String name) {
        return userRepository.save(User.builder().id(id).name(name).build());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
