package lq.cipher.base.cert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.cert.*;

public class Simple {
	public static void main(String[] args) throws Exception {
		// CertificateFactory();
		// X509Certificate();
		// CRL();
		// X509CRLEntry();
		// CertPath();
	}

	private static void CertPath() throws CertificateException,
			FileNotFoundException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		// 获得证书输入流
		FileInputStream fis = new FileInputStream("src/.keystore");
		CertPath cp = cf.generateCertPath(fis);
		fis.close();
	}

	private static void X509CRLEntry() throws CertificateException,
			FileNotFoundException, CRLException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		// 获得证书输入流
		FileInputStream fis = new FileInputStream("src/.keystore");
		// 获得证书
		X509Certificate x509 = (X509Certificate) cf.generateCertificate(fis);
		// 获得证书撤销列表
		X509CRL x509crl = (X509CRL) cf.generateCRL(fis);
		// 获得证书撤销列表实体
		X509CRLEntry x509CrlEntry = x509crl.getRevokedCertificate(x509);
		fis.close();
	}

	private static void CRL() throws CertificateException,
			FileNotFoundException, CRLException, IOException {
		// 获得证书撤销列表
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		// 获得证书输入流
		FileInputStream fis = new FileInputStream("src/.keystore");
		// 获得证书
		CRL crl = cf.generateCRL(fis);
		System.out.println(crl);
		fis.close();
	}

	private static void X509Certificate() throws FileNotFoundException,
			KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException {
		// 通过密钥库获得X.509证书
		FileInputStream fis = new FileInputStream("src/.keystore");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(fis, "password".toCharArray());
		fis.close();
		X509Certificate x509 = (X509Certificate) ks.getCertificate("alias");
		// 通过证书标明的签名算法构建Signature对象
		Signature s = Signature.getInstance(x509.getSigAlgName());
	}

	private static void CertificateFactory() throws CertificateException,
			FileNotFoundException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		// 获得证书输入流
		FileInputStream fis = new FileInputStream("src/.keystore");
		// 获得证书
		Certificate c = cf.generateCertificate(fis);
		System.out.println(c);
		fis.close();
	}
}
