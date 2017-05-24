package com.mblog.bean;

/**
 * Created by Mengliang Li on 5/17/2017.
 */
public class Category {
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

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
