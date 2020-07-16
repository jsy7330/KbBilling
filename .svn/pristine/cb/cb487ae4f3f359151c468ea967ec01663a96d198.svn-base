package com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.OrderPlacementVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public interface OrderPlacementService {

	List<OrderPlacementVO> orderPlacementList(OrderPlacementVO orderPlacementVO, int page, int perPage);

	int orderPlacementCount(OrderPlacementVO orderPlacementVO);
	
	int insertOrderPlacement(OrderPlacementVO orderPlacementVO);
	
	int updateOrderPlacement(OrderPlacementVO orderPlacementVO);
	
	int updatePoPrgrStatCd(List<OrderPlacementVO> orderPlacementVOs, SessionUser session);
	
	int insertActnc(OrderPlacementVO orderPlacementVO);

	List<OrderPlacementVO> actncList(OrderPlacementVO orderPlacementVO, int page, int perPage);

	int actncCount(OrderPlacementVO orderPlacementVO);
	
	int insertActncIdlDtl(List<OrderPlacementVO> orderPlacementVOs, SessionUser session);
	
	String getTaxRate(String param);
	
}
