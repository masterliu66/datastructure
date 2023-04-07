package com.example.code.test.module.param;

import com.example.code.test.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ArrayParam<U, T extends EntityNewParam<U>> {

    private T[] params;

    public void valid() {
        Validator.validateNotNull(params, "Params must input");
    }

    public List<U> build() {

        valid();

        List<U> result = new ArrayList<>();
        for (T param : params) {
            result.add(param.build());
        }

        return result;
    }

    public T[] getParams() {
        return params;
    }

    public void setParams(T[] params) {
        this.params = params;
    }
}
