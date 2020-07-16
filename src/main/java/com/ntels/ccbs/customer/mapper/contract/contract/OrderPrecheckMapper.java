package com.ntels.ccbs.customer.mapper.contract.contract;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.contract.contract.PrecheckInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.PrecheckMastVO;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountInfoVO;



/**
 * <PRE>
 * 1. ClassName: OrderPrecheckMapper
 * 2. FileName : OrderPrecheckMapper.java
 * 3. Package  : com.ntels.ccbs.customer.mapper.contract.contract
 * 4. Comment  : 사전심사 Mapper
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 23. 오후 3:23:24
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 23.    : 신규개발
 * </PRE>
 */
@Component
public interface OrderPrecheckMapper {
	/**
	 * <PRE>
	 * 1. MethodName: deletePrecheckData
	 * 2. ClassName : OrderPrecheckMapper
	 * 3. Comment   : 동일 접수ID의 이전 심사기록 삭제
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:23:22
	 * </PRE>
	 *   @return int 삭제수
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param rcptId 접수ID
	 *   @return
	 */
	int deletePrecheckData(@Param("soId")String soId, 
			@Param("custId")String custId,
			@Param("rcptId")String rcptId);

	/**
	 * <PRE>
	 * 1. MethodName: getOrderPrecheckMastInfo
	 * 2. ClassName : OrderPrecheckMapper
	 * 3. Comment   : 전산 심사 마스터 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:24:10
	 * </PRE>
	 *   @return PrecheckMastVO 심사정보
	 *   @param checkCd 체크코드
	 *   @param soId 사업ID
	 *   @param basicSvcCd 서비스코드
	 *   @param custTp 고객유형
	 *   @param lng 언어코드
	 *   @param today 조회일자
	 *   @return
	 */
	PrecheckMastVO getOrderPrecheckMastInfo(@Param("checkCd")String checkCd,
			@Param("soId")String soId,
	        @Param("basicSvcCd")String basicSvcCd,
	        @Param("custTp")String custTp,
	        @Param("lng")String lng,
	        @Param("today")String today);

	/**
	 * <PRE>
	 * 1. MethodName: savePrecheckResult
	 * 2. ClassName : OrderPrecheckMapper
	 * 3. Comment   : 심사기록 저장
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:24:56
	 * </PRE>
	 *   @return void
	 *   @param precheckInfo 저장할 심사내용
	 */
	void savePrecheckResult(@Param("precheckInfo")PrecheckInfoVO precheckInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntIdList
	 * 2. ClassName : OrderPrecheckMapper
	 * 3. Comment   : 고객의 사용중인 납부계정ID 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:25:10
	 * </PRE>
	 *   @return List<Map<String,Object>> 납부계정ID 리스트
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 */
	List<Map<String, Object>> getPymAcntIdList(@Param("soId")String soId, @Param("custId")String custId);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtCnt
	 * 2. ClassName : OrderPrecheckMapper
	 * 3. Comment   : 고객의 사용중인 계약수 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:29:24
	 * </PRE>
	 *   @return Integer 계약수
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 */
	Integer getCtrtCnt(@Param("soId")String soId, @Param("custId")String custId);

	/**
	 * <PRE>
	 * 1. MethodName: savePrecheckAsApprove
	 * 2. ClassName : OrderPrecheckMapper
	 * 3. Comment   : 전산심사 결과 승인으로 변경 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:29:46
	 * </PRE>
	 *   @return void
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param rcptId 접수ID
	 *   @param orderCd 오더코드
	 *   @param basicSvcCd 서비스코드
	 *   @param custTp 고객유형
	 *   @param attrCd 속성코드
	 *   @param resultCd 결과코드
	 *   @param checkCd 체크코
	 *   @param nowDate 현재일
	 *   @param lng 언어코드
	 *   @param usrId 사용자ID
	 */
	void savePrecheckAsApprove(@Param("soId")String soId,
			@Param("custId")String custId,
			@Param("rcptId")String rcptId,
	        @Param("orderCd")String orderCd,
	        @Param("basicSvcCd")String basicSvcCd,
	        @Param("custTp")String custTp,
	        @Param("attrCd")String attrCd,
	        @Param("resultCd")String resultCd,
	        @Param("checkCd")String checkCd,
	        @Param("nowDate")Date nowDate,
	        @Param("lng")String lng,
	        @Param("usrId")String usrId);

	/**
	 * <PRE>
	 * 1. MethodName: getPrecheckResultList
	 * 2. ClassName : OrderPrecheckMapper
	 * 3. Comment   : 전산심사 결과 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:30:30
	 * </PRE>
	 *   @return List<PrecheckInfoVO> 결과리스트
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param rcptId 접수ID
	 *   @param orderCd 오더코드
	 *   @param basicSvcCd 서비스코드
	 *   @param custTp 고객유형
	 *   @param today 조회일자
	 *   @param lng 언어코드
	 *   @return
	 */
	List<PrecheckInfoVO> getPrecheckResultList(@Param("soId")String soId,
			@Param("custId")String custId,
			@Param("rcptId")String rcptId,
	        @Param("orderCd")String orderCd,
	        @Param("basicSvcCd")String basicSvcCd,
	        @Param("custTp")String custTp,
	        @Param("today")String today,
	        @Param("lng")String lng);

	
}
