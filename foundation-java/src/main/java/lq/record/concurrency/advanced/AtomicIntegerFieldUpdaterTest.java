package lq.record.concurrency.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 让普通变量也可以享受原子操作。
 * <P>
 * 比如选民投票。
 * 
 * @author 刘泉
 * @date 2017年2月20日 上午10:33:45
 */
public class AtomicIntegerFieldUpdaterTest {

	static int THREAD_COUNT = 1000;

	public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater
			.newUpdater(Candidate.class, "score");

	// 参照的检查项标准
	public static AtomicInteger allScore = new AtomicInteger(0);

	// 测试类
	public static class Candidate {
		int id;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		volatile int score;
	}

	public static void main(String[] args) throws InterruptedException {
		Candidate cd = new Candidate();
		ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);
		for (int i = 0; i < THREAD_COUNT; i++) {
			es.execute(new Runnable() {

				@Override
				public void run() {
					if (Math.random() > 0.4) {
						scoreUpdater.incrementAndGet(cd);
						allScore.incrementAndGet();
					}
				}
			});
		}
		es.shutdown();
		es.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("scoreUpdater=" + cd.score);
		System.out.println("allScore=" + allScore);
	}

}
