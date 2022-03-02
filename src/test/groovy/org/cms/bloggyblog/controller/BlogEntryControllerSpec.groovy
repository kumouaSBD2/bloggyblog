package org.cms.bloggyblog.controller

import org.cms.bloggyblog.BloggyBlogApplication
import org.cms.bloggyblog.mapper.EntryMapper
import org.cms.bloggyblog.model.entity.Entry
import org.cms.bloggyblog.model.entity.User
import org.cms.bloggyblog.repository.EntryRepository
import org.cms.bloggyblog.repository.UserRepository
import org.codehaus.jackson.map.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

import javax.transaction.Transactional

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(classes = BloggyBlogApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlogEntryControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc
    private ObjectMapper objectMapper = new ObjectMapper()


    private UserRepository userRepository = Mock()

    private EntryRepository entryRepository = Mock()

    def "when POST is performed with no user set"() {
        given:
        String uri = "/blog-posts"
        String contentType =  "application/json"
        String title = "Hello"
        String body = "Hello, World!"
        Entry entry = Entry.builder().body(body).title(title).build()
        String requestBodyJson =
                objectMapper.writeValueAsString(
                        EntryMapper.INSTANCE.map(entry))
        entryRepository.save(entry) >> entry



        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBodyJson)

                .contentType(contentType))

        then:
        response.andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath('$.title').value(title))
    }

    @Transactional
    def "when PUT is performed with user set"() {
        given:
        User testUser = new User()
        testUser.setId(1L)
        testUser.setName("Tester")
        Long id = userRepository.save(testUser).getId()
        String uri = "/blog-posts/$id/Test"
        String contentType = "application/json"
        String body = "Testing"
        userRepository.save(testUser)

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .content(body)
                .contentType(contentType))


        then:
        response.andExpect(status().isCreated())

    }


    def "when DELETE is performed with valid ID"() {
        given:
        Entry post = new Entry()
        post.setBody("Dummy1")
        post.setTitle("Dummy2")
        Long id = postRepository.save(post).getId()
        String uri = "/blog-posts/$id"


        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .delete(uri))

        then:
        response.andExpect(status().isOk())
    }

}
