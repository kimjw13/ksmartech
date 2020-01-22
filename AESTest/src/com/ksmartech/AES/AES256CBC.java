package com.ksmartech.AES;

public class AES256CBC extends AES256 {

	public AES256CBC() throws Exception {
		super();
		this.CipherAlgorithm = "AES/CBC/PKCS5Padding";
	}

}
