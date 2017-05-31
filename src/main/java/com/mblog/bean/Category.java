package com.mblog.bean;

import java.io.Serializable;

/**
 * Created by Mengliang Li on 5/17/2017.
 */
public class Category implements Serializable {

    public static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }


}
