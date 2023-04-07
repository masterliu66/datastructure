package com.example.leetcode;

import java.util.Random;

/**
 * 528. 按权重随机选择
 *
 * @author CCC
 * @date 2021/8/30 22:14
 */
public class WeightSelection {

    public static void main(String[] args) {

        int[] w = {1, 3};

        WeightSelection weightSelection = new WeightSelection(w);
        int count = 0;
        for (int i = 0; i < 10000; i ++) {
            if (weightSelection.pickIndex() == 1) {
                count++;
            }
        }
        System.out.println(count);
    }

    int[] preSum;

    int max;

    Random random;

    public WeightSelection(int[] w) {
        this.preSum = new int[w.length];
        this.random = new Random();
        init(w);
    }

    private void init(int[] w) {

        int n = w.length;
        preSum[0] = w[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = this.preSum[i - 1] + w[i];
        }

        max = preSum[n - 1];
    }

    public int pickIndex() {
        return binarySearch(random.nextInt(max) + 1);
    }

    private int binarySearch(int target) {

        int left = 0, right = preSum.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > preSum[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
