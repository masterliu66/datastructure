package com.example.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 446. 等差数列划分 II - 子序列
 *
 * @author CCC
 * @date 2021/8/11 20:59
 */
public class NumberOfArithmeticSlices {

    public static void main(String[] args) {

//        int[] nums = {2,4,6,8,10};
        int[] nums = {2,2,3,4};

        System.out.println(new NumberOfArithmeticSlices(nums).numberOfArithmeticSlices());
    }

    private int[] nums;

    public NumberOfArithmeticSlices(int[] nums) {
        this.nums = nums;
    }

    public int numberOfArithmeticSlices() {
        return numberOfArithmeticSlices(nums);
    }

    public int numberOfArithmeticSlices(int[] nums) {

        int n = nums.length;

        if (n < 3) {
            return 0;
        }

        // dp[i][j] 表示nums[i]为结尾、公差为k(j = map.get(k))的等差子序列的最大个数
        Map<Long, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - nums[j];
                Integer count = dp[j].getOrDefault(diff, 0);
                ans += count;
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + count + 1);
            }
        }

        return ans;
    }

}
