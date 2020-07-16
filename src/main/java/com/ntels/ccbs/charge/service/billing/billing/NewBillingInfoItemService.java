package com.ntels.ccbs.charge.service.billing.billing;

import java.util.HashMap;
import java.util.List;

import com.ntels.ccbs.charge.domain.billing.billing.NewBillingInfoItemVO;

public interface NewBillingInfoItemService {

	public List<NewBillingInfoItemVO> newBillingInfoItemListAction(NewBillingInfoItemVO newBillingInfoItemVO, int page, int perPage);
	
	public int newBillingInfoItemListCount(NewBillingInfoItemVO newBillingInfoItemVO);
	
	public List<NewBillingInfoItemVO> getNewSetItmNm(NewBillingInfoItemVO newBillingInfoItemVO);
	
	public List<NewBillingInfoItemVO> getBillSetupItemComboList(NewBillingInfoItemVO newBillingInfoItemVO, String referenceTypeCd);
	
	public int getBillSetupItemInsertValidate(NewBillingInfoItemVO newBillingInfoItemVO);
	
	public int getBillSetupItemAddValidate(NewBillingInfoItemVO newBillingInfoItemVO);
	
	public int insertNewBillingInfoItemInfo(NewBillingInfoItemVO newBillingInfoItemVO);
	
	public int updateNewBillingInfoItemInfo(NewBillingInfoItemVO newBillingInfoItemVO);
	
	public int deleteNewBillingInfoItemInfo(NewBillingInfoItemVO newBillingInfoItemVO);
}
