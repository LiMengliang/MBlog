package com.mblog.bean;

import com.mblog.common.IJsonable;
import com.google.gson.Gson;

/**
 * Created by Mengliang Li on 5/13/2017.
 */
public class Auth implements IJsonable
 {

    private int token;
    private boolean authentificated;

    public boolean is_authentificated() {
        return authentificated;
    }

    public void set_authentificated(boolean _authentificated) {
        this.authentificated = _authentificated;
    }

    public int get_token() {
        return token;
    }

    public void set_token(int _token) {
        this.token = _token;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Auth fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Auth.class);
    }
}
