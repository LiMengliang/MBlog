package com.mblog.test.controller;

import com.mblog.bean.Article;
import com.mblog.service.ArticleService;
import com.mblog.service.ArticleServiceImp;
import com.mblog.test.configuration.TestContext;
import com.mblog.test.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by root on 10/15/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml")
@ContextConfiguration(classes={TestContext.class}, locations = "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml")
@WebAppConfiguration
public class ArticleRestControllerUnitTests {

    private MockMvc mockMvc;
    private List<Article> articles;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ArticleService articleServiceMock;

    @Before
    public void setup() {
        articles = new ArrayList<Article>();
        articles.add(new Article() {
            {
                setId(1);
                setTitle("Article 1");
                setTags("Tag1 Tag2");
                setMarkDown("This is a text");
                setDate(new Date());
                setCategoryName("Category 1");
            }
        });
        articles.add(new Article() {
            {
                setId(2);
                setTitle("Article 2");
                setTags("Tag3 Tag4");
                setMarkDown("This is a text");
                setDate(new Date());
                setCategoryName("Category 1");
            }
        });
        articles.add(new Article() {
            {
                setId(2);
                setTitle("Article 3");
                setTags("Tag3 Tag4");
                setMarkDown("This is a text");
                setDate(new Date());
                setCategoryName("Category 2");
            }
        });
    }


    @Test
    public void getArticle_Exist_ReturnArticle() throws Exception {
        when(articleServiceMock.getArticleById("1")).thenReturn(articles.get(0));
        mockMvc.perform(get("/article/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8));

    }
}
