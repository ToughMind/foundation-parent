package record.concurrency.advanced;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试ThreadLocal的垃圾回收机制。
 * <p>
 * 跟踪ThreadLocal对象以及其内部的SimpleDateFormat对象的垃圾回收机制。
 * 
 * @author 刘泉
 * @date 2017年2月17日 上午11:36:59
 */
public class ThreadLocalGCTest {

	static volatile ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			System.out.println(this.toString() + "is GC!");
		}
	};

	static volatile CountDownLatch cd = new CountDownLatch(10);

	public static class ParseDateTask implements Runnable {

		int i = 0;

		public ParseDateTask(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			try {
				if (tl.get() == null) {
					tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
						@Override
						protected void finalize() throws Throwable {
							super.finalize();
							System.out.println(this.toString() + "is GC!");
						}
					});
				}
				System.out.println(Thread.currentThread().getId() + ": create SimpleDateFormatter");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cd.countDown();
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			es.execute(new ParseDateTask(i));
		}
		cd.await();
		System.out.println("所有任务执行完成");
		tl = null;
		System.gc();
		System.out.println("第一次GC完成\n");
		// 设置ThreadLocal时，会清除ThreadLocalMap中的无效对象
		tl = new ThreadLocal<SimpleDateFormat>();
		cd = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			es.execute(new ParseDateTask(i));
		}
		cd.await();
		Thread.sleep(1000);
		System.gc();
		System.out.println("第二次GC完成\n");
	}

}
