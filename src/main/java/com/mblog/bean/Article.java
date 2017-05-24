package com.mblog.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Article.
 * Created by Mengliang Li on 5/8/2017.
 */
public class Article implements Serializable {

    private String title;
    private String markDown;
    private String htmlDocument;
    private String categoryName;
    private List<String> tags;
    private String digest;

    /**
     * Get title.
     * @return Title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title.
     * @param title Title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get markdown content.
     * @return Markdown content.
     */
    public String getMarkDown() {
        return markDown;
    }

    /**
     * Set markdown.
     * @param markDown Markdown content.
     */
    public void setMarkDown(String markDown) {
        this.markDown = markDown;
    }

    /**
     *get html document.
     * @return
     */
    public String getHtmlDocument() {
        return htmlDocument;
    }

    /**
     * Set html document.
     * @param htmlDocument The html document.
     */
    public void setHtmlDocument(String htmlDocument) {
        this.htmlDocument = htmlDocument;
    }

    /**
     * Get tags.
     * @return Tags.
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Set tags.
     * @param tags Tags.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Get category name.
     * @return Category name.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Set category name.
     * @param categoryName Category name.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Add tag.
     * @param tag The tag.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Remove tag.
     * @param tag The tag to be removed.
     */
    public void removeTag(String tag) {
        if(tags.contains(tag)) {
            tags.remove(tag);
        }
    }

    /**
     * Get digest.
     * @return
     */
    public String getDigest() {
        return digest;
    }

    /**
     * Set digest.
     * @param digest Digest.
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }
}
