package com.data.test.search;

import com.data.test.sort.QuickSort1;

public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        QuickSort1.sort(a);
        int index = binarySearch(a, 11);
        System.out.println("index=" + index);
    }

    public static int binarySearch(int[] a, int b) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < b)
                low = mid + 1;
            else if (a[mid] > b)
                high = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] a, int low, int high, int b) {
        if (low > high)
            return -1;
        int mid = (low + high) / 2;
        if (a[mid] == b)
            return mid;
        else if (a[mid] < b)
            return binarySearchRecursive(a, mid + 1, high, b);
        else
            return binarySearchRecursive(a, low, high - 1, b);
    }

}
