package com.mblog.error.exception;

/**
 * Created by root on 10/15/17.
 */
public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
