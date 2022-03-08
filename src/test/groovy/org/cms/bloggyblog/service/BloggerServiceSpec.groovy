package org.cms.bloggyblog.service

import org.cms.bloggyblog.model.entity.Blogger
import org.cms.bloggyblog.repository.BloggerRepository
import spock.lang.Specification

class BloggerServiceSpec extends Specification {

    BloggerRepository userRepository = Mock()
    BloggerService userService = new BloggerService(userRepository)

    def "get all users"() {
        when:
        userService.getAllUsers()

        then:
        1 * userRepository.findAll()
    }

    def "save user"() {
        given:
        Blogger user = Blogger.builder().id(1L).name("Tester").build()

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
        1 * userRepository.save(Blogger.builder().id(id).name(name).build())
    }

    def "delete user"() {
        given:
        Blogger user = Blogger.builder().id(1L).name("Tester").build()

        when:
        userService.deleteById(user.getId())

        then:
        userRepository.deleteById(user.getId())
    }

}
