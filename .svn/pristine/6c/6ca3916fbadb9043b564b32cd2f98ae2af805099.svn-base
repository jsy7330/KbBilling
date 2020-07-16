package com.ntels.ccbs.customer.mapper.contract.contract;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.contract.contract.CtrtInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.CustInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderMastInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptProcInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptTransInfoVO;



/**
 * <PRE>
 * 1. ClassName: ContractManagementMapper
 * 2. FileName : ContractManagementMapper.java
 * 3. Package  : com.ntels.ccbs.customer.mapper.contract.contract
 * 4. Comment  : 계약정보관리 Mapper
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 3. 오후 5:13:02
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 3.    : 신규개발
 * </PRE>
 */
@Component
public interface ContractManagementMapper {

	/**
	 * <PRE>
	 * 1. MethodName: getCustInfoWithCtrtInfo
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 계약정보로 고객 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:05:24
	 * </PRE>
	 *   @return List<Map<String,Object>> 고객정보 Map
	 *   @param soAuthList 사업리스트
	 *   @param condCustSoId 사업ID
	 *   @param condCustNm 고객명
	 *   @param condCustId 고객ID
	 *   @param condSearchTp 조회구분코드
	 *   @param condSearchKey 조회키값
	 *   @return
	 */
	List<Map<String, Object>> getCustInfoWithCtrtInfo(
			@Param("soAuthList")List<Map<String, Object>> soAuthList,
			@Param("condCustSoId")String condCustSoId,
			@Param("condCustNm")String condCustNm,
			@Param("condCustId")String condCustId,
			@Param("condSearchTp")String condSearchTp,
			@Param("condSearchKey")String condSearchKey);

	/**
	 * <PRE>
	 * 1. MethodName: getCustInfoOnlyCustInfo
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 고객정보호 고객 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:06:31
	 * </PRE>
	 *   @return List<Map<String,Object>> 고객정보 Map
	 *   @param soAuthList 사업리스트
	 *   @param condCustSoId 사업ID
	 *   @param condCustNm 고객명
	 *   @param condCustId 고객ID
	 *   @param condSearchTp 조회구분코드
	 *   @param condSearchKey 조회키값
	 *   @return
	 */
	List<Map<String, Object>> getCustInfoOnlyCustInfo(
			@Param("soAuthList")List<Map<String, Object>> soAuthList,
			@Param("condCustSoId")String condCustSoId,
			@Param("condCustNm")String condCustNm,
			@Param("condCustId")String condCustId,
			@Param("condSearchTp")String condSearchTp,
			@Param("condSearchKey")String condSearchKey);

	/**
	 * <PRE>
	 * 1. MethodName: getCustInfo
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 고객정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:07:08
	 * </PRE>
	 *   @return CustInfoVO {@link CustInfoVO} 고객정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param lng 언어코드
	 *   @param today 조회일자(YYYYMMDD)
	 */
	CustInfoVO getCustInfo(
			@Param("soId")String soId,
			@Param("custId")String custId,
			@Param("lng")String lng,
			@Param("today")String today);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtInfoList
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 계약리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:08:19
	 * </PRE>
	 *   @return List<CtrtInfoVO> {@link CtrtInfoVO} 계약정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param isIncludeTermYn 해지계약포함여부(Y/N)
	 *   @param lng 언어코드
	 *   @param today 조회일자(YYYYMMDD)
	 *   @param sidx 정렬키
	 *   @param sord 정렬순서
	 *   @return
	 */
	List<CtrtInfoVO> getCtrtInfoList(
			@Param("soId")String soId,
			@Param("custId")String custId,
			@Param("isIncludeTermYn")String isIncludeTermYn,
			@Param("lng")String lng,
			@Param("today")String today,
			@Param("sidx")String sidx,
			@Param("sord")String sord);

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtInfo
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 계약조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:10:41
	 * </PRE>
	 *   @return CtrtInfoVO {@link CtrtInfoVO} 계약조회
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param lng 언어코드
	 *   @param today 조회일자
	 */
	CtrtInfoVO getCtrtInfo(
			@Param("soId")String soId,
			@Param("custId")String custId,
			@Param("ctrtId")String ctrtId,
			@Param("lng")String lng,
			@Param("today")String today);
	
