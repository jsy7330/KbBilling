package com.ntels.ccbs.product.service.refInfo.itemTypeMgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.itemTypeMgt.BillingItem;
import com.ntels.ccbs.product.mapper.refInfo.itemTypeMgt.BillingItemMapper;
import com.ntels.ccbs.product.service.refInfo.itemTypeMgt.BillingItemService;

@Service
public class BillingItemServiceImpl implements BillingItemService {

	@Autowired
	private BillingItemMapper billingItemMapper;

	@Override
	public List<BillingItem> getInvoiceItemList(BillingItem billingItem,
			int page, int perPage) {

		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return billingItemMapper.getInvoiceItemList(billingItem, start, end);
	}

	@Override
	public int getInvoiceItemListCount(BillingItem billingItem) {
		return billingItemMapper.getInvoiceItemListCount(billingItem);
	}

	@Override
	public int getInvoiceItmPriNo() {
		return billingItemMapper.getInvoiceItmPriNo();
	}

	@Override
	public int getInvoiceItemDupCnt(BillingItem billingItem) {
		return billingItemMapper.getInvoiceItemDupCnt(billingItem);
	}

	@Override
	public int addInvoiceItem(BillingItem billingItem) {
		return billingItemMapper.addInvoiceItem(billingItem);
	}

	@Override
	public BillingItem getInvoiceItm(BillingItem billingItem) {
		return billingItemMapper.getInvoiceItm(billingItem);
	}

	@Override
	public int getInvoiceItemDupExpCnt(BillingItem billingItem) {
		return billingItemMapper.getInvoiceItemDupExpCnt(billingItem);
	}

	@Override
	public int modInvoiceItm(BillingItem billingItem) {
		return billingItemMapper.modInvoiceItm(billingItem);
	}

	@Override
	public int modInvoiceItmInactDt(BillingItem billingItem) {
		return billingItemMapper.modInvoiceItmInactDt(billingItem);
	}

	@Override
	public int modInvoiceItmInactDtion(BillingItem billingItem) {
		return billingItemMapper.modInvoiceItmInactDtion(billingItem);
	}
	
	
	

}
