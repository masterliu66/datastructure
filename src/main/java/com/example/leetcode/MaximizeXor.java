package com.example.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 *
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。
 *
 * 换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。
 *
 * 如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 *
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length
 *
 * 且 answer[i] 是第 i 个查询的答案。
 *
 * 1 <= nums.length, queries.length <= 105
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 109
 *
 * @author CCC
 * @date 2021/5/23 21:36
 */
public class MaximizeXor {

    public static void main(String[] args) {

//        int[] nums = {0,1,2,3,4};
//        int[][] queries = {{3,1},{1,3},{5,6}};
        int[] nums = {5,2,4,6,6,3};
        int[][] queries = {{12,4},{8,1},{6,3}};

        System.out.println(Arrays.toString(new MaximizeXor(nums, queries).maximizeXor()));
    }

    int[] nums;

    int[][] queries;

    public MaximizeXor(int[] nums, int[][] queries) {
        this.nums = nums;
        this.queries = queries;
    }

    public int[] maximizeXor() {
        return maximizeXor(nums, queries);
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {

        int[] ans = new int[queries.length];

        Trie root = new Trie();
        for (int num : nums) {
            Trie trie = root;
            for (int i = 31; i >= 0; i--) {
                if ((num >> i & 1) == 0) {
                    if (trie.left == null) {
                        trie.left = new Trie();
                    }
                    trie = trie.left;
                } else {
                    if (trie.right == null) {
                        trie.right = new Trie();
                    }
                    trie = trie.right;
                }
            }
        }

        outer: for (int k = 0; k < queries.length; k++) {
            int[] query = queries[k];
            int x = query[0];
            int m = query[1];
            Trie trie = root;
            int j = 0;
            for (int i = 31; i >= 0; i--) {
                if ((x >> i & 1) == 0) {
                    if (trie.right != null) {
                        if (check(i - 1, j | (1 << i), m, trie.right)) {
                            trie = trie.right;
                            j |= (1 << i);
                        } else {
                            if (trie.left == null) {
                                ans[k] = -1;
                                continue outer;
                            }
                            trie = trie.left;
                        }

                    } else {
                        trie = trie.left;
                    }

                } else {

                    if (trie.left != null) {
                        trie = trie.left;
                    } else {
                        trie = trie.right;
                        j |= (1 << i);
                        if (j > m) {
                            ans[k] = -1;
                            continue outer;
                        }
                    }
                }
            }

            ans[k] = j <= m ? x ^ j : -1;
        }

        return ans;
    }

    private boolean check(int i, int j, int m, Trie trie) {

        if (j > m) {
            return false;
        }

        while (i >= 0) {
            if (trie.left != null) {
                trie = trie.left;
            } else {
                trie = trie.right;
                j |= (1 << i);
                if (j > m) {
                    return false;
                }
            }
            i--;
        }

        return true;
    }

    class Trie {
        // 0
        public Trie left;
        // 1
        public Trie right;
    }

}
