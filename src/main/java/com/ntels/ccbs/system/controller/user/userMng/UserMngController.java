package com.ntels.ccbs.system.controller.user.userMng;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.crypto.AES256Cipher;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.user.userMng.UserMngService;
import com.ntels.nisf.util.message.MessageUtil;

/**
 * <PRE>
 * 1. ClassName: UserMngController
 * 2. FileName : UserMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.user.userMng
 * 4. Comment  : 사용자관리 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 21. 오전 11:43:01
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 21.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/user/userMng/userMng")
public class UserMngController {
	/**
	 * 사용자관리 메인 URL
	 */
	private static String URL = "system/user/userMng/userMng";
	

	/**
	 * 사용자관리 서비스
	 */
	@Autowired UserMngService userMngService;
	/**
	 * 공통코드 서비스
	 */
	@Autowired
	private CommonDataService commonDataService;
	/**
	 * <PRE>
	 * 1. MethodName: userMng
	 * 2. ClassName : UserMngController
	 * 3. Comment   : 사용자관리 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 21. 오전 11:44:06
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param user 사용자관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "userMng", method = RequestMethod.POST)
	public String userMng(	Model model,UserMngVO user,HttpServletRequest request) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("user", user);
		model.addAttribute("commonCodeList", commonDataService.getCommonCodeList("SY00003", lng));
		return URL + "/userMng";
		
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: mainListAction
	 * 2. ClassName : UserMngController
	 * 3. Comment   : 사용자관리 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 3:51:04
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param user 사용자관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @param srchYn 검색버튼 클릭 여부
	 *   @param condSoId 사업ID 조건
	 *   @param condOrgId 조직ID 조건
	 *   @param condOrgNm 조직명 조건
	 *   @param condUserNm 사용자명 조건
	 *   @param condYn 사용여부 조건
	 *   @param condLockYn 잠김여부 조건
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 */
	@RequestMapping(value = "mainListAction", method = RequestMethod.POST)
	public String mainListAction(Model model,UserMngVO user,HttpServletRequest request
			,String srchYn
			,String condSoId
			,String condOrgId
			,String condOrgNm
			,String condUserNm
			,String condYn
			,String condLockYn
			,String sidx
			,String sord
			,int page
			,int rows){
		
		if(StringUtils.isEmpty(srchYn)){
			return URL + "/ajax/userMng";
		}
			user.setCondSoId(condSoId);
			user.setCondOrgId(condOrgId);
			user.setCondOrgNm(condOrgNm);
			user.setCondUserNm(condUserNm);
			user.setCondYn(condYn);
			user.setCondLockYn(condLockYn);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> soInfo = userMngService.list(sidx,sord, page, rows, lng,user);
		model.addAttribute("max",  soInfo.get("max"));
		model.addAttribute("userList", soInfo.get("userList"));
		model.addAttribute("userListCnt", soInfo.size());
		model.addAttribute("totalCount", soInfo.get("totalCount"));
		model.addAttribute("totalPages", soInfo.get("totalPages"));
		model.addAttribute("page", soInfo.get("page"));
		return URL + "/ajax/userMng";
		
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: insertAction
	 * 2. ClassName : UserMngController
	 * 3. Comment   : 사용자 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 3:53:16
	 * </PRE>
	 *  @return String 리턴 URL
	 *  @param {@link Model}
	 *  @param user 사용자관리VO
	 *  @param request {@link HttpServletRequest}
	 *   @throws InvalidKeyException
	 *   @throws UnsupportedEncodingException
	 *   @throws NoSuchAlgorithmException
	 *   @throws NoSuchPaddingException
	 *   @throws InvalidAlgorithmParameterException
	 *   @throws IllegalBlockSizeException
	 *   @throws BadPaddingException
	 */
	@RequestMapping(value = "insertAction", method = RequestMethod.POST)
	public String insertAction(Model model,UserMngVO user,HttpServletRequest request) throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		try{
			int result = userMngService.insert(user,request);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		return URL + "/ajax/userMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: updateAction
	 * 2. ClassName : UserMngController
	 * 3. Comment   : 사용자수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 3:53:53
	 * </PRE>
	 *  @return String 리턴 URL
	 *  @param {@link Model}
	 *  @param user 사용자관리VO
	 *  @param request {@link HttpServletRequest}
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	@RequestMapping(value = "updateAction", method = RequestMethod.POST)
	public String updateAction(Model model,UserMngVO user,HttpServletRequest request) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		int result = userMngService.update(user,request);
		
		return URL + "/ajax/userMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCheckUserIdAction
	 * 2. ClassName : UserMngController
	 * 3. Comment   : 사용자ID 중복체크
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 3:54:38
	 * </PRE>
	 *  @return String 리턴 URL
	 *  @param {@link Model}
	 *  @param user 사용자관리VO
	 *  @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "getCheckUserIdAction", method = RequestMethod.POST)
	public String getCheckUserIdAction(Model model,UserMngVO user,HttpServletRequest request){
		int check=userMngService.checkUserId(user);
		model.addAttribute("check", check);
		return URL + "/ajax/userMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: updatePasswordResetAction
	 * 2. ClassName : UserMngController
	 * 3. Comment   : 비밀번호 초기화(메일로 암호화)
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 3:57:13
	 * </PRE>
	 *  @return String 리턴 URL
	 *  @param {@link Model}
	 *  @param user 사용자관리VO
	 *  @param request {@link HttpServletRequest}
	 *   @param userId 사용자ID
	 *   @param eMail 이메일
	 *   @param password 패스워드
	 *   @throws InvalidKeyException
	 *   @throws UnsupportedEncodingException
	 *   @throws NoSuchAlgorithmException
	 *   @throws NoSuchPaddingException
	 *   @throws InvalidAlgorithmParameterException
	 *   @throws IllegalBlockSizeException
	 *   @throws BadPaddingException
	 */
	@RequestMapping(value = "updatePasswordResetAction", method = RequestMethod.POST)
	public String updatePasswordResetAction(Model model,UserMngVO user,HttpServletRequest request,String userId,String eMail,String password){
		//password=AES256Cipher.AES_Encode(eMail, Consts.ENCODE_KEY);;
		model.addAttribute("change", "PASSWORD_INIT");
		return URL + "/ajax/userMng";
	}
	
}