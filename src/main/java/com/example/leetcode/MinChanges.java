package com.example.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k 。区间 [left, right]（left <= right）的 异或结果
 * 是对下标位于left 和 right（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：
 * nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
 *
 * 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
 *
 * 1 <= k <= nums.length <= 2000
 * 0 <= nums[i] < 210
 *
 * @author CCC
 * @date 2021/5/25 20:29
 */
public class MinChanges {

    public static void main(String[] args) {

        int[] nums = {11,20,3,18,26,30,20,7,3,0,25,9,19,21,3,23};

        int k = 5;

        System.out.println(new MinChanges(nums, k).minChanges());
    }

    private int[] nums;

    private int k;

    public MinChanges(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
    }

    public int minChanges() {
        return minChanges(nums, k);
    }

    // x 的范围为 [0, 2^10)
    static final int MAXX = 1 << 10;
    // 极大值，为了防止整数溢出选择 INT_MAX / 2
    static final int INFTY = Integer.MAX_VALUE / 2;

    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[MAXX];
        Arrays.fill(f, INFTY);
        // 边界条件 f(-1,0)=0
        f[0] = 0;

        for (int i = 0; i < k; ++i) {
            // 第 i 个组的哈希映射
            Map<Integer, Integer> cnt = new HashMap<>();
            int size = 0;
            for (int j = i; j < n; j += k) {
                cnt.put(nums[j], cnt.getOrDefault(nums[j], 0) + 1);
                ++size;
            }

            // 求出 t2
            int t2min = Arrays.stream(f).min().getAsInt();

            int[] g = new int[MAXX];
            Arrays.fill(g, t2min);
            for (int mask = 0; mask < MAXX; ++mask) {
                // t1 则需要枚举 x 才能求出
                for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                    int x = entry.getKey(), countx = entry.getValue();
                    g[mask] = Math.min(g[mask], f[mask ^ x] - countx);
                }
            }

            // 别忘了加上 size
            for (int j = 0; j < MAXX; ++j) {
                g[j] += size;
            }
            f = g;
        }

        return f[0];
    }

}
