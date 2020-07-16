package com.ntels.ccbs.distribute.domain.distributor.distributorMgt;

import java.util.Date;
import java.util.List;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("CollateralVO")
public class CollateralVO extends PagingValue {

	private String soId;			//사업ID
	private String orgId;			//조직ID
	private String collOrg;			//담보순번
	private String collKndCd;		//담보종류코드
	private String locLd;			//소재지
	private String securKndCd;		//저당권종류코드
	private String securTpCd;		//저당권구분코드
	private String ara;				//면적
	private String ownrNm;			//소유자명
	private String ownrRelCd;		//소유자관계코드
	private String stpDt;			//설정일
	private String stpPri;			//설정순위
	private String apprYn;			//감정유무
	private String apprOffc;		//감정사무소
	private String apprAmt;			//감정금액
	private String apprDt;			//감정일
	private String asstAmt;			//순자산가액
	private String stpAmt;			//설정금액
	private String loanAmt;			//여신금액
	private String loanArpiAmt;		//공과금여신금액
	private String termDt;			//해지일
	private String gndStpYn;		//지상권설정여부
	private String rmrk;			//특기사항
	private String preOrdNm1;		//선순위자명1
	private String preOrdAmt1;		//선순위금액1
	private String preOrdNm2;		//선순위자명2
	private String preOrdAmt2;		//선순위금액2
	private String preOrdNm3;		//선순위자명3
	private String preOrdAmt3;		//선순위금액3
	private String bldMst;			//건물주
	private String regNo;			//등기번호
	private String leasPsn;			//전세권자
	private String leasStpAmt;		//전세설정액
	private String insuCtrtrNm;		//보험계약자명
	private String stckNo;			//증권번호
	private String assrKndCd;		//보증보험종류코드
	private String assrCt;			//보증내용
	private String assrStrtDt;		//보증개시일
	private String assrEndDt;		//보증만료일
	private String assrCorpCd;		//보증회사코드
	private String assrBrNm;		//보증보험지점명
	private String assrAgncyNm;		//보증보험대리점명
	private String collOpt1;		//구채무특약사항적시
	private String collOpt2;		//추가위험부담약관의무적시
	private String bnkCd;			//은행코드
	private String acntNo;			//계좌번호
	private String dpstNm;			//예금주명
	private String dpstRel;			//예금주와관계
	private String dpstRno;			//예금주주민등록번호
	private String regrId;			//등록자
	private Date regDate;			//등록일시
	private String chgrId;			//수정자
	private Date chgDate;			//수정일시
	
	private String soNm;			//사업명
	private String orgNm;			//조직명
	private String collKndNm;		//담보종류코드명
	private String repNm;			//대표자명
	private String assrKndNm;		
	private String assrCorpNm;
	
	private String loanKndCd;		//여신종류코드
	private String normLoanAmt;		//일반여신금액
	private String spclLoanAmt;		//특별여신금액
	private String loanUseAmt;		//여신사용금액
	private String loanAvlAmt;		//여신가능금액
	
	private String loanOrd;			//여신순번
	private String loanTpCd;		//여신구분코드
	private String loanStrtDt;		//여신일자From
	private String loanEndDt;		//여신일자To
	private String loanStpAmt;		//여신설정금액
	private String loanApprrId;		//여신결정자ID
	private String loanApprrNm;		//여신결정자명
	private String loanStpResn;		//여신결정사유
	
	private String loanGvFlg;		//여신부여여부
	private String sysdate;			//
	
	private List<String> orgIdList;
	
	private String lngTyp;
	private String sidx;
	private String sord;
	
