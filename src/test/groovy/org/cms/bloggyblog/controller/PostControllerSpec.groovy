package org.cms.bloggyblog.controller

import org.cms.bloggyblog.BloggyBlogApplication
import org.cms.bloggyblog.model.entity.Post
import org.cms.bloggyblog.model.entity.User
import org.cms.bloggyblog.repository.PostRepository
import org.cms.bloggyblog.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
@SpringBootTest(classes = BloggyBlogApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private UserRepository userRepository = Mock()

    @Autowired
    private PostRepository postRepository = Mock()

    def "when POST is performed with no user set"() {
        given:
        String uri = "/blog-posts"
        String contentType = "application/json"
        String body = "{\n" + "    \"postTitle\": \"Hello\",\n" + "    \"postBody\": \"Hello, World!\"\n" + "}"

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(body)
                .contentType(contentType))

        then:
        response.andExpect(status().isOk())
    }

    def "when PUT is performed with user set"() {
        given:
        User testUser = new User()
        testUser.setId(1L)
        testUser.setName("Tester")
        String uri = "/blog-posts/1/Test"
        String contentType = "application/json"
        String body = "Testing"
        userRepository.save(testUser)

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .content(body)
                .contentType(contentType))
                .andExpect(status().isOk())

        then:
        response.andExpect(status().isOk())
    }

    def "when DELETE is performed with valid ID"() {
        given:
        String uri = "/blog-posts/1"
        Post post = new Post()
        post.setId(1L)
        post.setBody("Dummy1")
        post.setTitle("Dummy2")
        postRepository.save(post)

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .delete(uri))

        then:
        response.andExpect(status().isOk())
    }

}
