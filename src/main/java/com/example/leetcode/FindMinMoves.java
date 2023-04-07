package com.example.leetcode;

import java.util.stream.IntStream;

/**
 * 517. 超级洗衣机
 *
 * @author CCC
 * @date 2021/9/29 22:56
 */
public class FindMinMoves {

    public static void main(String[] args) {

        int[] machines = {1,0,5};

        System.out.println(new FindMinMoves(machines).findMinMoves());
    }

    private int[] machines;

    public FindMinMoves(int[] machines) {
        this.machines = machines;
    }

    public int findMinMoves() {
        return findMinMoves(machines);
    }

    public int findMinMoves(int[] machines) {

        int n = machines.length;

        int sum = IntStream.of(machines).sum();
        if (sum % n != 0) {
            return -1;
        }

        int target = sum / n;

        int ans = 0;
        sum = 0;
        for (int machine : machines) {
            machine -= target;
            sum += machine;
            ans = Math.max(ans, Math.max(machine, Math.abs(sum)));
        }

        return ans;
    }

}
