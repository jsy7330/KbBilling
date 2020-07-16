package com.ntels.ccbs.customer.domain.statistics.orderStatistics;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class OrderStatisticsVO implements Serializable,CommonVO{

	private String soId; 			//사업ID
	private String ctrtId; 			//계약ID
	private String ctrtNm; 			//계약명
	private String orderId; 		//작업ID
	private String orderStat; 		//작업상태
	private String orderStatNm;		//작업상태명	
	private String orderDt; 		//작업일시
	private String inactDttm; 		//적용시작일
	private String actDttm; 		//적용종료일
	private String svcTelNo;		//서비스번호
	private String orderTp;			//오더유형
	private String orderTpNm;		//오더유형명

	private String rateStrtDt;		//과금개시일		
	private String rateEndDt;		//과금종료일	
	private String openDd;			//개통일
	private String termDt;			//해지일
	private String ctrtCl;			//계약구분
	private String rcptId;			//접수ID
	private String custId;			//고객ID
	private String custNm;			//고객명	
	private String custRel;			//고객관계
	private String appnt;			//신청자
	private String pymAcntId;		//납부번호
	private String acntCustRel;		//납부자관계
	private String custClDcAplyYn;	//신분할인적용여부
	private String prodCmpsId;		//상품구성번호
	private String prodCd;			//상품코드
	private String prodNm;			//상품명	
	private String prodGrp;			//상품그룹코드
	
	private String instlZipCd;		//설치우편번호
	private String instlBaseAddr;	//설치기본주소
	private String instlAddrDtl;	//설치상세주소
	private String instlAgnt;		//설치대리점
	private String salesUsrId;		//영업대표
	private String sales_orgId;		//매출부서
	private String ctrtStat;		//계약상태

	private String ctrtChgResnCd;	//계약변경사유
	private String susHopeDd;		//일시정지희망일
	private String mmtSusHopeDd;	//일시정지 종료일
	private String mmtSusCd;		//일시정지사유
	private String termHopeDt;		//해지희망일
	private String defResnCd;		//해지희망사유코드
	private String svcStrtDd;		//서비스개시일
	private String svcStrtHopeDt;	//서비스 개시희망일
	private String joinId;			//결합번호
	private String promYn;			//약정여부
	private String promId;			//약정ID
	
	private String orgId;			//조직ID
	private String usrId;			//사용자ID
	private String serviceId;		//서비스ID
	private String rcvTransCtrtId;	//양수계약번호
	private String sndTransCtrtId;	//양도계약번호
	private String transCtrtYn;		//양도여부
	private String extId;			//확장ID
	private String npYn;			//번호이동 여부
	private String befNp;			//변경전 사업자
	private String orgNp;			//번부사업자
	private String firstOrgId;		//최초유통망번호
	private String firstUsrId;		//최초유통망직원번호
	private String remark;
	private String ifSucYn;			//인터페이스결과
	private String regrId;
	private String regDate;
	private String chgrId;
	private String chgDate;
	private String soNm;
	private String lngTyp;
	private String ctrtStatNm;		//계약상태명
	private String orgNm;			//조직명
	private String usrNm;
	//그래프
	private String statCnt01;
	private String statCnt02;
	private String statCnt03;
	private String statCnt04;
	private String statCntTotal;
	//검색
	private String searchStatDt;
	private String searchEndDt;
	private String condCustId;
	private String condCustNm;

	private String searchSoId;
	
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getCtrtNm() {
		return ctrtNm;
	}
	public void setCtrtNm(String ctrtNm) {
		this.ctrtNm = ctrtNm;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderStat() {
		return orderStat;
	}
	public void setOrderStat(String orderStat) {
		this.orderStat = orderStat;
	}
	public String getOrderStatNm() {
		return orderStatNm;
	}
	public void setOrderStatNm(String orderStatNm) {
		this.orderStatNm = orderStatNm;
	}
	public String getOrderDt() {
		return orderDt;
	}
	public void setOrderDt(String orderDt) {
		this.orderDt = orderDt;
	}
	public String getInactDttm() {
		return inactDttm;
	}
	public void setInactDttm(String inactDttm) {
		this.inactDttm = inactDttm;
	}
	public String getActDttm() {
		return actDttm;
	}
	public void setActDttm(String actDttm) {
		this.actDttm = actDttm;
	}
	public String getSvcTelNo() {
		return svcTelNo;
	}
	public void setSvcTelNo(String svcTelNo) {
		this.svcTelNo = svcTelNo;
	}
	public String getOrderTp() {
		return orderTp;
	}
	public void setOrderTp(String orderTp) {
		this.orderTp = orderTp;
	}
	public String getOrderTpNm() {
		return orderTpNm;
	}
	public void setOrderTpNm(String orderTpNm) {
		this.orderTpNm = orderTpNm;
	}
	public String getRateStrtDt() {
		return rateStrtDt;
	}
	public void setRateStrtDt(String rateStrtDt) {
		this.rateStrtDt = rateStrtDt;
	}
	public String getRateEndDt() {
		return rateEndDt;
	}
	public void setRateEndDt(String rateEndDt) {
		this.rateEndDt = rateEndDt;
	}
	public String getOpenDd() {
		return openDd;
	}
	public void setOpenDd(String openDd) {
		this.openDd = openDd;
	}
	public String getTermDt() {
		return termDt;
	}
	public void setTermDt(String termDt) {
		this.termDt = termDt;
	}
	public String getCtrtCl() {
		return ctrtCl;
	}
	public void setCtrtCl(String ctrtCl) {
		this.ctrtCl = ctrtCl;
	}
	public String getRcptId() {
		return rcptId;
	}
	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getCustRel() {
		return custRel;
	}
	public void setCustRel(String custRel) {
		this.custRel = custRel;
	}
	public String getAppnt() {
		return appnt;
	}
	public void setAppnt(String appnt) {
		this.appnt = appnt;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getAcntCustRel() {
		return acntCustRel;
	}
	public void setAcntCustRel(String acntCustRel) {
		this.acntCustRel = acntCustRel;
	}
	public String getCustClDcAplyYn() {
		return custClDcAplyYn;
	}
	public void setCustClDcAplyYn(String custClDcAplyYn) {
		this.custClDcAplyYn = custClDcAplyYn;
	}
	public String getProdCmpsId() {
		return prodCmpsId;
	}
	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getProdGrp() {
		return prodGrp;
	}
	public void setProdGrp(String prodGrp) {
		this.prodGrp = prodGrp;
	}
	public String getInstlZipCd() {
		return instlZipCd;
	}
	public void setInstlZipCd(String instlZipCd) {
		this.instlZipCd = instlZipCd;
	}
	public String getInstlBaseAddr() {
		return instlBaseAddr;
	}
	public void setInstlBaseAddr(String instlBaseAddr) {
		this.instlBaseAddr = instlBaseAddr;
	}
	public String getInstlAddrDtl() {
		return instlAddrDtl;
	}
	public void setInstlAddrDtl(String instlAddrDtl) {
		this.instlAddrDtl = instlAddrDtl;
	}
	public String getInstlAgnt() {
		return instlAgnt;
	}
	public void setInstlAgnt(String instlAgnt) {
		this.instlAgnt = instlAgnt;
	}
	public String getSalesUsrId() {
		return salesUsrId;
	}
	public void setSalesUsrId(String salesUsrId) {
		this.salesUsrId = salesUsrId;
	}
	public String getSales_orgId() {
		return sales_orgId;
	}
	public void setSales_orgId(String sales_orgId) {
		this.sales_orgId = sales_orgId;
	}
	public String getCtrtStat() {
		return ctrtStat;
	}
	public void setCtrtStat(String ctrtStat) {
		this.ctrtStat = ctrtStat;
	}
	public String getCtrtChgResnCd() {
		return ctrtChgResnCd;
	}
	public void setCtrtChgResnCd(String ctrtChgResnCd) {
		this.ctrtChgResnCd = ctrtChgResnCd;
	}
	public String getSusHopeDd() {
		return susHopeDd;
	}
	public void setSusHopeDd(String susHopeDd) {
		this.susHopeDd = susHopeDd;
	}
	public String getMmtSusHopeDd() {
		return mmtSusHopeDd;
	}
	public void setMmtSusHopeDd(String mmtSusHopeDd) {
		this.mmtSusHopeDd = mmtSusHopeDd;
	}
	public String getMmtSusCd() {
		return mmtSusCd;
	}
	public void setMmtSusCd(String mmtSusCd) {
		this.mmtSusCd = mmtSusCd;
	}
	public String getTermHopeDt() {
		return termHopeDt;
	}
	public void setTermHopeDt(String termHopeDt) {
		this.termHopeDt = termHopeDt;
	}
	public String getDefResnCd() {
		return defResnCd;
	}
	public void setDefResnCd(String defResnCd) {
		this.defResnCd = defResnCd;
	}
	public String getSvcStrtDd() {
		return svcStrtDd;
	}
	public void setSvcStrtDd(String svcStrtDd) {
		this.svcStrtDd = svcStrtDd;
	}
	public String getSvcStrtHopeDt() {
		return svcStrtHopeDt;
	}
	public void setSvcStrtHopeDt(String svcStrtHopeDt) {
		this.svcStrtHopeDt = svcStrtHopeDt;
	}
	public String getJoinId() {
		return joinId;
	}
	public void setJoinId(String joinId) {
		this.joinId = joinId;
	}
	public String getPromYn() {
		return promYn;
	}
	public void setPromYn(String promYn) {
		this.promYn = promYn;
	}
	public String getPromId() {
		return promId;
	}
	public void setPromId(String promId) {
		this.promId = promId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getRcvTransCtrtId() {
		return rcvTransCtrtId;
	}
	public void setRcvTransCtrtId(String rcvTransCtrtId) {
		this.rcvTransCtrtId = rcvTransCtrtId;
	}
	public String getSndTransCtrtId() {
		return sndTransCtrtId;
	}
	public void setSndTransCtrtId(String sndTransCtrtId) {
		this.sndTransCtrtId = sndTransCtrtId;
	}
	public String getTransCtrtYn() {
		return transCtrtYn;
	}
	public void setTransCtrtYn(String transCtrtYn) {
		this.transCtrtYn = transCtrtYn;
	}
	public String getExtId() {
		return extId;
	}
	public void setExtId(String extId) {
		this.extId = extId;
	}
	public String getNpYn() {
		return npYn;
	}
	public void setNpYn(String npYn) {
		this.npYn = npYn;
	}
	public String getBefNp() {
		return befNp;
	}
	public void setBefNp(String befNp) {
		this.befNp = befNp;
	}
	public String getOrgNp() {
		return orgNp;
	}
	public void setOrgNp(String orgNp) {
		this.orgNp = orgNp;
	}
	public String getFirstOrgId() {
		return firstOrgId;
	}
	public void setFirstOrgId(String firstOrgId) {
		this.firstOrgId = firstOrgId;
	}
	public String getFirstUsrId() {
		return firstUsrId;
	}
	public void setFirstUsrId(String firstUsrId) {
		this.firstUsrId = firstUsrId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIfSucYn() {
		return ifSucYn;
	}
	public void setIfSucYn(String ifSucYn) {
		this.ifSucYn = ifSucYn;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public String getChgDate() {
		return chgDate;
	}
	public void setChgDate(String chgDate) {
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
	public String getCtrtStatNm() {
		return ctrtStatNm;
	}
	public void setCtrtStatNm(String ctrtStatNm) {
		this.ctrtStatNm = ctrtStatNm;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
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
	public String getCondCustId() {
		return condCustId;
	}
	public void setCondCustId(String condCustId) {
		this.condCustId = condCustId;
	}
	public String getCondCustNm() {
		return condCustNm;
	}
	public void setCondCustNm(String condCustNm) {
		this.condCustNm = condCustNm;
	}	
	public String getStatCnt01() {
		return statCnt01;
	}
	public void setStatCnt01(String statCnt01) {
		this.statCnt01 = statCnt01;
	}
	public String getStatCnt02() {
		return statCnt02;
	}
	public void setStatCnt02(String statCnt02) {
		this.statCnt02 = statCnt02;
	}
	public String getStatCnt03() {
		return statCnt03;
	}
	public void setStatCnt03(String statCnt03) {
		this.statCnt03 = statCnt03;
	}
	public String getStatCnt04() {
		return statCnt04;
	}
	public void setStatCnt04(String statCnt04) {
		this.statCnt04 = statCnt04;
	}
	public String getStatCntTotal() {
		return statCntTotal;
	}
	public void setStatCntTotal(String statCntTotal) {
		this.statCntTotal = statCntTotal;
	}
	public String getSearchSoId() {
		return searchSoId;
	}
	public void setSearchSoId(String searchSoId) {
		this.searchSoId = searchSoId;
	}	
}
