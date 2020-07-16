package com.ntels.ccbs.distribute.domain.distributor.distributorMgt;

import java.util.Date;
import java.util.List;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DistributionInfoVO")
public class DistributionInfoVO extends PagingValue {

	private String soId;			//사업ID
	private String orgId;			//조직ID
	private String busiTpCd;		//사업자구분코드
	private String borNo;			//사업자등록번호
	private String corpRegNo;		//법인등록번호
	private String busiTp;			//업종
	private String busiCndt;		//업태
	private String repNm;			//대표자명
	private String repRno;			//대표자주민번호
	private String hmpg;			//홈페이지
	private String telNo;			//전화번호
	private String faxNo;			//팩스번호
	private String eml;				//이메일
	private String zipCd;			//우편번호
	private String addr1;			//주소1
	private String addr2;			//주소2
	private String dpstNm;			//예금주명
	private String bnkCd;			//은행코드
	private String acntNo;			//계좌번호
	private String onlnTpCd;		//온라인구분코드
	private String termResn;		//해지사유
	private String sttlPsnInchrg;	//정산담당자ID
	private String sttlInchrgTel;	//정산담당자전화번호
	private String sttlInchrgCp;	//정산담당자휴대폰번호
	private String sttlInchrgEml;	//정산담당자이메일
	private String eqtSchnAcntNo;	//단말기가상계좌
	private String eqtBnkCd;		//단말기가상계좌은행코드
	private String billSchnAcntNo;	//공과금가상계좌
	private String billBnkCd;		//공과금가상계좌은행코드
	private String psnInchrgId;		//담당자ID
	private String mngrNm;			//실경영자명
	private String mngrRno;			//실경영자주민번호
	private String regrId;			//등록자
	private Date regDate;			//등록일시
	private String chgrId;			//수정자
	private Date chgDate;			//수정일시
	
	private String soNm;			//사업명
	private String orgNm;			//조직명
	private String busiTpNm;		//사업자구분코드명
	private String privTpCd;		//자사구분코드
	private String privTpNm;		//자사구분코드명
	private String bnkNm;			//은행코드명
	private String appyStrtDt;		//적용시작일
	private String appyEndDt;		//적용종료일
	private String onlyTpNm;		//온라인구분코드명
	private String orgStatCd;		//조직상태코드
	private String orgStatNm;		//조직상태코드명
	private String sttlPsnInchrgNm;	//정산담당자명
	private String eqtBnkNm;		//단말기가상계좌은행코드명
	private String billBnkNm;		//공과금가상계좌은행코드명
	private String psnInchrgNm;		//담당자명
	
	private List<String> orgIdList;
	
