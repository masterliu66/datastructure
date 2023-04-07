package com.example.leetcode;

/**
 * 639. 解码方法 II
 *
 * @author CCC
 * @date 2021/9/27 22:25
 */
public class NumDecodings {

    public static void main(String[] args) {

        String s = "**";

        System.out.println(new NumDecodings(s).numDecodings());
    }

    private String s;

    public NumDecodings(String s) {
        this.s = s;
    }

    public int numDecodings() {

        return numDecodings(s);
    }

    public int numDecodings(String s) {

        int n = s.length();

        // dp[i]表示前i位字符可以解码出方法最大数目
        long a = 0, b = 1, c = 0;
        final int MOD = 1000000007;
        for (int i = 1; i <= n; i++) {
            c = (s.charAt(i - 1) == '0' ? 0 : s.charAt(i - 1) == '*' ? 9 : 1) * b % MOD;
            if (i > 1) {
                long num = getNums(s.charAt(i - 2), s.charAt(i - 1));
                c = (c + a * num) % MOD;
            }
            a = b;
            b = c;
        }

        return (int) c;
    }

    private long getNums(char x, char y) {

        if (x == '*') {
            if (y == '*') {
                return 15;
            } else if (y <= '6') {
                return 2;
            } else {
                return 1;
            }
        } else if (x == '1') {
            return y == '*' ? 9 : 1;
        } else if (x == '2') {
            if (y == '*') {
                return 6;
            } else if (y <= '6') {
                return 1;
            }
        }

        return 0;
    }

}
