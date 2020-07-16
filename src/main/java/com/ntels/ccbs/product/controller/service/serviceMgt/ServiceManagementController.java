package com.ntels.ccbs.product.controller.service.serviceMgt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.product.domain.service.serviceMgt.ServiceMgt;
import com.ntels.ccbs.product.service.product.productDev.ProductDevMgtService;
import com.ntels.ccbs.product.service.service.serviceMgt.ServiceMgtService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;


@Controller
@RequestMapping(value = "/product/service/serviceMgt/service")
public class ServiceManagementController {

	private static String URL = "product/service/serviceMgt/service";

	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ServiceMgtService serviceMgtservice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private ProductDevMgtService productDevMgtService;	
	
	@Value("${file.path}")
	private String filePath;

	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
/*		String userSoId = "";
		
		for (int i = 0; i < sessionUser.getSoAuthList().size(); i ++){
			if ( sessionUser.getSoId().equals(sessionUser.getSoAuthList().get(i).get("so_id"))){
				userSoId = sessionUser.getSoAuthList().get(i).get("so_nm") + sessionUser.getSoId();
			}
		}*/
		
		model.addAttribute("userSoId", sessionUser.getSoId());		
		
		return URL + "/main";
	}
	
	List<ServiceMgt> svcMgtList;
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
	List<Map<String,Object>> soAuthList;
	//오늘날짜 가져오기
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);

    
    /** 
     * 하루 전 날을 구한다. 
     */  
    public static Date getPreviousDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
          
        cal.add(Calendar.DATE, -1);  
          
        return cal.getTime();  
    }      
    
    public static String getMessage( String[] cnt, String[] msg )
    {
        String result = "";
        for ( int i = 0; i < cnt.length; i++ )
        {
            String strCnt = (String) cnt[i];
            if ( strCnt == null || strCnt.equals( "0" ) )
            {
            }
            else
            {
                result += msg[i];
            }
        }
        return result;
    }    
    
    
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getTreeAction
	 * 2. ClassName : ServiceManagementController
	 * 3. Comment   : 메뉴 Tree 데이터 조회
	 * 4. 작성자    : 정재훈
	 * 5. 작성일    : 2016. 5. 12. 오후 2:25:56
	 * </PRE>
	 *   @return Object
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "getTreeAction", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object getTreeAction(HttpServletRequest request, ServiceMgt serviceMgt) {
		String sessionLanguage = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();
		serviceMgt.setCurrentDay(currentDay);
		svcMgtList = serviceMgtservice.getListMenu(sessionLanguage, soAuthList, serviceMgt); // db data

		ArrayList<Object> root = new ArrayList<Object>(); // json data
		Map<String, Object> rootNode = new HashMap<String, Object>();
		Map<String, Boolean> removeNode = new HashMap<String, Boolean>();

		serviceMgt = svcMgtList.get(0);
		rootNode.put("title", serviceMgt.getTargetNm());
		rootNode.put("isFolder", serviceMgt.getIsFolder());
		rootNode.put("key", "0"); 
		rootNode.put("targetGubun", serviceMgt.getTargetGubun());
		rootNode.put("targetCd", serviceMgt.getTargetCd());
		rootNode.put("targetMainSvcCd", serviceMgt.getTargetMainSvcCd());
		rootNode.put("mstrFl", "1");
		rootNode.put("children", makeMenu2Json(null, 0, removeNode));
		rootNode.put("targetSoId", serviceMgt.getTargetSoId());
		rootNode.put("treeLevel", serviceMgt.getTreeLevel());

		
		

		root.add(rootNode);		

		return root;
	}
		
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: makeMenu2Json
	 * 2. ClassName : ServiceManagementController
	 * 3. Comment   : 자식 node 출력 
	 * 4. 작성자    : 정재훈
	 * 5. 작성일    : 2016. 5. 12. 오후 2:26:36
	 * </PRE>
	 *   @return ArrayList<Object>
	 *   @param parent
	 *   @param index
	 *   @param removeNode
	 *   @return
	 */
	private ArrayList<Object> makeMenu2Json(Map<String, Object> parent, int index, Map<String, Boolean> removeNode) {
		ServiceMgt serviceMgt = null;
		ArrayList<Object> folder = new ArrayList<Object>();
		String parent_menu_no = "0";
		String parent_so_id = "0";

		if (index > 0) {
			parent_menu_no = parent.get("key").toString();
			parent_so_id = parent.get("targetSoId").toString();
		}
		
		if (index == 0) {
			index++;
		}

		for(int i = index; i < svcMgtList.size(); i++) {
			serviceMgt = svcMgtList.get(i);

			if (index > 0 && !serviceMgt.getTargetUpperCd().equals(parent_menu_no)) {
				continue;
			}

			if (removeNode.get(serviceMgt.getTargetCd()) != null) {
				continue;
			}
			
			if (Integer.parseInt(serviceMgt.getTreeLevel()) > 1 && !serviceMgt.getTargetSoId().equals(parent_so_id)) {
				continue;
			}

			try {
				if (DateUtil.sysdate().compareTo(transFormat.parse(serviceMgt.getInactDt())) > 0 ){
					continue;
				}
			} catch (ParseException e) {
				logger.error("error", e);
			}				
			
			if ("TRUE".equals(serviceMgt.getIsFolder())) {
				Map<String, Object> node = new HashMap<String, Object>();

				node.put("title", serviceMgt.getTargetNm());
				node.put("isFolder", serviceMgt.getIsFolder());
				node.put("key", serviceMgt.getTargetCd());
				node.put("expand", "true");
				node.put("targetGubun", serviceMgt.getTargetGubun());
				node.put("targetCd", serviceMgt.getTargetCd());
				node.put("targetMainSvcCd", serviceMgt.getTargetMainSvcCd());
				node.put("mstrFl", serviceMgt.getMstrFl());
				node.put("targetSoId", serviceMgt.getTargetSoId());
				node.put("targetChrgCtgry", serviceMgt.getTargetChrgCtgry());
				node.put("treeLevel", serviceMgt.getTreeLevel());
				
				ArrayList<Object> tmpList = makeMenu2Json(node, index + 1, removeNode);

				if (tmpList.size() > 0) {
					node.put("children", tmpList);
				}

				folder.add(node);

				removeNode.put(serviceMgt.getTargetCd(), Boolean.TRUE);
			} else {
				Map<String, Object> leaf = new HashMap<String, Object>();

				leaf.put("title", serviceMgt.getTargetNm());
				leaf.put("key", serviceMgt.getTargetCd());
				leaf.put("targetGubun", serviceMgt.getTargetGubun());
				leaf.put("targetCd", serviceMgt.getTargetCd());
				leaf.put("targetMainSvcCd", serviceMgt.getTargetMainSvcCd());
				leaf.put("mstrFl", serviceMgt.getMstrFl());
				leaf.put("targetSoId", serviceMgt.getTargetSoId());
				leaf.put("targetChrgCtgry", serviceMgt.getTargetChrgCtgry());
				leaf.put("treeLevel", serviceMgt.getTreeLevel());
				
				folder.add(leaf);

				removeNode.put(serviceMgt.getTargetCd(), Boolean.TRUE);
			}
		}
		return folder;
	}

	@RequestMapping(value = "serviceMgtComListAction", method = RequestMethod.POST)
	public void serviceMgtComListAction(
			ServiceMgt serviceMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
	
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();		
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		serviceMgt.setLngTyp(lngTyp);
		serviceMgt.setTargetCd(request.getParameter("targetCd"));
		serviceMgt.setMstrFl(request.getParameter("mstrfl"));
		
        serviceMgt.setCurrentDay(currentDay);
        
		
		List<ServiceMgt> serviceMgtComList = null;
		int count = 0;
		count = serviceMgtservice.getServiceMgtComListCount(serviceMgt,soAuthList);
		logger.debug("count : {}", count);
		if (count > 0) { 
			serviceMgtComList = serviceMgtservice.getServiceMgtComList(serviceMgt, soAuthList, page, perPageRow);
			model.addAttribute("records", serviceMgtComList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", serviceMgtComList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);
		
		
	}	
	
	@RequestMapping(value = "serviceMgtInsertPopUp", method = RequestMethod.POST)
	public String serviceMgtInsertPopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");	
		
		
		String svcType = "PP00075";		//서비스유형
		String subscripCmpsFl = "PP00021";	//가입계약구성
		

        serviceMgt.setCurrentDay(currentDay);
		
        model.addAttribute("serviceMgt", serviceMgt);
		model.addAttribute("svcType", commonDataService.getCommonCodeList(svcType, lngTyp));
		model.addAttribute("subscripCmpsFl", commonDataService.getCommonCodeList(subscripCmpsFl, lngTyp));
		model.addAttribute("dispPriNo", serviceMgtservice.getNextDispPriNo(serviceMgt));
		model.addAttribute("targetUpperCd", serviceMgt.getTargetUpperCd());
		model.addAttribute("targetMainSvcCd", serviceMgt.getTargetMainSvcCd());
		
		return  URL + "/ajax/serviceMgtInsertPopup";
	}
	
	@RequestMapping(value = "insertServiceMgt", method = RequestMethod.POST)
	public void insertServiceMgt (
			ServiceMgt serviceMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		String svcCd = "";
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();

		serviceMgt.setCurrentDay(currentDay);
		serviceMgt.setSysdate(DateUtil.sysdate());

		serviceMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		serviceMgt.setRegrId(usrId);
		serviceMgt.setChgrId(usrId);
		serviceMgt.setSvcEngNm(serviceMgt.getSvcNm());
	
		logger.debug("svcCd : {}", serviceMgt.getSvcCd());
		int svcCnt = serviceMgtservice.getDualSvcNmCnt(serviceMgt);		

		logger.debug("svcCnt : {}", svcCnt);
		
		if (svcCnt == 0){
			
			svcCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMSV_SVC, "SV", 5);
			serviceMgt.setSvcCd(svcCd);	
			
			if (serviceMgt.getTargetMainSvcCd().equals(Consts.SVC_MGT_CODE.MAIN_SVC_UPR_SVC_CD) || 
					serviceMgt.getTargetMainSvcCd().equals(Consts.SVC_MGT_CODE.MAIN_SVC_UPR_SO_ID)){
								
				serviceMgt.setTargetMainSvcCd(svcCd);
				serviceMgt.setMainSvcFl(Consts.SVC_MGT_CODE.MAIN_SVC_FL_YES);
			} else {
				serviceMgt.setMainSvcFl(Consts.SVC_MGT_CODE.MAIN_SVC_FL_NO);
			}
			
			int result = serviceMgtservice.insertServiceMgt(serviceMgt);
			model.addAttribute("result", String.valueOf(result));
			
		} else {
			//throw new ServiceException( "MSG.M09.MSG00051" );
			model.addAttribute("result", "-1");
		}
				
	}

	@RequestMapping(value = "serviceMgtUpdatePopUp", method = RequestMethod.POST)
	public String serviceMgtUpdatePopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
		ServiceMgt getServiceMgt = null;
				
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		
		getServiceMgt = serviceMgtservice.getService(serviceMgt);
		
		
		String svcType = "PP00075";		//서비스유형
		String subscripCmpsFl = "PP00021";	//가입계약구성
		
        serviceMgt.setCurrentDay(currentDay);
		
		
		model.addAttribute("svcType", commonDataService.getCommonCodeList(svcType, lngTyp));
		model.addAttribute("subscripCmpsFl", commonDataService.getCommonCodeList(subscripCmpsFl, lngTyp));
		model.addAttribute("getServiceMgt", getServiceMgt);
		model.addAttribute("targetMainSvcCd", serviceMgt.getTargetMainSvcCd());
		model.addAttribute("svcCd", serviceMgt.getSvcCd());
		
		return  URL + "/ajax/serviceMgtUpdatePopUp";
	}	
	
	@RequestMapping(value = "updateServiceMgt", method = RequestMethod.POST)
	public void updateServiceMgt (
			ServiceMgt serviceMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {	
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();		
		
		serviceMgt.setCurrentDay(currentDay);
		serviceMgt.setChgrId(usrId);
		serviceMgt.setRegrId(usrId);
		serviceMgt.setSvcEngNm(serviceMgt.getSvcNm());
		serviceMgt.setSysdate(DateUtil.sysdate());
		
		String result = serviceMgtservice.updateServiceMgt(serviceMgt);

				
		model.addAttribute("result", String.valueOf(result));
	}	
	
	@RequestMapping(value = "serviceMgtSaleItmListAction", method = RequestMethod.POST)
	public void serviceMgtSaleItmListAction(
			ServiceMgt serviceMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
	
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();		
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		serviceMgt.setLngTyp(lngTyp);
		serviceMgt.setTargetCd(request.getParameter("targetCd"));
		
        serviceMgt.setCurrentDay(currentDay);
        
		
		List<ServiceMgt> serviceMgtSaleItmList = null;
		int count = 0;
		count = serviceMgtservice.getServiceMgtSaleItmListCount(serviceMgt,soAuthList);
		logger.debug("count : {}", count);
		if (count > 0) { 
			serviceMgtSaleItmList = serviceMgtservice.getServiceMgtSaleItmList(serviceMgt, soAuthList, page, perPageRow);
			model.addAttribute("records", serviceMgtSaleItmList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", serviceMgtSaleItmList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	}		
		
	@RequestMapping(value = "serviceMgtSaleItmListInsertPopUp", method = RequestMethod.POST)
	public String serviceMgtSaleItmListInsertPopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String saleTyp = "PP00188";		//서비스유형		

        serviceMgt.setCurrentDay(currentDay);
		
        model.addAttribute("serviceMgt", serviceMgt);
		model.addAttribute("saleTyp", commonDataService.getCommonCodeList(saleTyp, lngTyp));
		model.addAttribute("rateItmTyp", serviceMgtservice.getRateItmTypComboList(serviceMgt));
		model.addAttribute("svcCd", serviceMgt.getSvcCd());
		
		return  URL + "/ajax/serviceMgtSaleItmListInsertPopUp";
	}	

	@RequestMapping(value = "insertServiceMgtSaleItmListInsert", method = RequestMethod.POST)
	public void insertServiceMgtSaleItmListInsert (
			ServiceMgt serviceMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		String saleItmCd = "";
		
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();

		serviceMgt.setCurrentDay(currentDay);
		serviceMgt.setSysdate(DateUtil.sysdate());
		serviceMgt.setRegrId(usrId);
		serviceMgt.setChgrId(usrId);
		serviceMgt.setSaleItmEngNm(serviceMgt.getSaleItmNm());
	
		int svcCnt = serviceMgtservice.getDualSaleItmNmCnt(serviceMgt);
		
		if (svcCnt == 0){
			
			// 상품매출 항목 ID(SALE_ITM_CD)
			saleItmCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE. PD_TPMPD_SVC_RATE_ITM_TYP, "SI", 5);
			
			serviceMgt.setSaleItmCd(saleItmCd);
			serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
			serviceMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
			
			
			int result = serviceMgtservice.insertServiceMgtSaleItm(serviceMgt);
			model.addAttribute("result", String.valueOf(result));
			
		} else {
			//throw new ServiceException( "MSG.M09.MSG00051" );
			model.addAttribute("result", "-1");
		}
				
	}	

	@RequestMapping(value = "serviceMgtSaleItmListUpdatePopUp", method = RequestMethod.POST)
	public String serviceMgtSaleItmListUpdatePopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
		ServiceMgt getSaleItm = null;
				
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String saleTyp = "PP00188";
		serviceMgt.setCurrentDay(currentDay);	
		
		getSaleItm = serviceMgtservice.getSaleItm(serviceMgt);
		
		model.addAttribute("saleTyp", commonDataService.getCommonCodeList(saleTyp, lngTyp));
		model.addAttribute("rateItmTyp", serviceMgtservice.getRateItmTypComboList(serviceMgt));
		model.addAttribute("saleItmCd", serviceMgt.getSaleItmCd());
		model.addAttribute("getSaleItm", getSaleItm);
		model.addAttribute("svcCd", serviceMgt.getSvcCd());		
		
		return  URL + "/ajax/serviceMgtSaleItmListUpdatePopUp";
	}	

	@RequestMapping(value = "updateServiceMgtSaleItmListUpdate", method = RequestMethod.POST)
	public void updateServiceMgtSaleItmListUpdate (
			ServiceMgt serviceMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {	
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();		
				
		serviceMgt.setCurrentDay(currentDay);
		serviceMgt.setChgrId(usrId);
		serviceMgt.setRegrId(usrId);
		serviceMgt.setSaleItmEngNm(serviceMgt.getSaleItmNm());
		serviceMgt.setSysdate(DateUtil.sysdate());
		serviceMgt.setSaleItmCd(serviceMgt.getSaleItmCd());			
		String result = serviceMgtservice.updateServiceMgtSaleItmListUpdate(serviceMgt);
		
		model.addAttribute("result", String.valueOf(result));
	}	
	
	@RequestMapping(value = "serviceMgtSvcRateItmTypListAction", method = RequestMethod.POST)
	public void serviceMgtSvcRateItmTypListAction(
			ServiceMgt serviceMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
	
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();		
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		serviceMgt.setLngTyp(lngTyp);
		serviceMgt.setTargetCd(request.getParameter("targetCd"));
		
        serviceMgt.setCurrentDay(currentDay);
        
		
		List<ServiceMgt> serviceMgtSvcRateItmTypList = null;
		int count = 0;
		count = serviceMgtservice.getServiceMgtSvcRateItmTypListCount(serviceMgt,soAuthList);
		logger.debug("count : {}", count);
		if (count > 0) { 
			serviceMgtSvcRateItmTypList = serviceMgtservice.getServiceMgtSvcRateItmTypList(serviceMgt, soAuthList, page, perPageRow);
			model.addAttribute("records", serviceMgtSvcRateItmTypList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", serviceMgtSvcRateItmTypList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	}			

	@RequestMapping(value = "serviceMgtSvcRateItmTypListInsertPopUp", method = RequestMethod.POST)
	public String serviceMgtSvcRateItmTypListInsertPopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
			
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String rateLocat = "PP00034";
		serviceMgt.setCurrentDay(currentDay);	
		serviceMgt.setLngTyp(lngTyp);
		
		model.addAttribute("rateItmTyp", serviceMgtservice.getRateItmTypComboListBySvc(serviceMgt));
		model.addAttribute("invItm", serviceMgtservice.getInvItmComboList(serviceMgt));
		model.addAttribute("rateLocat", commonDataService.getCommonCodeList(rateLocat, lngTyp));
		model.addAttribute("svcCd", serviceMgt.getSvcCd());
		model.addAttribute("serviceMgt", serviceMgt);
		
		return  URL + "/ajax/serviceMgtSvcRateItmTypListInsertPopUp";
	}	
	
	
	@RequestMapping(value = "addSvcRateItmTyp", method = RequestMethod.POST)
	public void addSvcRateItmTyp (
			ServiceMgt serviceMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();		
		
		serviceMgt.setRegrId(usrId);
		serviceMgt.setChgrId(usrId);
		serviceMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
        serviceMgt.setCurrentDay(currentDay);
        serviceMgt.setSysdate(DateUtil.sysdate());
		
		String result = serviceMgtservice.addSvcRateItmTyp(serviceMgt);
		
		model.addAttribute("result", result);
		
	}
	
	
	@RequestMapping(value = "getSaleItmComboList", method = RequestMethod.POST)
	public void getSaleItmComboList (
			ServiceMgt serviceMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		List<ServiceMgt> serviceMgtSaleItmComboList = null;
		serviceMgtSaleItmComboList = serviceMgtservice.getSaleItmComboList(serviceMgt);
		
		model.addAttribute("serviceMgtSaleItmComboList", serviceMgtSaleItmComboList);	
		
	}
	
	
	@RequestMapping(value = "getSvcRateItmTypAttrList", method = RequestMethod.POST)	
	public void getSvcRateItmTypAttrList(
			ServiceMgt serviceMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		List<ServiceMgt> rateItmTypAttrList = null;
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		serviceMgt.setLngTyp(lngTyp);
        serviceMgt.setCurrentDay(currentDay);
        serviceMgt.setSvcRateItmTypCd(serviceMgt.getTargetCd());
		
		if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_N)) {
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_31);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_R)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_32);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_U)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_33);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_A)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_41);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_M)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_42);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_D)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_43);
		} else {
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_91);
		}
		
		rateItmTypAttrList = serviceMgtservice.getSvcRateItmTypAttrList(serviceMgt);
		
		
		model.addAttribute("records", rateItmTypAttrList.size()); //현화면의 리스트갯수	
		model.addAttribute("rows", rateItmTypAttrList);
	}
	
	@RequestMapping(value = "getSvcRateItmTypFctrList", method = RequestMethod.POST)	
	public void getSvcRateItmTypFctrList(
			ServiceMgt serviceMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		List<ServiceMgt> rateItmTypFctrList = null;
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		serviceMgt.setLngTyp(lngTyp);		
        serviceMgt.setCurrentDay(currentDay);
        serviceMgt.setSvcRateItmTypCd(serviceMgt.getTargetCd());		
		
		rateItmTypFctrList = serviceMgtservice.getSvcRateItmTypFctrList(serviceMgt);
		
		model.addAttribute("records", rateItmTypFctrList.size());
		model.addAttribute("rows", rateItmTypFctrList);
	}
	
	@RequestMapping(value = "svcRateItmTypAttrListSearchPopUp", method = RequestMethod.POST)
	public String svcRateItmTypAttrListSearchPopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
		ServiceMgt getSaleItm = null;
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();;
		
		if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_N)) {
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_31);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_R)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_32);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_U)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_33);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_A)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_41);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_M)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_42);
		} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_D)){
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_43);
		} else {
			serviceMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_91);
		}		
		
		serviceMgt.setCurrentDay(currentDay);	
		List<ServiceMgt> resultList = serviceMgtservice.getSvcRateItmTypAttrList2(serviceMgt);
	 
