package com.example.leetcode;

import java.util.Arrays;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 使用常量空间, 使空间复杂度为O(1)
 *
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 *
 * @author CCC
 * @date 2021/3/21 22:06
 */
public class SetZeroes {

    public static void main(String[] args) {

//        int[][] matrix = {{1,1,1}, {1,0,1},{1,1,1}};

        int[][] matrix = {{1,1,2,0},
                          {3,4,5,2},
                          {0,3,1,5}};

        new SetZeroes().setZeroes(matrix);

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

    }

    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        // 第一列是否存在元素为0
        boolean flagCol0 = false;

        for (int i = 0; i < m; i++) {

            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }

            for (int j = 1; j < n; j++) {

                if (matrix[i][j] == 0) {
                    // 用矩阵的第一行和第一列记录该行/列是否存在元素为0
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0 ; i--) {

            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }

            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }

    }

}
