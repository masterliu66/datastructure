package com.example.leetcode;

/**
 * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称k（k>=2）是 n 的一个好进制。
 *
 * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
 *
 * n的取值范围是 [3, 10^18]。
 * 输入总是有效且没有前导 0。
 *
 * @author CCC
 * @date 2021/6/18 22:22
 */
public class SmallestGoodBase {

    public static void main(String[] args) {

        String s = "1000000000000000000";

        System.out.println(new SmallestGoodBase(s).smallestGoodBase());
    }

    private String s;

    public SmallestGoodBase(String s) {
        this.s = s;
    }

    public String smallestGoodBase() {
        return smallestGoodBase(s);
    }

    public String smallestGoodBase(String n) {
        long m = Long.parseLong(n);
        int max = (int)(Math.log(m) / Math.log(2) + 1);
        // 枚举1的个数
        for (int len = max; len >= 3; len--) {
            long l = 2, r = m - 1;
            while (l < r) {
                long mid = (l + r) >> 1;
                if (check(mid, len, m) >= 0) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (check(r, len, m) == 0) {
                return String.valueOf(r);
            }
        }
        return String.valueOf(m - 1);
    }

    int check(long k, int len, long t) {
        long ans = 1;
        for (int i = 1; i < len; i++) {
            // 防止数据溢出
            if (ans > (t - 1) / k) {
                return 1;
            }
            ans = ans * k + 1;
        }
        if (ans == t) {
            return 0;
        }
        return ans - t > 0 ? 1 : -1;
    }

}
