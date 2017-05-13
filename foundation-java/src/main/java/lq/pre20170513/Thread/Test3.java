package lq.pre20170513.Thread;

/**
 * 子线程循环 10 次，接着主线程循环 10，接着又回到子线程循环 10 次，接着再回到主线程又循环 10，如此循环 50 次
 */
public class Test3 {

	public static void main(String[] args) {
		Business b = new Test3().new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					b.subThread(i);
				}
			}
		}).start();

		for (int i = 0; i < 50; i++) {
			b.mainThread(i);
		}
	}

	private class Business {
		boolean isSub = true;

		public synchronized void mainThread(int i) {
			if (isSub) { // 如果此时应该是子线程执行，则释放锁
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int j = 0; j < 10; j++) {
				System.out.println(Thread.currentThread().getName() + ": i=" + i + ", j=" + j);
			}
			isSub = true;
			this.notify();
		}

		public synchronized void subThread(int i) {
			if (!isSub) { // 如果此时不是子线程执行
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			for (int j = 0; j < 10; j++) {
				System.out.println(Thread.currentThread().getName() + ": i=" + i + ", j=" + j);
			}
			isSub = false;
			this.notify();
		}

	}
}
