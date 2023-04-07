package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoTest {

    public static void main(String[] args) {

        double delta = 10e-9;

        int total = 18405085 - 1113 * 365;

        double x2 = 1812.5;

        double x3 = 1812.5;

        double x4 = 2239;

        int max = (int) (total / x2);

        List<int[]> result = new ArrayList<>();

        for (int i = 1; i < max; i++) {
            for (int j = 1; j < max; j++) {
                double remain = total - x2 * i - x3 * j;
                if (remain < 0) {
                    break;
                }
                int k = (int) (remain / x4);
                if (total - x2 * i + x3 * j + k * x4 < delta) {
                    int[] res = new int[3];
                    res[0] = i;
                    res[1] = j;
                    res[2] = k;
                    result.add(res);
                }
            }
        }

        for (int[] res : result) {
            System.out.println(Arrays.toString(res));
        }
    }


}
