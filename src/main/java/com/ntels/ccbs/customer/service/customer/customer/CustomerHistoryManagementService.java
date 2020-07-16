package com.ntels.ccbs.customer.service.customer.customer;

import java.util.Map;

import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoHistVO;

/**
 * <PRE>
 * 1. ClassName: ContractManagementService
 * 2. FileName : ContractManagementService.java
 * 3. Package  : com.ntels.ccbs.customer.service.customer.customer
 * 4. Comment  : 고객정보변경이력관리
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 5. 26. 오후 1:10:22
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 5. 26.    : 신규개발
 * </PRE>
 */
public interface CustomerHistoryManagementService {


	/**
	 * <PRE>
	 * 1. MethodName: getCustChngHistList
	 * 2. ClassName : ContractManagementService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 26. 오후 1:10:46
	 * </PRE>
	 *   @return Map<String,Object>
	 *   @param soId
	 *   @param custId
	 *   @param today
	 *   @param sidx
	 *   @param sord
	 *   @param page
	 *   @param rows
	 *   @param lng
	 * @param custInfoHist 
	 *   @return
	 */
	public Map<String, Object> getCustChngHistList(String soId, String custId,
	        String today, String sidx, String sord, int page, int rows,
	        String lng, CustomerInfoHistVO custInfoHist);

}
