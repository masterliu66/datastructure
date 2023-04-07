package com.example.datastructure.tree;

import com.example.datastructure.entity.Entity;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * className ArrayBinaryTree
 * packageName com.example.datastructure.tree
 * description
 *
 * @author CCC
 * @date 2020/12/6 23:38
 */
public class ArrayBinaryTree<E extends Entity> {

    private Object[] elementData;

    private int size = 0;

    public ArrayBinaryTree() {
        super();
        this.elementData = new Object[] {};
    }

    public ArrayBinaryTree(int size) {
        super();
        this.elementData = new Object[size];
    }

    public void infixOrderList() {

        if (size == 0) {
            System.out.println("[]");
            return;
        }

        infixOrderList(0, System.out::println);
    }

    public void infixOrderList(int index, Consumer<E> consumer) {

        if (index >= size) {
            return;
        }

        // 向左递归
        infixOrderList(2 * index + 1, consumer);

        consumer.accept(elementData(index));

        // 向右递归
        infixOrderList(2 * index + 2, consumer);
    }

    public void add(E e) {

        if (size == elementData.length) {
            int newSize = size == 0 ? 16 : size << 1;
            elementData = Arrays.copyOf(elementData, newSize);
        }

        elementData[size++] = e;
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

}
