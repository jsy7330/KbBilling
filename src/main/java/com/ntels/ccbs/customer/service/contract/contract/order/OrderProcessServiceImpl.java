package com.ntels.ccbs.customer.service.contract.contract.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OCtrtMastVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OProdCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsExtVO;
import com.ntels.ccbs.customer.domain.contract.contract.OSvcCmpsVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderAttrVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderMastInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.PrecheckInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderProcessMapper;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderProcessService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.StringUtil;


@Service
public abstract class OrderProcessServiceImpl implements OrderProcessService{
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private OrderProcessMapper orderProcessMapper;
	
	@Autowired
	private OrderManagementService orderManagementService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private ContractManagementService contractManagementService;
	
	
	@Autowired
	private SequenceService sequenceService;
	
	private static final String ORDER_STAT01 	= "01";
	private static final String ORDER_STAT02 	= "02";
	private static final String ORDER_STAT03 	= "03";
	private static final String ORDER_STAT04 	= "04";
	
	/**
	 * 오더 생성 전처리 공통
	 * <PRE>
	 * 1. MethodName: processPreOrderMakeCommon
	 * 2. ClassName : OrderService
	 * 3. Comment   : 오더 생성 이전에 공통 Validation체크
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:16:44
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processPreOrderMakeCommon(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPreOrderMakeCommon ###########");
		//공통 필수항목 체크
		if(StringUtils.isEmpty(orderInfo.getSoId()) ||
				StringUtils.isEmpty(orderInfo.getCustId()) ||
				StringUtils.isEmpty(orderInfo.getCtrtId()) ||
				StringUtils.isEmpty(orderInfo.getOrderCd()) ||
				StringUtils.isEmpty(orderInfo.getOrderTp()) ||
				StringUtils.isEmpty(orderInfo.getCnslMstCl()) ||
				StringUtils.isEmpty(orderInfo.getCnslMidCl()) ||
				StringUtils.isEmpty(orderInfo.getCnslSlvCl()) ||
				StringUtils.isEmpty(orderInfo.getBasicSvcCd()) ||
				StringUtils.isEmpty(orderInfo.getCustTp()) ){
			throw new ServiceException("MSG.M13.MSG00024");
		}
		
		
		//오더 상태 체크
		OrderCommonVO orderCommon = orderManagementService.getOrderCommonInfo(orderInfo.getSoId()
				, orderInfo.getCustId()
				, orderInfo.getCtrtId()
				, orderInfo.getOrderCd()
				, orderInfo.getRcptId()
				, orderInfo.getLng()
				, orderInfo.getOrderReqDt());
		
		
		//상담이력 작성
		SessionUser sessionUser = CommonUtil.getSessionManager();
		//시간조회
		String dateTime = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		String today = dateTime.substring(0,8);
		String nowTime = dateTime.substring(8);
		
		//접수 ID 미존재시 접수내용 작성
		if(StringUtils.isEmpty(orderInfo.getRcptId())){
			
			
			RcptInfoVO rcptInfo = new RcptInfoVO();
			
			rcptInfo.setSoId(orderInfo.getSoId());
			rcptInfo.setRcptId("");
			rcptInfo.setCustId(orderInfo.getCustId());
			if(StringUtils.isEmpty(orderInfo.getCtrtId())){
				rcptInfo.setCtrtId("0000000000");
			}else{
				rcptInfo.setCtrtId(orderInfo.getCtrtId());
			}
			rcptInfo.setRcptTp("A"); //관리자접수
			rcptInfo.setReqTelNo("00000000000"); //관리자접수
			rcptInfo.setReqNm("");
			rcptInfo.setCustRel("A"); //본인
			rcptInfo.setCnslMstCl(orderInfo.getCnslMstCl());
			rcptInfo.setCnslMidCl(orderInfo.getCnslMidCl());
			rcptInfo.setCnslSlvCl(orderInfo.getCnslSlvCl());
			rcptInfo.setReqDesc(orderInfo.getReqDesc());
			rcptInfo.setReqDesc(orderInfo.getReqDesc());
			rcptInfo.setCnslStat("1"); //접수
			rcptInfo.setRcptDt(today);
			rcptInfo.setRcptDm(nowTime);
			rcptInfo.setProcDt(today);
			rcptInfo.setProcDm(nowTime);
			rcptInfo.setRcptOrgId(sessionUser == null ?  orderInfo.getReqOrgId() : sessionUser.getOrgId());
			rcptInfo.setRcptUsrId(sessionUser == null ?  orderInfo.getReqUsrId() : sessionUser.getUserId());
			rcptInfo.setProcOrgId(sessionUser == null ?  orderInfo.getReqOrgId() : sessionUser.getOrgId());
			rcptInfo.setProcUsrId(sessionUser == null ?  orderInfo.getReqUsrId() : sessionUser.getUserId());
			rcptInfo.setRegDate(DateUtil.sysdate());
			rcptInfo.setRegrId(sessionUser == null ?  orderInfo.getReqUsrId() : sessionUser.getUserId());
			rcptInfo.setChgDate(DateUtil.sysdate());
			rcptInfo.setChgrId(sessionUser == null ?  orderInfo.getReqUsrId() : sessionUser.getUserId());
			
			RcptInfoVO resRcptInfo = contractManagementService.saveRcptInfo(rcptInfo, orderInfo.getLng(), today);
			
			orderInfo.setRcptId(resRcptInfo.getRcptId());
			orderCommon.setRcptId(resRcptInfo.getRcptId());
		}
		
		//오더 상태 체크
		//이전 진행 중 오더 존재 여부 체크
		if(!StringUtils.isEmpty(orderInfo.getCtrtId())  && !"0000000000".equals(orderInfo.getCtrtId())  ){ //계약 아이디가 존재 할 경우, 이전 오더 체크
			int preOrderCnt = orderProcessMapper.getPreProcessOrderCnt(orderInfo.getSoId(), orderInfo.getCtrtId());
			
			if(preOrderCnt > 0){
				throw new ServiceException("MSG.M09.MSG00061");
			}
		}
		
		//전산심사처리
		List<PrecheckInfoVO> precheckList = new ArrayList<PrecheckInfoVO>();
		if(StringUtils.isEmpty(orderCommon.getOrderStat())){ //접수전 데이터 삭제 후 신규 조회
			precheckList = orderManagementService.savePrecheckInfo(orderCommon, orderInfo.getReqUsrId(), orderInfo.getLng(), orderInfo.getOrderReqDt());
		}else{ //기존 데이터 조회
			precheckList = orderManagementService.getPrecheckInfo(orderCommon, orderInfo.getReqUsrId(), orderInfo.getLng(), orderInfo.getOrderReqDt());
		}
		
		//기준 판단 값
		//전산심사 불가 항목 존재 여부 체크
		if(precheckList != null && precheckList.size() > 0){
			for(PrecheckInfoVO precheck :precheckList){
				if(!"00".equals(precheck.getResultCd())){
					String[] args = {precheck.getPrecheckNm(), precheck.getCheckValue(), precheck.getEvalCdNm(), precheck.getResultValue()};
					throw new ServiceException("MSG.M09.MSG00015", args);
				}
			}
		}
	}
	
	/**
	 * 오더 생성 전처리
	 * <PRE>
	 * 1. MethodName: processPreOrderMake
	 * 2. ClassName : OrderService
	 * 3. Comment   : 오더 생성 이전에 오더별 특수 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:17:17
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public abstract void processPreOrderMake(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	/**
	 * 오더 생성 처리 공통
	 * <PRE>
	 * 1. MethodName: processOrderMake
	 * 2. ClassName : OrderService
	 * 3. Comment   : 오더 생성 공통 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:17:53
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processOrderMake(OrderRequestInfoVO orderInfo)  throws ServiceException{
		logger.debug("########## processOrderMake ###########");
		//오더 상태 설정
		orderInfo.setOrderStat(ORDER_STAT01);
		CommonCodeVO orderCode = commonDataService.getCommonCode("CM00030",ORDER_STAT01, orderInfo.getLng());
		orderInfo.setOrderStatNm(orderCode.getCommonCdNm());
		
		//오더 속성 정보 조회
		OrderMastInfoVO orderMastInfo = orderManagementService.getOrderMastInfo(orderInfo.getOrderCd(), orderInfo.getLng(),orderInfo.getSoId());
		
		//오더 Copy 실행
		processOrderCopy(orderInfo, orderMastInfo);
		
		//오더 변경 처리 진행
		processOrderChange(orderInfo, orderMastInfo);
		
		//상담접수건 수정
		orderProcessMapper.updateRcptInfo(orderInfo.getSoId(), orderInfo.getRcptId(), orderInfo.getCtrtId());
	}
	
	

	/**
	 * 오더 생성 이후 공통 처리
	 * <PRE>
	 * 1. MethodName: processPostOrderMakeCommon
	 * 2. ClassName : OrderService
	 * 3. Comment   : 오더 생성 이후 공통 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:18:57
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processPostOrderMakeCommon(OrderRequestInfoVO orderInfo)  throws ServiceException{
		logger.debug("########## processPostOrderMakeCommon ###########");
		
		//작업처리
		Map<String,Object> addBasicProdInfo = orderInfo.getAddBasicProdList() == null ? null : orderInfo.getAddBasicProdList().get(0);
		if(addBasicProdInfo == null) return;
		List<Map<String,Object>> workInfoList = orderProcessMapper.getWorkInfoList(orderInfo.getSoId(), orderInfo.getBasicSvcCd(), (String)addBasicProdInfo.get("prodCd"), (String)orderInfo.getOrderTp());
		int firstWork = 0;
		for(Map<String,Object> workInfo : workInfoList){
			String seqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_SEQ_ID, 10);
			workInfo.put("wrk_id", seqNo);
			workInfo.put("order_id", orderInfo.getOrderId());
			workInfo.put("wrk_stat", "00");
			workInfo.put("wrk_req_wrker", firstWork == 0 ? orderInfo.getReqUsrId() : "" );
			workInfo.put("wrk_act_dt", "");
			workInfo.put("wrk_cmt_dt", "");
			workInfo.put("wrk_req_dt", orderInfo.getOrderReqDt());
			workInfo.put("usr_id", orderInfo.getReqUsrId());
			workInfo.put("today", orderInfo.getOrderReqDate());
			
			orderProcessMapper.insertWorkInfo(workInfo);
			firstWork++;
		}
		
		if(workInfoList.size() > 0){
			orderProcessMapper.updateStatusOCtrtMast(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_STAT02, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
		}
		
	}
	
	/**
	 * 오더 생성 이후 오더별 처리
	 * <PRE>
	 * 1. MethodName: processPostOrderMake
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:19:10
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public abstract void processPostOrderMake(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	/**
	 * 오더 취소 전처리
	 * <PRE>
	 * 1. MethodName: processPreOrderCancelCommon
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:25:53
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processPreOrderCancelCommon(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPreOrderCancelCommon ###########");
		/**
		 * Order Stat 체크
		 * 1. 작업이 존재하면 작업 완료 여부 체크
		 * 2. 작업이 존재하지 않으면 완료 가능한 상태인지 체크(01,02)만 가능
		 */
		
