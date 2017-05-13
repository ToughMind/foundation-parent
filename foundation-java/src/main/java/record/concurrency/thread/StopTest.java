package record.concurrency.thread;

/**
 * 测试线程终止。Thread.stop过于暴力，会造成数据混乱。
 * 
 * @author 刘泉
 * @date 2016年12月26日 下午4:22:33
 */
public class StopTest {

	public static User user = new User();

	public static class User {
		private int id = 0;
		private String name = "0";

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "id=" + id + ", name=" + name;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new ReadObject().start();
		while (true) {
			// Thread t = new ChangeObject();
			// t.start();
			// t.sleep(3000);
			// t.stop();

			ChangeObject2 t2 = new ChangeObject2();
			t2.start();
			t2.sleep(3000);
			t2.stopMe();
		}
	}

	/**
	 * 使用Thread.stop()中断线程。
	 * 
	 * @author 刘泉
	 * @date 2017年1月5日 下午3:44:44
	 */
	public static class ChangeObject extends Thread {
		public void run() {
			while (true) {
				synchronized (user) {
					int v = user.getId() + 1;
					user.setId(v);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					user.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}

	/**
	 * 改进后的中断线程，使用标识符停止线程继续进行。 这种做法已经很类似与Thread.interrupt()方法判定了。
	 * 
	 * @author 刘泉
	 * @date 2017年1月5日 下午3:44:44
	 */
	public static class ChangeObject2 extends Thread {

		/**
		 * 不加volatile也没有问题。
		 */
		private volatile boolean stopMe = false;

		public void stopMe() {
			this.stopMe = true;
		}

		public void run() {
			while (true) {
				// 放在这里的作用就相当于Thread.stop()
				if (stopMe) {
					System.out.println("exit thread by flag stopMe");
					break;
				}
				synchronized (user) {
					int v = user.getId() + 1;
					user.setId(v);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					user.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}

	public static class ReadObject extends Thread {
		public void run() {
			while (true) {
				synchronized (user) {
					if (user.getId() != Integer.parseInt(user.getName())) {
						System.out.println(user.toString());
					}
				}
				Thread.yield();
			}
		}
	}

}
