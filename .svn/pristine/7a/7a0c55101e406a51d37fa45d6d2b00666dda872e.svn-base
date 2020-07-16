package com.ntels.ccbs.charge.mapper.billing.billing;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.billing.billing.NewBillingInfoItemVO;

@Component
public interface NewBillingInfoItemMapper {

	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attribute
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	List<NewBillingInfoItemVO> newBillingInfoItemListAction(
		@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );

	int newBillingInfoItemListCount(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	List<NewBillingInfoItemVO> getNewSetItmNm(
		@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO
	);
	
	List<NewBillingInfoItemVO> getBillSetupItemComboList(
		@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO, String referenceTypeCd
	);
	
	int getBillSetupItemAddValidate(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int getBillSetupItemInsertValidate(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	String getBillSetupItemMaxId(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int insertBillSetup(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int insertBillSetupItem(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int getBillSetupItemUpdateValidate(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int updateBillSetup(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int updateBillSetupItem(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int updateBillSetupItemCycle(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int deleteBillSetup(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
	
	int deleteBillSetupItem(@Param(value ="newBillingInfoItemVO") NewBillingInfoItemVO newBillingInfoItemVO);
}
