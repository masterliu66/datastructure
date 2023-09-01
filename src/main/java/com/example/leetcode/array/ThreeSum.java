package com.example.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 */
public class ThreeSum {

    @Test
    public void test() {

        int[] nums1 = {-1,0,1,2,-1,-4};
        int[] nums2 = {-2,0,0,2,2};
        assert List.of(Arrays.asList(-1,-1,2), Arrays.asList(-1,0,1)).toString().equals(threeSum(nums1).toString());
        assert List.of(Arrays.asList(-2,0,2)).toString().equals(threeSum(nums2).toString());
    }

    public List<List<Integer>> threeSum(int[] nums) {

        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (n < 3) {
            return ans;
        }

        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            // 过滤第一个数的重复值
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                // 过滤第二个数的重复值
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return ans;
    }

}
