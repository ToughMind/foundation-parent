package lq.cipher.servlet;

import util.AESUtils;
import util.HttpUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description AES加密数据算法 
 * @author liuquan
 * @time 2016年3月5日 下午2:06:57
 */
public class ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static String key; // 秘密密钥 
	private static final String KEY_PARAM = "key"; //servlet初始化参数-密钥 
	private static final String HEAD_MD = "messageDigest"; // Http Header摘要参数名
    
    public ServerServlet() {
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
			String messageDigest = request.getHeader(HEAD_MD); 
			byte[] input = HttpUtils.requestRead(request); 
			//对数据解密
			byte[] data = AESUtils.decrypt(input,  key);
			System.out.println("被请求端得到数据=" +  new String(data));
			//默认回复内容
			byte[] output = "".getBytes();
			//验证摘要 即验证数据完整性
			if(AESUtils.validate(data, messageDigest)){
				System.out.println("验证摘要正确");
				output = "OK".getBytes();
			}
			//加密回复
			HttpUtils.responseWrite(response, AESUtils.encrypt(output, key));			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
