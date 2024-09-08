package com.coding.mutil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintTest {
    private static volatile int time = 100;
    private static volatile int curr = 0;

    private static Lock lock = new ReentrantLock();

    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();

    public static void main(String[] args) {
        Thread a = new Thread(
                () -> {
                    for (int i = 0; i < time; i++) {
                        lock.lock();
                        try {
                            while (curr % 3 != 0) {
                                try {
                                    A.await();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            System.out.println("A");
                            curr++;
                            B.signal();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
        );
        Thread b = new Thread(
                () -> {
                    for (int i = 0; i < time; i++) {
                        lock.lock();
                        try {
                            while (curr % 3 != 1) {
                                try {
                                    B.await();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            System.out.println("B");
                            curr++;
                            C.signal();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
        );
        Thread c = new Thread(
                () -> {
                    for (int i = 0; i < time; i++) {
                        lock.lock();
                        try {
                            while (curr % 3 != 2) {
                                try {
                                    C.await();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            System.out.println("C");
                            curr++;
                            A.signal();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
        );
        a.start();
        b.start();
        c.start();
    }
}
