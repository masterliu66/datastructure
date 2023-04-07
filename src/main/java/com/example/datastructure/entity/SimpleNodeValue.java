package com.example.datastructure.entity;

/**
 * className SimpleNodeValue
 * packageName com.example.datastructure.entity
 * description
 *
 * @author CCC
 * @date 2020/12/16 22:18
 */
public class SimpleNodeValue implements Value {

    private int value;

    public SimpleNodeValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SimpleNodeValue{" +
                "value=" + value +
                '}';
    }

}
