package com.ntels.ccbs.system.mapper.log.logMng;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.log.logMng.ProcessLogHistVO;
import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;

/**
 * <PRE>
 * 1. ClassName: ProcessLogHistMapper
 * 2. FileName : ProcessLogHistMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.log.logMng
 * 4. Comment  : 사용이력조회 MAPPER
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 6:16:03
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface ProcessLogHistMapper {

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : ProcessLogHistMapper
	 * 3. Comment   : 사용이력 리스트 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 6:16:05
	 * </PRE>
	 *   @return Integer 사용이력 리스트 수
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param lng 언어코드
	 *   @param process 사용이력VO
	 */
	Integer count(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord,
			@Param(value = "lng")String lng,  @Param(value = "process")ProcessLogHistVO process);

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : ProcessLogHistMapper
	 * 3. Comment   :사용이력 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 6:16:08
	 * </PRE>
	 *   @return List<UserMngVO> 사용이력 리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param end 페이징에 보여질 첫번째 index
	 *   @param start 페이징에 보여질 마지막 index
	 *   @param lng 언어코드
	 *   @param process 사용이력VO
	 */
	List<UserMngVO> list(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord, @Param(value = "start")String start,
			@Param(value = "end")String end, @Param(value = "lng")String lng,  @Param(value = "process")ProcessLogHistVO process);

	/**
	 * <PRE>
	 * 1. MethodName: listExcel
	 * 2. ClassName : ProcessLogHistMapper
	 * 3. Comment   : 사용이력조회 엑셀다운로드
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 28. 오후 2:22:38
	 * </PRE>
	 *   @return List<Map<String,Object>> 사용이력조회 엑셀다운로드
	 *   @param condUserId 사용자아이디 조건
	 *   @param sdate 시작날짜
	 *   @param edate 마지막 날짜
	 *   @param lng 언어코드
	 *   @param condSessionId 세션아이디 조건
	 *   @param condWorkType 사용유형 조건
	 */
	List<Map<String, Object>> listExcel(@Param(value="condUserId")String condUserId
			, @Param(value="sdate")String sdate, @Param(value="edate")String edate
			, @Param(value="lng")String lng,@Param(value="condSessionId")String condSessionId
			,@Param(value="condWorkType")String condWorkType);
	
}