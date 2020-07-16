package com.ntels.ccbs.customer.domain.customer.customer;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class TransactionReportVO implements Serializable,CommonVO{

	private String purchaseUser;
	private String purchaseDate;
	private String purchasePrice;
	private String prodCd;
	private String prodTyp;
	private String prodNm;
	private String payMthd;
	private String regDate;
	
	private String soId;
	private String userNm;
	private String selPurchaseStrtDt;
	private String selPurchaseEndDt;
	
	private Integer totalCount;
	private Integer totalPages;
	private Integer page;
	private String end;
	private String start;
	

	public String getPurchaseUser() {
		return purchaseUser;
	}

	public void setPurchaseUser(String purchaseUser) {
		this.purchaseUser = purchaseUser;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getProdCd() {
		return prodCd;
	}

	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}

	public String getProdTp() {
		return prodTyp;
	}

	public void setProdTp(String prodTyp) {
		this.prodTyp = prodTyp;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getPayMthd() {
		return payMthd;
	}

	public void setPayMthd(String payMthd) {
		this.payMthd = payMthd;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getSelPurchaseStrtDt() {
		return selPurchaseStrtDt;
	}

	public void setSelPurchaseStrtDt(String selPurchaseStrtDt) {
		this.selPurchaseStrtDt = selPurchaseStrtDt;
	}

	public String getSelPurchaseEndDt() {
		return selPurchaseEndDt;
	}

	public void setSelPurchaseEndDt(String selPurchaseEndDt) {
		this.selPurchaseEndDt = selPurchaseEndDt;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

}




