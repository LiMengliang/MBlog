package com.mblog.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mengliang Li on 5/19/2017.
 */
public class ArticleDigest implements Serializable {

    public static final long serialVersionUID = 1L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String title;
    private Date date;
    private String digest;
    private int id;
    private String tags;
    private String category;

    public ArticleDigest() {

    }

    public ArticleDigest(String title, String digest, String category, String tags) {
        this.title = title;
        this.date = date;
        this.digest = digest;
        this.category = category;
        this.tags = tags;
    }
}
