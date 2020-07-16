package com.ntels.ccbs.distribute.service.logistics.referenceInfo;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ProductInfoVO;

public interface ProductInfoService {

	List<ProductInfoVO> productInfoList(ProductInfoVO productInfoVO, int page, int perPage);

	int productInfoCount(ProductInfoVO productInfoVO);
	
	int checkItemId(ProductInfoVO productInfoVO);
	
	int insertProductInfo(ProductInfoVO productInfoVO);

	int updateProductInfo(ProductInfoVO productInfoVO);
	
	int deleteProductInfo(ProductInfoVO productInfoVO);
	
	
	List<ProductInfoVO> itemAttrList(ProductInfoVO productInfoVO, int page, int perPage);

	int itemAttrCount(ProductInfoVO productInfoVO);
	
	int checkAttrCd(ProductInfoVO productInfoVO);

	int insertItemAttr(ProductInfoVO productInfoVO);
	
	int updateItemAttr(ProductInfoVO productInfoVO);
	
	int deleteItemAttr(ProductInfoVO productInfoVO);
	
	
	List<ProductInfoVO> itemAttrValList(ProductInfoVO productInfoVO, int page, int perPage);

	int itemAttrValCount(ProductInfoVO productInfoVO);
	
	int checkAttrValCd(ProductInfoVO productInfoVO);
	
	int insertItemAttrVal(ProductInfoVO productInfoVO);
	
	int updateItemAttrVal(ProductInfoVO productInfoVO);
	
	int deleteItemAttrVal(ProductInfoVO productInfoVO);
	
	
	List<ProductInfoVO> itemAttrMappList(ProductInfoVO productInfoVO, int page, int perPage);

	int itemAttrMappCount(ProductInfoVO productInfoVO);
	
	
	List<ProductInfoVO> itemAttrSelectList(ProductInfoVO productInfoVO);
	
	List<ProductInfoVO> itemAttrValSelectList(ProductInfoVO productInfoVO);
	
	
	int insertItemAttrMapp(ProductInfoVO productInfoVO);
	
	int updateItemAttrMapp(ProductInfoVO productInfoVO);
	
	int deleteItemAttrMapp(ProductInfoVO productInfoVO);
	
	
	List<ProductInfoVO> itemDtlList(ProductInfoVO productInfoVO, int page, int perPage);

	int itemDtlCount(ProductInfoVO productInfoVO);
	
	int insertItemDtl(ProductInfoVO productInfoVO);
	
	int updateItemDtl(ProductInfoVO productInfoVO);
	
	int deleteItemDtl(ProductInfoVO productInfoVO);
}
