package com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt;

import java.util.Date;
import java.util.List;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DistributorReceiptInspVO")
public class DistributorReceiptInspVO extends PagingValue {

	private String soId;			//사업ID
	private String inoutId;			//입출고ID
	private String itemId;			//제품ID
	private String inoutResnCd;		//입출고사유코드
	private String inoutClCd;		//입출고구분코드
	private String itemTpCd;		//제품유형코드
	private String eqtUseCd;		//단말기용도코드
	private String eqtRsrcClCd;		//단말자원구분코드
	private String clorCd;			//색상코드
	private String inoutQty;		//입출고수량
	private String inoutUtPrc;		//입출고단가
	private String inoutAmt;		//입출고공급가액
	private String inoutAddTx;		//입출고부가세
	private String inoutTotAmt;		//입출고총금액
	private String note;			//비고
	private String poNo;			//발주번호
	private String actncNo;			//납품번호
	private String ordNo;			//주문번호
	private String regrId;			//등록자
	private Date regDate;			//등록일시
	private String chgrId;			//수정자
	private Date chgDate;			//수정일시
	
	private String inoutDt;			//입출고일자
	private String inoutPrgrStatCd;	//입출고진행상태코드
	private String statProcId;		//상태처리자ID
	private String statProcDttm;	//상태처리일시
	private String ownOrgId;		//보유조직ID
	private String inoutOrgId;		//입출고조직ID
	private String relInoutId;		//관련입출고ID
	private String dlvId;			//배송ID
	
	private String itemNm;			//제품명
	private String inoutResnNm;		//입출고사유코드명
	private String inoutClNm;		//입출고구분코드명
	private String itemTpNm;		//제품유형코드명
	private String eqtUseNm;		//단말기용도코드명
	private String colorNm;			//색상코드명
	private String inoutPrgrStatNm;	//입출고진행상태코드명
	private String ownOrgNm;		//보유조직명
	private String inoutOrgNm;		//입출고조직명
	private String mncoNm;			//제조사명
	private String dlvDt;			//
	private String invoNo;			//
	private String lgstCentNm;		//
	private String mncoId;			//제조사ID
	private String searchStatDt;	//
	private String searchEndDt;		//
	
	
	private String eqtStatCd;		//제품상태코드
	private String eqtBarCd;		//
	private String apprYn;			//
	private String apprNm;			//
	private String apprYn2;			//
	private String barCd;			//
	private String eqtSeq;			//
	private String lgstProcStatCd;	//
	private String statDt;			//
	private String ownLocCd;		//
	private String eqtRsrcClNm;		//
	private String itemKndNm;		//
	private String eqtStatNm;		//
	
	private String crtSeqNo;		//이력생성일련번호
	private String voSeqNo;			//바우쳐일련번호
	private String voBarCd;			//바우쳐바코드
	private String voStatCd;		//바우쳐상태코드
	private String voIssueDt;		//바우쳐발행일자
	private String voExpiredDt;		//바우쳐만료일자
	private String voIssueAmt;		//바우쳐발행금액
	private String voProdCd;		//바우쳐상품
	private String lgstProcDttm;	//물류처리일시
	private String frstInDt;		//최초물류센터입고일자
	private String agcInDt;			//대리점입고일자
	private String prchsUtPrc;		//매입단가
	private String dstrbUtPrc;		//유통단가
	private String ctOrgId;			//접점조직
	private String ctChgId;			//접점변경자
	private String ctChgDt;			//접점변경일
	private String remark;			//비고
	
	private String histSeq;			//이력일련번호
	private String updProcClCd;		//변경처리구분코드
	private String usimSeq;			//USIM일련번호
	private String updBfrCd;		//변경전코드
	private String aftrUpdCd;		//변경후코드
	private String updProcId;		//변경처리자ID
	private String updProcDttm;		//변경처리일시
	
	private String usimStatCd;
	private String idlStatCd;
	
	private String lngTyp;
	private String sidx;
	private String sord;
	
	private List<DistributorReceiptInspVO> voList;
	
