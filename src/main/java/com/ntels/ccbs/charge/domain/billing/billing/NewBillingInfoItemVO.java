package com.ntels.ccbs.charge.domain.billing.billing;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("NewBillingInfoItemVO")
public class NewBillingInfoItemVO extends PagingValue {

	private String soId;            // 사업
	private String soNm;            // 사업명
	private String setItmNm;      // 설정항목명
	private String newSetItmNm;    // 설정항목코드 + 설정항목명
	private String setItmCd;       // 항목코드
	private String eftBgnYymm;   // 효력발생월
	private String eftEndYymm;   // 효력종료월
	private String referenceType; // 설정위치
	private String referenceTypeCd; // 설정위치구분코드
	private String required;         // 필수여부
	private String requiredCd;         // 필수여부코드
	private String fieldNature;     // 필드성격
	private String fieldNatureCd;  // 필드성격구분코드
	private String fieldStructure; // 필드구조
	private String fieldDigit;        // 필드자릿수
	private String setVal;           // 설정값
	private String regId;             // 등록자
	private Date regDate;         // 등록일자
	private String chgId;            // 수정자
	private Date chgDate;        // 수정일자
	
	private String setLoc; //
	
	private String searchSoId;	// 사업ID(조회용)
	private String searchSetItmNm;  // 설정항목명(조회용)
	private String searchYymm;   // 기준년월(조회용)
	private String searchReferenceType; // 설정위치(조회용)
	
	private String lngTyp;
	private String sidx;
	private String sord;
	
	
	public String getRequiredCd() {
		return requiredCd;
	}
	public void setRequiredCd(String requiredCd) {
		this.requiredCd = requiredCd;
	}
	public String getNewSetItmNm() {
		return newSetItmNm;
	}
	public void setNewSetItmNm(String newSetItmNm) {
		this.newSetItmNm = newSetItmNm;
	}
	public String getReferenceTypeCd() {
		return referenceTypeCd;
	}
	public void setReferenceTypeCd(String referenceTypeCd) {
		this.referenceTypeCd = referenceTypeCd;
	}
	public String getFieldNatureCd() {
		return fieldNatureCd;
	}
	public void setFieldNatureCd(String fieldNatureCd) {
		this.fieldNatureCd = fieldNatureCd;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getFieldNature() {
		return fieldNature;
	}
	public void setFieldNature(String fieldNature) {
		this.fieldNature = fieldNature;
	}
	public String getFieldStructure() {
		return fieldStructure;
	}
	public void setFieldStructure(String fieldStructure) {
		this.fieldStructure = fieldStructure;
	}
	public String getFieldDigit() {
		return fieldDigit;
	}
	public void setFieldDigit(String fieldDigit) {
		this.fieldDigit = fieldDigit;
	}
	public String getSearchReferenceType() {
		return searchReferenceType;
	}
	public void setSearchReferenceType(String searchReferenceType) {
		this.searchReferenceType = searchReferenceType;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getSetItmNm() {
		return setItmNm;
	}
	public void setSetItmNm(String setItmNm) {
		this.setItmNm = setItmNm;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSearchSoId() {
		return searchSoId;
	}
	public void setSearchSoId(String searchSoId) {
		this.searchSoId = searchSoId;
	}
	public String getSearchSetItmNm() {
		return searchSetItmNm;
	}
	public void setSearchSetItmNm(String searchSetItmNm) {
		this.searchSetItmNm = searchSetItmNm;
	}
	public String getSearchYymm() {
		return searchYymm;
	}
	public void setSearchYymm(String searchYymm) {
		this.searchYymm = searchYymm;
	}
	public String getSetItmCd() {
		return setItmCd;
	}
	public void setSetItmCd(String setItmCd) {
		this.setItmCd = setItmCd;
	}
	public String getEftBgnYymm() {
		return eftBgnYymm;
	}
	public void setEftBgnYymm(String eftBgnYymm) {
		this.eftBgnYymm = eftBgnYymm;
	}
	public String getEftEndYymm() {
		return eftEndYymm;
	}
	public void setEftEndYymm(String eftEndYymm) {
		this.eftEndYymm = eftEndYymm;
	}
	public String getSetVal() {
		return setVal;
	}
	public void setSetVal(String setVal) {
		this.setVal = setVal;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getChgId() {
		return chgId;
	}
	public void setChgId(String chgId) {
		this.chgId = chgId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getChgDate() {
		return chgDate;
	}
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	public String getSetLoc() {
		return setLoc;
	}
	public void setSetLoc(String setLoc) {
		this.setLoc = setLoc;
	}
	
	
	
}
