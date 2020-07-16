package com.ntels.ccbs.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 날짜 Util.
 * 
 * @author smyun@ntels.com
 */
public class DateUtil {
	
	/**
	 * 생성자.
	 */
	private DateUtil(){
		
	}
	
	/** the logger. */
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	
	/**
	 * yyyyMMdd 형태의 문자열을 yyyy-MM-dd로 변환.
	 * 
	 * @param yyyyMMdd 입력문자열
	 * @return String
	 */
	public static String getDateFormat(final String yyyyMMdd) {
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		
		String ret = "";
		
		if (yyyyMMdd == null || yyyyMMdd.length() != 8){
			return "";
		}else{
			try {
				ret = df2.format(df1.parse(yyyyMMdd));
			} catch (ParseException e) {
				logger.error("error", e);
			}
		}
		
		return ret;
	}
	
	/**
	 * yyyyMMdd 형태의 문자열을 yyyy-MM-dd로 변환.
	 * 
	 * @param yyyyMMdd 입력문자열
	 * @return String
	 */
	public static String getLngDateFormat(final String yyyyMMdd, String lngTyp) {
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df3 = new SimpleDateFormat("MM/dd/yyyy");
		
		String ret = "";
		
		if (yyyyMMdd == null || yyyyMMdd.length() != 8){
			return "";
		}else{
			try {
				if(lngTyp != null && lngTyp.equals("ko")) {
					ret = df2.format(df1.parse(yyyyMMdd));
				}
				else if(lngTyp != null && lngTyp.equals("en")) {
					ret = df3.format(df1.parse(yyyyMMdd));
				}
			} catch (ParseException e) {
				logger.error("error", e);
			}
		}
		
		return ret;
	}
	
	/**
	 * yyyyMMdd 형태의 문자열을 yyyy-MM-dd로 변환.
	 * 
	 * @param yyyyMMdd 입력문자열
	 * @return String
	 */
	public static String getLngDateFormat_yymm(final String yyyyMM, String lngTyp) {
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat df3 = new SimpleDateFormat("MM/yyyy");
		
		String ret = "";
		
		if (yyyyMM == null || yyyyMM.length() != 6){
			return "";
		}else{
			try {
				if(lngTyp != null && lngTyp.equals("ko")) {
					ret = df2.format(df1.parse(yyyyMM));
				}
				else if(lngTyp != null && lngTyp.equals("en")) {
					ret = df3.format(df1.parse(yyyyMM));
				}
			} catch (ParseException e) {
				logger.error("error", e);
			}
		}
		
		return ret;
	}
	
	/**
	 * HHmmss 형태의 문자열을 HH:mm:ss 형태로 변환.
	 * 
	 * @param HHmmss 입력문자열
	 * @return String
	 */
	public static String getTimeFormat(final String HHmmss) {
		SimpleDateFormat df1 = new SimpleDateFormat("HHmmss");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		
		String ret = "";
		
		if (HHmmss == null || HHmmss.length() != 6){
			return "";
		}else{
			try {
				ret = df2.format(df1.parse(HHmmss));
			} catch (ParseException e) {
				logger.error("error", e);
			}
		}
		
		return ret;
	}	
	
	/**
	 * n 값에 따라 날짜 계산를 계산하여 String으로 전달( n이 0=오늘, -1=어제, 1=내일)
	 * 
	 * @param  int 
	 * @return String
	 */
	public static String getDateStringYYYYMMDD(int n){
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, n);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(cal.getTime());
	}
	
	/**
	 * n 값에 따라 날짜 계산를 계산하여 String으로 전달( n이 0=현재, -1=1초전, 1=1초)
	 * 
	 * @param  int 
	 * @return String
	 */
	public static String getDateStringYYYYMMDDHH24MISS(int n){
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, n);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(cal.getTime());
	}
	
	
	/**
	 * 오늘 날짜 DATE 객체
	 * 
	 * @param  int 
	 * @return String
	 */
	public static Date sysdate(){
		Date date = new Date();                 
        return date;		
	}
	
	public static Date getDateCalByDays(int n){
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, n);
        return cal.getTime();
	}


	/**
	 * n 값에 따라 날짜 계산를 계산하여 String으로 전달( n이 0=오늘, -1=어제, 1=내일)
	 * 
	 * @param  int 
	 * @return String
	 */
	public static String getDateStringYYYYMM(int n){
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, n);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        return dateFormat.format(cal.getTime());
	}
}
