package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class PrecheckInfoVO implements Serializable,CommonVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4519666572404666770L;
	private String soId;
	private String custId;
	private String rcptId;
	private String orderCd;
	private String basicSvcCd;
	private String custTp;
	private String custTpNm;
	private String attrCd;
	private String checkCd;
	private String checkCdNm;
	private String checkValue;
	private String evalCd;
	private String evalCdNm;
	private String resultValue;
	private String resultCd;
	private String resultCdNm;
	private String regrId; /* 등록자 */
	private String regrIdNm; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자 */
	private String chgrIdNm; /* 수정자 */
	private Date chgDate; /* 수정일시 */
	private String precheckNm;
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
	 * @return the attrCd
	 */
	public String getAttrCd() {
		return attrCd;
	}
	/**
	 * @param attrCd the attrCd to set
	 */
	public void setAttrCd(String attrCd) {
		this.attrCd = attrCd;
	}
	/**
	 * @return the checkCd
	 */
	public String getCheckCd() {
		return checkCd;
	}
	/**
	 * @param checkCd the checkCd to set
	 */
	public void setCheckCd(String checkCd) {
		this.checkCd = checkCd;
	}
	/**
	 * @return the checkCdNm
	 */
	public String getCheckCdNm() {
		return checkCdNm;
	}
	/**
	 * @param checkCdNm the checkCdNm to set
	 */
	public void setCheckCdNm(String checkCdNm) {
		this.checkCdNm = checkCdNm;
	}
	/**
	 * @return the checkValue
	 */
	public String getCheckValue() {
		return checkValue;
	}
	/**
	 * @param checkValue the checkValue to set
	 */
	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}
	/**
	 * @return the evalCd
	 */
	public String getEvalCd() {
		return evalCd;
	}
	/**
	 * @param evalCd the evalCd to set
	 */
	public void setEvalCd(String evalCd) {
		this.evalCd = evalCd;
	}
	/**
	 * @return the evalCdNm
	 */
	public String getEvalCdNm() {
		return evalCdNm;
	}
	/**
	 * @param evalCdNm the evalCdNm to set
	 */
	public void setEvalCdNm(String evalCdNm) {
		this.evalCdNm = evalCdNm;
	}
	/**
	 * @return the resultValue
	 */
	public String getResultValue() {
		return resultValue;
	}
	/**
	 * @param resultValue the resultValue to set
	 */
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
	/**
	 * @return the resultCd
	 */
	public String getResultCd() {
		return resultCd;
	}
	/**
	 * @param resultCd the resultCd to set
	 */
	public void setResultCd(String resultCd) {
		this.resultCd = resultCd;
	}
	/**
	 * @return the resultCdNm
	 */
	public String getResultCdNm() {
		return resultCdNm;
	}
	/**
	 * @param resultCdNm the resultCdNm to set
	 */
	public void setResultCdNm(String resultCdNm) {
		this.resultCdNm = resultCdNm;
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
	 * @return the regrIdNm
	 */
	public String getRegrIdNm() {
		return regrIdNm;
	}
	/**
	 * @param regrIdNm the regrIdNm to set
	 */
	public void setRegrIdNm(String regrIdNm) {
		this.regrIdNm = regrIdNm;
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
	 * @return the chgrIdNm
	 */
	public String getChgrIdNm() {
		return chgrIdNm;
	}
	/**
	 * @param chgrIdNm the chgrIdNm to set
	 */
	public void setChgrIdNm(String chgrIdNm) {
		this.chgrIdNm = chgrIdNm;
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
	/**
	 * @return the precheckNm
	 */
	public String getPrecheckNm() {
		return precheckNm;
	}
	/**
	 * @param precheckNm the precheckNm to set
	 */
	public void setPrecheckNm(String precheckNm) {
		this.precheckNm = precheckNm;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrecheckInfoVO [soId=");
		builder.append(soId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", rcptId=");
		builder.append(rcptId);
		builder.append(", orderCd=");
		builder.append(orderCd);
		builder.append(", basicSvcCd=");
		builder.append(basicSvcCd);
		builder.append(", custTp=");
		builder.append(custTp);
		builder.append(", custTpNm=");
		builder.append(custTpNm);
		builder.append(", attrCd=");
		builder.append(attrCd);
		builder.append(", checkCd=");
		builder.append(checkCd);
		builder.append(", checkCdNm=");
		builder.append(checkCdNm);
		builder.append(", checkValue=");
		builder.append(checkValue);
		builder.append(", evalCd=");
		builder.append(evalCd);
		builder.append(", evalCdNm=");
		builder.append(evalCdNm);
		builder.append(", resultValue=");
		builder.append(resultValue);
		builder.append(", resultCd=");
		builder.append(resultCd);
		builder.append(", resultCdNm=");
		builder.append(resultCdNm);
		builder.append(", regrId=");
		builder.append(regrId);
		builder.append(", regrIdNm=");
		builder.append(regrIdNm);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", chgrId=");
		builder.append(chgrId);
		builder.append(", chgrIdNm=");
		builder.append(chgrIdNm);
		builder.append(", chgDate=");
		builder.append(chgDate);
		builder.append(", precheckNm=");
		builder.append(precheckNm);
		builder.append("]");
		return builder.toString();
	}
			
}
