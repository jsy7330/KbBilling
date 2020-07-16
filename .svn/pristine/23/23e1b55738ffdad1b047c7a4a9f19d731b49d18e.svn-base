package com.ntels.ccbs.charge.controller.payment.payment;

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

import com.ntels.ccbs.charge.domain.payment.payment.ReceiptDetailVO;
import com.ntels.ccbs.charge.service.payment.payment.ReceiptDetailService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/payment/payment/receiptDetail")
public class ReceiptDetailController {
	
	private static String URL = "charge/payment/payment/receiptDetail";
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ReceiptDetailService receiptDetailService;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: ReceiptDetailList
	 * 2. ClassName : ReceiptDetailController
	 * 3. Comment   : 수납내역 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param BillingInfoItemVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "receiptDetailList", method = RequestMethod.POST)
	public String receiptDetailList(Model model, ReceiptDetailVO receiptDetailVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("dateTp", commonDataService.getCommonCodeList("BL00122", lngTyp));	//일자구분
		
		return URL + "/receiptDetailList";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: recStatisticsListAction
	 * 2. ClassName : ReceiptDetailController
	 * 3. Comment   : 수납 통계 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param ReceiptDetailVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "recStatisticsListAction", method = RequestMethod.POST)
	public void recStatisticsListAction(
			ReceiptDetailVO receiptDetail
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		receiptDetail.setLngTyp(lngTyp);
		
		List<ReceiptDetailVO> pymStaticsList = new ArrayList<ReceiptDetailVO>();
		
		pymStaticsList = receiptDetailService.receiptStatisticsList(receiptDetail);
		
		model.addAttribute("rows", pymStaticsList);	// 목록리스트
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: recDetailsAction
	 * 2. ClassName : ReceiptDetailController
	 * 3. Comment   : 수납 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param ReceiptDetailVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "recDetailsAction", method = RequestMethod.POST)
	public void recDetailsAction(
			ReceiptDetailVO receiptDetail
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		receiptDetail.setLngTyp(lngTyp);
		
		List<ReceiptDetailVO> pymList = new ArrayList<ReceiptDetailVO>();
		
		pymList = receiptDetailService.recList(receiptDetail);
		
		model.addAttribute("rows", pymList);	// 목록리스트
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: recDetailsPerChgItmAction
	 * 2. ClassName : ReceiptDetailController
	 * 3. Comment   : 수납 상세 조회
	 * 4. 작성자    : kih
	 * 5. 작성일    : 2016. 7. 12. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param ReceiptDetailVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "recDetailsPerChgItmAction", method = RequestMethod.POST)
	public void recDetailsPerChgItmAction(
			ReceiptDetailVO receiptDetail
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		receiptDetail.setLngTyp(lngTyp);
		
		List<ReceiptDetailVO> pymDtlList = new ArrayList<ReceiptDetailVO>();
		
		pymDtlList = receiptDetailService.recDtlList(receiptDetail);
		
		model.addAttribute("rows", pymDtlList);	// 목록리스트
		
	}

}
