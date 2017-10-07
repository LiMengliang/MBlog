package com.mblog.controller;

import com.mblog.bean.Category;
import com.mblog.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Category rest controller.
 * Created by root on 10/5/17.
 */
@RestController
public class CategoryRestController {

    /**
     * Get categories.
     * @return Categories.
     */
    @RequestMapping("/rest/categories")
    public List<Category> getCategories() {
        CategoryService categoryService = new CategoryService();
        return categoryService.getCategories();
    }
}
