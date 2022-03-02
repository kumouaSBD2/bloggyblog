package org.cms.bloggyblog.mapper


import org.cms.bloggyblog.model.transfer.Entry
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class EntryMapperSpec extends Specification {

    @Shared
    private testPost = new Entry()

    def "DTO to Entity, then back to DTO"() {
        given:
        def DTO = testPost

        when:
        def Entity = EntryMapper.INSTANCE.map(DTO)

        then:
        Entity.getBody() == DTO.getBody()

        when:
        def result = EntryMapper.INSTANCE.map(Entity)

        then:
        result == DTO
    }

}

