package com.ntels.ccbs.common.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/*
http://www.imcore.net | hosihito@gmail.com
Developer. Kyoungbin Lee
2012.09.07

AES256 EnCrypt / DeCrypt
*/
public class Aes256 {
	
	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		String key = "ntels!@tkdlxmwnth!2dlqksjd123456";

		String plainText;
		String encodeText;
		String decodeText;
		// Encrypt
		plainText  = "aaa@aaa.aaa";
		encodeText = AES256Cipher.AES_Encode(plainText, key);		
		System.out.println("AES256_Encode : "+encodeText);
		 
		// Decrypt
		decodeText = AES256Cipher.AES_Decode("qv6SPAQAfQzq1Y+DbhHEtg==", key);
		System.out.println("AES256_Decode : "+decodeText);

	}

}