		OCtrtMastVO ctrtMastInfo = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		if(ctrtMastInfo.getOrderStat().equals(ORDER_STAT01)){
			return;
		}else if(ctrtMastInfo.getOrderStat().equals(ORDER_STAT02)){
			//작업 미완료 상태
			//throw new ServiceException("MSG.M01.MSG00002");
			return;
		}else if(ctrtMastInfo.getOrderStat().equals(ORDER_STAT03)){
			//취소 상태
			throw new ServiceException("MSG.M10.MSG00035");
			
		}else if(ctrtMastInfo.getOrderStat().equals(ORDER_STAT04)){
			//완료 상태
			throw new ServiceException("MSG.M08.MSG00024");
			
		}else{
			//상태 에러
			throw new ServiceException("MSG.M08.MSG00020");
		}
		
	}
	
	/**
	 * 오더 취소 오더별 전처리
	 * <PRE>
	 * 1. MethodName: processPreOrderCancel
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:26:05
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public abstract void processPreOrderCancel(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	/**
	 * 오더 취소 처리
	 * <PRE>
	 * 1. MethodName: processOrderCancel
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:27:22
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processOrderCancel(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processOrderCancel ###########");
		orderInfo.setOrderStat(ORDER_STAT03);
		CommonCodeVO orderCode = commonDataService.getCommonCode("CM00030",ORDER_STAT03, orderInfo.getLng());
		orderInfo.setOrderStatNm(orderCode.getCommonCdNm());
		
		orderProcessMapper.updateStatusOCtrtMast(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_STAT03, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
		List<OProdCmpsVO> prodCmpsList = orderProcessMapper.getOProdCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		for(OProdCmpsVO prodCmpsInfo : prodCmpsList){
			orderProcessMapper.updateStatusOProdCmps(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), prodCmpsInfo.getProdCmpsId(), ORDER_STAT03, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
		}
		
		List<OSvcCmpsVO> svcCmpsList = orderProcessMapper.getOSvcCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		for(OSvcCmpsVO svcCmpsInfo : svcCmpsList){
			orderProcessMapper.updateStatusOSvcCmps(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), svcCmpsInfo.getSvcCmpsId(), ORDER_STAT03, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
		}
		
	}
	
	/**
	 * 오더 취소 후처리 공통
	 * <PRE>
	 * 1. MethodName: processPostOrderCancelCommon
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:25:53
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processPostOrderCancelCommon(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPostOrderCancelCommon ###########");
	}
	
	/**
	 * 오더 취소 오더별 처리
	 * <PRE>
	 * 1. MethodName: processPostOrderCancel
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:26:05
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public abstract void processPostOrderCancel(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	
	/**
	 * 오더 완료 전처리 공통
	 * <PRE>
	 * 1. MethodName: processPreOrderCmplCommon
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:31:31
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processPreOrderCmplCommon(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPreOrderCmplCommon ###########");
		/**
		 * Order Stat 체크
		 * 1. 작업이 존재하면 작업 완료 여부 체크
		 * 2. 작업이 존재하지 않으면 완료 가능한 상태인지 체크(01,02)만 가능
		 */
		
		OCtrtMastVO ctrtMastInfo = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		
		if(ctrtMastInfo == null || StringUtils.isEmpty(ctrtMastInfo.getCtrtId())){
			throw new ServiceException("MSG.M08.MSG00088");
		}
		
		
		if(ctrtMastInfo.getOrderStat().equals(ORDER_STAT01)){
			return;
			
		}else if(ctrtMastInfo.getOrderStat().equals(ORDER_STAT02)){
			return;
			//작업 미완료 상태
			//throw new ServiceException("MSG.M01.MSG00002");
		}else if(ctrtMastInfo.getOrderStat().equals(ORDER_STAT03)){
			//취소 상태
			throw new ServiceException("MSG.M10.MSG00035");
			
		}else if(ctrtMastInfo.getOrderStat().equals(ORDER_STAT04)){
			//완료 상태
			throw new ServiceException("MSG.M08.MSG00024");
			
		}else{
			//상태 에러
			throw new ServiceException("MSG.M08.MSG00020");
		}
	}
	
	/**
	 * 오더 완료 전처리 오더별
	 * <PRE>
	 * 1. MethodName: processPreOrderCmpl
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:31:43
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public abstract void processPreOrderCmpl(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	/**
	 * <PRE>
	 * 1. MethodName: processOrderCmpl
	 * 2. ClassName : OrderService
	 * 3. Comment   : 오더 완료 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:31:51
	 * </PRE>
	 *   @return void 
	 *   @param orderInfo 오더정보
	 */
	public void processOrderCmpl(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processOrderCmpl =====   ###########");
		//계약 상태 설정
		orderInfo.setOrderStat(ORDER_STAT04);
		CommonCodeVO orderCode = commonDataService.getCommonCode("CM00030",ORDER_STAT04, orderInfo.getLng());
		orderInfo.setOrderStatNm(orderCode.getCommonCdNm());
		
		//시간 설정
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
        orderInfo.setOrderReqDttm(dateFormat.format(cal.getTime()));
        orderInfo.setOrderReqDt(dtFormat.format(cal.getTime()));
        orderInfo.setOrderReqDate(new Date());
        
        cal.add(Calendar.SECOND, 1);
        String nextActDttm =  dateFormat.format(cal.getTime()); //1초 더한 시간
        String nextOrderDt = dtFormat.format(cal.getTime()); //1초더한 일자
        Date nextReqDate = cal.getTime(); //1초 더한 시간
		
		
        OCtrtMastVO ctrtMastLastInfo = orderProcessMapper.getCtrtMastLastInfo(orderInfo.getSoId(),  orderInfo.getCustId(), orderInfo.getCtrtId());
        String cancleCtrtStat = new String();
		if(ctrtMastLastInfo != null && ("50".equals(ctrtMastLastInfo.getCtrtStat()) || "90".equals(ctrtMastLastInfo.getCtrtStat()))){
			cancleCtrtStat = "98";
		}
        
		OCtrtMastVO ctrtMastInfo = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		ctrtMastInfo.setOrderStat(ORDER_STAT04);
		
		//해지 플래그
		boolean isTerm = false;
		if("50".equals(ctrtMastInfo.getCtrtStat()) || "90".equals(ctrtMastInfo.getCtrtStat())){
			isTerm = true;
		}
		
		//해지 취소의 경우 이전 게약상태 수정
		int ctrtUpdCnt = orderProcessMapper.updateCtrtHist(orderInfo, cancleCtrtStat);
		if(ctrtUpdCnt > 0){
			ctrtMastInfo.setActDttm(nextActDttm);
			ctrtMastInfo.setOpenDd(nextOrderDt);
			ctrtMastInfo.setChgDate(nextReqDate);
			ctrtMastInfo.setTermDt(isTerm ? nextOrderDt : ctrtMastInfo.getTermDt());
			ctrtMastInfo.setTermHopeDt(isTerm ? nextOrderDt : ctrtMastInfo.getTermDt());
		}else{
			ctrtMastInfo.setActDttm(orderInfo.getOrderReqDttm());
			ctrtMastInfo.setOpenDd(orderInfo.getOrderReqDt());
			ctrtMastInfo.setChgDate(orderInfo.getOrderReqDate());
			ctrtMastInfo.setTermDt(isTerm ? orderInfo.getOrderReqDt() : ctrtMastInfo.getTermDt());
			ctrtMastInfo.setTermHopeDt(isTerm ? orderInfo.getOrderReqDt() : ctrtMastInfo.getTermDt());
		}
		
		
		ctrtMastInfo.setChgrId(orderInfo.getReqUsrId());
		orderProcessMapper.insertCtrtMast(ctrtMastInfo);
		orderProcessMapper.updateStatusOCtrtMast(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_STAT04, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
		
		if(!StringUtils.isEmpty(ctrtMastInfo.getExtId())){
			OCtrtMastExtVO ctrtMastExt = orderProcessMapper.getOCtrtMastExtInfo(ctrtMastInfo.getExtId(), ctrtMastInfo.getOrderId());
			
			int ctrtExtUpdCnt = orderProcessMapper.updateCtrtExtHist(ctrtMastInfo.getExtId(), orderInfo.getOrderReqDttm());
			if(ctrtExtUpdCnt > 0){
				ctrtMastExt.setActDttm(nextActDttm);
				ctrtMastExt.setChgDate(nextReqDate);
			}else{
				ctrtMastExt.setActDttm(orderInfo.getOrderReqDttm());
				ctrtMastExt.setChgDate(orderInfo.getOrderReqDate());
			}
			ctrtMastExt.setChgrId(orderInfo.getReqUsrId());
			orderProcessMapper.insertCtrtMastExt(ctrtMastExt);
		}
		
		List<OProdCmpsVO> prodCmpsList = orderProcessMapper.getOProdCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		for(OProdCmpsVO prodCmpsInfo : prodCmpsList){
			prodCmpsInfo.setOrderStat(ORDER_STAT04);
			int prodCmpsUpdCnt = orderProcessMapper.updateProdCmpsHist(prodCmpsInfo.getSoId(), prodCmpsInfo.getCtrtId(), prodCmpsInfo.getProdCmpsId(), orderInfo.getOrderReqDttm(), cancleCtrtStat);
			if(prodCmpsUpdCnt > 0){
				prodCmpsInfo.setActDttm(nextActDttm);
				prodCmpsInfo.setOpenDd(nextOrderDt);
				prodCmpsInfo.setChgDate(nextReqDate);
				prodCmpsInfo.setTermDt(isTerm ? nextOrderDt : prodCmpsInfo.getTermDt());
			}else{
				prodCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				prodCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				prodCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				prodCmpsInfo.setTermDt(isTerm ? orderInfo.getOrderReqDt() : prodCmpsInfo.getTermDt());
			}
			prodCmpsInfo.setChgrId(orderInfo.getReqUsrId());
			orderProcessMapper.insertProdCmps(prodCmpsInfo);
			orderProcessMapper.updateStatusOProdCmps(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), prodCmpsInfo.getProdCmpsId(), ORDER_STAT04, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
			
			if(!StringUtils.isEmpty(prodCmpsInfo.getExtId())){
				OProdCmpsExtVO prodCmpsInfoExt = orderProcessMapper.getOProdCmpsExtInfo(prodCmpsInfo.getExtId(), orderInfo.getOrderId());
				
				int prodCmpsExtUpdCnt = orderProcessMapper.updateProdCmpsExtHist(prodCmpsInfo.getExtId(), orderInfo.getOrderReqDttm());
				if(prodCmpsExtUpdCnt > 0){
					prodCmpsInfoExt.setActDttm(nextActDttm);
					prodCmpsInfoExt.setChgDate(nextReqDate);
				}else{
					prodCmpsInfoExt.setActDttm(orderInfo.getOrderReqDttm());
					prodCmpsInfoExt.setChgDate(orderInfo.getOrderReqDate());
				}

				prodCmpsInfoExt.setChgrId(orderInfo.getReqUsrId());
				orderProcessMapper.insertProdCmpsExt(prodCmpsInfoExt);
			}
		}
		
		List<OSvcCmpsVO> svcCmpsList = orderProcessMapper.getOSvcCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		for(OSvcCmpsVO svcCmpsInfo : svcCmpsList){
			svcCmpsInfo.setOrderStat(ORDER_STAT04);
			
			int svcCmpsUpdCnt = orderProcessMapper.updateSvcCmpsHist(svcCmpsInfo.getSoId(), svcCmpsInfo.getCtrtId(), svcCmpsInfo.getSvcCmpsId(), orderInfo.getOrderReqDttm(), cancleCtrtStat);
			
			if(svcCmpsUpdCnt > 0){
				svcCmpsInfo.setActDttm(nextActDttm);
				svcCmpsInfo.setOpenDd(nextOrderDt);
				svcCmpsInfo.setChgDate(nextReqDate);
				svcCmpsInfo.setTermDt(isTerm ? nextOrderDt : svcCmpsInfo.getTermDt());
			}else{
				svcCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				svcCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				svcCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				svcCmpsInfo.setTermDt(isTerm ? orderInfo.getOrderReqDt() : svcCmpsInfo.getTermDt());
				
			}
			
			svcCmpsInfo.setChgrId(orderInfo.getReqUsrId());
			orderProcessMapper.insertSvcCmps(svcCmpsInfo);
			orderProcessMapper.updateStatusOSvcCmps(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), svcCmpsInfo.getSvcCmpsId(), ORDER_STAT04, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
			
			if(!StringUtils.isEmpty(svcCmpsInfo.getExtId())){
				OSvcCmpsExtVO svcCmpsInfoExt = orderProcessMapper.getOSvcCmpsExtInfo(svcCmpsInfo.getExtId(), orderInfo.getOrderId());
				
				int svcCmpsExtUpdCnt = orderProcessMapper.updateSvcCmpsExtHist(svcCmpsInfo.getExtId(), orderInfo.getOrderReqDttm());
				
				if(svcCmpsExtUpdCnt > 0){
					svcCmpsInfoExt.setActDttm(nextActDttm);
					svcCmpsInfoExt.setChgDate(nextReqDate);	
				}else{
					svcCmpsInfoExt.setActDttm(orderInfo.getOrderReqDttm());
					svcCmpsInfoExt.setChgDate(orderInfo.getOrderReqDate());
					
				}
				svcCmpsInfoExt.setChgrId(orderInfo.getReqUsrId());
				orderProcessMapper.insertSvcCmpsExt(svcCmpsInfoExt);
			}
		}
	}
	
	/**
	 * 오더 완료 후처리 공통
	 * <PRE>
	 * 1. MethodName: processPostOrderCmplCommon
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:34:40
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processPostOrderCmplCommon(OrderRequestInfoVO orderInfo) throws ServiceException{
		
	}
	
	/**
	 * 오더 완료 오더별 처리
	 * <PRE>
	 * 1. MethodName: processPostOrderCmpl
	 * 2. ClassName : OrderService
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:34:52
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public abstract void processPostOrderCmpl(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	
	
	
	/**
	 * <PRE>
	 * 1. MethodName: processPreOrderProgressCommon
	 * 2. ClassName : OrderService
	 * 3. Comment   : 오더 진행 이전에 공통 Validation체크
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 16. 오후 2:16:44
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 */
	public void processPreOrderProgressCommon(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPreOrderProgressCommon ###########");
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: processPreOrderProgress
	 * 2. ClassName : OrderProcessServiceImpl
	 * 3. Comment   : 오더 진행 이전 오더별 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 5:06:44
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 *   @throws ServiceException
	 */
	public abstract void processPreOrderProgress(OrderRequestInfoVO orderInfo) throws ServiceException;
	
	/**
	 * <PRE>
	 * 1. MethodName: processOrderProgress
	 * 2. ClassName : OrderProcessServiceImpl
	 * 3. Comment   : 오더 진행 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 5:07:04
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 *   @throws ServiceException
	 */
	public void processOrderProgress(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processOrderProgress ###########");
		orderInfo.setOrderStat(ORDER_STAT02);
		CommonCodeVO orderCode = commonDataService.getCommonCode("CM00030",ORDER_STAT02, orderInfo.getLng());
		orderInfo.setOrderStatNm(orderCode.getCommonCdNm());
		
		orderProcessMapper.updateStatusOCtrtMast(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), ORDER_STAT02, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
		List<OProdCmpsVO> prodCmpsList = orderProcessMapper.getOProdCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		for(OProdCmpsVO prodCmpsInfo : prodCmpsList){
			orderProcessMapper.updateStatusOProdCmps(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), prodCmpsInfo.getProdCmpsId(), ORDER_STAT02, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
		}
		
		List<OSvcCmpsVO> svcCmpsList = orderProcessMapper.getOSvcCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
		for(OSvcCmpsVO svcCmpsInfo : svcCmpsList){
			orderProcessMapper.updateStatusOSvcCmps(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId(), svcCmpsInfo.getSvcCmpsId(), ORDER_STAT02, orderInfo.getReqUsrId(), orderInfo.getOrderReqDate());
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: processPostOrderProgressCommon
	 * 2. ClassName : OrderProcessServiceImpl
	 * 3. Comment   : 오더 진행 이후 공통 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 5:07:33
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 *   @throws ServiceException
	 */
	public void processPostOrderProgressCommon(OrderRequestInfoVO orderInfo) throws ServiceException{
		logger.debug("########## processPreOrderProgressCommon ###########");
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: processPostOrderProgress
	 * 2. ClassName : OrderProcessServiceImpl
	 * 3. Comment   : 오더 진행 이후 오더별 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 5:07:47
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 *   @throws ServiceException
	 */
	public abstract void processPostOrderProgress(OrderRequestInfoVO orderInfo) throws ServiceException;
	

	/**
	 * <PRE>
	 * 1. MethodName: processOrderCopy
	 * 2. ClassName : OrderProcessServiceImpl
	 * 3. Comment   : 오더 정보 Copy처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 2:16:38
	 * </PRE>
	 *   @return void
	 *   @param orderInfo 오더요청정보
	 *   @param orderMastInfo 오더마스터 정보
	 */
	private void processOrderCopy(OrderRequestInfoVO orderInfo, OrderMastInfoVO orderMastInfo) {
		
		
		OrderAttrVO attr = orderMastInfo.getOrderAttrMap().get("OA00000003");
		
		System.out.println("copy ==============================uu============================= " + attr.getOrderAttrVal());
		
		if("A".equals(attr.getOrderAttrVal())){ //전체 이력 복사
			String orderId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_ORDER_ID, 10);
			
			//계약 원부 조회
			OCtrtMastVO ctrtInfo = orderProcessMapper.getCtrtMastLastInfo(orderInfo.getSoId(), orderInfo.getCustId(), orderInfo.getCtrtId());
			String lastOrderId = ctrtInfo.getOrderId();
			String ctrtStat = ctrtInfo.getCtrtStat();
			
			ctrtInfo.setOrderId(orderId);
			ctrtInfo.setOrderStat(ORDER_STAT01);
			ctrtInfo.setOrderDt(orderInfo.getOrderReqDt());
			ctrtInfo.setOpenDd(orderInfo.getOrderReqDt());
			ctrtInfo.setOrderTp(orderMastInfo.getOrderTp());
			ctrtInfo.setRcptId(orderInfo.getRcptId());
			ctrtInfo.setIfSucYn("H");
			ctrtInfo.setActDttm(orderInfo.getOrderReqDttm());
			ctrtInfo.setUsrId(orderInfo.getReqUsrId());
			ctrtInfo.setOrgId(orderInfo.getReqOrgId());
			ctrtInfo.setChgrId(orderInfo.getReqUsrId());
			ctrtInfo.setChgDate(orderInfo.getOrderReqDate());
			ctrtInfo.setCtrtStat(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
			ctrtInfo.setDefResnCd(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
			ctrtInfo.setCtrtNm(orderMastInfo.getCtrtNm());
			orderProcessMapper.insertOrderMast(ctrtInfo);
			if(!StringUtils.isEmpty(ctrtInfo.getExtId())){
				OCtrtMastExtVO ctrtExtInfo = orderProcessMapper.getCtrtMastLastExtInfo(ctrtInfo.getExtId());
				ctrtExtInfo.setActDttm(orderInfo.getOrderReqDttm());
				ctrtExtInfo.setOrderId(ctrtInfo.getOrderId());
				ctrtExtInfo.setChgrId(orderInfo.getReqUsrId());
				ctrtExtInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderMastExt(ctrtExtInfo);
			}
			
			//상품구성정보 오더 작성
			List<OProdCmpsVO> prodInfoList;
			if(!"50".equals(ctrtStat) && !"90".equals(ctrtStat)){
				prodInfoList = orderProcessMapper.getProdCmpsLastInfo(orderInfo.getSoId(), orderInfo.getCtrtId());
			}else{
				prodInfoList = orderProcessMapper.getProdCmpsLastInfoTerminated(orderInfo.getSoId(), orderInfo.getCtrtId(), lastOrderId);
			}
			 
			for(OProdCmpsVO prodCmps : prodInfoList){
				prodCmps.setOrderId(orderId);
				prodCmps.setOrderStat(ORDER_STAT01);
				prodCmps.setOrderTp(orderMastInfo.getOrderTp());
				prodCmps.setRcptId(orderInfo.getRcptId());
				prodCmps.setOpenDd(orderInfo.getOrderReqDt());
				prodCmps.setActDttm(orderInfo.getOrderReqDttm());
				prodCmps.setUsrId(orderInfo.getReqUsrId());
				prodCmps.setOrgId(orderInfo.getReqOrgId());
				prodCmps.setChgrId(orderInfo.getReqUsrId());
				prodCmps.setChgDate(orderInfo.getOrderReqDate());
				prodCmps.setProdStatCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				prodCmps.setProdChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				orderProcessMapper.insertOrderProdCmps(prodCmps);
				if(!StringUtils.isEmpty(prodCmps.getExtId())){
					OProdCmpsExtVO prodExtInfo = orderProcessMapper.getProdCmpsExtLastInfo(prodCmps.getExtId());
					prodExtInfo.setActDttm(orderInfo.getOrderReqDttm());
					prodExtInfo.setOrderId(orderId);
					prodExtInfo.setChgrId(orderInfo.getReqUsrId());
					prodExtInfo.setChgDate(orderInfo.getOrderReqDate());
					orderProcessMapper.insertOrderProdCmpsExt(prodExtInfo);
				}
			}
			//서비스구성정보 오더 작성
			List<OSvcCmpsVO> svcInfoList;
			if(!"50".equals(ctrtStat) && !"90".equals(ctrtStat)){
				svcInfoList = orderProcessMapper.getSvcCmpsLastInfo(orderInfo.getSoId(), orderInfo.getCtrtId());
			}else{
				svcInfoList = orderProcessMapper.getSvcCmpsLastInfoTerminated(orderInfo.getSoId(), orderInfo.getCtrtId(), lastOrderId);
			}
			
			for(OSvcCmpsVO svcCmps : svcInfoList){
				svcCmps.setOrderId(orderId);
				svcCmps.setOrderStat(ORDER_STAT01);
				svcCmps.setOrderTp(orderMastInfo.getOrderTp());
				svcCmps.setRcptId(orderInfo.getRcptId());
				svcCmps.setOpenDd(orderInfo.getOrderReqDt());
				svcCmps.setActDttm(orderInfo.getOrderReqDttm());
				svcCmps.setChgrId(orderInfo.getReqUsrId());
				svcCmps.setChgDate(orderInfo.getOrderReqDate());
				svcCmps.setSvcStsCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				svcCmps.setSvcChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				orderProcessMapper.insertOrderSvcCmps(svcCmps);
				if(!StringUtils.isEmpty(svcCmps.getExtId())){
					OSvcCmpsExtVO svcExtInfo = orderProcessMapper.getSvcCmpsExtLastInfo(svcCmps.getExtId());
					svcExtInfo.setActDttm(orderInfo.getOrderReqDttm());
					svcExtInfo.setOrderId(orderId);
					svcExtInfo.setChgrId(orderInfo.getReqUsrId());
					svcExtInfo.setChgDate(orderInfo.getOrderReqDate());
					orderProcessMapper.insertOrderSvcCmpsExt(svcExtInfo);
				}
			}
			
			orderInfo.setOrderId(ctrtInfo.getOrderId());
			
		}else if("B".equals(attr.getOrderAttrVal())){ //변경 정보만 처리
			String orderId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_ORDER_ID, 10);
			//계약 원부 조회
			OCtrtMastVO ctrtInfo = orderProcessMapper.getCtrtMastLastInfo(orderInfo.getSoId(), orderInfo.getCustId(), orderInfo.getCtrtId());
			ctrtInfo.setOrderId(orderId);
			ctrtInfo.setOrderStat(ORDER_STAT01);
			ctrtInfo.setOrderDt(orderInfo.getOrderReqDt());
			ctrtInfo.setOpenDd(orderInfo.getOrderReqDt());
			ctrtInfo.setOrderTp(orderMastInfo.getOrderTp());
			ctrtInfo.setRcptId(orderInfo.getRcptId());
			ctrtInfo.setIfSucYn("H");
			ctrtInfo.setActDttm(orderInfo.getOrderReqDttm());
			ctrtInfo.setUsrId(orderInfo.getReqUsrId());
			ctrtInfo.setOrgId(orderInfo.getReqOrgId());
			ctrtInfo.setChgrId(orderInfo.getReqUsrId());
			ctrtInfo.setChgDate(orderInfo.getOrderReqDate());
			ctrtInfo.setCtrtStat(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
			ctrtInfo.setDefResnCd(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
			ctrtInfo.setCtrtNm(orderMastInfo.getCtrtNm());
			orderProcessMapper.insertOrderMast(ctrtInfo);
			if(!StringUtils.isEmpty(ctrtInfo.getExtId())){
				OCtrtMastExtVO ctrtExtInfo = orderProcessMapper.getCtrtMastLastExtInfo(ctrtInfo.getExtId());
				ctrtExtInfo.setActDttm(orderInfo.getOrderReqDttm());
				ctrtExtInfo.setOrderId(ctrtInfo.getOrderId());
				ctrtExtInfo.setChgrId(orderInfo.getReqUsrId());
				ctrtExtInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderMastExt(ctrtExtInfo);
			}
			
			
			
			//삭제 상품구성정보 오더 작성
			List<OProdCmpsVO> prodInfoList = orderProcessMapper.getProdCmpsLastInfo(orderInfo.getSoId(), orderInfo.getCtrtId());
			for(OProdCmpsVO prodCmps : prodInfoList){
				boolean isDelProd = false;
				
				if(orderInfo.getDelBasicProdList() != null){
					for(Map<String,Object> delProdInfo : orderInfo.getDelBasicProdList()){
						if(prodCmps.getProdCd().equals((String)delProdInfo.get("prodCd"))){
							isDelProd = true;
							break;
						}
					}
				}
				
				if(orderInfo.getDelDeviceProdList() != null){
					for(Map<String,Object> delProdInfo : orderInfo.getDelDeviceProdList()){
						if(prodCmps.getProdCmpsId().equals((String)delProdInfo.get("prodCmpsId"))){
							isDelProd = true;
							break;
						}
					}
				}
				
				if(orderInfo.getDelAddonProdList() != null){
					if(isDelProd == false){
						for(Map<String,Object> delProdInfo : orderInfo.getDelAddonProdList()){
							if(prodCmps.getProdCmpsId().equals((String)delProdInfo.get("prodCmpsId"))){
								isDelProd = true;
								break;
							}
						}
					}
				}

				
				if(isDelProd == false) continue; //대상 상품이 아니면 Continue
				
				prodCmps.setOrderId(orderId);
				prodCmps.setOrderStat(ORDER_STAT01);
				prodCmps.setOrderTp(orderMastInfo.getOrderTp());
				prodCmps.setRcptId(orderInfo.getRcptId());
				prodCmps.setOpenDd(orderInfo.getOrderReqDt());
				prodCmps.setActDttm(orderInfo.getOrderReqDttm());
				prodCmps.setUsrId(orderInfo.getReqUsrId());
				prodCmps.setOrgId(orderInfo.getReqOrgId());
				prodCmps.setChgrId(orderInfo.getReqUsrId());
				prodCmps.setChgDate(orderInfo.getOrderReqDate());
				prodCmps.setProdStatCd("90");
				prodCmps.setProdChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				orderProcessMapper.insertOrderProdCmps(prodCmps);
				if(!StringUtils.isEmpty(prodCmps.getExtId())){
					OProdCmpsExtVO prodExtInfo = orderProcessMapper.getProdCmpsExtLastInfo(prodCmps.getExtId());
					prodExtInfo.setActDttm(orderInfo.getOrderReqDttm());
					prodExtInfo.setOrderId(orderId);
					prodExtInfo.setChgrId(orderInfo.getReqUsrId());
					prodExtInfo.setChgDate(orderInfo.getOrderReqDate());
					orderProcessMapper.insertOrderProdCmpsExt(prodExtInfo);
				}
			}
			
			//삭제 서비스구성정보 오더 작성
			List<OSvcCmpsVO> svcInfoList = orderProcessMapper.getSvcCmpsLastInfo(orderInfo.getSoId(), orderInfo.getCtrtId());
			for(OSvcCmpsVO svcCmps : svcInfoList){
				boolean isDelProd = false;
				

				if(orderInfo.getDelBasicProdList() != null){

					System.out.println("orderInfo.getDelBasicProdList()============================================= 사이즈==" + orderInfo.getDelBasicProdList().size());
					
					for(Map<String,Object> delProdInfo : orderInfo.getDelBasicProdList()){
						if(svcCmps.getProdCd().equals((String)delProdInfo.get("prodCd"))){
							isDelProd = true;
							break;
						}
					}
				}
				
				
				if( orderInfo.getDelDeviceProdList() != null){
					for(Map<String,Object> delProdInfo : orderInfo.getDelDeviceProdList()){
						if(svcCmps.getProdCmpsId().equals((String)delProdInfo.get("prodCmpsId"))){
							isDelProd = true;
							break;
						}
					}
					
					
				}
				if( orderInfo.getDelAddonProdList() != null){
					
					System.out.println("orderInfo.getDelAddonProdList()============================================= 사이즈==" + orderInfo.getDelAddonProdList().size());
					
					
					if(isDelProd == false){
						for(Map<String,Object> delProdInfo : orderInfo.getDelAddonProdList()){
							if(svcCmps.getProdCmpsId().equals((String)delProdInfo.get("prodCmpsId"))){
								isDelProd = true;
								break;
							}
						}
					}
				}

				if(isDelProd == false) continue; //대상 상품이 아니면 Continue
				
				svcCmps.setOrderId(orderId);
				svcCmps.setOrderStat(ORDER_STAT01);
				svcCmps.setOrderTp(orderMastInfo.getOrderTp());
				svcCmps.setRcptId(orderInfo.getRcptId());
				svcCmps.setOpenDd(orderInfo.getOrderReqDt());
				svcCmps.setActDttm(orderInfo.getOrderReqDttm());
				svcCmps.setChgrId(orderInfo.getReqUsrId());
				svcCmps.setChgDate(orderInfo.getOrderReqDate());
				svcCmps.setSvcStsCd("90");
				svcCmps.setSvcChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				
				System.out.println("insertOrderSvcCmps =====================등록호출되나....");
				
				orderProcessMapper.insertOrderSvcCmps(svcCmps);
				if(!StringUtils.isEmpty(svcCmps.getExtId())){
					OSvcCmpsExtVO svcExtInfo = orderProcessMapper.getSvcCmpsExtLastInfo(svcCmps.getExtId());
					svcExtInfo.setActDttm(orderInfo.getOrderReqDttm());
					svcExtInfo.setOrderId(orderId);
					svcExtInfo.setChgrId(orderInfo.getReqUsrId());
					svcExtInfo.setChgDate(orderInfo.getOrderReqDate());
					orderProcessMapper.insertOrderSvcCmpsExt(svcExtInfo);
				}
			}
			
			
			
			
			
			
			orderInfo.setOrderId(ctrtInfo.getOrderId());
		}else if("C".equals(attr.getOrderAttrVal())){ //원부만 복사
			//계약 원부 조회
			OCtrtMastVO ctrtInfo = orderProcessMapper.getCtrtMastLastInfo(orderInfo.getSoId(), orderInfo.getCustId(), orderInfo.getCtrtId());
			ctrtInfo.setOrderId(sequenceService.createNewSequence(Consts.SEQ_CODE.CM_ORDER_ID, 10));
			ctrtInfo.setOrderStat(ORDER_STAT01);
			ctrtInfo.setOrderDt(orderInfo.getOrderReqDt());
			ctrtInfo.setOpenDd(orderInfo.getOrderReqDt());
			ctrtInfo.setOrderTp(orderMastInfo.getOrderTp());
			ctrtInfo.setRcptId(orderInfo.getRcptId());
			ctrtInfo.setIfSucYn("H");
			ctrtInfo.setActDttm(orderInfo.getOrderReqDttm());
			ctrtInfo.setUsrId(orderInfo.getReqUsrId());
			ctrtInfo.setOrgId(orderInfo.getReqOrgId());
			ctrtInfo.setChgrId(orderInfo.getReqUsrId());
			ctrtInfo.setChgDate(orderInfo.getOrderReqDate());
			ctrtInfo.setDefResnCd(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
			ctrtInfo.setCtrtNm(orderMastInfo.getCtrtNm());
			orderProcessMapper.insertOrderMast(ctrtInfo);
			if(!StringUtils.isEmpty(ctrtInfo.getExtId())){
				OCtrtMastExtVO ctrtExtInfo = orderProcessMapper.getCtrtMastLastExtInfo(ctrtInfo.getExtId());
				ctrtExtInfo.setActDttm(orderInfo.getOrderReqDttm());
				ctrtExtInfo.setOrderId(ctrtInfo.getOrderId());
				ctrtExtInfo.setChgrId(orderInfo.getReqUsrId());
				ctrtExtInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderMastExt(ctrtExtInfo);
			}
			orderInfo.setOrderId(ctrtInfo.getOrderId());
		}else if("D".equals(attr.getOrderAttrVal())){
			return;
		}
	}
	
	
	
	/**
	 * <PRE>
	 * 1. MethodName: processOrderChange
	 * 2. ClassName : OrderProcessServiceImpl
	 * 3. Comment   :
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 3. 23. 오후 3:52:43
	 * </PRE>
	 *   @return void
	 *   @param orderInfo
	 *   @param orderMastInfo
	 */
	private void processOrderChange(OrderRequestInfoVO orderInfo, OrderMastInfoVO orderMastInfo) {
		OrderAttrVO attr = orderMastInfo.getOrderAttrMap().get("OA00000003");
		if("A".equals(attr.getOrderAttrVal())){ //전체 변경 시점에 처리
			//일시정지의 경우
			OCtrtMastVO octrtInfo = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());

			//변경사유 상세 세팅
			octrtInfo.setCtrtChgResnCd(orderInfo.getChngResn());
			octrtInfo.setSusHopeDd(orderInfo.getStartHopeDt());
			octrtInfo.setMmtSusHopeDd(orderInfo.getEndHopeDt());
			octrtInfo.setMmtSusCd(orderInfo.getChngDetailResn()); // 정지 상세 사유코드
			octrtInfo.setTermDt(StringUtils.isNotEmpty(orderInfo.getTermHopeDt()) ? orderInfo.getTermHopeDt() : "99991231");
			octrtInfo.setTermHopeDt(StringUtils.isNotEmpty(orderInfo.getTermHopeDt()) ? orderInfo.getTermHopeDt() : "");
			octrtInfo.setRemark(orderInfo.getRemark());
			orderProcessMapper.updateOCtrtInfo(octrtInfo);
			
			List<OProdCmpsVO> oprodCmpsInfoList = orderProcessMapper.getOProdCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
			for(OProdCmpsVO oprodCmpsInfo : oprodCmpsInfoList){
				//해지요청
				oprodCmpsInfo.setTermDt(StringUtils.isNotEmpty(orderInfo.getTermHopeDt()) ? orderInfo.getTermHopeDt() : "99991231");
				orderProcessMapper.updateOProdCmpsInfo(oprodCmpsInfo);
			}
			
			List<OSvcCmpsVO> osvcCmpsInfoList = orderProcessMapper.getOSvcCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
			for(OSvcCmpsVO osvcCmpsInfo : osvcCmpsInfoList){
				osvcCmpsInfo.setTermDt(StringUtils.isNotEmpty(orderInfo.getTermHopeDt()) ? orderInfo.getTermHopeDt() : "99991231");
				orderProcessMapper.updateOSvcCmpsInfo(osvcCmpsInfo);
			}
		}else if("B".equals(attr.getOrderAttrVal())){ //변경 정보만 처리
			OCtrtMastVO octrtInfo = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
			
			System.out.println(" orderInfo.getCtrtId() =================" +  orderInfo.getCtrtId());
			System.out.println(" orderInfo.getOrderId() =================" +  orderInfo.getOrderId());
			
			//변경사유 상세 세팅
			octrtInfo.setCtrtChgResnCd(orderInfo.getChngResn());
			octrtInfo.setSusHopeDd(orderInfo.getStartHopeDt());
			octrtInfo.setMmtSusHopeDd(orderInfo.getEndHopeDt());
			octrtInfo.setMmtSusCd(orderInfo.getChngDetailResn()); // 정지 상세 사유코드
			octrtInfo.setTermDt(StringUtils.isNotEmpty(orderInfo.getTermHopeDt()) ? orderInfo.getTermHopeDt() : "99991231");
			octrtInfo.setTermHopeDt(StringUtils.isNotEmpty(orderInfo.getTermHopeDt()) ? orderInfo.getTermHopeDt() : "");
			octrtInfo.setRemark(orderInfo.getRemark());
			
			String basicProdCmpsId = null;
			String basicSvcCmpsId = null;
			if(orderInfo.getAddBasicProdList() != null){
				basicProdCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_PROD_CMPS_ID, "P", 10);
				basicSvcCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_SVC_CMPS_ID, "S", 10);
				orderInfo.getAddBasicProdList().get(0).put("prodCmpsId", basicProdCmpsId);
				orderInfo.getAddBasicProdList().get(0).put("svcCmpsId", basicSvcCmpsId);
				octrtInfo.setProdCmpsId(basicProdCmpsId);
				octrtInfo.setProdCd((String)orderInfo.getAddBasicProdList().get(0).get("prodCd"));
				octrtInfo.setProdGrp((String)orderInfo.getAddBasicProdList().get(0).get("prodGrp"));
			}
			orderProcessMapper.updateOCtrtInfo(octrtInfo);
			
			//삭제상품처리
			List<OProdCmpsVO> oprodCmpsInfoList = orderProcessMapper.getOProdCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
			OProdCmpsVO basicProdInUse = null;
			for(OProdCmpsVO oprodCmpsInfo : oprodCmpsInfoList){
				boolean isDelProd = false;
				
				if(orderInfo.getDelBasicProdList() != null){
					for(Map<String,Object> delProdInfo : orderInfo.getDelBasicProdList()){
						if(oprodCmpsInfo.getProdCd().equals((String)delProdInfo.get("prodCd"))){
							isDelProd = true;
							basicProdInUse = oprodCmpsInfo;
							break;
						}
					}
				}
				
				
				if(orderInfo.getDelDeviceProdList() != null){
					for(Map<String,Object> delProdInfo : orderInfo.getDelDeviceProdList()){
						if(oprodCmpsInfo.getProdCmpsId().equals((String)delProdInfo.get("prodCmpsId"))){
							isDelProd = true;
							break;
						}
					}
				}
				
				if(orderInfo.getDelAddonProdList() != null){
					if(isDelProd == false){
						for(Map<String,Object> delProdInfo : orderInfo.getDelAddonProdList()){
							if(oprodCmpsInfo.getProdCmpsId().equals((String)delProdInfo.get("prodCmpsId"))){
								isDelProd = true;
								break;
							}
						}	
					}
				}
				
				
				if(isDelProd == false) continue; //대상 상품이 아니면 Continue
				oprodCmpsInfo.setTermDt(orderInfo.getOrderReqDt());
				orderProcessMapper.updateOProdCmpsInfo(oprodCmpsInfo);
			}
			
			List<OSvcCmpsVO> osvcCmpsInfoList = orderProcessMapper.getOSvcCmpsInfoList(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
			for(OSvcCmpsVO osvcCmpsInfo : osvcCmpsInfoList){
				
				boolean isDelProd = false;
				if(orderInfo.getDelBasicProdList() != null){
					for(Map<String,Object> delProdInfo : orderInfo.getDelBasicProdList()){
						if(osvcCmpsInfo.getProdCd().equals((String)delProdInfo.get("prodCd"))){
							isDelProd = true;
							break;
						}
					}
				}
				
				if(orderInfo.getDelDeviceProdList() != null){
					for(Map<String,Object> delProdInfo : orderInfo.getDelDeviceProdList()){
						if(osvcCmpsInfo.getProdCmpsId().equals((String)delProdInfo.get("prodCmpsId"))){
							isDelProd = true;
							break;
						}
					}
				}
				
				if(orderInfo.getDelAddonProdList() != null){
					if(isDelProd == false){
						if(isDelProd == false){
							for(Map<String,Object> delProdInfo : orderInfo.getDelAddonProdList()){
								if(osvcCmpsInfo.getProdCmpsId().equals((String)delProdInfo.get("prodCmpsId"))){
									isDelProd = true;
									break;
								}
							}
						}
						
					}
				}
				
				if(isDelProd == false) continue; //대상 상품이 아니면 Continue
				osvcCmpsInfo.setTermDt(orderInfo.getOrderReqDt());
				orderProcessMapper.updateOSvcCmpsInfo(osvcCmpsInfo);
			}
			
			
			//기본상품 추가 정보
			Map<String,Object> addBasicProdInfo = orderInfo.getAddBasicProdList() == null ? null : orderInfo.getAddBasicProdList().get(0);
			if(addBasicProdInfo != null){

				//기본상품의 상품구성 작성
				OProdCmpsVO oprodBasicCmpsInfo = new OProdCmpsVO();
				oprodBasicCmpsInfo.setSoId(orderInfo.getSoId());
				oprodBasicCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				oprodBasicCmpsInfo.setProdCmpsId(basicProdCmpsId);
				oprodBasicCmpsInfo.setOrderId(orderInfo.getOrderId());
				oprodBasicCmpsInfo.setOrderStat(ORDER_STAT01);
				oprodBasicCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				oprodBasicCmpsInfo.setInactDttm("99991231235959");
				oprodBasicCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				oprodBasicCmpsInfo.setRcptId(orderInfo.getRcptId());
				oprodBasicCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				oprodBasicCmpsInfo.setRateEndDt("99991231");
				oprodBasicCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				oprodBasicCmpsInfo.setTermDt("99991231");
				oprodBasicCmpsInfo.setProdGrp((String)addBasicProdInfo.get("prodGrp"));
				oprodBasicCmpsInfo.setProdCmpsCl((String)addBasicProdInfo.get("prodTyp"));
				oprodBasicCmpsInfo.setProdCd((String)addBasicProdInfo.get("prodCd"));
				oprodBasicCmpsInfo.setUpProdCd("");
				oprodBasicCmpsInfo.setUpProdCmpsId("");
				oprodBasicCmpsInfo.setUpProdGrp("");
				oprodBasicCmpsInfo.setBefCmpsProdId(basicProdInUse.getProdCmpsId());
				oprodBasicCmpsInfo.setBefProdCd(basicProdInUse.getProdCd());
				oprodBasicCmpsInfo.setBefProdGrp(basicProdInUse.getProdGrp());
				oprodBasicCmpsInfo.setOrgId(orderInfo.getReqOrgId());
				oprodBasicCmpsInfo.setUsrId(orderInfo.getReqUsrId());
				oprodBasicCmpsInfo.setProdChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				oprodBasicCmpsInfo.setProdStatCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				oprodBasicCmpsInfo.setExtId("");
				oprodBasicCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				oprodBasicCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				oprodBasicCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				oprodBasicCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				int insertOrderProdCmps = orderProcessMapper.insertOrderProdCmps(oprodBasicCmpsInfo);
				
				
				//기본상품의 서비스구성작성
				OSvcCmpsVO osvcBasicCmpsInfo = new OSvcCmpsVO();
				osvcBasicCmpsInfo.setSoId(orderInfo.getSoId());
				osvcBasicCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				osvcBasicCmpsInfo.setOrderId(orderInfo.getOrderId());
				osvcBasicCmpsInfo.setOrderStat(ORDER_STAT01);
				osvcBasicCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				osvcBasicCmpsInfo.setSvcCmpsId(basicSvcCmpsId);
				osvcBasicCmpsInfo.setInactDttm("99991231235959");
				osvcBasicCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				osvcBasicCmpsInfo.setProdCmpsId(basicProdCmpsId);
				osvcBasicCmpsInfo.setRcptId(orderInfo.getRcptId());
				osvcBasicCmpsInfo.setProdCd((String)addBasicProdInfo.get("prodCd"));
				osvcBasicCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				osvcBasicCmpsInfo.setRateEndDt("99991231");
				osvcBasicCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				osvcBasicCmpsInfo.setTermDt("99991231");
				osvcBasicCmpsInfo.setSvcGrp((String)addBasicProdInfo.get("svcGrp"));
				osvcBasicCmpsInfo.setSvcCd((String)addBasicProdInfo.get("svcCd"));
				osvcBasicCmpsInfo.setSvcChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				osvcBasicCmpsInfo.setSvcStsCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				osvcBasicCmpsInfo.setExtId("");
				osvcBasicCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				osvcBasicCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				osvcBasicCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				osvcBasicCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				int insertOrderSvcCmps = orderProcessMapper.insertOrderSvcCmps(osvcBasicCmpsInfo);
				
				
			}
			
			//장비상품추가정보
			List<Map<String,Object>> addDeviceProdInfoList = orderInfo.getAddDeviceProdList() == null ? new ArrayList<Map<String,Object>>() :  orderInfo.getAddDeviceProdList();
			
			
			//장비상품 정보 작성
			for(Map<String,Object> prodInfo : addDeviceProdInfoList){
				String prodCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_PROD_CMPS_ID, "P", 10);
				prodInfo.put("prodCmpsId", prodCmpsId);
				
				OProdCmpsVO oprodAddCmpsInfo = new OProdCmpsVO();
				oprodAddCmpsInfo.setSoId(orderInfo.getSoId());
				oprodAddCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				oprodAddCmpsInfo.setProdCmpsId(prodCmpsId);
				oprodAddCmpsInfo.setOrderId(orderInfo.getOrderId());
				oprodAddCmpsInfo.setOrderStat(ORDER_STAT01);
				oprodAddCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				oprodAddCmpsInfo.setInactDttm("99991231235959");
				oprodAddCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				oprodAddCmpsInfo.setRcptId(orderInfo.getRcptId());
				oprodAddCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				oprodAddCmpsInfo.setRateEndDt("99991231");
				oprodAddCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				oprodAddCmpsInfo.setTermDt("99991231");
				oprodAddCmpsInfo.setProdGrp((String)prodInfo.get("prodGrp"));
				oprodAddCmpsInfo.setProdCmpsCl((String)prodInfo.get("prodTyp"));
				oprodAddCmpsInfo.setProdCd((String)prodInfo.get("prodCd"));
				oprodAddCmpsInfo.setUpProdCd((String)octrtInfo.getProdCd());
				oprodAddCmpsInfo.setUpProdCmpsId(octrtInfo.getProdCmpsId());
				oprodAddCmpsInfo.setUpProdGrp((String)octrtInfo.getProdGrp());
				oprodAddCmpsInfo.setBefCmpsProdId("");
				oprodAddCmpsInfo.setBefProdCd("");
				oprodAddCmpsInfo.setBefProdGrp("");
				oprodAddCmpsInfo.setOrgId(orderInfo.getReqOrgId());
				oprodAddCmpsInfo.setUsrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setProdChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				oprodAddCmpsInfo.setProdStatCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				oprodAddCmpsInfo.setExtId("");
				oprodAddCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				oprodAddCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderProdCmps(oprodAddCmpsInfo);
				
				String svcCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_SVC_CMPS_ID, "S", 10);
				prodInfo.put("svcCmpsId", svcCmpsId);
				
				OSvcCmpsVO osvcAddCmpsInfo = new OSvcCmpsVO();
				osvcAddCmpsInfo.setSoId(orderInfo.getSoId());
				osvcAddCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				osvcAddCmpsInfo.setOrderId(orderInfo.getOrderId());
				osvcAddCmpsInfo.setOrderStat(ORDER_STAT01);
				osvcAddCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				osvcAddCmpsInfo.setSvcCmpsId(svcCmpsId);
				osvcAddCmpsInfo.setInactDttm("99991231235959");
				osvcAddCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				osvcAddCmpsInfo.setProdCmpsId(prodCmpsId);
				osvcAddCmpsInfo.setRcptId(orderInfo.getRcptId());
				osvcAddCmpsInfo.setProdCd((String)prodInfo.get("prodCd"));
				osvcAddCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				osvcAddCmpsInfo.setRateEndDt("99991231");
				osvcAddCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				osvcAddCmpsInfo.setTermDt("99991231");
				osvcAddCmpsInfo.setSvcGrp((String)prodInfo.get("svcGrp"));
				osvcAddCmpsInfo.setSvcCd((String)prodInfo.get("svcCd"));
				osvcAddCmpsInfo.setSvcChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal()); //신규
				osvcAddCmpsInfo.setSvcStsCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				osvcAddCmpsInfo.setExtId("");
				osvcAddCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				osvcAddCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				osvcAddCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				osvcAddCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderSvcCmps(osvcAddCmpsInfo);
			}
			
			//부가상품추가정보
			List<Map<String,Object>> addAddonProdInfoList = orderInfo.getAddAddonProdList() == null ? new ArrayList<Map<String,Object>>() :  orderInfo.getAddAddonProdList();

			//부가상품 정보 작성
			for(Map<String,Object> prodInfo : addAddonProdInfoList){
				String prodCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_PROD_CMPS_ID, "P", 10);
				prodInfo.put("prodCmpsId", prodCmpsId);
				
				OProdCmpsVO oprodAddCmpsInfo = new OProdCmpsVO();
				oprodAddCmpsInfo.setSoId(orderInfo.getSoId());
				oprodAddCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				oprodAddCmpsInfo.setProdCmpsId(prodCmpsId);
				oprodAddCmpsInfo.setOrderId(orderInfo.getOrderId());
				oprodAddCmpsInfo.setOrderStat(ORDER_STAT01);
				oprodAddCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				oprodAddCmpsInfo.setInactDttm("99991231235959");
				oprodAddCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				oprodAddCmpsInfo.setRcptId(orderInfo.getRcptId());
				oprodAddCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				oprodAddCmpsInfo.setRateEndDt("99991231");
				oprodAddCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				oprodAddCmpsInfo.setTermDt("99991231");
				oprodAddCmpsInfo.setProdGrp((String)prodInfo.get("prodGrp"));
				oprodAddCmpsInfo.setProdCmpsCl((String)prodInfo.get("prodTyp"));
				oprodAddCmpsInfo.setProdCd((String)prodInfo.get("prodCd"));
				oprodAddCmpsInfo.setUpProdCd((String)octrtInfo.getProdCd());
				oprodAddCmpsInfo.setUpProdCmpsId(octrtInfo.getProdCmpsId());
				oprodAddCmpsInfo.setUpProdGrp((String)octrtInfo.getProdGrp());
				oprodAddCmpsInfo.setBefCmpsProdId("");
				oprodAddCmpsInfo.setBefProdCd("");
				oprodAddCmpsInfo.setBefProdGrp("");
				oprodAddCmpsInfo.setOrgId(orderInfo.getReqOrgId());
				oprodAddCmpsInfo.setUsrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setProdChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				oprodAddCmpsInfo.setProdStatCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				oprodAddCmpsInfo.setExtId("");
				oprodAddCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				oprodAddCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderProdCmps(oprodAddCmpsInfo);
				
				String svcCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_SVC_CMPS_ID, "S", 10);
				prodInfo.put("svcCmpsId", svcCmpsId);
				
				OSvcCmpsVO osvcAddCmpsInfo = new OSvcCmpsVO();
				osvcAddCmpsInfo.setSoId(orderInfo.getSoId());
				osvcAddCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				osvcAddCmpsInfo.setOrderId(orderInfo.getOrderId());
				osvcAddCmpsInfo.setOrderStat(ORDER_STAT01);
				osvcAddCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				osvcAddCmpsInfo.setSvcCmpsId(svcCmpsId);
				osvcAddCmpsInfo.setInactDttm("99991231235959");
				osvcAddCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				osvcAddCmpsInfo.setProdCmpsId(prodCmpsId);
				osvcAddCmpsInfo.setRcptId(orderInfo.getRcptId());
				osvcAddCmpsInfo.setProdCd((String)prodInfo.get("prodCd"));
				osvcAddCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				osvcAddCmpsInfo.setRateEndDt("99991231");
				osvcAddCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				osvcAddCmpsInfo.setTermDt("99991231");
				osvcAddCmpsInfo.setSvcGrp((String)prodInfo.get("svcGrp"));
				osvcAddCmpsInfo.setSvcCd((String)prodInfo.get("svcCd"));
				osvcAddCmpsInfo.setSvcChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal()); //신규
				osvcAddCmpsInfo.setSvcStsCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				osvcAddCmpsInfo.setExtId("");
				osvcAddCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				osvcAddCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				osvcAddCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				osvcAddCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderSvcCmps(osvcAddCmpsInfo);
			}
			
		}else if("C".equals(attr.getOrderAttrVal())){ //원부만 변경 처리
			OCtrtMastVO octrtInfo = orderProcessMapper.getOctrtMastInfo(orderInfo.getSoId(), orderInfo.getCtrtId(), orderInfo.getOrderId());
			
			//변경사유 상세 세팅
			octrtInfo.setCtrtChgResnCd(orderInfo.getChngDetailResn());
			
			//납부계정
			if(StringUtils.isNotEmpty(orderInfo.getPymAcntId())) octrtInfo.setPymAcntId(orderInfo.getPymAcntId());
			
			//설치주소
			if(StringUtils.isNotEmpty(orderInfo.getInstZipCode())){
				octrtInfo.setInstlZipCd(orderInfo.getInstZipCode()); 
				octrtInfo.setInstlBaseAddr(orderInfo.getInstBaseAddr());
				octrtInfo.setInstlAddrDtl(orderInfo.getInstAddrDtl());
				octrtInfo.setInstlCity(orderInfo.getInstCity());
				octrtInfo.setInstlState(orderInfo.getInstState());
			}
			octrtInfo.setRemark(orderInfo.getRemark());
			orderProcessMapper.updateOCtrtInfo(octrtInfo);
			
			if(StringUtils.isNotEmpty(octrtInfo.getExtId())){
				OCtrtMastExtVO octrtExtInfo = orderProcessMapper.getOCtrtMastExtInfo(octrtInfo.getExtId(), octrtInfo.getOrderId());
				//공정률
				if(StringUtils.isNotEmpty(orderInfo.getProcRate())) octrtExtInfo.setProcRate(orderInfo.getProcRate());
				
				//실사용자
				if(StringUtils.isNotEmpty(orderInfo.getRealCustNm())){
					octrtExtInfo.setRealCustNm(orderInfo.getRealCustNm());
					octrtExtInfo.setRealCustRel(orderInfo.getRealCustRel());
					octrtExtInfo.setRealCustTelNo(orderInfo.getRealCustTelNo());
				}
				orderProcessMapper.updateOCtrtExtInfo(octrtExtInfo);
			}
		}else if("D".equals(attr.getOrderAttrVal())){//신규 계약 생성 유형인 경우 추가
			
			//신청자정보조회
			Map<String,Object> rcptInfo = orderProcessMapper.getRcptReqCustInfo(orderInfo.getSoId(), orderInfo.getRcptId());
			
			if(StringUtils.isEmpty(orderInfo.getCtrtId()) || "0000000000".equals(orderInfo.getCtrtId())){
				orderInfo.setCtrtId(sequenceService.createNewSequence(Consts.SEQ_CODE.CM_CTRT_ID, "C", 10));
			}
			orderInfo.setOrderId(sequenceService.createNewSequence(Consts.SEQ_CODE.CM_ORDER_ID, 10));
			String basicProdCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_PROD_CMPS_ID, "P", 10);
			String basicSvcCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_SVC_CMPS_ID, "S", 10);
			orderInfo.getAddBasicProdList().get(0).put("prodCmpsId", basicProdCmpsId);
			orderInfo.getAddBasicProdList().get(0).put("svcCmpsId", basicSvcCmpsId);
			
			//기본상품 추가 정보
			Map<String,Object> addBasicProdInfo = orderInfo.getAddBasicProdList() == null ? null : orderInfo.getAddBasicProdList().get(0);
			//부가상품추가정보
			List<Map<String,Object>> addAddOnProdInfoList = orderInfo.getAddAddonProdList();
			//장비상품추가정보
			List<Map<String,Object>> addDeviceProdInfoList = orderInfo.getAddDeviceProdList();
			
			//계약오더원부작성
			OCtrtMastVO octrtMast = new OCtrtMastVO();
			octrtMast.setSoId(orderInfo.getSoId());
			octrtMast.setCtrtId(orderInfo.getCtrtId());
			octrtMast.setOrderId(orderInfo.getOrderId());
			octrtMast.setOrderStat(ORDER_STAT01);
			octrtMast.setOrderDt(orderInfo.getOrderReqDt());
			octrtMast.setInactDttm("99991231235959");
			octrtMast.setActDttm(orderInfo.getOrderReqDttm());
			octrtMast.setSvcTelNo(StringUtil.isEmpty(orderInfo.getAddSvcTelNo()) ? orderInfo.getCtrtId() : orderInfo.getAddSvcTelNo());
			octrtMast.setOrderTp(orderInfo.getOrderTp());
			octrtMast.setCtrtNm(orderInfo.getCtrtNm());
			octrtMast.setRateStrtDt(orderInfo.getOrderReqDt());
			octrtMast.setRateEndDt("99991231");
			octrtMast.setOpenDd(orderInfo.getOrderReqDt());
			octrtMast.setTermDt("99991231");
			octrtMast.setCtrtCl("1");
			octrtMast.setRcptId(orderInfo.getRcptId());
			octrtMast.setCustId(orderInfo.getCustId());
			octrtMast.setCustRel(rcptInfo.get("cust_rel") == null ? "" : (String)rcptInfo.get("cust_rel") );
			octrtMast.setAppnt(rcptInfo.get("req_nm") == null ? "" : (String)rcptInfo.get("req_nm") );
			octrtMast.setPymAcntId(orderInfo.getPymAcntId());
			octrtMast.setAcntCustRel("A"); //신규청약시 본인 고정 변경처리시 고려 필요
			octrtMast.setCustClDcAplyYn("N"); // 신분할인 없음
			octrtMast.setProdCmpsId(basicProdCmpsId);
			octrtMast.setProdCd((String)addBasicProdInfo.get("prodCd"));
			octrtMast.setProdGrp((String)addBasicProdInfo.get("prodGrp"));
			octrtMast.setInstlZipCd((String)orderInfo.getInstZipCode());
			octrtMast.setInstlBaseAddr((String)orderInfo.getInstBaseAddr());
			octrtMast.setInstlAddrDtl((String)orderInfo.getInstAddrDtl());
			octrtMast.setInstlCity((String)orderInfo.getInstCity());
			octrtMast.setInstlState((String)orderInfo.getInstState());
			octrtMast.setInstlAgnt("");
			octrtMast.setSalesOrgId(orderInfo.getReqOrgId());
			octrtMast.setSalesUsrId(orderInfo.getReqUsrId());
			octrtMast.setCtrtStat(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
			octrtMast.setDefResnCd(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
			octrtMast.setCtrtChgResnCd("");
			octrtMast.setSusHopeDd("");
			octrtMast.setMmtSusHopeDd("");
			octrtMast.setMmtSusCd(""); 
			octrtMast.setTermHopeDt("");
			octrtMast.setSvcStrtDd(orderInfo.getOrderReqDt());
			octrtMast.setSvcStrtHopeDt(orderInfo.getOrderReqDt());
			octrtMast.setJoinId("");
			octrtMast.setPromYn("");
			octrtMast.setPromId("");
			octrtMast.setOrgId(orderInfo.getReqOrgId());
			octrtMast.setUsrId(orderInfo.getReqUsrId());
			octrtMast.setServiceId(sequenceService.createNewSequence(Consts.SEQ_CODE.CM_SERVICE_ID, 10));
			octrtMast.setRcvTransCtrtId("");
			octrtMast.setSndTransCtrtId("");
			octrtMast.setTransCtrtYn("");
			octrtMast.setExtId("");
			octrtMast.setNpYn("N");
			octrtMast.setBefNp("");
			octrtMast.setOrgNp("");
			octrtMast.setFirstOrgId(orderInfo.getReqOrgId());
			octrtMast.setFirstUsrId(orderInfo.getReqUsrId());
			octrtMast.setRemark("");
			octrtMast.setIfSucYn("H");
			octrtMast.setRegrId(orderInfo.getReqUsrId());
			octrtMast.setRegDate(orderInfo.getOrderReqDate());
			octrtMast.setChgrId(orderInfo.getReqUsrId());
			octrtMast.setChgDate(orderInfo.getOrderReqDate());
			octrtMast.setCtrtNm(orderInfo.getCtrtNm());
			int insertOrderMastCnt = orderProcessMapper.insertOrderMast(octrtMast);
			
			
			
			//기본상품의 상품구성 작성
			OProdCmpsVO oprodBasicCmpsInfo = new OProdCmpsVO();
			oprodBasicCmpsInfo.setSoId(orderInfo.getSoId());
			oprodBasicCmpsInfo.setCtrtId(orderInfo.getCtrtId());
			oprodBasicCmpsInfo.setProdCmpsId(octrtMast.getProdCmpsId());
			oprodBasicCmpsInfo.setOrderId(orderInfo.getOrderId());
			oprodBasicCmpsInfo.setOrderStat(ORDER_STAT01);
			oprodBasicCmpsInfo.setOrderTp(orderInfo.getOrderTp());
			oprodBasicCmpsInfo.setInactDttm("99991231235959");
			oprodBasicCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
			oprodBasicCmpsInfo.setRcptId(orderInfo.getRcptId());
			oprodBasicCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
			oprodBasicCmpsInfo.setRateEndDt("99991231");
			oprodBasicCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
			oprodBasicCmpsInfo.setTermDt("99991231");
			oprodBasicCmpsInfo.setProdGrp((String)addBasicProdInfo.get("prodGrp"));
			oprodBasicCmpsInfo.setProdCmpsCl((String)addBasicProdInfo.get("prodTyp"));
			oprodBasicCmpsInfo.setProdCd((String)addBasicProdInfo.get("prodCd"));
			oprodBasicCmpsInfo.setUpProdCd("");
			oprodBasicCmpsInfo.setUpProdCmpsId("");
			oprodBasicCmpsInfo.setUpProdGrp("");
			oprodBasicCmpsInfo.setBefCmpsProdId("");
			oprodBasicCmpsInfo.setBefProdCd("");
			oprodBasicCmpsInfo.setBefProdGrp("");
			oprodBasicCmpsInfo.setOrgId(orderInfo.getReqOrgId());
			oprodBasicCmpsInfo.setUsrId(orderInfo.getReqUsrId());
			oprodBasicCmpsInfo.setProdChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
			oprodBasicCmpsInfo.setProdStatCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
			oprodBasicCmpsInfo.setExtId("");
			oprodBasicCmpsInfo.setRegrId(orderInfo.getReqUsrId());
			oprodBasicCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
			oprodBasicCmpsInfo.setChgrId(orderInfo.getReqUsrId());
			oprodBasicCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
			int insertOrderProdCmps = orderProcessMapper.insertOrderProdCmps(oprodBasicCmpsInfo);
			
			
			//기본상품의 서비스구성작성
			OSvcCmpsVO osvcBasicCmpsInfo = new OSvcCmpsVO();
			osvcBasicCmpsInfo.setSoId(orderInfo.getSoId());
			osvcBasicCmpsInfo.setCtrtId(orderInfo.getCtrtId());
			osvcBasicCmpsInfo.setOrderId(orderInfo.getOrderId());
			osvcBasicCmpsInfo.setOrderStat(ORDER_STAT01);
			osvcBasicCmpsInfo.setOrderTp(orderInfo.getOrderTp());
			osvcBasicCmpsInfo.setSvcCmpsId(basicSvcCmpsId);
			osvcBasicCmpsInfo.setInactDttm("99991231235959");
			osvcBasicCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
			osvcBasicCmpsInfo.setProdCmpsId(octrtMast.getProdCmpsId());
			osvcBasicCmpsInfo.setRcptId(orderInfo.getRcptId());
			osvcBasicCmpsInfo.setProdCd((String)addBasicProdInfo.get("prodCd"));
			osvcBasicCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
			osvcBasicCmpsInfo.setRateEndDt("99991231");
			osvcBasicCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
			osvcBasicCmpsInfo.setTermDt("99991231");
			osvcBasicCmpsInfo.setSvcGrp((String)addBasicProdInfo.get("svcGrp"));
			osvcBasicCmpsInfo.setSvcCd((String)addBasicProdInfo.get("svcCd"));
			osvcBasicCmpsInfo.setSvcChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
			osvcBasicCmpsInfo.setSvcStsCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
			osvcBasicCmpsInfo.setExtId("");
			osvcBasicCmpsInfo.setRegrId(orderInfo.getReqUsrId());
			osvcBasicCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
			osvcBasicCmpsInfo.setChgrId(orderInfo.getReqUsrId());
			osvcBasicCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
			int insertOrderSvcCmps = orderProcessMapper.insertOrderSvcCmps(osvcBasicCmpsInfo);
			
			//부가상품 정보 작성
			for(Map<String,Object> prodInfo : addAddOnProdInfoList){
				String prodCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_PROD_CMPS_ID, "P", 10);
				prodInfo.put("prodCmpsId", prodCmpsId);
				
				OProdCmpsVO oprodAddCmpsInfo = new OProdCmpsVO();
				oprodAddCmpsInfo.setSoId(orderInfo.getSoId());
				oprodAddCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				oprodAddCmpsInfo.setProdCmpsId(prodCmpsId);
				oprodAddCmpsInfo.setOrderId(orderInfo.getOrderId());
				oprodAddCmpsInfo.setOrderStat(ORDER_STAT01);
				oprodAddCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				oprodAddCmpsInfo.setInactDttm("99991231235959");
				oprodAddCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				oprodAddCmpsInfo.setRcptId(orderInfo.getRcptId());
				oprodAddCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				oprodAddCmpsInfo.setRateEndDt("99991231");
				oprodAddCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				oprodAddCmpsInfo.setTermDt("99991231");
				oprodAddCmpsInfo.setProdGrp((String)prodInfo.get("prodGrp"));
				oprodAddCmpsInfo.setProdCmpsCl((String)prodInfo.get("prodTyp"));
				oprodAddCmpsInfo.setProdCd((String)prodInfo.get("prodCd"));
				oprodAddCmpsInfo.setUpProdCd((String)addBasicProdInfo.get("prodCd"));
				oprodAddCmpsInfo.setUpProdCmpsId(octrtMast.getProdCmpsId());
				oprodAddCmpsInfo.setUpProdGrp((String)addBasicProdInfo.get("prodGrp"));
				oprodAddCmpsInfo.setBefCmpsProdId("");
				oprodAddCmpsInfo.setBefProdCd("");
				oprodAddCmpsInfo.setBefProdGrp("");
				oprodAddCmpsInfo.setOrgId(orderInfo.getReqOrgId());
				oprodAddCmpsInfo.setUsrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setProdChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				oprodAddCmpsInfo.setProdStatCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				oprodAddCmpsInfo.setExtId("");
				oprodAddCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				oprodAddCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderProdCmps(oprodAddCmpsInfo);
				
				String svcCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_SVC_CMPS_ID, "S", 10);
				prodInfo.put("svcCmpsId", svcCmpsId);
				OSvcCmpsVO osvcAddCmpsInfo = new OSvcCmpsVO();
				osvcAddCmpsInfo.setSoId(orderInfo.getSoId());
				osvcAddCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				osvcAddCmpsInfo.setOrderId(orderInfo.getOrderId());
				osvcAddCmpsInfo.setOrderStat(ORDER_STAT01);
				osvcAddCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				osvcAddCmpsInfo.setSvcCmpsId(svcCmpsId);
				osvcAddCmpsInfo.setInactDttm("99991231235959");
				osvcAddCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				osvcAddCmpsInfo.setProdCmpsId(prodCmpsId);
				osvcAddCmpsInfo.setRcptId(orderInfo.getRcptId());
				osvcAddCmpsInfo.setProdCd((String)prodInfo.get("prodCd"));
				osvcAddCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				osvcAddCmpsInfo.setRateEndDt("99991231");
				osvcAddCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				osvcAddCmpsInfo.setTermDt("99991231");
				osvcAddCmpsInfo.setSvcGrp((String)prodInfo.get("svcGrp"));
				osvcAddCmpsInfo.setSvcCd((String)prodInfo.get("svcCd"));
				osvcAddCmpsInfo.setSvcChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				osvcAddCmpsInfo.setSvcStsCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				osvcAddCmpsInfo.setExtId("");
				osvcAddCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				osvcAddCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				osvcAddCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				osvcAddCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderSvcCmps(osvcAddCmpsInfo);
				
			}
			
			//장비상품 정보 작성
			for(Map<String,Object> prodInfo : addDeviceProdInfoList){
				String prodCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_PROD_CMPS_ID, "P", 10);
				prodInfo.put("prodCmpsId", prodCmpsId);
				
				OProdCmpsVO oprodAddCmpsInfo = new OProdCmpsVO();
				oprodAddCmpsInfo.setSoId(orderInfo.getSoId());
				oprodAddCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				oprodAddCmpsInfo.setProdCmpsId(prodCmpsId);
				oprodAddCmpsInfo.setOrderId(orderInfo.getOrderId());
				oprodAddCmpsInfo.setOrderStat(ORDER_STAT01);
				oprodAddCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				oprodAddCmpsInfo.setInactDttm("99991231235959");
				oprodAddCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				oprodAddCmpsInfo.setRcptId(orderInfo.getRcptId());
				oprodAddCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				oprodAddCmpsInfo.setRateEndDt("99991231");
				oprodAddCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				oprodAddCmpsInfo.setTermDt("99991231");
				oprodAddCmpsInfo.setProdGrp((String)prodInfo.get("prodGrp"));
				oprodAddCmpsInfo.setProdCmpsCl((String)prodInfo.get("prodTyp"));
				oprodAddCmpsInfo.setProdCd((String)prodInfo.get("prodCd"));
				oprodAddCmpsInfo.setUpProdCd((String)addBasicProdInfo.get("prodCd"));
				oprodAddCmpsInfo.setUpProdCmpsId(octrtMast.getProdCmpsId());
				oprodAddCmpsInfo.setUpProdGrp((String)addBasicProdInfo.get("prodGrp"));
				oprodAddCmpsInfo.setBefCmpsProdId("");
				oprodAddCmpsInfo.setBefProdCd("");
				oprodAddCmpsInfo.setBefProdGrp("");
				oprodAddCmpsInfo.setOrgId(orderInfo.getReqOrgId());
				oprodAddCmpsInfo.setUsrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setProdChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal());
				oprodAddCmpsInfo.setProdStatCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				oprodAddCmpsInfo.setExtId("");
				oprodAddCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				oprodAddCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				oprodAddCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderProdCmps(oprodAddCmpsInfo);
				
				String svcCmpsId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_SVC_CMPS_ID, "S", 10);
				prodInfo.put("svcCmpsId", svcCmpsId);
				
				OSvcCmpsVO osvcAddCmpsInfo = new OSvcCmpsVO();
				osvcAddCmpsInfo.setSoId(orderInfo.getSoId());
				osvcAddCmpsInfo.setCtrtId(orderInfo.getCtrtId());
				osvcAddCmpsInfo.setOrderId(orderInfo.getOrderId());
				osvcAddCmpsInfo.setOrderStat(ORDER_STAT01);
				osvcAddCmpsInfo.setOrderTp(orderInfo.getOrderTp());
				osvcAddCmpsInfo.setSvcCmpsId(svcCmpsId);
				osvcAddCmpsInfo.setInactDttm("99991231235959");
				osvcAddCmpsInfo.setActDttm(orderInfo.getOrderReqDttm());
				osvcAddCmpsInfo.setProdCmpsId(prodCmpsId);
				osvcAddCmpsInfo.setRcptId(orderInfo.getRcptId());
				osvcAddCmpsInfo.setProdCd((String)prodInfo.get("prodCd"));
				osvcAddCmpsInfo.setRateStrtDt(orderInfo.getOrderReqDt());
				osvcAddCmpsInfo.setRateEndDt("99991231");
				osvcAddCmpsInfo.setOpenDd(orderInfo.getOrderReqDt());
				osvcAddCmpsInfo.setTermDt("99991231");
				osvcAddCmpsInfo.setSvcGrp((String)prodInfo.get("svcGrp"));
				osvcAddCmpsInfo.setSvcCd((String)prodInfo.get("svcCd"));
				osvcAddCmpsInfo.setSvcChgCl(orderMastInfo.getOrderAttrMap().get("OA00000006").getOrderAttrVal()); //신규
				osvcAddCmpsInfo.setSvcStsCd(orderMastInfo.getOrderAttrMap().get("OA00000005").getOrderAttrVal());
				osvcAddCmpsInfo.setExtId("");
				osvcAddCmpsInfo.setRegrId(orderInfo.getReqUsrId());
				osvcAddCmpsInfo.setRegDate(orderInfo.getOrderReqDate());
				osvcAddCmpsInfo.setChgrId(orderInfo.getReqUsrId());
				osvcAddCmpsInfo.setChgDate(orderInfo.getOrderReqDate());
				orderProcessMapper.insertOrderSvcCmps(osvcAddCmpsInfo);
			}
		}
		
	}
}