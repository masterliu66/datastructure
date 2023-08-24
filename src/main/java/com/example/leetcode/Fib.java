package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 */
public class Fib {

    @Test
    public void test() {

        int n = 1000000000;

        int fib = fib(n);

        assert fib == 21;
    }

    int[][] matrix = {{1,1},{1,0}};

    int mod = 1000000007;

    public int fib(int n) {

        if (n == 0) {
            return 0;
        }

        // f(n + 2) = f(n + 1) + f(n)
        // [f(n+1),f(n)] = [f(n),f(n-1)] * A
        // [f(n+1),f(n)] = [f(1),f(0)] * A ^ n
        int[][] matrix = this.matrix;
        int[][] res = {{1,0},{0,1}};
        n--;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = multiply(res, matrix);
            }
            matrix = multiply(matrix, matrix);
            n >>= 1;
        }

        return (res[0][1] + res[1][1]) % mod;
    }

    private int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int m = matrix1.length;
        int n = matrix2[0].length;
        int v = matrix1[0].length;
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < v; k++) {
                    matrix[i][j] = (matrix[i][j] + multiplyAndMod(matrix1[i][k], matrix2[k][j], mod)) % mod;
                }
            }
        }
        return matrix;
    }

    private int multiplyAndMod(long x, long y, int mod) {
        return (int) ((x * y) % mod);
    }

}
