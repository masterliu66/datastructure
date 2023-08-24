package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 44. 数字序列中某一位的数字
 */
public class FindNthDigit {

    @Test
    public void test() {

        // 0 ~ 9
        assert findNthDigit(9) == 9;
        // 10 ~ 99
        assert findNthDigit(10) == 1;
        assert findNthDigit(11) == 0;
        assert findNthDigit(188) == 9;
        assert findNthDigit(189) == 9;
        // 100 ~ 999
        assert findNthDigit(190) == 1;
        assert findNthDigit(191) == 0;
        assert findNthDigit(192) == 0;
        assert findNthDigit(2887) == 9;
        assert findNthDigit(2888) == 9;
        assert findNthDigit(2889) == 9;
        // 1000 ~ 9999
        assert findNthDigit(2890) == 1;
        assert findNthDigit(2891) == 0;
        assert findNthDigit(2892) == 0;
        assert findNthDigit(2893) == 0;
        // 数据溢出
        assert findNthDigit(1000000000) == 1;
    }

    public int findNthDigit(int n) {
        // mulk 表示 10^k
        long mulk = 10;
        // total表示k位数一共有多少数
        long total = 10;
        int k = 1;
        while (n >= total * k) {
            // 个数 * 位数
            n -= total * k;
            total = mulk * 10L - mulk;
            mulk *= 10;
            k++;
        }
        // 当前字符序列的整数
        long num = n / k + (k == 1 ? 0 : mulk / 10);
        // 当前指向整数的字符位置, 从0开始
        int position = n % k;
        for (int i = k - 1; i > position; i--) {
            num /= 10;
        }

        return (int) (num % 10);
    }

}
