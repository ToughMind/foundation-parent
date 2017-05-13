package lq.lq.design_pattern.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description 计算薪资和税率，并统计时间的代理类 
 * 
 * @author liuquan
 * @date  2016年1月14日
 */
public class TimeProxy implements InvocationHandler{
	private Object obj; 
    //绑定代理对象
    public Object bind(Object obj) {
        this.obj = obj; 
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
 
    /**
     * @description  每一个代理实例都必须指定一个调用处理器，代理对象调用方法时，该方法会指派到调用处理器的invoke()中去
     * @date  2016年1月14日
     */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		try { 
			long startTime = System.nanoTime(); // 获取开始时间
			result = method.invoke(obj, args);
			long endTime = System.nanoTime(); // 获取结束时间
			System.out.println("计算程序运行时间： " + (endTime - startTime) + "ns");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
    

}
