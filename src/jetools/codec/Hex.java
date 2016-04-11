package jetools.codec;

/**
 * 16进制转换工具
 * */
public class Hex {
	
	//内部使用的，用于转换byte
	private final static char[] s_hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	/**
	 * long型转16进制字符串
	 * */
	public static String toHex(long l) {
		return Long.toHexString(l);
	}
	
	/**
	 * int型转16进制字符串
	 * */
	public static String toHex(int i) {
		return Integer.toHexString(i);
	}

	/**
	 * byte型转16进制字符串
	 * */
	public static String toHex(byte b) {
		int n = b;
		if(n < 0) {
			n = 256 + n;
		}
		int hex1 = n / s_hexDigits.length;
		int hex2 = n % s_hexDigits.length;
		
		return "" + s_hexDigits[hex1] + s_hexDigits[hex2];
	}
	
	/**
	 * byte数组转16进制字符串
	 * */
	public static String toHex(byte[] bs) {
		StringBuilder strBuf = new StringBuilder();
		for(byte b : bs) {
			String str = toHex(b);
			strBuf.append(str);
		}
		return strBuf.toString();
	}
	
	/**
	 * 16进制字符串转long型
	 * */
	public static long toLong(String hex) {
		return Long.parseLong(hex, s_hexDigits.length);
	}
	
	/**
	 * 16进制字符串转int型
	 * */
	public static int toInt(String hex) {
		return Integer.parseInt(hex, s_hexDigits.length);
	}
	
	/**
	 * 16进制字符串转byte型
	 * */
	public static byte toByte(String hex) {
		return Byte.parseByte(hex, s_hexDigits.length);
	}
	
	/**
	 * 16进制字符串转byte数组
	 * */
	public static byte[] toBytes(String hex) {
		if(hex == null || hex.isEmpty()) {
			return null;
		}
		String hexStr = hex.toLowerCase();
		if(hexStr.length() % 2 > 0) {
			hexStr = "0" + hexStr;
		}
		int len = hex.length() / 2;
		byte[] bytes = new byte[len];
		for(int i = 0; i < len; i++) {
			char c1 = hexStr.charAt(i * 2);
			char c2 = hexStr.charAt(i * 2 + 1);
			String byteHex = "" + c1 + c2;
			bytes[i] = toByte(byteHex);
		}
		return bytes;
	}
}
