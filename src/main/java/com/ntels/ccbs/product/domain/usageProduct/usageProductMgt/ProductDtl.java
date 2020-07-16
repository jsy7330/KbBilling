package com.ntels.ccbs.product.domain.usageProduct.usageProductMgt;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ProductDtl")
public class ProductDtl extends PagingValue {
	//@NotEmpty
	//@Length(min=4,max=20)
	private String userId;
	private Integer page;
	private Integer perPage;
	private String sidx;
	private String sord;
	private String ifSys;
	private String ifSysNm;	
	private String lngTyp;
	
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
	
	private String soNm;
	private String prodCd;
	private String prodNm;
	private String prodGrp;
	private String prodDesc;
	private String basicProdFl;
	private String actDt;
	private String inactDt;
	
	private String searchSoNm;
	private String searchProdTyp;
	private String searchTyp;
	private String searchProd;
	
	@Override
	public String toString() {
		return "ProductDtl [userId=" + userId + ", page=" + page + ", perPage="
				+ perPage + ", sidx=" + sidx + ", sord=" + sord + ", ifSys="
				+ ifSys + ", ifSysNm=" + ifSysNm + ", lngTyp=" + lngTyp
				+ ", selectMenuNo=" + selectMenuNo + ", menuNo=" + menuNo
				+ ", selectedMenu=" + selectedMenu + ", selectMenuNm="
				+ selectMenuNm + ", topMenuNo=" + topMenuNo + ", topMenuNm="
				+ topMenuNm + ", currentDay=" + currentDay + ", tomorrow="
				+ tomorrow + ", yesterday=" + yesterday + ", sysToDate="
				+ sysToDate + ", soNm=" + soNm + ", prodCd=" + prodCd
				+ ", prodNm=" + prodNm + ", prodGrp=" + prodGrp + ", prodDesc="
				+ prodDesc + ", basicProdFl=" + basicProdFl + ", actDt="
				+ actDt + ", inactDt=" + inactDt + ", searchSoNm=" + searchSoNm
				+ ", searchProdTyp=" + searchProdTyp + ", searchTyp="
				+ searchTyp + ", searchProd=" + searchProd + "]";
	}
	
	public String getSearchSoNm() {
		return searchSoNm;
	}
	public void setSearchSoNm(String searchSoNm) {
		this.searchSoNm = searchSoNm;
	}
	public String getSearchProdTyp() {
		return searchProdTyp;
	}
	public void setSearchProdTyp(String searchProdTyp) {
		this.searchProdTyp = searchProdTyp;
	}
	public String getSearchTyp() {
		return searchTyp;
	}
	public void setSearchTyp(String searchTyp) {
		this.searchTyp = searchTyp;
	}
	public String getSearchProd() {
		return searchProd;
	}
	public void setSearchProd(String searchProd) {
		this.searchProd = searchProd;
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
	public String getProdGrp() {
		return prodGrp;
	}
	public void setProdGrp(String prodGrp) {
		this.prodGrp = prodGrp;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getBasicProdFl() {
		return basicProdFl;
	}
	public void setBasicProdFl(String basicProdFl) {
		this.basicProdFl = basicProdFl;
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
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
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
	public String getIfSys() {
		return ifSys;
	}
	public void setIfSys(String ifSys) {
		this.ifSys = ifSys;
	}
	public String getIfSysNm() {
		return ifSysNm;
	}
	public void setIfSysNm(String ifSysNm) {
		this.ifSysNm = ifSysNm;
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
