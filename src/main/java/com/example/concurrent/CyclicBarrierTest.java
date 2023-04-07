package com.example.concurrent;

import java.io.Writer;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * className CyclicBarrierTest
 * packageName com.example.concurrent
 * description
 *
 * @author CCC
 * @date 2021/9/4 21:50
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        int n = 4;
        CyclicBarrier barrier = new CyclicBarrier(n);
        for (int i = 0; i < n; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {

        private final CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                // 以睡眠来模拟线程需要预定写入数据操作
                Thread.sleep(1000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                int await = cyclicBarrier.await();
                System.out.println("线程" + Thread.currentThread().getName() + "排序：" + await);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("所有线程写入完毕，继续处理其他任务，比如数据操作");
        }
    }

}
