package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 */
public class MaxSubArray {

    @Test
    public void test() {

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        int ans = maxSubArray(nums);

        assert ans == 6;
    }

    public int maxSubArray(int[] nums) {

        int n = nums.length;
        // dp[i]表示包含第i个元素的连续子数组的最大和
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
