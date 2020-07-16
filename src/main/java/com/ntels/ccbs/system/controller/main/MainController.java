package com.ntels.ccbs.system.controller.main;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;
import com.ntels.ccbs.system.domain.common.service.SelectedMenu;
import com.ntels.ccbs.system.service.bulletin.bulletinMng.BulletinMngService;
import com.ntels.ccbs.system.service.main.MainService;

/**
 * 
 * <PRE>
 * 1. ClassName: MainController
 * 2. FileName : MainController.java
 * 3. Package  : com.ntels.ccbs.system.controller.main
 * 4. Comment  : Main 화면 Controller.
 * 5. 작성자   : kgw
 * 6. 작성일   : 2015. 9. 17. 오전 11:24:05
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		kgw :	2015. 9. 17.	: 신규 개발.
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/main")
public class MainController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MainService mainService;
	
	/** InquiryHist Service  */
	@Autowired BulletinMngService bulletinMngService;
	
	private String thisUrl = "system/main";
	
	/**
	 * 메인 화면.
	 *
	 * @param noSelectMenu 선택메뉴여부
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "main", method = {RequestMethod.POST, RequestMethod.GET})
	public String main(@RequestParam(required = false, defaultValue = "N")String noSelectMenu, HttpServletRequest request, Model model) {

		Object oSelectedMenu = request.getSession().getAttribute(
				"selectedMenu");
		if (oSelectedMenu != null) {
			SelectedMenu selectedMenu = (SelectedMenu) oSelectedMenu;
			request.getSession().setAttribute("selectedMenu", selectedMenu);
		}
		request.getSession().setAttribute("mainPath", "/" + thisUrl + "/main");
		request.getSession().setAttribute("lastPagePath", "");
		return thisUrl + "/main";
	}
	
	/**
	 * 세션 유지.
	 *
	 * @return Object
	 */
	@RequestMapping(value = "keepAlive")
	public @ResponseBody Object keepAlive() {
		return true;
	}
	
	@RequestMapping(value = "mainAction", method = RequestMethod.POST)
	public String mainAction(HttpServletRequest request
		    , BulletinMngVO bulletin, Model model
		    ,String sidx
			,String sord
			,int page
			,int rows) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
	    Map<String,Object> bulleintInfo =mainService.mainBulletinList(sidx,sord, page, rows, bulletin,lng);
	    
	    model.addAttribute("bulletinList", bulleintInfo.get("bulletinList"));
		model.addAttribute("totalCount", bulleintInfo.get("totalCount"));
		model.addAttribute("totalPages", bulleintInfo.get("totalPages"));
		model.addAttribute("page", bulleintInfo.get("page"));
	    return thisUrl + "/ajax/main";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: bulletinDetailPopup
	 * 2. ClassName : MainController
	 * 3. Comment   : 게시판 상세정보
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 29. 오후 3:29:00
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param bulletin 게시판VO
	 *   @param request {@link HttpServletRequest}
	 *   @param noticeId 게시물ID
	 *   @param effectStartDt 적용시작일자
	 *   @param effectEndDt 적용종료일자
	 *   @throws Exception
	 */
	@RequestMapping(value = "bulletinDetailPopup",method = RequestMethod.POST)
	public String bulletinDetailPopup(Model model,BulletinMngVO bulletin,HttpServletRequest request
			,String noticeId
			,String effectStartDt
			,String effectEndDt)throws Exception {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
		BulletinMngVO detail = mainService.updateDetailBulletin(noticeId,lng);
		model.addAttribute("bulletin", detail);
		model.addAttribute("effectStartDt", effectStartDt);
		model.addAttribute("effectEndDt", effectEndDt);
		if("o".equals(bulletin.getPopType())){
			return thisUrl + "/opopup/bulletinDetailPopup";
		}else{
			return thisUrl + "/ajax/bulletinDetailPopup";
		}
	}
	@RequestMapping(value = "mainCustomer", method = {RequestMethod.POST, RequestMethod.GET})
	public String mainCustomer(@RequestParam(required = false, defaultValue = "N")String noSelectMenu, HttpServletRequest request, Model model) {

		Object oSelectedMenu = request.getSession().getAttribute(
				"selectedMenu");
		if (oSelectedMenu != null) {
			SelectedMenu selectedMenu = (SelectedMenu) oSelectedMenu;
			request.getSession().setAttribute("selectedMenu", selectedMenu);
		}
		request.getSession().setAttribute("mainPath", "/" + thisUrl + "/mainCustomer");
		request.getSession().setAttribute("lastPagePath", "");
		return thisUrl + "/mainCustomer";
	}
	
	@RequestMapping(value = "mainBilling", method = {RequestMethod.POST, RequestMethod.GET})
	public String mainBilling(@RequestParam(required = false, defaultValue = "N")String noSelectMenu, HttpServletRequest request, Model model) {

		Object oSelectedMenu = request.getSession().getAttribute(
				"selectedMenu");
		if (oSelectedMenu != null) {
			SelectedMenu selectedMenu = (SelectedMenu) oSelectedMenu;
			request.getSession().setAttribute("selectedMenu", selectedMenu);
		}
		request.getSession().setAttribute("mainPath", "/" + thisUrl + "/mainBilling");
		request.getSession().setAttribute("lastPagePath", "");
		return thisUrl + "/mainBilling";
	}	
	
	@RequestMapping(value = "billingMainChart1", method = RequestMethod.POST)
	public String billingMainChart1(HttpServletRequest request, BulletinMngVO bulletin, Model model) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
	    Map<String,Object> bulleintInfo =mainService.billingMainChart1(bulletin,lng);
	    
	    model.addAttribute("billingMainChart1", bulleintInfo.get("billingMainChart1"));
	    return thisUrl + "/ajax/mainBilling";
	}
	
	@RequestMapping(value = "billingMainChart2", method = RequestMethod.POST)
	public String billingMainChart2(HttpServletRequest request, BulletinMngVO bulletin, Model model) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
	    Map<String,Object> bulleintInfo =mainService.billingMainChart2(bulletin,lng);
	    
	    model.addAttribute("billingMainChart2", bulleintInfo.get("billingMainChart2"));
	    return thisUrl + "/ajax/mainBilling";
	}	
	@RequestMapping(value = "customerMainChart1", method = RequestMethod.POST)
	public String customerMainChart1(HttpServletRequest request, BulletinMngVO bulletin, Model model) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
	    Map<String,Object> bulleintInfo =mainService.customerMainChart1(bulletin,lng);
	    
	    model.addAttribute("customerMainChart1", bulleintInfo.get("customerMainChart1"));
	    return thisUrl + "/ajax/mainBilling";
	}
	
	@RequestMapping(value = "customerMainChart2", method = RequestMethod.POST)
	public String customerMainChart2(HttpServletRequest request, BulletinMngVO bulletin, Model model) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");	
	    Map<String,Object> bulleintInfo =mainService.customerMainChart2(bulletin,lng);
	    
	    model.addAttribute("customerMainChart2", bulleintInfo.get("customerMainChart2"));
	    return thisUrl + "/ajax/mainBilling";
	}		
}
