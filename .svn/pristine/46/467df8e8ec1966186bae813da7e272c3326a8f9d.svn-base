package com.ntels.ccbs.common.util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 쿠폰번호 생성 <br>
 * 랜덤으로 유일한 쿠폰번호를 생성한다. <p/>
 *
 * Created on 2009. 11. 19.
 * @author    손승모, 엔텔스
 */

public class CouponNumberGenerator {

	final private static char[] possibleCharacters = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	final private static int possibleCharacterCount = possibleCharacters.length;

	/**
	 * 쿠폰번호를 랜덤으로 생성한다.
	 * @param numberLength 	랜덤으로 생성할 쿠폰번호 길이
	 * @return				랜덤으로 생성한 쿠폰번호
	 */
	private static String randomNumber(int numberLength) {
		Random rnd = new Random();
		StringBuffer randomBuf = new StringBuffer(numberLength);

		for (int i = numberLength; i > 0; i -- ) {
			randomBuf.append(possibleCharacters[rnd.nextInt(possibleCharacterCount)]);
		}
		return randomBuf.toString();
	}

	/**
	 * 입력받은 문자열을 정수형으로 변환 후 devideInt(9)로 나눈 나머지를 체크코드로 한다.
	 * @param couponNumber	쿠폰번호
	 * @param firstOffset	체크코드를 입력할 옵셋(1)
	 * @param secondOffset	체크코드를 입력할 옵셋(2)
	 * @return				체크코드를 입력한 쿠폰번호
	 */
	private static String insertCheckDigit(String couponNumber, int firstOffset, int secondOffset) {
		String retString="";
		String checkDigitFirst = "";
		String checkDigitSecond = "";

		//----------------------------------------------------------------------------------------------------------
		//체크문자열 생성
		//----------------------------------------------------------------------------------------------------------
		try {
			checkDigitFirst  = Integer.toString((sumString(couponNumber)%firstOffset)%9);
			checkDigitSecond = Integer.toString((sumString(couponNumber)%secondOffset)%9);

			if(firstOffset==1) {
				if(secondOffset == (couponNumber.length()+2)) {
					retString = checkDigitFirst + couponNumber + checkDigitSecond;
				}
				else {
					retString = checkDigitFirst+couponNumber;
					retString = retString.substring(0,secondOffset-1) + checkDigitSecond + retString.substring(secondOffset-1,retString.length());
				}
			}
			else {
				retString = couponNumber.substring(0, firstOffset-1) + checkDigitFirst + couponNumber.substring(firstOffset-1, couponNumber.length());
				if(secondOffset == (couponNumber.length()+2)) {
					retString = retString + checkDigitSecond;
				}
				else {
					retString = retString.substring(0, secondOffset-1) + checkDigitSecond + retString.substring(secondOffset-1, retString.length());
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			retString="";
		}

		return retString;
	}

	/**
	 * 스트링을 십진수로 변환한 후 모두 합산한다.
	 *
	 * <pre>
	 * sumString("AB") = 131
	 * </pre>
	 *
	 * @param inputString	입력스트링
	 * @return				입력스트링을 합산한 정수
	 */
	private static int sumString(String inputString) {
		int convertInt=0;
		for( int i=0; i<inputString.length(); i++ ) {
			convertInt += Integer.parseInt(Integer.toString((inputString.charAt(i))));
		}

		return convertInt;
	}

	/**
	 * 십진수를 스트링으로 변환한다.
	 *
	 * <pre>
	 * convertIntToString(65) = "A"
	 * </pre>
	 *
	 * @param inputInt	십진수
	 * @return			변환된 스트링
	 */
	private String convertIntToString(int inputInt) {
		byte[] bytes = new BigInteger(Integer.toHexString(inputInt), 16).toByteArray();
		return new String(bytes);
	}

	/**
	 * 쿠폰번호에 입력된 체크코드를 검증한다.
	 * @param couponNumber	쿠폰번호
	 * @param firstOffset	체크코드가 위치한 옵셋(1)
	 * @param secondOffset	체크코드가 위치한 옵셋(2)
	 * @return				true/false
	 */
	public boolean checkCouponNumber(String couponNumber, int firstOffset, int secondOffset) {

		String tempString;
		String checkFirstString;
		String checkSecondString;
		int checkFirstInt = 0;
		int checkSecondInt = 0;

		//----------------------------------------------------------------------------------------------------------
		//체크디지트를가 제외된 랜덤으로 생성한 원번호 생성
		//----------------------------------------------------------------------------------------------------------
		try {
			checkFirstString = couponNumber.substring(firstOffset-1, firstOffset);
			checkSecondString = couponNumber.substring(secondOffset-1, secondOffset);

			if(firstOffset == 1) {
				if(secondOffset == couponNumber.length()) {
					tempString  = couponNumber.substring(firstOffset,secondOffset-1);
				}
				else {
					tempString = couponNumber.substring(firstOffset,secondOffset-1);
					tempString+= couponNumber.substring(secondOffset,couponNumber.length());
				}
			}
			else {
				if(secondOffset == couponNumber.length()) {
					tempString = couponNumber.substring(0,firstOffset-1);
					tempString+= couponNumber.substring(firstOffset,secondOffset-1);
				}
				else {
					tempString = couponNumber.substring(0,firstOffset-1);
					tempString+= couponNumber.substring(firstOffset,secondOffset-1);
					tempString+= couponNumber.substring(secondOffset,couponNumber.length());
				}
			}
		}
		catch(Throwable e) {
			e.printStackTrace();
			return false;
		}

		//--------------------------------------------------------------------------------------------------------------
		//체크디지트 생성
		//--------------------------------------------------------------------------------------------------------------
		checkFirstInt = (sumString(tempString)%firstOffset)%9;
		checkSecondInt = (sumString(tempString)%secondOffset)%9;

		return checkFirstString.equals(Integer.toString(checkFirstInt)) &
		checkSecondString.equals(Integer.toString(checkSecondInt));
	}

	/**
	 * 쿠폰번호를 생성한다.
	 * @param numberLength	쿠폰번호 길이
	 * @param firstOffset
	 * @param secondOffset
	 * @return				쿠폰번호
	 */
	public static String generateCouponNumber(int numberLength, int firstOffset, int secondOffset) {

		String randomString="";

		if( numberLength < firstOffset || numberLength < secondOffset ) {
			return "";
		}
		
		numberLength = numberLength-2;
		randomString = randomNumber(numberLength);

		return insertCheckDigit(randomString, firstOffset, secondOffset);
	}

	public static void main(String[] args) {
		int len=15;
		int first=3;
		int second=4;
		Map<String,String> test = new HashMap<String,String>();
		for(int i = 0; i < 10000000; i++){
			String couponNumber = CouponNumberGenerator.generateCouponNumber(len,first,second);
			if(test.containsKey(couponNumber)){
				System.out.println(i + " : couponNumber : "+ couponNumber+", Len : "+couponNumber.length());
			}
			test.put(couponNumber, couponNumber);
		}
		
	}
}
