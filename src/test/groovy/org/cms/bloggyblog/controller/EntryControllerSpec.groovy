package org.cms.bloggyblog.controller

import org.cms.bloggyblog.BloggyBlogApplication
import org.cms.bloggyblog.mapper.EntryMapper
import org.cms.bloggyblog.model.entity.Entry
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

    def "when POST is performed with no blogger set"() {
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

    def "no html in input should return 201"() {
        given:
        Long id = 1L
        String title = "Test"
        String body = "This is a test"
        Entry entry = Entry.builder().id(id).body(body).title(title).build()
        String requestBodyJson =
                objectMapper.writeValueAsString(
                        EntryMapper.INSTANCE.map(entry))
        entryRepository.save(entry) >> entry
        String contentType = "application/json"
        String uri = "/blog-entries"

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBodyJson)
                .contentType(contentType))

        then:
        response.andExpect(status().isCreated())
    }

    def "html in input should return 400"() {
        given:
        Long id = 1L
        String title = "<h1>Test</h1>"
        String body = "<p>This is a test</p>"
        String contentType = "application/json"
        String uri = "/blog-entries"
        Entry entry = Entry.builder().id(id).body(body).title(title).build()
        String requestBodyJson = objectMapper.writeValueAsString(EntryMapper.INSTANCE.map(entry))

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBodyJson)
                .contentType(contentType))

        then:
        response.andExpect(status().is4xxClientError())
    }

    def "violation message when html present in title and body"() {
        given:
        Long id = 1L
        String title = "<h1>Test</h1>"
        String body = "<p>This is a test</p>"
        String contentType = "application/json"
        String uri = "/blog-entries"
        Entry entry = Entry.builder().id(id).body(body).title(title).build()
        String requestBodyJson = objectMapper.writeValueAsString(EntryMapper.INSTANCE.map(entry))
        String violationMessage = "No HTML is allowed"

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBodyJson)
                .contentType(contentType))

        then:
        response.andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath('$.violations[0].message').value(violationMessage))
                .andExpect(MockMvcResultMatchers.jsonPath('$.violations[1].message').value(violationMessage))
    }

    def "violation message when html present in title"() {
        given:
        Long id = 1L
        String title = "<h1>Test</h1>"
        String body = "This is a test"
        String contentType = "application/json"
        String uri = "/blog-entries"
        Entry entry = Entry.builder().id(id).body(body).title(title).build()
        String requestBodyJson = objectMapper.writeValueAsString(EntryMapper.INSTANCE.map(entry))
        String violationMessage = "No HTML is allowed"

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBodyJson)
                .contentType(contentType))

        then:
        response.andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath('$.violations[0].message').value(violationMessage))
    }

    def "violation message when html present in body"() {
        given:
        Long id = 1L
        String title = "Test"
        String body = "<div>This is a test</div>"
        String contentType = "application/json"
        String uri = "/blog-entries"
        Entry entry = Entry.builder().id(id).body(body).title(title).build()
        String requestBodyJson = objectMapper.writeValueAsString(EntryMapper.INSTANCE.map(entry))
        String violationMessage = "No HTML is allowed"

        when:
        def response = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(requestBodyJson)
                .contentType(contentType))

        then:
        response.andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath('$.violations[0].message').value(violationMessage))
    }

}
