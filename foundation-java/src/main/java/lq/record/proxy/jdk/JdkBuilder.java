package lq.record.proxy.jdk;

import java.lang.reflect.Proxy;

import lq.record.proxy.IDBQuery;

public class JdkBuilder {

	public static IDBQuery createProxy() throws Exception {
		IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { IDBQuery.class }, new JdkHandler());
		return jdkProxy;
	}

}
