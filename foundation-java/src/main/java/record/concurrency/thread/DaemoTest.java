package record.concurrency.thread;

/**
 * 守护线程测试。若与之对应的用户线程结束，则守护线程也会推出jvm。
 * 
 * @author 刘泉
 * @date 2017年1月7日 上午10:47:42
 */
public class DaemoTest {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new DaemoT("线程1");
		Thread t2 = new DaemoT("线程2");
		t1.setDaemon(true);
		t1.start();
		// t2.start();
		Thread.sleep(2000);
		// main线程结束后，若只有守护线程t1，则会退出jvm，终止循环。若还有t2在，则两者都不会退出。
	}

	public static class DaemoT extends Thread {

		public DaemoT(String name) {
			super.setName(name);
		}

		@Override
		public void run() {
			while (true) {
				System.out.println(Thread.currentThread().getName() + "i am alive");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
