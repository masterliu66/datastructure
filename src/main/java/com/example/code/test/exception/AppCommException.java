package com.example.code.test.exception;

public class AppCommException extends RuntimeException  {

    private int errorCode;

    public AppCommException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}