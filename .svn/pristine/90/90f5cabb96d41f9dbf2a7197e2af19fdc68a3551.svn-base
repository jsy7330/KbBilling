package com.ntels.ccbs.product.mapper.refInfo.commonInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.TierMng;

public interface TierMngMapper {
	 
	List<TierMng> getTierMngList(@Param(value = "tierMng") TierMng tierMng,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	
	int getTierMngListCount(@Param(value = "tierMng") TierMng tierMng);
	
	List<TierMng> getTierMngDtlList(@Param(value = "tierMng") TierMng tierMng,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	
	int getTierMngDtlListCount(@Param(value = "tierMng") TierMng tierMng);	
	
	List<TierMng> getSearchProdList(@Param(value = "tierMng") TierMng tierMng,
			 @Param(value="start")int start
			, @Param(value="end") int end);	
	
	int getSearchProdListCount(@Param(value = "tierMng") TierMng tierMng);	
}
