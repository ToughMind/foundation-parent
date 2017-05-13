package record.concurrency.advanced;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lq.java.domain.vo.UserVO;

/**
 * 简单的使用ThreadLocal的示例。
 * <P>
 * 三种方式实现在，证明共享资源要跟线程绑定。注意把共享变量声明在Task2中也会有并发线程争夺资源的情况，实际上也会产生并发问题。
 * 
 * @author 刘泉
 * @date 2017年2月16日 上午11:15:26
 */
public class ThreadLocalTest {

	// 此对象作为共享资源
	private static SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 改进，将SimpleDateFormat存到ThreadLocal中
	private static ThreadLocal<SimpleDateFormat> t = new ThreadLocal<SimpleDateFormat>();

	public static class MyTask1 implements Runnable {

		@Override
		public void run() {
			int i = new Random().nextInt(60);
			try {
				Date d = s.parse("2017-02-16 11:19:" + i % 60);
				System.out.println(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public static class MyTask2 implements Runnable {

		@Override
		public void run() {
			int i = new Random().nextInt(60);
			try {
				if (t.get() == null) {
					t.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
				}
				Date d = t.get().parse("2017-02-16 11:19:" + i % 60);
				System.out.println(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public static class MyTask3 implements Runnable {

		private SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		@Override
		public void run() {
			int i = new Random().nextInt(60);
			try {
				Date d = s.parse("2017-02-16 11:19:" + i % 60);
				System.out.println(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 1000; i++) {
			// es.execute(new MyTask1());
			// es.execute(new MyTask2());
			// es.execute(new MyTask3());
		}
		MyTask1 t1 = new MyTask1();
		MyTask2 t2 = new MyTask2();
		MyTask3 t3 = new MyTask3();
		for (int i = 0; i < 1000; i++) {
			// es.execute(t1);
			// es.execute(t2);
			// es.execute(t3);
		}

	}

}
