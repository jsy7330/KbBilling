package com.ntels.ccbs.distribute.domain.logistics.referenceInfo;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ManufacturerInfoVO")
public class ManufacturerInfoVO extends PagingValue {

	private String soId;				//사업ID
	private String mncoId;				//제조사ID
	private String mncoNm;				//제조사명
	private String borNo;				//사업자번호
	private String repNm;				//대표자명
	private String repTelNo;			//대표전화번호
	private String repFaxNo;			//대표팩스번호
	private String busiCndt;			//업태
	private String busiTp;				//업종
	private String poInchrgNm;			//발주담당자명
	private String poInchrgTelNo;		//발주담당자전화번호
	private String poInchrgFaxNo;		//발주담당자팩스번호
	private String poInchrgEml;			//발주담당자이메일
	private String rtngdInchrgNm;		//반품담당자명
	private String rtngdInchrgTelNo;	//반품담당자전화번호
	private String rtngdInchrgFaxNo;	//반품담당자팩스번호
	private String rtngdInchrgEml;		//반품담당자이메일
	private String zipCd;				//우편번호
	private String bssAddr;				//기본주소
	private String dtlAddr;				//상세주소
	private String ctrtInchrgNm;		//계약담당자명
	private String ctrtInchrgTelNo;		//계약담당자전화번호
	private String ctrtInchrgFaxNo;		//계약담당자팩스번호
	private String ctrtInchrgEml;		//계약담당자이메일
	private String ctrtStrtDt;			//계약시작일자
	private String ctrtEndDt;			//계약종료일자
	private String ctrtUuid;			//계약첨부파일ID
	private String ctrtAttchFileNm;		//계약첨부파일명
	private String ctrtNote;			//계약비고
	private String bnkCd;				//은행코드
	private String acnt;				//계좌번호
	private String acntHldNm;			//예금주명
	private String pymMthdCd;			//대금지급방법코드
	private String sttlInchrgNm;		//정산담당자명
	private String sttlInchrgTelNo;		//정산담당자전화번호
	private String sttlInchrgFaxNo;		//정산담당자팩스번호
	private String sttlInchrgEml;		//정산담당자이메일
	private String sttlUuid;			//정산첨부파일ID
	private String sttlAttchFileNm;		//정산첨부파일명
	private String sttlInchrgNote;		//정산비고
	private String regrId;				//등록자
	private Date regDate;				//등록일시
	private String chgrId;				//수정자
	private Date chgDate;				//수정일시
	
	private String soNm;				//사업명
	private String addrPurifyYn;		//정제여부
	private String searchType;			//검색타입
	private String searchText;			//검색값
	
