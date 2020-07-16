package com.ntels.ccbs.product.service.refInfo.ratingRefInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.AllowanceDiscount;
import com.ntels.ccbs.product.mapper.refInfo.ratingRefInfo.AllowanceDiscountMapper;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.AllowanceDiscountService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
	
@Service
public class AllowanceDiscountServiceImpl implements AllowanceDiscountService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** RatingFactorUnitMapper Autowired. */
	@Autowired
	private AllowanceDiscountMapper allowanceDiscountMapper;

	@Override
	public List<AllowanceDiscount> getAllowanceDiscountList(AllowanceDiscount allowanceDiscount, int page, int perPage) {		
		int start = 0;
		int end = 0;

		start = (page-1)*perPage;
		end = perPage;

		return allowanceDiscountMapper.getAllowanceDiscountList(allowanceDiscount, start,  end);
	}

	@Override
	public int addAllowanceDiscount(AllowanceDiscount allowanceDiscount) {
		return allowanceDiscountMapper.addAllowanceDiscount(allowanceDiscount);
	}

	@Override
	public int delAllowanceDiscount(AllowanceDiscount allowanceDiscount) {
		return allowanceDiscountMapper.delAllowanceDiscount(allowanceDiscount);
	}

	@Override
	public int modAllowanceDiscount(AllowanceDiscount allowanceDiscount) {
		return allowanceDiscountMapper.modAllowanceDiscount(allowanceDiscount);
	}

	@Override
	public List<CommonCodeVO> getChrgCdList() {
		return allowanceDiscountMapper.getChrgCdList();
	}

	@Override
	public List<CommonCodeVO> getCommonCodeList() {
		
		return allowanceDiscountMapper.getCommonCodeList();
	}
}
