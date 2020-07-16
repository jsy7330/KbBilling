package com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("OrderPlacementVO")
public class OrderPlacementVO extends PagingValue {

	private String soId;			//사업ID
	private String poNo;			//발주번호
	private String poOrgId;			//발주조직ID
	private String lgstCentId;		//물류센터ID
	private String mncoId;			//제조사ID
	private String poPrgrStatCd;	//발주진행상태코드
	private String itemId;			//제품ID
	private String itemTpCd;		//제품유형코드
	private String colorCd;			//색상코드
	private String dlgdAgreeDt;		//납품합의일자
	private String poQty;			//발주수량
	private String poUtPrc;			//발주단가
	private String poAmt;			//발주금액
	private String dlvZipCd;		//배송지우편번호
	private String dlvBssAddr;		//배송지기본주소
	private String dlvDtlAddr;		//배송지상세주소
	private String addTx;			//부가가치세
	private String totPrchsAmt;		//발주총금액
	private String poRegDt;			//발주일자
	private String poConfDt;		//발주확정일자
	private String actncInchrg;		//인수담당자
	private String actncInchrgTel;	//인수담당자전화번호
	private String note;			//비고
	private String regrId;			//등록자
	private Date regDate;			//등록일시
	private String chgrId;			//수정자
	private Date chgDate;			//수정일시
	
	private String searchStatDt;	//수정일시
	private String searchEndDt;		//수정일시
	
	private String poOrgNm;			//발주조직명
	private String lgstCentNm;		//물류센터명
	private String mncoNm;			//제조사명
	private String itemNm;			//제품명
	private String itemTpNm;		//제품유형명
	private String colorNm;			//색상명
	private String poActncQty;		//기납품수량
	private String poActncAddTx;	//기부가세
	private String poInchrgNm;		//발주담당자명
	private String poInchrgEml;		//발주담당자이메일
	private String poPrgrStatNm;	//발주진행상태명
	
	private String statProcId;		//상태처리자ID
	private String statProcDttm;	//상태처리일시
	
	private String idlStatCd;		//대기상태코드
	private String paramType;		//발주확정,취소 구분 타입
	
	private String actncNo;			//납품번호
	private String ordNo;			//주문번호
	private String clorCd;			//색상코드
	private String actncDt;			//납품일자
	private String actncPrgrStatCd;	//납품진행상태코드
	private String actncQty;		//납품수량
	private String actncUtPrc;		//납품단가
	private String actncAmt;		//납품금액
	private String actncAddTx;		//납품부가세
	private String actncTotAmt;		//납품총금액
	private String actncOrgId;		//납품조직ID
	private String actncInchrgId;	//납품담당자ID
	private String actncInchrgTelNo;//납품담당자연락처
	private String inoutResnCd;		//입출고사유코드
	private String inoutClCd;		//입출고구분코드
	
	private String itemUsgAmt;		//
	
	private String actncPrgrStatNm;	//납품진행상태명
	
	private String taxRate;				//부가세
	
