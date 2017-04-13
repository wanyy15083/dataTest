package com.data.test.sort;

import com.sun.org.apache.bcel.internal.generic.SWAP;
import org.omg.CORBA.INTERNAL;

import java.util.Arrays;

/**
 * Created by Frotly on 2017/4/12.
 */
public class HeapSort {
    public static void heapAdjust(int[] arr, int start, int end) {
        int temp = arr[start];

        for (int i = 2 * start + 1; i <= end; i *= 2) {
            if (i < end && arr[i] < arr[i + 1]) {
                i++;
            }
            if (temp >= arr[i]) {
                break;
            }
            arr[start] = arr[i];
            start = i;
        }
        arr[start] = temp;
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        for (int i = arr.length / 2; i >= 0; i--) {
            heapAdjust(arr, i, arr.length - 1);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            Commen.swap(arr, 0, i);
            heapAdjust(arr, 0, i - 1);
        }
    }

    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
