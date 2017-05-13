package lq.pre20170513.http.tutorial;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @description HttpClient handles all types of redirects automatically, except
 *              those explicitly prohibited by the HTTP specification as
 *              requiring user intervention
 * 
 * @author liuquan
 * @date 2016年2月14日
 */
public class redirect {
	public static void main(String[] args) throws URISyntaxException,
			IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpClientContext context = HttpClientContext.create();
		HttpGet httpget = new HttpGet("http://localhost:8080/");
		CloseableHttpResponse response = httpclient.execute(httpget, context);
		try {
			HttpHost target = context.getTargetHost();
			List<URI> redirectLocations = context.getRedirectLocations();
			URI location = URIUtils.resolve(httpget.getURI(), target,
					redirectLocations);
			System.out.println("Final HTTP location: "
					+ location.toASCIIString());
			// Expected to be an absolute URI
		} finally {
			response.close();
		}
	}
}
