package com.ntels.ccbs.product.domain.service.serviceMgt;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;

/**
 * <PRE>
 * 1. ClassName: ServiceMgt
 * 2. FileName : ServiceMgt.java
 * 3. Package  : com.ntels.ccbs.product.domain.service.serviceMgt
 * 4. Comment  :
 * 5. 작성자   : 정재훈
 * 6. 작성일   : 2016. 5. 12. 오후 3:09:04
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     정재훈 :    2016. 5. 12.    : 신규개발
 * </PRE>
 */
public class ServiceMgt extends PagingValue { 
	private String targetCd;
	private String targetUpperCd;
	private String targetMainSvcCd;
	private String targetChrgCtgry;
	private String targetNm;
	private String targetDispNo;
	private String targetGubun;
	private String svcTyp;
	private String inactDt;
	private String targetImageId;
	private String isFolder;
	private String mstrFl;
	private String targetSoId;
	private String treeLevel;
	
	private String lngTyp;
	private String currentDay;
	private String svcCd;	
	private String svcNm;
	private String svcAbbrNm;
	private String svcTypNm;
	private String dispPriNo;
	private String actDt;
	private String soId;
	private String mainSvcFl;
	private String svcEngNm;
	private String regrId;
	private String chgrId;
	private String subscripCmpsFl;
	private Date   sysdate;
	private String baseActDt;
	private String inActYn;

	private String saleItmCd;
	private String saleItmNm;
	private String saleItmAbbrNm;
	private String rateItmTypNm;
	private String rateItmTypCd;
	private String saleTyp;
	private String saleItmEngNm;
	
	private String svcRateItmTypCd;
	private String svcRateItmTypNm;
	private String svcRateItmTypAbbrNm;
	private String chrgCtgryNm;
	private String rateLocatNm;
	private String invItmNm;
	private String invItmCd;
	private String svcRateItmTypDesc;
	
	private String chrgCtgry;
	private String attrTyp;
	private String attrCd;
	private String attrNm;
	private String attrValNm;
	
