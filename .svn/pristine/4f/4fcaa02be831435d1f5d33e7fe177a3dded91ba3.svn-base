package com.ntels.ccbs.customer.service.contract.receipt;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.domain.contract.receipt.ReceiptCounselVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;

public interface ReceiptCounselService {


	/**
	 * <PRE>
	 * 1. MethodName: getRcptLvlCodeList
	 * 2. ClassName : CommonDataService
	 * 3. Comment   : 상담 대중소 분류 가져오기
	 * 4. 작성자    : ekyun
	 * 5. 작성일    : 2016. 8. 2. 오후 3:54:39
	 * </PRE>
	 *   @return List<CommonCodeVO>
	 *   @param grpCd
	 *   @param refCode
	 *   @param lng
	 *   @return
	 */
	public List<ReceiptCounselVO> getRcptLvlCodeList(String commonGrp, String refCode, String lng);

	Map<String, Object> getRcptTabList(String searchStatDt, String searchEndDt, String cnslStat, String condCustId, String selRcptLvl1, String selRcptLvl2,String selRcptLvl3,String rcptYn,String elapse, String elapseDate, String orgId,
	        String sidx, String sord, int page, int rows, String lng,
	        String today);
	ReceiptCounselVO getRcptInfo(String rcptId, String lng, String today);	
	
}
