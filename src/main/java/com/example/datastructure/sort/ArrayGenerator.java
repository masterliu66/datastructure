package com.example.datastructure.sort;

import java.util.Random;

/**
 * className ArrayGenerator
 * packageName com.example.datastructure.sort
 * description
 *
 * @author CCC
 * @date 2020/11/11 23:32
 */
public class ArrayGenerator {

    public static int[] generateArr(int length, int bound) {

        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(bound);
        }

        return arr;
    }

}
