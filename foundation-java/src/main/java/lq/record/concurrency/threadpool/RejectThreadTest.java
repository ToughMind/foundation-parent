package lq.record.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试自定义拒绝策略和自定义线程池创建。
 * 
 * @author 刘泉
 * @date 2017年2月13日 上午10:29:46
 */
public class RejectThreadTest {

	public static void main(String[] args) {
		MyTask myTask = new MyTask();
		// 拒绝策略
		ExecutorService es = new ThreadPoolExecutor(5, 8, 0L, TimeUnit.MICROSECONDS,
				new LinkedBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.err.println(r.toString() + "is diacard");
					}
				});
		// 8+10=18，线程最大数量是18，超过了就要采用拒绝策略咯。
		for (int i = 0; i < 19; i++) {
			es.submit(myTask);
		}

		// 创建策略
		ExecutorService es2 = new ThreadPoolExecutor(5, 8, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
				new ThreadFactory() {

					@Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						t.setDaemon(true);
						System.out.println("create " + t);
						return t;
					}
				});
		// 8+0（SynchronousQueue是没有容量的）=8，最大线程数量是8
		for (int i = 0; i < 8; i++) {
			es2.submit(myTask);
		}

	}
}
