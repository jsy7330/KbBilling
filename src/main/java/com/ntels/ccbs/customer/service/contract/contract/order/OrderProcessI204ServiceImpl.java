package com.ntels.ccbs.customer.service.contract.contract.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderManagementMapper;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderProcessMapper;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderProcessService;
import com.ntels.ccbs.ifg.service.InterfaceService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.nisf.util.message.MessageUtil;



/**
 * <PRE>
 * 1. ClassName: OrderProcessI204ServiceImpl
 * 2. FileName : OrderProcessI204ServiceImpl.java
 * 3. Package  : com.ntels.ccbs.customer.service.contract.contract.order
 * 4. Comment  : IOT 일시 정지 처리
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 23. 오후 5:17:00
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 23.    : 신규개발
 * </PRE>
 */
@Service
public class OrderProcessI204ServiceImpl extends OrderProcessServiceImpl implements OrderProcessService{
	
    private final Log logger = LogFactory.getLog(getClass());

    private static final String[] SVC_CD = {"SV096"}; //IOT 서비스
	private static final String ORDER_TP = "204"; //오더유형
	private static final String[] CTRT_STAT = {"20"}; //이전 계약 상태
	
	@Autowired
	private ContractManagementService contractManagementService;
	
	@Autowired
	private OrderProcessMapper orderProcessMapper;
	
	@Autowired
	private OrderManagementMapper orderManagementMapper;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private InterfaceService interfaceService;
	
	
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
		
		logger.debug("########## IOT 204 Create Order START ###########");
		
		processPreOrderMakeCommon(orderInfo); //오더 작성 전 공통 처리
		processPreOrderMake(orderInfo); //오더 작성 전 처리
		processOrderMake(orderInfo);  // 오더 작성
		processPostOrderMakeCommon(orderInfo); // 오더작성 후 공통 처리
		processPostOrderMake(orderInfo);  //오더 작성 후 처리
		
		logger.debug("########## IOT 204 Create Order End ###########");
		
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
		
		logger.debug("########## IOT 204 Cancel Order START ###########");
		
		processPreOrderCancelCommon(orderInfo);
		processPreOrderCancel(orderInfo);
		processOrderCancel(orderInfo);
		processPostOrderCancelCommon(orderInfo);
		processPostOrderCancel(orderInfo);
		
		logger.debug("########## IOT 204 Cancel Order END ###########");
		
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
		
		logger.debug("########## IOT 204 Save Order START ###########");
		processPreOrderCmplCommon(orderInfo);
		processPreOrderCmpl(orderInfo);
		processOrderCmpl(orderInfo);
		processPostOrderCmplCommon(orderInfo);
		processPostOrderCmpl(orderInfo);
		logger.debug("########## IOT 204 Save Order END ###########");
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
		
