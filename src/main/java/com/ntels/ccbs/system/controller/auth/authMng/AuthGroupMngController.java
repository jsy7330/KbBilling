package com.ntels.ccbs.system.controller.auth.authMng;

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

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.domain.auth.authMng.AuthGroupMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.auth.authMng.AuthGroupMngService;
import com.ntels.nisf.util.message.MessageUtil;

/**
 * <PRE>
 * 1. ClassName: AuthGroupMngController
 * 2. FileName : AuthGroupMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.auth.authMng
 * 4. Comment  : 그룹관리 컨트롤러
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 22. 오후 4:19:21
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 22.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/auth/authMng/authGroupMng")
public class AuthGroupMngController {
	/**
	 * 그룹관리 메인 URL
	 */
	private static String URL = "system/auth/authMng/authGroupMng";
	
	
	/**
	 * 그룹관리 서비스
	 */
	@Autowired AuthGroupMngService authGroupMngService;
	
	/**
	 * <PRE>
	 * 1. MethodName: authGroupMng
	 * 2. ClassName : AuthGroupMngController
	 * 3. Comment   : 그룹관리 메인뷰
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:20:29
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "authGroupMng", method = RequestMethod.POST)
	public String authGroupMng(	Model model,AuthGroupMngVO authGroup ,HttpServletRequest request) {
		model.addAttribute("authGroup", authGroup);
		return URL + "/authGroupMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: mainListAction
	 * 2. ClassName : AuthGroupMngController
	 * 3. Comment   : 그룹관리 리스트 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:21:05
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @param srchYn 검색버튼 클릭 여부
	 *   @param condGroupId 기본그룹ID 조건
	 *   @param condGroupNm 기본그룹명 조건
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 */
	@RequestMapping(value = "mainListAction", method = RequestMethod.POST)
	public String mainListAction(Model model,AuthGroupMngVO authGroup,HttpServletRequest request
			,String srchYn
			,String condGroupId
			,String condGroupNm
			,String sidx
			,String sord
			,int page
			,int rows){
		if(StringUtils.isEmpty(srchYn)){
			return URL + "/ajax/authGroupMng";
		}
		authGroup.setCondGroupId(condGroupId);
		authGroup.setCondGroupNm(condGroupNm);
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> authGroupInfo = authGroupMngService.list(sidx,sord, page, rows, lng,authGroup);
		
		model.addAttribute("authGroupList", authGroupInfo.get("authGroupList"));
		model.addAttribute("totalCount", authGroupInfo.get("totalCount"));
		model.addAttribute("totalPages", authGroupInfo.get("totalPages"));
		model.addAttribute("page", authGroupInfo.get("page"));
		
		return URL + "/ajax/authGroupMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCheckUserGroupIdAction
	 * 2. ClassName : AuthGroupMngController
	 * 3. Comment   : 기본그룹ID 중복체크
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:29:48
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @throws InvalidKeyException
	 *   @throws UnsupportedEncodingException
	 *   @throws NoSuchAlgorithmException
	 *   @throws NoSuchPaddingException
	 *   @throws InvalidAlgorithmParameterException
	 *   @throws IllegalBlockSizeException
	 *   @throws BadPaddingException
	 */
	@RequestMapping(value = "getCheckUserGroupIdAction", method = RequestMethod.POST)
	public String getCheckUserGroupIdAction(Model model,AuthGroupMngVO authGroup,HttpServletRequest request) throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		int check=authGroupMngService.checkUserId(authGroup);
		model.addAttribute("check", check);
		return URL + "/ajax/authGroupMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: insertAction
	 * 2. ClassName : AuthGroupMngController
	 * 3. Comment   : 그룹관리 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:31:25
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @throws InvalidKeyException
	 *   @throws UnsupportedEncodingException
	 *   @throws NoSuchAlgorithmException
	 *   @throws NoSuchPaddingException
	 *   @throws InvalidAlgorithmParameterException
	 *   @throws IllegalBlockSizeException
	 *   @throws BadPaddingException
	 */
	@RequestMapping(value = "insertAction", method = RequestMethod.POST)
	public String insertAction(Model model,AuthGroupMngVO authGroup,HttpServletRequest request) throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		try{
			int result = authGroupMngService.insert(authGroup,request);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		

		return URL + "/ajax/authGroupMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: updateAction
	 * 2. ClassName : AuthGroupMngController
	 * 3. Comment   : 그룹관리 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:32:01
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "updateAction", method = RequestMethod.POST)
	public String updateAction(Model model,AuthGroupMngVO authGroup,HttpServletRequest request){
		int result = authGroupMngService.update(authGroup,request);
		
		return URL + "/ajax/authGroupMng";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: deleteAction
	 * 2. ClassName : AuthGroupMngController
	 * 3. Comment   : 그룹관리 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:32:45
	 * </PRE>
	 *   @return String 리턴URL
	 *   @param model {@link Model}
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "deleteAction", method = RequestMethod.POST)
	public String deleteAction(Model model,AuthGroupMngVO authGroup,HttpServletRequest request){
		int result = authGroupMngService.delete(authGroup,request);
		model.addAttribute("result", result);
		return URL + "/ajax/authGroupMng";
	}
}