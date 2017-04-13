package com.data.test;

/**
 * Created by Frotly on 2017/4/5.
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        System.out.println("Before Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();

        shellSort(a);

        System.out.println("After Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();
    }

    //希尔排序
    private static void shellSort(int[] a) {
        int i, j;
        int temp;
        int len = a.length / 2;
        while (len >= 1) {
            for (i = len; i < a.length; i++) {
                for (j = i; j >= len; j -= len) {
                    if (a[j] < a[j - len]) {
                        temp = a[j];
                        a[j] = a[j - len];
                        a[j - len] = temp;
                    }
                }
            }
            len = len / 2;
        }
    }
}
