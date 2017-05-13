package record.concurrency.thread;

/**
 * 测试线程中断。
 * 
 * @author 刘泉
 * @date 2016年12月26日 下午4:16:59
 */
public class InterruptTest {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while (true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("interrupted!");
						break;
					}
					try {
						Thread.sleep(4000);
						System.out.println("what！");
					} catch (Exception e) {
						System.out.println("interrupted when sleep!");
						// 设置中断状态。sleep由于中断而抛出异常，此时会清除中断标记。
						Thread.currentThread().interrupt();
					}
					Thread.yield();
				}
			}
		};
		t1.start();
		Thread.sleep(2000);
		t1.interrupt();
	}
}
