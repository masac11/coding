package com.coding.mutil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolCountDown {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个容量为10的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // 创建CountDownLatch，计数为100
        CountDownLatch latch = new CountDownLatch(100);

        // 循环提交100个任务
        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                try {
                    // 模拟任务处理
                    System.out.println(Thread.currentThread().getName() + " is working");
                } finally {
                    // 每个任务完成后，减少CountDownLatch的计数
                    latch.countDown();
                }
            });
        }

        // 主线程等待所有任务完成
        latch.await();

        // 所有任务完成后，打印100
        System.out.println("100");

        // 关闭线程池
        threadPool.shutdown();
    }
}
