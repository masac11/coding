package com.coding.mutil;

import java.util.concurrent.Semaphore;

public class PrintABCUsingSemaphore {
    private int times;
    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);

    public PrintABCUsingSemaphore(int times) {
            this.times = times;
    }

    public static void main(String[] args) {
        PrintABCUsingSemaphore printABCUsingSemaphore = new PrintABCUsingSemaphore(10);
        new Thread(() -> printABCUsingSemaphore.print("A", semaphoreA, semaphoreB)).start();
        new Thread(() -> printABCUsingSemaphore.print("B", semaphoreB, semaphoreC)).start();
        new Thread(() -> printABCUsingSemaphore.print("C", semaphoreC, semaphoreA)).start();
    }

    private void print(String name, Semaphore current, Semaphore next) {
            for (int i = 0; i < times; i++) {
                try {
//                    System.out.println(Thread.currentThread().getName());
                    current.acquire();
                    System.out.print(name);
                    next.release();
//                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
