package com.mblog.controller;

import com.mblog.bean.Article;
import com.mblog.bean.ArticleDigest;
import com.mblog.bean.Auth;
import com.mblog.bean.Category;
import com.mblog.service.ArticleDigestsService;
import com.mblog.service.AuthentificationService;
import com.mblog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
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
    @RequestMapping("/article/add")
    public  String addArticle(){
        return "add_article";
    }

    /**
     * Post article.
     * @param model Model from http.
     * @param request Http servlet request.
     * @return view_article page.
     */
    @RequestMapping(value = "/article/post")
    public String postArticle(Model model, HttpServletRequest request) {
        String html = request.getParameter("editormd-html-code");
        String markdown = request.getParameter("markdownDoc");
        String cateogry = request.getParameter("category");
        String tags = request.getParameter("tags");
        String title = request.getParameter("title");
        String digest = request.getParameter("digest");

        Article article = new Article();
        article.setHtmlDocument(html);
        article.setCategoryName(cateogry);
        article.setTags(Arrays.asList(tags.split(" ")));
        article.setTitle(title);
        article.setDigest(digest);
        model.addAttribute("article", article);
        return "view_article";
    }

    @RequestMapping(value = "/blog")
    public String showBlog() {
        return "blog";
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

    @RequestMapping(value = "/blog/articles")
    public @ResponseBody List<ArticleDigest> getArticleDigests(HttpServletRequest request) {
        String category_id = request.getParameter("category_id");
        int startIndex = Integer.valueOf(request.getParameter("start_index"));
        int count = Integer.valueOf(request.getParameter("count"));
        ArticleDigestsService service = new ArticleDigestsService();
        return service.getArticleDigests(category_id, startIndex, count);
    }
}
