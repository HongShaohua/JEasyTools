package jetools.codec;

import java.security.MessageDigest;

/**
 * SHA
 * Secure Hash Algorithm，安全散列算法
 * */
public class SHA1 {
	
	private static final String ALGORITHM = "SHA1";
	
	/**
	 * 加密
	 * */
	public static byte[] encrypt(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);
		md5.update(data);
		return md5.digest();
	}
}
