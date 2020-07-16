package com.ntels.ccbs.customer.service.contract.contract;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.customer.domain.contract.contract.CtrtInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.CustInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderMastInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;

/**
 * <PRE>
 * 1. ClassName: ContractManagementService
 * 2. FileName : ContractManagementService.java
 * 3. Package  : com.ntels.ccbs.customer.service.contract.contract
 * 4. Comment  : 계약관리서비스
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 3. 오후 4:32:53
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 3.    : 신규개발
 * </PRE>
 */
public interface ContractManagementService {

	/**
	 * <PRE>
	 * 1. MethodName: getCustInfoForSearching
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 고객정보사전조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:08:35
	 * </PRE>
	 *   @return List<Map<String,Object>> 고객정보 Map List
	 *   @param soAuthList 조회조건 사업ID리스트
	 *   @param condCustSoId 사업ID
	 *   @param condCustNm 고객명
	 *   @param condCustId 고객ID
	 *   @param condSearchTp 조회유형코드
	 *   @param condSearchKey 조회조건키값
	 *   @return
	 */
	List<Map<String, Object>> getCustInfoForSearching(
	          List<Map<String, Object>> soAuthList
	        ,String condCustSoId
	        ,String condCustNm
	        ,String condCustId
	        ,String condSearchTp
	        ,String condSearchKey);

	/**
	 * <PRE>
	 * 1. MethodName: getCustInfo
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 고객정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:17:17
	 * </PRE>
	 *   @return CustInfoVO {@link CustInfoVO} 고객정보 
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param lng 언어코드
	 *   @param today 조회일(YYYYMMDD)
	 */
	CustInfoVO getCustInfo(String soId
			,String custId
			,String lng
			,String today);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtInfoList
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 계약리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:17:57
	 * </PRE>
	 *   @return List<CtrtInfoVO> {@link CtrtInfoVO} 계약리스트
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param isIncludeTermYn 해지계약포함여부(Y/N)
	 *   @param lng 언어코드
	 *   @param today 조회일자(YYYYMMDD)
	 *   @param sidx 정렬키
	 *   @param sord 정렬기준
	 */
	List<CtrtInfoVO> getCtrtInfoList(String soId
			, String custId
			, String isIncludeTermYn
			, String lng
			, String today
			, String sidx
			, String sord);

	
	/**
	 * <PRE>
	 * 1. MethodName: getCtrtInfo
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 계약정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:20:27
	 * </PRE>
	 *   @return CtrtInfoVO {@link CtrtInfoVO} 계약정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param lng 언어코드
	 *   @param today 조회일자(YYYYMMDD)
	 */
	CtrtInfoVO getCtrtInfo(String soId
			, String custId
			, String ctrtId
			, String lng
			, String today);
	
	/**
	 * <PRE>
	 * 1. MethodName: saveRcptInfo
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 상담정보신규생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:21:39
	 * </PRE>
	 *   @return RcptInfoVO {@link RcptInfoVO} 상담정보
	 *   @param rcptInfo {@link RcptInfoVO} 상담정보
	 *   @param lng 언어코드
	 *   @param today 저장일자(YYYYMMDD)
	 */
	RcptInfoVO saveRcptInfo(RcptInfoVO rcptInfo
			, String lng
			, String today);

	/**
	 * <PRE>
	 * 1. MethodName: updateRcptInfo
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 상담정보저장
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:22:32
	 * </PRE>
	 *   @return RcptInfoVO {@link RcptInfoVO} 상담정보
	 *   @param rcptInfo {@link RcptInfoVO} 상담정보
	 *   @param lng 언어코드
	 *   @param today 저장일자(YYYYMMDD)
	 */
	RcptInfoVO updateRcptInfo(RcptInfoVO rcptInfo
			, String lng
			, String today);

	/**
	 * <PRE>
	 * 1. MethodName: updateRcptInfoCmpl
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 상담정보 완료 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:23:03
	 * </PRE>
	 *   @return RcptInfoVO {@link RcptInfoVO} 상담정보
	 *   @param rcptInfo {@link RcptInfoVO} 상담정보
	 *   @param lng 언어코드
	 *   @param today 완료일자(YYYYMMDD)
	 */
	RcptInfoVO updateRcptInfoCmpl(RcptInfoVO rcptInfo
			, String lng
			, String today);

	/**
	 * <PRE>
	 * 1. MethodName: getRcptInfo
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 상담 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:32:56
	 * </PRE>
	 *   @return RcptInfoVO {@link RcptInfoVO} 상담정보
	 *   @param rcptId 접수ID
	 *   @param lng 언어코드
	 *   @param today 조회일자(YYYYMMDD)
	 */
	RcptInfoVO getRcptInfo(String rcptId
			, String lng
			, String today);

	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoList
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 계약변경리스트조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 4:33:36
	 * </PRE>
	 *   @return List<OrderInfoVO> {@link OrderInfoVO} 오더정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param lng 언어코드
	 *   @param today 조회일자
	 */
	List<OrderInfoVO> getOrderInfoList(String soId
			, String custId
			, String ctrtId
			, String lng
			, String today);

	/**
	 * <PRE>
	 * 1. MethodName: getOrderListBySoId
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   : 사업자의 이용 가능한 오더 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 20. 오후 2:09:05
	 * </PRE>
	 *   @return List<OrderMastInfoVO> 오더유형리스트
	 *   @param soId 사업ID
	 *   @param custTp 고객유형
	 */
	List<OrderMastInfoVO> getOrderListBySoId(String soId, String custTp, String lng);

}
