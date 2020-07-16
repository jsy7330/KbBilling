package com.ntels.ccbs.appIf.service.sh.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;
import com.ntels.ccbs.appIf.mapper.sh.service.ServiceIfMapper;
import com.ntels.ccbs.appIf.service.sh.service.ServiceIfService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.AppException;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.CustInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderMastInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderProcessService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ServiceIfServiceImpl implements ServiceIfService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ServiceIfMapper serviceIfMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private OrderManagementService orderManagementService;
	
	@Autowired
	private ContractManagementService contractManagementService;
	
	@Autowired
	private OrderProcessService[] orderProcessService;

	@Override
	public Map<String, Object> getStockStat(AppRequestVO appRequestVO) {
		// TODO Auto-generated method stub
		Map<String, Object> param = appRequestVO.getBody();
		
		//재고상태 조회
		Map<String, Object> result = serviceIfMapper.getStockStat(param);
		
		return result;
	}

	@Override
	public Map<String, Object> getWorkInfo(AppRequestVO appRequestVO) {
		// TODO Auto-generated method stub
		Map<String, Object> param = appRequestVO.getBody();
		
		//고객정보 셀렉트
		Map<String, Object> result = serviceIfMapper.getCustomer(param);

		//고객의 계약 정보 셀렉트
		List<LinkedHashMap<String, Object>> ctrtInfo = serviceIfMapper.getCtrtInfo(param);
		
		//계약 정보가 있을 경우
		if(ctrtInfo != null && ctrtInfo.size()>0){
			
			for(LinkedHashMap<String, Object> data : ctrtInfo){
				
				//상품정보 셀렉트
				List<LinkedHashMap<String, Object>> prodInfo = serviceIfMapper.getProdCmpsInfo(data);
				
				if(prodInfo != null && prodInfo.size() > 0){
					data.put("PROD_INFO", prodInfo);
				}
				
			}
			
			result.put("CTRT_INFO", ctrtInfo);
			
		} //계약 정보가 있을 경우
		
		return result;
		
	}

	@Override
	public Map<String, Object> setEquipmentReception(AppRequestVO appRequestVO) {
		// TODO Auto-generated method stub
		
		Map<String, Object> param = appRequestVO.getBody();
		
		//리턴 Map
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> eqtInfoList = (List<Map<String, Object>>)param.get("EQT_INFO");
		
		//계약 아이디로 TCMCT_DEVICE_HIST 데이터 삭제
		serviceIfMapper.deleteDeviceHist(param);
		
		//파라미터 리스트 체크
		if(eqtInfoList != null && eqtInfoList.size() > 0){
			
			//for(Map<String, Object> eqtInfo : eqtInfoList){
			for(int i=0; i<eqtInfoList.size(); i++){
				
				try{
					
					Map<String, Object> eqtInfo = eqtInfoList.get(i);
					eqtInfo.put("SO_ID", param.get("SO_ID"));		//사업구분
					eqtInfo.put("CTRT_ID", param.get("CTRT_ID"));	//계약ID
					eqtInfo.put("ACT_DTTM", DateUtil.getDateStringYYYYMMDDHH24MISS(0) );	//시작일시
					eqtInfo.put("INACT_DTTM", "99991231235959");	//종료일시
					eqtInfo.put("DEVICE_STATUS", "1");	//장비상태 1신규, 2중고  고정 값
					
					eqtInfo.put("REGR_ID", Consts.IF_ADM_ID);		//등록자ID
					eqtInfo.put("REG_DATE", DateUtil.sysdate());	//사업구분
					eqtInfo.put("CHGR_ID", Consts.IF_ADM_ID);		//수정자ID
					eqtInfo.put("CHG_DATE", DateUtil.sysdate());	//수정일시
					
					if( (eqtInfo.get("OLD_SN") == null || eqtInfo.get("OLD_SN").equals("") ) 
							&& (eqtInfo.get("NEW_SN") != null && !eqtInfo.get("NEW_SN").equals("") )  ){	//OLD_SN 값이 없을 경우 NEW_SN 값이 있을 경우
						
						//TDNDT_EQT NEW_SN 값으로  EQT_SEQ 값 조회
						eqtInfo.put("EQT_SEQ", eqtInfo.get("NEW_SN"));
						List<LinkedHashMap<String, Object>> newEqtSeqMap = serviceIfMapper.getTdndtEqtSeq(eqtInfo);
						
						//TDNDT_EQT 데이터가 있을 경우
						if(newEqtSeqMap != null && newEqtSeqMap.size() > 0 ){
								
							if ( newEqtSeqMap.get(0).get("EQT_STAT_CD").equals("001") || newEqtSeqMap.get(0).get("EQT_STAT_CD").equals("102") 
									|| newEqtSeqMap.get(0).get("EQT_STAT_CD").equals("101") ) {
								
								//TDNDT_EQT 테이블에서 NEW_SN 값을 이용하여 EQT_STAT_CD 값이 '001', '102', '101' 있는지 조회 해서 없으면 ERROR 처리 있으면  EQT_STAT_CD = '100', LGST_PROC_STAT_CD='902'로 업데이트
								//TDNDT_EQT 파라미터 셋팅, 업데이트
								eqtInfo.put("EQT_STAT_CD", "100");
								eqtInfo.put("LGST_PROC_STAT_CD", "902");
								serviceIfMapper.updateTdndtEqt(eqtInfo);
								
								//TDNDT_UPD_PROC_HIST 파라미터 셋팅, 테이블에 이력 등록
								Map<String, Object> histParam = new LinkedHashMap<String, Object>();
								//위에서 선언한 파라미터 사용
								String histSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10);	//이력일련번호
								histParam.put("HIST_SEQ", histSeq);			//이력일련번호
								histParam.put("UPD_PROC_CL_CD", "20");		//변경처리구분코드
								histParam.put("SO_ID", param.get("SO_ID"));	//사업ID
								histParam.put("ITEM_ID", eqtInfo.get("NEW_EQT_CL_ID"));	//제품ID
								//EQT_SEQ - 단말기일련번호   입력 얿음.
								//USIM_SEQ - USIM일련번호 입력 없음
								histParam.put("UPD_BFR_CD", newEqtSeqMap.get(0).get("EQT_STAT_CD"));		//변경전코드 - 업데이트 하기 전 TDNDT_EQT 테이블의 EQT_STAT_CD 코드
								histParam.put("AFTR_UPD_CD", "100");		//변경후코드
								histParam.put("UPD_PROC_ID", Consts.IF_ADM_ID);		//변경처리자ID
								histParam.put("UPD_PROC_DTTM", DateUtil.getDateStringYYYYMMDDHH24MISS(0));	//변경처리일시
								histParam.put("REGR_ID", Consts.IF_ADM_ID);		//등록자
								histParam.put("REG_DATE", DateUtil.sysdate());	//등록일시
								histParam.put("CHGR_ID", Consts.IF_ADM_ID);		//수정자
								histParam.put("CHG_DATE", DateUtil.sysdate());	//수정일시
								
								serviceIfMapper.setTdndtUpdProcHist(histParam);		//TDNDT_UPD_PROC_HIST 테이블에 이력 등록
								
								//TCMCT_DEVICE_HIST 테이블 등록, 수정
								setTcmctDeviceHist(eqtInfo);
								
							}else{
								//EQT_STAT_CD 001, 101, 102가 아닐 경우 에러 처리
								
								Object[] arguments = { i+1 };
								throw new AppException("MSG.IF.E0002", "E0002", arguments );  // ({0}) 데이터의 NEW_SN 값 오류 입니다.
							}
							
						}else{
							//TDNDT_EQT 데이터가 없을 경우
							
							//TCMCT_DEVICE_HIST 테이블 등록, 수정
							setTcmctDeviceHist(eqtInfo);
						}

						
						
					}else if(eqtInfo.get("NEW_SN").equals(eqtInfo.get("OLD_SN"))){// NEW_SN과 OLD_SN을 비교해서 같은 경우
						
						//TCMCT_DEVICE_HIST 테이블 등록, 수정
						setTcmctDeviceHist(eqtInfo);
						
					}else{	// NEW_SN과 OLD_SN을 비교해서 다른 경우
						
						//TDNDT_EQT OLD_SN 값으로  EQT_SEQ 값 조회
						eqtInfo.put("EQT_SEQ", eqtInfo.get("OLD_SN"));
						List<LinkedHashMap<String, Object>> eqtSeqMap = serviceIfMapper.getTdndtEqtSeq(eqtInfo);
						
						if(eqtSeqMap != null && eqtSeqMap.size() > 0 ){		////TDNDT_EQT 테이블에 데이터가 있을 경우
							
							//TDNDT_EQT 파라미터 셋팅, 업데이트
							eqtInfo.put("EQT_STAT_CD", "102");
							eqtInfo.put("LGST_PROC_STAT_CD", "911");
							serviceIfMapper.updateTdndtEqt(eqtInfo);
							
							//TDNDT_UPD_PROC_HIST 파라미터 셋팅, 테이블에 이력 등록
							Map<String, Object> histParam = new LinkedHashMap<String, Object>();
							String histSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10);	//이력일련번호
							histParam.put("HIST_SEQ", histSeq);			//이력일련번호
							histParam.put("UPD_PROC_CL_CD", "20");		//변경처리구분코드
							histParam.put("SO_ID", param.get("SO_ID"));	//사업ID
							histParam.put("ITEM_ID", eqtInfo.get("NEW_EQT_CL_ID"));	//제품ID
							//EQT_SEQ - 단말기일련번호   입력 얿음.
							//USIM_SEQ - USIM일련번호 입력 없음
							histParam.put("UPD_BFR_CD", eqtSeqMap.get(0).get("EQT_STAT_CD"));		//변경전코드 - 업데이트 하기 전 TDNDT_EQT 테이블의 EQT_STAT_CD 코드
							histParam.put("AFTR_UPD_CD", "102");		//변경후코드
							histParam.put("UPD_PROC_ID", Consts.IF_ADM_ID);		//변경처리자ID
							histParam.put("UPD_PROC_DTTM", DateUtil.getDateStringYYYYMMDDHH24MISS(0));	//변경처리일시
							histParam.put("REGR_ID", Consts.IF_ADM_ID);		//등록자
							histParam.put("REG_DATE", DateUtil.sysdate());	//등록일시
							histParam.put("CHGR_ID", Consts.IF_ADM_ID);		//수정자
							histParam.put("CHG_DATE", DateUtil.sysdate());	//수정일시						
							
							serviceIfMapper.setTdndtUpdProcHist(histParam);		//TDNDT_UPD_PROC_HIST 테이블에 이력 등록
							
							//TDNDT_EQT NEW_SN 값으로  EQT_SEQ 값 조회
							eqtInfo.put("EQT_SEQ", eqtInfo.get("NEW_SN"));
							List<LinkedHashMap<String, Object>> newEqtSeqMap = serviceIfMapper.getTdndtEqtSeq(eqtInfo);
							//TDNDT_EQT 테이블에서 NEW_SN 값을 이용하여 EQT_STAT_CD 값이 '001', '102', '101' 있는지 조회 해서 없으면 ERROR 처리 있으면  EQT_STAT_CD = '100', LGST_PROC_STAT_CD='902'로 업데이트
							if(newEqtSeqMap != null && newEqtSeqMap.size() > 0 && 
									( newEqtSeqMap.get(0).get("EQT_STAT_CD").equals("001") || newEqtSeqMap.get(0).get("EQT_STAT_CD").equals("102") || newEqtSeqMap.get(0).get("EQT_STAT_CD").equals("101") ) ){
								
								//TDNDT_EQT 파라미터 셋팅, 업데이트
								eqtInfo.put("EQT_STAT_CD", "100");
								eqtInfo.put("LGST_PROC_STAT_CD", "902");
								serviceIfMapper.updateTdndtEqt(eqtInfo);
								
								//TDNDT_UPD_PROC_HIST 파라미터 셋팅, 테이블에 이력 등록
								//위에서 선언한 파라미터 사용
								histSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10);	//이력일련번호
								histParam.put("HIST_SEQ", histSeq);			//이력일련번호
								//histParam.put("UPD_PROC_CL_CD", "20");		//변경처리구분코드
								//histParam.put("SO_ID", param.get("SO_ID"));	//사업ID
								//histParam.put("ITEM_ID", eqtInfo.get("NEW_EQT_CL_ID"));	//제품ID
								//EQT_SEQ - 단말기일련번호   입력 얿음.
								//USIM_SEQ - USIM일련번호 입력 없음
								histParam.put("UPD_BFR_CD", newEqtSeqMap.get(0).get("EQT_STAT_CD"));		//변경전코드 - 업데이트 하기 전 TDNDT_EQT 테이블의 EQT_STAT_CD 코드
								histParam.put("AFTR_UPD_CD", "100");		//변경후코드
								//histParam.put("UPD_PROC_ID", Consts.IF_ADM_ID);		//변경처리자ID
								histParam.put("UPD_PROC_DTTM", DateUtil.getDateStringYYYYMMDDHH24MISS(0));	//변경처리일시
								//histParam.put("REGR_ID", Consts.IF_ADM_ID);		//등록자
								histParam.put("REG_DATE", DateUtil.sysdate());	//등록일시
								//histParam.put("CHGR_ID", Consts.IF_ADM_ID);		//수정자
								histParam.put("CHG_DATE", DateUtil.sysdate());	//수정일시
								
								serviceIfMapper.setTdndtUpdProcHist(histParam);		//TDNDT_UPD_PROC_HIST 테이블에 이력 등록
								
								//TCMCT_DEVICE_HIST 테이블 등록, 수정
								setTcmctDeviceHist(eqtInfo);
								
								
							}else{	//맞는 값이 없으면 에러 처리
								Object[] arguments = { i+1 };
								throw new AppException("MSG.IF.E0002", "E0002", arguments );  // ({0}) 데이터의 NEW_SN 값 오류 입니다.
							}
							
						}else{	//TDNDT_EQT 테이블 에서  OLD_SN 값으로  EQT_SEQ 조회 값이 없을 경우
							//TCMCT_DEVICE_HIST 테이블 등록, 수정
							setTcmctDeviceHist(eqtInfo);
						}
						
						
					}
					
				}catch(Exception e){
					Object[] arguments = { i+1 };
					throw new AppException("MSG.IF.E0001", "E0001", arguments );  // {0} 번째 데이터가 잘못 들어 왔습니다. 
				}
				
			}
			
			result.put("SUCCESS_YN", "Y");	//등록 성공
			
		}else{	//파라미터가 안들어왔을 경우
			result.put("SUCCESS_YN", "N");
		}
		
		
		result.put("SO_ID", param.get("SO_ID"));
		result.put("CTRT_ID", param.get("CTRT_ID"));
		
		
		return result;
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: setTcmctDeviceHist
	 * 2. ClassName : ServiceIfServiceImpl
	 * 3. Comment   : TCMCT_DEVICE_HIST 테이블 등록, 수정
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 3. 22. 오후 1:38:45
	 * </PRE>
	 *   @return int
	 *   @param eqtInfo
	 *   @return
	 */
	private int setTcmctDeviceHist(Map<String, Object> eqtInfo) throws Exception {
		
		int result = 0;
		
		//TCMCT_DEVICE_HIST 키중복 체크 하기위한 조회
		List<LinkedHashMap<String, Object>> keyCheck = serviceIfMapper.getTcmctDeviceHistKeyCheck(eqtInfo);
		
		//TCMCT_DEVICE_HIST 테이블에 중복 값이 있을경우 업데이트 한다
		if(keyCheck != null && keyCheck.size() > 0){
			serviceIfMapper.updateTcmctDeviceHistKey(eqtInfo);
		}
		
		//TCMCT_DEVICE_HIST 테이블 인서트
		result = serviceIfMapper.setTcmctDeviceHist(eqtInfo);
		
		return result;
	}

	@Override
	public Map<String, Object> processServiceResult(AppRequestVO appRequestVO) {
		//진행 상태값 체크 (03,04만 체크)
		 String orderStat = (String)appRequestVO.getBody().get("ORDER_STAT");
		 if(!"03".equals(orderStat) && !"04".equals(orderStat)){
			throw new ServiceException("MSG.M08.MSG00020"); 
		 }
		 
		 String soId = (String)appRequestVO.getBody().get("SO_ID");
		 String custId = (String)appRequestVO.getBody().get("CUST_ID");
		 String ctrtId = (String)appRequestVO.getBody().get("CTRT_ID");
		 String orderId = (String)appRequestVO.getBody().get("ORDER_ID");
		 
		 String today = DateUtil.getDateStringYYYYMMDD(0);
		 String lng = "ko";
		 String nowDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		 Date nowDate = DateUtil.sysdate();
			
		 CustInfoVO custInfo = contractManagementService.getCustInfo(soId, custId, lng, today);
		 if(custInfo == null || StringUtils.isEmpty(custInfo.getCustId())){
			 throw new ServiceException("MSG.M01.MSG00019");
		 }
			
		 List<OrderMastInfoVO> orderServiceList = contractManagementService.getOrderListBySoId(soId, custInfo.getCustTp(), lng);
		 OCtrtMastVO ctrtInfo =   orderManagementService.getOCtrtMastInfo(soId, custId, ctrtId, orderId);
		 if(ctrtInfo == null || StringUtils.isEmpty(ctrtInfo.getCtrtId())){
			 throw new ServiceException("MSG.M08.MSG00088");
		 }
		 
		 String orderCd = null;
		 for(OrderMastInfoVO mastInfo : orderServiceList){
			 if(mastInfo.getOrderTp().equals(ctrtInfo.getOrderTp())){
				 orderCd = mastInfo.getOrderCd();
				 break;
			 }
		 }
		 OrderCommonVO orderCommonInfo = orderManagementService.getOrderCommonInfo(soId, custId, ctrtId, orderCd, ctrtInfo.getRcptId(), lng , today);
		 //사전 체크
		 for (int index = 0; orderCommonInfo != null && index < orderProcessService.length; index++) {
			 try {
				 orderProcessService[index].precheckOrder(orderCommonInfo);
			 } catch (Exception e) {
				 throw e;
			 }
		 }
		 
		 OrderRequestInfoVO request = new OrderRequestInfoVO();
		 request.setSoId(ctrtInfo.getSoId());
		 request.setCustId(ctrtInfo.getCustId());
		 request.setCtrtId(ctrtInfo.getCtrtId());
		 request.setRcptId(ctrtInfo.getRcptId());
		 request.setOrderId(ctrtInfo.getOrderId());
		 request.setOrderCd(orderCommonInfo.getOrderCd());
		 request.setOrderTp(ctrtInfo.getOrderTp());
		 request.setOrderStat(ctrtInfo.getOrderStat());
		 request.setCnslMstCl(orderCommonInfo.getCnslMstCl());
		 request.setCnslMidCl(orderCommonInfo.getCnslMidCl());
		 request.setCnslSlvCl(orderCommonInfo.getCnslSlvCl());
		 request.setBasicSvcCd(orderCommonInfo.getBasicSvcCd());
		 request.setCustTp(custInfo.getCustTp());
		 request.setReqUsrId(ctrtInfo.getUsrId());
		 request.setReqOrgId(ctrtInfo.getOrgId());
		 request.setReqDesc("Service Flatform Result");
		 request.setOrderReqDttm(nowDttm);
		 request.setOrderReqDt(today);
		 request.setLng(lng);
		 request.setOrderReqDate(nowDate);
		 

		 
		 OrderRequestInfoVO orderInfo = null;
		 for (int index = 0; orderInfo == null && index < orderProcessService.length; index++) {
			 try {
				 if("03".equals(orderStat)){
					 orderInfo = orderProcessService[index].cancelOrder(request);
					 
				 }else if("04".equals(orderStat)){
					 orderInfo = orderProcessService[index].saveOrder(request);
				 }
			 } catch (Exception e) {
				 throw e;
			 }
		 }
		if(orderInfo == null){
			throw new ServiceException("MSG.M08.MSG00020");
		}
		
		Map<String,Object> result = new LinkedHashMap<String,Object>();
		result.put("SO_ID", soId);
		result.put("CTRT_ID", ctrtId);
		result.put("SUCCESS_YN", "Y");
		return result;
	}
	
}
