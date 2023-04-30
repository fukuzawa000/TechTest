package com.techtest.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Token {
	
	private final String SYSTEM_SUFFIX = "TechTest";

	//Token作成
	public String creatNewToken(String id){
		String retToken = "";
		Date currentDate = new Date();
		String currentTime = String.valueOf(currentDate.getTime());
		
		retToken = getSha256Encode(id + currentTime + SYSTEM_SUFFIX);
		
		return retToken;
	}
	
	//Sha265暗号化
	private String getSha256Encode(String sourceString){
		MessageDigest messageDigest;
		String encodeString = "";
		
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(sourceString.getBytes("UTF-8"));
			encodeString = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return encodeString;
	}
	private String byte2Hex (byte[] bytes){
		StringBuffer stringBuffer = new StringBuffer();
		
		String temp = null;
		for (int i=0; i<bytes.length; i++){
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1 ) {
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		
		return stringBuffer.toString();
	}
}
