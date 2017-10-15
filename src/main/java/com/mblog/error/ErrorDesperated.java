package com.mblog.error;

import com.mblog.error.ErrorType;

/**
 * Created by root on 10/14/17.
 */
@Deprecated
public class ErrorDesperated {

    private ErrorType errorType;

    private String errorMessage;

    public ErrorDesperated(ErrorType errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }


    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
