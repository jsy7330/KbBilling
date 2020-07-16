package com.ntels.ccbs.distribute.domain.logistics.voucherMgt;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;
import com.ntels.ccbs.system.domain.common.service.Paging;

public class VissueVO extends Paging implements Serializable, CommonVO {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	private String soId;
	private String soNm;
	private String vissueSeqNo;
	private String itemId;
	private String itemNm;
	private String mncoOtptUtPrc;
	private String mncoId;
	private String mncoNm;
	private String voTp;
	private String voTpNm;
	private Double voIssueAmt;
	private String voProdCd;
	private String voProdNm;
	private Integer voIssueCnt;
	private Integer transferCnt;
	private String voIssueDt;
	private String voIssueOrgId;
	private String voExpiredDt;
	private String voPoDttm;
	private String poNo;
	private String poDt;
	private String poStat;
	private String poStatNm;
	private String regrId;
	private Date regDate;
	private String chgrId;
	private Date chgDate;
	private Integer issueChkCnt;
	private String statCdChk;
	private String lgstCentId;
	private String lgstCentNm;
	private String dlgdAgreeDt;
	private String dlvZipCd;
	private String dlvBssAddr;
	private String dlvDtlAddr;

	private String lngTyp;

	// 발주금액
	private Double poAmt;

	// 세금정보
	private String taxItmId;
	private String calcTp;
	private Double taxRate;

	private String itemTpCd;

	private String crtSeqNo;
	private String lgstProcDttm;

