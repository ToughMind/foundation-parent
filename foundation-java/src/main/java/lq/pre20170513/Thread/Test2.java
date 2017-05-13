package lq.pre20170513.Thread;

/** 
 * 设计 4 个线程，其中两个线程每次对 j 增加 1，另外两个线程对 j 每次减少
 */
public class Test2 {
	private int j;	
	
	public static void main(String[] args) {
		//共享变量在这个主类中
		Test2 t = new Test2();
		for(int i = 0; i < 2; i++){
			new Thread(t.new Inc()).start();
			new Thread(t.new Dec()).start();
		}
		
		//共享变量在其他类中
		JManager j = new JManager();
		for(int i = 0; i < 2; i++){
			new Thread(new Runnable(){
				public void run(){j.accumulate();}
			}).start();
			new Thread(new Runnable(){
				public void run(){j.subtract();}
			}).start();
		}
	}
	
	private class Inc implements Runnable{
		@Override
		public void run() {  
			synchronized (Test2.class) {
				j++;
				System.out.println(Thread.currentThread().getName() + "增加：" + j);
			}			 
		}
	}
	
	private class Dec implements Runnable{
		@Override
		public void run() { 
			synchronized (Test2.class) {
				j--;
				System.out.println(Thread.currentThread().getName() + "减少：" + j);
			}		 
		}
	}

	//共享变量在这个类中
	static class JManager{
		private int j = 0;
		
		public synchronized void accumulate(){
			j++;
			System.out.println(Thread.currentThread().getName() + "增加：" + j);
		}
		
		public synchronized void subtract(){
			j--;
			System.out.println(Thread.currentThread().getName() + "减少：" + j);
		}
	}

}
