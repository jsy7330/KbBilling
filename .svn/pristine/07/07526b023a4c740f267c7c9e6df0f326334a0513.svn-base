package com.ntels.ccbs.distribute.service.logistics.voucherMgt;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.LogisticsCenterVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueDtlVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VoucherItemVO;
import com.ntels.ccbs.system.domain.common.common.ProductMngVO;

public interface VoucherGenerateService {

	Integer vissueListCount(VissueVO vissueVO);
	
	List<VissueVO> vissueList(VissueVO vissueVO);
	
	Integer vissueDtlListCount(VissueDtlVO vissueDtlVO);
	
	List<VissueDtlVO> vissueDtlList(VissueDtlVO vissueDtlVO);
	
	Integer logisticsCenterCount(LogisticsCenterVO logisticsCenterVO);
	
	List<LogisticsCenterVO> searchLogisticsCenter(LogisticsCenterVO logisticsCenterVO);
	
	void addIssueVoucherBulkInfo(VissueVO vissueVO);
	
	int voucherItemCount(VoucherItemVO voucherItemVO);
	
	List<VoucherItemVO> searchVoucherItem(VoucherItemVO voucherItemVO);
	
	void saveVissueInfo(VissueVO vissueVO);
	
	void addOrder(VissueVO vissueVO);
	
	Integer vissuePoInfoListCount(VissueVO vissueVO);
	
	List<VissueVO> vissuePoInfoList(VissueVO vissueVO);
	
	void saveConfirmOrder(VissueVO vissueVO);
	
	void saveCancelOrder(VissueVO vissueVO);
	
	void saveVoucherTransfer(VissueVO vissueVO);
	
	public List<ProductMngVO> productList(ProductMngVO productMngVO, int page,	int perPage);

	int productCount(ProductMngVO productMngVO);
	
}
