package com.ntels.ccbs.charge.controller.billing.billingAdjust;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.AdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAdjustService;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingAfterAdjustService;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/billing/billingAdjust/billingAfterAdjust")
public class BillingAfterAdjustController {

	private static String URL = "charge/billing/billingAdjust/billingAfterAdjust";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private BillingAfterAdjustService billingAfterAdjustService;
	
	@Autowired
	private BillingAdjustService billingAdjustService;
	
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: billingAfterAdjust
	 * 2. ClassName : BillingAfterAdjustController
	 * 3. Comment   : 청구전요금조정
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param BillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "billingAfterAdjust", method = RequestMethod.POST)
	public String billingAfterAdjust(Model model, BillingAdjustVO billingAdjustVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		return URL + "/billingAfterAdjust";
	}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getPymList
	 * 2. ClassName : BillingAfterAdjustController
	 * 3. Comment   : 납부자 정보 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param BillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "getPymList", method = RequestMethod.POST)
	public  String getPymList(BillingAdjustVO billingAdjust, Model model, HttpServletRequest request) throws Exception {
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		billingAdjust.setLngTyp(lngTyp);
		
		List<BillingAdjustVO> pymList = new ArrayList<BillingAdjustVO>();
		
		System.out.println(">> getPymList << ");
		System.out.println("## SO_ID   : " +billingAdjust.getSoId());
		System.out.println("## CUST_ID : " +billingAdjust.getCustNm());
		
		pymList = billingAfterAdjustService.getPymList(billingAdjust);
		
		model.addAttribute("pymList", pymList);
		model.addAttribute("pymListCnt", pymList.size());
		
		return URL + "/billingAfterAdjust";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getPymBillList
	 * 2. ClassName : BillingAfterAdjustController
	 * 3. Comment   : 납부자 빌링 정보 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param BillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "getBillList", method = RequestMethod.POST)
	public  void getBillList(BillingAdjustVO billingAdjust, Model model, HttpServletRequest request) throws Exception {
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		billingAdjust.setLngTyp(lngTyp);
		
		List<BillingAdjustVO> BillList = new ArrayList<BillingAdjustVO>();
		
		System.out.println(">> getPymBillList << ");
		System.out.println("## SO_ID       : " +billingAdjust.getSoId());
		System.out.println("## PYM_ACNT_ID : " +billingAdjust.getPymAcntId());
		System.out.println("## ADJ_PT      : " +billingAdjust.getAdjPt());
		
		BillList = billingAfterAdjustService.getBillList(billingAdjust);
		
		model.addAttribute("rows", BillList);
	}
	
	
	@RequestMapping(value = "openAfterAdjReqPopup", method = RequestMethod.POST)
	public String openCustChngHistPopup(BillingAdjustVO billingAdjust, Model model, HttpServletRequest request) throws ServiceException{
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		billingAdjust.setLngTyp(lngTyp);
		
		System.out.println(">> openCustChngHistPopup << ");
		System.out.println("## PYM_ACNT_ID : " +billingAdjust.getPymAcntId());
		System.out.println("## SO_ID : " + billingAdjust.getSoId());
		System.out.println("## ADJ_NO : " + billingAdjust.getAdjNo());
		System.out.println("## ADJ_PT : " + billingAdjust.getAdjPt());
		System.out.println("## CLS_TSK_CL : " + billingAdjust.getClsTskCl());
		System.out.println("## BILL_CYCL : " + billingAdjust.getBillCycl());
		System.out.println("## LNG_TYP : " + billingAdjust.getLngTyp());
		System.out.println("## BILL_SEQ_NO : " + billingAdjust.getBillSeqNo());
		System.out.println("## BILL_YYMM : " + billingAdjust.getBillYymm());
		
		model.addAttribute("billAfterAdj", billingAdjust);
		model.addAttribute("pymRcpt", billingAfterAdjustService.getPymRcpt(billingAdjust));
		model.addAttribute("adjRsnCd", commonDataService.getCommonCodeList("BL00024", lngTyp));
		
		return  URL + "/ajax/billingAfterAdjReq";
	}
	
	@RequestMapping(value = "getAdjTgtList", method = RequestMethod.POST)
	public String getAdjTgtList(BillingAdjustVO billingAdjust, Model model, HttpServletRequest request) throws ServiceException{
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage").toString();
		billingAdjust.setLngTyp(lngTyp);
		
		System.out.println(">> Popup List << ");
		System.out.println("## SO_ID : " + billingAdjust.getSoId());
		System.out.println("## ADJ_NO : " + billingAdjust.getAdjNo());
		System.out.println("## ADJ_PT : " + billingAdjust.getAdjPt());
		System.out.println("## LNG_TYP : " + billingAdjust.getLngTyp());
		System.out.println("## BILL_SEQ_NO : " + billingAdjust.getBillSeqNo());
		System.out.println("## BILL_YYMM : " + billingAdjust.getBillYymm());
		
		List<BillingAdjustVO> adjTgtList = new ArrayList<BillingAdjustVO>();
		
		adjTgtList = billingAdjustService.getAdjTgtList(billingAdjust);
		
		model.addAttribute("adjTgtList", adjTgtList);
		
		return  URL + "/ajax/billingAfterAdjReq";
	}
	
	@RequestMapping(value = "modAdjTgtList", method = RequestMethod.POST)
	public void modAdjTgtList(
			@RequestBody AdjustVO adjust
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		System.out.println(">> modAdjTgtList << ");
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		
		String gubun = adjust.getGubun();
		BillingAdjustVO adjAply = adjust.getAdj();
		List<BillingAdjustVO> adjAplyDtl  = adjust.getAdjDtl();
		
		adjAply.setRegrId(session.getUserId());
		adjAply.setLngTyp(lngTyp);
		
		int result = billingAfterAdjustService.modAdjTgtList(adjAply, adjAplyDtl, gubun);
		
		model.addAttribute("result", result);
	
	}
	
	@RequestMapping(value = "cancelAdjList", method = RequestMethod.POST)
	public void cancelAdjList(
			@RequestBody BillingAdjustVO billingAdjust
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		System.out.println(">> modAdjTgtList << ");
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		int result = billingAfterAdjustService.cancelAdjList(billingAdjust);
		
		model.addAttribute("result", result);
	
	}
}
