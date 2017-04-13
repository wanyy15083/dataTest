package com.data.test;

/**
 * Created by Frotly on 2017/4/5.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        System.out.println("Before Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();

        quickSort(a, 0, a.length - 1);

        System.out.println("After Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();
    }

    //快速排序
    private static void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int j = partition(a, left, right);
        quickSort(a, left, j - 1);
        quickSort(a, j + 1, right);
    }

    private static int partition(int[] a, int left, int right) {
        int i = left, j = right + 1;
        int pivot = a[left];
        while (true) {
            while (a[++i] < pivot) {
                if (i == right) {
                    break;
                }
            }
            while (pivot < a[--j]) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        int temp = a[left];
        a[left] = a[j];
        a[j] = temp;
        return j;
    }
}
