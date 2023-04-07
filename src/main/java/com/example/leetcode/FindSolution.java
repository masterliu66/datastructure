package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个函数f(x, y)和一个目标结果z，函数公式未知，计算方程f(x,y) == z所有可能的正整数数对 x 和 y 。
 * 满足条件的结果数对可以按任意顺序返回。
 *
 * 尽管函数的具体式子未知，但它是单调递增函数，也就是说：
 * f(x, y) < f(x + 1, y)
 * f(x, y) < f(x, y + 1)
 *
 * 1 <= z <= 100
 * f(x, y) == z的解处于1 <= x, y <= 1000的范围内。
 * 在 1 <= x, y <= 1000的前提下, f(x, y)是一个32位有符号整数。
 *
 * @author CCC
 * @date 2021/3/18 22:46
 */
public class FindSolution {

    public static void main(String[] args) {

        FindSolution solution = new FindSolution();

        System.out.println(solution.findSolution((x, y) -> x * y, 5));
    }

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {

        List<List<Integer>> results = new ArrayList<>();

        int x = 1;
        int y = 1000;

        while (x <= 1000 && y >= 1) {

            int tmp = customfunction.f(x, y);
            if (tmp == z) {
                List<Integer> ans = new ArrayList<>(2);
                ans.add(x);
                ans.add(y);
                results.add(ans);
                x++;
                y--;
            } else if (tmp < z) {
                x++;
            } else {
                y--;
            }
        }

        return results;
    }

}

interface CustomFunction {

    int f(int x, int y);

}
