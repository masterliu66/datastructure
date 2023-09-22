package com.example.leetcode.search;

import org.junit.Test;

/**
 * 4. 寻找两个正序数组的中位数
 */
public class FindMedianSortedArrays {

    @Test
    public void test() {
        assert 3.5 == findMedianSortedArrays(new int[]{3,4}, new int[]{});
        assert 1.0 == findMedianSortedArrays(new int[]{}, new int[]{1});
        assert 2.0 == findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        assert 2.5 == findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        assert 3.5 == findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{3, 4, 5, 6});
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int k = (m + n) / 2;
        if (((m+n) & 1) == 1) {
            return getKthElement(nums1, nums2, k + 1);
        } else {
            return (getKthElement(nums1, nums2, k) + getKthElement(nums1, nums2, k + 1)) / 2.0;
        }
    }

    private int getKthElement(int[] nums1, int[] nums2, int k) {

        int m = nums1.length;
        int n = nums2.length;

        int index1 = 0;
        int index2 = 0;

        while (k > 1 && index1 < m && index2 < n) {
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, m) - 1;
            int newIndex2 = Math.min(index2 + half, n) - 1;
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }

        if (index1 == m) {
            return nums2[index2 + k - 1];
        }

        if (index2 == n) {
            return nums1[index1 + k - 1];
        }

        return Math.min(nums1[index1], nums2[index2]);
    }

}
