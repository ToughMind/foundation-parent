package lq.common.utils.cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @description 用AES算法进行加密和解密 
 * 
 * @author liuquan
 * @date  2016年3月3日
 */
public class AESUtils {

	// 密钥算法
	public static final String KEY_ALGORITHM = "AES";

	// 加密/解密算法 / 工作模式 / 填充方式 Java 6支持PKCS5Padding填充方式  Bouncy Castle支持PKCS7Padding填充方式
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * @description 转换密钥 
	 * @date  2016年3月3日
	 */
	private static Key toKey(byte[] key) throws Exception {
		// 实例化AES密钥材料
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	public static void main(String[] args) {
		try {
			System.out.println(initKeyString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @description 生成密钥 返回字节数组
	 * @date  2016年3月3日
	 */
	public static byte[] initKey() throws Exception {
		// 实例化
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// AES 要求密钥长度为 128位、192位或 256位
		kg.init(256);
		// 生成秘密密钥
		SecretKey secretKey = kg.generateKey();
		// 获得密钥的二进制编码形式
		return secretKey.getEncoded();
	}
	
	/**
	 * @description 将密钥用base64编码返回其字符串形式
	 * @date  2016年3月3日
	 */
	public static String initKeyString() throws Exception{
		return Base64.encodeBase64String(initKey());
	}
	
	/**
	 * @description 用base64解码密钥
	 * @date  2016年3月3日
	 */
	public static byte[] getKey(String key){
		return Base64.decodeBase64(key);
	}
	
	/**
	 * @description 解密 
	 * @date  2016年3月3日
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * @description 加密  
	 * @date  2016年3月3日
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}
	
	/**
	 * @description 解密 
	 * @date  2016年3月3日
	 */
	public static byte[] decrypt(byte[] data, String key) throws Exception {
		return decrypt(data, getKey(key));
	}

	/**
	 * @description 加密  
	 * @date  2016年3月3日
	 */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		return encrypt(data, getKey(key));
	}
	
	/**
	 * @description 摘要处理
	 * @date  2016年3月3日
	 */
	public static String shaHex(byte[] data){
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * @description 验证摘要
	 * @date  2016年3月3日
	 */
	public static boolean validate(byte[] data, String messageDigest){ 
		return messageDigest.equals(shaHex(data));	
	}
}
