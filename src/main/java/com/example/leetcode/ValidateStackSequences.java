package com.example.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 */
public class ValidateStackSequences {

    @Test
    public void test() {

        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};

        ValidateStackSequences stackSequences = new ValidateStackSequences();

        assert stackSequences.validateStackSequences(pushed, popped);

        popped = new int[] {4,3,5,1,2};

        assert !stackSequences.validateStackSequences(pushed, popped);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        int n = pushed.length;
        if (n != popped.length) {
            return false;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        for (int push : pushed) {
            stack.push(push);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }

        return stack.isEmpty();
    }

}
