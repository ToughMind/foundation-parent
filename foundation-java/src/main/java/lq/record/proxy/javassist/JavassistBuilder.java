package lq.record.proxy.javassist;

import org.apache.ibatis.javassist.util.proxy.ProxyFactory;
import org.apache.ibatis.javassist.util.proxy.ProxyObject;
import lq.record.proxy.IDBQuery;

public class JavassistBuilder {

	public static IDBQuery createProxy() throws Exception {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(new Class[] { IDBQuery.class });
		Class proxyClass = proxyFactory.createClass();
		IDBQuery proxy = (IDBQuery) proxyClass.newInstance();
		((ProxyObject) proxy).setHandler(new JavassistHandler());
		return proxy;
	}

}
