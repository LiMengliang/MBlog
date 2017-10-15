package com.mblog.controller;

import com.mblog.common.utils.Urls;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by root on 10/5/17.
 *
 * Control page redirecting.
 */
@Controller
public class RedirectController {

    /**
     * Go to page to show article editor.
     * @param model The model.
     * @return article_editor page.
     */
    @RequestMapping(value = Urls.ARTICLE + Urls.NEW, method = {GET})
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
    @RequestMapping(value = Urls.ARTICLE + Urls.NUMERICID, method = {GET})
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
    @RequestMapping(value = Urls.ARTICLE + Urls.NUMERICID + Urls.EDIT, method = {GET})
    public String showArticleInEditor(@PathVariable("id") String id, Model model) {
        model.addAttribute("article_id", id);
        return "article_editor";
    }
}
