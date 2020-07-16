package com.ntels.ccbs.customer.mapper.statistics.orderStatistics;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.ntels.ccbs.customer.domain.statistics.orderStatistics.OrderStatisticsVO;

@Component
public interface OrderStatisticsMapper {


	Integer orderStatisticsListCnt(@Param("searchStatDt")String searchStatDt			
			,@Param("searchEndDt")String searchEndDt	
			,@Param("orderStat")String orderStat		
			,@Param("condCustId")String condCustId			
			,@Param("orderTp")String orderTp
			,@Param("searchSoId")String searchSoId);

	List<OrderStatisticsVO> orderStatisticsList(@Param("searchStatDt")String searchStatDt			
			,@Param("searchEndDt")String searchEndDt	
			,@Param("orderStat")String orderStat		
			,@Param("condCustId")String condCustId			
			,@Param("orderTp")String orderTp
			,@Param("searchSoId")String searchSoId
			,@Param("lng") String lng
			,@Param("sidx") String sidx
			,@Param("sord") String sord
			,@Param("start") String start
			,@Param("end") String end);
	List<OrderStatisticsVO> orderStatisticsChart(@Param(value = "orderStatisticsVO")OrderStatisticsVO orderStatisticsVO,  @Param(value = "lng")String lng);	
}