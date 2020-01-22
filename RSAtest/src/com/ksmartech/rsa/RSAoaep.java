package com.ksmartech.rsa;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

public class RSAoaep extends RSAex1 {

	public RSAoaep(String num) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		super(num);
		this.num = "o";
		// TODO Auto-generated constructor stub
	}
	

}
