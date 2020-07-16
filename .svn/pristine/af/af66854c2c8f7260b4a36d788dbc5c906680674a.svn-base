package com.ntels.ccbs.product.service.refInfo.itemTypeMgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.itemTypeMgt.ChargeType;
import com.ntels.ccbs.product.mapper.refInfo.itemTypeMgt.ChargeTypeMapper;
import com.ntels.ccbs.product.service.refInfo.itemTypeMgt.ChargeTypeService;


@Service
public class ChargeTypeServiceImpl implements ChargeTypeService {

	@Autowired
	private ChargeTypeMapper chargeTypeMapper;
	
	@Override
	public List<ChargeType> getRateTypeList(ChargeType chargeType, int page,
			int perPage) {

		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return chargeTypeMapper.getRateTypeList(chargeType, start, end);
	}

	@Override
	public int getRateTypeListCount(ChargeType chargeType) {
		return chargeTypeMapper.getRateTypeListCount(chargeType);
	}

	@Override
	public ChargeType getRateTypeMaxPriNo(ChargeType chargeType) {
		return chargeTypeMapper.getRateTypeMaxPriNo(chargeType);
	}

	@Override
	public int getRateTypeDupCnt(ChargeType chargeType) {
		return chargeTypeMapper.getRateTypeDupCnt(chargeType);
	}

	@Override
	public int addRateType(ChargeType chargeType) {
		return chargeTypeMapper.addRateType(chargeType);
	}

	@Override
	public ChargeType getRateType(ChargeType chargeType) {
		return chargeTypeMapper.getRateType(chargeType);
	}
	
	@Override
	public int getRateTypeDupExpCnt(ChargeType chargeType) {
		return chargeTypeMapper.getRateTypeDupExpCnt(chargeType);
	}

	@Override
	public int modRateType(ChargeType chargeType) {
		return chargeTypeMapper.modRateType(chargeType);
	}

	@Override
	public int modRateTypeActDt(ChargeType chargeType) {
		return chargeTypeMapper.modRateTypeActDt(chargeType);
	}

	@Override
	public int modRateTypeActDtion(ChargeType chargeType) {
		return chargeTypeMapper.modRateTypeActDtion(chargeType);
	}
	
	

}
