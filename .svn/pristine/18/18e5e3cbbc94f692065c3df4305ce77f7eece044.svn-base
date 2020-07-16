package com.ntels.ccbs.product.service.usageProduct.usageProductMgt.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ntels.ccbs.product.domain.usageProduct.usageProductMgt.ProductDtl;
import com.ntels.ccbs.product.mapper.usageProduct.usageProductMgt.ProductDtlMapper;
import com.ntels.ccbs.product.service.usageProduct.usageProductMgt.ProductDtlService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
	

@Service
public class ProductDtlServiceImpl implements ProductDtlService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** RatingFactorUnitMapper Autowired. */
	@Autowired
	private ProductDtlMapper productDtlMapper;

	@Override
	public List<ProductDtl> getProductDtlList(ProductDtl productDtl, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return productDtlMapper.getProductDtlList(productDtl, start,  end);
	}

	@Override
	public List<Map<String, Object>> productDtlListExcel(
			ProductDtl productDtl 
			,String soNm
			,String prodCd
			,String prodNm
			,String prodGrp
			,String prodDesc
			,String basicProdTyp
			,String actDt
			,String inactDt 
			) {
		
		return productDtlMapper.productDtlListExcel(productDtl, soNm, prodCd, prodNm, prodGrp, prodDesc, basicProdTyp, actDt, inactDt);
	}

	@Override
	public List<CommonCodeVO> getSoNmList() {

		return productDtlMapper.getSoNmList();
	}
}
