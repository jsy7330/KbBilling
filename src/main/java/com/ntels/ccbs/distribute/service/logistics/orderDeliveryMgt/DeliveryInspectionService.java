package com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.DeliveryInspectionVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public interface DeliveryInspectionService {

	List<DeliveryInspectionVO> inOutActncList(DeliveryInspectionVO deliveryInspectionVO, int page, int perPage);

	int inOutActncCount(DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> inActncList(DeliveryInspectionVO deliveryInspectionVO, int page, int perPage);

	int inActncCount(DeliveryInspectionVO deliveryInspectionVO);
	
	int insertInout(DeliveryInspectionVO deliveryInspectionVO);
	
	int updateInout(DeliveryInspectionVO deliveryInspectionVO);
	
	int deleteInout(DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectInOutEqtList(DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectInOutUsimList(DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectInOutVEqtList(DeliveryInspectionVO deliveryInspectionVO);
	
	int insertInoutList(List<DeliveryInspectionVO> deliveryInspectionVOs, SessionUser session);
	
	int insertInspection(List<DeliveryInspectionVO> deliveryInspectionVOs, SessionUser session);
	
	int insertWearingAc(DeliveryInspectionVO deliveryInspectionVO, SessionUser session);
	
	List<DeliveryInspectionVO> selectSalesDivision(DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectSalesTeam(DeliveryInspectionVO deliveryInspectionVO);
	
	
}
