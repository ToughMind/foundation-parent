package lq.record.concurrency.thread;

/**
 * 线程等待结束和谦让。
 * 
 * @author 刘泉
 * @date 2017年1月6日 下午4:55:09
 */
public class JoinYieldTest {

	public volatile static int i = 0;

	public volatile static int j = 0;

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (i = 0; i < 10; i++) {
					System.out.println("t1: " + i);
				}
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (j = 0; j < 10; j++) {
					System.out.println("t2: " + j);
				}
			}
		};

		t1.start();
		t1.join();
		System.out.println("main t1: " + i);
		// Thread.yield();
		t2.start();
		System.out.println("main t2: " + j);
	}

}
