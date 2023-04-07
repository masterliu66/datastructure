package com.example.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * className ShellSort
 * packageName com.example.datastructure.sort
 * description
 *
 * @author CCC
 * @date 2020/11/10 22:42
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateArr(16, 100);

        System.out.println("排序前: "+ Arrays.toString(arr));

        sort2(arr);

        System.out.println("排序后: "+ Arrays.toString(arr));
    }

    private static void sort(int[] arr) {

        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        int temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
                }
            }
        }
    }

    private static void sort2(int[] arr) {

        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                int v = arr[i];
                int j = i;
                while (j - step >= 0 && v < arr[j - step]) {
                    arr[j] = arr[j - step];
                    j -= step;
                }
                arr[j] = v;
            }
        }
    }

}
