package com.example.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 */
public class SpiralOrder {

    @Test
    public void test() {

        int[][] matrix = {{1},{2},{3},{4},{5},{6},{7},{8},{9},{10}};

        int[] ans = new SpiralOrder().spiralOrder(matrix);

        System.out.println(Arrays.toString(ans));
    }

    public int[] spiralOrder(int[][] matrix) {

        int m = matrix.length;
        if (m == 0) {
            return new int[] {};
        }
        int n = matrix[0].length;
        // 行边界
        int row1 = 0;
        int row2 = m - 1;
        // 列边界
        int col1 = 0;
        int col2 = n - 1;
        int[] ans = new int[m * n];
        int index = 0;
        while (row1 <= row2 && col1 <= col2) {
            // 左 -> 右
            for (int j = col1; j <= col2; j++) {
                ans[index++] = matrix[row1][j];
            }
            // 上 -> 下
            for (int i = row1 + 1; i <= row2; i++) {
                ans[index++] = matrix[i][col2];
            }
            if (row1 != row2) {
                // 右 -> 左
                for (int j = col2 - 1; j >= col1; j--) {
                    ans[index++] = matrix[row2][j];
                }
            }
            if (col1 != col2) {
                // 下 -> 上
                for (int i = row2 - 1; i > row1; i--) {
                    ans[index++] = matrix[i][col1];
                }
            }
            row1++;
            row2--;
            col1++;
            col2--;
        }

        return ans;
    }


}
