package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.UserGroupMngVO;

/**
 * <PRE>
 * 1. ClassName: UserGroupMngMapper
 * 2. FileName : UserGroupMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common.common
 * 4. Comment  :  사용자그룹팝업 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 2:44:43
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface UserGroupMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: userGroupList
	 * 2. ClassName : UserGroupMngMapper
	 * 3. Comment   : 사용자그룹팝업 리스트조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:44:45
	 * </PRE>
	 *   @return List<UserGroupMngVO> 사용자그룹팝업 리스트
	 *   @param userGroup 사용자그룹VO
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	List<UserGroupMngVO> userGroupList(@Param(value = "userGroup")UserGroupMngVO userGroup, 
			@Param(value = "sidx")String sidx, 
			@Param(value = "sord")String sord);
	
}