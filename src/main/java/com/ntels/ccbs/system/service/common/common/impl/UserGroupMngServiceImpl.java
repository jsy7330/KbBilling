package com.ntels.ccbs.system.service.common.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.common.common.UserGroupMngVO;
import com.ntels.ccbs.system.mapper.common.common.UserGroupMngMapper;
import com.ntels.ccbs.system.service.common.common.UserGroupMngService;

@Service
public class UserGroupMngServiceImpl implements UserGroupMngService {
	@Autowired
	private UserGroupMngMapper userGroupMngMapper;

	@Override
	public List<UserGroupMngVO> userGroupList(UserGroupMngVO userGroup, String sidx, String sord) {
		List<UserGroupMngVO> userGroupList=userGroupMngMapper.userGroupList(userGroup,sidx,sord);
		return userGroupList;
	}
}