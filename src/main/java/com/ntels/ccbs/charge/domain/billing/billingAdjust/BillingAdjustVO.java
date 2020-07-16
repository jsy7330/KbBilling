package com.ntels.ccbs.charge.domain.billing.billingAdjust;

import java.io.Serializable;
import java.util.Date;
import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class BillingAdjustVO implements Serializable,CommonVO{

	private static final long serialVersionUID = -6112760163377176959L;
	
	private String soId; /* 사업구분 */
	private String regrId; /* 등록자ID */
	private String regrNm; /* 등록자 */
	private Date   regDate; /* 등록일시 */
	private String chgrId; /* 수정자ID */
	private String chgrNm; /* 수정자명 */
	private Date   chgDate; /* 변경일시 */
	private String commonCd;
	private String commonCdNm;
	private String pymAcnt;
	private String custNm;
	private String custTp;
	private String custTpNm;
	private String addr;
	private String pymAcntId;
	private String ctrtIdCnt;
	private String prodCdCnt;
	private String svcCdCnt;
	private String adjNo;
	private String billSeqNo;
	private String adjPt;
	private String adjPtNm;
	private String hopeAplyYymm;
	private String DcsnProcStat;
	private String applDttm;
	private String rcptPsnNm;
	private String applSoNm;
	private String soNm;
	private String apprrId;
	private String lngTyp;
	private String acntNm;
	private String dcsnProcStatNm;
	private String gubun;
	private String useYymm;
	private String prodCmpsId;
	private String svcCmpsId;
	private String chrgItmCd;
	private String chrgItmNm;
	private String custId;
	private Double adjPrevBillAmt;
	private Double adjAmt;
	private Double adjAftBillAmt;
	private Double adjApplAmt;
	private String prodCd;
	private String prodNm;
	private String svcCd;
	private String exrate;
	private String crncyCd;
	private String exrateAplyDt;
	private String rateStrtDt;
	private String termDt;
	private String ctrtStat;
	private String ctrtStatNm;
	private String svcTelNo;
	private String currentYymm;
	private String clsTskCl;
	private String billCycl;
	private String mnsFlag;
	private String ctrtId;
	private String billYymm;
	private String clsDt;
	private Double orgAdjApplAmt;
	private Double totAdjApplAmt;
	private String orgAdjNo;
	private String billDt;
	private Double adjPrvBillAmtA;
	private Double adjAmtA;
	private String payDueDt;
	private Double adjPrvBillAmt;
	private Double billAmt;
	private Double preAdjApplAmt;
	private String chgrIdNm;
	private String chgDttm;
	private String billAplyDt;
	private Double billRcptAmt;
	private Double rcptAmt;
	private String taxBillYn;
	private Double pymRcptAmt;
	private String usgCnt;
	private String billMmTp;
	private String mmTp;
	private String grpId;
	private Double unpayAmt;
	private String attrVal;
	private String adjResnCd;
	private String adjApplConts;
	private String saleAplyYn;
	private String rcptId;
	private String pymAcntNm;
	private String appntTelNo;
	private String sidx;
	private String sord;
	
	public String getAppntTelNo() {
		return appntTelNo;
	}
	public void setAppntTelNo(String appntTelNo) {
		this.appntTelNo = appntTelNo;
	}
	public String getPymAcntNm() {
		return pymAcntNm;
	}
	public void setPymAcntNm(String pymAcntNm) {
		this.pymAcntNm = pymAcntNm;
	}
	public String getRcptId() {
		return rcptId;
	}
	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
	}
	public String getSaleAplyYn() {
		return saleAplyYn;
	}
	public void setSaleAplyYn(String saleAplyYn) {
		this.saleAplyYn = saleAplyYn;
	}
	public String getAdjApplConts() {
		return adjApplConts;
	}
	public void setAdjApplConts(String adjApplConts) {
		this.adjApplConts = adjApplConts;
	}
	public String getAdjResnCd() {
		return adjResnCd;
	}
	public void setAdjResnCd(String adjResnCd) {
		this.adjResnCd = adjResnCd;
	}
	public Double getUnpayAmt() {
		return unpayAmt;
	}
	public void setUnpayAmt(Double unpayAmt) {
		this.unpayAmt = unpayAmt;
	}
	public String getUsgCnt() {
		return usgCnt;
	}
	public void setUsgCnt(String usgCnt) {
		this.usgCnt = usgCnt;
	}
	public String getBillMmTp() {
		return billMmTp;
	}
	public void setBillMmTp(String billMmTp) {
		this.billMmTp = billMmTp;
	}
	public String getMmTp() {
		return mmTp;
	}
	public void setMmTp(String mmTp) {
		this.mmTp = mmTp;
	}
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public String getAttrVal() {
		return attrVal;
	}
	public void setAttrVal(String attrVal) {
		this.attrVal = attrVal;
	}
	public Double getPymRcptAmt() {
		return pymRcptAmt;
	}
	public void setPymRcptAmt(Double pymRcptAmt) {
		this.pymRcptAmt = pymRcptAmt;
	}
	public Double getAdjPrevBillAmt() {
		return adjPrevBillAmt;
	}
	public void setAdjPrevBillAmt(Double adjPrevBillAmt) {
		this.adjPrevBillAmt = adjPrevBillAmt;
	}
	public Double getBillRcptAmt() {
		return billRcptAmt;
	}
	public void setBillRcptAmt(Double billRcptAmt) {
		this.billRcptAmt = billRcptAmt;
	}
	public Double getRcptAmt() {
		return rcptAmt;
	}
	public void setRcptAmt(Double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}
	public String getTaxBillYn() {
		return taxBillYn;
	}
	public void setTaxBillYn(String taxBillYn) {
		this.taxBillYn = taxBillYn;
	}
	public Double getAdjAmt() {
		return adjAmt;
	}
	public void setAdjAmt(Double adjAmt) {
		this.adjAmt = adjAmt;
	}
	public Double getAdjAftBillAmt() {
		return adjAftBillAmt;
	}
	public void setAdjAftBillAmt(Double adjAftBillAmt) {
		this.adjAftBillAmt = adjAftBillAmt;
	}
	public Double getAdjApplAmt() {
		return adjApplAmt;
	}
	public void setAdjApplAmt(Double adjApplAmt) {
		this.adjApplAmt = adjApplAmt;
	}
	public Double getOrgAdjApplAmt() {
		return orgAdjApplAmt;
	}
	public void setOrgAdjApplAmt(Double orgAdjApplAmt) {
		this.orgAdjApplAmt = orgAdjApplAmt;
	}
	public Double getTotAdjApplAmt() {
		return totAdjApplAmt;
	}
	public void setTotAdjApplAmt(Double totAdjApplAmt) {
		this.totAdjApplAmt = totAdjApplAmt;
	}
	public Double getAdjPrvBillAmtA() {
		return adjPrvBillAmtA;
	}
	public void setAdjPrvBillAmtA(Double adjPrvBillAmtA) {
		this.adjPrvBillAmtA = adjPrvBillAmtA;
	}
	public Double getAdjAmtA() {
		return adjAmtA;
	}
	public void setAdjAmtA(Double adjAmtA) {
		this.adjAmtA = adjAmtA;
	}
	public Double getAdjPrvBillAmt() {
		return adjPrvBillAmt;
	}
	public void setAdjPrvBillAmt(Double adjPrvBillAmt) {
		this.adjPrvBillAmt = adjPrvBillAmt;
	}
	public Double getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}
	public Double getPreAdjApplAmt() {
		return preAdjApplAmt;
	}
	public void setPreAdjApplAmt(Double preAdjApplAmt) {
		this.preAdjApplAmt = preAdjApplAmt;
	}
	public String getOrgAdjNo() {
		return orgAdjNo;
	}
	public void setOrgAdjNo(String orgAdjNo) {
		this.orgAdjNo = orgAdjNo;
	}
	public String getBillDt() {
		return billDt;
	}
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}
	public String getPayDueDt() {
		return payDueDt;
	}
	public void setPayDueDt(String payDueDt) {
		this.payDueDt = payDueDt;
	}
	public String getChgrIdNm() {
		return chgrIdNm;
	}
	public void setChgrIdNm(String chgrIdNm) {
		this.chgrIdNm = chgrIdNm;
	}
	public String getChgDttm() {
		return chgDttm;
	}
	public void setChgDttm(String chgDttm) {
		this.chgDttm = chgDttm;
	}
	public String getBillAplyDt() {
		return billAplyDt;
	}
	public void setBillAplyDt(String billAplyDt) {
		this.billAplyDt = billAplyDt;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getClsDt() {
		return clsDt;
	}
	public void setClsDt(String clsDt) {
		this.clsDt = clsDt;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getMnsFlag() {
		return mnsFlag;
	}
	public void setMnsFlag(String mnsFlag) {
		this.mnsFlag = mnsFlag;
	}
	public String getClsTskCl() {
		return clsTskCl;
	}
	public void setClsTskCl(String clsTskCl) {
		this.clsTskCl = clsTskCl;
	}
	public String getBillCycl() {
		return billCycl;
	}
	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}
	public String getCurrentYymm() {
		return currentYymm;
	}
	public void setCurrentYymm(String currentYymm) {
		this.currentYymm = currentYymm;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getUseYymm() {
		return useYymm;
	}
	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}
	public String getProdCmpsId() {
		return prodCmpsId;
	}
	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}
	public String getSvcCmpsId() {
		return svcCmpsId;
	}
	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}
	public String getChrgItmCd() {
		return chrgItmCd;
	}
	public void setChrgItmCd(String chrgItmCd) {
		this.chrgItmCd = chrgItmCd;
	}
	public String getChrgItmNm() {
		return chrgItmNm;
	}
	public void setChrgItmNm(String chrgItmNm) {
		this.chrgItmNm = chrgItmNm;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getSvcCd() {
		return svcCd;
	}
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}
	public String getExrate() {
		return exrate;
	}
	public void setExrate(String exrate) {
		this.exrate = exrate;
	}
	public String getCrncyCd() {
		return crncyCd;
	}
	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}
	public String getExrateAplyDt() {
		return exrateAplyDt;
	}
	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
	}
	public String getRateStrtDt() {
		return rateStrtDt;
	}
	public void setRateStrtDt(String rateStrtDt) {
		this.rateStrtDt = rateStrtDt;
	}
	public String getTermDt() {
		return termDt;
	}
	public void setTermDt(String termDt) {
		this.termDt = termDt;
	}
	public String getCtrtStat() {
		return ctrtStat;
	}
	public void setCtrtStat(String ctrtStat) {
		this.ctrtStat = ctrtStat;
	}
	public String getCtrtStatNm() {
		return ctrtStatNm;
	}
	public void setCtrtStatNm(String ctrtStatNm) {
		this.ctrtStatNm = ctrtStatNm;
	}
	public String getSvcTelNo() {
		return svcTelNo;
	}
	public void setSvcTelNo(String svcTelNo) {
		this.svcTelNo = svcTelNo;
	}
	public String getDcsnProcStatNm() {
		return dcsnProcStatNm;
	}
	public void setDcsnProcStatNm(String dcsnProcStatNm) {
		this.dcsnProcStatNm = dcsnProcStatNm;
	}
	public String getAcntNm() {
		return acntNm;
	}
	public void setAcntNm(String acntNm) {
		this.acntNm = acntNm;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getRegrNm() {
		return regrNm;
	}
	public void setRegrNm(String regrNm) {
		this.regrNm = regrNm;
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
	public String getChgrNm() {
		return chgrNm;
	}
	public void setChgrNm(String chgrNm) {
		this.chgrNm = chgrNm;
	}
	public Date getChgDate() {
		return chgDate;
	}
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	public String getCommonCd() {
		return commonCd;
	}
	public void setCommonCd(String commonCd) {
		this.commonCd = commonCd;
	}
	public String getCommonCdNm() {
		return commonCdNm;
	}
	public void setCommonCdNm(String commonCdNm) {
		this.commonCdNm = commonCdNm;
	}
	public String getPymAcnt() {
		return pymAcnt;
	}
	public void setPymAcnt(String pymAcnt) {
		this.pymAcnt = pymAcnt;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getCustTp() {
		return custTp;
	}
	public void setCustTp(String custTp) {
		this.custTp = custTp;
	}
	public String getCustTpNm() {
		return custTpNm;
	}
	public void setCustTpNm(String custTpNm) {
		this.custTpNm = custTpNm;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getCtrtIdCnt() {
		return ctrtIdCnt;
	}
	public void setCtrtIdCnt(String ctrtIdCnt) {
		this.ctrtIdCnt = ctrtIdCnt;
	}
	public String getProdCdCnt() {
		return prodCdCnt;
	}
	public void setProdCdCnt(String prodCdCnt) {
		this.prodCdCnt = prodCdCnt;
	}
	public String getSvcCdCnt() {
		return svcCdCnt;
	}
	public void setSvcCdCnt(String svcCdCnt) {
		this.svcCdCnt = svcCdCnt;
	}
	public String getAdjNo() {
		return adjNo;
	}
	public void setAdjNo(String adjNo) {
		this.adjNo = adjNo;
	}
	public String getBillSeqNo() {
		return billSeqNo;
	}
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}
	public String getAdjPt() {
		return adjPt;
	}
	public void setAdjPt(String adjPt) {
		this.adjPt = adjPt;
	}
	public String getAdjPtNm() {
		return adjPtNm;
	}
	public void setAdjPtNm(String adjPtNm) {
		this.adjPtNm = adjPtNm;
	}
	public String getHopeAplyYymm() {
		return hopeAplyYymm;
	}
	public void setHopeAplyYymm(String hopeAplyYymm) {
		this.hopeAplyYymm = hopeAplyYymm;
	}
	public String getDcsnProcStat() {
		return DcsnProcStat;
	}
	public void setDcsnProcStat(String dcsnProcStat) {
		DcsnProcStat = dcsnProcStat;
	}
	public String getApplDttm() {
		return applDttm;
	}
	public void setApplDttm(String applDttm) {
		this.applDttm = applDttm;
	}
	public String getRcptPsnNm() {
		return rcptPsnNm;
	}
	public void setRcptPsnNm(String rcptPsnNm) {
		this.rcptPsnNm = rcptPsnNm;
	}
	public String getApplSoNm() {
		return applSoNm;
	}
	public void setApplSoNm(String applSoNm) {
		this.applSoNm = applSoNm;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getApprrId() {
		return apprrId;
	}
	public void setApprrId(String apprrId) {
		this.apprrId = apprrId;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	
}
