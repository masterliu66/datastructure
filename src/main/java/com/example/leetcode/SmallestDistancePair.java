package com.example.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 *
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 *
 * @author CCC
 * @date 2021/4/25 23:30
 */
public class SmallestDistancePair {

    @Test
    public void smallestDistancePair() {

        int[] nums = {1,6,1};

        int k = 3;

        System.out.println(binarySearch(nums, k));
        System.out.println(binarySearch2(nums, k));
        System.out.println(smallestDistancePair(nums, k));
    }

    public int binarySearch2(int[] nums, int k) {

        Arrays.sort(nums);

        int n = nums.length;

        int low = 0;

        int high = nums[n - 1] - nums[0];

        while (low < high) {

            int mid = low + (high - low) / 2;

            // count表示小于等于mid的距离对的数量
            int count = 0;
            int left = 0;
            for (int right = 1; right < n; right++) {
               while (nums[right] - nums[left] > mid) {
                   left++;
               }
               // 左右指针之间的数对都符合 nums[right] - nums[left] <= mid的要求
               count += right - left;
            }

            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public int binarySearch(int[] nums, int k) {

        Arrays.sort(nums);

        // multiplicity[j] 表示满足 i < j 且 nums[i] == nums[j] 的 i 的个数
        int[] multiplicity = new int[nums.length];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i-1]) {
                multiplicity[i] = 1 + multiplicity[i - 1];
            }
        }

        int WIDTH = 2 * nums[nums.length - 1];
        // prefix[v] 表示 nums 数组中比 v 小或者和 v 相等的元素个数，
        int[] prefix = new int[WIDTH];
        int left = 0;
        for (int i = 0; i < WIDTH; ++i) {
            while (left < nums.length && nums[left] == i) {
                left++;
            }
            prefix[i] = left;
        }

        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        while (low < high) {
            int mid = (low + high) / 2;
            // count 表示距离小于等于 mid 的距离对数量
            int count = 0;
            /*
             * 设 c[i] 表示 nums[i] 在数组中出现的次数, 则满足 i < j 且 nums[j] - nums[i] <= mid 的距离对的个数为
             * prefix[nums[i] + mid] - prefix[nums[i]] + c[i] - multiplicity[i]
             * c[i] - multiplicity[i] 表示 nums[i]右边（包括其本身）与其相等的元素个数
             * 由于对 j < i且nums[j] == nums[i]的个数 求和等价于对 j > i且nums[j] == nums[i]的个数 求和
             * 所以可以使用multiplicity[i]的和来代替c[i] - multiplicity[i]的和
             */
            for (int i = 0; i < nums.length; ++i) {
                count += prefix[nums[i] + mid] - prefix[nums[i]] + multiplicity[i];
            }

            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public int smallestDistancePair(int[] nums, int k) {

        Arrays.sort(nums);

        int n = nums.length;

        PriorityQueue<Node> heap = new PriorityQueue<>(n, Comparator.comparingInt(Node::getDistance));

        for (int i = 0; i < n - 1; i++) {
            heap.offer(new Node(i, i + 1));
        }

        Node node = null;
        for (int i = k; i > 0; i--) {
            node = heap.poll();
            if (node.neighbor < n - 1) {
                heap.offer(new Node(node.root, node.neighbor + 1));
            }
        }

        return nums[node.neighbor] - nums[node.root];
    }

    class Node {

        int root;

        int neighbor;

        public Node(int root, int neighbor) {
            this.root = root;
            this.neighbor = neighbor;
        }

        public int getDistance() {
            return neighbor - root;
        }

    }

}
