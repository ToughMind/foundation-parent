package lq.record.singleton;

/**
 * 延迟加载的单例类。
 * 
 * @author 刘泉
 * @date 2016年12月7日 下午8:25:47
 */
public class SingletonLazy {

	private static SingletonLazy instance = null;

	private SingletonLazy() {
		System.out.println("创建延迟加载单例类");
	}

	public static synchronized SingletonLazy getInstance() {
		if (instance == null) {
			instance = new SingletonLazy();
		}
		return instance;
	}

}
