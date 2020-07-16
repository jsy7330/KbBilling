package com.ntels.ccbs.customer.service.contract.contract.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.CtrtPromVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderManagementMapper;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderProcessMapper;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderProcessService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;



/**
 * <PRE>
 * 1. ClassName: OrderProcessI201ServiceImpl
 * 2. FileName : OrderProcessI201ServiceImpl.java
 * 3. Package  : com.ntels.ccbs.customer.service.contract.contract.order
 * 4. Comment  : IOT 기본 상품 변경
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 23. 오후 5:17:00
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 23.    : 신규개발
 * </PRE>
 */
@Service
public class OrderProcessI201ServiceImpl extends OrderProcessServiceImpl implements OrderProcessService{
	
    private final Log logger = LogFactory.getLog(getClass());

    private static final String[] SVC_CD = {"SV096"}; //IOT 서비스
	private static final String ORDER_TP = "201"; //오더유형
	private static final String[] CTRT_STAT = {"20"}; //이전 계약 상태
	
	@Autowired
	private ContractManagementService contractManagementService;
	
	@Autowired
	private OrderProcessMapper orderProcessMapper;
	
	@Autowired
	private OrderManagementMapper orderManagementMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public OrderRequestInfoVO createOrder(OrderRequestInfoVO orderInfo)
	        throws ServiceException {
		
		boolean isSvc = false;
		for(String svcCd : SVC_CD){
			if(orderInfo != null && svcCd.equals(orderInfo.getBasicSvcCd())){
				isSvc = true;
				break;
			}
		}
		if(isSvc == false){
			return null;
		}
		
		if(orderInfo != null && ORDER_TP.equals(orderInfo.getOrderTp()) == false){
			return null;
		}
		
		logger.debug("########## IOT 201 Create Order START ###########");
		
		processPreOrderMakeCommon(orderInfo); //오더 작성 전 공통 처리
		processPreOrderMake(orderInfo); //오더 작성 전 처리
		processOrderMake(orderInfo);  // 오더 작성
		processPostOrderMakeCommon(orderInfo); // 오더작성 후 공통 처리
		processPostOrderMake(orderInfo);  //오더 작성 후 처리
		
		logger.debug("########## IOT 201 Create Order End ###########");
		
		return orderInfo;
	}
	
	@Override
	public OrderRequestInfoVO cancelOrder(OrderRequestInfoVO orderInfo)
	        throws ServiceException {
		boolean isSvc = false;
		for(String svcCd : SVC_CD){
			if(orderInfo != null && svcCd.equals(orderInfo.getBasicSvcCd())){
				isSvc = true;
				break;
			}
		}
		if(isSvc == false){
			return null;
		}
		
		if(orderInfo != null && ORDER_TP.equals(orderInfo.getOrderTp()) == false){
			return null;
		}
		
		logger.debug("########## IOT 201 Cancel Order START ###########");
		
		processPreOrderCancelCommon(orderInfo);
		processPreOrderCancel(orderInfo);
		processOrderCancel(orderInfo);
		processPostOrderCancelCommon(orderInfo);
		processPostOrderCancel(orderInfo);
		
		logger.debug("########## IOT 201 Cancel Order END ###########");
		
		return orderInfo;
	}
	
	@Override
	public OrderRequestInfoVO saveOrder(OrderRequestInfoVO orderInfo)
	        throws ServiceException {
		boolean isSvc = false;
		for(String svcCd : SVC_CD){
			if(orderInfo != null && svcCd.equals(orderInfo.getBasicSvcCd())){
				isSvc = true;
				break;
			}
		}
		if(isSvc == false){
			return null;
		}
		
		if(orderInfo != null && ORDER_TP.equals(orderInfo.getOrderTp()) == false){
			return null;
		}
		
		logger.debug("########## IOT 201 Save Order START ###########");
		processPreOrderCmplCommon(orderInfo);
		processPreOrderCmpl(orderInfo);
		processOrderCmpl(orderInfo);
		processPostOrderCmplCommon(orderInfo);
		processPostOrderCmpl(orderInfo);
		logger.debug("########## IOT 201 Save Order END ###########");
		return orderInfo;
	}
	
	
	@Override
	public OrderRequestInfoVO progressOrder(OrderRequestInfoVO orderInfo) throws ServiceException {
		boolean isSvc = false;
		for(String svcCd : SVC_CD){
			if(orderInfo != null && svcCd.equals(orderInfo.getBasicSvcCd())){
				isSvc = true;
				break;
			}
		}
		if(isSvc == false){
			return null;
		}
		
		if(orderInfo != null && ORDER_TP.equals(orderInfo.getOrderTp()) == false){
			return null;
		}
		
		logger.debug("########## IOT 201 Progress Order START ###########");
		processPreOrderProgressCommon(orderInfo);
		processPreOrderProgress(orderInfo);
		processOrderProgress(orderInfo);
		processPostOrderProgressCommon(orderInfo);
		processPostOrderProgress(orderInfo);
		logger.debug("########## IOT 201 Progress Order END ###########");
		return orderInfo;
	}
	
