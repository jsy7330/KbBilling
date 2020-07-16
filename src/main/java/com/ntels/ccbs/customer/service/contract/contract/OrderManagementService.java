package com.ntels.ccbs.customer.service.contract.contract;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.customer.domain.contract.contract.CtrtPromVO;
import com.ntels.ccbs.customer.domain.contract.contract.CustInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderMastInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.PrecheckInfoVO;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountInfoVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;

/**
 * <PRE>
 * 1. ClassName: OrderManagementService
 * 2. FileName : OrderManagementService.java
 * 3. Package  : com.ntels.ccbs.customer.service.contract.contract
 * 4. Comment  : 오더 관리 서비스
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 7. 오후 7:44:46
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 7.    : 신규개발
 * </PRE>
 */
public interface OrderManagementService {

	/**
	 * <PRE>
	 * 1. MethodName: getOrderCommonInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 오더 공통 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 7. 오후 7:44:44
	 * </PRE>
	 *   @return OrderCommonVO 오더 공통 정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderCd 오더코드
	 *   @param rcptId 접수ID
	 *   @param lng 언어코드
	 *   @param today 일자
	 */
	OrderCommonVO getOrderCommonInfo(String soId
			, String custId
			, String ctrtId
			, String orderCd
			, String rcptId
			, String lng
			, String today);

