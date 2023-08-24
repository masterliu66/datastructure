package com.example.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 */
public class Permutation {

    @Test
    public void test() {

        assert Arrays.equals(new String[] {"abc","acb","bac","bca","cab","cba"}, permutation("abc"));
        assert Arrays.equals(new String[] {"abb","bab","bba"}, permutation("abb"));
    }

    public String[] permutation(String s) {

        char[] arr = s.toCharArray();
        Arrays.sort(arr);

        List<String> res = new ArrayList<>();
        do {
            res.add(new String(arr));
        } while (nextPermutation(arr));

        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }

    private boolean nextPermutation(char[] arr) {

        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = arr.length - 1;
        while (j >= 0 && arr[i] >= arr[j]) {
            j--;
        }

        swap(arr, i, j);
        reverse(arr, i + 1);

        return true;
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void reverse(char[] arr, int left) {
        int right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

}
