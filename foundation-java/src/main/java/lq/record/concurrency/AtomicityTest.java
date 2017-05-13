package lq.record.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试多线程的原子性。int型是原子性。long在32位系统中不是原子性（long有64位）。long在64位jvm中还是可以保证原子性的。
 * <P>
 * 改变Thread的sleep时间，可以看出不同的结果。而且&&操作也会有延迟，不能保证原子性。另外就是读写串位问题。
 * 
 * @author 刘泉
 * @date 2016年12月8日 下午8:11:53
 */
public class AtomicityTest {

	public static int _int = 0;

	public static long _long = 0L;

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					_int = -111;
					_long = -111L;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		pool.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					_int = 000;
					_long = 000L;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		pool.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					_int = 111;
					_long = 111L;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		pool.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (_int != 0 && _int != 111 && _int != -111) {
						System.out.println("int: " + _int);
					}
					if (_long != 0L && _long != 111L && _long != -111L) {
						System.out.println("long  " + _long);
					}

				}
			}
		});
	}

}
