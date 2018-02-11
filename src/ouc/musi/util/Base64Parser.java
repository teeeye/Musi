package ouc.musi.util;

import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Parser {
	
	public static byte[] Base64Decode(String encodedData) {
		byte[] decodedData = Base64.decodeBase64(encodedData);
		return decodedData;
	}
}
