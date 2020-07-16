package com.ntels.ccbs.system.service.log.logMng.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.log.logMng.AccessLogHistVO;
import com.ntels.ccbs.system.mapper.log.logMng.AccessLogHistMapper;
import com.ntels.ccbs.system.service.log.logMng.AccessLogHistService;


@Service
public class AccessLogHistServiceImpl implements AccessLogHistService {
	/** InquiryHistMapper Autowired. */
	@Autowired
	private AccessLogHistMapper accessLogHistMapper;
	/** InquiryHist Service  */
	AccessLogHistService accessLogHistService;
	@Override
	public Map<String, Object> list(String sidx, String sord, int page, int rows, String lng, AccessLogHistVO access) {
		Map<String,Object> accssInfo = new HashMap<String,Object>();
		
		Integer totalCount= accessLogHistMapper.count(sidx, sord,lng,access);
		if(totalCount.intValue() == 0){
			accssInfo.put("totalCount", totalCount);
			accssInfo.put("totalPages", new Integer(0));
			accssInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			List<AccessLogHistVO> accssList = accessLogHistMapper.list(sidx, sord, start, end, lng,access);
			
			accssInfo.put("accssList", accssList);
			accssInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			accssInfo.put("totalPages", totalPages);
			accssInfo.put("page", new Integer(page));
		}
		
		return accssInfo;
	}
	
	
	@Override
	public List<Map<String,Object>> listExcel(String condUserId,String sdate,String edate, String lng) {
		return accessLogHistMapper.listExcel(condUserId, sdate, edate,lng );
	}
}