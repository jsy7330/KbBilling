package com.ntels.ccbs.customer.mapper.contract.contract;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.contract.contract.CtrtPromVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;



/**
 * <PRE>
 * 1. ClassName: OrderProcessMapper
 * 2. FileName : OrderProcessMapper.java
 * 3. Package  : com.ntels.ccbs.customer.mapper.contract.contract
 * 4. Comment  : 오더 프로세스 Mapper
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 23. 오후 3:16:44
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 23.    : 신규개발
 * </PRE>
 */
@Component
public interface OrderProcessMapper {
	/**
	 * <PRE>
	 * 1. MethodName: getPreProcessOrderCnt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 계약의 진행중인 오더 수 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:40:00
	 * </PRE>
	 *   @return int 진행 오더 수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 */
	int getPreProcessOrderCnt(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId);

	/**
	 * <PRE>
	 * 1. MethodName: getPrecheckFailCnt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 전산 심사 실패 수 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:41:04
	 * </PRE>
	 *   @return int 전산심사 실패 수
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param rcptId 접수ID
	 *   @param orderCd 오더코드
	 *   @param basicSvcCd 서비스코드
	 *   @param custTp 고객유형
	 *   @return
	 */
	int getPrecheckFailCnt(@Param("soId")String soId,
			@Param("custId")String custId,
			@Param("rcptId")String rcptId,
	        @Param("orderCd")String orderCd,
	        @Param("basicSvcCd")String basicSvcCd,
	        @Param("custTp")String custTp);

	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntCnt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 납부계정 수 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:41:46
	 * </PRE>
	 *   @return int 납부계정수
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param pymAcntId 납부계정ID
	 *   @return
	 */
	int getPymAcntCnt(@Param("soId")String soId, 
			@Param("custId")String custId, 
			@Param("pymAcntId")String pymAcntId);

	/**
	 * <PRE>
	 * 1. MethodName: getBasicProdCnt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 상품정보의 기본상품정보 수 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:42:32
	 * </PRE>
	 *   @return int 기본상품수
	 *   @param soId 사업ID
	 *   @param basicProdGrp 상품그룹
	 *   @param basicSvcCd 서비스코드
	 *   @param basicProdCd 상품코드
	 *   @param today 조회일자
	 *   @return
	 */
	int getBasicProdCnt(@Param("soId")String soId,
			@Param("basicProdGrp")String basicProdGrp,
			@Param("basicSvcCd")String basicSvcCd,
			@Param("basicProdCd")String basicProdCd,
	        @Param("today")String today);

	/**
	 * <PRE>
	 * 1. MethodName: getRcptReqCustInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 신청자정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:43:10
	 * </PRE>
	 *   @return Map<String,Object> 신청자정보
	 *   @param soId 사업ID
	 *   @param rcptId 접수ID
	 */
	Map<String, Object> getRcptReqCustInfo(@Param("soId")String soId, @Param("rcptId")String rcptId);

	/**
	 * <PRE>
	 * 1. MethodName: insertOrderMast
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더마스터 정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:44:19
	 * </PRE>
	 *   @return int 오더마스터 추가수
	 *   @param octrtMast 오더마스터 정보
	 *   @return
	 */
	int insertOrderMast(@Param("octrtMast")OCtrtMastVO octrtMast);

	/**
	 * <PRE>
	 * 1. MethodName: insertOrderProdCmps
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더상품정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:44:48
	 * </PRE>
	 *   @return int 오더상품정보 추가수
	 *   @param oprodBasicCmpsInfo 오더상품정보
	 *   @return
	 */
	int insertOrderProdCmps(@Param("oprodBasicCmpsInfo")OProdCmpsVO oprodBasicCmpsInfo);

	/**
	 * <PRE>
	 * 1. MethodName: insertOrderSvcCmps
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더서비스정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:45:09
	 * </PRE>
	 *   @return int 오더서비스정보 추가수
	 *   @param osvcBasicCmpsInfo 오더서비스정보
	 */
	int insertOrderSvcCmps(@Param("osvcBasicCmpsInfo")OSvcCmpsVO osvcBasicCmpsInfo);

