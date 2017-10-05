package com.mblog.controller;

import com.mblog.bean.*;
import com.mblog.service.ArticleService;
import com.mblog.service.AuthentificationService;
import com.mblog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Page controller.
 * Created by Mengliang Li on 5/6/2017.
 */
@Controller
public class PageController {

    /**
     *
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public  Article test(Model model,HttpServletRequest request){
        Article article = new Article();
        article.setTitle("this is a test");
        return article;
    }
    /**
     *
     * @return
     */
    @RequestMapping(value = "/article/add")
    public  String addArticle(Model model,HttpServletRequest request){
        model.addAttribute("article_creation_info", new ArticleCreationInfo(false, new Article()));
        return "add_article";
    }

    /**
     * Post article.
     * @param model Model from http.
     * @param request Http servlet request.
     * @return view_article page.
     */
    @RequestMapping(value = "/article/post")
    public String postArticle(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String html = request.getParameter("editormd-html-code");
        String markdown = request.getParameter("markdownDoc");
        String cateogry = request.getParameter("category");
        String tags = request.getParameter("tags");
        String title = request.getParameter("title");
        String digest = request.getParameter("digest");
        Date date = new Date();

        Article article = new Article();
        article.setDate(date);
        article.setMarkDown(markdown);
        article.setHtmlDocument(html);
        article.setCategoryName(cateogry);
        article.setTags(tags);
        article.setTitle(title);
        article.setDigest(digest);
        model.addAttribute("article", article);

        // Update category table.
        CategoryService categoryService = new CategoryService();
        categoryService.addCategoryIfNotExist(article.getCategoryName());

        // Update article table;
        ArticleService articleService = new ArticleService();
        String isEdit = request.getParameter("is_edit");
        String articleId = request.getParameter("article_id");
        if (isEdit.equals("true")) {
            articleService.update(articleId, article);
        }
        else {
            articleService.addArticle(article);
        }
        return "view_article";
    }

    @RequestMapping(value = "/blog")
    public String showBlog() {
        return "blog";
    }

    @RequestMapping(value = "/admin/manage")
    public String showManagementPage() {
        return "dashboard";
    }

    @RequestMapping(value = "/blog/articles")
    public @ResponseBody
    List<Article> getAllArticles(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        // int startIndex = Integer.valueOf(request.getParameter("start_index"));
        // int count = Integer.valueOf(request.getParameter("count"));
        ArticleService articleService = new ArticleService();
        List<Article> articles = articleService.getArticles("", 0, 0);
        // model.addAttribute("articles", articles);
        return articles;
    }

    @RequestMapping(value = "/blog/article")
    public String showArticle(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String article_id = request.getParameter("article_id");
        ArticleService articleService = new ArticleService();
        Article article = articleService.getArticleById(article_id);
        model.addAttribute("article", article);
        return "view_article";
    }

    @RequestMapping(value = "/article/delete")
    public String deleteArticle(HttpServletRequest request) {
        String article_id = request.getParameter("article_id");
        ArticleService articleService = new ArticleService();
        Article article = articleService.getArticleById(article_id);
        articleService.removeArticle(article);
        return "dashboard";
    }

    @RequestMapping(value = "/article/edit")
    public String editArticle(Model model, HttpServletRequest request) {
        String article_id = request.getParameter("article_id");
        ArticleService articleService = new ArticleService();
        Article article = articleService.getArticleById(article_id);
        model.addAttribute("creationInfo", new ArticleCreationInfo(true, article));
        return "add_article";
    }

    @RequestMapping(value = "/admin/login")
    public String adminLogin(HttpSession session) {
        return "admin_login";
    }

    @RequestMapping(value = "/admin/authentification")
    public @ResponseBody
    Auth verifyAdmin(HttpServletRequest request){
        String md5Password = request.getParameter("password");
        String userName = request.getParameter("user_name");
        AuthentificationService authService = new AuthentificationService();
        Auth auth = new Auth();
        if(authService.authentificate(userName, md5Password))
        {
            auth.set_authentificated(true);
            auth.set_token(123456);
        }
        else
        {
            auth.set_authentificated(false);
            auth.set_token(-1);
        }
        return auth;
    }

    @RequestMapping(value = "/blog/categories")
    public @ResponseBody List<Category> getArticleCategories() {
        CategoryService categoryService = new CategoryService();
        return categoryService.getCategories();
    }

    @RequestMapping(value = "/blog/article_digests")
    public @ResponseBody List<ArticleDigest> getArticleDigests(HttpServletRequest request) {
        String category_id = request.getParameter("category_id");
        int startIndex = Integer.valueOf(request.getParameter("start_index"));
        int count = Integer.valueOf(request.getParameter("count"));
        CategoryService categoryService = new CategoryService();

        ArticleService articleService = new ArticleService();
        List<Article> articles;
        if (category_id == "") {
            articles = articleService.getArticles("", 0, 0);
        }
        else {
            articles = articleService.getArticles(categoryService.getCategory(category_id).getName(), startIndex, count);
        }
        List<ArticleDigest> digests = new ArrayList<ArticleDigest>();
        for(Article article : articles) {
            digests.add(new ArticleDigest(article.getTitle(),
                    article.getDigest(),
                    article.getCategoryName(),
                    article.getTags(),
                    article.getDate(),
                    article.getId()));
        }
        return digests;
    }
}
