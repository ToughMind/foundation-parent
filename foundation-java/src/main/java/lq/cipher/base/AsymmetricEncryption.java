package lq.cipher.base;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @description 非对称加密算法
 * 
 * @author liuquan
 * @date 2016年3月4日
 */
public class AsymmetricEncryption {
	public static void main(String[] args) throws Exception { 
		//DH();
		//ECDH算法同上，但密钥生成器算法填"EC",支持的对称算法是Blowfish、RC2、RC4，用AES、DES不行
				
		//RSA(); 
		//ElGamal();
		
		
	}

	private static void ElGamal() throws NoSuchAlgorithmException,
			InvalidParameterSpecException, InvalidAlgorithmParameterException,
			InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		//加入BouncyCastleProvider支持
		Security.addProvider(new BouncyCastleProvider());
		//实例化算法参数生成器
		AlgorithmParameterGenerator apg = AlgorithmParameterGenerator.getInstance("ElGamal");
		apg.init(512);
		//生成算法参数
		AlgorithmParameters params = apg.generateParameters();
		//构建参数材料
		DHParameterSpec dhs = params.getParameterSpec(DHParameterSpec.class);
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("ElGamal");
		kpg.initialize(dhs, new SecureRandom());
		//生成密钥对
		KeyPair kp = kpg.generateKeyPair();
		PublicKey publicKey = kp.getPublic();
		PrivateKey privateKey = kp.getPrivate();
		System.out.println("公钥\t" + Base64.encodeBase64String(publicKey.getEncoded()));
		System.out.println("私钥\t" + Base64.encodeBase64String(privateKey.getEncoded()));
		
