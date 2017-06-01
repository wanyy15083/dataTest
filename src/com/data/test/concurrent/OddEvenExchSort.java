package com.data.test.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by songyigui on 2017/5/31.
 */
public class OddEvenExchSort {


    public static void oddEventSort(int[] arr) {
        int exchFlag = 1, start = 0;
        while (exchFlag == 1 || start == 1) {
            exchFlag = 0;
            for (int i = start; i < arr.length - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    exchFlag = 1;
                }
            }
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
    }

    static ThreadLocalRandom random = ThreadLocalRandom.current();

    static ExecutorService pool = Executors.newCachedThreadPool();

    static int[] arr;

    static int exchFlag = 1;

    static {
        arr = new int[10 * 1024];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10 * 1024);
        }
    }

    static synchronized void setExchFlag(int v) {
        exchFlag = v;
    }

    static synchronized int getExchFlag() {
        return exchFlag;
    }

    public static class OddEventSortTask implements Runnable {
        int i;
        CountDownLatch latch;

        public OddEventSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    public static void pOddEventSort(int[] arr) throws InterruptedException {
        int start = 0;
        while (getExchFlag() == 1 || start == 1) {
            setExchFlag(0);
            CountDownLatch latch = new CountDownLatch(arr.length / 2 - (arr.length % 2 == 0 ? start : 0));
            for (int i = start; i < arr.length - 1; i += 2) {
                pool.submit(new OddEventSortTask(i, latch));
            }
            latch.await();
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        oddEventSort(arr);
        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(Arrays.toString(arr));

        long start1 = System.currentTimeMillis();
        pOddEventSort(arr);
        System.out.println(System.currentTimeMillis() - start1);
//        System.out.println(Arrays.toString(arr));
    }
}
