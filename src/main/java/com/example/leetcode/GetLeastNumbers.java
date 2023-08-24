package com.example.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 40. 最小的k个数
 */
public class GetLeastNumbers {

    @Test
    public void test() {

        int[] arr = {0,0,0,2,0,5};
        int k = 3;

        int[] ans = getLeastNumbers(arr, k);

        assert Arrays.equals(new int[] {0,0,0}, ans);
    }

    public int[] getLeastNumbers(int[] arr, int k) {

        if (k == 0) {
            return new int[] {};
        }

        if (arr.length <= k) {
            return arr;
        }

        quickSort(arr, 0, arr.length - 1, k);

        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);

        return ans;
    }

    private void quickSort(int[] arr, int left, int right, int k) {

        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (j > i && arr[j] >= pivot) {
                j--;
            }
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            swap(arr, i, j);
        }

        swap(arr, i, left);

        if (i + 1 == k) {
            return;
        }

        if (i + 1 < k) {
            quickSort(arr, i + 1, right, k);
        } else {
            quickSort(arr, left, i - 1, k);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
