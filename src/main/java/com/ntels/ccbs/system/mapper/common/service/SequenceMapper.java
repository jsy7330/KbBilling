package com.ntels.ccbs.system.mapper.common.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * <PRE>
 * 1. ClassName: SequenceMapper
 * 2. FileName : SequenceMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common
 * 4. Comment  : Sequence 처리 Mapper
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 2. 25. 오후 3:18:52
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 2. 25.    : 신규개발
 * </PRE>
 */
/**
 * <PRE>
 * 1. ClassName: SequenceMapper
 * 2. FileName : SequenceMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common
 * 4. Comment  :
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 5. 11. 오후 3:11:13
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 5. 11.    : 신규개발
 * </PRE>
 */
/**
 * <PRE>
 * 1. ClassName: SequenceMapper
 * 2. FileName : SequenceMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common
 * 4. Comment  :
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 5. 11. 오후 3:12:01
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 5. 11.    : 신규개발
 * </PRE>
 */
/**
 * <PRE>
 * 1. ClassName: SequenceMapper
 * 2. FileName : SequenceMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.common.service
 * 4. Comment  :
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 7. 13. 오전 9:03:56
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 7. 13.    : 신규개발
 * </PRE>
 */
@Component
public interface SequenceMapper {
	
	/**
	 * <PRE>
	 * 1. MethodName: getSequence
	 * 2. ClassName : SequenceMapper
	 * 3. Comment   : 현재의 Sequence 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 26. 오후 2:01:07
	 * </PRE>
	 *   @return int 조회된 Sequence 값
	 *   @param modCd Sequence 코드
	 *   @return
	 */
	int getSequence(@Param("modCd") String modCd);
	
	/**
	 * <PRE>
	 * 1. MethodName: updateNextSequence
	 * 2. ClassName : SequenceMapper
	 * 3. Comment   : 현재의 Sequence 증가 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 26. 오후 2:01:10
	 * </PRE>
	 *   @return int 수정 Count
	 *   @param modCd Sequence 코드
	 *   @return
	 */
	int updateNextSequence(@Param("modCd") String modCd);
	
	/**
	 * <PRE>
	 * 1. MethodName: getNowDate
	 * 2. ClassName : SequenceMapper
	 * 3. Comment   : Sequence의 현재 일자 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 26. 오후 2:01:14
	 * </PRE>
	 *   @return Map<String,Object> <br>
	 *   							"CURR_YYYY" 현재 년<br>
	 *   							"CURR_MM" 현재 월<br>
	 *   							"CURR_DD" 현재 일
	 *   @param modCd Sequence 코드
	 *   @return
	 */
	Map<String,Object> getNowDate(@Param("modCd") String modCd);
	
	/**
	 * <PRE>
	 * 1. MethodName: updateCycleSequence
	 * 2. ClassName : SequenceMapper
	 * 3. Comment   : 현재 Sequence의 날짜를 Next 값으로 초기화
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 26. 오후 2:01:17
	 * </PRE>
	 *   @return int 수정 Count
	 *   @param newDate <br>
	 *   				"CURR_YYYY" 수정 년<br>
	 *   				"CURR_MM" 수정 월<br>
	 *   				"CURR_DD" 수정 일
	 *   @return
	 */
	int updateCycleSequence(Map<String,Object> newDate);
	
	

	/**
	 * <PRE>
	 * 1. MethodName: getSubSequence
	 * 2. ClassName : SequenceMapper
	 * 3. Comment   : 서브 시퀀스 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 11. 오후 3:11:15
	 * </PRE>
	 *   @return int
	 *   @param targetTblNm
	 *   @param targetColNm
	 *   @param keyCode
	 *   @return
	 */
	int getSubSequence(@Param("targetTblNm") String targetTblNm
			,@Param("targetColNm") String targetColNm
			,@Param("keyCode") String keyCode);
	
	

	/**
	 * <PRE>
	 * 1. MethodName: updateNextSubSequence
	 * 2. ClassName : SequenceMapper
	 * 3. Comment   : 서브 시퀀스 증가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 11. 오후 3:12:03
	 * </PRE>
	 *   @return int
	 *   @param targetTblNm
	 *   @param targetColNm
	 *   @param keyCode
	 *   @return
	 */
	int updateNextSubSequence(@Param("targetTblNm") String targetTblNm
			,@Param("targetColNm") String targetColNm
			,@Param("keyCode") String keyCode);
	

	/**
	 * <PRE>
	 * 1. MethodName: insertSubSequence
	 * 2. ClassName : SequenceMapper
	 * 3. Comment   : 신규 서브 시퀀스 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 11. 오후 3:12:39
	 * </PRE>
	 *   @return int
	 *   @param targetTblNm
	 *   @param targetColNm
	 *   @param keyCode
	 *   @return
	 */
	int insertSubSequence(@Param("targetTblNm") String targetTblNm
			,@Param("targetColNm") String targetColNm
			,@Param("keyCode") String keyCode);


	/**
	 * <PRE>
	 * 1. MethodName: updateNextSequenceMulti
	 * 2. ClassName : SequenceMapper
	 * 3. Comment   : 복수 시퀀스 증가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 7. 13. 오전 9:03:59
	 * </PRE>
	 *   @return int 수정 Count
	 *   @param modCd 
	 *   @param size
	 *   @return
	 */
	int updateNextSequenceMulti(@Param("modCd") String modCd, @Param("size") int size);
}
