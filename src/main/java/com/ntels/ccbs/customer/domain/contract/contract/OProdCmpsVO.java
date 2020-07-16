package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class OProdCmpsVO implements Serializable,CommonVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7494096287295929958L;
	private String soId; /* 사업구분 */
	private String ctrtId; /* 계약번호 */
	private String prodCmpsId; /* 상품구성번호 */
	private String orderId; /* 오더번호 */
	private String orderTp;
	private String orderStat;
	private String inactDttm; /* 유효종료일시 */
	private String actDttm; /* 유효시작일시 */
	private String rcptId; /* 접수번호 */
	private String rateStrtDt; /* 과금개시일 */
	private String rateEndDt; /* 과금종료일 */
	private String openDd; /* 개통일 */
	private String termDt; /* 해지일 */
	private String prodGrp; /* 상품그룹 */
	private String prodCmpsCl; /* 상품구성구분 */
	private String prodCd; /* 상품코드 */
	private String upProdGrp; /* 상위상품그룹 */
	private String upProdCmpsId; /* 상위상품구성번호 */
	private String upProdCd; /* 상위상품코드 */
	private String befCmpsProdId; /* 변경전상품구성번호 */
	private String befProdGrp; /* 변경전상품그룹 */
	private String befProdCd; /* 변경전상품코드 */
	private String orgId; /* 유통망번호 */
	private String usrId; /* 유통망직원번호 */
	private String prodChgCl; /* 상품변경유형 */
	private String prodStatCd; /* 상품계약상태 */
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
	 * @return the prodGrp
	 */
	public String getProdGrp() {
		return prodGrp;
	}
	/**
	 * @param prodGrp the prodGrp to set
	 */
	public void setProdGrp(String prodGrp) {
		this.prodGrp = prodGrp;
	}
	/**
	 * @return the prodCmpsCl
	 */
	public String getProdCmpsCl() {
		return prodCmpsCl;
	}
	/**
	 * @param prodCmpsCl the prodCmpsCl to set
	 */
	public void setProdCmpsCl(String prodCmpsCl) {
		this.prodCmpsCl = prodCmpsCl;
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
	 * @return the upProdGrp
	 */
	public String getUpProdGrp() {
		return upProdGrp;
	}
	/**
	 * @param upProdGrp the upProdGrp to set
	 */
	public void setUpProdGrp(String upProdGrp) {
		this.upProdGrp = upProdGrp;
	}
	/**
	 * @return the upProdCmpsId
	 */
	public String getUpProdCmpsId() {
		return upProdCmpsId;
	}
	/**
	 * @param upProdCmpsId the upProdCmpsId to set
	 */
	public void setUpProdCmpsId(String upProdCmpsId) {
		this.upProdCmpsId = upProdCmpsId;
	}
	/**
	 * @return the upProdCd
	 */
	public String getUpProdCd() {
		return upProdCd;
	}
	/**
	 * @param upProdCd the upProdCd to set
	 */
	public void setUpProdCd(String upProdCd) {
		this.upProdCd = upProdCd;
	}
	/**
	 * @return the befCmpsProdId
	 */
	public String getBefCmpsProdId() {
		return befCmpsProdId;
	}
	/**
	 * @param befCmpsProdId the befCmpsProdId to set
	 */
	public void setBefCmpsProdId(String befCmpsProdId) {
		this.befCmpsProdId = befCmpsProdId;
	}
	/**
	 * @return the befProdGrp
	 */
	public String getBefProdGrp() {
		return befProdGrp;
	}
	/**
	 * @param befProdGrp the befProdGrp to set
	 */
	public void setBefProdGrp(String befProdGrp) {
		this.befProdGrp = befProdGrp;
	}
	/**
	 * @return the befProdCd
	 */
	public String getBefProdCd() {
		return befProdCd;
	}
	/**
	 * @param befProdCd the befProdCd to set
	 */
	public void setBefProdCd(String befProdCd) {
		this.befProdCd = befProdCd;
	}
	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the usrId
	 */
	public String getUsrId() {
		return usrId;
	}
	/**
	 * @param usrId the usrId to set
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	/**
	 * @return the prodChgCl
	 */
	public String getProdChgCl() {
		return prodChgCl;
	}
	/**
	 * @param prodChgCl the prodChgCl to set
	 */
	public void setProdChgCl(String prodChgCl) {
		this.prodChgCl = prodChgCl;
	}
	/**
	 * @return the prodStatCd
	 */
	public String getProdStatCd() {
		return prodStatCd;
	}
	/**
	 * @param prodStatCd the prodStatCd to set
	 */
	public void setProdStatCd(String prodStatCd) {
		this.prodStatCd = prodStatCd;
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
		builder.append("OProdCmpsVO [soId=");
		builder.append(soId);
		builder.append(", ctrtId=");
		builder.append(ctrtId);
		builder.append(", prodCmpsId=");
		builder.append(prodCmpsId);
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
		builder.append(", rcptId=");
		builder.append(rcptId);
		builder.append(", rateStrtDt=");
		builder.append(rateStrtDt);
		builder.append(", rateEndDt=");
		builder.append(rateEndDt);
		builder.append(", openDd=");
		builder.append(openDd);
		builder.append(", termDt=");
		builder.append(termDt);
		builder.append(", prodGrp=");
		builder.append(prodGrp);
		builder.append(", prodCmpsCl=");
		builder.append(prodCmpsCl);
		builder.append(", prodCd=");
		builder.append(prodCd);
		builder.append(", upProdGrp=");
		builder.append(upProdGrp);
		builder.append(", upProdCmpsId=");
		builder.append(upProdCmpsId);
		builder.append(", upProdCd=");
		builder.append(upProdCd);
		builder.append(", befCmpsProdId=");
		builder.append(befCmpsProdId);
		builder.append(", befProdGrp=");
		builder.append(befProdGrp);
		builder.append(", befProdCd=");
		builder.append(befProdCd);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", usrId=");
		builder.append(usrId);
		builder.append(", prodChgCl=");
		builder.append(prodChgCl);
		builder.append(", prodStatCd=");
		builder.append(prodStatCd);
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
