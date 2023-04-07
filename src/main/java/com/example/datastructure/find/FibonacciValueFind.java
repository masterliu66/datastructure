package com.example.datastructure.find;

import com.example.datastructure.sort.ArrayGenerator;
import com.example.datastructure.sort.RadixSort;

import java.util.Arrays;

/**
 * className FibonacciValueFind
 * packageName com.example.datastructure.find
 * description
 *
 * @author CCC
 * @date 2020/11/29 22:50
 */
public class FibonacciValueFind {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateArr(6, 100);

        RadixSort.sort(arr);

        System.out.println("数组: "+ Arrays.toString(arr));

        int index = find(arr, arr[0]);

        System.out.println("查到到的索引为: "+ index);
    }

    public static int find(int[] arr, int value) {

        int low = 0;
        int high = arr.length - 1;

        int k = 0;
        int[] f = fib(Math.max(3, arr.length));
        while (f[k] - 1 < high) {
            k++;
        }

        int[] temp = Arrays.copyOf(arr, f[k] - 1);
        // 将扩充至斐波那契数列长度的新数组的扩充值赋值为原数组的最大值
        for (int i = high; i < f[k] - 1; i++) {
            temp[i] = arr[high];
        }

        int cursor;
        while (low <= high) {
            System.out.println("调用find()");
            // 数组长度 = 前段数组 + 1 (cursor) + 后段数组
            // f[k] - 1 = (f[k - 1] - 1) + (f[k - 2] - 1) + 1
            cursor = low + f[k - 1] - 1;

            if (value < temp[cursor]) {
                // 向左查找,
                high = cursor - 1;
                k -= 1;
            } else if (value > temp[cursor]) {
                // 向右查找
                low = cursor + 1;
                k -= 2;
            } else {
                return Math.min(cursor, high);
            }
        }

        return -1;
    }

    private static int[] fib(int size) {

        if (size <= 0) {
            return new int[0];
        }

        int[] f = new int[size];
        f[0] = 1;

        if (size == 1) {
            return f;
        }

        f[1] = 1;

        for (int i = 2; i < size; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

}
