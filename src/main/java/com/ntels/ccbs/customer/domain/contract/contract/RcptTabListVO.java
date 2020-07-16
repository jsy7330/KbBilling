package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class RcptTabListVO implements Serializable,CommonVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4772728476689409867L;
	private String rcptId;
	private String ctrtId;
	private String rcptTpNm;
	private String cnslStatNm;
	private String tranStatNm;
	private String rcptUsrInfo;
	private String rcptDttm;
	private String procUsrInfo;
	private String procDttm;
	private String cmplUsrInfo;
	private String cmplDttm;
	private String cnslMstClNm;
	private String cnslMidClNm;
	private String cnslSlvClNm;
	private String reqDesc;
	private String procDesc;
	
	private String custRelNm;
	private String reqNm;
	private String reqTelNo;

	private String colorFl;	 /*색상플래그*/
	
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
	 * @return the rcptTpNm
	 */
	public String getRcptTpNm() {
		return rcptTpNm;
	}
	/**
	 * @param rcptTpNm the rcptTpNm to set
	 */
	public void setRcptTpNm(String rcptTpNm) {
		this.rcptTpNm = rcptTpNm;
	}
	/**
	 * @return the cnslStatNm
	 */
	public String getCnslStatNm() {
		return cnslStatNm;
	}
	/**
	 * @param cnslStatNm the cnslStatNm to set
	 */
	public void setCnslStatNm(String cnslStatNm) {
		this.cnslStatNm = cnslStatNm;
	}
	/**
	 * @return the tranStatNm
	 */
	public String getTranStatNm() {
		return tranStatNm;
	}
	/**
	 * @param tranStatNm the tranStatNm to set
	 */
	public void setTranStatNm(String tranStatNm) {
		this.tranStatNm = tranStatNm;
	}
	/**
	 * @return the rcptUsrInfo
	 */
	public String getRcptUsrInfo() {
		return rcptUsrInfo;
	}
	/**
	 * @param rcptUsrInfo the rcptUsrInfo to set
	 */
	public void setRcptUsrInfo(String rcptUsrInfo) {
		this.rcptUsrInfo = rcptUsrInfo;
	}
	/**
	 * @return the rcptDttm
	 */
	public String getRcptDttm() {
		return rcptDttm;
	}
	/**
	 * @param rcptDttm the rcptDttm to set
	 */
	public void setRcptDttm(String rcptDttm) {
		this.rcptDttm = rcptDttm;
	}
	/**
	 * @return the procUsrInfo
	 */
	public String getProcUsrInfo() {
		return procUsrInfo;
	}
	/**
	 * @param procUsrInfo the procUsrInfo to set
	 */
	public void setProcUsrInfo(String procUsrInfo) {
		this.procUsrInfo = procUsrInfo;
	}
	/**
	 * @return the procDttm
	 */
	public String getProcDttm() {
		return procDttm;
	}
	/**
	 * @param procDttm the procDttm to set
	 */
	public void setProcDttm(String procDttm) {
		this.procDttm = procDttm;
	}
	/**
	 * @return the cmplUsrInfo
	 */
	public String getCmplUsrInfo() {
		return cmplUsrInfo;
	}
	/**
	 * @param cmplUsrInfo the cmplUsrInfo to set
	 */
	public void setCmplUsrInfo(String cmplUsrInfo) {
		this.cmplUsrInfo = cmplUsrInfo;
	}
	/**
	 * @return the cmplDttm
	 */
	public String getCmplDttm() {
		return cmplDttm;
	}
	/**
	 * @param cmplDttm the cmplDttm to set
	 */
	public void setCmplDttm(String cmplDttm) {
		this.cmplDttm = cmplDttm;
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
	 * @return the reqDesc
	 */
	public String getReqDesc() {
		return reqDesc;
	}
	/**
	 * @param reqDesc the reqDesc to set
	 */
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	/**
	 * @return the procDesc
	 */
	public String getProcDesc() {
		return procDesc;
	}
	/**
	 * @param procDesc the procDesc to set
	 */
	public void setProcDesc(String procDesc) {
		this.procDesc = procDesc;
	}
	
	public String getCustRelNm() {
		return custRelNm;
	}
	public void setCustRelNm(String custRelNm) {
		this.custRelNm = custRelNm;
	}
	public String getReqNm() {
		return reqNm;
	}
	public void setReqNm(String reqNm) {
		this.reqNm = reqNm;
	}
	public String getReqTelNo() {
		return reqTelNo;
	}
	public void setReqTelNo(String reqTelNo) {
		this.reqTelNo = reqTelNo;
	}
	public String getColorFl() {
		return colorFl;
	}
	public void setColorFl(String colorFl) {
		this.colorFl = colorFl;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcptTabListVO [rcptId=");
		builder.append(rcptId);
		builder.append(", ctrtId=");
		builder.append(ctrtId);
		builder.append(", rcptTpNm=");
		builder.append(rcptTpNm);
		builder.append(", cnslStatNm=");
		builder.append(cnslStatNm);
		builder.append(", tranStatNm=");
		builder.append(tranStatNm);
		builder.append(", rcptUsrInfo=");
		builder.append(rcptUsrInfo);
		builder.append(", rcptDttm=");
		builder.append(rcptDttm);
		builder.append(", procUsrInfo=");
		builder.append(procUsrInfo);
		builder.append(", procDttm=");
		builder.append(procDttm);
		builder.append(", cmplUsrInfo=");
		builder.append(cmplUsrInfo);
		builder.append(", cmplDttm=");
		builder.append(cmplDttm);
		builder.append(", cnslMstClNm=");
		builder.append(cnslMstClNm);
		builder.append(", cnslMidClNm=");
		builder.append(cnslMidClNm);
		builder.append(", cnslSlvClNm=");
		builder.append(cnslSlvClNm);
		builder.append(", reqDesc=");
		builder.append(reqDesc);
		builder.append(", procDesc=");
		builder.append(procDesc);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
