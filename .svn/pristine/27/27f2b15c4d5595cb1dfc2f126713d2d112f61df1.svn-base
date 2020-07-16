package com.ntels.ccbs.system.service.common.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.common.common.ViewPathVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.mapper.common.common.ViewPathMngMapper;
import com.ntels.ccbs.system.service.common.common.ViewPathMngService;

@Service
public class ViewPathMngServiceImpl implements ViewPathMngService {

	/** CommonDataMapper  Autowired. */
	@Autowired
	private ViewPathMngMapper viewPathMngMapper;

	@Override
	public Map<String, Object> list(ViewPathVO view, String lng) {
		Map<String,Object> viewPathInfo = new HashMap<String,Object>();

		SessionUser sessionUser = CommonUtil.getSessionManager();
		List<ViewPathVO> viewPathList = viewPathMngMapper.list(lng,view);
			
		viewPathInfo.put("viewPathList", viewPathList);
		return viewPathInfo;
	}

	@Override
	public List<ViewPathVO> getCommonCdList(ViewPathVO view,String lng) {
		// TODO Auto-generated method stub
		return viewPathMngMapper.getCommonCdList(view,lng);
	}
}