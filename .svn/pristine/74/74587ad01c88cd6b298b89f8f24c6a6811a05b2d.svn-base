package com.ntels.ccbs.customer.controller.contract.contract;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.ifg.service.InterfaceService;


/**
 * <PRE>
 * 1. ClassName: HomeServiceController
 * 2. FileName : HomeServiceController.java
 * 3. Package  : com.ntels.ccbs.customer.controller.contract.contract
 * 4. Comment  : 홈서비스ID 조회 Controller
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 5. 31. 오후 1:29:48
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 5. 31.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/customer/contract/contract/homeService")
public class HomeServiceController {
private static String URL = "customer/contract/contract/homeService";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    
    @Autowired
	private InterfaceService interfaceService;

	/**
	 * <PRE>
	 * 1. MethodName: openInvoiceDetail
	 * 2. ClassName : HomeServiceController
	 * 3. Comment   : 홈서비스ID 조회 Popup 오픈
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 1:30:02
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 */
	@RequestMapping(value = "openHomeService", method = RequestMethod.POST)
	public String openHomeService(Model model
			,HttpServletRequest request
			,String soId
			,String custId
			) {
		model.addAttribute("soId", soId);
		model.addAttribute("custId", custId);
		return URL + "/ajax/homeServicePopup";
	}
	
	
	@RequestMapping(value = "getHomeServiceListAction", method = RequestMethod.POST)
	public String getBillCtrtList(Model model, HttpServletRequest request, String soId, String custId) {
		model.addAttribute("homeServiceList", interfaceService.getHomeServiceList(soId, custId));
		return URL + "/homeServicePopup";
	}
	
}