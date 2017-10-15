package com.mblog.error.exception;

/**
 * Created by root on 10/14/17.
 */
public class ServerInnerException extends RuntimeException {

    public ServerInnerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
