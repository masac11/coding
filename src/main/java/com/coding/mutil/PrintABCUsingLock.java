package com.coding.mutil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCUsingLock {

    private int times; // 控制打印次数
    private int state;   // 当前状态值：保证三个线程之间交替打印
    private Lock lock = new ReentrantLock();

    public PrintABCUsingLock(int times) {
        this.times = times;
    }

    private void printLetter(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.print(name);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABCUsingLock loopThread = new PrintABCUsingLock(5);

        new Thread(() -> {
            loopThread.printLetter("B", 1);
        }, "B").start();

        new Thread(() -> {
            loopThread.printLetter("A", 0);
        }, "A").start();

        new Thread(() -> {
            loopThread.printLetter("C", 2);
        }, "C").start();
    }
}
