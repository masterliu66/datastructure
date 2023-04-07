package com.example.leetcode;

import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

public class SubArrayRanges {

    public static void main(String[] args) {
        int[] nums = {1,3,3};
        long res = new SubArrayRanges().subArrayRanges(nums);
        Assert.assertEquals(4, res);
    }

    public long subArrayRanges(int[] nums) {

        int n = nums.length;

        // 单调递减栈
        Deque<Integer> decrementStack = new LinkedList<>();
        // 单调递增栈
        Deque<Integer> incrementStack = new LinkedList<>();

        // 左边最近的比nums[i]小的下标
        int[] minLeft = new int[n];
        // 右边最近的比nums[i]小的下标
        int[] minRight = new int[n];
        // 左边最近的比nums[i]大的下标
        int[] maxLeft = new int[n];
        // 右边最近的比nums[i]大的下标
        int[] maxRight = new int[n];

        for (int i = 0; i < n; i++) {
            maxRight[i] = minRight[i] = n;
            while (!incrementStack.isEmpty() && nums[incrementStack.peek()] > nums[i]) {
                minRight[incrementStack.pop()] = i;
            }
            incrementStack.push(i);
            while (!decrementStack.isEmpty() && nums[decrementStack.peek()] <= nums[i]) {
                maxRight[decrementStack.pop()] = i;
            }
            decrementStack.push(i);
        }

        decrementStack.clear();
        incrementStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            minLeft[i] = maxLeft[i] = -1;
            while (!incrementStack.isEmpty() && nums[incrementStack.peek()] >= nums[i]) {
                minLeft[incrementStack.pop()] = i;
            }
            incrementStack.push(i);
            while (!decrementStack.isEmpty() && nums[decrementStack.peek()] < nums[i]) {
                maxLeft[decrementStack.pop()] = i;
            }
            decrementStack.push(i);
        }

        long minSum = 0, maxSum = 0;
        for (int i = 0; i < n; i++) {
            minSum += (long) (minRight[i] - i) * (i - minLeft[i]) * nums[i];
            maxSum += (long) (maxRight[i] - i) * (i - maxLeft[i]) * nums[i];
        }

        return maxSum - minSum;
    }

}
