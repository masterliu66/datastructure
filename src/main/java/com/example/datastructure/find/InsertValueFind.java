package com.example.datastructure.find;

import com.example.datastructure.sort.ArrayGenerator;
import com.example.datastructure.sort.RadixSort;

import java.util.Arrays;

/**
 * className InsertValueFind
 * packageName com.example.datastructure.find
 * description
 *
 * @author CCC
 * @date 2020/11/29 22:40
 */
public class InsertValueFind {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateArr(6, 100);

        RadixSort.sort(arr);

        System.out.println("数组: "+ Arrays.toString(arr));

        int index = find(arr, arr[0]);

        System.out.println("查到到的索引为: "+ index);
    }

    public static int find(int[] arr, int value) {

        return find(arr, 0, arr.length - 1, value);
    }

    private static int find(int[] arr, int left, int right, int value) {

        System.out.println("调用find()");

        if (left > right || value < arr[0] || value > arr[right]) {
            return -1;
        }

        // 根据差值公式计算cursor
        int cursor = left + (value - arr[left]) / (arr[right] - arr[left]);

        if (value < arr[cursor]) {
            // 向左递归
            return find(arr, left, cursor - 1, value);
        } else if (value > arr[cursor]) {
            // 向右递归
            return find(arr, cursor + 1, right, value);
        } else {
            return cursor;
        }
    }
}
