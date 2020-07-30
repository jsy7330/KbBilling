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
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingBeforeAdjustService;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/billing/billingAdjust/billingBeforeAdjust")
public class BillingBeforeAdjustController {

	private static String URL = "charge/billing/billingAdjust/billingBeforeAdjust";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private BillingBeforeAdjustService billingBeforeAdjustService;
	
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
	 * 1. MethodName: billingBeforeAdjust
	 * 2. ClassName : BillingBeforeAdjustController
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
	@RequestMapping(value = "billingBeforeAdjust", method = RequestMethod.POST)
	public String billingBeforeAdjust(Model model, BillingAdjustVO billingAdjustVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		return URL + "/billingBeforeAdjust";
	}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getPymList
	 * 2. ClassName : BillingBeforeAdjustController
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
		
		pymList = billingBeforeAdjustService.getPymList(billingAdjust);
		
		model.addAttribute("pymList", pymList);
		model.addAttribute("pymListCnt", pymList.size());
		
		return URL + "/billingBeforeAdjust";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getPymBillList
	 * 2. ClassName : BillingBeforeAdjustController
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
	@RequestMapping(value = "getPymBillList", method = RequestMethod.POST)
	public  void getPymBillList(BillingAdjustVO billingAdjust, Model model, HttpServletRequest request) throws Exception {
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		billingAdjust.setLngTyp(lngTyp);
		
		List<BillingAdjustVO> pymBillList = new ArrayList<BillingAdjustVO>();
		
		System.out.println(">> getPymBillList << ");
		System.out.println("## SO_ID       : " +billingAdjust.getSoId());
		System.out.println("## PYM_ACNT_ID : " +billingAdjust.getPymAcntId());
		System.out.println("## ADJ_PT      : " +billingAdjust.getAdjPt());
		
		pymBillList = billingBeforeAdjustService.getPymBillList(billingAdjust);
		
		model.addAttribute("rows", pymBillList);
	}
	
	
	/*
	 * @RequestMapping(value = "openBeforeAdjReqPopup", method = RequestMethod.POST)
	 * public String openCustChngHistPopup(BillingAdjustVO billingAdjust, Model
	 * model, HttpServletRequest request) throws ServiceException{ String lng =
	 * (String)request.getSession().getAttribute("sessionLanguage");
	 * 
	 * System.out.println(">> openCustChngHistPopup << ");
	 * System.out.println("## PYM_ACNT_ID : " +billingAdjust.getPymAcntId());
	 * 
	 * model.addAttribute("billBefAdj", billingAdjust);
	 * model.addAttribute("adjApprover",
	 * commonDataService.getCommonCodeList("BL00042", lng));
	 * model.addAttribute("adjRsn", commonDataService.getCommonCodeList("BL00024",
	 * lng));
	 * 
	 * return URL + "/ajax/billingBeforeAdjReq"; }
	 */
	
	@RequestMapping(value = "getAdjTgtList", method = RequestMethod.POST)
	public String getAdjTgtList(BillingAdjustVO param, Model model, HttpServletRequest request) throws ServiceException{
		
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		param.setLngTyp(lngTyp);
		
		System.out.println(">> Popup List << ");
		System.out.println("## SO_ID : " + param.getSoId());
		System.out.println("## ADJ_NO : " + param.getAdjNo());
		System.out.println("## ADJ_PT : " + param.getAdjPt());
		System.out.println("## CLS_TSK_CL : " + param.getClsTskCl());
		System.out.println("## BILL_CYCL : " + param.getBillCycl());
		System.out.println("## LNG_TYP : " + param.getLngTyp());
		System.out.println("## PYM_ACNT_ID : " + param.getPymAcntId());
		
		List<BillingAdjustVO> adjTgtList = new ArrayList<BillingAdjustVO>();
		//List<BillingAdjustVO> billClsInfo = new ArrayList<BillingAdjustVO>();
		
		adjTgtList = billingAdjustService.getAdjTgtList(param);
		//billClsInfo = billingAdjustService.getBillClsInfo(param);
		
		model.addAttribute("adjTgtList", adjTgtList);
		//model.addAttribute("billClsInfo", billClsInfo);
		
		return  URL + "/ajax/billingBeforeAdjReq";
	}
	
