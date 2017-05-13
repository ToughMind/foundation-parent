package lq.record.concurrency.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量可以指定多个线程同时访问某个资源。
 * 
 * @author 刘泉
 * @date 2017年1月10日 下午2:37:55
 */
public class SemaphoreTest implements Runnable {

	final Semaphore semp = new Semaphore(5);

	@Override
	public void run() {
		try {
			semp.acquire();
			// 模拟耗时操作
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId() + ": done!");
			semp.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService exe = Executors.newFixedThreadPool(20);
		final SemaphoreTest t = new SemaphoreTest();
		for (int i = 0; i < 20; i++) {
			exe.submit(t);
		}
	}

}
