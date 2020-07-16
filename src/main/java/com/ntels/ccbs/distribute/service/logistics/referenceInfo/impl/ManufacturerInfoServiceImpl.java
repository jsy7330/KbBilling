package com.ntels.ccbs.distribute.service.logistics.referenceInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ManufacturerInfoVO;
import com.ntels.ccbs.distribute.mapper.logistics.referenceInfo.ManufacturerInfoMapper;
import com.ntels.ccbs.distribute.service.logistics.referenceInfo.ManufacturerInfoService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ManufacturerInfoServiceImpl implements ManufacturerInfoService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ManufacturerInfoMapper manufacturerInfoMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<ManufacturerInfoVO> manufacturerInfoList(
			ManufacturerInfoVO manufacturerInfoVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return manufacturerInfoMapper.manufacturerInfoList(manufacturerInfoVO, start, end);
	}

	@Override
	public int manufacturerInfoCount(ManufacturerInfoVO manufacturerInfoVO) {
		return manufacturerInfoMapper.manufacturerInfoCount(manufacturerInfoVO);
	}

	@Override
	public int getExistManufacturerInfo(ManufacturerInfoVO manufacturerInfoVO) {
		return manufacturerInfoMapper.getExistManufacturerInfo(manufacturerInfoVO);
	}

	@Override
	public int insertManufacturerInfo(ManufacturerInfoVO manufacturerInfoVO) {
		return manufacturerInfoMapper.insertManufacturerInfo(manufacturerInfoVO);
	}

	@Override
	public int updateManufacturerInfo(ManufacturerInfoVO manufacturerInfoVO) {
		return manufacturerInfoMapper.updateManufacturerInfo(manufacturerInfoVO);
	}

	@Override
	public int deleteManufacturerInfo(ManufacturerInfoVO manufacturerInfoVO) {
		return manufacturerInfoMapper.deleteManufacturerInfo(manufacturerInfoVO);
	}
	
}
