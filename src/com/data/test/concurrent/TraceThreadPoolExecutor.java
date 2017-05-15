package com.data.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor {
    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
        TimeUnit unit,
        BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override public void execute(Runnable command) {
        super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
    }

    @Override public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace() {
        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
        return new Runnable() {
            @Override public void run() {
                try {
                    task.run();
                } catch (Exception e) {
                    clientStack.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            pools.execute(new DivTask(100, i));
        }
    }

    public static class DivTask implements Runnable {
        int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override public void run() {
            double re = a / b;
            System.out.println(re);
        }
    }
}
