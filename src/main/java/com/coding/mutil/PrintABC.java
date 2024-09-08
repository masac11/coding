package com.coding.mutil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {

    private static final Lock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    private static final Condition conditionC = lock.newCondition();
    private static volatile int i = 0;
    private static final int times = 10;

    static Thread threadA = new Thread() {

        @Override
        public void run() {
            for (int time = 0; time < times; time++) {
                lock.lock();
                try {
                    while (i % 3 != 0) {
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("A");
                    i++;
                    conditionB.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    };

    static Thread threadB = new Thread(() -> {
        for (int time = 0; time < times; time++) {
            lock.lock();
            try {
                while (i % 3 != 1) {
                    try {
                        conditionB.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("B");
                i++;
                conditionC.signal();
            } finally {
                lock.unlock();
            }
        }
    });

    static Thread threadC = new Thread() {

        @Override
        public void run() {
            for (int time = 0; time < times; time++) {
                lock.lock();
                try {
                    while (i % 3 != 2) {
                        try {
                            conditionC.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("C");
                    i++;
                    conditionA.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    };
    public static void main(String[] args) {
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
