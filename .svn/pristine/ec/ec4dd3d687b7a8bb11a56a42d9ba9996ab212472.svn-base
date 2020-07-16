package com.ntels.ccbs.system.service.log.logMng.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.log.logMng.ProcessLogHistVO;
import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;
import com.ntels.ccbs.system.mapper.log.logMng.ProcessLogHistMapper;
import com.ntels.ccbs.system.service.log.logMng.ProcessLogHistService;


@Service
public class ProcessLogHistServiceImpl implements ProcessLogHistService {
	/** InquiryHistMapper Autowired. */
	@Autowired
	private ProcessLogHistMapper processLogHistMapper;
	/** InquiryHist Service  */
	ProcessLogHistService processLogHistService;
	
	@Override
	public Map<String, Object> list(String sidx, String sord, int page, int rows, String lng,
			ProcessLogHistVO process) {
		Map<String,Object> prcssInfo = new HashMap<String,Object>();
		
		Integer totalCount= processLogHistMapper.count(sidx, sord,lng,process);
		if(totalCount.intValue() == 0){
			prcssInfo.put("totalCount", totalCount);
			prcssInfo.put("totalPages", new Integer(0));
			prcssInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			List<UserMngVO> prcssList = processLogHistMapper.list(sidx, sord, start, end, lng,process);
			
			prcssInfo.put("prcssList", prcssList);
			prcssInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			prcssInfo.put("totalPages", totalPages);
			prcssInfo.put("page", new Integer(page));
		}
		
		return prcssInfo;
	}

	@Override
	public List<Map<String, Object>> listExcel(String condUserId, String sdate, String edate, String lng,
			String condSessionId,String condWorkType) {
		
		return processLogHistMapper.listExcel(condUserId,sdate,edate,lng,condSessionId,condWorkType);
	}
}