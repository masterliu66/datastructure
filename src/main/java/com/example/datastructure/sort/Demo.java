package com.example.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * className Demo
 * packageName com.example.datastructure.sort
 * description
 *
 * @author CCC
 * @date 2020/11/12 0:35
 */
public class Demo {

    /**
     * 求数组中前k个最小数
     */
    public static void quickSort(int[] arr, int left, int right, int k){

        if (left <= right) {
            return;
        }

        int pos = randomizedPartition(arr, left, right);

        if (pos == k - 1) {
            return;
        }

        if (pos < k - 1) {
            quickSort(arr, pos + 1, right, k);
        } else {
            quickSort(arr, left, pos - 1, k);
        }
    }

    private static int randomizedPartition(int[] arr, int left, int right) {
        int i = new Random().nextInt(right - left + 1) + left;
        swap(arr, i, right);
        return partition(arr, left, right);
    }

    private static int partition(int[] arr, int left, int right) {

        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, ++i, j);
            }
        }

        swap(arr, i + 1, right);

        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean test(int[] arr, int k) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            max = Math.max(max, arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < max) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        int k = 80000;
        int[] arr = ArrayGenerator.generateArr(800 << 1, 10000);
        quickSort(arr, 0, arr.length - 1, k);
        assert test(arr, k);
        System.out.println(Arrays.toString(arr));
    }

}
