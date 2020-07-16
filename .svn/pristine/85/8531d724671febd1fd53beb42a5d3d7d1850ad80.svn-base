package com.ntels.ccbs.customer.service.customer.payment.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountHistVO;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountInfoVO;
import com.ntels.ccbs.customer.domain.customer.payment.VirAccountVO;
import com.ntels.ccbs.customer.mapper.customer.payment.PaymentAccountManagementMapper;
import com.ntels.ccbs.customer.service.customer.payment.PaymentAccountManagementService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;


/**
 * <PRE>
 * 1. ClassName: PaymentAccountManagementServiceImpl
 * 2. FileName : PaymentAccountManagementServiceImpl.java
 * 3. Package  : com.ntels.ccbs.customer.service.customer.payment.impl
 * 4. Comment  :
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 4. 28. 오후 5:06:21
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 4. 28.    : 신규개발
 * </PRE>
 */
@Service
public class PaymentAccountManagementServiceImpl implements PaymentAccountManagementService{
	
	@Autowired
	private PaymentAccountManagementMapper paymentAccountManagementMapper;

	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Override
	public List<Map<String, Object>> getCustInfoList(String soId,
	        String custName,
	        String custId,
	        List<Map<String, Object>> soAuthList) {
		return paymentAccountManagementMapper.getCustomerInfoList(soId, custName, custId, soAuthList);
	}

	@Override
	public Map<String,Object> getPymAcntInfoList(String soId,
	        String custId, 
	        String today,
	        String sidx,
	        String sord,
	        int page,
	        int rows,
	        String lng) {
		Map<String,Object> pymAcntInfo = new HashMap<String,Object>();
		Integer totalCount = paymentAccountManagementMapper.getPymAcntInfoListTotalCnt(soId, custId);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		

		if(totalCount.intValue() == 0){
			pymAcntInfo.put("pymAcntList", new ArrayList<PaymentAccountInfoVO>());
			pymAcntInfo.put("totalCount", totalCount);
			pymAcntInfo.put("totalPages", new Integer(0));
			pymAcntInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<PaymentAccountInfoVO> pymAcntList = paymentAccountManagementMapper.getPymAcntInfoList(soId, custId, today, sidx, sord, start, end, lng);
			pymAcntInfo.put("pymAcntList", pymAcntList);
			pymAcntInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			pymAcntInfo.put("totalPages", totalPages);
			pymAcntInfo.put("page", new Integer(page));
		}
		
		return pymAcntInfo;
	}

	@Override
	public void insertPymAcntInfo(PaymentAccountInfoVO pymInfo, String today,
	        String lng) throws ServiceException {
		
		//납부계정ID채번
		String pymAcntId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_PYM_ACNT_ID, "P", 10);
		pymInfo.setPymAcntId(pymAcntId);
		
		//납부계정원부 저장
		paymentAccountManagementMapper.insertPymAcntInfo(pymInfo);
		//납부계정히스토리 저장
		paymentAccountManagementMapper.insertPymAcntHistInfo(pymInfo, today);
		//가상계좌 기준정보 조회
		//List<CommonCodeVO> virCodeList = commonDataService.getCommonCodeListByRef1("CM00008", pymInfo.getSoId(), lng);
		
