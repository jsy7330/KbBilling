package com.ntels.ccbs.charge.domain.batchprocmng.receipterpsndmng;

import java.util.Date;

public class ReceiptErpIntrVO {

	/**
	 * 기준일자
	 */
	private String dataOccDt;
	
	/**
	 * ERP 전송여부
	 */
	private String erpSndYn;
	
	/**
	 * ERP 전송일자
	 */
	private String erpSndDt;
	
	/**
	 * 등록자ID
	 */
	private String chgrId;
	
	/**
	 * 등록일
	 */
	private Date chgDate;

	public String getDataOccDt() {
		return dataOccDt;
	}

	public void setDataOccDt(String dataOccDt) {
		this.dataOccDt = dataOccDt;
	}

	public String getErpSndYn() {
		return erpSndYn;
	}

	public void setErpSndYn(String erpSndYn) {
		this.erpSndYn = erpSndYn;
	}

	public String getErpSndDt() {
		return erpSndDt;
	}

	public void setErpSndDt(String erpSndDt) {
		this.erpSndDt = erpSndDt;
	}

	public String getChgrId() {
		return chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public Date getChgDate() {
		return chgDate;
	}

	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	
}
