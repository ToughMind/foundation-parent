package lq.lq.design_pattern.singleton;

/**
 * @description 饿汉模式
 * 
 * @author liuquan
 * @date 2015年12月17日
 */
public class Singleton {

	private static Singleton instance = new Singleton();

	// 私有构造方法，防止被实例化
	private Singleton() {
	}

	// 静态工程方法，创建实例
	public static Singleton getInstance() {
		return instance;
	}

	

}