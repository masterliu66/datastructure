package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 */
public class MajorityElement {

    @Test
    public void test() {

        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};

        int ans = majorityElement(nums);

        assert ans == 2;
    }

    public int majorityElement(int[] nums) {

        int count = 0;
        int ans = nums[0];

        for (int num : nums) {
            if (count == 0) {
                ans = num;
                count = 1;
            } else if (ans == num) {
                count++;
            } else {
                count--;
            }
        }

        return ans;
    }

}
