package com.ntels.ccbs.system.domain.common.service;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 국가별 언어 관리.
 * 
 * @author kkw@ntels.com
 */
@XStreamAlias("countryLanguage")
public class CountryLanguage extends PagingValue implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1165469623994523559L;

	/** 국가코드. */
	@NotEmpty
	private String countryCode;
	
	/** 국가명. */
	private String countryName;
	
	/** 언어코드. */
	@NotEmpty
	private String languageCode;
	
	/** 언어명. */
	private String languageName;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	@Override
	public String toString() {
		return "CountryLanguage [countryCode=" + countryCode + ", countryName="
				+ countryName + ", languageCode=" + languageCode
				+ ", languageName=" + languageName + "]";
	}
}
