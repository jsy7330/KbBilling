package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.ManufacturerMngVO;

@Component
public interface ManufacturerMngMapper {

	List<ManufacturerMngVO> manufacturerList(
			@Param(value = "manufacturerMngVO") ManufacturerMngVO manufacturerMngVO,
			@Param(value = "start") int start, @Param(value = "end") int end);

	int manufacturerCount(@Param(value = "manufacturerMngVO") ManufacturerMngVO manufacturerMngVO);
	
}
