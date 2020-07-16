package com.ntels.ccbs.product.controller.usageProduct.usageProductMgt;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.ntels.ccbs.common.consts.Consts.ExcelFormatType;
import com.ntels.ccbs.common.view.ExcelCellVO;
import com.ntels.ccbs.common.view.ExcelColumnVO;
import com.ntels.ccbs.common.view.ExcelFileVO;
import com.ntels.ccbs.common.view.ExcelRowVO;
import com.ntels.ccbs.common.view.ExcelSheetVO;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.ManageRatingDef;
import com.ntels.ccbs.product.domain.usageProduct.usageProductMgt.ProductDtl;
import com.ntels.ccbs.product.service.usageProduct.usageProductMgt.ProductDtlService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/usageProduct/usageProductMgt/productDtlList")
public class ProductDtlController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ProductDtlService productDtlService;
	
	private String thisUrl = "product/usageProduct/usageProductMgt/productDtlList";
	
	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    
    
	/**
	 * productDtlList
	 * 설명 : 상품내역조회 목록 조회 페이지 호출
	 */
	@RequestMapping(value = "productDtlList", method = RequestMethod.POST)
	public String productDtlList(Model model, ProductDtl productDtl, HttpServletRequest request) throws Exception {
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		productDtl.setLngTyp(lngTyp);
		
		model.addAttribute("prodTypList", commonDataService.getCommonCodeList("PP00263", lngTyp));//데이터유형 셀렉트박스에 들어갈 내용
		model.addAttribute("soNmList", productDtlService.getSoNmList());
		
		return thisUrl + "/productDtlList";
	}
	
	@RequestMapping(value = "productDtlListAction", method = RequestMethod.POST)
	public void productDtlListAction(
			ProductDtl productDtl
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

        System.out.println("############" + productDtl.toString());
        
        List<ProductDtl> list = new ArrayList<ProductDtl>();
		
		list = productDtlService.getProductDtlList(productDtl, page, perPage);
		
		model.addAttribute("rows", list);
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	@RequestMapping(value = "productDtlListExcelAction", method = RequestMethod.POST)
	public String productDtlListExcelAction(
		Model model 
		,ProductDtl productDtl 
		,HttpServletRequest request
		,String soNm
		,String prodCd
		,String prodNm
		,String prodGrp
		,String prodDesc
		,String basicProdTyp
		,String actDt
		,String inactDt 
		) {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		productDtl.setUserId(session.getUserId());
		productDtl.setLngTyp(lngTyp);
		
		System.out.println(">> EXCEL chrgCd<< " + soNm);
		
		List<Map<String,Object>> list = productDtlService.productDtlListExcel(productDtl, soNm, prodCd, prodNm, prodGrp, prodDesc, basicProdTyp, actDt, inactDt);
		
		/*
		 * Header 작성
		 */
		List<ExcelColumnVO> columnList = new ArrayList<ExcelColumnVO>();
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00003"), "SO_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00156"), "PROD_CD", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00130"), "PROD_NM", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(27, MessageUtil.getMessage("LAB.M07.LAB00127"), "PROD_GRP", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00143"), "PROD_DESC", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00141"), "BASIC_PROD_FL", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(11, MessageUtil.getMessage("LAB.M07.LAB00147"), "ACT_DT", ExcelFormatType.STRING));
		columnList.add(new ExcelColumnVO(27, MessageUtil.getMessage("LAB.M13.LAB00010"), "INACT_DT", ExcelFormatType.STRING));
	
		/*
		 * 데이터 세팅
		 */
		List<ExcelRowVO> rowList = new ArrayList<ExcelRowVO>();
		for(Map<String,Object> row : list) {
			ExcelRowVO rowVo = new ExcelRowVO();
			//Row
			Map<String, ExcelCellVO> rowMap = new HashMap<String, ExcelCellVO>();
			
			for( ExcelColumnVO col : columnList ) {
				//Col 세팅
				ExcelCellVO cell = new ExcelCellVO();
				cell.setValue(row.get(col.getKey()));
				rowMap.put(col.getKey(), cell);
			}
			rowVo.setRowData(rowMap);
			rowList.add(rowVo);
		}
		
		/*
		 * Sheet 작성
		 */
		List<ExcelSheetVO> shList = new ArrayList<ExcelSheetVO>();
		ExcelSheetVO sh = new ExcelSheetVO();
		sh.setSheetName(MessageUtil.getMessage("LAB.M07.LAB00129"));
		sh.setDataList(rowList);
		sh.setTitleList(columnList);
		shList.add(sh);
		
		/*
		 * 파일작성
		 */
		ExcelFileVO file = new ExcelFileVO();
		file.setFileName(MessageUtil.getMessage("LAB.M07.LAB00129"));
		file.setSheetCount(1);
		file.setSheetList(shList);
		
		/*
		 * Model Set
		 */
		model.addAttribute("excelDataFile", file);
		
		return "excelXlsxView";
	}
}