package com.mblog.controller;

import com.mblog.bean.*;
import com.mblog.service.ArticleService;
import com.mblog.service.ArticleServiceImp;
import com.mblog.service.AuthentificationService;
import com.mblog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Page controller.
 * Created by Mengliang Li on 5/6/2017.
 */
@Controller
public class PageControllerDesperated {

//    /**
//     *
//     * @return
//     */
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public  Article test(Model model,HttpServletRequest request){
//        Article article = new Article();
//        article.setTitle("this is a test");
//        return article;
//    }
//    /**
//     *
//     * @return
//     */
//    @RequestMapping(value = "/article/add")
//    public  String addArticle(Model model,HttpServletRequest request){
//        model.addAttribute("article_creation_info", new ArticleCreationInfo(false, new Article()));
//        return "article_editor";
//    }


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
        ArticleService articleService = new ArticleServiceImp();
        List<Article> articles = articleService.getArticles("", 0, 0);
        // model.addAttribute("articles", articles);
        return articles;
    }

//    @RequestMapping(value = "/blog/article")
//    public String showArticle(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
//        request.setCharacterEncoding("UTF-8");
//        String article_id = request.getParameter("article_id");
//        ArticleServiceImp articleService = new ArticleServiceImp();
//        Article article = articleService.getArticleById(article_id);
//        model.addAttribute("article", article);
//        return "article_viewer";
//    }

//    @RequestMapping(value = "/article/delete")
//    public String deleteArticle(HttpServletRequest request) {
//        String article_id = request.getParameter("article_id");
//        ArticleServiceImp articleService = new ArticleServiceImp();
//        Article article = articleService.getArticleById(article_id);
//        articleService.removeArticle(article);
//        return "dashboard";
//    }

//    @RequestMapping(value = "/article/edit")
//    public String editArticle(Model model, HttpServletRequest request) {
//        String article_id = request.getParameter("article_id");
//        ArticleServiceImp articleService = new ArticleServiceImp();
//        Article article = articleService.getArticleById(article_id);
//        model.addAttribute("creationInfo", new ArticleCreationInfo(true, article));
//        return "article_editor";
//    }

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

//    @RequestMapping(value = "/blog/categories")
//    public @ResponseBody List<Category> getArticleCategories() {
//        CategoryService categoryService = new CategoryService();
//        return categoryService.getCategories();
//    }

    @RequestMapping(value = "/blog/article_digests")
    public @ResponseBody List<ArticleDigest> getArticleDigests(HttpServletRequest request) {
        String category_id = request.getParameter("category_id");
        int startIndex = Integer.valueOf(request.getParameter("start_index"));
        int count = Integer.valueOf(request.getParameter("count"));
        CategoryService categoryService = new CategoryService();

        ArticleService articleService = new ArticleServiceImp();
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
