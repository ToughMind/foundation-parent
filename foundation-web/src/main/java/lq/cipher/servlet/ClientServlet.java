package lq.cipher.servlet;

import util.AESUtils;
import util.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String key = "nbWiQUy0c8G9aMdfM4U5BNerUYulITZasjEQRJNvEoU=";
	private static final String url = "http://localhost:8080/test/ServerServlet";

	public ClientServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String sb = request.getParameter("data");
			System.out.println("即将被本机后台摘要、加密处理的数据是" + sb);
			byte[] data = sb.toString().getBytes();
			// 向HTTP HEADER附加摘要信息
			Properties requestPro = new Properties();
			requestPro.put("messageDigest", AESUtils.shaHex(data));
			// 加密信息并发送 得到响应结果
			byte[] input;
			input = HttpUtils.post(url, AESUtils.encrypt(data, key), requestPro);
			// 将得到的响应数据解密
			input = AESUtils.decrypt(input, key);
			System.out.println(new String(input));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