	@RequestMapping(value = "openBeforeAdjReqPopup", method = RequestMethod.POST)
	public String openBeforeAdjReqPopup(BillingAdjustVO billingAdjust, Model model, HttpServletRequest request) throws ServiceException{
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
		
		model.addAttribute("billBeforeAdj", billingAdjust);
		model.addAttribute("pymRcpt", billingBeforeAdjustService.getPymRcpt(billingAdjust));
		model.addAttribute("adjRsnCd", commonDataService.getCommonCodeList("BL00024", lngTyp));
		
		return  URL + "/ajax/billingBeforeAdjReq";
	}
	
	 /**
	 * <PRE>
	 * 1. MethodName: getApplBeforeCount
	 * 2. ClassName : BillingBeforeAdjustController
	 * 3. Comment   : 청구된 년월과 신청된 조정과 조정상세 COUNT
	 * 4. 작성자    : 
	 * 5. 작성일    : 2020. 7. 29. 오전 11:04:49
	 * </PRE>
	 *   @return String
	 *   @param billingAdjust
	 *   @param model
	 *   @param request
	 *   @return
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getApplBeforeCount", method = RequestMethod.POST)
	 public String getApplBeforeCount(BillingAdjustVO billingAdjust, Model model,HttpServletRequest request) throws ServiceException{ 
		 
		int applYymmCount = 0; //청구진행중인 신청건
		int applHopeYymm = 0;  //해당 희망청구년월 신청건
		int resultCount = 0;   //신청건 여부
		
		applYymmCount = (int) billingBeforeAdjustService.getApplYymmCount(billingAdjust);
		
		applHopeYymm = billingBeforeAdjustService.getApplHopeYymm(billingAdjust);
		if(applYymmCount != 0) {
			model.addAttribute("resultCount",-1); // -1 구분값 : 청구작업진행중
		}else if(applHopeYymm != 0) {
			model.addAttribute("resultCount",-2); // -2 구분값 : 해당희망청구년월 신청건 존재
		}else {
			resultCount= (int) billingBeforeAdjustService.getApplBeforeCount(billingAdjust);
			model.addAttribute("resultCount",resultCount);
		}
		  
		 return URL + "/ajax/billingBeforeAdjReq"; 
		 
	 }
	 
	 
	 /**
	 * <PRE>
	 * 1. MethodName: reqAppl
	 * 2. ClassName : BillingBeforeAdjustController
	 * 3. Comment   : 청구전 요금조정 신청 
	 * 4. 작성자    : 
	 * 5. 작성일    : 2020. 7. 29. 오전 10:05:06
	 * </PRE>
	 *   @return void
	 *   @param adjust
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	 
	@RequestMapping(value = "reqAppl", method = RequestMethod.POST) 
	 public void reqAppl(@RequestBody AdjustVO adjust, Model model, HttpServletRequest request ) throws Exception{	 		
	 
		 String gubun = adjust.getGubun();							//INSERT UPDATE 구분
		 BillingAdjustVO adjAply = adjust.getAdj();					//메인신청 정보
		 List<BillingAdjustVO> adjAplyDtl  = adjust.getAdjDtl();	//세부신청 정보 리스트		
		 
		 int result = billingBeforeAdjustService.modAdjTgtList(adjAply, adjAplyDtl, gubun);
		 
		 model.addAttribute("result", result);
	 }
	 
	 @RequestMapping(value = "cancelAdjList", method = RequestMethod.POST)
	 public String cancelAdjList(BillingAdjustVO billingAdjust, Model model,HttpServletRequest request) throws ServiceException{ 
		 
		 String lngTyp =(String)request.getSession().getAttribute("sessionLanguage");
		 billingAdjust.setLngTyp(lngTyp);
		 
		 int result =  billingBeforeAdjustService.deleteReqDtlAppl(billingAdjust);	//세부신청 정보삭제
		 
		 if(result != 0) {
			 result +=  billingBeforeAdjustService.deleteReqAppl(billingAdjust);	//메인신청 정보삭제
		 }
		 
		 model.addAttribute("resultCount",result);
	 
		 return URL + "/ajax/billingBeforeAdjReq"; 
		 
	 }
}
