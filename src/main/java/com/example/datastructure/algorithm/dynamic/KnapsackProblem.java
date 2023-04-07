package com.example.datastructure.algorithm.dynamic;

import java.util.Arrays;

/**
 * className Knapsack
 * packageName com.example.datastructure.algorithm.dynamic
 * description
 *
 * @author CCC
 * @date 2021/1/8 23:33
 */
public class KnapsackProblem {

    public static void main(String[] args) {

        // 背包容量
        int c = 4;

        // 物品数量
        int n = 3;

        // 物品的重量
        int[] w = {1, 4, 3};
        // 物品的价格
        int[] v = {1500, 3000, 2000};

        // 动态规格使用的表
        int[][] t = new int[n + 1][c + 1];
        // 记录背包放入的物品位置
        int[][] path = new int[n + 1][c + 1];
        /*
         * 动态规划使用的规则
         * (1) t[i][0]=t[0][j]=0;
         * (2) 当w[i]> j 时：t[i][j]=t[i-1][j]
         * (3) 当j>=w[i]时： t[i][j]=max{t[i-1][j], v[i]+t[i-1][j-w[i]]}
         */
        for (int i = 1; i < t.length; i++) {
            for (int j = 1; j < t[i].length; j++) {
                if (w[i - 1] > j) {
                    t[i][j] = t[i - 1][j];
                    continue;
                }
                // 表中上一个单元格中的值
                int aboveCellValue = t[i - 1][j];
                // 当前物品的价格 + 剩余空间的单元格价格
                int planCellValue = v[i - 1] + t[i - 1][j - w[i - 1]];
                if (planCellValue < aboveCellValue) {
                    t[i][j] = aboveCellValue;
                } else {
                    t[i][j] = planCellValue;
                    path[i][j] = 1;
                }
            }
        }

        // 打印动态规划表
        for (int[] array : t) {
            System.out.println(Arrays.toString(array));
        }

        System.out.println("最优策略为: ");

        int row = n;
        int col = c;
        while (row > 0 && col > 0) {
            if (path[row][col] == 1) {
                System.out.printf("背包中放入第%d个物品\n", row);
                col -= w[row - 1];
            }
            row--;
        }
    }

}
