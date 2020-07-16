package com.ntels.ccbs.distribute.mapper.logistics.referenceInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.PurchaseUnitPriceVO;

@Component
public interface PurchaseUnitPriceMapper {

	List<PurchaseUnitPriceVO> purchaseUnitPriceList(
			@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int purchaseUnitPriceCount(@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO);
	
	List<PurchaseUnitPriceVO> mncoUtPrcDtlList(
			@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int mncoUtPrcDtlCount(@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO);
	
	int insertMncoUtPrcDtl(@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO);
	
	int updateMncoUtPrcDtlEndDt(@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO);
	
	int updateProductInfoPrc(@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO);
	
	int updateMncoUtPrcDtl(@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO);
	
	int deleteMncoUtPrcDtl(@Param(value = "purchaseUnitPriceVO") PurchaseUnitPriceVO purchaseUnitPriceVO);
	
	
}
