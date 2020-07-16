package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.auth.authMng.AuthGroupMngVO;
import com.ntels.ccbs.system.domain.common.common.SoSearchVO;

/**
 * <PRE>
 * 1. ClassName: SoSearchMngMapper
 * 2. FileName : SoSearchMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common.common
 * 4. Comment  : 사업검색 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:25:57
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface SoSearchMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : SoSearchMngMapper
	 * 3. Comment   : 사업검색 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 10:37:06
	 * </PRE>
	 *   @return List<SoSearchVO> 사업리스트
	 *   @param lng 언어코드
	 *   @param soSearch 사업검색 VO
	 *   @return
	 */
	List<SoSearchVO> list(@Param(value = "lng")String lng, @Param(value = "soSearch")SoSearchVO soSearch);
	
}