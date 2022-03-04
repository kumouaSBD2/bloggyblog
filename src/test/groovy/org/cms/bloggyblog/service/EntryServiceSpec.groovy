package org.cms.bloggyblog.service

import org.cms.bloggyblog.model.entity.Entry
import org.cms.bloggyblog.model.entity.User
import org.cms.bloggyblog.repository.EntryRepository
import spock.lang.Specification

class EntryServiceSpec extends Specification {

    EntryRepository entryRepository = Mock()
    EntryService entryService = new EntryService(entryRepository)

    def "when GET all entries"() {
        when:
        entryService.getAllEntries()

        then:
        1 * entryRepository.findAll()
    }

    def "when save an entry"() {
        given:
        Entry entry = Entry.builder().build()

        when:
        entryService.save(entry)

        then:
        1 * entryRepository.save(entry)
    }

    def "when updating a post with a valid user ID"() {
        given:
        User user = User.builder().id(1L).name("Tester").build()

        when:
        entryService.upsert(1L, "Test", "Testing", user)

        then:
        1 * entryRepository.save(Entry.builder().id(1L).title("Test").body("Testing").user(user).build())

    }

    def "deleting entry by id"() {
        given:
        User user = User.builder().id(1L).name("Tester").build()

        when:
        entryService.deleteById(user.getId())

        then:
        entryRepository.deleteById(user.getId())
    }

}
