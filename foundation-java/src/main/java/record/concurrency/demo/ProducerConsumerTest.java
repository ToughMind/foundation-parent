/**
 * @(#)ProducerConsumerTest.JAVA, 2017年05月04日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package record.concurrency.demo;

import com.sun.xml.internal.bind.v2.runtime.output.Pcdata;
import record.test.Test1;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试生产者-消费者实例，计算乘方。用BlockingQueue充当共享内存缓冲区。
 *
 * @author 刘泉 2017年05月04日 15:14
 */
public class ProducerConsumerTest {
    public static void main(String[] args) throws InterruptedException {
        // 建立缓冲区
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);
        // 建立生产者
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);
        // 建立消费者
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
         service.execute(c3);
        Thread.sleep(10 * 1000);
        p1.stop();
        p2.stop();
        p3.stop();
        Thread.sleep(3000);
        service.shutdown();
    }
}

// 共享数据模型，不变模式设计
final class PCData {
    private final int intData;

    public PCData(int d) {
        this.intData = d;
    }

    public String toString() {
        return "data=" + this.intData;
    }

    public int getIntData() {
        return intData;
    }
}

class Producer implements Runnable {

    private volatile boolean isRunning = true;

    // 内存缓冲区
    private BlockingQueue<PCData> queue;

    // 总数，原子操作
    private static AtomicInteger count = new AtomicInteger();

    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();
        System.out.println("开始生产，id=" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println(data + "加入队列");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println(data + "加入队列失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}

class Consumer implements Runnable {
    // 内存缓冲区
    private BlockingQueue<PCData> queue;

    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random r = new Random();
        System.out.println("开始消费，id=" + Thread.currentThread().getId());
        try {
            while (true) {
                PCData data = queue.take();
                if (null != data) {
                    // 计算乘方
                    int re = data.getIntData() * data.getIntData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}",
                        data.getIntData(), data.getIntData(), String.valueOf(re)));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
