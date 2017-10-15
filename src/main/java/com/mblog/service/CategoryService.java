package com.mblog.service;

import com.mblog.bean.Category;
import com.mblog.common.utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Mengliang Li on 5/19/2017.
 */
public class CategoryService {

    public List<Category> getCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List categories = session.createQuery("from com.mblog.bean.Category").list();
        session.getTransaction ().commit();
        HibernateUtil.getSessionFactory().close();
        return categories;
    }

    public Category getCategory(String category_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from com.mblog.bean.Category where id=:categoryId");
        query.setParameter("categoryId",category_id, Hibernate.STRING);
        List<Category> categories=query.list();
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        if (categories.size() > 0)
        {
            return (Category)categories.get(0);
        }
        return null;
    }

    public void addCategoryIfNotExist(String categoryName) {
        List<Category> categories = getCategories();
        for(Category category : categories)
        {
            if (category.getName().equals(categoryName))
            {
                return;
            }
        }
        try
        {
            System.out.println("Maven + Hibernate + MySQL");
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Category category = new Category(categoryName);
            session.save(category);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            int a = 0;
        }
    }
}
