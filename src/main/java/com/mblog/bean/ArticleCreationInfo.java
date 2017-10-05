package com.mblog.bean;

/**
 * Created by root on 7/12/17.
 */
public class ArticleCreationInfo {
    public boolean is_Edit() {
        return _edit;
    }

    public Article get_Article() {
        return _article;
    }

    private boolean _edit;
    private Article _article;

    public ArticleCreationInfo(boolean isEdit, Article article) {
        _edit = isEdit;
        _article = article;
    }
}