		logger.debug("########## IOT 204 Progress Order START ###########");
		processPreOrderProgressCommon(orderInfo);
		processPreOrderProgress(orderInfo);
		processOrderProgress(orderInfo);
		processPostOrderProgressCommon(orderInfo);
		processPostOrderProgress(orderInfo);
		logger.debug("########## IOT 204 Progress Order END ###########");
		return orderInfo;
	}
	
	@Override
	public void processPreOrderMake(OrderRequestInfoVO orderInfo) throws ServiceException {
		logger.debug("########## processPreOrderMake ###########");
		//일시정지 사유 체크
		if(StringUtils.isEmpty(orderInfo.getChngResn())){
			String[] arg = {MessageUtil.getMessage("LAB.M07.LAB00311")}; // 정지 사유
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		//일시정지기간
		if(StringUtils.isEmpty(orderInfo.getStartHopeDt()) || StringUtils.isEmpty(orderInfo.getEndHopeDt())){
			String[] arg = {MessageUtil.getMessage("LAB.M08.LAB00190")}; // 일시정지기간
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		//계약상태 체크
		String ctrtStat = orderProcessMapper.getCtrtStat(orderInfo.getSoId(), orderInfo.getCustId(), orderInfo.getCtrtId());
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
		
		
		OCtrtMastVO ctrtMast = orderProcessMapper.getCtrtMastLastInfo(orderInfo.getSoId(), orderInfo.getCustId(), orderInfo.getCtrtId());
		Map<String,Object> maxNumAttr = orderManagementMapper.getProdAttr(ctrtMast.getSoId(), ctrtMast.getProdCd(), "AT030",orderInfo.getOrderReqDt());
		Map<String,Object> maxDayAttr = orderManagementMapper.getProdAttr(ctrtMast.getSoId(), ctrtMast.getProdCd(), "AT029",orderInfo.getOrderReqDt());
		
		int maxSusCnt = 0;
		long maxDays = 0;
		
		boolean isCheckSusCnt = false;
		boolean isCheckDays = false;
		
		if(maxNumAttr != null && StringUtils.isNotEmpty((String)maxNumAttr.get("ATTR_VAL"))){
			try{
				maxSusCnt = Integer.parseInt((String)maxNumAttr.get("ATTR_VAL"));
				isCheckSusCnt = true;
			}catch(Exception e){
				isCheckSusCnt = false;
			}
		}
		
		if(maxDayAttr != null && StringUtils.isNotEmpty((String)maxDayAttr.get("ATTR_VAL"))){
			try{
				maxDays = Long.parseLong((String)maxDayAttr.get("ATTR_VAL"));
				isCheckDays = true;
			}catch(Exception e){
				isCheckDays = false;
			}
		}
		
		//사유코드 예외 조건 조회
		CommonCodeVO resnCode = commonDataService.getCommonCode("CM00049", (String)orderInfo.getChngResn(), orderInfo.getLng());
		if("Y".equals(resnCode.getRefCode())){
			isCheckDays = false; //예외 조건일 경우 패스
			isCheckSusCnt = false; //예외 조건일 경우 패스
		}
		
		if(isCheckSusCnt || isCheckDays){
			Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        cal.add(Calendar.YEAR, -1);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        String dttm = dateFormat.format(cal.getTime()) + "000000";
	        
			List<Map<String, Object>> suspendedList = orderProcessMapper.getSuspendedList(orderInfo.getSoId(), orderInfo.getCustId(), orderInfo.getCtrtId(), dttm);
			
			int susCnt = 0;
			for(Map<String,Object> susInfo : suspendedList){
				if(maxSusCnt == 0) continue;
				
				CommonCodeVO code = commonDataService.getCommonCode("CM00049", (String)susInfo.get("CTRT_CHG_RESN_CD"), orderInfo.getLng());
				
				if(code == null) continue;
				if("Y".equals(code.getRefCode())) continue; //예외 조건일 경우 패스
				if(Integer.parseInt(((String)susInfo.get("ACT_DTTM")).substring(0, 8)) == Integer.parseInt(((String)susInfo.get("INACT_DTTM")).substring(0, 8))) continue; //당일 해지인 경우 패스
				
				susCnt++;
			}
			
			
			//정지 횟수 초과 체크
			if(isCheckSusCnt &&  susCnt >= maxSusCnt){
				String[] arg = {String.valueOf(maxSusCnt)}; 
				throw new ServiceException("MSG.M10.MSG00046", arg);
			}
			

			
			//정지 가능 일자 체크
			if(isCheckDays){
				Date beginDate;
			    Date endDate;
				try {
					beginDate = dateFormat.parse(orderInfo.getStartHopeDt());
					endDate = dateFormat.parse(orderInfo.getEndHopeDt());
					long diff = endDate.getTime() - beginDate.getTime();
				    long diffDays = diff / (24 * 60 * 60 * 1000);
				    
				    if(diffDays > maxDays){
						String[] arg = {String.valueOf(maxDays)}; 
						throw new ServiceException("MSG.M09.MSG00067", arg);
				    }
				    
				    
				}catch (ServiceException e) {
					throw e;
				}catch (Exception e){
					
				}
			}
		}
	}
	
	@Override
	public void processPostOrderMake(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPostOrderMake ###########");
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
	}
	@Override
	public void processPreOrderCmpl(OrderRequestInfoVO orderInfo) throws ServiceException{
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
		
		//Service Platform 연동
		OCtrtMastVO octrt = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		List<OProdCmpsVO> prodList = orderProcessMapper.getOProdCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		Map<String,Object> requestInfo = new LinkedHashMap<String,Object>();
		requestInfo.put("SO_ID", orderInfo.getSoId());
		requestInfo.put("CUST_ID", orderInfo.getCustId());
		requestInfo.put("ORDER_TP", orderInfo.getOrderTp());
		requestInfo.put("ORDER_ID", orderInfo.getOrderId());
		requestInfo.put("CTRT_ID", orderInfo.getCtrtId());
		requestInfo.put("PROD_CD", octrt.getProdCd());
		
		List<LinkedHashMap<String,Object>> prodInfoList = new ArrayList<LinkedHashMap<String,Object>>();
		for(OProdCmpsVO prod : prodList){
			if("11".equals(prod.getProdCmpsCl())){
				continue;
			}
			
			LinkedHashMap<String,Object> prodInfo = new LinkedHashMap<String,Object>();
			prodInfo.put("PROD_CD", prod.getProdCd());
			prodInfo.put("PROD_CMPS_ID", prod.getProdCmpsId());
			prodInfo.put("PROD_CNT", "1");
			prodInfo.put("PROD_STAT", prod.getProdStatCd());
			prodInfoList.add(prodInfo);
		}
		requestInfo.put("PROD_INFO", prodInfoList);
		
		//interfaceService.sendCtrtChange(requestInfo); // POC 준비 주석처리
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