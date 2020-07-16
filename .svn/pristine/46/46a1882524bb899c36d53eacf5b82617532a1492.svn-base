package com.ntels.ccbs.charge.mapper.billing.billing;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.billing.billing.BillingInfoItemVO;

@Component
public interface BillingInfoItemMapper {

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
	List<BillingInfoItemVO> billingInfoItemListAction(
		@Param(value ="billingInfoItemVO") BillingInfoItemVO billingInfoItemVO
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );

	int billingInfoItemListCount(@Param(value ="billingInfoItemVO") BillingInfoItemVO billingInfoItemVO);
}
