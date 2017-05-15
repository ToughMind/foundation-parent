package lq.cipher.base.spec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Simple {
	public static void main(String[] args) throws Exception {
		// X509EncodedKeySpec();
		//PKCS8EncodedKeySpec();
		//SecretKeySpec();
		//DESKeySpec();
		
	}

	private static void DESKeySpec() throws NoSuchAlgorithmException,
			InvalidKeyException, InvalidKeySpecException {
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		SecretKey sk = kg.generateKey();
		byte[] key = sk.getEncoded();
		DESKeySpec des = new DESKeySpec(key);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk1 = skf.generateSecret(des);
		System.out.println(key);
		System.out.println(sk1.getFormat());
		System.out.println(sk1.getEncoded());
		System.out.println(sk1);
	}

	private static void SecretKeySpec() throws NoSuchAlgorithmException {
		//构建秘密密钥规范，可根据一个字节数组构造一个SecretKey，而无须通过一个基于provider的SecretKeyFactory
		KeyGenerator kg = KeyGenerator.getInstance("RC2");
		SecretKey sk = kg.generateKey();
		byte[] key = sk.getEncoded();
		SecretKey sk1 = new SecretKeySpec(key, "RC2");
		System.out.println(key);
		System.out.println(sk1.getFormat());
		System.out.println(sk1.getEncoded());
		System.out.println(sk1);
	}

	private static void PKCS8EncodedKeySpec() throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		// 转换私钥编码密钥
		KeyPairGenerator kg = KeyPairGenerator.getInstance("DSA");
		kg.initialize(1024);
		KeyPair kp = kg.generateKeyPair();
		byte[] privateKeyBytes = kp.getPrivate().getEncoded();
		PKCS8EncodedKeySpec pksc8 = new PKCS8EncodedKeySpec(privateKeyBytes);
		KeyFactory kf = KeyFactory.getInstance("DSA");
		PrivateKey pk = kf.generatePrivate(pksc8);
		System.out.println(privateKeyBytes);
		System.out.println(pksc8.getFormat());
		System.out.println(pksc8.getEncoded());
		System.out.println(pk);
	}

	private static void X509EncodedKeySpec() throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		// 转换公钥编码密钥
		KeyPairGenerator kg = KeyPairGenerator.getInstance("DSA");
		kg.initialize(1024);
		KeyPair kp = kg.generateKeyPair();
		byte[] publicKeyBytes = kp.getPublic().getEncoded();
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory kf = KeyFactory.getInstance("DSA");
		PublicKey pk = kf.generatePublic(x509);
		System.out.println(publicKeyBytes);
		System.out.println(x509.getFormat());
		System.out.println(x509.getEncoded());
		System.out.println(pk);
	}
}
