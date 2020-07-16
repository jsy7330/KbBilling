package com.ntels.ccbs.product.controller.product.productDev;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.ntels.ccbs.product.service.product.productDev.ProductDevMgtService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.StringUtil;


@Controller
@RequestMapping(value = "/product/product/productDev/productDevMgt")
public class ProductDevelopmentManagementController {

	@Autowired
	private ProductDevMgtService productDevMgtService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private SequenceService sequenceSevice;	
	
	private static String URL = "product/product/productDev/productDevMgt";

	List<Map<String,Object>> soAuthList;
	List<ProductDevMgt> productDevMgtTree;
	
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-mm-dd");
	
	//오늘날짜 가져오기
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);	
	
	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String viewInit(	Model model,
			HttpServletRequest request) {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String userSoId = "";
		
		for (int i = 0; i < sessionUser.getSoAuthList().size(); i ++){
			if ( sessionUser.getSoId().equals(sessionUser.getSoAuthList().get(i).get("so_id"))){
				userSoId = sessionUser.getSoAuthList().get(i).get("so_nm") + sessionUser.getSoId();
			}
		}
		
		model.addAttribute("userSoId", userSoId);
		return URL + "/main";
	}
	
    public static String[] getItemArray( String src )
    {

        String[] retVal = null;
        if ( src.length() == 0 )
            return null;

        int nitem = 1;

        for ( int i = 0; i < src.length(); i++ )
            if ( src.charAt( i ) == ',' )
                nitem++;

        retVal = new String[nitem];

        int ep = 0;
        int sp = 0;

        for ( int i = 0; i < nitem; i++ )
        {
            ep = src.indexOf( ",", sp );
            if ( ep == -1 )
                ep = src.length();
            retVal[i] = new String( src.substring( sp, ep ) );
            sp = ep + 1;
        }

        return retVal;
    }	
	
	@RequestMapping(value = "getTreeAction", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object getTreeAction(HttpServletRequest request) {
		
		String sessionLanguage = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();
		
		ArrayList<Object> root = new ArrayList<Object>(); // json data
		Map<String, Object> rootNode = new HashMap<String, Object>();
		Map<String, Boolean> removeNode = new HashMap<String, Boolean>();		
		
		productDevMgtTree = productDevMgtService.getDProdTree(sessionLanguage, soAuthList);
		
		rootNode.put("title", "PRODUCT");
		rootNode.put("targetCd", "PRODUCT");	
		rootNode.put("isFolder", "TRUE");
		rootNode.put("key", "PRODUCT"); 
		rootNode.put("prodTyp", "");
		rootNode.put("prodGrp", "ROOT");
		rootNode.put("children", makeMenu2Json(rootNode, 0, removeNode));
		rootNode.put("soId", "");
		rootNode.put("order", 0);
		
		root.add(rootNode);
		
		return root;
	}
	
	private ArrayList<Object> makeMenu2Json(Map<String, Object> parent, int index, Map<String, Boolean> removeNode) {
		ProductDevMgt productDevMgt = null;
		ArrayList<Object> folder = new ArrayList<Object>();
		String parent_menu_no = "0";
	
		parent_menu_no = parent.get("key").toString();
		int cnt = 0;
		for(int i = index; i < productDevMgtTree.size(); i++) {
			productDevMgt = productDevMgtTree.get(i);
			cnt++;
			
			if (!productDevMgt.getUprTargetCd().equals(parent_menu_no)) {
				continue;
			}
			
			if (removeNode.get(productDevMgt.getTargetCd()) != null) {
				continue;
			}			
			
			if ("TRUE".equals(productDevMgt.getIsFolder())) {
				Map<String, Object> node = new HashMap<String, Object>();
				
				node.put("title", productDevMgt.getTargetNm());	
				node.put("isFolder", productDevMgt.getIsFolder());
				node.put("key", productDevMgt.getTargetCd()); 
				node.put("expand", "true");
				node.put("prodTyp", productDevMgt.getProdTyp());
				node.put("prodGrp", productDevMgt.getProdGrp());
				node.put("soId", productDevMgt.getSoId());
				node.put("treeLevel", productDevMgt.getTreeLevel());
				node.put("prodCd", productDevMgt.getProdCd());
				node.put("svcCd", productDevMgt.getSvcCd());
				node.put("rateItmCd", productDevMgt.getRateItmCd());
				node.put("chrgCtgry", productDevMgt.getChrgCtgry());
				
				ArrayList<Object> tmpList = makeMenu2Json(node, index + 1, removeNode);
				
				if (tmpList.size() > 0) {
					node.put("children", tmpList);
				}

				folder.add(node);

				removeNode.put(productDevMgt.getTargetCd(), Boolean.TRUE);				
				
			} else {
				cnt++;
				Map<String, Object> leaf = new HashMap<String, Object>();
				
				leaf.put("title", productDevMgt.getTargetNm());	
				leaf.put("key", productDevMgt.getTargetCd());
				leaf.put("prodTyp", productDevMgt.getProdTyp());
				leaf.put("prodGrp", productDevMgt.getProdGrp());				
				leaf.put("soId", productDevMgt.getSoId());
				leaf.put("treeLevel", productDevMgt.getTreeLevel());
				leaf.put("prodCd", productDevMgt.getProdCd());
				leaf.put("svcCd", productDevMgt.getSvcCd());
				leaf.put("rateItmCd", productDevMgt.getRateItmCd());
				leaf.put("chrgCtgry", productDevMgt.getChrgCtgry());
				folder.add(leaf);
				removeNode.put(productDevMgt.getTargetCd(), Boolean.TRUE);	
			}	
			
		}
		return folder;		
		
	}
	
	@RequestMapping(value = "productDevMgtProductListAction", method = RequestMethod.POST)
	public void productDevMgtProductListAction(
			ProductDevMgt productDevMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productDevMgt.setLngTyp(lngTyp);
		
		List<ProductDevMgt> productDevMgtProductList = null;
		int count = 0;
		
		count = productDevMgtService.getProductListCount(productDevMgt);
		
		if (count > 0) { 
			productDevMgtProductList = productDevMgtService.getProductList(productDevMgt, page, perPageRow);
			model.addAttribute("records", productDevMgtProductList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", productDevMgtProductList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);		
		
		
	}
	
	@RequestMapping(value = "productDevMgtProductListInsertPopUp", method = RequestMethod.POST)
	public String productDevMgtProductListInsertPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt 
										) throws Exception {
		
		String prodTyp = "PP00068";
		String prodGrp = "PP00141";
		String subsFl = "PP00071";
		String basicProdFl = "PP00069";
		String useMmTyp = "PP00079";
		String svcGrp = "PP00143";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String dispPriNo = productDevMgtService.getNextDispPriNo();
		
		model.addAttribute("prodTyp", commonDataService.getCommonCodeList(prodTyp, lngTyp));
		model.addAttribute("prodGrp", commonDataService.getCommonCodeList(prodGrp, lngTyp));
		model.addAttribute("subsFl", commonDataService.getCommonCodeList(subsFl, lngTyp));
		model.addAttribute("basicProdFl", commonDataService.getCommonCodeList(basicProdFl, lngTyp));
		model.addAttribute("useMmTyp", commonDataService.getCommonCodeList(useMmTyp, lngTyp));
		model.addAttribute("svcGrp", commonDataService.getCommonCodeList(svcGrp, lngTyp));
		model.addAttribute("dispPriNo", dispPriNo);
		model.addAttribute("productDevMgt", productDevMgt);
		
		
		
		
		return  URL + "/ajax/productDevMgtProductListInsertPopUp";		
	}
	
	@Transactional
	@RequestMapping(value = "productDevMgtProductListInsert", method = RequestMethod.POST)
	public void productDevMgtProductListInsert (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		int result = -1;
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();		
		
		List<ProductDevMgt> nmList = productDevMgtService.getDualProductNm(productDevMgt);
		if (nmList.size() == 0) 
		{
			String prodCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMPD_D_PROD, "PD", 10);

			productDevMgt.setProdCd(prodCd);
			productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
	        productDevMgt.setActDt(currentDay);
			productDevMgt.setProdEngNm(productDevMgt.getProdNm());
			productDevMgt.setProdEngAbbrNm(productDevMgt.getProdAbbrNm());
	        productDevMgt.setUsgRatePriNo(productDevMgt.getUsgRatePriNo());
	        productDevMgt.setRegrId(usrId);
	        productDevMgt.setChgrId(usrId);
	        productDevMgt.setSysdate(DateUtil.sysdate());
	        productDevMgt.setCurrentDay(currentDay);
	        productDevMgt.setMstrFl("1");
			result = productDevMgtService.addProduct(productDevMgt);
			
			productDevMgt.setDvlpTyp(Consts.PROD_MGT_CODE.PROD_INSERT);
			productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.DEV_ING);
		
			result = productDevMgtService.addProductDvlpHist(productDevMgt);
			model.addAttribute("result", String.valueOf(result));
		} else {
			model.addAttribute("result", "-1");
		}
		
		
	}
	
	@Transactional
	@RequestMapping(value = "productDevMgtProductListUpdatePopUp", method = RequestMethod.POST)
	public String productDevMgtProductListUpdatePopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt 
										) throws Exception {

		String subsFl = "PP00071";
		String basicProdFl = "PP00069";
		String useMmTyp = "PP00079";
		String svcGrp = "PP00143";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
	
		productDevMgt.setLngTyp(lngTyp);
		
		model.addAttribute("subsFl", commonDataService.getCommonCodeList(subsFl, lngTyp));
		model.addAttribute("basicProdFl", commonDataService.getCommonCodeList(basicProdFl, lngTyp));
		model.addAttribute("useMmTyp", commonDataService.getCommonCodeList(useMmTyp, lngTyp));
		model.addAttribute("svcGrp", commonDataService.getCommonCodeList(svcGrp, lngTyp));		
		model.addAttribute("modProductInit", productDevMgtService.getModProductInit(productDevMgt));
		return  URL + "/ajax/productDevMgtProductListUpdatePopUp";	
	}
	
	

	@RequestMapping(value = "modProduct", method = RequestMethod.POST)
	public void modProduct (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		int result = -1;
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();
		
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());		
		
		
		List<ProductDevMgt> nmList = productDevMgtService.getDualProductNm(productDevMgt);
		if (nmList.size() == 0) 
		{
			result = productDevMgtService.modProduct(productDevMgt);
			
			productDevMgt.setDvlpStatus(Consts.PROD_MGT_CODE.DEV_ING);
			result = productDevMgtService.modProductDvlpHist(productDevMgt);
			model.addAttribute("result", String.valueOf(result));
		} else {
			model.addAttribute("result", "-1");
		}
		
	}
	
	@RequestMapping(value = "delProduct", method = RequestMethod.POST)
	public void delProduct (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();	
		
		productDevMgt.setChgrId(usrId);
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.delProduct(productDevMgt);
		
		model.addAttribute("result", result);
	}
	
	@RequestMapping(value = "productDevMgtProductListCopyPopUp", method = RequestMethod.POST)
	public String productDevMgtProductListCopyPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt 
										) throws Exception {
		
		String prodTyp = "PP00068";
		String prodGrp = "PP00141";
		String subsFl = "PP00071";
		String basicProdFl = "PP00069";
		String useMmTyp = "PP00079";
		String svcGrp = "PP00143";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		String dispPriNo = productDevMgtService.getNextDispPriNo();
		
		model.addAttribute("prodTyp", commonDataService.getCommonCodeList(prodTyp, lngTyp));
		model.addAttribute("prodGrp", commonDataService.getCommonCodeList(prodGrp, lngTyp));
		model.addAttribute("subsFl", commonDataService.getCommonCodeList(subsFl, lngTyp));
		model.addAttribute("basicProdFl", commonDataService.getCommonCodeList(basicProdFl, lngTyp));
		model.addAttribute("useMmTyp", commonDataService.getCommonCodeList(useMmTyp, lngTyp));
		model.addAttribute("svcGrp", commonDataService.getCommonCodeList(svcGrp, lngTyp));
		model.addAttribute("dispPriNo", dispPriNo);
		model.addAttribute("productDevMgt", productDevMgt);
		return  URL + "/ajax/productDevMgtProductListCopyPopUp";		
	}
	
	@RequestMapping(value = "addCopyProduct", method = RequestMethod.POST)
	public void addCopyProduct (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();	
		
		productDevMgt.setChgrId(usrId);
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setSysdate(DateUtil.sysdate());
        productDevMgt.setActDt(currentDay);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl("1");
        productDevMgt.setRegrId(usrId);
			
		String result = productDevMgtService.addCopyProduct(productDevMgt);
		
		model.addAttribute("result", result);
	}	
	
	@RequestMapping(value = "getProductRelComboListPopUp", method = RequestMethod.POST)
	public String getProductRelComboListPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt
										) throws Exception {

		return  URL + "/ajax/getProductRelComboListPopUp";
	}	
		
	@RequestMapping(value = "getCopyProductList", method = RequestMethod.POST)	
	public void getCopyProductList(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();		
		productDevMgt.setLngTyp(lngTyp);	
		productDevMgt.setCurrentDay(currentDay);
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();		
		
		List<ProductDevMgt> commonGrpList = productDevMgtService.getCopyProductList(productDevMgt, soAuthList);
		
		model.addAttribute("rows", commonGrpList);
	}
	
	
	@RequestMapping(value = "getEqtClCdComboListPopUp", method = RequestMethod.POST)
	public String getEqtClCdComboListPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {

		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String soId = sessionUser.getSoId();
		
		model.addAttribute("userSoId", soId);
		
		return  URL + "/ajax/getEqtClCdComboListPopUp";
	}		
	
	
	@RequestMapping(value = "getEqtClCdComboList", method = RequestMethod.POST)	
	public void getEqtClCdComboList(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
				
		List<ProductDevMgt> getEqtClCdComboList = productDevMgtService.getEqtClCdComboList(productDevMgt);
		
		model.addAttribute("rows", getEqtClCdComboList);
	}	

	@RequestMapping(value = "getProductUpdateExtractListPopUp", method = RequestMethod.POST)
	public String getProductUpdateExtractListPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		return  URL + "/ajax/getProductUpdateExtractListPopUp";
	}			
	
	@RequestMapping(value = "getProductUpdateExtractList", method = RequestMethod.POST)	
	public void getProductUpdateExtractList(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
				
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();		
		productDevMgt.setLngTyp(lngTyp);	
		productDevMgt.setCurrentDay(currentDay);
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();		
		
		List<ProductDevMgt> getProductUpdateExtractList = productDevMgtService.getProductUpdateExtractList(productDevMgt, soAuthList);
		
		model.addAttribute("rows", getProductUpdateExtractList);
	}		
	
	@RequestMapping(value = "addExtractProduct", method = RequestMethod.POST)
	public void addExtractProduct (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();	
		
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String inactDt = "9999-12-31";	
		
		
		try {
			Date to = transFormat.parse(inactDt);
			productDevMgt.setInactDate(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		String result = productDevMgtService.addExtractProduct(productDevMgt);
		
		model.addAttribute("result", result);
	}		

	@RequestMapping(value = "getProductServiceList", method = RequestMethod.POST)
	public void getProductServiceList(
			ProductDevMgt productDevMgt
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		
		List<ProductDevMgt> productServiceList = null;
		int count = 0;
		
		count = productDevMgtService.getProductServiceListCount(productDevMgt);
		
		if (count > 0) { 
			productServiceList = productDevMgtService.getProductServiceList(productDevMgt, page, perPageRow);
			model.addAttribute("records", productServiceList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", productServiceList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);		
		
		
	}	

	@RequestMapping(value = "getAddProductServiceInitPopUp", method = RequestMethod.POST)
	public String getAddProductServiceInitPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		model.addAttribute("productDevMgt", productDevMgt);
		
		return  URL + "/ajax/getAddProductServiceInitPopUp";
	}			
		
	@RequestMapping(value = "getAddProductServiceInit", method = RequestMethod.POST)	
	public void getAddProductServiceInit(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {		
		
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setSoId(productDevMgt.getSoId());
		List<ProductDevMgt> productServiceInit = productDevMgtService.getAddProductServiceInit(productDevMgt);
		
		model.addAttribute("rows", productServiceInit);
	}	

	@RequestMapping(value = "addProductService", method = RequestMethod.POST)
	public void addProductService (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.addProductService(productDevMgt);
		model.addAttribute("result", result);
	}
	
	@RequestMapping(value = "getModProductServiceInitPopUp", method = RequestMethod.POST)
	public String getModProductServiceInitPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String subscripCmpsFl = "PP00021";
		String essFl = "PP00076";
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("modProductService", productDevMgtService.getModProductServiceInit(productDevMgt));
		model.addAttribute("subscripCmpsFl", commonDataService.getCommonCodeList(subscripCmpsFl, lngTyp));
		model.addAttribute("essFl", commonDataService.getCommonCodeList(essFl, lngTyp));
		
		return  URL + "/ajax/getModProductServiceInitPopUp";
	}		
	
	@RequestMapping(value = "modProductService", method = RequestMethod.POST)	
	public void modProductService(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {		
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.modProductService(productDevMgt);
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "delProductService", method = RequestMethod.POST)	
	public void delProductService(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {		
		
		
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.delProductService(productDevMgt);
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "getSuprtEquipList", method = RequestMethod.POST)	
	public void getSuprtEquipList(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {		
		
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
		int count = 0;
       
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setCurrentDay(currentDay);
		
		count = productDevMgtService.getSuprtEquipListCount(productDevMgt);
		
		List<ProductDevMgt> suprtEquipList = null;
		
		if (count > 0) { 
			suprtEquipList = productDevMgtService.getSuprtEquipList(productDevMgt, page, perPageRow);
			model.addAttribute("records", suprtEquipList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", suprtEquipList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	}		

	@RequestMapping(value = "addSuprtEquipPopUp", method = RequestMethod.POST)
	public String addSuprtEquipPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		productDevMgt.setCurrentDay(currentDay);

		model.addAttribute("eqtClCdCombo", productDevMgtService.getEqtClCdComboList2(productDevMgt));
		model.addAttribute("sbjProdCdCombo", productDevMgtService.getSbjProdCdComboList(productDevMgt));
		model.addAttribute("nextDispPriNo", productDevMgtService.getNextDispPriNo2(productDevMgt));
		
		return  URL + "/ajax/addSuprtEquipPopUp";
	}		
	
	@RequestMapping(value = "getSbjSvcCdComboList", method = RequestMethod.POST)
	public void getSbjSvcCdComboList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		productDevMgt.setCurrentDay(currentDay);
		
		 model.addAttribute("sbjSvcCdComboList", productDevMgtService.getSbjSvcCdComboList(productDevMgt));		 
	}		
	
	@RequestMapping(value = "addSuprtEquip", method = RequestMethod.POST)
	public void addSuprtEquip (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.addSuprtEquip(productDevMgt);
		model.addAttribute("result", result);
	}
	
	@RequestMapping(value = "modSuprtEquipInitPopUp", method = RequestMethod.POST)
	public String getModSuprtEquipInitPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		productDevMgt.setCurrentDay(currentDay);

		model.addAttribute("sbjProdCdCombo", productDevMgtService.getSbjProdCdComboList(productDevMgt));
		model.addAttribute("suprtEquip", productDevMgtService.getSuprtEquip(productDevMgt));
		model.addAttribute("productDevMgt", productDevMgt);
		
		return  URL + "/ajax/modSuprtEquipInitPopUp";
	}		

	@RequestMapping(value = "modSuprtEquip", method = RequestMethod.POST)
	public void modSuprtEquip (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.modSuprtEquip(productDevMgt);
		model.addAttribute("result", result);
	}	
	
	@RequestMapping(value = "delSuprtEquip", method = RequestMethod.POST)	
	public void delSuprtEquip(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {		
				
		String result = productDevMgtService.delSuprtEquip(productDevMgt);
		model.addAttribute("result", result);
	}		

	@RequestMapping(value = "getProductAttrList", method = RequestMethod.POST)
	public void getProductAttrList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = 0;
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));		
		
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
		
		if (productDevMgt.getProdTyp().equals(Consts.PROD_MGT_CODE.PROD_TYP_PROMOTION)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_PROMOTION);
		} else {
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_PRODUCT);
		}
		
		count = productDevMgtService.getProductAttrListCount(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getProductAttrList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}			
	
	@RequestMapping(value = "productAttrListPopUp", method = RequestMethod.POST)
	public String productAttrListPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();;
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
		
		if (productDevMgt.getProdTyp().equals(Consts.PROD_MGT_CODE.PROD_TYP_PROMOTION)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_PROMOTION);
		} else {
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_PRODUCT);
		}		
		
		List<ProductDevMgt> resultList = productDevMgtService.getProductAttrList2(productDevMgt);
		
		Iterator it = resultList.iterator();
		List<Map<String,Object>> commonGrp = new ArrayList<Map<String,Object>>();
		
		while(it.hasNext()) {
			Map<String, Object> refCd = new HashMap<String, Object>();
			ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();
			if (tmpProductDevMgt.getRefCd() != null) {
				refCd.put("refCd", tmpProductDevMgt.getRefCd());
				commonGrp.add(refCd);
			}
			
		}	
		System.out.println("commonGrp=================>"+commonGrp);
		System.out.println("commonGrp.size()=================>"+commonGrp.size());
		List<ProductDevMgt> commonGrpList = null;
		if(commonGrp.size() > 0 ){
			commonGrpList = productDevMgtService.getCommonGrpCdList(commonGrp, lngTyp);			
		}else{
			commonGrpList = null;
		}
		
		model.addAttribute("commonGrpList", commonGrpList);
		model.addAttribute("resultList", resultList);
		model.addAttribute("prodCd", productDevMgt.getProdCd());
		
		return  URL + "/ajax/productAttrListPopUp";
	}	
	
	@RequestMapping(value = "treatProdAttrDvlp", method = RequestMethod.POST)	
	public void treatProdAttrDvlp(
			@RequestParam HashMap<String, String> map
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		ProductDevMgt productDevMgt = new ProductDevMgt();

		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();	
		
		String prodCd = map.get("prodCd");
		productDevMgt.setProdCd(prodCd);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		int result = -1;
		
		while (it.hasNext()) {
			Map.Entry<String, String> e = (Map.Entry<String, String>)it.next();
			
			if (!e.getKey().equals("prodCd")) {
				productDevMgt.setAttrCd(e.getKey());
				productDevMgt.setAttrVal(e.getValue());
				
				int attrCnt = productDevMgtService.getProductAttrCnt(productDevMgt);
	
				if (attrCnt == 0) {
					result = productDevMgtService.addProductAttribute(productDevMgt);
				} else {
					result = productDevMgtService.modProductAttribute(productDevMgt);
				}
			}
		}		
		
		
		model.addAttribute("result", String.valueOf(result));
	}
	
	@RequestMapping(value = "getProductRelationshipList", method = RequestMethod.POST)
	public void getProductRelationshipList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = 0;
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));		
		
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
		
		count = productDevMgtService.getProductRelationshipListCount(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getProductRelationshipList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}			
	
	@RequestMapping(value = "addProductRelationshipPopUp", method = RequestMethod.POST)
	public String addProductRelationshipPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String prodRelTyp = "PP00067";

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("prodRelTyp", commonDataService.getCommonCodeList(prodRelTyp, lngTyp));
		
		return  URL + "/ajax/addProductRelationshipPopUp";
	}		
	
	
	@RequestMapping(value = "getProductRelComboList", method = RequestMethod.POST)
	public void getProductRelComboList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		if (productDevMgt.getProdRelTyp().equals(Consts.PROD_MGT_CODE.PROD_REL_TYP_EXCLUSION)) {
			productDevMgt.setProdTyp(Consts.PROD_MGT_CODE.PROD_TYP_LIKE_ADDITION + "%");
		} else if (productDevMgt.getProdRelTyp().equals(Consts.PROD_MGT_CODE.PROD_REL_TYP_DEPENDENCY)){
			productDevMgt.setProdTyp(Consts.PROD_MGT_CODE.PROD_TYP_LIKE_BASIC + "%");
		} else if (productDevMgt.getProdRelTyp().equals(Consts.PROD_MGT_CODE.PROD_REL_TYP_CONVERSION)){
			productDevMgt.setProdTyp(Consts.PROD_MGT_CODE.PROD_TYP_LIKE_BASIC + "%");
			productDevMgt.setSubsFl("1");
		} else {
			productDevMgt.setProdTyp("%");
		}
		
		List<ProductDevMgt> resultList = productDevMgtService.getProductRelComboList(productDevMgt);
		
		model.addAttribute("rows", resultList);	// 목록리스트
		
	}

	@RequestMapping(value = "addProductRelationship", method = RequestMethod.POST)
	public void addProductRelationship (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.addProductRelationship(productDevMgt);
		model.addAttribute("result", result);
	}	

	@RequestMapping(value = "delProductRelationship", method = RequestMethod.POST)	
	public void delProductRelationship(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {		
				
		String result = productDevMgtService.delProductRelationship(productDevMgt);
		model.addAttribute("result", result);
	}	
	
	@RequestMapping(value = "getRateItmList", method = RequestMethod.POST)
	public void getRateItmList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = 0;
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));		
		
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
		
		count = productDevMgtService.getRateItmListCount(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getRateItmList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}	
	
	@RequestMapping(value = "addRetrieveRateItm", method = RequestMethod.POST)
	public void addRetrieveRateItm (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.addRetrieveRateItm(productDevMgt);
		model.addAttribute("result", result);
	}		

	@RequestMapping(value = "addRateItmInitPopup", method = RequestMethod.POST)
	public String addRateItmInitPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String rateLocat = "PP00034";
		String rateRefTyp = "PP00032";

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productDevMgt.setLngTyp(lngTyp);
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("rateLocat", commonDataService.getCommonCodeList(rateLocat, lngTyp));
		model.addAttribute("rateRefTyp", commonDataService.getCommonCodeList(rateRefTyp, lngTyp));
		model.addAttribute("svcRateItmTyp", productDevMgtService.getAddRateItmInit(productDevMgt));
		
		return  URL + "/ajax/addRateItmInitPopup";
	}	
	
	@RequestMapping(value = "getDefltRateItemList", method = RequestMethod.POST)
	public void getDefltRateItemList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		productDevMgt.setCurrentDay(currentDay);
		
		List<ProductDevMgt> defltRateItemList = productDevMgtService.getDefltRateItemList(productDevMgt);
		
		model.addAttribute("defltRateItemList", defltRateItemList);	// 목록리스트
		
	}	

	@RequestMapping(value = "addRateItm", method = RequestMethod.POST)
	public void addRateItm (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		String result = "";
		
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		List<ProductDevMgt> nmList = productDevMgtService.getDualRateItmNm(productDevMgt);
		
		if (nmList.size() == 0)  {
			result = productDevMgtService.addRateItm(productDevMgt);
		} else {
			result = "-1";
		}

		model.addAttribute("result", result);
	}	
	
	@RequestMapping(value = "modRateItmPopup", method = RequestMethod.POST)
	public String modRateItmPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String rateLocat = "PP00034";
		String rateRefTyp = "PP00032";

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("rateLocat", commonDataService.getCommonCodeList(rateLocat, lngTyp));
		model.addAttribute("rateRefTyp", commonDataService.getCommonCodeList(rateRefTyp, lngTyp));
		model.addAttribute("rateItm", productDevMgtService.getRateItm(productDevMgt));
		model.addAttribute("defltRateItmList", productDevMgtService.getDefltRateItemList(productDevMgt));
		
		return  URL + "/ajax/modRateItmPopup";
	}	
	
	@RequestMapping(value = "modRateItm", method = RequestMethod.POST)
	public void modRateItm (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.modRateItm(productDevMgt);
		model.addAttribute("result", result);
	}	

	@RequestMapping(value = "delRateItm", method = RequestMethod.POST)
	public void delRateItm (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.delRateItm(productDevMgt);
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "getRateItmFctrList", method = RequestMethod.POST)
	public void getRateItmFctrList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = 0;
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));		
		
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
		
		count = productDevMgtService.getRateItmFctrListCount(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getRateItmFctrList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}		

	@RequestMapping(value = "getRateItmRegCnt", method = RequestMethod.POST)
	public void getRateItmRegCnt(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		List<ProductDevMgt> resultList = productDevMgtService.getRateItmRegCnt(productDevMgt);
		
		if (resultList.size() > 0) {
			Iterator it = resultList.iterator();
			
			while(it.hasNext()) {
				ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();

				if (tmpProductDevMgt.getFctrCnt().equals("-1")) {
					tmpProductDevMgt.setFctrCnt("Not Registered");
				}
				
				if (tmpProductDevMgt.getAttrCnt().equals("-1")) {
					tmpProductDevMgt.setAttrCnt("Not Registered");
				}
				
				if (tmpProductDevMgt.getCondCnt().equals("-1")) {
					tmpProductDevMgt.setCondCnt("Not Registered");
				}	
				
				if (tmpProductDevMgt.getRateCnt().equals("-1")) {
					tmpProductDevMgt.setRateCnt("Not Registered");
				}	
				
				if (tmpProductDevMgt.getDiscExclCnt().equals("-1")) {
					tmpProductDevMgt.setDiscExclCnt("Not Registered");
				}	
				
				if (tmpProductDevMgt.getDiscSvcRateItmTypCnt().equals("-1")) {
					tmpProductDevMgt.setDiscSvcRateItmTypCnt("Not Registered");
				}	
				
				if (tmpProductDevMgt.getDiscPerdCnt().equals("-1")) {
					tmpProductDevMgt.setDiscPerdCnt("Not Registered");
				}				
				
			}
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		
	}	
	
	@RequestMapping(value = "retrieveRateItemFactorPopup", method = RequestMethod.POST)
	public String retrieveRateItemFactorPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		model.addAttribute("productDevMgt", productDevMgt);
		
		return  URL + "/ajax/retrieveRateItemFactorPopup";
	}	
	
	@RequestMapping(value = "getRetrieveRateItemFactor", method = RequestMethod.POST)	
	public void getRetrieveRateItemFactor(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
			
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setSysdate(DateUtil.sysdate());		
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);			
		
		List<ProductDevMgt> commonGrpList = productDevMgtService.getRetrieveRateItemFactor(productDevMgt);
		
		model.addAttribute("rows", commonGrpList);
	}
	
	@RequestMapping(value = "addRetrieveRateItemFactor_2", method = RequestMethod.POST)
	public void addRetrieveRateItemFactor_2 (
			@RequestBody List<ProductDevMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();
		
		int result = -1;
		int fctrNo = 0;
		
		
		for (int i=0; i<params.size(); i++) {
			if (params.get(i).getFctrCd().equals("FT00000002")) {
				params.get(i).setFctrNo("9");	
			} else {
				//테이블이 없어 잠시 막음
				fctrNo = productDevMgtService.getRetrieveRateItemFactorNumber(params.get(i).getRateItmCd());
				params.get(i).setFctrNo(String.valueOf(fctrNo));
				//params.get(i).setFctrNo("9");
			}
			
			if (fctrNo == 10) {
				result = -2;
			} else {
				params.get(i).setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
				params.get(i).setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
				params.get(i).setCurrentDay(currentDay);
				params.get(i).setRegrId(usrId);
				params.get(i).setChgrId(usrId);	
				result = productDevMgtService.addRetrieveRateItemFactor_2(params.get(i));
			}
		}
	
		
		
		model.addAttribute("result", String.valueOf(result));
	}	
	
	@RequestMapping(value = "rateItmFctrListUpdateBtnPopup", method = RequestMethod.POST)
	public String rateItmFctrListUpdateBtnPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String rateInfoExeFl = "PP00014";

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("rateInfoExeFl", commonDataService.getCommonCodeList(rateInfoExeFl, lngTyp));
		model.addAttribute("rateItmFctr", productDevMgtService.getModRateItmFctrInit(productDevMgt));
		
		return  URL + "/ajax/modRateItmFctrInitPopup";
	}		
	
	@RequestMapping(value = "modRateItmFctr", method = RequestMethod.POST)
	public void modRateItmFctr (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		String result = productDevMgtService.modRateItmFctr(productDevMgt);
		model.addAttribute("result", result);
	}
	
	@RequestMapping(value = "delRateItmFctr", method = RequestMethod.POST)
	public void delRateItmFctr (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
				
		String result = productDevMgtService.delRateItmFctr(productDevMgt);
		model.addAttribute("result", result);
	}	

	@RequestMapping(value = "getRateItmAttrList", method = RequestMethod.POST)
	public void getRateItmAttrList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = -1;
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));		
		
		if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_N)) {
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_31);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_R)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_32);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_U)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_33);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_A)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_41);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_M)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_42);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_D)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_43);
		} else {
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_91);
		}	        
		
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
		
		count = productDevMgtService.getRateItmAttrListCount(productDevMgt);
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getRateItmAttrList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}		
	
	@RequestMapping(value = "addRetrieveRateItemAttribute", method = RequestMethod.POST)
	public void addRetrieveRateItemAttribute (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			

		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setRateRefTyp(Consts.PROD_MGT_CODE.RATE_REF_TYP);
		
		String result = productDevMgtService.addRetrieveRateItemAttribute(productDevMgt);
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "rateItmAttrListPopUp", method = RequestMethod.POST)
	public String rateItmAttrListPopUp(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();;
		productDevMgt.setLngTyp(lngTyp);		
		productDevMgt.setCurrentDay(currentDay);
			
		if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_N)) {
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_31);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_R)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_32);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_U)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_33);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_A)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_41);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_M)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_42);
		} else if (productDevMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_D)){
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_43);
		} else {
			productDevMgt.setAttrTyp(Consts.PROD_MGT_CODE.ATTR_TYP_91);
		}		
		
		List<ProductDevMgt> resultList = productDevMgtService.getRateItmAttrList2(productDevMgt);
		
		Iterator it = resultList.iterator();
		List<Map<String,Object>> commonGrp = new ArrayList<Map<String,Object>>();

		int cnt = 0;
		while(it.hasNext()) {
			Map<String, Object> refCd = new HashMap<String, Object>();
			ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();	
			if (tmpProductDevMgt.getRefCd() != null) {
				refCd.put("refCd", tmpProductDevMgt.getRefCd());
				commonGrp.add(refCd);
			}	
		}	
		
		List<ProductDevMgt> commonGrpList = productDevMgtService.getCommonGrpCdList(commonGrp, lngTyp);
		
		model.addAttribute("commonGrpList", commonGrpList);
		model.addAttribute("resultList", resultList);
		model.addAttribute("rateItmCd", productDevMgt.getRateItmCd());
		
		return  URL + "/ajax/rateItmAttrListPopUp";
	}		
	
	@RequestMapping(value = "treatRateItmAttr", method = RequestMethod.POST)	
	public void treatRateItmAttr(
			@RequestParam HashMap<String, String> map
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		ProductDevMgt productDevMgt = new ProductDevMgt();

		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();	
		
		String rateItmCd = map.get("rateItmCd");
		productDevMgt.setRateItmCd(rateItmCd);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		int result = -1;
		
		while (it.hasNext()) {
			Map.Entry<String, String> e = (Map.Entry<String, String>)it.next();
			
			if (!e.getKey().equals("rateItmCd")) {
				productDevMgt.setAttrCd(e.getKey());
				productDevMgt.setAttrVal(e.getValue());
				
				int attrCnt = productDevMgtService.getRateItmAttributeCnt(productDevMgt);
	
				if (attrCnt == 0) {
					result = productDevMgtService.addRateItmAttribute(productDevMgt);
				} else {
					result = productDevMgtService.modRateItmAttribute(productDevMgt);
				}
			}
		}		
		
		
		model.addAttribute("result", String.valueOf(result));
	}
	
	@RequestMapping(value = "getRateItmCondList", method = RequestMethod.POST)
	public void getRateItmCondList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = -1;
	
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));		
		
     		
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
		
		count = productDevMgtService.getRateItmCondListCount(productDevMgt);
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getRateItmCondList(productDevMgt, page, perPageRow);
	        Iterator it = resultList.iterator();

	        while ( it.hasNext() ) {
	        	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();	
	        	tmpProductDevMgt.setLngTyp(lngTyp);
	            //고정값 1 처리
	            List<ProductDevMgt> oprnd1DataList = oprndDataList2( 1, tmpProductDevMgt );
	            if ( oprnd1DataList != null ){
	                if ( !oprnd1DataList.isEmpty() ){
	                    String oprnd1Nm = oprndDataNm2( oprnd1DataList );
	                    System.out.println("oprnd1Nm==>"+oprnd1Nm);
	                    System.out.println("oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 )==>"+oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 ));
	                    tmpProductDevMgt.setOprnd1Nm(oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 ));
	                    //resultMap.put( "OPRND_1_NM", oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 ) );
	            		
	                }
	            }
	            //고정값 2 처리
	            if(tmpProductDevMgt.getOprnd2() != null){
	            	System.out.println("getCondOperator============>"+tmpProductDevMgt.getCondOperator());
	            	System.out.println("getOprnd2============>"+tmpProductDevMgt.getOprnd2());	   
	            	System.out.println("Length============>"+tmpProductDevMgt.getOprnd2().length());
	            	if(tmpProductDevMgt.getOprnd2().length()!=0){
	            		System.out.println("A============>");
	                    List<ProductDevMgt> oprnd2DataList = oprndDataList2( 2, tmpProductDevMgt );
	                    System.out.println("B============>");
			            //System.out.println(oprnd2DataList.isEmpty());
			            if ( oprnd2DataList != null ){
			                if ( !oprnd2DataList.isEmpty() ){
			                    String oprnd2Nm = oprndDataNm2( oprnd2DataList );
			                    tmpProductDevMgt.setOprnd1Nm(oprnd2Nm.substring( 0, oprnd2Nm.length() - 2 ));
			                    //resultMap.put( "OPRND_2_NM", oprnd2Nm.substring( 0, oprnd2Nm.length() - 2 ) );
			                }
			            }	
	            	}
	            }
	        }
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		

		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}		
	
    public List<ProductDevMgt> oprndDataList( int oprndVal, ProductDevMgt productDevMgt )
    {
        String dataTyp = productDevMgt.getDataTyp();
        String oprnd = "";
        if ( oprndVal == 1 )
        {
            //oprnd = (String) resultMap.get( "OPRND_1" );
            oprnd = productDevMgt.getOprnd1();
        }
        else
        {
            oprnd = productDevMgt.getOprnd2();
        }
        List<ProductDevMgt> resultList = null;
        if ( oprnd != null )
        {
            if ( dataTyp.equals( "4" ) )
            {
                Map paramMap = new HashMap();
                paramMap.put( "REF_TABLE_ID", "TSYCO_CODE_DETAIL_NAME" );
                paramMap.put( "REF_COLMN_ID", "CODE_NM" );
                //paramMap.put( "REF_COLMN_NM", "COMMON_CD_NM" );
                String[] retVal = getItemArray( oprnd );
                oprnd = "";
                for ( int i = 0; i < retVal.length; i++ )
                {
                    oprnd += "'" + (String) retVal[i] + "',";
                }
                oprnd += "''";
                String grpStr = "COMMON_GRP = '" + (String) productDevMgt.getRefCd() + "'";
                grpStr += " AND COMMON_CD IN (" + oprnd + ")";
                paramMap.put( "REF_TABLE_COND", grpStr );
                resultList = productDevMgtService.getTableDataList(paramMap);
                //resultList = generalDao.queryForList( "pm.pd.cm.getTableDataList", paramMap );
            }
            if ( dataTyp.equals( "6" ) )
            {
                Map paramMap = new HashMap();
                paramMap.put( "REF_TABLE_ID", productDevMgt.getRefTableId() );
                paramMap.put( "REF_COLMN_ID", productDevMgt.getRefColmnId() );
                //paramMap.put( "REF_COLMN_NM", productDevMgt.getRefColmnNM() );
   
                String[] retVal = getItemArray( oprnd );
                oprnd = "";
                for ( int i = 0; i < retVal.length; i++ )
                {
                    oprnd += "'" + (String) retVal[i] + "',";
                }
                oprnd += "''";
                paramMap.put( "REF_TABLE_COND", productDevMgt.getRefColmnId() + " IN (" + oprnd + ")" );
                //resultList = generalDao.queryForList( "pm.pd.cm.getTableDataList", paramMap );
                resultList = productDevMgtService.getTableDataList(paramMap);
            }
        }
        return resultList;
    }	
    public List<ProductDevMgt> oprndDataList2( int oprndVal, ProductDevMgt productDevMgt )
    {
        String dataTyp = productDevMgt.getDataTyp();
        String lngTyp = productDevMgt.getLngTyp();
        String oprnd = "";
        if ( oprndVal == 1 )
        {
            //oprnd = (String) resultMap.get( "OPRND_1" );
            oprnd = productDevMgt.getOprnd1();
        }
        else
        {
            oprnd = productDevMgt.getOprnd2();
        }
  System.out.println("oprnd====================>"+oprnd);
        List<ProductDevMgt> resultList = null;
        if ( oprnd == null || StringUtils.isEmpty(oprnd)){
        	
        }else{
       	 if(dataTyp !=null){
	            if ( dataTyp.equals( "4" ) )
	            {
	            	System.out.println("2============>");
	                Map paramMap = new HashMap();
	                paramMap.put( "REF_TABLE_ID", "TSYCO_CODE_DETAIL_NAME" );
	                paramMap.put( "REF_COLMN_ID", "CODE_NM" );
	                //paramMap.put( "REF_COLMN_NM", "COMMON_CD_NM" );
	                String[] retVal = getItemArray( oprnd );
	                oprnd = "";
	                System.out.println("3============>");
	                for ( int i = 0; i < retVal.length; i++ )
	                {
	                    oprnd += "'" + (String) retVal[i] + "',";
	                }

	                oprnd += "''";
	                String grpStr = "COMMON_GRP = '" + (String) productDevMgt.getRefCd() + "'";
	     
	                grpStr += " AND COMMON_CD IN (" + oprnd + ")";
	    
	                paramMap.put( "REF_TABLE_COND", grpStr );
	
	                paramMap.put( "LNG_TYP", lngTyp );    
	                System.out.println("4============>");
	                resultList = productDevMgtService.getTableDataList(paramMap);
	                //resultList = generalDao.queryForList( "pm.pd.cm.getTableDataList", paramMap );
	
	                System.out.println("5============>");
	            }
	            if ( dataTyp.equals( "6" ) )
	            {
	            	System.out.println("A1============>");
	                Map paramMap = new HashMap();
	                paramMap.put( "REF_TABLE_ID", productDevMgt.getRefTableId() );
	                paramMap.put( "REF_COLMN_ID", productDevMgt.getRefColmnId() );
	                //paramMap.put( "REF_COLMN_NM", productDevMgt.getRefColmnNM() );
	   
	                String[] retVal = getItemArray( oprnd );
	                oprnd = "";
	                System.out.println("A2============>");
	                System.out.println("retVal============>"+retVal);
	                for ( int i = 0; i < retVal.length; i++ )
	                {
	                	System.out.println("A3============>"+retVal[i]);
	                    oprnd += "'" + (String) retVal[i] + "',";
	                }

	                oprnd += "''";
	                System.out.println("A4============>");
	                paramMap.put( "REF_TABLE_COND", productDevMgt.getRefColmnId() + " IN (" + oprnd + ")" );
	                //resultList = generalDao.queryForList( "pm.pd.cm.getTableDataList", paramMap );
	                System.out.println("A5============>");
	                resultList = productDevMgtService.getTableDataList(paramMap);

	            }
     	 }  	
        }
        return resultList;
    }	
    public String oprndDataNm( List<ProductDevMgt> tableData )
    {
        String oprndNm = "";
        Iterator dataIt = tableData.iterator();
        while ( dataIt.hasNext() )
        {
        	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) dataIt.next();	
            //Map dataMap = (Map) dataIt.next();
        	oprndNm = oprndNm + tmpProductDevMgt.getCommonCdNm() + ", ";
            //oprndNm = oprndNm + (String) dataMap.get( "COMMON_CD_NM" ) + ", ";
        }
        return oprndNm;
    }	
    public String oprndDataNm2( List<ProductDevMgt> tableData )
    {
        String oprndNm = "";
        Iterator dataIt = tableData.iterator();
        while ( dataIt.hasNext() )
        {
        	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) dataIt.next();	
            //Map dataMap = (Map) dataIt.next();
        	oprndNm = oprndNm + tmpProductDevMgt.getCommonCd() + ", ";
            //oprndNm = oprndNm + (String) dataMap.get( "COMMON_CD_NM" ) + ", ";
        }
        return oprndNm;
    }	
	@RequestMapping(value = "addRateItmCondPopup", method = RequestMethod.POST)
	public String addRateItmCondPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String condOperator = "PP00015";
		String oprndRefFl = "PP00059";

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setProcFl("ADD_PROC");
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("condOperator", commonDataService.getCommonCodeList(condOperator, lngTyp));
		model.addAttribute("oprndRefFl", commonDataService.getCommonCodeList(oprndRefFl, lngTyp));		
		model.addAttribute("fctr", productDevMgtService.getAddRateItmCondInit(productDevMgt));
		
		return  URL + "/ajax/addRateItmCondPopup";
	}	    
 
	@RequestMapping(value = "addRateItmCond", method = RequestMethod.POST)
	public void addRateItmCond (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			

		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);	
		
		int condNo = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMPD_RATE_ITM_COND);	
		productDevMgt.setCondNo1(condNo);
		String result = productDevMgtService.addRateItmCond(productDevMgt);
		model.addAttribute("result", result);
	}		

	@RequestMapping(value = "modRateItmCondPopup", method = RequestMethod.POST)
	public String modRateItmCondPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String condOperator = "PP00015";
		String oprndRefFl = "PP00059";

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
		
		
		ProductDevMgt result = productDevMgtService.getRateItmCond(productDevMgt);
		System.out.println("getDataTyp===============>"+result.getDataTyp());
        //List resultList = generalDao.queryForList( "pm.pd.rc.getRateItmCond", map );
        //복수 고정값일 경우 복수 고정값 명칭 조회
        //Iterator it = resultList.iterator();
		result.setLngTyp(lngTyp);
		
    	List<ProductDevMgt> oprnd1DataList = oprndDataList2( 1, result );
        if ( oprnd1DataList != null )
        {
        	String oprnd1Nm = oprndDataNm2( oprnd1DataList );
        	result.setOprnd1Nm(oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 ));
        	System.out.println("==================>"+oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 ));
        }
        //고정값 2 처리
        List<ProductDevMgt> oprnd2DataList = oprndDataList2( 2, result );
        if ( oprnd2DataList != null )
        {
        	String oprnd2Nm = oprndDataNm2( oprnd2DataList );
        	result.setOprnd1Nm(oprnd2Nm.substring( 0, oprnd2Nm.length() - 2 ));
        }	
		
