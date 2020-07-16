package com.ntels.ccbs.customer.mapper.contract.contract;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface InvoiceDetailMapper {

	/**
	 * <PRE>
	 * 1. MethodName: getBillList
	 * 2. ClassName : InvoiceDetailMapper
	 * 3. Comment   : 청구내역조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 3:14:24
	 * </PRE>
	 *   @return List<Map<String,Object>> 청구내역리스트
	 *   @param soId 사업ID
	 *   @param pymList 납부계정리스트
	 */
	List<Map<String, Object>> getBillList(@Param("soId") String soId, @Param("pymList") List<Map<String,Object>> pymList);

	/**
	 * <PRE>
	 * 1. MethodName: getPymList
	 * 2. ClassName : InvoiceDetailMapper
	 * 3. Comment   : 납부계정리스트
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 3:18:32
	 * </PRE>
	 *   @return List<Map<String,Object>> 납부계정 리스트
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 */
	List<Map<String, Object>> getPymList(@Param("soId") String soId, @Param("custId") String custId, @Param("ctrtId") String ctrtId);

	/**
	 * <PRE>
	 * 1. MethodName: getBillCtrtList
	 * 2. ClassName : InvoiceDetailMapper
	 * 3. Comment   : 계약별 청구내역 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 3:57:48
	 * </PRE>
	 *   @return List<Map<String,Object>> 계약별 청구내역
	 *   @param billSeqNo 청구일련번호
	 *   @param billYymm 청구년월
	 *   @param billDt 청구일자
	 *   @param pymAcntId 납부계정ID
	 */
	List<Map<String, Object>> getBillCtrtList(@Param("billSeqNo") String billSeqNo, @Param("billYymm") String billYymm, @Param("billDt") String billDt, @Param("pymAcntId") String pymAcntId);

	/**
	 * <PRE>
	 * 1. MethodName: getBillCtrtDtlList
	 * 2. ClassName : InvoiceDetailMapper
	 * 3. Comment   : 계약별 청구내역 상세
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 26. 오후 5:49:57
	 * </PRE>
	 *   @return List<Map<String,Object>> 계약별 청구내역 상세
	 *   @param billSeqNo 청구일련번호
	 *   @param billYymm 청구년월
	 *   @param billDt 청구일자
	 *   @param soId 사업ID
	 *   @param ctrtId 계약ID
	 *   @param pymAcntId 납부계정ID
	 */
	List<Map<String, Object>> getBillCtrtDtlList(@Param("billSeqNo") String billSeqNo, @Param("billYymm") String billYymm, @Param("billDt") String billDt, @Param("soId") String soId,
			@Param("ctrtId") String ctrtId, @Param("pymAcntId") String pymAcntId);
	
}