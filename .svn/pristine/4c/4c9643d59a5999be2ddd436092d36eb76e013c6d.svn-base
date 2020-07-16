package com.ntels.ccbs.customer.mapper.customer.customer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoHistVO;
import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoVO;


/**
 * <PRE>
 * 1. ClassName: ContractManagementMapper
 * 2. FileName : ContractManagementMapper.java
 * 3. Package  : com.ntels.ccbs.customer.mapper.customer.customer
 * 4. Comment  : 고객정보변경이력
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 5. 26. 오후 1:14:06
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 5. 26.    : 신규개발
 * </PRE>
 */
@Component
public interface CustomerHistoryManagementMapper {
	
	/**
	 * <PRE>
	 * 1. MethodName: getCustChngHistListTotalCnt
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객정보변경이력 Cnt
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 25. 오후 2:49:10
	 * </PRE>
	 *   @return Integer
	 *   @param soId
	 *   @param custId
	 * @param today 
	 * @param custInfoHist 
	 *   @return
	 */
	Integer getCustChngHistListTotalCnt(@Param("soId") String soId, @Param("custId") String custId, 
			@Param("today")String today, @Param("custInfoHist")CustomerInfoHistVO custInfoHist);

	/**
	 * <PRE>
	 * 1. MethodName: getCustChngHistList
	 * 2. ClassName : CustomerManagementMapper
	 * 3. Comment   : 고객정보변경이력 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 25. 오후 2:49:30
	 * </PRE>
	 *   @return List<CustomerInfoHistVO>
	 *   @param soId
	 *   @param custId
	 *   @param today
	 *   @param sidx
	 *   @param sord
	 *   @param start
	 *   @param end
	 *   @param lng
	 * @param custInfoHist 
	 *   @return
	 */
	List<CustomerInfoHistVO> getCustChngHistList(@Param("soId")String soId,
			@Param("custId")String custId,
	        @Param("today")String today,
	        @Param("sidx")String sidx,
	        @Param("sord")String sord,
	        @Param("start")String start,
	        @Param("end")String end,
	        @Param("lng")String lng, 
	        @Param("custInfoHist")CustomerInfoHistVO custInfoHist);


}

