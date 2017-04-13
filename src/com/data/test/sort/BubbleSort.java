package com.data.test.sort;

/**
 * Created by Frotly on 2017/4/12.
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    Commen.swap(arr, j - 1, j);
                }
            }
        }
    }

}
