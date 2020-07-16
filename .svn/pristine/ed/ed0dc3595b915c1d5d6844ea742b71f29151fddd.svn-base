package com.ntels.ccbs.product.controller.product.productDev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.product.service.product.productDev.ProductDevMgtService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

@Controller
@RequestMapping(value = "/product/product/productOp/productOpMgt")
public class ProductOpMgtController {

	@Autowired
	private ProductDevMgtService productDevMgtService;
	
	private static String URL = "product/product/productOp/productOpMgt";	
	
	List<Map<String,Object>> soAuthList;
	List<ProductDevMgt> productDevMgtTree;	
	
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
	
	@RequestMapping(value = "getTreeAction2", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object getTreeAction(HttpServletRequest request) {
		
		String sessionLanguage = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session_user");
		soAuthList = sessionUser.getSoAuthList();
		
		ArrayList<Object> root = new ArrayList<Object>(); // json data
		Map<String, Object> rootNode = new HashMap<String, Object>();
		Map<String, Boolean> removeNode = new HashMap<String, Boolean>();		
		
		productDevMgtTree = productDevMgtService.getDProdTree2(sessionLanguage, soAuthList,currentDay);
		
		rootNode.put("title", "PRODUCT");
		rootNode.put("targetCd", "PRODUCT");	
		rootNode.put("isFolder", "TRUE");
		rootNode.put("key", "PRODUCT"); 
		rootNode.put("prodTyp", "");
		rootNode.put("prodGrp", "ROOT");
		rootNode.put("children", makeMenu2Json(rootNode, 0, removeNode));
		rootNode.put("soId", "");
		
		root.add(rootNode);
		
		return root;
	}
	
	private ArrayList<Object> makeMenu2Json(Map<String, Object> parent, int index, Map<String, Boolean> removeNode) {
		ProductDevMgt productDevMgt = null;
		ArrayList<Object> folder = new ArrayList<Object>();
		String parent_menu_no = "0";
	
		parent_menu_no = parent.get("key").toString();
		
		for(int i = index; i < productDevMgtTree.size(); i++) {
			productDevMgt = productDevMgtTree.get(i);
			
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
		
		count = productDevMgtService.getProductListCount2(productDevMgt);
		
		if (count > 0) { 
			productDevMgtProductList = productDevMgtService.getProductList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", productDevMgtProductList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", productDevMgtProductList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);		
		
		
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
		productDevMgt.setCurrentDay(currentDay);
		List<ProductDevMgt> productServiceList = null;
		int count = 0;
		
		count = productDevMgtService.getProductServiceListCount2(productDevMgt);
		
		if (count > 0) { 
			productServiceList = productDevMgtService.getProductServiceList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", productServiceList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}
		model.addAttribute("rows", productServiceList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);		
		
		
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
		
		count = productDevMgtService.getSuprtEquipListCount2(productDevMgt);
		
		List<ProductDevMgt> suprtEquipList = null;
		
		if (count > 0) { 
			suprtEquipList = productDevMgtService.getSuprtEquipList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", suprtEquipList.size()); //현화면의 리스트갯수	
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", suprtEquipList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
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
		
		count = productDevMgtService.getProductAttrListCount3(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getProductAttrList3(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
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
		
		count = productDevMgtService.getProductRelationshipListCount2(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getProductRelationshipList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
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
		
		count = productDevMgtService.getRateItmListCount2(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getRateItmList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
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
		
		count = productDevMgtService.getRateItmFctrListCount2(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getRateItmFctrList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);		 
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
		
		count = productDevMgtService.getRateItmAttrListCount2(productDevMgt);
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getRateItmAttrList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
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
		
		count = productDevMgtService.getRateItmCondListCount2(productDevMgt);
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getRateItmCondList2(productDevMgt, page, perPageRow);
	        Iterator it = resultList.iterator();

	        while ( it.hasNext() ) {
	        	ProductDevMgt tmpProductDevMgt = (ProductDevMgt) it.next();	
	        	tmpProductDevMgt.setLngTyp(lngTyp);
	            //고정값 1 처리
	            List<ProductDevMgt> oprnd1DataList = oprndDataList2( 1, tmpProductDevMgt );
	            //    System.out.println(oprnd1DataList.isEmpty());
	            if ( oprnd1DataList != null )
	            {
	                if ( !oprnd1DataList.isEmpty() )
	                {
	                    String oprnd1Nm = oprndDataNm2( oprnd1DataList );
	                    tmpProductDevMgt.setOprnd1Nm(oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 ));
	                    //resultMap.put( "OPRND_1_NM", oprnd1Nm.substring( 0, oprnd1Nm.length() - 2 ) );	            		
	                }
	            }

	            if(tmpProductDevMgt.getOprnd2() != null){	 	            	
	            	if(tmpProductDevMgt.getOprnd2().length()!=0){
		                //고정값 2 처리
			            List<ProductDevMgt> oprnd2DataList = oprndDataList2( 2, tmpProductDevMgt );
			            //System.out.println(oprnd2DataList.isEmpty());
			            if ( oprnd2DataList != null )
			            {
			                if ( !oprnd2DataList.isEmpty() )
			                {
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

		count = productDevMgtService.getAllowanceListCount2(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getAllowanceList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
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
        System.out.println("currentDay========>"+currentDay);
        productDevMgt.setCurrentDay(currentDay);
		count = productDevMgtService.getDiscExclListCount2(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getDiscExclList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
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
		
		count = productDevMgtService.getDiscSvcRateItmTypListCount2(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getDiscSvcRateItmTypList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);		 
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
		
		count = productDevMgtService.getDiscPerdListCount2(productDevMgt);
		
		List<ProductDevMgt> resultList = null;
		
		if (count > 0) { 
			resultList = productDevMgtService.getDiscPerdList2(productDevMgt, page, perPageRow);
			model.addAttribute("records", resultList.size()); //현화면의 리스트갯수
		} else {
			model.addAttribute("records", "0");
		}		
		
		model.addAttribute("rows", resultList);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("page", page); 			//현재 페이지
		
		model.addAttribute("perpage", perPageRow);	
	 
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
  
        List<ProductDevMgt> resultList = null;
        if ( oprnd != null )
        {
       	 if(dataTyp != null){
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

                paramMap.put( "LNG_TYP", lngTyp );             
   
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
        }
        return resultList;
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
}
