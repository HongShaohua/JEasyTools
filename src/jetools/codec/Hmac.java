package jetools.codec;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC
 * Hash Message Authentication Code，散列消息鉴别码
 * MAC算法可选以下多种算法
 * HmacMD5
 * HmacSHA1
 * HmacSHA256
 * HmacSHA384
 * HmacSHA512
 * */
public class Hmac {
	
	public static final String ALGORITHM_HMAC_MD5 = "HmacMD5";
	public static final String ALGORITHM_HMAC_SHA1 = "HmacSHA1";
	public static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";
	public static final String ALGORITHM_HMAC_SHA384 = "HmacSHA384";
	public static final String ALGORITHM_HMAC_SHA512 = "HmacSHA512";
	
	/**
	 * 初始化key
	 * algorithm：指定算法
	 * */
	public static String initKey(String algorithm) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		SecretKey secretKey = keyGenerator.generateKey();
		return Hex.toHex(secretKey.getEncoded());
	}
	
	/**
	 * 初始化key
	 * 默认使用HmacMD5
	 * */
	public static String initkey() throws Exception {
		return initKey(ALGORITHM_HMAC_MD5);
	}
	
	/**
	 * 加密
	 * algorithm：指定算法
	 * */
	public static byte[] encrypt(byte[] data, String key, String algorithm) throws Exception {
		SecretKey secretKey = new SecretKeySpec(Hex.toBytes(key), algorithm);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}
	
	/**
	 * 加密
	 * 默认使用HmacMD5
	 * */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		return encrypt(data, key, ALGORITHM_HMAC_MD5);
	}
}
