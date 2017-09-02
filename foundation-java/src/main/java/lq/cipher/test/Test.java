package lq.cipher.test;

//import util.AESUtils;
//import util.HttpUtils;
//import util.RSAUtils;

import java.io.IOException;
import java.util.Properties;

public class Test {
	private static final String KEY = "nbWiQUy0c8G9aMdfM4U5BNerUYulITZasjEQRJNvEoU=";
	private static final String PUBLIC_KEY = "305c300d06092a864886f70d0101010500034b00304802410081fa9e24f78b242d968fb8977a78e6099a03e8d35246bdc4e75acb4d6ba4d58df83142434f2f53784dd6672290307a4d9108c2d145a3de2879cd9433fb0770490203010001";
	private static final String URL = "http://localhost:8080/test/ServerServlet";
	private static final String URL2 = "http://localhost:8080/test/ServerServlet2";
	private static final String URL3 = "http://localhost:8080/test/ServerServlet3";
	
	/*public static void main(String[] args) throws IOException, Exception {
		System.out.println("----------------AES实例");
		StringBuilder sb= new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"\r\n");
		sb.append("<dataGroup>\r\n");
		sb.append("\t<data>\r\n");
		sb.append("\t\t<id>1204211169</id>\r\n");
		sb.append("\t\t<name>liuquan</name>\r\n");
		sb.append("\t</data>\r\n");
		sb.append("</dataGroup>");
		byte[] data = sb.toString().getBytes();
		//向HTTP HEADER附加摘要信息
		Properties requestPro = new Properties();
		requestPro.put("messageDigest", AESUtils.shaHex(data));
		//加密信息并发送 得到响应结果
		byte[] input = HttpUtils.post(URL, AESUtils.encrypt(data, KEY), requestPro);
		//将得到的响应数据解密
		input = AESUtils.decrypt(input, KEY); 
		System.out.println("得到响应的数据是" + new String(input));
		
		
		*//*下面这个例子是用RSA加密秘密密钥，这里用哪种秘密密钥的算法是双方商量好了的。其实就是在上面例子中加了一步骤，
		上面的例子是这里作为发送者，秘密密钥是双方都有的；而下面这个例子，这里作为接收者，接收者存有RSA公钥，发送者那端有私钥，
		这时候的秘密密钥双方还不知道，只是规定了算法，这样这边用公钥加密密钥然后传到发送者那边（其实就是秘密密钥每次都重新生成，不是定值），
		然后发送者那边用RSA私钥解密得到秘密密钥编码，然后就可以用秘密密钥加密真正的内容数据了.*//*
		
		//下面这个例子就是用公钥加密秘密密钥 然后传到（发送者端），发送者端再用私钥解密得到秘密密钥，然后就可以用秘密密钥加密数据了。
		System.out.println("\n----------------RSA结合AES实例");
		String secretKey = AESUtils.initKeyString();
		System.out.println("Base64编码后的秘密密钥\t" + secretKey);
		byte[] input2 = HttpUtils.post(URL2, RSAUtils.encryptByPublicKey(secretKey.getBytes(), PUBLIC_KEY));
		//使用AES算法对响应数据解密
		input2 = AESUtils.decrypt(input2, secretKey);
		System.out.println("得到响应的数据是" + new String(input2)); 
		
		//下面这个例子对上面的进行改进，增加了数字签名功能，数字签名的数据占128位16进制位，在数据包的顶头
		System.out.println("\n----------------RSA加密解密+RSA数字签名+AES实例");
		String secretKey2 = AESUtils.initKeyString();
		System.out.println("Base64编码后的秘密密钥\t" + secretKey2);
		byte[] input3 = HttpUtils.post(URL3, RSAUtils.encryptByPublicKey(secretKey2.getBytes(), PUBLIC_KEY));
		//使用AES算法对响应数据解密
		input3 = AESUtils.decrypt(input3, secretKey2);
		//由于得到的数据包含了签名，所以要分离签名和数据
		String sign = new String(input3).substring(0, 128);
		String realData = new String(input3).substring(128);
		System.out.println("得到相应数据的签名\t" + sign);
		System.out.println("得到相应数据的真正内容\t" + realData);
	}*/
}
