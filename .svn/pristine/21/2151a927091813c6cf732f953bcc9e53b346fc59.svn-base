package com.ntels.ccbs.system.mapper.configuration.approvalMng;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;
import com.ntels.ccbs.system.domain.configuration.approvalMng.ApprovalMng;



@Component
public interface ApprovalMngMapper {
	int getApprovalMngCnt(@Param(value = "approvalMng") ApprovalMng approvalMng);
	List<ApprovalMng> getApprovalMngList(@Param(value = "approvalMng") ApprovalMng approvalMng, @Param(value="start")int start, @Param(value="end") int end);
	int getAddApprovalCnt(@Param(value = "approvalMng") ApprovalMng approvalMng);
	List<ApprovalMng> getAddApprovalList(@Param(value = "approvalMng") ApprovalMng approvalMng, @Param(value="start")int start, @Param(value="end") int end);
	int getApprovalCnt(@Param(value = "approvalMng") ApprovalMng approvalMng);
	List<ApprovalMng> getApprovalList(@Param(value = "approvalMng") ApprovalMng approvalMng, @Param(value="start")int start, @Param(value="end") int end);
	
	int deleteAprvmngMast(@Param(value = "approvalMng") ApprovalMng approvalMng);
	int insertAprvmngMast(@Param(value = "approvalMng") ApprovalMng approvalMng);
	
	int getAprvMastCount(@Param(value = "approvalMng") ApprovalMng approvalMng);
	int insertAprvMast(@Param(value = "approvalMng") ApprovalMng approvalMng);
	int insertAprvHist(@Param(value = "approvalMng") ApprovalMng approvalMng);
	
	List<ApprovalMng> getApprovalOkList(@Param(value = "approvalMng") ApprovalMng approvalMng, @Param(value="start")int start, @Param(value="end") int end);
	int updateAprvMast(@Param(value = "approvalMng") ApprovalMng approvalMng);
	
	List<ApprovalMng> getApprovalHistList(@Param(value = "approvalMng") ApprovalMng approvalMng, @Param(value="start")int start, @Param(value="end") int end);
	
}