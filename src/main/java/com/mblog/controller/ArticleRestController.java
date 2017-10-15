package com.mblog.controller;

import com.mblog.bean.Article;
import com.mblog.common.utils.Urls;
import com.mblog.error.exception.ArticleNotFoundException;
import com.mblog.error.exception.ServerInnerException;
import com.mblog.service.ArticleService;
import com.mblog.service.ArticleServiceImp;
import com.mblog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    @RequestMapping(value = Urls.REST + Urls.ARTICLE + Urls.NUMERICID, method = {GET}, produces = "application/json")
    public Article getArticle(@PathVariable("id") String id, HttpServletRequest request)
            throws UnsupportedEncodingException {

        Article article = null;
        try {
            request.setCharacterEncoding("UTF-8");
            ArticleService articleService = new ArticleServiceImp();
            article = articleService.getArticleById(id);
        } catch(Exception e) {
            throw new ServerInnerException(e.getMessage(), e);
        }
        if (article != null) {
            return article;
        }
        throw new ArticleNotFoundException("Can't find the article");
    }

    /**
     * Save article.
     * @param article The article to be saved.
     * @param request The http request.
     * @return Article instance.
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = Urls.REST + Urls.ARTICLE, method = {POST})
    public Article postArticle(@RequestBody Article article, HttpServletRequest request) {

        try {
            request.setCharacterEncoding("UTF-8");

            // set creation time.
            Date date = new Date();
            article.setDate(date);

            // Update category table.
            CategoryService categoryService = new CategoryService();
            categoryService.addCategoryIfNotExist(article.getCategoryName());

            // Update article table;
            ArticleService articleService = new ArticleServiceImp();
            String articleId = request.getParameter("article_id");
            if (article.getId() != -1) {
                // update article.
                articleService.update(articleId, article);
            }
            else {
                // save new article.
                articleService.addArticle(article);
            }
        } catch(Exception e) {
            throw new ServerInnerException(e.getMessage(), e);
        }
        return article;
    }

    /**
     * delete an article.
     * @param id the id to be deleted.
     */
    @RequestMapping(value = Urls.REST + Urls.ARTICLE + Urls.NUMERICID, method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable("id") String id) {
        try {
            ArticleService articleService = new ArticleServiceImp();
            Article article = articleService.getArticleById(id);
            if (article == null) {
                throw new ArticleNotFoundException("Article to be deleted is not found");
            }
            articleService.removeArticle(article);
        } catch(Exception e) {
            throw new ServerInnerException("Server inner exception happens", e);
        }
    }
}
