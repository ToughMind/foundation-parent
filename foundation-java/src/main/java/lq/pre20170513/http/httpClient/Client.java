package lq.pre20170513.http.httpClient;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * @description HttpClient学习---HTTP客户端服务
 * 
 * @author liuquan
 * @date  2015年12月10日
 */
public class Client {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// HTTP 客户端和执行上下文
		f1();
	}

	/**@description 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f1() throws IOException, ClientProtocolException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet("http://localhost:8080/");
		HttpResponse response = httpclient.execute(httpget,localContext);
		
		HttpHost target = (HttpHost) localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
		HttpUriRequest req = (HttpUriRequest)localContext.getAttribute(ExecutionContext.HTTP_REQUEST);
		System.out.println("Target host: " + target);
		System.out.println("Final request URI: " + req.getURI());
		System.out.println("Final request method: " + req.getMethod());
	}
}
