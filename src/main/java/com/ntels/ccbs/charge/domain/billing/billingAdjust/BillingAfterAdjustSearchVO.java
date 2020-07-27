package com.ntels.ccbs.charge.domain.billing.billingAdjust;

import java.io.Serializable;
import java.util.Date;
import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class BillingAfterAdjustSearchVO implements Serializable,CommonVO{

	private String soId; /* 사업구분 */
	private String regrId; /* 등록자ID */
	private String regrNm; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자ID */
	private String chgrNm; /* 수정자명 */
	private Date chgDate; /* 변경일시 */
	private String commonCd;
	private String commonCdNm;
	
	private String applDttmDt;	/* 신청 일자 */
	private String pymAcntId; /* 납부계정ID */
	private String pymAcntNm; /* 납부계정명 */
	private String adjResnNm; /* 조정사유 */
	private String adjNo; /* 신청번호 */
	private String adjPrvBillAmt; /* 최초조정 전 청구금액 */
	private String adjAmt; /* 조정누적금액 */
	private String billAmt; /* 청구금액 */
	private String adjPrvBillAmtA; /* 조정 전 청구금액 */
	private String adjApplAmt; /* 신청금액*/
	private String adjAmtA; /* 조정금액 */
	private String dcsnProcStat; /* 진행상태 */
	private String dcsnProcStatNm; /*진행상태명*/
	private String rcpPsnNm; /* 신청자명 */
	private String billDt; /* 청구일자 */
	private String billAplyDt; /* 청구반영일자 */
	private String adjApplConts; /* 신청사유 */
	private String chgrIdNm ;
	private String chgDttm; /* 수정일시 */
	
	private String condSoId;
	private String condStDt;
	private String condEdDt;
	private String condBillYymm;
	private String condPymAcntId;
	private String condRcptPsnId;
	private String condDcsnProcStat;
	
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
	public String getApplDttmDt() {
		return applDttmDt;
	}
	public void setApplDttmDt(String applDttmDt) {
		this.applDttmDt = applDttmDt;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getPymAcntNm() {
		return pymAcntNm;
	}
	public void setPymAcntNm(String pymAcntNm) {
		this.pymAcntNm = pymAcntNm;
	}
	public String getAdjResnNm() {
		return adjResnNm;
	}
	public void setAdjResnNm(String adjResnNm) {
		this.adjResnNm = adjResnNm;
	}
	public String getAdjNo() {
		return adjNo;
	}
	public void setAdjNo(String adjNo) {
		this.adjNo = adjNo;
	}
	public String getAdjPrvBillAmt() {
		return adjPrvBillAmt;
	}
	public void setAdjPrvBillAmt(String adjPrvBillAmt) {
		this.adjPrvBillAmt = adjPrvBillAmt;
	}
	public String getAdjAmt() {
		return adjAmt;
	}
	public void setAdjAmt(String adjAmt) {
		this.adjAmt = adjAmt;
	}
	public String getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}
	public String getAdjPrvBillAmtA() {
		return adjPrvBillAmtA;
	}
	public void setAdjPrvBillAmtA(String adjPrvBillAmtA) {
		this.adjPrvBillAmtA = adjPrvBillAmtA;
	}
	public String getAdjApplAmt() {
		return adjApplAmt;
	}
	public void setAdjApplAmt(String adjApplAmt) {
		this.adjApplAmt = adjApplAmt;
	}
	public String getAdjAmtA() {
		return adjAmtA;
	}
	public void setAdjAmtA(String adjAmtA) {
		this.adjAmtA = adjAmtA;
	}
	public String getDcsnProcStat() {
		return dcsnProcStat;
	}
	public void setDcsnProcStat(String dcsnProcStat) {
		this.dcsnProcStat = dcsnProcStat;
	}
	public String getRcpPsnNm() {
		return rcpPsnNm;
	}
	public void setRcpPsnNm(String rcpPsnNm) {
		this.rcpPsnNm = rcpPsnNm;
	}
	public String getBillDt() {
		return billDt;
	}
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}
	public String getBillAplyDt() {
		return billAplyDt;
	}
	public void setBillAplyDt(String billAplyDt) {
		this.billAplyDt = billAplyDt;
	}
	public String getAdjApplConts() {
		return adjApplConts;
	}
	public void setAdjApplConts(String adjApplConts) {
		this.adjApplConts = adjApplConts;
	}
	public String getChgrIdNm() {
		return chgrIdNm;
	}
	public void setChgrIdNm(String chgrIdNm) {
		this.chgrIdNm = chgrIdNm;
	}

	public String getCondSoId() {
		return condSoId;
	}
	public void setCondSoId(String condSoId) {
		this.condSoId = condSoId;
	}
	public String getCondStDt() {
		return condStDt;
	}
	public void setCondStDt(String condStDt) {
		this.condStDt = condStDt;
	}
	public String getCondEdDt() {
		return condEdDt;
	}
	public void setCondEdDt(String condEdDt) {
		this.condEdDt = condEdDt;
	}
	public String getCondBillYymm() {
		return condBillYymm;
	}
	public void setCondBillYymm(String condBillYymm) {
		this.condBillYymm = condBillYymm;
	}
	public String getCondPymAcntId() {
		return condPymAcntId;
	}
	public void setCondPymAcntId(String condPymAcntId) {
		this.condPymAcntId = condPymAcntId;
	}
	public String getCondRcptPsnId() {
		return condRcptPsnId;
	}
	public void setCondRcptPsnId(String condRcptPsnId) {
		this.condRcptPsnId = condRcptPsnId;
	}
	public String getCondDcsnProcStat() {
		return condDcsnProcStat;
	}
	public void setCondDcsnProcStat(String condDcsnProcStat) {
		this.condDcsnProcStat = condDcsnProcStat;
	}
	public String getChgDttm() {
		return chgDttm;
	}
	public void setChgDttm(String chgDttm) {
		this.chgDttm = chgDttm;
	}
	public String getDcsnProcStatNm() {
		return dcsnProcStatNm;
	}
	public void setDcsnProcStatNm(String dcsnProcStatNm) {
		this.dcsnProcStatNm = dcsnProcStatNm;
	}
	
}
