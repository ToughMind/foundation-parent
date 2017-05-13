package lq.record.proxy.cglib;

import lq.record.proxy.IDBQuery;
import org.springframework.cglib.proxy.Enhancer;

public class CglibBuilder {

	public static IDBQuery createProxy() {
		Enhancer enhancer = new Enhancer();
		// 指定切入器，定义代理逻辑类
		enhancer.setCallback(new CglibInterceptor());
		// 指定实现的接口
		enhancer.setInterfaces(new Class[] { IDBQuery.class });
		IDBQuery proxy = (IDBQuery) enhancer.create();
		return proxy;
	}
}
