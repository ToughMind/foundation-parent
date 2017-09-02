package lq.exam.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有A、B、C三个线程，要求同时启动三个线程，按顺序执行ABC，循环10次。（Semaphore）
 *
 * @author 刘泉 2017年08月29日 17:17
 */
public class Test4 {

    private static Semaphore A = new Semaphore(1);

    private static Semaphore B = new Semaphore(1);

    private static Semaphore C = new Semaphore(1);

    static int RUN_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        a.start();
        b.start();
        c.start();
        c.join();
        long end = System.currentTimeMillis();
        System.out.println("time=" + (end - start));
    }

    static Thread a = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i < RUN_NUMBER; i++) {
                    A.acquire();
                    System.out.println("第" + i + "次");
                    doJob("a", 2);
                    B.release();
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
                for (int i = 0; i < RUN_NUMBER; i++) {
                    B.acquire();
                    doJob("b", 2);
                    C.release();
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
                for (int i = 0; i < RUN_NUMBER; i++) {
                    C.acquire();
                    doJob("c", 2);
                    A.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    static void doJob(String str, int num) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            System.out.println(str + i);
            str.intern();
            Thread.sleep(100);
        }
    }

}
