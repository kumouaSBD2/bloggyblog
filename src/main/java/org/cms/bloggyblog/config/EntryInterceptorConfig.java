package org.cms.bloggyblog.config;

import org.cms.bloggyblog.interceptor.EntryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;


@Configuration
public class EntryInterceptorConfig implements WebMvcConfigurer {

    EntryInterceptor entryInterceptor;

    @Autowired
    public EntryInterceptorConfig(EntryInterceptor entryInterceptor) {
        this.entryInterceptor = entryInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(entryInterceptor);
    }
}
