package com.data.test.concurrent;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by songyigui on 2017/5/31.
 */
public class PShellSort {
    public static void insertSort(int[] arr) {
        int length = arr.length;
        int i, j, key;
        for (i = 1; i < length; i++) {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void shellSort(int[] arr) {
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = h; i < arr.length; i++) {
                if (arr[i] < arr[i - h]) {
                    int tmp = arr[i];
                    int j = i - h;
                    while (j >= 0 && arr[j] > tmp) {
                        arr[j + h] = arr[j];
                        j -= h;
                    }
                    arr[j + h] = tmp;
                }
            }
            h = (h - 1) / 3;
        }
    }

    static int[] arr;
    static ThreadLocalRandom random = ThreadLocalRandom.current();
    static ExecutorService pool = Executors.newCachedThreadPool();

    static {
        arr = new int[10 * 1024];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10 * 1024);
        }
    }

    public static class ShellSortTask implements Runnable {
        int i = 0;
        int h = 0;
        CountDownLatch l;

        public ShellSortTask(int i, int h, CountDownLatch l) {
            this.i = i;
            this.h = h;
            this.l = l;
        }

        @Override
        public void run() {
            if (arr[i] < arr[i - h]) {
                int tmp = arr[i];
                int j = i - h;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = tmp;
            }
            l.countDown();
        }
    }

    public static void pShellSort(int[] arr) throws InterruptedException {
        int h = 1;
        CountDownLatch latch = null;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            System.out.println("h=" + h);
            if (h >= 4) {
                latch = new CountDownLatch(arr.length - h);
            }
            for (int i = h; i < arr.length; i++) {
                if (h >= 4) {
                    pool.execute(new ShellSortTask(i, h, latch));
                } else {
                    if (arr[i] < arr[i - h]) {
                        int tmp = arr[i];
                        int j = i - h;
                        while (j >= 0 && arr[j] > tmp) {
                            arr[j + h] = arr[j];
                            j -= h;
                        }
                        arr[j + h] = tmp;
                    }
                }
            }
            latch.await();
            h = (h - 1) / 3;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        shellSort(arr);
        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(Arrays.toString(arr));

        long start1 = System.currentTimeMillis();
        pShellSort(arr);
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        insertSort(arr);
        System.out.println(System.currentTimeMillis() - start2);
        System.out.println(Arrays.toString(arr));
    }
}
