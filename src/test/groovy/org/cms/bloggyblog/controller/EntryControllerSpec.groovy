package org.cms.bloggyblog.controller

import org.cms.bloggyblog.BloggyBlogApplication
import org.cms.bloggyblog.mapper.EntryMapper
import org.cms.bloggyblog.model.entity.Entry
import org.cms.bloggyblog.model.entity.User
import org.cms.bloggyblog.repository.EntryRepository
import org.codehaus.jackson.map.ObjectMapper
import org.jeasy.random.EasyRandom
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
class EntryControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc
    private ObjectMapper objectMapper = new ObjectMapper()
    private EntryRepository entryRepository = Mock()

    def "when POST is performed with no user set"() {
        given:
        EasyRandom easyRandom = new EasyRandom()
        String uri = "/blog-entries"
        String contentType = "application/json"
        String title = easyRandom.nextObject(String.class)
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

    def "when PUT is performed with user set"() {
        given:
        EasyRandom easyRandom = new EasyRandom()
        String userName = easyRandom.nextObject(String.class)
        User testUser = User.builder().name(userName).build()
        String name = testUser.getName()

        mvc.perform(MockMvcRequestBuilders
                .post("/blog-users/$name"))

        String title = "Test"
        String contentType = "application/json"
        String body = "Testing"

        Entry entry = Entry.builder().body(body).build()
        String requestBodyJson =
                objectMapper.writeValueAsString(
                        EntryMapper.INSTANCE.map(entry))
        entryRepository.save(entry) >> entry
        String uri = "/blog-entries/2/$title"


        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .content(requestBodyJson)
                .contentType(contentType))

        then:
        response.andExpect(status().isCreated())
    }


    def "when DELETE is performed with valid ID"() {
        given:
        Long id = 1L
        String title = "Hello"
        String body = "Hello, World!"
        Entry entry = Entry.builder().id(id).body(body).title(title).build()
        String requestBodyJson =
                objectMapper.writeValueAsString(
                        EntryMapper.INSTANCE.map(entry))
        entryRepository.save(entry) >> entry
        String contentType = "application/json"
        String uri = "/blog-entries/$id"

        mvc.perform(MockMvcRequestBuilders
                .post("/blog-entries")
                .content(requestBodyJson)
                .contentType(contentType))

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .delete(uri))

        then:
        response.andExpect(status().isOk())
    }

}
