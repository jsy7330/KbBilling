package com.ntels.ccbs.system.service.common.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.common.UserSearchVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.mapper.common.common.UserSearchMngMapper;
import com.ntels.ccbs.system.service.common.common.UserSearchMngService;

@Service
public class UserSearchMngServiceImpl implements UserSearchMngService {

	/** CommonDataMapper  Autowired. */
	@Autowired
	private UserSearchMngMapper userSearchMngMapper;

	@Override
	public Map<String, Object> list(UserSearchVO userSearch, String lng, String sidx, String sord, int page, int rows) {
		Map<String,Object> userSearchInfo = new HashMap<String,Object>();

		SessionUser sessionUser = CommonUtil.getSessionManager();
		String today = DateUtil.getDateStringYYYYMMDD(0);
		Integer totalCount= userSearchMngMapper.count(userSearch,today,sessionUser.getSoAuthList());
		
		if(totalCount.intValue() == 0){
			userSearchInfo.put("totalCount", totalCount);
			userSearchInfo.put("totalPages", new Integer(0));
			userSearchInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			List<UserSearchVO> userSearchList = userSearchMngMapper.list(sidx, sord, start, end,lng,userSearch,today,sessionUser.getSoAuthList());
			
			userSearchInfo.put("userSearchList", userSearchList);
			userSearchInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			userSearchInfo.put("totalPages", totalPages);
			userSearchInfo.put("page", new Integer(page));
		}
		
		return userSearchInfo;
	}
}
	