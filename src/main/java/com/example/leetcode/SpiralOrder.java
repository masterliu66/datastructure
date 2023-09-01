package com.example.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 54. 螺旋矩阵
 */
public class SpiralOrder {

    @Test
    public void test() {

        int[][] matrix = {{1},{2},{3},{4},{5},{6},{7},{8},{9},{10}};
        // [1,2,3,4,5,6,7,8,9,10]
        System.out.println(spiralOrder(matrix));
        // [1,2,3,6,9,8,7,4,5]
        System.out.println(spiralOrder(new int[][] {{1,2,3},{4,5,6},{7,8,9}}));
        // [1,2,3,4,8,12,11,10,9,5,6,7]
        System.out.println(spiralOrder(new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        int m = matrix.length;
        if (m == 0) {
            return Collections.emptyList();
        }
        int n = matrix[0].length;
        if (n == 0) {
            return Collections.emptyList();
        }

        int[][] directions = {{0,1}, {1, 0}, {0,-1}, {-1,0}};

        int size = m * n;
        List<Integer> ans = new ArrayList<>(size);
        int flag = 0;
        int row = 0;
        int col = 0;
        int top = 0;
        int bottom = m;
        int left = 0;
        int right = n;
        ans.add(matrix[0][0]);
        int index = 1;
        while (index < size) {
            int nextRow = row + directions[flag % 4][0];
            int nextCol = col + directions[flag % 4][1];
            if (nextRow < top || nextRow == bottom || nextCol < left || nextCol == right) {
                switch (flag % 4) {
                    // 左 -> 右
                    case 0 -> top++;
                    // 上 -> 下
                    case 1 -> right--;
                    // 右 -> 左
                    case 2 -> bottom--;
                    // 下 -> 上
                    default -> left++;
                }
                // 转向
                flag++;
                continue;
            }
            row = nextRow;
            col = nextCol;
            ans.add(matrix[row][col]);
            index++;
        }

        return ans;
    }


}
