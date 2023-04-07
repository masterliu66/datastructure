package com.example.code.test.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(AppCommException.class)
    @ResponseBody
    public RestError handleAppCommException(AppCommException ex, WebRequest request) {

        log.error("Converting Data Store exception to RestResponse", ex);

        return new RestError(ex.getErrorCode(), request.toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public RestError handleException(Exception ex, WebRequest request) {

        log.error("Converting Data Store exception to RestResponse", ex);

        return new RestError(0, request.toString(), ex.getMessage());
    }

}
