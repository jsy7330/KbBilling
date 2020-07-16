package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class OSvcCmpsVO implements Serializable,CommonVO{


	/**
	 * 
	 */
	private static final long serialVersionUID = -884265844004437615L;
	private String soId; /* 사업구분 */
	private String ctrtId; /* 계약번호 */
	private String svcCmpsId; /* 서비스구성번호 */
	private String orderId; /* 오더번호 */
	private String orderTp;
	private String orderStat;
	private String inactDttm; /* 유효종료일시 */
	private String actDttm; /* 유효시작일시 */
	private String prodCmpsId; /* 상품구성번호 */
	private String rcptId; /* 접수번호 */
	private String prodCd; /* 상품코드 */
	private String rateStrtDt; /* 과금개시일 */
	private String rateEndDt; /* 과금종료일 */
	private String openDd; /* 개통일 */
	private String termDt; /* 해지일 */
	private String svcGrp; /* 서비스그룹 */
	private String svcCd; /* 서비스코드 */
	private String svcChgCl; /* 서비스변경구분 */
	private String svcStsCd; /* 서비스태 */
	private String extId; /* 확장ID */
	private String regrId; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자 */
	private Date chgDate; /* 수정일시 */
	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}
	/**
	 * @param soId the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}
	/**
	 * @return the ctrtId
	 */
	public String getCtrtId() {
		return ctrtId;
	}
	/**
	 * @param ctrtId the ctrtId to set
	 */
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	/**
	 * @return the svcCmpsId
	 */
	public String getSvcCmpsId() {
		return svcCmpsId;
	}
	/**
	 * @param svcCmpsId the svcCmpsId to set
	 */
	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderTp
	 */
	public String getOrderTp() {
		return orderTp;
	}
	/**
	 * @param orderTp the orderTp to set
	 */
	public void setOrderTp(String orderTp) {
		this.orderTp = orderTp;
	}
	/**
	 * @return the orderStat
	 */
	public String getOrderStat() {
		return orderStat;
	}
	/**
	 * @param orderStat the orderStat to set
	 */
	public void setOrderStat(String orderStat) {
		this.orderStat = orderStat;
	}
	/**
	 * @return the inactDttm
	 */
	public String getInactDttm() {
		return inactDttm;
	}
	/**
	 * @param inactDttm the inactDttm to set
	 */
	public void setInactDttm(String inactDttm) {
		this.inactDttm = inactDttm;
	}
	/**
	 * @return the actDttm
	 */
	public String getActDttm() {
		return actDttm;
	}
	/**
	 * @param actDttm the actDttm to set
	 */
	public void setActDttm(String actDttm) {
		this.actDttm = actDttm;
	}
	/**
	 * @return the prodCmpsId
	 */
	public String getProdCmpsId() {
		return prodCmpsId;
	}
	/**
	 * @param prodCmpsId the prodCmpsId to set
	 */
	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}
	/**
	 * @return the rcptId
	 */
	public String getRcptId() {
		return rcptId;
	}
	/**
	 * @param rcptId the rcptId to set
	 */
	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
	}
	/**
	 * @return the prodCd
	 */
	public String getProdCd() {
		return prodCd;
	}
	/**
	 * @param prodCd the prodCd to set
	 */
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	/**
	 * @return the rateStrtDt
	 */
	public String getRateStrtDt() {
		return rateStrtDt;
	}
	/**
	 * @param rateStrtDt the rateStrtDt to set
	 */
	public void setRateStrtDt(String rateStrtDt) {
		this.rateStrtDt = rateStrtDt;
	}
	/**
	 * @return the rateEndDt
	 */
	public String getRateEndDt() {
		return rateEndDt;
	}
	/**
	 * @param rateEndDt the rateEndDt to set
	 */
	public void setRateEndDt(String rateEndDt) {
		this.rateEndDt = rateEndDt;
	}
	/**
	 * @return the openDd
	 */
	public String getOpenDd() {
		return openDd;
	}
	/**
	 * @param openDd the openDd to set
	 */
	public void setOpenDd(String openDd) {
		this.openDd = openDd;
	}
	/**
	 * @return the termDt
	 */
	public String getTermDt() {
		return termDt;
	}
	/**
	 * @param termDt the termDt to set
	 */
	public void setTermDt(String termDt) {
		this.termDt = termDt;
	}
	/**
	 * @return the svcGrp
	 */
	public String getSvcGrp() {
		return svcGrp;
	}
	/**
	 * @param svcGrp the svcGrp to set
	 */
	public void setSvcGrp(String svcGrp) {
		this.svcGrp = svcGrp;
	}
	/**
	 * @return the svcCd
	 */
	public String getSvcCd() {
		return svcCd;
	}
	/**
	 * @param svcCd the svcCd to set
	 */
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}
	/**
	 * @return the svcChgCl
	 */
	public String getSvcChgCl() {
		return svcChgCl;
	}
	/**
	 * @param svcChgCl the svcChgCl to set
	 */
	public void setSvcChgCl(String svcChgCl) {
		this.svcChgCl = svcChgCl;
	}
	/**
	 * @return the svcStsCd
	 */
	public String getSvcStsCd() {
		return svcStsCd;
	}
	/**
	 * @param svcStsCd the svcStsCd to set
	 */
	public void setSvcStsCd(String svcStsCd) {
		this.svcStsCd = svcStsCd;
	}
	/**
	 * @return the extId
	 */
	public String getExtId() {
		return extId;
	}
	/**
	 * @param extId the extId to set
	 */
	public void setExtId(String extId) {
		this.extId = extId;
	}
	/**
	 * @return the regrId
	 */
	public String getRegrId() {
		return regrId;
	}
	/**
	 * @param regrId the regrId to set
	 */
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the chgrId
	 */
	public String getChgrId() {
		return chgrId;
	}
	/**
	 * @param chgrId the chgrId to set
	 */
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	/**
	 * @return the chgDate
	 */
	public Date getChgDate() {
		return chgDate;
	}
	/**
	 * @param chgDate the chgDate to set
	 */
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OSvcCmpsVO [soId=");
		builder.append(soId);
		builder.append(", ctrtId=");
		builder.append(ctrtId);
		builder.append(", svcCmpsId=");
		builder.append(svcCmpsId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", orderTp=");
		builder.append(orderTp);
		builder.append(", orderStat=");
		builder.append(orderStat);
		builder.append(", inactDttm=");
		builder.append(inactDttm);
		builder.append(", actDttm=");
		builder.append(actDttm);
		builder.append(", prodCmpsId=");
		builder.append(prodCmpsId);
		builder.append(", rcptId=");
		builder.append(rcptId);
		builder.append(", prodCd=");
		builder.append(prodCd);
		builder.append(", rateStrtDt=");
		builder.append(rateStrtDt);
		builder.append(", rateEndDt=");
		builder.append(rateEndDt);
		builder.append(", openDd=");
		builder.append(openDd);
		builder.append(", termDt=");
		builder.append(termDt);
		builder.append(", svcGrp=");
		builder.append(svcGrp);
		builder.append(", svcCd=");
		builder.append(svcCd);
		builder.append(", svcChgCl=");
		builder.append(svcChgCl);
		builder.append(", svcStsCd=");
		builder.append(svcStsCd);
		builder.append(", extId=");
		builder.append(extId);
		builder.append(", regrId=");
		builder.append(regrId);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", chgrId=");
		builder.append(chgrId);
		builder.append(", chgDate=");
		builder.append(chgDate);
		builder.append("]");
		return builder.toString();
	}
		
}
