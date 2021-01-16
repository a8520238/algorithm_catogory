package com;

public class ObjectLock {

    private static Object lock = new Object(); // 临界资源，互斥锁

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) { // 线程运行时，加对象锁
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread A " + i);
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread B " + i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
//        Thread.sleep(1000); // 让主线程睡眠一会，防止 B 先拿到锁
        new Thread(new ThreadB()).start();
    }
}
