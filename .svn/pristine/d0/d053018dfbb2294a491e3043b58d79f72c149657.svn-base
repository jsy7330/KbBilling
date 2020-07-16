package com.ntels.ccbs.distribute.mapper.logistics.logisticsCenterReceIssue;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.logisticsCenterReceIssue.LogisticsCenterIssueInspVO;

@Component
public interface LogisticsCenterIssueInspMapper {

	List<LogisticsCenterIssueInspVO> lgstCntrOrdList(
			@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int lgstCntrOrdCount(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutList(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutEqtList(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutUsimList(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutVeqtList(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int insertInout(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int insertInoutDtl(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int insertInoutStatProcHist(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int insertInoutEqt(@Param(value = "logisticsCenterIssueInspVOs") List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs);
	
	int updateUsim(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int deleteInout(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int deleteInoutDtl(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int deleteInoutStatProcHist(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int updateUsim2(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int deleteInoutEqt(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrInOutEqtList(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutEqtCheckList(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int updateLgstCntrInOutEqtInfo(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int deleteLgstCntrInOutEqtInfo(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int countMinusInOutQty(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int updateEqtInfo(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int insertUpdProcHist(@Param(value = "logisticsCenterIssueInspVOs") List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs);
	
	int updateUsim3(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int updateVeqtInfoVoStatLgst(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> selectVeqtList(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int insertVeqtTrans(@Param(value = "logisticsCenterIssueInspVOs") List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs);
	
	int updateInOutInfo(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int insertProcIdlDtl(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int countOrdMinusInOutQty(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int deleteOrdIdlDtl(@Param(value = "logisticsCenterIssueInspVO") LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	
}
