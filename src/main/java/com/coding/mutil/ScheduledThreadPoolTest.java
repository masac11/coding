package com.coding.mutil;

import java.util.Date;
import java.util.concurrent.*;

public class ScheduledThreadPoolTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 3; i++) {
            Task worker = new Task("task-" + i);
            // 只执行一次
//            scheduledThreadPool.schedule(worker, 5, TimeUnit.SECONDS);
            // 周期性执行，每5秒执行一次
            ScheduledFuture scheduledFuture = scheduledThreadPool.schedule(worker,5, TimeUnit.SECONDS);
            System.out.println(scheduledFuture.get());
//            ScheduledFuture scheduledFuture = scheduledThreadPool.scheduleAtFixedRate(worker, 0,5, TimeUnit.SECONDS);
        }

//        Thread.sleep(10000);

        System.out.println("Shutting down executor...");
        // 关闭线程池
//        scheduledThreadPool.shutdown();
        boolean isDone;
        // 等待线程池终止
        do {
            isDone = scheduledThreadPool.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("awaitTermination...");
        } while(!isDone);

        System.out.println("Finished all threads");
    }


}


class Task implements Callable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println("name = " + name + ", startTime = " + new Date());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + name + ", endTime = " + new Date());
        return this.name;
    }
}
