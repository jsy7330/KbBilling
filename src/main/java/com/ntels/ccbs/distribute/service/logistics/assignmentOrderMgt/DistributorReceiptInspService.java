package com.ntels.ccbs.distribute.service.logistics.assignmentOrderMgt;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt.DistributorReceiptInspVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public interface DistributorReceiptInspService {
	
	List<DistributorReceiptInspVO> inoutDtlList(DistributorReceiptInspVO distributorReceiptInspVO, int page, int perPage);
	
	int inoutDtlCount(DistributorReceiptInspVO distributorReceiptInspVO);
	
	List<DistributorReceiptInspVO> inoutDtlList2(DistributorReceiptInspVO distributorReceiptInspVO);
	
	List<DistributorReceiptInspVO> inoutDtlList3(DistributorReceiptInspVO distributorReceiptInspVO);
	
	int insertInsp(DistributorReceiptInspVO distributorReceiptInspVO, SessionUser session);
	
	int insertInConf(DistributorReceiptInspVO distributorReceiptInspVO, SessionUser session);
	
	int insertInRefuse(DistributorReceiptInspVO distributorReceiptInspVO, SessionUser session);
	
}


