package com.ntels.ccbs.common.util;

import org.apache.log4j.Logger;

public class MaskUtil{

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static String converToMask(String str, int startIndex, int endIndex, String maskChar) throws Exception{
        int total 	   = str.length();
        if(endIndex == 9999){
        	endIndex = str.length();
        }
        int masklen    = endIndex - startIndex;
        
        StringBuffer maskedbuf = new StringBuffer(str.substring(0, startIndex));
        for(int i=0;i<masklen;i++) {
            maskedbuf.append(maskChar);
        }
        
        maskedbuf.append(str.substring(startIndex + masklen, total));
        return maskedbuf.toString();

    }
	
	public static String convert2CamelCase(String underScore) {

        // '_' 가 나타나지 않으면 이미 camel case 로 가정함.
        // 단 첫째문자가 대문자이면 camel case 변환 (전체를 소문자로) 처리가
        // 필요하다고 가정함. --> 아래 로직을 수행하면 바뀜
        if (underScore.indexOf('_') < 0
            && Character.isLowerCase(underScore.charAt(0))) {
            return underScore;
        }
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();

        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(currentChar));
                    nextUpper = false;
                } else {
                    result.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return result.toString();
    }
}
