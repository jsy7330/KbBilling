package com.ntels.ccbs.system.controller.common.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.PostMngVO;
import com.ntels.ccbs.system.service.common.common.PostMngService;

/**
 * <PRE>
 * 1. ClassName: PostMngController
 * 2. FileName : PostMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.common.common
 * 4. Comment  : 도로명주소 팝업컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 5:01:14
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/common/common/postMng")
public class PostMngController {
	/**
	 * 도로명주소 메인 URL
	 */
	private static String URL = "system/common/common/postMng";
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 도로명주소 서비스
	 */
	@Autowired PostMngService postMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: postPopup
	 * 2. ClassName : PostMngController
	 * 3. Comment   : 도로명주소 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:01:26
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param postMng 도로명주소VO
	 *   @param zipCd 우편번호
	 *   @param baseAddr 기본주소
	 *   @param addrDtl 상세주소
	 *   @param mode 팝업종류
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "postPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String postPopup(Model model,PostMngVO postMng, String zipCd, String baseAddr, String addrDtl, String mode, HttpServletRequest request) {
		
		model.addAttribute("postMng", postMng);
		model.addAttribute("paramZipCd", zipCd);
		model.addAttribute("paramBaseAddr", baseAddr);
		model.addAttribute("paramAddrDtl", addrDtl);
		if("o".equals(mode)){
			model.addAttribute("mode", "o");
			return URL + "/opopup/postPopup";
		}else{
			model.addAttribute("mode", "m");
			return URL + "/ajax/postPopup";
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: postListAction
	 * 2. ClassName : PostMngController
	 * 3. Comment   : 도로명주소리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:01:31
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param postMng 도로명주소VO
	 *   @param request {@link HttpServletRequest}
	 *   @param srchNm 도로명주소이름
	 *   @param srchBldg 건물이름
	 *   @param sidx Sort 대상 키
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param str1 시도명
	 *   @param str2 구군명
	 *   @param str3 구군명(예)보통 XXXD이지만 구군명이 XXX XXX 일경우 
	 *   @param str4 동명
	 *   @param str5 읍동면도로명
	 *   @param str6 건물번호1
	 *   @param str7 건물번호2
	 *   @param mode 팝업종류
	 */
	@RequestMapping(value = "postListAction", method = RequestMethod.POST)
	public String postListAction(Model model,PostMngVO postMng,HttpServletRequest request
			,String srchNm
			,String srchBldg
			,String sidx
			,int page
			,int rows
			,String str1
			,String str2
			,String str3
			,String str4
			,String str5
			,String str6
			,String str7
			,String mode){
		
		if(StringUtils.isEmpty(str1) &&
				StringUtils.isEmpty(str2) &&
				StringUtils.isEmpty(str3) &&
				StringUtils.isEmpty(str4) &&
				StringUtils.isEmpty(str5) &&
				StringUtils.isEmpty(str6) &&
				StringUtils.isEmpty(str7) && 
				StringUtils.isEmpty(srchBldg)
				){
			if("o".equals(mode)){
				model.addAttribute("mode", "o");
				return URL + "/opopup/postPopup";
			}else{
				model.addAttribute("mode", "m");
				return URL + "/ajax/postPopup";
			}
		}
		
		postMng.setStr1(str1);
		postMng.setStr2(str2);
		postMng.setStr3(str3);
		postMng.setStr4(str4);
		postMng.setStr5(str5);
		postMng.setStr6(str6);
		postMng.setStr7(str7);
		postMng.setSrchBldg(srchBldg);
		postMng.setSrchNm(srchNm);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> soInfo = postMngService.list(sidx, page, rows, lng,postMng);
		
		model.addAttribute("postList", soInfo.get("postList"));
		model.addAttribute("totalCount", soInfo.get("totalCount"));
		model.addAttribute("totalPages", soInfo.get("totalPages"));
		model.addAttribute("page", soInfo.get("page"));
		model.addAttribute("str1", soInfo.get("str1"));
		model.addAttribute("str2", soInfo.get("str2"));
		model.addAttribute("str3", soInfo.get("str3"));
		model.addAttribute("str4", soInfo.get("str4"));
		model.addAttribute("str5", soInfo.get("str5"));
		model.addAttribute("str6", soInfo.get("str6"));
		model.addAttribute("str7", soInfo.get("str7"));
		
		if("o".equals(mode)){
			model.addAttribute("mode", "o");
			return URL + "/opopup/postPopup";
		}else{
			model.addAttribute("mode", "m");
			return URL + "/ajax/postPopup";
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getSearchCountAction
	 * 2. ClassName : PostMngController
	 * 3. Comment   : 검색키워드 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 24. 오후 5:01:38
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param postMng 도로명주소VO
	 *   @param mode 팝업종류
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "getSearchCountAction", method = RequestMethod.POST)
	public String getSearchCountAction(Model model,PostMngVO postMng,HttpServletRequest request, String mode){
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		postMng.setLng(lng);
		Map<String,Object> soInfo = postMngService.searchCount(postMng);
		model.addAttribute("str1", soInfo.get("str1"));
		model.addAttribute("str2", soInfo.get("str2"));
		model.addAttribute("str3", soInfo.get("str3"));
		model.addAttribute("str4", soInfo.get("str4"));
		model.addAttribute("str5", soInfo.get("str5"));
		model.addAttribute("str6", soInfo.get("str6"));
		model.addAttribute("str7", soInfo.get("str7"));
		
		if("o".equals(mode)){
			model.addAttribute("mode", "o");
			return URL + "/opopup/postPopup";
		}else{
			model.addAttribute("mode", "m");
			return URL + "/ajax/postPopup";
		}
	}
}