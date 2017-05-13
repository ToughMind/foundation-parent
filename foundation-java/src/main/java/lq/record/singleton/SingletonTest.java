package lq.record.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {
	public static void main(String[] args) {
		SingletonOrdinary.createString();
		ExecutorService pool = Executors.newCachedThreadPool();
		// 开启十个线程测试
		for (int i = 0; i < 5; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					for (int j = 0; j < 100000; j++) {
						SingletonOrdinary.getInstance();
					}
					System.out.println("一个线程执行10W次普通单例类的获取实例化对象，耗时：" + (System.currentTimeMillis() - start));
				}
			});
		}
		for (int i = 0; i < 5; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					for (int j = 0; j < 100000; j++) {
						SingletonLazy.getInstance();
					}
					System.out.println("一个线程执行10W次延迟单例类的获取实例化对象，耗时：" + (System.currentTimeMillis() - start));
				}
			});
		}

		for (int i = 0; i < 5; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					for (int j = 0; j < 100000; j++) {
						SingltonGood.getInstance();
					}
					System.out.println("一个线程执行10W次加强版单例类的获取实例化对象，耗时：" + (System.currentTimeMillis() - start));
				}
			});
		}

	}

}
