package com.example.leetcode;

import java.util.*;

/**
 * 815. 公交路线
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 *
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 *
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 *
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 *
 * @author CCC
 * @date 2021/6/28 22:09
 */
public class NumBusesToDestination {

    public static void main(String[] args) {

        int[][] routes = {{1,2,7},{3,6,7}};
        int source = 1;
        int target = 6;

        System.out.println(new NumBusesToDestination(routes, source, target).numBusesToDestination());
    }

    private int[][] routes;
    private int source;
    private int target;

    public NumBusesToDestination(int[][] routes, int source, int target) {
        this.routes = routes;
        this.source = source;
        this.target = target;
    }

    public int numBusesToDestination() {
        return numBusesToDestination(routes, source, target);
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target) {
            return 0;
        }

        Map<Integer, List<Integer>> map = new HashMap<>(routes.length);

        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            for (int station : route) {
                List<Integer> buses = map.computeIfAbsent(station, k -> new ArrayList<>());
                buses.add(i);
            }
        }

        if (!map.containsKey(source)) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        // int[0] 当前所在车站 int[1]乘坐公交数量
        queue.add(new int[] {source, 0});
        // 表示已乘坐的公交
        Set<Integer> seenBus = new HashSet<>();

        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            List<Integer> nextBuses = map.get(p[0]);
            if (nextBuses == null) {
                return -1;
            }
            for (Integer nextBus : nextBuses) {
                if (seenBus.contains(nextBus)) {
                    continue;
                }
                for (int station : routes[nextBus]) {
                    if (station == target) {
                        return p[1] + 1;
                    }
                    queue.add(new int[] {station, p[1] + 1});
                }
                seenBus.add(nextBus);
            }
        }

        return -1;
    }

}
