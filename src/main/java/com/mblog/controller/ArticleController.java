package com.mblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 10/5/17.
 *
 * Control page redirecting.
 */
@Controller
public class ArticleController {

    /**
     * Go to page to show article editor.
     * @param model The model.
     * @return article_editor page.
     */
    @RequestMapping("/article/new")
    public String showEmptyArticleEditor(Model model) {
        model.addAttribute("id", "new");
        return "article_editor";
    }

    /**
     * Go to page to show article.
     * @param id Article id.
     * @param model The model.
     * @return article_viewer page.
     */
    @RequestMapping("/article/{id}")
    public String showArticle(@PathVariable("id") String id, Model model) {
        model.addAttribute("article_id", id);
        return "article_viewer";
    }

    /**
     * Show article in article editor.
     * @param id The article id.
     * @param model The model.
     * @return article_editor page.
     */
    @RequestMapping("/article/{id}/edit")
    public String showArticleInEditor(@PathVariable("id") String id, Model model) {
        model.addAttribute("article_id", id);
        return "article_editor";
    }
}
