package com.mblog.service;

import com.mblog.bean.Article;

import java.util.List;

/**
 * Created by root on 10/15/17.
 */
public interface ArticleService {

    void addArticle(Article article);

    void removeArticle(Article article);

    List<Article> getArticles(String category_name, int startIndex, int count);

    Article getArticleById(String article_id);

    void update(String article_id, Article article);
}
