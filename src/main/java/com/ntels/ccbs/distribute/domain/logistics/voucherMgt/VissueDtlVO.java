package com.ntels.ccbs.distribute.domain.logistics.voucherMgt;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;
import com.ntels.ccbs.system.domain.common.service.Paging;

public class VissueDtlVO extends Paging implements CommonVO, Serializable {

	private static final long serialVersionUID = 1L;

	private String soId;
	private String mncoId;
	private String mncoNm;
	private String itemId;
	private String itemNm;
	private String voSeqNo;
	private String vissueSeqNo;
	private String voBarCd;
	private String voStatCd;
	private String voStatNm;
	private Double voIssueAmt;
	private String voProdCd;
	private String voProdNm;
	private String voIssueDt;
	private String voExpiredDt;
	private String voIssueOrgId;
	private String orgNm;
	private String voPoDttm;
	private String voInDttm;
	private String regrId;
	private Date regDate;
	private String chgrId;
	private Date chgDate;
	private String dlvZipCd;
	private String dlvAddr;
	private String voIssueDt2;
	private String voExpiredDt2;
	private String poConfDt;
	private String dlgdAgreeDt;

	private String lngTyp;

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
	 * @return the voSeqNo
	 */
	public String getVoSeqNo() {
		return voSeqNo;
	}

	/**
	 * @param voSeqNo
	 *            the voSeqNo to set
	 */
	public void setVoSeqNo(String voSeqNo) {
		this.voSeqNo = voSeqNo;
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
	 * @return the voBarCd
	 */
	public String getVoBarCd() {
		return voBarCd;
	}

	/**
	 * @param voBarCd
	 *            the voBarCd to set
	 */
	public void setVoBarCd(String voBarCd) {
		this.voBarCd = voBarCd;
	}

	/**
	 * @return the voStatCd
	 */
	public String getVoStatCd() {
		return voStatCd;
	}

	/**
	 * @param voStatCd
	 *            the voStatCd to set
	 */
	public void setVoStatCd(String voStatCd) {
		this.voStatCd = voStatCd;
	}

	/**
	 * @return the voStatNm
	 */
	public String getVoStatNm() {
		return voStatNm;
	}

	/**
	 * @param voStatNm
	 *            the voStatNm to set
	 */
	public void setVoStatNm(String voStatNm) {
		this.voStatNm = voStatNm;
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

	/**
	 * @return the voIssueOrgId
	 */
	public String getVoIssueOrgId() {
		return voIssueOrgId;
	}

	/**
	 * @param voIssueOrgId
	 *            the voIssueOrgId to set
	 */
	public void setVoIssueOrgId(String voIssueOrgId) {
		this.voIssueOrgId = voIssueOrgId;
	}

	/**
	 * @return the orgNm
	 */
	public String getOrgNm() {
		return orgNm;
	}

	/**
	 * @param orgNm
	 *            the orgNm to set
	 */
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	/**
	 * @return the voPoDttm
	 */
	public String getVoPoDttm() {
		return voPoDttm;
	}

	/**
	 * @param voPoDttm
	 *            the voPoDttm to set
	 */
	public void setVoPoDttm(String voPoDttm) {
		this.voPoDttm = voPoDttm;
	}

	/**
	 * @return the voInDttm
	 */
	public String getVoInDttm() {
		return voInDttm;
	}

	/**
	 * @param voInDttm
	 *            the voInDttm to set
	 */
	public void setVoInDttm(String voInDttm) {
		this.voInDttm = voInDttm;
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
	 * @return the dlvAddr
	 */
	public String getDlvAddr() {
		return dlvAddr;
	}

	/**
	 * @param dlvAddr
	 *            the dlvAddr to set
	 */
	public void setDlvAddr(String dlvAddr) {
		this.dlvAddr = dlvAddr;
	}

	/**
	 * @return the voIssueDt2
	 */
	public String getVoIssueDt2() {
		return voIssueDt2;
	}

	/**
	 * @param voIssueDt2
	 *            the voIssueDt2 to set
	 */
	public void setVoIssueDt2(String voIssueDt2) {
		this.voIssueDt2 = voIssueDt2;
	}

	/**
	 * @return the voExpiredDt2
	 */
	public String getVoExpiredDt2() {
		return voExpiredDt2;
	}

	/**
	 * @param voExpiredDt2
	 *            the voExpiredDt2 to set
	 */
	public void setVoExpiredDt2(String voExpiredDt2) {
		this.voExpiredDt2 = voExpiredDt2;
	}

	/**
	 * @return the poConfDt
	 */
	public String getPoConfDt() {
		return poConfDt;
	}

	/**
	 * @param poConfDt
	 *            the poConfDt to set
	 */
	public void setPoConfDt(String poConfDt) {
		this.poConfDt = poConfDt;
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

}
