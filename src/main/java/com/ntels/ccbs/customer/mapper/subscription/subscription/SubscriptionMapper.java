package com.ntels.ccbs.customer.mapper.subscription.subscription;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.statistics.orderStatistics.OrderStatisticsVO;
import com.ntels.ccbs.customer.domain.subscription.subscription.SubscriptionVO;

@Component
public interface SubscriptionMapper {
	Integer subscriptionListCnt(@Param("searchStatDt")String searchStatDt			
			,@Param("searchEndDt")String searchEndDt	
			,@Param("orderStat")String orderStat		
			,@Param("condCustId")String condCustId			
			,@Param("orderTp")String orderTp
			,@Param("searchSoId")String searchSoId
			,@Param("wrkDftWrker")String wrkDftWrker
			,@Param("wrkStat")String wrkStat);

	List<SubscriptionVO> subscriptionList(@Param("searchStatDt")String searchStatDt			
			,@Param("searchEndDt")String searchEndDt	
			,@Param("orderStat")String orderStat		
			,@Param("condCustId")String condCustId			
			,@Param("orderTp")String orderTp
			,@Param("searchSoId")String searchSoId
			,@Param("wrkDftWrker")String wrkDftWrker
			,@Param("wrkStat")String wrkStat
			,@Param("lng") String lng
			,@Param("sidx") String sidx
			,@Param("sord") String sord
			,@Param("start") String start
			,@Param("end") String end);
	
	List<SubscriptionVO> subscription(@Param("searchStatDt")String searchStatDt			
			,@Param("searchEndDt")String searchEndDt
			,@Param("searchSoId")String searchSoId
			,@Param("wrkDftWrker")String wrkDftWrker
			,@Param("lng") String lng);	
}