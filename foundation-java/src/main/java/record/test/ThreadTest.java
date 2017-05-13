package record.test;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lq.java.domain.vo.UserVO;

/**
 * 多线程同时请求一个循环执行的线程的方法。
 * 
 * @author 刘泉
 * @date 2017年2月16日 下午5:18:06
 */
public class ThreadTest {

	private UserVO user = null;

	private class ReloadTask implements Runnable {

		@Override
		public void run() {
			while (true) {
				user = reload();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private UserVO reload() {
		UserVO user = new UserVO();
		user.setUpdateTime(new Date());
		user.setName("good");
		user.setId(user.getId() + 1);
		return user;
	}

	private UserVO getUser() {
		if (user == null) {
			System.out.println(System.currentTimeMillis() + ": 此时共享资源对象user=null");
			reload();
		}
		return user;
	}

	public static void main(String[] args) {
		ThreadTest test = new ThreadTest();
		new Thread(test.new ReloadTask()).start();
		ScheduledExecutorService es = Executors.newScheduledThreadPool(100);
		for (int i = 0; i < 1000000; i++) {
			es.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					test.getUser();
					if (test.getUser().getId() != test.user.getId()) {
						System.out.println("error");
					}
				}
			}, 0, new Random().nextInt(500) + 1, TimeUnit.MILLISECONDS);
		}
	}

}
