package lq.pre20170513.Thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {
	private int j;
	private Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		ThreadLock t = new ThreadLock();
		for (int i = 0; i < 2; i++) {
			new Thread(t.new Adder()).start();
			new Thread(t.new Subtractor()).start();
		}
	}

	private class Subtractor implements Runnable {

		@Override
		public void run() {
			while (true) {
				/*
				 * synchronized(ThreadLock.this){ System.out.println("j--=" +
				 * j--); //这里抛异常了，锁能释放吗？ }
				 */

				lock.lock();
				try {
					System.out.println("--j=" + --j);
					lock.newCondition().await(2, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		}
	}

	private class Adder implements Runnable {

		@Override
		public void run() {
			while (true) {
				lock.lock();
				try {
					System.out.println("++j=" + ++j);
					lock.newCondition().await(1, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

}
