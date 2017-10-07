package com.mblog.controller;

import com.mblog.bean.Article;
import com.mblog.service.ArticleService;
import com.mblog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Rest controller of article.
 * Created by root on 10/5/17.
 */
@RestController
public class ArticleRestController {

    /**
     * Get article.
     * @param id The id.
     * @param request The http request.
     * @return Article instance.
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/rest/article/{id}", method = RequestMethod.GET, produces = "application/json")
    public Article getArticle(@PathVariable("id") String id, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ArticleService articleService = new ArticleService();
        Article article = articleService.getArticleById(id);
        return article;
    }

    /**
     * Save article.
     * @param article The article to be saved.
     * @param request The http request.
     * @return Article instance.
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/rest/article", method = RequestMethod.POST)
    public Article postArticle(@RequestBody Article article, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        // set creation time.
        Date date = new Date();
        article.setDate(date);

        // Update category table.
        CategoryService categoryService = new CategoryService();
        categoryService.addCategoryIfNotExist(article.getCategoryName());

        // Update article table;
        ArticleService articleService = new ArticleService();
        String articleId = request.getParameter("article_id");
        if (article.getId() != -1) {
            // update article.
            articleService.update(articleId, article);
        }
        else {
            // save new article.
            articleService.addArticle(article);
        }
        return article;
    }

    /**
     * delete an article.
     * @param id the id to be deleted.
     */
    @RequestMapping(value = "/rest/article/{id}", method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable("id") String id) {
        ArticleService articleService = new ArticleService();
        Article article = articleService.getArticleById(id);
        articleService.removeArticle(article);
    }
}
