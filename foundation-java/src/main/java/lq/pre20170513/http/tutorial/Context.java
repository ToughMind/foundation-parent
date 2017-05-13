package lq.pre20170513.http.tutorial;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * @description the request configuration set by the initial request will be
 *              kept in the execution context and get propagated to the
 *              consecutive requests sharing the same context.
 * 
 * @author liuquan
 * @date 2016年2月14日
 */
public class Context {
	public static void main(String[] args) throws IOException {
		HttpContext context = new BasicHttpContext();
		HttpClientContext clientContext = HttpClientContext.adapt(context);
		HttpHost target = clientContext.getTargetHost();
		HttpRequest request = clientContext.getRequest();
		HttpResponse response = clientContext.getResponse();
		RequestConfig config = clientContext.getRequestConfig();
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(1000).setConnectTimeout(1000).build();
		HttpGet httpget1 = new HttpGet("http://www.baidu.com");
		httpget1.setConfig(requestConfig);
		CloseableHttpResponse response1 = httpclient.execute(httpget1, context);
		try {
			HttpEntity entity1 = response1.getEntity();
		} finally {
			response1.close();
		}
		HttpGet httpget2 = new HttpGet("http://www.baidu.com");
		CloseableHttpResponse response2 = httpclient.execute(httpget2, context);
		try {
			HttpEntity entity2 = response2.getEntity();
		} finally {
			response2.close();
		}
	}
}
