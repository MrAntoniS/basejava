package com.basejava.webapp;

public class DeadLock {
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public static void main(String[] args) {
        Thread tread0 = new Thread(() -> {
            takeObject(LOCK_1, LOCK_2);
        });

        Thread tread1 = new Thread(() -> {
            takeObject(LOCK_2, LOCK_1);
        });

        tread0.start();
        tread1.start();
    }

    public static void takeObject(Object obj1, Object obj2) {
        synchronized (obj1) {
            System.out.println("Потоком " + Thread.currentThread().getName() + " захвачен обьект " + obj1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("Потоком " + Thread.currentThread().getName() + " захвачен обьект " + obj2);
            }
        }
    }
}