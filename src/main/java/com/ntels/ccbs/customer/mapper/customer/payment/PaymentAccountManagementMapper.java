package com.ntels.ccbs.customer.mapper.customer.payment;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountHistVO;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountInfoVO;
import com.ntels.ccbs.customer.domain.customer.payment.VirAccountVO;

@Component
public interface PaymentAccountManagementMapper {
	
	/**
	 * <PRE>
	 * 1. MethodName: getCustomerInfoList
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 납부계정용 고객정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 4. 29. 오전 7:28:34
	 * </PRE>
	 *   @return List<Map<String,Object>>
	 *   @param soId
	 *   @param custName
	 *   @return
	 */
	List<Map<String,Object>> getCustomerInfoList(
			@Param("soId")String soId, 
			@Param("custName")String custName,
			@Param("custId")String custId,
			@Param("soAuthList")List<Map<String, Object>> soAuthList);
	
	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntInfoList
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객의 납부 계정정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 4. 29. 오전 7:28:45
	 * </PRE>
	 *   @return List<PaymentAccountInfoVO>
	 *   @param soId
	 *   @param custId
	 *   @return
	 */
	List<PaymentAccountInfoVO> getPymAcntInfoList(
			@Param("soId")String soId, 
			@Param("custId")String custId,
			@Param("today")String today,
			@Param("sidx")String sidx,
			@Param("sord")String sord,
			@Param("start")String start,
			@Param("end")String end,
			@Param("lng")String lng
			);
	
	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntInfoListTotalCnt
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   :고객의 납부 계정정보 총 Cnt
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 4. 29. 오후 4:45:09
	 * </PRE>
	 *   @return int
	 *   @param soId
	 *   @param custId
	 *   @return
	 */
	Integer getPymAcntInfoListTotalCnt(
			@Param("soId")String soId, 
			@Param("custId")String custId);
	
	/**
	 * <PRE>
	 * 1. MethodName: insertPymAcntInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 납부계정 신규 생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 9. 오후 1:46:39
	 * </PRE>
	 *   @return void
	 *   @param pymAcntInfo
	 */
	void insertPymAcntInfo(@Param("pymAcntInfo") PaymentAccountInfoVO pymAcntInfo) throws ServiceException;
	
	/**
	 * <PRE>
	 * 1. MethodName: insertPymAcntHistInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 납부계정 이력 신규 작성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 9. 오후 2:58:23
	 * </PRE>
	 *   @return void
	 *   @param pymAcntInfo
	 *   @param today
	 *   @throws ServiceException
	 */
	void insertPymAcntHistInfo(@Param("pymAcntInfo") PaymentAccountInfoVO pymAcntInfo, @Param("today") String today) throws ServiceException;
	

	/**
	 * <PRE>
	 * 1. MethodName: getVirAcntInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 사용가능한 가상계좌 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 9. 오후 5:10:43
	 * </PRE>
	 *   @return VirAccountVO
	 *   @param soId
	 *   @param bnkCd
	 *   @param start
	 *   @param end
	 *   @return
	 */
	List<VirAccountVO> getVirAcntInfo(@Param("soId") String soId, @Param("bnkCd") String bnkCd, @Param("start")String start,@Param("end")String end );
	
	/**
	 * <PRE>
	 * 1. MethodName: updateVirAcntInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 가상계좌 
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 10. 오후 4:30:24
	 * </PRE>
	 *   @return int
	 *   @param virAcntVo
	 *   @return
	 */
	int updateVirAcntInfo(@Param("virAcntVo") VirAccountVO virAcntVo);
	

	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 납부계정조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 10. 오후 4:31:12
	 * </PRE>
	 *   @return PaymentAccountInfoVO
	 *   @param soId
	 *   @param pymAcntId
	 *   @return
	 */
	PaymentAccountInfoVO getPymAcntInfo(@Param("soId") String soId, @Param("pymAcntId") String pymAcntId, @Param("today")String today, @Param("lng") String lng);
	
	
	/**
	 * <PRE>
	 * 1. MethodName: updatePymAcntInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 납부계정수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 11. 오전 11:35:32
	 * </PRE>
	 *   @return int
	 *   @param pymAcntInfo
	 *   @return
	 */
	int updatePymAcntInfo(@Param("pymAcntInfo") PaymentAccountInfoVO pymAcntInfo);
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntChngHistList
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 납부계정 변경 이력 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 11. 오전 11:35:44
	 * </PRE>
	 *   @return List<PymAcntSearchVO>
	 *   @param soId
	 *   @param pymAcntId
	 *   @param today
	 *   @param sidx
	 *   @param sord
	 *   @param end
	 *   @param start
	 *   @param lng
	 *   @return
	 */
	List<PaymentAccountHistVO> getPymAcntChngHistList(
			@Param("soId")String soId, 
			@Param("pymAcntId")String pymAcntId,
			@Param("today")String today,
			@Param("sidx")String sidx,
			@Param("sord")String sord,
			@Param("start")String start,
			@Param("end")String end,
			@Param("lng")String lng
			);
	
	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntChngHistListTotalCnt
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 납부계정 변경이력 총 카운트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 11. 오전 11:35:56
	 * </PRE>
	 *   @return Integer
	 *   @param soId
	 *   @param pymAcntId
	 *   @return
	 */
	Integer getPymAcntChngHistListTotalCnt(
			@Param("soId")String soId, 
			@Param("pymAcntId")String pymAcntId);
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getVirAcntListTotalCnt
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 가상계좌 조회 총 카운트
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 16. 오후 2:31:29
	 * </PRE>
	 *   @return Integer
	 *   @param soId
	 *   @param pymAcntId
	 *   @return
	 */
	Integer getVirAcntListTotalCnt(
			@Param("soId")String soId, 
			@Param("pymAcntId")String pymAcntId);
	
	/**
	 * <PRE>
	 * 1. MethodName: getVirAcntList
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 가상계좌 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 16. 오후 2:32:11
	 * </PRE>
	 *   @return List<VirAccountVO>
	 *   @param soId
	 *   @param pymAcntId
	 *   @param today
	 *   @param sidx
	 *   @param sord
	 *   @param end
	 *   @param start
	 *   @param lng
	 *   @return
	 */
	List<VirAccountVO> getVirAcntList(
			@Param("soId")String soId, 
			@Param("pymAcntId")String pymAcntId,
			@Param("today")String today,
			@Param("sidx")String sidx,
			@Param("sord")String sord,
			@Param("start")String start,
			@Param("end")String end,
			@Param("lng")String lng
			);
	
	/**
	 * <PRE>
	 * 1. MethodName: processChngVirAcnt
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 기존 계좌 종료 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 16. 오후 3:18:30
	 * </PRE>
	 *   @return int
	 *   @param soId
	 *   @param pymAcntId
	 *   @param vrAcntNo
	 *   @param usrId
	 *   @param today
	 *   @return
	 */
	int processChngVirAcnt(@Param("soId")String soId, 
			@Param("pymAcntId")String pymAcntId,
			@Param("vrBnkCd")String vrBnkCd,
			@Param("vrAcntNo")String vrAcntNo,
			@Param("usrId")String usrId,
			@Param("today")Date today);
}
