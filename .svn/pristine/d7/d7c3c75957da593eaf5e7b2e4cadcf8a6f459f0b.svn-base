package com.ntels.ccbs.system.service.configuration.codeMng;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpVO;

/**
 * <PRE>
 * 1. ClassName: CommonCodeMngService
 * 2. FileName : CommonCodeMngService.java
 * 3. Package  : com.ntels.ccbs.system.service.configuration.codeMng
 * 4. Comment  : 공통코드 관리 서비스
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 20. 오전 11:39:52
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 20.    : 신규개발
 * </PRE>
 */
public interface CommonCodeMngService {

	/**
	 * <PRE>
	 * 1. MethodName: getCodeGrpTreeList
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 그룹 리스트 Tree형 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:40:02
	 * </PRE>
	 *   @return List<Map<String,Object>>
	 *   @param lng
	 *   @return
	 */
	List<Map<String,Object>> getCodeGrpTreeList(String lng);

	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeList
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 상세 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:40:04
	 * </PRE>
	 *   @return List<CommonCodeVO>
	 *   @param condGroupId  조회 그룹ID
	 *   @param sidx Sort Key
	 *   @param sord Sort Type(DESC,ASC)
	 *   @param lng 조회 요형 언어 유형
	 *   @return
	 */
	List<CommonCodeVO> getCommonCodeList(String condGroupId, String sidx, String sord, String lng);
	
	/**
	 * <PRE>
	 * 1. MethodName: getLngList
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 빈 언어 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:40:05
	 * </PRE>
	 *   @return List<Map<String,Object>> 코드 언어 리스트
	 *   @return
	 */
	List<Map<String,Object>> getLngList();

	/**
	 * <PRE>
	 * 1. MethodName: insertCodeDetailInfo
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 추가 서비스
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:40:22
	 * </PRE>
	 *   @return void
	 *   @param insertCodeDetail 추가 공통코드 VO
	 */
	void insertCodeDetailInfo(CommonCodeVO insertCodeDetail);

	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeDetail
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 삭제 서비스
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:40:25
	 * </PRE>
	 *   @return void
	 *   @param codeGrp 삭제 대상 그룹 ID
	 *   @param code 삭제 대상 코드
	 */
	void deleteCodeDetail(String codeGrp, String code);

	/**
	 * <PRE>
	 * 1. MethodName: updateCodeDetailInfo
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 수정 서비스
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:40:29
	 * </PRE>
	 *   @return void
	 *   @param updateCodeDetal 수정 대상 공통코드 VO
	 */
	void updateCodeDetailInfo(CommonCodeVO updateCodeDetal);

	/**
	 * <PRE>
	 * 1. MethodName: insertCodeGrpInfo
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 그룹 추가 서비스
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:40:32
	 * </PRE>
	 *   @return void
	 *   @param insertCodeGrpInfo 추가할 공통코드 그룹 VO
	 */
	void insertCodeGrpInfo(CommonGrpVO insertCodeGrpInfo);

	/**
	 * <PRE>
	 * 1. MethodName: getCodeGrpInfo
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 그룹 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 11:40:36
	 * </PRE>
	 *   @return CommonGrpVO 공통코드 그룹 VO
	 *   @param codeGrp 조회 할 공통코드 그룹 ID
	 *   @return
	 */
	CommonGrpVO getCodeGrpInfo(String codeGrp);

	/**
	 * <PRE>
	 * 1. MethodName: updateCodeGrpInfo
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 그룹 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 8:10:26
	 * </PRE>
	 *   @return void
	 *   @param updateCodeGrpInfo 수정할 공통코드 VO
	 */
	void updateCodeGrpInfo(CommonGrpVO updateCodeGrpInfo);

	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeGrp
	 * 2. ClassName : CommonCodeMngService
	 * 3. Comment   : 공통코드 그룹 삭제
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 9:29:02
	 * </PRE>
	 *   @return void
	 *   @param codeGrp
	 */
	void deleteCodeGrp(String codeGrp);

}