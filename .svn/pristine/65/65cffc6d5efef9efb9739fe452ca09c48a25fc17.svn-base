package com.ntels.ccbs.customer.service.contract.contract.precheck;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.OrderAttrVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.PrecheckInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.PrecheckMastVO;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderPrecheckMapper;
import com.ntels.ccbs.customer.service.contract.contract.OrderPrecheckService;
import com.ntels.ccbs.ifg.service.InterfaceService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Service
public class OrderPrecheckC3ServiceImpl implements OrderPrecheckService{
	
	/* The attribute management. */
    private final Log logger = LogFactory.getLog(getClass());

	private static final String CHECK_CD = "C3";
	
	private static final String SUCCESS = "00";
	private static final String FAIL = "E1";
	private static final String IF_ERROR = "E2";
	private static final String RESULT_SAVE_ERROR = "E3";
	
	@Autowired
	private OrderPrecheckMapper orderPrecheckMapper;
	
	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private CommonDataService commonDataService;
	

	@Override
	public PrecheckInfoVO savePrecheckResult(OrderCommonVO orderCommonInfo,
	        OrderAttrVO orderAttrInfo,
	        String usrId,
	        String lng,
	        String today) {
		if(orderAttrInfo != null && CHECK_CD.equals(orderAttrInfo.getOrderAttrVal()) == false){
			return null;
		}
		
		//전산심사 기준정보 조회
		PrecheckMastVO precheckMastInfo = orderPrecheckMapper.getOrderPrecheckMastInfo(orderAttrInfo.getOrderAttrVal(), orderCommonInfo.getSoId(), orderCommonInfo.getBasicSvcCd(), orderCommonInfo.getCustTp(), lng, today);
		
		if(precheckMastInfo == null){
			return null;
		}
		String resultCd = "";
		
		
		Integer checkVal = Integer.parseInt(precheckMastInfo.getCheckVal());
		Integer resultVal = orderPrecheckMapper.getCtrtCnt(orderCommonInfo.getSoId(), orderCommonInfo.getCustId());
		
		try{
			if(">".equals(precheckMastInfo.getEvalCdNm())){
				if(checkVal.intValue() > resultVal.intValue()){
					resultCd = SUCCESS;
				}else{
					resultCd = FAIL;
				}
			}else if(">=".equals(precheckMastInfo.getEvalCdNm())){
				if(checkVal.intValue() >= resultVal.intValue()){
					resultCd = SUCCESS;
				}else{
					resultCd = FAIL;
				}
			}else if("<".equals(precheckMastInfo.getEvalCdNm())){
				if(checkVal.intValue() < resultVal.intValue()){
					resultCd = SUCCESS;
				}else{
					resultCd = FAIL;
				}
			}else if("<=".equals(precheckMastInfo.getEvalCdNm())){
				if(checkVal.intValue() <= resultVal.intValue()){
					resultCd = SUCCESS;
				}else{
					resultCd = FAIL;
				}
			}else if("=".equals(precheckMastInfo.getEvalCdNm())){
				if(checkVal.intValue() == resultVal.intValue()){
					resultCd = SUCCESS;
				}else{
					resultCd = FAIL; //조건부합X
				}
			}

		}catch(Exception e){
			resultCd = IF_ERROR; // 연동에러
		}
		PrecheckInfoVO precheckInfo = new PrecheckInfoVO();
		precheckInfo.setSoId(orderCommonInfo.getSoId());
		precheckInfo.setCustId(orderCommonInfo.getCustId());
		precheckInfo.setRcptId(orderCommonInfo.getRcptId());
		precheckInfo.setOrderCd(orderCommonInfo.getOrderCd());
		precheckInfo.setBasicSvcCd(orderCommonInfo.getBasicSvcCd());
		precheckInfo.setCustTp(orderCommonInfo.getCustTp());
		precheckInfo.setCustTpNm(orderCommonInfo.getCustTpNm());
		precheckInfo.setAttrCd(orderAttrInfo.getOrderAttrCd());
		precheckInfo.setCheckCd(precheckMastInfo.getCheckCd());
		precheckInfo.setCheckCdNm(precheckMastInfo.getCheckCdNm());
		precheckInfo.setCheckValue(checkVal.toString());
		precheckInfo.setEvalCd(precheckMastInfo.getEvalCd());
		precheckInfo.setEvalCdNm(precheckMastInfo.getEvalCdNm());
		precheckInfo.setResultValue(resultVal.toString());
		precheckInfo.setResultCd(resultCd);
		
		precheckInfo.setChgDate(DateUtil.sysdate());
		precheckInfo.setChgrId(usrId);
		precheckInfo.setRegDate(DateUtil.sysdate());
		precheckInfo.setRegrId(usrId);
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(orderCommonInfo.getCustTpNm());
		sb.append("]");
		sb.append(precheckMastInfo.getCheckCdNm());
		precheckInfo.setPrecheckNm(sb.toString());
		
		
		//기록추가
		try{
			orderPrecheckMapper.savePrecheckResult(precheckInfo);
		}catch(Exception e){
			logger.error(e.getMessage());
			precheckInfo.setResultCd(RESULT_SAVE_ERROR); //기록에러
			
		}
		precheckInfo.setResultCdNm(commonDataService.getCommonCode("CM00033", resultCd, lng).getCommonCdNm());
		return precheckInfo;
	}
}