/*		Iterator it = resultList.iterator();
		
		while(it.hasNext()) {
			ServiceMgt tmpServiceMgt = (ServiceMgt) it.next();
			String attrVal = tmpServiceMgt.getAttrVal();
			
			if ( attrVal != null){
				ServiceMgt getTableServiceMgt = new ServiceMgt();
				getTableServiceMgt.setRefTableId(tmpServiceMgt.getRefTableId());
				getTableServiceMgt.setRefColmnId(tmpServiceMgt.getRefColmnId());
				getTableServiceMgt.setRefColmnNm(tmpServiceMgt.getRefColmnNm());
				getTableServiceMgt.setAttrVal(tmpServiceMgt.getAttrVal());
				
				Map<String,String> tableData = serviceMgtservice.getTableData(getTableServiceMgt);
				
				if (tableData != null) {
					tmpServiceMgt.setAttrValNm(tableData.get( "COLMN_DATA" ));
				}
			}
		}		
		*/
		Iterator it1 = resultList.iterator();
		List<Map<String,Object>> commonGrp = new ArrayList<Map<String,Object>>();
		
		while(it1.hasNext()) {
			Map<String, Object> refCd = new HashMap<String, Object>();
			ServiceMgt tmpServiceMgt = (ServiceMgt) it1.next();
			if (tmpServiceMgt.getRefCd() != null) {
				refCd.put("refCd", tmpServiceMgt.getRefCd());
				commonGrp.add(refCd);
			}
			
		}	
		
		List<ProductDevMgt> commonGrpList = productDevMgtService.getCommonGrpCdList(commonGrp, lngTyp);
		
		model.addAttribute("commonGrpList", commonGrpList);		
		model.addAttribute("resultList", resultList);
		model.addAttribute("svcRateItmTypCd", serviceMgt.getSvcRateItmTypCd());
		model.addAttribute("soId", serviceMgt.getSoId());
		
		return  URL + "/ajax/svcRateItmTypAttrListSearchPopUp";
	}		

	@RequestMapping(value = "treatSvcRateItmTypAttr", method = RequestMethod.POST)	
	public void treatSvcRateItmTypAttr(
			@RequestParam HashMap<String, String> map
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		
		String result = serviceMgtservice.treatSvcRateItmTypAttr(map, sessionUser);
				
		model.addAttribute("result", result);
	}	
	
	
	@RequestMapping(value = "svcRateItmTypFctrListInsertPopUp", method = RequestMethod.POST)
	public String svcRateItmTypFctrListInsertPopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
		
		model.addAttribute("serviceMgt", serviceMgt);
		return  URL + "/ajax/svcRateItmTypFctrListInsertPopUp";
	}	
	
	@RequestMapping(value = "getTotalFactorListMergedeSvc", method = RequestMethod.POST)	
	public void getTotalFactorListMergedeSvc(
			ServiceMgt serviceMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		List<ServiceMgt> totalFactorList = null;
	
        serviceMgt.setCurrentDay(currentDay);
		
        totalFactorList = serviceMgtservice.getTotalFactorListMergedeSvc(serviceMgt);
		
		model.addAttribute("records", totalFactorList.size());
		model.addAttribute("rows", totalFactorList);
	}	
	
	@RequestMapping(value = "addSvcRateItmTypFctr", method = RequestMethod.POST)
	public void addSvcRateItmTypFctr (
			@RequestBody List<ServiceMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();
		
		int result = -1;
		
		logger.debug("count : {}", params);
		for (int i=0; i<params.size(); i++) {
			String tmpStr = params.get(i).getFctrCd();
			
			params.get(i).setRegrId(usrId);
			params.get(i).setChgrId(usrId);
			params.get(i).setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
			params.get(i).setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
			params.get(i).setSysdate(DateUtil.sysdate());
			
			result = serviceMgtservice.addSvcRateItmTypFctr(params.get(i));
			
			logger.debug("tmpStr : {}", tmpStr);
		}
	
		
		
		model.addAttribute("result", String.valueOf(result));
	}
	
	
	@RequestMapping(value = "serviceMgtSvcRateItmTypListUpdatePopUp", method = RequestMethod.POST)
	public String serviceMgtSvcRateItmTypListUpdatePopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String rateLocat = "PP00034";
		serviceMgt.setCurrentDay(currentDay);	
		serviceMgt.setLngTyp(lngTyp);
		
		model.addAttribute("invItm", serviceMgtservice.getInvItmComboList(serviceMgt));
		model.addAttribute("rateLocat", commonDataService.getCommonCodeList(rateLocat, lngTyp));
		model.addAttribute("serviceMgtInit", serviceMgtservice.getSvcRateItmTyp(serviceMgt));
		model.addAttribute("saleItm", serviceMgtservice.getSaleItmComboList(serviceMgt));
		model.addAttribute("targetMainSvcCd", serviceMgt.getTargetMainSvcCd());
		
		
		return  URL + "/ajax/serviceMgtSvcRateItmTypListUpdatePopUp";
	}		

	@RequestMapping(value = "modSvcRateItmTyp", method = RequestMethod.POST)
	public void modSvcRateItmTyp (
			ServiceMgt serviceMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {	
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();
		
		serviceMgt.setChgrId(usrId);
		serviceMgt.setRegrId(usrId);
		serviceMgt.setCurrentDay(currentDay);
		serviceMgt.setSysdate(DateUtil.sysdate());		
		String result = serviceMgtservice.modSvcRateItmTyp(serviceMgt);
	
		model.addAttribute("result", result);
		
	}
	
	@RequestMapping(value = "svcRateItmTypFctrListUpdatePopUp", method = RequestMethod.POST)
	public String svcRateItmTypFctrListUpdatePopUp(Model model
										, HttpServletRequest request
										, ServiceMgt serviceMgt 
										) throws Exception {
		
		model.addAttribute("svcRateItmTypFctr", serviceMgtservice.getModSvcRateItmTypFctrInit(serviceMgt));
		
		
		return  URL + "/ajax/svcRateItmTypFctrListUpdatePopUp";
	}		
	
	@RequestMapping(value = "modSvcRateItmTypFctr", method = RequestMethod.POST)
	public void modSvcRateItmTypFctr (
			ServiceMgt serviceMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {	
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();
		
		serviceMgt.setChgrId(usrId);
		serviceMgt.setCurrentDay(currentDay);
		serviceMgt.setSysdate(DateUtil.sysdate());		
		String result = serviceMgtservice.modSvcRateItmTypFctr(serviceMgt);
	
		model.addAttribute("result", result);
		
	}	

}
