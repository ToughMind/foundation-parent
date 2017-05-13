package lq.pre20170513.http.tutorial;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @description HTTP messages can carry a content entity associated with the
 *              request or response.
 * 
 * @author liuquan
 * @date 2016年2月14日
 */
public class Entity {
	public static void main(String[] args) throws ParseException, IOException {
		StringEntity myEntity = new StringEntity("important message",
				ContentType.create("text/plain", "UTF-8"));
		System.out.println(myEntity.getContentType());
		System.out.println(myEntity.getContentLength());
		System.out.println(EntityUtils.toString(myEntity));
		System.out.println(EntityUtils.toByteArray(myEntity).length);

		// 确保低级别资源释放 可关闭与entity连接的content流或者关闭
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://www.baidu.com");
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					// do something useful
				} finally {
					instream.close();
				}
			}
		} finally {
			response.close();
		}

		// 消耗实体内容 然而， EntityUtils 的使用是强烈不鼓励的，除非响应实体源自可靠的 HTTP 服务器和已知的长度限制。
		HttpGet httpget2 = new HttpGet("http://www.baidu.com");
		HttpResponse response2 = httpclient.execute(httpget);
		HttpEntity entity = response2.getEntity();
		if (entity != null) {
			long len = entity.getContentLength();
			if (len != -1 && len < 2048) {
				System.out.println(EntityUtils.toString(entity));
			} else {
				// Stream content out
			}
		}

		// 获取可重复读取的实体 entity = new BufferedHttpEntity(entity);

		// HTML forms
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("param1", "value1"));
		formparams.add(new BasicNameValuePair("param2", "value2"));
		UrlEncodedFormEntity entity2 = new UrlEncodedFormEntity(formparams,
				Consts.UTF_8);
		HttpPost httppost2 = new HttpPost("http://localhost/handler.do");
		httppost2.setEntity(entity2);

		// 设置 HttpEntity#setChunked()方法为 true 是通知 HttpClient 分块编码的首选。
		// entity.setChunked(true);

		
		HttpClient httpclient3 = new DefaultHttpClient();
		HttpGet httpget3 = new HttpGet("http://www.baidu.com");
		ResponseHandler<byte[]> handler = new ResponseHandler<byte[]>() {
			public byte[] handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				HttpEntity entity3 = response.getEntity();
				if (entity3 != null) {
					return EntityUtils.toByteArray(entity3);
				} else {
					return null;
				}
			}
		};
		byte[] response3 = httpclient.execute(httpget, handler);
	}
}
