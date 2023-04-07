package com.example.leetcode;

import java.util.*;

/**
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 *
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间
 * 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 *
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 107
 *
 * @author CCC
 * @date 2021/3/21 22:40
 */
public class MinimumTimeRequired {

    public static void main(String[] args) {

//        int[] jobs = {3,2,3};
//        int k = 3;

        int[] jobs = {1,2,4,7,8};
        int k = 2;

        System.out.println(new MinimumTimeRequired().minimumTimeRequired(jobs, k));
    }

    public int minimumTimeRequired(int[] jobs, int k) {

        List<Integer> workDays = new ArrayList<>(jobs.length);

        for (int job : jobs) {
            workDays.add(job);
        }

        // 按工作天数从大到小排序
        workDays.sort(Comparator.comparingInt(day -> -day));

        // 记录给每个工人分配的工作总天数
        int[] workDaysPer = new int[k];

        // 将前几个最长天数的工作按顺序分配给每个工人
        for (int i = 0; i < k; i++) {
            workDaysPer[i] = workDays.get(i);
        }

        for (int i = k; i < jobs.length; i++) {

            Integer days = workDays.get(i);

            // 将工作分配给当前工作总天数最少的工人
            int minDays = workDaysPer[0];
            int minIndex = 0;
            for (int j = 0; j < workDaysPer.length; j++) {
                if (workDaysPer[j] < minDays) {
                    minDays = workDaysPer[j];
                    minIndex = j;
                }
            }

            workDaysPer[minIndex] += days;
        }

        // 返回最大的工作总天数
        return Arrays.stream(workDaysPer).max().orElse(0);
    }

}
