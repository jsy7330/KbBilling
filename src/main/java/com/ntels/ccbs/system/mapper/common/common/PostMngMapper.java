package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.PostMngVO;

/**
 * <PRE>
 * 1. ClassName: PostMngMapper
 * 2. FileName : PostMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common.common
 * 4. Comment  : 도로명주소 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 5:20:59
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface PostMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   : 도로명주소 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:20:55
	 * </PRE>
	 *   @return List<PostMngVO> 도로명주소 조회
	 *   @param sidx 대상 키
	 *   @param end 페이징에 보일 첫번째 index
	 *   @param start 페이징에 보일 마지막 index
	 *   @param lng 언어코드
	 *   @param postMng 도로명주소VO
	 *   @param soAuthList 해당아이디가 가지고있는 사업권한 리스트
	 */
	List<PostMngVO> list(@Param(value = "sidx")String sidx, 
			@Param(value = "start")String start,@Param(value = "end")String end, @Param(value = "lng")String lng, @Param(value = "postMng")PostMngVO postMng,
			List<Map<String, Object>> soAuthList);

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   :도로명주소 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:25:20
	 * </PRE>
	 *   @return Integer 도로명주소 수
	 *   @param postMng 도로명주소VO
	 *   @param soAuthList 해당아이디가 가지고있는 사업권한 리스트
	 */
	Integer count(@Param(value = "postMng")PostMngVO postMng, @Param(value = "user")List<Map<String, Object>> soAuthList);

	/**
	 * <PRE>
	 * 1. MethodName: getSidoNm
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   : 시도명수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:25:50
	 * </PRE>
	 *   @return int 시도명수
	 *   @param str 입력값
	 */
	int getSidoNm(@Param(value = "str") String str);

	/**
	 * <PRE>
	 * 1. MethodName: getGugunNmConcatString
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   : 구군명 수 조회(구군명이 2개의 단어일때)
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:26:29
	 * </PRE>
	 *   @return int  구군명 수
	 *   @param str1 입력값
	 *   @param str2 입력값
	 */
	int getGugunNmConcatString(@Param(value = "str1")String str1, @Param(value = "str2") String str2 );
	
	/**
	 * <PRE>
	 * 1. MethodName: getGugunNm
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   : 구군명 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:27:31
	 * </PRE>
	 *   @return int  구군명 수
	 *   @param str 입력값
	 */
	int getGugunNm(@Param(value = "str")String str);
	
	/**
	 * <PRE>
	 * 1. MethodName: getDongNm
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   : 동명 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:28:12
	 * </PRE>
	 *   @return int 동명 수
	 *   @param str 입력값
	 */
	int getDongNm(@Param(value = "str")String str);
	
	/**
	 * <PRE>
	 * 1. MethodName: getDongMyonNm
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   : 읍동면도로명 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:28:30
	 * </PRE>
	 *   @return int 읍동면도로명 수
	 *   @param str 입력값
	 */
	int getDongMyonNm(@Param(value = "str")String str);

	/**
	 * <PRE>
	 * 1. MethodName: getBilgNo1
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   : 건물번호1 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:28:52
	 * </PRE>
	 *   @return int 건물번호1 수
	 *   @param str 입력값
	 */
	int getBilgNo1(@Param(value = "str")String str);

	/**
	 * <PRE>
	 * 1. MethodName: getBilgNo2
	 * 2. ClassName : PostMngMapper
	 * 3. Comment   :건물번호2 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:28:55
	 * </PRE>
	 *   @return int 건물번호2 수
	 *   @param str 입력값
	 */
	int getBilgNo2(@Param(value = "str")String str);
	
}