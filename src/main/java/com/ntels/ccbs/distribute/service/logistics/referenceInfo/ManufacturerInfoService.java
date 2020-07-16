package com.ntels.ccbs.distribute.service.logistics.referenceInfo;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ManufacturerInfoVO;

public interface ManufacturerInfoService {

	public List<ManufacturerInfoVO> manufacturerInfoList(ManufacturerInfoVO manufacturerInfoVO,	int page, int perPage);	
	
	public int manufacturerInfoCount(ManufacturerInfoVO manufacturerInfoVO);
	
	public int getExistManufacturerInfo(ManufacturerInfoVO manufacturerInfoVO);
	
	public int insertManufacturerInfo(ManufacturerInfoVO manufacturerInfoVO);
	
	public int updateManufacturerInfo(ManufacturerInfoVO manufacturerInfoVO);
	
	public int deleteManufacturerInfo(ManufacturerInfoVO manufacturerInfoVO);
	
}
