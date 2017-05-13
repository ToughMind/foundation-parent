package lq.pre20170513.http.httpClient;

import org.apache.http.*;
import org.apache.http.auth.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * @description HttpClient学习---HTTP认证部分
 * 
 * @author liuquan
 * @date  2015年12月10日
 */
public class Certification {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//用户凭证		
		//f1();
		
		//凭据提供器
		//f2();
		
		// HTTP 认证和执行上下文
		//f3();
		
		// 抢占认证	
		//f4();
	}

	/**@description 抢占认证
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f4() {
		HttpRequestInterceptor  preemptiveAuth = new HttpRequestInterceptor() {
			public void process(final HttpRequest request,final HttpContext context) throws HttpException, IOException {
					AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
					CredentialsProvider credsProvider = (CredentialsProvider)context.getAttribute(ClientContext.CREDS_PROVIDER);
					HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
					// 如果没有初始化auth模式
					if (authState.getAuthScheme() == null) {
						AuthScope authScope = new AuthScope(targetHost.getHostName(),targetHost.getPort());
						// 获得匹配目标主机的凭据
						Credentials creds =	credsProvider.getCredentials(authScope);
						// 如果发现了，抢先生成BasicScheme
						if (creds != null) {
							authState.setAuthScheme(new BasicScheme());
							authState.setCredentials(creds);
						}
					}
			}
		};
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// 作为第一个拦截器加入到协议链中
		httpclient.addRequestInterceptor(preemptiveAuth, 0);
	}

	/**@description  HTTP 认证和执行上下文
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f3() throws IOException, ClientProtocolException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet("http://localhost:8080/");
		HttpResponse response = httpclient.execute(httpget,localContext);
		
		AuthState proxyAuthState = (AuthState)localContext.getAttribute(ClientContext.PROXY_AUTH_STATE);
		System.out.println("Proxy auth scope: " +	proxyAuthState.getAuthScope());
		System.out.println("Proxy auth scheme: " +  proxyAuthState.getAuthScheme());
		System.out.println("Proxy auth credentials: " +	proxyAuthState.getCredentials());
		
		AuthState targetAuthState = (AuthState)localContext.getAttribute(ClientContext.TARGET_AUTH_STATE);
		System.out.println("Target auth scope: " +	targetAuthState.getAuthScope());
		System.out.println("Target auth scheme: " +	targetAuthState.getAuthScheme());
		System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
	}

	/**@description 凭据提供器
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f2() {
		CredentialsProvider credsProvider = new	BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope("somehost", AuthScope.ANY_PORT),new UsernamePasswordCredentials("u1", "p1"));
		credsProvider.setCredentials(new AuthScope("somehost", 8080),new UsernamePasswordCredentials("u2", "p2"));
		credsProvider.setCredentials(new AuthScope("otherhost", 8080, AuthScope.ANY_REALM, "ntlm"),new UsernamePasswordCredentials("u3", "p3"));
		System.out.println(credsProvider.getCredentials(new AuthScope("somehost", 80, "realm", "basic")));
		System.out.println(credsProvider.getCredentials(new AuthScope("somehost", 8080, "realm", "basic")));
		System.out.println(credsProvider.getCredentials(new AuthScope("otherhost", 8080, "realm", "basic")));
		System.out.println(credsProvider.getCredentials(new AuthScope("otherhost", 8080, null, "ntlm")));
	}

	/**@description 用户凭证		
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f1() {
		//UsernamePasswordCredentials 代表了一组包含安全规则和明文密码的凭据
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials("user", "pwd");
		System.out.println(creds.getUserPrincipal().getName());
		System.out.println(creds.getPassword());
		
		// 在微软的Windows网络中，相同的用户使用不同设置的认证可以属于不同的域
		NTCredentials creds2 = new NTCredentials("user", "pwd","workstation", "domain");
		System.out.println(creds2.getUserPrincipal().getName());
		System.out.println(creds2.getPassword());
	}
}
