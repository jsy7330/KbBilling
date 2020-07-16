package com.ntels.ccbs.customer.service.contract.contract;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;

/**
 * <PRE>
 * 1. ClassName: OrderProcessService
 * 2. FileName : OrderProcessService.java
 * 3. Package  : com.ntels.ccbs.customer.service.contract.contract
 * 4. Comment  : 오더 진행 서비스
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 20. 오후 4:04:52
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 20.    : 신규개발
 * </PRE>
 */
public interface OrderProcessService {
	
	
	/**
	 * <PRE>
	 * 1. MethodName: precheckOrder
	 * 2. ClassName : OrderProcessService
	 * 3. Comment   : 오픈전 사전 체크
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 12. 오전 8:29:09
	 * </PRE>
	 *   @param orderCommon 오더요청정보
	 *   @throws ServiceException
	 */
	void precheckOrder(OrderCommonVO orderCommon) throws ServiceException;
	
	/**
	 * <PRE>
	 * 1. MethodName: createOrder
	 * 2. ClassName : OrderProcessService
	 * 3. Comment   : 오더 작성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 20. 오후 4:05:02
	 * </PRE>
	 *   @return OrderRequestInfoVO 오더요청정보
	 *   @param orderInfo 오더요청정보
	 *   @throws ServiceException
	 */
	OrderRequestInfoVO createOrder(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	/**
	 * <PRE>
	 * 1. MethodName: cancelOrder
	 * 2. ClassName : OrderProcessService
	 * 3. Comment   : 오더 취소 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 20. 오후 4:05:31
	 * </PRE>
	 *   @return OrderRequestInfoVO 오더요청정보
	 *   @param orderInfo 오더요청정보
	 *   @throws ServiceException
	 */
	OrderRequestInfoVO cancelOrder(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	/**
	 * <PRE>
	 * 1. MethodName: saveOrder
	 * 2. ClassName : OrderProcessService
	 * 3. Comment   : 오더 완료 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 20. 오후 4:05:50
	 * </PRE>
	 *   @return OrderRequestInfoVO 오더요청정보
	 *   @param orderInfo 오더요청정보
	 *   @throws ServiceException
	 */
	OrderRequestInfoVO saveOrder(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	/**
	 * <PRE>
	 * 1. MethodName: progressOrder
	 * 2. ClassName : OrderProcessService
	 * 3. Comment   : 오더 진행 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 5:01:49
	 * </PRE>
	 *   @return OrderRequestInfoVO 오더요청정보
	 *   @param orderInfo 오더요청정보
	 *   @throws ServiceException
	 */
	OrderRequestInfoVO progressOrder(OrderRequestInfoVO orderInfo) throws ServiceException;
}