/*        while ( it.hasNext() )
        {
        	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();
            //고정값 1 처리
        	List<ProductDevMgt> oprnd1DataList = oprndDataList( 1, tmpProductDevMgt );
            if ( oprnd1DataList != null )
            {
            	String oprnd1Nm = oprndDataNm( oprnd1DataList );
                tmpProductDevMgt.setOprnd1Nm(oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 ));
            }
            //고정값 2 처리
            List<ProductDevMgt> oprnd2DataList = oprndDataList( 2, tmpProductDevMgt );
            if ( oprnd2DataList != null )
            {
            	String oprnd2Nm = oprndDataNm( oprnd2DataList );
                tmpProductDevMgt.setOprnd1Nm(oprnd2Nm.substring( 0, oprnd2Nm.length() - 2 ));
            }
        }*/

		productDevMgt.setProcFl("MOD_PROC");
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("condOperator", commonDataService.getCommonCodeList(condOperator, lngTyp));
		model.addAttribute("oprndRefFl", commonDataService.getCommonCodeList(oprndRefFl, lngTyp));		
		model.addAttribute("fctr", productDevMgtService.getRateItmCondComboList(productDevMgt));
		model.addAttribute("condInOut", result);	
		return  URL + "/ajax/modRateItmCondPopup";
	}	 	

	@RequestMapping(value = "modRateItmCond", method = RequestMethod.POST)
	public void modRateItmCond (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			

		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);	
		
		//int condNo = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMPD_RATE_ITM_COND);	
		//productDevMgt.setCondNo1(condNo);
		String result = productDevMgtService.modRateItmCond(productDevMgt);
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "delRateItmCond", method = RequestMethod.POST)
	public void delRateItmCond (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		productDevMgt.setDelFl("COND_DELETE");
		String result = productDevMgtService.delRateItmCond(productDevMgt);
		model.addAttribute("result", result);
	}		

	@RequestMapping(value = "getAllowanceList", method = RequestMethod.POST)
	public void getAllowanceList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = 0;
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));		
		
		productDevMgt.setLngTyp(lngTyp);		

		count = productDevMgtService.getAllowanceListCount(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getAllowanceList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
			
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}		

	@RequestMapping(value = "addRateExtractList", method = RequestMethod.POST)
	public void addRateExtractList (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);	
				
		String result = productDevMgtService.addRateExtractList(productDevMgt);
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "addAllowancePopup", method = RequestMethod.POST)
	public String addAllowancePopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String rateRefFl = "PP00056";
		String qtyRefFl = "PP00057";
		String crncyRefFl = "PP00058";
		String crncyCd = "PP00041";

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
		
		
		List<ProductDevMgt> resultList = productDevMgtService.getAddRateItmRateFctrList(productDevMgt);

		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("rateRefFl", commonDataService.getCommonCodeList(rateRefFl, lngTyp));
		model.addAttribute("qtyRefFl", commonDataService.getCommonCodeList(qtyRefFl, lngTyp));
		model.addAttribute("crncyRefFl", commonDataService.getCommonCodeList(crncyRefFl, lngTyp));
		model.addAttribute("crncyCd", commonDataService.getCommonCodeList(crncyCd, lngTyp));
		model.addAttribute("fctr", productDevMgtService.getAddRateItmRateFctrInit(productDevMgt));

		List oprndNm = null;
		for(int i=0; i<resultList.size(); i++){
System.out.println("getOprnd1====>"+resultList.get(i).getOprnd1());
			if(resultList.get(i).getOprnd1() == null || resultList.get(i).getOprnd1().isEmpty()){

			}else{
				System.out.println("getRefCd====>"+resultList.get(i).getRefCd());				
				if(resultList.get(i).getRefCd() == null || resultList.get(i).getRefCd().isEmpty()){

				}else{
					productDevMgt.setOprnd1(resultList.get(i).getOprnd1());
					productDevMgt.setRefCd(resultList.get(i).getRefCd());
					productDevMgt.setFctrNo(resultList.get(i).getFctrNo());	
					String[] spl = resultList.get(i).getFctrNo().split(",");			 
					System.out.println("getFctrNo=====>"+resultList.get(i).getFctrNo());
					for(int k=0; k<spl.length; k++){ 
						 oprndNm = productDevMgtService.getOprndNm(productDevMgt);
					}
					model.addAttribute("fctrList"+resultList.get(i).getFctrNo(), oprndNm);
				}
			}
		}
		//model.addAttribute("fctrList", productDevMgtService.getAddRateItmRateFctrList(productDevMgt));
		
		return  URL + "/ajax/addAllowancePopup";
	}		
	
	@RequestMapping(value = "addRate", method = RequestMethod.POST)
	public void addRate (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			

		productDevMgt.setCurrentDay(currentDay);
	    productDevMgt.setRegrId(usrId);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);	

		//현재 요율 NULL 값으로 무조건 들어옴
		if(StringUtil.isEmpty(productDevMgt.getFctrVal1())){
			productDevMgt.setFctrVal1("*");
		}
		if(StringUtil.isEmpty(productDevMgt.getFctrVal2())){
			productDevMgt.setFctrVal2("*");
		}
		if(StringUtil.isEmpty(productDevMgt.getFctrVal3())){
			productDevMgt.setFctrVal3("*");
		}
		if(StringUtil.isEmpty(productDevMgt.getFctrVal4())){
			productDevMgt.setFctrVal4("*");
		}
		if(StringUtil.isEmpty(productDevMgt.getFctrVal5())){
			productDevMgt.setFctrVal5("*");
		}
		if(StringUtil.isEmpty(productDevMgt.getFctrVal6())){
			productDevMgt.setFctrVal6("*");
		}
		if(StringUtil.isEmpty(productDevMgt.getFctrVal7())){
			productDevMgt.setFctrVal7("*");
		}
		if(StringUtil.isEmpty(productDevMgt.getFctrVal8())){
			productDevMgt.setFctrVal8("*");
		}
		if(StringUtil.isEmpty(productDevMgt.getFctrVal9())){
			productDevMgt.setFctrVal9("*");
		}

		String result = productDevMgtService.addRate(productDevMgt);
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "modAllowancePopup", method = RequestMethod.POST)
	public String modAllowancePopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String rateRefFl = "PP00056";
		String qtyRefFl = "PP00057";
		String crncyRefFl = "PP00058";
		String crncyCd = "PP00041";

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productDevMgt.setLngTyp(lngTyp);
		productDevMgt.setCurrentDay(currentDay);
															
		productDevMgt.setFctrVal1(productDevMgt.getFctrVal1());
		productDevMgt.setFctrVal2(productDevMgt.getFctrVal2());
		productDevMgt.setFctrVal3(productDevMgt.getFctrVal3());
		productDevMgt.setFctrVal4(productDevMgt.getFctrVal4());
		productDevMgt.setFctrVal5(productDevMgt.getFctrVal5());
		productDevMgt.setFctrVal6(productDevMgt.getFctrVal6());
		productDevMgt.setFctrVal7(productDevMgt.getFctrVal7());
		productDevMgt.setFctrVal8(productDevMgt.getFctrVal8());
		productDevMgt.setFctrVal9(productDevMgt.getFctrVal9());
		
		List<ProductDevMgt> resultList = productDevMgtService.getAddRateItmRateFctrList(productDevMgt);
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("rateRefFl", commonDataService.getCommonCodeList(rateRefFl, lngTyp));
		model.addAttribute("qtyRefFl", commonDataService.getCommonCodeList(qtyRefFl, lngTyp));
		model.addAttribute("crncyRefFl", commonDataService.getCommonCodeList(crncyRefFl, lngTyp));
		model.addAttribute("crncyCd", commonDataService.getCommonCodeList(crncyCd, lngTyp));
		model.addAttribute("fctr", productDevMgtService.getAddRateItmRateFctrInit(productDevMgt));
		model.addAttribute("rateList", productDevMgtService.getRateItmRate(productDevMgt));		

		List oprndNm = null;
		for(int i=0; i<resultList.size(); i++){
			if(resultList.get(i).getOprnd1() != null || resultList.get(i).getRefCd() == "4"){
				
				productDevMgt.setOprnd1(resultList.get(i).getOprnd1());
				productDevMgt.setRefCd(resultList.get(i).getRefCd());
				productDevMgt.setFctrNo(resultList.get(i).getFctrNo());	
				String[] spl = resultList.get(i).getFctrNo().split(",");			 
				
				for(int k=0; k<spl.length; k++){ 
					 oprndNm = productDevMgtService.getOprndNm(productDevMgt);
				}
				model.addAttribute("fctrList"+i, oprndNm);
			}
		}
		
		return  URL + "/ajax/modAllowancePopup";
	}		
	
	@RequestMapping(value = "modRate", method = RequestMethod.POST)
	public void modRate (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();			

		productDevMgt.setChgrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());

		//현재 요율 NULL 값으로 무조건 들어옴
		productDevMgt.setFctrVal1(productDevMgt.getFctrVal1());
		productDevMgt.setFctrVal2(productDevMgt.getFctrVal2());
		productDevMgt.setFctrVal3(productDevMgt.getFctrVal3());
		productDevMgt.setFctrVal4(productDevMgt.getFctrVal4());
		productDevMgt.setFctrVal5(productDevMgt.getFctrVal5());
		productDevMgt.setFctrVal6(productDevMgt.getFctrVal6());
		productDevMgt.setFctrVal7(productDevMgt.getFctrVal7());
		productDevMgt.setFctrVal8(productDevMgt.getFctrVal8());
		productDevMgt.setFctrVal9(productDevMgt.getFctrVal9());
		
		System.out.println("=============>"+productDevMgt.getQty());
		
		String result = productDevMgtService.modRate(productDevMgt);
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "delRate", method = RequestMethod.POST)
	public void delRate (
			ProductDevMgt productDevMgt
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {

		//현재 요율 NULL 값으로 무조건 들어옴
		productDevMgt.setFctrVal1(productDevMgt.getFctrVal1());
		productDevMgt.setFctrVal2(productDevMgt.getFctrVal2());
		productDevMgt.setFctrVal3(productDevMgt.getFctrVal3());
		productDevMgt.setFctrVal4(productDevMgt.getFctrVal4());
		productDevMgt.setFctrVal5(productDevMgt.getFctrVal5());
		productDevMgt.setFctrVal6(productDevMgt.getFctrVal6());
		productDevMgt.setFctrVal7(productDevMgt.getFctrVal7());
		productDevMgt.setFctrVal8(productDevMgt.getFctrVal8());
		productDevMgt.setFctrVal9(productDevMgt.getFctrVal9());
		productDevMgt.setDelFl("RATE_DELETE");
		
		String result = productDevMgtService.delRate(productDevMgt);
		model.addAttribute("result", result);
	}		

	@RequestMapping(value = "getProdDvlpStatus", method = RequestMethod.POST)
	public void getProdDvlpStatus(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
	
		model.addAttribute("productDevMgt", productDevMgtService.getProdDvlpStatus(productDevMgt));	
	}		
	
	@RequestMapping(value = "getDiscExclList", method = RequestMethod.POST)
	public void getDiscExclList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = 0;
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));			
		
		count = productDevMgtService.getDiscExclListCount(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getDiscExclList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}		

	@RequestMapping(value = "addDiscExclInitPopup", method = RequestMethod.POST)
	public String addDiscExclInitPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		

		
		
		model.addAttribute("productDevMgt", productDevMgt);		
		return  URL + "/ajax/addDiscExclInitPopup";
	}		
	
	
	@RequestMapping(value = "getAddDiscExclInit", method = RequestMethod.POST)	
	public void getAddDiscExclInit(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
			
		productDevMgt.setCurrentDay(currentDay);
		
		List<ProductDevMgt> resultList = productDevMgtService.getAddDiscExclInit(productDevMgt);
		
		model.addAttribute("rows", resultList);
	}	
	
	@RequestMapping(value = "addDiscExcl", method = RequestMethod.POST)
	public void addDiscExcl (
			@RequestBody List<ProductDevMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
				
		String result = productDevMgtService.addDiscExcl(params, sessionUser);
		
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "delDiscExclusionRelationship", method = RequestMethod.POST)
	public void delDiscExclusionRelationship (
			ProductDevMgt productDevMgt,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		productDevMgt.setDelFl("SVC_RATE_ITM_TYP_DELETE");
		
		String result = productDevMgtService.delDiscExclusionRelationship(productDevMgt);
		
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "getDiscSvcRateItmTypList", method = RequestMethod.POST)
	public void getDiscSvcRateItmTypList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = 0;
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));			
		
		count = productDevMgtService.getDiscSvcRateItmTypListCount(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getDiscSvcRateItmTypList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}	
	
	@RequestMapping(value = "getAddDiscSvcRateItmTypInit", method = RequestMethod.POST)	
	public void getAddDiscSvcRateItmTypInit(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
			
		productDevMgt.setCurrentDay(currentDay);
		
		List<ProductDevMgt> resultList = productDevMgtService.getAddDiscSvcRateItmTypInit(productDevMgt);
		
		model.addAttribute("rows", resultList);
	}		
	
	@RequestMapping(value = "addDiscSvcRateItmTypInitPopup", method = RequestMethod.POST)
	public String addDiscSvcRateItmTypInitPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		model.addAttribute("productDevMgt", productDevMgt);		
		return  URL + "/ajax/addDiscSvcRateItmTypInitPopup";
	}		
	
	@RequestMapping(value = "addDiscSvcRateItmTyp", method = RequestMethod.POST)
	public void addDiscSvcRateItmTyp (
			@RequestBody List<ProductDevMgt> params,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
				
		String result = productDevMgtService.addDiscSvcRateItmTyp(params, sessionUser);
		
		model.addAttribute("result", result);
	}			
	
	@RequestMapping(value = "delDiscSvcRateItmTyp", method = RequestMethod.POST)
	public void delDiscSvcRateItmTyp (
			ProductDevMgt productDevMgt,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		productDevMgt.setDelFl("SVC_RATE_ITM_TYP_DELETE");
		
		String result = productDevMgtService.delDiscSvcRateItmTyp(productDevMgt);
		
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "getDiscPerdList", method = RequestMethod.POST)
	public void getDiscPerdList(
			ProductDevMgt productDevMgt  
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {
		
		int count = 0;
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));		
		
		productDevMgt.setLngTyp(lngTyp);	
		productDevMgt.setCurrentDay(currentDay);
		
		count = productDevMgtService.getDiscPerdListCount(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getDiscPerdList(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
	}	
	
	@RequestMapping(value = "addDiscPerdPopup", method = RequestMethod.POST)
	public String addDiscPerdPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String operator = "PP00016";
		String oprndRefFl1 = "PP00059";
		String oprndRefFl2 = "PP00059";
		productDevMgt.setCurrentDay(currentDay);
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("oprndFctrCd", productDevMgtService.getOprndFctrCd(productDevMgt));
		model.addAttribute("operator", commonDataService.getCommonCodeList(operator, lngTyp));
		model.addAttribute("oprndRefFl1", commonDataService.getCommonCodeList(oprndRefFl1, lngTyp));
		model.addAttribute("oprndRefFl2", commonDataService.getCommonCodeList(oprndRefFl2, lngTyp));		
		return  URL + "/ajax/addDiscPerdPopup";
	}		
	
	@RequestMapping(value = "addDiscPerd", method = RequestMethod.POST)
	public void addDiscPerd (
			ProductDevMgt productDevMgt,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();
		
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setRegrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());	
		
		String result = productDevMgtService.addDiscPerd(productDevMgt);
		
		model.addAttribute("result", result);
	}		
	
	@RequestMapping(value = "modDiscPerd", method = RequestMethod.POST)
	public void modDiscPerd (
			ProductDevMgt productDevMgt,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		String usrId = sessionUser.getUserId();
		
		productDevMgt.setCurrentDay(currentDay);
		productDevMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
		productDevMgt.setChgrId(usrId);
		productDevMgt.setRegrId(usrId);
		productDevMgt.setSysdate(DateUtil.sysdate());	
		
		String result = productDevMgtService.modDiscPerd(productDevMgt);
		
		model.addAttribute("result", result);
	}			
	
	@RequestMapping(value = "delDiscPerd", method = RequestMethod.POST)
	public void delDiscPerd (
			ProductDevMgt productDevMgt,
			 Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		productDevMgt.setDelFl("RATE_ITM_DELETE");
		
		String result = productDevMgtService.delDiscPerd(productDevMgt);
		
		model.addAttribute("result", result);
	}	
	
	
	@RequestMapping(value = "modDiscPerdPopup", method = RequestMethod.POST)
	public String modDiscPerdPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {
		
		String operator = "PP00016";
		String oprndRefFl1 = "PP00059";
		String oprndRefFl2 = "PP00059";
		productDevMgt.setCurrentDay(currentDay);
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		model.addAttribute("productDevMgt", productDevMgt);
		model.addAttribute("oprndFctrCd", productDevMgtService.getOprndFctrCd(productDevMgt));
		model.addAttribute("operator", commonDataService.getCommonCodeList(operator, lngTyp));
		model.addAttribute("oprndRefFl1", commonDataService.getCommonCodeList(oprndRefFl1, lngTyp));
		model.addAttribute("oprndRefFl2", commonDataService.getCommonCodeList(oprndRefFl2, lngTyp));
		model.addAttribute("discPerd", productDevMgtService.getDiscPerd(productDevMgt));
		
		return  URL + "/ajax/modDiscPerdPopup";
	}		
	
	@RequestMapping(value = "getOprndFctrCd", method = RequestMethod.POST)
	public void getOprndFctrCd (
			ProductDevMgt productDevMgt  
		      , Model model
		      , HttpServletRequest request
			  ) throws ServiceException {
		
		productDevMgt.setCurrentDay(currentDay);
		List<ProductDevMgt> oprndFctrCd = productDevMgtService.getOprndFctrCd(productDevMgt);
		
		model.addAttribute("oprndFctrCd", oprndFctrCd);	
		
	}	

	@RequestMapping(value = "getAddDiscSvcRateItmTypInitPopup", method = RequestMethod.GET)
	public String getAddDiscSvcRateItmTypInitPopup(Model model
										, HttpServletRequest request
										, ProductDevMgt productDevMgt  
										) throws Exception {

		model.addAttribute("productDevMgt", productDevMgt);
		return  URL + "/ajax/getAddDiscSvcRateItmTypInitPopup";
	}			
	
	@RequestMapping(value = "getCommonCodeComboList", method = RequestMethod.POST)	
	public void getCommonCodeComboList(
			ProductDevMgt productDevMgt 
		  , Model model
		  , HttpServletRequest request
		  ) throws Exception {	
			
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		productDevMgt.setLngTyp(lngTyp);
		
		List<ProductDevMgt> resultList = productDevMgtService.getCommonCodeComboList(productDevMgt);
		
		model.addAttribute("rows", resultList);
	}
	
}



