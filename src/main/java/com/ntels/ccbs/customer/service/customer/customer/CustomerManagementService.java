package com.ntels.ccbs.customer.service.customer.customer;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoVO;

/**
 * <PRE>
 * 1. ClassName: CustomerManagementService
 * 2. FileName : CustomerManagementService.java
 * 3. Package  : com.ntels.ccbs.customer.service.customer.customer
 * 4. Comment  : 고객정보관리 서비스
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 5. 18. 오후 4:03:29
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 5. 18.    : 신규개발
 * </PRE>
 */
public interface CustomerManagementService {
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getCustInfoList
	 * 2. ClassName : CustomerManagementService
	 * 3. Comment   :고객정보리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 25. 오후 1:26:33
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
	public List<CustomerInfoVO> getCustInfoList(String soId, String custId, String custName, List<Map<String, Object>> soAuthList, String today, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: insertNewCustomerInfo
	 * 2. ClassName : CustomerManagementService
	 * 3. Comment   : 고객정보등록
	 * 
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 19. 오후 3:46:00
	 * </PRE>
	 *   @return void
	 *   @param customerInfo
	 *   @param today
	 *   @param lng
	 */
	public CustomerInfoVO insertNewCustomerInfo(CustomerInfoVO customerInfo, String today, String lng);


	/**
	 * <PRE>
	 * 1. MethodName: updateCustInfo
	 * 2. ClassName : CustomerManagementService
	 * 3. Comment   : 고객정보변경
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 25. 오전 11:09:17
	 * </PRE>
	 *   @return CustInfoVO
	 *   @param customerInfo
	 *   @param today
	 *   @param todayYYYYMMDD
	 *   @param lng
	 *   @return
	 */
	public CustomerInfoVO updateCustInfo(CustomerInfoVO customerInfo, String today, String todayYYYYMMDD, String lng);


}
