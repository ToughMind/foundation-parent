package lq.common.utils.cipher;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;


/**
 * @description HTTPS连接的相关配置  单向认证
 * 
 * @author liuquan
 * @date  2016年3月14日
 */
public class HttpsUtils {
	public static final String PROTOCOL = "TLS";
	
	/**
	 * @description 获取密钥库  
	 * @date  2016年3月14日
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
		// 实例化密钥库
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());  // 若是双向认证   KeyStore ks = KeyStore.getInstance("PKCS12");
		// 获得密钥库文件流
		FileInputStream is = new FileInputStream(keyStorePath);
		// 加载密钥库
		ks.load(is, password.toCharArray());
		// 关闭密钥库文件流
		is.close();
		return ks;
	}
	
	/**
	 * 获得SSLSocektFactory 若是双向认证，密钥库和信任库路径不一致
	 * 
	 * @param password 密码
	 * @param keyStorePath 密钥库路径
	 * @param trustStorePath 信任库路径 
	 */
	private static SSLSocketFactory getSSLSocketFactory(String password, String keyStorePath, String trustStorePath) throws Exception {
		// 实例化密钥库
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		// 获得密钥库
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		// 初始化密钥工厂
		keyManagerFactory.init(keyStore, password.toCharArray());

		// 实例化信任库
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		// 获得信任库
		KeyStore trustStore = getKeyStore(trustStorePath, password);
		// 初始化信任库
		trustManagerFactory.init(trustStore);

		// 实例化SSL上下文
		SSLContext ctx = SSLContext.getInstance(PROTOCOL);
		// 初始化SSL上下文
		ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
		// 获得SSLSocketFactory
		return ctx.getSocketFactory();
	}
	
	/**
	 * 为HttpsURLConnection配置SSLSocketFactory
	 *  
	 * @param password 密码
	 * @param keyStorePath 密钥库路径
	 * @param trustKeyStorePath 信任库路径
	 * @throws Exception
	 */
	public static void configSSLSocketFactory(HttpsURLConnection conn, String password, String keyStorePath, String trustKeyStorePath) throws Exception {
		// 获得SSLSocketFactory
		SSLSocketFactory sslSocketFactory = getSSLSocketFactory(password, keyStorePath, trustKeyStorePath);
		// 设置SSLSocketFactory
		conn.setSSLSocketFactory(sslSocketFactory);
	}
	
	public static void main(String[] args) throws Exception {
		String password = "liuquan"; 
		String keyStorePath = "conf/lq.keystore";
		String trustStorePath = "conf/lq.keystore";
		String httpsUrl = "https://www.lq.com/test/";
		
		// 建立HTTPS链接
		URL url = new URL(httpsUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		// conn.setRequestMethod(method);

		// 打开输入输出流
		conn.setDoInput(true);
		// conn.setDoOutput(true);

		// 为HttpsURLConnection配置SSLSocketFactory
		HttpsUtils.configSSLSocketFactory(conn, password, keyStorePath, trustStorePath);

		// 鉴别内容长度
		int length = conn.getContentLength();
		byte[] data = null;

		// 如果内容长度为-1，则放弃解析
		if (length != -1) {
			DataInputStream dis = new DataInputStream(conn.getInputStream());
			data = new byte[length];
			dis.readFully(data);
			dis.close();
			System.err.println(new String(data));
		}
		conn.disconnect(); 
		
	}
	
}
