package com.ntels.ccbs.product.controller.refInfo.ratingRefInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.ratingRefInfo.AllowanceDiscount;
import com.ntels.ccbs.product.service.refInfo.commonInfo.AttributeService;
import com.ntels.ccbs.product.service.refInfo.ratingRefInfo.AllowanceDiscountService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Controller
@RequestMapping(value = "/product/refInfo/ratingRefInfo/allowanceDiscount")
public class AllowanceDiscountController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private AllowanceDiscountService allowanceDiscountService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	private String thisUrl = "product/refInfo/ratingRefInfo/allowanceDiscount";

	/** per page. */
    private @Value("${paging.per_page}") Integer perPage;
    

	/**
	 * allowanceDiscountList
	 * 설명 : 속성유형연결 목록 조회페이지 호출
	 */
	@RequestMapping(value = "allowanceDiscountList", method = RequestMethod.POST)
	public String allowanceDiscountList(Model model, AllowanceDiscount allowanceDiscount, HttpServletRequest request) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		
		allowanceDiscount.setLngTyp(lngTyp);
		allowanceDiscount.setCurrentDay(currentDay);

		return thisUrl + "/allowanceDiscountList";
	}
	/**
	 * attributeListAction
	 * 설명 : 속성 목록 조회 페이지 리스트
	 * @param attribute
	 * @param int page
	 * @param int perPage
	 * @return model
	 */
	@RequestMapping(value = "allowanceDiscountListAction", method = RequestMethod.POST)
	public void allowanceDiscountListAction(
			AllowanceDiscount allowanceDiscount
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		allowanceDiscount.setLngTyp(lngTyp);
		allowanceDiscount.setSidx(sidx);
		allowanceDiscount.setSord(sord);
		
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		allowanceDiscount.setCurrentDay(currentDay);
		
		List<AllowanceDiscount> list = null;
		int count = 0;

		list = allowanceDiscountService.getAllowanceDiscountList(allowanceDiscount, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * allowanceDiscountInsert
	 * 설명 : 속성유형 등록 팝업 Layer
	 * @param allowanceDiscount
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "allowanceDiscountInsertPopUp", method = RequestMethod.POST)
	public String allowanceDiscountInsertPopUp(
			AllowanceDiscount allowanceDiscount
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String[] dataTypCdList = new String[] {
				"PP00256", "PP00291", "PP00226", "PP00238",	"PP00212", 
				"PP00247", "PP00227", "PP00225", "PP00290", "PP00224",
				"PP00275", "PP00248", "PP00223", "PP00303", "PP00285"
		};
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		allowanceDiscount.setCurrentDay(currentDay);
		System.out.println("currentDay==>"+currentDay);
		
		System.out.println(allowanceDiscount);
		
		for( int i=0; i<dataTypCdList.length; i++ ) {
			model.addAttribute("listCommon"+(i+1), commonDataService.getCommonCodeList(dataTypCdList[i], lngTyp));
		}
		model.addAttribute("lngTyp",lngTyp);
		model.addAttribute("listItem", allowanceDiscountService.getCommonCodeList());
		model.addAttribute("chrgCdList", allowanceDiscountService.getChrgCdList());

		return  thisUrl + "/ajax/allowanceDiscountInsertPopUp";
	}
	/**
	 * allowanceDiscountInsertAction
	 * 설명 : 속성유형연결 정보 등록
	 * @param allowanceDiscount
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "allowanceDiscountInsertAction", method = RequestMethod.POST)
	public String allowanceDiscountInsertAction(
			AllowanceDiscount allowanceDiscount, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		allowanceDiscount.setUserId(session.getUserId());
		allowanceDiscount.setSysToDate(DateUtil.sysdate());
		allowanceDiscount.setInsertEffDt(allowanceDiscount.getInsertEffDt().replace("-", "") + "000000");
		
		System.out.println("#############" + allowanceDiscount.toString());
		
		
   		if( allowanceDiscountService.addAllowanceDiscount(allowanceDiscount) > 0 ) {		
   			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
   		} else {		
   			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
   		}	

   		return thisUrl + "/allowanceDiscountList";
	}
	
	/**
	 * allowanceDiscountInsert
	 * 설명 : 속성유형 등록 팝업 Layer
	 * @param allowanceDiscount
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "allowanceDiscountUpdatePopUp", method = RequestMethod.POST)
	public String allowanceDiscountUpdatePopUp(
			AllowanceDiscount allowanceDiscount
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		String[] dataTypCdList = new String[] {
				"PP00256", "PP00291", "PP00226", "PP00238",	"PP00212", 
				"PP00247", "PP00227", "PP00225", "PP00290", "PP00224",
				"PP00275", "PP00248", "PP00223", "PP00303", "PP00285"
		};
		String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
		allowanceDiscount.setCurrentDay(currentDay);
		System.out.println("currentDay==>"+currentDay);
		
		for( int i=0; i<dataTypCdList.length; i++ ) {
			model.addAttribute("listCommon"+(i+1), commonDataService.getCommonCodeList(dataTypCdList[i], lngTyp));
		}
		
		model.addAttribute("lngTyp",lngTyp);
		model.addAttribute("listItem", allowanceDiscountService.getCommonCodeList());
		model.addAttribute("chrgCdList", allowanceDiscountService.getChrgCdList());
		
		return  thisUrl + "/ajax/allowanceDiscountUpdatePopUp";
	}
	/**
	 * allowanceDiscountInsertAction
	 * 설명 : 속성유형연결 정보 등록
	 * @param allowanceDiscount
	 * @return model
	 * @throws Exception 
	 */
	@RequestMapping(value = "allowanceDiscountUpdateAction", method = RequestMethod.POST)
	public String allowanceDiscountUpdateAction(
			AllowanceDiscount allowanceDiscount, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {

		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		
		allowanceDiscount.setUserId(session.getUserId());
		allowanceDiscount.setSysToDate(DateUtil.sysdate());

   		if( allowanceDiscountService.modAllowanceDiscount(allowanceDiscount) > 0 ) {		
   			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M09.MSG00005"));
   		} else {		
   			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
   		}	

   		return thisUrl + "/allowanceDiscountList";
	}
	
	@RequestMapping(value = "allowanceDiscountDeleteAction", method = RequestMethod.POST)
	public String allowanceDiscountDeleteAction(
			AllowanceDiscount allowanceDiscount, // 파라미터를 모델로 받아서 처리
			Model model,
			HttpServletRequest request) {
		
   		if( allowanceDiscountService.delAllowanceDiscount(allowanceDiscount) > 0 ) {		
   			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M07.MSG00053"));
   		} else {		
   			model.addAttribute("resultMsg", MessageUtil.getMessage("MSG.M08.MSG00022"));
   		}	

   		return thisUrl + "/allowanceDiscountList";
	}
}