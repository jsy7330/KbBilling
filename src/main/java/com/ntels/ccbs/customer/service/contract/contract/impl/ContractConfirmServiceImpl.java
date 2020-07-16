package com.ntels.ccbs.customer.service.contract.contract.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.CtrtInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsVO;
import com.ntels.ccbs.customer.mapper.contract.contract.ContractConfirmMapper;
import com.ntels.ccbs.customer.mapper.contract.contract.ContractManagementMapper;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderManagementMapper;
import com.ntels.ccbs.customer.service.contract.contract.ContractConfirmService;
import com.ntels.nisf.util.message.MessageUtil;


@Service
public class ContractConfirmServiceImpl implements ContractConfirmService{

	@Autowired
	private ContractConfirmMapper contractConfirmMapper;
	
	@Autowired
	private ContractManagementMapper contractManagementMapper;
	
	@Autowired
	private OrderManagementMapper orderManagementMapper;

	@Override
	public Map<String, Object> getContractConfirmInfo(String soId, String custId, String ctrtId, String lng,
			String today) {
		Map<String,Object> ctrtInfoMap = new HashMap<String,Object>();
		CtrtInfoVO ctrtInfo =contractManagementMapper.getCtrtInfo(soId, custId, ctrtId, lng, today);
		ctrtInfoMap.put("custInfo", ctrtInfo);
		return ctrtInfoMap;
	}

	@Override
	public CtrtInfoVO getCtrtInfo(String soId, String custId, String ctrtId, String lng, String today) {
		return contractManagementMapper.getCtrtInfo(soId, custId, ctrtId, lng, today);
	}

