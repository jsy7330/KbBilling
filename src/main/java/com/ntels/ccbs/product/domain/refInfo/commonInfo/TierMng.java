package com.ntels.ccbs.product.domain.refInfo.commonInfo;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;

public class TierMng extends PagingValue {


	private String regrId;
	private String chgrId;
	private Date regDate;
	private String currentDay;
	private Date   sysDate;
	private String commonGrpNm;
	private String commonGrp;
	private String lngTyp;
	private String actDt;
	private String inactDt;	
	private String soId;
	
	private String tierSetCd; 	//구간대그룹코드
	private String ver; 		//버젼
	private String tierSetNm; 	//구간대그룹명
	private String tierSegmtNo; //구간대번호
	private String tierStrtVal; //구간시작값
	private String tierEndVal;	//구간종료값
	private String tierLvl;		//구간대레벨
	private String unitVal;		//단위도수값
	private String unitRate;	//단위요율
	private String rate;		//요율
	private String unitAppYn; 	//단가적용여부
	private String soNm;

	private String searchProdNm;
	private String searchSoId;
	
	private String prodCd;
	private String prodNm;
	private String rateItmNm;
	private String commonCdNm;
	private String sortNo;
	private String applyDt;
	private String searchProdNmPop;
	private String searchSoIdPop;
	private String searchProdCd;
	private String searchMon;
	
	private String priceApply;
	private String priceApplyNm;
	
	public String getSearchMon() {
		return searchMon;
	}
	public void setSearchMon(String searchMon) {
		this.searchMon = searchMon;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}
	public Date getSysDate() {
		return sysDate;
	}
	public void setSysDate(Date sysDate) {
		this.sysDate = sysDate;
	}
	public String getCommonGrpNm() {
		return commonGrpNm;
	}
	public void setCommonGrpNm(String commonGrpNm) {
		this.commonGrpNm = commonGrpNm;
	}
	public String getCommonGrp() {
		return commonGrp;
	}
	public void setCommonGrp(String commonGrp) {
		this.commonGrp = commonGrp;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getActDt() {
		return actDt;
	}
	public void setActDt(String actDt) {
		this.actDt = actDt;
	}
	public String getInactDt() {
		return inactDt;
	}
	public void setInactDt(String inactDt) {
		this.inactDt = inactDt;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getTierSetCd() {
		return tierSetCd;
	}
	public void setTierSetCd(String tierSetCd) {
		this.tierSetCd = tierSetCd;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getTierSetNm() {
		return tierSetNm;
	}
	public void setTierSetNm(String tierSetNm) {
		this.tierSetNm = tierSetNm;
	}
	public String getTierSegmtNo() {
		return tierSegmtNo;
	}
	public void setTierSegmtNo(String tierSegmtNo) {
		this.tierSegmtNo = tierSegmtNo;
	}
	public String getTierStrtVal() {
		return tierStrtVal;
	}
	public void setTierStrtVal(String tierStrtVal) {
		this.tierStrtVal = tierStrtVal;
	}
	public String getTierEndVal() {
		return tierEndVal;
	}
	public void setTierEndVal(String tierEndVal) {
		this.tierEndVal = tierEndVal;
	}
	public String getTierLvl() {
		return tierLvl;
	}
	public void setTierLvl(String tierLvl) {
		this.tierLvl = tierLvl;
	}
	public String getUnitVal() {
		return unitVal;
	}
	public void setUnitVal(String unitVal) {
		this.unitVal = unitVal;
	}
	public String getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(String unitRate) {
		this.unitRate = unitRate;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getUnitAppYn() {
		return unitAppYn;
	}
	public void setUnitAppYn(String unitAppYn) {
		this.unitAppYn = unitAppYn;
	}
	public String getSearchSoId() {
		return searchSoId;
	}
	public void setSearchSoId(String searchSoId) {
		this.searchSoId = searchSoId;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getRateItmNm() {
		return rateItmNm;
	}
	public void setRateItmNm(String rateItmNm) {
		this.rateItmNm = rateItmNm;
	}
	public String getCommonCdNm() {
		return commonCdNm;
	}
	public void setCommonCdNm(String commonCdNm) {
		this.commonCdNm = commonCdNm;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	public String getSearchProdNm() {
		return searchProdNm;
	}
	public void setSearchProdNm(String searchProdNm) {
		this.searchProdNm = searchProdNm;
	}
	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	public String getSearchProdNmPop() {
		return searchProdNmPop;
	}
	public void setSearchProdNmPop(String searchProdNmPop) {
		this.searchProdNmPop = searchProdNmPop;
	}
	public String getSearchSoIdPop() {
		return searchSoIdPop;
	}
	public void setSearchSoIdPop(String searchSoIdPop) {
		this.searchSoIdPop = searchSoIdPop;
	}
	public String getSearchProdCd() {
		return searchProdCd;
	}
	public void setSearchProdCd(String searchProdCd) {
		this.searchProdCd = searchProdCd;
	}
	public String getPriceApply() {
		return priceApply;
	}
	public void setPriceApply(String priceApply) {
		this.priceApply = priceApply;
	}
	public String getPriceApplyNm() {
		return priceApplyNm;
	}
	public void setPriceApplyNm(String priceApplyNm) {
		this.priceApplyNm = priceApplyNm;
	}
}
