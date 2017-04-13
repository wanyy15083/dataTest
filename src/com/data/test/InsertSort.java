package com.data.test;

/**
 * Created by Frotly on 2017/4/5.
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        System.out.println("Before Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();

        insertSort(a);

        System.out.println("After Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();
    }

    //插入排序
    private static void insertSort(int[] a) {
        int i, j;
        int temp;
        for (i = 1; i < a.length; i++) {
            for (j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
    }
}