	List<PrecheckInfoVO> savePrecheckInfo(OrderCommonVO orderCommonInfo, String usrId, String lng, String today);

	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntInfoList
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 고객의 납부 계정 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 9. 오후 5:20:08
	 * </PRE>
	 *   @return List<PaymentAccountInfoVO> 납부계정 VO
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param today 조회일자
	 *   @param lng 언어코드
	 */
	List<PaymentAccountInfoVO> getPymAcntInfoList(String soId
			, String custId
			, String today
			, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getBasicProdGrpList
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 기본상품그룹조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오전 10:11:12
	 * </PRE>
	 *   @return List<Map<String,Object>> 기본상품그룹정보
	 *   @param soId 사업ID
	 *   @param basicSvcCd 서비스코드
	 *   @param custTp 고객유형
	 *   @param today 조회일자
	 *   @param lng 언어코드 
	 */
	List<Map<String, Object>> getBasicProdGrpList(String soId,
	        String basicSvcCd, String custTp, String today, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getBasicProdList
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 기본상품리스트조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오전 11:03:05
	 * </PRE>
	 *   @return List<Map<String,Object>> 기본상품리스
	 *   @param soId 사업ID
	 *   @param basicSvcCd 서비스코드
	 *   @param basicProdGrp 상품그룹
	 *   @param custTp 고객유형
	 *   @param today 조회일자
	 *   @param lng 언어코드
	 */
	List<Map<String, Object>> getBasicProdList(String soId, String basicSvcCd,
	        String basicProdGrp, String custTp, String today, String lng);
	
	

	/**
	 * <PRE>
	 * 1. MethodName: getPenaltyListByBasicProdCd
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 기본상품의 가입가능한 약정코드리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 1:40:44
	 * </PRE>
	 *   @return List<CommonCodeVO> 약정코드리스트
	 *   @param soId 사업ID
	 *   @param basicProdGrp 상품그룹
	 *   @param basicProdCd 상품코드
	 *   @param today 조회일자
	 *   @param lng 언어코드
	 *   @return
	 */
	List<CommonCodeVO> getPenaltyListByBasicProdCd(String soId
			, String basicProdGrp
			, String basicProdCd
			, String today
			, String lng);
	
	/**
	 * <PRE>
	 * 1. MethodName: getDeviceProdList
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 기본 상품의 종속된 장비상품 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 3:38:55
	 * </PRE>
	 *   @return List<Map<String,Object>> 장비상품리스트
	 *   @param soId 사업ID
	 *   @param basicProdGrp 상품그룹
	 *   @param basicProdCd 기본상품코드
	 *   @param today 조회일자
	 *   @param lng 언어코드
	 */
	List<Map<String, Object>> getDeviceProdList(String soId,
	        String basicProdGrp, String basicProdCd, String today, String lng);
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getAddProdList
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 기본 상품의 종속된 부가상품 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 6:57:26
	 * </PRE>
	 *   @return List<Map<String,Object>> 부가상품리스트
	 *   @param soId 사업ID
	 *   @param basicProdGrp 상품그룹
	 *   @param basicProdCd 기본상품코드
	 *   @param today 조회일자
	 *   @param lng 언어코드
	 */
	List<Map<String, Object>> getAddProdList(String soId, String basicProdGrp,
	        String basicProdCd, String today, String lng);

	
	/**
	 * <PRE>
	 * 1. MethodName: getBasicProdRateInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 상품의 과금 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 14. 오후 1:18:09
	 * </PRE>
	 *   @return Map<String,Object> 과금정보
	 *   <li>과금정보(Key : RATE_ITM_TYP_CD, Object : String)</li>
	 *   @param soId 사업ID
	 *   @param basicProdCd 상품코드
	 *   @param rateItmTypCd 과금유형코드
	 *   @param today 조회일자
	 */
	Map<String, Object> getProdRateInfoByChargeType(String soId, String basicProdCd, String rateItmTypCd, String today);


	/**
	 * <PRE>
	 * 1. MethodName: getOrderMastInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 오더 마스터 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:26:55
	 * </PRE>
	 *   @return OrderMastInfoVO 오더마스터정보
	 *   @param orderCd 오더CD
	 *   @param lng 언어유형
	 */
	OrderMastInfoVO getOrderMastInfo(String orderCd, String lng, String soId);

	/**
	 * <PRE>
	 * 1. MethodName: getPrecheckInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 사전체크 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:27:58
	 * </PRE>
	 *   @return List<PrecheckInfoVO> 사전체크 결과
	 *   @param orderCommonInfo 오더공통정보
	 *   @param userId 사용자ID
	 *   @param lng 언어유형
	 *   @param today 조회일자
	 */
	List<PrecheckInfoVO> getPrecheckInfo(OrderCommonVO orderCommonInfo,
	        String userId, String lng, String today);

	/**
	 * <PRE>
	 * 1. MethodName: getOCtrtMastInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 오더 계약 원부 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:28:49
	 * </PRE>
	 *   @return OCtrtMastVO 오더계약원부정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 */
	OCtrtMastVO getOCtrtMastInfo(String soId, String custId, String ctrtId,
	        String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: getOCtrtMastExtInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 오더계약원부 확장정보
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:29:34
	 * </PRE>
	 *   @return OCtrtMastExtVO 오더 계약원부 확장정보
	 *   @param orderId 오더ID
	 *   @param extId 확장ID
	 */
	OCtrtMastExtVO getOCtrtMastExtInfo(String orderId, String extId);

	/**
	 * <PRE>
	 * 1. MethodName: getOProdCmpsInfoList
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 오더 상품 구성 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:30:50
	 * </PRE>
	 *   @return List<OProdCmpsVO> 오더 상품구성정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 */
	List<OProdCmpsVO> getOProdCmpsInfoList(String soId, String custId,
	        String ctrtId, String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: getOProdCmpsExtInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 오더 상품 구성 확장 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:31:27
	 * </PRE>
	 *   @return OProdCmpsExtVO 오더 상품구성 확장정보
	 *   @param orderId 오더ID
	 *   @param extId 확장ID
	 */
	OProdCmpsExtVO getOProdCmpsExtInfo(String orderId, String extId);

	/**
	 * <PRE>
	 * 1. MethodName: getOSvcCmpsInfoList
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 오더 서비스 구성 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:31:57
	 * </PRE>
	 *   @return List<OSvcCmpsVO> 오더 서비스 구성 정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 */
	List<OSvcCmpsVO> getOSvcCmpsInfoList(String soId, String custId,
	        String ctrtId, String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: getOSvcCmpsExtInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 오더 서비스 구성 확장 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:32:31
	 * </PRE>
	 *   @return OSvcCmpsExtVO 오덧 서비스 구성 확장 정보
	 *   @param orderId 오더ID
	 *   @param extId 확장ID
	 */
	OSvcCmpsExtVO getOSvcCmpsExtInfo(String orderId, String extId);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 이용중인 계약 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:33:00
	 * </PRE>
	 *   @return Map<String,Object> 계약정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param lng 언어코드
	 *   @return
	 */
	Map<String, Object> getCtrtInfo(String soId, String custId, String ctrtId, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntListForChange
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 납부자 변경을 위한 납부 계정리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 28. 오전 7:43:51
	 * </PRE>
	 *   @return List<PaymentAccountInfoVO> 납부계정정보
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param searchTp 조회유형
	 *   @param searchKey 조회키
	 *   @param lng 언어코드
	 */
	List<PaymentAccountInfoVO> getPymAcntListForChange(String soId, String ctrtId, String searchTp, String searchKey, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtHist
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 계약 상세 정보 조회를 위한 계약의 각 이력 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 4. 오후 2:02:20
	 * </PRE>
	 *   @return Map<String,Object> 각 이력 정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param lng 언어코드
	 *   @return
	 */
	Map<String, Object> getCtrtHist(String soId, String custId, String ctrtId, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtSusHist
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 계약의 일시정지 이력 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 5. 오후 4:40:13
	 * </PRE>
	 *   @return List<Map<String, Object>> 일시정지 이력 정보 
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param lng 언어정보
	 */
	List<Map<String, Object>> getCtrtSusHist(String soId, String custId, String ctrtId, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtPromInfo
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 계약 약정 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 14. 오후 2:56:52
	 * </PRE>
	 *   @return CtrtPromVO
	 *   @param promId
	 *   @param ctrtId
	 *   @return
	 */
	CtrtPromVO getCtrtPromInfo(String promId, String ctrtId);

	/**
	 * <PRE>
	 * 1. MethodName: getProdListTobeChange
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 기본상품 변경의 변경 대상 상품 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 27. 오후 5:29:32
	 * </PRE>
	 *   @return Map<String,Object> 변경상품리스트
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param basicProdGrp 변경 기본 상품 그룹
	 *   @param basicProdCd 변경 기본 상품 코드
	 *   @param today 조회일
	 *   @param lng 언어코드
	 */
	Map<String, Object> getProdListTobeChange(String soId, String custId, String ctrtId,
			String basicProdGrp, String basicProdCd, String today, String lng);
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getCustTp
	 * 2. ClassName : OrderManagementService
	 * 3. Comment   : 고객유형조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 11. 오후 5:40:56
	 * </PRE>
	 *   @return String 고객유형
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 */
	String getCustTp(String soId, String custId);

	OrderInfoVO getBasicProdList2(String soId, String prodCd, String today, String lng);	
}
