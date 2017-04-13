package com.data.test;

import java.text.BreakIterator;

/**
 * Created by Frotly on 2017/4/6.
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        System.out.println("Before Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();

        heapSort(a);

        System.out.println("After Insert Sorting:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println();
    }


    //堆排序
    private static void heapSort(int[] a) {
        int len = a.length;
        for (int i = len / 2; i >= 0; i--) {
            sink(i, len - 1, a);
        }
        for (int i = len - 1; i > 0; i--) {
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            sink(0, i, a);
        }
    }

    private static void sink(int parent, int len, int[] a) {
        int temp = a[parent];
        int child = 2 * parent + 1;
        while (child < len) {
            if (child + 1 < len && a[child] < a[child + 1]) {
                child++;
            }
            if (temp > a[child]) {
                break;
            }
            a[parent] = a[child];
            parent = child;
            child = 2 * parent + 1;
        }
        a[parent] = temp;
    }

}
