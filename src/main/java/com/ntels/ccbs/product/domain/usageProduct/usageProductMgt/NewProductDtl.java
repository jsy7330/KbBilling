package com.ntels.ccbs.product.domain.usageProduct.usageProductMgt;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("NewProductDtl")
public class NewProductDtl extends PagingValue {
	//@NotEmpty
	//@Length(min=4,max=20)
	private String userId;
	private Integer page;
	private Integer perPage;
	private String sidx;
	private String sord;
	private String lngTyp;
	private Date sysToDate;
	private String currentDay;
	
	private String prodId;
	private String prodNm;
	private String prodFamily;
	private String description;
	private String prodState;
	private String effDt;
	private String expDt;
	private String recurringChrg;
	private String prorateFlag;
	private String prodOfferTyp;
	private String lmtCtrlTyp;
	private String prodPurchCond;
	private String prodPriority;
	private String precondProdTyp;
	private String precondProd;
	private String rowType;
	private String soId;
	private String soNm;
	private String basicProdFl;
	private String attrNm;
	private String usgTyp;
	private String usgTypNm;
	private String regYn;
	private String chrgCd;
	private String chrgCdNm;
	private String calendarDefId;
	private String multipleRateInd;
	private String tierSetId;
	private String tierStepFlag;
	private String usgMeasureUnit;
	private String currencyCd;
	private String periodApplMethod;
	private String usgRoundingMethod;
	private String commRateFlag;
	private String usgFeeRoundingMethod;
	private String discFlag;
	private String multipleDiscFlag;
	private String multipleDiscCriteria;
	private String usgFeeRoundingOffset;
	private String dedtFlag;
	private String prorateApplGrp;
	private String dedtRateId;
	private String lmtFlag;
	private String crossDiscFlag;
	private String realTmAccFlag;
	private String maxReserveAmt;
	private String unitSvcCd;
	private String vatRate;
	private String rateFac;
	private String rateFacNm;
	private String rateFacFlag;
	private String rateFacFlagNm;
	private String count;
	private String actDay;
	private String deactDay;
	private String periodDefId;
	private String periodCd;
	private String tierSegmentId;
	private String corrId;
	private String callingZone;
	private String calledZone;
	private String sameGrp;
	private String extraRateFac;
	private String rateId;
	private String ratePerUnit;
	private String usgBillingIncrement;
	private String firstUnit;
	private String firstRate;
	private String minChrg;
	private String rateItmCd;
	private String rateItmNm;
	
	private String mapCd;
	private String mapEffDt;
	private String mapExpDt;
	private String mapDiscDedtPrty;
	private String mapDiscDedtCd;
	private String mapShareFlag;
	private String mapHotOperator;
	private String mapProdId;
	private String discDedtCd;
	private String discDedtNm;
	private String usgTypLvl;
	private String usgTypLvlNm;
	private String usgTypGrpCd;
	private String usgItmTyp;
	private String usgItmTypNm;
	private String usgItmCd;
	private String usgItmCdNm;
	private String dedtDiscFlag;
	private String freeTyp;
	private String condRateFac1;
	private String condRateFacNm1;
	private String condOperator1;
	private String condOperatorNm1;
	private String condVal1;
	private String logicalOperator12;
	private String logicalOperatorNm12;
	private String condRateFac2;
	private String condRateFacNm2;
	private String condOperator2;
	private String condOperatorNm2;
	private String condVal2;
	private String quantity;
	private String applLvl;
	private String freeOfChrgFlag;
	private String replenishCycl;
	private String replenishCyclNm;
	private String amtFlag;
	private String amtFlagNm;
	private String amtUnit;
	private String amtUnitNm;
	private String voiceCallDedtFlag;
	private String applDiscFlag;
	private String balCrOvrMethod;
	private String balCrOvrPeriod;
	private String maxAccumBal;
	private String subscProrateFlag;
	private String termProrateFlag;
	private String dedtTyp;
	private String smsNotiCd;
	private String currOrNextCycl;
	private String durationUnit;
	private String durationUnitNm;
	private String duration;
	private String usgTypGrpCdNm;
	private String lmtCd;
	private String usgTypCtrlLvl;
	private String ctrlUsgTypGrpCd;
	private String ctrlUsgTypGrpCdNm;
	private String svcCtrlMethod;
	private String warningThreshold1;
	private String warningThreshold2;
	private String warningThresholdUnit;
	private String warningFreq;
	private String warningFreqUnit;
	private String effDays;
	private String rechrgLmtTypCd;
	private String rechrgLmtAmt;
	private String lmtTyp;
	private String usgTypCtrlLvlNm;
	private String searchSoId;
	private String searchProdCd;
	private String searchProdNm;
	private String searchProdId;
	private String searchDiscDedtNm;
	private String mapDiscPrty;
	private String multipleDiscMethod;
	private String tmDivisionFlag;
	private String segmentFlag;
	private String prodCd;
	private String mappedInfo;
	private String searchDiscDedtCd;
	private String searchDiscDedtCdNm;
	private String changedTag;

	private List<Map<String, Object>> updateUsgTypValList;
	private List<Map<String, Object>> updateRateFacValList; 
	private List<Map<String, Object>> updatePeriodValList;
	private List<Map<String, Object>> updateRateInfoValList;
	private List<Map<String, Object>> updateDiscDefValList;
	private List<Map<String, Object>> updateDedtDefValList;
	private List<Map<String, Object>> updateLmtValList;
	
