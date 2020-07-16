package com.ntels.ccbs.distribute.domain.logistics.logisticsCenterReceIssue;

import java.util.Date;
import java.util.List;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("LogisticsCenterIssueInspVO")
public class LogisticsCenterIssueInspVO  extends PagingValue {

	private String soId;				//사업ID
	private String ordNo;				//주문번호
	private String ordOrgId;			//주문조직ID
	private String lgstCentId;			//물류센터ID
	private String ordMngOrgId;			//주문관리조직ID
	private String ordMngInchrgId;		//주문관리담당자ID
	private String ordPrgrStatCd;		//주문진행상태코드
	private String itemId;				//제품ID
	private String itemTpCd;			//제품유형코드
	private String eqtUseCd;			//단말기용도코드
	private String clorCd;				//색상코드
	private String ordQty;				//주문수량
	private String ordUtPrc;			//주문단가
	private String ordAmt;				//주문공급가액
	private String ordAddTx;			//주문부가세
	private String ordTotAmt;			//주문총금액
	private String ordConfQty;			//주문확정수량
	private String statProcId;			//상태처리자ID
	private String statProcDttm;		//상태처리일시
	private String dlvZipCd;			//배송지우편번호
	private String dlvBssAddr;			//배송지기본주소
	private String dlvDtlAddr;			//배송지상세주소
	private String ordNote;				//주문비고
	private String asgnNo;				//할당번호
	private String asgnOrgId;			//할당조직ID
	private String regrId;				//등록자
	private Date regDate;				//등록일시
	private String chgrId;				//수정자
	private Date chgDate;				//수정일시
	
	private String ordOrgNm;			//주문조직명
	private String ordMngOrgNm;			//주문관리조직명
	private String itemNm;				//제품명
	private String itemTpNm;			//제품유형명
	private String eqtUseNm;			//단말기용도코드명
	private String colorNm;				//색상명
	private String ordInoutAddTx;		//기부가세
	private String ordInoutQty;			//기출고수량
	private String ordDt;				//주문일자
	private String dlvAddr;				//주배송지소
	
	private String searchStatDt;		//
	private String searchEndDt;			//
	private String searchSeq;			//
	
	private String eqtSeq;				//일련번호
	private String eqtBarCd;			//바코드
	private String eqtRsrcClCd;			//단말기자원구분코드
	private String eqtRsrcClNm;			//단말기자원구분코드명
	private String eqtStatCd;			//단말기상태코드
	private String eqtStatNm;			//단말기상태코드명
	private String searchStatSeq;		//검색일련번호 시작값
	private String searchEndSeq;		//검색일련번호 종료값
	private String ownLocCd;			//
	private String idlStatCd;			//
	private String usimSeq;				//
	private String imsiNo;				//
	
	private String inoutId;				//입출고ID
	private String inoutClCd;			//입출고구분코드
	private String inoutDt;				//입출고일자
	private String inoutPrgrStatCd;		//입출고진행상태코드
	private String ownOrgId;			//보유조직ID
	private String inoutOrgId;			//입출고조직ID
	private String relInoutId;			//관련입출고ID
	private String dlvId;				//배송ID
	
	private String inoutResnCd;		//입출고사유코드
	private String inoutQty;		//입출고수량
	private String inoutUtPrc;		//입출고단가
	private String inoutAmt;		//입출고공급가액
	private String inoutAddTx;		//입출고부가세
	private String inoutTotAmt;		//입출고총금액
	private String note;			//비고
	private String poNo;			//발주번호
	private String actncNo;			//납품번호

	private String ownOrgNm;		//출고대상조직
	private String inoutClNm;		//입출고구분코드명
	private String inoutResnNm;		//입출고사유코드명
	private String inoutPrgrStatNm;	//입출고진행상태코드명
	private String statProcDt;		//
	private String inoutOrgNm;		//입출고조직명
	
