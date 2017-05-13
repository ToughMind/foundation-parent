package lq.pre20170513.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//发送端
public class UdpClient {
	public static void main(String[] args) throws IOException {
		//创建DataGramSocket，指定端口
		DatagramSocket ds = new DatagramSocket(9998);
		String str = "abc";
		//用byte数组，创建数据包对象
		DatagramPacket dp = new DatagramPacket(str.getBytes(), 0, str.length(),InetAddress.getByName("localhost"),9999);
		ds.send(dp);
	}
}
