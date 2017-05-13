package record.concurrency.jdk;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的好搭档。 使用condition.await()和signal()前，要先获得重入锁，之后也记得要释放锁。
 * 
 * @author 刘泉
 * @date 2017年1月10日 上午11:20:06
 */
public class ConditionTest implements Runnable {

	public static ReentrantLock lock = new ReentrantLock();

	public static Condition condition = lock.newCondition();

	@Override
	public void run() {
		try {
			lock.lock();
			condition.await();
			System.out.println("Thread is going on");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ConditionTest t = new ConditionTest();
		Thread t1 = new Thread(t);
		t1.start();
		Thread.sleep(2000);
		/**·
		 * 通知线程t1继续执行。
		 */
		lock.lock();
		condition.signal();
		lock.unlock();
	}

}
