package lq.cipher.base.ssl;

import javax.net.ssl.*;
import javax.security.cert.Certificate;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;

public class Simple {
	public static void main(String[] args) throws Exception {
		//FileInputStream();
		//TrustManagerFactory();
		//SSLSocketFactory();
		//HttpsURLConnection();
		//SSLSocket("193.168.0.1", 8080);
		//SSLServerSocket();
		SSLSocket();
		
	}
	
	/**
	 * @description 
	 * @throws IOException  SSLSocket获取数字证书 这里是获取苹果的数字证书 
	 * @date  2016年3月14日
	 */
	private static void SSLSocket() throws IOException, UnknownHostException,
			SSLPeerUnverifiedException, FileNotFoundException,
			CertificateEncodingException {
		SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket)f.createSocket("itunes.apple.com", 443);
		
		//握手 会话开始
		socket.startHandshake();
		SSLSession session = socket.getSession();
		socket.close();
		
		//获取服务器证书链
		java.security.cert.Certificate[] certs = session.getPeerCertificates();
		for(java.security.cert.Certificate cert: certs){
			FileOutputStream fos = new FileOutputStream(System.currentTimeMillis() + "-itunes.cer");
			DataOutputStream dos = new DataOutputStream(fos);
			dos.write(cert.getEncoded());
			dos.flush();
			dos.close();
		}
	}

	private static void SSLServerSocket() throws IOException {
		SSLServerSocketFactory sf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket serverSocket = (SSLServerSocket) sf.createServerSocket(80);
		//服务器套接字进入阻塞状态，等待来自客户端的连接
		SSLSocket socket = (SSLSocket) serverSocket.accept();
		socket.close();
	}

	private static Certificate[] SSLSocket(String name, int port) throws IOException,
			SSLPeerUnverifiedException {
		SSLSocketFactory ssl = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket) ssl.createSocket(name, port);
		//SSL握手
		socket.startHandshake();
		SSLSession session = socket.getSession();
		socket.close();
		return session.getPeerCertificateChain();
	}

	private static void HttpsURLConnection() throws MalformedURLException,
			IOException, NoSuchAlgorithmException, FileNotFoundException,
			KeyStoreException, CertificateException, UnrecoverableKeyException,
			KeyManagementException {
		URL url = new URL("https://www.sun.com");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//打开输入模式
		conn.setDoInput(true);
		//打开输出模式
		conn.setDoOutput(true);
		conn.setSSLSocketFactory(SSLSocketFactory());
		InputStream is = conn.getInputStream();
		//若正常打开HTTPS，将获得一个有效值（即contentLength的值不为-1）
		int length = conn.getContentLength();
		is.close();
	}

	private static SSLSocketFactory SSLSocketFactory() throws NoSuchAlgorithmException,
			FileNotFoundException, KeyStoreException, IOException,
			CertificateException, UnrecoverableKeyException,
			KeyManagementException {
		//初始化密钥库
		KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
		FileInputStream fis = new FileInputStream("src/.keystore");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(fis, "liuquan".toCharArray());
		fis.close();
		kf.init(ks, "123456".toCharArray());
		//初始化信任库
		TrustManagerFactory tf = TrustManagerFactory.getInstance("SunX509");
		tf.init(ks);
		//初始化SSL上下文
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(kf.getKeyManagers(), tf.getTrustManagers(), null);
		SSLSocketFactory sf = ctx.getSocketFactory();
		return sf;
	}

	private static void TrustManagerFactory() throws NoSuchAlgorithmException,
			FileNotFoundException, KeyStoreException, IOException,
			CertificateException {
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		FileInputStream fis = new FileInputStream("src/.keystore");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(fis, "liuquan".toCharArray());
		fis.close();
		tmf.init(ks);
		
		//密钥库和信任库可同通过Ststem设定
		System.setProperty("javax.net.ssl.keyStore", "src/.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "liuquan");
		System.setProperty("javax.net.ssl.trustStore", "src/.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "liuquan");
	}

	private static void FileInputStream() throws NoSuchAlgorithmException,
			FileNotFoundException, KeyStoreException, IOException,
			CertificateException, UnrecoverableKeyException {
		KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
		FileInputStream fis = new FileInputStream("src/.keystore");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(fis, "liuquan".toCharArray());
		fis.close();
		kf.init(ks, "123456".toCharArray());
	}
}
