package com.example.leetcode;

import java.util.*;

/**
 * 给你一个数组target，包含若干 互不相同的整数，以及另一个整数数组arr，arr可能 包含重复元素。
 *
 * 每一次操作中，你可以在 arr的任意位置插入任一整数。比方说，如果arr = [1,4,1,2]，那么你可以在中间添加 3得到[1,4,3,1,2]。
 * 你可以在数组最开始或最后面添加整数。
 *
 * 请你返回 最少操作次数，使得target成为arr的一个子序列。
 *
 * 一个数组的 子序列指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。
 * 比方说，[2,7,4]是[4,2,3,7,2,1,4]的子序列（加粗元素），但[2,4,2]不是子序列。
 *
 * 1 <= target.length, arr.length <= 105
 * 1 <= target[i], arr[i] <= 109
 * target不包含任何重复元素。
 *
 * @author CCC
 * @date 2021/7/26 20:46
 */
public class MinOperations {

    public static void main(String[] args) {

        int[] target = {6,4,8,1,3,2};

        int[] arr = {4,7,6,2,3,8,6,1};

        System.out.println(new MinOperations(target, arr).minOperations());
    }

    private int[] target;

    private int[] arr;

    public MinOperations(int[] target, int[] arr) {
        this.target = target;
        this.arr = arr;
    }

    public int minOperations() {
        return minOperations(target, arr);
    }

    public int minOperations(int[] target, int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }

        List<Integer> indexes = new ArrayList<>();
        for (int k : arr) {
            Integer index = map.get(k);
            if (index != null) {
                indexes.add(index);
            }
        }

        int n = indexes.size();

        // g[i]表示长度为i的上升子序列的最小结尾元素
        int[] g = new int[n + 1];
        Arrays.fill(g, Integer.MAX_VALUE);

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            Integer index = indexes.get(i);
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (g[mid] < index) l = mid;
                else r = mid - 1;
            }
            int len = r + 1;
            g[len] = Math.min(g[len], index);
            maxLength = Math.max(maxLength, len);
        }

        return target.length - maxLength;
    }

}
