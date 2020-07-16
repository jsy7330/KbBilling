package com.ntels.ccbs.appIf.service.mso.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;
import com.ntels.ccbs.appIf.mapper.mso.service.ServiceMsoMapper;
import com.ntels.ccbs.appIf.service.mso.service.ServiceMsoService;
import com.ntels.ccbs.appIf.service.sh.service.ServiceIfService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.AppException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderMastInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;
import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoVO;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderManagementMapper;
import com.ntels.ccbs.customer.mapper.customer.customer.CustomerManagementMapper;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderProcessService;
import com.ntels.ccbs.customer.service.customer.customer.CustomerManagementService;

@Service
public class ServiceMsoServiceImpl implements ServiceMsoService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ServiceMsoMapper serviceMsoMapper;
	
	@Autowired
	private CustomerManagementService customerManagementService;
	
	@Autowired
	private CustomerManagementMapper customerManagementMapper;
	
	@Autowired
	private OrderManagementService orderManagementService;
	
	@Autowired
	private ContractManagementService contractManagementService;
	
	@Autowired
	private OrderProcessService[] orderProcessService;
	
	@Autowired
	ServiceIfService serviceIfService;
	
	@Autowired
	private OrderManagementMapper orderManagementMapper;
	
	@Override
	public Map<String, Object> setServiceApply(AppRequestVO appRequestVO) {

		//전문 파라미터
		Map<String, Object> param = appRequestVO.getBody();
		
		checkParams(param);		//파라미터 체크
		
		//고객정보
		CustomerInfoVO customerInfoVO = new CustomerInfoVO();
		//ordre저장 파라미터
		OrderRequestInfoVO orderRequestInfoVO = new OrderRequestInfoVO();
		
		String today = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)param.get("lng");
		
		String soId = (String)param.get("SO_ID");
		String custId = (String)param.get("CUST_ID");
		String orderTp = (String)param.get("ORDER_TP");
		String orderTp2 = (String)param.get("ORDER_TP");		//부가서비스 처리 위해 orderTp 변경 값 
		if(orderTp.equals("201")){
			orderTp2 = "202";
		}
		String ctrtId = (String)param.get("CTRT_ID");
		String prodCd = (String)param.get("PROD_CD");
		String custTp = (String)param.get("CUST_TP");
		String rcptId = "";
		String orderCd = "";
		
		if(ctrtId == null || ctrtId.equals("")){
			ctrtId = "0000000000";
		}
		
		if(orderTp.equals("101") && (custId == null || custId.equals("")) ){		//신규 청약 이고, 고객이 등록 되지 않았을경우 고객등록 
			customerInfoVO = setCustInfo(param);
			custId = customerInfoVO.getCustId();
		}else{
			List<CustomerInfoVO> custList = customerManagementMapper.getCustomerInfoList((String)param.get("SO_ID"), custId, "", null,today, lng);
			if(custList == null || custList.size() < 1){	//고객정보 조회
				throw new AppException("MSG.M01.MSG00019", "MSG00019");  //고객정보가 존재하지 않습니다. 관리자에게 문의해 주세요.
			}
			customerInfoVO = custList.get(0);
		}
		
		if(custTp == null ||custTp.equals("")){
			custTp = customerInfoVO.getCustTp();
		}
		
		List<OrderMastInfoVO> orderServiceList = contractManagementService.getOrderListBySoId(soId, custTp, lng);
		for(OrderMastInfoVO mastInfo : orderServiceList){
			if(mastInfo.getOrderTp().equals(orderTp2)){	//orderTp2 값 사용
				orderCd = mastInfo.getOrderCd();
				break;
			}
		}
		
		OrderCommonVO orderCommonInfo = orderManagementService.getOrderCommonInfo(soId, custId, ctrtId, orderCd, rcptId, lng , today);

		for (int index = 0; orderCommonInfo != null && index < orderProcessService.length; index++) {
            try {
            	orderProcessService[index].precheckOrder(orderCommonInfo);
            } catch (Exception e) {
            	throw e;
            }
        }
		
		orderCd = orderCommonInfo.getOrderCd();
		
		
		//공통영역
		orderRequestInfoVO.setSoId(soId);
		orderRequestInfoVO.setCustId(custId);
		orderRequestInfoVO.setCtrtId(ctrtId);
		orderRequestInfoVO.setRcptId(rcptId);
		orderRequestInfoVO.setOrderId(orderCommonInfo.getOrderId());
		orderRequestInfoVO.setOrderCd(orderCd);
		orderRequestInfoVO.setOrderTp(orderTp2);	//orderTp2 값 사용
		orderRequestInfoVO.setOrderStat(orderCommonInfo.getOrderStat());
		orderRequestInfoVO.setCnslMstCl(orderCommonInfo.getCnslMstCl());
		orderRequestInfoVO.setCnslMidCl(orderCommonInfo.getCnslMidCl());
		orderRequestInfoVO.setCnslSlvCl(orderCommonInfo.getCnslSlvCl());
		orderRequestInfoVO.setBasicSvcCd(orderCommonInfo.getBasicSvcCd());
		orderRequestInfoVO.setCustTp(orderCommonInfo.getCustTp());
		
		
		param.put("CUST_ID", custId);
		param.put("today", today);
		param.put("basicSvcCd", orderCommonInfo.getBasicSvcCd());
		param.put("basicProdGrp", "a");	//상품군.
		
		if(orderTp.equals("101")){		//신규 청약일 경우 만 납부계정, 기본요금제 셋팅 
			
			//납부계정ID
			List<LinkedHashMap<String, Object>> pymAcntInfoList = serviceMsoMapper.getPymAcntInfoList(param);
			if(pymAcntInfoList != null && pymAcntInfoList.size() > 0){
				orderRequestInfoVO.setPymAcntId((String)pymAcntInfoList.get(0).get("PYM_ACNT_ID"));
			}else{
				throw new AppException("MSG.M02.MSG00001", "MSG00001");  //납부계정 정보가 없습니다.
			}
			
			//기본요금제
			List<Map<String,Object>> addBasicProdList = getBasicProdList(param, today, orderCommonInfo);	//기본상품 데이터 정보 
			orderRequestInfoVO.setAddBasicProdList(addBasicProdList);
			//기본요금제
			
		}
		
		if(orderTp.equals("101") || orderTp.equals("201")){	//신규청약, 부가상품추가 일 경우
			//장비상품 정보, 부가 상품 정보
			
			List<Map<String,Object>> delAddonProdList = new ArrayList<Map<String, Object>>();
			orderRequestInfoVO.setDelAddonProdList(delAddonProdList);	//부가서비스 신청일 경우 삭제리스트 생성 - 서비스단 오류 방지
			
			orderRequestInfoVO = getAddonDeviceProdList(orderRequestInfoVO, param);	//장비,부가 상품정보 파람 셋팅
			
		}else if(orderTp.equals("202")){	//부가서비스 해지일 경우
			
			getDelAddonProdList(orderRequestInfoVO, param);	//부가서비스 삭제리스트
			
		}else if(orderTp.equals("204")){	//일시정시 신청 일경우 셋팅
			
			String nowDate = DateUtil.getDateStringYYYYMMDD(0);
			
			Map<String,Object> maxDayAttr = orderManagementMapper.getProdAttr(soId, prodCd, "AT029",nowDate);
			int maxDays = Integer.parseInt((String)maxDayAttr.get("ATTR_VAL"));
			
			orderRequestInfoVO.setStartHopeDt(nowDate);
			orderRequestInfoVO.setEndHopeDt(DateUtil.getDateStringYYYYMMDD(maxDays));
			orderRequestInfoVO.setChngResn("A1");	//고객요청
			
		}else if(orderTp.equals("205")){	//일시정시 신청 일경우 셋팅
			
			orderRequestInfoVO.setChngResn("B1");	//고객요청
			
		}else if(orderTp.equals("301")){	//청약해지 일 경우
			
			orderRequestInfoVO.setTermHopeDt(DateUtil.getDateStringYYYYMMDD(0));
			orderRequestInfoVO.setChngResn("C1");	//고객요청
			
		}
		
		//기타 정보 셋팅
		orderRequestInfoVO.setReqUsrId(Consts.IF_ADM_ID);
		String orgId = "";
		List<LinkedHashMap<String, Object>> orgIdList = serviceMsoMapper.getOrgIdList(param);
		if(orgIdList != null && orgIdList.size() > 0){
			orgId = (String)orgIdList.get(0).get("ORG_ID");
		}else{
			throw new AppException("MSG.M15.MSG00057", "MSG00057");  //SO_ID에 맞는 ORG_ID 가 없습니다.
		}
		
		orderRequestInfoVO.setReqOrgId(orgId);	//VO정보에 없음.
		orderRequestInfoVO.setPromCnt("00");	//약정정보 무약정으로 셋팅 "00"
		orderRequestInfoVO.setInstZipCode((String)param.get("INSTL_ZIP_CD"));
		orderRequestInfoVO.setInstBaseAddr((String)param.get("INSTL_BASE_ADDR"));
		orderRequestInfoVO.setInstAddrDtl((String)param.get("INSTL_ADDR_DTL"));
		orderRequestInfoVO.setInstCity("");
		orderRequestInfoVO.setInstState("");
		//orderRequestInfoVO.setDesc();		//VO정보에 없음.
		
		String nowDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		Date nowDate = DateUtil.sysdate();
		String orderReqDt = DateUtil.getDateStringYYYYMMDD(0);
		
		orderRequestInfoVO.setOrderReqDttm(nowDttm);
		orderRequestInfoVO.setOrderReqDt(orderReqDt);
		orderRequestInfoVO.setLng(lng);
		orderRequestInfoVO.setOrderReqDate(nowDate);
		
		//저장 호출
		OrderRequestInfoVO orderInfo = null;
		
		for (int index = 0; orderInfo == null && index < orderProcessService.length; index++) {
            try {
            	orderInfo = orderProcessService[index].createOrder(orderRequestInfoVO);
            } catch (Exception e) {
            	throw e;
            }
        }
		
		ctrtId = orderInfo.getCtrtId();
		
		orderInfo.setOrderReqDttm(nowDttm);
		orderInfo.setOrderReqDt(orderReqDt);
		orderInfo.setLng(lng);
		orderInfo.setOrderReqDate(nowDate);
		orderInfo.setReqDesc("Service Flatform Result");
		
		//완료호출
		OrderRequestInfoVO orderInfo2 = null;
		if(orderTp.equals("101")){		//신규 청약일 경우 
			
			for (int index = 0; orderInfo2 == null && index < orderProcessService.length; index++) {
				try {
					orderInfo2 = orderProcessService[index].progressOrder(orderInfo);
				} catch (Exception e) {
					throw e;
				}
			}
			
		}		
		
		Map<String,Object> result = new LinkedHashMap<String,Object>();
		result.put("SO_ID", soId);
		result.put("CUST_ID", custId);
		result.put("CTRT_ID", ctrtId);
		result.put("ORDER_ID", orderInfo.getOrderId());
		result.put("ORDER_STAT", "04");	//완료상태값
		
		AppRequestVO resultParamVO = new AppRequestVO();
		resultParamVO.setBody(result);
		
		//완료처리 - 서비스결과처리 호출
		if(!orderTp.equals("101")){	//신규청약이 아닐 경우
			serviceIfService.processServiceResult(resultParamVO);
		}
		
		
		
		return result;
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getBasicProdList
	 * 2. ClassName : ServiceMsoServiceImpl
	 * 3. Comment   : 기본상품 데이터 정보 
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 6. 8. 오전 10:23:00
	 * </PRE>
	 *   @return List<Map<String,Object>>
	 *   @param param
	 *   @param today
	 *   @param orderCommonInfo
	 *   @return
	 */
	private List<Map<String, Object>> getBasicProdList(Map<String, Object> param, String today, OrderCommonVO orderCommonInfo){
		
		List<Map<String,Object>> addBasicProdList = new ArrayList<Map<String, Object>>();
		Map<String, Object> basicProdInfo = new HashMap<String, Object>();
		
		List<LinkedHashMap<String, Object>> basicProdList = serviceMsoMapper.getProdInfoList(param);
		if(basicProdList == null || basicProdList.size() < 1){	//상품정보가 없을경우
			throw new AppException("MSG.M01.MSG00059", "MSG00059");  //기본상품 정보가 존재하지 않습니다.
		}
		
		Map<String, Object> basicProd = basicProdList.get(0);
		
		basicProdInfo.put("prodGrp", basicProd.get("BASIC_PROD_GRP"));
		basicProdInfo.put("prodCd", basicProd.get("BASIC_PROD_CD"));
		basicProdInfo.put("prodNm", basicProd.get("BASIC_PROD_CD_NM"));
		basicProdInfo.put("svcCd", basicProd.get("SVC_CD"));
		basicProdInfo.put("svcGrp", basicProd.get("SVC_GRP"));
		basicProdInfo.put("prodTyp", basicProd.get("PROD_TYP"));
		basicProdInfo.put("basicProdFl", basicProd.get("BASIC_PROD_FL"));
		//협정가 없음.
		basicProdInfo.put("onetimeFee", String.valueOf(basicProd.get("ONETIME_FEE")));
		basicProdInfo.put("onetimeFeeNegoRate", "0");
		basicProdInfo.put("onetimeFeeNegoAmt", String.valueOf(basicProd.get("ONETIME_FEE")));
		basicProdInfo.put("onetimeInstMonths", "1");
		
		basicProdInfo.put("monthlyFee", String.valueOf(basicProd.get("MONTHLY_FEE")));
		basicProdInfo.put("monthlyFeeNegoRate", "0");
		basicProdInfo.put("monthlyFeeNegoAmt", String.valueOf(basicProd.get("MONTHLY_FEE")));
		
		addBasicProdList.add(basicProdInfo);
		
		return addBasicProdList;
		
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getAddonDeviceProdList
	 * 2. ClassName : ServiceMsoServiceImpl
	 * 3. Comment   : 장비상품 정보, 부가 상품 정보 데이터 셋팅
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 6. 8. 오후 4:13:25
	 * </PRE>
	 *   @return OrderRequestInfoVO
	 *   @param orderRequestInfoVO
	 *   @param param
	 *   @return
	 */
	private OrderRequestInfoVO getAddonDeviceProdList(OrderRequestInfoVO orderRequestInfoVO, Map<String, Object> param){
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> prodInfoList = (List<Map<String, Object>>)param.get("PROD_INFO");
		
		if(prodInfoList == null || prodInfoList.size() < 1){
			throw new AppException("MSG.M07.MSG00139", "MSG00139");  //상풉정보가 없습니다.
		}
		
		List<Map<String,Object>> addAddonProdList = new ArrayList<Map<String, Object>>();
		List<Map<String,Object>> addDeviceProdList = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i<prodInfoList.size(); i++){
			
			String prodTp = "";	//장비타입 구분 하기 위한 값 (부가상품 : V, 장비상품 :  E)
			List<LinkedHashMap<String,Object>> addonProdList = new ArrayList<LinkedHashMap<String, Object>>();	//부가상품
			List<LinkedHashMap<String,Object>> deviceProdList = new ArrayList<LinkedHashMap<String, Object>>();	//장비상품
			
			
			param.put("SUB_PROD_CD", prodInfoList.get(i).get("PROD_CD"));
			addonProdList = serviceMsoMapper.getAddProdList(param);	//부가상품 조회
			if(addonProdList == null || addonProdList.size() < 1){
				
				//부가서비스 등록일때 상품 정보가 없으면 에러 처리
				if(orderRequestInfoVO.getOrderTp().equals("202")){
					Object[] arguments = { i+1 };
					throw new AppException("MSG.M15.MSG00058", "MSG00058", arguments );  // ({0}) 데이터의 상품코드가 잘못 되었습니다.
				}
				
				deviceProdList = serviceMsoMapper.getDeviceProdList(param);	//장비상품 조회
				prodTp = "E";
				if(deviceProdList == null || deviceProdList.size() < 1){	//장비상품, 부가상품 전부 없을 경우
					Object[] arguments = { i+1 };
					throw new AppException("MSG.M15.MSG00058", "MSG00058", arguments );  // ({0}) 데이터의 상품코드가 잘못 되었습니다.
				}
				
			}else{
				prodTp = "V";
			}
			
			if(prodTp.equals("V")){
				
				Map<String, Object> addonProdInfo = new HashMap<String, Object>();
				
				addonProdInfo.put("prodGrp", addonProdList.get(0).get("ADD_PROD_GRP"));
				addonProdInfo.put("prodCd", addonProdList.get(0).get("ADD_PROD_CD"));
				addonProdInfo.put("prodNm", addonProdList.get(0).get("ADD_PROD_NM"));
				addonProdInfo.put("svcCd", addonProdList.get(0).get("SVC_CD"));
				addonProdInfo.put("svcGrp", addonProdList.get(0).get("SVC_GRP"));
				addonProdInfo.put("prodTyp", addonProdList.get(0).get("PROD_TYP"));
				addonProdInfo.put("prodCnt", addonProdList.get(0).get("ADD_CNT"));
				
				addonProdInfo.put("monthlyFee", String.valueOf(addonProdList.get(0).get("MONTHLY_FEE")));
				addonProdInfo.put("monthlyFeeNegoRate", "0");
				addonProdInfo.put("monthlyFeeNegoAmt", String.valueOf(addonProdList.get(0).get("MONTHLY_FEE")));
				
				addonProdInfo.put("onetimeFee", String.valueOf(addonProdList.get(0).get("ONETIME_FEE")));
				addonProdInfo.put("onetimeFeeNegoRate", "0");
				addonProdInfo.put("onetimeFeeNegoAmt", String.valueOf(addonProdList.get(0).get("ONETIME_FEE")));
				addonProdInfo.put("onetimeInstMonths", "1");
				
				addAddonProdList.add(addonProdInfo);
				
			}else if(prodTp.equals("E")){
				
				Map<String, Object> addDeviceInfo = new HashMap<String, Object>();
				
				addDeviceInfo.put("prodGrp", deviceProdList.get(0).get("DEVICE_PROD_GRP"));
				addDeviceInfo.put("prodCd", deviceProdList.get(0).get("DEVICE_PROD_CD"));
				addDeviceInfo.put("prodNm", deviceProdList.get(0).get("DEVICE_PROD_CD_NM"));
				addDeviceInfo.put("svcCd", deviceProdList.get(0).get("SVC_CD"));
				addDeviceInfo.put("svcGrp", deviceProdList.get(0).get("SVC_GRP"));
				addDeviceInfo.put("prodTyp", deviceProdList.get(0).get("PROD_TYP"));
				addDeviceInfo.put("prodCnt", deviceProdList.get(0).get("DEVICE_CNT"));
				
				addDeviceInfo.put("monthlyFee", String.valueOf(deviceProdList.get(0).get("MONTHLY_FEE")));
				addDeviceInfo.put("monthlyFeeNegoRate", "0");
				addDeviceInfo.put("monthlyFeeNegoAmt", String.valueOf(deviceProdList.get(0).get("MONTHLY_FEE")));
				
				addDeviceInfo.put("onetimeFee", String.valueOf(deviceProdList.get(0).get("ONETIME_FEE")));
				addDeviceInfo.put("onetimeFeeNegoRate", "0");
				addDeviceInfo.put("onetimeFeeNegoAmt", String.valueOf(deviceProdList.get(0).get("ONETIME_FEE")));
				addDeviceInfo.put("onetimeInstMonths", "1");
				
				addDeviceProdList.add(addDeviceInfo);
			}
			
			
		}
		
		orderRequestInfoVO.setAddAddonProdList(addAddonProdList);
		orderRequestInfoVO.setAddDeviceProdList(addDeviceProdList);
		
		return orderRequestInfoVO;
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getDelAddonProdList
	 * 2. ClassName : ServiceMsoServiceImpl
	 * 3. Comment   : 부가상품 삭제 리스트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 6. 9. 오후 1:25:39
	 * </PRE>
	 *   @return OrderRequestInfoVO
	 *   @param orderRequestInfoVO
	 *   @param param
	 *   @return
	 */
	private OrderRequestInfoVO getDelAddonProdList(OrderRequestInfoVO orderRequestInfoVO, Map<String, Object> param){
		
		List<Map<String,Object>> addAddonProdList = new ArrayList<Map<String, Object>>();	//부가서비스 추가 리스트 초기화
		orderRequestInfoVO.setAddAddonProdList(addAddonProdList);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> prodInfoList = (List<Map<String, Object>>)param.get("PROD_INFO");
		
		if(prodInfoList == null || prodInfoList.size() < 1){
			throw new AppException("MSG.M07.MSG00139", "MSG00139");  //상풉정보가 없습니다.
		}
		
		List<Map<String,Object>> delAddonProdList = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i<prodInfoList.size(); i++){
			
			if(prodInfoList == null || prodInfoList.size() < 1 
					|| prodInfoList.get(i).get("PROD_CD") == null || prodInfoList.get(i).get("PROD_CD").equals("")
					|| prodInfoList.get(i).get("PROD_CMPS_ID") == null || prodInfoList.get(i).get("PROD_CMPS_ID").equals("")){	//부가상품 정보가 없을 경우
				Object[] arguments = { i+1 };
				throw new AppException("MSG.M15.MSG00058", "MSG00058", arguments );  // ({0}) 데이터의 상품코드가 잘못 되었습니다.
			}
			
			Map<String, Object> addonProdInfo = new HashMap<String, Object>();
			
			addonProdInfo.put("prodCd", prodInfoList.get(i).get("PROD_CD"));
			//addonProdInfo.put("prodNm", addonProdList.get(0).get("ADD_PROD_NM"));
			addonProdInfo.put("prodCmpsId", prodInfoList.get(i).get("PROD_CMPS_ID"));
			
			delAddonProdList.add(addonProdInfo);
				
		}
			
		orderRequestInfoVO.setDelAddonProdList(delAddonProdList);	//부가서비스 삭제리스트
		
		return orderRequestInfoVO;
	}
	
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: setCustInfo
	 * 2. ClassName : ServiceMsoServiceImpl
	 * 3. Comment   : 고객정보 등록
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 6. 8. 오전 10:17:47
	 * </PRE>
	 *   @return CustomerInfoVO
	 *   @param custInfo
	 *   @return
	 */
	private CustomerInfoVO setCustInfo(Map<String, Object> custInfo){
		
		CustomerInfoVO customerInfo = new CustomerInfoVO();
		
		if(custInfo.get("SO_ID") == null || custInfo.get("SO_ID").equals("")){
			throw new AppException("MSG.M15.MSG00055", "MSG00055");  //SO_ID를 입력해 주세요.
		}
		if(custInfo.get("USR_ID") == null || custInfo.get("USR_ID").equals("")){
			throw new AppException("MSG.M15.MSG00056", "MSG00056");  //USR_ID를 입력해 주세요.
		}
		if(custInfo.get("CUST_NM") == null || custInfo.get("CUST_NM").equals("")){
			throw new AppException("MSG.M01.MSG00017", "MSG00017");  //고객명은 필수 입력 입니다.
		}
		if(custInfo.get("CUST_TP") == null || custInfo.get("CUST_TP").equals("")){
			throw new AppException("MSG.M01.MSG00065", "MSG00065");  //고객유형을 입력해 주세요.
		}
		if(custInfo.get("SVC_TEL_NO") == null || custInfo.get("SVC_TEL_NO").equals("")){
			throw new AppException("MSG.M14.MSG00026", "MSG00026");  //휴대폰번호를입력해주세요
		}
		if(custInfo.get("INSTL_ZIP_CD") == null || custInfo.get("INSTL_ZIP_CD").equals("")){
			throw new AppException("MSG.M08.MSG00035", "MSG00035");  //우편번호를 입력해주세요
		}
		if(custInfo.get("INSTL_BASE_ADDR") == null || custInfo.get("INSTL_BASE_ADDR").equals("")){
			throw new AppException("MSG.M01.MSG00062", "MSG00062");  //기본주소를 입력해주세요.
		}
		if(custInfo.get("INSTL_ADDR_DTL") == null || custInfo.get("INSTL_ADDR_DTL").equals("")){
			throw new AppException("MSG.M07.MSG00062", "MSG00062");  //상세주소를 입력해주세요
		}
		
		//고객유형이 B,D,E (개인사업자, 법인사업자, 공공기관) 일 경우
		if(custInfo.get("CUST_TP").equals("B") || custInfo.get("CUST_TP").equals("D") || custInfo.get("CUST_TP").equals("E")){
			
			if(custInfo.get("BIZ_REG_NO") == null || custInfo.get("BIZ_REG_NO").equals("")){
				throw new AppException("MSG.M07.MSG00138", "MSG00138");  //사업자번호를 입력해 주세요.
			}
			if(custInfo.get("BUSI_CNDT") == null || custInfo.get("BUSI_CNDT").equals("")){
				throw new AppException("MSG.M08.MSG00089", "MSG00089");  //업태를 입력해 주세요.
			}
			if(custInfo.get("BUSI_TP") == null || custInfo.get("BUSI_TP").equals("")){
				throw new AppException("MSG.M08.MSG00090", "MSG00090");  //업종을 입력해 주세요.
			}
			
		}
		
		String today = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String lng = (String)custInfo.get("lng");
		
		List<LinkedHashMap<String, Object>> orgIdList = serviceMsoMapper.getOrgIdList(custInfo);
		if(orgIdList != null && orgIdList.size() > 0){
			customerInfo.setOrgId((String)orgIdList.get(0).get("ORG_ID"));
		}else{
			throw new AppException("MSG.M15.MSG00057", "MSG00057");  //SO_ID에 맞는 ORG_ID 가 없습니다.
		}
		
		customerInfo.setUsrId((String)custInfo.get("USR_ID"));
		customerInfo.setChgrId(Consts.IF_ADM_ID);
		customerInfo.setChgDate(DateUtil.sysdate());
		customerInfo.setRegrId(Consts.IF_ADM_ID);
		customerInfo.setRegDate(DateUtil.sysdate());

		customerInfo.setSoId((String)custInfo.get("SO_ID"));
		customerInfo.setCustNm((String)custInfo.get("CUST_NM"));
		customerInfo.setCustTp((String)custInfo.get("CUST_TP"));
		customerInfo.setMtelNo((String)custInfo.get("SVC_TEL_NO"));
		customerInfo.setZipCd((String)custInfo.get("INSTL_ZIP_CD"));
		customerInfo.setBaseAddr((String)custInfo.get("INSTL_BASE_ADDR"));
		customerInfo.setAddrDtl((String)custInfo.get("INSTL_ADDR_DTL"));
		
		customerInfo.setCorpRegNo("0000000******");		//받는값없어서 임의 등록
		
		CustomerInfoVO custInfoVO = customerManagementService.insertNewCustomerInfo(customerInfo, today, lng);
		
		return custInfoVO;
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: checkParams
	 * 2. ClassName : ServiceMsoServiceImpl
	 * 3. Comment   : 파라미터 body 값 체크
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 6. 9. 오전 9:38:15
	 * </PRE>
	 *   @return void
	 *   @param param
	 */
	private void checkParams(Map<String, Object> param){
		
		if(param == null ){	//body 체크
			throw new AppException("MSG.M03.MSG00019", "MSG00019");  //데이터가 없습니다.
		}
		
		if(param.get("SO_ID") == null || param.get("SO_ID").equals("")){
			Object[] arguments = { "SO_ID" };
			throw new AppException("MSG.M13.MSG00025", ".MSG00025", arguments);  //필수값을 입력해 주세요.(항목 : {0})
		}
		
		if(param.get("ORDER_TP") == null || param.get("ORDER_TP").equals("")){
			Object[] arguments = { "ORDER_TP" };
			throw new AppException("MSG.M13.MSG00025", ".MSG00025", arguments);  //필수값을 입력해 주세요.(항목 : {0})
		}
		
		if(param.get("ORDER_TP").equals("101") || param.get("ORDER_TP").equals("201") || param.get("ORDER_TP").equals("202")
				|| param.get("ORDER_TP").equals("204") || param.get("ORDER_TP").equals("205") || param.get("ORDER_TP").equals("301")){	//신청유형 체크
			
			
			if(!param.get("ORDER_TP").equals("101")){	//신규신청 이외 파라미터 체크
				
				if(param.get("CUST_ID") == null || param.get("CUST_ID").equals("")){
					Object[] arguments = { "CUST_ID" };
					throw new AppException("MSG.M13.MSG00025", ".MSG00025", arguments);  //필수값을 입력해 주세요.(항목 : {0})
				}
				if(param.get("CTRT_ID") == null || param.get("CTRT_ID").equals("")){
					Object[] arguments = { "CTRT_ID" };
					throw new AppException("MSG.M13.MSG00025", ".MSG00025", arguments);  //필수값을 입력해 주세요.(항목 : {0})
				}
				if(param.get("PROD_CD") == null || param.get("PROD_CD").equals("")){
					Object[] arguments = { "PROD_CD" };
					throw new AppException("MSG.M13.MSG00025", ".MSG00025", arguments);  //필수값을 입력해 주세요.(항목 : {0})
				}
				
			}
			
			
		}else{
			Object[] arguments = { "ORDER_TP" };
			throw new AppException("MSG.M13.MSG00025", ".MSG00025", arguments);  //필수값을 입력해 주세요.(항목 : {0})	- ORDER_TP 값 오류
		}
		
		
		
	}
	
}
