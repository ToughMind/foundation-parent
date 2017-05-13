package lq.pre20170513.http.httpClient;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.conn.*;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @description HttpClient学习---连接管理部分
 * 
 * @author liuquan
 * @date  2015年12月10日
 */
public class Connect {
	public static void main(String[] args) throws NoSuchAlgorithmException, ConnectionPoolTimeoutException, InterruptedException, IOException {
		//套接字工厂  PlainSocketFactory 是创建和初始化普通的（不加密的）套接字的默认工厂
		//没有成功  抛出转换成Integer异常  （反编译调试位置不对   可利用mvn dependency:resouce下载源码）
		//f1();   
		
		//SSL/TLS 的定制  SSLSocketFactory
		//f2();
		
		// 每一个默认的 HttpClient 使用 BrowserCompatHostnameVerifier 的实现
		//f3();
		
		//协议模式		
		//f4(); 
		
		//HttpClient 代理配置
		//f5();
		
		//管理连接和连接管理器
		//f6();
		
	}

	/**@description 
	 * @throws InterruptedException
	 * @throws ConnectionPoolTimeoutException
	 * @throws IOException
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f6() throws InterruptedException,ConnectionPoolTimeoutException, IOException {
		HttpParams params = new BasicHttpParams();
		Scheme http = new Scheme("http",PlainSocketFactory.getSocketFactory(), 80);
		SchemeRegistry sr = new SchemeRegistry();
		sr.register(http);
		ClientConnectionManager connMrg = new SingleClientConnManager(params, sr);
		// 请求新连接。这可能是一个很长的过程。
		ClientConnectionRequest connRequest =connMrg.requestConnection(new HttpRoute(new HttpHost("localhost", 80)), null);
		// 等待连接10秒
		ManagedClientConnection conn = connRequest.getConnection(10,TimeUnit.SECONDS);
		try {
		// 用连接在做有用的事情。当完成时释放连接。
		conn.releaseConnection();
		} catch (IOException ex) {
		// 在I/O error之上终止连接。
		conn.abortConnection();
		throw ex;
		}
	}

	/**@description HttpClient 代理配置
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f5() {
		//默认代理参数
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpHost proxy = new HttpHost("someproxy", 8080);
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		
		//使用标准的 JRE 代理选择器来获得代理信息：
		DefaultHttpClient httpclient2= new DefaultHttpClient();
		ProxySelectorRoutePlanner routePlanner = new ProxySelectorRoutePlanner(httpclient2.getConnectionManager().getSchemeRegistry(),ProxySelector.getDefault());
		httpclient2.setRoutePlanner(routePlanner);
				
		//提供一个定制的 RoutePlanner 实现来获得 HTTP 路由计算处理上的复杂的控制
		DefaultHttpClient httpclient3 = new DefaultHttpClient();
		httpclient3.setRoutePlanner(new HttpRoutePlanner() 
		{
			public HttpRoute determineRoute(HttpHost target,HttpRequest request,HttpContext context) throws HttpException 
			{	
				return new HttpRoute(target, null, new HttpHost("someproxy", 8080),"https".equalsIgnoreCase(target.getSchemeName()));
			} 
		});	
	}

	/**
	 * @description 协议模式		
	 * 
	 * @author liuquan
	 * @throws NoSuchAlgorithmException 
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f4() throws NoSuchAlgorithmException {
		Scheme http = new Scheme("http",PlainSocketFactory.getSocketFactory(), 80);
		SSLSocketFactory sf = new SSLSocketFactory(SSLContext.getInstance("TLS"));
		sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
		Scheme https = new Scheme("https", sf, 443);
		SchemeRegistry sr = new SchemeRegistry();
		sr.register(http);
		sr.register(https);
	}	
	
	/**
	 * @description 每一个默认的 HttpClient 使用 BrowserCompatHostnameVerifier 的实现
	 * 
	 * @author liuquan
	 * @throws NoSuchAlgorithmException 
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f3() throws NoSuchAlgorithmException {
		SSLSocketFactory sf = new SSLSocketFactory(SSLContext.getInstance("TLS"));
		sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);		
	}

	/**
	 * @description 
	 * 
	 * @author liuquan
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws IOException 
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f2() throws NoSuchAlgorithmException, KeyManagementException, IOException {
		X509TrustManager easyTrustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain,
			String authType) throws CertificateException {
			// 哦，这很简单！
			}
			@Override
			public void checkServerTrusted(X509Certificate[] chain,
			String authType) throws CertificateException {
			//哦，这很简单！
			}
			@Override
			public X509Certificate[] getAcceptedIssuers() {
			return null;
			}
		};
		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(null, new TrustManager[] { easyTrustManager },null);
		SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
		SSLSocket socket = (SSLSocket) sf.createSocket();
		socket.setEnabledCipherSuites(new String[]{ "SSL_RSA_WITH_RC4_128_MD5" });
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,1000L);
		sf.connectSocket(socket, "localhost", 8080, null, -1, params);		
	}

	/**
	 * @description 套接字工厂  PlainSocketFactory 是创建和初始化普通的（不加密的）套接字的默认工厂
	 * 
	 * @author liuquan
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ConnectTimeoutException 
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f1() throws ConnectTimeoutException, UnknownHostException, IOException {
		PlainSocketFactory sf = PlainSocketFactory.getSocketFactory();
		Socket socket = sf.createSocket();
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,1000L);
		sf.connectSocket(socket, "localhost",8080, null, -1, params);
	}
}
