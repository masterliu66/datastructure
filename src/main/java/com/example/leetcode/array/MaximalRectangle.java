package com.example.leetcode.array;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 85. 最大矩形
 */
public class MaximalRectangle {

    @Test
    public void test() {
        char[][] matrix = {{'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
        int ans = maximalRectangle(matrix);
        assert ans == 6;
    }

    public int maximalRectangle(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] numMatrix = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '0') {
                    numMatrix[row][col] = 0;
                } else {
                    numMatrix[row][col] = col > 0 ? numMatrix[row][col - 1] + 1 : 1;
                }
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        // 左边小于当前值的最近下标
        int[] left = new int[m];
        // 右边小于当前值的最近下标
        int[] right = new int[m];
        int ans = 0;
        for (int col = 0; col < n; col++) {
            stack.clear();
            Arrays.fill(right, m);
            for (int row = 0; row < m; row++) {
                while (!stack.isEmpty() && numMatrix[stack.peek()][col] >= numMatrix[row][col]) {
                    right[stack.pop()] = row;
                }
                left[row] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(row);
            }
            for (int row = 0; row < m; row++) {
                int area = (right[row] - left[row] - 1) * numMatrix[row][col];
                ans = Math.max(ans, area);
            }
        }

        return ans;
    }


}
