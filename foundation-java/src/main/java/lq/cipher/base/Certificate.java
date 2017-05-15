package lq.cipher.base;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.*;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


public class Certificate {
	public static void main(String[] args) throws Exception {
		//JKS();
		
		PEM();
		
	}

	private static void PEM() throws FileNotFoundException, IOException,
			PEMException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, SignatureException {
		//PEM文件提取密钥对
		Security.addProvider(new BouncyCastleProvider());
		String password = "liuquan";
		String alias = "www.lq.com";
		String pemFilePath = "src/base/ca.key.pem";

		
		//先获取pem对象，利用JcaPEMKeyConverter转化成密钥对
		PEMParser parser = new PEMParser(new FileReader(pemFilePath));
		Object obj =  parser.readObject();
		parser.close();
		JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
		//获得密钥对
		KeyPair kp = null;
		//如果PEM文件本身经过加密，需要使用相应密码通过PEMDecryptorProvider对其解密
		PEMDecryptorProvider decpro = new JcePEMDecryptorProviderBuilder().build(password.toCharArray());
		if(obj instanceof PEMEncryptedKeyPair){
			kp = converter.getKeyPair(((PEMEncryptedKeyPair)obj).decryptKeyPair(decpro));
		}
		else{
			kp = converter.getKeyPair((PEMKeyPair)obj);
		}
		PublicKey publickey = kp.getPublic();
		PrivateKey privatekey = kp.getPrivate();
		
		
		System.out.println("--------------私钥加密，公钥解密");
		String input = "数字证书";
		System.out.println("原始数据\t" + input);
		byte[] data = input.getBytes();
		Cipher cipher = Cipher.getInstance(publickey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publickey);
		byte[] encrypt = cipher.doFinal(data);
		System.out.println("加密后\t" + new String(encrypt));
		cipher.init(Cipher.DECRYPT_MODE, privatekey);
		byte[] decrypt = cipher.doFinal(encrypt);
		System.out.println("解密后\t" + new String(decrypt));
		
		System.out.println("--------------签名验证");
		input = "签名";
		System.out.println("原始数据\t" + input);
		data = input.getBytes();
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(privatekey);
		signature.update(data);
		byte[] sign = signature.sign();
		System.out.println("签名\t" + Hex.encodeHexString(sign)); 
		//用公钥校验签名
		signature.initVerify(publickey);
		signature.update(data);
		boolean status = signature.verify(sign); 		
		System.out.println("验证结果\t" + status);
	}

	private static void JKS() throws Exception, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, KeyStoreException,
			FileNotFoundException, IOException, CertificateException,
			UnrecoverableKeyException, SignatureException {
		String password = "liuquan";
		String alias ="www.lq.com";
		String certificatePath = "src/base/lq.cer";
		String keyStorePath = "src/base/lq.keystore";
		
		System.out.println("--------------公钥加密，私钥解密");
		String input1 = "数字证书";
		System.out.println("原始数据\t" + input1);
		byte[] data1 = input1.getBytes();
		//由证书取得公钥并对数据加密 
		PublicKey publickey1 = getPublicKey(certificatePath);
		Cipher cipher1 = Cipher.getInstance(publickey1.getAlgorithm());
		System.out.println("加密算法\t" + publickey1.getAlgorithm());
		cipher1.init(Cipher.ENCRYPT_MODE, publickey1);
		byte[] encrypt = cipher1.doFinal(data1);
		System.out.println("加密后\t" + new String(encrypt));
		//由证书得到私钥并解密
		PrivateKey privatekey1 = getPrivateKey(keyStorePath, password, alias);
		Cipher cipher2 = Cipher.getInstance(privatekey1.getAlgorithm());
		System.out.println("解密算法\t" + privatekey1.getAlgorithm());
		cipher2.init(Cipher.DECRYPT_MODE, privatekey1);
		byte[] decrypt = cipher2.doFinal(encrypt);
		System.out.println("解密后\t" + new String(decrypt));
		
		
		System.out.println("--------------私钥加密，公钥解密");
		String input2 = "数字签名";
		System.out.println("原始数据\t" + input2);
		byte[] data2 = input2.getBytes();
		//由证书取得私钥并对数据加密 
		PrivateKey privatekey2 = getPrivateKey(keyStorePath, password, alias);
		Cipher cipher3 = Cipher.getInstance(privatekey2.getAlgorithm());
		System.out.println("加密算法\t" + privatekey2.getAlgorithm());
		cipher3.init(Cipher.ENCRYPT_MODE, privatekey2);
		byte[] encrypt2 = cipher3.doFinal(data2);
		System.out.println("加密后\t" + new String(encrypt2));
		//由证书得到公钥并解密
		PublicKey publickey2 = getPublicKey(certificatePath);
		Cipher cipher4 = Cipher.getInstance(publickey2.getAlgorithm());
		System.out.println("解密算法\t" + publickey2.getAlgorithm());
		cipher4.init(Cipher.DECRYPT_MODE, publickey2);
		byte[] decrypt2 = cipher4.doFinal(encrypt2);
		System.out.println("解密后\t" + new String(decrypt2)); 
		
		
		System.out.println("--------------签名验证");
		String input3 = "签名";
		System.out.println("原始数据\t" + input3);
		byte[] data3 = input3.getBytes();
		//先得到密钥库 再获得证书、私钥  再产生签名  这里得到证书的方式是通过密钥库
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream in = new FileInputStream(keyStorePath);
		ks.load(in, password.toCharArray());
		in.close(); 
		X509Certificate cer = (X509Certificate) ks.getCertificate(alias);
		//由证书构建签名
		Signature signature = Signature.getInstance(cer.getSigAlgName());
		PrivateKey privatekey3 =(PrivateKey) ks.getKey(alias, password.toCharArray());
		signature.initSign(privatekey3);
		signature.update(data3);
		byte[] sign = signature.sign();
		System.out.println("签名\t" + Hex.encodeHexString(sign));
		
		//验证签名 先得到证书  这里得到证书的方式是通过证书路径
		CertificateFactory cf = CertificateFactory.getInstance("X.509"); //证书类型
		FileInputStream in2 = new FileInputStream(certificatePath);
		X509Certificate cer2 = (X509Certificate) cf.generateCertificate(in2);
		in2.close();  
		//由证书构建签名 这里构建签名算法用到的是证书，而不是公钥，虽然内部其实还是用的公钥
		Signature signature2 = Signature.getInstance(cer2.getSigAlgName());
		signature2.initVerify(cer2);
		signature2.update(data3);
		boolean status = signature2.verify(sign);
		System.out.println("验证结果\t" + status);
	}
	
	public static PrivateKey getPrivateKey(String keyStorePath, String password,String alias) throws Exception{
		//先获得密钥库，再通过密钥库得到私钥
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream in = new FileInputStream(keyStorePath);
		ks.load(in, password.toCharArray());
		in.close();
		return (PrivateKey) ks.getKey(alias, password.toCharArray());
	}
	
	public static PublicKey getPublicKey(String certificatePath) throws Exception{
		//取得证书再得到公钥
		CertificateFactory cf = CertificateFactory.getInstance("X.509"); //证书类型
		FileInputStream in = new FileInputStream(certificatePath);
		java.security.cert.Certificate cer = cf.generateCertificate(in);
		in.close(); 
		return cer.getPublicKey();
	}
	 
}
