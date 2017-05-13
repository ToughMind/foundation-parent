package lq.record.concurrency.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

import com.alibaba.fastjson.JSON;

/**
 * 测试各种类型的原子性。
 * 
 * @author 刘泉
 * @date 2017年2月18日 下午4:21:01
 */
public class AtomicTest {

	static int THREAD_COUNT = 10;
	static int CYCLE_COUNT = 10000;
	static int ARRAY_COUNT = 10;

	static int i = 0;
	static long[] ls = new long[ARRAY_COUNT];
	static AtomicInteger ai = new AtomicInteger();
	static AtomicLongArray als = new AtomicLongArray(ARRAY_COUNT);
	static Vector<Integer> vector = new Vector<>();
	// 多线程调用非线程安全的类，执行时会有异常发生
	static List<Integer> arrayList = Collections.synchronizedList(new ArrayList<Integer>());

	public static class AddTask implements Runnable {

		@Override
		public void run() {
			for (int count = 0; count < CYCLE_COUNT; count++) {
				i++;
				ai.incrementAndGet();
				// 将al的第i个下标的元素加1
				als.incrementAndGet(count % als.length());
				ls[count % ls.length]++;
				arrayList.add(1);
				vector.add(1);
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);
		for (int i = 0; i < THREAD_COUNT; i++) {
			es.execute(new AddTask());
		}
		// 等待线程池中的所有线程完成任务
		es.shutdown();
		es.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
		System.out.println("若干次循环后，int=" + i);
		System.out.println("若干次循环后，AtomicInteger=" + ai);
		System.out.println("若干次循环后，long[]=" + JSON.toJSONString(ls));
		System.out.println("若干次循环后，AtomicLongArray=" + als);
		System.out.println("若干次循环后，arraList=" + arrayList.size());
		System.out.println("若干次循环后，vector=" + vector.size());
	}

}
