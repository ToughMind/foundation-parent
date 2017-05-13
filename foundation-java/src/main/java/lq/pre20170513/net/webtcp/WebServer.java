package lq.pre20170513.net.webtcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//web服务器端
public class WebServer {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		Socket s = null;
		while ((s = ss.accept()) != null) {
			new MyHttpThread(s).start();
		}
		ss.close();
	}
}

class MyHttpThread extends Thread {
	private Socket socket;

	public MyHttpThread(Socket socket) {
		super();
		this.socket = socket;
	}

	public void run() {

		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println("<html>");
			pw.println("<body>");
			pw.println("hello world");
			pw.println("</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}