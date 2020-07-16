package com.ntels.ccbs.product.controller.refInfo.ratingRefInfo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.PurchaseUnitPriceVO;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.AttrTypMap;
import com.ntels.ccbs.product.service.refInfo.commonInfo.AttrTypMapService;
import com.ntels.ccbs.product.service.refInfo.commonInfo.AttributeService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/timePeriod")
public class TimePeriodController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private AttrTypMapService attrTypMapService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/timePeriod";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    

	/**
	 * attrTypMapList
	 * 설명 : 속성유형연결 목록 조회페이지 호출
	 */
	@RequestMapping(value = "timePeriod", method = RequestMethod.POST)
	public String list(Model model, AttrTypMap attrTypMap, HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String dataTypCd = "PP00017"; 
		
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		attrTypMap.setCurrentDay(currentDay);
		model.addAttribute("listCommon", commonDataService.getCommonCodeList(dataTypCd, lngTyp));
		model.addAttribute("listAttr", attrListAction(attrTypMap)); //속성 셀렉트 박스에 들어갈 속성 

		return thisUrl + "/timePeriod";
	}
	/**
	 * attributeListAction
	 * 설명 : 속성 목록 조회 페이지 리스트
	 * @param attribute
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "attrTypMapListAction", method = RequestMethod.POST)
	public void attrTypMapListAction(
			AttrTypMap attrTypMap
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		attrTypMap.setLngTyp(lngTyp);
		attrTypMap.setSidx(sidx);
		attrTypMap.setSord(sord);
		
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		attrTypMap.setCurrentDay(currentDay);
		
		List<AttrTypMap> list = null;
		int count = 0;
		count = attrTypMapService.count(attrTypMap);		

		if (count > 0) list = attrTypMapService.list(attrTypMap, page, perPageRow);

		model.addAttribute("searchAttrTyp", attrTypMap.getSearchAttrTyp());	
		model.addAttribute("getSearchAttrCd", attrTypMap.getSearchAttrCd());	
		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * attrTypMapInsert
	 * 설명 : 속성유형 등록 팝업 Layer
	 * @param attrTypMap
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "timePeriodTypPopUp", method = RequestMethod.POST)
	public String attrTypMapInsert(
			AttrTypMap attrTypMap
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
				
		
		String[] dataTypCdList = new String[] {
				"PP00256", "PP00291", "PP00226", "PP00238",	"PP00212", "PP00247", "PP00227",
				"PP00225", "PP00290", "PP00224", "PP00275", "PP00248"
		};
		
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		attrTypMap.setCurrentDay(currentDay);
		System.out.println("currentDay==>"+currentDay);
		//속성유형 셀렉트 박스에 들어갈 속성 유형 
		
		for( int i=0; i<dataTypCdList.length; i++ ) {
			model.addAttribute("listCommon"+(i+1), commonDataService.getCommonCodeList(dataTypCdList[i], lngTyp));
		}
		model.addAttribute("attrTyp",attrTypMap.getSearchAttrTyp());	
		model.addAttribute("listAttr", attrListAction(attrTypMap)); //속성 셀렉트 박스에 들어갈 속
		model.addAttribute("lngTyp",lngTyp);
		
		return  thisUrl + "/ajax/timePeriodTypPopUp";
	}
	

	/**
	 * commListPopUp
	 * 설명 : 상품속성 유형 등록 리스트 조회 하는 팝업 Layer
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "manageRatingDefInsertActionPopUp", method = RequestMethod.POST)
	public void attrTypMapInsertActionPopUp(
			AttrTypMap attrTypMap
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
			System.out.println("searchAttrTyp=======>"+request.getParameter("searchAttrTyp"));
			System.out.println("searchAttrTyp2=======>"+request.getParameter("searchAttrTyp2"));	
			attrTypMap.setSearchAttrTyp(request.getParameter("searchAttrTyp"));
			attrTypMap.setLngTyp(lngTyp);
			attrTypMap.setSidx(sidx);
			attrTypMap.setSord(sord);
			
			String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
			attrTypMap.setCurrentDay(currentDay);
			
			List<AttrTypMap> list = null;
			int count = 0;
			count = attrTypMapService.count2(attrTypMap);		

			if (count > 0) list = attrTypMapService.list2(attrTypMap, page, perPage);			
			model.addAttribute("lngTyp",lngTyp);
			model.addAttribute("searchAttrTyp2", attrTypMap.getSearchAttrTyp2());	// 목록리스트
			model.addAttribute("rows", list);	// 목록리스트
			model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
			model.addAttribute("records", list.size()); //현화면의 리스트갯수		
			model.addAttribute("page", page); 			//현재 페이지
			model.addAttribute("perpage", perPageRow);
	}	
	
	/**
	 * attrTypMapInsertAction
	 * 설명 : 속성유형연결 정보 등록
	 * @param attrTypMap
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "manageRatingDefInsertAction", method = RequestMethod.POST)
	public String attrTypMapInsertAction(
			AttrTypMap attrTypMap, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {

		String resultUrl = thisUrl + "/manageRatingDefList";

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");

		String insAttrCd = attrTypMap.getInsAttrCd();
		String attrTyp =attrTypMap.getSearchAttrTyp2();	
		
		String[] result = insAttrCd.split(",");
		
		System.out.println("getInsActDt====>"+attrTypMap.getInsActDt());
		System.out.println("attrTyp====>"+attrTyp);
		
		attrTypMap.setAttrTyp(attrTyp);
		attrTypMap.setActDt(attrTypMap.getInsActDt().replaceAll("-", ""));
		
		attrTypMap.setUserId(session.getUserId());
		attrTypMap.setInactDt("99991231");
		attrTypMap.setSysToDate(DateUtil.sysdate());
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		
		List<Map<String, Object>> list = null;	
		for(int i = 0;  i < result.length; i++)	{
		
			attrTypMap.setAttrCd(result[i]);

			int count = 0;
			count = attrTypMapService.attrCnt(result[i],attrTyp,currentDay);		//동일 속성유형연결 중복건수 조회	
			if (count == 0){
				//속성의 실제 적용일자 정보를 조회
				list = attrTypMapService.attrDate(result[i]);
                
				//연결정보의 적용시작일자와 속성의 실제 적용시작일 체크
                //속성테이블에서 적용시작일의 최소값을 가져온후, 그 값이 UI에서 파라미터로 넘긴 적용시작일자보다 작으면 정상적으로 입력한다.
                //즉, UI에서 넘긴 파라미터값은 속성테이블의 등록되어 있는 적용시작일자의 최소값보단 작을 수 없다.
                if ( ( (String) list.get(0).get("ACT_DT")).compareTo( (String) list.get(0).get("INACT_DT")) <= 0 ){
            		if(attrTypMapService.insert(attrTypMap)) {		
            			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
            		} else {		
            			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
            		}	
                } else {
                	model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022")); //적용시작일은 당월 1일부터 익월 현재일기간내에 존재해야합니다. 
                }
			}
		}
		return resultUrl;
	}
	/**
	 * attrTypMapInsert
	 * 설명 : 속성유형 등록 팝업 Layer
	 * @param attrTypMap
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "attrTypMapUpdatePopUp", method = RequestMethod.POST)
	public String attrTypMapUpdatePopUp(
			AttrTypMap attrTypMap
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		AttrTypMap tempAttrTypMap = attrTypMapService.getAttrTypMap(attrTypMap.getAttrCd(),attrTypMap.getAttrTyp(),DateUtil.getDateStringYYYYMMDD(0),lngTyp);
		model.addAttribute("attrTypMap", tempAttrTypMap);
		
		return  thisUrl + "/ajax/attrTypMapUpdatePopUp";
	}
	
	/**
	 * attributeUpdateAction
	 * 설명 : 속성 정보 수정
	 * @param attribute
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "attrTypMapUpdateAction", method = RequestMethod.POST)
	public String attrTypMapUpdateAction(
			AttrTypMap attrTypMap,
			Model model,
			HttpServletRequest request) {
		String resultUrl = thisUrl + "/attrTypMapList";

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
			
		String attrCd = attrTypMap.getInsAttrCd();
		String attrTyp = attrTypMap.getInsAttrTyp();	
		String inactDt = attrTypMap.getInsInactDt().replaceAll("-", "");
		String actDt = attrTypMap.getInsActDt().replaceAll("-", "");	
		
		attrTypMap.setAttrTyp(attrTyp);
		attrTypMap.setInactDt(inactDt);
		attrTypMap.setActDt(actDt);
		attrTypMap.setUserId(session.getUserId());
		attrTypMap.setAttrCd(attrCd);
		attrTypMap.setSysToDate(DateUtil.sysdate());
		
		List<Map<String, Object>> list = null;	
		
		//속성의 실제 적용일자 정보를 조회
		list = attrTypMapService.attrDate(attrCd);
		
        //적용하고자 하는 종료일이 속성의 실제 적용시작일 기간내 존재하는 경우
        if ( Integer.parseInt( (String) actDt) >= Integer.parseInt( (String) list.get(0).get("ACT_DT"))
            && Integer.parseInt( (String) inactDt) <= Integer.parseInt( (String) list.get(0).get("INACT_DT"))){
            //속성유형연결 정보의 적용일 update(종료 처리)
    		if(attrTypMapService.update(attrTypMap)) {		
    			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
    		} else {		
    			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
    		}	
        }else{
            if ( "99991231".equals( (String)  list.get(0).get("INACT_DT" ) ) ){
                //속성유형연결 정보의 적용일 update(종료 처리)
        		if(attrTypMapService.update(attrTypMap)) {	
        			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
        		} else {		
        			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
        		}	
            }else{
    			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
            }
        }		
		return resultUrl;
	}
	/**
	 * attrListAction
	 * 설명 : 속성목록 조회
	 * @param String
	 * @return model
	 */
	public List<Map<String, Object>> attrListAction(AttrTypMap attrTypMap) throws Exception {
		List<Map<String, Object>> list = attrTypMapService.attrListAction(attrTypMap);		
		return list;		
	}	
	
}