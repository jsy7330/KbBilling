package com.ntels.ccbs.system.controller.common.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.UserGroupMngVO;
import com.ntels.ccbs.system.service.common.common.UserGroupMngService;

/**
 * <PRE>
 * 1. ClassName: UserGroupMngController
 * 2. FileName : UserGroupMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.common.common
 * 4. Comment  : 사용자그룹팝업 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 2:27:44
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/common/common/userGroupMng")
public class UserGroupMngController {
	/**
	 * 사용자그룹 메인 URL
	 */
	private static String URL = "system/common/common/userGroupMng";
	/**
	 * 사용자그룹 서비스
	 */
	@Autowired UserGroupMngService userGroupMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: userGroupListPopup
	 * 2. ClassName : UserGroupMngController
	 * 3. Comment   : 사용자그룹팝업 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:30:00
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userGroup 사용자그룹VO
	 *   @param multiFlag multiselect 구분
	 *   @param userGroupId 사용자그룹ID
	 *   @param userGroupName 사용자그룹명
	 *   @param userId 사용자ID
	 *   @param baseId user_ID에 해당하는 사용자그룹ID 
	 *   @param request {@link HttpServletRequest}
	 *   @throws Exception 
	 */
	@RequestMapping(value = "userGroupListPopup", method = RequestMethod.POST)
	public String userGroupListPopup(Model model,UserGroupMngVO userGroup
			,String multiFlag
			,String userGroupId,String userGroupName,String userId,String baseId
			, HttpServletRequest request) throws Exception {
		model.addAttribute("userGroupId", userGroupId);
		model.addAttribute("userGroupName", userGroupName);
		model.addAttribute("userId", userId);
		userGroup.setMultiFlag(multiFlag);
		model.addAttribute("baseId",baseId);
		model.addAttribute("userGroup", userGroup);
		return  URL+"/ajax/userGroupListPopup";
		
	}

	/**
	 * <PRE>
	 * 1. MethodName: userGroupListActionPopup
	 * 2. ClassName : UserGroupMngController
	 * 3. Comment   : 사용자그룹ID 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:42:32
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param userGroup 사용자그룹VO
	 *   @param request {@link HttpServletRequest}
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @throws Exception
	 */
	@RequestMapping(value = "userGroupListActionPopup", method = RequestMethod.POST)
	public String userGroupListActionPopup(
			UserGroupMngVO userGroup
			,Model model
			, HttpServletRequest request
			,String sidx
			,String sord) throws Exception {
		List<UserGroupMngVO> userGroupList = userGroupMngService.userGroupList(userGroup,sidx,sord);
		model.addAttribute("userGroupList", userGroupList);
		return  URL + "/ajax/userGroupListActionPopup";
	}
}