package com.ntels.ccbs.charge.mapper.batchprocmng.receipterpsndmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.receipterpsndmng.ErpZmis11VO;
import com.ntels.ccbs.charge.domain.batchprocmng.receipterpsndmng.ErpZmis14VO;
import com.ntels.ccbs.charge.domain.batchprocmng.receipterpsndmng.ReceiptErpIntrVO;

@Component
public interface ReceiptErpSndMapper {

	/**
	 * 수납 ERP 테이블에 등록할 데이터를 조회한다(ERP_ZMIS11)
	 * @param dataOccDt
	 * @return List<ErpZmis11VO>
	 */
	List<ErpZmis11VO> getErpZmis11List(@Param("dataOccDt") String dataOccDt);
	
	/**
	 * 수납 ERP 테이블에 등록한다(ERP_ZMIS11)
	 * @param erpZmis11VO
	 * @return
	 */
	int insertErpZmis11(@Param("erpZmis11VO") ErpZmis11VO erpZmis11VO);
	
	/**
	 * 대체 ERP 테이블에 등록할 데이터를 조회한다(ERP_ZMIS14)
	 * @param dataOccDt
	 * @return List<RefundApplVO>
	 */
	List<ErpZmis14VO> getErpZmis14List(@Param("dataOccDt") String dataOccDt);
	
	/**
	 * 대체 ERP 테이블에 등록한다(ERP_ZMIS14)
	 * @param erpZmis14VO
	 * @return
	 */
	int insertErpZmis14(@Param("erpZmis14VO") ErpZmis14VO erpZmis14VO);
	
	/**
	 * ERP 전송여부, 전송일자를 UPDATE 한다.
	 * @param refundApplVO
	 * @return
	 */
	int updateErpSysSndYn(@Param("receiptErpIntrVO") ReceiptErpIntrVO receiptErpIntrVO);
	
}