	private String apprYn;			//검수여부
	private String lgstProcStatCd;	//물류진행상태
	private String lgstProcDttm;	//물류처리일시
	private String updBfrCd;		//이전물류진행상태
	private String aftrUpdCd;		//이후물류진행상태
	private String updProcClCd;		//변경처리구분
	private String updProcId;		//변경처리자ID
	private String dstrbUtPrc;		//유통단가
	
	private String histSeq;			//이력일련번호
	private String updProcDttm;		//변경처리일시
	private String voStatCd;		//바우처상태
	private String voSeqNo;			//바우쳐일련번호
	
	private String crtSeqNo;		//이력생성일련번호
	private String voBarCd;			//바우쳐바코드
	private String voIssueDt;		//바우쳐발행일자
	private String voExpiredDt;		//바우쳐만료일자
	private String voIssueAmt;		//바우쳐발행금액
	private String voProdCd;		//바우쳐상품
	private String frstInDt;		//최초물류센터입고일자
	private String agcInDt;			//대리점입고일자
	private String prchsUtPrc;		//매입단가
	private String ctOrgId;			//접점조직
	private String ctChgId;			//접점변경자
	private String ctChgDt;			//접점변경일
	private String remark;			//비고
	
	private List<LogisticsCenterIssueInspVO> eqtSeqList;
	
	private String lngTyp;
	private String sidx;
	private String sord;
	
