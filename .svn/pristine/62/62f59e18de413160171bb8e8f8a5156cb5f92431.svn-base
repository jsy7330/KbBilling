package com.ntels.ccbs.system.service.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpVO;
import com.ntels.ccbs.system.mapper.common.service.CommonDataMapper;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

/**
 * 공통 데이터 Service.
 *
 * @author smyun@ntels.com
 */
@Service
public class CommonDataServiceImpl implements CommonDataService {

	/** CommonDataMapper  Autowired. */
	@Autowired
	private CommonDataMapper commonDataMapper;

	@Override
	public CommonCodeVO getCommonCode(String grpCd, String code, String lng) {
		return commonDataMapper.getCommonCode(grpCd, code, lng);
	}

	@Override
	public List<CommonCodeVO> getCommonCodeList(String grpCd, String lng) {
		return commonDataMapper.getCommonCodeList(grpCd, lng);
	}
	
	@Override
	public List<CommonCodeVO> getCommonCodeListByRef1(String grpCd, String ref1, String lng) {
		return commonDataMapper.getCommonCodeListByRef1(grpCd, ref1, lng);
	}
	
	@Override
	public List<CommonCodeVO> getCommonCodeListByRef2(String grpCd, String ref2, String lng) {
		return commonDataMapper.getCommonCodeListByRef2(grpCd, ref2, lng);
	}
	
	@Override
	public List<CommonCodeVO> getCommonCodeListByRef3(String grpCd, String ref3, String lng) {
		return commonDataMapper.getCommonCodeListByRef3(grpCd, ref3, lng);
	}
	
	@Override
	public List<CommonCodeVO> getCommonCodeListByRef4(String grpCd, String ref4, String lng) {
		return commonDataMapper.getCommonCodeListByRef4(grpCd, ref4, lng);
	}
	@Override
	public List<CommonCodeVO> getCommonCodeListProd(String grpCd, String prodCd, String qry) {
		return commonDataMapper.getCommonCodeListProd(grpCd, prodCd, qry);
	}
}