	private String lngTyp;
	private String sidx;
	private String sord;
	
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getMncoId() {
		return mncoId;
	}
	public void setMncoId(String mncoId) {
		this.mncoId = mncoId;
	}
	public String getMncoNm() {
		return mncoNm;
	}
	public void setMncoNm(String mncoNm) {
		this.mncoNm = mncoNm;
	}
	public String getBorNo() {
		return borNo;
	}
	public void setBorNo(String borNo) {
		this.borNo = borNo;
	}
	public String getRepNm() {
		return repNm;
	}
	public void setRepNm(String repNm) {
		this.repNm = repNm;
	}
	public String getRepTelNo() {
		return repTelNo;
	}
	public void setRepTelNo(String repTelNo) {
		this.repTelNo = repTelNo;
	}
	public String getRepFaxNo() {
		return repFaxNo;
	}
	public void setRepFaxNo(String repFaxNo) {
		this.repFaxNo = repFaxNo;
	}
	public String getBusiCndt() {
		return busiCndt;
	}
	public void setBusiCndt(String busiCndt) {
		this.busiCndt = busiCndt;
	}
	public String getBusiTp() {
		return busiTp;
	}
	public void setBusiTp(String busiTp) {
		this.busiTp = busiTp;
	}
	public String getPoInchrgNm() {
		return poInchrgNm;
	}
	public void setPoInchrgNm(String poInchrgNm) {
		this.poInchrgNm = poInchrgNm;
	}
	public String getPoInchrgTelNo() {
		return poInchrgTelNo;
	}
	public void setPoInchrgTelNo(String poInchrgTelNo) {
		this.poInchrgTelNo = poInchrgTelNo;
	}
	public String getPoInchrgFaxNo() {
		return poInchrgFaxNo;
	}
	public void setPoInchrgFaxNo(String poInchrgFaxNo) {
		this.poInchrgFaxNo = poInchrgFaxNo;
	}
	public String getPoInchrgEml() {
		return poInchrgEml;
	}
	public void setPoInchrgEml(String poInchrgEml) {
		this.poInchrgEml = poInchrgEml;
	}
	public String getRtngdInchrgNm() {
		return rtngdInchrgNm;
	}
	public void setRtngdInchrgNm(String rtngdInchrgNm) {
		this.rtngdInchrgNm = rtngdInchrgNm;
	}
	public String getRtngdInchrgTelNo() {
		return rtngdInchrgTelNo;
	}
	public void setRtngdInchrgTelNo(String rtngdInchrgTelNo) {
		this.rtngdInchrgTelNo = rtngdInchrgTelNo;
	}
	public String getRtngdInchrgFaxNo() {
		return rtngdInchrgFaxNo;
	}
	public void setRtngdInchrgFaxNo(String rtngdInchrgFaxNo) {
		this.rtngdInchrgFaxNo = rtngdInchrgFaxNo;
	}
	public String getRtngdInchrgEml() {
		return rtngdInchrgEml;
	}
	public void setRtngdInchrgEml(String rtngdInchrgEml) {
		this.rtngdInchrgEml = rtngdInchrgEml;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getBssAddr() {
		return bssAddr;
	}
	public void setBssAddr(String bssAddr) {
		this.bssAddr = bssAddr;
	}
	public String getDtlAddr() {
		return dtlAddr;
	}
	public void setDtlAddr(String dtlAddr) {
		this.dtlAddr = dtlAddr;
	}
	public String getCtrtInchrgNm() {
		return ctrtInchrgNm;
	}
	public void setCtrtInchrgNm(String ctrtInchrgNm) {
		this.ctrtInchrgNm = ctrtInchrgNm;
	}
	public String getCtrtInchrgTelNo() {
		return ctrtInchrgTelNo;
	}
	public void setCtrtInchrgTelNo(String ctrtInchrgTelNo) {
		this.ctrtInchrgTelNo = ctrtInchrgTelNo;
	}
	public String getCtrtInchrgFaxNo() {
		return ctrtInchrgFaxNo;
	}
	public void setCtrtInchrgFaxNo(String ctrtInchrgFaxNo) {
		this.ctrtInchrgFaxNo = ctrtInchrgFaxNo;
	}
	public String getCtrtInchrgEml() {
		return ctrtInchrgEml;
	}
	public void setCtrtInchrgEml(String ctrtInchrgEml) {
		this.ctrtInchrgEml = ctrtInchrgEml;
	}
	public String getCtrtStrtDt() {
		return ctrtStrtDt;
	}
	public void setCtrtStrtDt(String ctrtStrtDt) {
		this.ctrtStrtDt = ctrtStrtDt;
	}
	public String getCtrtEndDt() {
		return ctrtEndDt;
	}
	public void setCtrtEndDt(String ctrtEndDt) {
		this.ctrtEndDt = ctrtEndDt;
	}
	public String getCtrtUuid() {
		return ctrtUuid;
	}
	public void setCtrtUuid(String ctrtUuid) {
		this.ctrtUuid = ctrtUuid;
	}
	public String getCtrtAttchFileNm() {
		return ctrtAttchFileNm;
	}
	public void setCtrtAttchFileNm(String ctrtAttchFileNm) {
		this.ctrtAttchFileNm = ctrtAttchFileNm;
	}
	public String getCtrtNote() {
		return ctrtNote;
	}
	public void setCtrtNote(String ctrtNote) {
		this.ctrtNote = ctrtNote;
	}
	public String getBnkCd() {
		return bnkCd;
	}
	public void setBnkCd(String bnkCd) {
		this.bnkCd = bnkCd;
	}
	public String getAcnt() {
		return acnt;
	}
	public void setAcnt(String acnt) {
		this.acnt = acnt;
	}
	public String getAcntHldNm() {
		return acntHldNm;
	}
	public void setAcntHldNm(String acntHldNm) {
		this.acntHldNm = acntHldNm;
	}
	public String getPymMthdCd() {
		return pymMthdCd;
	}
	public void setPymMthdCd(String pymMthdCd) {
		this.pymMthdCd = pymMthdCd;
	}
	public String getSttlInchrgNm() {
		return sttlInchrgNm;
	}
	public void setSttlInchrgNm(String sttlInchrgNm) {
		this.sttlInchrgNm = sttlInchrgNm;
	}
	public String getSttlInchrgTelNo() {
		return sttlInchrgTelNo;
	}
	public void setSttlInchrgTelNo(String sttlInchrgTelNo) {
		this.sttlInchrgTelNo = sttlInchrgTelNo;
	}
	public String getSttlInchrgFaxNo() {
		return sttlInchrgFaxNo;
	}
	public void setSttlInchrgFaxNo(String sttlInchrgFaxNo) {
		this.sttlInchrgFaxNo = sttlInchrgFaxNo;
	}
	public String getSttlInchrgEml() {
		return sttlInchrgEml;
	}
	public void setSttlInchrgEml(String sttlInchrgEml) {
		this.sttlInchrgEml = sttlInchrgEml;
	}
	public String getSttlUuid() {
		return sttlUuid;
	}
	public void setSttlUuid(String sttlUuid) {
		this.sttlUuid = sttlUuid;
	}
	public String getSttlAttchFileNm() {
		return sttlAttchFileNm;
	}
	public void setSttlAttchFileNm(String sttlAttchFileNm) {
		this.sttlAttchFileNm = sttlAttchFileNm;
	}
	public String getSttlInchrgNote() {
		return sttlInchrgNote;
	}
	public void setSttlInchrgNote(String sttlInchrgNote) {
		this.sttlInchrgNote = sttlInchrgNote;
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
	public String getAddrPurifyYn() {
		return addrPurifyYn;
	}
	public void setAddrPurifyYn(String addrPurifyYn) {
		this.addrPurifyYn = addrPurifyYn;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	
	
}
