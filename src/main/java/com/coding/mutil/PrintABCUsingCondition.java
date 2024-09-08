package com.coding.mutil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCUsingCondition {
    private static int state = 0;

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition A = lock.newCondition();
    private static final Condition B = lock.newCondition();
    private static final Condition C = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    lock.lock();
                    try {
                        while (state % 3 != 0) {
                            try {
                                A.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("A");
                        state++;
                        B.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });


        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    lock.lock();
                    try {
                        while (state % 3 != 1) {
                            try {
                                B.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("B");
                        state++;
                        C.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });



        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    lock.lock();
                    try {
                        while (state % 3 != 2) {
                            try {
                                C.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("C");
                        state++;
                        A.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        // 启动三个线程
        threadA.start();
        threadB.start();
        threadC.start();

    }

}
