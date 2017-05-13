package lq.pre20170513.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TcpServer {
	public static void main(String[] args) throws IOException {
		//创建一个服务器的Socket，指定一个端口
		ServerSocket ss = new ServerSocket(8888);
		//监听来自客户端的请求要求
		Socket s = ss.accept();
		//获得输出流，调用read和write
		OutputStream os = s.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.print("now time = " + new Date());
		pw.flush();
		pw.close();
		s.close();
		ss.close();
	}
}