	public String getUserId() {
		return userId;
	}
	public String getChangedTag() {
		return changedTag;
	}
	public void setChangedTag(String changedTag) {
		this.changedTag = changedTag;
	}
	public String getRateItmCd() {
		return rateItmCd;
	}
	public void setRateItmCd(String rateItmCd) {
		this.rateItmCd = rateItmCd;
	}
	public String getRateItmNm() {
		return rateItmNm;
	}
	public void setRateItmNm(String rateItmNm) {
		this.rateItmNm = rateItmNm;
	}
	public String getSearchDiscDedtCd() {
		return searchDiscDedtCd;
	}
	public void setSearchDiscDedtCd(String searchDiscDedtCd) {
		this.searchDiscDedtCd = searchDiscDedtCd;
	}
	public String getSearchDiscDedtCdNm() {
		return searchDiscDedtCdNm;
	}
	public void setSearchDiscDedtCdNm(String searchDiscDedtCdNm) {
		this.searchDiscDedtCdNm = searchDiscDedtCdNm;
	}
	public String getMappedInfo() {
		return mappedInfo;
	}
	public void setMappedInfo(String mappedInfo) {
		this.mappedInfo = mappedInfo;
	}
	public String getCtrlUsgTypGrpCdNm() {
		return ctrlUsgTypGrpCdNm;
	}
	public void setCtrlUsgTypGrpCdNm(String ctrlUsgTypGrpCdNm) {
		this.ctrlUsgTypGrpCdNm = ctrlUsgTypGrpCdNm;
	}
	public List<Map<String, Object>> getUpdateLmtValList() {
		return updateLmtValList;
	}
	public void setUpdateLmtValList(List<Map<String, Object>> updateLmtValList) {
		this.updateLmtValList = updateLmtValList;
	}
	public List<Map<String, Object>> getUpdateDiscDefValList() {
		return updateDiscDefValList;
	}
	public void setUpdateDiscDefValList(
			List<Map<String, Object>> updateDiscDefValList) {
		this.updateDiscDefValList = updateDiscDefValList;
	}
	public String getCondRateFacNm1() {
		return condRateFacNm1;
	}
	public void setCondRateFacNm1(String condRateFacNm1) {
		this.condRateFacNm1 = condRateFacNm1;
	}
	public String getCondRateFacNm2() {
		return condRateFacNm2;
	}
	public void setCondRateFacNm2(String condRateFacNm2) {
		this.condRateFacNm2 = condRateFacNm2;
	}
	public String getCondOperatorNm1() {
		return condOperatorNm1;
	}
	public void setCondOperatorNm1(String condOperatorNm1) {
		this.condOperatorNm1 = condOperatorNm1;
	}
	public String getLogicalOperatorNm12() {
		return logicalOperatorNm12;
	}
	public void setLogicalOperatorNm12(String logicalOperatorNm12) {
		this.logicalOperatorNm12 = logicalOperatorNm12;
	}
	public String getCondOperatorNm2() {
		return condOperatorNm2;
	}
	public void setCondOperatorNm2(String condOperatorNm2) {
		this.condOperatorNm2 = condOperatorNm2;
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
	public String getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getProdFamily() {
		return prodFamily;
	}
	public void setProdFamily(String prodFamily) {
		this.prodFamily = prodFamily;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProdState() {
		return prodState;
	}
	public void setProdState(String prodState) {
		this.prodState = prodState;
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
	public String getRecurringChrg() {
		return recurringChrg;
	}
	public void setRecurringChrg(String recurringChrg) {
		this.recurringChrg = recurringChrg;
	}
	public String getProrateFlag() {
		return prorateFlag;
	}
	public void setProrateFlag(String prorateFlag) {
		this.prorateFlag = prorateFlag;
	}
	public String getProdOfferTyp() {
		return prodOfferTyp;
	}
	public void setProdOfferTyp(String prodOfferTyp) {
		this.prodOfferTyp = prodOfferTyp;
	}
	public String getLmtCtrlTyp() {
		return lmtCtrlTyp;
	}
	public void setLmtCtrlTyp(String lmtCtrlTyp) {
		this.lmtCtrlTyp = lmtCtrlTyp;
	}
	public String getProdPurchCond() {
		return prodPurchCond;
	}
	public void setProdPurchCond(String prodPurchCond) {
		this.prodPurchCond = prodPurchCond;
	}
	public String getProdPriority() {
		return prodPriority;
	}
	public void setProdPriority(String prodPriority) {
		this.prodPriority = prodPriority;
	}
	public String getPrecondProdTyp() {
		return precondProdTyp;
	}
	public void setPrecondProdTyp(String precondProdTyp) {
		this.precondProdTyp = precondProdTyp;
	}
	public String getPrecondProd() {
		return precondProd;
	}
	public void setPrecondProd(String precondProd) {
		this.precondProd = precondProd;
	}
	public String getRowType() {
		return rowType;
	}
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getBasicProdFl() {
		return basicProdFl;
	}
	public void setBasicProdFl(String basicProdFl) {
		this.basicProdFl = basicProdFl;
	}
	public String getAttrNm() {
		return attrNm;
	}
	public void setAttrNm(String attrNm) {
		this.attrNm = attrNm;
	}
	public String getUsgTyp() {
		return usgTyp;
	}
	public void setUsgTyp(String usgTyp) {
		this.usgTyp = usgTyp;
	}
	public String getUsgTypNm() {
		return usgTypNm;
	}
	public void setUsgTypNm(String usgTypNm) {
		this.usgTypNm = usgTypNm;
	}
	public String getRegYn() {
		return regYn;
	}
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	public String getChrgCd() {
		return chrgCd;
	}
	public void setChrgCd(String chrgCd) {
		this.chrgCd = chrgCd;
	}
	public String getChrgCdNm() {
		return chrgCdNm;
	}
	public void setChrgCdNm(String chrgCdNm) {
		this.chrgCdNm = chrgCdNm;
	}
	public String getCalendarDefId() {
		return calendarDefId;
	}
	public void setCalendarDefId(String calendarDefId) {
		this.calendarDefId = calendarDefId;
	}
	public String getMultipleRateInd() {
		return multipleRateInd;
	}
	public void setMultipleRateInd(String multipleRateInd) {
		this.multipleRateInd = multipleRateInd;
	}
	public String getTierSetId() {
		return tierSetId;
	}
	public void setTierSetId(String tierSetId) {
		this.tierSetId = tierSetId;
	}
	public String getTierStepFlag() {
		return tierStepFlag;
	}
	public void setTierStepFlag(String tierStepFlag) {
		this.tierStepFlag = tierStepFlag;
	}
	public String getUsgMeasureUnit() {
		return usgMeasureUnit;
	}
	public void setUsgMeasureUnit(String usgMeasureUnit) {
		this.usgMeasureUnit = usgMeasureUnit;
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public String getPeriodApplMethod() {
		return periodApplMethod;
	}
	public void setPeriodApplMethod(String periodApplMethod) {
		this.periodApplMethod = periodApplMethod;
	}
	public String getUsgRoundingMethod() {
		return usgRoundingMethod;
	}
	public void setUsgRoundingMethod(String usgRoundingMethod) {
		this.usgRoundingMethod = usgRoundingMethod;
	}
	public String getCommRateFlag() {
		return commRateFlag;
	}
	public void setCommRateFlag(String commRateFlag) {
		this.commRateFlag = commRateFlag;
	}
	public String getUsgFeeRoundingMethod() {
		return usgFeeRoundingMethod;
	}
	public void setUsgFeeRoundingMethod(String usgFeeRoundingMethod) {
		this.usgFeeRoundingMethod = usgFeeRoundingMethod;
	}
	public String getDiscFlag() {
		return discFlag;
	}
	public void setDiscFlag(String discFlag) {
		this.discFlag = discFlag;
	}
	public String getMultipleDiscFlag() {
		return multipleDiscFlag;
	}
	public void setMultipleDiscFlag(String multipleDiscFlag) {
		this.multipleDiscFlag = multipleDiscFlag;
	}
	public String getMultipleDiscCriteria() {
		return multipleDiscCriteria;
	}
	public void setMultipleDiscCriteria(String multipleDiscCriteria) {
		this.multipleDiscCriteria = multipleDiscCriteria;
	}
	public String getUsgFeeRoundingOffset() {
		return usgFeeRoundingOffset;
	}
	public void setUsgFeeRoundingOffset(String usgFeeRoundingOffset) {
		this.usgFeeRoundingOffset = usgFeeRoundingOffset;
	}
	public String getDedtFlag() {
		return dedtFlag;
	}
	public void setDedtFlag(String dedtFlag) {
		this.dedtFlag = dedtFlag;
	}
	public String getProrateApplGrp() {
		return prorateApplGrp;
	}
	public void setProrateApplGrp(String prorateApplGrp) {
		this.prorateApplGrp = prorateApplGrp;
	}
	public String getDedtRateId() {
		return dedtRateId;
	}
	public void setDedtRateId(String dedtRateId) {
		this.dedtRateId = dedtRateId;
	}
	public String getLmtFlag() {
		return lmtFlag;
	}
	public void setLmtFlag(String lmtFlag) {
		this.lmtFlag = lmtFlag;
	}
	public String getCrossDiscFlag() {
		return crossDiscFlag;
	}
	public void setCrossDiscFlag(String crossDiscFlag) {
		this.crossDiscFlag = crossDiscFlag;
	}
	public String getRealTmAccFlag() {
		return realTmAccFlag;
	}
	public void setRealTmAccFlag(String realTmAccFlag) {
		this.realTmAccFlag = realTmAccFlag;
	}
	public String getMaxReserveAmt() {
		return maxReserveAmt;
	}
	public void setMaxReserveAmt(String maxReserveAmt) {
		this.maxReserveAmt = maxReserveAmt;
	}
	public String getUnitSvcCd() {
		return unitSvcCd;
	}
	public void setUnitSvcCd(String unitSvcCd) {
		this.unitSvcCd = unitSvcCd;
	}
	public String getVatRate() {
		return vatRate;
	}
	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
	}
	public String getRateFac() {
		return rateFac;
	}
	public void setRateFac(String rateFac) {
		this.rateFac = rateFac;
	}
	public String getRateFacNm() {
		return rateFacNm;
	}
	public void setRateFacNm(String rateFacNm) {
		this.rateFacNm = rateFacNm;
	}
	public String getRateFacFlag() {
		return rateFacFlag;
	}
	public void setRateFacFlag(String rateFacFlag) {
		this.rateFacFlag = rateFacFlag;
	}
	public String getRateFacFlagNm() {
		return rateFacFlagNm;
	}
	public void setRateFacFlagNm(String rateFacFlagNm) {
		this.rateFacFlagNm = rateFacFlagNm;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getActDay() {
		return actDay;
	}
	public void setActDay(String actDay) {
		this.actDay = actDay;
	}
	public String getDeactDay() {
		return deactDay;
	}
	public void setDeactDay(String deactDay) {
		this.deactDay = deactDay;
	}
	public String getPeriodDefId() {
		return periodDefId;
	}
	public void setPeriodDefId(String periodDefId) {
		this.periodDefId = periodDefId;
	}
	public String getPeriodCd() {
		return periodCd;
	}
	public void setPeriodCd(String periodCd) {
		this.periodCd = periodCd;
	}
	public String getTierSegmentId() {
		return tierSegmentId;
	}
	public void setTierSegmentId(String tierSegmentId) {
		this.tierSegmentId = tierSegmentId;
	}
	public String getCorrId() {
		return corrId;
	}
	public void setCorrId(String corrId) {
		this.corrId = corrId;
	}
	public String getCallingZone() {
		return callingZone;
	}
	public void setCallingZone(String callingZone) {
		this.callingZone = callingZone;
	}
	public String getCalledZone() {
		return calledZone;
	}
	public void setCalledZone(String calledZone) {
		this.calledZone = calledZone;
	}
	public String getSameGrp() {
		return sameGrp;
	}
	public void setSameGrp(String sameGrp) {
		this.sameGrp = sameGrp;
	}
	public String getExtraRateFac() {
		return extraRateFac;
	}
	public void setExtraRateFac(String extraRateFac) {
		this.extraRateFac = extraRateFac;
	}
	public String getRateId() {
		return rateId;
	}
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	public String getRatePerUnit() {
		return ratePerUnit;
	}
	public void setRatePerUnit(String ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}
	public String getUsgBillingIncrement() {
		return usgBillingIncrement;
	}
	public void setUsgBillingIncrement(String usgBillingIncrement) {
		this.usgBillingIncrement = usgBillingIncrement;
	}
	public String getFirstUnit() {
		return firstUnit;
	}
	public void setFirstUnit(String firstUnit) {
		this.firstUnit = firstUnit;
	}
	public String getFirstRate() {
		return firstRate;
	}
	public void setFirstRate(String firstRate) {
		this.firstRate = firstRate;
	}
	public String getMinChrg() {
		return minChrg;
	}
	public void setMinChrg(String minChrg) {
		this.minChrg = minChrg;
	}
	public String getMapCd() {
		return mapCd;
	}
	public void setMapCd(String mapCd) {
		this.mapCd = mapCd;
	}
	public String getMapEffDt() {
		return mapEffDt;
	}
	public void setMapEffDt(String mapEffDt) {
		this.mapEffDt = mapEffDt;
	}
	public String getMapExpDt() {
		return mapExpDt;
	}
	public void setMapExpDt(String mapExpDt) {
		this.mapExpDt = mapExpDt;
	}
	public String getMapDiscDedtPrty() {
		return mapDiscDedtPrty;
	}
	public void setMapDiscDedtPrty(String mapDiscDedtPrty) {
		this.mapDiscDedtPrty = mapDiscDedtPrty;
	}
	public String getMapDiscDedtCd() {
		return mapDiscDedtCd;
	}
	public void setMapDiscDedtCd(String mapDiscDedtCd) {
		this.mapDiscDedtCd = mapDiscDedtCd;
	}
	public String getMapShareFlag() {
		return mapShareFlag;
	}
	public void setMapShareFlag(String mapShareFlag) {
		this.mapShareFlag = mapShareFlag;
	}
	public String getMapHotOperator() {
		return mapHotOperator;
	}
	public void setMapHotOperator(String mapHotOperator) {
		this.mapHotOperator = mapHotOperator;
	}
	public String getMapProdId() {
		return mapProdId;
	}
	public void setMapProdId(String mapProdId) {
		this.mapProdId = mapProdId;
	}
	public String getDiscDedtCd() {
		return discDedtCd;
	}
	public void setDiscDedtCd(String discDedtCd) {
		this.discDedtCd = discDedtCd;
	}
	public String getDiscDedtNm() {
		return discDedtNm;
	}
	public void setDiscDedtNm(String discDedtNm) {
		this.discDedtNm = discDedtNm;
	}
	public String getUsgTypLvl() {
		return usgTypLvl;
	}
	public void setUsgTypLvl(String usgTypLvl) {
		this.usgTypLvl = usgTypLvl;
	}
	public String getUsgTypLvlNm() {
		return usgTypLvlNm;
	}
	public void setUsgTypLvlNm(String usgTypLvlNm) {
		this.usgTypLvlNm = usgTypLvlNm;
	}
	public String getUsgTypGrpCd() {
		return usgTypGrpCd;
	}
	public void setUsgTypGrpCd(String usgTypGrpCd) {
		this.usgTypGrpCd = usgTypGrpCd;
	}
	public String getUsgItmTyp() {
		return usgItmTyp;
	}
	public void setUsgItmTyp(String usgItmTyp) {
		this.usgItmTyp = usgItmTyp;
	}
	public String getUsgItmTypNm() {
		return usgItmTypNm;
	}
	public void setUsgItmTypNm(String usgItmTypNm) {
		this.usgItmTypNm = usgItmTypNm;
	}
	public String getUsgItmCd() {
		return usgItmCd;
	}
	public void setUsgItmCd(String usgItmCd) {
		this.usgItmCd = usgItmCd;
	}
	public String getUsgItmCdNm() {
		return usgItmCdNm;
	}
	public void setUsgItmCdNm(String usgItmCdNm) {
		this.usgItmCdNm = usgItmCdNm;
	}
	public String getDedtDiscFlag() {
		return dedtDiscFlag;
	}
	public void setDedtDiscFlag(String dedtDiscFlag) {
		this.dedtDiscFlag = dedtDiscFlag;
	}
	public String getFreeTyp() {
		return freeTyp;
	}
	public void setFreeTyp(String freeTyp) {
		this.freeTyp = freeTyp;
	}
	public String getCondRateFac1() {
		return condRateFac1;
	}
	public void setCondRateFac1(String condRateFac1) {
		this.condRateFac1 = condRateFac1;
	}
	public String getCondOperator1() {
		return condOperator1;
	}
	public void setCondOperator1(String condOperator1) {
		this.condOperator1 = condOperator1;
	}
	public String getCondVal1() {
		return condVal1;
	}
	public void setCondVal1(String condVal1) {
		this.condVal1 = condVal1;
	}
	public String getLogicalOperator12() {
		return logicalOperator12;
	}
	public void setLogicalOperator12(String logicalOperator12) {
		this.logicalOperator12 = logicalOperator12;
	}
	public String getCondRateFac2() {
		return condRateFac2;
	}
	public void setCondRateFac2(String condRateFac2) {
		this.condRateFac2 = condRateFac2;
	}
	public String getCondOperator2() {
		return condOperator2;
	}
	public void setCondOperator2(String condOperator2) {
		this.condOperator2 = condOperator2;
	}
	public String getCondVal2() {
		return condVal2;
	}
	public void setCondVal2(String condVal2) {
		this.condVal2 = condVal2;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getApplLvl() {
		return applLvl;
	}
	public void setApplLvl(String applLvl) {
		this.applLvl = applLvl;
	}
	public String getFreeOfChrgFlag() {
		return freeOfChrgFlag;
	}
	public void setFreeOfChrgFlag(String freeOfChrgFlag) {
		this.freeOfChrgFlag = freeOfChrgFlag;
	}
	public String getReplenishCycl() {
		return replenishCycl;
	}
	public void setReplenishCycl(String replenishCycl) {
		this.replenishCycl = replenishCycl;
	}
	public String getReplenishCyclNm() {
		return replenishCyclNm;
	}
	public void setReplenishCyclNm(String replenishCyclNm) {
		this.replenishCyclNm = replenishCyclNm;
	}
	public String getAmtFlag() {
		return amtFlag;
	}
	public void setAmtFlag(String amtFlag) {
		this.amtFlag = amtFlag;
	}
	public String getAmtFlagNm() {
		return amtFlagNm;
	}
	public void setAmtFlagNm(String amtFlagNm) {
		this.amtFlagNm = amtFlagNm;
	}
	public String getAmtUnit() {
		return amtUnit;
	}
	public void setAmtUnit(String amtUnit) {
		this.amtUnit = amtUnit;
	}
	public String getAmtUnitNm() {
		return amtUnitNm;
	}
	public void setAmtUnitNm(String amtUnitNm) {
		this.amtUnitNm = amtUnitNm;
	}
	public String getVoiceCallDedtFlag() {
		return voiceCallDedtFlag;
	}
	public void setVoiceCallDedtFlag(String voiceCallDedtFlag) {
		this.voiceCallDedtFlag = voiceCallDedtFlag;
	}
	public String getApplDiscFlag() {
		return applDiscFlag;
	}
	public void setApplDiscFlag(String applDiscFlag) {
		this.applDiscFlag = applDiscFlag;
	}
	public String getBalCrOvrMethod() {
		return balCrOvrMethod;
	}
	public void setBalCrOvrMethod(String balCrOvrMethod) {
		this.balCrOvrMethod = balCrOvrMethod;
	}
	public String getBalCrOvrPeriod() {
		return balCrOvrPeriod;
	}
	public void setBalCrOvrPeriod(String balCrOvrPeriod) {
		this.balCrOvrPeriod = balCrOvrPeriod;
	}
	public String getMaxAccumBal() {
		return maxAccumBal;
	}
	public void setMaxAccumBal(String maxAccumBal) {
		this.maxAccumBal = maxAccumBal;
	}
	public String getSubscProrateFlag() {
		return subscProrateFlag;
	}
	public void setSubscProrateFlag(String subscProrateFlag) {
		this.subscProrateFlag = subscProrateFlag;
	}
	public String getTermProrateFlag() {
		return termProrateFlag;
	}
	public void setTermProrateFlag(String termProrateFlag) {
		this.termProrateFlag = termProrateFlag;
	}
	public String getDedtTyp() {
		return dedtTyp;
	}
	public void setDedtTyp(String dedtTyp) {
		this.dedtTyp = dedtTyp;
	}
	public String getSmsNotiCd() {
		return smsNotiCd;
	}
	public void setSmsNotiCd(String smsNotiCd) {
		this.smsNotiCd = smsNotiCd;
	}
	public String getCurrOrNextCycl() {
		return currOrNextCycl;
	}
	public void setCurrOrNextCycl(String currOrNextCycl) {
		this.currOrNextCycl = currOrNextCycl;
	}
	public String getDurationUnit() {
		return durationUnit;
	}
	public void setDurationUnit(String durationUnit) {
		this.durationUnit = durationUnit;
	}
	public String getDurationUnitNm() {
		return durationUnitNm;
	}
	public void setDurationUnitNm(String durationUnitNm) {
		this.durationUnitNm = durationUnitNm;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getUsgTypGrpCdNm() {
		return usgTypGrpCdNm;
	}
	public void setUsgTypGrpCdNm(String usgTypGrpCdNm) {
		this.usgTypGrpCdNm = usgTypGrpCdNm;
	}
	public String getLmtCd() {
		return lmtCd;
	}
	public void setLmtCd(String lmtCd) {
		this.lmtCd = lmtCd;
	}
	public String getUsgTypCtrlLvl() {
		return usgTypCtrlLvl;
	}
	public void setUsgTypCtrlLvl(String usgTypCtrlLvl) {
		this.usgTypCtrlLvl = usgTypCtrlLvl;
	}
	public String getCtrlUsgTypGrpCd() {
		return ctrlUsgTypGrpCd;
	}
	public void setCtrlUsgTypGrpCd(String ctrlUsgTypGrpCd) {
		this.ctrlUsgTypGrpCd = ctrlUsgTypGrpCd;
	}
	public String getSvcCtrlMethod() {
		return svcCtrlMethod;
	}
	public void setSvcCtrlMethod(String svcCtrlMethod) {
		this.svcCtrlMethod = svcCtrlMethod;
	}
	public String getWarningThreshold1() {
		return warningThreshold1;
	}
	public void setWarningThreshold1(String warningThreshold1) {
		this.warningThreshold1 = warningThreshold1;
	}
	public String getWarningThreshold2() {
		return warningThreshold2;
	}
	public void setWarningThreshold2(String warningThreshold2) {
		this.warningThreshold2 = warningThreshold2;
	}
	public String getWarningThresholdUnit() {
		return warningThresholdUnit;
	}
	public void setWarningThresholdUnit(String warningThresholdUnit) {
		this.warningThresholdUnit = warningThresholdUnit;
	}
	public String getWarningFreq() {
		return warningFreq;
	}
	public void setWarningFreq(String warningFreq) {
		this.warningFreq = warningFreq;
	}
	public String getWarningFreqUnit() {
		return warningFreqUnit;
	}
	public void setWarningFreqUnit(String warningFreqUnit) {
		this.warningFreqUnit = warningFreqUnit;
	}
	public String getEffDays() {
		return effDays;
	}
	public void setEffDays(String effDays) {
		this.effDays = effDays;
	}
	public String getRechrgLmtTypCd() {
		return rechrgLmtTypCd;
	}
	public void setRechrgLmtTypCd(String rechrgLmtTypCd) {
		this.rechrgLmtTypCd = rechrgLmtTypCd;
	}
	public String getRechrgLmtAmt() {
		return rechrgLmtAmt;
	}
	public void setRechrgLmtAmt(String rechrgLmtAmt) {
		this.rechrgLmtAmt = rechrgLmtAmt;
	}
	public String getLmtTyp() {
		return lmtTyp;
	}
	public void setLmtTyp(String lmtTyp) {
		this.lmtTyp = lmtTyp;
	}
	public String getUsgTypCtrlLvlNm() {
		return usgTypCtrlLvlNm;
	}
	public void setUsgTypCtrlLvlNm(String usgTypCtrlLvlNm) {
		this.usgTypCtrlLvlNm = usgTypCtrlLvlNm;
	}
	public String getSearchSoId() {
		return searchSoId;
	}
	public void setSearchSoId(String searchSoId) {
		this.searchSoId = searchSoId;
	}
	public String getSearchProdCd() {
		return searchProdCd;
	}
	public void setSearchProdCd(String searchProdCd) {
		this.searchProdCd = searchProdCd;
	}
	public String getSearchProdNm() {
		return searchProdNm;
	}
	public void setSearchProdNm(String searchProdNm) {
		this.searchProdNm = searchProdNm;
	}
	public String getSearchProdId() {
		return searchProdId;
	}
	public void setSearchProdId(String searchProdId) {
		this.searchProdId = searchProdId;
	}
	public String getSearchDiscDedtNm() {
		return searchDiscDedtNm;
	}
	public void setSearchDiscDedtNm(String searchDiscDedtNm) {
		this.searchDiscDedtNm = searchDiscDedtNm;
	}
	public String getMapDiscPrty() {
		return mapDiscPrty;
	}
	public void setMapDiscPrty(String mapDiscPrty) {
		this.mapDiscPrty = mapDiscPrty;
	}
	public String getMultipleDiscMethod() {
		return multipleDiscMethod;
	}
	public void setMultipleDiscMethod(String multipleDiscMethod) {
		this.multipleDiscMethod = multipleDiscMethod;
	}
	public String getTmDivisionFlag() {
		return tmDivisionFlag;
	}
	public void setTmDivisionFlag(String tmDivisionFlag) {
		this.tmDivisionFlag = tmDivisionFlag;
	}
	public String getSegmentFlag() {
		return segmentFlag;
	}
	public void setSegmentFlag(String segmentFlag) {
		this.segmentFlag = segmentFlag;
	}
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public List<Map<String, Object>> getUpdateUsgTypValList() {
		return updateUsgTypValList;
	}
	public void setUpdateUsgTypValList(List<Map<String, Object>> updateUsgTypValList) {
		this.updateUsgTypValList = updateUsgTypValList;
	}
	public List<Map<String, Object>> getUpdateRateFacValList() {
		return updateRateFacValList;
	}
	public void setUpdateRateFacValList(
			List<Map<String, Object>> updateRateFacValList) {
		this.updateRateFacValList = updateRateFacValList;
	}
	public List<Map<String, Object>> getUpdatePeriodValList() {
		return updatePeriodValList;
	}
	public void setUpdatePeriodValList(List<Map<String, Object>> updatePeriodValList) {
		this.updatePeriodValList = updatePeriodValList;
	}
	public List<Map<String, Object>> getUpdateRateInfoValList() {
		return updateRateInfoValList;
	}
	public void setUpdateRateInfoValList(
			List<Map<String, Object>> updateRateInfoValList) {
		this.updateRateInfoValList = updateRateInfoValList;
	}
	public List<Map<String, Object>> getUpdateDedtDefValList() {
		return updateDedtDefValList;
	}
	public void setUpdateDedtDefValList(
			List<Map<String, Object>> updateDedtDefValList) {
		this.updateDedtDefValList = updateDedtDefValList;
	}
	@Override
	public String toString() {
		return "NewProductDtl [userId=" + userId + ", page=" + page
				+ ", perPage=" + perPage + ", sidx=" + sidx + ", sord=" + sord
				+ ", lngTyp=" + lngTyp + ", sysToDate=" + sysToDate
				+ ", currentDay=" + currentDay + ", prodId=" + prodId
				+ ", prodNm=" + prodNm + ", prodFamily=" + prodFamily
				+ ", description=" + description + ", prodState=" + prodState
				+ ", effDt=" + effDt + ", expDt=" + expDt + ", recurringChrg="
				+ recurringChrg + ", prorateFlag=" + prorateFlag
				+ ", prodOfferTyp=" + prodOfferTyp + ", lmtCtrlTyp="
				+ lmtCtrlTyp + ", prodPurchCond=" + prodPurchCond
				+ ", prodPriority=" + prodPriority + ", precondProdTyp="
				+ precondProdTyp + ", precondProd=" + precondProd
				+ ", rowType=" + rowType + ", soId=" + soId + ", soNm=" + soNm
				+ ", basicProdFl=" + basicProdFl + ", attrNm=" + attrNm
				+ ", usgTyp=" + usgTyp + ", usgTypNm=" + usgTypNm + ", regYn="
				+ regYn + ", chrgCd=" + chrgCd + ", chrgCdNm=" + chrgCdNm
				+ ", calendarDefId=" + calendarDefId + ", multipleRateInd="
				+ multipleRateInd + ", tierSetId=" + tierSetId
				+ ", tierStepFlag=" + tierStepFlag + ", usgMeasureUnit="
				+ usgMeasureUnit + ", currencyCd=" + currencyCd
				+ ", periodApplMethod=" + periodApplMethod
				+ ", usgRoundingMethod=" + usgRoundingMethod
				+ ", commRateFlag=" + commRateFlag + ", usgFeeRoundingMethod="
				+ usgFeeRoundingMethod + ", discFlag=" + discFlag
				+ ", multipleDiscFlag=" + multipleDiscFlag
				+ ", multipleDiscCriteria=" + multipleDiscCriteria
				+ ", usgFeeRoundingOffset=" + usgFeeRoundingOffset
				+ ", dedtFlag=" + dedtFlag + ", prorateApplGrp="
				+ prorateApplGrp + ", dedtRateId=" + dedtRateId + ", lmtFlag="
				+ lmtFlag + ", crossDiscFlag=" + crossDiscFlag
				+ ", realTmAccFlag=" + realTmAccFlag + ", maxReserveAmt="
				+ maxReserveAmt + ", unitSvcCd=" + unitSvcCd + ", vatRate="
				+ vatRate + ", rateFac=" + rateFac + ", rateFacNm=" + rateFacNm
				+ ", rateFacFlag=" + rateFacFlag + ", rateFacFlagNm="
				+ rateFacFlagNm + ", count=" + count + ", actDay=" + actDay
				+ ", deactDay=" + deactDay + ", periodDefId=" + periodDefId
				+ ", periodCd=" + periodCd + ", tierSegmentId=" + tierSegmentId
				+ ", corrId=" + corrId + ", callingZone=" + callingZone
				+ ", calledZone=" + calledZone + ", sameGrp=" + sameGrp
				+ ", extraRateFac=" + extraRateFac + ", rateId=" + rateId
				+ ", ratePerUnit=" + ratePerUnit + ", usgBillingIncrement="
				+ usgBillingIncrement + ", firstUnit=" + firstUnit
				+ ", firstRate=" + firstRate + ", minChrg=" + minChrg
				+ ", mapCd=" + mapCd + ", mapEffDt=" + mapEffDt + ", mapExpDt="
				+ mapExpDt + ", mapDiscDedtPrty=" + mapDiscDedtPrty
				+ ", mapDiscDedtCd=" + mapDiscDedtCd + ", mapShareFlag="
				+ mapShareFlag + ", mapHotOperator=" + mapHotOperator
				+ ", mapProdId=" + mapProdId + ", discDedtCd=" + discDedtCd
				+ ", discDedtNm=" + discDedtNm + ", usgTypLvl=" + usgTypLvl
				+ ", usgTypLvlNm=" + usgTypLvlNm + ", usgTypGrpCd="
				+ usgTypGrpCd + ", usgItmTyp=" + usgItmTyp + ", usgItmTypNm="
				+ usgItmTypNm + ", usgItmCd=" + usgItmCd + ", usgItmCdNm="
				+ usgItmCdNm + ", dedtDiscFlag=" + dedtDiscFlag + ", freeTyp="
				+ freeTyp + ", condRateFac1=" + condRateFac1
				+ ", condRateFacNm1=" + condRateFacNm1 + ", condOperator1="
				+ condOperator1 + ", condOperatorNm1=" + condOperatorNm1
				+ ", condVal1=" + condVal1 + ", logicalOperator12="
				+ logicalOperator12 + ", logicalOperatorNm12="
				+ logicalOperatorNm12 + ", condRateFac2=" + condRateFac2
				+ ", condRateFacNm2=" + condRateFacNm2 + ", condOperator2="
				+ condOperator2 + ", condOperatorNm2=" + condOperatorNm2
				+ ", condVal2=" + condVal2 + ", quantity=" + quantity
				+ ", applLvl=" + applLvl + ", freeOfChrgFlag=" + freeOfChrgFlag
				+ ", replenishCycl=" + replenishCycl + ", replenishCyclNm="
				+ replenishCyclNm + ", amtFlag=" + amtFlag + ", amtFlagNm="
				+ amtFlagNm + ", amtUnit=" + amtUnit + ", amtUnitNm="
				+ amtUnitNm + ", voiceCallDedtFlag=" + voiceCallDedtFlag
				+ ", applDiscFlag=" + applDiscFlag + ", balCrOvrMethod="
				+ balCrOvrMethod + ", balCrOvrPeriod=" + balCrOvrPeriod
				+ ", maxAccumBal=" + maxAccumBal + ", subscProrateFlag="
				+ subscProrateFlag + ", termProrateFlag=" + termProrateFlag
				+ ", dedtTyp=" + dedtTyp + ", smsNotiCd=" + smsNotiCd
				+ ", currOrNextCycl=" + currOrNextCycl + ", durationUnit="
				+ durationUnit + ", durationUnitNm=" + durationUnitNm
				+ ", duration=" + duration + ", usgTypGrpCdNm=" + usgTypGrpCdNm
				+ ", lmtCd=" + lmtCd + ", usgTypCtrlLvl=" + usgTypCtrlLvl
				+ ", ctrlUsgTypGrpCd=" + ctrlUsgTypGrpCd + ", svcCtrlMethod="
				+ svcCtrlMethod + ", warningThreshold1=" + warningThreshold1
				+ ", warningThreshold2=" + warningThreshold2
				+ ", warningThresholdUnit=" + warningThresholdUnit
				+ ", warningFreq=" + warningFreq + ", warningFreqUnit="
				+ warningFreqUnit + ", effDays=" + effDays
				+ ", rechrgLmtTypCd=" + rechrgLmtTypCd + ", rechrgLmtAmt="
				+ rechrgLmtAmt + ", lmtTyp=" + lmtTyp + ", usgTypCtrlLvlNm="
				+ usgTypCtrlLvlNm + ", searchSoId=" + searchSoId
				+ ", searchProdCd=" + searchProdCd + ", searchProdNm="
				+ searchProdNm + ", searchProdId=" + searchProdId
				+ ", searchDiscDedtNm=" + searchDiscDedtNm + ", mapDiscPrty="
				+ mapDiscPrty + ", multipleDiscMethod=" + multipleDiscMethod
				+ ", tmDivisionFlag=" + tmDivisionFlag + ", segmentFlag="
				+ segmentFlag + ", prodCd=" + prodCd + ", updateUsgTypValList="
				+ updateUsgTypValList + ", updateRateFacValList="
				+ updateRateFacValList + ", updatePeriodValList="
				+ updatePeriodValList + ", updateRateInfoValList="
				+ updateRateInfoValList + ", updateDiscDefValList="
				+ updateDiscDefValList + ", updateDedtDefValList="
				+ updateDedtDefValList + "]";
	}
}