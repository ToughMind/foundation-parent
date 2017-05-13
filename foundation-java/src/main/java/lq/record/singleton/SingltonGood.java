package lq.record.singleton;

/**
 * 较为完善的单例类，但利用反射机制还是会实例化多个类
 * <p>
 * 2017年05月04日 11:20
 * 1、getInstance()没有锁，高并发环境下性能优越。
 * 2、只有在getInstance()第一次被调用时，才创建实例。
 * </p>
 * @author 刘泉
 * @date 2016年12月7日 下午8:44:44
 */
public class SingltonGood {

	private SingltonGood() {
		System.out.println("创建单例类");
	}


	private static class SingletonHolder {
		private static SingltonGood instance = new SingltonGood();
	}

	public static synchronized SingltonGood getInstance() {
		return SingletonHolder.instance;
	}
}
