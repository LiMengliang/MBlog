package com.mblog.service;

/**
 * Created by Mengliang Li on 5/13/2017.
 */
public class AuthentificationService {
    public boolean authentificate(String userName, String md5Password) {
        return userName.equals("meli") && md5Password.equals("92d24fcccfc9cb58a45d8ccd51d33c19");
    }
}
