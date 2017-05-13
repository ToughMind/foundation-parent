package record.concurrency.thread;

/**
 * 线程组的测试。
 * 
 * @author 刘泉
 * @date 2017年1月6日 下午9:08:44
 */
public class ThreadGroupTest extends Thread {
	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("线程组");
		Thread t1 = new Thread(tg, new ThreadGroupTest(), "线程1");
		Thread t2 = new Thread(tg, new ThreadGroupTest(), "线程2");
		t1.start();
		t2.start();
		System.out.println(tg.activeCount());
		tg.list();
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
		while (true) {
			System.out.println("name = " + name);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
