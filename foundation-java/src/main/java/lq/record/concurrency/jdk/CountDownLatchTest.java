package lq.record.concurrency.jdk;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器，让某个线程等到倒计时结束再执行。
 * 
 * @author 刘泉
 * @date 2017年1月10日 下午3:23:25
 */
public class CountDownLatchTest implements Runnable {

	static final int THREAD_COUNT = 30;

	static final int GROUP_COUNT = 10;

	static final CountDownLatch end = new CountDownLatch(GROUP_COUNT);

	@Override
	public void run() {
		try {
			// 模拟检查耗时
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println("check complete");
			end.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		CountDownLatchTest t = new CountDownLatchTest();
		ExecutorService exe = Executors.newFixedThreadPool(10);
		for (int i = 0; i < THREAD_COUNT; i++) {
			exe.submit(t);
		}
		// 等待检查
		end.await();
		// 发射
		System.out.println("fire!");
		exe.shutdown();
	}

}
