package com.ntels.ccbs.product.service.refInfo.commonInfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.TierMng;
import com.ntels.ccbs.product.mapper.refInfo.commonInfo.TierMngMapper;
import com.ntels.ccbs.product.service.refInfo.commonInfo.TierMngService;

@Service
public class TierMngServiceImpl implements TierMngService {

	@Autowired
	private TierMngMapper tierMngMapper;

	@Override
	public List<TierMng> getTierMngList(TierMng tierMng, int page, int perPage) {

		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return tierMngMapper.getTierMngList(tierMng, start, end);
	}

	@Override
	public int getTierMngListCount(TierMng tierMng) {
		return tierMngMapper.getTierMngListCount(tierMng);
	}
	
	@Override
	public List<TierMng> getTierMngDtlList(TierMng tierMng, int page, int perPage) {

		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return tierMngMapper.getTierMngDtlList(tierMng, start, end);
	}

	@Override
	public int getTierMngDtlListCount(TierMng tierMng) {
		return tierMngMapper.getTierMngDtlListCount(tierMng);
	}	

	@Override
	public List<TierMng> getSearchProdList(TierMng tierMng, int page, int perPage) {

		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return tierMngMapper.getSearchProdList(tierMng, start, end);
	}

	@Override
	public int getSearchProdListCount(TierMng tierMng) {
		return tierMngMapper.getSearchProdListCount(tierMng);
	}	
}
