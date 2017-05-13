package record.concurrency.advanced;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import sun.misc.Unsafe;
import sun.misc.VM;
import sun.reflect.Reflection;

/**
 * 多线程下请求Random对象的性能比较。一种是多线程共享一个Random对象，一种是多线程各分配一个Random。
 * 
 * @author 刘泉
 * @date 2017年2月17日 下午5:37:55
 */
public class RandomTest {
	// 产生的随机数数量
	static final int RANDOM_COUNT = 100;

	// 参与工作的线程数量
	static final int THREAD_COUNT = 100000;

	static ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);

	// 多线程共享一个Random实例
	public static Random rd = new Random(123);

	// 多线程各分配一个Random
	public static ThreadLocal<Random> trd = new ThreadLocal<Random>() {

		@Override
		protected Random initialValue() {
			return new Random(123);
		}

	};

	public static class RandomTask implements Callable<Long> {
		private int mode = 0;

		public RandomTask(int mode) {
			this.mode = mode;
		}

		public Random getRandom() {
			if (mode == 0) {
				return rd;
			} else if (mode == 1) {
				return trd.get();
			} else {
				return null;
			}
		}

		@Override
		public Long call() throws Exception {
			long start = System.currentTimeMillis();
			for (int i = 0; i < RANDOM_COUNT; i++) {
				getRandom().nextInt();
			}
			long end = System.currentTimeMillis();
			// System.out.println(Thread.currentThread().getName() + " spend" +
			// (end - start) + "ms");
			return end - start;
		}

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Future<Long>[] fu = new Future[THREAD_COUNT];
		// 先测试多个线程共用一个Random实例
		for (int i = 0; i < THREAD_COUNT; i++) {
			fu[i] = es.submit(new RandomTask(0));
		}
		long totalTime = 0;
		long start = System.currentTimeMillis();
		for (int i = 0; i < THREAD_COUNT; i++) {
			totalTime += fu[i].get();
		}
		System.out.println("多线程访问同一个Random实例：" + totalTime + "ms");
		System.out.println("应用层调用耗时：" + (System.currentTimeMillis() - start) + "ms");
		// 再测试多个线程各自占有一个ThreadLocal
		for (int i = 0; i < THREAD_COUNT; i++) {
			fu[i] = es.submit(new RandomTask(1));
		}
		totalTime = 0;
		start = System.currentTimeMillis();
		for (int i = 0; i < THREAD_COUNT; i++) {
			totalTime += fu[i].get();
		}
		System.out.println("使用ThreadLocal包装Random实例：" + totalTime + "ms");
		System.out.println("应用层调用耗时：" + (System.currentTimeMillis() - start) + "ms");
		// 上面的时间主要耗在一个线程里多次读取该Random的消耗
		es.shutdown();
	}

}
