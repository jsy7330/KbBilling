package com.ntels.ccbs.system.service.auth.authMng.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.auth.authMng.AuthGroupMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.mapper.auth.authMng.AuthGroupMngMapper;
import com.ntels.ccbs.system.service.auth.authMng.AuthGroupMngService;

@Service
public class AuthGroupMngServiceImpl implements AuthGroupMngService {
	/** InquiryHistMapper Autowired. */
	@Autowired
	private AuthGroupMngMapper authGroupMngMapper;
	/** InquiryHist Service  */
	AuthGroupMngService authGroupMngService;
	@Override
	public Map<String, Object> list(String sidx, String sord, int page, int rows, String lng,
			AuthGroupMngVO authGroup) {
		Map<String,Object> authGroupInfo = new HashMap<String,Object>();
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		Integer totalCount= authGroupMngMapper.count(authGroup);
		
		if(totalCount.intValue() == 0){
			authGroupInfo.put("totalCount", totalCount);
			authGroupInfo.put("totalPages", new Integer(0));
			authGroupInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			List<AuthGroupMngVO> authGroupList = authGroupMngMapper.list(sidx, sord, start, end, lng,authGroup);
			
			authGroupInfo.put("authGroupList", authGroupList);
			authGroupInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			authGroupInfo.put("totalPages", totalPages);
			authGroupInfo.put("page", new Integer(page));
		}
		
		return authGroupInfo;
	}
	@Override
	public int checkUserId(AuthGroupMngVO authGroup) {
		int check=authGroupMngMapper.checkUserId(authGroup);
		return check;
	}
	@Override
	public int insert(AuthGroupMngVO authGroup, HttpServletRequest request) {
		int check=authGroupMngMapper.checkUserId(authGroup);
		if(check >0){
			throw new ServiceException("MSG.M14.MSG00018"); 
		}else{
			authGroup.setUserGroupLevel("00");
			return authGroupMngMapper.insert(authGroup);
		}
	}
	@Override
	public int update(AuthGroupMngVO authGroup, HttpServletRequest request) {
		authGroup.setUserGroupLevel("00");
		return authGroupMngMapper.update(authGroup);
	}
	@Override
	public int delete(AuthGroupMngVO authGroup, HttpServletRequest request) {
		authGroupMngMapper.deleteRole(authGroup);	//TSYCO_USER_GROUP_ROLE
		authGroupMngMapper.deleteAuth(authGroup);
		return authGroupMngMapper.delete(authGroup);
	}
}