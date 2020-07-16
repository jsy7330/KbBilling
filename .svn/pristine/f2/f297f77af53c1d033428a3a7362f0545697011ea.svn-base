package com.ntels.ccbs.system.controller.common.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.system.domain.common.common.ManufacturerMngVO;
import com.ntels.ccbs.system.service.common.common.ManufacturerMngService;

@Controller
@RequestMapping(value = "/system/common/common/manufacturerMng")
public class ManufacturerMngController {

	private static String URL = "system/common/common/manufacturerMng";
	
	@Autowired
	private ManufacturerMngService manufacturerMngService;
	
	/**
	 * 제조사 목록 팝업
	 * <PRE>
	 * 1. MethodName: manufacturerSearchPopup
	 * 2. ClassName : ManufacturerMngController
	 * 3. Comment   :
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 26. 오후 3:29:39
	 * </PRE>
	 *   @return String
	 *   @param manufacturerMngVO
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "manufacturerSearchPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public String manufacturerSearchPopup(
			ManufacturerMngVO manufacturerMngVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		model.addAttribute("manufacturerMngVO", manufacturerMngVO);
		
		if(manufacturerMngVO.getPopType().equals("m")){
			return URL + "/mpopup/manufacturerSearchPopup";
		}else{
			return URL + "/opopup/manufacturerSearchPopup";
		}
			
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: manufacturerListAction
	 * 2. ClassName : ManufacturerMngController
	 * 3. Comment   : 제조사 목록 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2016. 5. 26. 오후 3:29:56
	 * </PRE>
	 *   @return void
	 *   @param manufacturerMngVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "manufacturerListAction", method = RequestMethod.POST)
	public void manufacturerListAction(
			ManufacturerMngVO manufacturerMngVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		manufacturerMngVO.setLngTyp(lngTyp);
		manufacturerMngVO.setSidx(sidx);
		manufacturerMngVO.setSord(sord);
		
        List<ManufacturerMngVO> list = new ArrayList<ManufacturerMngVO>();
		int count = 0;
		count = manufacturerMngService.manufacturerCount(manufacturerMngVO);		
		if (count > 0) list = manufacturerMngService.manufacturerList(manufacturerMngVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
}