	@Override
	public void processPreOrderMake(OrderRequestInfoVO orderInfo) throws ServiceException {
		logger.debug("########## processPreOrderMake ###########");
		
		//기본상품 체크
		if(orderInfo.getAddBasicProdList() == null || orderInfo.getAddBasicProdList().size() == 0){
			throw new ServiceException("MSG.M01.MSG00059");
		}
		//기본상품 사용 가능 체크
		Map<String,Object> basicInfo = orderInfo.getAddBasicProdList().get(0);
		int basicProdCnt = orderProcessMapper.getBasicProdCnt(orderInfo.getSoId(), (String)basicInfo.get("prodGrp"), (String)basicInfo.get("svcCd"),(String)basicInfo.get("prodCd"),orderInfo.getOrderReqDt());
		if(basicProdCnt == 0){
			throw new ServiceException("MSG.M08.MSG00032");
		}
		
		//기본상품의 전환 불가 체크(추후)TO_DO
		//부가 상품의 베티관계
		//부가 상품의 종속관계
				
		
		//기본상품 협정가 체크(협정가 불가 상품은 수량 및 협정할인률 적용 불가)
		Map<String,Object> basicProdNegoAttr = orderManagementMapper.getProdAttr(orderInfo.getSoId(), (String)basicInfo.get("prodCd"), "AT031",orderInfo.getOrderReqDt());
		if(!"1".equals(basicProdNegoAttr.get("ATTR_VAL"))){
			if(!"0".equals((String)basicInfo.get("onetimeFeeNegoRate")) || 
					!"0".equals((String)basicInfo.get("monthlyFeeNegoRate"))){
				String[] arg = {(String) basicInfo.get("prodNm"), (String) basicInfo.get("prodCd")};
				throw new ServiceException("MSG.M14.MSG00027", arg);
			}
		}
		

		//기본 상품의 협정가 범위 체크
		if("1".equals(basicProdNegoAttr.get("ATTR_VAL"))){
			Map<String,Object> minNegoAttr = orderManagementMapper.getProdAttr(orderInfo.getSoId(), (String)basicInfo.get("prodCd"), "AT032",orderInfo.getOrderReqDt());
			Map<String,Object> maxNegoAttr = orderManagementMapper.getProdAttr(orderInfo.getSoId(), (String)basicInfo.get("prodCd"), "AT033",orderInfo.getOrderReqDt());
			
			//협정가 범위 미설정
			if(minNegoAttr == null || minNegoAttr.isEmpty() || StringUtils.isEmpty((String)minNegoAttr.get("ATTR_VAL"))){
				String[] arg = {(String) basicInfo.get("prodNm")};
				throw new ServiceException("MSG.M07.MSG00131", arg);
			}
			// 협정가 범위 미설정
			if(maxNegoAttr == null || maxNegoAttr.isEmpty() || StringUtils.isEmpty((String)maxNegoAttr.get("ATTR_VAL"))){
				String[] arg = {(String) basicInfo.get("prodNm")};
				throw new ServiceException("MSG.M07.MSG00131", arg);
				
			}
			int minValue = 0;
			int maxValue = 0;
			try{
				minValue = Integer.parseInt((String)minNegoAttr.get("ATTR_VAL"));
				maxValue = Integer.parseInt((String)maxNegoAttr.get("ATTR_VAL"));
			}catch(Exception e){
				String[] arg = {(String) basicInfo.get("prodNm")};
				throw new ServiceException("MSG.M07.MSG00132", arg);
			}
			
			//일회성협정가체크
			if(StringUtils.isNotEmpty((String)basicInfo.get("onetimeFee")) && !"0".equals((String)basicInfo.get("onetimeFee"))){
				try{
					int onetimeFeeNegoRate = Integer.parseInt((String)basicInfo.get("onetimeFeeNegoRate"));
					
					if(onetimeFeeNegoRate < minValue || onetimeFeeNegoRate > maxValue){
						String[] arg = {(String) basicInfo.get("prodNm"), String.valueOf(minValue), String.valueOf(maxValue)};
						throw new ServiceException("MSG.M14.MSG00030", arg);
					}
						
				}catch(ServiceException e){
					throw e;
				}catch(Exception e){
					//일회성 협정할인률 오류
					String[] arg = {(String) basicInfo.get("prodNm")};
					throw new ServiceException("MSG.M14.MSG00029", arg);
				}
			}
			
			//월정액협정가체크
			if(StringUtils.isNotEmpty((String)basicInfo.get("monthlyFee")) && !"0".equals((String)basicInfo.get("monthlyFee"))){
				try{
					int monthlyFeeNegoRate = Integer.parseInt((String)basicInfo.get("monthlyFeeNegoRate"));
					
					if(monthlyFeeNegoRate < minValue || monthlyFeeNegoRate > maxValue){
						String[] arg = {(String) basicInfo.get("prodNm"), String.valueOf(minValue), String.valueOf(maxValue)};
						throw new ServiceException("MSG.M14.MSG00030", arg);
					}
						
				}catch(ServiceException e){
					throw e;
				}catch(Exception e){
					//월정액 협정할인률 오류
					String[] arg = {(String) basicInfo.get("prodNm")};
					throw new ServiceException("MSG.M14.MSG00029", arg);
				}
			}
		}
		
		
		
		//사용중인 부가 상품 체크
		Map<String,Object> baicProdInfo = orderManagementMapper.getCtrtBasicInfoInUse(orderInfo.getSoId(), orderInfo.getCustId(), orderInfo.getCtrtId(), orderInfo.getOrderReqDt(), orderInfo.getLng());
		
		List<Map<String, Object>> addProdList = orderManagementMapper.getAddProdList(orderInfo.getSoId(), (String)basicInfo.get("prodGrp"), (String)basicInfo.get("prodCd") , orderInfo.getOrderReqDt(), orderInfo.getLng());
		//사용중인 서비스 구성 정보 조회
		List<OSvcCmpsVO> svcCmpsList = orderManagementMapper.getSvcCmpsInfoInUse(orderInfo.getSoId(), orderInfo.getCustId(), orderInfo.getCtrtId(), "");
		List<OSvcCmpsExtVO> svcCmpsExtList = new ArrayList<OSvcCmpsExtVO>();
		for(OSvcCmpsVO svcCmps : svcCmpsList){
			if(StringUtils.isEmpty(svcCmps.getExtId())){
				continue;
			}
			svcCmpsExtList.add(orderManagementMapper.getSvcCmpsExtInfo(svcCmps.getExtId()));
		}
		
		//부가상품리스트 조회
		List<Map<String,Object>> addProdInfoList = orderManagementMapper.getAllAddProdList(orderInfo.getSoId(), (String)baicProdInfo.get("prod_grp"), (String)baicProdInfo.get("prod_cd"), orderInfo.getOrderReqDt(), orderInfo.getLng());
		List<Map<String,Object>> addConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : svcCmpsList){
			Map<String,Object> confAdd = new HashMap<String,Object>();
			for(Map<String,Object> addInfo : addProdInfoList){
				if(svcCmps.getProdCd().equals((String)addInfo.get("add_prod_cd"))){
					confAdd.putAll(addInfo);
					confAdd.put("prod_cmps_id", svcCmps.getProdCmpsId());
					confAdd.put("rate_strt_dt", svcCmps.getRateStrtDt());
					break;
				}
			}
			
			if(confAdd.isEmpty())
				continue;
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : svcCmpsExtList){
					if(svcCmps.getExtId().equals(svcExt.getExtId())){
						confAdd.put("add_cnt", svcExt.getProdCnt());
						confAdd.put("monthly_fee", svcExt.getMonthlyAmt());
						confAdd.put("onetime_fee", svcExt.getOnetimeAmt());
						break;
					}
				}
			}
			//삭제 요청 상품은 제외
			boolean isIncludeDelProd = false;
			for(Map<String,Object> delAddon : orderInfo.getDelAddonProdList()){
				if(svcCmps.getProdCmpsId().equals(delAddon.get("prodCmpsId"))){
					isIncludeDelProd = true;
					break;
				}
			}
			
			if(isIncludeDelProd) continue;
			addConfList.add(confAdd);
		}
		
		//추가 요청 상품 추가
		for(Map<String,Object> addProd : orderInfo.getAddAddonProdList()){
			Map<String,Object> prod = new HashMap<String,Object>();
			prod.put("add_prod_cd", addProd.get("prodCd"));
			prod.put("add_prod_nm", addProd.get("prodNm"));
			addConfList.add(prod);
		}
		
		//필수상품체크
		for(Map<String,Object> prod : addProdList){
			if("Y".equals(prod.get("add_prod_mandatory_yn"))){
				boolean isIncludeMandatoryProd = false; 
				for(Map<String,Object> addProd : addConfList){
					if(addProd.get("add_prod_cd").equals(prod.get("add_prod_cd"))){
						isIncludeMandatoryProd = true;
						break;
					}
				}
				
				if(isIncludeMandatoryProd == false){
					String[] arg = {(String) prod.get("add_prod_nm"), (String) prod.get("add_prod_cd")};
					throw new ServiceException("MSG.M13.MSG00022", arg);
				}
				
			}
		}
		
		
		//부가서비스간 필수 관계 체크
		for(Map<String,Object> addProd : addConfList){
			List<Map<String,Object>> mandatoryProdList = orderManagementMapper.getMandatoryProdList(orderInfo.getSoId(), (String)addProd.get("add_prod_cd"), orderInfo.getOrderReqDt());
			for(Map<String,Object> mProd : mandatoryProdList){
				boolean isIncludeMandatoryProd = false;
				for(Map<String, Object> prod : addConfList){
					if(mProd.get("prod_cd").equals(prod.get("add_prod_cd"))){
						isIncludeMandatoryProd = true;
						break;
					}
				}
				if(isIncludeMandatoryProd == false){
					String[] arg = {(String)addProd.get("add_prod_nm"), (String)addProd.get("add_prod_cd"), (String) mProd.get("prod_nm"), (String) mProd.get("prod_cd")};
					throw new ServiceException("MSG.M13.MSG00029", arg);
				}
			}
		}
		
		//부가서비스간 베타 관계 체크
		for(Map<String,Object> addProd : addConfList){
			List<Map<String,Object>> exclusiveProdList = orderManagementMapper.getExclusiveProdList(orderInfo.getSoId(), (String)addProd.get("add_prod_cd"), orderInfo.getOrderReqDt());
			for(Map<String,Object> xProd : exclusiveProdList){
				boolean isIncludeExclusiveProd = false;
				for(Map<String, Object> prod : addConfList){
					if(xProd.get("prod_cd").equals(prod.get("add_prod_cd"))){
						isIncludeExclusiveProd = true;
						break;
					}
				}
				if(isIncludeExclusiveProd == true){
					String[] arg = {(String)addProd.get("add_prod_nm"), (String)addProd.get("add_prod_cd"), (String) xProd.get("prod_nm"), (String) xProd.get("prod_cd")};
					throw new ServiceException("MSG.M13.MSG00023", arg);
				}
			}
		}
		
		//사용가능 부가 체크
		for(Map<String,Object> addProd : orderInfo.getAddAddonProdList()){
			boolean isIncludeAddProd = false;
			for(Map<String,Object> prod : addProdList){
				if(prod.get("add_prod_cd").equals(addProd.get("prodCd"))){
					isIncludeAddProd = true;
					break;
				}
			}
			if(isIncludeAddProd == false){
				String[] arg = {(String) addProd.get("prodNm"), (String) addProd.get("prodCd")};
				throw new ServiceException("MSG.M07.MSG00009", arg);
			}
		}
		
		
		for(Map<String,Object> delAdd : orderInfo.getDelAddonProdList()){
			boolean isIncludeDelProd = false;
			for(OSvcCmpsVO prod : svcCmpsList){
				if(prod.getProdCmpsId().equals(delAdd.get("prodCmpsId"))){
					isIncludeDelProd = true;
					break;
				}
			}
			if(isIncludeDelProd == false){
				String[] arg = {(String) delAdd.get("prodCdNm"), (String) delAdd.get("prodCd")};
				throw new ServiceException("MSG.M14.MSG00031", arg);
			}
		}
		
		//부가 협정가 가능 체크(협정가 불가 상품은 수량 및 협정할인률 적용 불가)
		for(Map<String,Object> prod : addProdList){
			if(!"1".equals(prod.get("is_nego"))){
				for(Map<String,Object> addProd : orderInfo.getAddAddonProdList()){
					if(prod.get("add_prod_cd").equals(addProd.get("prodCd"))){
						if(!"0".equals((String)addProd.get("onetimeFeeNegoRate")) || 
								!"0".equals((String)addProd.get("monthlyFeeNegoRate"))){
							String[] arg = {(String) addProd.get("prodNm"), (String) addProd.get("prodCd")};
							throw new ServiceException("MSG.M14.MSG00027", arg);
						}
					}
				}
			}
		}
		

		//부가상품의 협정가 체크
		for(Map<String,Object> prod : addProdList){
			if("1".equals(prod.get("is_nego"))){
				for(Map<String,Object> addProd : orderInfo.getAddAddonProdList()){
					if(prod.get("add_prod_cd").equals(addProd.get("prodCd"))){
						
						Map<String,Object> minNegoAttr = orderManagementMapper.getProdAttr(orderInfo.getSoId(), (String)addProd.get("prodCd"), "AT032",orderInfo.getOrderReqDt());
						Map<String,Object> maxNegoAttr = orderManagementMapper.getProdAttr(orderInfo.getSoId(), (String)addProd.get("prodCd"), "AT033",orderInfo.getOrderReqDt());
						//협정가 범위 미설정
						if(minNegoAttr == null || minNegoAttr.isEmpty() || StringUtils.isEmpty((String)minNegoAttr.get("ATTR_VAL"))){
							String[] arg = {(String) addProd.get("prodNm")};
							throw new ServiceException("MSG.M07.MSG00131", arg);
						}
						// 협정가 범위 미설정
						if(maxNegoAttr == null || maxNegoAttr.isEmpty() || StringUtils.isEmpty((String)maxNegoAttr.get("ATTR_VAL"))){
							String[] arg = {(String) addProd.get("prodNm")};
							throw new ServiceException("MSG.M07.MSG00131", arg);
							
						}
						int minValue = 0;
						int maxValue = 0;
						try{
							minValue = Integer.parseInt((String)minNegoAttr.get("ATTR_VAL"));
							maxValue = Integer.parseInt((String)maxNegoAttr.get("ATTR_VAL"));
						}catch(Exception e){
							String[] arg = {(String) addProd.get("prodNm")};
							throw new ServiceException("MSG.M07.MSG00132", arg);
						}
						
						//일회성협정가체크
						if(StringUtils.isNotEmpty((String)addProd.get("onetimeFee")) && !"0".equals((String)addProd.get("onetimeFee"))){
							try{
								int onetimeFeeNegoRate = Integer.parseInt((String)addProd.get("onetimeFeeNegoRate"));
								
								if(onetimeFeeNegoRate < minValue || onetimeFeeNegoRate > maxValue){
									String[] arg = {(String) addProd.get("prodNm"), String.valueOf(minValue), String.valueOf(maxValue)};
									throw new ServiceException("MSG.M14.MSG00030", arg);
								}
									
							}catch(ServiceException e){
								throw e;
							}catch(Exception e){
								//일회성 협정할인률 오류
								String[] arg = {(String) addProd.get("prodNm")};
								throw new ServiceException("MSG.M14.MSG00029", arg);
							}
						}
						
						//월정액협정가체크
						if(StringUtils.isNotEmpty((String)addProd.get("monthlyFee")) && !"0".equals((String)addProd.get("monthlyFee"))){
							try{
								int monthlyFeeNegoRate = Integer.parseInt((String)addProd.get("monthlyFeeNegoRate"));
								
								if(monthlyFeeNegoRate < minValue || monthlyFeeNegoRate > maxValue){
									String[] arg = {(String) addProd.get("prodNm"), String.valueOf(minValue), String.valueOf(maxValue)};
									throw new ServiceException("MSG.M14.MSG00030", arg);
								}
									
							}catch(ServiceException e){
								throw e;
							}catch(Exception e){
								//월정액 협정할인률 오류
								String[] arg = {(String) addProd.get("prodNm")};
								throw new ServiceException("MSG.M14.MSG00029", arg);
							}
						}
					}
				}
			}
		}
		
		
		//가입 가능한 장비상품정보
		List<Map<String, Object>> addDeviceProd = orderManagementMapper.getDeviceProdList(orderInfo.getSoId(), (String)basicInfo.get("prodGrp"), (String)basicInfo.get("prodCd") , orderInfo.getOrderReqDt(), orderInfo.getLng());
		//사용중인 단말상품리스트 조회
		List<Map<String,Object>> deviceProdInfoList = orderManagementMapper.getAllDeviceProdList(orderInfo.getSoId(), (String)baicProdInfo.get("prod_grp"), (String)baicProdInfo.get("prod_cd"), orderInfo.getOrderReqDt(), orderInfo.getLng());
		List<Map<String,Object>> deviceConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : svcCmpsList){
			Map<String,Object> confDevice = new HashMap<String,Object>();
			for(Map<String,Object> deviceInfo : deviceProdInfoList){
				if(svcCmps.getProdCd().equals((String)deviceInfo.get("device_prod_cd"))){
					confDevice.putAll(deviceInfo);
					confDevice.put("prod_cmps_id", svcCmps.getProdCmpsId());
					confDevice.put("rate_strt_dt", svcCmps.getRateStrtDt());
					break;
				}
			}
			if(confDevice.isEmpty())
				continue;
			
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : svcCmpsExtList){
					if(svcCmps.getExtId().equals(svcExt.getExtId())){
						confDevice.put("device_cnt", svcExt.getProdCnt());
						confDevice.put("monthly_fee", svcExt.getMonthlyAmt());
						confDevice.put("onetime_fee", svcExt.getOnetimeAmt());
						break;
					}
				}
			}
			
			//삭제 요청 상품은 제외
			boolean isIncludeDelProd = false;
			for(Map<String,Object> delDevice : orderInfo.getDelDeviceProdList()){
				if(svcCmps.getProdCmpsId().equals(delDevice.get("prodCmpsId"))){
					isIncludeDelProd = true;
					break;
				}
			}
			
			if(isIncludeDelProd) continue;
			deviceConfList.add(confDevice);
		}
		//추가 요청 상품 추가
		for(Map<String,Object> addProd : orderInfo.getAddDeviceProdList()){
			Map<String,Object> prod = new HashMap<String,Object>();
			prod.put("device_prod_cd", addProd.get("prodCd"));
			prod.put("device_prod_cd_nm", addProd.get("prodNm"));
			deviceConfList.add(prod);
		}

		//필수상품체크
		for(Map<String,Object> prod : addDeviceProd){
			if("Y".equals(prod.get("is_mandatory_yn"))){
				boolean isIncludeMandatoryProd = false; 
				for(Map<String,Object> addProd : deviceConfList){
					if(addProd.get("device_prod_cd").equals(prod.get("device_prod_cd"))){
						isIncludeMandatoryProd = true;
						break;
					}
				}
				
				if(isIncludeMandatoryProd == false){
					String[] arg = {(String) prod.get("device_prod_cd_nm"), (String) prod.get("device_prod_cd")};
					throw new ServiceException("MSG.M13.MSG00022", arg);
				}
				
			}
		}
		
		//사용가능 단말 체크
		for(Map<String,Object> addDevice : orderInfo.getAddDeviceProdList()){
			boolean isIncludeAddProd = false;
			for(Map<String,Object> prod : addDeviceProd){
				if(prod.get("device_prod_cd").equals(addDevice.get("prodCd"))){
					isIncludeAddProd = true;
					break;
				}
			}
			if(isIncludeAddProd == false){
				String[] arg = {(String) addDevice.get("prodNm"), (String) addDevice.get("prodCd")};
				throw new ServiceException("MSG.M07.MSG00009", arg);
			}
		}
		
		//삭제 단말 상태 체크
		for(Map<String,Object> delDevice : orderInfo.getDelDeviceProdList()){
			boolean isIncludeDelProd = false;
			for(OSvcCmpsVO prod : svcCmpsList){
				if(prod.getProdCmpsId().equals(delDevice.get("prodCmpsId"))){
					isIncludeDelProd = true;
					break;
				}
			}
			if(isIncludeDelProd == false){
				String[] arg = {(String) delDevice.get("prodCdNm"), (String) delDevice.get("prodCd")};
				throw new ServiceException("MSG.M14.MSG00031", arg);
			}
		}
		
		//장비 협정가 가능 체크(협정가 불가 상품은 수량 및 협정할인률 적용 불가)
		for(Map<String,Object> prod : addDeviceProd){
			if(!"1".equals(prod.get("is_nego"))){
				for(Map<String,Object> deviceProd : orderInfo.getAddDeviceProdList()){
					if(prod.get("device_prod_cd").equals(deviceProd.get("prodCd"))){
						if(!"0".equals((String)deviceProd.get("onetimeFeeNegoRate")) || 
								!"0".equals((String)deviceProd.get("monthlyFeeNegoRate"))){
							String[] arg = {(String) deviceProd.get("prodNm"), (String) deviceProd.get("prodCd")};
							throw new ServiceException("MSG.M14.MSG00027", arg);
						}
					}
				}
			}
		}
		

		//장비상품의 협정가 체크
		for(Map<String,Object> prod : addDeviceProd){
			if("1".equals(prod.get("is_nego"))){
				for(Map<String,Object> deviceProd : orderInfo.getAddDeviceProdList()){
					if(prod.get("device_prod_cd").equals(deviceProd.get("prodCd"))){
						
						Map<String,Object> minNegoAttr = orderManagementMapper.getProdAttr(orderInfo.getSoId(), (String)deviceProd.get("prodCd"), "AT032",orderInfo.getOrderReqDt());
						Map<String,Object> maxNegoAttr = orderManagementMapper.getProdAttr(orderInfo.getSoId(), (String)deviceProd.get("prodCd"), "AT033",orderInfo.getOrderReqDt());
						//협정가 범위 미설정
						if(minNegoAttr == null || minNegoAttr.isEmpty() || StringUtils.isEmpty((String)minNegoAttr.get("ATTR_VAL"))){
							String[] arg = {(String) deviceProd.get("prodNm")};
							throw new ServiceException("MSG.M07.MSG00131", arg);
						}
						// 협정가 범위 미설정
						if(maxNegoAttr == null || maxNegoAttr.isEmpty() || StringUtils.isEmpty((String)maxNegoAttr.get("ATTR_VAL"))){
							String[] arg = {(String) deviceProd.get("prodNm")};
							throw new ServiceException("MSG.M07.MSG00131", arg);
							
						}
						int minValue = 0;
						int maxValue = 0;
						try{
							minValue = Integer.parseInt((String)minNegoAttr.get("ATTR_VAL"));
							maxValue = Integer.parseInt((String)maxNegoAttr.get("ATTR_VAL"));
						}catch(Exception e){
							String[] arg = {(String) deviceProd.get("prodNm")};
							throw new ServiceException("MSG.M07.MSG00132", arg);
						}
						
						//일회성협정가체크
						if(StringUtils.isNotEmpty((String)deviceProd.get("onetimeFee")) && !"0".equals((String)deviceProd.get("onetimeFee"))){
							try{
								int onetimeFeeNegoRate = Integer.parseInt((String)deviceProd.get("onetimeFeeNegoRate"));
								
								if(onetimeFeeNegoRate < minValue || onetimeFeeNegoRate > maxValue){
									String[] arg = {(String) deviceProd.get("prodNm"), String.valueOf(minValue), String.valueOf(maxValue)};
									throw new ServiceException("MSG.M14.MSG00030", arg);
								}
									
							}catch(ServiceException e){
								throw e;
							}catch(Exception e){
								//일회성 협정할인률 오류
								String[] arg = {(String) deviceProd.get("prodNm")};
								throw new ServiceException("MSG.M14.MSG00029", arg);
							}
						}
						
						//월정액협정가체크
						if(StringUtils.isNotEmpty((String)deviceProd.get("monthlyFee")) && !"0".equals((String)deviceProd.get("monthlyFee"))){
							try{
								int monthlyFeeNegoRate = Integer.parseInt((String)deviceProd.get("monthlyFeeNegoRate"));
								
								if(monthlyFeeNegoRate < minValue || monthlyFeeNegoRate > maxValue){
									String[] arg = {(String) deviceProd.get("prodNm"), String.valueOf(minValue), String.valueOf(maxValue)};
									throw new ServiceException("MSG.M14.MSG00030", arg);
								}
									
							}catch(ServiceException e){
								throw e;
							}catch(Exception e){
								//월정액 협정할인률 오류
								String[] arg = {(String) deviceProd.get("prodNm")};
								throw new ServiceException("MSG.M14.MSG00029", arg);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void processPostOrderMake(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPostOrderMake ###########");
		
		if(orderInfo.getAddBasicProdList() != null){
			//기본상품정보
			Map<String,Object> basicProdInfo = orderInfo.getAddBasicProdList().get(0);
			//기본상품의 서비스 구성 조회
			OSvcCmpsVO basicOsvcCmpsInfo = orderProcessMapper.getOSvcCmpsInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_TP, (String)basicProdInfo.get("prodCd"), (String)basicProdInfo.get("svcCmpsId"));
			if(basicOsvcCmpsInfo == null || StringUtils.isEmpty(basicOsvcCmpsInfo.getSoId())){
				throw new ServiceException("MSG.M08.MSG00021");
			}
			
			//기본상품의 서비스 확장 채번
			String basicSvcExdId = sequenceService.createNewSequenceYealy(Consts.SEQ_CODE.CM_CTRT_EXT_ID, "YYYY", 14);
			
			
			//기본상품의 서비스 구성
			OSvcCmpsExtVO osvcBasicCmpsExtInfo = new OSvcCmpsExtVO();
			osvcBasicCmpsExtInfo.setExtId(basicSvcExdId);
			osvcBasicCmpsExtInfo.setOrderId(orderInfo.getOrderId());
			osvcBasicCmpsExtInfo.setInactDttm("99991231235959");
			osvcBasicCmpsExtInfo.setActDttm(orderInfo.getOrderReqDttm());
			osvcBasicCmpsExtInfo.setMonthlyAmt(StringUtils.isEmpty((String)basicProdInfo.get("monthlyFee")) ? "0" : (String)basicProdInfo.get("monthlyFee"));
			osvcBasicCmpsExtInfo.setMonthlyNegoRate(StringUtils.isEmpty((String)basicProdInfo.get("monthlyFeeNegoRate")) ? "0" : (String)basicProdInfo.get("monthlyFeeNegoRate"));
			osvcBasicCmpsExtInfo.setMonthlyNegoAmt(StringUtils.isEmpty((String)basicProdInfo.get("monthlyFeeNegoAmt")) ? "0" : (String)basicProdInfo.get("monthlyFeeNegoAmt"));
			osvcBasicCmpsExtInfo.setOnetimeAmt(StringUtils.isEmpty((String)basicProdInfo.get("onetimeFee")) ? "0" : (String)basicProdInfo.get("onetimeFee"));
			osvcBasicCmpsExtInfo.setOnetimeNegoRate(StringUtils.isEmpty((String)basicProdInfo.get("onetimeFeeNegoRate")) ? "0" : (String)basicProdInfo.get("onetimeFeeNegoRate"));
			osvcBasicCmpsExtInfo.setOnetimeNegoAmt(StringUtils.isEmpty((String)basicProdInfo.get("onetimeFeeNegoAmt")) ? "0" : (String)basicProdInfo.get("onetimeFeeNegoAmt"));
			osvcBasicCmpsExtInfo.setOnetimeInstlCnt(StringUtils.isEmpty((String)basicProdInfo.get("onetimeInstMonths")) ? "0" : (String)basicProdInfo.get("onetimeInstMonths"));
			osvcBasicCmpsExtInfo.setProdCnt(StringUtils.isEmpty((String)basicProdInfo.get("prodCnt")) ? "1" : (String)basicProdInfo.get("prodCnt"));
			osvcBasicCmpsExtInfo.setOther("");
			osvcBasicCmpsExtInfo.setChgDate(orderInfo.getOrderReqDate());
			osvcBasicCmpsExtInfo.setChgrId(orderInfo.getReqUsrId());
			osvcBasicCmpsExtInfo.setRegDate(orderInfo.getOrderReqDate());
			osvcBasicCmpsExtInfo.setRegrId(orderInfo.getReqUsrId());
			
			//기본상품의 서비스 구성 확장 추가
			orderProcessMapper.insertOrderSvcCmpsExt(osvcBasicCmpsExtInfo);
			orderProcessMapper.updateOSvcExtId(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_TP, (String)orderInfo.getAddBasicProdList().get(0).get("prodCd"), (String)orderInfo.getAddBasicProdList().get(0).get("svcCmpsId"), basicSvcExdId);
			
		}
		

		//장비 상품구성, 서비스 구성 확장 정보 생성
		for(Map<String,Object> deviceProd : orderInfo.getAddDeviceProdList()){
			//장비 서비스구성 정보 조회
			OSvcCmpsVO osvcCmpsInfo = orderProcessMapper.getOSvcCmpsInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_TP, (String)deviceProd.get("prodCd"), (String)deviceProd.get("svcCmpsId"));
			if(osvcCmpsInfo == null || StringUtils.isEmpty(osvcCmpsInfo.getSoId())){
				throw new ServiceException("MSG.M08.MSG00021");
			}
			//장비 서비스구성 정보 확장 ID채번
			String svcExdId = sequenceService.createNewSequenceYealy(Consts.SEQ_CODE.CM_CTRT_EXT_ID, "YYYY", 14);
			//장비 서비스구성 정보 수정
			orderProcessMapper.updateOSvcExtId(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_TP, (String)deviceProd.get("prodCd"),(String)deviceProd.get("svcCmpsId"), svcExdId);
			//장비 서비스구성 확장 정보 추가
			OSvcCmpsExtVO osvcCmpsExtInfo = new OSvcCmpsExtVO();
			
			osvcCmpsExtInfo.setExtId(svcExdId);
			osvcCmpsExtInfo.setOrderId(orderInfo.getOrderId());
			osvcCmpsExtInfo.setInactDttm("99991231235959");
			osvcCmpsExtInfo.setActDttm(orderInfo.getOrderReqDttm());
			osvcCmpsExtInfo.setOther("");
			osvcCmpsExtInfo.setMonthlyAmt(StringUtils.isEmpty((String)deviceProd.get("monthlyFee")) ? "0" : (String)deviceProd.get("monthlyFee"));
			osvcCmpsExtInfo.setMonthlyNegoRate(StringUtils.isEmpty((String)deviceProd.get("monthlyFeeNegoRate")) ? "0" : (String)deviceProd.get("monthlyFeeNegoRate"));
			osvcCmpsExtInfo.setMonthlyNegoAmt(StringUtils.isEmpty((String)deviceProd.get("monthlyFeeNegoAmt")) ? "0" : (String)deviceProd.get("monthlyFeeNegoAmt"));
			osvcCmpsExtInfo.setOnetimeAmt(StringUtils.isEmpty((String)deviceProd.get("onetimeFee")) ? "0" : (String)deviceProd.get("onetimeFee"));
			osvcCmpsExtInfo.setOnetimeNegoRate(StringUtils.isEmpty((String)deviceProd.get("onetimeFeeNegoRate")) ? "0" : (String)deviceProd.get("onetimeFeeNegoRate"));
			osvcCmpsExtInfo.setOnetimeNegoAmt(StringUtils.isEmpty((String)deviceProd.get("onetimeFeeNegoAmt")) ? "0" : (String)deviceProd.get("onetimeFeeNegoAmt"));
			osvcCmpsExtInfo.setOnetimeInstlCnt(StringUtils.isEmpty((String)deviceProd.get("onetimeInstMonths")) ? "0" : (String)deviceProd.get("onetimeInstMonths"));
			osvcCmpsExtInfo.setProdCnt(StringUtils.isEmpty((String)deviceProd.get("prodCnt")) ? "1" : (String)deviceProd.get("prodCnt"));
			osvcCmpsExtInfo.setOther("");
			osvcCmpsExtInfo.setChgDate(orderInfo.getOrderReqDate());
			osvcCmpsExtInfo.setChgrId(orderInfo.getReqUsrId());
			osvcCmpsExtInfo.setRegDate(orderInfo.getOrderReqDate());
			osvcCmpsExtInfo.setRegrId(orderInfo.getReqUsrId());
			orderProcessMapper.insertOrderSvcCmpsExt(osvcCmpsExtInfo);
		}
		
		//부가 상품구성, 서비스 구성 확장 정보 생성
		for(Map<String,Object> addProd : orderInfo.getAddAddonProdList()){
			//장비 서비스구성 정보 조회
			OSvcCmpsVO osvcCmpsInfo = orderProcessMapper.getOSvcCmpsInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_TP, (String)addProd.get("prodCd"), (String)addProd.get("svcCmpsId"));
			if(osvcCmpsInfo == null || StringUtils.isEmpty(osvcCmpsInfo.getSoId())){
				throw new ServiceException("MSG.M08.MSG00021");
			}
			//장비 서비스구성 정보 확장 ID채번
			String svcExdId = sequenceService.createNewSequenceYealy(Consts.SEQ_CODE.CM_CTRT_EXT_ID, "YYYY", 14);
			//장비 서비스구성 정보 수정
			orderProcessMapper.updateOSvcExtId(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_TP, (String)addProd.get("prodCd"),(String)addProd.get("svcCmpsId"), svcExdId);
			//장비 서비스구성 확장 정보 추가
			OSvcCmpsExtVO osvcCmpsExtInfo = new OSvcCmpsExtVO();
			
			osvcCmpsExtInfo.setExtId(svcExdId);
			osvcCmpsExtInfo.setOrderId(orderInfo.getOrderId());
			osvcCmpsExtInfo.setInactDttm("99991231235959");
			osvcCmpsExtInfo.setActDttm(orderInfo.getOrderReqDttm());
			osvcCmpsExtInfo.setOther("");
			osvcCmpsExtInfo.setMonthlyAmt(StringUtils.isEmpty((String)addProd.get("monthlyFee")) ? "0" : (String)addProd.get("monthlyFee"));
			osvcCmpsExtInfo.setMonthlyNegoRate(StringUtils.isEmpty((String)addProd.get("monthlyFeeNegoRate")) ? "0" : (String)addProd.get("monthlyFeeNegoRate"));
			osvcCmpsExtInfo.setMonthlyNegoAmt(StringUtils.isEmpty((String)addProd.get("monthlyFeeNegoAmt")) ? "0" : (String)addProd.get("monthlyFeeNegoAmt"));
			osvcCmpsExtInfo.setOnetimeAmt(StringUtils.isEmpty((String)addProd.get("onetimeFee")) ? "0" : (String)addProd.get("onetimeFee"));
			osvcCmpsExtInfo.setOnetimeNegoRate(StringUtils.isEmpty((String)addProd.get("onetimeFeeNegoRate")) ? "0" : (String)addProd.get("onetimeFeeNegoRate"));
			osvcCmpsExtInfo.setOnetimeNegoAmt(StringUtils.isEmpty((String)addProd.get("onetimeFeeNegoAmt")) ? "0" : (String)addProd.get("onetimeFeeNegoAmt"));
			osvcCmpsExtInfo.setOnetimeInstlCnt(StringUtils.isEmpty((String)addProd.get("onetimeInstMonths")) ? "0" : (String)addProd.get("onetimeInstMonths"));
			osvcCmpsExtInfo.setProdCnt(StringUtils.isEmpty((String)addProd.get("prodCnt")) ? "1" : (String)addProd.get("prodCnt"));
			osvcCmpsExtInfo.setOther("");
			osvcCmpsExtInfo.setChgDate(orderInfo.getOrderReqDate());
			osvcCmpsExtInfo.setChgrId(orderInfo.getReqUsrId());
			osvcCmpsExtInfo.setRegDate(orderInfo.getOrderReqDate());
			osvcCmpsExtInfo.setRegrId(orderInfo.getReqUsrId());
			orderProcessMapper.insertOrderSvcCmpsExt(osvcCmpsExtInfo);
		}
		
		

		//약정데이터 생성
		boolean isProm = false;
		if(StringUtils.isNotEmpty(orderInfo.getPromCnt()) && !"0".equals(orderInfo.getPromCnt()) && !"00".equals(orderInfo.getPromCnt())){
			isProm = true;
		}
		
		if(isProm){
			
			OCtrtMastVO octrtMast = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
			
			if(StringUtils.isNotEmpty(octrtMast.getPromId())){
				//약정데이터 존재시 사용안함으로 처리
				orderProcessMapper.updatePromCtrtCancle(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), octrtMast.getPromId());
			}
			
			String promCtrtId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_PROM_CTRT_ID, 10);
			String promEndDt = null;
			try{
				Calendar cal = Calendar.getInstance();
		        cal.setTime(new Date());
		        cal.add(Calendar.MONTH, Integer.parseInt(orderInfo.getPromCnt()));
		        cal.add(Calendar.MINUTE, -1440);
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		        promEndDt = dateFormat.format(cal.getTime());
		        
		        CtrtPromVO promCtrtInfo = new CtrtPromVO();
				promCtrtInfo.setPromCtrtId(promCtrtId);
				promCtrtInfo.setRcptDttm(orderInfo.getOrderReqDttm());
				promCtrtInfo.setMstrFl("Y");
				promCtrtInfo.setCtrtAplyStrtDt(orderInfo.getOrderReqDt());
				promCtrtInfo.setCtrtAplyEndDt(promEndDt);
				promCtrtInfo.setCtrtId(orderInfo.getCtrtId());
				promCtrtInfo.setPromCnt(orderInfo.getPromCnt());
				promCtrtInfo.setPromChgCd("01"); // 신규
				promCtrtInfo.setPromChgrsnCd("A"); //고객요청
				promCtrtInfo.setUseYn("Y");
				promCtrtInfo.setRegrId(orderInfo.getReqUsrId());
				promCtrtInfo.setRegDate(orderInfo.getOrderReqDate());
				promCtrtInfo.setChgrId(orderInfo.getReqUsrId());
				promCtrtInfo.setChgDate(orderInfo.getOrderReqDate());
				
				
				//Insert
				orderProcessMapper.insetCtrtPromInfo(promCtrtInfo);
				
				//Update
				orderProcessMapper.updatePromCtrtId(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), promCtrtId);
			}catch(Exception e){
				throw new ServiceException("MSG.M08.MSG00021");
				
			}
		}
		
	}
	@Override
	public void processPreOrderCancel(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPreOrderCancel ###########");
	}
	@Override
	public void processPostOrderCancel(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPostOrderCancel ###########");
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		//시간조회
		String dateTime = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String today = dateTime.substring(0,8);
		String nowTime = dateTime.substring(8);
		
		//상담 완료 처리
		RcptInfoVO rcptInfo = new RcptInfoVO();
		rcptInfo.setSoId(orderInfo.getSoId());
		rcptInfo.setRcptId(orderInfo.getRcptId());
		rcptInfo.setRcptTp("A");
		rcptInfo.setReqTelNo("00000000000");
		rcptInfo.setCustRel("A");
		rcptInfo.setReqDesc(orderInfo.getReqDesc());
		rcptInfo.setProcDesc(orderInfo.getReqDesc());
		rcptInfo.setCnslStat("4");
		rcptInfo.setCmplDt(today);
		rcptInfo.setCmplDm(nowTime);
		rcptInfo.setCmplOrgId(sessionUser == null ?  orderInfo.getReqOrgId() : sessionUser.getOrgId());
		rcptInfo.setCmplUsrId(sessionUser == null ? orderInfo.getReqUsrId() : sessionUser.getUserId());
		rcptInfo.setChgDate(DateUtil.sysdate());
		rcptInfo.setChgrId(sessionUser == null ? orderInfo.getReqUsrId() : sessionUser.getUserId());
		contractManagementService.updateRcptInfoCmpl(rcptInfo, orderInfo.getLng(), orderInfo.getOrderReqDt());
		
		OCtrtMastVO octrtMast = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		
		if(StringUtils.isNotEmpty(octrtMast.getPromId())){
			//약정데이터 존재시 사용안함으로 처리
			orderProcessMapper.updatePromCtrtCancle(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), octrtMast.getPromId());
		}
	}
	@Override
	public void processPreOrderCmpl(OrderRequestInfoVO orderInfo) throws ServiceException{
		//일회성요금 생성
		List<Map<String,Object>> thrwyChrgInfoList = orderProcessMapper.getThrwyInfoList(orderInfo.getOrderId());
		
		for(Map<String,Object> thrwyChrgInfo : thrwyChrgInfoList){
			if(!thrwyChrgInfo.containsKey("ONETIME_AMT")){
				continue;
			}
			try{
				if(thrwyChrgInfo.get("ONETIME_AMT") != null){
					//BigDecimal rateVal = (BigDecimal)thrwyChrgInfo.get("ONETIME_AMT");
					BigDecimal rateVal = new BigDecimal((String)thrwyChrgInfo.get("ONETIME_AMT"));
					if(rateVal.compareTo(new BigDecimal(0)) == 0){
						continue;
					}
				}
			}catch(Exception e){
				logger.error("Error : Onetime Amt Error : " + e.getMessage());
				throw new ServiceException("MSG.M15.MSG00001");
			}
			Map<String, Object> insertParam = new HashMap<String,Object>();
			
			String thrwyChrgId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_THRWY_CHRG_ID, 10);
			
			BigDecimal occAmt = new BigDecimal((String)thrwyChrgInfo.get("ONETIME_AMT")).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal negoRate = new BigDecimal(100 - Integer.parseInt((String)thrwyChrgInfo.get("ONETIME_NEGO_RATE")));
			BigDecimal billAmt = occAmt.multiply(negoRate).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
			BigDecimal dcAmt = occAmt.subtract(billAmt);
			
			
			insertParam.put("thrwyChrgId", thrwyChrgId);
			insertParam.put("reqDt", orderInfo.getOrderReqDt());
			insertParam.put("mstrFl", "Y");
			insertParam.put("ctrtId", orderInfo.getCtrtId());
			insertParam.put("prodCmpsId", thrwyChrgInfo.get("PROD_CMPS_ID"));
			insertParam.put("svcCmpsId", thrwyChrgInfo.get("SVC_CMPS_ID"));
			insertParam.put("prodCd", thrwyChrgInfo.get("PROD_CD"));
			insertParam.put("chrgItmId", thrwyChrgInfo.get("CHRG_ITM_ID"));
			insertParam.put("rateItmCd", thrwyChrgInfo.get("RATE_ITM_CD"));
			insertParam.put("clcWrkNo", "");
			insertParam.put("thrwyChrgOccCl", "10");
			insertParam.put("thrwyChrgResn", "");
			insertParam.put("otChrgStat", "10");
			insertParam.put("occAmt", occAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
			insertParam.put("vat", 0.00);
			insertParam.put("dcAmt", dcAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
			insertParam.put("billAmt", billAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
			insertParam.put("itllmtPymFlg", "N");
			insertParam.put("ipmMmCnt", 1);
			insertParam.put("fullPayYn", "N");
			insertParam.put("adjResnCd", "");
			insertParam.put("prmsCl", "");
			insertParam.put("rcptAmt", 0.00);
			insertParam.put("billYymm", "");
			insertParam.put("billAplyDt", "");
			insertParam.put("rcptMthd", "");
			insertParam.put("cmplDt", "");
			insertParam.put("upymAmt", 0.00);
			insertParam.put("unpayResnCd", "");
			insertParam.put("rcptId", orderInfo.getRcptId());
			insertParam.put("soId", orderInfo.getSoId());
			insertParam.put("rmrk", "");
			insertParam.put("regrId", orderInfo.getReqUsrId());
			insertParam.put("regDate", orderInfo.getOrderReqDate());
			insertParam.put("chgrId", orderInfo.getReqUsrId());
			insertParam.put("chgDate", orderInfo.getOrderReqDate());
			orderProcessMapper.insertThrwyInfo(insertParam);
		}
		
	}
	@Override
	public void processPostOrderCmpl(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPostOrderCmpl ###########");
		SessionUser sessionUser = CommonUtil.getSessionManager();
		//시간조회
		String dateTime = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String today = dateTime.substring(0,8);
		String nowTime = dateTime.substring(8);
		
		//상담 완료 처리
		RcptInfoVO rcptInfo = new RcptInfoVO();
		rcptInfo.setSoId(orderInfo.getSoId());
		rcptInfo.setRcptId(orderInfo.getRcptId());
		rcptInfo.setRcptTp("A");
		rcptInfo.setReqTelNo("00000000000");
		rcptInfo.setCustRel("A");
		rcptInfo.setReqDesc(orderInfo.getReqDesc());
		rcptInfo.setProcDesc(orderInfo.getReqDesc());
		rcptInfo.setCnslStat("4");
		rcptInfo.setCmplDt(today);
		rcptInfo.setCmplDm(nowTime);
		rcptInfo.setCmplOrgId(sessionUser == null ?  orderInfo.getReqOrgId() : sessionUser.getOrgId());
		rcptInfo.setCmplUsrId(sessionUser == null ? orderInfo.getReqUsrId() : sessionUser.getUserId());
		rcptInfo.setChgDate(DateUtil.sysdate());
		rcptInfo.setChgrId(sessionUser == null ? orderInfo.getReqUsrId() : sessionUser.getUserId());
		contractManagementService.updateRcptInfoCmpl(rcptInfo, orderInfo.getLng(), orderInfo.getOrderReqDt());
		

		OCtrtMastVO octrtMast = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		
		if(StringUtils.isNotEmpty(octrtMast.getPromId())){
			//약정데이터 존재시 약정 일자 재조정
			
			String promEndDt = null;
			try{
				CtrtPromVO ctrtPromInfo = orderManagementMapper.getCtrtPromInfo(octrtMast.getPromId(), octrtMast.getCtrtId());
				
				Calendar cal = Calendar.getInstance();
		        cal.setTime(new Date());
		        cal.add(Calendar.MONTH, Integer.parseInt(ctrtPromInfo.getPromCnt()));
		        cal.add(Calendar.MINUTE, -1440);
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		        promEndDt = dateFormat.format(cal.getTime());
		        
		        CtrtPromVO promCtrtInfo = new CtrtPromVO();
				promCtrtInfo.setRcptDttm(orderInfo.getOrderReqDttm());
				promCtrtInfo.setCtrtAplyStrtDt(today);
				promCtrtInfo.setCtrtAplyEndDt(promEndDt);
				promCtrtInfo.setChgrId(orderInfo.getReqUsrId());
				promCtrtInfo.setChgDate(DateUtil.sysdate());
				
				//Update
				orderProcessMapper.updatePromCtrt(ctrtPromInfo);
			}catch(Exception e){
				throw new ServiceException("MSG.M08.MSG00021");
				
			}
		}
	}
	
	@Override
	public void processPreOrderProgress(OrderRequestInfoVO orderInfo) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processPostOrderProgress(OrderRequestInfoVO orderInfo) throws ServiceException {
		// TODO Auto-generated method stub
	}

	@Override
	public void precheckOrder(OrderCommonVO orderCommon) throws ServiceException {
		boolean isSvc = false;
		for(String svcCd : SVC_CD){
			if(orderCommon != null && svcCd.equals(orderCommon.getBasicSvcCd())){
				isSvc = true;
				break;
			}
		}
		if(isSvc == false){
			return;
		}
		
		if(orderCommon != null && ORDER_TP.equals(orderCommon.getOrderTp()) == false){
			return;
		}
		
		//공통 필수항목 체크
		if(StringUtils.isEmpty(orderCommon.getSoId()) ||
				StringUtils.isEmpty(orderCommon.getCustId()) ||
				StringUtils.isEmpty(orderCommon.getCtrtId())){
			throw new ServiceException("MSG.M13.MSG00024");
		}
		
		//계약상태 체크
		String ctrtStat = orderProcessMapper.getCtrtStat(orderCommon.getSoId(), orderCommon.getCustId(), orderCommon.getCtrtId());
		boolean isCheck = false;
		for(String stat : CTRT_STAT){
			if(stat.equals(ctrtStat)){
				isCheck = true;
				break;
			}
		}
		if(!isCheck){
			throw new ServiceException("MSG.M01.MSG00063");
		}
	}
		
}