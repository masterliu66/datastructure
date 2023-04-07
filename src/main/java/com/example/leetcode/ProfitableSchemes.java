package com.example.leetcode;

import java.util.Arrays;

/**
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 *
 * 第i种工作会产生profit[i]的利润，它要求group[i]名成员共同参与。
 * 如果成员参与了其中一项工作，就不能参与另一项工作。
 *
 * 工作的任何至少产生minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 *
 * 有多少种计划可以选择？因为答案很大，结果模10^9 + 7的值。
 *
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 *
 * @author CCC
 * @date 2021/6/9 20:55
 */
public class ProfitableSchemes {

    public static void main(String[] args) {

        int n = 20;

        int minProfit = 5;

        int[] group = {2,2,2,3,4,5,5};

        int[] profit = {2,3,4,5,6,7,8};

        System.out.println(new ProfitableSchemes(n, minProfit, group, profit).profitableSchemes());
    }

    private final int n;

    private final int minProfit;

    private final int[] group;

    private final int[] profit;

    public ProfitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        this.n = n;
        this.minProfit = minProfit;
        this.group = group;
        this.profit = profit;
    }

    public int profitableSchemes() {
        return profitableSchemes(n, minProfit, group, profit);
    }

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {

        int mol = 1000000007;

        int sum = Arrays.stream(profit).sum();

        int len = group.length;

        // schemes[i][j][k] 表示下标不超过i, 总参加人数不超过j的能够产生利润k的方案数量（取模后）
        int[][] schemes = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            schemes[i][0] = 1;
        }

        for (int i = 1; i <= len; i++) {
            int curProfit = profit[i - 1];
            for (int j = n; j >= 0; j--) {
                int curPeople = group[i - 1];
                for (int k = sum; k >= 0; k--) {
                    if (j >= curPeople && k >= curProfit) {
                        schemes[j][k] = (schemes[j][k] + schemes[j - curPeople][k - curProfit]) % mol;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = minProfit; i <= sum; i++) {
            ans = (ans + schemes[n][i]) % mol;
        }

        return ans;
    }

}
