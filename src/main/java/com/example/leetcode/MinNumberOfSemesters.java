package com.example.leetcode;

import java.util.*;

/**
 * 一个整数n表示某所大学里课程的数目，编号为1到n
 *
 * 数组dependencies中，dependencies[i] = [xi, yi] 表示一个先修课的关系，也就是课程xi必须在课程yi之前上。
 *
 * 同时你还有一个整数k。在一个学期中，最多可以同时上 k门课，前提是这些课的先修课在之前的学期里已经上过了。
 *
 * 返回上完所有课最少需要多少个学期。保证一定存在一种上完所有课的方式。
 *
 *
 * 1 <= n <= 15
 * 1 <= k <= n
 * 0 <=dependencies.length <= n * (n-1) / 2
 * dependencies[i].length == 2
 * 1 <= xi, yi<= n
 * xi != yi
 * 所有先修关系都是不同的，也就是说dependencies[i] != dependencies[j]。
 * 输入的图是个有向无环图。
 *
 * @author CCC
 * @date 2021/3/30 22:49
 */
public class MinNumberOfSemesters {

    public static void main(String[] args) {

        int n = 13;
        int[][] dependencies = {{12,8},{2,4},{3,7}, {6,8}, {11,8},{9,4},{9,7},{12,4},{11,4},{6,4},{1,4},{10,7},
                {10,4},{1,7},{1,8},{2,7},{8,4},{10,8},{12,7},{5,4},{3,4},{11,7},{7,4},{13,4},{9,8},{13,8}};
        int k = 9;

        System.out.println(new MinNumberOfSemesters().minNumberOfSemesters(n, dependencies, k));
    }

    // 1494. 并行课程 II
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {

        // 课程i所需的前导课程的二进制表示
        int[] preCourse = new int[n];
        for (int[] dep: dependencies) {
            preCourse[dep[1] - 1] |= (1 << (dep[0] - 1));
        }

        // 状态mask所需的前导课程的二进制表示
        int[] maskPreCourse = new int[1 << n];
        // 指示是否有效
        boolean[] valid = new boolean[1 << n];
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) <= k) {
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) > 0) {
                        // 计算出状态mask，所需要的前导课程
                        maskPreCourse[mask] |= preCourse[i];
                    }
                }
                // 如果一个状态包含了部分自己的前导课程，认为是无效的
                valid[mask] = ((maskPreCourse[mask] & mask) == 0);
            }
        }

        // dp[i]表示到状态i最少需要多少个学期
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        // 遍历每一个状态
        for (int mask = 0; mask < (1 << n); mask++) {
            // 对每一个状态的"子集"进行考察
            for (int subset = mask; subset > 0; subset = (subset - 1) & mask) {
                // 子集有效 且 mask要包含这个子集所需要的前导课程
                if (valid[subset] && ((mask & maskPreCourse[subset]) == maskPreCourse[subset])) {
                    // mask ^ subset 表示状态mask减去subset之后的状态
                    dp[mask] = Math.min(dp[mask], dp[mask ^ subset] + 1);
                }
            }
        }

        return dp[(1 << n) - 1];
    }

}
