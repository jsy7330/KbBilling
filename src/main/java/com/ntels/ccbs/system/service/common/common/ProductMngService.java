package com.ntels.ccbs.system.service.common.common;

import java.util.List;

import com.ntels.ccbs.system.domain.common.common.ProductMngVO;

public interface ProductMngService {

	public List<ProductMngVO> productList(ProductMngVO productMngVO, int page,	int perPage);

	int productCount(ProductMngVO productMngVO);
	
}
