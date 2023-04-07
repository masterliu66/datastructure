package com.example.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 arr 。
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 *
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 *
 * @author CCC
 * @date 2021/3/17 21:18
 */
public class CountTriplets {

    public static void main(String[] args) {

//        int[] arr = {2,3,1,6,7};

        int[] arr = {1,1,1,1,1};

        CountTriplets countTriplets = new CountTriplets();
        System.out.println(countTriplets.countTriplets2(arr));
    }

    /**
     * 用 xorsum(i, j) 表示 arr[i] ^ arr[i + 1] ⋯ ^ arr[j]
     * 根据题目的定义：a = xorsum(i, j−1), b = xorsum(j, k)
     *
     * 如果 a 与 b 的值相等, 那么 a 与 b 的异或和 a ^ b = 0。
     * 也就是说，对于任意一个三元组(i, j, k), 一定有 xorsum(i, k) = a ^ b = 0。
     *
     * 只要选择的 j 满足题目最基本的要求 i < j <= k, 即 j∈[i+1, k]
     * 那么三元组(i, j, k)一定满足要求, 方案数为 k - i。
     *
     */
    public int countTriplets(int[] arr) {

        int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            int xorSum = arr[i];
            for (int k = i + 1; k < arr.length; k++) {
                xorSum ^= arr[k];
                if (xorSum == 0) {
                    count += k - i;
                }
            }

        }

        return count;
    }

    public int countTriplets2(int[] arr) {
        int n = arr.length;
        // 第一个哈希表
        // 其中有初始边界值 (0, 1)，表示异或和为 0（左边界，一个数都不选择）出现了 1 次
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        // 第二个哈希表
        // 其中有初始边界值 (0, 0)，表示异或和为 0（左边界，一个数都不选择）对应的 i 值为 0
        Map<Integer, Integer> idsum = new HashMap<>();
        idsum.put(0, 0);
        int xorsum = 0, ans = 0;
        // 枚举 k
        for (int k = 0; k < n; ++k) {
            // 计算前缀异或和
            xorsum ^= arr[k];
            // 如果这个前缀异或和之前出现过，那么就找到了一些满足要求的三元组
            if (freq.containsKey(xorsum)) {
                // 对应了题解中的公式 t * k - sum(i_t)
                ans += freq.get(xorsum) * k - idsum.get(xorsum);
            }
            // 更新前缀异或和的出现次数
            freq.put(xorsum, freq.getOrDefault(xorsum, 0) + 1);
            // 更新前缀异或和出现位置的下标之和
            // 注意 i-1 和 i 的关系，所以这里要额外增加 1
            idsum.put(xorsum, idsum.getOrDefault(xorsum, 0) + k + 1);
        }
        return ans;
    }

}
