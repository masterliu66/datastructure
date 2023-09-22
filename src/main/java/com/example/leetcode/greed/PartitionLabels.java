package com.example.leetcode.greed;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间
 */
public class PartitionLabels {

    @Test
    public void test() {
        assert "[9, 7, 8]".equals(partitionLabels("ababcbacadefegdehijhklij").toString());
    }

    public List<Integer> partitionLabels(String s) {

        int n = s.length();
        // 记录每个字符在字符串s中的最大下标
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> ans = new ArrayList<>();

        int start = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                ans.add(end - start + 1);
                start = end + 1;
            }
        }

        return ans;
    }

}
