package com.example.leetcode.sliding;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 */
public class MinWindow {

    @Test
    public void test() {
        assert "BANC".equals(minWindow("ADOBECODEBANC", "ABC"));
        assert "a".equals(minWindow("a", "a"));
        assert "".equals(minWindow("a", "aa"));
    }

    public String minWindow(String s, String t) {

        int m = s.length();
        int n = t.length();
        if (m < n) {
            return "";
        }

        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        int i = 0;
        int minLen = m + 1;
        int minLeft = 0;
        int minRight = 0;
        for (int j = 0; j < m; j++) {
            char c = s.charAt(j);
            if (!counts.containsKey(c)) {
                continue;
            }
            counts.put(c, counts.get(c) - 1);
            while (check(counts)) {
                // 区间[i,j]包含 t 所有字符
                int len = j - i + 1;
                // 记录最小区间长度的左右端点
                if (len < minLen) {
                    minLen = len;
                    minLeft = i;
                    minRight = j;
                }
                char c2 = s.charAt(i++);
                counts.computeIfPresent(c2, (k, v) -> v + 1);
            }
        }

        return minLen == m + 1 ? "" : s.substring(minLeft, minRight + 1);
    }

    private boolean check(Map<Character, Integer> counts) {
        return counts.values().stream().noneMatch(count -> count > 0);
    }

}
