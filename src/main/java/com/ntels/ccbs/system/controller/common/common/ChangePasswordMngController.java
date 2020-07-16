package com.ntels.ccbs.system.controller.common.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.service.common.common.ChangePasswordMngService;

@Controller
@RequestMapping(value = "/system/common/common/changePwMng")
public class ChangePasswordMngController {
	
	private static String URL = "system/common/common/changePwMng";
	@Autowired
	private ChangePasswordMngService changePasswordMngService;
	@RequestMapping(value = "changePwMngPopup", method = {RequestMethod.GET, RequestMethod.POST})
	public String mainViewMng(Model model
			,HttpServletRequest request
			,String userId) {
		model.addAttribute("userId", userId);
		return  URL+"/opopup/changePwMngPopup";
		
	}
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	public String updatePassword(Model model,HttpServletRequest request
			, @RequestBody Map<String, Object> updatePwInfo) {
		String userId = (String)updatePwInfo.get("userId");
		String password = (String)updatePwInfo.get("password");
		String newPassword1 = (String)updatePwInfo.get("newPassword1");
		String newPassword2 = (String)updatePwInfo.get("newPassword1");
		
		changePasswordMngService.updatePassword(userId, password,newPassword1,newPassword2);
		return  URL+"/ajax/changePwMngPopup";
	}
}