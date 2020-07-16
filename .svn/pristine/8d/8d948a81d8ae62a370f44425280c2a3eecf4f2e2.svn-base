package com.ntels.ccbs.distribute.mapper.logistics.assignmentOrderMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt.DistributorReceiptInspVO;

@Component
public interface DistributorReceiptInspMapper {
	
	List<DistributorReceiptInspVO> inoutDtlList(
			@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int inoutDtlCount(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	List<DistributorReceiptInspVO> inoutDtlList2(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);

	List<DistributorReceiptInspVO> inoutDtlList3(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	List<DistributorReceiptInspVO> inoutEqtList(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	List<DistributorReceiptInspVO> inoutUsimList(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	List<DistributorReceiptInspVO> inoutVeqtList(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int insertInout(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int insertInoutStatProcHist(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int insertInoutDtl(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int insertInoutEqt(@Param(value = "distributorReceiptInspVOs") List<DistributorReceiptInspVO> distributorReceiptInspVOs);
	
	int updateInout(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int updateInoutEqt(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int deleteProcIdlDtl(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int updateEqt(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int updateUsim(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int updateVeqt(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	List<DistributorReceiptInspVO> selectVeqtList(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int insertVeqtTrans(@Param(value = "distributorReceiptInspVOs") List<DistributorReceiptInspVO> distributorReceiptInspVOs);
	
	int insertUpdProcHist(@Param(value = "distributorReceiptInspVOs") List<DistributorReceiptInspVO> distributorReceiptInspVOs);
	
	int insertProcIdlDtl(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int insertInoutEqt2(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	DistributorReceiptInspVO selectEqt(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int updateEqt2(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	DistributorReceiptInspVO selectUsim(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int updateUsim2(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	DistributorReceiptInspVO selectVeqt(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int updateVeqt2(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
	int insertUpdProcHist2(@Param(value = "distributorReceiptInspVO") DistributorReceiptInspVO distributorReceiptInspVO);
	
}
