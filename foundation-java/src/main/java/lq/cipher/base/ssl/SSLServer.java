package lq.cipher.base.ssl;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class SSLServer {
	private static int port = 7070;
	
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.keyStore", "src/lq.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "liuquan");
		System.setProperty("javax.net.ssl.trustStore", "src/lq.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "liuquan");
		
		SSLServerSocket serverSocket = null;
		try{
			SSLServerSocketFactory sf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			serverSocket = (SSLServerSocket) sf.createServerSocket(port);
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
		
		while(true){
			try{
				//SSLServerSocket阻塞，等待客户端连接请求
				SSLSocket socket = (SSLSocket) serverSocket.accept();
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				//获得用户名密码
				Map<String, String> map = (Map<String, String>)input.readObject();
				String username = map.get("USERNAME");
				String password = map.get("PASSWORD");
				if("liuquan".equals(username) && "liuquan".equals(password)){		
					System.out.println("验证用户名");
					ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
					os.writeUTF("OK");
					os.flush();
					os.close();
					input.close();
					socket.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
}
