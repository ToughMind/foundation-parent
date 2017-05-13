package lq.record.concurrency.thread;

/**
 * 改进后的线程挂起测试，结合wait()和notify()，
 * 
 * @author 刘泉
 * @date 2017年1月6日 下午4:09:47
 */
public class SuspendTest2 {
	public static Object u = new Object();

	public static void main(String[] args) throws InterruptedException {
		ChangeObjectThread t1 = new ChangeObjectThread();
		ReadObjectThread t2 = new ReadObjectThread();
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t1.suspendMe();
		System.out.println("suspend t1 2 seconds");
		Thread.sleep(5000);
		System.out.println("resume t1");
		t1.resume();
	}

	public static class ChangeObjectThread extends Thread {
		volatile boolean suspendMe = false;

		public void suspendMe() {
			suspendMe = true;
		}

		public void resumeMe() {
			suspendMe = false;
			synchronized (this) {
				notify();
			}
		}

		@Override
		public void run() {
			while (true) {
				synchronized (this) {
					while (suspendMe) {
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				synchronized (u) {
					System.out.println("in ChangeObjectThread");
				}
				Thread.yield();
			}
		}
	}

	public static class ReadObjectThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronized (u) {
					System.out.println("in ReadObjectThread");
				}
				Thread.yield();
			}
		}
	}

}
