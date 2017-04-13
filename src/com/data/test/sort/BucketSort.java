package com.data.test.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Frotly on 2017/4/12.
 */
public class BucketSort {
    public static void bucketSort(int[] arr){
        if (arr == null || arr.length ==0) return;
        int bucketNums = 10;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<Integer>());
        }

        for (int i = 0; i < arr.length; i++) {
            buckets.get(f(arr[i])).add(arr[i]);
        }

        for (int i = 0; i < buckets.size(); i++) {
            if(!buckets.get(i).isEmpty()){
                Collections.sort(buckets.get(i));
            }
        }
    }

    public static int f(int x) {
        return x/10;
    }
}
