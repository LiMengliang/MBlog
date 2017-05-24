package com.mblog.service;

import com.mblog.bean.ArticleDigest;
import com.mblog.bean.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mengliang Li on 5/19/2017.
 */
public class ArticleDigestsService {
    private static ArticleDigest dummyDigest = new ArticleDigest("This is a dummy article digest",
            new Date(2017, 8, 23), "Food is my passion. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
    public List<ArticleDigest> getArticleDigests(String category_id, int startIndex, int count) {
        ArrayList<ArticleDigest> articleDigests = new ArrayList<ArticleDigest>();
        for(int i = 0; i < count; i++) {
            articleDigests.add(dummyDigest);
        }
        return articleDigests;
    }
}
