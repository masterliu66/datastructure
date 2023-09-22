package com.example.leetcode.greed;

import org.junit.Test;

/**
 * 402. 移掉 K 位数字
 */
public class RemoveKdigits {

    @Test
    public void test() {
        assert "1219".equals(removeKdigits("1432219", 3));
        assert "200".equals(removeKdigits("10200", 1));
        assert "0".equals(removeKdigits("10", 2));
        assert "0".equals(removeKdigits("1234567890", 9));
    }

    public String removeKdigits(String num, int k) {

        int n = num.length();
        if (n <= k) {
            return "0";
        }

        // 维护一个单调递增的栈
        char[] stack = new char[n];
        int top = 0;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            // 当前数位小于栈顶时, 将栈顶弹出
            while (k > 0 && top > 0 && stack[top - 1] > c) {
                top--;
                k--;
            }
            stack[top++] = c;
        }
        // 移除的数位小于k的情况, 说明剩余所有数位单调递增, 从后往前进行移除
        while (k > 0) {
            top--;
            k--;
        }

        int start = 0;
        while (start < top && stack[start] == '0') {
            start++;
        }

        return start == top ? "0" : new String(stack, start, top - start);
    }

}
