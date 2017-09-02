package lq.pre20170513.http.tutorial;

import com.sun.deploy.net.HttpResponse;
import org.apache.http.*;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.springframework.http.HttpStatus;

/**
 * @description HttpClient provides methods to retrive,add,remove and enumerate
 *              headers
 * 
 * @author liuquan
 * @date 2016年1月25日
 */
public class Headers {
/*    public static void main(String[] args) {
        HttpResponse httpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1,
            HttpStatus.OK, "OK");
        httpResponse.addHeader("Cookie", "c1=a; path=/; domain=localhost");
        httpResponse.addHeader("Cookie",
            "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
        Header h1 = httpResponse.getFirstHeader("Cookie");
        Header h2 = httpResponse.getLastHeader("Cookie");
        Header[] hs = httpResponse.getHeaders("Cookie");
        System.out.println(h1);
        System.out.println(h2);
        System.out.println(hs.length);

        // 最有效的获取所有headers的一个现有接口是HeaderIterator
        System.out.println("\n最有效的获取headers");
        HeaderIterator it = httpResponse.headerIterator("Cookie");
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 有快捷的方法将http信息解析成个体的header元素
        System.out.println("\n解析成header element");
        HeaderElementIterator eit = new BasicHeaderElementIterator(
            httpResponse.headerIterator("Cookie"));
        while (eit.hasNext()) {
            System.out.println("********a header element:");
            HeaderElement elem = eit.nextElement();
            System.out.println(elem.getName() + "=" + elem.getValue());
            NameValuePair[] params = elem.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.println("" + params[i]);
            }
        }
    }*/
}
