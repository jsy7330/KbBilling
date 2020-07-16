package com.ntels.ccbs.system.service.common.common;

import java.util.List;

import com.ntels.ccbs.system.domain.common.common.UserGroupMngVO;

/**
 * <PRE>
 * 1. ClassName: UserGroupMngService
 * 2. FileName : UserGroupMngService.java
 * 3. Package  : com.ntels.ccbs.system.service.common.common
 * 4. Comment  : 사용자그룹팝업 서비스
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 2:43:39
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
public interface UserGroupMngService {

	/**
	 * <PRE>
	 * 1. MethodName: userGroupList
	 * 2. ClassName : UserGroupMngService
	 * 3. Comment   : 사용자그룹리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:43:36
	 * </PRE>
	 *   @return List<UserGroupMngVO> 사용자그룹 리스트
	 *   @param userGroup 사용자그룹VO
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	List<UserGroupMngVO> userGroupList(UserGroupMngVO userGroup, String sidx, String sord);
	
}