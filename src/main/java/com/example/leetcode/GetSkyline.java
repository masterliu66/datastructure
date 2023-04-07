package com.example.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 218. 天际线问题
 *
 * @author CCC
 * @date 2021/7/13 22:27
 */
public class GetSkyline {

    @Test
    public void getSkyline() {

        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};

        System.out.println(getSkyline(buildings));
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {

        int n = buildings.length;

        int[] boundaries = new int[n * 2];
        for (int i = 0; i < n; i++) {
            boundaries[2 * i] = buildings[i][0];
            boundaries[2 * i + 1] = buildings[i][1];
        }

        Arrays.sort(boundaries);

        PriorityQueue<int[]> queue = new PriorityQueue<>((x,y) -> y[1] - x[1]);
        LinkedList<List<Integer>> ans = new LinkedList<>();
        int index = 0;
        for (int boundary : boundaries) {

            while (index < n && buildings[index][0] <= boundary) {
                queue.offer(new int[] {buildings[index][1], buildings[index][2]});
                index++;
            }

            while (!queue.isEmpty() && queue.peek()[0] <= boundary) {
                queue.poll();
            }

            int maxHeight = queue.isEmpty() ? 0 : queue.peek()[1];
            if (ans.isEmpty() || ans.peekLast().get(1) != maxHeight) {
                ans.add(Arrays.asList(boundary, maxHeight));
            }
        }

        return ans;
    }

}
