package lq.record.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {

	public static void main(String[] args) {
		MyTask myTask = new MyTask();
		ExecutorService es = Executors.newFixedThreadPool(5);
		ExecutorService es2 = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			// new Thread(myTask).start();
			// es.submit(myTask);
			es2.submit(myTask);
		}

	}
}