		//가상계좌 할당
//		for(CommonCodeVO code : virCodeList){
//			int maxCnt = 10;//총 10회 반복처리
//			
//			for(int i = 0; i < maxCnt; i++){
//				boolean result = updateVirAcnt(pymInfo.getSoId(), code.getRefCode2(), pymAcntId, pymInfo.getUsrId());
//				
//				if(!result){
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//					}
//				}else{
//					break;
//				}
//				
//			}
//		}
	}
	
	private boolean updateVirAcnt(String soId, String bnkCd, String pymAcntId, String usrId){
		boolean result = false;
		
 		List<VirAccountVO> virAcntVoList = paymentAccountManagementMapper.getVirAcntInfo(soId, bnkCd, "0", "9");
		for(VirAccountVO virAcntVo : virAcntVoList){
			try{
				virAcntVo.setVrAcntNoStat("02");
				virAcntVo.setPymAcntId(pymAcntId);
				virAcntVo.setQtaDt(DateUtil.getDateStringYYYYMMDD(0));
				virAcntVo.setChgrId(usrId);
				virAcntVo.setChgDate(DateUtil.sysdate());
				int cnt = paymentAccountManagementMapper.updateVirAcntInfo(virAcntVo);
				if(cnt == 1){
					result = true;
					break;
				}
			}catch(Exception e){
				continue;
			}
		}
		return result;
	}

	@Override
	public void updatePymCustInfo(PaymentAccountInfoVO pymInfo,
	        String today, String todayYYYYMMDD, String lng) throws ServiceException {
		//납부계정 변경분 세팅
		paymentAccountManagementMapper.updatePymAcntInfo(pymInfo);
		//변경 내용 조회
		PaymentAccountInfoVO updatedPymInfo = paymentAccountManagementMapper.getPymAcntInfo(pymInfo.getSoId(),pymInfo.getPymAcntId(),todayYYYYMMDD, lng);
		//납부계정히스토리 저장
		paymentAccountManagementMapper.insertPymAcntHistInfo(updatedPymInfo, today);
	}
	
	
	@Override
	public Map<String,Object> getPymAcntChngHistList(String soId,
	        String pymAcntId, 
	        String today,
	        String sidx,
	        String sord,
	        int page,
	        int rows,
	        String lng) {
		Map<String,Object> pymAcntChngHistInfo = new HashMap<String,Object>();
		Integer totalCount = paymentAccountManagementMapper.getPymAcntChngHistListTotalCnt(soId, pymAcntId);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			pymAcntChngHistInfo.put("pymAcntChngHistList", new ArrayList<PaymentAccountHistVO>());
			pymAcntChngHistInfo.put("totalCount", totalCount);
			pymAcntChngHistInfo.put("totalPages", new Integer(0));
			pymAcntChngHistInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<PaymentAccountHistVO> pymAcntList = paymentAccountManagementMapper.getPymAcntChngHistList(soId, pymAcntId, today, sidx, sord, start, end, lng);
			pymAcntChngHistInfo.put("pymAcntChngHistList", pymAcntList);
			pymAcntChngHistInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			pymAcntChngHistInfo.put("totalPages", totalPages);
			pymAcntChngHistInfo.put("page", new Integer(page));
		}
		
		return pymAcntChngHistInfo;
	}
	
	@Override
	public Map<String,Object> getVirAcntList(String soId,
	        String pymAcntId, 
	        String today,
	        String sidx,
	        String sord,
	        int page,
	        int rows,
	        String lng) {
		Map<String,Object> virAcntInfo = new HashMap<String,Object>();
		Integer totalCount = paymentAccountManagementMapper.getVirAcntListTotalCnt(soId, pymAcntId);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			virAcntInfo.put("virAcntList", new ArrayList<VirAccountVO>());
			virAcntInfo.put("totalCount", totalCount);
			virAcntInfo.put("totalPages", new Integer(0));
			virAcntInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<VirAccountVO> virAcntList = paymentAccountManagementMapper.getVirAcntList(soId, pymAcntId, today, sidx, sord, start, end, lng);
			virAcntInfo.put("virAcntList", virAcntList);
			virAcntInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			virAcntInfo.put("totalPages", totalPages);
			virAcntInfo.put("page", new Integer(page));
		}
		
		return virAcntInfo;
	}

	@Override
	public void processChngVirAcnt(String soId, 
			String pymAcntId,
			String vrBnkCd,
			String vrAcntNo,
	        String today, 
	        String lng, 
	        String usrId) {
		//기존 정보 변경 처리
		int updateCnt = paymentAccountManagementMapper.processChngVirAcnt(soId, pymAcntId, vrBnkCd, vrAcntNo, usrId, DateUtil.sysdate());
		//새로운 계좌 할당
		int maxCnt = 10;//총 10회 반복처리
		
		for(int i = 0; i < maxCnt; i++){
			boolean result = updateVirAcnt(soId, vrBnkCd, pymAcntId, usrId);
			if(!result){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}else{
				break;
			}
			
		}
		
		
	}
}
