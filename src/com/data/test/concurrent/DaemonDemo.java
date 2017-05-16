package com.data.test.concurrent;

/**
 * Created by songyigui on 2017/4/27.
 */
public class DaemonDemo {
    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true){
                System.out.println("I'm alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }
}
