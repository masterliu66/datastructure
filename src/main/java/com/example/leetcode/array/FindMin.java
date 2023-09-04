package com.example.leetcode.array;

import org.junit.Test;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 */
public class FindMin {

    @Test
    public void test() {

        assert findMin(new int[] {4,5,6,7,0,1,4}) == 0;
        assert findMin(new int[] {2,2,2,0,1}) == 0;
        assert findMin(new int[] {2,2,2,2,2}) == 2;
    }

    public int findMin(int[] nums) {

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

}