		System.out.println("\n--------公钥加密，私钥解密");
		String input = "ElGamal加密算法"; 
		System.out.println("原文\t" + input); 
		KeyFactory kf = KeyFactory.getInstance("ElGamal");
		//取得公钥材料 然后生成公钥 
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKey.getEncoded());
		PublicKey pubKey = kf.generatePublic(x509);
		Cipher cipher = Cipher.getInstance("ElGamal");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] enCode = cipher.doFinal(input.getBytes());  
		System.out.println("使用公钥对数据加密\t" +Base64.encodeBase64String(enCode));
		//取得私钥材料 然后生成私钥 
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		PrivateKey priKey = kf.generatePrivate(pkcs8);
		Cipher cipher2 = Cipher.getInstance("ElGamal");
		cipher2.init(Cipher.DECRYPT_MODE, priKey);
		byte[] deCode = cipher2.doFinal(enCode); 
		System.out.println("使用私钥对数据解密\t" + new String(deCode));
	}

	private static void RSA() throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		//初始化密钥对生成器
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(8192);
		KeyPair kp = kpg.generateKeyPair();
		//生成公钥和私钥 
		RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
		System.out.println("公钥\t" + Base64.encodeBase64String(publicKey.getEncoded()));
		System.out.println("私钥\t" + Base64.encodeBase64String(privateKey.getEncoded()));
		
		//有了公钥和私钥 就可以加密处理了
		System.out.println("--------私钥加密，公钥解密");
		String input1 = "RSA加密算法"; 
		System.out.println("原文\t" + input1); 
		KeyFactory kf = KeyFactory.getInstance("RSA");
		//取得私钥材料 然后生成私钥 
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		PrivateKey priKey1 = kf.generatePrivate(pkcs8);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, priKey1);
		byte[] enCode1 = cipher.doFinal(input1.getBytes()); 
		System.out.println("使用私钥对数据加密\t" + Base64.encodeBase64String(enCode1));
		//取得公钥材料 然后生成公钥 
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKey.getEncoded());
		PublicKey pubKey1 = kf.generatePublic(x509);
		Cipher cipher2 = Cipher.getInstance("RSA");
		cipher2.init(Cipher.DECRYPT_MODE, pubKey1);
		byte[] deCode1 = cipher2.doFinal(enCode1); 
		System.out.println("使用公钥对数据解密\t" + new String(deCode1));
		
		System.out.println("\n--------公钥加密，私钥解密");
		String input2 = "RSA Encypt Algorithm"; 
		System.out.println("原文\t" + input2); 
		KeyFactory kf2 = KeyFactory.getInstance("RSA");
		//取得公钥材料 然后生成公钥 
		X509EncodedKeySpec x509_ = new X509EncodedKeySpec(publicKey.getEncoded());
		PublicKey pubKey2 = kf2.generatePublic(x509_);
		Cipher cipher3 = Cipher.getInstance("RSA");
		cipher3.init(Cipher.ENCRYPT_MODE, pubKey2);
		byte[] enCode2 = cipher3.doFinal(input2.getBytes());  
		System.out.println("使用公钥对数据加密\t" +Base64.encodeBase64String(enCode2));
		//取得私钥材料 然后生成私钥 
		PKCS8EncodedKeySpec pkcs8_ = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		PrivateKey priKey2 = kf2.generatePrivate(pkcs8_);
		Cipher cipher4 = Cipher.getInstance("RSA");
		cipher4.init(Cipher.DECRYPT_MODE, priKey2);
		byte[] deCode2 = cipher4.doFinal(enCode2); 
		System.out.println("使用私钥对数据解密\t" + new String(deCode2));
	}

	private static void DH() throws NoSuchAlgorithmException,
			InvalidKeySpecException, InvalidAlgorithmParameterException,
			Exception {
		// 初始化甲方密钥
		KeyPairGenerator kpg1 = KeyPairGenerator.getInstance("DH");
		kpg1.initialize(1024);
		KeyPair kp1 = kpg1.generateKeyPair();
		//甲方公钥
		DHPublicKey publicKey1 = (DHPublicKey) kp1.getPublic();
		//甲方私钥
		DHPrivateKey privateKey1 = (DHPrivateKey) kp1.getPrivate();
		System.out.println("甲方公钥\t" + Base64.encodeBase64String(publicKey1.getEncoded()));
		System.out.println("甲方私钥\t" + Base64.encodeBase64String(privateKey1.getEncoded()));
		
		//由甲方公钥产生乙方本地密钥对
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKey1.getEncoded());
		KeyFactory kf = KeyFactory.getInstance("DH");
		//还原甲方公钥成对象
		PublicKey pk = kf.generatePublic(x509); 
		//由甲方公钥创建乙方私钥和公钥
		DHParameterSpec dhpg = ((DHPublicKey)pk).getParams();
		KeyPairGenerator kpg2 = KeyPairGenerator.getInstance("DH");
		kpg2.initialize(dhpg);
		KeyPair kp2 = kpg2.generateKeyPair();
		//乙方公钥
		DHPublicKey publicKey2 = (DHPublicKey) kp2.getPublic();
		//乙方私钥
		DHPrivateKey privateKey2 = (DHPrivateKey) kp2.getPrivate();
		System.out.println("乙方公钥\t" + Base64.encodeBase64String(publicKey2.getEncoded()));
		System.out.println("乙方私钥\t" + Base64.encodeBase64String(privateKey2.getEncoded()));
		
		//创建甲、乙各自的本地秘密密钥 
		byte[] key1 = getSecretKey(publicKey2.getEncoded(), privateKey1.getEncoded(), "DH");
		byte[] key2 = getSecretKey(publicKey2.getEncoded(), privateKey1.getEncoded(), "DH");
		System.out.println("甲方本地密钥\t" + Base64.encodeBase64String(key1));
		System.out.println("乙方本地密钥\t" + Base64.encodeBase64String(key2));
		
		//有了本地密钥就可以加密处理了
		System.out.println("********甲方向乙方发送加密数据");
		String input1 = "密码交换算法";
		System.out.println("原文\t" + input1);
		byte[] code1 = encrypt(input1.getBytes(), key1, "AES");
		System.out.println("使用甲方本地密钥对数据加密\t" + Base64.encodeBase64String(code1));
		System.out.println("使用乙方本地密钥对数据解密\t" + new String(decrypt(code1, key2, "AES")));
		
		System.out.println("\n********乙方向甲方发送加密数据");
		String input2 = "DH";
		System.out.println("原文\t" + input2);
		byte[] code2 = encrypt(input2.getBytes(), key2, "AES");
		System.out.println("使用乙方本地密钥对数据加密\t" + Base64.encodeBase64String(code2));
		System.out.println("使用甲方本地密钥对数据解密\t" + new String(decrypt(code2, key2, "AES")));
	}
	
	/** 
	 * @description 根据公钥、私钥、和算法生成秘密密钥，注意公钥和私钥是字节码形式，不是对象，
	 * 				要做的就是先通过字节编码的公钥私钥生成相应的材料，再还原成对象形式，再生成秘密密钥
	 * @date  2016年3月4日
	 */
	public static byte[] getSecretKey(byte[] publicKey, byte[] privateKey, String algorithm) throws Exception{
		KeyFactory kf = KeyFactory.getInstance(algorithm);
		//公钥材料转换
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKey);
		PublicKey pubKey = kf.generatePublic(x509);
		//私钥转换材料
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(privateKey);
		PrivateKey priKey = kf.generatePrivate(pkcs8);
	
		KeyAgreement keyAgree = KeyAgreement.getInstance(algorithm);
		keyAgree.init(priKey);
		keyAgree.doPhase(pubKey, true);
		//这里的对称密钥的算法就有很多 这里选AES算法
		SecretKey sk = keyAgree.generateSecret("AES");
		return sk.getEncoded();
	}
	
	/**
	 * @description 加密数据  
	 * @date  2016年3月4日
	 */
	public static byte[] encrypt(byte[] data, byte[] key,String algorithm) throws Exception{
		//生成本地密钥
		SecretKey sk = new SecretKeySpec(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, sk);
		return cipher.doFinal(data);		
	}
	
	/**
	 * @description 解密数据  
	 * @date  2016年3月4日
	 */
	public static byte[] decrypt(byte[] data, byte[] key,String algorithm) throws Exception{
		//生成本地密钥
		SecretKey sk = new SecretKeySpec(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, sk);
		return cipher.doFinal(data);		
	}
	
}
