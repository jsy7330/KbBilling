package com.ntels.ccbs.customer.service.contract.work.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderRequestInfoVO;
import com.ntels.ccbs.customer.mapper.contract.contract.OrderProcessMapper;
import com.ntels.ccbs.customer.mapper.contract.work.WorkManagementMapper;
import com.ntels.ccbs.customer.service.contract.contract.OrderManagementService;
import com.ntels.ccbs.customer.service.contract.contract.OrderProcessService;
import com.ntels.ccbs.customer.service.contract.work.WorkManagementService;
import com.ntels.ccbs.system.service.common.service.SequenceService;


@Service
public class WorkManagementServiceImpl implements WorkManagementService{
	
	@Autowired
	private WorkManagementMapper workManagementMapper;
	
	@Autowired
	private OrderProcessMapper orderProcessMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private OrderManagementService orderManagementService;
	
	@Autowired
	private OrderProcessService[] orderProcessService;

	@Override
	public Map<String, Object> getWorkStatistics(String soId,
			List<Map<String, Object>> soAuthList) {
		
		return workManagementMapper.getWorkStatistics(soId, soAuthList) ;
	}

	@Override
	public Map<String, Object> getWorkList(String soId,
	        List<Map<String, Object>> soAuthList, String workStartDate,
	        String workEndDate, String orderTp, String workStat, String custId,
	        String workUserId, String sidx, String sord, int page, int rows, String today, String lng) {
		Map<String,Object> workInfo = new HashMap<String,Object>();
		Integer totalCount = workManagementMapper.getWorkListTotalCnt(soId,
				soAuthList,
				workStartDate,
				workEndDate,
				orderTp,
				workStat,
				custId,
				workUserId);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			workInfo.put("workList", new ArrayList<Map<String,Object>>());
			workInfo.put("totalCount", totalCount);
			workInfo.put("totalPages", new Integer(0));
			workInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<Map<String,Object>> workGrpList = workManagementMapper.getWorkList(soId,
				soAuthList,
				workStartDate,
				workEndDate,
				orderTp,
				workStat,
				custId,
				workUserId,
				sidx,
				sord,
				start,
				end,
				today,
				lng);
			
			
			workInfo.put("workList", workGrpList);
			workInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			workInfo.put("totalPages", totalPages);
			workInfo.put("page", new Integer(page));
		}
		