	/**
	 * <PRE>
	 * 1. MethodName: insertOrderSvcCmpsExt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더서비스확장정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:45:28
	 * </PRE>
	 *   @return int 오더서비스확장정보
	 *   @param osvcCmpsExtInfo 오더서비스확장정보
	 */
	int insertOrderSvcCmpsExt(@Param("osvcCmpsExtInfo")OSvcCmpsExtVO osvcCmpsExtInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getOProdCmpsInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더상품정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:45:49
	 * </PRE>
	 *   @return OProdCmpsVO 오더상품정보
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param orderTp 오더유형
	 *   @param prodCd 상품코드
	 *   @return
	 */
	OProdCmpsVO getOProdCmpsInfo(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId,
	        @Param("orderTp")String orderTp,
	        @Param("prodCd")String prodCd);

	/**
	 * <PRE>
	 * 1. MethodName: updateOProdExtId
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더상품정보 확장ID 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:47:20
	 * </PRE>
	 *   @return int 수정수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param orderTp 오더유형
	 *   @param prodCd 상품코드
	 *   @param extId 확장ID
	 *   @return
	 */
	int updateOProdExtId(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId,
	        @Param("orderTp")String orderTp,
	        @Param("prodCd")String prodCd,
	        @Param("extId")String extId);

	/**
	 * <PRE>
	 * 1. MethodName: insertOrderProdCmpsExt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더상품구성확장정보추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:48:52
	 * </PRE>
	 *   @return int 추가수
	 *   @param oprodCmpsExtInfo 오더상품구성확장정보
	 */
	int insertOrderProdCmpsExt(@Param("oprodCmpsExtInfo")OProdCmpsExtVO oprodCmpsExtInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getOSvcCmpsInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더서비스구성정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:49:36
	 * </PRE>
	 *   @return OSvcCmpsVO 오더서비스구성정보
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param orderTp 오더유형
	 *   @param prodCd 상품코드
	 *   @param svcCmpsId 서비스구성ID
	 */
	OSvcCmpsVO getOSvcCmpsInfo(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId,
	        @Param("orderTp")String orderTp,
	        @Param("prodCd")String prodCd,
			@Param("svcCmpsId")String svcCmpsId);
	
	/**
	 * <PRE>
	 * 1. MethodName: updateOSvcExtId
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 서비스구성 확장ID 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:01:13
	 * </PRE>
	 *   @return int 수정수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param orderTp 오더유형
	 *   @param prodCd 상품코드
	 *   @param svcCmpsId 서비스구성ID
	 *   @param extId 확장ID
	 */
	int updateOSvcExtId(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId,
	        @Param("orderTp")String orderTp,
	        @Param("prodCd")String prodCd,
	        @Param("svcCmpsId")String svcCmpsId,
	        @Param("extId")String extId);

	/**
	 * <PRE>
	 * 1. MethodName: updateRcptInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 접수테이블의 계약ID 수정 
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:01:59
	 * </PRE>
	 *   @return int 수정수
	 *   @param soId 사업ID
	 *   @param rcptId 접수ID
	 *   @param ctrtId 계약ID
	 */
	int updateRcptInfo(@Param("soId")String soId,
			@Param("rcptId")String rcptId,
			@Param("ctrtId")String ctrtId);

	/**
	 * <PRE>
	 * 1. MethodName: getOctrtMastInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더 마스터 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:03:07
	 * </PRE>
	 *   @return OCtrtMastVO 오더마스터정보
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @return
	 */
	OCtrtMastVO getOctrtMastInfo(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: insertCtrtMast
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 계약원부추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:03:29
	 * </PRE>
	 *   @return int 추가수
	 *   @param ctrtMastInfo 계약원부정보
	 */
	int insertCtrtMast(@Param("ctrtMastInfo")OCtrtMastVO ctrtMastInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getOProdCmpsInfoList
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더 상품그성정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:04:21
	 * </PRE>
	 *   @return List<OProdCmpsVO> 상품구성정보리스트
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 */
	List<OProdCmpsVO> getOProdCmpsInfoList(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: insertProdCmps
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 상품구성정보추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:05:01
	 * </PRE>
	 *   @return int 추가수
	 *   @param prodCmpsInfo 상품구성정보
	 *   @return
	 */
	int insertProdCmps(@Param("prodCmpsInfo")OProdCmpsVO prodCmpsInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getOProdCmpsExtInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더 상품구성 확장정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:05:20
	 * </PRE>
	 *   @return OProdCmpsExtVO 상품구성확장정보
	 *   @param extId 확장ID
	 *   @param orderId 오더ID
	 *   @return
	 */
	OProdCmpsExtVO getOProdCmpsExtInfo(@Param("extId")String extId, @Param("orderId")String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: insertProdCmpsExt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 상품구성 확장 정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:05:50
	 * </PRE>
	 *   @return int 추가수
	 *   @param prodCmpsInfoExt 상품구성확장정보
	 *   @return
	 */
	int insertProdCmpsExt(@Param("prodCmpsInfoExt")OProdCmpsExtVO prodCmpsInfoExt);

	/**
	 * <PRE>
	 * 1. MethodName: getOSvcCmpsInfoList
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더서비스구성정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:06:05
	 * </PRE>
	 *   @return List<OSvcCmpsVO> 서비스구성정보리스트
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 */
	List<OSvcCmpsVO> getOSvcCmpsInfoList(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: insertSvcCmps
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 서비스구성정보추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:07:02
	 * </PRE>
	 *   @return int 추가수
	 *   @param svcCmpsInfo 서비스구성정보
	 */
	int insertSvcCmps(@Param("svcCmpsInfo")OSvcCmpsVO svcCmpsInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getOSvcCmpsExtInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더서비스구성확장정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:07:19
	 * </PRE>
	 *   @return OSvcCmpsExtVO 오더서비스구성 확장정보
	 *   @param extId 확장ID
	 *   @param orderId 오더ID
	 */
	OSvcCmpsExtVO getOSvcCmpsExtInfo(@Param("extId")String extId, @Param("orderId")String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: insertSvcCmpsExt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 서비스구성 확장 정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:07:45
	 * </PRE>
	 *   @return int 추가수
	 *   @param svcCmpsInfoExt 서비스구성확장정보
	 */
	int insertSvcCmpsExt(@Param("svcCmpsInfoExt")OSvcCmpsExtVO svcCmpsInfoExt);

	/**
	 * <PRE>
	 * 1. MethodName: updateStatusOCtrtMast
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더마스터정보 상태 변경
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:08:09
	 * </PRE>
	 *   @return int 변경수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param stat 상태코드
	 *   @param usrId 사용자ID
	 *   @param today 변경일자
	 */
	int updateStatusOCtrtMast(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId,
			@Param("stat")String stat,
			@Param("usrId")String usrId,
			@Param("today")Date today);

	/**
	 * <PRE>
	 * 1. MethodName: updateStatusOProdCmps
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더상품구성정보 상태 변경
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:08:48
	 * </PRE>
	 *   @return int 변경수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param prodCmpsId 상품구성ID
	 *   @param stat 상태코드
	 *   @param usrId 사용자ID
	 *   @param today 변경일자
	 */
	int updateStatusOProdCmps(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId,
			@Param("prodCmpsId")String prodCmpsId,
			@Param("stat")String stat,
			@Param("usrId")String usrId,
			@Param("today")Date today);

	/**
	 * <PRE>
	 * 1. MethodName: updateStatusOSvcCmps
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더서비스구성정보 상태 변경
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:09:36
	 * </PRE>
	 *   @return int 변경 수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCmpsId 서비스구성ID
	 *   @param stat 상태코드
	 *   @param usrId 사용자ID
	 *   @param today 변경일자
	 *   @return
	 */
	int updateStatusOSvcCmps(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId,
			@Param("svcCmpsId")String svcCmpsId,
			@Param("stat")String stat,
			@Param("usrId")String usrId,
			@Param("today")Date today);

	/**
	 * <PRE>
	 * 1. MethodName: insertOrderMastExt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더마스터 확장정보추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:10:19
	 * </PRE>
	 *   @return int 추가수
	 *   @param octrtMastExt 오더마스터확장정보
	 *   @return
	 */
	int insertOrderMastExt(@Param("octrtMastExt")OCtrtMastExtVO octrtMastExt);

	/**
	 * <PRE>
	 * 1. MethodName: updateOCtrtExtId
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더마스터 확장정보 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:12:13
	 * </PRE>
	 *   @return int 수정수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param ctrtExtId 확장ID
	 */
	int updateOCtrtExtId(@Param("soId")String soId,
			@Param("ctrtId")String ctrtId,
			@Param("orderId")String orderId,
	        @Param("ctrtExtId")String ctrtExtId);

	/**
	 * <PRE>
	 * 1. MethodName: getOCtrtMastExtInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더원부 확장정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:12:44
	 * </PRE>
	 *   @return OCtrtMastExtVO 확장정보
	 *   @param extId 확장ID
	 *   @param orderId 오더ID
	 */
	OCtrtMastExtVO getOCtrtMastExtInfo(@Param("extId")String extId, @Param("orderId")String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: insertCtrtMastExt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 계약원부 확장 정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:13:58
	 * </PRE>
	 *   @return int 추가수
	 *   @param ctrtMastExt 확장정보
	 */
	int insertCtrtMastExt(@Param("ctrtMastExt")OCtrtMastExtVO ctrtMastExt);
	
	


	/**
	 * <PRE>
	 * 1. MethodName: getWorkInfoList
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 작업정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:15:37
	 * </PRE>
	 *   @return List<Map<String,Object>> 작업정보리스트
	 *   @param soId 사업ID
	 *   @param basicSvcCd 서비스코드
	 *   @param basicProdCd 상품코드
	 *   @param orderTp 오더유형
	 */
	List<Map<String, Object>> getWorkInfoList(@Param("soId")String soId, 
			@Param("basicSvcCd")String basicSvcCd,
	        @Param("basicProdCd")String basicProdCd,
	        @Param("orderTp")String orderTp);

	/**
	 * <PRE>
	 * 1. MethodName: insertWorkInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 작업정보추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:16:07
	 * </PRE>
	 *   @param workInfo 작업정보
	 */
	void insertWorkInfo(@Param("workInfo")Map<String, Object> workInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtMastLastInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 최종 계약 원부 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:17:03
	 * </PRE>
	 *   @return OCtrtMastVO 계약원부
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 */
	OCtrtMastVO getCtrtMastLastInfo(@Param("soId") String soId, @Param("custId") String custId, @Param("ctrtId") String ctrtId);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtMastLastExtInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 최종 계약 원부 확장 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:45:29
	 * </PRE>
	 *   @return OCtrtMastExtVO 계약원부 확장 정보
	 *   @param extId 확장ID
	 */
	OCtrtMastExtVO getCtrtMastLastExtInfo(@Param("extId") String extId);

	/**
	 * <PRE>
	 * 1. MethodName: updateOCtrtInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더 원부 변경 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 4:01:53
	 * </PRE>
	 *   @return int 변경수 
	 *   @param orderMast 오더 원부 변경 정보
	*/
	int updateOCtrtInfo(@Param("orderMast") OCtrtMastVO orderMast);
	
	/**
	 * <PRE>
	 * 1. MethodName: updateOCtrtExtInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더 원부 확장정보 변경 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 4:01:53
	 * </PRE>
	 *   @return int 변경수 
	 *   @param octrtExt 오더 원부 확장 변경 정보
	*/
	int updateOCtrtExtInfo(@Param("octrtExt") OCtrtMastExtVO octrtExt);
	
	
	/**
	 * <PRE>
	 * 1. MethodName: updateOProdCmpsInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더 상품 구성 정보 수정 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 7. 오후 4:02:38
	 * </PRE>
	 *   @return int 수정수
	 *   @param oprodCmps 오더 구성 정보 수정 처리
	 */
	int updateOProdCmpsInfo(@Param("oprodCmps") OProdCmpsVO oprodCmps);
	
	/**
	 * <PRE>
	 * 1. MethodName: updateOSvcCmpsInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더 서비스 구성 정보 수정 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 7. 오후 4:02:38
	 * </PRE>
	 *   @return int 수정수
	 *   @param osvcCmps 오더 구성 정보 수정 처리
	 */
	int updateOSvcCmpsInfo(@Param("osvcCmps") OSvcCmpsVO osvcCmps);

	/**
	 * <PRE>
	 * 1. MethodName: updateCtrtHist
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 계약 원부 최종 이력 변경
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 24. 오전 11:27:00
	 * </PRE>
	 *   @return int 변경수
	 *   @param orderInfo 오더정보
	 *   @param cancleCtrtStat 취소계약상태 
	 */
	int updateCtrtHist(@Param("orderInfo") OrderRequestInfoVO orderInfo, @Param("cancleCtrtStat") String cancleCtrtStat);
	
	/**
	 * <PRE>
	 * 1. MethodName: updateCtrtExtHist
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 계약 원부 확장정보 이력 변경
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 27. 오후 1:03:58
	 * </PRE>
	 *   @return int 변경수
	 *   @param extId 확장ID
	 *   @param actDttm 종료일시
	 */
	int updateCtrtExtHist(@Param("extId") String extId, @Param("actDttm") String actDttm);

	
	/**
	 * <PRE>
	 * 1. MethodName: getCtrtStat
	 * 2. ClassName : OrderPrecheckMapper
	 * 3. Comment   : 현재 계약상태 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:31:12
	 * </PRE>
	 *   @return String 계약상태코드
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @return
	 */
	String getCtrtStat(@Param("soId") String soId, @Param("custId") String custId, @Param("ctrtId") String ctrtId);

	/**
	 * <PRE>
	 * 1. MethodName: getProdCmpsLastInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 상품 구성 정보 조회(사용중, 일시정지, 직권정지)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 5. 오후 5:04:01
	 * </PRE>
	 *   @return List<OProdCmpsVO> 상품구성정보
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 */
	List<OProdCmpsVO> getProdCmpsLastInfo(@Param("soId") String soId, @Param("ctrtId") String ctrtId);

	/**
	 * <PRE>
	 * 1. MethodName: getProdCmpsExtLastInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 상품 구성 정보 확장 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 5. 오후 5:33:35
	 * </PRE>
	 *   @return OProdCmpsExtVO 상품 구성 확장 정보
	 *   @param extId 확장ID
	 */
	OProdCmpsExtVO getProdCmpsExtLastInfo(@Param("extId") String extId);
	
	/**
	 * <PRE>
	 * 1. MethodName: getSvcCmpsLastInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 서비스 구성 정보 조회(사용중, 일시정지, 직권정지)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 5. 오후 5:50:35
	 * </PRE>
	 *   @return List<OSvcCmpsVO> 서비스구성정보
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 */
	List<OSvcCmpsVO> getSvcCmpsLastInfo(@Param("soId") String soId, @Param("ctrtId") String ctrtId);
	
	/**
	 * <PRE>
	 * 1. MethodName: getSvcCmpsExtLastInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 서비스 구성 정보 확장 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 5. 오후 5:50:49
	 * </PRE>
	 *   @return OSvcCmpsExtVO 서비스구성 확장 정보
	 *   @param extId 확장ID
	 */
	OSvcCmpsExtVO getSvcCmpsExtLastInfo(@Param("extId") String extId);

	/**
	 * <PRE>
	 * 1. MethodName: updateProdCmpsHist
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 상품 구성 정보 최종 이력 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 6. 오전 10:44:00
	 * </PRE>
	 *   @return int 수정수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param prodCmpsId 상품구성ID
	 *   @param orderReqDttm 수정일시
	 *   @param cancleCtrtStatStat 취소계약상태
	 */
	int updateProdCmpsHist(@Param("soId") String soId, @Param("ctrtId") String ctrtId, @Param("prodCmpsId") String prodCmpsId, @Param("orderReqDttm") String orderReqDttm, @Param("cancleCtrtStat") String cancleCtrtStat);

	/**
	 * <PRE>
	 * 1. MethodName: updateProdCmpsExtHist
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 상품 구성 확장 정보 최종 이력 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 6. 오전 10:47:42
	 * </PRE>
	 *   @return int 수정수
	 *   @param extId 확장ID
	 *   @param orderReqDttm 수정일시
	 *   @return
	 */
	int updateProdCmpsExtHist(@Param("extId") String extId, @Param("orderReqDttm") String orderReqDttm);

	/**
	 * <PRE>
	 * 1. MethodName: updateSvcCmpsHist
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 서비스 구성 정보 최종 이력 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 6. 오전 10:57:33
	 * </PRE>
	 *   @return int 수정수
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param svcCmpsId 서비스구성ID
	 *   @param orderReqDttm 수정일시
	 *   @param cancleCtrtStat  취소 계약 상태 
	 */
	int updateSvcCmpsHist(@Param("soId") String soId, @Param("ctrtId") String ctrtId, @Param("svcCmpsId") String svcCmpsId, @Param("orderReqDttm") String orderReqDttm, @Param("cancleCtrtStat") String cancleCtrtStat);

	/**
	 * <PRE>
	 * 1. MethodName: updateSvcCmpsExtHist
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 서비스 구성 확장 정보 이력 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 6. 오전 11:15:14
	 * </PRE>
	 *   @return int 수정수
	 *   @param extId 확장ID
	 *   @param orderReqDttm 수정일시
	 */
	int updateSvcCmpsExtHist(@Param("extId") String extId, @Param("orderReqDttm") String orderReqDttm);

	/**
	 * <PRE>
	 * 1. MethodName: getProdCmpsLastInfoTerminated
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 해지계약의 최종 상품 구성 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 11. 오후 1:17:57
	 * </PRE>
	 *   @return List<OProdCmpsVO> 해지 계약의 최종 상품 구성 정보
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param lastOrderId 해지계약의 오더ID
	 */
	List<OProdCmpsVO> getProdCmpsLastInfoTerminated(@Param("soId") String soId, @Param("ctrtId") String ctrtId, @Param("lastOrderId") String lastOrderId);

	/**
	 * <PRE>
	 * 1. MethodName: getSvcCmpsLastInfoTerminated
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 해지 계약의 최종 서비스 구성 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 11. 오후 1:21:16
	 * </PRE>
	 *   @return List<OSvcCmpsVO> 해지 계약의 최종 서비스 구성 정보
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param lastOrderId 해지계약의 오더ID
	 */
	List<OSvcCmpsVO> getSvcCmpsLastInfoTerminated(@Param("soId") String soId, @Param("ctrtId") String ctrtId, @Param("lastOrderId") String lastOrderId);

	/**
	 * <PRE>
	 * 1. MethodName: getSuspendedList
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 계약의 해지 이력 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 12. 오후 1:10:38
	 * </PRE>
	 *   @return List<Map<String,Object>> 정지이력
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param dttm 1년전일시
	 */
	List<Map<String, Object>> getSuspendedList(@Param("soId") String soId, @Param("custId") String custId, @Param("ctrtId") String ctrtId, @Param("dttm") String dttm);

	/**
	 * <PRE>
	 * 1. MethodName: insetCtrtPromInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 계약의 약정정보 생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 14. 오후 1:40:03
	 * </PRE>
	 *   @return void 
	 *   @param promCtrtInfo 약정정보
	 */
	void insetCtrtPromInfo(@Param("promCtrtInfo") CtrtPromVO promCtrtInfo);

	/**
	 * <PRE>
	 * 1. MethodName: updatePromCtrtId
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 오더의 약정ID 수정  
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 14. 오후 1:58:43
	 * </PRE>
	 *   @return void
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param promCtrtId 약정ID
	 */
	void updatePromCtrtId(@Param("soId") String soId, @Param("ctrtId") String ctrtId, @Param("orderId") String orderId, @Param("promCtrtId") String promCtrtId);

	/**
	 * <PRE>
	 * 1. MethodName: updatePromCtrtCancle
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 약정정보 취소
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 14. 오후 3:12:14
	 * </PRE>
	 *   @return void
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param promId 약정ID
	 */
	void updatePromCtrtCancle(@Param("soId") String soId, @Param("ctrtId") String ctrtId, @Param("orderId") String orderId, @Param("promCtrtId") String promCtrtId);

	/**
	 * <PRE>
	 * 1. MethodName: updatePromCtrt
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 약정 정보 시작일 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 14. 오후 3:21:11
	 * </PRE>
	 *   @return void
	 *   @param ctrtPromInfo 약정정보
	 */
	void updatePromCtrt(@Param("ctrtPromInfo") CtrtPromVO ctrtPromInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getThrwyInfoList
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 일회성요금 생성 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 17. 오후 1:38:13
	 * </PRE>
	 *   @return List<Map<String,Object>> 일회성요금생성정보
	 *   @param orderId 오더ID
	 */
	List<Map<String, Object>> getThrwyInfoList(@Param("orderId") String orderId);

	/**
	 * <PRE>
	 * 1. MethodName: insertThrwyInfo
	 * 2. ClassName : OrderProcessMapper
	 * 3. Comment   : 일회성 요금 생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 17. 오후 3:16:45
	 * </PRE>
	 *   @return void
	 *   @param insertParam
	 */
	void insertThrwyInfo(@Param("insertParam") Map<String, Object> insertParam);

}

