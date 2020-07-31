package com.ntels.ccbs.charge.service.billing.billingAdjust.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.mapper.billing.billingAdjust.BillingBeforeAdjustMapper;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingBeforeAdjustService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class BillingBeforeAdjustServiceImpl implements BillingBeforeAdjustService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SequenceService sequenceService;

	/** AttributeMapper Autowired. */
	@Autowired
	private BillingBeforeAdjustMapper billingBeforeAdjustMapper;
	
	@Override
	public List<BillingAdjustVO> getPymList(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.getPymList(billingAdjust);
	}
	
	@Override
	public List<BillingAdjustVO> getPymBillList(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.getPymBillList(billingAdjust);
	}
	
	@Override
	public List<BillingAdjustVO> getPymRcpt(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.getPymRcpt(billingAdjust);
	}
	
	@Override
	public int getApplBeforeCount(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.getApplCount(billingAdjust);
	}
	
	@Override
	public int getApplYymmCount(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.getApplYymmCount(billingAdjust);
	}
	
	@Override
	public int getApplHopeYymm(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.getApplHopeYymm(billingAdjust);
	}
	
	@Override
	public int modAdjTgtList(BillingAdjustVO adjAply, List<BillingAdjustVO> adjAplyDtl, String gubun) {
		
		String poNo = "";
		int count = 0 ;
		
		if("I".equals(gubun)){ 
			if(adjAply.getAdjNo() == null || adjAply.getAdjNo() == "") {
											
				poNo = sequenceService.createNewSequence("BL003", 10); //조정 seq 
				adjAply.setAdjNo(poNo);			 				
				adjAply.setChgDate(DateUtil.sysdate());
				adjAply.setChgrId(Consts.IF_ADM_ID);	
				adjAply.setApplDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));		
				
				count = billingBeforeAdjustMapper.insertReqAppl(adjAply);
				
				if(count == 0){
					throw new ServiceException("MSG.M10.MSG00035");
					
				}else{										
					for(BillingAdjustVO billingAdjustVO : adjAplyDtl){
						
						billingAdjustVO.setAdjNo(poNo);
						billingAdjustVO.setRegrId(Consts.IF_ADM_ID);
						billingAdjustVO.setRegDate(DateUtil.sysdate());
						billingAdjustVO.setChgrId(Consts.IF_ADM_ID);
						billingAdjustVO.setChgDate(DateUtil.sysdate());
						
						if("I".equals(billingAdjustVO.getGubun())) {	
							count += billingBeforeAdjustMapper.insertReqDtlAppl(billingAdjustVO);
						}else {
							count += billingBeforeAdjustMapper.updateReqDtlAppl(billingAdjustVO);
						}
					}
				}
			}else { 
				for(BillingAdjustVO billingAdjustVO : adjAplyDtl){
					
					billingAdjustVO.setAdjNo(adjAply.getAdjNo());	//조정상세만 insert될 경우
					billingAdjustVO.setRegrId(Consts.IF_ADM_ID);
					billingAdjustVO.setRegDate(DateUtil.sysdate());
					billingAdjustVO.setChgrId(Consts.IF_ADM_ID);
					billingAdjustVO.setChgDate(DateUtil.sysdate());				
					
					if("I".equals(billingAdjustVO.getGubun())) {
						count += billingBeforeAdjustMapper.insertReqDtlAppl(billingAdjustVO);
					}else {
						count += billingBeforeAdjustMapper.updateReqDtlAppl(billingAdjustVO);
					}
				}
			}
		}
		
		if("U".equals(gubun)){			
			adjAply.setChgDate(DateUtil.sysdate());
			adjAply.setChgrId(Consts.IF_ADM_ID);	
			
			count = billingBeforeAdjustMapper.updateReqAppl(adjAply);
			
			for(BillingAdjustVO billingAdjustVO : adjAplyDtl){
				
				billingAdjustVO.setAdjNo(adjAply.getAdjNo());
				billingAdjustVO.setRegrId(Consts.IF_ADM_ID);
				billingAdjustVO.setRegDate(DateUtil.sysdate());
				billingAdjustVO.setChgrId(Consts.IF_ADM_ID);
				billingAdjustVO.setChgDate(DateUtil.sysdate());	
				
				if("I".equals(billingAdjustVO.getGubun())) {
					count += billingBeforeAdjustMapper.insertReqDtlAppl(billingAdjustVO);
					
				}else {
					count += billingBeforeAdjustMapper.updateReqDtlAppl(billingAdjustVO);
				}
			}
		}	
		return count;
	}	
	
	public int deleteReqAppl(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.deleteReqAppl(billingAdjust);
	}
	
	public int deleteReqDtlAppl(BillingAdjustVO billingAdjust) {
		return billingBeforeAdjustMapper.deleteReqDtlAppl(billingAdjust);
	}
	
}
