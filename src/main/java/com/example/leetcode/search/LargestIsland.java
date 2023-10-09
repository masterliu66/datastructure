package com.example.leetcode.search;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 827. 最大人工岛
 */
public class LargestIsland {

    @Test
    public void test() {
        assert 9 == largestIsland(new int[][] {
                {1, 1, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1}
        });

        assert 4 == largestIsland(new int[][] {
                {1, 1},
                {1, 0}
        });

        assert 4 == largestIsland(new int[][] {
                {1, 1},
                {1, 1}
        });

        assert 29 == largestIsland(new int[][]{
                {1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 0},
                {1, 1, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 0},
                {0, 0, 1, 0, 1, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 0, 1, 0, 1}
        });
    }

    private static final int[] directions = {0, 1, 0, -1, 0};

    public int largestIsland(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int mask = 2;
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, cntMap, mask++);
                }
            }
        }

        // 全是岛屿的情况
        for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
            if (entry.getValue() == m * n) {
                return entry.getValue();
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int area = 1;
                    Set<Integer> visited = new HashSet<>();
                    for (int idx = 0; idx < directions.length - 1; idx++) {
                        int x = i + directions[idx];
                        int y = j + directions[idx + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 1) {
                            if (visited.contains(grid[x][y])) {
                                continue;
                            }
                            area += cntMap.get(grid[x][y]);
                            visited.add(grid[x][y]);
                        }
                    }
                    ans = Math.max(ans, area);
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] grid, int i, int j, Map<Integer, Integer> cntMap, int mask) {

        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
            return;
        }

        grid[i][j] = mask;

        cntMap.put(mask, cntMap.getOrDefault(mask, 0) + 1);

        for (int idx = 0; idx < directions.length - 1; idx++) {
            dfs(grid, i + directions[idx], j + directions[idx + 1], cntMap, mask);
        }
    }

}