	public String getSysdate() {
		return sysdate;
	}
	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}
	public String getLoanOrd() {
		return loanOrd;
	}
	public void setLoanOrd(String loanOrd) {
		this.loanOrd = loanOrd;
	}
	public String getLoanTpCd() {
		return loanTpCd;
	}
	public void setLoanTpCd(String loanTpCd) {
		this.loanTpCd = loanTpCd;
	}
	public String getLoanStrtDt() {
		return loanStrtDt;
	}
	public void setLoanStrtDt(String loanStrtDt) {
		this.loanStrtDt = loanStrtDt;
	}
	public String getLoanEndDt() {
		return loanEndDt;
	}
	public void setLoanEndDt(String loanEndDt) {
		this.loanEndDt = loanEndDt;
	}
	public String getLoanStpAmt() {
		return loanStpAmt;
	}
	public void setLoanStpAmt(String loanStpAmt) {
		this.loanStpAmt = loanStpAmt;
	}
	public String getLoanApprrId() {
		return loanApprrId;
	}
	public void setLoanApprrId(String loanApprrId) {
		this.loanApprrId = loanApprrId;
	}
	public String getLoanApprrNm() {
		return loanApprrNm;
	}
	public void setLoanApprrNm(String loanApprrNm) {
		this.loanApprrNm = loanApprrNm;
	}
	public String getLoanStpResn() {
		return loanStpResn;
	}
	public void setLoanStpResn(String loanStpResn) {
		this.loanStpResn = loanStpResn;
	}
	public String getLoanKndCd() {
		return loanKndCd;
	}
	public void setLoanKndCd(String loanKndCd) {
		this.loanKndCd = loanKndCd;
	}
	public String getNormLoanAmt() {
		return normLoanAmt;
	}
	public void setNormLoanAmt(String normLoanAmt) {
		this.normLoanAmt = normLoanAmt;
	}
	public String getSpclLoanAmt() {
		return spclLoanAmt;
	}
	public void setSpclLoanAmt(String spclLoanAmt) {
		this.spclLoanAmt = spclLoanAmt;
	}
	public String getLoanUseAmt() {
		return loanUseAmt;
	}
	public void setLoanUseAmt(String loanUseAmt) {
		this.loanUseAmt = loanUseAmt;
	}
	public String getLoanAvlAmt() {
		return loanAvlAmt;
	}
	public void setLoanAvlAmt(String loanAvlAmt) {
		this.loanAvlAmt = loanAvlAmt;
	}
	public String getAssrCorpNm() {
		return assrCorpNm;
	}
	public void setAssrCorpNm(String assrCorpNm) {
		this.assrCorpNm = assrCorpNm;
	}
	public String getAssrKndNm() {
		return assrKndNm;
	}
	public void setAssrKndNm(String assrKndNm) {
		this.assrKndNm = assrKndNm;
	}
	public String getLoanGvFlg() {
		return loanGvFlg;
	}
	public void setLoanGvFlg(String loanGvFlg) {
		this.loanGvFlg = loanGvFlg;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getCollKndNm() {
		return collKndNm;
	}
	public void setCollKndNm(String collKndNm) {
		this.collKndNm = collKndNm;
	}
	public String getRepNm() {
		return repNm;
	}
	public void setRepNm(String repNm) {
		this.repNm = repNm;
	}
	public List<String> getOrgIdList() {
		return orgIdList;
	}
	public void setOrgIdList(List<String> orgIdList) {
		this.orgIdList = orgIdList;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getCollOrg() {
		return collOrg;
	}
	public void setCollOrg(String collOrg) {
		this.collOrg = collOrg;
	}
	public String getCollKndCd() {
		return collKndCd;
	}
	public void setCollKndCd(String collKndCd) {
		this.collKndCd = collKndCd;
	}
	public String getLocLd() {
		return locLd;
	}
	public void setLocLd(String locLd) {
		this.locLd = locLd;
	}
	public String getSecurKndCd() {
		return securKndCd;
	}
	public void setSecurKndCd(String securKndCd) {
		this.securKndCd = securKndCd;
	}
	public String getSecurTpCd() {
		return securTpCd;
	}
	public void setSecurTpCd(String securTpCd) {
		this.securTpCd = securTpCd;
	}
	public String getAra() {
		return ara;
	}
	public void setAra(String ara) {
		this.ara = ara;
	}
	public String getOwnrNm() {
		return ownrNm;
	}
	public void setOwnrNm(String ownrNm) {
		this.ownrNm = ownrNm;
	}
	public String getOwnrRelCd() {
		return ownrRelCd;
	}
	public void setOwnrRelCd(String ownrRelCd) {
		this.ownrRelCd = ownrRelCd;
	}
	public String getStpDt() {
		return stpDt;
	}
	public void setStpDt(String stpDt) {
		this.stpDt = stpDt;
	}
	public String getStpPri() {
		return stpPri;
	}
	public void setStpPri(String stpPri) {
		this.stpPri = stpPri;
	}
	public String getApprYn() {
		return apprYn;
	}
	public void setApprYn(String apprYn) {
		this.apprYn = apprYn;
	}
	public String getApprOffc() {
		return apprOffc;
	}
	public void setApprOffc(String apprOffc) {
		this.apprOffc = apprOffc;
	}
	public String getApprAmt() {
		return apprAmt;
	}
	public void setApprAmt(String apprAmt) {
		this.apprAmt = apprAmt;
	}
	public String getApprDt() {
		return apprDt;
	}
	public void setApprDt(String apprDt) {
		this.apprDt = apprDt;
	}
	public String getAsstAmt() {
		return asstAmt;
	}
	public void setAsstAmt(String asstAmt) {
		this.asstAmt = asstAmt;
	}
	public String getStpAmt() {
		return stpAmt;
	}
	public void setStpAmt(String stpAmt) {
		this.stpAmt = stpAmt;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanArpiAmt() {
		return loanArpiAmt;
	}
	public void setLoanArpiAmt(String loanArpiAmt) {
		this.loanArpiAmt = loanArpiAmt;
	}
	public String getTermDt() {
		return termDt;
	}
	public void setTermDt(String termDt) {
		this.termDt = termDt;
	}
	public String getGndStpYn() {
		return gndStpYn;
	}
	public void setGndStpYn(String gndStpYn) {
		this.gndStpYn = gndStpYn;
	}
	public String getRmrk() {
		return rmrk;
	}
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}
	public String getPreOrdNm1() {
		return preOrdNm1;
	}
	public void setPreOrdNm1(String preOrdNm1) {
		this.preOrdNm1 = preOrdNm1;
	}
	public String getPreOrdAmt1() {
		return preOrdAmt1;
	}
	public void setPreOrdAmt1(String preOrdAmt1) {
		this.preOrdAmt1 = preOrdAmt1;
	}
	public String getPreOrdNm2() {
		return preOrdNm2;
	}
	public void setPreOrdNm2(String preOrdNm2) {
		this.preOrdNm2 = preOrdNm2;
	}
	public String getPreOrdAmt2() {
		return preOrdAmt2;
	}
	public void setPreOrdAmt2(String preOrdAmt2) {
		this.preOrdAmt2 = preOrdAmt2;
	}
	public String getPreOrdNm3() {
		return preOrdNm3;
	}
	public void setPreOrdNm3(String preOrdNm3) {
		this.preOrdNm3 = preOrdNm3;
	}
	public String getPreOrdAmt3() {
		return preOrdAmt3;
	}
	public void setPreOrdAmt3(String preOrdAmt3) {
		this.preOrdAmt3 = preOrdAmt3;
	}
	public String getBldMst() {
		return bldMst;
	}
	public void setBldMst(String bldMst) {
		this.bldMst = bldMst;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getLeasPsn() {
		return leasPsn;
	}
	public void setLeasPsn(String leasPsn) {
		this.leasPsn = leasPsn;
	}
	public String getLeasStpAmt() {
		return leasStpAmt;
	}
	public void setLeasStpAmt(String leasStpAmt) {
		this.leasStpAmt = leasStpAmt;
	}
	public String getInsuCtrtrNm() {
		return insuCtrtrNm;
	}
	public void setInsuCtrtrNm(String insuCtrtrNm) {
		this.insuCtrtrNm = insuCtrtrNm;
	}
	public String getStckNo() {
		return stckNo;
	}
	public void setStckNo(String stckNo) {
		this.stckNo = stckNo;
	}
	public String getAssrKndCd() {
		return assrKndCd;
	}
	public void setAssrKndCd(String assrKndCd) {
		this.assrKndCd = assrKndCd;
	}
	public String getAssrCt() {
		return assrCt;
	}
	public void setAssrCt(String assrCt) {
		this.assrCt = assrCt;
	}
	public String getAssrStrtDt() {
		return assrStrtDt;
	}
	public void setAssrStrtDt(String assrStrtDt) {
		this.assrStrtDt = assrStrtDt;
	}
	public String getAssrEndDt() {
		return assrEndDt;
	}
	public void setAssrEndDt(String assrEndDt) {
		this.assrEndDt = assrEndDt;
	}
	public String getAssrCorpCd() {
		return assrCorpCd;
	}
	public void setAssrCorpCd(String assrCorpCd) {
		this.assrCorpCd = assrCorpCd;
	}
	public String getAssrBrNm() {
		return assrBrNm;
	}
	public void setAssrBrNm(String assrBrNm) {
		this.assrBrNm = assrBrNm;
	}
	public String getAssrAgncyNm() {
		return assrAgncyNm;
	}
	public void setAssrAgncyNm(String assrAgncyNm) {
		this.assrAgncyNm = assrAgncyNm;
	}
	public String getCollOpt1() {
		return collOpt1;
	}
	public void setCollOpt1(String collOpt1) {
		this.collOpt1 = collOpt1;
	}
	public String getCollOpt2() {
		return collOpt2;
	}
	public void setCollOpt2(String collOpt2) {
		this.collOpt2 = collOpt2;
	}
	public String getBnkCd() {
		return bnkCd;
	}
	public void setBnkCd(String bnkCd) {
		this.bnkCd = bnkCd;
	}
	public String getAcntNo() {
		return acntNo;
	}
	public void setAcntNo(String acntNo) {
		this.acntNo = acntNo;
	}
	public String getDpstNm() {
		return dpstNm;
	}
	public void setDpstNm(String dpstNm) {
		this.dpstNm = dpstNm;
	}
	public String getDpstRel() {
		return dpstRel;
	}
	public void setDpstRel(String dpstRel) {
		this.dpstRel = dpstRel;
	}
	public String getDpstRno() {
		return dpstRno;
	}
	public void setDpstRno(String dpstRno) {
		this.dpstRno = dpstRno;
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
