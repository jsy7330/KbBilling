package com.ntels.ccbs.system.service.common.common;

import java.util.Map;

import com.ntels.ccbs.system.domain.common.common.PostMngVO;

/**
 * <PRE>
 * 1. ClassName: PostMngService
 * 2. FileName : PostMngService.java
 * 3. Package  : com.ntels.ccbs.system.service.common.common
 * 4. Comment  : 도로명주소 서비스
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 5:14:52
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
public interface PostMngService {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : PostMngService
	 * 3. Comment   : 도로명주소 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:14:47
	 * </PRE>
	 *   @return Map<String,Object> 도로명주소 조회 정보
	 *   @param sidx Sort 대상 키
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param lng 언어코드
	 *   @param postMng  도로명주소VO
	 */
	Map<String, Object> list(String sidx, int page, int rows, String lng, PostMngVO postMng);

	/**
	 * <PRE>
	 * 1. MethodName: searchCount
	 * 2. ClassName : PostMngService
	 * 3. Comment   : 검색키워드 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:14:50
	 * </PRE>
	 *   @return Map<String,Object> 검색키워드 정보
	 *   @param postMng 도로명주소VO
	 */
	Map<String, Object> searchCount(PostMngVO postMng);
	
}