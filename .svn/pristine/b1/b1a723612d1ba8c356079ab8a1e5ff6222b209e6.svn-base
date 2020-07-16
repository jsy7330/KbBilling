package com.ntels.ccbs.appIf.controller.mso.service;

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
import com.ntels.ccbs.appIf.service.mso.service.ServiceMsoService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.AppException;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/appIf/mso/service/service")
public class ServiceMsoController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	ServiceMsoService serviceMsoService;
	
	@RequestMapping(value="setServiceApply", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView setServiceApply(Model model, HttpServletRequest httpServletRequest, 
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
		if("MSOOP01".equals(request.getHeader().getOpcode()) == false){
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
		
		Map<String, Object> responseBody = serviceMsoService.setServiceApply(request);
		
		response.setBody(responseBody); 
		
		mav.addObject("RESPONSE",response);
		
		return mav;
		
	}
	
	
}
