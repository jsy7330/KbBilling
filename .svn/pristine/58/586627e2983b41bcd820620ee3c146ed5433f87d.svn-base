package com.ntels.ccbs.system.mapper.bulletin.bulletinMng;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.auth.authMng.AuthGroupMngVO;
import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;

/**
 * <PRE>
 * 1. ClassName: BulletinMapper
 * 2. FileName : BulletinMapper.java
 * 3. Package  : com.ntels.ccbs.system.mapper.bulletin.bulletinMng
 * 4. Comment  : 게시판Mapper
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 4:10:39
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Component
public interface BulletinMapper {

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 게시판 리스트 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:10:31
	 * </PRE>
	 *   @return int 게시판 리스트 수
	 *   @param bulletin 게시판VO
	 */
	int count(@Param(value = "bulletin")BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 게시판 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:10:42
	 * </PRE>
	 *   @return List<BulletinMngVO> 게시판 리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param start  페이징 첫번째 index
	 *   @param end 페이징 마지막 index
	 *   @param lng 언어코드
	 *   @param bulletin 게시판VO
	 */
	List<BulletinMngVO> list(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord, @Param(value = "start")String start,
			@Param(value = "end")String end, @Param(value = "lng")String lng,@Param(value = "bulletin")  BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 게시판등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:10:45
	 * </PRE>
	 *   @return int 등록여부
	 *   @param bulletin 게시판VO
	 */
	int insert(BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: insertAuth
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 권한 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:10:48
	 * </PRE>
	 *   @return int 등록여부
	 *   @param bulletin 게시판VO
	 */
	int insertAuth(BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: bulletinUpdateAction
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 게시판 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:10:53
	 * </PRE>
	 *   @return int 수정여부
	 *   @param bulletin 게시판VO
	 */
	int updateBulletinAction(BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: updateAuth
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 권한 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:10:56
	 * </PRE>
	 *   @return int 수정여부
	 *   @param bulletin 게시판VO
	 */
	int updateAuth(BulletinMngVO bulletin);


	/**
	 * <PRE>
	 * 1. MethodName: deleteBulletin
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 게시판 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:11:06
	 * </PRE>
	 *   @return int 삭제여부
	 *   @param bulletin 게시판VO
	 */
	int deleteBulletin(BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: deleteAuth
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 권한 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:11:09
	 * </PRE>
	 *   @return int 삭제여부
	 *   @param bulletin 게시판VO
	 */
	int deleteAuth(BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: insertFile
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 파일 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:11:11
	 * </PRE>
	 *   @return void
	 *   @param bulletin 게시판VO
	 */
	void insertFile(BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: authList
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 게시물ID에 해당하는 권한 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:11:15
	 * </PRE>
	 *   @return List<BulletinMngVO> 권한리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param bulletin 게시판VO
	 */
	List<BulletinMngVO> authList(@Param(value = "sidx")String sidx, @Param(value = "sord")String sord, @Param(value = "bulletin") BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: getFileList
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 파일 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 29. 오전 11:28:50
	 * </PRE>
	 *   @return List<Map<String,Object>> 파일정보
	 *   @param noticeId 게시판 ID
	 */
	List<Map<String, Object>> getFileList(@Param(value = "noticeId")String noticeId);

	/**
	 * <PRE>
	 * 1. MethodName: deleteFile
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 파일 삭제 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 29. 오후 3:47:44
	 * </PRE>
	 *   @return int 삭제 CNT
	 *   @param noticeId 삭제 대상 ID
	 *   @param uuid 삭제 대상 uuid
	 */
	int deleteFile(@Param(value = "noticeId")String noticeId, @Param(value = "uuid")String uuid);

	/**
	 * <PRE>
	 * 1. MethodName: getFileInfo
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : 파일 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 29. 오후 3:57:53
	 * </PRE>
	 *   @return Map<String,Object> 파일 정보
	 *   @param noticeId Notice ID
	 *   @param uuid UUID
	 *   @return
	 */
	Map<String, Object> getFileInfo(@Param(value = "noticeId")String noticeId, @Param(value = "uuid")String uuid);

	/**
	 * <PRE>
	 * 1. MethodName: getMaxFileSeq
	 * 2. ClassName : BulletinMapper
	 * 3. Comment   : File Seq의 MAX조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 29. 오후 4:57:03
	 * </PRE>
	 *   @return int max Seq
	 *   @param noticeId noticeId
	 */
	int getMaxFileSeq(@Param(value = "noticeId")String noticeId);

}