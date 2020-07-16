package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.ViewPathVO;

/**
 * <PRE>
 * 1. ClassName: ViewPathMngMapper
 * 2. FileName : ViewPathMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common.common
 * 4. Comment  : viewPath관리 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:14:26
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface ViewPathMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : ViewPathMngMapper
	 * 3. Comment   : viewPath 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:14:28
	 * </PRE>
	 *   @return List<ViewPathVO> viewPath 리스트
	 *   @param view viewPath관리 VO
	 *   @param lng 언어코드
	 */
	List<ViewPathVO> list(@Param(value = "lng")String lng, @Param(value = "view")ViewPathVO view);

	/**
	 * <PRE>
	 * 1. MethodName: getCommonCdList
	 * 2. ClassName : ViewPathMngMapper
	 * 3. Comment   : 공통코드 시스템구분 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:14:30
	 * </PRE>
	 *   @return List<ViewPathVO> 공통코드 시스템구분 리스트
	 *   @param view viewPath관리 VO
	 *   @param lng 언어코드n
	 */
	List<ViewPathVO> getCommonCdList( @Param(value = "view") ViewPathVO view, @Param(value = "lng")String lng);
	
}