package com.ntels.ccbs.product.domain.refInfo.ratingRefInfo;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("AllowanceDiscount")
public class AllowanceDiscount extends PagingValue {
	@NotEmpty
	@Length(min=4,max=20)
	private String userId;
	private Integer page;
	private Integer perPage;
	private String sidx;
	private String sord;
	private String lngTyp;

	private String discDedtCd;
	private String discDedtNm;
	private String duration;
	private String durationUnitNm;
	private String durationUnit;
	private String freeTyp;
	private String freeTypNm;
	private String dedtDiscFlag;
	private String dedtDiscFlagNm;
	private String usgTypLvl;
	private String usgTypLvlNm;
	private String usgTypGrpCd;
	private String usgTypGrpCdNm;
	private String usgItmTyp;
	private String usgItmTypNm;
	private String usgItmCd;
	private String usgItmCdNm;
	private String applLvl;
	private String applLvlNm;
	private String replenishCycl;
	private String replenishCyclNm;
	private String dedtTyp;
	private String dedtTypNm;
	private String amtFlag;
	private String amtFlagNm;
	private String amtUnit;
	private String amtUnitNm;
	private String quantity;
	private String balCrOvrMethodNm;
	private String smsNotiFlagNm;
	private String condRateFac1;				
	private String condOperator1;				
	private String condVal1;					
	private String logicalOperator12;			
	private String condRateFac2;				
	private String condOperator2 ;
	private String condVal2;
	private String maxAccumBal;
	private String subscProrateFlag;
	private String termProrateFlag;
	
	private String modDiscDedtCd;				//1-1 코드
	private String modDiscDedtNm;				//1-2 명칭
	private String modUsgTypLvl;				//2-1 사용유형레벨
	private String modUsgTypGrpCd;				//2-2 사용유형그룹
	private String modUsgItmTyp;				//2-3 사용항목유형
	private String modUsgItmCd;					//2-4 사용항목코드
	private String modDedtDiscFlag;				//1-3 공제할인여부
	private String modFreeTyp;					//1-4 공제유형
	private String modCondRateFac1;				//3-1 조건과금요소1
	private String modCondOp1;			//3-2 조건연산자1
	private String modCondVal1;					//3-3 조건값1
	private String modLogicalOperator12;		//3-4 조건연결자1-2
	private String modCondRateFac2;				//3-5 조건과금요소2
	private String modCondOp2 ;			//3-6 조건연산자2
	private String modCondVal2;					//3-7 조건값2
	private String modQuantity;					//5-6 공제량/할인율
	private String modApplLvl;					//5-1 공제적용수준
	private String modReplenishCycl;			//5-2 공제부여주기 
	private String modAmtFlag;					//5-4 공제량FLAG
	private String modAmtUnit;					//5-5 공제량단위
	private String modSubscProrateFlag;			//4-1 가입시일할계산
	private String modTermProrateFlag;			//4-2 해지시일할계산
	private String modDedtTyp;					//5-3 공제코드유형
	private String modMaxAccumBal;				//5-7 추가허용량
	private String modDurationUnit;
	private String modDuration;
	private String modVoiceCallDedtFlag;
	private String modApplDiscFlag;
	private String modBalCrOvrMethod;	
	private String modBalCrOvrPeriod;
	private String modTmDivisionFlag;
	private String modSegmentFlag;
	private String modFreeOfChrgFlag;   
	private String modSmsNotiCd;
	private String modSmsNotiFlag;
	private String modCurrOrNextCycl;
	private String modExpDt;
	private String modMultipleDiscMethod;
	private String modEffDt;
	
