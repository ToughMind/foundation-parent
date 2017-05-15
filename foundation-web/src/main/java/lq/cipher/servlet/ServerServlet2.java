package lq.cipher.servlet;

import util.AESUtils;
import util.HttpUtils;
import util.RSAUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description RSA加密、解密秘密密钥算法 
 * @author liuquan
 * @time 2016年3月5日 下午2:06:35
 */
public class ServerServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static String key; // 私钥  
	private static final String KEY_PARAM = "key"; //servlet初始化参数-私钥  
    
    public ServerServlet2() {
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
	 *  @description 通过调用HttpUtils类的requestRead()方法获取请求内容，
	 *  调用AESUtils的decrypt()方法对内容解密。同时从HTTP Header中获得此次交互数据的摘要信息，并验证
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
			HttpUtils.responseWrite(response, AESUtils.encrypt(data, secretStr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
