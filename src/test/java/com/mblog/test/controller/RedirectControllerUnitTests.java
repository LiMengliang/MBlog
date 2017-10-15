package com.mblog.test.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by root on 10/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml")
@WebAppConfiguration
public class RedirectControllerUnitTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void showEmptyArticleEditor_RedirectCorrectly() throws Exception {
        mockMvc.perform(get("/article/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("article_editor"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/article_editor.jsp"))
                .andExpect(model().attribute("id", "new"));
    }

    @Test
    public void showEmptyArticleEditor_UsingPostMethod_ExceptionThrown() throws Exception {
        MvcResult result = mockMvc.perform(post("/article/new"))
                .andReturn();
        Assert.assertTrue(HttpRequestMethodNotSupportedException.class.isAssignableFrom(result.getResolvedException().getClass()));

    }

    @Test
    public void showArticle_RedirectCorrectly() throws Exception {
        mockMvc.perform(get("/article/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("article_viewer"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/article_viewer.jsp"))
                .andExpect(model().attribute("article_id", "1"));
    }
}
