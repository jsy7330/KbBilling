package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class OrderCommonVO implements Serializable,CommonVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5986162646334417183L;
	private String rcptId; 
	private String soId;
	private String ctrtId;
	private String custId;
	private String custTp;
	private String custTpNm;
	private String custNm;
	private String svcTelNoAsMask;
	private String orderId;
	private String orderCd;
	private String orderTp;
	private String orderTpNm;
	private String orderStat;
	private String orderStatNm;
	private String cnslMstCl;
	private String cnslMstClNm;
	private String cnslMidCl;
	private String cnslMidClNm;
	private String cnslSlvCl;
	private String cnslSlvClNm;
	private String basicSvcCd;
	private String url;
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
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return the custTp
	 */
	public String getCustTp() {
		return custTp;
	}
	/**
	 * @param custTp the custTp to set
	 */
	public void setCustTp(String custTp) {
		this.custTp = custTp;
	}
	/**
	 * @return the custTpNm
	 */
	public String getCustTpNm() {
		return custTpNm;
	}
	/**
	 * @param custTpNm the custTpNm to set
	 */
	public void setCustTpNm(String custTpNm) {
		this.custTpNm = custTpNm;
	}
	/**
	 * @return the custNm
	 */
	public String getCustNm() {
		return custNm;
	}
	/**
	 * @param custNm the custNm to set
	 */
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	/**
	 * @return the svcTelNoAsMask
	 */
	public String getSvcTelNoAsMask() {
		return svcTelNoAsMask;
	}
	/**
	 * @param svcTelNoAsMask the svcTelNoAsMask to set
	 */
	public void setSvcTelNoAsMask(String svcTelNoAsMask) {
		this.svcTelNoAsMask = svcTelNoAsMask;
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
	 * @return the orderCd
	 */
	public String getOrderCd() {
		return orderCd;
	}
	/**
	 * @param orderCd the orderCd to set
	 */
	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
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
	 * @return the orderTpNm
	 */
	public String getOrderTpNm() {
		return orderTpNm;
	}
	/**
	 * @param orderTpNm the orderTpNm to set
	 */
	public void setOrderTpNm(String orderTpNm) {
		this.orderTpNm = orderTpNm;
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
	 * @return the orderStatNm
	 */
	public String getOrderStatNm() {
		return orderStatNm;
	}
	/**
	 * @param orderStatNm the orderStatNm to set
	 */
	public void setOrderStatNm(String orderStatNm) {
		this.orderStatNm = orderStatNm;
	}
	/**
	 * @return the cnslMstCl
	 */
	public String getCnslMstCl() {
		return cnslMstCl;
	}
	/**
	 * @param cnslMstCl the cnslMstCl to set
	 */
	public void setCnslMstCl(String cnslMstCl) {
		this.cnslMstCl = cnslMstCl;
	}
	/**
	 * @return the cnslMstClNm
	 */
	public String getCnslMstClNm() {
		return cnslMstClNm;
	}
	/**
	 * @param cnslMstClNm the cnslMstClNm to set
	 */
	public void setCnslMstClNm(String cnslMstClNm) {
		this.cnslMstClNm = cnslMstClNm;
	}
	/**
	 * @return the cnslMidCl
	 */
	public String getCnslMidCl() {
		return cnslMidCl;
	}
	/**
	 * @param cnslMidCl the cnslMidCl to set
	 */
	public void setCnslMidCl(String cnslMidCl) {
		this.cnslMidCl = cnslMidCl;
	}
	/**
	 * @return the cnslMidClNm
	 */
	public String getCnslMidClNm() {
		return cnslMidClNm;
	}
	/**
	 * @param cnslMidClNm the cnslMidClNm to set
	 */
	public void setCnslMidClNm(String cnslMidClNm) {
		this.cnslMidClNm = cnslMidClNm;
	}
	/**
	 * @return the cnslSlvCl
	 */
	public String getCnslSlvCl() {
		return cnslSlvCl;
	}
	/**
	 * @param cnslSlvCl the cnslSlvCl to set
	 */
	public void setCnslSlvCl(String cnslSlvCl) {
		this.cnslSlvCl = cnslSlvCl;
	}
	/**
	 * @return the cnslSlvClNm
	 */
	public String getCnslSlvClNm() {
		return cnslSlvClNm;
	}
	/**
	 * @param cnslSlvClNm the cnslSlvClNm to set
	 */
	public void setCnslSlvClNm(String cnslSlvClNm) {
		this.cnslSlvClNm = cnslSlvClNm;
	}
	/**
	 * @return the basicSvcCd
	 */
	public String getBasicSvcCd() {
		return basicSvcCd;
	}
	/**
	 * @param basicSvcCd the basicSvcCd to set
	 */
	public void setBasicSvcCd(String basicSvcCd) {
		this.basicSvcCd = basicSvcCd;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderCommonVO [rcptId=");
		builder.append(rcptId);
		builder.append(", soId=");
		builder.append(soId);
		builder.append(", ctrtId=");
		builder.append(ctrtId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", custTp=");
		builder.append(custTp);
		builder.append(", custTpNm=");
		builder.append(custTpNm);
		builder.append(", custNm=");
		builder.append(custNm);
		builder.append(", svcTelNoAsMask=");
		builder.append(svcTelNoAsMask);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", orderCd=");
		builder.append(orderCd);
		builder.append(", orderTp=");
		builder.append(orderTp);
		builder.append(", orderTpNm=");
		builder.append(orderTpNm);
		builder.append(", orderStat=");
		builder.append(orderStat);
		builder.append(", orderStatNm=");
		builder.append(orderStatNm);
		builder.append(", cnslMstCl=");
		builder.append(cnslMstCl);
		builder.append(", cnslMstClNm=");
		builder.append(cnslMstClNm);
		builder.append(", cnslMidCl=");
		builder.append(cnslMidCl);
		builder.append(", cnslMidClNm=");
		builder.append(cnslMidClNm);
		builder.append(", cnslSlvCl=");
		builder.append(cnslSlvCl);
		builder.append(", cnslSlvClNm=");
		builder.append(cnslSlvClNm);
		builder.append(", basicSvcCd=");
		builder.append(basicSvcCd);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}
							
}
