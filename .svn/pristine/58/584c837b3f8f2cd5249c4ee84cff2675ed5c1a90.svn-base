package com.ntels.ccbs.system.service.common.common.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.common.common.ManufacturerMngVO;
import com.ntels.ccbs.system.mapper.common.common.ManufacturerMngMapper;
import com.ntels.ccbs.system.service.common.common.ManufacturerMngService;

@Service
public class ManufacturerMngServiceImpl implements ManufacturerMngService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ManufacturerMngMapper manufacturerMngMapper;

	@Override
	public List<ManufacturerMngVO> manufacturerList(
			ManufacturerMngVO manufacturerMngVO, int page,	int perPage) {
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return manufacturerMngMapper.manufacturerList(manufacturerMngVO, start, end);
	}

	@Override
	public int manufacturerCount(ManufacturerMngVO manufacturerMngVO) {
		return manufacturerMngMapper.manufacturerCount(manufacturerMngVO);
	}
	
	
}
