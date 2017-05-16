package com.data.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by songyigui on 2017/4/28.
 */
public class SemapDemo implements Runnable {
    final Semaphore semap = new Semaphore(5);

    @Override
    public void run() {
        try {
            semap.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done!");
            semap.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}
