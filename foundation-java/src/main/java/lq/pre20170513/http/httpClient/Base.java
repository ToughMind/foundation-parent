package lq.pre20170513.http.httpClient;

import com.sun.deploy.net.HttpResponse;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParamBean;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description HttpClient学习---基础部分 
 * 
 * @author liuquan
 * @date  2015年12月10日
 */
public class Base {
	/**@description 
	 * @param args
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author liuquan
	 * @throws URISyntaxException 
	 * @date  2015年12月9日
	 */
	public static void main(String[] args) throws ClientProtocolException,
			IOException, URISyntaxException {
				
		//关于请求uri	和发送get 读取response	
		f2();
		
		//参数+拦截器
		f1();

		//设置http上下文
		f3();
		
		//POST设置entity 请求参数封装到list
		f4();
		
		//响应控制器 ResponseHandler
		f5();
		
		//请求重试处理器
		f6();
		
		//对response操作 增加报头 如Cookie
		f7();
		
		//动态生成内容实体  发送post或者get请求时建立
		f8();		
		
		//HTTP参数bean的设置  HttpParams不能使用 DI 框架来组合,不过包含了一些bean
		f9();
		 
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * @description HTTP参数bean的设置
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f9() {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParamBean paramsBean = new	HttpProtocolParamBean(params);
		
		paramsBean.setVersion(HttpVersion.HTTP_1_1);
		paramsBean.setContentCharset("UTF-8");
		paramsBean.setUseExpectContinue(true);
		
		System.out.println(params.getParameter(CoreProtocolPNames.PROTOCOL_VERSION));
		System.out.println(params.getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET));
		System.out.println(params.getParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE));
		System.out.println(params.getParameter(CoreProtocolPNames.USER_AGENT));		
	}

	@SuppressWarnings("deprecation")
	/**
	 * @description 动态生成内容实体  发送post或者get请求时建立
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f8() throws ParseException, IOException {
		ContentProducer cp = new ContentProducer() {
			public void writeTo(OutputStream outstream) throws IOException {
			Writer writer = new OutputStreamWriter(outstream, "UTF-8");
			writer.write("<response>");
			writer.write("<content>");
			writer.write("important stuff");
			writer.write("</content>");
			writer.write("</response>");
			writer.flush();
			}
		};	
		HttpEntity entity = new EntityTemplate(cp);
		HttpPost httppost = new HttpPost("http://localhost:8080/test/CnpTxnMsgQueryServlet?pid=900&orderid=152040949998160&channeltype=1");
	 	httppost.setEntity(entity); 		
	 	System.out.println(EntityUtils.toString(entity)); 		
	}

	@SuppressWarnings("deprecation")
	/**
	 * @description 对response操作 增加报头  如Cookie
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f7() {		
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		response.addHeader("Cookie","a=java;b=/;c=goodJava");
		response.addHeader("Cookie","a=C;b=/;c=goodC");
		response.addHeader("Cookie","a=python; b=/; c=goodPython");
		response.addHeader("Cookie","a=\"java\";b=\"/\";c=\"goodJava\""); 
		
		Header h1 = response.getFirstHeader("Cookie");
		System.out.println("Cookie first---" + h1);
		Header h2 = response.getLastHeader("Cookie");
		System.out.println("Cookie last---" + h2);
		Header[] hs = response.getHeaders("Cookie");
		System.out.println("Cookie length---" + hs.length);  
		
		HeaderIterator it = response.headerIterator("Cookie");
		while (it.hasNext()) { 
			System.out.println("Cookie---" + it.next());
		} 
		
		HeaderElementIterator it2 = new BasicHeaderElementIterator(response.headerIterator("Cookie"));
			while (it2.hasNext()) {
				HeaderElement elem = it2.nextElement();
				System.out.println("getName()---" + elem.getName() + " = " + elem.getValue());
				NameValuePair[] params = elem.getParameters();
				for (int i = 0; i < params.length; i++) {
					System.out.println("params" + i + "---" + params[i]);
			}
		}  			
	}

	@SuppressWarnings("deprecation")
	/**
	 * @description 请求重试处理器
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f6() throws ClientProtocolException, IOException {
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() { 
			@Override
			public boolean retryRequest(IOException arg0, int arg1, HttpContext arg2) {
				if (arg1 >= 5) {
					// 如果超过最大重试次数，那么就不要继续了
					return false;
					}
					if (arg0 instanceof NoHttpResponseException) {
					// 如果服务器丢掉了连接，那么就重试
					return true;
					}
					if (arg0 instanceof SSLHandshakeException) {
					// 不要重试SSL握手异常
					return false;
					}
					HttpRequest request = (HttpRequest) arg2.getAttribute(ExecutionContext.HTTP_REQUEST);
					boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
					if (idempotent) {
					// 如果请求被认为是幂等的，那么就重试
					return true;
					}
					return false;
				} 
		};
		HttpClient httpclient = new DefaultHttpClient(); 
		HttpGet httpget = new HttpGet("http://localhost:8080/test/CnpTxnMsgQueryServlet?pid=900&orderid=152040949998160&channeltype=1");
		((AbstractHttpClient) httpclient).setHttpRequestRetryHandler(myRetryHandler); 
		System.out.println(EntityUtils.toString(httpclient.execute(httpget).getEntity()));			
	}

	@SuppressWarnings("deprecation")
	/**
	 * @description 响应控制器 ResponseHandler
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f5() throws ClientProtocolException, IOException {
		ResponseHandler<byte[]> handler = new ResponseHandler<byte[]>() { 
			@Override
			public byte[] handleResponse(HttpResponse arg0)
					throws ClientProtocolException, IOException {
				HttpEntity entity = arg0.getEntity();
				if (entity != null) {
				return EntityUtils.toByteArray(entity);
				} else {
				return null;
				}
			}
		};
		HttpClient httpclient = new DefaultHttpClient(); 
		HttpGet httpget = new HttpGet("http://localhost:8080/test/CnpTxnMsgQueryServlet?pid=900&orderid=152040949998160&channeltype=1");
		byte[] response = httpclient.execute(httpget, handler);
		System.out.println("response---" + new String(response)); 
	}

	@SuppressWarnings("deprecation")
	/**
	 * @description POST创建entity 请求参数封装到list 
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f4() throws ParseException, IOException {
		List<NameValuePair>  paramsList = new ArrayList<NameValuePair>();
		paramsList.add(new BasicNameValuePair("pid", "900"));
		paramsList.add(new BasicNameValuePair("orderid","152040949998160"));
		paramsList.add(new BasicNameValuePair("channeltype", "1"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramsList, "UTF-8");
		HttpPost httppost = new	HttpPost("http://localhost:8080/test/CnpTxnMsgQueryServlet");
		httppost.setEntity(entity);
		System.out.println("entity3---"+EntityUtils.toString(entity));		
	}

	@SuppressWarnings("deprecation")
	/**
	 * @description 设置http上下文
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f3() throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient(); 
		HttpGet httpget = new HttpGet("http://localhost:8080/test/CnpTxnMsgQueryServlet?pid=900&orderid=152040949998160&channeltype=1");
		HttpContext localContext = new BasicHttpContext();
		HttpResponse response = httpclient.execute(httpget,localContext);
		HttpHost target = (HttpHost) localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
		System.out.println("target--- " + target);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			entity.consumeContent();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * @description 关于请求uri 和发送get 读取response	
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f2() throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient(); 
		//URI可以编程拼装 GET
		URI uri = URIUtils.createURI("http", "localhost:8080", -1,"/test/CnpTxnMsgQueryServlet","pid=900", null);
		System.out.println("uri---"+uri); 
		
		//查询字符串可以从封装的参数中生成 GET
		List<NameValuePair>  paramsList = new ArrayList<NameValuePair>();
		paramsList.add(new BasicNameValuePair("pid", "900"));
		paramsList.add(new BasicNameValuePair("orderid","152040949998160"));
		paramsList.add(new BasicNameValuePair("channeltype", "1"));
		URI uri2 = URIUtils.createURI("http", "localhost:8080", -1,"/test/CnpTxnMsgQueryServlet",URLEncodedUtils.format(paramsList, "UTF-8"), null);
		System.out.println("uri2---" + uri2);
		
		HttpGet httpget = new HttpGet(uri2);	 		
		HttpResponse response = httpclient.execute(httpget);
		
		//用自带的工具类EntityUtils读取entity
		HttpEntity entity = response.getEntity(); 	
		if (entity != null) {		 
			//加上这句entity被存到内存或者缓存区
			entity = new BufferedHttpEntity(entity);
			long len = entity.getContentLength();
			System.out.println("ContentLength---" + len);
			System.out.println("第一次读取entity---" + EntityUtils.toString(entity));
			System.out.println("再读(存入内存了)---" + EntityUtils.toString(entity));	
		}
		
		//标准的自定义读取entity 
	    InputStream instream = entity.getContent();
		int l;
		byte[] tmp = new byte[2048];
		while ((l = instream.read(tmp)) != -1) {
			System.out.println("标准读取entity---" + new String(tmp));
		}
		httpget.abort(); 
	}
 
	@SuppressWarnings("deprecation")
	/**
	 * @description 参数+拦截器
	 * 
	 * @author liuquan
	 * @date  2015年12月10日
	 */
	private static void f1() throws ClientProtocolException, IOException {		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_0);
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,"UTF-8");
		HttpGet httpget = new HttpGet("http://localhost:8080/test/CnpTxnMsgQueryServlet?pid=900&orderid=152040949998160&channeltype=1");
		httpget.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
		httpget.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,Boolean.FALSE);
		httpclient.addRequestInterceptor(
			new HttpRequestInterceptor() {
				public void process(final HttpRequest request,final HttpContext context) throws HttpException, IOException {
					System.out.println(request.getParams().getParameter(CoreProtocolPNames.PROTOCOL_VERSION));
					System.out.println(request.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET));
					System.out.println(request.getParams().getParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE));
					System.out.println(request.getParams().getParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING));
			}
		}); 
	} 	

}

