package lq.pre20170513.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MutiTcpServer {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		Socket s = null;
		while((s=ss.accept()) != null){
			new MyThread(s).start();
		}
		ss.close();
	}
}

class MyThread extends Thread{
	private Socket socket;
	public MyThread(Socket socket){
		super();
		this.socket = socket;
	}
	
	public void run(){
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.print("now time = "+new Date());
			pw.flush();
			pw.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
