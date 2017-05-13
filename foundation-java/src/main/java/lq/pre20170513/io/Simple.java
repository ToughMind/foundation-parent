package lq.pre20170513.io;

import java.io.*;

public class Simple {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		String str = "中国人good";
		// 字节流形式
		FileOutputStream fos = new FileOutputStream("1.txt");
		fos.write(str.getBytes("UTF-8"));
		fos.close();
		FileInputStream fis = new FileInputStream("1.txt");
		byte[] buff = new byte[1024];
		fis.read(buff);
		System.out.println(new String(buff, 0, buff.length, "UTF-8"));
		
		//字符流形式
		FileWriter fw = new FileWriter("2.txt");
		fw.write(str);
		fw.close();
		FileReader fr = new FileReader("2.txt");
		char[] buf = new char[1024];
		fr.read(buf);
		System.out.println(new String(buf, 0, buf.length));
		
		//打印流
		PrintWriter pw = new PrintWriter("1.txt", "utf-8");
		pw.write(str);
		pw.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("1.txt"), "utf-8"));
		System.out.println(br.readLine());
		br.close();
		
		
	}
}
