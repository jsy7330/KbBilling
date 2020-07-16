package com.ntels.ccbs.customer.service.customer.payment;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountInfoVO;



/**
 * <PRE>
 * 1. ClassName: CustomerManagementServiceImpl
 * 2. FileName : CustomerManagementServiceImpl.java
 * 3. Package  : com.ntels.ccbs.customer.service.customer.payment
 * 4. Comment  :납부계정 관리 서비스
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 4. 28. 오후 5:06:02
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 4. 28.    : 신규개발
 * </PRE>
 */
public interface PaymentAccountManagementService {


	public List<Map<String,Object>> getCustInfoList(String soId, String custName, String custId, List<Map<String, Object>> soAuthList);
	
	public Map<String, Object> getPymAcntInfoList(String soId, String custId, String today,  String sidx, String sort, int page, int rows, String lng);
	
	public void insertPymAcntInfo(PaymentAccountInfoVO pymInfo, String today, String lng) throws ServiceException;
	
	public void updatePymCustInfo(PaymentAccountInfoVO pymInfo, String today, String todayYYYYMMDD, String lng) throws ServiceException;
	
	public Map<String, Object> getPymAcntChngHistList(String soId, String custId, String today,  String sidx, String sort, int page, int rows, String lng);
	
	public Map<String, Object> getVirAcntList(String soId, String custId, String today,  String sidx, String sort, int page, int rows, String lng);
	
	public void processChngVirAcnt(String soId, String pymAcntId, String vrBnkCd, String vrAcntNo, String today,  String lng, String usrId);
}