	private String insertDiscDedtCd;		//1-1 코드
	private String insertDiscDedtNm;		//1-2 명칭
	private String insertUsgTypLvl;			//2-1 사용유형레벨
	private String insertUsgTypGrpCd;		//2-2 사용유형그룹
	private String insertUsgItmTyp;			//2-3 사용항목유형
	private String insertUsgItmCd;			//2-4 사용항목코드
	private String insertDedtDiscFlag;		//1-3 공제할인여부
	private String insertFreeTyp;			//1-4 공제유형
	private String insertCondRatingFactor1;		//3-1 조건과금요소1
	private String insertCondOp1;					//3-2 조건연산자1
	private String insertCondVal1;				//3-3 조건값1
	private String insertLogicalOperator12;		//3-4 조건연결자1-2
	private String insertCondRatingFactor2;		//3-5 조건과금요소2
	private String insertCondOp2;					//3-6 조건연산자2
	private String insertCondVal2;				//3-7 조건값2
	private String insertQuantity;			//5-5 공제량/할인율
	private String insertApplLvl;			//5-1 공제적용수준
	private String insertReplenishCycl;		//5-2 공제부여주기 
	private String insertAmtFlag;			//5-4 공제량FLAG
	private String insertAmtUnit;			//5-5 공제량단위
	private String insertSubscProrateFlag;	//4-1 가입시일할계산
	private String insertTermProrateFlag;	//4-2 해지시일할계산
	private String insertDedtTyp;			//5-3 공제코드유형
	private String insertSmsNotiCd;
	private String insertSmsNotiFlag;
	private String insertCurrOrNextCycl;
	private String insertMultipleDiscMethod;
	private String insertDurationUnit;
	private String insertDuration;
	private String insertEffDt;
	private String effDt;
	private String expDt;
	private String insertVoiceCallDedtFlag;
	private String insertApplDiscFlag;
	private String insertBalCrOvrMethod;	
	private String insertBalCrOvrPeriod;
	private String insertMaxAccumBal;
	private String insertTmDivisionFlag;
	private String insertSegmentFlag;
	private String insertFreeOfChrgFlag;
	
	private String selectMenuNo;
	private String menuNo;
	private String selectedMenu;
	private String selectMenuNm;
	private String topMenuNo;
	private String topMenuNm;
	private String currentDay;
	private String tomorrow;
	private String yesterday;
	private Date sysToDate;
	
	@Override
	public String toString() {
		return "AllowanceDiscount [insertDiscDedtCd=" + insertDiscDedtCd
				+ ", insertDiscDedtNm=" + insertDiscDedtNm
				+ ", insertUsgTypLvl=" + insertUsgTypLvl
				+ ", insertUsgTypGrpCd=" + insertUsgTypGrpCd
				+ ", insertUsgItmTyp=" + insertUsgItmTyp + ", insertUsgItmCd="
				+ insertUsgItmCd + ", insertDedtDiscFlag=" + insertDedtDiscFlag
				+ ", insertFreeTyp=" + insertFreeTyp
				+ ", insertCondRatingFactor1=" + insertCondRatingFactor1
				+ ", insertCondOp1=" + insertCondOp1 + ", insertCondVal1="
				+ insertCondVal1 + ", insertLogicalOperator12="
				+ insertLogicalOperator12 + ", insertCondRatingFactor2="
				+ insertCondRatingFactor2 + ", insertCondOp2=" + insertCondOp2
				+ ", insertCondVal2=" + insertCondVal2 + ", insertQuantity="
				+ insertQuantity + ", insertApplLvl=" + insertApplLvl
				+ ", insertReplenishCycl=" + insertReplenishCycl
				+ ", insertAmtFlag=" + insertAmtFlag + ", insertAmtUnit="
				+ insertAmtUnit + ", insertSubscProrateFlag="
				+ insertSubscProrateFlag + ", insertTermProrateFlag="
				+ insertTermProrateFlag + ", insertDedtTyp=" + insertDedtTyp
				+ ", insertSmsNotiCd=" + insertSmsNotiCd
				+ ", insertSmsNotiFlag=" + insertSmsNotiFlag
				+ ", insertCurrOrNextCycl=" + insertCurrOrNextCycl
				+ ", insertMultipleDiscMethod=" + insertMultipleDiscMethod
				+ ", insertDurationUnit=" + insertDurationUnit
				+ ", insertDuration=" + insertDuration + ", effDt=" + effDt
				+ ", expDt=" + expDt + ", insertVoiceCallDedtFlag="
				+ insertVoiceCallDedtFlag + ", insertApplDiscFlag="
				+ insertApplDiscFlag + ", insertBalCrOvrMethod="
				+ insertBalCrOvrMethod + ", insertBalCrOvrPeriod="
				+ insertBalCrOvrPeriod + ", insertMaxAccumBal="
				+ insertMaxAccumBal + ", insertTmDivisionFlag="
				+ insertTmDivisionFlag + ", insertSegmentFlag="
				+ insertSegmentFlag + ", insertFreeOfChrgFlag="
				+ insertFreeOfChrgFlag + "]";
	}
	
