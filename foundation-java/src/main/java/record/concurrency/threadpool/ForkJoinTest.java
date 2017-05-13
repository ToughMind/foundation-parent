package record.concurrency.threadpool;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * fork/josin框架，分而治之理论。
 * 
 * @author 刘泉
 * @date 2017年2月15日 上午11:32:04
 */
public class ForkJoinTest {
	private static class CountTask extends RecursiveTask<Long> {
		private static final int THRESHOLD = 1000;

		private long start;

		private long end;

		public CountTask(long start, long end) {
			this.start = start;
			this.end = end;
		}

		public Long compute() {
			long sum = 0;
			boolean canCompute = (end - start) < THRESHOLD;
			if (canCompute) {
				for (long i = start; i <= end; i++) {
					sum += i;
				}
			} else {
				// 分成100个小任务
				long step = (start + end) / 100;
				ArrayList<CountTask> list = new ArrayList<>();
				long pos = start;
				for (int i = 0; i < 100; i++) {
					long lastOne = pos + step;
					if (lastOne > end)
						lastOne = end;
					CountTask subTask = new CountTask(pos, lastOne);
					pos += step + 1;
					list.add(subTask);
					subTask.fork();
				}
				for (CountTask t : list) {
					sum += t.join();
				}
			}
			return sum;
		}
	}

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		long t1 = System.currentTimeMillis();
		// 量太大会造成线程过多性能下降，再是栈溢出
		long MAX = 50000L;
		CountTask task = new CountTask(0, MAX);
		ForkJoinTask<Long> result = pool.submit(task);
		try {
			long res = result.get();
			System.out.println("fork/join框架：sum=" + res + "，耗时" + (System.currentTimeMillis() - t1) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		long sum = 0;
		t1 = System.currentTimeMillis();
		for (int i = 0; i <= MAX; i++) {
			sum += i;
		}
		System.out.println("直接计算：sum=" + sum + "，耗时" + (System.currentTimeMillis() - t1) + "ms");
	}

}