	/**
	 * <PRE>
	 * 1. MethodName: saveRcptInfo
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 상담 신규 생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:11:35
	 * </PRE>
	 *   @return int 신규생성수
	 *   @param rcptInfo {@link RcptInfoVO} 상담정소
	 */
	int saveRcptInfo(@Param("rcptInfo")RcptInfoVO rcptInfo);

	/**
	 * <PRE>
	 * 1. MethodName: saveRcptProcInfo
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 상담진행정보 저장
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:12:15
	 * </PRE>
	 *   @return int 저장수
	 *   @param rcptProcInfo {@link RcptProcInfoVO} 상담진행정보
	 */
	int saveRcptProcInfo(@Param("rcptProcInfo")RcptProcInfoVO rcptProcInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getRcptInfo
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 상담정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:13:20
	 * </PRE>
	 *   @return RcptInfoVO {@link RcptInfoVO} 상담정보
	 *   @param rcptId 접수ID
	 *   @param lng 언어코드
	 *   @param today 조회일자(YYYYMMDD)
	 */
	RcptInfoVO getRcptInfo(@Param("rcptId") String rcptId
			,@Param("lng")String lng
			,@Param("today")String today);

	/**
	 * <PRE>
	 * 1. MethodName: getRcptTranInfoList
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 상담전달정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:18:40
	 * </PRE>
	 *   @return List<RcptTransInfoVO> {@link RcptTransInfoVO} 상담전달정보
	 *   @param rcptId 상담ID
	 *   @param lng 언어코드
	 *   @param today 조회일자(YYYYMMDD)
	 */
	List<RcptTransInfoVO> getRcptTranInfoList(
			@Param("rcptId") String rcptId,
			@Param("lng")String lng,
	        @Param("today")String today);

	/**
	 * <PRE>
	 * 1. MethodName: updateRcptInfo
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 상담정보수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:19:30
	 * </PRE>
	 *   @return int 수정수
	 *   @param rcptInfo {@link RcptInfoVO} 상담정보
	 */
	int updateRcptInfo(@Param("rcptInfo")RcptInfoVO rcptInfo);
	
	/**
	 * <PRE>
	 * 1. MethodName: getMaxSeqRcptProc
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 상담진행정보 최대 Seq 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:20:06
	 * </PRE>
	 *   @return int Max Seq
	 *   @param rcptId 상담ID
	 */
	int getMaxSeqRcptProc(@Param("rcptId") String rcptId);
	
	/**
	 * <PRE>
	 * 1. MethodName: getMaxSeqRcptTrans
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 상담전달정보 최대 Seq 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:20:40
	 * </PRE>
	 *   @return int Max Seq
	 *   @param rcptId 상담ID
	 */
	int getMaxSeqRcptTrans(@Param("rcptId") String rcptId);

	/**
	 * <PRE>
	 * 1. MethodName: updateRcptInfoCmpl
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 상담완료처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:21:07
	 * </PRE>
	 *   @return int 완료수
	 *   @param rcptInfo {@link RcptInfoVO} 상담정보
	 */
	int updateRcptInfoCmpl(@Param("rcptInfo")RcptInfoVO rcptInfo);


	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoList
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 오더정보리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 3. 오후 5:22:05
	 * </PRE>
	 *   @return List<OrderInfoVO> {@link OrderInfoVO} 오더정보
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param lng 언어크도
	 *   @param today 조회일자(YYYYMMDD)
	 */
	List<OrderInfoVO> getOrderInfoList(@Param("soId") String soId
			, @Param("custId")String custId
			, @Param("ctrtId")String ctrtId
			, @Param("lng")String lng
			, @Param("today")String today);

	/**
	 * <PRE>
	 * 1. MethodName: getOrderListBySoId
	 * 2. ClassName : ContractManagementMapper
	 * 3. Comment   : 사업자의 오더 유형 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 20. 오후 2:10:43
	 * </PRE>
	 *   @return List<OrderMastInfoVO> 오더 마스터정보
	 *   @param soId 사업ID
	 *   @param custTp 고객유형
	 *   @param lng 언어크도
	 */
	List<OrderMastInfoVO> getOrderListBySoId(@Param("soId") String soId
			, @Param("custTp")String custTp
			, @Param("lng")String lng);
}

