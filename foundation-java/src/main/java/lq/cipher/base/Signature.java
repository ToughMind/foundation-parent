package lq.cipher.base;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Signature {
	public static void main(String[] args) throws Exception {
		MD5withRSA();
		//DSA、ECDSA代码同上
		
	}

	private static void MD5withRSA() throws NoSuchAlgorithmException,
			InvalidKeySpecException, InvalidKeyException, SignatureException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(512);
		KeyPair kp = kpg.generateKeyPair();
		//生成公钥和私钥
		RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
		System.out.println("公钥\t" + Base64.encodeBase64String(publicKey.getEncoded()));
		System.out.println("私钥\t" + Base64.encodeBase64String(privateKey.getEncoded()));
		
		String input = "RSA数字签名";
		System.out.println("原始数据\t" + input);
		byte[] data = input.getBytes();
		
		//通过私钥材料得到私钥
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey priKey = kf.generatePrivate(pkcs8);
		java.security.Signature s = java.security.Signature.getInstance("MD5withRSA");
		s.initSign(priKey);
		s.update(data);
		byte[] sign = s.sign();
		System.out.println("16进制编码的签名\t" + Hex.encodeHexString(sign));
		
		//通过公钥材料得到公钥
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKey.getEncoded());
		KeyFactory kf2 = KeyFactory.getInstance("RSA");
		PublicKey pubKey = kf2.generatePublic(x509);
		java.security.Signature s2 = java.security.Signature.getInstance("MD5withRSA");
		s2.initVerify(pubKey);
		s2.update(data);
		boolean status = s2.verify(sign);
		System.out.println("验证结果\t" + status);
	}
}
