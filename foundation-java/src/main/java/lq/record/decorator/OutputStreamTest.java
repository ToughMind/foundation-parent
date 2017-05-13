package lq.record.decorator;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * 测试OutputStream，典型的装饰者模式实现。
 * 
 * @author 刘泉
 * @date 2016年12月8日 下午4:53:15
 */
public class OutputStreamTest {
	public static final int CIRCLE = 1000000;

	public static void main(String[] args) throws Exception {
		// 含有缓冲流
		DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/a.txt")));
		// 没有缓冲
		DataOutputStream os2 = new DataOutputStream(new FileOutputStream("/a.txt"));
		long begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			os.writeInt(i);
		}
		System.out.println("缓冲流写time=" + (System.currentTimeMillis() - begin));
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			os2.writeInt(i);
		}
		System.out.println("无缓冲流写time=" + (System.currentTimeMillis() - begin));
		os.close();
		os2.close();
	}
}
