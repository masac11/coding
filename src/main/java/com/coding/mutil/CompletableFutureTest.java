package com.coding.mutil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(
                () -> api1("name")
        );

        CompletableFuture<Integer> c1 = CompletableFuture.supplyAsync(() -> 10);
        c1.thenApply(r -> r * 1).thenApply(r -> r * 2).thenApply(r -> r * 3);
        c1.thenAccept(r -> System.out.println(r));
        c1.thenRun(() -> System.out.println("final"));

//        CompletableFuture<String> cf2 = cf.thenApplyAsync(CompletableFutureTest::api2);

//                .findFirst() // 找到第一个元素
//                .ifPresent(System.out::println);
//        CompletableFuture<String> cf2 = cf.thenApplyAsync(CompletableFutureTest::api2);
//        cf2.thenApplyAsync(CompletableFutureTest::api3);
//        Thread.sleep(20000);
    }

    public static String api1(String str) {
        System.out.println("hello " + str);
        return str;
    }

    public static String api2(String str) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("do " + str);
        return str;
    }

    public static String api3(String str) {
        System.out.println("end " + str);
        return str;
    }
}
