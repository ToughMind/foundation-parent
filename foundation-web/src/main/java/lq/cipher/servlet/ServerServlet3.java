package lq.cipher.servlet;


import lq.common.utils.cipher.AESUtils;
import lq.common.utils.cipher.HttpUtils;
import lq.common.utils.cipher.RSAUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Description RSA数字签名算法
 * @author liuquan
 * @time 2016年3月5日 下午2:06:35
 */
public class ServerServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static String key; // 私钥
	private static final String KEY_PARAM = "key"; //servlet初始化参数-私钥  
    
    public ServerServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	public void init(ServletConfig config) throws ServletException { 
		super.init(config); 
		key = getInitParameter(KEY_PARAM);  
	}

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @description  对秘密密钥的传输同ServerServlet2，但增加了数字签名功能，这里作为发送者，要对发送的数据进行数字签名
	 * @date  2016年3月3日
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			byte[] input = HttpUtils.requestRead(request);
			//用RSA私钥对秘密密钥解密
			String secretStr = new String(RSAUtils.decryptByPrivateKey(input, key));
			System.out.println("RSA私钥解码得到Base64编码后的秘密密钥\t" + secretStr);
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"\r\n");
			sb.append("<dataGroup>\r\n");
			sb.append("\t<data>\r\n");
			sb.append("\t\t<id>1204211169</id>\r\n");
			sb.append("\t\t<name>liuquan</name>\r\n");
			sb.append("\t</data>\r\n");
			sb.append("</dataGroup>");
			byte[] data = sb.toString().getBytes();
			
			//使用RSA签名算法 用私钥（web.xml中配置好的）对数据签名
			String sign = RSAUtils.sign(data, key);
			System.out.println("sign\t" + sign);
			/**
			 * 重新组织待输出的数据，将该数据的签名封装到数据包顶部
			 * RSA得到的数字签名长度固定，与初始化密钥长度相同。
			 * 我这里在RSAUtils包中设定了密钥的长度为512位，则得到的签名长度为128位16进制，即512位二进制
			 */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bos.write(sign.getBytes());
			bos.write(data);
			data = bos.toByteArray();
			bos.flush();
			bos.close();
			//使用AES算法加密数据（包括数字签名和真正的数据内容）			
			HttpUtils.responseWrite(response, AESUtils.encrypt(data, secretStr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
