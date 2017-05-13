package record.proxy.javassist;

import java.lang.reflect.Method;

import org.apache.ibatis.javassist.util.proxy.MethodHandler;

import com.alibaba.fastjson.JSON;

import record.proxy.DBQuery;
import record.proxy.IDBQuery;

public class JavassistHandler implements MethodHandler {

	IDBQuery real = null;

	@Override
	public Object invoke(Object arg0, Method arg1, Method arg2, Object[] arg3) throws Throwable {
		// System.out.println("代理对象处理方法：arg0=" + JSON.toJSONString(arg0) + ",
		// arg1=" + JSON.toJSONString(arg1) + ", arg2="
		//		+ JSON.toJSONString(arg2) + " arg3=" + JSON.toJSONString(arg3));
		if (real == null) {
			real = new DBQuery();
		}
		return real.request();
	}

}
