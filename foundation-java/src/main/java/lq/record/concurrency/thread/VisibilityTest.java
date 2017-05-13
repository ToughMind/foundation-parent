package lq.record.concurrency.thread;

/**
 * 可见性的典型案例。按理虚拟机在Client模式下，由于JIT没有足够优化，在主线程修改ready值后，运行线程可以发现改动。但在Server模式下就看不到。
 * <P>
 * java虚拟机参数 -server 可切换到server模式。 volatile可以完美解决。
 * 
 * @author 刘泉
 * @date 2017年1月6日 下午8:28:16
 */
public class VisibilityTest {
	private static boolean ready;

	private static int number;

	private static class ReadThread extends Thread {
		public void run() {
			while (!ready)
				;
			System.out.println(number);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new ReadThread().start();
		Thread.sleep(100);
		number = 42;
		ready = true;
		Thread.sleep(10000);
	}
}
