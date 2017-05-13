/**
 * @(#)DisruptorProducerConsumerTest.JAVA, 2017年05月04日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package record.concurrency.demo;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用Disruptor实现生产者和消费者案例。高性能无锁内存队列。
 *
 * @author 刘泉 2017年05月04日 16:43
 */
public class DisruptorProducerConsumerTest {

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        PCData2Factory factory = new PCData2Factory();
        int bufferSize = 1024;
        Disruptor<PCData2> disruptor = new Disruptor<PCData2>(factory,
            bufferSize, executor, ProducerType.MULTI,
            new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new Consumer2(), new Consumer2(),
            new Consumer2(), new Consumer2());
        disruptor.start();

        RingBuffer<PCData2> ringBuffer = disruptor.getRingBuffer();
        Producer2 producer2 = new Producer2(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (int i = 0; true; i++) {
            bb.putInt(0, i);
            producer2.pushData(bb);
            Thread.sleep(100);
            System.out.println("add data" + i);
        }
    }
}

class PCData2 {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class PCData2Factory implements EventFactory<PCData2> {
    public PCData2 newInstance() {
        return new PCData2();
    }
}

class Producer2 {

    private final RingBuffer<PCData2> ringBuffer;

    public Producer2(RingBuffer<PCData2> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb) {
        long sequence = ringBuffer.next();
        try {
            PCData2 event = ringBuffer.get(sequence);
            event.setValue(bb.getInt(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}

class Consumer2 implements WorkHandler<PCData2> {

    @Override
    public void onEvent(PCData2 pcData2) throws Exception {
        System.out.println(Thread.currentThread().getId() + ": event="
            + pcData2.getValue() * pcData2.getValue());
    }
}
