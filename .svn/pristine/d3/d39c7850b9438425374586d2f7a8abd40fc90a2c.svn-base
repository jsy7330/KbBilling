package com.ntels.ccbs.system.controller.login;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.crypto.AES256Cipher;
import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;
import com.ntels.ccbs.system.domain.common.common.MailTemplate;
import com.ntels.ccbs.system.domain.common.service.CountryLanguage;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.login.LoginService;

/**
 *
 * <PRE>
 * 1. ClassName: LoginController
 * 2. FileName : LoginController.java
 * 3. Package  : com.ntels.ccbs.system.controller.login
 * 4. Comment  : 사용자 로그인 및 인증 확인 Controller
 * 5. 작성자   : smyun@ntels.com
 * 6. 작성일   : 2014. 4. 10. 오후 1:01:21
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		smyun@ntels.com :	2014. 4. 10.	: 신규 개발.
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/login")
public class LoginController {

	/** 로그출력. */
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/** LoginService autowired. */
	@Autowired
	private LoginService loginService;
	
	/** mail url. */
	private String thisUrl = "system/login";
	
	/**
	 * 로그인 화면.
	 *
	 * @param request HttpServletRequest
	 * @return String
	 */
	@RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Model model, HttpServletRequest request) {
		String resultUrl = thisUrl + "/login";

		HttpSession session = request.getSession(false);

		if (session == null)
			return resultUrl;

		session.invalidate();
		
		// 언어코드
		List<CountryLanguage> listCountryLanguage = loginService.listCountryLanguage();

		model.addAttribute("listCountryLanguage", listCountryLanguage);
		//기본세팅
		request.getSession().setAttribute("sessionCountry", "KR");
		request.getSession().setAttribute("sessionLanguage", "ko");

		return resultUrl;
	}

	/**
	 *
	 * 로그인 프로세스.
	 *
	 * @param text_id 아이디
	 * @param text_nm 비밀번호
	 * @param model Model
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return Object
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	@RequestMapping(value = "loginAction", method = RequestMethod.POST)
	public @ResponseBody Object loginAction(
							@RequestParam(required = false) String text_id,
							@RequestParam(required = false) String text_nm,
							 Model					model,
							 HttpServletRequest		request,
							 HttpServletResponse	response) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		text_nm = AES256Cipher.AES_Encode(text_nm,Consts.ENCODE_KEY);
		HttpSession session = request.getSession(false);
		session.setAttribute("listCountryLanguage", loginService.listCountryLanguage());
		return loginService.updateLogin(text_id, text_nm, request);
	}

	/**
	 * 로그아웃 프로세스.
	 *
	 * @param model Model
	 * @param request HttpServletRequest
	 * @return String
	 */
	@RequestMapping(value = "logoutAction", method = RequestMethod.POST)
	public String logoutAction(Model model, HttpServletRequest request) {
		String resultUrl = "redirect:/system/login/login";

		HttpSession session = request.getSession(false);

		if (session == null)
			return resultUrl;

		SessionUser sessionUser = (SessionUser) session.getAttribute("session_user");
		if (sessionUser != null) {
			loginService.updateLogout(sessionUser, "N");
			logger.info("==>> logout!!! : {}", sessionUser.getUserId());
		}
		
		// session remove
		session.invalidate();
		return resultUrl;
	}
	
	@RequestMapping(value = "findPasswordPopup", method = {RequestMethod.GET, RequestMethod.POST})
	public String findPasswordPopup(Model model,HttpServletRequest request
			) {
			return thisUrl + "/findPasswordPopup";
	}
	
	@RequestMapping(value = "getUserInfoConfirm", method = {RequestMethod.GET, RequestMethod.POST})
	public String getUserInfoConfirm(Model model,HttpServletRequest request
			, @RequestBody Map<String, Object> pwInfo			){
		
		loginService.updateUserInfoConfirm((String)pwInfo.get("userId"),(String) pwInfo.get("email"));
		
		return thisUrl + "/ajax/findPasswordPopup";
	}
}