	public String getInsertCondRatingFactor1() {
		return insertCondRatingFactor1;
	}
	public String getCondVal2() {
		return condVal2;
	}
	public void setCondVal2(String condVal2) {
		this.condVal2 = condVal2;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setInsertCondRatingFactor1(String insertCondRatingFactor1) {
		this.insertCondRatingFactor1 = insertCondRatingFactor1;
	}
	public String getInsertEffDt() {
		return insertEffDt;
	}
	public void setInsertEffDt(String insertEffDt) {
		this.insertEffDt = insertEffDt;
	}
	public String getModSmsNotiCd() {
		return modSmsNotiCd;
	}
	public void setModSmsNotiCd(String modSmsNotiCd) {
		this.modSmsNotiCd = modSmsNotiCd;
	}
	public String getModSmsNotiFlag() {
		return modSmsNotiFlag;
	}
	public void setModSmsNotiFlag(String modSmsNotiFlag) {
		this.modSmsNotiFlag = modSmsNotiFlag;
	}
	public String getModCurrOrNextCycl() {
		return modCurrOrNextCycl;
	}
	public void setModCurrOrNextCycl(String modCurrOrNextCycl) {
		this.modCurrOrNextCycl = modCurrOrNextCycl;
	}
	public String getModExpDt() {
		return modExpDt;
	}
	public void setModExpDt(String modExpDt) {
		this.modExpDt = modExpDt;
	}
	public String getModMultipleDiscMethod() {
		return modMultipleDiscMethod;
	}
	public void setModMultipleDiscMethod(String modMultipleDiscMethod) {
		this.modMultipleDiscMethod = modMultipleDiscMethod;
	}
	public String getModEffDt() {
		return modEffDt;
	}
	public void setModEffDt(String modEffDt) {
		this.modEffDt = modEffDt;
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
	public String getMaxAccumBal() {
		return maxAccumBal;
	}
	public void setMaxAccumBal(String maxAccumBal) {
		this.maxAccumBal = maxAccumBal;
	}
	public String getAmtUnit() {
		return amtUnit;
	}
	public void setAmtUnit(String amtUnit) {
		this.amtUnit = amtUnit;
	}
	public String getAmtFlag() {
		return amtFlag;
	}
	public void setAmtFlag(String amtFlag) {
		this.amtFlag = amtFlag;
	}
	public String getDedtTyp() {
		return dedtTyp;
	}
	public void setDedtTyp(String dedtTyp) {
		this.dedtTyp = dedtTyp;
	}
	public String getReplenishCycl() {
		return replenishCycl;
	}
	public void setReplenishCycl(String replenishCycl) {
		this.replenishCycl = replenishCycl;
	}
	public String getApplLvl() {
		return applLvl;
	}
	public void setApplLvl(String applLvl) {
		this.applLvl = applLvl;
	}
	public String getUsgTypLvl() {
		return usgTypLvl;
	}
	public void setUsgTypLvl(String usgTypLvl) {
		this.usgTypLvl = usgTypLvl;
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
	public String getUsgItmCdNm() {
		return usgItmCdNm;
	}
	public void setUsgItmCdNm(String usgItmCdNm) {
		this.usgItmCdNm = usgItmCdNm;
	}
	public String getFreeTyp() {
		return freeTyp;
	}
	public void setFreeTyp(String freeTyp) {
		this.freeTyp = freeTyp;
	}
	public String getDedtDiscFlag() {
		return dedtDiscFlag;
	}
	public void setDedtDiscFlag(String dedtDiscFlag) {
		this.dedtDiscFlag = dedtDiscFlag;
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
	public String getDurationUnitNm() {
		return durationUnitNm;
	}
	public void setDurationUnitNm(String durationUnitNm) {
		this.durationUnitNm = durationUnitNm;
	}
	public String getDurationUnit() {
		return durationUnit;
	}
	public void setDurationUnit(String durationUnit) {
		this.durationUnit = durationUnit;
	}
	public String getFreeTypNm() {
		return freeTypNm;
	}
	public void setFreeTypNm(String freeTypNm) {
		this.freeTypNm = freeTypNm;
	}
	public String getDedtDiscFlagNm() {
		return dedtDiscFlagNm;
	}
	public void setDedtDiscFlagNm(String dedtDiscFlagNm) {
		this.dedtDiscFlagNm = dedtDiscFlagNm;
	}
	public String getUsgTypLvlNm() {
		return usgTypLvlNm;
	}
	public void setUsgTypLvlNm(String usgTypLvlNm) {
		this.usgTypLvlNm = usgTypLvlNm;
	}
	public String getUsgTypGrpCdNm() {
		return usgTypGrpCdNm;
	}
	public void setUsgTypGrpCdNm(String usgTypGrpCdNm) {
		this.usgTypGrpCdNm = usgTypGrpCdNm;
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
	public String getApplLvlNm() {
		return applLvlNm;
	}
	public void setApplLvlNm(String applLvlNm) {
		this.applLvlNm = applLvlNm;
	}
	public String getReplenishCyclNm() {
		return replenishCyclNm;
	}
	public void setReplenishCyclNm(String replenishCyclNm) {
		this.replenishCyclNm = replenishCyclNm;
	}
	public String getDedtTypNm() {
		return dedtTypNm;
	}
	public void setDedtTypNm(String dedtTypNm) {
		this.dedtTypNm = dedtTypNm;
	}
	public String getAmtFlagNm() {
		return amtFlagNm;
	}
	public void setAmtFlagNm(String amtFlagNm) {
		this.amtFlagNm = amtFlagNm;
	}
	public String getAmtUnitNm() {
		return amtUnitNm;
	}
	public void setAmtUnitNm(String amtUnitNm) {
		this.amtUnitNm = amtUnitNm;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getBalCrOvrMethodNm() {
		return balCrOvrMethodNm;
	}
	public void setBalCrOvrMethodNm(String balCrOvrMethodNm) {
		this.balCrOvrMethodNm = balCrOvrMethodNm;
	}
	public String getSmsNotiFlagNm() {
		return smsNotiFlagNm;
	}
	public void setSmsNotiFlagNm(String smsNotiFlagNm) {
		this.smsNotiFlagNm = smsNotiFlagNm;
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
	public String getModDiscDedtCd() {
		return modDiscDedtCd;
	}
	public void setModDiscDedtCd(String modDiscDedtCd) {
		this.modDiscDedtCd = modDiscDedtCd;
	}
	public String getModDiscDedtNm() {
		return modDiscDedtNm;
	}
	public void setModDiscDedtNm(String modDiscDedtNm) {
		this.modDiscDedtNm = modDiscDedtNm;
	}
	public String getModUsgTypLvl() {
		return modUsgTypLvl;
	}
	public void setModUsgTypLvl(String modUsgTypLvl) {
		this.modUsgTypLvl = modUsgTypLvl;
	}
	public String getModUsgTypGrpCd() {
		return modUsgTypGrpCd;
	}
	public void setModUsgTypGrpCd(String modUsgTypGrpCd) {
		this.modUsgTypGrpCd = modUsgTypGrpCd;
	}
	public String getModUsgItmTyp() {
		return modUsgItmTyp;
	}
	public void setModUsgItmTyp(String modUsgItmTyp) {
		this.modUsgItmTyp = modUsgItmTyp;
	}
	public String getModUsgItmCd() {
		return modUsgItmCd;
	}
	public void setModUsgItmCd(String modUsgItmCd) {
		this.modUsgItmCd = modUsgItmCd;
	}
	public String getModDedtDiscFlag() {
		return modDedtDiscFlag;
	}
	public void setModDedtDiscFlag(String modDedtDiscFlag) {
		this.modDedtDiscFlag = modDedtDiscFlag;
	}
	public String getModFreeTyp() {
		return modFreeTyp;
	}
	public void setModFreeTyp(String modFreeTyp) {
		this.modFreeTyp = modFreeTyp;
	}
	public String getModCondRateFac1() {
		return modCondRateFac1;
	}
	public void setModCondRateFac1(String modCondRateFac1) {
		this.modCondRateFac1 = modCondRateFac1;
	}
	public String getModCondOp1() {
		return modCondOp1;
	}
	public void setModCondOp1(String modCondOp1) {
		this.modCondOp1 = modCondOp1;
	}
	public String getModCondVal1() {
		return modCondVal1;
	}
	public void setModCondVal1(String modCondVal1) {
		this.modCondVal1 = modCondVal1;
	}
	public String getModLogicalOperator12() {
		return modLogicalOperator12;
	}
	public void setModLogicalOperator12(String modLogicalOperator12) {
		this.modLogicalOperator12 = modLogicalOperator12;
	}
	public String getModCondRateFac2() {
		return modCondRateFac2;
	}
	public void setModCondRateFac2(String modCondRateFac2) {
		this.modCondRateFac2 = modCondRateFac2;
	}
	public String getModCondOp2() {
		return modCondOp2;
	}
	public void setModCondOp2(String modCondOp2) {
		this.modCondOp2 = modCondOp2;
	}
	public String getModCondVal2() {
		return modCondVal2;
	}
	public void setModCondVal2(String modCondVal2) {
		this.modCondVal2 = modCondVal2;
	}
	public String getModQuantity() {
		return modQuantity;
	}
	public void setModQuantity(String modQuantity) {
		this.modQuantity = modQuantity;
	}
	public String getModApplLvl() {
		return modApplLvl;
	}
	public void setModApplLvl(String modApplLvl) {
		this.modApplLvl = modApplLvl;
	}
	public String getModReplenishCycl() {
		return modReplenishCycl;
	}
	public void setModReplenishCycl(String modReplenishCycl) {
		this.modReplenishCycl = modReplenishCycl;
	}
	public String getModAmtFlag() {
		return modAmtFlag;
	}
	public void setModAmtFlag(String modAmtFlag) {
		this.modAmtFlag = modAmtFlag;
	}
	public String getModAmtUnit() {
		return modAmtUnit;
	}
	public void setModAmtUnit(String modAmtUnit) {
		this.modAmtUnit = modAmtUnit;
	}
	public String getModSubscProrateFlag() {
		return modSubscProrateFlag;
	}
	public void setModSubscProrateFlag(String modSubscProrateFlag) {
		this.modSubscProrateFlag = modSubscProrateFlag;
	}
	public String getModTermProrateFlag() {
		return modTermProrateFlag;
	}
	public void setModTermProrateFlag(String modTermProrateFlag) {
		this.modTermProrateFlag = modTermProrateFlag;
	}
	public String getModDedtTyp() {
		return modDedtTyp;
	}
	public void setModDedtTyp(String modDedtTyp) {
		this.modDedtTyp = modDedtTyp;
	}
	public String getModDurationUnit() {
		return modDurationUnit;
	}
	public void setModDurationUnit(String modDurationUnit) {
		this.modDurationUnit = modDurationUnit;
	}
	public String getModDuration() {
		return modDuration;
	}
	public void setModDuration(String modDuration) {
		this.modDuration = modDuration;
	}
	public String getModVoiceCallDedtFlag() {
		return modVoiceCallDedtFlag;
	}
	public void setModVoiceCallDedtFlag(String modVoiceCallDedtFlag) {
		this.modVoiceCallDedtFlag = modVoiceCallDedtFlag;
	}
	public String getModApplDiscFlag() {
		return modApplDiscFlag;
	}
	public void setModApplDiscFlag(String modApplDiscFlag) {
		this.modApplDiscFlag = modApplDiscFlag;
	}
	public String getModBalCrOvrMethod() {
		return modBalCrOvrMethod;
	}
	public void setModBalCrOvrMethod(String modBalCrOvrMethod) {
		this.modBalCrOvrMethod = modBalCrOvrMethod;
	}
	public String getModBalCrOvrPeriod() {
		return modBalCrOvrPeriod;
	}
	public void setModBalCrOvrPeriod(String modBalCrOvrPeriod) {
		this.modBalCrOvrPeriod = modBalCrOvrPeriod;
	}
	public String getModMaxAccumBal() {
		return modMaxAccumBal;
	}
	public void setModMaxAccumBal(String modMaxAccumBal) {
		this.modMaxAccumBal = modMaxAccumBal;
	}
	public String getModTmDivisionFlag() {
		return modTmDivisionFlag;
	}
	public void setModTmDivisionFlag(String modTmDivisionFlag) {
		this.modTmDivisionFlag = modTmDivisionFlag;
	}
	public String getModSegmentFlag() {
		return modSegmentFlag;
	}
	public void setModSegmentFlag(String modSegmentFlag) {
		this.modSegmentFlag = modSegmentFlag;
	}
	public String getModFreeOfChrgFlag() {
		return modFreeOfChrgFlag;
	}
	public void setModFreeOfChrgFlag(String modFreeOfChrgFlag) {
		this.modFreeOfChrgFlag = modFreeOfChrgFlag;
	}
	public String getInsertDiscDedtCd() {
		return insertDiscDedtCd;
	}
	public void setInsertDiscDedtCd(String insertDiscDedtCd) {
		this.insertDiscDedtCd = insertDiscDedtCd;
	}
	public String getInsertDiscDedtNm() {
		return insertDiscDedtNm;
	}
	public void setInsertDiscDedtNm(String insertDiscDedtNm) {
		this.insertDiscDedtNm = insertDiscDedtNm;
	}
	public String getInsertUsgTypLvl() {
		return insertUsgTypLvl;
	}
	public void setInsertUsgTypLvl(String insertUsgTypLvl) {
		this.insertUsgTypLvl = insertUsgTypLvl;
	}
	public String getInsertUsgTypGrpCd() {
		return insertUsgTypGrpCd;
	}
	public void setInsertUsgTypGrpCd(String insertUsgTypGrpCd) {
		this.insertUsgTypGrpCd = insertUsgTypGrpCd;
	}
	public String getInsertUsgItmTyp() {
		return insertUsgItmTyp;
	}
	public void setInsertUsgItmTyp(String insertUsgItmTyp) {
		this.insertUsgItmTyp = insertUsgItmTyp;
	}
	public String getInsertUsgItmCd() {
		return insertUsgItmCd;
	}
	public void setInsertUsgItmCd(String insertUsgItmCd) {
		this.insertUsgItmCd = insertUsgItmCd;
	}
	public String getInsertDedtDiscFlag() {
		return insertDedtDiscFlag;
	}
	public void setInsertDedtDiscFlag(String insertDedtDiscFlag) {
		this.insertDedtDiscFlag = insertDedtDiscFlag;
	}
	public String getInsertFreeTyp() {
		return insertFreeTyp;
	}
	public void setInsertFreeTyp(String insertFreeTyp) {
		this.insertFreeTyp = insertFreeTyp;
	}
	public String getInsertCondOp1() {
		return insertCondOp1;
	}
	public void setInsertCondOp1(String insertCondOp1) {
		this.insertCondOp1 = insertCondOp1;
	}
	public String getInsertCondVal1() {
		return insertCondVal1;
	}
	public void setInsertCondVal1(String insertCondVal1) {
		this.insertCondVal1 = insertCondVal1;
	}
	public String getInsertLogicalOperator12() {
		return insertLogicalOperator12;
	}
	public void setInsertLogicalOperator12(String insertLogicalOperator12) {
		this.insertLogicalOperator12 = insertLogicalOperator12;
	}
	public String getInsertCondRatingFactor2() {
		return insertCondRatingFactor2;
	}
	public void setInsertCondRatingFactor2(String insertCondRatingFactor2) {
		this.insertCondRatingFactor2 = insertCondRatingFactor2;
	}
	public String getInsertCondOp2() {
		return insertCondOp2;
	}
	public void setInsertCondOp2(String insertCondOp2) {
		this.insertCondOp2 = insertCondOp2;
	}
	public String getInsertCondVal2() {
		return insertCondVal2;
	}
	public void setInsertCondVal2(String insertCondVal2) {
		this.insertCondVal2 = insertCondVal2;
	}
	public String getInsertQuantity() {
		return insertQuantity;
	}
	public void setInsertQuantity(String insertQuantity) {
		this.insertQuantity = insertQuantity;
	}
	public String getInsertApplLvl() {
		return insertApplLvl;
	}
	public void setInsertApplLvl(String insertApplLvl) {
		this.insertApplLvl = insertApplLvl;
	}
	public String getInsertReplenishCycl() {
		return insertReplenishCycl;
	}
	public void setInsertReplenishCycl(String insertReplenishCycl) {
		this.insertReplenishCycl = insertReplenishCycl;
	}
	public String getInsertAmtFlag() {
		return insertAmtFlag;
	}
	public void setInsertAmtFlag(String insertAmtFlag) {
		this.insertAmtFlag = insertAmtFlag;
	}
	public String getInsertAmtUnit() {
		return insertAmtUnit;
	}
	public void setInsertAmtUnit(String insertAmtUnit) {
		this.insertAmtUnit = insertAmtUnit;
	}
	public String getInsertSubscProrateFlag() {
		return insertSubscProrateFlag;
	}
	public void setInsertSubscProrateFlag(String insertSubscProrateFlag) {
		this.insertSubscProrateFlag = insertSubscProrateFlag;
	}
	public String getInsertTermProrateFlag() {
		return insertTermProrateFlag;
	}
	public void setInsertTermProrateFlag(String insertTermProrateFlag) {
		this.insertTermProrateFlag = insertTermProrateFlag;
	}
	public String getInsertDedtTyp() {
		return insertDedtTyp;
	}
	public void setInsertDedtTyp(String insertDedtTyp) {
		this.insertDedtTyp = insertDedtTyp;
	}
	public String getInsertSmsNotiCd() {
		return insertSmsNotiCd;
	}
	public void setInsertSmsNotiCd(String insertSmsNotiCd) {
		this.insertSmsNotiCd = insertSmsNotiCd;
	}
	public String getInsertSmsNotiFlag() {
		return insertSmsNotiFlag;
	}
	public void setInsertSmsNotiFlag(String insertSmsNotiFlag) {
		this.insertSmsNotiFlag = insertSmsNotiFlag;
	}
	public String getInsertCurrOrNextCycl() {
		return insertCurrOrNextCycl;
	}
	public void setInsertCurrOrNextCycl(String insertCurrOrNextCycl) {
		this.insertCurrOrNextCycl = insertCurrOrNextCycl;
	}
	public String getInsertMultipleDiscMethod() {
		return insertMultipleDiscMethod;
	}
	public void setInsertMultipleDiscMethod(String insertMultipleDiscMethod) {
		this.insertMultipleDiscMethod = insertMultipleDiscMethod;
	}
	public String getInsertDurationUnit() {
		return insertDurationUnit;
	}
	public void setInsertDurationUnit(String insertDurationUnit) {
		this.insertDurationUnit = insertDurationUnit;
	}
	public String getInsertDuration() {
		return insertDuration;
	}
	public void setInsertDuration(String insertDuration) {
		this.insertDuration = insertDuration;
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
	public String getInsertVoiceCallDedtFlag() {
		return insertVoiceCallDedtFlag;
	}
	public void setInsertVoiceCallDedtFlag(String insertVoiceCallDedtFlag) {
		this.insertVoiceCallDedtFlag = insertVoiceCallDedtFlag;
	}
	public String getInsertApplDiscFlag() {
		return insertApplDiscFlag;
	}
	public void setInsertApplDiscFlag(String insertApplDiscFlag) {
		this.insertApplDiscFlag = insertApplDiscFlag;
	}
	public String getInsertBalCrOvrMethod() {
		return insertBalCrOvrMethod;
	}
	public void setInsertBalCrOvrMethod(String insertBalCrOvrMethod) {
		this.insertBalCrOvrMethod = insertBalCrOvrMethod;
	}
	public String getInsertBalCrOvrPeriod() {
		return insertBalCrOvrPeriod;
	}
	public void setInsertBalCrOvrPeriod(String insertBalCrOvrPeriod) {
		this.insertBalCrOvrPeriod = insertBalCrOvrPeriod;
	}
	public String getInsertMaxAccumBal() {
		return insertMaxAccumBal;
	}
	public void setInsertMaxAccumBal(String insertMaxAccumBal) {
		this.insertMaxAccumBal = insertMaxAccumBal;
	}
	public String getInsertTmDivisionFlag() {
		return insertTmDivisionFlag;
	}
	public void setInsertTmDivisionFlag(String insertTmDivisionFlag) {
		this.insertTmDivisionFlag = insertTmDivisionFlag;
	}
	public String getInsertSegmentFlag() {
		return insertSegmentFlag;
	}
	public void setInsertSegmentFlag(String insertSegmentFlag) {
		this.insertSegmentFlag = insertSegmentFlag;
	}
	public String getInsertFreeOfChrgFlag() {
		return insertFreeOfChrgFlag;
	}
	public void setInsertFreeOfChrgFlag(String insertFreeOfChrgFlag) {
		this.insertFreeOfChrgFlag = insertFreeOfChrgFlag;
	}
	public String getSelectMenuNo() {
		return selectMenuNo;
	}
	public void setSelectMenuNo(String selectMenuNo) {
		this.selectMenuNo = selectMenuNo;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getSelectedMenu() {
		return selectedMenu;
	}
	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}
	public String getSelectMenuNm() {
		return selectMenuNm;
	}
	public void setSelectMenuNm(String selectMenuNm) {
		this.selectMenuNm = selectMenuNm;
	}
	public String getTopMenuNo() {
		return topMenuNo;
	}
	public void setTopMenuNo(String topMenuNo) {
		this.topMenuNo = topMenuNo;
	}
	public String getTopMenuNm() {
		return topMenuNm;
	}
	public void setTopMenuNm(String topMenuNm) {
		this.topMenuNm = topMenuNm;
	}
	public String getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}
	public String getTomorrow() {
		return tomorrow;
	}
	public void setTomorrow(String tomorrow) {
		this.tomorrow = tomorrow;
	}
	public String getYesterday() {
		return yesterday;
	}
	public void setYesterday(String yesterday) {
		this.yesterday = yesterday;
	}
	public Date getSysToDate() {
		return sysToDate;
	}
	public void setSysToDate(Date sysToDate) {
		this.sysToDate = sysToDate;
	}
}
