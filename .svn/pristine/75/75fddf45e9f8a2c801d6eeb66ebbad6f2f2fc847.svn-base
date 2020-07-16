package com.ntels.ccbs.system.mapper.configuration.codeMng;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeLngVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpLngVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpVO;


/**
 * <PRE>
 * 1. ClassName: CommonCodeMngMapper
 * 2. FileName : CommonCodeMngMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.configuration.codeMng
 * 4. Comment  : 공통코드 관리 Mapper
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 20. 오전 11:52:10
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 20.    : 신규개발
 * </PRE>
 */
@Component
public interface CommonCodeMngMapper {

	/**
	 * <PRE>
	 * 1. MethodName: getCodeGrpList
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:12
	 * </PRE>
	 *   @return List<CommonGrpVO>
	 *   @param codeType 시스템ID
	 *   @param lng 사용언어 코드
	 */
	List<CommonGrpVO> getCodeGrpList(@Param(value = "codeType") String codeType, @Param(value = "lng")String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeList
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:14
	 * </PRE>
	 *   @return List<CommonCodeVO> 공통코드 상세리스트
	 *   @param condGroupId 그룹ID
	 *   @param sidx Sort키
	 *   @param sord Sort 유형(DESC,ASC)
	 *   @param lng 사용언어 코드
	 */
	List<CommonCodeVO> getCommonCodeList(@Param(value = "condGroupId")String condGroupId,
			@Param(value ="sidx")String sidx,
			@Param(value ="sord")String sord,
			@Param(value = "lng")String lng);
	
	/**
	 * <PRE>
	 * 1. MethodName: getCodeDetailLngList
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 언어 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:17
	 * </PRE>
	 *   @return List<Map<String,Object>> 언어리스트
	 *   @param commonGrp 공통코드 그룹ID
	 *   @param commonCd 공통코드
	 */
	List<Map<String,Object>> getCodeDetailLngList(@Param(value="commonGrp") String commonGrp, @Param(value="commonCd") String commonCd);
	
	/**
	 * <PRE>
	 * 1. MethodName: getLngList
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 빈 언어 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:20
	 * </PRE>
	 *   @return List<Map<String,Object>> 빈 언어리스트
	 */
	List<Map<String,Object>> getLngList();

	/**
	 * <PRE>
	 * 1. MethodName: getCodeDetailCnt
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 카운트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:23
	 * </PRE>
	 *   @return int 조회 Count
	 *   @param commonGrp 공통코드 그룹
	 *   @param commonCd 공통코드
	 */
	int getCodeDetailCnt(@Param(value="commonGrp")String commonGrp, @Param(value="commonCd")String commonCd);

	/**
	 * <PRE>
	 * 1. MethodName: insertCodeDetail
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:26
	 * </PRE>
	 *   @return int 추가된 Count
	 *   @param insertCodeDetal 추가 공통코드 상세 VO
	 */
	int insertCodeDetail(@Param(value="code")CommonCodeVO insertCodeDetal);

	/**
	 * <PRE>
	 * 1. MethodName: insertCodeDetailLng
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 언어 정보 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:29
	 * </PRE>
	 *   @return int 추가된 언어정보 Count
	 *   @param lng 추가 언어 정보 VO
	 */
	int insertCodeDetailLng(@Param(value="lng")CommonCodeLngVO lng);

	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeDetail
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 삭제 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:32
	 * </PRE>
	 *   @return int 삭제 Count
	 *   @param codeGrp 삭제 대상 공통코드 그룹
	 *   @param code 삭제 대상 공통코드
	 */
	int deleteCodeDetail(@Param(value="codeGrp")String codeGrp, @Param(value="code")String code);

	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeDetailLng
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 언어 정보 삭제 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:35
	 * </PRE>
	 *   @return int 삭제 Count
	 *   @param codeGrp 삭제 대상 공통코드 그룹
	 *   @param code 삭제 대상 공통코드
	 */
	int deleteCodeDetailLng(@Param(value="codeGrp")String codeGrp, @Param(value="code")String code);

	/**
	 * <PRE>
	 * 1. MethodName: updateCodeDetail
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 수정 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:38
	 * </PRE>
	 *   @return int 수정 Count
	 *   @param updateCodeDetail 공통코드 상세 수정 VO
	 *   @return
	 */
	int updateCodeDetail(@Param(value="code")CommonCodeVO updateCodeDetail);

	/**
	 * <PRE>
	 * 1. MethodName: updateCodeDetailLng
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 언어 정보 수정 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:41
	 * </PRE>
	 *   @return int 수정 Count
	 *   @param lng 언어정보 VO
	 */
	int updateCodeDetailLng(@Param(value="lng")CommonCodeLngVO lng);

	/**
	 * <PRE>
	 * 1. MethodName: getCodeGrpCnt
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 Count조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:45
	 * </PRE>
	 *   @return int 그룹 Count
	 *   @param commonGrp 조회 대상 그룹ID
	 */
	int getCodeGrpCnt(@Param(value="commonGrp")String commonGrp);

	/**
	 * <PRE>
	 * 1. MethodName: insertCodeGrp
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통 코드 그룹 추가 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:47
	 * </PRE>
	 *   @return int 추가 Count
	 *   @param insertCodeGrpInfo 추가 할 그룹 VO
	 */
	int insertCodeGrp(@Param(value="grpInfo")CommonGrpVO insertCodeGrpInfo);

	/**
	 * <PRE>
	 * 1. MethodName: insertCodeGrpLng
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 언어 정보 추가 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:50
	 * </PRE>
	 *   @return int 추가 Count
	 *   @param lng 언어정보 VO
	 */
	int insertCodeGrpLng(@Param(value="lng")CommonGrpLngVO lng);

	/**
	 * <PRE>
	 * 1. MethodName: getCodeGrpInfo
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:53
	 * </PRE>
	 *   @return CommonGrpVO 공통코드 그룹 정보 VO
	 *   @param codeGrp 조회 대상 그룹ID
	 *   @return
	 */
	CommonGrpVO getCodeGrpInfo(@Param(value="codeGrp")String codeGrp);

	/**
	 * <PRE>
	 * 1. MethodName: getCodeGrpLngList
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 언어정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:52:55
	 * </PRE>
	 *   @return List<Map<String,Object>> 언어정보 리스트
	 *   @param codeGrp 조회대상 그룹 ID
	 */
	List<Map<String, Object>> getCodeGrpLngList(@Param(value="codeGrp") String codeGrp);

	/**
	 * <PRE>
	 * 1. MethodName: updateCodeGrp
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 수정 Mapper
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 8:22:19
	 * </PRE>
	 *   @return int 수정 Cnt
	 *   @param updateCodeGrpInfo 수정할 공통코드 정보
	 */
	int updateCodeGrp(@Param(value="codeGrp")CommonGrpVO updateCodeGrpInfo);

	/**
	 * <PRE>
	 * 1. MethodName: updateCodeGrpLng
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 언어 정보 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 8:23:07
	 * </PRE>
	 *   @return int 수정 Cnt
	 *   @param lng 언어정보 VO
	 */
	int updateCodeGrpLng(@Param(value="lng")CommonGrpLngVO lng);

	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeGrp
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 삭제
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 9:31:07
	 * </PRE>
	 *   @return int 삭제 Cnt
	 *   @param codeGrp 삭제 대상 그룹코드
	 */
	int deleteCodeGrp(String codeGrp);

	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeGrpLng
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 그룹 언어 정보 삭제
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 9:34:57
	 * </PRE>
	 *   @return int 삭제 Cnt
	 *   @param codeGrp 삭제 대상 그룹코드
	 */
	int deleteCodeGrpLng(String codeGrp);

	/**
	 * <PRE>
	 * 1. MethodName: deleteCode
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 삭제 삭제 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 9:35:21
	 * </PRE>
	 *   @return int 삭제 Cnt
	 *   @param codeGrp 삭제 대상 그룹코드
	 */
	int deleteCode(String codeGrp);

	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeLng
	 * 2. ClassName : CommonCodeMngMapper
	 * 3. Comment   : 공통코드 상세 언어정보 삭제 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 9:35:57
	 * </PRE>
	 *   @return int 삭제 Cnt
	 *   @param codeGrp 삭제 대상 그룹코드
	 */
	int deleteCodeLng(String codeGrp);
	
}