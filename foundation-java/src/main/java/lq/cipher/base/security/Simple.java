package lq.cipher.base.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @description 介绍关于java.security包中的一些类 。
 * 
 * @author liuquan
 * @date 2016年2月29日
 */
public class Simple {
	public static void main(String[] args) throws Exception {
		// security();
		// MessageDigest();
		// DigestInputStream();
		// DigestOutputStream();
		// AlgorithmParameters();
		// AlgorithmParameterGenerator();
		// KeyPairGenerator_KeyFactory();
		// SecureRandom();
		// Signature();
		// SignedObject();
		// Timestamp_CodeSigner();
		KeyStore();
	}

	private static void KeyStore() throws FileNotFoundException,
			KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException {
		// 获得密钥库对象
		FileInputStream is = new FileInputStream("D:\\keystore");
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(is, "password".toCharArray());  
		is.close();
	}

	private static void Timestamp_CodeSigner() throws CertificateException,
			FileNotFoundException {
		// 获得数字时间戳
		CertificateFactory cf = CertificateFactory.getInstance("X509");
		// 生成CertPath对象
		CertPath cp = cf.generateCertPath(new FileInputStream(
				"src/banknew1024.cer"));
		Timestamp t = new Timestamp(new Date(), cp);
		CodeSigner cs = new CodeSigner(cp, t);
		boolean status = cs.equals(new CodeSigner(cp, t));
		System.out.println(status);
	}

	private static void SignedObject() throws NoSuchAlgorithmException,
			IOException, InvalidKeyException, SignatureException {
		byte[] data = "待做数字签名的原始信息".getBytes();
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
		kpg.initialize(1024);
		KeyPair keyPair = kpg.generateKeyPair();
		System.out.println(kpg.getAlgorithm());
		Signature sa = Signature.getInstance(kpg.getAlgorithm());
		SignedObject so = new SignedObject(data, keyPair.getPrivate(), sa);
		// 另一种方式获得签名
		byte[] sign = so.getSignature();
		// 验证方式也不一样
		boolean status = so.verify(keyPair.getPublic(), sa);
		System.out.println("status=" + status);
	}

	private static void Signature() throws NoSuchAlgorithmException,
			InvalidKeyException, SignatureException {
		// 使用私钥完成数字签名
		byte[] data = "待做数字签名的原始信息".getBytes();
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
		kpg.initialize(1024);
		KeyPair keyPair = kpg.generateKeyPair();
		System.out.println(kpg.getAlgorithm());
		Signature sa = Signature.getInstance(kpg.getAlgorithm());
		sa.initSign(keyPair.getPrivate());
		byte[] sign = sa.sign();
		// 私钥完成签名，公钥用于验证
		sa.initVerify(keyPair.getPublic());
		sa.update(data);
		boolean status = sa.verify(sign);
		System.out.println("states=" + status);
	}

	private static void SecureRandom() throws NoSuchAlgorithmException {
		// 构建安全随机数及秘密密钥
		SecureRandom sr = new SecureRandom();
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		kg.init(sr);
		SecretKey secreKey = kg.generateKey();
	}

	private static void KeyPairGenerator_KeyFactory()
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 如生成一个DSA算法密钥对,构建密钥对与还原密钥
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
		kpg.initialize(1024);
		KeyPair keypair = kpg.generateKeyPair();
		byte[] keyBytes = keypair.getPrivate().getEncoded();
		// 由私钥字节数组获得密钥规范
		PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(keyBytes);
		// 实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		Key privateKey = keyFactory.generatePrivate(pkcs);
		System.out.println(privateKey);
	}

	private static void AlgorithmParameterGenerator()
			throws NoSuchAlgorithmException, IOException {
		// 通过算法参数生成器获得DES算法相应的算法参数
		AlgorithmParameterGenerator apg = AlgorithmParameterGenerator
				.getInstance("DES");
		apg.init(56);
		AlgorithmParameters ap = apg.generateParameters();
		byte[] b = ap.getEncoded();
		System.out.println(new BigInteger(b).toString());
	}

	private static void AlgorithmParameters() throws NoSuchAlgorithmException,
			IOException {
		AlgorithmParameters ap = AlgorithmParameters.getInstance("DES");
		ap.init(new BigInteger("19050619766489163472469").toByteArray());
		// 获得参数字节数组
		byte[] b = ap.getEncoded();
		System.out.println(new BigInteger(b).toString());
	}

	private static void DigestOutputStream() throws NoSuchAlgorithmException,
			IOException {
		// 消息摘要输出流
		byte[] input = "md5算法实现摘要".getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		DigestOutputStream dos = new DigestOutputStream(
				new ByteArrayOutputStream(), md5);
		dos.write(input, 0, input.length);
		byte[] output = dos.getMessageDigest().digest();
		dos.flush();
		dos.close();
		System.out.println(new String(output));
		System.out.println(md5.toString());
	}

	private static void DigestInputStream() throws NoSuchAlgorithmException,
			IOException {
		// 消息摘要输入流
		byte[] input = "md5算法实现摘要".getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(
				input), md5);
		dis.read(input, 0, input.length);
		byte[] output = dis.getMessageDigest().digest();
		dis.close();
		System.out.println(new String(output));
		System.out.println(md5.getAlgorithm());
		System.out.println(md5.getProvider());
		System.out.println(md5.toString());
	}

	private static void MessageDigest() throws NoSuchAlgorithmException {
		// 简单摘要处理示例
		byte[] input = "sha算法实现摘要".getBytes();
		MessageDigest sha = MessageDigest.getInstance("SHA");
		// 更新摘要信息
		sha.update(input);
		byte[] output = sha.digest();
		System.out.println(new String(output));
		System.out.println(sha.getAlgorithm());
		System.out.println(sha.getDigestLength());
		System.out.println(sha.getProvider());
		System.out.println(sha.toString());
	}

	private static void security() {
		// 遍历目前环境的安全提供者 在%JAVA_HOME/jre/lib/security/java.security文件中有相应配置
		for (Provider p : Security.getProviders()) {
			// 打印提供者的信息
			System.out.println(p);
			// 遍历提供者的Set实体
			for (Map.Entry<Object, Object> entry : p.entrySet()) {
				System.out.println("\t" + entry.getKey());
			}
		}
	}
}