	@Override
	public Map<String, Object> getCtrtInfo(String soId, String custId, String ctrtId, String lng) {
		if(StringUtils.isEmpty(soId)){
			String[] arg = {MessageUtil.getMessage("LAB.M07.LAB00004")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(custId)){
			String[] arg = {MessageUtil.getMessage("LAB.M01.LAB00046")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(ctrtId)){
			String[] arg = {MessageUtil.getMessage("LAB.M01.LAB00032")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		boolean isTerm = false;
		
		String today = DateUtil.getDateStringYYYYMMDD(0);
		
		//기본계약정보조회
		Map<String,Object> basicProdInfo = orderManagementMapper.getCtrtBasicInfoInUse(soId, custId, ctrtId, today, lng);
		
		if("50".equals(basicProdInfo.get("CTRT_STAT")) || "90".equals(basicProdInfo.get("CTRT_STAT"))){
			isTerm = true;
		}
		
		//사용중인 상품계약정보 조회
		List<OProdCmpsVO> prodCmpsList = orderManagementMapper.getProdCmpsInfoInUse(soId, ctrtId, lng, isTerm ? (String)basicProdInfo.get("ORDER_ID") : "");
		List<OProdCmpsExtVO> prodCmpsExtList = new ArrayList<OProdCmpsExtVO>();
		for(OProdCmpsVO prodCmps : prodCmpsList){
			if(StringUtils.isEmpty(prodCmps.getExtId())){
				continue;
			}
			prodCmpsExtList.add(orderManagementMapper.getProdCmpsExtInfo(prodCmps.getExtId()));
		}
		String basicSvcCd = null;
		//사용중인 서비스정보 조회
		List<OSvcCmpsVO> svcCmpsList = orderManagementMapper.getSvcCmpsInfoInUse(soId, custId, ctrtId, isTerm ? (String)basicProdInfo.get("ORDER_ID") : "");
		List<OSvcCmpsExtVO> svcCmpsExtList = new ArrayList<OSvcCmpsExtVO>();
		for(OSvcCmpsVO svcCmps : svcCmpsList){
			if(svcCmps.getProdCd().equals(basicProdInfo.get("PROD_CD"))){
				basicSvcCd = svcCmps.getSvcCd();
			}
			if(StringUtils.isEmpty(svcCmps.getExtId())){
				continue;
			}
			svcCmpsExtList.add(orderManagementMapper.getSvcCmpsExtInfo(svcCmps.getExtId()));
		}
		//기본상품리스트 조회
		List<Map<String,Object>> basicProdInfoList = orderManagementMapper.getAllBasicProdList(soId, basicSvcCd, (String)basicProdInfo.get("PROD_GRP"), today, lng);
		//단말상품리스트 조회
		List<Map<String,Object>> deviceProdInfoList = orderManagementMapper.getAllDeviceProdList(soId, (String)basicProdInfo.get("PROD_GRP"), (String)basicProdInfo.get("PROD_CD"), today, lng);
		//부가서비스리스트조회
		List<Map<String,Object>> addProdInfoList = orderManagementMapper.getAllAddProdList(soId,  (String)basicProdInfo.get("PROD_GRP"), (String)basicProdInfo.get("PROD_CD"), today, lng);
		
		
		//단말설정정보작성
		List<Map<String,Object>> deviceConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : svcCmpsList){
			Map<String,Object> confDevice = new HashMap<String,Object>();
			for(Map<String,Object> deviceInfo : deviceProdInfoList){
				if(svcCmps.getProdCd().equals((String)deviceInfo.get("device_prod_cd"))){
					confDevice.putAll(deviceInfo);
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
			deviceConfList.add(confDevice);
		}
		
		//부가설정정보작성
		List<Map<String,Object>> addConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : svcCmpsList){
			Map<String,Object> confAdd = new HashMap<String,Object>();
			for(Map<String,Object> addInfo : addProdInfoList){
				if(svcCmps.getProdCd().equals((String)addInfo.get("add_prod_cd"))){
					confAdd.putAll(addInfo);
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
			addConfList.add(confAdd);
		}
		
		//협정가 설정
		List<Map<String,Object>> chargeConfList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> onetimeConfList = new ArrayList<Map<String,Object>>();
		for(OSvcCmpsVO svcCmps : svcCmpsList){
			Map<String,Object> prodInfo = null;
			String prodNm = null;
			for(Map<String,Object> prod : basicProdInfoList){
				if(svcCmps.getProdCd().equals(prod.get("basic_prod_cd"))){
					prodInfo = prod;
					prodNm = (String)prod.get("basic_prod_cd_nm");
					break;
				}
			}
			
			if(prodInfo == null){
				for(Map<String,Object> deviceProdInfo : deviceProdInfoList){
					if(svcCmps.getProdCd().equals(deviceProdInfo.get("device_prod_cd"))){
						prodInfo = deviceProdInfo;
						prodNm = (String)deviceProdInfo.get("device_prod_cd_nm");
						break;
					}
				}
			}
			if(prodInfo == null){
				for(Map<String,Object> addProdInfo : addProdInfoList){
					if(svcCmps.getProdCd().equals(addProdInfo.get("add_prod_cd"))){
						prodInfo = addProdInfo;
						prodNm = (String)addProdInfo.get("add_prod_nm");
						break;
					}
				}
			}
			
			if(prodInfo == null) continue;
			DateFormat sdFormat =new SimpleDateFormat("yyyyMMdd");
			Date temDate;
			if(!StringUtils.isEmpty(svcCmps.getExtId())){
				for(OSvcCmpsExtVO svcExt : svcCmpsExtList){
					if(svcCmps.getExtId().equals(svcExt.getExtId())){
						Map<String,Object> chargeConf = new HashMap<String,Object>();
						chargeConf.put("so_id", svcCmps.getSoId());
						chargeConf.put("svc_grp", svcCmps.getSvcGrp());
						chargeConf.put("svc_cd", svcCmps.getSvcCd());
						chargeConf.put("prod_typ", (String)prodInfo.get("prod_typ"));
						try {
							chargeConf.put("rate_strt_dt", sdFormat.parse(svcCmps.getRateStrtDt()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						chargeConf.put("nego_prod_cd", svcCmps.getProdCd());
						chargeConf.put("nego_prod_cd_nm", prodNm);
						chargeConf.put("nego_cnt", svcExt.getProdCnt());
						if(!"0".equals(svcExt.getMonthlyAmt())){
							chargeConf.put("sale_amt_monthly", svcExt.getMonthlyAmt());
							chargeConf.put("discount_rate_monthly", svcExt.getMonthlyNegoRate());
							chargeConf.put("nego_amt_monthly", svcExt.getMonthlyNegoAmt());
							
						}else{
							chargeConf.put("sale_amt_monthly", "0");
							chargeConf.put("discount_rate_monthly", "0");
							chargeConf.put("nego_amt_monthly", "0");
						}
						
						if(!"0".equals(svcExt.getOnetimeAmt())){
							chargeConf.put("sale_amt_onetime", svcExt.getOnetimeAmt());
							chargeConf.put("discount_rate_onetime", svcExt.getOnetimeNegoRate());
							chargeConf.put("nego_amt_onetime", svcExt.getOnetimeNegoAmt());
							//onetimeConfList.add(onetimeConf);
						}else{
							chargeConf.put("sale_amt_onetime", "0");
							chargeConf.put("discount_rate_onetime", "0");
							chargeConf.put("nego_amt_onetime","0");
						}
						chargeConfList.add(chargeConf);
					}
				}
			}
		}
		
		
		resultMap.put("basicProdInfo", basicProdInfo);    // 기본계약정보
		resultMap.put("deviceInfoList", deviceConfList);  // 사용중인 장비상품정보
		resultMap.put("addInfoList", addConfList);        // 사용중인 부가서비스정보
		resultMap.put("chargeConfList", chargeConfList); // 월정액 협정가 정보
		resultMap.put("oneTimeFeeList", onetimeConfList); // 일회성 협정가 정보
		
		return resultMap;
	}
}