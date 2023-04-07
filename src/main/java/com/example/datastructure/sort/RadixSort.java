package com.example.datastructure.sort;

import java.util.Arrays;

/**
 * className RadixSort
 * packageName com.example.datastructure.sort
 * description
 *
 * @author CCC
 * @date 2020/11/27 0:25
 */
public class RadixSort {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateArr(20, 100);

        System.out.println("排序前: "+ Arrays.toString(arr));

        sort(arr);

        System.out.println("排序后: "+ Arrays.toString(arr));
    }

    public static void sort(int[] arr) {

        // 找出数组中的最大数的位数
        int max = Integer.MIN_VALUE;

        for (int value : arr) {
            max = Math.max(max, value);
        }

        int maxLength = String.valueOf(max).length();

        // 声明10个桶和桶中已存放数据个数的计数器数组
        final int bucketLength = 10;

        int[][] buckets = new int[bucketLength][arr.length];
        int[] bucketElementCounts = new int[bucketLength];

        int index;
        for (int i = 0; i < maxLength; i++) {

            // 将数组中每个数据按个,十,百,千...位的值放到对应下标的桶中, 并将对应的桶计数器+1
            for (int value : arr) {
                int digit = value / (i == 0 ? 1 : i * 10) % 10;
                buckets[digit][bucketElementCounts[digit]++] = value;
            }

            // 将桶中存放的数据依次放到回原数组中
            index = 0;
            for (int k = 0; k < bucketLength; k++) {

                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = buckets[k][l];
                }

                // 清空桶计数器的值
                bucketElementCounts[k] = 0;

                if (index == arr.length) {
                    break;
                }
            }
        }

    }

}
