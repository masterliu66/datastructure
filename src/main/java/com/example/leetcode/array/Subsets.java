package com.example.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 */
public class Subsets {

    @Test
    public void test() {

        List<List<Integer>> ans = subsets(new int[]{1, 2, 3});
        assert "[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]".equals(ans.toString());
    }

    public List<List<Integer>> subsets(int[] nums) {

        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        if (n == 0) {
            return ans;
        }

        int size = 1 << n;
        for (int i = 1; i < size; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    list.add(nums[j]);
                }
            }
            ans.add(list);
        }

        return ans;
    }

}
