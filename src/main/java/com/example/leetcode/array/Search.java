package com.example.leetcode.array;

import org.junit.Test;

/**
 * 33. 搜索旋转排序数组
 */
public class Search {

    @Test
    public void test() {

        int[] nums = {3,4,5,1,2};

        assert 0 == search(nums, 3);
        assert 1 == search(nums, 4);
        assert 2 == search(nums, 5);
        assert 3 == search(nums, 1);
        assert 4 == search(nums, 2);
    }

    public int search(int[] nums, int target) {

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // 数组分为[原始部分 | 旋转部分] 旋转部分 < nums[0]
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                // mid处于旋转部分
                if (nums[mid] >= nums[0] && target < nums[0]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // mid处于旋转部分
                if (nums[mid] < nums[0] && target >= nums[0]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return nums[left] == target ? left : -1;
    }

}