	public String getCrtSeqNo() {
		return crtSeqNo;
	}
	public void setCrtSeqNo(String crtSeqNo) {
		this.crtSeqNo = crtSeqNo;
	}
	public String getVoBarCd() {
		return voBarCd;
	}
	public void setVoBarCd(String voBarCd) {
		this.voBarCd = voBarCd;
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
	public String getVoSeqNo() {
		return voSeqNo;
	}
	public void setVoSeqNo(String voSeqNo) {
		this.voSeqNo = voSeqNo;
	}
	public String getVoStatCd() {
		return voStatCd;
	}
	public void setVoStatCd(String voStatCd) {
		this.voStatCd = voStatCd;
	}
	public String getUpdProcDttm() {
		return updProcDttm;
	}
	public void setUpdProcDttm(String updProcDttm) {
		this.updProcDttm = updProcDttm;
	}
	public String getHistSeq() {
		return histSeq;
	}
	public void setHistSeq(String histSeq) {
		this.histSeq = histSeq;
	}
	public String getLgstProcDttm() {
		return lgstProcDttm;
	}
	public void setLgstProcDttm(String lgstProcDttm) {
		this.lgstProcDttm = lgstProcDttm;
	}
	public String getDstrbUtPrc() {
		return dstrbUtPrc;
	}
	public void setDstrbUtPrc(String dstrbUtPrc) {
		this.dstrbUtPrc = dstrbUtPrc;
	}
	public String getUpdProcId() {
		return updProcId;
	}
	public void setUpdProcId(String updProcId) {
		this.updProcId = updProcId;
	}
	public String getUpdProcClCd() {
		return updProcClCd;
	}
	public void setUpdProcClCd(String updProcClCd) {
		this.updProcClCd = updProcClCd;
	}
	public String getAftrUpdCd() {
		return aftrUpdCd;
	}
	public void setAftrUpdCd(String aftrUpdCd) {
		this.aftrUpdCd = aftrUpdCd;
	}
	public String getUpdBfrCd() {
		return updBfrCd;
	}
	public void setUpdBfrCd(String updBfrCd) {
		this.updBfrCd = updBfrCd;
	}
	public String getLgstProcStatCd() {
		return lgstProcStatCd;
	}
	public void setLgstProcStatCd(String lgstProcStatCd) {
		this.lgstProcStatCd = lgstProcStatCd;
	}
	public String getSearchSeq() {
		return searchSeq;
	}
	public void setSearchSeq(String searchSeq) {
		this.searchSeq = searchSeq;
	}
	public String getInoutClNm() {
		return inoutClNm;
	}
	public void setInoutClNm(String inoutClNm) {
		this.inoutClNm = inoutClNm;
	}
	public String getInoutResnNm() {
		return inoutResnNm;
	}
	public void setInoutResnNm(String inoutResnNm) {
		this.inoutResnNm = inoutResnNm;
	}
	public String getInoutPrgrStatNm() {
		return inoutPrgrStatNm;
	}
	public void setInoutPrgrStatNm(String inoutPrgrStatNm) {
		this.inoutPrgrStatNm = inoutPrgrStatNm;
	}
	public String getStatProcDt() {
		return statProcDt;
	}
	public void setStatProcDt(String statProcDt) {
		this.statProcDt = statProcDt;
	}
	public String getInoutOrgNm() {
		return inoutOrgNm;
	}
	public void setInoutOrgNm(String inoutOrgNm) {
		this.inoutOrgNm = inoutOrgNm;
	}
	public String getOwnOrgNm() {
		return ownOrgNm;
	}
	public void setOwnOrgNm(String ownOrgNm) {
		this.ownOrgNm = ownOrgNm;
	}
	public List<LogisticsCenterIssueInspVO> getEqtSeqList() {
		return eqtSeqList;
	}
	public void setEqtSeqList(List<LogisticsCenterIssueInspVO> eqtSeqList) {
		this.eqtSeqList = eqtSeqList;
	}
	public String getInoutId() {
		return inoutId;
	}
	public void setInoutId(String inoutId) {
		this.inoutId = inoutId;
	}
	public String getInoutClCd() {
		return inoutClCd;
	}
	public void setInoutClCd(String inoutClCd) {
		this.inoutClCd = inoutClCd;
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
	public String getInoutResnCd() {
		return inoutResnCd;
	}
	public void setInoutResnCd(String inoutResnCd) {
		this.inoutResnCd = inoutResnCd;
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
	public String getApprYn() {
		return apprYn;
	}
	public void setApprYn(String apprYn) {
		this.apprYn = apprYn;
	}
	public String getEqtSeq() {
		return eqtSeq;
	}
	public void setEqtSeq(String eqtSeq) {
		this.eqtSeq = eqtSeq;
	}
	public String getEqtBarCd() {
		return eqtBarCd;
	}
	public void setEqtBarCd(String eqtBarCd) {
		this.eqtBarCd = eqtBarCd;
	}
	public String getEqtRsrcClCd() {
		return eqtRsrcClCd;
	}
	public void setEqtRsrcClCd(String eqtRsrcClCd) {
		this.eqtRsrcClCd = eqtRsrcClCd;
	}
	public String getEqtRsrcClNm() {
		return eqtRsrcClNm;
	}
	public void setEqtRsrcClNm(String eqtRsrcClNm) {
		this.eqtRsrcClNm = eqtRsrcClNm;
	}
	public String getEqtStatCd() {
		return eqtStatCd;
	}
	public void setEqtStatCd(String eqtStatCd) {
		this.eqtStatCd = eqtStatCd;
	}
	public String getEqtStatNm() {
		return eqtStatNm;
	}
	public void setEqtStatNm(String eqtStatNm) {
		this.eqtStatNm = eqtStatNm;
	}
	public String getSearchStatSeq() {
		return searchStatSeq;
	}
	public void setSearchStatSeq(String searchStatSeq) {
		this.searchStatSeq = searchStatSeq;
	}
	public String getSearchEndSeq() {
		return searchEndSeq;
	}
	public void setSearchEndSeq(String searchEndSeq) {
		this.searchEndSeq = searchEndSeq;
	}
	public String getOwnLocCd() {
		return ownLocCd;
	}
	public void setOwnLocCd(String ownLocCd) {
		this.ownLocCd = ownLocCd;
	}
	public String getIdlStatCd() {
		return idlStatCd;
	}
	public void setIdlStatCd(String idlStatCd) {
		this.idlStatCd = idlStatCd;
	}
	public String getUsimSeq() {
		return usimSeq;
	}
	public void setUsimSeq(String usimSeq) {
		this.usimSeq = usimSeq;
	}
	public String getImsiNo() {
		return imsiNo;
	}
	public void setImsiNo(String imsiNo) {
		this.imsiNo = imsiNo;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getOrdOrgId() {
		return ordOrgId;
	}
	public void setOrdOrgId(String ordOrgId) {
		this.ordOrgId = ordOrgId;
	}
	public String getLgstCentId() {
		return lgstCentId;
	}
	public void setLgstCentId(String lgstCentId) {
		this.lgstCentId = lgstCentId;
	}
	public String getOrdMngOrgId() {
		return ordMngOrgId;
	}
	public void setOrdMngOrgId(String ordMngOrgId) {
		this.ordMngOrgId = ordMngOrgId;
	}
	public String getOrdMngInchrgId() {
		return ordMngInchrgId;
	}
	public void setOrdMngInchrgId(String ordMngInchrgId) {
		this.ordMngInchrgId = ordMngInchrgId;
	}
	public String getOrdPrgrStatCd() {
		return ordPrgrStatCd;
	}
	public void setOrdPrgrStatCd(String ordPrgrStatCd) {
		this.ordPrgrStatCd = ordPrgrStatCd;
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
	public String getEqtUseCd() {
		return eqtUseCd;
	}
	public void setEqtUseCd(String eqtUseCd) {
		this.eqtUseCd = eqtUseCd;
	}
	public String getClorCd() {
		return clorCd;
	}
	public void setClorCd(String clorCd) {
		this.clorCd = clorCd;
	}
	public String getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(String ordQty) {
		this.ordQty = ordQty;
	}
	public String getOrdUtPrc() {
		return ordUtPrc;
	}
	public void setOrdUtPrc(String ordUtPrc) {
		this.ordUtPrc = ordUtPrc;
	}
	public String getOrdAmt() {
		return ordAmt;
	}
	public void setOrdAmt(String ordAmt) {
		this.ordAmt = ordAmt;
	}
	public String getOrdAddTx() {
		return ordAddTx;
	}
	public void setOrdAddTx(String ordAddTx) {
		this.ordAddTx = ordAddTx;
	}
	public String getOrdTotAmt() {
		return ordTotAmt;
	}
	public void setOrdTotAmt(String ordTotAmt) {
		this.ordTotAmt = ordTotAmt;
	}
	public String getOrdConfQty() {
		return ordConfQty;
	}
	public void setOrdConfQty(String ordConfQty) {
		this.ordConfQty = ordConfQty;
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
	public String getOrdNote() {
		return ordNote;
	}
	public void setOrdNote(String ordNote) {
		this.ordNote = ordNote;
	}
	public String getAsgnNo() {
		return asgnNo;
	}
	public void setAsgnNo(String asgnNo) {
		this.asgnNo = asgnNo;
	}
	public String getAsgnOrgId() {
		return asgnOrgId;
	}
	public void setAsgnOrgId(String asgnOrgId) {
		this.asgnOrgId = asgnOrgId;
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
	public String getOrdOrgNm() {
		return ordOrgNm;
	}
	public void setOrdOrgNm(String ordOrgNm) {
		this.ordOrgNm = ordOrgNm;
	}
	public String getOrdMngOrgNm() {
		return ordMngOrgNm;
	}
	public void setOrdMngOrgNm(String ordMngOrgNm) {
		this.ordMngOrgNm = ordMngOrgNm;
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
	public String getOrdInoutAddTx() {
		return ordInoutAddTx;
	}
	public void setOrdInoutAddTx(String ordInoutAddTx) {
		this.ordInoutAddTx = ordInoutAddTx;
	}
	public String getOrdInoutQty() {
		return ordInoutQty;
	}
	public void setOrdInoutQty(String ordInoutQty) {
		this.ordInoutQty = ordInoutQty;
	}
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
	public String getDlvAddr() {
		return dlvAddr;
	}
	public void setDlvAddr(String dlvAddr) {
		this.dlvAddr = dlvAddr;
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


