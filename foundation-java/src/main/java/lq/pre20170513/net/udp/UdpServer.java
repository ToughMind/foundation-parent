package lq.pre20170513.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//接收端
public class UdpServer {
	public static void main(String[] args) throws IOException {
		//创建DatagramSocket，指定端口
		DatagramSocket ds = new DatagramSocket(9999);
		//创建数据包对象
		byte[] buff = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buff, 1024);
		//调用receive()
		ds.receive(dp);
		//调用getData获取byte数组数据
		String str = new String(dp.getData(),0,dp.getLength());
		System.out.println(str);
		ds.close();
	}
}
