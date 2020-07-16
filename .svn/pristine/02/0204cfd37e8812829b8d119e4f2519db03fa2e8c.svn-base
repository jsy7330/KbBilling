package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.UserSearchVO;

/**
 * <PRE>
 * 1. ClassName: UserSearchMngMapper
 * 2. FileName : UserSearchMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common.common
 * 4. Comment  : 사용자검색팝업 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:59:38
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface UserSearchMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : UserSearchMngMapper
	 * 3. Comment   : 사용자수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:59:33
	 * </PRE>
	 *   @return Integer 사용자수
	 *   @param userSearch 사용자검색VO
	 *   @param today 오늘날짜
	 *   @param soAuthList 해당 사용자의 사업권한
	 *   @return
	 */
	Integer count(@Param(value = "userSearch")UserSearchVO userSearch,@Param(value = "today") String today,@Param(value = "soAuthList") List<Map<String, Object>> soAuthList);

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : UserSearchMngMapper
	 * 3. Comment   :
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:59:35
	 * </PRE>
	 *   @return List<UserSearchVO> 사용자 조회
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param start 페이징 첫번째 index
	 *   @param end 페이징 마지막 index
	 *   @param lng 언어코드
	 *   @param userSearch 사용자검색VO
	 *   @param today 오늘날짜
	 *   @param soAuthList 해당 사용자의 사업권한
	 */
	List<UserSearchVO> list(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord, @Param(value = "start")String start,
			@Param(value = "end")String end, @Param(value = "lng")String lng,@Param(value = "userSearch") UserSearchVO userSearch
			,@Param(value = "today") String today,@Param(value = "soAuthList") List<Map<String, Object>> soAuthList);
	
}