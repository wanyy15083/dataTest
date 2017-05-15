package com.data.test.search;

import java.util.LinkedHashMap;

public class SequelSearch {
    public static void main(String[] args) {
        int[] a = {95, 20, 36, 5, 67, 23, 11, 15, 66, 47};
        int index = serch(a, 11);
        System.out.println("index=" + index);
    }

    public static int serch(int[] a, int b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b) {
                return i;
            }
        }
        return -1;
    }

}
