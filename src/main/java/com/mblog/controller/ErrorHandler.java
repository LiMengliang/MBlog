package com.mblog.controller;

import com.mblog.error.exception.ArticleNotFoundException;
import com.mblog.error.exception.ServerInnerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by root on 10/14/17.
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ServerInnerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public java.lang.Error innerServerError(ServerInnerException exception) {
        return new java.lang.Error(exception.getMessage());
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error articleNotFound(ArticleNotFoundException exception) {
        return new Error(exception.getMessage());
    }
}
