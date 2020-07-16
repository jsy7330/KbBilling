package com.ntels.ccbs.system.domain.common.common;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ManufacturerMngVO")
public class ManufacturerMngVO  extends PagingValue {

	private String soId;				//사업ID
	private String mncoId;				//제조사ID
	private String mncoNm;				//제조사명
	private String borNo;				//사업자번호
	private String repNm;				//대표자명
	private String repTelNo;			//대표전화번호
	private String repFaxNo;			//대표팩스번호
	private String busiCndt;			//업태
	private String busiTp;				//업종
	
	private String soNm;				//사업명
	private String searchType;			//검색타입
	private String searchText;			//검색값
	
	private String returnId1;			//리턴값1
	private String returnId2;			//리턴값2
	
	private String lngTyp;
	private String sidx;
	private String sord;
	private String popType;				//팝업 타입
	
	public String getReturnId1() {
		return returnId1;
	}
	public void setReturnId1(String returnId1) {
		this.returnId1 = returnId1;
	}
	public String getReturnId2() {
		return returnId2;
	}
	public void setReturnId2(String returnId2) {
		this.returnId2 = returnId2;
	}
	public String getPopType() {
		return popType;
	}
	public void setPopType(String popType) {
		this.popType = popType;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getMncoId() {
		return mncoId;
	}
	public void setMncoId(String mncoId) {
		this.mncoId = mncoId;
	}
	public String getMncoNm() {
		return mncoNm;
	}
	public void setMncoNm(String mncoNm) {
		this.mncoNm = mncoNm;
	}
	public String getBorNo() {
		return borNo;
	}
	public void setBorNo(String borNo) {
		this.borNo = borNo;
	}
	public String getRepNm() {
		return repNm;
	}
	public void setRepNm(String repNm) {
		this.repNm = repNm;
	}
	public String getRepTelNo() {
		return repTelNo;
	}
	public void setRepTelNo(String repTelNo) {
		this.repTelNo = repTelNo;
	}
	public String getRepFaxNo() {
		return repFaxNo;
	}
	public void setRepFaxNo(String repFaxNo) {
		this.repFaxNo = repFaxNo;
	}
	public String getBusiCndt() {
		return busiCndt;
	}
	public void setBusiCndt(String busiCndt) {
		this.busiCndt = busiCndt;
	}
	public String getBusiTp() {
		return busiTp;
	}
	public void setBusiTp(String busiTp) {
		this.busiTp = busiTp;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
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
	
	
}
