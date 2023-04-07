package com.example.test;

import java.util.concurrent.*;

public class PublisherTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        // 1. 定义发布者, 发布的数据类型是 Integer
        // 直接使用jdk自带的SubmissionPublisher, 它实现了 Publisher 接口
        // executor – 用于异步发布任务的执行器
        // maxBufferCapacity – 每个订阅者缓冲区的最大容量（会向上取整到最接近的 2 次幂）
        // handler - 如果非 null, 则在方法onNext中抛出异常时调用
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>(pool, 8, null);

        // 2. 定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                // 保存订阅关系, 需要用它来给发布者响应
                this.subscription = subscription;

                // 请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                // 接受到一个数据, 处理
                System.out.println("接受到数据: " + item);

                // 处理完调用request再请求一个数据
                this.subscription.request(1);

                // 或者已经达到了目标, 可以调用cancel告诉发布者不再接受数据了
                // this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                // 出现了异常(例如处理数据的时候产生了异常)
                throwable.printStackTrace();

                // 我们可以告诉发布者, 后面不接受数据了
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 全部数据处理完了(发布者关闭了)
                System.out.println("处理完了!");
                pool.shutdown();
            }

        };

        // 3. 发布者和订阅者 建立订阅关系
        publisher.subscribe(subscriber);

        try {
            // 4. 生产数据, 并发布
            // 这里忽略数据生产过程
            for (int i = 0; i < 20; i++) {
                System.out.println("生成数据:" + i);
                // submit是个block方法
                publisher.submit(i);
            }
        } finally {
            // 5. 结束后 关闭发布者
            // 使用 finally 或者 try-resource 确保关闭
            publisher.close();
        }

        // 主线程延迟停止, 否则数据没有消费就会退出
        while (!pool.isShutdown()) {
            TimeUnit.MICROSECONDS.sleep(1);
        }

        System.out.println("End");
    }

}
