package com.data.test.sort;

import java.util.Arrays;

/**
 * Created by Frotly on 2017/4/12.
 */
public class CountSort {
    public static void countSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int max = max(arr);

        int[] count = new int[max + 1];
        Arrays.fill(count, 0);
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        int k = 0;
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < count[i]; j++) {
                arr[k++] = i;
            }
        }

    }

    public static int max(int[] arr) {
        int max = Integer.MAX_VALUE;
        for (int ele : arr) {
            if (ele > max)
                max = ele;
        }
        return max;
    }
}
