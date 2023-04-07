package com.example.leetcode;

import java.util.Arrays;

/**
 * 352. 将数据流变为多个不相交区间
 *
 * @author CCC
 * @date 2021/10/9 21:10
 */
public class SummaryRanges {

    public static void main(String[] args) {

        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(6);
        summaryRanges.getIntervals();
        summaryRanges.addNum(0);
        summaryRanges.getIntervals();
        summaryRanges.addNum(4);
        summaryRanges.getIntervals();
        summaryRanges.addNum(8);
        summaryRanges.getIntervals();
        summaryRanges.addNum(7);
        summaryRanges.getIntervals();
        summaryRanges.addNum(6);
        summaryRanges.getIntervals();
        summaryRanges.addNum(4);
        summaryRanges.getIntervals();
        summaryRanges.addNum(7);
        summaryRanges.getIntervals();
        summaryRanges.addNum(5);
        summaryRanges.getIntervals();
    }

    int[][] intervals;

    public SummaryRanges() {
    }

    public void addNum(int val) {

        if (intervals == null) {
            intervals = new int[][] {{val, val}};
            return;
        }

        int index = binarySearch(val);
        if (intervals[index][0] <= val && intervals[index][1] >= val) {
            return;
        }
        int n = intervals.length;
        // 最左区间
        if (intervals[0][0] > val) {
            if (intervals[0][0] == val + 1) {
                intervals[0][0] = val;
                return;
            }
            int[][] newIntervals = new int[n + 1][2];
            newIntervals[0] = new int[] {val, val};
            System.arraycopy(intervals, 0, newIntervals, 1, n);
            intervals = newIntervals;
            return;
        }
        // 最右区间
        if (index == n - 1 || intervals[n - 1][1] < val) {
            if (intervals[n - 1][1] == val - 1) {
                intervals[n - 1][1] = val;
                return;
            }
            intervals = Arrays.copyOf(intervals, n + 1);
            intervals[n] = new int[] {val, val};
            return;
        }
        // 插入区间
        if (intervals[index][1] < val - 1 && intervals[index + 1][0] > val + 1) {
            int[][] newIntervals = new int[n + 1][2];
            System.arraycopy(intervals, 0, newIntervals, 0, index + 1);
            newIntervals[index + 1] = new int[] {val, val};
            System.arraycopy(intervals, index + 1, newIntervals, index + 2, n - 1 - index);
            intervals = newIntervals;
            return;
        }
        if (intervals[index][1] == val - 1) {
            intervals[index][1] = val;
        }
        if (intervals[index + 1][0] == val + 1) {
            intervals[index + 1][0] = val;
        }
        // 合并区间
        if (intervals[index][1] == intervals[index + 1][0]) {
            int[][] newIntervals = new int[n - 1][2];
            System.arraycopy(intervals, 0, newIntervals, 0, index);
            System.arraycopy(intervals, index + 1, newIntervals, index, n - 1 - index);
            newIntervals[index][0] = intervals[index][0];
            intervals = newIntervals;
        }
    }

    private int binarySearch(int val) {

        int left = 0, right = intervals.length - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (intervals[mid][0] <= val) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public int[][] getIntervals() {

        print();

        return intervals;
    }

    private void print() {

        for (int[] interval : intervals) {
            System.out.print(Arrays.toString(interval));
        }

        System.out.println();
    }

}
