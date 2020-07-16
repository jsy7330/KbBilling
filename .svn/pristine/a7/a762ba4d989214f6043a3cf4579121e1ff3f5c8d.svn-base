package com.ntels.ccbs.distribute.mapper.logistics.orderDeliveryMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.OrderPlacementVO;

@Component
public interface OrderPlacementMapper {

	List<OrderPlacementVO> orderPlacementList(
			@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int orderPlacementCount(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int insertOrderPlacement(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int insertPoStatProcHist(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int updateOrderPlacement(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int updatePoPrgrStatCd(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int insertPoIdlDtl(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int deletePoIdlDtl(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int insertActnc(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	List<OrderPlacementVO> actncList(
			@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int actncCount(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);	
	
	int updateActncPrgrStatCd(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int updateLoanUsgAmt(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	int insertActncIdlDtl(@Param(value = "orderPlacementVO") OrderPlacementVO orderPlacementVO);
	
	String getTaxRate(String param);
}
