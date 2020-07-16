package com.ntels.ccbs.system.mapper.log.logMng;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.log.logMng.AccessLogHistVO;

/**
 * <PRE>
 * 1. ClassName: AccessLogHistMapper
 * 2. FileName : AccessLogHistMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.log.logMng
 * 4. Comment  :접속로그조회 Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 6:04:14
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface AccessLogHistMapper {

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : AccessLogHistMapper
	 * 3. Comment   : 접속로그조회 리스트 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 6:04:16
	 * </PRE>
	 *   @return Integer 접속로그조회 리스트 수
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param lng 언어코드
	 *   @param access 접속로그조회VO
	 */
	Integer count(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord,
			@Param(value = "lng")String lng,  @Param(value = "access")AccessLogHistVO access);

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : AccessLogHistMapper
	 * 3. Comment   : 접속로그조회 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 6:04:18
	 * </PRE>
	 *   @return List<AccessLogHistVO> 접속로그조회 리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param end 페이징에 보여질 첫번째 index
	 *   @param start 페이징에 보여질 마지막 index
	 *   @param lng 언어코드
	 *   @param access 접속로그조회VO
	 */
	List<AccessLogHistVO> list(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord, @Param(value = "start")String start,
			@Param(value = "end")String end, @Param(value = "lng")String lng,  @Param(value = "access")AccessLogHistVO access);

	List<Map<String,Object>> listExcel(@Param(value="userId")String condUserId, @Param(value="sdate")String sdate, @Param(value="edate")String edate, @Param(value="lng")String lng);
	
}