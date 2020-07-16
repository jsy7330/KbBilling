package com.ntels.ccbs.distribute.service.logistics.assignmentOrderMgt;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt.OrderDistributorVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public interface OrderDistributorService {

	List<OrderDistributorVO> orderMngOrgList(OrderDistributorVO orderDistributorVO);
	
	List<OrderDistributorVO> asgnList(OrderDistributorVO orderDistributorVO, int page, int perPage);

	int asgnCount(OrderDistributorVO orderDistributorVO);
	
	List<OrderDistributorVO> ordList(OrderDistributorVO orderDistributorVO);
	
	List<OrderDistributorVO> ordPreList(OrderDistributorVO orderDistributorVO);
	
	int insertOrd(List<OrderDistributorVO> orderDistributorVOs, SessionUser session);
	
	int deleteOrd(OrderDistributorVO orderDistributorVO);
	
	String updateOrd(OrderDistributorVO orderDistributorVO);
	
	List<OrderDistributorVO> ordList2(OrderDistributorVO orderDistributorVO, int page, int perPage);

	int ordCount2(OrderDistributorVO orderDistributorVO);
	
}
