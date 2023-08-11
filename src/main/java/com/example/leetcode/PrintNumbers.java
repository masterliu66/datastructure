package com.example.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class PrintNumbers {

    @Test
    public void test() {

        int n = 3;

        String[] ans = new PrintNumbers().printNumbers(n);

        String[] expect = new String[999];
        for (int i = 0; i < 999; i++) {
            expect[i] = String.valueOf(i + 1);
        }

        assert Arrays.equals(expect, ans);
    }

    char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};

    int index;


    public String[] printNumbers(int n) {

        String[] ans = new String[(int) Math.pow(10, n) - 1];

        for (int digit = 1; digit <= n; digit++) {
            char[] nums = new char[digit];
            for (int j = 1; j < numbers.length; j++) {
                nums[0] = numbers[j];
                dfs(1, digit, ans, nums);
            }
        }

        return ans;
    }

    private void dfs(int i, int digit, String[] res, char[] nums) {

        if (i == digit) {
            res[index++] = new String(nums);
            return;
        }

        for (char num : numbers) {
            nums[i] = num;
            dfs(i + 1, digit, res, nums);
        }

    }

}
