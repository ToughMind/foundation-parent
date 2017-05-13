package lq.record.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SingletonSerializable implements Serializable {

	private static final long serialVersionUID = 1L;

	private static SingletonSerializable instance = new SingletonSerializable();

	private SingletonSerializable() {
		System.out.println("创建串行化单例类");
	}

	public static synchronized SingletonSerializable getInstance() {
		return instance;
	}

	/**
	 * 此方法是关键，没有此方法，结果则是false。此方法阻止生成新的实例，总是返回当前的对象。
	 */
	private Object readResolve() {
		return instance;
	}

	public static void main(String[] args) throws Exception {
		SingletonSerializable s1 = null;
		SingletonSerializable s2 = SingletonSerializable.getInstance();
		FileOutputStream fos = new FileOutputStream("ser.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s2);
		oos.flush();
		oos.close();
		FileInputStream fis = new FileInputStream("ser.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		s1 = (SingletonSerializable) ois.readObject();
		System.out.println(s1 == s2);
	}
}
