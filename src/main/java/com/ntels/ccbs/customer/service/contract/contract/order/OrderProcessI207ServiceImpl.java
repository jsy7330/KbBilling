package com.ntels.ccbs.customer.service.contract.contract.order;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderProcessMapper;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderProcessService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.nisf.util.message.MessageUtil;



/**
 * <PRE>
 * 1. ClassName: OrderProcessI207ServiceImpl
 * 2. FileName : OrderProcessI207ServiceImpl.java
 * 3. Package  : com.ntels.ccbs.customer.service.contract.contract.order
 * 4. Comment  : IOT 공정률 변경 서비스
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2017. 3. 23. 오후 5:17:00
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2017. 3. 23.    : 신규개발
 * </PRE>
 */
@Service
public class OrderProcessI207ServiceImpl extends OrderProcessServiceImpl implements OrderProcessService{
    private final Log logger = LogFactory.getLog(getClass());

    private static final String[] SVC_CD = {"SV096"}; //IOT 서비스
	private static final String ORDER_TP = "207"; //오더유형
	private static final String[] CTRT_STAT = {"20"}; //이전 계약 상태
	
	@Autowired
	private ContractManagementService contractManagementService;
	
	@Autowired
	private OrderProcessMapper orderProcessMapper;
	
	
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
		
		logger.debug("########## IOT 207 Create Order START ###########");
		
		processPreOrderMakeCommon(orderInfo); //오더 작성 전 공통 처리
		processPreOrderMake(orderInfo); //오더 작성 전 처리
		processOrderMake(orderInfo);  // 오더 작성
		processPostOrderMakeCommon(orderInfo); // 오더작성 후 공통 처리
		processPostOrderMake(orderInfo);  //오더 작성 후 처리
		
		logger.debug("########## IOT 207 Create Order End ###########");
		
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
		
		logger.debug("########## IOT 207 Cancel Order START ###########");
		
		processPreOrderCancelCommon(orderInfo);
		processPreOrderCancel(orderInfo);
		processOrderCancel(orderInfo);
		processPostOrderCancelCommon(orderInfo);
		processPostOrderCancel(orderInfo);
		
		logger.debug("########## IOT 207 Cancel Order END ###########");
		
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
		
		logger.debug("########## IOT 207 Save Order START ###########");
		processPreOrderCmplCommon(orderInfo);
		processPreOrderCmpl(orderInfo);
		processOrderCmpl(orderInfo);
		processPostOrderCmplCommon(orderInfo);
		processPostOrderCmpl(orderInfo);
		logger.debug("########## IOT 207 Save Order END ###########");
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
		
		logger.debug("########## IOT 207 Progress Order START ###########");
		processPreOrderProgressCommon(orderInfo);
		processPreOrderProgress(orderInfo);
		processOrderProgress(orderInfo);
		processPostOrderProgressCommon(orderInfo);
		processPostOrderProgress(orderInfo);
		logger.debug("########## IOT 207 Progress Order END ###########");
		return orderInfo;
	}
	
	@Override
	public void processPreOrderMake(OrderRequestInfoVO orderInfo) throws ServiceException {
		logger.debug("########## processPreOrderMake ###########");
		//공정률 체크
		if(StringUtils.isEmpty(orderInfo.getProcRate())){
			String[] arg = {MessageUtil.getMessage("LAB.M01.LAB00235")}; //공정률
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
