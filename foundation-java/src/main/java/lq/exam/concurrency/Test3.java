package lq.exam.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有A、B、C三个线程，要求同时启动三个线程，按顺序执行ABC，循环10次。（Condition）
 *
 * @author 刘泉 2017年08月29日 17:17
 */
public class Test3 {

    static Lock lock = new ReentrantLock(); // 控制同步，用锁保证线程访问的互斥

    static int state = 0; // 控制任务执行的顺序

    static int RUN_NUMBER = 10;

    static Condition A = lock.newCondition();

    static Condition B = lock.newCondition();

    static Condition C = lock.newCondition();

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
            lock.lock();
            try {
                for (int i = 0; i < RUN_NUMBER; i++) {
                    while (state % 3 != 0)
                        A.await();
                    System.out.println("第" + i + "次");
                    doJob("a", 2);
                    state++;
                    B.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    });

    static Thread b = new Thread(new Runnable() {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < RUN_NUMBER; i++) {
                    while (state % 3 != 1)
                        B.await();
                    doJob("b", 2);
                    state++;
                    C.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    });

    static Thread c = new Thread(new Runnable() {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < RUN_NUMBER; i++) {
                    while (state % 3 != 2)
                        C.await();
                    doJob("c", 2);
                    state++;
                    A.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
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
