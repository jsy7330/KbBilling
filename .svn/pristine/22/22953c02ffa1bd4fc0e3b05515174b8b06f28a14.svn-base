package com.ntels.ccbs.system.service.common.common.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.common.common.ProductMngVO;
import com.ntels.ccbs.system.mapper.common.common.ProductMngMapper;
import com.ntels.ccbs.system.service.common.common.ProductMngService;

@Service
public class ProductMngServiceImpl implements ProductMngService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductMngMapper productMngMapper;

	@Override
	public List<ProductMngVO> productList(
			ProductMngVO productMngVO, int page,	int perPage) {
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return productMngMapper.productList(productMngVO, start, end);
	}

	@Override
	public int productCount(ProductMngVO productMngVO) {
		return productMngMapper.productCount(productMngVO);
	}
}
