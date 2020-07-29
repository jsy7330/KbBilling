package com.ntels.ccbs.charge.service.billing.billingAdjust.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingBasicCustInfoVO;
import com.ntels.ccbs.charge.mapper.billing.billingAdjust.BillingAdjustMapper;
import com.ntels.ccbs.charge.mapper.billing.billingAdjust.BillingAfterAdjustMapper;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAfterAdjustService;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class BillingAfterAdjustServiceImpl implements BillingAfterAdjustService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SequenceService sequenceService;

	/** AttributeMapper Autowired. */
	@Autowired
	private BillingAfterAdjustMapper billingAfterAdjustMapper;
	
	@Autowired
	private BillingAdjustMapper BillingAdjustMapper;
	
	@Override
	public List<BillingAdjustVO> getPymList(BillingAdjustVO billingAdjust) {
		if(billingAdjust.getSoId() == null){
			//취소 상태
			throw new ServiceException("MSG.M10.MSG00035");
		}
		
		return billingAfterAdjustMapper.getPymList(billingAdjust);
	}
	
	@Override
	public List<BillingAdjustVO> getBillList(BillingAdjustVO billingAdjust) {
		return billingAfterAdjustMapper.getBillList(billingAdjust);
	}
	
	@Override
	public List<BillingAdjustVO> getPymRcpt(BillingAdjustVO billingAdjust) {
		return billingAfterAdjustMapper.getPymRcpt(billingAdjust);
	}
	
	@Override
	public int modAdjTgtList(BillingAdjustVO adjAply, List<BillingAdjustVO> adjAplyDtl, String gubun) {
		
		int count = 0;
		String poNo = "";
		
		if("I".equals(gubun)){
			poNo = sequenceService.createNewSequence("BL003", 10);   //조정 seq
			adjAply.setAdjNo(poNo);
			adjAply.setApplDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			adjAply.setRegDate(DateUtil.sysdate());
			
			count = billingAfterAdjustMapper.insertAdjAply(adjAply);
			
			if(count == 0){
				throw new ServiceException("MSG.M10.MSG00035");
			}else{
				for(BillingAdjustVO billingAdjustVO : adjAplyDtl){
					
					billingAdjustVO.setAdjNo(poNo);
					billingAdjustVO.setApplDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
					billingAdjustVO.setRegDate(DateUtil.sysdate());
					
					count += billingAfterAdjustMapper.insertAdjAplyDtl(billingAdjustVO);
				}
			}
		}
		
		if("U".equals(gubun)){
			adjAply.setRegDate(DateUtil.sysdate());
			count = billingAfterAdjustMapper.updateAdjAply(adjAply);
			
			count = billingAfterAdjustMapper.deleteAdjAplyDtl(adjAply);
			
			for(BillingAdjustVO billingAdjustVO : adjAplyDtl){
				
				billingAdjustVO.setAdjNo(adjAply.getAdjNo());
				billingAdjustVO.setApplDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
				billingAdjustVO.setRegDate(DateUtil.sysdate());
				
				count += billingAfterAdjustMapper.insertAdjAplyDtl(billingAdjustVO);
			}

		}
		return count;
	}
	
	@Override
	public int cancelAdjList(BillingAdjustVO adjNo) {
		int count = 0;
		
		count = billingAfterAdjustMapper.deleteAdjAply(adjNo);
		
		count = billingAfterAdjustMapper.deleteAdjAplyDtl(adjNo);
		
		return count;
	}

	@Override
	public BillingBasicCustInfoVO getBasicCustInfo(String soId, String pymAcntId) {
		return BillingAdjustMapper.getBasicCustInfo(soId, pymAcntId);
	}
}
