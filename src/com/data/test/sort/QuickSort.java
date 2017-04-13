package com.data.test.sort;

/**
 * Created by Frotly on 2017/4/12.
 */
public class QuickSort {
    public static int partition(int[] arr, int left, int right) {
        int pivotKey = arr[left];
        int pivotPointer = left;

        while (left < right) {
            while (left < right && arr[right] >= pivotKey)
                right--;
            while (left < right && arr[left] <= pivotKey)
                left++;
            Commen.swap(arr, left, right);
        }
        Commen.swap(arr, pivotPointer, left);
        return left;
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotPos = partition(arr, left, right);
        quickSort(arr, left, pivotPos - 1);
        quickSort(arr, pivotPos + 1, right);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        quickSort(arr, 0, arr.length - 1);
    }
}
