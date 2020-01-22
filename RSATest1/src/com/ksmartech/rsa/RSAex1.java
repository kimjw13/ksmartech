package com.ksmartech.rsa;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

//import org.apache.commons.codec.binary.Base64;

public class RSAex1 {
	private Key publicKey;
	private Key privateKey;
	private SecureRandom random = new SecureRandom();
	protected String num = "";
	
	public RSAex1(String n) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException{
		KeyPairGenerator keyPairGenerator = null;
		
		long start = System.currentTimeMillis();
		
		switch(n){
			case "o" :
				System.out.println("RSA-OAEP Mode");
				
				Security.addProvider(new BouncyCastleProvider());
				
				keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
				keyPairGenerator.initialize(2048,random);
				break;
				
			default :
				System.out.println("RSA Mode");
				keyPairGenerator = KeyPairGenerator.getInstance("RSA");
				keyPairGenerator.initialize(2048);	
				
				break;
		}
		
			        
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		this.publicKey = keyPair.getPublic(); // 공개키 modulus(n) = p*q , public exponent = E(65537)
		/**
		 *RSA 공개지수는 {3,5,17,257 or 65537} 
		 *65537인 이유는 RSA-OAEP 패딩 방식을 사용하는 경우에는 이것이 문제가되지 않지만
		 *공개 지수 3을 사용하면 PKCS # 1 패딩 방식 (아래 답변에서 적절한 패딩 방식으로 제공됨)이 취약합니다.
		 *지수가 충분하지 않으면 패딩 오라클 공격에 취약
		 *그렇지만 지수가 충분히 크다고해서 보안이 강해지는 건 아님, 적절한 패딩이 주어진다면 지수가 작아도 상관없다.
		 	***/
		this.privateKey = keyPair.getPrivate(); // 개인키 D
		
		long end = System.currentTimeMillis();
		
		System.out.println("time: "+(end - start));
		
	}
	
	public byte[] encrypt (byte[] inputStr) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException{
		Cipher c = null;
		switch(num) {
			case "o" :
				System.out.println("123123");
				
				c = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
				c.init(Cipher.ENCRYPT_MODE, publicKey,random);
				
				break;
				
			case "rsa" : 
				
				System.out.println("456456");
				c = Cipher.getInstance("RSA");
				c.init(Cipher.ENCRYPT_MODE, publicKey);
				break;
		}		
		return c.doFinal(inputStr);
	}
	
	public byte[] decrypt (byte[] inputStr) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException {
		Cipher c = null;
		
		switch(num) {
			case "o" :
				c = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
				break;
				
			case "rsa" :
				c = Cipher.getInstance("RSA");
				break;
		}
		c.init(Cipher.DECRYPT_MODE, privateKey);		
        return c.doFinal(inputStr);
	}

	public Key getPublicKey() {
		return publicKey;
	}

	public Key getPrivateKey() {
		return privateKey;
	}
}