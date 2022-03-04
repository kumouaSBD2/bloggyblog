package org.cms.bloggyblog.controller

import org.cms.bloggyblog.BloggyBlogApplication
import org.cms.bloggyblog.model.entity.User
import org.cms.bloggyblog.repository.UserRepository
import org.codehaus.jackson.map.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(classes = BloggyBlogApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerSpec extends Specification {

    @Autowired
    MockMvc mvc

    ObjectMapper objectMapper = new ObjectMapper()
    UserRepository userRepository = Mock()

    def "adding new user"() {
        given:
        String name = "Tester"
        String uri = "/blog-users/$name"

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .post(uri))

        then:
        response.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath('$.name').value(name))

    }

    def "updating a user"() {
        given:
        Long id = 1L
        String name = "Tester"
        String uri = "/blog-users/$id"
        User user = User.builder().id(id).name(name).build()
        userRepository.save(user) >> user

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .put(uri)
        .content("Real Tester")
        .contentType("application/json"))

        then:
        response
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath('$.name').value("Real Tester"))
    }

    def "delete user"() {
        given:
        Long id = 1L
        String name = "Tester"
        User user = User.builder().id(id).name(name).build()
        String uriDelete = "/blog-users/$id"
        String uriPost = "/blog-users/$name"

        mvc.perform(MockMvcRequestBuilders.post(uriPost))

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .delete(uriDelete))

        then:
        response.andExpect(status().isOk())
    }

}
