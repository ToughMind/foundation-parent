package lq.record.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展线程池测试，在线程启动前后执行操作。
 * 
 * @author 刘泉
 * @date 2017年2月14日 下午5:28:05
 */
public class ExtThreadPoolTest {

	private static class ExceptionTask implements Runnable {
		int a, b;

		public ExceptionTask(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			System.out.println(a / b);
		}

	}

	public static void main(String[] args) throws InterruptedException {

		ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

		ExecutorService myEs = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()) {

			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("准备执行：" + r.toString());
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("执行完成：" + r.toString());
			}

			@Override
			protected void terminated() {
				System.out.println("线程池退出");
			}

			@Override
			public void execute(Runnable task) {
				super.execute(wrap(task, new Exception("异常源")));
			}

			@Override
			public Future<?> submit(Runnable task) {
				return super.submit(wrap(task, new Exception("异常源")));
			}

			private Runnable wrap(Runnable task, Exception exception) {
				return new Runnable() {

					@Override
					public void run() {
						try {
							task.run();
						} catch (Exception e) {
							exception.printStackTrace();
							throw e;
						}

					}
				};
			}

		};

		for (int i = 0; i < 5; i++) {
			ExceptionTask task = new ExceptionTask(100, i);
			// 若使用submit不会打印异常
			// es.submit(task);
			// 异常只会打出抛出异常的地方，但无法知道上层的提交调用处
			es.execute(task);
		}
		// shutdown并不会暴力终止所有，会等待任务完成再关闭线程池。但之后线程池不能再接受其他新的任务了。
		es.shutdown();
		Thread.sleep(3000);

		for (int i = 0; i < 5; i++) {
			ExceptionTask task = new ExceptionTask(100, i);
			myEs.execute(task);
		}
		myEs.shutdown();
	}

}
