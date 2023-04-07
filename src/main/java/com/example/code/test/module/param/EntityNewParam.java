package com.example.code.test.module.param;

public interface EntityNewParam<T> {

    void init();

    void valid();

    T doBuild();

    default T build() {
        init();
        valid();
        return doBuild();
    }

}
