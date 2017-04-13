package com.data.test;

/**
 * Created by Frotly on 2017/4/5.
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        System.out.println("Before Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();

        selectSort(a);

        System.out.println("After Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();
    }

    //选择排序
    private static void selectSort(int[] a) {
        int i, j;
        int temp;
        for (i = 0; i < a.length - 1; i++) {
            int min = i;
            for (j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
}
