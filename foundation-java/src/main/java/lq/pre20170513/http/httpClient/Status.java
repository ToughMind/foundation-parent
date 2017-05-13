package lq.pre20170513.http.httpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * @description HttpClient学习---状态管理部分
 * 
 * @author liuquan
 * @date  2015年12月10日
 */
public class Status {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//不同版本的Cookie的创建
		//f1();
		
		//选择Cookie
		//f2();
		
		//定制Cookie
		//f3();

		//HTTP 状态管理和执行上下文
		//f4();
		
		//每个用户/线程的状态管理
		//f5();
	}

	/**@description 每个用户/线程的状态管理
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f5() throws IOException, ClientProtocolException {
		HttpClient httpclient = new DefaultHttpClient();
		// 创建cookie store的本地实例
		CookieStore cookieStore = new BasicCookieStore();
		// 创建本地的HTTP内容
		HttpContext localContext = new BasicHttpContext();
		// 绑定定制的cookie store到本地内容中
		localContext.setAttribute(ClientContext.COOKIE_STORE,cookieStore);
		HttpGet httpget = new HttpGet("http://www.google.com/");
		// 作为参数传递本地内容
		HttpResponse response = httpclient.execute(httpget,localContext);
	}

	/**@description HTTP 状态管理和执行上下文
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f4() throws IOException, ClientProtocolException {
		//HTTP 状态管理和执行上下文
		HttpClient httpclient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet("http://localhost:8080/");
		HttpResponse response = httpclient.execute(httpget,localContext);
		CookieOrigin cookieOrigin = (CookieOrigin)localContext.getAttribute(ClientContext.COOKIE_ORIGIN);
		System.out.println("Cookie origin--- " + cookieOrigin);
		CookieSpec cookieSpec = (CookieSpec)localContext.getAttribute(ClientContext.COOKIE_SPEC);
		System.out.println("Cookie spec used---" + cookieSpec);
	}

	/**@description  定制Cookie
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f3() {
		CookieSpecFactory csf = new CookieSpecFactory() {
			public CookieSpec newInstance(HttpParams params) {
				return new BrowserCompatSpec() {   
					public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
					// 这相当简单 
					}
			    };
		    }
		};
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getCookieSpecs().register("easy", csf);
		httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
	}

	/**@description 选择Cookie
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	@SuppressWarnings("deprecation")
	private static void f2() {
		HttpClient httpclient = new DefaultHttpClient();
		// 对每个默认的强制严格cookie策略
		httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2965);
		HttpGet httpget = new HttpGet("http://www.broken-server.com/");
		// 对这个请求覆盖默认策略
		httpget.getParams().setParameter(ClientPNames.COOKIE_POLICY,CookiePolicy.BROWSER_COMPATIBILITY);
	}

	/**@description 不同版本的Cookie的创建
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f1() {
		//重新创建网景公司草案 cookie 
		BasicClientCookie netscapeCookie = new BasicClientCookie("name", "value");
		netscapeCookie.setVersion(0);
		netscapeCookie.setDomain(".mycompany.com");
		netscapeCookie.setPath("/");
		
		//重新创建标准 cookie 
		BasicClientCookie stdCookie = new BasicClientCookie("name","value");
		stdCookie.setVersion(1);
		stdCookie.setDomain(".mycompany.com");
		stdCookie.setPath("/");
		stdCookie.setSecure(true);
		// 精确设置由服务器发送的属性
		stdCookie.setAttribute(ClientCookie.VERSION_ATTR, "1");
		stdCookie.setAttribute(ClientCookie.DOMAIN_ATTR,".mycompany.com");
		
		//重新创建 Set-Cookie2 兼容 cookie 的实例
		BasicClientCookie2 stdCookie2 = new BasicClientCookie2("name","value");
		stdCookie2.setVersion(1);
		stdCookie2.setDomain(".mycompany.com");
		stdCookie2.setPorts(new int[] {80,8080});
		stdCookie2.setPath("/");
		stdCookie2.setSecure(true);
		// 精确设置由服务器发送的属性
		stdCookie2.setAttribute(ClientCookie.VERSION_ATTR, "1");
		stdCookie2.setAttribute(ClientCookie.DOMAIN_ATTR,".mycompany.com");
		stdCookie2.setAttribute(ClientCookie.PORT_ATTR, "80,8080");
	}
}
