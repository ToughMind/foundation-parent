package record.concurrency.jdk;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试读写锁和重入锁的对比。若读操作远大于写操作，则可发挥读写锁最大功效。
 * 
 * @author 刘泉
 * @date 2017年1月10日 下午2:53:15
 */
public class ReadWriteLockTest {

	// 作为读写锁对比的重入锁。
	private static Lock lock = new ReentrantLock();

	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static Lock readLock = readWriteLock.readLock();

	private static Lock writeLock = readWriteLock.writeLock();

	private int value;

	public Object handleRead(Lock lock) {
		try {
			lock.lock();
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + "读操作");
			return value;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return null;
	}

	public void handleWrite(Lock lock, int value) {
		try {
			lock.lock();
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + "写操作");
			this.value = value;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final ReadWriteLockTest t = new ReadWriteLockTest();
		// 读线程
		Runnable read = new Runnable() {

			@Override
			public void run() {
				// 此处可以换读锁或者重入锁，比较性能
				// t.handleRead(lock);
				t.handleRead(readLock);
			}
		};

		// 写线程
		Runnable write = new Runnable() {

			@Override
			public void run() {
				// 此处可以写锁或者重入锁
				// t.handleWrite(lock, new Random().nextInt());
				t.handleWrite(writeLock, new Random().nextInt());
			}
		};

		for (int i = 0; i < 20; i++) {
			new Thread(read, "线程" + i).start();
		}

		for (int i = 0; i < 20; i++) {
			new Thread(write, "线程" + i).start();
		}

	}

}
