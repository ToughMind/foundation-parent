/**
 * @(#)CpuCacheLineTest.JAVA, 2017年05月06日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package record.concurrency.demo;

/**
 * cpu cache优化，解决伪共享问题。有可能两个变量在同一个缓存行，
 *
 * @author 刘泉 2017年05月06日 17:02
 */
public class CpuCacheLineTest implements Runnable {

    public final static int NUM_THREAD = 2;

    public final static long ITERATIONS = 5L * 1000L * 1000L;

    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREAD];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public CpuCacheLineTest(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        
        Thread[] threads = new Thread[NUM_THREAD];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new CpuCacheLineTest(i));
        }
        for (Thread t: threads) {
            t.start();
        }
        for (Thread t: threads) {
            t.join();
        }

        System.out.println("duration = " + (System.currentTimeMillis() - start));
    }

    public final static class VolatileLong {
        public volatile long value = 0L;

        //public long p1, p2, p3, p4, p5, p6, p7;
    }

}
