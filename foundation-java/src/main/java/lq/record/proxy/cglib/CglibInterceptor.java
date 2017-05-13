package lq.record.proxy.cglib;

import java.lang.reflect.Method;

import lq.record.proxy.DBQuery;
import lq.record.proxy.IDBQuery;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibInterceptor implements MethodInterceptor {

	IDBQuery real = null;

	@Override
	public Object intercept(Object paramObject, Method paramMethod, Object[] paramArrayOfObject,
			MethodProxy paramMethodProxy) throws Throwable {
		// System.out.println("代理对象处理方法：paramObject=" +
		// JSON.toJSONString(paramObject) + ", paramMethod=" +
		// JSON.toJSONString(paramMethod) + ", paramArrayOfObject=" +
		// JSON.toJSONString(paramArrayOfObject));
		if (real == null) {
			real = new DBQuery();
		}
		return real.request();
	}

}
