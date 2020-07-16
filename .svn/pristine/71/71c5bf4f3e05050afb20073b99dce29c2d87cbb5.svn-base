package com.ntels.ccbs.distribute.service.logistics.logisticsCenterReceIssue;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.logisticsCenterReceIssue.LogisticsCenterIssueInspVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public interface LogisticsCenterIssueInspService {

	List<LogisticsCenterIssueInspVO> lgstCntrOrdList(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO, int page, int perPage);

	int lgstCntrOrdCount(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutList(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutEqtList(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutUsimList(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	List<LogisticsCenterIssueInspVO> lgstCntrOutVeqtList(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO);
	
	int insertInoutList(List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs, SessionUser session);
	
	int deleteOut(List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs, SessionUser session);
	
	int insertOutConf(List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs, SessionUser session);
	
}
