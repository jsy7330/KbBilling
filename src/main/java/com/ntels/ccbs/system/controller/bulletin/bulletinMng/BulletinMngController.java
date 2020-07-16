package com.ntels.ccbs.system.controller.bulletin.bulletinMng;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;
import com.ntels.ccbs.system.service.bulletin.bulletinMng.BulletinMngService;


/**
 * <PRE>
 * 1. ClassName: BulletinMngController
 * 2. FileName : BulletinMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.bulletin.bulletinMng
 * 4. Comment  : 게시판 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 2:49:03
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/bulletin/bulletinMng/bulletinMng")
public class BulletinMngController {
	
	/**
	 * 게시판 메인 URL
	 */
	private static String URL = "system/bulletin/bulletinMng/bulletinMng";
	
	/**
	 * 게시판 서비스
	 */
	@Autowired BulletinMngService bulletinMngService;
	
	/**
	 * 파일경로
	 */
	@Value("${file.path}")
	private String filePath;
	
	/**
	 * <PRE>
	 * 1. MethodName: bulletinMng
	 * 2. ClassName : BulletinMngController
	 * 3. Comment   : 게시판 메인 뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:50:12
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param bulletin 게시판VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "bulletinMng", method = RequestMethod.POST)
	public String bulletinMng(	Model model,BulletinMngVO bulletin,
			HttpServletRequest request) {
		model.addAttribute("bulletin", bulletin);
		String startDate = DateUtil.getDateStringYYYYMMDD(0);
		String endDate = DateUtil.getDateStringYYYYMMDD(7);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return URL + "/bulletinMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getBulletinListAction
	 * 2. ClassName : BulletinMngController
	 * 3. Comment   : 게시판 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:50:18
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param bulletin 게시판VO
	 *   @param request {@link HttpServletRequest}
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 화면에 보여질 행의 수
	 *   @param srchYn 검색여부
	 *   @param srchTyp 제목/내용 선택
	 *   @param srchNm 제목/내용 검색
	 */
	@RequestMapping(value = "getBulletinListAction", method = RequestMethod.POST)
	public String getBulletinListAction(HttpServletRequest request, BulletinMngVO bulletin, Model model,String sidx
			,String sord
			,int page
			,int rows
			,String srchYn
			,String srchTyp
			,String srchNm){
		if(StringUtils.isEmpty(srchYn)){
			return URL + "/ajax/bulletinMng";
		}
		bulletin.setSrchTyp(srchTyp);
		bulletin.setSrchNm(srchNm);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
		Map<String,Object> bulleintInfo = bulletinMngService.list(sidx,sord, page, rows, lng,bulletin);
	    
		model.addAttribute("bulletinList", bulleintInfo.get("bulletinList"));
		model.addAttribute("totalCount", bulleintInfo.get("totalCount"));
		model.addAttribute("totalPages", bulleintInfo.get("totalPages"));
		model.addAttribute("page", bulleintInfo.get("page"));
		
		return URL + "/ajax/bulletinMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: bulletinInsertAction
	 * 2. ClassName : BulletinMngController
	 * 3. Comment   : 게시판 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:51:41
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param bulletin 게시판VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "bulletinInsertAction", method = RequestMethod.POST)
	public String bulletinInsertAction(BulletinMngVO bulletin,	Model model,MultipartHttpServletRequest request) {
		int result=bulletinMngService.insert(bulletin,request);
		
		model.addAttribute("bulletin", bulletin);
		return URL + "/bulletinMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: bulletinUpdateAction
	 * 2. ClassName : BulletinMngController
	 * 3. Comment   : 게시판 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:52:18
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param bulletin 게시판VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "bulletinUpdateAction", method = RequestMethod.POST)
	public String bulletinUpdateAction(BulletinMngVO bulletin,Model model,MultipartHttpServletRequest request) {
		int result=bulletinMngService.updateBulletinAction(bulletin,request);
		return URL + "/bulletinMng";
	}

	/**
	 * <PRE>
	 * 1. MethodName: bulletinDeleteAction
	 * 2. ClassName : BulletinMngController
	 * 3. Comment   : 게시판 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 2:52:21
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param bulletin 게시판VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "bulletinDeleteAction", method = RequestMethod.POST)
	public String bulletinDeleteAction(BulletinMngVO bulletin,Model model,HttpServletRequest request) {
		int result=bulletinMngService.deleteBulletin(bulletin);
		return URL + "/bulletinMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: bulletinDeleteFileAction
	 * 2. ClassName : BulletinMngController
	 * 3. Comment   : 파일 삭제
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 29. 오후 3:11:18
	 * </PRE>
	 *   @return String
	 *   @param bulletin
	 *   @param model
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "bulletinDeleteFileAction", method = RequestMethod.POST)
	public String bulletinDeleteFileAction(Model model,HttpServletRequest request, String uuid, String noticeId) {
		String fileInfo = bulletinMngService.bulletinDeleteFile(uuid, noticeId);
		model.addAttribute("fileList", fileInfo);
		model.addAttribute("uuid", uuid);
		
		return URL + "/bulletinMng";
	}

}
