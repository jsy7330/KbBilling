package com.ntels.ccbs.system.service.common.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.auth.authMng.AuthGroupMngVO;
import com.ntels.ccbs.system.domain.common.common.SoSearchVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.mapper.common.common.SoSearchMngMapper;
import com.ntels.ccbs.system.service.common.common.SoSearchMngService;

@Service
public class SoSearchMngServiceImpl implements SoSearchMngService {

	@Autowired
	private SoSearchMngMapper soSearchMngMapper;

	@Override
	public Map<String, Object> list(SoSearchVO soSearch, String lng) {
		Map<String,Object> soSearchInfo = new HashMap<String,Object>();

		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		List<SoSearchVO> soSearchList = soSearchMngMapper.list(lng,soSearch);
			
		soSearchInfo.put("soSearchList", soSearchList);
		return soSearchInfo;
	}

}