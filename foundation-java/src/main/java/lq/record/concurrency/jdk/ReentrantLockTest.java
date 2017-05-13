package lq.record.concurrency.jdk;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁可以完全替代synchronized。
 * 
 * @author 刘泉
 * @date 2017年1月9日 下午7:21:18
 */
public class ReentrantLockTest implements Runnable {

	public static ReentrantLock lock = new ReentrantLock();

	public static int i = 0;

	@Override
	public void run() {
		for (int j = 0; j < 1000000; j++) {
			lock.lock();
			try {
				i++;
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTest rl = new ReentrantLockTest();
		Thread t1 = new Thread(rl);
		Thread t2 = new Thread(rl);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}

}
