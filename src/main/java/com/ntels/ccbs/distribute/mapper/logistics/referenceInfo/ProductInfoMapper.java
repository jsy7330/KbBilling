package com.ntels.ccbs.distribute.mapper.logistics.referenceInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ProductInfoVO;

@Component
public interface ProductInfoMapper {

	List<ProductInfoVO> productInfoList(
			@Param(value = "productInfoVO") ProductInfoVO productInfoVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int productInfoCount(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int checkItemId(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int insertProductInfo(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int insertTdndtMncoUtPrcDtl(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int updateMncoUtPrcDtlEndDt(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int updateProductInfo(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int deleteTdndtDstrbUtPrcDtl(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int deleteTdndtMncoUtPrcDtl(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int deleteTdndtItemAttrMapp(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int deleteTdndtItemDtl(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int deleteTdndtPckgAcceMapp(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int deleteTdndtItem(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	
	
	List<ProductInfoVO> itemAttrList(
			@Param(value = "productInfoVO") ProductInfoVO productInfoVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int itemAttrCount(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int checkAttrCd(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int insertItemAttr(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int updateItemAttr(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int deleteItemAttr(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	
	
	List<ProductInfoVO> itemAttrValList(
			@Param(value = "productInfoVO") ProductInfoVO productInfoVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int itemAttrValCount(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);

	int checkAttrValCd(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int insertItemAttrVal(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int updateItemAttrVal(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int deleteItemAttrVal(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	
	List<ProductInfoVO> itemAttrMappList(
			@Param(value = "productInfoVO") ProductInfoVO productInfoVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int itemAttrMappCount(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	
	List<ProductInfoVO> itemAttrSelectList(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	List<ProductInfoVO> itemAttrValSelectList(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int insertItemAttrMapp(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int updateItemAttrMapp(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	List<ProductInfoVO> itemDtlList(
			@Param(value = "productInfoVO") ProductInfoVO productInfoVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int itemDtlCount(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int insertItemDtl(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
	
	int updateItemDtl(@Param(value = "productInfoVO") ProductInfoVO productInfoVO);
}



