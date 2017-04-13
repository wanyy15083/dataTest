package com.data.test.sort;

/**
 * Created by Frotly on 2017/4/12.
 */
public class SelectSort {
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Commen.swap(arr, i, minIndex);
            }
        }
    }
}
