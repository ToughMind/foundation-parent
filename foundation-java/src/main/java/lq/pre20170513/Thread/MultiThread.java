package lq.pre20170513.Thread;

public class MultiThread {

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Thread1()).start();
		Thread.sleep(10);
		new Thread(new Thread2()).start();
	}

	private static class Thread1 implements Runnable {
		@Override
		public void run() {
			// 由于这里的 Thread1和下面的 Thread2内部 run 方法要用同一对象作为监视器，我们这里不能用 this，因为在
			// Thread2里面的 this 和这个 Thread1的 this 不是同一个对象。
			synchronized (MultiThread.class) {
				System.out.println("thread1 enter");
				System.out.println("thread1 waiting");
				// 释放锁有两种方式，第一种方式是程序自然离开监视器的范围，也就是离开了 synchronized
				// 关键字管辖的代码范围，另一种方式就是在 synchronized 关键字管辖的代码内部调用监视器对象的 wait 方法。
				try {
					MultiThread.class.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generatedcatch block
					e.printStackTrace();
				}
				System.out.println("thread1 going on");
				System.out.println("thread1 over");
			}
		}

	}
	
	private static class Thread2 implements Runnable {
		@Override
		public void run() { 
			synchronized (MultiThread.class) { 
				System.out.println("thread2 enter");
				System.out.println("thread2 notify other thread can release wait status");
				MultiThread.class.notify();
				System.out.println("thread2  sleeping ten second...");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}
				System.out.println("thread2 going on...");
				System.out.println("thread2 over!");
			}
		}

	}

}
