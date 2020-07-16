package com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.OrderPlacementVO;
import com.ntels.ccbs.distribute.mapper.logistics.orderDeliveryMgt.OrderPlacementMapper;
import com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt.OrderPlacementService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class OrderPlacementServiceImpl implements OrderPlacementService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderPlacementMapper orderPlacementMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<OrderPlacementVO> orderPlacementList(
			OrderPlacementVO orderPlacementVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return orderPlacementMapper.orderPlacementList(orderPlacementVO, start, end);
	}

	@Override
	public int orderPlacementCount(OrderPlacementVO orderPlacementVO) {
		return orderPlacementMapper.orderPlacementCount(orderPlacementVO);
	}

	@Override
	public int insertOrderPlacement(OrderPlacementVO orderPlacementVO) {
		
		String poNo = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_PO_NO, "PO", 10);
		orderPlacementVO.setPoNo(poNo);
		
		int count = orderPlacementMapper.insertOrderPlacement(orderPlacementVO);
		
		
		orderPlacementVO.setStatProcId(orderPlacementVO.getRegrId());
		orderPlacementVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		orderPlacementMapper.insertPoStatProcHist(orderPlacementVO);
		
		return count;
	}

	@Override
	public int updateOrderPlacement(OrderPlacementVO orderPlacementVO) {
		return orderPlacementMapper.updateOrderPlacement(orderPlacementVO);
	}

	@Override
	public int updatePoPrgrStatCd(List<OrderPlacementVO> orderPlacementVOs, SessionUser session) {
		
		int count = 0;
		
		for(OrderPlacementVO orderPlacementVO : orderPlacementVOs){
			
			orderPlacementVO.setRegrId(session.getUserId());
			orderPlacementVO.setChgrId(session.getUserId());
			orderPlacementVO.setRegDate(DateUtil.sysdate());
			orderPlacementVO.setChgDate(DateUtil.sysdate());
			
			count += orderPlacementMapper.updatePoPrgrStatCd(orderPlacementVO);
			
			orderPlacementVO.setStatProcId(orderPlacementVO.getRegrId());
			orderPlacementVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			
			orderPlacementMapper.insertPoStatProcHist(orderPlacementVO);
			
			if(orderPlacementVO.getParamType().equals("01")){	//발주확정
				orderPlacementMapper.insertPoIdlDtl(orderPlacementVO);
			}else{		//발주취소
				orderPlacementMapper.deletePoIdlDtl(orderPlacementVO);
			}
			
		}
		
		return count;
	}

	@Override
	public int insertActnc(OrderPlacementVO orderPlacementVO) {
		
		String actncNo = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_ACTNC_NO, "AT", 10);
		orderPlacementVO.setActncNo(actncNo);
		
		orderPlacementVO.setStatProcId(orderPlacementVO.getRegrId());
		orderPlacementVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		return orderPlacementMapper.insertActnc(orderPlacementVO);
	}

	@Override
	public List<OrderPlacementVO> actncList(OrderPlacementVO orderPlacementVO,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return orderPlacementMapper.actncList(orderPlacementVO, start, end);
	}

	@Override
	public int actncCount(OrderPlacementVO orderPlacementVO) {
		return orderPlacementMapper.actncCount(orderPlacementVO);
	}

	@Override
	public int insertActncIdlDtl(List<OrderPlacementVO> orderPlacementVOs, SessionUser session) {
		
		int count = 0;
		
		for(OrderPlacementVO orderPlacementVO : orderPlacementVOs){
			
			orderPlacementVO.setRegrId(session.getUserId());
			orderPlacementVO.setChgrId(session.getUserId());
			orderPlacementVO.setRegDate(DateUtil.sysdate());
			orderPlacementVO.setChgDate(DateUtil.sysdate());
			
			orderPlacementVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			
			orderPlacementMapper.updateActncPrgrStatCd(orderPlacementVO);
			
			orderPlacementMapper.updateLoanUsgAmt(orderPlacementVO);
			
			count += orderPlacementMapper.insertActncIdlDtl(orderPlacementVO);
			
		}
		
		return count;
	}

	@Override
	public String getTaxRate(String param) {
		return orderPlacementMapper.getTaxRate(param);
	}
	
	

}
