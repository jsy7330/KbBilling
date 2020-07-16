package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class DeviceHistVO implements Serializable,CommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1907746140898508972L;
	private String soId; /* 사업구분 */
	private String ctrtId; /* 계약ID */
	private String itemMidCd; /* 장비유형 */
	private String serialNo; /* 일련번호 */
	private String inactDttm; /* 종료일시 */
	private String actDttm; /* 시작일시 */
	private String deviceStatus; /* 장비상태 */
	private String imei; /* IMEI */
	private String iccid; /* ICCID */
	private String imsi; /* IMSI */
	private String eqtClCd; /* EQT_CL_CD */
	private String itemId; /* ITEM_ID */
	private String prodCmpsId; /* 상품구성ID */
	private String svcCmpsId; /* 서비스구성ID */
	private String deviceChgCd; /* 변경사유 */
	private String regrId; /* 등록자ID */
	private Date regDate; /* 등록일 */
	private String chgrId; /* 수정자ID */
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
	 * @return the itemMidCd
	 */
	public String getItemMidCd() {
		return itemMidCd;
	}
	/**
	 * @param itemMidCd the itemMidCd to set
	 */
	public void setItemMidCd(String itemMidCd) {
		this.itemMidCd = itemMidCd;
	}
	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	 * @return the deviceStatus
	 */
	public String getDeviceStatus() {
		return deviceStatus;
	}
	/**
	 * @param deviceStatus the deviceStatus to set
	 */
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * @return the iccid
	 */
	public String getIccid() {
		return iccid;
	}
	/**
	 * @param iccid the iccid to set
	 */
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}
	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	/**
	 * @return the eqtClCd
	 */
	public String getEqtClCd() {
		return eqtClCd;
	}
	/**
	 * @param eqtClCd the eqtClCd to set
	 */
	public void setEqtClCd(String eqtClCd) {
		this.eqtClCd = eqtClCd;
	}
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	 * @return the deviceChgCd
	 */
	public String getDeviceChgCd() {
		return deviceChgCd;
	}
	/**
	 * @param deviceChgCd the deviceChgCd to set
	 */
	public void setDeviceChgCd(String deviceChgCd) {
		this.deviceChgCd = deviceChgCd;
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
		builder.append("DeviceHistVO [soId=");
		builder.append(soId);
		builder.append(", ctrtId=");
		builder.append(ctrtId);
		builder.append(", itemMidCd=");
		builder.append(itemMidCd);
		builder.append(", serialNo=");
		builder.append(serialNo);
		builder.append(", inactDttm=");
		builder.append(inactDttm);
		builder.append(", actDttm=");
		builder.append(actDttm);
		builder.append(", deviceStatus=");
		builder.append(deviceStatus);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", iccid=");
		builder.append(iccid);
		builder.append(", imsi=");
		builder.append(imsi);
		builder.append(", eqtClCd=");
		builder.append(eqtClCd);
		builder.append(", itemId=");
		builder.append(itemId);
		builder.append(", prodCmpsId=");
		builder.append(prodCmpsId);
		builder.append(", svcCmpsId=");
		builder.append(svcCmpsId);
		builder.append(", deviceChgCd=");
		builder.append(deviceChgCd);
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
