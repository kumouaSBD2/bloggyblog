package org.cms.bloggyblog.service

import org.cms.bloggyblog.model.entity.User
import org.cms.bloggyblog.repository.EntryRepository
import org.cms.bloggyblog.repository.UserRepository
import spock.lang.Specification

class UserServiceSpec extends Specification {

    UserRepository userRepository = Mock()
    UserService userService = new UserService(userRepository)

    def "get all users"() {
        when:
        userService.getAllUsers()

        then:
        1 * userRepository.findAll()
    }

    def "save user"() {
        given:
        User user = User.builder().id(1L).name("Tester").build()

        when:
        userService.save(user)

        then:
        1 * userRepository.save(user)
    }

    def "update user"() {
        given:
        Long id = 1
        String name = "Tester"

        when:
        userService.upsert(id, name)

        then:
        1 * userRepository.save(User.builder().id(id).name(name).build())
    }

    def "delete user"() {
        given:
        User user = User.builder().id(1L).name("Tester").build()

        when:
        userService.deleteById(user.getId())

        then:
        userRepository.deleteById(user.getId())
    }

}
