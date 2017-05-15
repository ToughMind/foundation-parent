package lq.cipher.base;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.util.zip.CRC32;

/**
 * @description 主要是通过sun、bouncy、commons三种方式构建摘要，验证摘要其实就是把两次构建的摘要比较是否相同，MD和SHA直接形成摘要，用密钥；MAC方式要密钥再生成摘要 
 * 
 * @author liuquan
 * @date  2016年3月2日
 */
public class Digest {
	public static void main(String[] args) throws Exception {
		//java_MD(); 
		//bouncy_MD();
		//commons_MD();  //SHA算法与上同理，java7、commons支持SHA-1、256、384、512四种，Bouncy支持SHA-224
		//java_MAC();	//Bouncy的步骤一样，只需将bouncy配置引进，就可以用HmacMD2,HmacMD4,HmacSHA224了
		//CRC32();
		TestFileMD5();
		
	}

	private static void TestFileMD5() throws FileNotFoundException,
			NoSuchAlgorithmException, IOException {
		//两种方式文件校验  效率差不多校验文件的MD5值
		String path = "E:\\镜像\\ubuntu\\ubuntu-14.04.3-desktop-amd64.iso";
		FileInputStream fis = new FileInputStream(new File(path));
		DigestInputStream dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"));
		byte[] buffer = new byte[1024];
		int read = dis.read(buffer, 0, 1024);
		while(read > -1){
			read = dis.read(buffer, 0, 1024);
		}
		dis.close();
		MessageDigest md = dis.getMessageDigest();
		byte[] b = md.digest();
		String hex = Hex.encodeHexString(b);
		System.out.println(hex);
		
		//commons
		String hex2 = DigestUtils.md5Hex(new FileInputStream(new File(path)));
		System.out.println(hex2);
	}

	private static void CRC32() {
		String str = "测试CRC-32";
		CRC32 crc = new CRC32();
		crc.update(str.getBytes());
		String hex = Long.toHexString(crc.getValue()); 
		System.out.println("原文\t" + str);
		System.out.println("CRC-32\t" + hex);
	}

	private static void java_MAC() throws NoSuchAlgorithmException,
			InvalidKeyException {
		String str = "HmacMD5消息摘要原始数据";
		KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
		SecretKey sk = kg.generateKey();
		//构造密钥
		byte[] key = sk.getEncoded();
		//还原密钥
		SecretKey sk2 = new SecretKeySpec(key, "HmacMD5");
		Mac mac = Mac.getInstance(sk2.getAlgorithm());
		mac.init(sk2);
		byte[] data = mac.doFinal(str.getBytes());
		System.out.println(new String(data));
	}

	private static void commons_MD() {
		byte[] out1 = DigestUtils.md5("MD5原始数据 ".getBytes());
		System.out.println(new String(out1));
	}

	private static void bouncy_MD() throws NoSuchAlgorithmException {
		Security.addProvider(new BouncyCastleProvider());
		MessageDigest md = MessageDigest.getInstance("MD4");
		byte[] out1 = md.digest("MD4原始数据 ".getBytes());
		System.out.println(new String(out1));
	}

	private static void java_MD() throws NoSuchAlgorithmException {
		//生成摘要
		MessageDigest md = MessageDigest.getInstance("MD2");
		byte[] out1 = md.digest("MD2原始数据 ".getBytes());
		System.out.println(new String(out1));
		MessageDigest md2 = MessageDigest.getInstance("MD5");
		byte[] out2 = md2.digest("MD5原始数据 ".getBytes());
		System.out.println(new String(out2));
	}
}
