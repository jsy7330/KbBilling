package com.ntels.ccbs.system.service.auth.authMng.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.auth.authMng.AuthMngVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpVO;
import com.ntels.ccbs.system.mapper.auth.authMng.AuthMngMapper;
import com.ntels.ccbs.system.service.auth.authMng.AuthMngService;

@Service
public class AuthMngServiceImpl implements AuthMngService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/** InquiryHistMapper Autowired. */
	@Autowired
	private AuthMngMapper authMngMapper;
	/** InquiryHist Service  */
	AuthMngService authMngService;
	
	
	@Override
	public Map<String, Object> getUserGroupList(String sidx, String sord, String lng, AuthMngVO auth) {
		Map<String,Object> groupInfo = new HashMap<String,Object>();
		List<AuthMngVO> groupList = authMngMapper.getUserGroupList(sidx, sord, lng,auth);
		groupInfo.put("groupList", groupList);
		groupInfo.put("totalCount", groupList.size());
		Integer totalPages = 1;
		groupInfo.put("totalPages", totalPages);
		groupInfo.put("page", new Integer(1));
		return groupInfo;
	}


	@Override
	public List<Map<String,Object>> getAuthList(String userGroupId,String onlyAssignMenuYn, String lng) {
		List<Map<String,Object>> authList = new ArrayList<Map<String,Object>>();
		int order = 0;
		/*
		 * 1레벨 권한 그룹 조회
		 */
		List<AuthMngVO> authList1 = authMngMapper.getAuthList(userGroupId, onlyAssignMenuYn, 1, "0", lng);
		for(AuthMngVO auth1 : authList1){
			order++;
			Map<String,Object> authData1 = new HashMap<String,Object>();
			authData1.put("title", auth1.getMenuName());
			authData1.put("isFolder", true);
			authData1.put("key", auth1.getMenuNo());
			authData1.put("expand", false);
			authData1.put("order", order);
			authData1.put("searchKey", auth1.getMenuNo()+auth1.getMenuName());
			authData1.put("checkAll", auth1.getCheckAll());
			authData1.put("authRYn", auth1.getAuthRYn());
			authData1.put("authCYn", auth1.getAuthCYn());
			authData1.put("authDYn", auth1.getAuthDYn());
			authData1.put("authUYn", auth1.getAuthUYn());
			authData1.put("authPYn", auth1.getAuthPYn());
			
			/*
			 * 2레벨 권한 그룹 조회
			 */
			List<AuthMngVO> authList2 = authMngMapper.getAuthList(userGroupId, onlyAssignMenuYn, 2, auth1.getMenuNo(), lng);
			List<Map<String,Object>> childAuthList2 = new ArrayList<Map<String,Object>>();
			for(AuthMngVO auth2 : authList2){
				order++;
				Map<String,Object> authData2 = new HashMap<String,Object>();
				authData2.put("title", auth2.getMenuName());
				authData2.put("isFolder", true);
				authData2.put("key", auth2.getMenuNo());
				authData2.put("expand", false);
				authData2.put("order", order);
				authData2.put("searchKey", auth2.getMenuNo()+auth2.getMenuName());
				authData2.put("checkAll", auth2.getCheckAll());
				authData2.put("authRYn", auth2.getAuthRYn());
				authData2.put("authCYn", auth2.getAuthCYn());
				authData2.put("authDYn", auth2.getAuthDYn());
				authData2.put("authUYn", auth2.getAuthUYn());
				authData2.put("authPYn", auth2.getAuthPYn());
				childAuthList2.add(authData2);
				
				/*
				 * 3레벨 권한 그룹 조회
				 */
				List<AuthMngVO> authList3 = authMngMapper.getAuthList(userGroupId, onlyAssignMenuYn, 3, auth2.getMenuNo(), lng);
				List<Map<String,Object>> childAuthList3 = new ArrayList<Map<String,Object>>();
				for(AuthMngVO auth3 : authList3){
					order++;
					Map<String,Object> authData3 = new HashMap<String,Object>();
					authData3.put("title", auth3.getMenuName());
					authData3.put("isFolder", true);
					authData3.put("key", auth3.getMenuNo());
					authData3.put("expand", false);
					authData3.put("order", order);
					authData3.put("searchKey", auth3.getMenuNo()+auth3.getMenuName());
					authData3.put("checkAll", auth3.getCheckAll());
					authData3.put("authRYn", auth3.getAuthRYn());
					authData3.put("authCYn", auth3.getAuthCYn());
					authData3.put("authDYn", auth3.getAuthDYn());
					authData3.put("authUYn", auth3.getAuthUYn());
					authData3.put("authPYn", auth3.getAuthPYn());
					childAuthList3.add(authData3);
					
					/*
					 * 4레벨 권한 그룹 조회
					 */
					List<AuthMngVO> authList4 = authMngMapper.getAuthList(userGroupId, onlyAssignMenuYn, 4, auth3.getMenuNo(), lng);
					List<Map<String,Object>> childAuthList4 = new ArrayList<Map<String,Object>>();
					for(AuthMngVO auth4 : authList4){
						order++;
						Map<String,Object> authData4 = new HashMap<String,Object>();
						authData4.put("title", auth4.getMenuName());
						authData4.put("isFolder", false);
						authData4.put("key", auth4.getMenuNo());
						authData4.put("expand", false);
						authData4.put("order", order);
						authData4.put("searchKey", auth4.getMenuNo()+auth4.getMenuName());
						authData4.put("checkAll", auth4.getCheckAll());
						authData4.put("authRYn", auth4.getAuthRYn());
						authData4.put("authCYn", auth4.getAuthCYn());
						authData4.put("authDYn", auth4.getAuthDYn());
						authData4.put("authUYn", auth4.getAuthUYn());
						authData4.put("authPYn", auth4.getAuthPYn());
						childAuthList4.add(authData4);
					}
					authData3.put("children", childAuthList4);
					
				}
				authData2.put("children", childAuthList3);
			}
			authData1.put("children", childAuthList2);
			authList.add(authData1);
		}
		return authList;
	}


	@Override
	public void updateAuth(String userGroupId, String menuNo, String level,
	        String authRYn, String authCYn, String authUYn, String authDYn,
	        String authPYn) {
		boolean deleteYn = false;
		if("N".equals(authRYn) && "N".equals(authCYn) && "N".equals(authUYn) && "N".equals(authDYn) && "N".equals(authPYn)){
			deleteYn = true;
		}
		
		if(deleteYn){ //전체 권한 삭제인 경우
			if("5".equals(level)){//4레벨의 메뉴인 경우 삭제 처리
				int deleteCnt = authMngMapper.deleteAuth(userGroupId, menuNo);
				logger.debug("권한 삭제 : " +userGroupId + "/" + menuNo );
			}else if("4".equals(level)){//3레벨의 메뉴인 경우 하위 레벨까지 삭제 처리
				List<String> menuNoList = new ArrayList<String>();
				menuNoList.add(menuNo);
				List<String> menuList4Level = authMngMapper.getMenuList(menuNo, 4);
				menuNoList.addAll(menuList4Level);
				for(String menu : menuNoList){
					int deleteCnt = authMngMapper.deleteAuth(userGroupId, menu);
					logger.debug("권한 삭제 : " +userGroupId + "/" + menu );
				}
			}else if("3".equals(level)){//2레벨의 메뉴인 경우 하위 레벨까지 삭제 처리
				List<String> menuNoList = new ArrayList<String>();
				menuNoList.add(menuNo);
				List<String> menuList3Level = authMngMapper.getMenuList(menuNo, 3);
				menuNoList.addAll(menuList3Level);
				for(String menu3Level : menuList3Level){
					List<String> menuList4Level = authMngMapper.getMenuList(menu3Level, 4);
					menuNoList.addAll(menuList4Level);
				}
				
				for(String menu : menuNoList){
					int deleteCnt = authMngMapper.deleteAuth(userGroupId, menu);
					logger.debug("권한 삭제 : " +userGroupId + "/" + menu );
				}
			}else if("2".equals(level)){//1레벨의 메뉴인 경우 하위 레벨까지 삭제 처리
				List<String> menuNoList = new ArrayList<String>();
				menuNoList.add(menuNo);
				List<String> menuList2Level = authMngMapper.getMenuList(menuNo, 2);
				menuNoList.addAll(menuList2Level);
				for(String menu2Level : menuList2Level){
					List<String> menuList3Level = authMngMapper.getMenuList(menu2Level, 3);
					menuNoList.addAll(menuList3Level);
					for(String menu3Level : menuList3Level){
						List<String> menuList4Level = authMngMapper.getMenuList(menu3Level, 4);
						menuNoList.addAll(menuList4Level);
					}
				
				}
				for(String menu : menuNoList){
					int deleteCnt = authMngMapper.deleteAuth(userGroupId, menu);
					logger.debug("권한 삭제 : " +userGroupId + "/" + menu );
				}
			}
			
		}else{  //단순 변경인 경우
			
			int updateCnt = authMngMapper.updateAuth(userGroupId, menuNo, authRYn, authCYn, authUYn, authDYn, authPYn);
			
			if(updateCnt == 0){ //권한 추가
				int insertCnt = authMngMapper.insertAuth(userGroupId, menuNo, authRYn, authCYn, authUYn, authDYn, authPYn);
				logger.debug("권한 추가 : " +userGroupId + "/" + menuNo + "/R:" + authRYn + "/C:" +  authCYn + "/U:" + authUYn + "/D:" + authDYn + "/P:" +  authPYn);
				
				if("5".equals(level)){//4레벨인 경우, 상위 레벨 체크해서 추가 처리
					String menu3Level = authMngMapper.getUpMenuNo(menuNo);
					int menu3LevelAuthCnt = authMngMapper.getMenuAuthCnt(userGroupId,menu3Level);
					if(menu3LevelAuthCnt == 0){
						authMngMapper.insertAuth(userGroupId, menu3Level, "Y", "Y", "Y", "Y", "Y");
						logger.debug("권한 추가 3레벨  : " +userGroupId + "/" + menu3Level);	
					}
					
					String menu2Level = authMngMapper.getUpMenuNo(menu3Level);
					int menu2LevelAuthCnt = authMngMapper.getMenuAuthCnt(userGroupId,menu2Level);
					if(menu2LevelAuthCnt == 0){
						authMngMapper.insertAuth(userGroupId, menu2Level, "Y", "Y", "Y", "Y", "Y");
						logger.debug("권한 추가 2레벨  : " +userGroupId + "/" + menu2Level);
					}
					
					String menu1Level = authMngMapper.getUpMenuNo(menu2Level);
					int menu1LevelAuthCnt = authMngMapper.getMenuAuthCnt(userGroupId,menu1Level);
					if(menu1LevelAuthCnt == 0){
						authMngMapper.insertAuth(userGroupId, menu1Level, "Y", "Y", "Y", "Y", "Y");
						logger.debug("권한 추가 1레벨  : " +userGroupId + "/" + menu1Level);
					}
					
					
				}else if("4".equals(level)){//3레벨인 경우, 상위 레벨 체크해서 추가 처리
					String menu2Level = authMngMapper.getUpMenuNo(menuNo);
					int menu2LevelAuthCnt = authMngMapper.getMenuAuthCnt(userGroupId,menu2Level);
					if(menu2LevelAuthCnt == 0){
						authMngMapper.insertAuth(userGroupId, menu2Level, "Y", "Y", "Y", "Y", "Y");
						logger.debug("권한 추가 2레벨  : " +userGroupId + "/" + menu2Level);
					}
					
					String menu1Level = authMngMapper.getUpMenuNo(menu2Level);
					int menu1LevelAuthCnt = authMngMapper.getMenuAuthCnt(userGroupId,menu1Level);
					if(menu1LevelAuthCnt == 0){
						authMngMapper.insertAuth(userGroupId, menu1Level, "Y", "Y", "Y", "Y", "Y");
						logger.debug("권한 추가 1레벨  : " +userGroupId + "/" + menu1Level);
					}
				}else if("3".equals(level)){//2레벨의 경우, 상위 레벨 체크해서 추가 처리
					String menu1Level = authMngMapper.getUpMenuNo(menuNo);
					int menu1LevelAuthCnt = authMngMapper.getMenuAuthCnt(userGroupId,menu1Level);
					if(menu1LevelAuthCnt == 0){
						authMngMapper.insertAuth(userGroupId, menu1Level, "Y", "Y", "Y", "Y", "Y");
						logger.debug("권한 추가 1레벨  : " +userGroupId + "/" + menu1Level);
					}
				}
			}else{
				logger.debug("권한 변경 : " +userGroupId + "/" + menuNo + "/R:" + authRYn + "/C:" +  authCYn + "/U:" + authUYn + "/D:" + authDYn + "/P:" +  authPYn);
			}
		}
	}
}