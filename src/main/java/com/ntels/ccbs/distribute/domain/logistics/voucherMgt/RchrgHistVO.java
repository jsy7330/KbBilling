package com.ntels.ccbs.distribute.domain.logistics.voucherMgt;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;
import com.ntels.ccbs.system.domain.common.service.Paging;

public class RchrgHistVO extends Paging implements Serializable, CommonVO {

	private static final long serialVersionUID = 1L;

	private String newOrderId;
	private String rchrgSeqNo;
	private String rchrgTp;
	private String rchrgStat;
	private String rchrgDt;
	private String rchrgChgDt;
	private Integer rchrgCnt;
	private String custId;
	private String ctrtId;
	private String soId;
	private String prodCd;
	private String thrwyChrgId;
	private Double chrgAmt;
	private Double chrgAddTx;
	private Double chrgBillAmt;
	private String delFlg;
	private String regrId;
	private Date regDate;
	private String chgrId;
	private Date chgDate;
	private String ifSucYn;
	private String ifRsnCd;
	private String srvTrxSno;
	private String svcTelNo;
	private String trgtCustId;
	private String trgtCtrtId;
	private String trgtSvcTelNo;
	private String svcEffDtm;
	private String voSeqNo;
	private String voBarCd;
	private String apprNo;
	private String apprTp;
	private String rchrgTermDt;
	private String dataQuota;
	private String rchrgTm;
	private String rchrgTermTm;

	public String getNewOrderId() {
		return newOrderId;
	}

	public void setNewOrderId(String newOrderId) {
		this.newOrderId = newOrderId;
	}

	public String getRchrgSeqNo() {
		return rchrgSeqNo;
	}

	public void setRchrgSeqNo(String rchrgSeqNo) {
		this.rchrgSeqNo = rchrgSeqNo;
	}

	public String getRchrgTp() {
		return rchrgTp;
	}

	public void setRchrgTp(String rchrgTp) {
		this.rchrgTp = rchrgTp;
	}

	public String getRchrgStat() {
		return rchrgStat;
	}

	public void setRchrgStat(String rchrgStat) {
		this.rchrgStat = rchrgStat;
	}

	public String getRchrgDt() {
		return rchrgDt;
	}

	public void setRchrgDt(String rchrgDt) {
		this.rchrgDt = rchrgDt;
	}

	public String getRchrgChgDt() {
		return rchrgChgDt;
	}

	public void setRchrgChgDt(String rchrgChgDt) {
		this.rchrgChgDt = rchrgChgDt;
	}

	public Integer getRchrgCnt() {
		return rchrgCnt;
	}

	public void setRchrgCnt(Integer rchrgCnt) {
		this.rchrgCnt = rchrgCnt;
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

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getProdCd() {
		return prodCd;
	}

	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}

	public String getThrwyChrgId() {
		return thrwyChrgId;
	}

	public void setThrwyChrgId(String thrwyChrgId) {
		this.thrwyChrgId = thrwyChrgId;
	}

	public Double getChrgAmt() {
		return chrgAmt;
	}

	public void setChrgAmt(Double chrgAmt) {
		this.chrgAmt = chrgAmt;
	}

	public Double getChrgAddTx() {
		return chrgAddTx;
	}

	public void setChrgAddTx(Double chrgAddTx) {
		this.chrgAddTx = chrgAddTx;
	}

	public Double getChrgBillAmt() {
		return chrgBillAmt;
	}

	public void setChrgBillAmt(Double chrgBillAmt) {
		this.chrgBillAmt = chrgBillAmt;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
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

	public String getIfSucYn() {
		return ifSucYn;
	}

	public void setIfSucYn(String ifSucYn) {
		this.ifSucYn = ifSucYn;
	}

	public String getIfRsnCd() {
		return ifRsnCd;
	}

	public void setIfRsnCd(String ifRsnCd) {
		this.ifRsnCd = ifRsnCd;
	}

	public String getSrvTrxSno() {
		return srvTrxSno;
	}

	public void setSrvTrxSno(String srvTrxSno) {
		this.srvTrxSno = srvTrxSno;
	}

	public String getSvcTelNo() {
		return svcTelNo;
	}

	public void setSvcTelNo(String svcTelNo) {
		this.svcTelNo = svcTelNo;
	}

	public String getTrgtCustId() {
		return trgtCustId;
	}

	public void setTrgtCustId(String trgtCustId) {
		this.trgtCustId = trgtCustId;
	}

	public String getTrgtCtrtId() {
		return trgtCtrtId;
	}

	public void setTrgtCtrtId(String trgtCtrtId) {
		this.trgtCtrtId = trgtCtrtId;
	}

	public String getTrgtSvcTelNo() {
		return trgtSvcTelNo;
	}

	public void setTrgtSvcTelNo(String trgtSvcTelNo) {
		this.trgtSvcTelNo = trgtSvcTelNo;
	}

	public String getSvcEffDtm() {
		return svcEffDtm;
	}

	public void setSvcEffDtm(String svcEffDtm) {
		this.svcEffDtm = svcEffDtm;
	}

	public String getVoSeqNo() {
		return voSeqNo;
	}

	public void setVoSeqNo(String voSeqNo) {
		this.voSeqNo = voSeqNo;
	}

	public String getVoBarCd() {
		return voBarCd;
	}

	public void setVoBarCd(String voBarCd) {
		this.voBarCd = voBarCd;
	}

	public String getApprNo() {
		return apprNo;
	}

	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

	public String getApprTp() {
		return apprTp;
	}

	public void setApprTp(String apprTp) {
		this.apprTp = apprTp;
	}

	public String getRchrgTermDt() {
		return rchrgTermDt;
	}

	public void setRchrgTermDt(String rchrgTermDt) {
		this.rchrgTermDt = rchrgTermDt;
	}

	public String getDataQuota() {
		return dataQuota;
	}

	public void setDataQuota(String dataQuota) {
		this.dataQuota = dataQuota;
	}

	public String getRchrgTm() {
		return rchrgTm;
	}

	public void setRchrgTm(String rchrgTm) {
		this.rchrgTm = rchrgTm;
	}

	public String getRchrgTermTm() {
		return rchrgTermTm;
	}

	public void setRchrgTermTm(String rchrgTermTm) {
		this.rchrgTermTm = rchrgTermTm;
	}

}
