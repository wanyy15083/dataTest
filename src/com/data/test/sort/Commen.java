package com.data.test.sort;

/**
 * Created by Frotly on 2017/4/12.
 */
public class Commen {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
