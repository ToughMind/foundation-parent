package lq.record.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lq.record.proxy.DBQuery;
import lq.record.proxy.IDBQuery;

/**
 * 实现代理方法的内部逻辑。
 * 
 * @author 刘泉
 * @date 2016年12月8日 上午11:23:56
 */
public class JdkHandler implements InvocationHandler {
	
	IDBQuery real = null;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// System.out.println("代理对象处理方法：proxy=" + JSON.toJSONString(proxy) + ",
		// method=" + JSON.toJSONString(method)
		//		+ ", args=" + JSON.toJSONString(args));
		if (real == null) {
			real = new DBQuery();
		}
		return real.request();
	}
}
