package com.ntels.ccbs.charge.controller.billing.billing;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.billing.billing.BillingInfoItemVO;
import com.ntels.ccbs.charge.service.billing.billing.BillingInfoItemService;


@Controller
@RequestMapping(value = "/charge/billing/billing/billingInfoItem")
public class BillingInfoItemController {
	
	private static String URL = "charge/billing/billing/billingInfoItem";
	
	@Autowired
	private BillingInfoItemService billingInfoItemService;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: BillingInfoItemList
	 * 2. ClassName : BillingInfoItemController
	 * 3. Comment   : 빌링 설정정보 관리 화면
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param BillingInfoItemVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "billingInfoItemList", method = RequestMethod.POST)
	public String BillingInfoItemList(Model model, BillingInfoItemVO billingInfoItemVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		return URL + "/billingInfoItemList";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: BillingInfoItemListAction
	 * 2. ClassName : BillingInfoItemController
	 * 3. Comment   : 빌링 설정정보 조회
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param BillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "billingInfoItemListAction", method = RequestMethod.POST)
	public void billingInfoItemListAction(
			BillingInfoItemVO billingInfoItemVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		billingInfoItemVO.setLngTyp(lngTyp);
		billingInfoItemVO.setSidx(sidx);
		billingInfoItemVO.setSord(sord);
		
        List<BillingInfoItemVO> list = new ArrayList<BillingInfoItemVO>();
		int count = 0;
		count = billingInfoItemService.billingInfoItemListCount(billingInfoItemVO);		
		if (count > 0) list = billingInfoItemService.billingInfoItemListAction(billingInfoItemVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
}
