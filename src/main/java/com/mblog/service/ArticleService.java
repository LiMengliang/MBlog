package com.mblog.service;

import com.mblog.bean.Article;
import com.mblog.bean.ArticleDigest;
import com.mblog.common.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by root on 5/31/17.
 */
public class ArticleService {
    public void addArticle(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(article);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    public void removeArticle(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(article);
        session.getTransaction().commit();
    }

    public List<Article> getArticles(String category_name, int startIndex, int count) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try
        {
            Query query;
            if (category_name != null && !category_name.equals("")) {
                query = session.createQuery("from com.mblog.bean.Article where categoryName=:CategoryName");
                query.setParameter("CategoryName",category_name, Hibernate.STRING);
            }
            else {
                query = session.createQuery("from com.mblog.bean.Article");
            }
            List<Article> articles=query.list();
            session.getTransaction().commit();
            HibernateUtil.getSessionFactory().close();
            return articles;

        }
        catch (Exception e)
        {
        }
        return null;
    }

    public Article getArticleById(String article_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            Query query = session.createQuery("from com.mblog.bean.Article where id=:Id");
            query.setParameter("Id",article_id, Hibernate.STRING);
            List<Article> articles=query.list();
            session.getTransaction().commit();
            HibernateUtil.getSessionFactory().close();
            if (articles.size() > 0)
            {
                return articles.get(0);
            }

        }
        catch (Exception e)
        {
        }
        return null;
    }

    public void update(String article_id, Article article){
        Article oldArticle = getArticleById(article_id);
        removeArticle(oldArticle);
        addArticle(article);
    }
}
