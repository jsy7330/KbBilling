package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class OrderRequestInfoVO implements Serializable,CommonVO{
	private static final long serialVersionUID = 7596387923860647936L;
	
	/**
	 * 사업ID(필수)
	 */
	private String soId;
	/**
	 * 고객ID(필수)
	 */
	private String custId;
	
	/**
	 * 계약ID(필수)
	 *  - 신규계약의 경우 "0000000000" 
	 */
	private String ctrtId;
	
	/**
	 * 접수ID
	 *  - 저장 : 미존재시 접수 내역 자동 생성(선택)
	 *  - 취소, 완료 : 필수
	 */
	private String rcptId;
	/**
	 * 오더ID
	 *  - 저장 : 미존재
	 *  - 취소, 완료 : 필수
	 */
	private String orderId;
	
	/**
	 * 오더코드(필수)
	 */
	private String orderCd;
	
	/**
	 * 오더유형(필수)
	 */
	private String orderTp;
	
	/**
	 * 오더상태
	 * 	- 저장 : 미존재
	 *  - 취소, 완료 : 필수
	 */
	private String orderStat;
	
	/**
	 * 오더상태명
	 * 	- 저장 : 미존재
	 *  - 취소, 완료 : 필수
	 */
	private String orderStatNm;
	
	/**
	 * 상담대분류(필수값)
	 */
	private String cnslMstCl;
	
	/**
	 * 상담중분류(필수)
	 */
	private String cnslMidCl;
	
	/**
	 * 상담소분류(필수)
	 */
	private String cnslSlvCl;
	
	/**
	 * 기본서비스코드(필수)
	 */
	private String basicSvcCd;
	
	/**
	 * 고객유형(필수)
	 */
	private String custTp;
	
	/**
	 * 납부계정ID(선택)
	 */
	private String pymAcntId;
	
	/**
	 * 요청자ID(필수)
	 */
	private String reqUsrId;
	
	/**
	 * 요청조직(필수)
	 */
	private String reqOrgId;
	
	/**
	 * 요청내용(선택)
	 */
	private String reqDesc;
	
	/**
	 * 비고(선택)
	 */
	private String remark;
	private String ctrtNm;
	
	public String getCtrtNm() {
		return ctrtNm;
	}

	public void setCtrtNm(String ctrtNm) {
		this.ctrtNm = ctrtNm;
	}

	/**
	 * 추가 기본상품정보(선택)
	 * <li>상품그룹(Key : prodGrp, Object : String)</li>
	 * <li>상품코드(Key : prodCd, Object : String)</li>
	 * <li>상품명(Key : prodNm, Object : String)</li>
	 * <li>서비스코드(Key : svcCd, Object : String)</li>
	 * <li>서비스그룹(Key : svcGrp, Object : String)</li>
	 * <li>상품유형(Key : prodTyp, Object : String)</li>
	 * <li>상품구분(Key : basicProdFl, Object : String)</li>
	 * <li>일회성요금(Key : onetimeFee, Object : String)</li>
	 * <li>일회성요금협정할인요율(Key : onetimeFeeNegoRate, Object : String)</li>
	 * <li>일회성요금협정가(Key : onetimeFeeNegoAmt, Object : String)</li>
	 * <li>일회성요금분납개월수(Key : onetimeInstMonths, Object : String)</li>
	 * <li>월정액요금(Key : monthlyFee, Object : String)</li>
	 * <li>월정액엽정할인요율(Key : monthlyFeeNegoRate, Object : String)</li>
	 * <li>월정액협정가(Key : monthlyFeeNegoAmt, Object : String)</li>
	 */
	private List<Map<String,Object>> addBasicProdList;
	
	/**
	 * 추가 부가상품정보(선택)
	 * <li>상품그룹(Key : prodGrp, Object : String)</li>
	 * <li>상품코드(Key : prodCd, Object : String)</li>
	 * <li>상품명(Key : prodNm, Object : String)</li>
	 * <li>서비스코드(Key : svcCd, Object : String)</li>
	 * <li>서비스그룹(Key : svcGrp, Object : String)</li>
	 * <li>상품유형(Key : prodTyp, Object : String)</li>
	 * <li>상품수량(Key : prodCnt, Object : String)</li>
	 * <li>상품구분(Key : basicProdFl, Object : String)</li>
	 * <li>일회성요금(Key : onetimeFee, Object : String)</li>
	 * <li>일회성요금협정할인요율(Key : onetimeFeeNegoRate, Object : String)</li>
	 * <li>일회성요금협정가(Key : onetimeFeeNegoAmt, Object : String)</li>
	 * <li>일회성요금분납개월수(Key : onetimeInstMonths, Object : String)</li>
	 * <li>월정액요금(Key : monthlyFee, Object : String)</li>
	 * <li>월정액엽정할인요율(Key : monthlyFeeNegoRate, Object : String)</li>
	 * <li>월정액협정가(Key : monthlyFeeNegoAmt, Object : String)</li>
	 */
	private List<Map<String,Object>> addAddonProdList;
	
	/**
	 * 추가 장비상품정보(선택)
	 * <li>상품그룹(Key : prodGrp, Object : String)</li>
	 * <li>상품코드(Key : prodCd, Object : String)</li>
	 * <li>상품명(Key : prodNm, Object : String)</li>
	 * <li>서비스코드(Key : svcCd, Object : String)</li>
	 * <li>서비스그룹(Key : svcGrp, Object : String)</li>
	 * <li>상품유형(Key : prodTyp, Object : String)</li>
	 * <li>상품수량(Key : prodCnt, Object : String)</li>
	 * <li>상품구분(Key : basicProdFl, Object : String)</li>
	 * <li>일회성요금(Key : onetimeFee, Object : String)</li>
	 * <li>일회성요금협정할인요율(Key : onetimeFeeNegoRate, Object : String)</li>
	 * <li>일회성요금협정가(Key : onetimeFeeNegoAmt, Object : String)</li>
	 * <li>일회성요금분납개월수(Key : onetimeInstMonths, Object : String)</li>
	 * <li>월정액요금(Key : monthlyFee, Object : String)</li>
	 * <li>월정액엽정할인요율(Key : monthlyFeeNegoRate, Object : String)</li>
	 * <li>월정액협정가(Key : monthlyFeeNegoAmt, Object : String)</li>
	 */
	private List<Map<String,Object>> addDeviceProdList;
	
	
	/**
	 * 삭제 기본상품정보(선택)
	 * <li>상품구성ID(Key : prodCmpsId, Object : String)</li>
	 * <li>상품코드(Key : prodCd, Object : String)</li>
	 */
	private List<Map<String,Object>> delBasicProdList;
	/**
	 * 삭제 부가상품정보(선택)
	 * <li>상품구성ID(Key : prodCmpsId, Object : String)</li>
	 * <li>상품코드(Key : prodCd, Object : String)</li>
	 */
	private List<Map<String,Object>> delAddonProdList;
	/**
	 * 삭제 장비상품정보(선택)
	 * <li>상품구성ID(Key : prodCmpsId, Object : String)</li>
	 * <li>상품코드(Key : prodCd, Object : String)</li>
	 */
	private List<Map<String,Object>> delDeviceProdList;
	
	/**
	 * 변경서비스번호(선택)
	 */
	private String addSvcTelNo;
	
	/**
	 * 변경서비스번호사업자식별번호(선택)
	 */
	private String addSvcKind;
	
	/**
	 * 변경서비스국번(선택)
	 */
	private String addSvcExchangeNo;
	
	/**
	 * 변경서비스 끝자리번호(선택)
	 */
	private String addSvcNumber;
	
	/**
	 * 이전서비스번호(선택)
	 */
	private String delSvcTelNo;
	
	/**
	 * 이전서비스번호사업자식별번호(선택)
	 */
	private String delSvcKind;
	
	/**
	 * 이전서비스번호국번(선택)
	 */
	private String delSvcExchangeNo;
	
	/**
	 * 이전서비스번호끝자리(선택)
	 */
	private String delSvcNumber;
	
	/**
	 * 판매정책(선택) 
	 */
	private String promotionCd;
	
	/**
	 * 판매정책유형(선택)
	 */
	private String promotionTp;
	
	/**
	 * 분납개월수(선택)
	 */
	private String itllmtMonth;
	
	/**
	 * 변경사유(선택)
	 */
	private String chngResn;
	
	/**
	 * 변경사유상세(선택)
	 */
	private String chngDetailResn;
	
	/**
	 * 일시정지 시작희망일(선택)
	 */
	private String startHopeDt;
	
	/**
	 * 일시정지 종료희망일(선택)
	 */
	private String endHopeDt;
	
	/**
	 * 해지요청일
	 */
	private String termHopeDt;
	
	/**
	 * 명의변경 양수자고객ID(선택)
	 */
	private String targetCustId;
	
	/**
	 * 실사용자명(선택)
	 */
	private String realCustNm;
	
	/**
	 * 실사용자연락처(선택)
	 */
	private String realCustTelNo;
	
	/**
	 * 실사용자관계(선택)
	 */
	private String realCustRel;
	
	/**
	 * 설치주소(선택)
	 */
	private String instZipCode;
	
	/**
	 * 설치주소 기본주소(선택)
	 */
	private String instBaseAddr;
	
	/**
	 * 설치주소 상세주소(선택)
	 */
	private String instAddrDtl;
	
	/**
	 * 설치주소 도시
	 */
	private String instCity;
	
	/**
	 * 설치주소 주
	 */
	private String instState;
	
	/**
	 * 약정개월수(선택)
	 */
	private String promCnt;
	
	/**
	 * 공저률(선택)
	 */
	private String procRate;
	
	/**
	 * 요청일(미입력)
	 */
	private String orderReqDt;
	
	/**
	 * 요청일시(미입력)
	 */
	private String orderReqDttm;
	
	/**
	 * 요청일시(미입력)
	 */
	private Date orderReqDate;
	
	/**
	 * 언어코드(미입력)
	 */
	private String lng;

	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}

	/**
	 * @param soId the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}

	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @return the ctrtId
	 */
	public String getCtrtId() {
		return ctrtId;
	}

	/**
	 * @param ctrtId the ctrtId to set
	 */
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}

	/**
	 * @return the rcptId
	 */
	public String getRcptId() {
		return rcptId;
	}

	/**
	 * @param rcptId the rcptId to set
	 */
	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the orderCd
	 */
	public String getOrderCd() {
		return orderCd;
	}

	/**
	 * @param orderCd the orderCd to set
	 */
	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
	}

	/**
	 * @return the orderTp
	 */
	public String getOrderTp() {
		return orderTp;
	}

	/**
	 * @param orderTp the orderTp to set
	 */
	public void setOrderTp(String orderTp) {
		this.orderTp = orderTp;
	}

	/**
	 * @return the orderStat
	 */
	public String getOrderStat() {
		return orderStat;
	}

	/**
	 * @param orderStat the orderStat to set
	 */
	public void setOrderStat(String orderStat) {
		this.orderStat = orderStat;
	}

	/**
	 * @return the orderStatNm
	 */
	public String getOrderStatNm() {
		return orderStatNm;
	}

	/**
	 * @param orderStatNm the orderStatNm to set
	 */
	public void setOrderStatNm(String orderStatNm) {
		this.orderStatNm = orderStatNm;
	}

	/**
	 * @return the cnslMstCl
	 */
	public String getCnslMstCl() {
		return cnslMstCl;
	}

	/**
	 * @param cnslMstCl the cnslMstCl to set
	 */
	public void setCnslMstCl(String cnslMstCl) {
		this.cnslMstCl = cnslMstCl;
	}

	/**
	 * @return the cnslMidCl
	 */
	public String getCnslMidCl() {
		return cnslMidCl;
	}

	/**
	 * @param cnslMidCl the cnslMidCl to set
	 */
	public void setCnslMidCl(String cnslMidCl) {
		this.cnslMidCl = cnslMidCl;
	}

	/**
	 * @return the cnslSlvCl
	 */
	public String getCnslSlvCl() {
		return cnslSlvCl;
	}

	/**
	 * @param cnslSlvCl the cnslSlvCl to set
	 */
	public void setCnslSlvCl(String cnslSlvCl) {
		this.cnslSlvCl = cnslSlvCl;
	}

	/**
	 * @return the basicSvcCd
	 */
	public String getBasicSvcCd() {
		return basicSvcCd;
	}

	/**
	 * @param basicSvcCd the basicSvcCd to set
	 */
	public void setBasicSvcCd(String basicSvcCd) {
		this.basicSvcCd = basicSvcCd;
	}

	/**
	 * @return the custTp
	 */
	public String getCustTp() {
		return custTp;
	}

	/**
	 * @param custTp the custTp to set
	 */
	public void setCustTp(String custTp) {
		this.custTp = custTp;
	}

	/**
	 * @return the pymAcntId
	 */
	public String getPymAcntId() {
		return pymAcntId;
	}

	/**
	 * @param pymAcntId the pymAcntId to set
	 */
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	/**
	 * @return the reqUsrId
	 */
	public String getReqUsrId() {
		return reqUsrId;
	}

	/**
	 * @param reqUsrId the reqUsrId to set
	 */
	public void setReqUsrId(String reqUsrId) {
		this.reqUsrId = reqUsrId;
	}

	/**
	 * @return the reqOrgId
	 */
	public String getReqOrgId() {
		return reqOrgId;
	}

	/**
	 * @param reqOrgId the reqOrgId to set
	 */
	public void setReqOrgId(String reqOrgId) {
		this.reqOrgId = reqOrgId;
	}

	/**
	 * @return the reqDesc
	 */
	public String getReqDesc() {
		return reqDesc;
	}

	/**
	 * @param reqDesc the reqDesc to set
	 */
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the addBasicProdList
	 */
	public List<Map<String, Object>> getAddBasicProdList() {
		return addBasicProdList;
	}

	/**
	 * @param addBasicProdList the addBasicProdList to set
	 */
	public void setAddBasicProdList(List<Map<String, Object>> addBasicProdList) {
		this.addBasicProdList = addBasicProdList;
	}

	/**
	 * @return the addAddonProdList
	 */
	public List<Map<String, Object>> getAddAddonProdList() {
		return addAddonProdList;
	}

	/**
	 * @param addAddonProdList the addAddonProdList to set
	 */
	public void setAddAddonProdList(List<Map<String, Object>> addAddonProdList) {
		this.addAddonProdList = addAddonProdList;
	}

	/**
	 * @return the addDeviceProdList
	 */
	public List<Map<String, Object>> getAddDeviceProdList() {
		return addDeviceProdList;
	}

	/**
	 * @param addDeviceProdList the addDeviceProdList to set
	 */
	public void setAddDeviceProdList(List<Map<String, Object>> addDeviceProdList) {
		this.addDeviceProdList = addDeviceProdList;
	}

	/**
	 * @return the delBasicProdList
	 */
	public List<Map<String, Object>> getDelBasicProdList() {
		return delBasicProdList;
	}

	/**
	 * @param delBasicProdList the delBasicProdList to set
	 */
	public void setDelBasicProdList(List<Map<String, Object>> delBasicProdList) {
		this.delBasicProdList = delBasicProdList;
	}

	/**
	 * @return the delAddonProdList
	 */
	public List<Map<String, Object>> getDelAddonProdList() {
		return delAddonProdList;
	}

	/**
	 * @param delAddonProdList the delAddonProdList to set
	 */
	public void setDelAddonProdList(List<Map<String, Object>> delAddonProdList) {
		this.delAddonProdList = delAddonProdList;
	}

	/**
	 * @return the delDeviceProdList
	 */
	public List<Map<String, Object>> getDelDeviceProdList() {
		return delDeviceProdList;
	}

	/**
	 * @param delDeviceProdList the delDeviceProdList to set
	 */
	public void setDelDeviceProdList(List<Map<String, Object>> delDeviceProdList) {
		this.delDeviceProdList = delDeviceProdList;
	}

	/**
	 * @return the addSvcTelNo
	 */
	public String getAddSvcTelNo() {
		return addSvcTelNo;
	}

	/**
	 * @param addSvcTelNo the addSvcTelNo to set
	 */
	public void setAddSvcTelNo(String addSvcTelNo) {
		this.addSvcTelNo = addSvcTelNo;
	}

	/**
	 * @return the addSvcKind
	 */
	public String getAddSvcKind() {
		return addSvcKind;
	}

	/**
	 * @param addSvcKind the addSvcKind to set
	 */
	public void setAddSvcKind(String addSvcKind) {
		this.addSvcKind = addSvcKind;
	}

	/**
	 * @return the addSvcExchangeNo
	 */
	public String getAddSvcExchangeNo() {
		return addSvcExchangeNo;
	}

	/**
	 * @param addSvcExchangeNo the addSvcExchangeNo to set
	 */
	public void setAddSvcExchangeNo(String addSvcExchangeNo) {
		this.addSvcExchangeNo = addSvcExchangeNo;
	}

	/**
	 * @return the addSvcNumber
	 */
	public String getAddSvcNumber() {
		return addSvcNumber;
	}

	/**
	 * @param addSvcNumber the addSvcNumber to set
	 */
	public void setAddSvcNumber(String addSvcNumber) {
		this.addSvcNumber = addSvcNumber;
	}

	/**
	 * @return the delSvcTelNo
	 */
	public String getDelSvcTelNo() {
		return delSvcTelNo;
	}

	/**
	 * @param delSvcTelNo the delSvcTelNo to set
	 */
	public void setDelSvcTelNo(String delSvcTelNo) {
		this.delSvcTelNo = delSvcTelNo;
	}

	/**
	 * @return the delSvcKind
	 */
	public String getDelSvcKind() {
		return delSvcKind;
	}

	/**
	 * @param delSvcKind the delSvcKind to set
	 */
	public void setDelSvcKind(String delSvcKind) {
		this.delSvcKind = delSvcKind;
	}

	/**
	 * @return the delSvcExchangeNo
	 */
	public String getDelSvcExchangeNo() {
		return delSvcExchangeNo;
	}

	/**
	 * @param delSvcExchangeNo the delSvcExchangeNo to set
	 */
	public void setDelSvcExchangeNo(String delSvcExchangeNo) {
		this.delSvcExchangeNo = delSvcExchangeNo;
	}

	/**
	 * @return the delSvcNumber
	 */
	public String getDelSvcNumber() {
		return delSvcNumber;
	}

	/**
	 * @param delSvcNumber the delSvcNumber to set
	 */
	public void setDelSvcNumber(String delSvcNumber) {
		this.delSvcNumber = delSvcNumber;
	}

	/**
	 * @return the promotionCd
	 */
	public String getPromotionCd() {
		return promotionCd;
	}

	/**
	 * @param promotionCd the promotionCd to set
	 */
	public void setPromotionCd(String promotionCd) {
		this.promotionCd = promotionCd;
	}

	/**
	 * @return the promotionTp
	 */
	public String getPromotionTp() {
		return promotionTp;
	}

	/**
	 * @param promotionTp the promotionTp to set
	 */
	public void setPromotionTp(String promotionTp) {
		this.promotionTp = promotionTp;
	}

	/**
	 * @return the itllmtMonth
	 */
	public String getItllmtMonth() {
		return itllmtMonth;
	}

	/**
	 * @param itllmtMonth the itllmtMonth to set
	 */
	public void setItllmtMonth(String itllmtMonth) {
		this.itllmtMonth = itllmtMonth;
	}

	/**
	 * @return the chngResn
	 */
	public String getChngResn() {
		return chngResn;
	}

	/**
	 * @param chngResn the chngResn to set
	 */
	public void setChngResn(String chngResn) {
		this.chngResn = chngResn;
	}

	/**
	 * @return the chngDetailResn
	 */
	public String getChngDetailResn() {
		return chngDetailResn;
	}

	/**
	 * @param chngDetailResn the chngDetailResn to set
	 */
	public void setChngDetailResn(String chngDetailResn) {
		this.chngDetailResn = chngDetailResn;
	}

	/**
	 * @return the startHopeDt
	 */
	public String getStartHopeDt() {
		return startHopeDt;
	}

	/**
	 * @param startHopeDt the startHopeDt to set
	 */
	public void setStartHopeDt(String startHopeDt) {
		this.startHopeDt = startHopeDt;
	}

	/**
	 * @return the endHopeDt
	 */
	public String getEndHopeDt() {
		return endHopeDt;
	}

	/**
	 * @param endHopeDt the endHopeDt to set
	 */
	public void setEndHopeDt(String endHopeDt) {
		this.endHopeDt = endHopeDt;
	}

	/**
	 * @return the termHope
	 */
	public String getTermHopeDt() {
		return termHopeDt;
	}

	/**
	 * @param termHope the termHope to set
	 */
	public void setTermHopeDt(String termHopeDt) {
		this.termHopeDt = termHopeDt;
	}

	/**
	 * @return the targetCustId
	 */
	public String getTargetCustId() {
		return targetCustId;
	}

	/**
	 * @param targetCustId the targetCustId to set
	 */
	public void setTargetCustId(String targetCustId) {
		this.targetCustId = targetCustId;
	}

	/**
	 * @return the realCustNm
	 */
	public String getRealCustNm() {
		return realCustNm;
	}

	/**
	 * @param realCustNm the realCustNm to set
	 */
	public void setRealCustNm(String realCustNm) {
		this.realCustNm = realCustNm;
	}

	/**
	 * @return the realCustTelNo
	 */
	public String getRealCustTelNo() {
		return realCustTelNo;
	}

	/**
	 * @param realCustTelNo the realCustTelNo to set
	 */
	public void setRealCustTelNo(String realCustTelNo) {
		this.realCustTelNo = realCustTelNo;
	}

	/**
	 * @return the realCustRel
	 */
	public String getRealCustRel() {
		return realCustRel;
	}

	/**
	 * @param realCustRel the realCustRel to set
	 */
	public void setRealCustRel(String realCustRel) {
		this.realCustRel = realCustRel;
	}

	/**
	 * @return the instZipCode
	 */
	public String getInstZipCode() {
		return instZipCode;
	}

	/**
	 * @param instZipCode the instZipCode to set
	 */
	public void setInstZipCode(String instZipCode) {
		this.instZipCode = instZipCode;
	}

	/**
	 * @return the instBaseAddr
	 */
	public String getInstBaseAddr() {
		return instBaseAddr;
	}

	/**
	 * @param instBaseAddr the instBaseAddr to set
	 */
	public void setInstBaseAddr(String instBaseAddr) {
		this.instBaseAddr = instBaseAddr;
	}

	/**
	 * @return the instAddrDtl
	 */
	public String getInstAddrDtl() {
		return instAddrDtl;
	}

	/**
	 * @param instAddrDtl the instAddrDtl to set
	 */
	public void setInstAddrDtl(String instAddrDtl) {
		this.instAddrDtl = instAddrDtl;
	}

	/**
	 * @return the instCity
	 */
	public String getInstCity() {
		return instCity;
	}

	/**
	 * @param instCity the instCity to set
	 */
	public void setInstCity(String instCity) {
		this.instCity = instCity;
	}

	/**
	 * @return the instState
	 */
	public String getInstState() {
		return instState;
	}

	/**
	 * @param instState the instState to set
	 */
	public void setInstState(String instState) {
		this.instState = instState;
	}

	/**
	 * @return the promCnt
	 */
	public String getPromCnt() {
		return promCnt;
	}

	/**
	 * @param promCnt the promCnt to set
	 */
	public void setPromCnt(String promCnt) {
		this.promCnt = promCnt;
	}

	/**
	 * @return the procRate
	 */
	public String getProcRate() {
		return procRate;
	}

	/**
	 * @param procRate the procRate to set
	 */
	public void setProcRate(String procRate) {
		this.procRate = procRate;
	}

	/**
	 * @return the orderReqDt
	 */
	public String getOrderReqDt() {
		return orderReqDt;
	}

	/**
	 * @param orderReqDt the orderReqDt to set
	 */
	public void setOrderReqDt(String orderReqDt) {
		this.orderReqDt = orderReqDt;
	}

	/**
	 * @return the orderReqDttm
	 */
	public String getOrderReqDttm() {
		return orderReqDttm;
	}

	/**
	 * @param orderReqDttm the orderReqDttm to set
	 */
	public void setOrderReqDttm(String orderReqDttm) {
		this.orderReqDttm = orderReqDttm;
	}

	/**
	 * @return the orderReqDate
	 */
	public Date getOrderReqDate() {
		return orderReqDate;
	}

	/**
	 * @param orderReqDate the orderReqDate to set
	 */
	public void setOrderReqDate(Date orderReqDate) {
		this.orderReqDate = orderReqDate;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderRequestInfoVO [soId=");
		builder.append(soId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", ctrtId=");
		builder.append(ctrtId);
		builder.append(", rcptId=");
		builder.append(rcptId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", orderCd=");
		builder.append(orderCd);
		builder.append(", orderTp=");
		builder.append(orderTp);
		builder.append(", orderStat=");
		builder.append(orderStat);
		builder.append(", orderStatNm=");
		builder.append(orderStatNm);
		builder.append(", cnslMstCl=");
		builder.append(cnslMstCl);
		builder.append(", cnslMidCl=");
		builder.append(cnslMidCl);
		builder.append(", cnslSlvCl=");
		builder.append(cnslSlvCl);
		builder.append(", basicSvcCd=");
		builder.append(basicSvcCd);
		builder.append(", custTp=");
		builder.append(custTp);
		builder.append(", pymAcntId=");
		builder.append(pymAcntId);
		builder.append(", reqUsrId=");
		builder.append(reqUsrId);
		builder.append(", reqOrgId=");
		builder.append(reqOrgId);
		builder.append(", reqDesc=");
		builder.append(reqDesc);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", addBasicProdList=");
		builder.append(addBasicProdList);
		builder.append(", addAddonProdList=");
		builder.append(addAddonProdList);
		builder.append(", addDeviceProdList=");
		builder.append(addDeviceProdList);
		builder.append(", delBasicProdList=");
		builder.append(delBasicProdList);
		builder.append(", delAddonProdList=");
		builder.append(delAddonProdList);
		builder.append(", delDeviceProdList=");
		builder.append(delDeviceProdList);
		builder.append(", addSvcTelNo=");
		builder.append(addSvcTelNo);
		builder.append(", addSvcKind=");
		builder.append(addSvcKind);
		builder.append(", addSvcExchangeNo=");
		builder.append(addSvcExchangeNo);
		builder.append(", addSvcNumber=");
		builder.append(addSvcNumber);
		builder.append(", delSvcTelNo=");
		builder.append(delSvcTelNo);
		builder.append(", delSvcKind=");
		builder.append(delSvcKind);
		builder.append(", delSvcExchangeNo=");
		builder.append(delSvcExchangeNo);
		builder.append(", delSvcNumber=");
		builder.append(delSvcNumber);
		builder.append(", promotionCd=");
		builder.append(promotionCd);
		builder.append(", promotionTp=");
		builder.append(promotionTp);
		builder.append(", itllmtMonth=");
		builder.append(itllmtMonth);
		builder.append(", chngResn=");
		builder.append(chngResn);
		builder.append(", chngDetailResn=");
		builder.append(chngDetailResn);
		builder.append(", startHopeDt=");
		builder.append(startHopeDt);
		builder.append(", endHopeDt=");
		builder.append(endHopeDt);
		builder.append(", termHope=");
		builder.append(termHopeDt);
		builder.append(", targetCustId=");
		builder.append(targetCustId);
		builder.append(", realCustNm=");
		builder.append(realCustNm);
		builder.append(", realCustTelNo=");
		builder.append(realCustTelNo);
		builder.append(", realCustRel=");
		builder.append(realCustRel);
		builder.append(", instZipCode=");
		builder.append(instZipCode);
		builder.append(", instBaseAddr=");
		builder.append(instBaseAddr);
		builder.append(", instAddrDtl=");
		builder.append(instAddrDtl);
		builder.append(", instCity=");
		builder.append(instCity);
		builder.append(", instState=");
		builder.append(instState);
		builder.append(", promCnt=");
		builder.append(promCnt);
		builder.append(", procRate=");
		builder.append(procRate);
		builder.append(", orderReqDt=");
		builder.append(orderReqDt);
		builder.append(", orderReqDttm=");
		builder.append(orderReqDttm);
		builder.append(", orderReqDate=");
		builder.append(orderReqDate);
		builder.append(", lng=");
		builder.append(lng);
		builder.append("]");
		return builder.toString();
	}
}
