package com.example.leetcode.back;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 93. 复原 IP 地址
 */
public class RestoreIpAddresses {

    @Test
    public void test() {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("0000"));
        System.out.println(restoreIpAddresses("101023"));
    }

    private List<String> ans;

    public List<String> restoreIpAddresses(String s) {

        this.ans = new ArrayList<>();

        int n = s.length();
        if (n < 4) {
            return ans;
        }

        int[] intIp = new int[4];
        Arrays.fill(intIp, -1);
        backtracking(s, intIp, 0, 0);

        return ans;
    }

    private void backtracking(String s, int[] intIp, int chatAt, int idx) {

        if (chatAt == s.length() && intIp[intIp.length - 1] >= 0) {
            this.ans.add(int2Str(intIp));
            return;
        }

        if (chatAt == s.length() || idx == 4) {
            return;
        }

        // 选择当前字符
        char c = s.charAt(chatAt);
        int num = intIp[idx];
        int newNum = (num < 0 ? 0 : num * 10) + c - '0';
        // 过滤前导0
        if (num != 0 && newNum <= 255) {
            intIp[idx] = newNum;
            backtracking(s, intIp, chatAt + 1, idx);
            intIp[idx] = num;
        }

        // 直接跳过当前字符
        if (intIp[idx] >= 0) {
            backtracking(s, intIp, chatAt, idx + 1);
        }
    }

    private String int2Str(int[] intIp) {

        StringBuilder builder = new StringBuilder();
        for (int num : intIp) {
            builder.append(num).append(".");
        }

        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

}
