package com.ntels.ccbs.charge.controller.billing.billing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.charge.domain.billing.billing.BillingCycleInfoVO;
import com.ntels.ccbs.charge.service.billing.billing.BillingCycleInfoService;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/billing/billing/billingCycleInfo")
public class BillingCycleInfoController {
	
	private static String URL = "charge/billing/billing/billingCycleInfo";
	
	@Autowired
	private BillingCycleInfoService billingCycleInfoService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: billingCycleInfoList
	 * 2. ClassName : BillingCycleInfoController
	 * 3. Comment   : 빌링 주기설정정보 관리 화면
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return String
	 *   @param model
	 *   @param BillingInfoItemVO
	 *   @param request
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "billingCycleInfoList", method = RequestMethod.POST)
	public String billingCycleInfoList(Model model, BillingCycleInfoVO billingCycleInfoVO, HttpServletRequest request) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("searchReferenceTypeList", commonDataService.getCommonCodeList("BL00013", lngTyp));		//빌링설정위치
		model.addAttribute("billYymmList", getBillYymmList(billingCycleInfoVO, 6, lngTyp)); // 청구년월(5개월)
		
		
		return URL + "/billingCycleInfoList";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: billingCycleInfoListAction
	 * 2. ClassName : BillingCycleInfoController
	 * 3. Comment   : 빌링 주기설정정보 조회
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param BillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	@RequestMapping(value = "BillingCycleInfoListAction", method = RequestMethod.POST)
	public void billingCycleInfoListAction(
			BillingCycleInfoVO billingCycleInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		String sidx = request.getParameter("sidx");  // Order by 대상 컬럼명
		String sord  = request.getParameter("sord"); // Asc 인지 DESC 인지 구분
		
		billingCycleInfoVO.setLngTyp(lngTyp);
		billingCycleInfoVO.setSidx(sidx);
		billingCycleInfoVO.setSord(sord);
		
        List<BillingCycleInfoVO> list = new ArrayList<BillingCycleInfoVO>();
		int count = 0;
		count = billingCycleInfoService.BillingCycleInfoListCount(billingCycleInfoVO);		
		if (count > 0) list = billingCycleInfoService.BillingCycleInfoListAction(billingCycleInfoVO, page, perPageRow);

		model.addAttribute("rows", list);	// 목록리스트
		model.addAttribute("total", Math.ceil((double)count / (double)perPageRow)); // 페이지 사이즈
		model.addAttribute("records", list.size()); //현화면의 리스트갯수		
		model.addAttribute("page", page); 			//현재 페이지
		model.addAttribute("perpage", perPageRow);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getBillYymmList
	 * 2. ClassName : BillingCycleInfoController
	 * 3. Comment   : 빌링 청구년월 (+n개월) 가져오기
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param BillingInfoItemVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	public List<String> getBillYymmList(BillingCycleInfoVO billingCycleInfoVO, int nMonth, String lngTyp) {
		
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		for(int i=0; i<=nMonth; i++) {
			
			SimpleDateFormat billYymmFormat = new SimpleDateFormat("yyyyMM");
			String billYymmList = billYymmFormat.format(cal.getTime());
			
			if(lngTyp.equals("ko")) {
				String koBillYymmMonth = "";
				String koBillYymmDay = "";
				String koOperator = "";
				koBillYymmMonth = billYymmList.substring(0, 4);
				koBillYymmDay = billYymmList.substring(4, 6);
				koOperator = "-";
				
				billingCycleInfoVO.setBillYymm(koBillYymmMonth + koOperator + koBillYymmDay);
			}
			else if(lngTyp.equals("en")) {
				String enBillYymmMonth = "";
				String enBillYymmDay = "";
				String enOperator = "";
				enBillYymmMonth = billYymmList.substring(0, 4);
				enBillYymmDay = billYymmList.substring(4, 6);
				enOperator = "/";
				
				billingCycleInfoVO.setBillYymm(enBillYymmDay + enOperator + enBillYymmMonth);
			}
			list.add(i, billingCycleInfoVO.getBillYymm());
			
			cal.add(Calendar.MONTH, 1);
		}
		return list;
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertBillingCycleInfoPopUp
	 * 2. ClassName : BillingCycleInfoController
	 * 3. Comment   : 빌링 주기설정정보 등록 팝업
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return String
	 *   @param billingCycleInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "billingCycleInfoInsertPopUp", method = RequestMethod.POST)
	public String insertBillingCycleInfoPopUp(
			BillingCycleInfoVO billingCycleInfoVO
		      , Model model
		      , HttpServletRequest request
			  ) throws Exception {
		
		String lngTyp = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("billYymmList", getBillYymmList(billingCycleInfoVO, 4, lngTyp)); // 청구년월(5개월)
		
		return URL + "/ajax/billingCycleInfoInsertPopUp";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertBillingCycleInfo
	 * 2. ClassName : BillingCycleInfoController
	 * 3. Comment   : 빌링 주기설정정보 등록
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param billingCycleInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "insertBillingCycleInfo", method = RequestMethod.POST)
	public void insertBillingCycleInfo(
			BillingCycleInfoVO billingCycleInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {

		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		billingCycleInfoVO.setLngTyp(lngTyp);		
		int result = billingCycleInfoService.insertBillingCycleInfo(billingCycleInfoVO);
		
		model.addAttribute("result", result);
	
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateBillingCycleInfo
	 * 2. ClassName : BillingCycleInfoController
	 * 3. Comment   : 빌링 주기설정정보 수정
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param billingCycleInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	/*
	@RequestMapping(value = "updateBillingCycleInfo", method = RequestMethod.POST)
	public String updateBillingCycleInfo(
	        Model model
	      , HttpServletRequest request
	      , @RequestBody Map<String, Object> billingCycleInfoVO
		  ) throws Exception {
		
		System.out.println(">> updateBillingCycleInfo << ");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		BillingCycleInfoVO billingCycleInfo = new BillingCycleInfoVO();
		billingCycleInfo.setSetVal((String) billingCycleInfoVO.get("setVal"));
		billingCycleInfo.setUpdateSetValList((List<Map<String,Object>>) billingCycleInfoVO.get("updateSetValList"));
		
		try{
			billingCycleInfoService.updateBillingCycleInfo(billingCycleInfo);
		}catch(ServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new ServiceException( "MSG.M10.MSG00005" );
		}
		
		return URL + "/ajax/billingCycleInfoList";	
	}
	*/
	
	@RequestMapping(value = "updateBillingCycleInfo", method = RequestMethod.POST)
	public void updateBillingCycleInfo(
			@RequestBody List<BillingCycleInfoVO> billingCycleInfoVOList
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		int result = billingCycleInfoService.updateBillSetupItemCycle(billingCycleInfoVOList, session.getUserId());
		
		model.addAttribute("result", result);
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: copyBillingCycleInfo
	 * 2. ClassName : BillingCycleInfoController
	 * 3. Comment   : 빌링 설정정보 전월 복사
	 * 4. 작성자    : jhpark
	 * 5. 작성일    : 2016. 7. 15. 오후 8:51:42
	 * </PRE>
	 *   @return void
	 *   @param billingCycleInfoVO
	 *   @param model
	 *   @param request
	 *   @throws Exception
	 */
	
	@RequestMapping(value = "copyBillingCycleInfo", method = RequestMethod.POST)
	public void copyBillingCycleInfo(
			BillingCycleInfoVO billingCycleInfoVO
	      , Model model
	      , HttpServletRequest request
		  ) throws Exception {
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String lngTyp = request.getSession().getAttribute("sessionLanguage").toString();
		
		billingCycleInfoVO.setLngTyp(lngTyp);
		billingCycleInfoVO.setRegId(session.getUserId());
		billingCycleInfoVO.setChgId(session.getUserId());
		billingCycleInfoVO.setRegDate(DateUtil.sysdate());
		billingCycleInfoVO.setChgDate(DateUtil.sysdate());

		int result = billingCycleInfoService.copyBillingCycleInfo(billingCycleInfoVO);
		
		model.addAttribute("result", result);
	
	}
	
}
