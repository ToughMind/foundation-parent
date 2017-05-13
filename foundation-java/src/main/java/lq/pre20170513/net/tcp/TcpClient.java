package lq.pre20170513.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//创建Socket对象
		Socket s = new Socket("localhost", 8888);
		//获得输入流，调用read和write
		InputStream is = s.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = br.readLine();
		System.out.println(str);
		
	}
}