		return workInfo;
	}

	@Override
	public Map<String, Object> getWorkInfo(String wrkId, String orderId,
	        String lng, String today) {
		return workManagementMapper.getWorkInfo(wrkId, orderId, lng, today);
	}

	@Override
	public List<Map<String, Object>> getWorkHistList(String orderId,
	        String today, String lng) {
		return workManagementMapper.getWorkHistList(orderId, today, lng);
	}

	@Override
	public String getWorkAuthR(String orderId, String userId) {
		int count = workManagementMapper.checkWorkAuthR(orderId, userId);
		
		return count == 0 ? "N" : "Y";
	}

	@Override
	public String getWorkAuthU(String wrkId, String orderId, String userId) {
		int count = workManagementMapper.checkWorkDftUser(wrkId, orderId, userId);
		if(count == 0){
			return "N";
		}
		List<Map<String,Object>> workList = workManagementMapper.getWorkResultList(wrkId, orderId);
		boolean isPreviousWorkCmpl = true;
		for(Map<String,Object> work : workList){
			if(!"04".equals((String)work.get("WRK_STAT"))){
				isPreviousWorkCmpl = false;
				break;
			}
		}
		
		if(isPreviousWorkCmpl){
			int workCmplCheck = workManagementMapper.checkWorkCmpl(wrkId, orderId);
			if(workCmplCheck == 0){
				return "Y"; 
			}else{
				return "N";
			}
			
		}else{
			return "N";
		}
	}

	@Override
	public String getWorkAuthC(String wrkId, String orderId, String userId) {
		int count = workManagementMapper.checkWorkDftUser(wrkId, orderId, userId);
		if(count == 0){
			return "N"; //작업 담당자가 아님
		}
		
		int firstWorkCheck = workManagementMapper.checkHasPreviousWork(wrkId, orderId);
		if(firstWorkCheck == 0){ //최초 담당자의 경우
			int workCmplCheck = workManagementMapper.checkWorkCmpl(wrkId, orderId);
			if(workCmplCheck == 0){
				return "Y"; //최초 담당자이면서 작업 완료 전 상태
			}
		}
		
		int moreWorkCnt = workManagementMapper.checkHasMoreWork(wrkId, orderId);
		if(moreWorkCnt > 0){
			return "N"; //최종 처리자가 아님
		}else{
			int workCmplCheck = workManagementMapper.checkWorkCmpl(wrkId, orderId);
			if(workCmplCheck == 0){
				return "Y"; //최종 처리자이면서 작업 완료 전 상태
			}else{
				return "N";
			}
		}
	}

	@Override
	public void updateWorkReceipt(String wrkId, String orderId, String comment, String usrId,
	        String lng, String today, Date todayDateType) {
		//원부 Update
		workManagementMapper.updateWorkInfo(wrkId, orderId, "01",  usrId, today, todayDateType );
		//히스토리 Insert
		String wrkHistSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_HIST_SEQ_ID, 10);
		workManagementMapper.insertWrkHist(wrkHistSeq, orderId, wrkId, comment, "01", usrId, todayDateType);
	}
	
	@Override
	public void updateWorkProcess(String wrkId, String orderId, String comment, String usrId,
	        String lng, String today, Date todayDateType) {
		//원부 Update
		workManagementMapper.updateWorkInfo(wrkId, orderId, "02",  usrId, today, todayDateType );
		//히스토리 Insert
		String wrkHistSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_HIST_SEQ_ID, 10);
		workManagementMapper.insertWrkHist(wrkHistSeq, orderId, wrkId, comment, "02", usrId, todayDateType);
	}

	@Override
	public void updateWorkCancel(String wrkId, String orderId, String rcptId, String comment,
	        String usrId, String orgId, String lng, String today, Date todayDateType) {
		List<Map<String,Object>> previousWorkList = workManagementMapper.getPreviousWorkList(wrkId, orderId);
		for(Map<String,Object> previousWorkInfo : previousWorkList){
			//원부 Update
			workManagementMapper.updateWorkInfo((String)previousWorkInfo.get("WRK_ID"), orderId, "03",  usrId, today, todayDateType );
			//히스토리 Insert
			String previousSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_HIST_SEQ_ID, 10);
			workManagementMapper.insertWrkHist(previousSeq, orderId, (String)previousWorkInfo.get("WRK_ID"), comment, "03", usrId, todayDateType);
		}
		
		//원부 Update
		workManagementMapper.updateWorkInfo(wrkId, orderId, "03",  usrId, today, todayDateType );
		//히스토리 Insert
		String wrkHistSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_HIST_SEQ_ID, 10);
		workManagementMapper.insertWrkHist(wrkHistSeq, orderId, wrkId, comment, "03", usrId, todayDateType);
		
		List<Map<String,Object>> remainWorkList = workManagementMapper.getRemainWorkList(wrkId, orderId);
		for(Map<String,Object> remainWorkInfo : remainWorkList){
			//원부 Update
			workManagementMapper.updateWorkInfo((String)remainWorkInfo.get("WRK_ID"), orderId, "03",  usrId, today, todayDateType );
			//히스토리 Insert
			String remainSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_HIST_SEQ_ID, 10);
			workManagementMapper.insertWrkHist(remainSeq, orderId, (String)remainWorkInfo.get("WRK_ID"), comment, "03", usrId, todayDateType);
		}
		
		//오더 취소 처리
		String nowDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		Date nowDate = DateUtil.sysdate();
		
		OrderCommonVO orderCommonInfo = orderManagementService.getOrderCommonInfo("","","","",rcptId, lng, today); //임시처리
		
		orderProcessMapper.updateStatusOCtrtMast(orderCommonInfo.getSoId(), orderCommonInfo.getCtrtId(), orderCommonInfo.getOrderId(), "01", usrId, todayDateType);
		
		OrderRequestInfoVO orderReqInfo = new OrderRequestInfoVO();
		orderReqInfo.setSoId(orderCommonInfo.getSoId());
		orderReqInfo.setCustId(orderCommonInfo.getCustId());
		orderReqInfo.setCtrtId(orderCommonInfo.getCtrtId());
		orderReqInfo.setRcptId(orderCommonInfo.getRcptId());
		orderReqInfo.setOrderId(orderCommonInfo.getOrderId());
		orderReqInfo.setOrderCd(orderCommonInfo.getOrderCd());
		orderReqInfo.setOrderTp(orderCommonInfo.getOrderTp());
		orderReqInfo.setOrderStat(orderCommonInfo.getOrderStat());
		orderReqInfo.setCnslMstCl(orderCommonInfo.getCnslMstCl());
		orderReqInfo.setCnslMidCl(orderCommonInfo.getCnslMidCl());
		orderReqInfo.setCnslSlvCl(orderCommonInfo.getCnslSlvCl());
		orderReqInfo.setBasicSvcCd(orderCommonInfo.getBasicSvcCd());
		orderReqInfo.setCustTp(orderCommonInfo.getCustTp());
		orderReqInfo.setReqUsrId(usrId);
		orderReqInfo.setReqOrgId(orgId);
		orderReqInfo.setOrderReqDttm(nowDttm);
		orderReqInfo.setOrderReqDt(today);
		orderReqInfo.setLng(lng);
		orderReqInfo.setOrderReqDate(nowDate);
		
		
		OrderRequestInfoVO orderInfo = null;
		
		for (int index = 0; orderInfo == null && index < orderProcessService.length; index++) {
            try {
            	orderInfo = orderProcessService[index].cancelOrder(orderReqInfo);
            } catch (Exception e) {
            	throw e;
            }
        }
	}
	
	
	@Override
	public void updateWorkCmpl(String wrkId, String orderId, String rcptId, String comment,
	        String usrId, String orgId, String lng, String today, Date todayDateType, String wrkReqDt) {
		
		String lastWrkId = wrkId;
		//원부 Update
		workManagementMapper.updateWorkInfo(wrkId, orderId, "04",  usrId, today, todayDateType );
		//히스토리 Insert
		String wrkHistSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_HIST_SEQ_ID, 10);
		workManagementMapper.insertWrkHist(wrkHistSeq, orderId, wrkId, comment, "04", usrId, todayDateType);
		
		List<Map<String,Object>> remainWorkList = workManagementMapper.getRemainWorkList(wrkId, orderId);
		
		int remainCnt = 0;
		for(Map<String,Object> remainWorkInfo : remainWorkList){
			//원부 Update
			workManagementMapper.updateWorkInfoReqUsr((String)remainWorkInfo.get("WRK_ID"), orderId,  usrId, wrkReqDt, todayDateType );
			
			//자동완료처리
			if("Y".equals(remainWorkInfo.get("WRK_AUTO_CMT_YN")) && remainCnt == 0){
				
				//원부 Update
				workManagementMapper.updateWorkInfo((String)remainWorkInfo.get("WRK_ID"), orderId, "04",  usrId, today, todayDateType );
				//히스토리 Insert
				String cmplSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_HIST_SEQ_ID, 10);
				workManagementMapper.insertWrkHist(cmplSeq, orderId, (String)remainWorkInfo.get("WRK_ID"), comment, "04", usrId, todayDateType);
				
				lastWrkId = (String)remainWorkInfo.get("WRK_ID");
			}
			remainCnt++;
		}
		//후속작업 존재여부 체크
		int hasMoreWrkCnt = workManagementMapper.checkHasMoreWork(lastWrkId, orderId);
		if(hasMoreWrkCnt == 0){
			//오더 완료 처리
			String nowDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
			Date nowDate = DateUtil.sysdate();
			
			OrderCommonVO orderCommonInfo = orderManagementService.getOrderCommonInfo("","","","",rcptId, lng, today); //임시처리
			
			orderProcessMapper.updateStatusOCtrtMast(orderCommonInfo.getSoId(), orderCommonInfo.getCtrtId(), orderCommonInfo.getOrderId(), "01", usrId, todayDateType);
			
			OrderRequestInfoVO orderReqInfo = new OrderRequestInfoVO();
			orderReqInfo.setSoId(orderCommonInfo.getSoId());
			orderReqInfo.setCustId(orderCommonInfo.getCustId());
			orderReqInfo.setCtrtId(orderCommonInfo.getCtrtId());
			orderReqInfo.setRcptId(orderCommonInfo.getRcptId());
			orderReqInfo.setOrderId(orderCommonInfo.getOrderId());
			orderReqInfo.setOrderCd(orderCommonInfo.getOrderCd());
			orderReqInfo.setOrderTp(orderCommonInfo.getOrderTp());
			orderReqInfo.setOrderStat(orderCommonInfo.getOrderStat());
			orderReqInfo.setCnslMstCl(orderCommonInfo.getCnslMstCl());
			orderReqInfo.setCnslMidCl(orderCommonInfo.getCnslMidCl());
			orderReqInfo.setCnslSlvCl(orderCommonInfo.getCnslSlvCl());
			orderReqInfo.setBasicSvcCd(orderCommonInfo.getBasicSvcCd());
			orderReqInfo.setCustTp(orderCommonInfo.getCustTp());
			orderReqInfo.setReqUsrId(usrId);
			orderReqInfo.setReqOrgId(orgId);
			orderReqInfo.setOrderReqDttm(nowDttm);
			orderReqInfo.setOrderReqDt(today);
			orderReqInfo.setLng(lng);
			orderReqInfo.setOrderReqDate(nowDate);
			
			
			OrderRequestInfoVO orderInfo = null;
			
			for (int index = 0; orderInfo == null && index < orderProcessService.length; index++) {
	            try {
	            	orderInfo = orderProcessService[index].saveOrder(orderReqInfo);
	            } catch (Exception e) {
	            	throw e;
	            }
	        }	
		}
		
		
		
	}
}
