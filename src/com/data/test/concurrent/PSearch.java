package com.data.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by songyigui on 2017/5/31.
 */
public class PSearch {
    static int arr[];
    static ExecutorService pool = Executors.newCachedThreadPool();
    static final int Thread_Num = 4;
    static AtomicInteger result = new AtomicInteger(-1);
    static ThreadLocalRandom random = ThreadLocalRandom.current();

    static {
        arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
//            System.out.println(arr[i]);
        }
    }

    public static int search(int searchValue, int beginPos, int endPos) {
        int i = 0;
        for (i = beginPos; i < endPos; i++) {
            if (result.get() >= 0) {
                return result.get();
            }
            if (arr[i] == searchValue) {
                if (!result.compareAndSet(-1, i)) {
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer> {
        int begin, end, searchValue;

        public SearchTask(int begin, int end, int searchValue) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }

        @Override
        public Integer call() throws Exception {
            int re = search(searchValue, begin, end);
            return re;
        }
    }

    public static int pSearch(int searchValue) throws Exception {

        int subArrSize = arr.length / Thread_Num + 1;
        List<Future<Integer>> re = new ArrayList<Future<Integer>>();
        for (int i = 0; i < arr.length; i += subArrSize) {
            int end = i + subArrSize;
            if (end >= arr.length)
                end = arr.length;
            re.add(pool.submit(new SearchTask(i, end, searchValue)));
        }
        for (Future<Integer> future : re) {
            if (future.get() >= 0)
                return future.get();
        }
        return -1;
    }

    public static int oSearch(int searchValue) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == searchValue) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println(pSearch(55));
        System.out.println(System.currentTimeMillis() - start);
        long start1 = System.currentTimeMillis();
        System.out.println(oSearch(55));
        System.out.println(System.currentTimeMillis() - start1);
    }

}
