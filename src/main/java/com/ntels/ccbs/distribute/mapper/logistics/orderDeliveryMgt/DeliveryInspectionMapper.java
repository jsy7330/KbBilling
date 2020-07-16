package com.ntels.ccbs.distribute.mapper.logistics.orderDeliveryMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.DeliveryInspectionVO;

@Component
public interface DeliveryInspectionMapper {

	List<DeliveryInspectionVO> inOutActncList(
			@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int inOutActncCount(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> inActncList(
			@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int inActncCount(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertInout(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertInoutDtl(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertInoutStatProcHist(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int updateInout(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int updateInoutDtl(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int deleteInout(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int deleteInoutDtl(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectInOutEqtList(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectInOutUsimList(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectInOutVEqtList(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertEqt(@Param(value = "deliveryInspectionVOs") List<DeliveryInspectionVO> deliveryInspectionVOs);
	
	int insertEqtOthsAttr(@Param(value = "deliveryInspectionVOs") List<DeliveryInspectionVO> deliveryInspectionVOs);
	
	int insertInoutEqt(@Param(value = "deliveryInspectionVOs") List<DeliveryInspectionVO> deliveryInspectionVOs);
	
	int updateInoutPrgrStatCd(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertUsim(@Param(value = "deliveryInspectionVOs") List<DeliveryInspectionVO> deliveryInspectionVOs);
	
	int insertUsimOthsAttr(@Param(value = "deliveryInspectionVOs") List<DeliveryInspectionVO> deliveryInspectionVOs);
	
	int updateInoutEqtApprYn(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int updateEqt(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectInoutEqt(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertUpdProcHist(@Param(value = "deliveryInspectionVOs") List<DeliveryInspectionVO> deliveryInspectionVOs);
	
	int updateInout2(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int updateUsim(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int getActncMinusInOutQty(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int deleteActncIdlDtl(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectInoutDtlActncNoList(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertAsgn(@Param(value = "deliveryInspectionVOs") List<DeliveryInspectionVO> deliveryInspectionVOs);
	
	DeliveryInspectionVO  selectVissue(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertInoutEqt2(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int updateVeqt(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int insertVeqtTrans(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int updateInoutEqtApprYn2(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int selectEqtCount(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	int updateVeqt2(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectSalesDivision(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);
	
	List<DeliveryInspectionVO> selectSalesTeam(@Param(value = "deliveryInspectionVO") DeliveryInspectionVO deliveryInspectionVO);	
	
	
}
