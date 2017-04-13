package com.data.test;

/**
 * Created by Frotly on 2017/4/5.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        System.out.println("Before Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();

        bubbleSort(a);

        System.out.println("After Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();
    }

    //冒泡排序
    private static void bubbleSort(int[] a) {
        int i, j;
        int temp;
        for (j = a.length - 1; j > 0; j--) {
            for (i = 0; i < j; i++) {
                if (a[i + 1] < a[i]) {
                    temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = temp;
                }
            }

        }
    }

}
