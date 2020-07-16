package com.ntels.ccbs.charge.domain.charge.calculationSearch;

import java.io.Serializable;
import java.util.Date;

public class PaymentChargeCalculationVo implements Serializable{

	private static final long serialVersionUID = -1815374314748838985L;

	private String soId;			//SO_ID
	private String clcWrkNo;		//CLC_WRK_NO
	private String clcWrkCl;		//CLC_WRK_CL
	private String procDttm;		//PROC_DTTM
	private String billYymm;		//BILL_YYMM
	private String billCycl;		//BILL_CYCL
	private String pymAcntId;		//PYM_ACNT_ID
	private String custId;			//CUST_ID
	private String ctrtId;			//CTRT_ID
	private String prodCd;			//PROD_CD
	private String totUseAmt;		//TOT_USE_AMT
	private String totUseCnt;		//TOT_USE_CNT
	private String custNm;			//CUST_NM
	private String prodNm;			//PROD_NM
	private String soNm;			//SO_NM
	
	private String regrId;			// 등록자
	private Date regDate;			// 등록일자
	private String chgrId;			// 수정자
	private Date chgDate;			// 수정일자
	
	private String lngTyp;
	
	
	private String useYymm; // 사용년월
	private String svcCmpsId;
	private String prodCmpsId;
	private String RateItmCd;
	private String rateItmNm;
	private String useAmt;
	private String useCnt;
	
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getClcWrkNo() {
		return clcWrkNo;
	}
	public void setClcWrkNo(String clcWrkNo) {
		this.clcWrkNo = clcWrkNo;
	}
	public String getClcWrkCl() {
		return clcWrkCl;
	}
	public void setClcWrkCl(String clcWrkCl) {
		this.clcWrkCl = clcWrkCl;
	}
	public String getProcDttm() {
		return procDttm;
	}
	public void setProcDttm(String procDttm) {
		this.procDttm = procDttm;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getBillCycl() {
		return billCycl;
	}
	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getTotUseAmt() {
		return totUseAmt;
	}
	public void setTotUseAmt(String totUseAmt) {
		this.totUseAmt = totUseAmt;
	}
	public String getTotUseCnt() {
		return totUseCnt;
	}
	public void setTotUseCnt(String totUseCnt) {
		this.totUseCnt = totUseCnt;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getUseYymm() {
		return useYymm;
	}
	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}
	public String getSvcCmpsId() {
		return svcCmpsId;
	}
	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}
	public String getProdCmpsId() {
		return prodCmpsId;
	}
	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}
	public String getRateItmCd() {
		return RateItmCd;
	}
	public void setRateItmCd(String rateItmCd) {
		RateItmCd = rateItmCd;
	}
	public String getRateItmNm() {
		return rateItmNm;
	}
	public void setRateItmNm(String rateItmNm) {
		this.rateItmNm = rateItmNm;
	}
	public String getUseAmt() {
		return useAmt;
	}
	public void setUseAmt(String useAmt) {
		this.useAmt = useAmt;
	}
	public String getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(String useCnt) {
		this.useCnt = useCnt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
