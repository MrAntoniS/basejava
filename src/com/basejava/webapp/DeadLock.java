package com.basejava.webapp;

public class DeadLock {
    private static final String LOCK_1 = "Lock_1";
    private static final String LOCK_2 = "Lock_2";

    public static void main(String[] args) {
        takeObject(LOCK_1, LOCK_2);
        takeObject(LOCK_2, LOCK_1);
    }

    public static void takeObject(Object obj1, Object obj2) {
        new Thread(() -> {
            synchronized (obj1) {
                System.out.println("Потоком " + Thread.currentThread().getName() + " захвачен обьект " + obj1);
                try {
                    Thread.sleep(500);
                    System.out.println("Поток " + Thread.currentThread().getName() + " ожидает обьект " + obj2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println("Потоком " + Thread.currentThread().getName() + " захвачен обьект " + obj2);
                }
            }
        }).start();
    }
}