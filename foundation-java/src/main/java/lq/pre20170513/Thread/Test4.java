package lq.pre20170513.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 子线程循环 10 次，接着主线程循环 10，接着又回到子线程循环 10 次，接着再回到主线程又循环 10，如此循环 50 次
 * （与Test3不同的是，这里用并发库来实现）
 */
public class Test4 {
	private static Lock lock = new ReentrantLock();
	private static Condition con = lock.newCondition();
	private static boolean isSub = true;

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(3);
		pool.execute(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					lock.lock();
					try {
						// 若不是子线程，则挂起
						if (!isSub)
							con.await();
						for (int j = 0; j < 10; j++) {
							System.out.println(Thread.currentThread().getName() + ": i=" + i + ", j=" + j);
						}
						isSub = false;
						con.signal();
					} catch (Exception e) {

					} finally {
						lock.unlock();
					}
				}
			}
		});
		pool.shutdown();
		for (int i = 0; i < 50; i++) {
			lock.lock();
			try {
				if (isSub)
					con.await();
				for (int j = 0; j < 10; j++) {
					System.out.println(Thread.currentThread().getName() + ": i=" + i + ", j=" + j);
				}
				isSub = true;
				con.signal();
			} catch (Exception e) {

			} finally {
				lock.unlock();
			}
		}

	}
}
