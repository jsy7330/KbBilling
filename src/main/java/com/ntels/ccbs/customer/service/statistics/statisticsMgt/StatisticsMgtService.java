package com.ntels.ccbs.customer.service.statistics.statisticsMgt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntels.ccbs.customer.domain.statistics.statisticsMgt.StatisticsMgtVO;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.RatingCodeGrp;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.service.SequenceService;

public interface StatisticsMgtService {

	public List<StatisticsMgtVO> getStatisticeList();	
	public StatisticsMgtVO getStatisticeDtl(String stmtCd);
	public List<StatisticsMgtVO> getInitCodeList(String lng);
	public List<StatisticsMgtVO> getUseableList(String varDefn, String lng);	
	public List<StatisticsMgtVO> getSelectedList(String varDefn, String lng);
	public int updateStatistics(StatisticsMgtVO statisticsMgtVO);
	public int insertStatistics(StatisticsMgtVO statisticsMgtVO);		
	public List<Map<String, Object>> test(String sql, int timeout);
	public List<StatisticsMgtVO> getStatisticeSoList(String searchSoId);
	public List<Map<String, Object>> execute(String sql, int timeout, Map<String, ?> args);
	public List<StatisticsMgtVO> getStatistice(StatisticsMgtVO statisticsMgtVO);	
	public int deleteStatistics(StatisticsMgtVO statisticsMgtVO);	
}
