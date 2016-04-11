package jetools.codec;

import java.security.MessageDigest;

/**
 * MD5
 * Message Digest algorithm 5，信息摘要算法
 * */
public class MD5 {
	
	private static final String ALGORITHM = "MD5";
	
	/**
	 * 加密
	 * */
	public static byte[] encrypt(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);
		md5.update(data);
		return md5.digest();
	}
}
