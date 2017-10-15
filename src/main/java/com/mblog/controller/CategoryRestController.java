package com.mblog.controller;

import com.mblog.bean.Category;
import com.mblog.common.utils.Urls;
import com.mblog.error.exception.ServerInnerException;
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
    @RequestMapping(Urls.REST + Urls.CATEGORIES)
    public List<Category> getCategories() {
        try {
            CategoryService categoryService = new CategoryService();
            return categoryService.getCategories();
        } catch(Exception e) {
            throw new ServerInnerException("Can't get categories, inner excpeiton happens",e);
        }
    }
}
