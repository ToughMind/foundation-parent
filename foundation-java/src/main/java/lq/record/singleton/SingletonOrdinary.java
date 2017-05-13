package lq.record.singleton;

/**
 * 最简单的单例模式。
 * 
 * @author 刘泉
 * @date 2016年12月7日 下午8:20:51
 */
public class SingletonOrdinary {

	private static SingletonOrdinary instance = new SingletonOrdinary();

	private SingletonOrdinary() {
		System.out.println("创建普通单例类");
	}

	public static SingletonOrdinary getInstance() {
		return instance;
	}

	public static void createString() {
		System.out.println("单例类中创建字符串方法");
	}

}