	private String fctrCd;
	private String fctrNm;
	private String fctrRefTypNm;
	private String tableNm;
	private String colmnNm;
	private String dataTypNm;
	private String refTableId;
	private String refColmnId;
	private String refColmnNm;
	private String refTableCond;
	private String refNm;
	private String refFunc;
	private String dataTyp;
	private String attrVal;
	private String fctrRefTyp;
	private String tableId;
	private String colmnId;
	private String refCd;
	private String chgNullFl;
	private String chgNullVal;
	private String mainSvcCd;
	private String rateLocat;
	private String ratePriNo;
	private String payPriNo;
	private String nextDispPriNo;
	
	
	public String getTargetCd() {
		return targetCd;
	}
	public void setTargetCd(String targetCd) {
		this.targetCd = targetCd;
	}
	public String getTargetUpperCd() {
		return targetUpperCd;
	}
	public void setTargetUpperCd(String targetUpperCd) {
		this.targetUpperCd = targetUpperCd;
	}
	public String getTargetMainSvcCd() {
		return targetMainSvcCd;
	}
	public void setTargetMainSvcCd(String targetMainSvcCd) {
		this.targetMainSvcCd = targetMainSvcCd;
	}
	public String getTargetChrgCtgry() {
		return targetChrgCtgry;
	}
	public void setTargetChrgCtgry(String targetChrgCtgry) {
		this.targetChrgCtgry = targetChrgCtgry;
	}
	public String getTargetNm() {
		return targetNm;
	}
	public void setTargetNm(String targetNm) {
		this.targetNm = targetNm;
	}
	public String getTargetDispNo() {
		return targetDispNo;
	}
	public void setTargetDispNo(String targetDispNo) {
		this.targetDispNo = targetDispNo;
	}
	public String getTargetGubun() {
		return targetGubun;
	}
	public void setTargetGubun(String targetGubun) {
		this.targetGubun = targetGubun;
	}
	public String getSvcTyp() {
		return svcTyp;
	}
	public void setSvcTyp(String svcTyp) {
		this.svcTyp = svcTyp;
	}
	public String getInactDt() {
		return inactDt;
	}
	public void setInactDt(String inactDt) {
		this.inactDt = inactDt;
	}
	public String getTargetImageId() {
		return targetImageId;
	}
	public void setTargetImageId(String targetImageId) {
		this.targetImageId = targetImageId;
	}
	public String getIsFolder() {
		return isFolder;
	}
	public void setIsFolder(String isFolder) {
		this.isFolder = isFolder;
	}
	public String getMstrFl() {
		return mstrFl;
	}
	public void setMstrFl(String mstrFl) {
		this.mstrFl = mstrFl;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}
	public String getSvcCd() {
		return svcCd;
	}
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}
	public String getSvcNm() {
		return svcNm;
	}
	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}
	public String getSvcAbbrNm() {
		return svcAbbrNm;
	}
	public void setSvcAbbrNm(String svcAbbrNm) {
		this.svcAbbrNm = svcAbbrNm;
	}
	public String getSvcTypNm() {
		return svcTypNm;
	}
	public void setSvcTypNm(String svcTypNm) {
		this.svcTypNm = svcTypNm;
	}
	public String getDispPriNo() {
		return dispPriNo;
	}
	public void setDispPriNo(String dispPriNo) {
		this.dispPriNo = dispPriNo;
	}
	public String getActDt() {
		return actDt;
	}
	public void setActDt(String actDt) {
		this.actDt = actDt;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getMainSvcFl() {
		return mainSvcFl;
	}
	public void setMainSvcFl(String mainSvcFl) {
		this.mainSvcFl = mainSvcFl;
	}
	public String getSvcEngNm() {
		return svcEngNm;
	}
	public void setSvcEngNm(String svcEngNm) {
		this.svcEngNm = svcEngNm;
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
	public String getSubscripCmpsFl() {
		return subscripCmpsFl;
	}
	public void setSubscripCmpsFl(String subscripCmpsFl) {
		this.subscripCmpsFl = subscripCmpsFl;
	}
	public Date getSysdate() {
		return sysdate;
	}
	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}
	public String getBaseActDt() {
		return baseActDt;
	}
	public void setBaseActDt(String baseActDt) {
		this.baseActDt = baseActDt;
	}
	public String getInActYn() {
		return inActYn;
	}
	public void setInActYn(String inActYn) {
		this.inActYn = inActYn;
	}
	public String getTargetSoId() {
		return targetSoId;
	}
	public void setTargetSoId(String targetSoId) {
		this.targetSoId = targetSoId;
	}
	public String getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(String treeLevel) {
		this.treeLevel = treeLevel;
	}
	public String getSaleItmCd() {
		return saleItmCd;
	}
	public void setSaleItmCd(String saleItmCd) {
		this.saleItmCd = saleItmCd;
	}
	public String getSaleItmNm() {
		return saleItmNm;
	}
	public void setSaleItmNm(String saleItmNm) {
		this.saleItmNm = saleItmNm;
	}
	public String getSaleItmAbbrNm() {
		return saleItmAbbrNm;
	}
	public void setSaleItmAbbrNm(String saleItmAbbrNm) {
		this.saleItmAbbrNm = saleItmAbbrNm;
	}
	public String getRateItmTypNm() {
		return rateItmTypNm;
	}
	public void setRateItmTypNm(String rateItmTypNm) {
		this.rateItmTypNm = rateItmTypNm;
	}
	public String getSaleTyp() {
		return saleTyp;
	}
	public void setSaleTyp(String saleTyp) {
		this.saleTyp = saleTyp;
	}
	public String getRateItmTypCd() {
		return rateItmTypCd;
	}
	public void setRateItmTypCd(String rateItmTypCd) {
		this.rateItmTypCd = rateItmTypCd;
	}
	public String getSaleItmEngNm() {
		return saleItmEngNm;
	}
	public void setSaleItmEngNm(String saleItmEngNm) {
		this.saleItmEngNm = saleItmEngNm;
	}
	public String getSvcRateItmTypCd() {
		return svcRateItmTypCd;
	}
	public void setSvcRateItmTypCd(String svcRateItmTypCd) {
		this.svcRateItmTypCd = svcRateItmTypCd;
	}
	public String getSvcRateItmTypNm() {
		return svcRateItmTypNm;
	}
	public void setSvcRateItmTypNm(String svcRateItmTypNm) {
		this.svcRateItmTypNm = svcRateItmTypNm;
	}
	public String getSvcRateItmTypAbbrNm() {
		return svcRateItmTypAbbrNm;
	}
	public void setSvcRateItmTypAbbrNm(String svcRateItmTypAbbrNm) {
		this.svcRateItmTypAbbrNm = svcRateItmTypAbbrNm;
	}
	public String getChrgCtgryNm() {
		return chrgCtgryNm;
	}
	public void setChrgCtgryNm(String chrgCtgryNm) {
		this.chrgCtgryNm = chrgCtgryNm;
	}
	public String getRateLocatNm() {
		return rateLocatNm;
	}
	public void setRateLocatNm(String rateLocatNm) {
		this.rateLocatNm = rateLocatNm;
	}
	public String getInvItmNm() {
		return invItmNm;
	}
	public void setInvItmNm(String invItmNm) {
		this.invItmNm = invItmNm;
	}
	public String getInvItmCd() {
		return invItmCd;
	}
	public void setInvItmCd(String invItmCd) {
		this.invItmCd = invItmCd;
	}
	public String getSvcRateItmTypDesc() {
		return svcRateItmTypDesc;
	}
	public void setSvcRateItmTypDesc(String svcRateItmTypDesc) {
		this.svcRateItmTypDesc = svcRateItmTypDesc;
	}
	public String getChrgCtgry() {
		return chrgCtgry;
	}
	public void setChrgCtgry(String chrgCtgry) {
		this.chrgCtgry = chrgCtgry;
	}
	public String getAttrTyp() {
		return attrTyp;
	}
	public void setAttrTyp(String attrTyp) {
		this.attrTyp = attrTyp;
	}
	public String getAttrCd() {
		return attrCd;
	}
	public void setAttrCd(String attrCd) {
		this.attrCd = attrCd;
	}
	public String getAttrNm() {
		return attrNm;
	}
	public void setAttrNm(String attrNm) {
		this.attrNm = attrNm;
	}
	public String getAttrValNm() {
		return attrValNm;
	}
	public void setAttrValNm(String attrValNm) {
		this.attrValNm = attrValNm;
	}
	public String getFctrCd() {
		return fctrCd;
	}
	public void setFctrCd(String fctrCd) {
		this.fctrCd = fctrCd;
	}
	public String getFctrNm() {
		return fctrNm;
	}
	public void setFctrNm(String fctrNm) {
		this.fctrNm = fctrNm;
	}
	public String getFctrRefTypNm() {
		return fctrRefTypNm;
	}
	public void setFctrRefTypNm(String fctrRefTypNm) {
		this.fctrRefTypNm = fctrRefTypNm;
	}
	public String getTableNm() {
		return tableNm;
	}
	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}
	public String getColmnNm() {
		return colmnNm;
	}
	public void setColmnNm(String colmnNm) {
		this.colmnNm = colmnNm;
	}
	public String getDataTypNm() {
		return dataTypNm;
	}
	public void setDataTypNm(String dataTypNm) {
		this.dataTypNm = dataTypNm;
	}
	public String getRefTableId() {
		return refTableId;
	}
	public void setRefTableId(String refTableId) {
		this.refTableId = refTableId;
	}
	public String getRefColmnId() {
		return refColmnId;
	}
	public void setRefColmnId(String refColmnId) {
		this.refColmnId = refColmnId;
	}
	public String getRefColmnNm() {
		return refColmnNm;
	}
	public void setRefColmnNm(String refColmnNm) {
		this.refColmnNm = refColmnNm;
	}
	public String getRefTableCond() {
		return refTableCond;
	}
	public void setRefTableCond(String refTableCond) {
		this.refTableCond = refTableCond;
	}
	public String getRefNm() {
		return refNm;
	}
	public void setRefNm(String refNm) {
		this.refNm = refNm;
	}
	public String getRefFunc() {
		return refFunc;
	}
	public void setRefFunc(String refFunc) {
		this.refFunc = refFunc;
	}
	public String getDataTyp() {
		return dataTyp;
	}
	public void setDataTyp(String dataTyp) {
		this.dataTyp = dataTyp;
	}
	public String getAttrVal() {
		return attrVal;
	}
	public void setAttrVal(String attrVal) {
		this.attrVal = attrVal;
	}
	public String getFctrRefTyp() {
		return fctrRefTyp;
	}
	public void setFctrRefTyp(String fctrRefTyp) {
		this.fctrRefTyp = fctrRefTyp;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getColmnId() {
		return colmnId;
	}
	public void setColmnId(String colmnId) {
		this.colmnId = colmnId;
	}
	public String getRefCd() {
		return refCd;
	}
	public void setRefCd(String refCd) {
		this.refCd = refCd;
	}
	public String getChgNullFl() {
		return chgNullFl;
	}
	public void setChgNullFl(String chgNullFl) {
		this.chgNullFl = chgNullFl;
	}
	public String getChgNullVal() {
		return chgNullVal;
	}
	public void setChgNullVal(String chgNullVal) {
		this.chgNullVal = chgNullVal;
	}
	public String getMainSvcCd() {
		return mainSvcCd;
	}
	public void setMainSvcCd(String mainSvcCd) {
		this.mainSvcCd = mainSvcCd;
	}
	public String getRateLocat() {
		return rateLocat;
	}
	public void setRateLocat(String rateLocat) {
		this.rateLocat = rateLocat;
	}
	public String getRatePriNo() {
		return ratePriNo;
	}
	public void setRatePriNo(String ratePriNo) {
		this.ratePriNo = ratePriNo;
	}
	public String getPayPriNo() {
		return payPriNo;
	}
	public void setPayPriNo(String payPriNo) {
		this.payPriNo = payPriNo;
	}
	public String getNextDispPriNo() {
		return nextDispPriNo;
	}
	public void setNextDispPriNo(String nextDispPriNo) {
		this.nextDispPriNo = nextDispPriNo;
	}	
	
	
	
}
