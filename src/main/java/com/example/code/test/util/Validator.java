package com.example.code.test.util;

import cn.hutool.core.exceptions.ValidateException;
import com.example.code.test.exception.AppCommException;

public class Validator {

    public static <T> void validateNotNull(T value, String errorMsgTemplate) throws ValidateException {
        if (null == value) {
            throw new AppCommException(0, errorMsgTemplate);
        }
    }

}
