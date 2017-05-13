package lq.pre20170513.http.selfUrl;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

public class HttpsPost {  
     
	/**
	 * @description 获得KeyStore. 
	 * @param password 密码 
	 * @param keyStorePath 密钥库路径 
	 * @return 密钥库 
	 * @throws Exception
	 * @author liuquan
	 * @date  2015年12月15日
	 */
    public static KeyStore getKeyStore(String password, String keyStorePath)  
            throws Exception {  
        // 实例化密钥库  
        KeyStore ks = KeyStore.getInstance("JKS");  
        // 获得密钥库文件流  
        FileInputStream is = new FileInputStream(keyStorePath);  
        // 加载密钥库  
        ks.load(is, password.toCharArray());  
        // 关闭密钥库文件流  
        is.close();  
        return ks;  
    }   
    
    /**
     * @description 
     * @param password 密码 
     * @param keyStorePath 密钥库路径 
     * @param trustStorePath 信任库路径 
     * @return
     * @throws Exception
     * @author liuquan
     * @date  2015年12月15日
     */
    public static SSLContext getSSLContext(String password,String keyStorePath, String trustStorePath) throws Exception {  
        // 实例化密钥库  
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());  
        // 获得密钥库  
        KeyStore keyStore = getKeyStore(password, keyStorePath);  
        // 初始化密钥工厂  
        keyManagerFactory.init(keyStore, password.toCharArray());    
        // 实例化信任库  
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());  
        // 获得信任库  
        KeyStore trustStore = getKeyStore(password, trustStorePath);  
        // 初始化信任库  
        trustManagerFactory.init(trustStore);  
        // 实例化SSL上下文  
        SSLContext ctx = SSLContext.getInstance("TLS");  
        // 初始化SSL上下文  
        ctx.init(keyManagerFactory.getKeyManagers(),  
                trustManagerFactory.getTrustManagers(), null);  
        // 获得SSLSocketFactory  
        return ctx;  
    }  
  

    /**
     * @description 
     * @param password 密码 
     * @param keyStorePath 密钥库路径 
     * @param trustStorePath  信任库路径 
     * @throws Exception 
     * @author liuquan
     * @date  2015年12月15日
     */
    public static void initHttpsURLConnection(String password,  
            String keyStorePath, String trustStorePath) throws Exception {  
        // 声明SSL上下文  
        SSLContext sslContext = null;  
        // 实例化主机名验证接口  
        HostnameVerifier hnv = new MyHostnameVerifier();  
        try {  
            sslContext = getSSLContext(password, keyStorePath, trustStorePath);  
        } catch (GeneralSecurityException e) {  
            e.printStackTrace();  
        }  
        if (sslContext != null) {  
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext  
                    .getSocketFactory());  
        }  
        HttpsURLConnection.setDefaultHostnameVerifier(hnv);  
    }  
  
    /**
     * @description 
     * @param httpsUrl 请求的地址 
     * @param xmlStr 请求的数据 
     * @author liuquan
     * @date  2015年12月15日
     */
    public static void post(String httpsUrl, String xmlStr) {  
        HttpsURLConnection urlCon = null;  
        try {  
            urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();  
            urlCon.setDoInput(true);  
            urlCon.setDoOutput(true);  
            urlCon.setRequestMethod("POST");  
            urlCon.setRequestProperty("Content-Length",  
                    String.valueOf(xmlStr.getBytes().length));  
            urlCon.setUseCaches(false); 
            //设置为gbk可以解决服务器接收时读取的数据中文乱码问题   
            urlCon.getOutputStream().write(xmlStr.getBytes("utf8"));  
            urlCon.getOutputStream().flush();  
            urlCon.getOutputStream().close();  
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    urlCon.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                System.out.println(line);  
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
   
    public static void main(String[] args) throws Exception {  
        // 密码  
        String password = "liuquan";  
        // 密钥库  
        String keyStorePath = "tomcat.keystore";  
        // 信任库  
        String trustStorePath = "tomcat.keystore";  
        // 本地起的https服务  
        String httpsUrl = "https://localhost:8443/Https/httpsPost";  
        // 传输文本  
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fruitShop><fruits><fruit><kind>萝卜</kind></fruit><fruit><kind>菠萝</kind></fruit></fruits></fruitShop>";  
        HttpsPost.initHttpsURLConnection(password, keyStorePath, trustStorePath);  
        // 发起请求  
        HttpsPost.post(httpsUrl, xmlStr);  
    }  
}  
