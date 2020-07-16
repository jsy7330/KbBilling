package com.ntels.ccbs.charge.service.billing.billing.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billing.BillingCycleInfoVO;
import com.ntels.ccbs.charge.mapper.billing.billing.BillingCycleInfoMapper;
import com.ntels.ccbs.charge.service.billing.billing.BillingCycleInfoService;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;

@Service
public class BillingCycleInfoServiceImpl implements BillingCycleInfoService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private BillingCycleInfoMapper billingCycleInfoMapper;

	@Override
	public List<BillingCycleInfoVO> BillingCycleInfoListAction(BillingCycleInfoVO billingCycleInfoVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return billingCycleInfoMapper.BillingCycleInfoListAction(billingCycleInfoVO, start, end);
	}
	
	@Override
	public int BillingCycleInfoListCount(BillingCycleInfoVO billingCycleInfoVO) {
		return billingCycleInfoMapper.BillingCycleInfoListCount(billingCycleInfoVO);
	}

	@Override
	public int insertBillingCycleInfo(BillingCycleInfoVO billingCycleInfoVO) {

		int dupCount = billingCycleInfoMapper.getBillSetupItemCycleCount(billingCycleInfoVO);
		if (dupCount > 0)
			throw new ServiceException("MSG.M14.MSG00016");
		
		int count = billingCycleInfoMapper.registerBillSetupItemCycle(billingCycleInfoVO);
		return count;
	}

	@Override
	public void updateBillingCycleInfo(BillingCycleInfoVO billingCycleInfoVO) {
		
		for(Map<String,Object> updateInfo : billingCycleInfoVO.getUpdateSetValList()){
			System.out.println("### billingCycleInfoVO.getUpdateSetValList() : " +billingCycleInfoVO.getUpdateSetValList());
			
			billingCycleInfoVO.setSoId((String)updateInfo.get("soId"));
			billingCycleInfoVO.setSoNm((String)updateInfo.get("soNm"));
			billingCycleInfoVO.setSetItmCd((String)updateInfo.get("setItmCd"));
			billingCycleInfoVO.setSetItmNm((String)updateInfo.get("setItmNm"));
			billingCycleInfoVO.setRequired((String)updateInfo.get("required"));
			billingCycleInfoVO.setFieldNature((String)updateInfo.get("fieldNature"));
			billingCycleInfoVO.setFieldStructure((String)updateInfo.get("fieldStructure"));
			billingCycleInfoVO.setFieldDigit((String)updateInfo.get("fieldDigit"));
			billingCycleInfoVO.setSetVal((String)updateInfo.get("setVal"));
			billingCycleInfoVO.setEftBgnYymm((String)updateInfo.get("eftBgnYymm"));
			billingCycleInfoVO.setEftEndYymm((String)updateInfo.get("eftEndYymm"));
			billingCycleInfoVO.setBillYymm((String)updateInfo.get("billYymm"));
			
			int deleteCnt = billingCycleInfoMapper.deleteBillSetupItemCycle(billingCycleInfoVO);
			if(deleteCnt == 1){
				int insertCnt = billingCycleInfoMapper.insertBillSetupItemCycle(billingCycleInfoVO);
			}
		}
	}

	@Override
	public int copyBillingCycleInfo(BillingCycleInfoVO billingCycleInfoVO) {
		int dupCount = billingCycleInfoMapper.getBillSetupItemCycleCopyValidate(billingCycleInfoVO);
		
		if(dupCount == 0) {
			throw new ServiceException("MSG.M06.MSG00028");
		}
		
		billingCycleInfoMapper.deleteBillSetupItemCycleCopy(billingCycleInfoVO);
		int count = billingCycleInfoMapper.insertBillSetupItemCycleCopy(billingCycleInfoVO);
		
		return count;
	}

	@Override
	public int updateBillSetupItemCycle(
			List<BillingCycleInfoVO> billingCycleInfoVOList, String userId) {
		
		int count = 0;
		Date chgDate = DateUtil.sysdate();
		for(BillingCycleInfoVO billingCycleInfoVO : billingCycleInfoVOList){
			
			billingCycleInfoVO.setChgDate(chgDate);
			billingCycleInfoVO.setChgId(userId);
			
			count = count + billingCycleInfoMapper.updateBillSetupItemCycle(billingCycleInfoVO);
		}
		
		return count;
	}

	
	
}
