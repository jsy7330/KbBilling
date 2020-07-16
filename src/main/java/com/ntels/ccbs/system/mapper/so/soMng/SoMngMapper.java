package com.ntels.ccbs.system.mapper.so.soMng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.so.soMng.SoMngVO;

@Component
public interface SoMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : SoMngMapper
	 * 3. Comment   : 사업리스트 총 갯수조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:26:29
	 * </PRE>
	 *   @return int 사업리스트 총 갯수
	 *   @param so 사업관리VO
	 */
	int count(@Param(value = "so")SoMngVO so);

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : SoMngMapper
	 * 3. Comment   : 사업리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:26:31
	 * </PRE>
	 *   @return List<SoMngVO> 사업리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param start 페이징 첫번째 index
	 *   @param end 페이징 마지막 index
	 *   @param lng 언어코드
	 *   @param so 사업관리VO
	 */
	List<SoMngVO> list( @Param(value = "sidx")String sidx, @Param(value = "sord")String sord 
			, @Param(value = "start")String start,@Param(value = "end")String end, @Param(value = "lng")String lng, @Param(value = "so")SoMngVO so);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : SoMngMapper
	 * 3. Comment   : 사업관리 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:26:35
	 * </PRE>
	 *   @return int 등록한 count
	 *   @param so 사업관리VO
	 */
	int insert(SoMngVO so);

	/**
	 * <PRE>
	 * 1. MethodName: update
	 * 2. ClassName : SoMngMapper
	 * 3. Comment   :사업관리 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:26:40
	 * </PRE>
	 *   @return int 수정한 count
	 *   @param so 사업관리VO
	 */
	int update(SoMngVO so);
	
	/**
	 * <PRE>
	 * 1. MethodName: getMaxSo
	 * 2. ClassName : SoMngMapper
	 * 3. Comment   : 사업ID 최대값 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:26:43
	 * </PRE>
	 *   @return Integer 사업ID 최대값
	 */
	Integer getMaxSo();

	/**
	 * <PRE>
	 * 1. MethodName: soAuthList
	 * 2. ClassName : SoMngMapper
	 * 3. Comment   : 사업ID,명 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:38:08
	 * </PRE>
	 *   @return List<SoMngVO> 사업ID,명 리스트
	 *   @param so 사업관리VO
	 */
	List<SoMngVO> soAuthList(@Param(value = "so")SoMngVO so);

	/**
	 * <PRE>
	 * 1. MethodName: checkSo
	 * 2. ClassName : SoMngMapper
	 * 3. Comment   : so_id 중복 여부조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 3:47:13
	 * </PRE>
	 *   @return int so_id 중복 여부
	 *   @param so 사업관리VO
	 */
	int checkSo(SoMngVO so);
	
}