
package lq.record.proxy;

import lq.record.proxy.javassist.JavassistBuilder;
import lq.record.proxy.javassist.JavassistBuilder2;
import lq.record.proxy.jdk.JdkBuilder;
import lq.record.proxy.cglib.CglibBuilder;

public class ProxyTest {

	public static final int CIRCLE = 100000000;

	public static void main(String[] args) throws Exception {
		IDBQuery d = null;
		long begin = System.currentTimeMillis();
		d = JdkBuilder.createProxy();
		System.out.println(
				"create jdk proxy=" + d.getClass().getName() + ", time=" + (System.currentTimeMillis() - begin));
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("call jdk proxy time=" + (System.currentTimeMillis() - begin));

		begin = System.currentTimeMillis();
		d = CglibBuilder.createProxy();
		System.out.println(
				"create cglib proxy=" + d.getClass().getName() + ", time=" + (System.currentTimeMillis() - begin));
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("call cglib proxy time=" + (System.currentTimeMillis() - begin));

		begin = System.currentTimeMillis();
		d = JavassistBuilder.createProxy();
		System.out.println(
				"create javassist proxy=" + d.getClass().getName() + ", time=" + (System.currentTimeMillis() - begin));
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("call javassist proxy time=" + (System.currentTimeMillis() - begin));

		begin = System.currentTimeMillis();
		d = JavassistBuilder2.createProxy();
		System.out.println(
				"create javassist2 proxy=" + d.getClass().getName() + ", time=" + (System.currentTimeMillis() - begin));
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("call javassist2 proxy time=" + (System.currentTimeMillis() - begin));

	}
}
