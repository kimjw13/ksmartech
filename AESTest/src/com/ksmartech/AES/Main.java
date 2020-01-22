package com.ksmartech.AES;

//import java.security.SecureRandom;
//import java.util.Random;

public class Main {

	public static void main(String[] args) throws Exception {
		
		AES256 a256 = new AES256CTR();
		
		byte[] textByte = "암호화되지 않은 문자입니다.".getBytes();
//		byte[] textByte = "HELLO".getBytes();
		
		byte[] encTextByte = a256.aesEncode(textByte);
		byte[] decTextByte = a256.aesDecode(encTextByte);
		
		System.out.println("암호화할 문자 : " + new String(textByte));
//		System.out.println("암호화된 문자 : " + encText);
		System.out.println("복호화된 문자 : " + new String(decTextByte));

		

	}
	
	
}
