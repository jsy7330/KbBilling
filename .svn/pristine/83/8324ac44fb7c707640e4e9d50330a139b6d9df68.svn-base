package com.ntels.ccbs.customer.mapper.statistics.statisticsMgt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.statistics.statisticsMgt.StatisticsMgtVO;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Factor;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeGrp;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;

@Component
public interface StatisticsMgtMapper {

	List<StatisticsMgtVO> getStatisticeList();
	StatisticsMgtVO getStatisticeDtl(@Param(value = "stmtCd") String stmtCd);
	List<StatisticsMgtVO> getInitCodeList(@Param(value = "lng") String lng);	
	List<StatisticsMgtVO> getUseableList(@Param(value = "varDefn") String varDefn,@Param(value = "lng") String lng);	
	List<StatisticsMgtVO> getSelectedList(@Param(value = "varDefn") String varDefn,@Param(value = "lng") String lng);	
	int updateStatistics(@Param(value ="statisticsMgtVO") StatisticsMgtVO statisticsMgtVO);
	int insertStatistics(@Param(value ="statisticsMgtVO") StatisticsMgtVO statisticsMgtVO);
	List<StatisticsMgtVO> getStatisticeSoList(@Param(value = "searchSoId") String searchSoId);
	List<StatisticsMgtVO> getStatistice(@Param(value ="statisticsMgtVO") StatisticsMgtVO statisticsMgtVO);	
	int deleteStatistics(@Param(value ="statisticsMgtVO") StatisticsMgtVO statisticsMgtVO);	
}