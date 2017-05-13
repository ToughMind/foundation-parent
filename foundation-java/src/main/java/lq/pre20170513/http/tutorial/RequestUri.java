package lq.pre20170513.http.tutorial;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @description 利用URIBuilder工具类构建请求URI
 * 
 * @author liuquan
 * @date 2016年1月25日
 */
public class RequestUri {
	public static void main(String[] args) throws URISyntaxException {
		// http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=
		URI uri = new URIBuilder().setScheme("http").setHost("www.google.com")
				.setPath("/search").setParameter("q", "httpclient")
				.setParameter("btnG", "Google Search").setParameter("aq", "f")
				.setParameter("oq", "").build(); 
		HttpGet httpget = new HttpGet(uri);
		System.out.println(httpget.getURI());
	}
}
