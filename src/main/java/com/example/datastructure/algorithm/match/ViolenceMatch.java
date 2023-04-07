package com.example.datastructure.algorithm.match;

/**
 * className ViolenceMatch
 * packageName com.example.datastructure.algorithm.match
 * description
 *
 * @author CCC
 * @date 2021/1/11 21:47
 */
public class ViolenceMatch {

    public static void main(String[] args) {

        String s1 = "abcde abcd abcdefghjk";

        String s2 = "abcdef";

        System.out.println(new ViolenceMatch().match(s1, s2));
    }

    public int match(String s1, String s2) {

        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == s2.length()) {
            return i - j;
        }

        return -1;
    }

}
