package com.ntels.ccbs.product.domain.refInfo.ratingRefInfo;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("AttrTypMap")
public class UsageType extends PagingValue {

	@NotEmpty	
	@Length(min=4,max=20)
	private String userId;
	private Integer page;
	private Integer perPage;
	private String sidx;
	private String sord;
	
	private String attrTyp;
	private String attrTypNm;
	private String attrCd;
	private String attrNm;
	private String actDt;
	private String inactDt;	
	private String mstrFl;
	private String regrId;
	private String regDate;
	private String chgrId;
	private String chgDate;
	
	private String lngTyp; //언어 타입
	private String oldActDt;
	//네비게이션 
	private String selectMenuNo;
	private String menuNo;
	private String selectedMenu;
	private String selectMenuNm;
	private String topMenuNo;
	private String topMenuNm;
	
	//공통코드
	private String commonGrp;
	private String commonCd;
	private String commonCdNm;
	private String refCode;
	private String refCode2;
	private String refCode3;
	private String refCode4; 
	private String defaultYn;
	private String sort_no;
	private String commonGrpNm;
	
	//검색
	private String searchAttrTyp;
	private String searchAttrCd;
	private String searchAttrTyp2;
	
	//등록 수정 값
	private String insAttrCd;
	private String insActDt;
	private String insInactDt;
	private String insAttrTyp;
	
	//날짜 처리
	private String currentDay;
	private String tomorrow;
	private String yesterday;
	private Date sysToDate;
	
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
	public String getAttrTyp() {
		return attrTyp;
	}
	public void setAttrTyp(String attrTyp) {
		this.attrTyp = attrTyp;
	}
	public String getAttrTypNm() {
		return attrTypNm;
	}
	public void setAttrTypNm(String attrTypNm) {
		this.attrTypNm = attrTypNm;
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
	public String getMstrFl() {
		return mstrFl;
	}
	public void setMstrFl(String mstrFl) {
		this.mstrFl = mstrFl;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public String getChgDate() {
		return chgDate;
	}
	public void setChgDate(String chgDate) {
		this.chgDate = chgDate;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getOldActDt() {
		return oldActDt;
	}
	public void setOldActDt(String oldActDt) {
		this.oldActDt = oldActDt;
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
	public String getCommonGrp() {
		return commonGrp;
	}
	public void setCommonGrp(String commonGrp) {
		this.commonGrp = commonGrp;
	}
	public String getCommonCd() {
		return commonCd;
	}
	public void setCommonCd(String commonCd) {
		this.commonCd = commonCd;
	}
	public String getCommonCdNm() {
		return commonCdNm;
	}
	public void setCommonCdNm(String commonCdNm) {
		this.commonCdNm = commonCdNm;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	public String getRefCode2() {
		return refCode2;
	}
	public void setRefCode2(String refCode2) {
		this.refCode2 = refCode2;
	}
	public String getRefCode3() {
		return refCode3;
	}
	public void setRefCode3(String refCode3) {
		this.refCode3 = refCode3;
	}
	public String getRefCode4() {
		return refCode4;
	}
	public void setRefCode4(String refCode4) {
		this.refCode4 = refCode4;
	}
	public String getDefaultYn() {
		return defaultYn;
	}
	public void setDefaultYn(String defaultYn) {
		this.defaultYn = defaultYn;
	}
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	public String getCommonGrpNm() {
		return commonGrpNm;
	}
	public void setCommonGrpNm(String commonGrpNm) {
		this.commonGrpNm = commonGrpNm;
	}
	public String getSearchAttrTyp() {
		return searchAttrTyp;
	}
	public void setSearchAttrTyp(String searchAttrTyp) {
		this.searchAttrTyp = searchAttrTyp;
	}
	public String getSearchAttrCd() {
		return searchAttrCd;
	}
	public void setSearchAttrCd(String searchAttrCd) {
		this.searchAttrCd = searchAttrCd;
	}
	public String getSearchAttrTyp2() {
		return searchAttrTyp2;
	}
	public void setSearchAttrTyp2(String searchAttrTyp2) {
		this.searchAttrTyp2 = searchAttrTyp2;
	}
	public String getInsAttrCd() {
		return insAttrCd;
	}
	public void setInsAttrCd(String insAttrCd) {
		this.insAttrCd = insAttrCd;
	}
	public String getInsActDt() {
		return insActDt;
	}
	public void setInsActDt(String insActDt) {
		this.insActDt = insActDt;
	}
	public String getInsInactDt() {
		return insInactDt;
	}
	public void setInsInactDt(String insInactDt) {
		this.insInactDt = insInactDt;
	}
	public String getInsAttrTyp() {
		return insAttrTyp;
	}
	public void setInsAttrTyp(String insAttrTyp) {
		this.insAttrTyp = insAttrTyp;
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


