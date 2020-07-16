package com.ntels.ccbs.product.controller.refInfo.commonInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;
import com.ntels.ccbs.product.service.refInfo.commonInfo.AttributeService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/commonInfo/attribute")
public class AttributeController{
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/commonInfo/attribute";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
	/**
	 * attributeList
	 * 설명 : 속성 목록 조회페이지 호출
	 */
	@RequestMapping(value = "attributeList", method = RequestMethod.POST)
	public String list(Model model, Attribute attribute, HttpServletRequest request) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String searchAttrNm = attribute.getSearchAttrNm();
		attribute.setSearchAttrNm(searchAttrNm);
		model.addAttribute("listIfSys", commonDataService.getCommonCodeList("PP00111", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
		return thisUrl + "/attributeList";
	}
	
	/** 
	 * attributeListAction
	 * 설	명 : 속성 목록 조회 페이지 리스트
	 * @param attribute
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "attributeListAction", method = RequestMethod.POST)
	public void attributeListAction(
			Attribute attribute
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

    	String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		String searchAttrNm = attribute.getSearchAttrNm();

		attribute.setSearchAttrNm(searchAttrNm);
		
		attribute.setLngTyp(lngTyp);
		attribute.setSidx(sidx);
		attribute.setSord(sord);
		
		//오늘날짜 가져오기
        String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
        attribute.setCurrentDay(currentDay);
		
        List<Attribute> list = new ArrayList<Attribute>();
        int count = 0;
		count = attributeService.count(attribute);		
		
		if (count > 0) list = attributeService.list(attribute, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);

	}
	/**
	 * attributeUpdate
	 * 설명 : 속성 상세 조회
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "attributeUpdate", method = RequestMethod.POST)
	public String attributeUpdate(
			Attribute attribute,
			Model model,
			HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		Attribute tmpAttribute = attributeService.getAttribute(attribute.getAttrCd(),lngTyp);
		tmpAttribute.setPage(attribute.getPage());
		tmpAttribute.setPerPage(attribute.getPerPage());
		model.addAttribute("attribute", tmpAttribute);

		String dataTypCd = "PP00111"; 
		//데이터유형 셀렉트박스에 들어갈 내용
		model.addAttribute("listIfSys", commonDataService.getCommonCodeList(dataTypCd, lngTyp));
		return thisUrl + "/attributeUpdate";
	}
	/**
	 * attributeUpdateAction
	 * 설명 : 속성 정보 수정
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "attributeUpdateAction", method = RequestMethod.POST)
	public String attributeUpdateAction(
			@RequestBody Attribute attribute, 
			Model model,
			HttpServletRequest request) {
		String resultUrl = thisUrl + "/attributeList";
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		attribute.setAttrNm(attribute.getAttrNm());
		
		/*
		System.out.println("getActDt==>"+attribute.getActDt());
		System.out.println("getInactDt==>"+attribute.getInactDt());
		*/		
		System.out.println("getOldActDt==>"+attribute.getOldActDt());

		String oldActDt = attribute.getOldActDt();
		
		String nowDate = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		String tomorrow = DateUtil.getDateStringYYYYMMDD(1);
		
		attribute.setInactDt(attribute.getInactDt());
		attribute.setActDt(attribute.getActDt());
		attribute.setOldActDt(oldActDt);
		/*
		System.out.println("getActDt2==>"+attribute.getActDt());
		System.out.println("getInactD2t==>"+attribute.getInactDt());
		System.out.println("getOldActDt2==>"+attribute.getOldActDt());
		System.out.println("nowDate==>"+nowDate);
		System.out.println("tomorrow==>"+tomorrow);
		*/
		attribute.setAttrStrtVal(attribute.getAttrStrtVal());
		attribute.setAttrEndVal(attribute.getAttrEndVal());
		attribute.setMstrFl(attribute.getMstrFl());
		attribute.setIfSys(attribute.getIfSys());
		attribute.setRemark(attribute.getRemark());
		attribute.setRefCd(attribute.getRefCd());
		attribute.setUserId(session.getUserId());
		attribute.setTomorrow(tomorrow);
		attribute.setSysToDate(DateUtil.sysdate());
		
		//기존 ActDt가 오늘 날짜보다 크면 수정 가능
		if(Integer.parseInt(nowDate) <= Integer.parseInt(oldActDt)){

			if(attributeService.update2(attribute)) {
				//System.out.println("update2==================>update2");
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
			}else{
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}
		}else{
			//기존 ActDt가 오늘 날짜보다 작으면 원부여부를 0, INACT_DT를 내일 날짜로 Update 하고 새로 Insert 처리

			if(attributeService.update(attribute)) {	
				//System.out.println("update==================>update");
				attribute.setAttrCd(sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMBI_ATTR, "AT", 5));	
				if(attributeService.insert(attribute)) {
				//	System.out.println("insert==================>insert");
					model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
				}else{
					model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
				}
			} else {		
				model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
			}
		}
		model.addAttribute("listIfSys", commonDataService.getCommonCodeList("PP00111", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
		return resultUrl;
	}	
	/**
	 * attributeInsert
	 * 설명 : 속성 등록 페이지 호출
	 * @param attribute
	 * @param int page
	 * @param int perPage
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "attributeInsert", method = RequestMethod.POST)
	public String attributeInsert(
			Attribute attribute,
			Model model,
			HttpServletRequest request) throws Exception {
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String dataTypCd = "PP00111"; 
		//데이터유형 셀렉트박스에 들어갈 내용
		model.addAttribute("listIfSys", commonDataService.getCommonCodeList(dataTypCd, lngTyp));
		return thisUrl + "/attributeInsert";
	}

	
	/**
	 * attributeInsertAction
	 * 설명 : 속성 정보 등록
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "attributeInsertAction", method = RequestMethod.POST)
	public String attributeInsertAction(
			@RequestBody Attribute attribute, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {
		
		String resultUrl = thisUrl + "/attributeList";
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		attribute.setAttrCd(sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMBI_ATTR, "AT", 5));	
		attribute.setAttrNm(attribute.getAttrNm());
		attribute.setInactDt(attribute.getInactDt());
		attribute.setActDt(attribute.getActDt());
		attribute.setAttrStrtVal(attribute.getAttrStrtVal());
		attribute.setAttrEndVal(attribute.getAttrEndVal());
		attribute.setMstrFl(attribute.getMstrFl());
		attribute.setIfSys(attribute.getIfSys());
		attribute.setRemark(attribute.getRemark());
		attribute.setRefCd(attribute.getRefCd());
		attribute.setUserId(session.getUserId());
		attribute.setSysToDate(DateUtil.sysdate());
		
		if(attributeService.insert(attribute)) {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
		} else {		
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}		
		model.addAttribute("listIfSys", commonDataService.getCommonCodeList("PP00111", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
		return resultUrl;
	}	
	
	/**
	 * deleteAction
	 * 설명 : 속성 종료
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteAction", method = RequestMethod.POST)
	public String deleteAction(
			@RequestBody Attribute attribute,
			Model model, 
			HttpServletRequest request) {
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		String resultUrl = thisUrl + "/attributeList";
		String oldActDt = attribute.getOldActDt();
		attribute.setUserId(session.getUserId());
		attribute.setInactDt(attribute.getInactDt().replaceAll("-", ""));
		attribute.setActDt(attribute.getActDt().replaceAll("-", ""));
		attribute.setSysToDate(DateUtil.sysdate());
		
		String nowDate = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		String tomorrow = DateUtil.getDateStringYYYYMMDD(1);
		attribute.setTomorrow(tomorrow);
		
		System.out.println("InactDt====>"+attribute.getInactDt());
		
		System.out.println("ActDt====>"+attribute.getActDt());
		
		if(attributeService.update(attribute)) {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
		} else {
			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
		}  
		return resultUrl;
	}
	
	/**
	 * commListPopUp
	 * 설명 : 공통코드 조회 하는 팝업 Layer
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "commListPopUp", method = RequestMethod.POST)
	public String commListPopUp(
			Attribute attribute
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String commonGrpNm = attribute.getCommonGrpNm();
		model.addAttribute("param",commonGrpNm);				
		return  thisUrl + "/ajax/commListPopUp";
	}
	/**
	 * commListPopUp
	 * 설명 : 공통코드 조회 하는 팝업 Layer
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "commListActionPopUp", method = RequestMethod.POST)
	public void commListActionPopUp(
			Attribute attribute
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
			int page = 0;		
			int perPageRow = 0;	
			
			if(request.getParameter("page") == null || request.getParameter("page") == ""){
				page = 1;
			}else{
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			if(request.getParameter("rows") == null || request.getParameter("rows") == ""){
				perPageRow = 10;
			}else{
				perPageRow = Integer.parseInt(request.getParameter("rows"));
			}
		
			String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
			String sidx = request.getParameter("sidx");  // Order by 대상 컬럼
			String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
			
			attribute.setLngTyp(lngTyp);
			attribute.setSidx(sidx);
			attribute.setSord(sord);

			System.out.println("getCommonGrpNm==============>"+attribute.getCommonGrpNm());
			
			int count = 0;
			count = attributeService.commCnt(attribute);
			
			List<Attribute> list = null;		
			if (count > 0) list = attributeService.commList(attribute, page, perPage);
		
			model.addAttribute("rows", list);	
			model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); 
			model.addAttribute("records", list.size()); 
			model.addAttribute("page", page); 
			model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * commonCdListAction
	 * 설명 : 공통코드 목록 조회 페이지 리스트
	 * @param String
	 * @return model
	 */
	public List<Map<String, Object>> commonCdListAction(String commonGrp, String lngTyp) throws Exception {
		List<Map<String, Object>> list = attributeService.commomCdList(commonGrp,lngTyp);		
		return list;		
	}

}