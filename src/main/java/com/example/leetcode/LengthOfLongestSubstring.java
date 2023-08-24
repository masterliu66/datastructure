package com.example.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 */
public class LengthOfLongestSubstring {

    @Test
    public void test() {

        assert lengthOfLongestSubstring("abcabcbb") == 3;
        assert lengthOfLongestSubstring("bbbbb") == 1;
        assert lengthOfLongestSubstring("pwwkew") == 3;
        assert lengthOfLongestSubstring("bbtablud") == 6;
        assert lengthOfLongestSubstring("aab") == 2;
    }

    public int lengthOfLongestSubstring(String s) {

        int n = s.length();
        if (n <= 1) {
            return n;
        }

        int right = 0;
        Set<Character> seen = new HashSet<>();
        int ans = 0;
        // 使用滑动窗口维护无重复的子串[left, right]
        for (int left = 0; left < n; left++) {
            while (right < n && !seen.contains(s.charAt(right))) {
                seen.add(s.charAt(right));
                right++;
            }
            ans = Math.max(ans, right - left);
            seen.remove(s.charAt(left));
        }

        return ans;
    }

    private int dp(String s) {
        // dp[i]表示包含第i个字符的不重复子串的最大长度
        // 由于dp[i]至于dp[i-1]相关, 可以使用一个变量优化空间
        int dp = 1;
        int res;
        // 保存已出现字符的最大下标
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        int ans = 1;
        for (int i = 1; i < s.length(); i++) {
            Integer index = map.get(s.charAt(i));
            if (index == null) {
                res = dp + 1;
            } else {
                res = Math.min(i - index, dp + 1);
            }
            dp = res;
            map.put(s.charAt(i), i);
            ans = Math.max(ans, res);
        }

        return ans;
    }

}
