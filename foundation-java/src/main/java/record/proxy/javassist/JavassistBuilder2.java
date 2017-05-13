package record.proxy.javassist;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtField;
import org.apache.ibatis.javassist.CtNewConstructor;
import org.apache.ibatis.javassist.CtNewMethod;

import record.proxy.DBQuery;
import record.proxy.IDBQuery;

/**
 * 没有写handler处理器类，直接利用动态java代码生成字节码。
 * 
 * @author 刘泉
 * @date 2016年12月8日 下午3:06:21
 */
public class JavassistBuilder2 {

	public static IDBQuery createProxy() throws Exception {
		ClassPool mPool = new ClassPool(true);
		// 定义类名
		CtClass mCtc = mPool.makeClass(IDBQuery.class.getName() + "Javaassist-BytecodeProxy");
		// 需要实现的接口
		mCtc.addInterface(mPool.get(IDBQuery.class.getName()));
		// 添加构造函数
		mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
		// 添加类的字段信息
		mCtc.addField(CtField.make("public " + IDBQuery.class.getName() + " real;", mCtc));
		String dbName = DBQuery.class.getName();
		// 添加方法，使用动态java代码指定内部逻辑
		mCtc.addMethod(CtNewMethod.make(
				"public String request() { if(real == null) real = new " + dbName + "(); return real.request();}",
				mCtc));
		// 基于上面信息，生成动态类
		Class pc = mCtc.toClass();
		IDBQuery proxy = (IDBQuery) pc.newInstance();
		return proxy;
	}

}
