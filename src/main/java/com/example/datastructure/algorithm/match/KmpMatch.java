package com.example.datastructure.algorithm.match;

/**
 * className KmpMatch
 * packageName com.example.datastructure.algorithm.match
 * description
 *
 * @author CCC
 * @date 2021/1/11 21:54
 */
public class KmpMatch {

    public static void main(String[] args) {

        String s1 = "abcde abcd abcdefghjk";

        String s2 = "bcdef";

        System.out.println(new KmpMatch().match(s1, s2));

    }

    public int match(String s1, String s2) {

        int i = 0;
        int j = 0;

        int[] next = getNext(s2);

        while (i < s1.length() && j < s2.length()) {

            if (j == -1 || s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }

        }

        if (j == s2.length()) {
            return i - j;
        }

        return -1;
    }

    /**
     * 获取一个字符串的部分匹配值表
     * @param dest 目标字符串
     * @return 部分匹配值表
     */
    private int[] getNext(String dest) {

        char[] p = dest.toCharArray();

        int[] next = new int[dest.length()];

        next[0] = -1;

        int j = 0;
        int k = -1;
        while (j < dest.length() - 1) {

            if (k == -1 || p[j] == p[k]) {
                if (p[++j] == p[++k]) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }

        }

        return next;
    }

}
