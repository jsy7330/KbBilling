package com.ntels.ccbs.charge.controller.billing.billing;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.billing.billing.BillingStatisticsVO;
import com.ntels.ccbs.charge.domain.charge.calculationSearch.PaymentChargeCalculationVo;
import com.ntels.ccbs.charge.service.billing.billing.BillingStatisticsService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Controller
@RequestMapping(value = "/charge/billing/billing/billingStatistics")
public class BillingStatisticsController {

	private static String URL = "charge/billing/billing/billingStatistics";

	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonDataService commonDataService;

	@Autowired
	private BillingStatisticsService billingStatisticsService;

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: billingStatisticsList
	 * 2. ClassName : BillingStatisticsController
	 * 3. Comment   : 수납내역 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 * 
	 * @return String
	 * @param model
	 * @param BillingInfoItemVO
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "billingStatisticsList", method = RequestMethod.POST)
	public String billingStatisticsList(Model model, BillingStatisticsVO billingStatistics, HttpServletRequest request)
			throws Exception {

		String lngTyp = (String) request.getSession().getAttribute("sessionLanguage");

		model.addAttribute("dateTp", commonDataService.getCommonCodeList("BL00122", lngTyp)); // 일자구분

		return URL + "/billingStatisticsList";
	}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: billDtlListAction
	 * 2. ClassName : BillingStatisticsController
	 * 3. Comment   : 빌링내역조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 * 
	 * @return void
	 * @param BillingInfoItemVO
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "billDtlListAction", method = RequestMethod.POST)
	public void billDtlListAction(BillingStatisticsVO billingStatistics, Model model, HttpServletRequest request)
			throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		billingStatistics.setLngTyp(lngTyp);

		System.out.println(">> billDtlListAction << ");
		System.out.println("## PYM_ACNT_ID : " + billingStatistics.getPymAcntId());
		System.out.println("## BILL_YYMM : " + billingStatistics.getBillYymm());
		System.out.println("## SO_ID : " + billingStatistics.getSoId());

		List<BillingStatisticsVO> billDtlList = new ArrayList<BillingStatisticsVO>();

		billDtlList = billingStatisticsService.billDtlList(billingStatistics);

		model.addAttribute("rows", billDtlList); // 목록리스트
	}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: ctrtDtlAction
	 * 2. ClassName : BillingStatisticsController
	 * 3. Comment   : 계약별 빌링내역 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 * 
	 * @return void
	 * @param BillingInfoItemVO
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "ctrtDtlAction", method = RequestMethod.POST)
	public void ctrtDtlAction(BillingStatisticsVO billingStatistics, Model model, HttpServletRequest request)
			throws Exception {
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		billingStatistics.setLngTyp(lngTyp);

		List<BillingStatisticsVO> ctrtDtlList = new ArrayList<BillingStatisticsVO>();

		System.out.println(">> ctrtDtlAction << ");
		System.out.println("## BILL_SEQ_NO : " + billingStatistics.getBillSeqNo());
		System.out.println("## PYM_ACNT_ID : " + billingStatistics.getPymAcntId());
		System.out.println("## BILL_YYMM : " + billingStatistics.getBillYymm());
		System.out.println("## BILL_DT : " + billingStatistics.getBillDt());

		ctrtDtlList = billingStatisticsService.ctrtDtlList(billingStatistics);

		model.addAttribute("rows", ctrtDtlList); // 목록리스트
	}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: recDetailsPerChgItmAction
	 * 2. ClassName : BillingStatisticsController
	 * 3. Comment   : 수납 상세 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 * 
	 * @return void
	 * @param BillingInfoItemVO
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "chrgItmDtlAction", method = RequestMethod.POST)
	public void chrgItmDtlAction(BillingStatisticsVO billingStatistics, Model model, HttpServletRequest request)
			throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		billingStatistics.setLngTyp(lngTyp);

		List<BillingStatisticsVO> chrgItmDtlList = new ArrayList<BillingStatisticsVO>();

		chrgItmDtlList = billingStatisticsService.chrgItmDtlList(billingStatistics);

		model.addAttribute("rows", chrgItmDtlList); // 목록리스트

	}

	@RequestMapping(value = "getBillInvoiceListAction", method = RequestMethod.POST)
	public void getBillInvoiceListAction(BillingStatisticsVO billingStatisticsVO, Model model,
			HttpServletRequest request) throws Exception {

		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		billingStatisticsVO.setLngTyp(lngTyp);

		int count = 0;
		count = billingStatisticsService.getBillInvoiceCount(billingStatisticsVO);

		List<BillingStatisticsVO> returnList = new ArrayList<BillingStatisticsVO>();
		if (count > 0) {
			returnList = billingStatisticsService.getBillInvoiceList(billingStatisticsVO, page, perPageRow);
		}

		model.addAttribute("rows", returnList); // 목록리스트
		model.addAttribute("total", Math.ceil((double) count / (double) perPageRow)); // 페이지
																						// 사이즈
		model.addAttribute("records", returnList.size()); // 현화면의 리스트갯수
		model.addAttribute("page", page); // 현재 페이지
		model.addAttribute("perpage", perPageRow);
	}

	// 빌링상세 조회
	@RequestMapping(value = "getBillDtlList", method = RequestMethod.POST)
	public void getBillDtlList(Model model, BillingStatisticsVO billingStatistics, HttpServletRequest request) {

		List<BillingStatisticsVO> list = billingStatisticsService.getBillDtlList(billingStatistics); 

		model.addAttribute("rows", list);
	}

}
