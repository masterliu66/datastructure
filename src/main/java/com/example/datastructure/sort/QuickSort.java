package com.example.datastructure.sort;

import java.util.Arrays;

/**
 * className QuickSort
 * packageName com.example.datastructure.sort
 * description
 *
 * @author CCC
 * @date 2020/11/11 23:31
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateArr(16, 100);

        System.out.println("排序前: "+ Arrays.toString(arr));

//        sort(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length - 1);

        System.out.println("排序后: "+ Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {

        int pivot = arr[left];

        int i = left, j = right;
        while (i < j) {

            // 注意是先右后左，这样最后i == j的时候，nums[i]还是小于nums[left]的值就可以交换到正确的地点
            // 向左找到第1个小于key的数
            while (i < j && pivot <= arr[j]) {
                j--;
            }
            // 向右找到第1个大于key的数
            while (i < j && pivot >= arr[i]) {
                i++;
            }

            swap(arr, i, j);
        }

        // pivot换到期望的位置
        swap(arr, left, i);
        // 递归搜左
        if (left < i - 1) {
            quickSort(arr, left, i - 1);
        }
        // 递归搜右
        if (right > i + 1) {
            quickSort(arr, i + 1, right);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];

        int temp;
        while (l < r) {

            while (arr[l] < pivot) {
                l++;
            }

            while (arr[r] > pivot) {
                r--;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r--;
            }

            if (arr[r] == pivot) {
                l++;
            }
        }

        sort(arr, left, l - 1);

        sort(arr, r + 1, right);
    }

}
