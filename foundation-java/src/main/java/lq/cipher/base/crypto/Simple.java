package lq.cipher.base.crypto;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Simple {
	public static void main(String[] args) throws Exception {
		//Mac(); 
		//KeyAgreement();
		//SecretKeyFactory();
		
		
	}

	private static void SecretKeyFactory() throws NoSuchAlgorithmException,
			InvalidKeyException, InvalidKeySpecException {		
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		SecretKey sk = kg.generateKey();
		//获取秘密密钥的编码密钥字节数组
		byte[] key = sk.getEncoded();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk1 = skf.generateSecret(dks);
		System.out.println(sk.toString());
		System.out.println(sk1);
		System.out.println(sk == sk1);
		System.out.println(sk.equals(sk1));
	}

	private static void KeyAgreement() throws NoSuchAlgorithmException,
			InvalidKeyException {
		//DH算法密钥对生成
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
		KeyPair kp1 = kpg.generateKeyPair();
		KeyPair kp2 = kpg.generateKeyPair();
		KeyAgreement ka = KeyAgreement.getInstance(kpg.getAlgorithm());
		ka.init(kp2.getPrivate());
		//执行计划
		ka.doPhase(kp1.getPublic(), true);
		//生成SecreKey对象
		SecretKey sk = ka.generateSecret("DES");
		System.out.println(sk.getAlgorithm());
	}

	private static void Mac() throws NoSuchAlgorithmException,
			InvalidKeyException {
		byte[] input = "MAC待做安全消息摘要的原始信息".getBytes(); 
		KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
		SecretKey sk = kg.generateKey();
		Mac mac = Mac.getInstance(sk.getAlgorithm());
		mac.init(sk);
		byte[] output = mac.doFinal(input);
		System.out.println(new String(output));
	}
}
