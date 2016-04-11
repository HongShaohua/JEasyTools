package jetools.codec;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA
 * 算法的名字以发明者的名字命名：Ron Rivest, AdiShamir 和Leonard Adleman
 * */
public class RSA {
	
	public static final String ALGORITHM_RSA = "RSA";
	public static final String ALGORITHM_MD5_WITH_RSA = "MD5withRSA";
	
	/**
	 * 初始化密匙
	 * algorithm:加密算法
	 * keysize:密匙长度
	 * 返回值:密码对
	 * */
	public static KeyPair initKey(String algorithm, int keysize) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
		keyPairGenerator.initialize(keysize);
		return keyPairGenerator.generateKeyPair();
	}
	
	/**
	 * 初始化密匙
	 * keysize:密匙长度
	 * 默认使用RSA算法
	 * 返回值:密码对
	 * */
	public static KeyPair initKey(int keysize) throws Exception {
		return initKey(ALGORITHM_RSA, keysize);
	}
	
	/**
	 * 获得Key编码
	 * key:可传入PublicKey或PrivateKey
	 * 返回值:字符串类型的key
	 * */
	private static byte[] key2Bytes(Key key) throws Exception {
		return key.getEncoded();
	}
	
	public static byte[] publicKey2Bytes(PublicKey publicKey) throws Exception {
		return key2Bytes(publicKey);
	}
	
	public static byte[] privateKey2Bytes(PrivateKey privateKey) throws Exception {
		return key2Bytes(privateKey);
	}
	
	/**
	 * 获得公钥
	 * bytes:公钥编码
	 * algorithm:加密算法
	 * 返回值:公钥
	 * */
	public static PublicKey bytes2PublicKey(byte[] bytes, String algorithm) throws Exception {
		KeySpec keySpec = new X509EncodedKeySpec(bytes);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		return keyFactory.generatePublic(keySpec);
	}
	
	/**
	 * 获得公钥
	 * bytes:公钥编码
	 * 默认使用RSA算法
	 * 返回值:公钥
	 * */
	public static PublicKey bytes2PublicKey(byte[] bytes) throws Exception {
		return bytes2PublicKey(bytes, ALGORITHM_RSA);
	}
	
	/**
	 * 获得秘钥
	 * bytes:秘钥编码
	 * algorithm:加密算法
	 * 返回值:秘钥
	 * */
	public static PrivateKey bytes2PrivateKey(byte[] bytes, String algorithm) throws Exception {
		KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		return keyFactory.generatePrivate(keySpec);
	}
	
	/**
	 * 获得秘钥
	 * bytes:秘钥编码
	 * 默认使用RSA算法
	 * 返回值:秘钥
	 * */
	public static PrivateKey bytes2PrivateKey(byte[] bytes) throws Exception {
		return bytes2PrivateKey(bytes, ALGORITHM_RSA);
	}
	
	/**
	 * 对数据进行编解码
	 * data:要编解码的数据
	 * key:可传入PublicKey或PrivateKey
	 * opmode:操作模式Cipher.ENCRYPT_MODE或Cipher.DECRYPT_MODE
	 * 返回值:编解码后的数据
	 * */
	private static byte[] code(byte[] data, Key key, int opmode) throws Exception {
		Cipher cipher = Cipher.getInstance(key.getAlgorithm());
		cipher.init(opmode, key);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥加密
	 * data:要加密的数据
	 * publicKey:公钥
	 * 返回值:加密后的数据
	 * */
	public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {
		return code(data, publicKey, Cipher.ENCRYPT_MODE);
	}
	
	/**
	 * 公钥解密
	 * data:要解密的数据
	 * publicKey:公钥
	 * 返回值:解密后的数据
	 * */
	public static byte[] decrypt(byte[] data, PublicKey publicKey) throws Exception {
		return code(data, publicKey, Cipher.DECRYPT_MODE);
	}
	
	/**
	 * 秘钥加密
	 * data:要加密的数据
	 * privateKey:秘钥
	 * 返回值:加密后的数据
	 * */
	public static byte[] encrypt(byte[] data, PrivateKey privateKey) throws Exception {
		return code(data, privateKey, Cipher.ENCRYPT_MODE);
	}
	
	/**
	 * 秘钥解密
	 * data:要解密的数据
	 * privateKey:秘钥
	 * 返回值:解密后的数据
	 * */
	public static byte[] decrypt(byte[] data, PrivateKey privateKey) throws Exception {
		return code(data, privateKey, Cipher.DECRYPT_MODE);
	}
	
	/**
	 * 用私钥对数据生成数字签名
	 * data:要签名的数据
	 * privateKey:秘钥
	 * sign_algorithm:签名算法
	 * 返回值:签名数据
	 * */
	public static byte[] sign(byte[] data, PrivateKey privateKey, String sign_algorithm) throws Exception {
		Signature signature = Signature.getInstance(sign_algorithm);
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}
	
	/**
	 * 用私钥对数据生成数字签名
	 * data:要签名的数据
	 * privateKey:秘钥
	 * 签名信息默认使用MD5withRSA加密算法
	 * 返回值:签名数据
	 * */
	public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
		return sign(data, privateKey, ALGORITHM_MD5_WITH_RSA);
	}
	
	/**
	 * 校验数字签名
	 * data:签名的数据
	 * publicKey:公钥
	 * sign:base64编码的签名字符串
	 * sign_algorithm:签名算法
	 * 返回值:true或false
	 * */
	public static boolean verify(byte[] data, PublicKey publicKey, byte[] sign, String sign_algorithm) throws Exception {
		Signature signature = Signature.getInstance(sign_algorithm);
		signature.initVerify(publicKey);
		signature.update(data);
		return signature.verify(sign);
	}
	
	/**
	 * 校验数字签名
	 * data:签名的数据
	 * publicKey:公钥
	 * sign:base64编码的签名字符串
	 * 签名信息默认使用MD5withRSA加密算法
	 * 返回值:true或false
	 * */
	public static boolean verify(byte[] data, PublicKey publicKey, byte[] sign) throws Exception {
		return verify(data, publicKey, sign, ALGORITHM_MD5_WITH_RSA);
	}
}
