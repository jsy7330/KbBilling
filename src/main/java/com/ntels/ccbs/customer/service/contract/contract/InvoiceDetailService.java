package com.ntels.ccbs.customer.service.contract.contract;

import java.util.List;
import java.util.Map;

public interface InvoiceDetailService {

	/**
	 * <PRE>
	 * 1. MethodName: getBillList
	 * 2. ClassName : InvoiceDetailService
	 * 3. Comment   : 청구내역조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 3:54:52
	 * </PRE>
	 *   @return List<Map<String,Object>> 청구내역리스트
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param pymAcntId 납부계정ID
	 */
	List<Map<String, Object>> getBillList(String soId, String custId, String ctrtId, String pymAcntId);

	/**
	 * <PRE>
	 * 1. MethodName: getBillCtrtList
	 * 2. ClassName : InvoiceDetailService
	 * 3. Comment   : 계약별청구내역조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 3:55:25
	 * </PRE>
	 *   @return List<Map<String,Object>> 계약별청구내역리스트
	 *   @param billSeqNo 청구시퀀스
	 *   @param billYymm 청구년월
	 *   @param billDt 청구일자
	 *   @param pymAcntId 납부계정ID
	 */
	List<Map<String, Object>> getBillCtrtList(String billSeqNo, String billYymm, String billDt, String pymAcntId);

	/**
	 * <PRE>
	 * 1. MethodName: getBillCtrtDtlList
	 * 2. ClassName : InvoiceDetailService
	 * 3. Comment   : 계약별 청구내역 상세
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 5:47:47
	 * </PRE>
	 *   @return List<Map<String,Object>> 청구내역상세
	 *   @param billSeqNo Bill Seq
	 *   @param billYymm 청구년월
	 *   @param billDt 청구일자
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID 
	 *   @param pymAcntId 납부계정ID
	 */
	List<Map<String, Object>> getBillCtrtDtlList(String billSeqNo, String billYymm, String billDt, String soId, String ctrtId,
			String pymAcntId);

	
}