package lq.common.utils.cipher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * @description 用于HTTP请求的工具类
 * 
 * @author liuquan
 * @date 2016年3月3日
 */
public class HttpUtils {
	public static final String CHARACTER_ENCODING = "UTF-8";
	public static final String METHOD_POST = "POST";
	public static final String CONTENT_TYPE = "Content-Type";

	/**
	 * @description 打印响应数据
	 * @date 2016年3月3日
	 */
	public static void responseWrite(HttpServletResponse response, byte[] data)
			throws IOException {
		if (data != null) {
			response.setContentLength(data.length);
			DataOutputStream out = new DataOutputStream(
					response.getOutputStream());
			out.write(data);
			out.flush();
			out.close();
		}
	}

	/**
	 * @description 从请求中读取字节流
	 * @date 2016年3月3日
	 */
	public static byte[] requestRead(HttpServletRequest request)
			throws IOException {
		int contentLength = request.getContentLength();
		byte[] data = null;
		if (contentLength > 0) {
			data = new byte[contentLength];
			DataInputStream dis = new DataInputStream(request.getInputStream());
			dis.readFully(data);
			dis.close();
		}
		return data;
	}

	/**
	 * @description 以post方式向指定地址发送数据包请求，并取得返回的数据包
	 * @author liuquan
	 * @date 2016年3月3日
	 */
	public static byte[] post(String urlString, byte[] reqData)
			throws IOException {
		Properties requestProperties = new Properties();
		requestProperties.setProperty(CONTENT_TYPE,
				"application/octet-stream;charset=" + CHARACTER_ENCODING);
		return post(urlString, reqData, requestProperties);
	}

	/**
	 * @throws IOException
	 * @description 以post方式向指定地址发送数据包请求，并取得返回的数据包
	 * @date 2016年3月3日
	 */
	public static byte[] post(String urlString, byte[] reqData,
			Properties requestProperties) throws IOException {
		byte[] resData = null;
		HttpURLConnection con = null;
		try {
			URL url = new URL(urlString);
			System.out.println("url=" + urlString);
			con = (HttpURLConnection) url.openConnection();
			if (requestProperties != null && requestProperties.size() > 0) {
				for (Map.Entry<Object, Object> entry : requestProperties
						.entrySet()) {
					String key = String.valueOf(entry.getKey());
					String value = String.valueOf(entry.getValue());
					con.setRequestProperty(key, value);
				}
			}
			con.setRequestMethod(METHOD_POST);
			con.setDoOutput(true);
			con.setDoInput(true);			
			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
			if (reqData != null) {
				dos.write(reqData);
			}
			dos.flush();
			dos.close();
			DataInputStream dis = new DataInputStream(con.getInputStream());
			int length = con.getContentLength();
			if (length > 0) {
				resData = new byte[length];
				dis.readFully(resData);
			}
			dis.close();
		} 
		finally {
			if (con != null) {
				con.disconnect();
				con = null;
			}
		}
		return resData;
	}
}