	private String lngTyp;
	private String sidx;
	private String sord;
	
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getItemUsgAmt() {
		return itemUsgAmt;
	}
	public void setItemUsgAmt(String itemUsgAmt) {
		this.itemUsgAmt = itemUsgAmt;
	}
	public String getActncPrgrStatNm() {
		return actncPrgrStatNm;
	}
	public void setActncPrgrStatNm(String actncPrgrStatNm) {
		this.actncPrgrStatNm = actncPrgrStatNm;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getIdlStatCd() {
		return idlStatCd;
	}
	public void setIdlStatCd(String idlStatCd) {
		this.idlStatCd = idlStatCd;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getPoOrgId() {
		return poOrgId;
	}
	public void setPoOrgId(String poOrgId) {
		this.poOrgId = poOrgId;
	}
	public String getLgstCentId() {
		return lgstCentId;
	}
	public void setLgstCentId(String lgstCentId) {
		this.lgstCentId = lgstCentId;
	}
	public String getMncoId() {
		return mncoId;
	}
	public void setMncoId(String mncoId) {
		this.mncoId = mncoId;
	}
	public String getPoPrgrStatCd() {
		return poPrgrStatCd;
	}
	public void setPoPrgrStatCd(String poPrgrStatCd) {
		this.poPrgrStatCd = poPrgrStatCd;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemTpCd() {
		return itemTpCd;
	}
	public void setItemTpCd(String itemTpCd) {
		this.itemTpCd = itemTpCd;
	}
	public String getColorCd() {
		return colorCd;
	}
	public void setColorCd(String colorCd) {
		this.colorCd = colorCd;
	}
	public String getDlgdAgreeDt() {
		return dlgdAgreeDt;
	}
	public void setDlgdAgreeDt(String dlgdAgreeDt) {
		this.dlgdAgreeDt = dlgdAgreeDt;
	}
	public String getPoQty() {
		return poQty;
	}
	public void setPoQty(String poQty) {
		this.poQty = poQty;
	}
	public String getPoUtPrc() {
		return poUtPrc;
	}
	public void setPoUtPrc(String poUtPrc) {
		this.poUtPrc = poUtPrc;
	}
	public String getPoAmt() {
		return poAmt;
	}
	public void setPoAmt(String poAmt) {
		this.poAmt = poAmt;
	}
	public String getDlvZipCd() {
		return dlvZipCd;
	}
	public void setDlvZipCd(String dlvZipCd) {
		this.dlvZipCd = dlvZipCd;
	}
	public String getDlvBssAddr() {
		return dlvBssAddr;
	}
	public void setDlvBssAddr(String dlvBssAddr) {
		this.dlvBssAddr = dlvBssAddr;
	}
	public String getDlvDtlAddr() {
		return dlvDtlAddr;
	}
	public void setDlvDtlAddr(String dlvDtlAddr) {
		this.dlvDtlAddr = dlvDtlAddr;
	}
	public String getAddTx() {
		return addTx;
	}
	public void setAddTx(String addTx) {
		this.addTx = addTx;
	}
	public String getTotPrchsAmt() {
		return totPrchsAmt;
	}
	public void setTotPrchsAmt(String totPrchsAmt) {
		this.totPrchsAmt = totPrchsAmt;
	}
	public String getPoRegDt() {
		return poRegDt;
	}
	public void setPoRegDt(String poRegDt) {
		this.poRegDt = poRegDt;
	}
	public String getPoConfDt() {
		return poConfDt;
	}
	public void setPoConfDt(String poConfDt) {
		this.poConfDt = poConfDt;
	}
	public String getActncInchrg() {
		return actncInchrg;
	}
	public void setActncInchrg(String actncInchrg) {
		this.actncInchrg = actncInchrg;
	}
	public String getActncInchrgTel() {
		return actncInchrgTel;
	}
	public void setActncInchrgTel(String actncInchrgTel) {
		this.actncInchrgTel = actncInchrgTel;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public String getPoOrgNm() {
		return poOrgNm;
	}
	public void setPoOrgNm(String poOrgNm) {
		this.poOrgNm = poOrgNm;
	}
	public String getLgstCentNm() {
		return lgstCentNm;
	}
	public void setLgstCentNm(String lgstCentNm) {
		this.lgstCentNm = lgstCentNm;
	}
	public String getMncoNm() {
		return mncoNm;
	}
	public void setMncoNm(String mncoNm) {
		this.mncoNm = mncoNm;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String getItemTpNm() {
		return itemTpNm;
	}
	public void setItemTpNm(String itemTpNm) {
		this.itemTpNm = itemTpNm;
	}
	public String getColorNm() {
		return colorNm;
	}
	public void setColorNm(String colorNm) {
		this.colorNm = colorNm;
	}
	public String getPoActncQty() {
		return poActncQty;
	}
	public void setPoActncQty(String poActncQty) {
		this.poActncQty = poActncQty;
	}
	public String getPoActncAddTx() {
		return poActncAddTx;
	}
	public void setPoActncAddTx(String poActncAddTx) {
		this.poActncAddTx = poActncAddTx;
	}
	public String getPoInchrgNm() {
		return poInchrgNm;
	}
	public void setPoInchrgNm(String poInchrgNm) {
		this.poInchrgNm = poInchrgNm;
	}
	public String getPoInchrgEml() {
		return poInchrgEml;
	}
	public void setPoInchrgEml(String poInchrgEml) {
		this.poInchrgEml = poInchrgEml;
	}
	public String getPoPrgrStatNm() {
		return poPrgrStatNm;
	}
	public void setPoPrgrStatNm(String poPrgrStatNm) {
		this.poPrgrStatNm = poPrgrStatNm;
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
	public String getClorCd() {
		return clorCd;
	}
	public void setClorCd(String clorCd) {
		this.clorCd = clorCd;
	}
	public String getActncDt() {
		return actncDt;
	}
	public void setActncDt(String actncDt) {
		this.actncDt = actncDt;
	}
	public String getActncPrgrStatCd() {
		return actncPrgrStatCd;
	}
	public void setActncPrgrStatCd(String actncPrgrStatCd) {
		this.actncPrgrStatCd = actncPrgrStatCd;
	}
	public String getActncQty() {
		return actncQty;
	}
	public void setActncQty(String actncQty) {
		this.actncQty = actncQty;
	}
	public String getActncUtPrc() {
		return actncUtPrc;
	}
	public void setActncUtPrc(String actncUtPrc) {
		this.actncUtPrc = actncUtPrc;
	}
	public String getActncAmt() {
		return actncAmt;
	}
	public void setActncAmt(String actncAmt) {
		this.actncAmt = actncAmt;
	}
	public String getActncAddTx() {
		return actncAddTx;
	}
	public void setActncAddTx(String actncAddTx) {
		this.actncAddTx = actncAddTx;
	}
	public String getActncTotAmt() {
		return actncTotAmt;
	}
	public void setActncTotAmt(String actncTotAmt) {
		this.actncTotAmt = actncTotAmt;
	}
	public String getActncOrgId() {
		return actncOrgId;
	}
	public void setActncOrgId(String actncOrgId) {
		this.actncOrgId = actncOrgId;
	}
	public String getActncInchrgId() {
		return actncInchrgId;
	}
	public void setActncInchrgId(String actncInchrgId) {
		this.actncInchrgId = actncInchrgId;
	}
	public String getActncInchrgTelNo() {
		return actncInchrgTelNo;
	}
	public void setActncInchrgTelNo(String actncInchrgTelNo) {
		this.actncInchrgTelNo = actncInchrgTelNo;
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
	
	
	
}
