package com.ntels.ccbs.product.service.usageProduct.usageProductMgt;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.product.domain.usageProduct.usageProductMgt.ProductDtl;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;


public interface ProductDtlService {

	public List<ProductDtl> getProductDtlList(ProductDtl productDtl, int page, int perPage);
	List<Map<String, Object>> productDtlListExcel(
			ProductDtl productDtl 
			,String soNm
			,String prodCd
			,String prodNm
			,String prodGrp
			,String prodDesc
			,String basicProdTyp
			,String actDt
			,String inactDt 
			);
	
	List<CommonCodeVO> getSoNmList();
}