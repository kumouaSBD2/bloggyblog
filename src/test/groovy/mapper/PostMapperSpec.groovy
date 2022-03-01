package org.cms.bloggyblog.mapper

import org.cms.bloggyblog.model.transfer.PostDTO
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class PostMapperSpec extends Specification {

    @Shared
    private testPost = new PostDTO()

    def "DTO to Entity, then back to DTO"() {
        given:
        def DTO = testPost

        when:
        def Entity = PostMapper.INSTANCE.map(DTO)

        then:
        Entity.getBody() == DTO.getPostBody()

        when:
        def result = PostMapper.INSTANCE.map(Entity)

        then:
        result == DTO
    }

}

