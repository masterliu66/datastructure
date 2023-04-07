package com.example.datastructure.sort;

import java.util.Arrays;

/**
 * className MergeSort
 * packageName com.example.datastructure.sort
 * description
 *
 * @author CCC
 * @date 2020/11/25 23:51
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateArr(20, 100);

        System.out.println("排序前: "+ Arrays.toString(arr));

        int[] temp = new int[arr.length];

        sort(arr, 0, arr.length - 1, temp);

        System.out.println("排序后: "+ Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {

        if (left < right) {

            int mid = (left + right) / 2;

            // 向左递归
            sort(arr, left, mid, temp);

            // 向右递归
            sort(arr, mid + 1, right, temp);

            // 合并
            merge(arr, left, mid, right, temp);
        }

    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;
        int j = mid + 1;
        int t = 0;

        // 将原始数组左右两边按照大小依次填充到temp数组中
        while (i <= mid && j <= right) {
            temp[t++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        // 将原始数组左右两边剩余元素依次填充到temp数组中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // 将temp数组中的元素拷贝到原始数组响应的位置
        int tempLeft = left;
        t = 0;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }

}
