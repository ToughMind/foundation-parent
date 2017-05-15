package lq.cipher.base;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class SymmetricEncryption {
	public static void main(String[] args) throws Exception {
		//DES();
		//DESede();
		//AES();	
		PBEWithMD5AndDES();
	}

	private static void PBEWithMD5AndDES() throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		//这里打算使用java7的PBEWithMD5AndDES,用随机数作为“盐” 盐的初始化，注意不是密钥
		SecureRandom random = new SecureRandom();
		byte[] salt = random.generateSeed(8);
		System.out.println("盐\t" + Base64.encodeBase64String(salt));
		//设置口令
		String pwd = "liuquan";
		System.out.println("口令\t" + pwd); 
		 
		//现在要使用口令、盐   先实例化PBE材料  注意第二个参数是字符数组，不是字节数组
		PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES"); 
		SecretKey sk_use = keyFactory.generateSecret(keySpec);
		//实例化PBE参数材料		
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
		System.out.println("密钥\t二进制形式：" + sk_use.getEncoded() + "，字符串形式：" + new String(sk_use.getEncoded()) + "，base64编码后:" + Base64.encodeBase64String(sk_use.getEncoded()));
		
		
		//有了密钥就可以加密 
		String inputStr = "使用PBEWithMD5AndDES加密数据";
		System.out.println("原始数据\t" + inputStr);
		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		cipher.init(Cipher.ENCRYPT_MODE, sk_use, paramSpec);
		byte[] inputData = cipher.doFinal(inputStr.getBytes());
		System.out.println("加密后数据\t二进制形式" + inputData + "，字符串形式：" + new String(inputData) + "，base64编码后:" + Base64.encodeBase64String(inputData));
		
		//然后解密
		Cipher cipher2 = Cipher.getInstance("PBEWithMD5AndDES");
		cipher2.init(Cipher.DECRYPT_MODE, sk_use, paramSpec);
		byte[] outputData = cipher2.doFinal(inputData);
		System.out.println("解密后数据\t" + new String(outputData));
	}

	private static void AES() throws NoSuchAlgorithmException,
			NoSuchProviderException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		//先构建密钥  这里打算使用bouncy的64位加密
		KeyGenerator kg = KeyGenerator.getInstance("AES", "BC");
		kg.init(256);
		SecretKey sk = kg.generateKey(); 
		// 密钥的二进制编码形式,便于存储在文件中或以数据流的形式在网络传输
		byte[] key = sk.getEncoded();
		System.out.println("密钥\t二进制形式：" + key + "，字符串形式：" + new String(key) + "，base64编码后:" + Base64.encodeBase64String(key));
		
		//无需考虑密钥材料的实现类
		SecretKey sk_use = new SecretKeySpec(sk.getEncoded(), "AES");
		
		//有了密钥就可以加密 
		String inputStr = "使用AES加密数据";
		System.out.println("原始数据\t" + inputStr);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
		cipher.init(Cipher.ENCRYPT_MODE, sk_use);
		byte[] inputData = cipher.doFinal(inputStr.getBytes());
		System.out.println("加密后数据\t二进制形式" + inputData + "，字符串形式：" + new String(inputData) + "，base64编码后:" + Base64.encodeBase64String(inputData));
		
		//然后解密
		Cipher cipher2 = Cipher.getInstance("AES/ECB/PKCS7Padding");
		cipher2.init(Cipher.DECRYPT_MODE, sk_use);
		byte[] outputData = cipher2.doFinal(inputData);
		System.out.println("解密后数据\t" + new String(outputData));
	}

	private static void DESede() throws NoSuchAlgorithmException,
			NoSuchProviderException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		//先构建密钥  这里打算使用bouncy的DESede192位加密
		KeyGenerator kg = KeyGenerator.getInstance("DESede", "BC");
		kg.init(192);
		SecretKey sk = kg.generateKey(); 
		// 密钥的二进制编码形式,便于存储在文件中或以数据流的形式在网络传输
		byte[] key = sk.getEncoded();
		System.out.println("密钥\t二进制形式：" + key + "，字符串形式：" + new String(key) + "，base64编码后:" + Base64.encodeBase64String(key));
		
		//现在要使用密钥 先实例化密钥材料  再通过密钥的二进制形式还原成密钥对象
		DESedeKeySpec dks = new DESedeKeySpec(sk.getEncoded());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
		SecretKey sk_use = skf.generateSecret(dks);
		
		//有了密钥就可以加密 
		String inputStr = "使用DESede加密数据";
		System.out.println("原始数据\t" + inputStr);
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS7Padding");
		cipher.init(Cipher.ENCRYPT_MODE, sk_use);
		byte[] inputData = cipher.doFinal(inputStr.getBytes());
		System.out.println("加密后数据\t二进制形式" + inputData + "，字符串形式：" + new String(inputData) + "，base64编码后:" + Base64.encodeBase64String(inputData));
		
		//然后解密
		Cipher cipher2 = Cipher.getInstance("DESede/ECB/PKCS7Padding");
		cipher2.init(Cipher.DECRYPT_MODE, sk_use);
		byte[] outputData = cipher2.doFinal(inputData);
		System.out.println("解密后数据\t" + new String(outputData));
	}

	private static void DES() throws NoSuchAlgorithmException,
			NoSuchProviderException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		//先构建密钥  这里打算使用bouncy的64位加密
		KeyGenerator kg = KeyGenerator.getInstance("DES", "BC");
		kg.init(64);
		SecretKey sk = kg.generateKey(); 
		// 密钥的二进制编码形式,便于存储在文件中或以数据流的形式在网络传输
		byte[] key = sk.getEncoded();
		System.out.println("密钥\t二进制形式：" + key + "，字符串形式：" + new String(key) + "，base64编码后:" + Base64.encodeBase64String(key));
		//现在要使用密钥 先实例化密钥材料  再通过密钥的二进制形式还原成密钥对象
		DESKeySpec dks = new DESKeySpec(sk.getEncoded());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk_use = skf.generateSecret(dks);
		
		//有了密钥就可以加密 
		String inputStr = "使用DES加密数据";
		System.out.println("原始数据\t" + inputStr);
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, sk_use);
		byte[] inputData = cipher.doFinal(inputStr.getBytes());
		System.out.println("加密后数据\t二进制形式" + inputData + "，字符串形式：" + new String(inputData) + "，base64编码后:" + Base64.encodeBase64String(inputData));
		
		//然后解密
		Cipher cipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher2.init(Cipher.DECRYPT_MODE, sk_use);
		byte[] outputData = cipher2.doFinal(inputData);
		System.out.println("解密后数据\t" + new String(outputData));
	}
}
