package com.ntels.ccbs.system.mapper.auth.authMng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.auth.authMng.SoAuthMngVO;

/**
 * <PRE>
 * 1. ClassName: SoAuthMngMapper
 * 2. FileName : SoAuthMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.auth.authMng
 * 4. Comment  :
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 9:52:08
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface SoAuthMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: soAuthCount
	 * 2. ClassName : SoAuthMngMapper
	 * 3. Comment   : 사용자별 사업권한관리 리스트 수 정보조회 
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:52:12
	 * </PRE>
	 *   @return Integer 사용자별 사업권한관리 리스트 수
	 *   @param soAuth 사용자별 사업권한관리VO
	 */
	Integer soAuthCount(@Param(value = "soAuth")SoAuthMngVO soAuth);

	/**
	 * <PRE>
	 * 1. MethodName: soAuthList
	 * 2. ClassName : SoAuthMngMapper
	 * 3. Comment   : 사용자별 사업권한관리 리스트 정보조회 
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:52:14
	 * </PRE>
	 *   @return List<SoAuthMngVO> 사용자별 사업권한관리 리스트
	 *   @param soAuth 사용자별 사업권한관리VO
	 *   @param lng 언어코드
	 */
	List<SoAuthMngVO> soAuthList(@Param(value = "soAuth")SoAuthMngVO soAuth,@Param(value = "lng") String lng);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : SoAuthMngMapper
	 * 3. Comment   : 사용자별 사업권한관리 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:52:17
	 * </PRE>
	 *   @return int  등록여부
	 *   @param soAuth 사용자별 사업권한관리VO
	 */
	int insert(@Param(value = "soAuth")SoAuthMngVO soAuth);

	/**
	 * <PRE>
	 * 1. MethodName: deleteAll
	 * 2. ClassName : SoAuthMngMapper
	 * 3. Comment   : 해당사용자의 모든 so_id 삭제(등록)
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:52:19
	 * </PRE>
	 *   @return void
	 *   @param soAuth 사용자별 사업권한관리VO
	 */
	void deleteAll(@Param(value = "soAuth")SoAuthMngVO soAuth);

	/**
	 * <PRE>
	 * 1. MethodName: delete
	 * 2. ClassName : SoAuthMngMapper
	 * 3. Comment   :해당사용자의 모든 so_id 삭제(삭제)
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:52:21
	 * </PRE>
	 *   @return int 삭제유무
	 *   @param soAuth 사용자별 사업권한관리VO
	 */
	int delete(@Param(value = "soAuth")SoAuthMngVO soAuth);

	/**
	 * <PRE>
	 * 1. MethodName: baseSoId
	 * 2. ClassName : SoAuthMngMapper
	 * 3. Comment   : 해당사용자의 기본사업아이디 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오전 9:52:24
	 * </PRE>
	 *   @return String 기본SO_ID
	 *   @param soAuth 사용자별 사업권한관리VO
	 *   @param today 오늘날짜
	 */
	String baseSoId(@Param(value = "soAuth")SoAuthMngVO soAuth,@Param(value = "today") String today);

}