package com.example.datastructure.sort;

import java.util.Arrays;

/**
 * className PriorityQueue
 * packageName com.example.datastructure.sort
 * description
 *
 * @author CCC
 * @date 2021/9/3 23:57
 */
public class PriorityQueue {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateArr(8, 100);

        System.out.println("排序前: "+ Arrays.toString(arr));

        buildHeap(arr);

        int heapSize = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapSize--;
            adjustHeap(arr, 0, heapSize);
        }

        System.out.println("排序后: "+ Arrays.toString(arr));
    }

    private static void buildHeap(int[] arr) {

        int heapSize = arr.length;
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, heapSize);
        }
    }

    private static void adjustHeap(int[] arr, int root, int heapSize) {

        int key = arr[root];

        int half = heapSize >> 1;

        while (root < half) {
            int left = root * 2 + 1;
            int right = left + 1;

            int child = left;
            if (right < heapSize && arr[right] < arr[left]) {
                child = right;
            }

            if (arr[child] >= arr[root]) {
                break;
            }

            swap(arr, child, root);
            root = child;
        }

        arr[root] = key;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
