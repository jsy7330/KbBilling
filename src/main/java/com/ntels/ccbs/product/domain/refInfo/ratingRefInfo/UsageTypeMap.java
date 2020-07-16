package com.ntels.ccbs.product.domain.refInfo.ratingRefInfo;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("UsageTypeMap")
public class UsageTypeMap extends PagingValue {
	@NotEmpty
	@Length(min=4,max=20)
	private String userId;
	private Integer page;
	private Integer perPage;
	private String sidx;
	private String sord;
	private String lngTyp;
	private Date sysToDate;
	
	private String dataNm;
	private String seqNo;
	private String fld1;
	private String fld2;
	private String fld3;
	private String effDt;
	private String expDt;
	private String usgTyp;
	private String usgTypNm;
	
	private String searchDataNm;
	private String searchSeqNo;
	private String searchFld1;
	private String searchFld2;
	private String searchFld3;
	private String searchEffDt;
	
	private String insertDataNm;
	private String insertSeqNo;
	private String insertFld1;
	private String insertFld2;
	private String insertFld3;
	private String insertEffDt;
	private String insertExpDt;
	private String insertUsgTyp;
	
	private String modDataNm;
	private String modSeqNo;
	private String modFld1;
	private String modFld2;
	private String modFld3;
	private String modEffDt;
	private String modExpDt;
	private String modUsgTyp;
	
	private String delDataNm;
	private String delSeqNo;
	private String delFld1;
	private String delFld2;
	private String delFld3;
	private String delEffDt;
	
	public String getUsgTypNm() {
		return usgTypNm;
	}
	public void setUsgTypNm(String usgTypNm) {
		this.usgTypNm = usgTypNm;
	}
	public String getDelDataNm() {
		return delDataNm;
	}
	public void setDelDataNm(String delDataNm) {
		this.delDataNm = delDataNm;
	}
	public String getDelSeqNo() {
		return delSeqNo;
	}
	public void setDelSeqNo(String delSeqNo) {
		this.delSeqNo = delSeqNo;
	}
	public String getDelFld1() {
		return delFld1;
	}
	public void setDelFld1(String delFld1) {
		this.delFld1 = delFld1;
	}
	public String getDelFld2() {
		return delFld2;
	}
	public void setDelFld2(String delFld2) {
		this.delFld2 = delFld2;
	}
	public String getDelFld3() {
		return delFld3;
	}
	public void setDelFld3(String delFld3) {
		this.delFld3 = delFld3;
	}
	public String getDelEffDt() {
		return delEffDt;
	}
	public void setDelEffDt(String delEffDt) {
		this.delEffDt = delEffDt;
	}
	public String getModDataNm() {
		return modDataNm;
	}
	public void setModDataNm(String modDataNm) {
		this.modDataNm = modDataNm;
	}
	public String getModSeqNo() {
		return modSeqNo;
	}
	public void setModSeqNo(String modSeqNo) {
		this.modSeqNo = modSeqNo;
	}
	public String getModFld1() {
		return modFld1;
	}
	public void setModFld1(String modFld1) {
		this.modFld1 = modFld1;
	}
	public String getModFld2() {
		return modFld2;
	}
	public void setModFld2(String modFld2) {
		this.modFld2 = modFld2;
	}
	public String getModFld3() {
		return modFld3;
	}
	public void setModFld3(String modFld3) {
		this.modFld3 = modFld3;
	}
	public String getModEffDt() {
		return modEffDt;
	}
	public void setModEffDt(String modEffDt) {
		this.modEffDt = modEffDt;
	}
	public String getModExpDt() {
		return modExpDt;
	}
	public void setModExpDt(String modExpDt) {
		this.modExpDt = modExpDt;
	}
	public String getModUsgTyp() {
		return modUsgTyp;
	}
	public void setModUsgTyp(String modUsgTyp) {
		this.modUsgTyp = modUsgTyp;
	}
	public String getInsertDataNm() {
		return insertDataNm;
	}
	public void setInsertDataNm(String insertDataNm) {
		this.insertDataNm = insertDataNm;
	}
	public String getInsertSeqNo() {
		return insertSeqNo;
	}
	public void setInsertSeqNo(String insertSeqNo) {
		this.insertSeqNo = insertSeqNo;
	}
	public String getInsertFld1() {
		return insertFld1;
	}
	public void setInsertFld1(String insertFld1) {
		this.insertFld1 = insertFld1;
	}
	public String getInsertFld2() {
		return insertFld2;
	}
	public void setInsertFld2(String insertFld2) {
		this.insertFld2 = insertFld2;
	}
	public String getInsertFld3() {
		return insertFld3;
	}
	public void setInsertFld3(String insertFld3) {
		this.insertFld3 = insertFld3;
	}
	public String getInsertEffDt() {
		return insertEffDt;
	}
	public void setInsertEffDt(String insertEffDt) {
		this.insertEffDt = insertEffDt;
	}
	public String getInsertExpDt() {
		return insertExpDt;
	}
	public void setInsertExpDt(String insertExpDt) {
		this.insertExpDt = insertExpDt;
	}
	public String getInsertUsgTyp() {
		return insertUsgTyp;
	}
	public void setInsertUsgTyp(String insertUsgTyp) {
		this.insertUsgTyp = insertUsgTyp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
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
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public Date getSysToDate() {
		return sysToDate;
	}
	public void setSysToDate(Date sysToDate) {
		this.sysToDate = sysToDate;
	}
	public String getDataNm() {
		return dataNm;
	}
	public void setDataNm(String dataNm) {
		this.dataNm = dataNm;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getFld1() {
		return fld1;
	}
	public void setFld1(String fld1) {
		this.fld1 = fld1;
	}
	public String getFld2() {
		return fld2;
	}
	public void setFld2(String fld2) {
		this.fld2 = fld2;
	}
	public String getFld3() {
		return fld3;
	}
	public void setFld3(String fld3) {
		this.fld3 = fld3;
	}
	public String getEffDt() {
		return effDt;
	}
	public void setEffDt(String effDt) {
		this.effDt = effDt;
	}
	public String getExpDt() {
		return expDt;
	}
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}
	public String getUsgTyp() {
		return usgTyp;
	}
	public void setUsgTyp(String usgTyp) {
		this.usgTyp = usgTyp;
	}
	public String getSearchDataNm() {
		return searchDataNm;
	}
	public void setSearchDataNm(String searchDataNm) {
		this.searchDataNm = searchDataNm;
	}
	public String getSearchSeqNo() {
		return searchSeqNo;
	}
	public void setSearchSeqNo(String searchSeqNo) {
		this.searchSeqNo = searchSeqNo;
	}
	public String getSearchFld1() {
		return searchFld1;
	}
	public void setSearchFld1(String searchFld1) {
		this.searchFld1 = searchFld1;
	}
	public String getSearchFld2() {
		return searchFld2;
	}
	public void setSearchFld2(String searchFld2) {
		this.searchFld2 = searchFld2;
	}
	public String getSearchFld3() {
		return searchFld3;
	}
	public void setSearchFld3(String searchFld3) {
		this.searchFld3 = searchFld3;
	}
	public String getSearchEffDt() {
		return searchEffDt;
	}
	public void setSearchEffDt(String searchEffDt) {
		this.searchEffDt = searchEffDt;
	}

}
