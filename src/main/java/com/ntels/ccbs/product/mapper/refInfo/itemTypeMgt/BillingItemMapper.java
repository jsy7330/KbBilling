package com.ntels.ccbs.product.mapper.refInfo.itemTypeMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.product.domain.refInfo.itemTypeMgt.BillingItem;

public interface BillingItemMapper {
	List<BillingItem> getInvoiceItemList(@Param(value = "billingItem") BillingItem billingItem,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	
	int getInvoiceItemListCount(@Param(value = "billingItem") BillingItem billingItem);
	int getInvoiceItmPriNo();
	int getInvoiceItemDupCnt(@Param(value = "billingItem") BillingItem billingItem);
	int addInvoiceItem(@Param(value = "billingItem") BillingItem billingItem);
	BillingItem getInvoiceItm(@Param(value = "billingItem") BillingItem billingItem);
	int getInvoiceItemDupExpCnt(@Param(value = "billingItem") BillingItem billingItem);
	int modInvoiceItm(@Param(value = "billingItem") BillingItem billingItem);
	int modInvoiceItmInactDt(@Param(value = "billingItem") BillingItem billingItem);
	int modInvoiceItmInactDtion(@Param(value = "billingItem") BillingItem billingItem);
}
