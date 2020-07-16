package com.ntels.ccbs.system.controller.configuration.codeMng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpVO;
import com.ntels.ccbs.system.service.configuration.codeMng.CommonCodeMngService;


/**
 * <PRE>
 * 1. ClassName: CommonCodeMngController
 * 2. FileName : CommonCodeMngController.java
 * 3. Package  : com.ntels.ccbs.system.controller.configuration.codeMng
 * 4. Comment  : 공통코드 관리 컨트롤러
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 6. 20. 오전 10:21:06
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 6. 20.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/system/configuration/codeMng/commonCodeMng")
public class CommonCodeMngController {


	/**
	 * 공통코드 관리 메인 URL
	 */
	private static String URL = "system/configuration/codeMng/commonCodeMng";
	
	/**
	 * 공통코드 관리 서비스
	 */
	@Autowired
	private CommonCodeMngService commonCodeMngService;
	
	/**
	 * Logger 
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * <PRE>
	 * 1. MethodName: viewInit
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 관리 메인 뷰
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:24:17
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "commonCode", method = RequestMethod.POST)
	public String viewInit(	Model mode, HttpServletRequest request) {
		return URL + "/commonCode";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeGrpListAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 그룹 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:25:28
	 * </PRE>
	 *   @return Object 공통코드 그룹 Tree뷰 작성을 위한 Object
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "getCommonCodeGrpListAction", method = RequestMethod.POST)
	public @ResponseBody Object getCommonCodeGrpListAction(Model model, HttpServletRequest request) {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		ArrayList<Object> root = new ArrayList<Object>(); // json data
		
		Map<String, Object> rootNode = new HashMap<String, Object>();
		rootNode.put("title", "/");
		rootNode.put("isFolder",true);
		rootNode.put("key", "ROOT"); 
		rootNode.put("expand", true);
		rootNode.put("order", 0);
		
		List<Map<String,Object>> codeGrpTypList = commonCodeMngService.getCodeGrpTreeList(lng);
		
		rootNode.put("children", codeGrpTypList);
		
		root.add(rootNode);
		
		return root;
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getCommonCodeListAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 상세 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:26:35
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param condGroupId 조회 대상 그룹 ID
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 */
	@RequestMapping(value = "getCommonCodeListAction", method = RequestMethod.POST)
	public String getCommonCodeListAction(Model model, HttpServletRequest request,
			String condGroupId,
			String sidx,
			String sord) {
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		if(StringUtils.isEmpty(condGroupId)){
			model.addAttribute("codeList", "");
			return URL + "/ajax/commonCode";	
		}
		
		List<CommonCodeVO> codeList = commonCodeMngService.getCommonCodeList(condGroupId, sidx, sord, lng);
		model.addAttribute("codeList", codeList);
		return URL + "/ajax/commonCode";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getLngListAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 언어 코드 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:29:05
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 */
	@RequestMapping(value = "getLngListAction", method = RequestMethod.POST)
	public String getLngListAction(Model model, HttpServletRequest request) {
		model.addAttribute("lngList", commonCodeMngService.getLngList());
		return URL + "/ajax/commonCode";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: insertCodeDetailAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 상세 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:29:43
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param codeInfo 코드 상세 추가 정보
	 */
	@RequestMapping(value = "insertCodeDetailAction", method = RequestMethod.POST)
	public String insertCodeDetailAction(Model model, HttpServletRequest request, @RequestBody Map<String, Object> codeInfo) {

		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		
		CommonCodeVO insertCodeDetail = new CommonCodeVO();
		insertCodeDetail.setCommonGrp((String) codeInfo.get("commonGrp"));
		insertCodeDetail.setCommonGrpNm((String) codeInfo.get("commonGrpNm"));
		insertCodeDetail.setCommonCd((String) codeInfo.get("commonCd"));
		insertCodeDetail.setCommonCdNm((String) codeInfo.get("commonCdNm"));
		insertCodeDetail.setSortNo((String) codeInfo.get("sortNo"));
		insertCodeDetail.setDefaultYn((String) codeInfo.get("defaultYn"));
		insertCodeDetail.setUseYn((String) codeInfo.get("useYn"));
		insertCodeDetail.setRefCode((String) codeInfo.get("refCode"));
		insertCodeDetail.setRefCode2((String) codeInfo.get("refCode2"));
		insertCodeDetail.setRefCode3((String) codeInfo.get("refCode3"));
		insertCodeDetail.setRefCode4((String) codeInfo.get("refCode4"));
		insertCodeDetail.setRmrk((String) codeInfo.get("rmrk"));
		insertCodeDetail.setCodeLngList((List<Map<String,Object>>) codeInfo.get("codeLngList"));
		insertCodeDetail.setChgrId(sessionUser.getUserId());
		insertCodeDetail.setRegrId(sessionUser.getUserId());
		insertCodeDetail.setChgDate(DateUtil.sysdate());
		insertCodeDetail.setRegDate(DateUtil.sysdate());
		try{
			commonCodeMngService.insertCodeDetailInfo(insertCodeDetail);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		
		return URL + "/ajax/commonCode";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: updateCodeDetailAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 상세 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:30:37
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param codeInfo 코드 상세 수정 정보
	 */
	@RequestMapping(value = "updateCodeDetailAction", method = RequestMethod.POST)
	public String updateCodeDetailAction(Model model, HttpServletRequest request, @RequestBody Map<String, Object> codeInfo) {

		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		CommonCodeVO updateCodeDetail = new CommonCodeVO();
		updateCodeDetail.setCommonGrp((String) codeInfo.get("commonGrp"));
		updateCodeDetail.setCommonGrpNm((String) codeInfo.get("commonGrpNm"));
		updateCodeDetail.setCommonCd((String) codeInfo.get("commonCd"));
		updateCodeDetail.setCommonCdNm((String) codeInfo.get("commonCdNm"));
		updateCodeDetail.setSortNo((String) codeInfo.get("sortNo"));
		updateCodeDetail.setDefaultYn((String) codeInfo.get("defaultYn"));
		updateCodeDetail.setUseYn((String) codeInfo.get("useYn"));
		updateCodeDetail.setRefCode((String) codeInfo.get("refCode"));
		updateCodeDetail.setRefCode2((String) codeInfo.get("refCode2"));
		updateCodeDetail.setRefCode3((String) codeInfo.get("refCode3"));
		updateCodeDetail.setRefCode4((String) codeInfo.get("refCode4"));
		updateCodeDetail.setRmrk((String) codeInfo.get("rmrk"));
		updateCodeDetail.setCodeLngList((List<Map<String,Object>>) codeInfo.get("codeLngList"));
		updateCodeDetail.setChgrId(sessionUser.getUserId());
		updateCodeDetail.setChgDate(DateUtil.sysdate());
		try{
			commonCodeMngService.updateCodeDetailInfo(updateCodeDetail);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		
		return URL + "/ajax/commonCode";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeDetailAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 상세 삭제
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:31:30
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param codeGrp 삭제 대상 그룹ID
	 *   @param code 삭제 대상 코드
	 */
	@RequestMapping(value = "deleteCodeDetailAction", method = RequestMethod.POST)
	public String deleteCodeDetailAction(Model model, HttpServletRequest request,String codeGrp, String code) {
		try{
			commonCodeMngService.deleteCodeDetail(codeGrp, code);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		
		return URL + "/ajax/commonCode";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: openCommonCodeGrpPopup
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통 코드 그룹 팝업 오픈
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:32:26
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param request
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param systemId 오픈 대상 시스템 ID
	 *   @param systemNm 오픈 대상 시스템명
	 *   @param codeGrp 오픈대상 그룹ID(수정시)
	 *   @param codeGrpNm 오픈대상 그룹명(수정시)
	 */
	@RequestMapping(value = "openCommonCodeGrpPopup", method = RequestMethod.POST)
	public String openCommonCodeGrpPopup(HttpServletRequest request, Model model,String mode, String systemId, String systemNm, String codeGrp, String codeGrpNm) {
		
		model.addAttribute("mode", mode);
		model.addAttribute("systemId", systemId);
		model.addAttribute("systemNm", systemNm);
		model.addAttribute("codeGrp", codeGrp);
		model.addAttribute("codeGrpNm", codeGrpNm);
		
		return  URL+"/ajax/commonCodeGrpPopup";
		
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: insertCodeGrpAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 그룹 추가
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:33:45
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param grpInfo 추가 대상 그룹 정보
	 *   @return
	 */
	@RequestMapping(value = "insertCodeGrpAction", method = RequestMethod.POST)
	public String insertCodeGrpAction(Model model, HttpServletRequest request, @RequestBody Map<String, Object> grpInfo) {

		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		
		CommonGrpVO insertCodeGrpInfo = new CommonGrpVO();
		insertCodeGrpInfo.setCommonGrp((String) grpInfo.get("codeGrp"));
		insertCodeGrpInfo.setCommonGrpNm((String) grpInfo.get("codeGrpNm"));
		insertCodeGrpInfo.setSystemId((String) grpInfo.get("systemId"));
		insertCodeGrpInfo.setLength((String) grpInfo.get("length"));
		insertCodeGrpInfo.setUseYn((String) grpInfo.get("useYn"));
		insertCodeGrpInfo.setRefCode((String) grpInfo.get("refCode"));
		insertCodeGrpInfo.setRefCode2((String) grpInfo.get("refCode2"));
		insertCodeGrpInfo.setRefCode3((String) grpInfo.get("refCode3"));
		insertCodeGrpInfo.setRmrk((String) grpInfo.get("rmrk"));
		insertCodeGrpInfo.setCodeLngList((List<Map<String,Object>>) grpInfo.get("codeGrpLngList"));
		insertCodeGrpInfo.setChgrId(sessionUser.getUserId());
		insertCodeGrpInfo.setRegrId(sessionUser.getUserId());
		insertCodeGrpInfo.setChgDate(DateUtil.sysdate());
		insertCodeGrpInfo.setRegDate(DateUtil.sysdate());
		try{
			commonCodeMngService.insertCodeGrpInfo(insertCodeGrpInfo);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		
		return URL + "/ajax/commonCodeGrpPopup";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: updateCodeGrpAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 그룹 수정
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 8:09:30
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param request
	 *   @param grpInfo
	 *   @return
	 */
	@RequestMapping(value = "updateCodeGrpAction", method = RequestMethod.POST)
	public String updateCodeGrpAction(Model model, HttpServletRequest request, @RequestBody Map<String, Object> grpInfo) {

		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		
		CommonGrpVO updateCodeGrpInfo = new CommonGrpVO();
		updateCodeGrpInfo.setCommonGrp((String) grpInfo.get("codeGrp"));
		updateCodeGrpInfo.setCommonGrpNm((String) grpInfo.get("codeGrpNm"));
		updateCodeGrpInfo.setSystemId((String) grpInfo.get("systemId"));
		updateCodeGrpInfo.setLength((String) grpInfo.get("length"));
		updateCodeGrpInfo.setUseYn((String) grpInfo.get("useYn"));
		updateCodeGrpInfo.setRefCode((String) grpInfo.get("refCode"));
		updateCodeGrpInfo.setRefCode2((String) grpInfo.get("refCode2"));
		updateCodeGrpInfo.setRefCode3((String) grpInfo.get("refCode3"));
		updateCodeGrpInfo.setRmrk((String) grpInfo.get("rmrk"));
		updateCodeGrpInfo.setCodeLngList((List<Map<String,Object>>) grpInfo.get("codeGrpLngList"));
		updateCodeGrpInfo.setChgrId(sessionUser.getUserId());
		updateCodeGrpInfo.setChgDate(DateUtil.sysdate());
		try{
			commonCodeMngService.updateCodeGrpInfo(updateCodeGrpInfo);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		
		return URL + "/ajax/commonCodeGrpPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCommonGrpInfoAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 그룹정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 20. 오전 10:41:13
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param codeGrp 조회 대상 그룹 정보
	 *   @return
	 */
	@RequestMapping(value = "getCommonGrpInfoAction", method = RequestMethod.POST)
	public String getCommonGrpInfo(HttpServletRequest request, Model model,String codeGrp) {
		
		model.addAttribute("codeGrp", commonCodeMngService.getCodeGrpInfo(codeGrp));
		
		return  URL+"/ajax/commonCodeGrpPopup";
		
	}
	

	/**
	 * <PRE>
	 * 1. MethodName: deleteCodeGrpAction
	 * 2. ClassName : CommonCodeMngController
	 * 3. Comment   : 공통코드 그룹 삭제
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오전 9:27:42
	 * </PRE>
	 *   @return String 리턴 URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param codeGrp 삭제 대상 그룹
	 *   @return
	 */
	@RequestMapping(value = "deleteCodeGrpAction", method = RequestMethod.POST)
	public String deleteCodeGrpAction(Model model, HttpServletRequest request,String codeGrp) {
		try{
			commonCodeMngService.deleteCodeGrp(codeGrp);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		
		return URL + "/ajax/commonCode";
	}
	
}
