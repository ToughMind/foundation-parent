package lq.cipher.base.ssl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SSLClient {
	private static String hostname = "localhost";
	private static int port = 7070;
	
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.keyStore", "src/lq.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "liuquan");
		System.setProperty("javax.net.ssl.trustStore", "src/lq.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "liuquan");
		
		try{
			//构建SSLSocketFactory
			SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket socket = (SSLSocket) sf.createSocket(hostname, port);
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			//构建一个MAP对象，用于记录用户名和密码
			Map<String, String> map = new HashMap<String, String>();
			map.put("USERNAME", "liuquan");
			map.put("PASSWORD", "liuquan");
			output.writeObject(map);
			output.flush();
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			if("OK".equals(input.readUTF())){
				System.out.println("成功！");
			}
			output.close();
			input.close();
			socket.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
