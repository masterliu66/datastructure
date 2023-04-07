package com.example.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 *
 * @author CCC
 * @date 2021/4/22 23:06
 */
public class MaxSumSubmatrix {

    public static void main(String[] args) {

        MaxSumSubmatrix o = new MaxSumSubmatrix();

        int[][] matrix = {{1,0,1},{0,-2,3}};

        int k = 2;

        System.out.println(o.maxSumSubmatrix(matrix, k));
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {

        int m = matrix.length;

        int n = matrix[0].length;

        int max = Integer.MIN_VALUE;

        // 枚举矩形的上下边界，并计算出该边界内每列的元素和，则原问题转换成了如下一维问题：
        // 给定一个整数数组和一个整数 k，计算该数组的最大区间和，要求区间和不超过 k。

        // 设前缀和s[i] = a[0] + ... + a[i - 1], 其中s[0] = 0
        // 则区间[i, j)的区间和为 a[i] + a[i + 1] + ... + a[j - 1] = s[j] - s[i]
        // 区间和不超过k则为 s[j] - s[i] <= k

        // 上边界
        for (int i = 0; i < m; i++) {

            // sum[col]表示在上下边界中col列数值的和
            int[] sum = new int[n];

            // 下边界
            for (int j = i; j < m; j++) {

                // 更新每列的元素和
                for (int col = 0; col < n; col++) {
                    sum[col] += matrix[j][col];
                }

                // s[j] - s[i] <= k 可以转换成 s[i] >= s[j] - k, 要使s[j] - s[i]尽可能大
                // 需要寻找最小的满足s[i] >= s[j] - k的s[i]
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceiling = set.ceiling(s - k);
                    if (ceiling != null) {
                        max = Math.max(max, s - ceiling);
                    }
                    set.add(s);
                }

            }

        }

        return max;
    }

}
