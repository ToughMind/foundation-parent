package record.concurrency.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 测试线程挂起，suspend不会释放锁。结合LockSupport.part()方法，part()方法可以中断线程，但是不会抛出异常。
 * 
 * @author 刘泉
 * @date 2017年1月6日 下午3:42:05
 */
public class SuspendTest {

	public static Object u = new Object();

	static ChangeObjectThread t1 = new ChangeObjectThread("t1");

	static ChangeObjectThread t2 = new ChangeObjectThread("t2");

	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		// t1.resume();
		// t2.resume();
		/**
		 * 若这里用LockSupport.park代替，就不会造成线程一直挂起状态。
		 */
		LockSupport.unpark(t1);
		t1.interrupt();
		LockSupport.unpark(t2);
		t1.join();
		t2.join();
	}

	public static class ChangeObjectThread extends Thread {
		public ChangeObjectThread(String name) {
			super.setName(name);
		}

		@Override
		public void run() {
			synchronized (u) {
				System.out.println("in  " + getName());
				// Thread.currentThread().suspend();
				LockSupport.park();
				if (Thread.interrupted()) {
					System.out.println(getName() + "被中断了");
				}
				System.out.println(getName() + "执行结束");
			}
		}
	}
}
