package com.mblog.test.configuration;

import com.mblog.service.ArticleService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by root on 10/15/17.
 */
@Configuration
public class TestContext {

    @Bean
    public ArticleService articleService() {
        return Mockito.mock(ArticleService.class);
    }

}
