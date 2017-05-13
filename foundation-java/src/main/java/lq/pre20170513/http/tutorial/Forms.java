package lq.pre20170513.http.tutorial;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @description HttpClient provides the entity class UrlEncodedFormEntity to
 *              facilitate the process.
 * 
 * @author liuquan
 * @date 2016年2月15日
 */
public class Forms {
	public static void main(String[] args) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("param1", "value1"));
		formparams.add(new BasicNameValuePair("param2", "value2"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				Consts.UTF_8);
		HttpPost httppost = new HttpPost("http://localhost/handler.do");
		httppost.setEntity(entity);
	}
}
