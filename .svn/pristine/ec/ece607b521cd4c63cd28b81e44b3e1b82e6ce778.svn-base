package com.ntels.ccbs.distribute.mapper.logistics.referenceInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ManufacturerInfoVO;

@Component
public interface ManufacturerInfoMapper {

	List<ManufacturerInfoVO> manufacturerInfoList(
			@Param(value = "manufacturerInfoVO") ManufacturerInfoVO manufacturerInfoVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int manufacturerInfoCount(@Param(value = "manufacturerInfoVO") ManufacturerInfoVO manufacturerInfoVO);
	
	int getExistManufacturerInfo(@Param(value = "manufacturerInfoVO") ManufacturerInfoVO manufacturerInfoVO);
	
	int insertManufacturerInfo(@Param(value = "manufacturerInfoVO") ManufacturerInfoVO manufacturerInfoVO);
	
	int updateManufacturerInfo(@Param(value = "manufacturerInfoVO") ManufacturerInfoVO manufacturerInfoVO);
	
	int deleteManufacturerInfo(@Param(value = "manufacturerInfoVO") ManufacturerInfoVO manufacturerInfoVO);
	
}
