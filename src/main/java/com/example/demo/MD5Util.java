package com.example.demo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String md5(String mess) {
		try {
			byte[] messageBytes = mess.getBytes(StandardCharsets.UTF_8);
			MessageDigest hasher = MessageDigest.getInstance("MD5");
			byte[] digest = hasher.digest(messageBytes);

			return new BigInteger(1,digest).toString(16);
		} catch (NoSuchAlgorithmException e) {
			return mess;
		}

	}

}
