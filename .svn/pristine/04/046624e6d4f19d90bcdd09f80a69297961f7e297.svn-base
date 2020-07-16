package com.ntels.ccbs.appIf.controller.sh.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.ntels.ccbs.appIf.service.sh.service.ServiceIfService;
import com.ntels.ccbs.common.exception.AppException;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/appIf/sh/service/service")
public class ServiceIfController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ServiceIfService serviceIfService;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getStockStat
	 * 2. ClassName : ServiceIfController
	 * 3. Comment   : 재고 상태 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 25. 오전 11:28:10
	 * </PRE>
	 *   @return ModelAndView
	 *   @param model
	 *   @param httpServletRequest
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value="getStockStat", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView getStockStat(Model model, HttpServletRequest httpServletRequest, 
			@RequestBody AppRequestVO request) {
		
		ModelAndView mav = new ModelAndView();
		
		/**
		 * 전문번호체크
		 */
		if("APP0002".equals(request.getHeader().getSvcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 전문번호체크
		 */
		if("APPOP01".equals(request.getHeader().getOpcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 채널체크 
		 */
		if("01".equals(request.getHeader().getChcode()) == false){
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
		
		Map<String, Object> responseBody = serviceIfService.getStockStat(request);
		
		response.setBody(responseBody); 
		
		mav.addObject("RESPONSE",response);
		
		return mav;
		
	}
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getWorkInfo
	 * 2. ClassName : ServiceIfController
	 * 3. Comment   : 작업정보조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 25. 오전 11:28:42
	 * </PRE>
	 *   @return ModelAndView
	 *   @param model
	 *   @param httpServletRequest
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value="getWorkInfo", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView getWorkInfo(Model model, HttpServletRequest httpServletRequest, 
			@RequestBody AppRequestVO request) {
		
		ModelAndView mav = new ModelAndView();
		
		/**
		 * 전문번호체크
		 */
		if("APP0002".equals(request.getHeader().getSvcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 전문번호체크
		 */
		if("APPOP02".equals(request.getHeader().getOpcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 채널체크 
		 */
		if("01".equals(request.getHeader().getChcode()) == false){
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
		
		Map<String, Object> responseBody = serviceIfService.getWorkInfo(request);
		
		response.setBody(responseBody); 
		
		mav.addObject("RESPONSE",response);
		
		return mav;
		
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: setEquipmentReception
	 * 2. ClassName : ServiceIfController
	 * 3. Comment   : 실장비정보수신
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 25. 오전 11:28:54
	 * </PRE>
	 *   @return ModelAndView
	 *   @param model
	 *   @param httpServletRequest
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value="setEquipmentReception", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView setEquipmentReception(Model model, HttpServletRequest httpServletRequest, 
			@RequestBody AppRequestVO request) {
		
		ModelAndView mav = new ModelAndView();
		
		/**
		 * 전문번호체크
		 */
		if("APP0002".equals(request.getHeader().getSvcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 전문번호체크
		 */
		if("APPOP05".equals(request.getHeader().getOpcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 채널체크 
		 */
		if("01".equals(request.getHeader().getChcode()) == false){
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
		
		Map<String, Object> responseBody = serviceIfService.setEquipmentReception(request);
		
		response.setBody(responseBody); 
		
		mav.addObject("RESPONSE",response);
		
		return mav;
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getServiceResult
	 * 2. ClassName : ServiceIfController
	 * 3. Comment   : 서비스결과 처리 
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 5. 29. 오후 1:11:41
	 * </PRE>
	 *   @return ModelAndView
	 *   @param model
	 *   @param httpServletRequest
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value="getServiceResult", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView getServiceResult(Model model, HttpServletRequest httpServletRequest, 
			@RequestBody AppRequestVO request) {
		
		ModelAndView mav = new ModelAndView();
		
		/**
		 * 전문번호체크
		 */
		if("APP0002".equals(request.getHeader().getSvcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 전문번호체크
		 */
		if("APPOP04".equals(request.getHeader().getOpcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 전문번호체크
		}
		/**
		 * 채널체크 
		 */
		if("01".equals(request.getHeader().getChcode()) == false){
			throw new AppException("MSG.IF.E0007", "E0007"); // 채널체크
		}
		
		/** 
		 * 필수값 체크
		 */
		if(StringUtils.isEmpty((String)request.getBody().get("SO_ID"))){
			Object[] arguments = {"SO_ID" };
			throw new AppException("MSG.IF.E0005", "E0005",arguments); // 사업ID누락
		}
		if(StringUtils.isEmpty((String)request.getBody().get("CUST_ID"))){
			Object[] arguments = {"CUST_ID" };
			throw new AppException("MSG.IF.E0005", "E0005",arguments); // 고객ID 누락
		}
		if(StringUtils.isEmpty((String)request.getBody().get("CTRT_ID"))){
			Object[] arguments = {"CTRT_ID" };
			throw new AppException("MSG.IF.E0005", "E0005",arguments); // 계약ID 누락
		}
		if(StringUtils.isEmpty((String)request.getBody().get("ORDER_ID"))){
			Object[] arguments = {"ORDER_ID" };
			throw new AppException("MSG.IF.E0005", "E0005",arguments); // 오더ID 누락
		}
		if(StringUtils.isEmpty((String)request.getBody().get("ORDER_STAT"))){
			Object[] arguments = {"ORDER_STAT" };
			throw new AppException("MSG.IF.E0005", "E0005",arguments); // 작업상태 누락
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
		
		Map<String, Object> responseBody = null;
		
		try{
			responseBody = serviceIfService.processServiceResult(request);
			
		}catch(ServiceException e){
			if(StringUtils.isEmpty(e.getMessage())){
				Object[] arguments = {MessageUtil.getMessage("MSG.IF.E0003") };
				throw new AppException("MSG.IF.E0008", "E0008",arguments);
				
			}else{
				Object[] arguments = {e.getMessage() };
				throw new AppException("MSG.IF.E0008", "E0008",arguments);
			}
		}catch(Exception e){
			throw new AppException("MSG.IF.E0003","E0003");
		}
		
		 
		
		response.setBody(responseBody); 
		
		mav.addObject("RESPONSE",response);
		
		return mav;
		
	}
}
