package com.ksmartech.AES;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
**/


public class AES256 {
//	private String iv;
	private byte[] riv;
    private Key keySpec;
    protected String CipherAlgorithm = "AES/CBC/PKCS5Padding";
    
    
    /** UnsupportedEncodingException **/
    public AES256() throws Exception {
//    	MessageDigest digest = MessageDigest.getInstance("SHA-512");
//    	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    	
    	
    	
    	/**
    	 KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    	 keyGenerator.init(128); 
    	 Key key = keyGenerator.generateKey();
    	
    	 **/
    	
    	/**random iv **/
    	
//		byte[] rndiv = new byte[16];
//		SecureRandom rnd = new SecureRandom();
//		rnd.nextBytes(rndiv);
//		System.arraycopy(rndiv, 0, riv, 0, rndiv.length);
    	
    	/**고정된 iv 필수로 16bytes**/
    	
    	String key = "aes256-test-key!";
    	riv = key.getBytes("UTF-8");
    	
    	
//    	this.iv = key.substring(0,16);
        
    	
    	/**aes key 생성**/
    	
    	byte[] keyBytes = new byte[32];
    	    	
        String skey = "abcd-efgh-ijkl-mnop-qrst-uvwx-yz"; //32bit
        
        byte[] b = skey.getBytes("UTF-8");
	    int len = b.length;
	     if(len > keyBytes.length)
	         len = keyBytes.length;
	    System.arraycopy(b, 0, keyBytes, 0, len);

	    /** random key **/
	    
//        SecureRandom sk = new SecureRandom();
//        sk.nextBytes(keyBytes);

	    
	    /**패스워드 기반 암호화 (메세지다이제스트, 솔팅)**/
	    
//	    byte[] saltBytes = digest.digest(keyBytes);
//	    PBEKeySpec pbeKeySpec = new PBEKeySpec(skey.toCharArray(), saltBytes, 65536, 256);  /** 65536은 iteration **/
//        Key secretKey = factory.generateSecret(pbeKeySpec);
//        System.arraycopy(secretKey.getEncoded(), 0, keyBytes, 0, 32);
//        //AES 알고리즘을 적용하여 암호화키 생성
//        SecretKeySpec secret = new SecretKeySpec(keyBytes, "AES");
//        setKeySpec(secret);
	    
	    
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        this.keySpec = keySpec;
    }
    
 
/** based on NIST(미국 국립표준기술연구소)'s Advanced Encryption Standard; cipher-block chaining mode;
    and the block cipher padding method detailed in PKCS #7. 자바에서는 pkcs#11 지원x **/
    
    // 암호화
    /**
	    java.io.UnsupportedEncodingException,
	    NoSuchAlgorithmException, 
	    NoSuchPaddingException,
	    InvalidKeyException, 
	    InvalidAlgorithmParameterException, 
	    IllegalBlockSizeException,
	    BadPaddingException
    **/
    public byte[] aesEncode(byte[] str) throws Exception{
//        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");//JCA에 정의, JCE에 설명나와있음.
//        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(riv));

/**      c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes())); **/
        	
    	System.out.println("CipherAlgorithm: " + CipherAlgorithm);
    	
        Cipher c = Cipher.getInstance(CipherAlgorithm);
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(riv));

        return c.doFinal(str);
    }

    //복호화
    /**
	    java.io.UnsupportedEncodingException,
	    NoSuchAlgorithmException, 
	    NoSuchPaddingException,
	    InvalidKeyException, 
	    InvalidAlgorithmParameterException, 
	    IllegalBlockSizeException,
	    BadPaddingException
    **/
    public byte[] aesDecode(byte[] str) throws Exception{
    	
    	/**algorithm/mode/padding" or "algorithm"**/
//        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding"); //자바에서는 pkcs#5 = pkcs#7
//        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(riv));
    	
/**      c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8"))); **/
        
    	System.out.println("CipherAlgorithm: " + CipherAlgorithm);
    	
        Cipher c = Cipher.getInstance(CipherAlgorithm); //자바에서는 pkcs#5=pkcs#7
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(riv));
                
        return c.doFinal(str);
    }
}
