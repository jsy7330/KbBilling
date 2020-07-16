package com.ntels.ccbs.system.service.configuration.approvalMng;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.system.domain.configuration.approvalMng.ApprovalMng;

public interface ApprovalMngService {
	//public List<ApprovalMng> getApprovlaMngList(String searchAprvId, String searchAprvNm);
	public int getApprovalMngCnt(ApprovalMng approvalMng);
	public List<ApprovalMng> getApprovalMngList(ApprovalMng approvalMng, int page, int perPage);
	public int getApprovalCnt(ApprovalMng approvalMng);
	public List<ApprovalMng> getApprovalList(ApprovalMng approvalMng, int page, int perPage);	
	public int getAddApprovalCnt(ApprovalMng approvalMng);
	public List<ApprovalMng> getAddApprovalList(ApprovalMng approvalMng, int page, int perPage);
	
	int saveAprvDtlList(List<ApprovalMng> approvalMngs);
	
	int saveAprvReport(ApprovalMng approvalMng);
	
	List<ApprovalMng> getApprovalOkList(ApprovalMng approvalMng, int page, int perPage);
	
	int saveApproval(ApprovalMng approvalMng);
	
	List<ApprovalMng> getApprovalHistList(ApprovalMng approvalMng, int page, int perPage);
	
}