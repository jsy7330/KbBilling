package com.ntels.ccbs.system.controller.so.soMng;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.domain.so.soMng.SoMngVO;
import com.ntels.ccbs.system.service.so.soMng.SoMngService;
import com.ntels.nisf.util.message.MessageUtil;

/**
 * <PRE>
 * 1. ClassName: SoMngController
 * 2. FileName : SoMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.so.soMng
 * 4. Comment  : 사업관리 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 21. 오전 11:02:18
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 21.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/so/soMng/soMng")
public class SoMngController {
	
	/**
	 * 사업관리 메인 URL
	 */
	private static String URL = "system/so/soMng/soMng";
	
		
	/** SoMngService Service  */
	/**
	 * 사업관리 서비스
	 */
	@Autowired SoMngService soMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: soMng
	 * 2. ClassName : SoMngController
	 * 3. Comment   : 사업관리 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:05:07
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param so 사업관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "soMng", method = RequestMethod.POST)
	public String soMng(	Model model,SoMngVO so,HttpServletRequest request) {
		List<SoMngVO> soAuthList=soMngService.soAuthList(so,request);
		
		model.addAttribute("soAuthList", soAuthList);
		model.addAttribute("so", so);
		
		return URL + "/soMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: mainListAction
	 * 2. ClassName : SoMngController
	 * 3. Comment   : 사업관리 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:05:57
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param {@link Model}
	 *   @param so 사업관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @param srchYn	검색버튼 클릭 여부
	 *   @param condSoId 사업ID 조건
	 *   @param condSoNm 사업명 조건
	 *   @param condSo	사용유무
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 */
	@RequestMapping(value = "mainListAction", method = RequestMethod.POST)
	public String mainListAction(Model model,SoMngVO so,HttpServletRequest request
			,String srchYn
			,String condSoId
			,String condSoNm
			,String condSo
			,String sidx
			,String sord
			,int page
			,int rows){
		
		if(StringUtils.isEmpty(srchYn)){
			return URL + "/ajax/soMng";
		}
		   so.setCondSoId(condSoId);
		   so.setCondSoNm(condSoNm);
		   so.setCondSo(condSo);
		  
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> soInfo = soMngService.list(sidx,sord, page, rows, lng,so);
		model.addAttribute("max",  soInfo.get("max"));
		model.addAttribute("soList", soInfo.get("soList"));
		model.addAttribute("totalCount", soInfo.get("totalCount"));
		model.addAttribute("totalPages", soInfo.get("totalPages"));
		model.addAttribute("page", soInfo.get("page"));
		return URL + "/ajax/soMng";
		
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: insertAction
	 * 2. ClassName : SoMngController
	 * 3. Comment   : 사업등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:09:29
	 * </PRE>
	 *  @return String 리턴 URL
	 *  @param {@link Model}
	 *  @param so 사업관리VO
	 *  @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "insertAction", method = RequestMethod.POST)
	public String insertAction(Model model,SoMngVO so,HttpServletRequest request){
		

		try{
			int result = soMngService.insert(so,request);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		return URL + "/ajax/soMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: updateAction
	 * 2. ClassName : SoMngController
	 * 3. Comment   : 사업 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:10:03
	 * </PRE>
	 *  @return String 리턴 URL
	 *  @param {@link Model}
	 *  @param so 사업관리VO
	 *  @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "updateAction", method = RequestMethod.POST)
	public String updateAction(Model model,SoMngVO so,HttpServletRequest request){
		int result = soMngService.update(so,request);
		
		List<SoMngVO> soAuthList=soMngService.soAuthList(so,request);
		model.addAttribute("soAuthList", soAuthList);
		
		return URL + "/ajax/soMng";
	}

}