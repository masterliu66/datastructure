package com.example.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 *
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 *
 * @author CCC
 * @date 2021/6/24 22:44
 */
public class MaxPoints {

    public static void main(String[] args) {

        int[][] points = {{0,1},{0,0},{0,4},{0,-2},{0,-1},{0,3},{0,-4}};

        System.out.println(new MaxPoints(points).maxPoints());
    }

    int[][] points;

    public MaxPoints(int[][] points) {
        this.points = points;
    }

    public int maxPoints() {
        return maxPoints(points);
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    // 最大公约数
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            ret = Math.max(ret, max + 1);
        }
        return ret;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
