package com.ntels.ccbs.charge.domain.batchprocmng.receipterpsndmng;

import java.util.Date;

public class ErpZmis11VO {

	private String	interIdex;	//전송번호
	private String	perncode;	//고객코드
	private String	pernname;	//고객명
	private String	cardcode;	//본부-코드
	private String	cardname;	//본부-명
	private String	artype;		//입금유형
	private String	invtype;	//청구구분
	private String 	docdate;	//입금일자
	private double	bondamt;	//입금금액
	private double	feeamt;		//수수료
	private String	prftcode;	//코스트 센터 부서
	private String	ocrcode2;	//코스트 센터 손익
	private String	memo;		//비고
	private String	regu;		//입금방법
	private String	intertype;	//전송형태
	private String	docstatus;	//매출전송상태
	private Date 	interdate;	//매출수신일자
	private Date 	erpdate;	//SAP 확정일자
	private int		erpentry;	//SAP 문서번호
	private int		erpline;	//SAP 문서라인
	private String	bankcd;		//은행코드
	private String	incardc;	//입금거래처코드
	private String	incardn;	//입금거래처명
	private String	amttype;	//입금구분
	
	public String getInterIdex() {
		return interIdex;
	}
	public void setInterIdex(String interIdex) {
		this.interIdex = interIdex;
	}
	public String getPerncode() {
		return perncode;
	}
	public void setPerncode(String perncode) {
		this.perncode = perncode;
	}
	public String getPernname() {
		return pernname;
	}
	public void setPernname(String pernname) {
		this.pernname = pernname;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getArtype() {
		return artype;
	}
	public void setArtype(String artype) {
		this.artype = artype;
	}
	public String getInvtype() {
		return invtype;
	}
	public void setInvtype(String invtype) {
		this.invtype = invtype;
	}
	public String getDocdate() {
		return docdate;
	}
	public void setDocdate(String docdate) {
		this.docdate = docdate;
	}
	public double getBondamt() {
		return bondamt;
	}
	public void setBondamt(double bondamt) {
		this.bondamt = bondamt;
	}
	public double getFeeamt() {
		return feeamt;
	}
	public void setFeeamt(double feeamt) {
		this.feeamt = feeamt;
	}
	public String getPrftcode() {
		return prftcode;
	}
	public void setPrftcode(String prftcode) {
		this.prftcode = prftcode;
	}
	public String getOcrcode2() {
		return ocrcode2;
	}
	public void setOcrcode2(String ocrcode2) {
		this.ocrcode2 = ocrcode2;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRegu() {
		return regu;
	}
	public void setRegu(String regu) {
		this.regu = regu;
	}
	public String getIntertype() {
		return intertype;
	}
	public void setIntertype(String intertype) {
		this.intertype = intertype;
	}
	public String getDocstatus() {
		return docstatus;
	}
	public void setDocstatus(String docstatus) {
		this.docstatus = docstatus;
	}
	public Date getInterdate() {
		return interdate;
	}
	public void setInterdate(Date interdate) {
		this.interdate = interdate;
	}
	public Date getErpdate() {
		return erpdate;
	}
	public void setErpdate(Date erpdate) {
		this.erpdate = erpdate;
	}
	public int getErpentry() {
		return erpentry;
	}
	public void setErpentry(int erpentry) {
		this.erpentry = erpentry;
	}
	public int getErpline() {
		return erpline;
	}
	public void setErpline(int erpline) {
		this.erpline = erpline;
	}
	public String getBankcd() {
		return bankcd;
	}
	public void setBankcd(String bankcd) {
		this.bankcd = bankcd;
	}
	public String getIncardc() {
		return incardc;
	}
	public void setIncardc(String incardc) {
		this.incardc = incardc;
	}
	public String getIncardn() {
		return incardn;
	}
	public void setIncardn(String incardn) {
		this.incardn = incardn;
	}
	public String getAmttype() {
		return amttype;
	}
	public void setAmttype(String amttype) {
		this.amttype = amttype;
	}
	
}
