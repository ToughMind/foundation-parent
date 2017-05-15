package lq.cipher.base;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.encoders.UrlBase64;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Base {
	public static void main(String[] args) throws Exception {
		//Cipher();
		//DataOutputStream_CipherInputStream();
		//SealedObject(); 
		//Base64();
		//UrlBase64();
		//Hex();
		Commons_base64_Hex(null, null);
		//DigestUtils();
	}

	private static void DigestUtils() {
		String input = "MD5消息摘要";
		System.out.println("原文\t" + input);
		String md5Hex = DigestUtils.md5Hex(input);
		System.out.println("加密后\t" + md5Hex);
	}

	private static void Commons_base64_Hex(InputStream is, OutputStream os) throws IOException, DecoderException { 
		String str = "Base64 编码";
		System.out.println("原文\t" + str);
		byte[] input = str.getBytes();
		byte[] data = org.apache.commons.codec.binary.Base64.encodeBase64(input);
		System.out.println("编码后\t" + new String(data)); 
		byte[] output = org.apache.commons.codec.binary.Base64.decodeBase64(data);		
		System.out.println("解码后\t" + new String(output));		
		
		String str1 = "UrlBase64 编码";
		System.out.println("原文\t" + str1);
		byte[] input1 = str.getBytes();
		byte[] data1 = org.apache.commons.codec.binary.Base64.encodeBase64URLSafe(input1);
		System.out.println("编码后\t" + new String(data1)); 
		byte[] output1 = org.apache.commons.codec.binary.Base64.decodeBase64(data1);
		System.out.println("解码后\t" + new String(output1));
		
		String str2 = "Hex 编码";
		System.out.println("原文\t" + str2);
		byte[] input2 = str2.getBytes();
		String data2 = org.apache.commons.codec.binary.Hex.encodeHexString(input2);
		System.out.println("编码后\t" + data2); 
		byte[] output2 = org.apache.commons.codec.binary.Hex.decodeHex(data2.toCharArray());
		System.out.println("解码后\t" + new String(output));
		
		
		Base64InputStream bis = new Base64InputStream(is, false);
		DataInputStream dis = new DataInputStream(bis);
		byte[] result = new byte[input.length];
		dis.readFully(result);
		dis.close();
		System.out.println(new String(result));
		
		Base64OutputStream bos = new Base64OutputStream(os, true);
		DataOutputStream dos = new DataOutputStream(bos);
		dos.write(input);
		dos.flush();
		dos.close();		 
	}

	private static void Hex() {
		String str = "Hex 编码";
		System.out.println("原文\t" + str);
		byte[] input = str.getBytes();
		byte[] data = Hex.encode(input);
		System.out.println("编码后\t" + new String(data)); 
		byte[] output = Hex.decode(data);
		System.out.println("解码后\t" + new String(output));
	}

	private static void UrlBase64() {
		String str = "UrlBase64 编码";
		System.out.println("原文\t" + str);
		byte[] input = str.getBytes();
		byte[] data = UrlBase64.encode(input);
		System.out.println("编码后\t" + new String(data)); 
		byte[] output = UrlBase64.decode(data);
		System.out.println("解码后\t" + new String(output));
	}

	private static void Base64() {
		String str = "Base64 编码";
		System.out.println("原文\t" + str);
		byte[] input = str.getBytes();
		byte[] data = Base64.encode(input);
		System.out.println("编码后\t" + new String(data)); 
		byte[] output = Base64.decode(data);
		System.out.println("解码后\t" + new String(output));
	}

	private static void SealedObject() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, ClassNotFoundException,
			BadPaddingException {
		//用加密算法创建对象并保护其机密性
		String input = "SealedObject待加密的数据";
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		SecretKey sk = kg.generateKey();
		//实例化用于加密的Cipher对象
		Cipher c1 = Cipher.getInstance(sk.getAlgorithm());
		c1.init(Cipher.ENCRYPT_MODE, sk);
		SealedObject so = new SealedObject(input, c1);
		
		//实例化用于解密的Cipher对象
		Cipher c2 = Cipher.getInstance(sk.getAlgorithm());
		c2.init(Cipher.DECRYPT_MODE, sk);
		String output = (String)so.getObject(c2);
		System.out.println(output);
	}

	private static void DataOutputStream_CipherInputStream() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, FileNotFoundException,
			IOException {
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		SecretKey sk = kg.generateKey();
		Cipher c = Cipher.getInstance("DES");
		//完成加密 并写到文件中
		c.init(Cipher.ENCRYPT_MODE, sk);
		String input = "待加密的原始数据";
		CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(new File("secret")), c);
		DataOutputStream dos = new DataOutputStream(cos);
		dos.writeUTF(input);
		dos.flush();
		dos.close();
		
		c.init(Cipher.DECRYPT_MODE, sk);
		CipherInputStream cis = new CipherInputStream(new FileInputStream(new File("secret")), c);
		//使用DataInputStream对象包装CipherInputStream
		DataInputStream dis = new DataInputStream(cis);
		//这里就可以获得解密后的数据了
		String output = dis.readUTF();	
		System.out.println(output);
		dis.close();
	}

	private static void Cipher() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		SecretKey sk = kg.generateKey();
		Cipher c = Cipher.getInstance("DES");
		
		//以下代码是示例 不一定能运行
		
		//包装秘密密钥
		c.init(Cipher.WRAP_MODE, sk);
		byte[] k = c.wrap(sk);
		System.out.println(new String(k));
		
		//解包
		c.init(Cipher.UNWRAP_MODE, sk);
		Key key = c.unwrap(k, "DES", Cipher.SECRET_KEY);
		System.out.println(key);
		
		//加密
		c.init(Cipher.ENCRYPT_MODE, sk);
		byte[] input = c.doFinal("DES加密原始数据".getBytes());
		System.out.println(new String(input));
		
		//解密
		c.init(Cipher.DECRYPT_MODE, sk);
		byte[] output = c.doFinal(input);
		System.out.println(new String(output));
	}
}
