package com.mblog.service;

import com.mblog.bean.ArticleDigest;
import com.mblog.bean.Category;
import com.mblog.common.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mengliang Li on 5/19/2017.
 */
public class ArticleDigestsService {

    public void addArticleDigests(ArticleDigest articleDigest)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(articleDigest);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    public List<ArticleDigest> getArticleDigests(String category_name, int startIndex, int count) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            Query query = session.createQuery("from com.mblog.bean.ArticleDigest where category=:CategoryName");
            query.setParameter("CategoryName",category_name, Hibernate.STRING);
            List<ArticleDigest> articleDigests=query.list();
            session.getTransaction().commit();
            HibernateUtil.getSessionFactory().close();
            return articleDigests;

        }
        catch (Exception e)
        {
            int a = 0;
        }
        return null;
    }
}
