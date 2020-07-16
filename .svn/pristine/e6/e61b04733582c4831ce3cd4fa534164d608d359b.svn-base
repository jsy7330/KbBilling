package com.ntels.ccbs.product.mapper.usageProduct.usageProductMgt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.usageProduct.usageProductMgt.ProductDtl;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;

/**
 * The Interface AttributeMapper.
 */
@Component
public interface ProductDtlMapper {

	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param productDtl
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	List<ProductDtl> getProductDtlList(
			@Param(value = "productDtl") ProductDtl productDtl
			, @Param(value="start")int start
			, @Param(value="end") int end
			);		
	
	List<Map<String,Object>> productDtlListExcel(
			@Param(value = "productDtl") ProductDtl productDtl 
			, @Param(value = "soNm") String soNm
			, @Param(value = "prodCd") String prodCd
			, @Param(value = "prodNm") String prodNm
			, @Param(value = "prodGrp") String prodGrp
			, @Param(value = "prodDesc") String prodDesc
			, @Param(value = "basicProdTyp") String basicProdTyp
			, @Param(value = "actDt") String actDt
			, @Param(value = "inactDt") String inactDt 
			);
	
	List<CommonCodeVO> getSoNmList();
}
