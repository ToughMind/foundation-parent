package lq.record.concurrency.threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

	public static volatile int count = 0;

	public static void main(String[] args) {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		ses.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				try {
					count++;
					Thread.sleep(1000);
					System.out.println(new Date() + " :" + count);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, 0, 2, TimeUnit.SECONDS);

		ses.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				try {
					count++;
					Thread.sleep(3000);
					System.err.println(new Date() + " :" + count);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (count > 6) {
					System.err.println("哈哈！异常啦！");
					int i = 1 / 0;
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
	}

}
