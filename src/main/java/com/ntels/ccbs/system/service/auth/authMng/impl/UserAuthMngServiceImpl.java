package com.ntels.ccbs.system.service.auth.authMng.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.auth.authMng.UserAuthMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.mapper.auth.authMng.UserAuthMngMapper;
import com.ntels.ccbs.system.service.auth.authMng.UserAuthMngService;

@Service
public class UserAuthMngServiceImpl implements UserAuthMngService {
	/** InquiryHistMapper Autowired. */
	@Autowired
	private UserAuthMngMapper userAuthMngMapper;
	/** InquiryHist Service  */
	UserAuthMngService userAuthMngService;
	@Override
	public Map<String, Object> list(UserAuthMngVO userAuth, String lng,String sidx,String sord) {
		Map<String,Object> userAuthInfo = new HashMap<String,Object>();
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String today = DateUtil.getDateStringYYYYMMDD(0);
		Integer totalCount= userAuthMngMapper.count(userAuth,sessionUser.getSoAuthList(),today);
		
		if(totalCount.intValue() == 0){
			userAuthInfo.put("totalCount", totalCount);
			userAuthInfo.put("totalPages", new Integer(0));
			userAuthInfo.put("page", new Integer(1));
		}else{
			
			List<UserAuthMngVO> userAuthList = userAuthMngMapper.list(userAuth,lng,sidx,sord,sessionUser.getSoAuthList(),today);
			
			userAuthInfo.put("userAuthList", userAuthList);
			userAuthInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)1));
			userAuthInfo.put("totalPages", totalPages);
			userAuthInfo.put("page", new Integer(1));
		}
		
		return userAuthInfo;
	}
	@Override
	public Map<String, Object> authList(UserAuthMngVO userAuth, String lng) {
		Map<String,Object> userGroupInfo = new HashMap<String,Object>();
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		Integer totalCount= userAuthMngMapper.authCount(userAuth);
		
		if(totalCount.intValue() == 0){
			userGroupInfo.put("totalCount", totalCount);
			userGroupInfo.put("totalPages", new Integer(0));
			userGroupInfo.put("page", new Integer(1));
		}else{

			List<UserAuthMngVO> userAuthList = userAuthMngMapper.userAuthList(userAuth,lng);
			userGroupInfo.put("userAuthList", userAuthList);
			userGroupInfo.put("totalCount2", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue()));
			userGroupInfo.put("totalPages2", totalPages);
			userGroupInfo.put("page2", new Integer(1));
		}
		
		return userGroupInfo;
	}
	@Override
	public int insert(UserAuthMngVO userAuth) {
		int result=0;
		userAuthMngMapper.deleteAll(userAuth);
		String[] arrGroupId=userAuth.getUserGroupId().split(",");
		for(int i=0;i<arrGroupId.length;i++){
			userAuth.setUserGroupId(arrGroupId[i]);
			result=userAuthMngMapper.insert(userAuth);
		}
		
		return result;
	}
	@Override
	public int delete(UserAuthMngVO userAuth) {
		int result=0;
		String[] arrGroupId=userAuth.getUserGroupId().split(",");
		
		for(int i=0;i<arrGroupId.length;i++){
			userAuth.setUserGroupId(arrGroupId[i]);
			result=userAuthMngMapper.delete(userAuth);
		}
		return result;
	}
	@Override
	public String baseUserGroupId(UserAuthMngVO userAuth) {
		
		return userAuthMngMapper.baseUserGroupId(userAuth);
	}
}