package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 *
 * 一次交换可选择任意两人，让他们站起来交换座位。
 *
 * 人和座位用0到2N-1的整数表示，情侣们按顺序编号，第一对是(0, 1)，第二对是(2, 3)，以此类推，最后一对是(2N-2, 2N-1)。
 *
 * 这些情侣的初始座位row[i]是由最初始坐在第 i 个座位上的人决定的。
 *
 * len(row) 是偶数且数值在 [4, 60]范围内。
 * row 是序列 0...len(row)-1 的一个全排列。
 *
 * @author CCC
 * @date 2021/3/19 20:33
 */
public class MinSwapsCouples {

    public static void main(String[] args) {

//        int[] row = {4, 11, 9, 3, 7, 6, 1, 8, 0, 2, 5, 10};

        int[] row = {10, 7, 4, 2, 3, 0, 9, 11, 1, 5, 6, 8};

//        int[] row = {0, 1, 2, 3};

        MinSwapsCouples couples = new MinSwapsCouples();

        System.out.println(couples.minSwapsCouples(row));
    }

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        // 反向索引表，row[i] = num => index[num] = i
        int[] indexMap = new int[n];
        for (int i = 0; i < n; i++) {
            indexMap[row[i]] = i;
        }
        int count = 0;
        for (int i = 0; i < n-1; i += 2) {
            int p1 = row[i];
            // p1的情侣
            int p2 = (p1 & 1) == 0 ? p1 + 1 : p1 - 1;
            if (row[i+1] == p2) {
                // p1和p2正好挨着，无需交换这一对
                continue;
            }
            // p2的位置
            int p2Index = indexMap[p2];
            // 交换 p2 <=> [i+1]
            swap(row, indexMap, i+1, p2Index);
            count++;
        }
        return count;
    }

    private static void swap(int[] row, int[] indexMap, int i, int j) {
        int tmp = row[i];
        row[i] = row[j];
        row[j] = tmp;
        indexMap[row[i]] = i;
        indexMap[row[j]] = j;
    }

}
