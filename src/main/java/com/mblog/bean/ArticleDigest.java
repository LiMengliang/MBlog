package com.mblog.bean;

import java.util.Date;

/**
 * Created by Mengliang Li on 5/19/2017.
 */
public class ArticleDigest {

    private String title;
    private Date date;
    private String digest;

    public ArticleDigest(String title, Date date, String digest) {
        this.title = title;
        this.date = date;
        this.digest = digest;
    }

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



}
