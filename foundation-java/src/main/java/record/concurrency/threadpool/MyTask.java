package record.concurrency.threadpool;

import java.util.Date;

/**
 * 公用的测试任务类。
 * 
 * @author 刘泉
 * @date 2017年2月14日 上午11:07:26
 */
public class MyTask implements Runnable {

	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
	}

	@Override
	public void run() {
		System.out.println(new Date() + ": Thread Id=" + Thread.currentThread().getId());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
