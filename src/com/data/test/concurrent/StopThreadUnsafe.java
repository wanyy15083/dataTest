package com.data.test.concurrent;

/**
 * Created by songyigui on 2017/4/26.
 */
public class StopThreadUnsafe {
    public static User u = new User();

    public static class User {
        private int id;
        private String name;

        public User() {
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("id=").append(id);
            sb.append(", name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static class ChangeObjectThread extends Thread {
        volatile boolean stopme = false;

        public void stopMe() {
            stopme = true;
        }

        @Override
        public void run() {
            while (true) {
                if (stopme) {
                    System.out.println("exit by stop me");
                    break;
                }

                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.setId(v);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }


//            while (true) {
//                synchronized (u) {
//                    int v = (int) (System.currentTimeMillis() / 1000);
//                    u.setId(v);
//
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    u.setName(String.valueOf(v));
//                }
//                Thread.yield();
//            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
