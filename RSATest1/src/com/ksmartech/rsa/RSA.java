package com.ksmartech.rsa;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

public class RSA extends RSAex1 {

	public RSA(String num) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		super(num);
		this.num = "rsa";
		// TODO Auto-generated constructor stub
	}
	

}
