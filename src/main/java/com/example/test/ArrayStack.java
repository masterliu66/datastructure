package com.example.test;

/**
 * className ArrayStack
 * packageName com.example.test
 * description
 *
 * @author CCC
 * @date 2020/8/11 21:50
 */
public class ArrayStack<T> {

    private final int size;

    private final T[] arr;

    private int top;

    public ArrayStack(T[] arr) {
        super();
        this.size = arr.length;
        this.arr = arr;
        this.top = -1;
    }

    public boolean full() {
        return top == size - 1;
    }

    public boolean empty() {
        return top == -1;
    }

    public T peek() {

        if (empty()) {
            throw new RuntimeException("栈空");
        }

        return arr[top];
    }

    public void push(T t) {

        if (full()) {
            System.out.println("栈满");
            return;
        }

        arr[++top] = t;
    }

    public T pop() throws RuntimeException {

        if (empty()) {
            throw new RuntimeException("栈空");
        }

        return arr[top--];
    }

}
