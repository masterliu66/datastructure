package com.example.leetcode.search;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1263. 推箱子
 */
public class MinPushBox {

    @Test
    public void test() {
        char[][] grid = {{'#', '#', '#', '#', '#', '#'},
                         {'#', 'T', '#', '#', '#', '#'},
                         {'#', '.', '.', 'B', '.', '#'},
                         {'#', '.', '#', '#', '.', '#'},
                         {'#', '.', '.', '.', 'S', '#'},
                         {'#', '#', '#', '#', '#', '#'}};
        assert minPushBox(grid) == 3;
    }

    // 左上右下
    int[][] directions = {{0,-1}, {-1,0}, {0,1}, {1,0}};

    public int minPushBox(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        // (x1,y1,x2,y2)表示箱子坐标(x1,y1) + 玩家坐标(x2,y2)
        int[] position = new int[4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 'B' -> {
                        position[0] = i;
                        position[1] = j;
                    }
                    case 'S' -> {
                        position[2] = i;
                        position[3] = j;
                    }
                    default -> {}
                }
            }
        }

        return bfs(grid, position);
    }

    private int bfs(char[][] grid, int[] position) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(position);
        int step = 0;
        Set<Integer> seen = new HashSet<>();
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int j = 0; j < directions.length; j++) {
                    int[] direction = directions[j];
                    int x = point[0] + direction[0];
                    int y = point[1] + direction[1];
                    if (check(grid, x, y) && dfs(grid, point[2], point[3], point[0], point[1], point[0] - direction[0], point[1] - direction[1])) {
                        if (grid[x][y] == 'T') {
                            return step;
                        }
                        int state = state(x, y, j);
                        if (!seen.contains(state)) {
                            queue.offer(new int[] {x, y, point[0], point[1]});
                            seen.add(state);
                        }
                    }
                }
            }
        }

        return -1;
    }

    int state(int x, int y, int direction) {
        return x * 1000 + y * 10 + direction;
    }

    private boolean dfs(char[][] grid, int x, int y, int bx, int by, int row, int col) {
        return dfs(grid, x, y, bx, by, row, col, new HashSet<>());
    }

    private boolean dfs(char[][] grid, int x, int y, int bx, int by, int row, int col, Set<Integer> seen) {

        if (!check(grid, x, y)) {
            return false;
        }
        // 箱子不能通行
        if (x == bx && y == by) {
            return false;
        }
        // 可以到达目标点
        if (x == row && y == col) {
            return true;
        }

        int state = x * 100 + y;
        if (seen.contains(state)) {
            return false;
        }
        seen.add(state);

        for (int[] direction : directions) {
            if (dfs(grid, x + direction[0], y + direction[1], bx, by, row, col, seen)) {
                return true;
            }
        }

        return false;
    }

    private boolean check(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return false;
        }
        return grid[x][y] != '#';
    }

}
