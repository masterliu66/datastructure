package com.example.leetcode.hash;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 */
public class LengthOfLongestSubstring {

    @Test
    public void test() {

        assert 3 == lengthOfLongestSubstring("abcabcbb");
        assert 1 == lengthOfLongestSubstring("bbbbb");
        assert 3 == lengthOfLongestSubstring("pwwkew");
        assert 0 == lengthOfLongestSubstring("");
    }

    public int lengthOfLongestSubstring(String s) {

        int n = s.length();

        Set<Character> seen = new HashSet<>(n);

        int i = 0;
        int ans = 0;
        for (int j = 0; j < n; j++) {
            char c = s.charAt(j);
            while (seen.contains(c)) {
                seen.remove(s.charAt(i++));
            }
            ans = Math.max(ans, j - i + 1);
            seen.add(c);
        }

        return ans;
    }

}
