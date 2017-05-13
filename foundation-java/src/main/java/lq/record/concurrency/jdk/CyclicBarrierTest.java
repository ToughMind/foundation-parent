package lq.record.concurrency.jdk;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 实现线程间的计数等待，并且是可循环的。
 * CyclicBarrier每一轮计数完毕后都会重新开始计数，并且可以指定每一轮完毕后的触发动作。这是很CountDownLatch的最大不同之处。
 * 
 * @author 刘泉
 * @date 2017年1月10日 下午4:29:35
 */
public class CyclicBarrierTest {

	static final int THREAD_COUNT = 30;

	static final int GROUP_COUNT = 10;

	static int count = 0;

	public static class Soldier implements Runnable {

		private String soldier;

		private final CyclicBarrier cyclic1;

		private final CyclicBarrier cyclic2;

		Soldier(String soldier, CyclicBarrier cyclic1, CyclicBarrier cyclic2) {
			this.cyclic1 = cyclic1;
			this.soldier = soldier;
			this.cyclic2 = cyclic2;
		}

		@Override
		public void run() {
			try {
				// 士兵首先要集合，每满10个一组就可以执行任务了
				cyclic1.await();
				Thread.sleep(Math.abs(new Random().nextInt() % 10000));
				System.out.println(soldier + "执行");
				cyclic2.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}

	}

	public static class BarrierRun implements Runnable {

		boolean flag;
		int N;

		public BarrierRun(boolean flag, int N) {
			this.flag = flag;
			this.N = N;
		}

		@Override
		public void run() {
			if (flag) {
				System.out.println("士兵：" + N + "个，任务完成");
			} else {
				System.out.println("士兵：" + N + "个，集合完成");
			}
		}
	}

	public static void main(String[] args) {
		CyclicBarrier cyclic1 = new CyclicBarrier(GROUP_COUNT, new BarrierRun(false, GROUP_COUNT));
		CyclicBarrier cyclic2 = new CyclicBarrier(GROUP_COUNT, new BarrierRun(true, GROUP_COUNT));
		for (int i = 0; i < THREAD_COUNT; i++) {
			Thread t = new Thread(new Soldier("士兵" + i, cyclic1, cyclic2));
			t.start();
			// 测试中断抛出BrokenBarrierException异常
			// if (i == 5) {
			// t.interrupt();
			// }

		}
	}

}
