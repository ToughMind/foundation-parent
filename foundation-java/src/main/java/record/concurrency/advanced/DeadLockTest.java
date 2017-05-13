package record.concurrency.advanced;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 死锁的测试，经典的哲学家问题。多个哲学家围成一圈，左右各有一只叉子，需要凑齐左右两个叉子才可就餐。
 * 
 * @author 刘泉
 * @date 2017年2月20日 下午2:35:19
 */
public class DeadLockTest {

	static class DeadLock extends Thread {
		int flag;
		static Object fork1 = new Object();
		static Object fork2 = new Object();

		public DeadLock(int flag) {
			this.flag = flag;
			if (flag == 1) {
				this.setName("哲学家1");
			}
			if (flag == 2) {
				this.setName("哲学家2");
			}
		}

		@Override
		public void run() {
			if (flag == 1) {
				synchronized (fork1) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (fork2) {
						System.out.println("哲学家1开始吃饭了");
					}
				}
			}
			if (flag == 2) {
				synchronized (fork2) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (fork1) {
						System.out.println("哲学家2开始吃饭了");
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		// DeadLock 哲学家1 = new DeadLock(1);
		// DeadLock 哲学家2 = new DeadLock(2);
		// 哲学家1.start();
		// 哲学家2.start();
		//int[] a = new int[] {2,3,4,5,6};
		//System.out.println(a);
		System.out.println(Long.MAX_VALUE);
	}

}
