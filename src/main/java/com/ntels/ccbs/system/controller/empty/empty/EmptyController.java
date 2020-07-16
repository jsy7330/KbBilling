package com.ntels.ccbs.system.controller.empty.empty;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/system/empty/empty/empty")
public class EmptyController {
	private static String URL = "system/empty/empty/empty";

	
	@RequestMapping(value = "emptyMain", method = RequestMethod.POST)
	public String emptyMain(Model model,  HttpServletRequest request) throws Exception {
		return  URL+"/empty";
	}
	
	@RequestMapping(value = "empty", method = RequestMethod.POST)
	public String empty(Model model,  HttpServletRequest request) throws Exception {
		return  URL+"/empty";
	}

}