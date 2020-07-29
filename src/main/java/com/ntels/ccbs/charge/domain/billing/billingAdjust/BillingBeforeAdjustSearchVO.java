package com.ntels.ccbs.charge.domain.billing.billingAdjust;

import java.io.Serializable;
import java.util.Date;
import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class BillingBeforeAdjustSearchVO implements Serializable,CommonVO{

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
	private String hopeAplyYymm; /*희망 적용 년월*/
	private String pymAcntId; /* 납부계정ID */
	private String pymAcntNm; /* 납부계정명 */
	private String svcTelNo; /*서비스번호*/
	private String adjPt;	/*조정구분*/
	private String adjResnNm; /* 조정사유 */
	private String adjPrvBillAmt; /*조정전청구금액*/
	private String adjAmt; /*조정금액*/
	private String adjApplAmt; /* 신청금액*/
	private String billAplyDt; /* 청구반영일자 */
	private String dcsnProcStat; /* 진행상태 */
	private String dcsnProcStatNm; /*진행상태명*/
	private String rcptPsnNm; /* 신청자명 */
	private String apprrId; /*결재자ID*/
	private String apprrIdNm; /* 결재자명*/
	private String billYymm;	/* 청구년월 */
	private String billDt; /* 청구일자 */
	private String adjNo; /* 신청번호 */
	private String adjApplConts; /* 신청사유 */
	private String chgrIdNm ;
	private String chgDttm; /* 수정일시 */
	private String billAmt; /*청구금액*/
	private String billSeqNo;
	
	private String ctrtId;
	public String getAdjPtNm() {
		return adjPtNm;
	}
	public void setAdjPtNm(String adjPtNm) {
		this.adjPtNm = adjPtNm;
	}
	private String adjPtNm;
	
	private String condSoId;
	private String applDttmFrom;
	private String applDttmTo;
	private String condApplDttmTyp;
	private String condPymAcntId;
	private String condRcptPsnId;
	private String condDcsnProcStat;
	private String condApprrId;
	
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
	public String getRcptPsnNm() {
		return rcptPsnNm;
	}
	public void setRcptPsnNm(String rcptPsnNm) {
		this.rcptPsnNm = rcptPsnNm;
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
	public String getHopeAplyYymm() {
		return hopeAplyYymm;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public String getPymAcntNm() {
		return pymAcntNm;
	}
	public String getSvcTelNo() {
		return svcTelNo;
	}
	public String getAdjPt() {
		return adjPt;
	}
	public String getAdjResnNm() {
		return adjResnNm;
	}
	public String getAdjPrvBillAmt() {
		return adjPrvBillAmt;
	}
	public String getAdjAmt() {
		return adjAmt;
	}
	public String getAdjApplAmt() {
		return adjApplAmt;
	}
	public String getBillAplyDt() {
		return billAplyDt;
	}
	public String getCondDcsnProcStat() {
		return condDcsnProcStat;
	}
	public void setCondDcsnProcStat(String condDcsnProcStat) {
		this.condDcsnProcStat = condDcsnProcStat;
	}
	public String getDcsnProcStat() {
		return dcsnProcStat;
	}
	public String getDcsnProcStatNm() {
		return dcsnProcStatNm;
	}

	public String getApprrId() {
		return apprrId;
	}
	public String getApprrIdNm() {
		return apprrIdNm;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public String getBillDt() {
		return billDt;
	}
	public String getAdjNo() {
		return adjNo;
	}
	public String getAdjApplConts() {
		return adjApplConts;
	}
	public String getChgrIdNm() {
		return chgrIdNm;
	}
	public String getChgDttm() {
		return chgDttm;
	}
	public void setApplDttmDt(String applDttmDt) {
		this.applDttmDt = applDttmDt;
	}
	public void setHopeAplyYymm(String hopeAplyYymm) {
		this.hopeAplyYymm = hopeAplyYymm;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public void setPymAcntNm(String pymAcntNm) {
		this.pymAcntNm = pymAcntNm;
	}
	public void setSvcTelNo(String svcTelNo) {
		this.svcTelNo = svcTelNo;
	}
	public void setAdjPt(String adjPt) {
		this.adjPt = adjPt;
	}
	public void setAdjResnNm(String adjResnNm) {
		this.adjResnNm = adjResnNm;
	}
	public void setAdjPrvBillAmt(String adjPrvBillAmt) {
		this.adjPrvBillAmt = adjPrvBillAmt;
	}
	public void setAdjAmt(String adjAmt) {
		this.adjAmt = adjAmt;
	}
	public void setAdjApplAmt(String adjApplAmt) {
		this.adjApplAmt = adjApplAmt;
	}
	public void setBillAplyDt(String billAplyDt) {
		this.billAplyDt = billAplyDt;
	}
	public void setDcsnProcStat(String dcsnProcStat) {
		this.dcsnProcStat = dcsnProcStat;
	}
	public void setDcsnProcStatNm(String dcsnProcStatNm) {
		this.dcsnProcStatNm = dcsnProcStatNm;
	}

	public void setApprrId(String apprrId) {
		this.apprrId = apprrId;
	}
	public void setApprrIdNm(String apprrIdNm) {
		this.apprrIdNm = apprrIdNm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}
	public void setAdjNo(String adjNo) {
		this.adjNo = adjNo;
	}
	public void setAdjApplConts(String adjApplConts) {
		this.adjApplConts = adjApplConts;
	}
	public void setChgrIdNm(String chgrIdNm) {
		this.chgrIdNm = chgrIdNm;
	}
	public void setChgDttm(String chgDttm) {
		this.chgDttm = chgDttm;
	}
	public String getCondSoId() {
		return condSoId;
	}
	public String getApplDttmFrom() {
		return applDttmFrom;
	}
	public String getApplDttmTo() {
		return applDttmTo;
	}
	public void setCondSoId(String condSoId) {
		this.condSoId = condSoId;
	}
	public void setApplDttmFrom(String applDttmFrom) {
		this.applDttmFrom = applDttmFrom;
	}
	public void setApplDttmTo(String applDttmTo) {
		this.applDttmTo = applDttmTo;
	}
	public String getCondApplDttmTyp() {
		return condApplDttmTyp;
	}
	public void setCondApplDttmTyp(String condApplDttmTyp) {
		this.condApplDttmTyp = condApplDttmTyp;
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
	public String getCondApprrId() {
		return condApprrId;
	}
	public void setCondApprrId(String condApprrId) {
		this.condApprrId = condApprrId;
	}

	public String getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getBillSeqNo() {
		return billSeqNo;
	}
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}
	
	
}
