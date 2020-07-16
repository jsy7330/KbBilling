package com.ntels.ccbs.system.service.bulletin.bulletinMng;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;

/**
 * <PRE>
 * 1. ClassName: BulletinMngService
 * 2. FileName : BulletinMngService.java
 * 3. Package  : com.ntels.ccbs.system.service.bulletin.bulletinMng
 * 4. Comment  :
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 4:08:41
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
public interface BulletinMngService {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : BulletinMngService
	 * 3. Comment   : 게시판 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:08:45
	 * </PRE>
	 *   @return Map<String,Object> 게시판 리스트 정보
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 화면에 보여질 행의 수
	 *   @param lng 언어코드
	 *   @param bulletin 게시판VO
	 */
	Map<String, Object> list(String sidx, String sord, int page, int rows, String lng, BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : BulletinMngService
	 * 3. Comment   : 게시판등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:08:47
	 * </PRE>
	 *   @return int 등록여부
	 *   @param bulletin 게시판VO
	 *   @param request {@link MultipartHttpServletRequest}
	 */
	int insert(BulletinMngVO bulletin, MultipartHttpServletRequest request);

	/**
	 * <PRE>
	 * 1. MethodName: bulletinUpdateAction
	 * 2. ClassName : BulletinMngService
	 * 3. Comment   : 게시판 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:08:49
	 * </PRE>
	 *   @return int 수정여부
	 *   @param bulletin 게시판VO
	 *   @param request {@link MultipartHttpServletRequest}
	 */
	int updateBulletinAction(BulletinMngVO bulletin, MultipartHttpServletRequest request);

	/**
	 * <PRE>
	 * 1. MethodName: deleteBulletin
	 * 2. ClassName : BulletinMngService
	 * 3. Comment   : 게시판 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 4:08:51
	 * </PRE>
	 *   @param bulletin 게시판VO
	 *   @param request {@link HttpServletRequest}
	 */
	int deleteBulletin(BulletinMngVO bulletin);

	/**
	 * <PRE>
	 * 1. MethodName: bulletinDeleteFile
	 * 2. ClassName : BulletinMngService
	 * 3. Comment   : 파일 삭제
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 29. 오후 3:29:52
	 * </PRE>
	 *   @return String 삭제후 해당 게시물의 첨부 파일 정보
	 *   @param uuid 삭제 대상 UUID
	 *   @param noticeId 삭제 대상 File
	 *   @return
	 */
	String bulletinDeleteFile(String uuid, String noticeId);

}