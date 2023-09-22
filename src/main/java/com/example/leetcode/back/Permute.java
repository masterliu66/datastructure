package com.example.leetcode.back;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 */
public class Permute {

    @Test
    public void test() {
        assert "[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]".equals(permute(new int[] {1,2,3}).toString());
    }

    List<List<Integer>> ans;

    public List<List<Integer>> permute(int[] nums) {

        this.ans = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        backtracking(nums, 0, list);

        return ans;
    }

    private void backtracking(int[] nums, int status, List<Integer> current) {

        if (current.size() == nums.length) {
            ans.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (((status >> i) & 1) == 1) {
                continue;
            }
            int num = nums[i];
            current.add(num);
            backtracking(nums, status | (1 << i), current);
            current.remove(current.size() - 1);
        }

    }

}