	private String voBarCd;
	private String fromVoBarCd;
	private String toVoBarCd;

	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}

	/**
	 * @param soId
	 *            the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}

	/**
	 * @return the soNm
	 */
	public String getSoNm() {
		return soNm;
	}

	/**
	 * @param soNm
	 *            the soNm to set
	 */
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}

	/**
	 * @return the vissueSeqNo
	 */
	public String getVissueSeqNo() {
		return vissueSeqNo;
	}

	/**
	 * @param vissueSeqNo
	 *            the vissueSeqNo to set
	 */
	public void setVissueSeqNo(String vissueSeqNo) {
		this.vissueSeqNo = vissueSeqNo;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemNm
	 */
	public String getItemNm() {
		return itemNm;
	}

	/**
	 * @param itemNm
	 *            the itemNm to set
	 */
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}

	/**
	 * @return the mncoOtptUtPrc
	 */
	public String getMncoOtptUtPrc() {
		return mncoOtptUtPrc;
	}

	/**
	 * @param mncoOtptUtPrc
	 *            the mncoOtptUtPrc to set
	 */
	public void setMncoOtptUtPrc(String mncoOtptUtPrc) {
		this.mncoOtptUtPrc = mncoOtptUtPrc;
	}

	/**
	 * @return the mncoId
	 */
	public String getMncoId() {
		return mncoId;
	}

	/**
	 * @param mncoId
	 *            the mncoId to set
	 */
	public void setMncoId(String mncoId) {
		this.mncoId = mncoId;
	}

	/**
	 * @return the mncoNm
	 */
	public String getMncoNm() {
		return mncoNm;
	}

	/**
	 * @param mncoNm
	 *            the mncoNm to set
	 */
	public void setMncoNm(String mncoNm) {
		this.mncoNm = mncoNm;
	}

	/**
	 * @return the voTp
	 */
	public String getVoTp() {
		return voTp;
	}

	/**
	 * @param voTp
	 *            the voTp to set
	 */
	public void setVoTp(String voTp) {
		this.voTp = voTp;
	}

	/**
	 * @return the voTpNm
	 */
	public String getVoTpNm() {
		return voTpNm;
	}

	/**
	 * @param voTpNm
	 *            the voTpNm to set
	 */
	public void setVoTpNm(String voTpNm) {
		this.voTpNm = voTpNm;
	}

	/**
	 * @return the voIssueAmt
	 */
	public Double getVoIssueAmt() {
		return voIssueAmt;
	}

	/**
	 * @param voIssueAmt
	 *            the voIssueAmt to set
	 */
	public void setVoIssueAmt(Double voIssueAmt) {
		this.voIssueAmt = voIssueAmt;
	}

	/**
	 * @return the voProdCd
	 */
	public String getVoProdCd() {
		return voProdCd;
	}

	/**
	 * @param voProdCd
	 *            the voProdCd to set
	 */
	public void setVoProdCd(String voProdCd) {
		this.voProdCd = voProdCd;
	}

	/**
	 * @return the voProdNm
	 */
	public String getVoProdNm() {
		return voProdNm;
	}

	/**
	 * @param voProdNm
	 *            the voProdNm to set
	 */
	public void setVoProdNm(String voProdNm) {
		this.voProdNm = voProdNm;
	}

	/**
	 * @return the voIssueCnt
	 */
	public Integer getVoIssueCnt() {
		return voIssueCnt;
	}

	/**
	 * @param voIssueCnt
	 *            the voIssueCnt to set
	 */
	public void setVoIssueCnt(Integer voIssueCnt) {
		this.voIssueCnt = voIssueCnt;
	}

	public Integer getTransferCnt() {
		return transferCnt;
	}

	public void setTransferCnt(Integer transferCnt) {
		this.transferCnt = transferCnt;
	}

	/**
	 * @return the voIssueDt
	 */
	public String getVoIssueDt() {
		return voIssueDt;
	}

	/**
	 * @param voIssueDt
	 *            the voIssueDt to set
	 */
	public void setVoIssueDt(String voIssueDt) {
		this.voIssueDt = voIssueDt;
	}

	public String getVoIssueOrgId() {
		return voIssueOrgId;
	}

	public void setVoIssueOrgId(String voIssueOrgId) {
		this.voIssueOrgId = voIssueOrgId;
	}

	/**
	 * @return the voExpiredDt
	 */
	public String getVoExpiredDt() {
		return voExpiredDt;
	}

	/**
	 * @param voExpiredDt
	 *            the voExpiredDt to set
	 */
	public void setVoExpiredDt(String voExpiredDt) {
		this.voExpiredDt = voExpiredDt;
	}

	public String getVoPoDttm() {
		return voPoDttm;
	}

	public void setVoPoDttm(String voPoDttm) {
		this.voPoDttm = voPoDttm;
	}

	/**
	 * @return the poNo
	 */
	public String getPoNo() {
		return poNo;
	}

	/**
	 * @param poNo
	 *            the poNo to set
	 */
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	/**
	 * @return the poDt
	 */
	public String getPoDt() {
		return poDt;
	}

	/**
	 * @param poDt
	 *            the poDt to set
	 */
	public void setPoDt(String poDt) {
		this.poDt = poDt;
	}

	/**
	 * @return the poStat
	 */
	public String getPoStat() {
		return poStat;
	}

	/**
	 * @param poStat
	 *            the poStat to set
	 */
	public void setPoStat(String poStat) {
		this.poStat = poStat;
	}

	/**
	 * @return the poStatNm
	 */
	public String getPoStatNm() {
		return poStatNm;
	}

	/**
	 * @param poStatNm
	 *            the poStatNm to set
	 */
	public void setPoStatNm(String poStatNm) {
		this.poStatNm = poStatNm;
	}

	/**
	 * @return the regrId
	 */
	public String getRegrId() {
		return regrId;
	}

	/**
	 * @param regrId
	 *            the regrId to set
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
	 * @param regDate
	 *            the regDate to set
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
	 * @param chgrId
	 *            the chgrId to set
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
	 * @param chgDate
	 *            the chgDate to set
	 */
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}

	/**
	 * @return the issueChkCnt
	 */
	public Integer getIssueChkCnt() {
		return issueChkCnt;
	}

	/**
	 * @param issueChkCnt
	 *            the issueChkCnt to set
	 */
	public void setIssueChkCnt(Integer issueChkCnt) {
		this.issueChkCnt = issueChkCnt;
	}

	/**
	 * @return the statCdChk
	 */
	public String getStatCdChk() {
		return statCdChk;
	}

	/**
	 * @param statCdChk
	 *            the statCdChk to set
	 */
	public void setStatCdChk(String statCdChk) {
		this.statCdChk = statCdChk;
	}

	/**
	 * @return the lgstCentId
	 */
	public String getLgstCentId() {
		return lgstCentId;
	}

	/**
	 * @param lgstCentId
	 *            the lgstCentId to set
	 */
	public void setLgstCentId(String lgstCentId) {
		this.lgstCentId = lgstCentId;
	}

	/**
	 * @return the lgstCentNm
	 */
	public String getLgstCentNm() {
		return lgstCentNm;
	}

	/**
	 * @param lgstCentNm
	 *            the lgstCentNm to set
	 */
	public void setLgstCentNm(String lgstCentNm) {
		this.lgstCentNm = lgstCentNm;
	}

	/**
	 * @return the dlgdAgreeDt
	 */
	public String getDlgdAgreeDt() {
		return dlgdAgreeDt;
	}

	/**
	 * @param dlgdAgreeDt
	 *            the dlgdAgreeDt to set
	 */
	public void setDlgdAgreeDt(String dlgdAgreeDt) {
		this.dlgdAgreeDt = dlgdAgreeDt;
	}

	/**
	 * @return the dlvZipCd
	 */
	public String getDlvZipCd() {
		return dlvZipCd;
	}

	/**
	 * @param dlvZipCd
	 *            the dlvZipCd to set
	 */
	public void setDlvZipCd(String dlvZipCd) {
		this.dlvZipCd = dlvZipCd;
	}

	/**
	 * @return the dlvBssAddr
	 */
	public String getDlvBssAddr() {
		return dlvBssAddr;
	}

	/**
	 * @param dlvBssAddr
	 *            the dlvBssAddr to set
	 */
	public void setDlvBssAddr(String dlvBssAddr) {
		this.dlvBssAddr = dlvBssAddr;
	}

	/**
	 * @return the dlvDtlAddr
	 */
	public String getDlvDtlAddr() {
		return dlvDtlAddr;
	}

	/**
	 * @param dlvDtlAddr
	 *            the dlvDtlAddr to set
	 */
	public void setDlvDtlAddr(String dlvDtlAddr) {
		this.dlvDtlAddr = dlvDtlAddr;
	}

	/**
	 * @return the lngTyp
	 */
	public String getLngTyp() {
		return lngTyp;
	}

	/**
	 * @param lngTyp
	 *            the lngTyp to set
	 */
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}

	public Double getPoAmt() {
		return poAmt;
	}

	public void setPoAmt(Double poAmt) {
		this.poAmt = poAmt;
	}

	public String getTaxItmId() {
		return taxItmId;
	}

	public void setTaxItmId(String taxItmId) {
		this.taxItmId = taxItmId;
	}

	public String getCalcTp() {
		return calcTp;
	}

	public void setCalcTp(String calcTp) {
		this.calcTp = calcTp;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public String getItemTpCd() {
		return itemTpCd;
	}

	public void setItemTpCd(String itemTpCd) {
		this.itemTpCd = itemTpCd;
	}

	public String getCrtSeqNo() {
		return crtSeqNo;
	}

	public void setCrtSeqNo(String crtSeqNo) {
		this.crtSeqNo = crtSeqNo;
	}

	public String getLgstProcDttm() {
		return lgstProcDttm;
	}

	public void setLgstProcDttm(String lgstProcDttm) {
		this.lgstProcDttm = lgstProcDttm;
	}

	public String getVoBarCd() {
		return voBarCd;
	}

	public void setVoBarCd(String voBarCd) {
		this.voBarCd = voBarCd;
	}

	public String getFromVoBarCd() {
		return fromVoBarCd;
	}

	public void setFromVoBarCd(String fromVoBarCd) {
		this.fromVoBarCd = fromVoBarCd;
	}

	public String getToVoBarCd() {
		return toVoBarCd;
	}

	public void setToVoBarCd(String toVoBarCd) {
		this.toVoBarCd = toVoBarCd;
	}

}
