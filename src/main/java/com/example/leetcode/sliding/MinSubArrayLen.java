package com.example.leetcode.sliding;

import org.junit.Test;

/**
 * 209. 长度最小的子数组
 */
public class MinSubArrayLen {

    @Test
    public void test() {
        assert 2 == minSubArrayLen(7, new int[] {2,3,1,2,4,3});
        assert 1 == minSubArrayLen(4, new int[] {1,4,4});
        assert 0 == minSubArrayLen(11, new int[] {1,1,1,1,1,1,1,1});
    }

    public int minSubArrayLen(int target, int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int i = 0;
        int sum = 0;
        int ans = n + 1;
        for (int j = 0; j < n; j++) {
            sum += nums[j];
            while (sum >= target) {
                ans = Math.min(ans, j - i + 1);
                sum -= nums[i++];
            }
        }

        return ans == n + 1 ? 0 : ans;
    }

}
