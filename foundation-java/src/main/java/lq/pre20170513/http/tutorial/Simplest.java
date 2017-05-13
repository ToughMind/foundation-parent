package lq.pre20170513.http.tutorial;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @description 最简单的request get请求示例 
 * 
 * @author liuquan
 * @date  2016年1月25日
 */
public class Simplest {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://www.baidu.com");
		CloseableHttpResponse response = httpclient.execute(httpget);
		System.out.println(response);
	}
}
