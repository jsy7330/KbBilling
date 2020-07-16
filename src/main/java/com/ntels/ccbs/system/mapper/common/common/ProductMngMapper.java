package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.ProductMngVO;

@Component
public interface ProductMngMapper {

	List<ProductMngVO> productList(
			@Param(value = "productMngVO") ProductMngVO productMngVO,
			@Param(value = "start") int start, @Param(value = "end") int end);

	int productCount(@Param(value = "productMngVO") ProductMngVO productMngVO);
	
}
