package com.ntels.ccbs.customer.mapper.customer.customer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoHistVO;
import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoVO;

/**
 * <PRE>
 * 1. ClassName: CustomerManagementMapper
 * 2. FileName : CustomerManagementMapper.java
 * 3. Package  : com.ntels.ccbs.customer.mapper.customer.customer
 * 4. Comment  :
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 5. 25. 오후 1:24:06
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 5. 25.    : 신규개발
 * </PRE>
 */
@Component
public interface CustomerManagementMapper {
	

	/**
	 * <PRE>
	 * 1. MethodName: getCustomerInfoList
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객정보조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 25. 오후 1:24:09
	 * </PRE>
	 *   @return List<CustInfoVO>
	 *   @param soId
	 *   @param custId
	 *   @param custName
	 *   @param soAuthList
	 *   @param today
	 *   @param lng
	 *   @return
	 */
	List<CustomerInfoVO> getCustomerInfoList(
			@Param("soId")String soId, 
			@Param("custId")String custId, 
			@Param("custName")String custName,
			@Param("soAuthList")List<Map<String, Object>> soAuthList,
			@Param("today") String today,
			@Param("lng") String lng);

	/**
	 * <PRE>
	 * 1. MethodName: insertCustInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객정보 저장
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 19. 오후 3:50:29
	 * </PRE>
	 *   @return void
	 *   @param customerInfo
	 */
	void insertCustInfo(CustomerInfoVO customerInfo);

	/**
	 * <PRE>
	 * 1. MethodName: insertCustHistInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객정보 히스토리 저장
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 19. 오후 3:50:31
	 * </PRE>
	 *   @return void
	 *   @param customerInfo
	 *   @param today
	 */
	void insertCustHistInfo(@Param("custInfo") CustomerInfoVO custInfo, @Param("today")String today);


	/**
	 * <PRE>
	 * 1. MethodName: checkBizRegNo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 사업자 번호 중복 체크
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 19. 오후 3:55:41
	 * </PRE>
	 *   @return int
	 *   @param soId
	 *   @param bizRegNo
	 *   @return
	 */
	int checkBizRegNo(@Param("soId") String soId, @Param("bizRegNo")String bizRegNo);


	/**
	 * <PRE>
	 * 1. MethodName: updateCustInfo
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객정보변경
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 25. 오전 10:29:36
	 * </PRE>
	 *   @return void
	 *   @param customerInfo
	 */
	void updateCustInfo(@Param("custInfo")CustomerInfoVO customerInfo);

	/**
	 * <PRE>
	 * 1. MethodName: insertCustInfoExt
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객의 담당자 정보 등록(EXT_ID)
	 * 4. 작성자    : hyewon
	 * 5. 작성일    : 2017. 2. 16. 오후 5:22:25
	 * </PRE>
	 *   @return void
	 *   @param customerInfo
	 */
	void insertCustInfoExt(@Param("custInfo")CustomerInfoVO customerInfo);

	/**
	 * <PRE>
	 * 1. MethodName: updateCustInfoExt
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객담당자 정보 수정(EXT_ID)
	 * 4. 작성자    : hyewon
	 * 5. 작성일    : 2017. 2. 16. 오후 6:04:24
	 * </PRE>
	 *   @return void
	 *   @param customerInfo
	 */
	void updateCustInfoExt(@Param("custInfo")CustomerInfoVO customerInfo);

	/**
	 * <PRE>
	 * 1. MethodName: insertCustHistInfoExt
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객담당자 정보 히스토리 저장
	 * 4. 작성자    : hyewon
	 * 5. 작성일    : 2017. 2. 17. 오전 9:27:55
	 * </PRE>
	 *   @return void
	 *   @param customerInfoVO
	 *   @param today
	 */
	void insertCustHistInfoExt(@Param("custInfo")CustomerInfoVO customerInfoVO, @Param("today")String today);
}

