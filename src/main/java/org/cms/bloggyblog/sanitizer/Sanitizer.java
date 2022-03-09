package org.cms.bloggyblog.sanitizer;

import org.cms.bloggyblog.model.entity.Entry;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Component;

@Component
public class Sanitizer {

    public Entry sanitize(Entry entry) {

        PolicyFactory policy =
                Sanitizers.FORMATTING
                        .and(Sanitizers.BLOCKS)
                        .and(Sanitizers.LINKS)
                        .and(Sanitizers.IMAGES)
                        .and(Sanitizers.TABLES);
        String safeTitle = policy.sanitize(entry.getTitle());
        String safeBody = policy.sanitize(entry.getBody());

        entry.setTitle(safeTitle);
        entry.setBody(safeBody);

        return entry;
    }

}
