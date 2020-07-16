package com.ntels.ccbs.common.util;

import java.util.Random;

public class OtpUtil {

	private static final String NUMBERS = "0123456789";
	private static final String UPPER_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
	private static final String SPECIALCHARACTERS = "!@#$%^*";
	private static final int MINLENGTHOFPASSWORD = 8;

	public static String getRandomPassword() {
	    StringBuilder password = new StringBuilder();
	    for (int i = 0; i < MINLENGTHOFPASSWORD; i++) {
	        password.append(getRandomPasswordCharacters(i));
	    }
	    return password.toString();
	}

	private static String getRandomPasswordCharacters(int index) {
	    Random randomNum = new Random();
	    
	    StringBuilder randomChar = new StringBuilder();
	    if(index < 4){
	    	randomChar.append(NUMBERS.charAt(randomNum.nextInt(NUMBERS.length() - 1)));
	    }else if(index == 4){
		    
	    	randomChar.append(SPECIALCHARACTERS.charAt(randomNum.nextInt(SPECIALCHARACTERS.length() - 1)));
	    }else{
	    	
	    	Random randomIndex = new Random();
		    int random = randomIndex.nextInt(2);
	    	switch (random) {
	        case 0:
	        	randomChar.append(LOWER_ALPHABETS.charAt(randomNum.nextInt(LOWER_ALPHABETS.length() - 1)));
	            break;
	        case 1:
	            randomChar.append(UPPER_ALPHABETS.charAt(randomNum.nextInt(UPPER_ALPHABETS.length() - 1)));
	            break;
	    	}
	    }
	    return randomChar.toString();
	}

}
