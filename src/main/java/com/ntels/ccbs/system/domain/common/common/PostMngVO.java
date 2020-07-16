package com.ntels.ccbs.system.domain.common.common;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: PostMngVO
 * 2. FileName : PostMngVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.common.common
 * 4. Comment  : 도로명주소VO
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 5:30:02
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@XStreamAlias("postMng")
public class PostMngVO extends PagingValue {
	/**
	 * 순서
	 */
	private String seq;
	/**
	 * 우편번호
	 */
	private String zipCd;
	/**
	 * 시도명
	 */
	private String sidoNm;
	/**
	 * 구군명
	 */
	private String gugunNm;
	/**
	 * 동명
	 */
	private String dongNm;
	/**
	 * 읍동면도로명
	 */
	private String dongmyonNm;
	/**
	 * 건물번호1
	 */
	private String bldgNo1;
	/**
	 * 건물번호2
	 */
	private String bldgNo2;
	/**
	 * 건물이름
	 */
	private String bldgNm;
	/**
	 * 검색여부
	 */
	private String srchYn;
	/**
	 * 주소 조건
	 */
	private String srchNm;
	/**
	 * 시도명
	 */
	private String str1;
	/**
	 * 구군명
	 */
	private String str2;
	/**
	 * 구군명(예)보통 XXXD이지만 구군명이 XXX XXX 일경우 
	 */
	private String str3;
	/**
	 * 동명
	 */
	private String str4;
	/**
	 * 읍동면도로명
	 */
	private String str5;
	/**
	 * 건물번호1
	 */
	private String str6;
	/**
	 * 건물번호2
	 */
	private String str7;
	/**
	 * 주소
	 */
	private String address;
	/**
	 * 건물명 조건
	 */
	private String srchBldg;
	/**
	 * 
	 */
	private String flag;
	/**
	 * 언어코드
	 */
	private String lng;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getSidoNm() {
		return sidoNm;
	}
	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}
	public String getGugunNm() {
		return gugunNm;
	}
	public void setGugunNm(String gugunNm) {
		this.gugunNm = gugunNm;
	}
	public String getDongNm() {
		return dongNm;
	}
	public void setDongNm(String dongNm) {
		this.dongNm = dongNm;
	}
	public String getDongmyonNm() {
		return dongmyonNm;
	}
	public void setDongmyonNm(String dongmyonNm) {
		this.dongmyonNm = dongmyonNm;
	}
	public String getBldgNo1() {
		return bldgNo1;
	}
	public void setBldgNo1(String bldgNo1) {
		this.bldgNo1 = bldgNo1;
	}
	public String getBldgNo2() {
		return bldgNo2;
	}
	public void setBldgNo2(String bldgNo2) {
		this.bldgNo2 = bldgNo2;
	}
	public String getBldgNm() {
		return bldgNm;
	}
	public void setBldgNm(String bldgNm) {
		this.bldgNm = bldgNm;
	}
	public String getSrchYn() {
		return srchYn;
	}
	public void setSrchYn(String srchYn) {
		this.srchYn = srchYn;
	}
	public String getSrchNm() {
		return srchNm;
	}
	public void setSrchNm(String srchNm) {
		this.srchNm = srchNm;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}
	public String getStr4() {
		return str4;
	}
	public void setStr4(String str4) {
		this.str4 = str4;
	}
	public String getStr5() {
		return str5;
	}
	public void setStr5(String str5) {
		this.str5 = str5;
	}
	public String getStr6() {
		return str6;
	}
	public void setStr6(String str6) {
		this.str6 = str6;
	}
	public String getStr7() {
		return str7;
	}
	public void setStr7(String str7) {
		this.str7 = str7;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSrchBldg() {
		return srchBldg;
	}
	public void setSrchBldg(String srchBldg) {
		this.srchBldg = srchBldg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostMngVO [seq=");
		builder.append(seq);
		builder.append(", zipCd=");
		builder.append(zipCd);
		builder.append(", sidoNm=");
		builder.append(sidoNm);
		builder.append(", gugunNm=");
		builder.append(gugunNm);
		builder.append(", dongNm=");
		builder.append(dongNm);
		builder.append(", dongmyonNm=");
		builder.append(dongmyonNm);
		builder.append(", bldgNo1=");
		builder.append(bldgNo1);
		builder.append(", bldgNo2=");
		builder.append(bldgNo2);
		builder.append(", bldgNm=");
		builder.append(bldgNm);
		builder.append(", srchYn=");
		builder.append(srchYn);
		builder.append(", srchNm=");
		builder.append(srchNm);
		builder.append(", str1=");
		builder.append(str1);
		builder.append(", str2=");
		builder.append(str2);
		builder.append(", str3=");
		builder.append(str3);
		builder.append(", str4=");
		builder.append(str4);
		builder.append(", str5=");
		builder.append(str5);
		builder.append(", str6=");
		builder.append(str6);
		builder.append(", str7=");
		builder.append(str7);
		builder.append(", address=");
		builder.append(address);
		builder.append(", srchBldg=");
		builder.append(srchBldg);
		builder.append(", flag=");
		builder.append(flag);
		builder.append(", lng=");
		builder.append(lng);
		builder.append("]");
		return builder.toString();
	}
	

	
}