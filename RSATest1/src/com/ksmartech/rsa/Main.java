package com.ksmartech.rsa;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException {
		byte[] plaintext = "Hello world!".getBytes();
		
		
//		RSAex1 rsa = new RSA("rsa");
		RSAex1 rsa = new RSAoaep("o");
		
		
		byte[] encText = rsa.encrypt(plaintext);
		byte[] decText = rsa.decrypt(encText);
				
		System.out.println("Public Key : " + rsa.getPublicKey());
		System.out.println("Private Key : " + rsa.getPrivateKey());
		//sun.security.rsa.RSAPrivateCrtKeyImpl@
		System.out.println("---------------------------------");
		
		System.out.println("encrypt text : " + Base64.encodeBase64(encText));
		System.out.println("decrypt text : " + new String(decText));
		
	
	}

}
