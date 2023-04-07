package com.example.leetcode;

import org.junit.Test;

import java.util.TreeSet;

/**
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j
 * 使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 *
 * @author CCC
 * @date 2021/4/17 22:40
 */
public class ContainsNearbyAlmostDuplicate {

    @Test
    public void containsNearbyAlmostDuplicate() {
        int[] nums = {1,5,9,1,5,9};
        int k = 2, t = 3;
        System.out.println(containsNearbyAlmostDuplicate(nums, k, t));
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        int n = nums.length;

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {

            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= ((long) nums[i] + (long) t)) {
                return true;
            }

            Long floor = set.floor((long) nums[i] + (long) t);
            if (floor != null && floor >= ((long) nums[i] - (long) t)) {
                return true;
            }

            set.add((long) nums[i]);

            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }

}
