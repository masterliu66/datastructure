package com.example.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 30. 包含min函数的栈
 */
public class MinStack {

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assert minStack.min() == -3;
        minStack.pop();
        assert minStack.top() == 0;
        assert minStack.min() == -2;
    }

    private final Deque<Long> stack;

    private long min;

    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new ArrayDeque<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push((long) 0);
            min = x;
            return;
        }
        stack.push(x - min);
        min = Math.min(min, x);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        if (stack.peek() < 0) {
            min = min - stack.pop();
            return;
        }
        stack.pop();
    }

    public int top() {
        if (stack.peek() > 0) {
            return (int) (stack.peek() + min);
        }
        return min();
    }

    public int min() {
        return Math.toIntExact(min);
    }

}
