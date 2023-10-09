package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class VirtualThreadTest {

    public static void main(String[] args) throws Exception {

//        var scopedValue = ScopedValue.newInstance();

        ThreadFactory factory = Thread.ofVirtual().name("Virtual-", 1).factory();
        ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory);

        for (int i = 1; i <= 100; i++) {
            final int j = i;
            executorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": " + j));
        }

        TimeUnit.SECONDS.sleep(1);

        executorService.shutdown();
    }

}
