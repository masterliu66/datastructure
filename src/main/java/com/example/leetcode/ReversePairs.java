package com.example.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 51. 数组中的逆序对
 */
public class ReversePairs {

    @Test
    public void test() {

        int[] nums = {7,5,6,4};

        assert reversePairs(nums) == 5;
    }

    public int reversePairs(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);
        // 离散化nums数组
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.binarySearch(copy, nums[i]);
        }

        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int count = tree.query(nums[i] + 1, n - 1);
            ans += count;
            tree.update(nums[i], 1);
        }

        return ans;
    }

    static class BinaryIndexedTree {

        int n;

        int[] c;

        public BinaryIndexedTree(int n) {
            this.n = n;
            this.c = new int[n + 1];
        }

        void update(int index, int target) {
            index++;
            while (index <= n) {
                c[index] += target;
                index += Integer.lowestOneBit(index);
            }
        }

        int query(int start, int end) {
            if (start > end) {
                return 0;
            }
            return query(end) - query(start - 1);
        }

        int query(int x) {
            x++;
            int ans = 0;
            while (x > 0) {
                ans += c[x];
                x -= Integer.lowestOneBit(x);
            }
            return ans;
        }

    }

}
