package com.example.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 */
public class TwoSum {

    @Test
    public void test() {

        assert Arrays.equals(new int[] {0,1}, twoSum(new int[]{2,7,11,15}, 9));
        assert Arrays.equals(new int[] {1,2}, twoSum(new int[]{3,2,4}, 6));
        assert Arrays.equals(new int[] {0,1}, twoSum(new int[]{3,3}, 6));
    }

    public int[] twoSum(int[] nums, int target) {

        int n = nums.length;
        if (n < 2) {
            return new int[] {};
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < n; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[] {};
    }

}
