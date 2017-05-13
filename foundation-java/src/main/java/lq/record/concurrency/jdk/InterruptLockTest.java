package lq.record.concurrency.jdk;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 中断响应，解决死锁情况。
 * 
 * @author 刘泉
 * @date 2017年1月10日 上午10:07:06
 */
public class InterruptLockTest implements Runnable {

	public static ReentrantLock lock1 = new ReentrantLock();

	public static ReentrantLock lock2 = new ReentrantLock();

	int lock;

	/*
	 * 控制加锁顺序，方便造成死锁。
	 */
	public InterruptLockTest(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		/**
		 * t1获得lock1，t2获得lock2，然后互相等待。
		 */
		try {
			if (lock == 1) {
				lock1.lockInterruptibly();
				Thread.sleep(500);
				lock2.lockInterruptibly();
			} else {
				lock2.lockInterruptibly();
				Thread.sleep(500);
				lock1.lockInterruptibly();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock1.isHeldByCurrentThread())
				lock1.unlock();
			if (lock2.isHeldByCurrentThread())
				lock2.unlock();
			System.out.println(Thread.currentThread().getId() + "：线程退出");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		InterruptLockTest r1 = new InterruptLockTest(1);
		InterruptLockTest r2 = new InterruptLockTest(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
		// 中断其中一个线程，可以打破死锁
		t2.interrupt();
	}

}
