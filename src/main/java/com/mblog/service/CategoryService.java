package com.mblog.service;

import com.mblog.bean.Category;
import com.mblog.bean.Stock;
import com.mblog.common.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void addCategoryIfNotExist(String categoryName) {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category category = new Category(categoryName);
        session.save(category);
        session.getTransaction().commit();
    }
}