	private String lngTyp;
	private String sidx;
	private String sord;
	
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
	public String getBusiTpCd() {
		return busiTpCd;
	}
	public void setBusiTpCd(String busiTpCd) {
		this.busiTpCd = busiTpCd;
	}
	public String getBorNo() {
		return borNo;
	}
	public void setBorNo(String borNo) {
		this.borNo = borNo;
	}
	public String getCorpRegNo() {
		return corpRegNo;
	}
	public void setCorpRegNo(String corpRegNo) {
		this.corpRegNo = corpRegNo;
	}
	public String getBusiTp() {
		return busiTp;
	}
	public void setBusiTp(String busiTp) {
		this.busiTp = busiTp;
	}
	public String getBusiCndt() {
		return busiCndt;
	}
	public void setBusiCndt(String busiCndt) {
		this.busiCndt = busiCndt;
	}
	public String getRepNm() {
		return repNm;
	}
	public void setRepNm(String repNm) {
		this.repNm = repNm;
	}
	public String getRepRno() {
		return repRno;
	}
	public void setRepRno(String repRno) {
		this.repRno = repRno;
	}
	public String getHmpg() {
		return hmpg;
	}
	public void setHmpg(String hmpg) {
		this.hmpg = hmpg;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getEml() {
		return eml;
	}
	public void setEml(String eml) {
		this.eml = eml;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getDpstNm() {
		return dpstNm;
	}
	public void setDpstNm(String dpstNm) {
		this.dpstNm = dpstNm;
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
	public String getOnlnTpCd() {
		return onlnTpCd;
	}
	public void setOnlnTpCd(String onlnTpCd) {
		this.onlnTpCd = onlnTpCd;
	}
	public String getTermResn() {
		return termResn;
	}
	public void setTermResn(String termResn) {
		this.termResn = termResn;
	}
	public String getSttlPsnInchrg() {
		return sttlPsnInchrg;
	}
	public void setSttlPsnInchrg(String sttlPsnInchrg) {
		this.sttlPsnInchrg = sttlPsnInchrg;
	}
	public String getSttlInchrgTel() {
		return sttlInchrgTel;
	}
	public void setSttlInchrgTel(String sttlInchrgTel) {
		this.sttlInchrgTel = sttlInchrgTel;
	}
	public String getSttlInchrgCp() {
		return sttlInchrgCp;
	}
	public void setSttlInchrgCp(String sttlInchrgCp) {
		this.sttlInchrgCp = sttlInchrgCp;
	}
	public String getSttlInchrgEml() {
		return sttlInchrgEml;
	}
	public void setSttlInchrgEml(String sttlInchrgEml) {
		this.sttlInchrgEml = sttlInchrgEml;
	}
	public String getEqtSchnAcntNo() {
		return eqtSchnAcntNo;
	}
	public void setEqtSchnAcntNo(String eqtSchnAcntNo) {
		this.eqtSchnAcntNo = eqtSchnAcntNo;
	}
	public String getEqtBnkCd() {
		return eqtBnkCd;
	}
	public void setEqtBnkCd(String eqtBnkCd) {
		this.eqtBnkCd = eqtBnkCd;
	}
	public String getBillSchnAcntNo() {
		return billSchnAcntNo;
	}
	public void setBillSchnAcntNo(String billSchnAcntNo) {
		this.billSchnAcntNo = billSchnAcntNo;
	}
	public String getBillBnkCd() {
		return billBnkCd;
	}
	public void setBillBnkCd(String billBnkCd) {
		this.billBnkCd = billBnkCd;
	}
	public String getPsnInchrgId() {
		return psnInchrgId;
	}
	public void setPsnInchrgId(String psnInchrgId) {
		this.psnInchrgId = psnInchrgId;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	public String getMngrRno() {
		return mngrRno;
	}
	public void setMngrRno(String mngrRno) {
		this.mngrRno = mngrRno;
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
	public String getBusiTpNm() {
		return busiTpNm;
	}
	public void setBusiTpNm(String busiTpNm) {
		this.busiTpNm = busiTpNm;
	}
	public String getPrivTpCd() {
		return privTpCd;
	}
	public void setPrivTpCd(String privTpCd) {
		this.privTpCd = privTpCd;
	}
	public String getPrivTpNm() {
		return privTpNm;
	}
	public void setPrivTpNm(String privTpNm) {
		this.privTpNm = privTpNm;
	}
	public String getBnkNm() {
		return bnkNm;
	}
	public void setBnkNm(String bnkNm) {
		this.bnkNm = bnkNm;
	}
	public String getAppyStrtDt() {
		return appyStrtDt;
	}
	public void setAppyStrtDt(String appyStrtDt) {
		this.appyStrtDt = appyStrtDt;
	}
	public String getAppyEndDt() {
		return appyEndDt;
	}
	public void setAppyEndDt(String appyEndDt) {
		this.appyEndDt = appyEndDt;
	}
	public String getOnlyTpNm() {
		return onlyTpNm;
	}
	public void setOnlyTpNm(String onlyTpNm) {
		this.onlyTpNm = onlyTpNm;
	}
	public String getOrgStatCd() {
		return orgStatCd;
	}
	public void setOrgStatCd(String orgStatCd) {
		this.orgStatCd = orgStatCd;
	}
	public String getOrgStatNm() {
		return orgStatNm;
	}
	public void setOrgStatNm(String orgStatNm) {
		this.orgStatNm = orgStatNm;
	}
	public String getSttlPsnInchrgNm() {
		return sttlPsnInchrgNm;
	}
	public void setSttlPsnInchrgNm(String sttlPsnInchrgNm) {
		this.sttlPsnInchrgNm = sttlPsnInchrgNm;
	}
	public String getEqtBnkNm() {
		return eqtBnkNm;
	}
	public void setEqtBnkNm(String eqtBnkNm) {
		this.eqtBnkNm = eqtBnkNm;
	}
	public String getBillBnkNm() {
		return billBnkNm;
	}
	public void setBillBnkNm(String billBnkNm) {
		this.billBnkNm = billBnkNm;
	}
	public String getPsnInchrgNm() {
		return psnInchrgNm;
	}
	public void setPsnInchrgNm(String psnInchrgNm) {
		this.psnInchrgNm = psnInchrgNm;
	}
	public List<String> getOrgIdList() {
		return orgIdList;
	}
	public void setOrgIdList(List<String> orgIdList) {
		this.orgIdList = orgIdList;
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
