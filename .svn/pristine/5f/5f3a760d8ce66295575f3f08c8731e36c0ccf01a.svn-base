package com.ntels.ccbs.system.mapper.auth.authMng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.auth.authMng.AuthGroupMngVO;

/**
 * <PRE>
 * 1. ClassName: ChargeMapper
 * 2. FileName : ChargeMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.auth.authMng
 * 4. Comment  : 그룹관리 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:05:28
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface AuthGroupMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : ChargeMapper
	 * 3. Comment   : 그룹관리 리스트 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:38:28
	 * </PRE>
	 *   @return Integer 그룹관리 리스트 수
	 *   @param authGroup 그룹관리VO
	 */
	Integer count(@Param(value = "authGroup")AuthGroupMngVO authGroup);

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : ChargeMapper
	 * 3. Comment   :그룹관리 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:39:27
	 * </PRE>
	 *   @return List<AuthGroupMngVO> 그룹관리 리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param lng 언어코드
	 *   @param authGroup 그룹관리VO
	 */
	List<AuthGroupMngVO> list(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord,
			@Param(value = "start")String start,@Param(value = "end")String end, @Param(value = "lng")String lng, 
			@Param(value = "authGroup")AuthGroupMngVO authGroup);

	/**
	 * <PRE>
	 * 1. MethodName: checkUserId
	 * 2. ClassName : ChargeMapper
	 * 3. Comment   : 기본그룹ID 중복체크
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:39:53
	 * </PRE>
	 *   @return int 기본그룹ID 중복체크 여부
	 *   @param authGroup 그룹관리VO
	 */
	int checkUserId(AuthGroupMngVO authGroup);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : ChargeMapper
	 * 3. Comment   : 그룹관리 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:40:21
	 * </PRE>
	 *   @return int 등록여부
	 *   @param authGroup 그룹관리VO
	 */
	int insert(AuthGroupMngVO authGroup);

	/**
	 * <PRE>
	 * 1. MethodName: update
	 * 2. ClassName : ChargeMapper
	 * 3. Comment   : 그룹관리 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:40:40
	 * </PRE>
	 *   @return int 수정여부
	 *   @param authGroup 그룹관리VO
	 */
	int update(AuthGroupMngVO authGroup);

	/**
	 * <PRE>
	 * 1. MethodName: deleteRole
	 * 2. ClassName : ChargeMapper
	 * 3. Comment   : 그룹정보 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:41:03
	 * </PRE>
	 *   @return void
	 *   @param authGroup 그룹관리VO
	 */
	void deleteRole(AuthGroupMngVO authGroup);

	/**
	 * <PRE>
	 * 1. MethodName: deleteAuth
	 * 2. ClassName : ChargeMapper
	 * 3. Comment   : 해당그룹에 해당하는 모든 권한 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:42:43
	 * </PRE>
	 *   @return void
	 *   @param authGroup
	 */
	void deleteAuth(AuthGroupMngVO authGroup);

	/**
	 * <PRE>
	 * 1. MethodName: delete
	 * 2. ClassName : ChargeMapper
	 * 3. Comment   : 기본그룹정보 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:43:41
	 * </PRE>
	 *   @return int 삭제여부
	 *   @param authGroup 그룹관리VO
	 */
	int delete(AuthGroupMngVO authGroup);

}