	public String getIdlStatCd() {
		return idlStatCd;
	}
	public void setIdlStatCd(String idlStatCd) {
		this.idlStatCd = idlStatCd;
	}
	public String getUsimStatCd() {
		return usimStatCd;
	}
	public void setUsimStatCd(String usimStatCd) {
		this.usimStatCd = usimStatCd;
	}
	public String getHistSeq() {
		return histSeq;
	}
	public void setHistSeq(String histSeq) {
		this.histSeq = histSeq;
	}
	public String getUpdProcClCd() {
		return updProcClCd;
	}
	public void setUpdProcClCd(String updProcClCd) {
		this.updProcClCd = updProcClCd;
	}
	public String getUsimSeq() {
		return usimSeq;
	}
	public void setUsimSeq(String usimSeq) {
		this.usimSeq = usimSeq;
	}
	public String getUpdBfrCd() {
		return updBfrCd;
	}
	public void setUpdBfrCd(String updBfrCd) {
		this.updBfrCd = updBfrCd;
	}
	public String getAftrUpdCd() {
		return aftrUpdCd;
	}
	public void setAftrUpdCd(String aftrUpdCd) {
		this.aftrUpdCd = aftrUpdCd;
	}
	public String getUpdProcId() {
		return updProcId;
	}
	public void setUpdProcId(String updProcId) {
		this.updProcId = updProcId;
	}
	public String getUpdProcDttm() {
		return updProcDttm;
	}
	public void setUpdProcDttm(String updProcDttm) {
		this.updProcDttm = updProcDttm;
	}
	public String getCrtSeqNo() {
		return crtSeqNo;
	}
	public void setCrtSeqNo(String crtSeqNo) {
		this.crtSeqNo = crtSeqNo;
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
	public String getVoStatCd() {
		return voStatCd;
	}
	public void setVoStatCd(String voStatCd) {
		this.voStatCd = voStatCd;
	}
	public String getVoIssueDt() {
		return voIssueDt;
	}
	public void setVoIssueDt(String voIssueDt) {
		this.voIssueDt = voIssueDt;
	}
	public String getVoExpiredDt() {
		return voExpiredDt;
	}
	public void setVoExpiredDt(String voExpiredDt) {
		this.voExpiredDt = voExpiredDt;
	}
	public String getVoIssueAmt() {
		return voIssueAmt;
	}
	public void setVoIssueAmt(String voIssueAmt) {
		this.voIssueAmt = voIssueAmt;
	}
	public String getVoProdCd() {
		return voProdCd;
	}
	public void setVoProdCd(String voProdCd) {
		this.voProdCd = voProdCd;
	}
	public String getLgstProcDttm() {
		return lgstProcDttm;
	}
	public void setLgstProcDttm(String lgstProcDttm) {
		this.lgstProcDttm = lgstProcDttm;
	}
	public String getFrstInDt() {
		return frstInDt;
	}
	public void setFrstInDt(String frstInDt) {
		this.frstInDt = frstInDt;
	}
	public String getAgcInDt() {
		return agcInDt;
	}
	public void setAgcInDt(String agcInDt) {
		this.agcInDt = agcInDt;
	}
	public String getPrchsUtPrc() {
		return prchsUtPrc;
	}
	public void setPrchsUtPrc(String prchsUtPrc) {
		this.prchsUtPrc = prchsUtPrc;
	}
	public String getDstrbUtPrc() {
		return dstrbUtPrc;
	}
	public void setDstrbUtPrc(String dstrbUtPrc) {
		this.dstrbUtPrc = dstrbUtPrc;
	}
	public String getCtOrgId() {
		return ctOrgId;
	}
	public void setCtOrgId(String ctOrgId) {
		this.ctOrgId = ctOrgId;
	}
	public String getCtChgId() {
		return ctChgId;
	}
	public void setCtChgId(String ctChgId) {
		this.ctChgId = ctChgId;
	}
	public String getCtChgDt() {
		return ctChgDt;
	}
	public void setCtChgDt(String ctChgDt) {
		this.ctChgDt = ctChgDt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<DistributorReceiptInspVO> getVoList() {
		return voList;
	}
	public void setVoList(List<DistributorReceiptInspVO> voList) {
		this.voList = voList;
	}
	public String getEqtStatNm() {
		return eqtStatNm;
	}
	public void setEqtStatNm(String eqtStatNm) {
		this.eqtStatNm = eqtStatNm;
	}
	public String getEqtStatCd() {
		return eqtStatCd;
	}
	public void setEqtStatCd(String eqtStatCd) {
		this.eqtStatCd = eqtStatCd;
	}
	public String getEqtBarCd() {
		return eqtBarCd;
	}
	public void setEqtBarCd(String eqtBarCd) {
		this.eqtBarCd = eqtBarCd;
	}
	public String getApprYn() {
		return apprYn;
	}
	public void setApprYn(String apprYn) {
		this.apprYn = apprYn;
	}
	public String getApprNm() {
		return apprNm;
	}
	public void setApprNm(String apprNm) {
		this.apprNm = apprNm;
	}
	public String getApprYn2() {
		return apprYn2;
	}
	public void setApprYn2(String apprYn2) {
		this.apprYn2 = apprYn2;
	}
	public String getBarCd() {
		return barCd;
	}
	public void setBarCd(String barCd) {
		this.barCd = barCd;
	}
	public String getEqtSeq() {
		return eqtSeq;
	}
	public void setEqtSeq(String eqtSeq) {
		this.eqtSeq = eqtSeq;
	}
	public String getLgstProcStatCd() {
		return lgstProcStatCd;
	}
	public void setLgstProcStatCd(String lgstProcStatCd) {
		this.lgstProcStatCd = lgstProcStatCd;
	}
	public String getStatDt() {
		return statDt;
	}
	public void setStatDt(String statDt) {
		this.statDt = statDt;
	}
	public String getOwnLocCd() {
		return ownLocCd;
	}
	public void setOwnLocCd(String ownLocCd) {
		this.ownLocCd = ownLocCd;
	}
	public String getEqtRsrcClNm() {
		return eqtRsrcClNm;
	}
	public void setEqtRsrcClNm(String eqtRsrcClNm) {
		this.eqtRsrcClNm = eqtRsrcClNm;
	}
	public String getItemKndNm() {
		return itemKndNm;
	}
	public void setItemKndNm(String itemKndNm) {
		this.itemKndNm = itemKndNm;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String getInoutResnNm() {
		return inoutResnNm;
	}
	public void setInoutResnNm(String inoutResnNm) {
		this.inoutResnNm = inoutResnNm;
	}
	public String getInoutClNm() {
		return inoutClNm;
	}
	public void setInoutClNm(String inoutClNm) {
		this.inoutClNm = inoutClNm;
	}
	public String getItemTpNm() {
		return itemTpNm;
	}
	public void setItemTpNm(String itemTpNm) {
		this.itemTpNm = itemTpNm;
	}
	public String getEqtUseNm() {
		return eqtUseNm;
	}
	public void setEqtUseNm(String eqtUseNm) {
		this.eqtUseNm = eqtUseNm;
	}
	public String getColorNm() {
		return colorNm;
	}
	public void setColorNm(String colorNm) {
		this.colorNm = colorNm;
	}
	public String getInoutPrgrStatNm() {
		return inoutPrgrStatNm;
	}
	public void setInoutPrgrStatNm(String inoutPrgrStatNm) {
		this.inoutPrgrStatNm = inoutPrgrStatNm;
	}
	public String getOwnOrgNm() {
		return ownOrgNm;
	}
	public void setOwnOrgNm(String ownOrgNm) {
		this.ownOrgNm = ownOrgNm;
	}
	public String getInoutOrgNm() {
		return inoutOrgNm;
	}
	public void setInoutOrgNm(String inoutOrgNm) {
		this.inoutOrgNm = inoutOrgNm;
	}
	public String getMncoNm() {
		return mncoNm;
	}
	public void setMncoNm(String mncoNm) {
		this.mncoNm = mncoNm;
	}
	public String getDlvDt() {
		return dlvDt;
	}
	public void setDlvDt(String dlvDt) {
		this.dlvDt = dlvDt;
	}
	public String getInvoNo() {
		return invoNo;
	}
	public void setInvoNo(String invoNo) {
		this.invoNo = invoNo;
	}
	public String getLgstCentNm() {
		return lgstCentNm;
	}
	public void setLgstCentNm(String lgstCentNm) {
		this.lgstCentNm = lgstCentNm;
	}
	public String getMncoId() {
		return mncoId;
	}
	public void setMncoId(String mncoId) {
		this.mncoId = mncoId;
	}
	public String getSearchStatDt() {
		return searchStatDt;
	}
	public void setSearchStatDt(String searchStatDt) {
		this.searchStatDt = searchStatDt;
	}
	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}
	public String getInoutDt() {
		return inoutDt;
	}
	public void setInoutDt(String inoutDt) {
		this.inoutDt = inoutDt;
	}
	public String getInoutPrgrStatCd() {
		return inoutPrgrStatCd;
	}
	public void setInoutPrgrStatCd(String inoutPrgrStatCd) {
		this.inoutPrgrStatCd = inoutPrgrStatCd;
	}
	public String getStatProcId() {
		return statProcId;
	}
	public void setStatProcId(String statProcId) {
		this.statProcId = statProcId;
	}
	public String getStatProcDttm() {
		return statProcDttm;
	}
	public void setStatProcDttm(String statProcDttm) {
		this.statProcDttm = statProcDttm;
	}
	public String getOwnOrgId() {
		return ownOrgId;
	}
	public void setOwnOrgId(String ownOrgId) {
		this.ownOrgId = ownOrgId;
	}
	public String getInoutOrgId() {
		return inoutOrgId;
	}
	public void setInoutOrgId(String inoutOrgId) {
		this.inoutOrgId = inoutOrgId;
	}
	public String getRelInoutId() {
		return relInoutId;
	}
	public void setRelInoutId(String relInoutId) {
		this.relInoutId = relInoutId;
	}
	public String getDlvId() {
		return dlvId;
	}
	public void setDlvId(String dlvId) {
		this.dlvId = dlvId;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getInoutId() {
		return inoutId;
	}
	public void setInoutId(String inoutId) {
		this.inoutId = inoutId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getInoutResnCd() {
		return inoutResnCd;
	}
	public void setInoutResnCd(String inoutResnCd) {
		this.inoutResnCd = inoutResnCd;
	}
	public String getInoutClCd() {
		return inoutClCd;
	}
	public void setInoutClCd(String inoutClCd) {
		this.inoutClCd = inoutClCd;
	}
	public String getItemTpCd() {
		return itemTpCd;
	}
	public void setItemTpCd(String itemTpCd) {
		this.itemTpCd = itemTpCd;
	}
	public String getEqtUseCd() {
		return eqtUseCd;
	}
	public void setEqtUseCd(String eqtUseCd) {
		this.eqtUseCd = eqtUseCd;
	}
	public String getEqtRsrcClCd() {
		return eqtRsrcClCd;
	}
	public void setEqtRsrcClCd(String eqtRsrcClCd) {
		this.eqtRsrcClCd = eqtRsrcClCd;
	}
	public String getClorCd() {
		return clorCd;
	}
	public void setClorCd(String clorCd) {
		this.clorCd = clorCd;
	}
	public String getInoutQty() {
		return inoutQty;
	}
	public void setInoutQty(String inoutQty) {
		this.inoutQty = inoutQty;
	}
	public String getInoutUtPrc() {
		return inoutUtPrc;
	}
	public void setInoutUtPrc(String inoutUtPrc) {
		this.inoutUtPrc = inoutUtPrc;
	}
	public String getInoutAmt() {
		return inoutAmt;
	}
	public void setInoutAmt(String inoutAmt) {
		this.inoutAmt = inoutAmt;
	}
	public String getInoutAddTx() {
		return inoutAddTx;
	}
	public void setInoutAddTx(String inoutAddTx) {
		this.inoutAddTx = inoutAddTx;
	}
	public String getInoutTotAmt() {
		return inoutTotAmt;
	}
	public void setInoutTotAmt(String inoutTotAmt) {
		this.inoutTotAmt = inoutTotAmt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getActncNo() {
		return actncNo;
	}
	public void setActncNo(String actncNo) {
		this.actncNo = actncNo;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
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
