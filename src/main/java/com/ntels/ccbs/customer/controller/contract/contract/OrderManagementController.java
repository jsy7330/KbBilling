package com.ntels.ccbs.customer.controller.contract.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.CtrtPromVO;
import com.ntels.ccbs.customer.domain.contract.contract.CustInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.PrecheckInfoVO;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountInfoVO;
import com.ntels.ccbs.customer.service.contract.contract.OrderManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderProcessService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


/**
 * <PRE>
 * 1. ClassName: OrderManagementController
 * 2. FileName : OrderManagementController.java
 * 3. Package  : com.ntels.ccbs.customer.controller.contract.contract
 * 4. Comment  : 오더 관리 컨트롤러
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 7. 오후 7:52:50
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 7.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/customer/contract/contract/orderManagement")
	public class OrderManagementController {
	
	private static String URL = "customer/contract/contract/orderManagement";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private OrderManagementService orderManagementService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private OrderProcessService[] orderProcessService;
	
	/**
	 * <PRE>
	 * 1. MethodName: getAddonProdList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 기본상품에 종속된 부가상품 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 6:56:10
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param basicProdGrp 상품그룹
	 *   @param basicProdCd 상품코드
	 */
	@RequestMapping(value = "getAddonProdListAction", method = RequestMethod.POST)
	public String getAddonProdList(Model model, HttpServletRequest request, String soId, String basicProdGrp, String basicProdCd, String StartDt) {
		
		String today = StartDt;
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<Map<String,Object>> addProdList = orderManagementService.getAddProdList(soId, basicProdGrp, basicProdCd, today, lng);
		model.addAttribute("addProdList", addProdList);
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getBasicProdGrp
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 기본상품그룹조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오전 10:10:21
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param basicSvcCd 서비스코드
	 *   @param custTp 고객유형
	 */
	@RequestMapping(value = "getBasicProdGrpAction", method = RequestMethod.POST)
	public String getBasicProdGrp(Model model, HttpServletRequest request, String soId, String basicSvcCd, String custTp) {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<Map<String,Object>> basicProdGrpList = orderManagementService.getBasicProdGrpList(soId, basicSvcCd, custTp, today, lng);
		model.addAttribute("basicProdGrpList", basicProdGrpList);
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getBasicProdList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 기본상품조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오전 11:02:22
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param basicSvcCd 서비스코드
	 *   @param basicProdGrp 상품그룹
	 */
	@RequestMapping(value = "getBasicProdListAction", method = RequestMethod.POST)
	public String getBasicProdList(Model model, HttpServletRequest request, String soId, String basicSvcCd, String basicProdGrp, String custTp) {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<Map<String,Object>> basicProdList = orderManagementService.getBasicProdList(soId, basicSvcCd, basicProdGrp, custTp, today, lng);
		model.addAttribute("basicProdList", basicProdList);
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getBasinProdDetailInfo
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 기본상품의 부가 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 1:38:04
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param basicProdGrp 상품그룹
	 *   @param basicProdCd 상품코드
	 */
	@RequestMapping(value = "getBasinProdDetailInfo", method = RequestMethod.POST)
	public String getBasinProdDetailInfo(Model model, HttpServletRequest request, String soId, String basicProdGrp, String basicProdCd) {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<CommonCodeVO> penatyCodeList = orderManagementService.getPenaltyListByBasicProdCd(soId, basicProdGrp, basicProdCd, today, lng);

		Map<String, Object> rateInfo_007 = orderManagementService.getProdRateInfoByChargeType(soId, basicProdCd, "CT007", today); //약정위약금
		Map<String, Object> rateInfo_008 = orderManagementService.getProdRateInfoByChargeType(soId, basicProdCd, "CT008", today); //기본료
		Map<String, Object> rateInfo_004 = orderManagementService.getProdRateInfoByChargeType(soId, basicProdCd, "CT004", today); //설치비
		Map<String, Object> rateInfo_009 = orderManagementService.getProdRateInfoByChargeType(soId, basicProdCd, "CT009", today); //유지보수비
		Map<String, Object> rateInfo_010 = orderManagementService.getProdRateInfoByChargeType(soId, basicProdCd, "CT010", today); //임대료
		
		
		
		model.addAttribute("penatyCodeList", penatyCodeList);
		model.addAttribute("rateInfo_007", rateInfo_007);
		model.addAttribute("rateInfo_008", rateInfo_008);
		model.addAttribute("rateInfo_004", rateInfo_004);
		model.addAttribute("rateInfo_009", rateInfo_009);
		model.addAttribute("rateInfo_010", rateInfo_010);
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCodeList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 오더 조회 공통 코드 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 30. 오후 2:28:00
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getCodeListAction", method = RequestMethod.POST)
	public String getCodeList(Model model,
			HttpServletRequest request) throws ServiceException {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("stateCodeList", commonDataService.getCommonCodeList("CM00050", lng));
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCtrtHist
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 계약의 각 이력 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 4. 오후 1:50:52
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @return
	 */
	@RequestMapping(value = "getCtrtHistAction", method = RequestMethod.POST)
	public String getCtrtHist(Model model
			, HttpServletRequest request
			, String soId
			, String custId
			, String ctrtId) {
		
		
		
		//오더정보조회
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> ctrtHistInfo = orderManagementService.getCtrtHist(soId, custId, ctrtId, lng);
		model.addAttribute("basicHistList", ctrtHistInfo.get("basicHistList"));       // 기본계약이력정보
		model.addAttribute("deviceHistList", ctrtHistInfo.get("deviceHistList"));     // 장비이력정보
		model.addAttribute("addonHistList", ctrtHistInfo.get("addonHistList"));       // 부가서비스 이력 정보
		model.addAttribute("suspentionHistList", ctrtHistInfo.get("suspentionHistList"));   // 일시정지 이력 정보
		model.addAttribute("instlAddrHistList", ctrtHistInfo.get("instlAddrHistList"));     // 설치주소 이력 정보
		model.addAttribute("procRateHistList", ctrtHistInfo.get("procRateHistList"));       // 공정률 이력 정보
		model.addAttribute("payerHistList", ctrtHistInfo.get("payerHistList"));             // 납부자 이력 정보
		model.addAttribute("deviceProdList", ctrtHistInfo.get("deviceProdList")); //장비 상품 리스트
		model.addAttribute("addonProdList", ctrtHistInfo.get("addonProdList")); //부가 상품 리스트
		model.addAttribute("suspentionResnCodeList", ctrtHistInfo.get("suspentionResnCodeList")); //일시정지사유

		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getCtrtInfo
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 사용중인 계약의 기본 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 22. 오전 10:22:51
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @return
	 */
	@RequestMapping(value = "getCtrtInfoAction", method = RequestMethod.POST)
	public String getCtrtInfo(Model model
			, HttpServletRequest request
			, String soId
			, String custId
			, String ctrtId) {
		//오더정보조회
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Map<String,Object> contractInformationInUse = orderManagementService.getCtrtInfo(soId, custId, ctrtId, lng);
		model.addAttribute("basicProdInfo", contractInformationInUse.get("basicProdInfo"));    // 기본계약정보
		model.addAttribute("deviceInfoList", contractInformationInUse.get("deviceInfoList"));    // 사용중인 장비상품정보
		model.addAttribute("addInfoList", contractInformationInUse.get("addInfoList"));    // 사용중인 부가서비스정보
		model.addAttribute("monthlyFeeList", contractInformationInUse.get("monthlyFeeList"));    // 월정액 협정가 정보
		model.addAttribute("oneTimeFeeList", contractInformationInUse.get("oneTimeFeeList"));    // 일회성 협정가 정보

		return URL + "/orderCommonPopup";
	}
	

	/**
	 * <PRE>
	 * 1. MethodName: getCtrtSusHist
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 계약의 일시정지 이력 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 5. 오후 4:38:04
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 */
	@RequestMapping(value = "getCtrtSusHistAction", method = RequestMethod.POST)
	public String getCtrtSusHist(Model model
			, HttpServletRequest request
			, String soId
			, String custId
			, String ctrtId) {
		
		//오더정보조회
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("suspentionHistList", orderManagementService.getCtrtSusHist(soId, custId, ctrtId, lng));

		return URL + "/orderCommonPopup";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getDeviceProdList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 기본 상품에 종속된 장비 상품 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 3:36:20
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param basicProdGrp 상품그룹
	 *   @param basicProdCd 상품코드
	 */
	@RequestMapping(value = "getDeviceProdListAction", method = RequestMethod.POST)
	public String getDeviceProdList(Model model, HttpServletRequest request, String soId, String basicProdGrp, String basicProdCd) {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		List<Map<String,Object>> deviceProdList = orderManagementService.getDeviceProdList(soId, basicProdGrp, basicProdCd, today, lng);
		model.addAttribute("deviceProdList", deviceProdList);
		return URL + "/orderCommonPopup";
	}
	

	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoAddChangeInProgress
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 부가서비스 변경의 진행 오더 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoAddChangeInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoAddChangeInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);
		List<OProdCmpsVO> oprodCmpsList = orderManagementService.getOProdCmpsInfoList(soId, custId, ctrtId, orderId);
		List<OProdCmpsExtVO> oprodCmpsExtList = new ArrayList<OProdCmpsExtVO>();
		for(OProdCmpsVO prodCmps : oprodCmpsList){
			if(StringUtils.isEmpty(prodCmps.getExtId())){
				continue;
			}
			oprodCmpsExtList.add(orderManagementService.getOProdCmpsExtInfo(orderId, prodCmps.getExtId()));
		}
		List<OSvcCmpsVO> osvcCmpsList = orderManagementService.getOSvcCmpsInfoList(soId, custId, ctrtId, orderId);
		List<OSvcCmpsExtVO> osvcCmpsExtList = new ArrayList<OSvcCmpsExtVO>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			if(StringUtils.isEmpty(svcCmps.getExtId())){
				continue;
			}
			osvcCmpsExtList.add(orderManagementService.getOSvcCmpsExtInfo(orderId, svcCmps.getExtId()));
		}
		model.addAttribute("octrtMast", octrtMast);
		
		//부가상품리스트 조회
		List<Map<String,Object>> gridAddProdList = orderManagementService.getAddProdList(soId, octrtMast.getProdGrp(), octrtMast.getProdCd(), octrtMast.getOpenDd(), lng);
		model.addAttribute("gridAddProdList", gridAddProdList);
		
		//단말설정정보작성
		List<Map<String,Object>> addonConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> confAddon = new HashMap<String,Object>();
			for(Map<String,Object> addonInfo : gridAddProdList){
				if(svcCmps.getProdCd().equals((String)addonInfo.get("add_prod_cd")) && svcCmps.getSvcStsCd().equals("20")){
					confAddon.putAll(addonInfo);
					break;
				}
			}
			
			if(confAddon.isEmpty())
				continue;
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						confAddon.put("add_cnt", svcExt.getProdCnt());
						confAddon.put("monthly_fee", svcExt.getMonthlyAmt());
						confAddon.put("onetime_fee", svcExt.getOnetimeAmt());
						break;
					}
				}
			}
			addonConfList.add(confAddon);
		}
		model.addAttribute("gridAddConfList", addonConfList);
		
		//단말 삭제 정보 조회
		List<Map<String,Object>> delAddonList = new ArrayList<Map<String,Object>>(); 
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> delAddon = new HashMap<String,Object>();
			for(Map<String,Object> addInfo : gridAddProdList){
				if(svcCmps.getProdCd().equals((String)addInfo.get("add_prod_cd")) && svcCmps.getSvcStsCd().equals("90")){
					delAddon.putAll(addInfo);
					delAddon.put("prod_cmps_id", svcCmps.getProdCmpsId());
					break;
				}
			}
			
			if(delAddon.isEmpty())
				continue;
			delAddonList.add(delAddon);
		}
		model.addAttribute("delAddonList", delAddonList);
		
		
		//협정가 설정
		List<Map<String,Object>> monthlyConfList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> onetimeConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> prodInfo = null;
			String prodNm = null;
			String isNego = null;
			String isNegoNm = null;
			if(prodInfo == null){
				for(Map<String,Object> addonProdInfo : gridAddProdList){
					if(svcCmps.getProdCd().equals(addonProdInfo.get("add_prod_cd"))  && svcCmps.getSvcStsCd().equals("20")){
						prodInfo = addonProdInfo;
						prodNm = (String)addonProdInfo.get("add_prod_nm");
						isNego = (String)addonProdInfo.get("is_nego");
						isNegoNm = (String)addonProdInfo.get("is_nego_nm");
						break;
					}
				}
			}
			if(prodInfo == null) continue;
			
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						if(!"0".equals(svcExt.getMonthlyAmt())){
							Map<String,Object> monthlyConf = new HashMap<String,Object>();
							monthlyConf.put("so_id", svcCmps.getSoId());
							monthlyConf.put("svc_grp", svcCmps.getSvcGrp());
							monthlyConf.put("svc_cd", svcCmps.getSvcCd());
							monthlyConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
							monthlyConf.put("nego_prod_cd", svcCmps.getProdCd());
							monthlyConf.put("nego_prod_cd_nm", prodNm);
							monthlyConf.put("nego_cnt", svcExt.getProdCnt());
							monthlyConf.put("sale_amt", svcExt.getMonthlyAmt());
							monthlyConf.put("discount_rate", svcExt.getMonthlyNegoRate());
							monthlyConf.put("nego_amt", svcExt.getMonthlyNegoAmt());
							monthlyConf.put("is_nego", isNego);
							monthlyConf.put("is_nego_nm", isNegoNm);
							monthlyConfList.add(monthlyConf);
						}
						
						if(!"0".equals(svcExt.getOnetimeAmt())){
							Map<String,Object> onetimeConf = new HashMap<String,Object>();
							onetimeConf.put("so_id", svcCmps.getSoId());
							onetimeConf.put("svc_grp", svcCmps.getSvcGrp());
							onetimeConf.put("svc_cd", svcCmps.getSvcCd());
							onetimeConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
							onetimeConf.put("nego_prod_cd", svcCmps.getProdCd());
							onetimeConf.put("nego_prod_cd_nm", prodNm);
							onetimeConf.put("nego_cnt", svcExt.getProdCnt());
							onetimeConf.put("sale_amt", svcExt.getOnetimeAmt());
							onetimeConf.put("discount_rate", svcExt.getOnetimeNegoRate());
							onetimeConf.put("nego_amt", svcExt.getOnetimeNegoAmt());
							onetimeConf.put("is_nego", isNego);
							onetimeConf.put("is_nego_nm", isNegoNm);
							onetimeConfList.add(onetimeConf);
						}
					}
				}
			}
		}
		
		model.addAttribute("gridMonthlyFeeList", monthlyConfList);
		model.addAttribute("gridOnetimeFeeList", onetimeConfList);
		return URL + "/orderCommonPopup";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoChngAddrInProgressAction
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 설치 주소 변경의 진행중인 오더 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoChngAddrInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoChngAddrInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);
		model.addAttribute("zipCd", octrtMast.getInstlZipCd());
		model.addAttribute("basseAddr", octrtMast.getInstlBaseAddr());
		model.addAttribute("addrDtl", octrtMast.getInstlAddrDtl());
		model.addAttribute("city", octrtMast.getInstlCity());
		model.addAttribute("state", octrtMast.getInstlState());
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoChngPayerInProgressAction
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 납부자 변경의 진행중인 오더 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoChngPayerInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoChngPayerInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		//오더정보조회
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);
		
		List<PaymentAccountInfoVO> pymAcntList = orderManagementService.getPymAcntListForChange(soId, ctrtId, "C", octrtMast.getPymAcntId(), lng);
		model.addAttribute("pymAcntList", pymAcntList);
		model.addAttribute("totalCount", pymAcntList.size());

		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoDeviceChangeInProgress
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 장비 변경의 진행 오더 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoDeviceChangeInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoDeviceChangeInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);
		List<OProdCmpsVO> oprodCmpsList = orderManagementService.getOProdCmpsInfoList(soId, custId, ctrtId, orderId);
		List<OProdCmpsExtVO> oprodCmpsExtList = new ArrayList<OProdCmpsExtVO>();
		for(OProdCmpsVO prodCmps : oprodCmpsList){
			if(StringUtils.isEmpty(prodCmps.getExtId())){
				continue;
			}
			oprodCmpsExtList.add(orderManagementService.getOProdCmpsExtInfo(orderId, prodCmps.getExtId()));
		}
		List<OSvcCmpsVO> osvcCmpsList = orderManagementService.getOSvcCmpsInfoList(soId, custId, ctrtId, orderId);
		List<OSvcCmpsExtVO> osvcCmpsExtList = new ArrayList<OSvcCmpsExtVO>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			if(StringUtils.isEmpty(svcCmps.getExtId())){
				continue;
			}
			osvcCmpsExtList.add(orderManagementService.getOSvcCmpsExtInfo(orderId, svcCmps.getExtId()));
		}
		model.addAttribute("octrtMast", octrtMast);
		
		//단말상품리스트 조회
		List<Map<String,Object>> gridDeviceProdList = orderManagementService.getDeviceProdList(soId, octrtMast.getProdGrp(), octrtMast.getProdCd(), octrtMast.getOpenDd(), lng);
		model.addAttribute("gridDeviceProdList", gridDeviceProdList);
		
		//단말설정정보작성
		List<Map<String,Object>> deviceConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> confDevice = new HashMap<String,Object>();
			for(Map<String,Object> deviceInfo : gridDeviceProdList){
				if(svcCmps.getProdCd().equals((String)deviceInfo.get("device_prod_cd")) && svcCmps.getSvcStsCd().equals("20")){
					confDevice.putAll(deviceInfo);
					break;
				}
			}
			
			if(confDevice.isEmpty())
				continue;
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						confDevice.put("device_cnt", svcExt.getProdCnt());
						confDevice.put("monthly_fee", svcExt.getMonthlyAmt());
						confDevice.put("onetime_fee", svcExt.getOnetimeAmt());
						break;
					}
				}
			}
			deviceConfList.add(confDevice);
		}
		model.addAttribute("gridDeviceConfList", deviceConfList);
		
		//단말 삭제 정보 조회
		List<Map<String,Object>> delDeviceList = new ArrayList<Map<String,Object>>(); 
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> delDevice = new HashMap<String,Object>();
			for(Map<String,Object> deviceInfo : gridDeviceProdList){
				if(svcCmps.getProdCd().equals((String)deviceInfo.get("device_prod_cd")) && svcCmps.getSvcStsCd().equals("90")){
					delDevice.putAll(deviceInfo);
					delDevice.put("prod_cmps_id", svcCmps.getProdCmpsId());
					break;
				}
			}
			
			if(delDevice.isEmpty())
				continue;
			delDeviceList.add(delDevice);
		}
		model.addAttribute("delDeviceList", delDeviceList);
		
		
		//협정가 설정
		List<Map<String,Object>> monthlyConfList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> onetimeConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> prodInfo = null;
			String prodNm = null;
			String isNego = null;
			String isNegoNm = null;
			if(prodInfo == null){
				for(Map<String,Object> deviceProdInfo : gridDeviceProdList){
					if(svcCmps.getProdCd().equals(deviceProdInfo.get("device_prod_cd"))  && svcCmps.getSvcStsCd().equals("20")){
						prodInfo = deviceProdInfo;
						prodNm = (String)deviceProdInfo.get("device_prod_cd_nm");
						isNego = (String)deviceProdInfo.get("is_nego");
						isNegoNm = (String)deviceProdInfo.get("is_nego_nm");
						break;
					}
				}
			}
			if(prodInfo == null) continue;
			
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						if(!"0".equals(svcExt.getMonthlyAmt())){
							Map<String,Object> monthlyConf = new HashMap<String,Object>();
							monthlyConf.put("so_id", svcCmps.getSoId());
							monthlyConf.put("svc_grp", svcCmps.getSvcGrp());
							monthlyConf.put("svc_cd", svcCmps.getSvcCd());
							monthlyConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
							monthlyConf.put("nego_prod_cd", svcCmps.getProdCd());
							monthlyConf.put("nego_prod_cd_nm", prodNm);
							monthlyConf.put("nego_cnt", svcExt.getProdCnt());
							monthlyConf.put("sale_amt", svcExt.getMonthlyAmt());
							monthlyConf.put("discount_rate", svcExt.getMonthlyNegoRate());
							monthlyConf.put("nego_amt", svcExt.getMonthlyNegoAmt());
							monthlyConf.put("is_nego", isNego);
							monthlyConf.put("is_nego_nm", isNegoNm);
							monthlyConfList.add(monthlyConf);
						}
						
						if(!"0".equals(svcExt.getOnetimeAmt())){
							Map<String,Object> onetimeConf = new HashMap<String,Object>();
							onetimeConf.put("so_id", svcCmps.getSoId());
							onetimeConf.put("svc_grp", svcCmps.getSvcGrp());
							onetimeConf.put("svc_cd", svcCmps.getSvcCd());
							onetimeConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
							onetimeConf.put("nego_prod_cd", svcCmps.getProdCd());
							onetimeConf.put("nego_prod_cd_nm", prodNm);
							onetimeConf.put("nego_cnt", svcExt.getProdCnt());
							onetimeConf.put("sale_amt", svcExt.getOnetimeAmt());
							onetimeConf.put("discount_rate", svcExt.getOnetimeNegoRate());
							onetimeConf.put("nego_amt", svcExt.getOnetimeNegoAmt());
							onetimeConf.put("is_nego", isNego);
							onetimeConf.put("is_nego_nm", isNegoNm);
							onetimeConfList.add(onetimeConf);
						}
					}
				}
			}
		}
		
		model.addAttribute("gridMonthlyFeeList", monthlyConfList);
		model.addAttribute("gridOnetimeFeeList", onetimeConfList);
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoNewContractInProgress
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 신규 계약의 진행 오더 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoNewContractInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoNewContractInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);
		List<OProdCmpsVO> oprodCmpsList = orderManagementService.getOProdCmpsInfoList(soId, custId, ctrtId, orderId);
		List<OProdCmpsExtVO> oprodCmpsExtList = new ArrayList<OProdCmpsExtVO>();
		for(OProdCmpsVO prodCmps : oprodCmpsList){
			if(StringUtils.isEmpty(prodCmps.getExtId())){
				continue;
			}
			oprodCmpsExtList.add(orderManagementService.getOProdCmpsExtInfo(orderId, prodCmps.getExtId()));
		}
		List<OSvcCmpsVO> osvcCmpsList = orderManagementService.getOSvcCmpsInfoList(soId, custId, ctrtId, orderId);
		List<OSvcCmpsExtVO> osvcCmpsExtList = new ArrayList<OSvcCmpsExtVO>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			if(StringUtils.isEmpty(svcCmps.getExtId())){
				continue;
			}
			osvcCmpsExtList.add(orderManagementService.getOSvcCmpsExtInfo(orderId, svcCmps.getExtId()));
		}
		model.addAttribute("octrtMast", octrtMast);
		
		
		//기본상품리스트 조회
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String custTp = orderManagementService.getCustTp(soId, octrtMast.getCustId());
		List<Map<String,Object>> gridBasicProd = orderManagementService.getBasicProdList(soId, svcCd, octrtMast.getProdGrp(), custTp,  today, lng);
		model.addAttribute("gridBasicProd", gridBasicProd);
		//기본상품상세정보 조회
		List<CommonCodeVO> penatyCodeList = orderManagementService.getPenaltyListByBasicProdCd(soId, octrtMast.getProdGrp(), octrtMast.getProdCd(), today, lng);
		Map<String, Object> rateInfo_007 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT007", today); //약정위약금
		Map<String, Object> rateInfo_008 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT008", today); //기본료
		Map<String, Object> rateInfo_004 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT004", today); //설치비
		Map<String, Object> rateInfo_009 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT009", today); //유지보수비
		Map<String, Object> rateInfo_010 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT010", today); //임대료
		model.addAttribute("penatyCodeList", penatyCodeList);
		model.addAttribute("rateInfo_007", rateInfo_007);
		model.addAttribute("rateInfo_008", rateInfo_008);
		model.addAttribute("rateInfo_004", rateInfo_004);
		model.addAttribute("rateInfo_009", rateInfo_009);
		model.addAttribute("rateInfo_010", rateInfo_010);
		//단말상품리스트 조회
		List<Map<String,Object>> gridDeviceProdList = orderManagementService.getDeviceProdList(soId, octrtMast.getProdGrp(), octrtMast.getProdCd(), today, lng);
		model.addAttribute("gridDeviceProdList", gridDeviceProdList);
		
		//부가서비스리스트조회
		List<Map<String,Object>> gridAddProdList = orderManagementService.getAddProdList(soId,  octrtMast.getProdGrp(), octrtMast.getProdCd(), today, lng);
		model.addAttribute("gridAddProdList", gridAddProdList);
		
		//단말설정정보작성
		List<Map<String,Object>> deviceConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> confDevice = new HashMap<String,Object>();
			for(Map<String,Object> deviceInfo : gridDeviceProdList){
				if(svcCmps.getProdCd().equals((String)deviceInfo.get("device_prod_cd"))){
					confDevice.putAll(deviceInfo);
					break;
				}
			}
			
			if(confDevice.isEmpty())
				continue;
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						confDevice.put("device_cnt", svcExt.getProdCnt());
						confDevice.put("monthly_fee", svcExt.getMonthlyAmt());
						confDevice.put("onetime_fee", svcExt.getOnetimeAmt());
						break;
					}
				}
			}
			deviceConfList.add(confDevice);
		}
		model.addAttribute("gridDeviceConfList", deviceConfList);
		
		
		//부가설정정보작성
		List<Map<String,Object>> addConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> confAdd = new HashMap<String,Object>();
			for(Map<String,Object> addInfo : gridAddProdList){
				if(svcCmps.getProdCd().equals((String)addInfo.get("add_prod_cd"))){
					confAdd.putAll(addInfo);
					break;
				}
			}
			
			if(confAdd.isEmpty())
				continue;
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						confAdd.put("add_cnt", svcExt.getProdCnt());
						confAdd.put("monthly_fee", svcExt.getMonthlyAmt());
						confAdd.put("onetime_fee", svcExt.getOnetimeAmt());
						break;
					}
				}
			}
			addConfList.add(confAdd);
		}
		model.addAttribute("gridAddConfList", addConfList);
		
		//협정가 설정
		List<Map<String,Object>> monthlyConfList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> onetimeConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> prodInfo = null;
			String prodNm = null;
			String isNego = null;
			String isNegoNm = null;
			for(Map<String,Object> basicProdInfo : gridBasicProd){
				if(svcCmps.getProdCd().equals(basicProdInfo.get("basic_prod_cd"))){
					prodInfo = basicProdInfo;
					prodNm = (String)basicProdInfo.get("basic_prod_cd_nm");
					isNego = (String)basicProdInfo.get("is_nego");
					isNegoNm = (String)basicProdInfo.get("is_nego_nm");
					break;
				}
			}
			
			if(prodInfo == null){
				for(Map<String,Object> deviceProdInfo : gridDeviceProdList){
					if(svcCmps.getProdCd().equals(deviceProdInfo.get("device_prod_cd"))){
						prodInfo = deviceProdInfo;
						prodNm = (String)deviceProdInfo.get("device_prod_cd_nm");
						isNego = (String)deviceProdInfo.get("is_nego");
						isNegoNm = (String)deviceProdInfo.get("is_nego_nm");
						break;
					}
				}
			}
			if(prodInfo == null){
				for(Map<String,Object> addProdInfo : gridAddProdList){
					if(svcCmps.getProdCd().equals(addProdInfo.get("add_prod_cd"))){
						prodInfo = addProdInfo;
						prodNm = (String)addProdInfo.get("add_prod_nm");
						isNego = (String)addProdInfo.get("is_nego");
						isNegoNm = (String)addProdInfo.get("is_nego_nm");
						break;
					}
				}
			}
			
			if(prodInfo == null) continue;
			
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						if(!"0".equals(svcExt.getMonthlyAmt())){
							Map<String,Object> monthlyConf = new HashMap<String,Object>();
							monthlyConf.put("so_id", svcCmps.getSoId());
							monthlyConf.put("svc_grp", svcCmps.getSvcGrp());
							monthlyConf.put("svc_cd", svcCmps.getSvcCd());
							monthlyConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
							monthlyConf.put("nego_prod_cd", svcCmps.getProdCd());
							monthlyConf.put("nego_prod_cd_nm", prodNm);
							monthlyConf.put("nego_cnt", svcExt.getProdCnt());
							monthlyConf.put("sale_amt", svcExt.getMonthlyAmt());
							monthlyConf.put("discount_rate", svcExt.getMonthlyNegoRate());
							monthlyConf.put("nego_amt", svcExt.getMonthlyNegoAmt());
							monthlyConf.put("is_nego", isNego);
							monthlyConf.put("is_nego_nm", isNegoNm);
							monthlyConfList.add(monthlyConf);
						}
						
						if(!"0".equals(svcExt.getOnetimeAmt())){
							Map<String,Object> onetimeConf = new HashMap<String,Object>();
							onetimeConf.put("so_id", svcCmps.getSoId());
							onetimeConf.put("svc_grp", svcCmps.getSvcGrp());
							onetimeConf.put("svc_cd", svcCmps.getSvcCd());
							onetimeConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
							onetimeConf.put("nego_prod_cd", svcCmps.getProdCd());
							onetimeConf.put("nego_prod_cd_nm", prodNm);
							onetimeConf.put("nego_cnt", svcExt.getProdCnt());
							onetimeConf.put("sale_amt", svcExt.getOnetimeAmt());
							onetimeConf.put("discount_rate", svcExt.getOnetimeNegoRate());
							onetimeConf.put("nego_amt", svcExt.getOnetimeNegoAmt());
							onetimeConf.put("is_nego", isNego);
							onetimeConf.put("is_nego_nm", isNegoNm);
							onetimeConfList.add(onetimeConf);
						}
					}
				}
			}
		}
		
		model.addAttribute("gridMonthlyFeeList", monthlyConfList);
		model.addAttribute("gridOnetimeFeeList", onetimeConfList);
		
		//약정정보 조회
		if(StringUtils.isEmpty(octrtMast.getPromId())){
			model.addAttribute("promCnt", "00");
		}else{
			CtrtPromVO promInfo = orderManagementService.getCtrtPromInfo(octrtMast.getPromId(), octrtMast.getCtrtId());
			model.addAttribute("promCnt", promInfo.getPromCnt());
		}
			
		
		return URL + "/orderCommonPopup";
	}
	
	
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoBasicProdChangeInProgress
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 기본상품변경의 진행 오더 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 28. 오후 2:56:43
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoBasicProdChangeInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoBasicProdChangeInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);
		List<OProdCmpsVO> oprodCmpsList = orderManagementService.getOProdCmpsInfoList(soId, custId, ctrtId, orderId);
		List<OProdCmpsExtVO> oprodCmpsExtList = new ArrayList<OProdCmpsExtVO>();
		for(OProdCmpsVO prodCmps : oprodCmpsList){
			if(StringUtils.isEmpty(prodCmps.getExtId())){
				continue;
			}
			oprodCmpsExtList.add(orderManagementService.getOProdCmpsExtInfo(orderId, prodCmps.getExtId()));
		}
		List<OSvcCmpsVO> osvcCmpsList = orderManagementService.getOSvcCmpsInfoList(soId, custId, ctrtId, orderId);
		List<OSvcCmpsExtVO> osvcCmpsExtList = new ArrayList<OSvcCmpsExtVO>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			if(StringUtils.isEmpty(svcCmps.getExtId())){
				continue;
			}
			osvcCmpsExtList.add(orderManagementService.getOSvcCmpsExtInfo(orderId, svcCmps.getExtId()));
		}
		model.addAttribute("octrtMast", octrtMast);
		
		
		//기본상품리스트 조회
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String custTp = orderManagementService.getCustTp(soId, octrtMast.getCustId());
		List<Map<String,Object>> gridBasicProd = orderManagementService.getBasicProdList(soId, svcCd, octrtMast.getProdGrp(), custTp, today, lng);
		model.addAttribute("gridBasicProd", gridBasicProd);
		//기본상품상세정보 조회
		List<CommonCodeVO> penatyCodeList = orderManagementService.getPenaltyListByBasicProdCd(soId, octrtMast.getProdGrp(), octrtMast.getProdCd(), today, lng);
		Map<String, Object> rateInfo_007 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT007", today); //약정위약금
		Map<String, Object> rateInfo_008 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT008", today); //기본료
		Map<String, Object> rateInfo_004 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT004", today); //설치비
		Map<String, Object> rateInfo_009 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT009", today); //유지보수비
		Map<String, Object> rateInfo_010 = orderManagementService.getProdRateInfoByChargeType(soId, octrtMast.getProdCd(), "CT010", today); //임대료
		model.addAttribute("penatyCodeList", penatyCodeList);
		model.addAttribute("rateInfo_007", rateInfo_007);
		model.addAttribute("rateInfo_008", rateInfo_008);
		model.addAttribute("rateInfo_004", rateInfo_004);
		model.addAttribute("rateInfo_009", rateInfo_009);
		model.addAttribute("rateInfo_010", rateInfo_010);
		//단말상품리스트 조회
		List<Map<String,Object>> gridDeviceProdList = orderManagementService.getDeviceProdList(soId, octrtMast.getProdGrp(), octrtMast.getProdCd(), today, lng);
		//부가서비스리스트조회
		List<Map<String,Object>> gridAddProdList = orderManagementService.getAddProdList(soId,  octrtMast.getProdGrp(), octrtMast.getProdCd(), today, lng);
		//단말설정정보작성
		List<Map<String,Object>> deviceConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> confDevice = new HashMap<String,Object>();
			for(Map<String,Object> deviceInfo : gridDeviceProdList){
				if(svcCmps.getProdCd().equals((String)deviceInfo.get("device_prod_cd"))){
					confDevice.putAll(deviceInfo);
					break;
				}
			}
			
			if(confDevice.isEmpty())
				continue;
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						confDevice.put("device_cnt", svcExt.getProdCnt());
						confDevice.put("monthly_fee", svcExt.getMonthlyAmt());
						confDevice.put("onetime_fee", svcExt.getOnetimeAmt());
						break;
					}
				}
			}
			if("20".equals(svcCmps.getSvcStsCd())){
				confDevice.put("change_tp", "A");
				confDevice.put("change_tp_nm", commonDataService.getCommonCode("CM00054", "A", lng).getCommonCdNm());
			}else{
				confDevice.put("change_tp", "D");
				confDevice.put("change_tp_nm", commonDataService.getCommonCode("CM00054", "D", lng).getCommonCdNm());
			}
			
			deviceConfList.add(confDevice);
		}
		model.addAttribute("gridDeviceConfList", deviceConfList);
		
		
		//부가설정정보작성
		List<Map<String,Object>> addConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> confAdd = new HashMap<String,Object>();
			for(Map<String,Object> addInfo : gridAddProdList){
				if(svcCmps.getProdCd().equals((String)addInfo.get("add_prod_cd"))){
					confAdd.putAll(addInfo);
					break;
				}
			}
			
			if(confAdd.isEmpty())
				continue;
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						confAdd.put("add_cnt", svcExt.getProdCnt());
						confAdd.put("monthly_fee", svcExt.getMonthlyAmt());
						confAdd.put("onetime_fee", svcExt.getOnetimeAmt());
						break;
					}
				}
			}
			if("20".equals(svcCmps.getSvcStsCd())){
				confAdd.put("change_tp", "A");
				confAdd.put("change_tp_nm", commonDataService.getCommonCode("CM00054", "A", lng).getCommonCdNm());
			}else{
				confAdd.put("change_tp", "D");
				confAdd.put("change_tp_nm", commonDataService.getCommonCode("CM00054", "D", lng).getCommonCdNm());
			}
			
			addConfList.add(confAdd);
		}
		model.addAttribute("gridAddConfList", addConfList);
		
		//협정가 설정
		List<Map<String,Object>> monthlyConfList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> onetimeConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : osvcCmpsList){
			Map<String,Object> prodInfo = null;
			String prodNm = null;
			String isNego = null;
			String isNegoNm = null;
			for(Map<String,Object> basicProdInfo : gridBasicProd){
				if(svcCmps.getProdCd().equals(basicProdInfo.get("basic_prod_cd"))){
					prodInfo = basicProdInfo;
					prodNm = (String)basicProdInfo.get("basic_prod_cd_nm");
					isNego = (String)basicProdInfo.get("is_nego");
					isNegoNm = (String)basicProdInfo.get("is_nego_nm");
					break;
				}
			}
			
			if(prodInfo == null){
				for(Map<String,Object> deviceProdInfo : gridDeviceProdList){
					if(svcCmps.getProdCd().equals(deviceProdInfo.get("device_prod_cd"))){
						prodInfo = deviceProdInfo;
						prodNm = (String)deviceProdInfo.get("device_prod_cd_nm");
						isNego = (String)deviceProdInfo.get("is_nego");
						isNegoNm = (String)deviceProdInfo.get("is_nego_nm");
						break;
					}
				}
			}
			if(prodInfo == null){
				for(Map<String,Object> addProdInfo : gridAddProdList){
					if(svcCmps.getProdCd().equals(addProdInfo.get("add_prod_cd"))){
						prodInfo = addProdInfo;
						prodNm = (String)addProdInfo.get("add_prod_nm");
						isNego = (String)addProdInfo.get("is_nego");
						isNegoNm = (String)addProdInfo.get("is_nego_nm");
						break;
					}
				}
			}
			
			if(prodInfo == null) continue;
			
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : osvcCmpsExtList){
					if(svcCmps.getOrderId().equals(svcExt.getOrderId()) &&
							svcCmps.getExtId().equals(svcExt.getExtId())){
						if(!"0".equals(svcExt.getMonthlyAmt())){
							Map<String,Object> monthlyConf = new HashMap<String,Object>();
							monthlyConf.put("so_id", svcCmps.getSoId());
							monthlyConf.put("svc_grp", svcCmps.getSvcGrp());
							monthlyConf.put("svc_cd", svcCmps.getSvcCd());
							monthlyConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
							monthlyConf.put("nego_prod_cd", svcCmps.getProdCd());
							monthlyConf.put("nego_prod_cd_nm", prodNm);
							monthlyConf.put("nego_cnt", svcExt.getProdCnt());
							monthlyConf.put("sale_amt", svcExt.getMonthlyAmt());
							monthlyConf.put("discount_rate", svcExt.getMonthlyNegoRate());
							monthlyConf.put("nego_amt", svcExt.getMonthlyNegoAmt());
							monthlyConf.put("is_nego", isNego);
							monthlyConf.put("is_nego_nm", isNegoNm);
							monthlyConfList.add(monthlyConf);
						}
						
						if(!"0".equals(svcExt.getOnetimeAmt())){
							Map<String,Object> onetimeConf = new HashMap<String,Object>();
							onetimeConf.put("so_id", svcCmps.getSoId());
							onetimeConf.put("svc_grp", svcCmps.getSvcGrp());
							onetimeConf.put("svc_cd", svcCmps.getSvcCd());
							onetimeConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
							onetimeConf.put("nego_prod_cd", svcCmps.getProdCd());
							onetimeConf.put("nego_prod_cd_nm", prodNm);
							onetimeConf.put("nego_cnt", svcExt.getProdCnt());
							onetimeConf.put("sale_amt", svcExt.getOnetimeAmt());
							onetimeConf.put("discount_rate", svcExt.getOnetimeNegoRate());
							onetimeConf.put("nego_amt", svcExt.getOnetimeNegoAmt());
							onetimeConf.put("is_nego", isNego);
							onetimeConf.put("is_nego_nm", isNegoNm);
							onetimeConfList.add(onetimeConf);
						}
					}
				}
			}
		}
		
		model.addAttribute("gridMonthlyFeeList", monthlyConfList);
		model.addAttribute("gridOnetimeFeeList", onetimeConfList);
		
		//약정정보 조회
		if(StringUtils.isEmpty(octrtMast.getPromId())){
			model.addAttribute("promCnt", "00");
		}else{
			CtrtPromVO promInfo = orderManagementService.getCtrtPromInfo(octrtMast.getPromId(), octrtMast.getCtrtId());
			model.addAttribute("promCnt", promInfo.getPromCnt());
		}
			
		
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoProcRateInProgressAction
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 공정률 변경의 진행중인 오더 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoProcRateInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoProcRateInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);
		OCtrtMastExtVO octrtExt = orderManagementService.getOCtrtMastExtInfo(orderId, octrtMast.getExtId());
		
		model.addAttribute("remark", octrtMast.getRemark());
		model.addAttribute("procRate",  octrtExt.getProcRate());
		return URL + "/orderCommonPopup";
	}
	
	
	

	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoSuspentionInProgressAction
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 일시정지 처리 오더의 진행 내용 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoSuspentionInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoSuspentionInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);

		model.addAttribute("chngResn", octrtMast.getCtrtChgResnCd());
		model.addAttribute("startHopeDt", octrtMast.getSvcStrtHopeDt());
		model.addAttribute("endHopeDt", octrtMast.getMmtSusHopeDd());
		model.addAttribute("remark", octrtMast.getRemark());
		return URL + "/orderCommonPopup";
	}
	
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoSuspentionReleaseInProgressAction
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 일시정지해제 처리 오더의 진행 내용 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoSuspentionReleaseInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoSuspentionReleaseInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);

		model.addAttribute("chngResn", octrtMast.getCtrtChgResnCd());
		model.addAttribute("endHopeDt", octrtMast.getMmtSusHopeDd());
		model.addAttribute("remark", octrtMast.getRemark());
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoTerminationCancelInProgress
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 해지취소 처리 오더의 진행 내용 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 11. 오후 6:12:11
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoTerminationCancelInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoTerminationCancelInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);

		model.addAttribute("chngResn", octrtMast.getCtrtChgResnCd());
		model.addAttribute("openDd", octrtMast.getOpenDd());
		model.addAttribute("remark", octrtMast.getRemark());
		return URL + "/orderCommonPopup";
	}
	

	/**
	 * <PRE>
	 * 1. MethodName: getOrderInfoTerminationInProgressAction
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 해지처리 오더의 진행 내용 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오후 3:37:27
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderId 오더ID
	 *   @param svcCd 서비스ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getOrderInfoTerminationInProgressAction", method = RequestMethod.POST)
	public String getOrderInfoTerminationInProgress(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId,
			String orderId,
			String svcCd) throws ServiceException {
		
		//오더정보조회
		OCtrtMastVO octrtMast = orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);

		model.addAttribute("chngResn", octrtMast.getCtrtChgResnCd());
		model.addAttribute("termHopeDt", octrtMast.getTermHopeDt());
		model.addAttribute("remark", octrtMast.getRemark());
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getOrderMastInfoAction
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 오더 기본 정보 조회 Action
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 8. 오후 7:57:16
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model} 
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param orderCd 오더 마스터 코드
	 *   @param rcptId 접수ID
	 */
	@RequestMapping(value = "getOrderMastInfoAction", method = RequestMethod.POST)
	public String getOrderMastInfo(Model model
			, HttpServletRequest request
			, String soId
			, String custId
			, String ctrtId
			, String orderCd
			, String rcptId) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		try{
			OrderCommonVO orderCommonInfo = orderManagementService.getOrderCommonInfo(soId, custId, ctrtId, orderCd, rcptId, lng , today);

			for (int index = 0; orderCommonInfo != null && index < orderProcessService.length; index++) {
	            try {
	            	orderProcessService[index].precheckOrder(orderCommonInfo);
	            } catch (Exception e) {
	            	throw e;
	            }
	        }
			model.addAttribute("orderCommonInfo", orderCommonInfo);
			return "customer/contract/contract/contractManagement/contractManagement";
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("MSG.M09.MSG00021");
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getPrecheckList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 전산심사 조회
	 * 					- VADS 미사용
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 4:10:24
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param orderCommonInfo 오더 공통 정보
	 */
	@RequestMapping(value = "getPrecheckListAction", method = RequestMethod.POST)
	public String getPrecheckList(	Model model,
			HttpServletRequest request,
			@RequestBody OrderCommonVO orderCommonInfo) {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		SessionUser sessionUser = CommonUtil.getSessionManager();
		if(StringUtils.isEmpty(orderCommonInfo.getOrderStat())){ //접수전 데이터 삭제 후 신규 조회
			List<PrecheckInfoVO> precheckList = orderManagementService.savePrecheckInfo(orderCommonInfo,sessionUser.getUserId(), lng, today);
			model.addAttribute("precheckList", precheckList);
		}else{ //기존 데이터 조회
			List<PrecheckInfoVO> precheckList = orderManagementService.getPrecheckInfo(orderCommonInfo,sessionUser.getUserId(), lng, today);
			model.addAttribute("precheckList", precheckList);
		}
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getProdListTobeChange
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 상품 변경시 변경해야할  조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 27. 오후 5:28:06
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @param basicProdGrp 변경대상 기본상품그룹
	 *   @param basicProdCd 변경대상 기본상품코드
	 *   @return
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getProdListTobeChangeAction", method = RequestMethod.POST)
	public String getProdListTobeChange(	Model model
			, HttpServletRequest request
			, String soId
			, String custId
			, String ctrtId
			, String basicProdGrp
			, String basicProdCd) throws ServiceException {
		

		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		Map<String,Object> changeProd = orderManagementService.getProdListTobeChange(soId, custId, ctrtId, basicProdGrp, basicProdCd, today, lng);
		model.addAttribute("changeDeviceProdList", changeProd.get("chageDeviceProdList"));
		model.addAttribute("changeAddProdList", changeProd.get("changeAddProdList"));
		
		return URL + "/orderCommonPopup";
	}
	

	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 계약화면 납부계정 조회 
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 9. 오후 5:17:49
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 */
	@RequestMapping(value = "getPymAcntListAction", method = RequestMethod.POST)
	public String getPymAcntList(	Model model,
			HttpServletRequest request,
			String soId, String custId) {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<PaymentAccountInfoVO> pymAcntList = orderManagementService.getPymAcntInfoList(soId, custId, today, lng);
		model.addAttribute("pymAcntList", pymAcntList);
		model.addAttribute("totalCount", pymAcntList.size());
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntListForChangeAction
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 납부자 변경 화면의 변경 대상자 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 27. 오후 2:53:45
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soID 사업ID
	 *   @param ctrtId 계약ID
	 *   @param searchTp 조회유형
	 *   @param searchKey 조회키
	 */
	@RequestMapping(value = "getPymAcntListForChangeAction", method = RequestMethod.POST)
	public String getPymAcntListForChange(Model model
			, HttpServletRequest request
			, String soId
			, String ctrtId
			, String searchTp
			, String searchKey) {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		List<PaymentAccountInfoVO> pymAcntList = orderManagementService.getPymAcntListForChange(soId, ctrtId, searchTp, searchKey, lng);
		model.addAttribute("pymAcntList", pymAcntList);
		model.addAttribute("totalCount", pymAcntList.size());
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getPymAcntSearchTp
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 납부자조회 유형 코드 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 28. 오전 10:09:37
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getPymAcntSearchTpAction", method = RequestMethod.POST)
	public String getPymAcntSearchTp(Model model,
			HttpServletRequest request) throws ServiceException {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("pymAcntSearchTp", commonDataService.getCommonCodeList("CM00047", lng));
		return URL + "/orderCommonPopup";
	}
	
	
	
	/**
	 * <PRE>
	 * 1. MethodName: getSuspendedPeriod
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 일시 정지 중인 계약의 일시 정지 기간 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 7. 오후 5:04:47
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param soId 사업ID
	 *   @param custId 고객ID
	 *   @param ctrtId 계약ID
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getSuspendedPeriodAction", method = RequestMethod.POST)
	public String getSuspendedPeriod(Model model,
			HttpServletRequest request,
			String soId,
			String custId,
			String ctrtId) throws ServiceException {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		List<Map<String,Object>> susHist = orderManagementService.getCtrtSusHist(soId, custId, ctrtId, lng);
		if(susHist.size() == 0){
			model.addAttribute("startHopeDt", "");
			model.addAttribute("endHopeDt", "");
		}else{
			model.addAttribute("startHopeDt", susHist.get(0).get("sus_hope_dd"));
			model.addAttribute("endHopeDt", susHist.get(0).get("mmt_sus_hope_dd"));
		}
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getSuspentionCodeList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 일시정지화면 코드 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 30. 오후 2:28:00
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getSuspentionCodeListAction", method = RequestMethod.POST)
	public String getSuspentionCodeList(Model model,
			HttpServletRequest request) throws ServiceException {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("resnList", commonDataService.getCommonCodeListByRef2("CM00049", "0250", lng));
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getSuspentionReleaseCodeList
	 * 2. ClassName : getSuspentionReleaseCodeListAction
	 * 3. Comment   : 일시정지해제화면 코드 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 30. 오후 2:28:00
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getSuspentionReleaseCodeListAction", method = RequestMethod.POST)
	public String getSuspentionReleaseCodeList(Model model,
			HttpServletRequest request) throws ServiceException {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("resnList", commonDataService.getCommonCodeListByRef2("CM00049", "0260", lng));
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getTerminationCancleCodeList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 해지취소화면 코드 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 4. 11. 오전 10:25:39
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getTerminationCancleCodeListAction", method = RequestMethod.POST)
	public String getTerminationCancleCodeList(Model model,
			HttpServletRequest request) throws ServiceException {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("resnList", commonDataService.getCommonCodeListByRef2("CM00049", "0120", lng));
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: getTerminationCodeList
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 해지화면 코드 리스트 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 30. 오후 2:28:00
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "getTerminationCodeListAction", method = RequestMethod.POST)
	public String getTerminationCodeList(Model model,
			HttpServletRequest request
			) throws ServiceException {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		model.addAttribute("resnList", commonDataService.getCommonCodeListByRef2("CM00049", "0310", lng));
		return URL + "/orderCommonPopup";
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: insertOrder
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 오더 저장
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오전 9:54:25
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServerErrorException}
	 *   @param orderRequestInfo 오더 요청 정보{@link OrderRequestInfoVO}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "insertOrderAction", method = RequestMethod.POST)
	public String insertOrder(	Model model,
			HttpServletRequest request,
			@RequestBody OrderRequestInfoVO orderRequestInfo) throws ServiceException {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String nowDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Date nowDate = DateUtil.sysdate();
		
		orderRequestInfo.setOrderReqDttm(nowDttm);
		orderRequestInfo.setOrderReqDt(today);
		orderRequestInfo.setLng(lng);
		orderRequestInfo.setOrderReqDate(nowDate);
		
		OrderRequestInfoVO orderInfo = null;
		
		System.out.println("orderProcessService.length=====================================" + orderProcessService.length);
		
		for (int index = 0; orderInfo == null && index < orderProcessService.length; index++) {
            try {
            	orderInfo = orderProcessService[index].createOrder(orderRequestInfo);
            } catch (Exception e) {
            	throw e;
            }
        }
		
		
		if(orderInfo == null){
			throw new ServiceException("MSG.M08.MSG00020");
		}
		model.addAttribute("orderRequestInfo", orderInfo);
		return URL + "/orderCommonPopup";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: openOrderPage
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 오더 진행화면 오픈
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 4:08:57
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param orderPageUrl 오더 URL
	 */
	@RequestMapping(value = "openOrderPage", method = RequestMethod.POST)
	public String openOrderPage(	Model model,
			HttpServletRequest request,
			String orderPageUrl) {
		return URL + "/ajax/" + orderPageUrl;
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: openOrderProcess
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 오더 공통 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 13. 오후 4:08:16
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param orderCommonInfo 오더 공통 정보 {@link OrderCommonVO}
	 */
	@RequestMapping(value = "openOrderProcess", method = RequestMethod.POST)
	public String openOrderProcess(	Model model,
			HttpServletRequest request,
			@RequestBody OrderCommonVO orderCommonInfo) {
		model.addAttribute("orderCommonInfo", orderCommonInfo);
		return URL + "/ajax/orderCommonPopup";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: saveCancelOrder
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 오더 취소
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오전 9:53:55
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param orderRequestInfo 오더 요청 정보{@link OrderRequestInfoVO}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "saveCancelOrderAction", method = RequestMethod.POST)
	public String saveCancelOrder(	Model model,
			HttpServletRequest request,
			@RequestBody OrderRequestInfoVO orderRequestInfo) throws ServiceException {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String nowDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Date nowDate = DateUtil.sysdate();
		
		orderRequestInfo.setOrderReqDttm(nowDttm);
		orderRequestInfo.setOrderReqDt(today);
		orderRequestInfo.setLng(lng);
		orderRequestInfo.setOrderReqDate(nowDate);
		
		
		OrderRequestInfoVO orderInfo = null;
		
		for (int index = 0; orderInfo == null && index < orderProcessService.length; index++) {
            try {
            	orderInfo = orderProcessService[index].cancelOrder(orderRequestInfo);
            } catch (Exception e) {
            	throw e;
            }
        }
		
		if(orderInfo == null){
			throw new ServiceException("MSG.M08.MSG00020");
		}
		model.addAttribute("orderRequestInfo", orderInfo);
		return URL + "/orderCommonPopup";
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName: saveCmplOrder
	 * 2. ClassName : OrderManagementController
	 * 3. Comment   : 오더 완료
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 21. 오전 9:53:52
	 * </PRE>
	 *   @return String Page URL
	 *   @param model {@link Model}
	 *   @param request {@link HttpServletRequest}
	 *   @param orderRequestInfo 오더 요청 정보{@link OrderRequestInfoVO}
	 *   @throws ServiceException
	 */
	@RequestMapping(value = "saveCmplOrderAction", method = RequestMethod.POST)
	public String saveCmplOrder(	Model model,
			HttpServletRequest request,
			@RequestBody OrderRequestInfoVO orderRequestInfo) throws ServiceException {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String nowDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Date nowDate = DateUtil.sysdate();
		
		orderRequestInfo.setOrderReqDttm(nowDttm);
		orderRequestInfo.setOrderReqDt(today);
		orderRequestInfo.setLng(lng);
		orderRequestInfo.setOrderReqDate(nowDate);
		
		
		OrderRequestInfoVO orderInfo = null;
		
		for (int index = 0; orderInfo == null && index < orderProcessService.length; index++) {
            try {
            	orderInfo = orderProcessService[index].saveOrder(orderRequestInfo);
            } catch (Exception e) {
            	throw e;
            }
        }
		if(orderInfo == null){
			throw new ServiceException("MSG.M08.MSG00020");
		}
		
		model.addAttribute("orderRequestInfo", orderInfo);
		return URL + "/orderCommonPopup";
	}
	
	
	@RequestMapping(value = "saveProgressOrderAction", method = RequestMethod.POST)
	public String saveProgressOrder(	Model model,
			HttpServletRequest request,
			@RequestBody OrderRequestInfoVO orderRequestInfo) throws ServiceException {
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String nowDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		Date nowDate = DateUtil.sysdate();
		
		orderRequestInfo.setOrderReqDttm(nowDttm);
		orderRequestInfo.setOrderReqDt(today);
		orderRequestInfo.setLng(lng);
		orderRequestInfo.setOrderReqDate(nowDate);
		
		OrderRequestInfoVO orderInfo = null;
		
		for (int index = 0; orderInfo == null && index < orderProcessService.length; index++) {
           try {
           	orderInfo = orderProcessService[index].saveOrder(orderRequestInfo);
           } catch (Exception e) {
           	throw e;
           }
       }
		
		
		if(orderInfo == null){
			throw new ServiceException("MSG.M08.MSG00020");
		}
		model.addAttribute("orderRequestInfo", orderInfo);
		return URL + "/orderCommonPopup";
	}

	@RequestMapping(value = "getBasicProdListAction2", method = RequestMethod.POST)
	public String getBasicProdListAction2(Model model, HttpServletRequest request,
			String soId,
			String prodCd) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		
		OrderInfoVO basicProdList = orderManagementService.getBasicProdList2(soId, prodCd, today, lng);
		
		model.addAttribute("basicProdList", basicProdList);
		return URL + "/ajax/orderCommonPopup";
	}
}
