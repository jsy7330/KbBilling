package com.ntels.ccbs.appIf.controller.mso.customer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;
import com.ntels.ccbs.appIf.domain.common.AppResHeaderVO;
import com.ntels.ccbs.appIf.domain.common.AppResponseVO;
import com.ntels.ccbs.appIf.service.mso.customer.CustomerMsoService;
import com.ntels.ccbs.appIf.service.sh.customer.CustomerIfService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.AppException;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/appIf/mso/customer/customer")
public class CustomerMsoController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CustomerIfService customerIfService;

	@Autowired
	private CustomerMsoService customerMsoService;
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getCustomer
	 * 2. ClassName : CustomerMsoController
	 * 3. Comment   : MSO에서 호출 , 가입자정보 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 6. 2. 오전 10:09:24
	 * </PRE>
	 *   @return ModelAndView
	 *   @param model
	 *   @param httpServletRequest
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value="getCustomer", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView getCustomer(Model model, HttpServletRequest httpServletRequest, 
			@RequestBody AppRequestVO request) {
		
		ModelAndView mav = new ModelAndView();
		
		/**
		 * 전문번호체크
		 */
		if("APP0001".equals(request.getHeader().getSvcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 전문번호체크
		 */
		if("MSOOP05".equals(request.getHeader().getOpcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 채널체크 
		 */
		if("02".equals(request.getHeader().getChcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 채널체크
		}
		
		/**
		 * 결과 세팅
		 */
		AppResponseVO response = new AppResponseVO();
		
		AppResHeaderVO resHeader = new AppResHeaderVO();
		resHeader.setSvcode(request.getHeader().getSvcode());
		resHeader.setOpcode(request.getHeader().getOpcode());
		resHeader.setChcode(request.getHeader().getChcode());
		resHeader.setMsgcode("N0000");
		resHeader.setMessage(MessageUtil.getMessage("MSG.IF.N0000"));
		
		response.setHeader(resHeader);
		
		
		Map<String, Object> param = request.getBody();
		param.put("lng", Consts.LanguageCode.KOREAN);
		request.setBody(param);
		
		Map<String, Object> responseBody = customerIfService.getCustomer(request);
		
		response.setBody(responseBody); 
		
		mav.addObject("RESPONSE",response);
		
		return mav;
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getEquipmentReception
	 * 2. ClassName : CustomerMsoController
	 * 3. Comment   : 실장비정보 수신
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 6. 2. 오전 10:12:18
	 * </PRE>
	 *   @return ModelAndView
	 *   @param model
	 *   @param httpServletRequest
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value="getEquipmentReception", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView getEquipmentReception(Model model, HttpServletRequest httpServletRequest, 
			@RequestBody AppRequestVO request) {
		
		ModelAndView mav = new ModelAndView();
		
		/**
		 * 전문번호체크
		 */
		if("APP0001".equals(request.getHeader().getSvcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 전문번호체크
		 */
		if("MSOOP06".equals(request.getHeader().getOpcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 채널체크 
		 */
		if("02".equals(request.getHeader().getChcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 채널체크
		}
		
		/**
		 * 결과 세팅
		 */
		AppResponseVO response = new AppResponseVO();
		
		AppResHeaderVO resHeader = new AppResHeaderVO();
		resHeader.setSvcode(request.getHeader().getSvcode());
		resHeader.setOpcode(request.getHeader().getOpcode());
		resHeader.setChcode(request.getHeader().getChcode());
		resHeader.setMsgcode("N0000");
		resHeader.setMessage(MessageUtil.getMessage("MSG.IF.N0000"));
		
		response.setHeader(resHeader);
		
		
		Map<String, Object> param = request.getBody();
		param.put("lng", Consts.LanguageCode.KOREAN);
		request.setBody(param);
		
		Map<String, Object> responseBody = customerMsoService.getEquipmentReception(request);
		
		response.setBody(responseBody); 
		
		mav.addObject("RESPONSE",response);
		
		return mav;
		
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getHomeService
	 * 2. ClassName : CustomerMsoController
	 * 3. Comment   : 홈서비스ID조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 6. 2. 오후 2:01:38
	 * </PRE>
	 *   @return ModelAndView
	 *   @param model
	 *   @param httpServletRequest
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value="getHomeService", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView getHomeService(Model model, HttpServletRequest httpServletRequest, 
			@RequestBody AppRequestVO request) {
		
		ModelAndView mav = new ModelAndView();
		
		/**
		 * 전문번호체크
		 */
		if("APP0001".equals(request.getHeader().getSvcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 전문번호체크
		 */
		if("MSOOP07".equals(request.getHeader().getOpcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 채널체크 
		 */
		if("02".equals(request.getHeader().getChcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 채널체크
		}
		
		/**
		 * 결과 세팅
		 */
		AppResponseVO response = new AppResponseVO();
		
		AppResHeaderVO resHeader = new AppResHeaderVO();
		resHeader.setSvcode(request.getHeader().getSvcode());
		resHeader.setOpcode(request.getHeader().getOpcode());
		resHeader.setChcode(request.getHeader().getChcode());
		resHeader.setMsgcode("N0000");
		resHeader.setMessage(MessageUtil.getMessage("MSG.IF.N0000"));
		
		response.setHeader(resHeader);
		
		
		Map<String, Object> param = request.getBody();
		request.setBody(param);
		
		Map<String, Object> responseBody = customerMsoService.getHomeService(request);
		
		response.setBody(responseBody); 
		
		mav.addObject("RESPONSE",response);
		
		return mav;
		
	}
	
}
