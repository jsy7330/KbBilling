package com.ntels.ccbs.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <PRE>
 * 1. ClassName: CommandInjectionWrapper
 * 2. FileName : CommandInjectionWrapper.java
 * 3. Package  : com.ntels.ccbs.common.filter
 * 4. Comment  :
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 10. 22. 오후 2:43:29
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     kgw :    2015. 10. 22.    : 신규개발
 * </PRE>
 */
public final class CommandInjectionWrapper extends HttpServletRequestWrapper {
	private static Logger logger = LoggerFactory.getLogger(CommandInjectionWrapper.class);
	
	
	private ByteArrayOutputStream cachedBytes;
	
	/**
	 *
	 * 생성자
	 *
	 * @param		servletRequest : servletRequest
	 */
	public CommandInjectionWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (cachedBytes == null)
			cacheInputStream();
		
		return new CachedServletInputStream();
	}
	
	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	private void cacheInputStream() throws IOException {
		cachedBytes = new ByteArrayOutputStream();
		IOUtils.copy(super.getInputStream(), cachedBytes);
	}
	
	public class CachedServletInputStream extends ServletInputStream {
		private ByteArrayInputStream input;
		public CachedServletInputStream() {
			input = new ByteArrayInputStream(cachedBytes.toByteArray());
		}
		@Override
		public int read() throws IOException {
			return input.read();
		}
	}
		
	
	@Override
	public String[] getParameterValues(String parameter) {
		logger.debug("In getParameterValues .. parameter .......");
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = clean(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		logger.debug("In getParameter .. parameter .......");
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		logger.debug("In getParameter CommandInjectionWrapper ........ value .......");
		return clean(value);
	}

	@Override
	public String getHeader(String name) {
		logger.debug("In getHeader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		logger.debug("In getHeader CommandInjectionWrapper ........... value ....");
		return clean(value);
	}

	/**
	 *
	 * clean
	 *
	 * @param		value : value
	 * @return		String
	 */
	private String clean(String value) {
		// You'll need to remove the spaces from the html entities below
		logger.debug("In clean CommandInjectionWrapper ...............{}", value);

		StringBuilder ret = new StringBuilder();

		do {
			ret.setLength(0);
			ret.append(value);

			value = ret.toString().replace("&", "");
			value = value.replace("&&", "");
			value = value.replace("|", "");
			value = value.replace("||", "");
		} while(ret.toString().length() != value.length());

		logger.debug("Out clean CommandInjectionWrapper ........ value ....... {}", value);
		return value;
	}
	
	
	public Map<String,String> headerMap() {
		Map<String,String> convertedHeaderMap = new HashMap<>();
		
		@SuppressWarnings("unchecked")
		Enumeration<String> headerMap =super.getHeaderNames();
		
		while (headerMap.hasMoreElements()) {
			String name = headerMap.nextElement();
			String value =super.getHeader(name);
			
			convertedHeaderMap.put(name, value);
		}
		
		return convertedHeaderMap;
	}
	
	
	public Map<String,String> parameterMap(){
		@SuppressWarnings("unchecked")
		Map<String,String[]> parameterMap =super.getParameterMap();
		Map<String,String> convertedParameterMap = new HashMap<>();
		try{
			if((parameterMap == null || parameterMap.isEmpty() )
					&&this.getInputStream() != null){
				ServletInputStream sis =this.getInputStream();
				ObjectMapper om = new ObjectMapper();
				String inputString = IOUtils.toString(sis);
				TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
				convertedParameterMap = om.readValue(inputString, typeRef);

				logger.info("HTTP request inputStream read to String : " + inputString);
			}else{
				// when parameters Exist
				for (String key : parameterMap.keySet()) {
					String[] values = parameterMap.get(key);
					StringJoiner valueString = new StringJoiner(",");
					
					for (String value : values) {
						valueString.add(value);
					}
					
					convertedParameterMap.put(key, valueString.toString());
				}
			}
		}catch(Exception e){
			logger.info("request inputStream read to String Error => skip ");
		}
		return convertedParameterMap;  
	}
	
	
	public String getRequestUri() {
		return super.getRequestURI();
	}
	
	public String getRequestMethod() {
		return super.getMethod();
	}
	
	// ELB 뒤에서 기존 아이피 획됙
	public String getRequestSrcIp() {
		String ipAddress =super.getHeader("X-FORWARDED-FOR"); 
		if (ipAddress == null) { 
			ipAddress =super.getRemoteAddr();
		}
		return ipAddress;
	}
}