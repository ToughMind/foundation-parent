package lq.exam.concurrency;

import lq.cipher.test.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有A、B、C三个线程，要求同时启动三个线程，按顺序执行ABC，循环10次。（ReentrantLock）
 * <p>
 * 这种方式其实并不好，因为会循环很多次，很多次是条件不满足跳过了，感觉性能比较低。
 * </p>
 * 
 * @author 刘泉 2017年08月28日 11:35
 */
public class Test2 {

    static Lock lock = new ReentrantLock(); // 控制同步，用锁保证线程访问的互斥

    static int state = 0; // 控制任务执行的顺序

    static int RUN_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        a.start();
        b.start();
        c.start();
       // c.join();
        long end = System.currentTimeMillis();
       // System.out.println("time=" + (end - start));
    }

    static Thread a = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i < RUN_NUMBER;) {
                    //lock.lock();
                    //synchronized (Test.class) {
                        if (state % 3 == 0) {
                            System.out.println("第" + (i + 1) + "次:");
                            doJob("a", 2);
                            state++;
                            i++;
                    //    }
                    }
                    //lock.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    static Thread b = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i < RUN_NUMBER;) {
                    // lock.lock();
                    //synchronized (Test.class) {
                        if (state % 3 == 1) {
                            doJob("b", 2);
                            state++;
                            i++;
                    //    }
                    }
                    // lock.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    static Thread c = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i < RUN_NUMBER;) {
                    // lock.lock();
                   // synchronized (Test.class) {
                        if (state % 3 == 2) {
                            doJob("c", 2);
                            state++;
                            i++;
                        }
                   // }
                    //lock.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    static void doJob(String str, int num) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            System.out.println(str + i);
            Thread.sleep(100);
        }
    }

}
