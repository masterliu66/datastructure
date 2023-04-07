package com.example.datastructure.sort;

import java.util.Arrays;

/**
 * className HeapSort
 * packageName com.example.datastructure.sort
 * description
 *
 * @author CCC
 * @date 2020/12/9 21:36
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateArr(8, 100);

        System.out.println("排序前: "+ Arrays.toString(arr));

        heapSort(arr);

        System.out.println("排序后: "+ Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {

        // 将数组调整为一个大顶堆
        for (int index = arr.length / 2 - 1; index >= 0; index--) {
            adjustMaxHeap(arr, index, arr.length);
        }

        int length = arr.length;

        int temp;
        for (int i = length - 1; i > 0; i--) {
            // 将堆顶元素与末尾元素交换, 将末尾元素踢出数组后继续循环
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // 重新调整数组为大顶堆
            adjustMaxHeap(arr, 0, i);
        }

    }

    /**
     * 将一个数组的特定区域调整为大顶堆
     * @param arr 待调整的数组
     * @param index 当前结点的索引
     * @param length 需要继续调整的结点数量
     */
    private static void adjustMaxHeap(int[] arr, int index, int length) {

        if (index >= arr.length - 1) {
            return;
        }

        // 取出当前结点的权值, 保存在一个临时变量中
        int temp = arr[index];

        // i指向索引为index的结点的左子树
        for (int i = 2 * index + 1; i < length; i = 2 * i + 1) {
            if (i + 1 < length && arr[i] < arr[i + 1]) {
                // i指向右子树
                i++;
            }
            if (arr[i] <= temp) {
                return;
            }
            // 索引为index的结点的权值永远大于其左右子树的权值
            arr[index] = arr[i];
            // 继续调整索引为i的结点
            index = i;
        }

        // 将temp的值放到调整后的位置
        arr[index] = temp;
    }

}
