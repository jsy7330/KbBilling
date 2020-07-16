package com.ntels.ccbs.distribute.service.logistics.assignmentOrderMgt.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt.OrderDistributorVO;
import com.ntels.ccbs.distribute.mapper.logistics.assignmentOrderMgt.OrderDistributorMapper;
import com.ntels.ccbs.distribute.service.logistics.assignmentOrderMgt.OrderDistributorService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class OrderDistributorServiceImpl implements OrderDistributorService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderDistributorMapper orderDistributorMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<OrderDistributorVO> orderMngOrgList(
			OrderDistributorVO orderDistributorVO) {
		return orderDistributorMapper.orderMngOrgList(orderDistributorVO);
	}

	@Override
	public List<OrderDistributorVO> asgnList(
			OrderDistributorVO orderDistributorVO, int page, int perPage) {
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return orderDistributorMapper.asgnList(orderDistributorVO, start, end);
	}

	@Override
	public int asgnCount(OrderDistributorVO orderDistributorVO) {
		return orderDistributorMapper.asgnCount(orderDistributorVO);
	}

	@Override
	public List<OrderDistributorVO> ordList(
			OrderDistributorVO orderDistributorVO) {
		return orderDistributorMapper.ordList(orderDistributorVO);
	}

	@Override
	public List<OrderDistributorVO> ordPreList(
			OrderDistributorVO orderDistributorVO) {
		return orderDistributorMapper.ordPreList(orderDistributorVO);
	}

	@Override
	public int insertOrd(List<OrderDistributorVO> orderDistributorVOs, SessionUser session) {
		
		int count = 0;
		
		for(int i=0; i < orderDistributorVOs.size(); i++){
			//oldOrdQty
			if(orderDistributorVOs.get(i).getOldOrdQty().equals(orderDistributorVOs.get(i).getOrdQty())){
				continue; // 이전주문수량 과 현주문수량이 같을경우 skip
			}
			
			
			orderDistributorVOs.get(i).setRegrId(session.getUserId());
			orderDistributorVOs.get(i).setChgrId(session.getUserId());
			orderDistributorVOs.get(i).setRegDate(DateUtil.sysdate());
			orderDistributorVOs.get(i).setChgDate(DateUtil.sysdate());
			orderDistributorVOs.get(i).setStatProcId(session.getUserId());
			orderDistributorVOs.get(i).setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			
			if (orderDistributorVOs.get(i).getOrdNo() != null && !orderDistributorVOs.get(i).getOrdNo().equals("")) { // 주문번호가 널이 아니면(주문 수정일경우)
				
				if (!orderDistributorVOs.get(i).getOrdPrgrStatCd().equals("20")) { // 주문확정상태가 아니면
					
					orderDistributorVOs.get(i).setOrdPrgrStatCd("10");
					count += orderDistributorMapper.updateOrd(orderDistributorVOs.get(i));
                }
				
				
			}else{
				
				if(orderDistributorVOs.get(i).getOrdQty() != null && !orderDistributorVOs.get(i).getOrdQty().equals("") && !orderDistributorVOs.get(i).getOrdQty().equals("0") ){
					
					String ordNo = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_ORD_NO, "OR", 10);
					
					orderDistributorVOs.get(i).setOrdNo(ordNo);
					orderDistributorVOs.get(i).setOrdPrgrStatCd("10");
					count += orderDistributorMapper.insertOrd(orderDistributorVOs.get(i));
					orderDistributorMapper.insertOrdStatProcHist(orderDistributorVOs.get(i));
					
				}
				
			}
			
		}
		return count;
	}

	@Override
	public int deleteOrd(OrderDistributorVO orderDistributorVO) {
		
		orderDistributorVO.setStatProcId(orderDistributorVO.getChgrId());
		orderDistributorVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		int count = orderDistributorMapper.updateOrd2(orderDistributorVO);
		orderDistributorMapper.insertOrdStatProcHist(orderDistributorVO);
		
		return count;
	}

	@Override
	public String updateOrd(OrderDistributorVO orderDistributorVO) {
		
		orderDistributorVO.setStatProcId(orderDistributorVO.getChgrId());
		orderDistributorVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		//여신여부체크
		OrderDistributorVO loanVO = orderDistributorMapper.ordLoanInfo(orderDistributorVO);
		if(loanVO == null || loanVO.getLoanAvlAmt() == null || loanVO.getLoanAvlAmt().equals("") ){
			return "E01";	//여신테이블에 값이 없음.
		}else if(loanVO.getLoanGvFlg() == null || loanVO.getLoanGvFlg().equals("") || loanVO.getLoanGvFlg().equals("0")){
			return "E02";	//여신부여여부 체크 X
		}else if(Double.valueOf(loanVO.getLoanAvlAmt()) < Double.valueOf(orderDistributorVO.getOrdTotAmt() ) ){
			return "E03";	//여신가능 금액 초과
		}
		
		String count = String.valueOf(orderDistributorMapper.updateOrd2(orderDistributorVO) );
		
		orderDistributorMapper.insertOrdStatProcHist(orderDistributorVO);
		
		orderDistributorMapper.insertOrdIdlDtl(orderDistributorVO);
		
		String accNo = sequenceService.createNewSequence(Consts.SEQ_CODE.AC_ACC_NO, "1", 10);
		
		orderDistributorVO.setAccNo(accNo);
		orderDistributorVO.setAccDt(DateUtil.getDateStringYYYYMMDD(0));	//발생일자
		orderDistributorVO.setClsCd("01");
		orderDistributorVO.setLoanKndCd("10");
		orderDistributorVO.setCtrtId("");
		orderDistributorVO.setItm(orderDistributorVO.getItemId());
		orderDistributorVO.setQty(orderDistributorVO.getOrdQty());
		orderDistributorVO.setAmt(orderDistributorVO.getOrdTotAmt());
		orderDistributorVO.setLoanYn("Y");
		orderDistributorVO.setInoutId(orderDistributorVO.getOrdNo());
		
		orderDistributorMapper.insertBondRcptTr(orderDistributorVO);
		
		
		String loanUseOrd = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_LOAN_USE_ORD, "1", 10);
		orderDistributorVO.setLoanUseOrd(loanUseOrd);
		orderDistributorVO.setLoanUseTp("10");
		orderDistributorVO.setLoanUseDt(DateUtil.getDateStringYYYYMMDD(0));
		orderDistributorVO.setLoanUseAmt(orderDistributorVO.getOrdTotAmt());
		orderDistributorVO.setUseCnt(orderDistributorVO.getOrdQty());
		orderDistributorVO.setUseAmt(orderDistributorVO.getOrdTotAmt());
		orderDistributorVO.setUseTax(orderDistributorVO.getOrdAddTx());
		
		orderDistributorMapper.insertLoanUseHist(orderDistributorVO);
		
		orderDistributorVO.setLoanUseAmt(String.valueOf(Integer.parseInt(loanVO.getLoanUseAmt()) + Integer.parseInt(orderDistributorVO.getOrdTotAmt() ) ) ) ;
		//일반여신금액 + 특별여신금액 - 여신사용금액 - 입력여신사용금액
		int loanAvlAmt = Integer.parseInt(loanVO.getNormLoanAmt()) + Integer.parseInt(loanVO.getSpclLoanAmt()) - Integer.parseInt(loanVO.getLoanUseAmt()) - Integer.parseInt(orderDistributorVO.getOrdTotAmt()) ; 
		orderDistributorVO.setLoanAvlAmt(String.valueOf(loanAvlAmt));
		
		orderDistributorMapper.updateLoanInfo(orderDistributorVO);
		
		return count;
	}

	@Override
	public List<OrderDistributorVO> ordList2(
			OrderDistributorVO orderDistributorVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return orderDistributorMapper.ordList2(orderDistributorVO, start, end);
	}

	@Override
	public int ordCount2(OrderDistributorVO orderDistributorVO) {
		return orderDistributorMapper.ordCount2(orderDistributorVO);
	}
	
	
	

}
