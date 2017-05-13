package record.concurrency.advanced;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 测试特殊情况，该线程的对象被其他线程修改多次又还原到了旧值后。
 * <p>
 * 账户余额小于20可免费充值1次，但只能一次。使用AtomicReference不能解决这个问题，但带时间戳的AtomicStampReference可以解决这个问题。
 * 
 * @author 刘泉
 * @date 2017年2月18日 下午3:40:57
 */
public class AtomicReferenceTest {

	static AtomicReference<Integer> money = new AtomicReference<Integer>(19);

	static AtomicStampedReference<Integer> money2 = new AtomicStampedReference<Integer>(19, 0);

	public static void main(String[] args) {
		// AtomicTest();
		new Thread() {

			@Override
			public void run() {
				int time = money2.getStamp();
				while (true) {
					Integer m = money2.getReference();
					if (m < 20) {
						if (money2.compareAndSet(m, m + 20, time, time + 1)) {
							System.out.println("账户余额小于20，充值成功，余额=" + money2.getReference());
						}
					} else {
						// System.out.println("账户余额大于20，充值成功，余额="+money.get());
					}
				}
			}

		}.start();

		new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					int time = money2.getStamp();
					Integer m = money2.getReference();
					if (m > 10) {
						if (money2.compareAndSet(m, m - 10, time, time + 1)) {
							System.out.println("成功消费10元，余额=" + money2.getReference());
						}
					} else {
						System.out.println("没有足够余额");
					}
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
			}

		}.start();
	}

	private static void AtomicTest() {
		new Thread() {

			@Override
			public void run() {
				while (true) {
					Integer m = money.get();
					if (m < 20) {
						if (money.compareAndSet(m, m + 20)) {
							System.out.println("账户余额小于20，充值成功，余额=" + money.get());
						}
					} else {
						// System.out.println("账户余额大于20，充值成功，余额="+money.get());
					}
				}
			}

		}.start();

		new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					Integer m = money.get();
					if (m > 10) {
						if (money.compareAndSet(m, m - 10)) {
							System.out.println("成功消费10元，余额=" + money.get());
						}
					} else {
						System.out.println("没有足够余额");
					}
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
			}

		}.start();
	}

}
