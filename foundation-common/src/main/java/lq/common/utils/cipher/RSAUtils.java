package lq.common.utils.cipher;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 用RSA算法进行加密和解密，用RSA算法进行数字签名
 * 
 * @author liuquan
 * @date 2016年3月4日
 */
public class RSAUtils {
	// 非对称加密密钥算法
	public static final String KEY_ALGORITHM = "RSA";

	// 公钥
	private static final String PUBLIC_KEY = "RSAPublicKey";

	// 私钥
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	// RSA密钥长度 默认1024位， 密钥长度必须是64的倍数， 范围在512至65536位之间。
	private static final int KEY_SIZE = 512;

	// 数字签名 签名/验证算法
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

	/**
	 * @description 私钥解密
	 * @date 2016年3月4日
	 */
	public static byte[] decryptByPrivateKey(byte[] data, byte[] key)
			throws Exception {
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * @description 公钥解密
	 * @date 2016年3月4日
	 */
	public static byte[] decryptByPublicKey(byte[] data, byte[] key)
			throws Exception {
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成公钥
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * @description 公钥加密
	 * @date 2016年3月4日
	 */
	public static byte[] encryptByPublicKey(byte[] data, byte[] key)
			throws Exception {
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * @description 私钥加密
	 * @date 2016年3月4日
	 */
	public static byte[] encryptByPrivateKey(byte[] data, byte[] key)
			throws Exception {
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * @description 取得私钥
	 * @date 2016年3月4日
	 */
	public static byte[] getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}

	/**
	 * @description 取得公钥
	 * @date 2016年3月4日
	 */
	public static byte[] getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}

	/**
	 * @description 初始化密钥
	 * @date 2016年3月4日
	 */
	public static Map<String, Object> initKey() throws Exception {
		// 实例化密钥对生成器
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		// 初始化密钥对生成器
		keyPairGen.initialize(KEY_SIZE);
		// 生成密钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 封装密钥
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * @description 还原经Hex编码的密钥成字节数组形式并并返回
	 * @date 2016年3月4日
	 */
	public static byte[] getKey(String key) throws Exception {
		return Hex.decodeHex(key.toCharArray());
	}

	/**
	 * @description 获取经Hex编码的私钥
	 * @date 2016年3月4日
	 */
	public static String getPrivateString(Map<String, Object> keyMap)
			throws Exception {
		return Hex.encodeHexString(getPrivateKey(keyMap));
	}

	/**
	 * @description 获取经Hex编码的公钥
	 * @date 2016年3月4日
	 */
	public static String getPublicString(Map<String, Object> keyMap)
			throws Exception {
		return Hex.encodeHexString(getPublicKey(keyMap));
	}

	/**
	 * @description 私钥解密 key是经Hex编码后的字符串形式
	 * @date 2016年3月4日
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key)
			throws Exception {
		return decryptByPrivateKey(data, getKey(key));
	}

	/**
	 * @description 公钥解密 key是经Hex编码后的字符串形式
	 * @date 2016年3月4日
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key)
			throws Exception {
		return decryptByPublicKey(data, getKey(key));
	}

	/**
	 * @description 公钥加密 key是经Hex编码后的字符串形式
	 * @date 2016年3月4日
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key)
			throws Exception {
		return encryptByPublicKey(data, getKey(key));
	}

	/**
	 * @description 私钥加密 key是经Hex编码后的字符串形式
	 * @date 2016年3月4日
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key)
			throws Exception {
		return encryptByPrivateKey(data, getKey(key));
	}
	
	/**
	 * @description 私钥签名
	 * @time 2016年3月5日 下午1:58:39
	 */
	public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {

		// 转换私钥材料
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);

		// 实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 实例化Signature
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

		// 初始化Signature
		signature.initSign(priKey);

		// 更新
		signature.update(data);

		// 签名
		return signature.sign();
	}

	/**
	 * @description 公钥验证 
	 * @time 2016年3月5日 下午1:59:09
	 */
	public static boolean verify(byte[] data, byte[] publicKey, byte[] sign)
			throws Exception {
		// 转换公钥材料
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);

		// 实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 生成公钥
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		// 实例化Signature
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

		// 初始化Signature
		signature.initVerify(pubKey);

		// 更新
		signature.update(data);

		// 验证
		return signature.verify(sign);
	}

	/**
	 * @description 私钥签名  参数私钥是用16进制编码后的字符串形式,返回的签名也是用16进制编码后的形式
	 * @time 2016年3月5日 下午1:59:35
	 */
	public static String sign(byte[] data, String privateKey) throws Exception{
		byte[] sign = sign(data, getKey(privateKey));
		return Hex.encodeHexString(sign); 
	}
	
	/**
	 * @description 公钥验证，参数公钥是用16进制编码后的字符串形式，参数签名也是这样
	 * @time 2016年3月5日 下午2:03:53
	 */
	public static boolean vertify(byte[]data, String publicKey, String sign) throws Exception{
		return verify(data, getKey(publicKey), Hex.decodeHex(sign.toCharArray()));
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> keyMap = RSAUtils.initKey();
		System.out.println("公钥\t" + RSAUtils.getPublicString(keyMap));
		System.out.println("私钥\t" + RSAUtils.getPrivateString(keyMap));
	}

}
