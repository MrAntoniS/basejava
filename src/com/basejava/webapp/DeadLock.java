package com.basejava.webapp;

public class DeadLock {
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public static void main(String[] args) {
        Thread tread0 = new Thread(() -> {
            synchronized (LOCK_1) {
                System.out.println("Потоком " + Thread.currentThread().getName() + " захвачен обьект LOCK_1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_2) {
                    System.out.println("Потоком " + Thread.currentThread().getName() + " захвачен обьект LOCK_2");
                }
            }
        });

        Thread tread1 = new Thread(() -> {
            synchronized (LOCK_2) {
                System.out.println("Потоком " + Thread.currentThread().getName() + " захвачен обьект LOCK_2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_1) {
                    System.out.println("Потоком " + Thread.currentThread().getName() + " захвачен обьект LOCK_1");
                }
            }
        });

        tread0.start();
        tread1.start();
    }
}