package com.ntels.ccbs.distribute.mapper.logistics.inventoryMoveChangeMng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.inventoryMoveChangeMng.ProductStatusVO;

@Component
public interface ProductStatusMapper {

	List<ProductStatusVO> eqtStatList(
			@Param(value = "productStatusVO") ProductStatusVO productStatusVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int eqtStatCount(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	List<ProductStatusVO> usimStatList(
			@Param(value = "productStatusVO") ProductStatusVO productStatusVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int usimStatCount(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	List<ProductStatusVO> veqtStatList(
			@Param(value = "productStatusVO") ProductStatusVO productStatusVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int veqtStatCount(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	
	List<ProductStatusVO> eqtStatInfoList(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	List<ProductStatusVO> usimStatInfoList(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	List<ProductStatusVO> veqtStatInfoList(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	int updateEqtStat(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	int updateUsimStat(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	int updateVeqtStat(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	int insertUpdProcHist(@Param(value = "productStatusVO") ProductStatusVO productStatusVO);
	
	
	
}
