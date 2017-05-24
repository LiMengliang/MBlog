package com.mblog.service;

import com.mblog.bean.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengliang Li on 5/19/2017.
 */
public class CategoryService {
    static Category dummyCategory = new Category(123456, "Category");

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        for(int i = 0; i < 10; i++) {
            categories.add(dummyCategory);
        }
        return categories;
    }
}
