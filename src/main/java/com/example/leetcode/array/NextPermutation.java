package com.example.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 31. 下一个排列
 */
public class NextPermutation {

    @Test
    public void test() {

        int[] nums = {1,2,3};

        nextPermutation(nums);
        assert Arrays.equals(new int[] {1,3,2}, nums);
        nextPermutation(nums);
        assert Arrays.equals(new int[] {2,1,3}, nums);
        nextPermutation(nums);
        assert Arrays.equals(new int[] {2,3,1}, nums);
        nextPermutation(nums);
        assert Arrays.equals(new int[] {3,1,2}, nums);
        nextPermutation(nums);
        assert Arrays.equals(new int[] {3,2,1}, nums);
        nextPermutation(nums);
        assert Arrays.equals(new int[] {1,2,3}, nums);
    }

    public void nextPermutation(int[] nums) {

        int n = nums.length;
        if (n <= 1) {
            return;
        }
        // 从后往前找出第一个非递减的数
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 说明当前数组是倒序排列, 重新变成正序排列
        if (i < 0) {
            reverse(nums, 0, n - 1);
            return;
        }
        // 找出大于i的最小下标
        int j = n - 1;
        while (j > i && nums[j] <= nums[i]) {
            j--;
        }
        // 交换i与j
        swap(nums, i, j);
        // 翻转[i + 1, n)
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[]nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

}
