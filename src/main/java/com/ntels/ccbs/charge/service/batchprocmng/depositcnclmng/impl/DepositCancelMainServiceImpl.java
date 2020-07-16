package com.ntels.ccbs.charge.service.batchprocmng.depositcnclmng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.mapper.batchprocmng.depositcnclmng.DepositCancelMapper;
import com.ntels.ccbs.charge.service.batchprocmng.depositcnclmng.DepositCancelMainService;
import com.ntels.ccbs.charge.service.batchprocmng.depositcnclmng.DepositCancelService;

@Service
public class DepositCancelMainServiceImpl implements DepositCancelMainService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private DepositCancelMapper depositCanclMapper;
	
	@Autowired
	private DepositCancelService depositCancelService;
	
	/**
	 * 입금취소 가능여부를 CHECK 한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int getDepositCancelAbleCheck(String dpstSeqNo) {
		int returnFlag = 1;
		
//		// 수납 처리여부를 CHECK 한다.
//		if(depositCanclMapper.getReceiptCheckCnt(dpstSeqNo) > 0) {
//			returnFlag = -1;
//		}
//		
//		// 선수금, 불명금, 보증금 대체건이 있는지 CHECK 한다.
//		if(depositCanclMapper.getTransCheckAmt(dpstSeqNo) > 0) {
//			returnFlag = -1;
//		}
		// 선수금, 불명금, 보증금 대체건이 있는지 CHECK 한다.
		if(depositCanclMapper.getTransCheckAmt(dpstSeqNo) > 0) {
			returnFlag = -1;
		} else {
		    // 입급내역 체크 하지않고 수납내역만 체크하도록 수정 [2018.08.30] 진익상
			//if(depositCanclMapper.getReceiptCheckCnt(dpstSeqNo) > 0) {
		    // 수납 처리여부를 CHECK 한다.
			if(depositCanclMapper.getReceiptCheckCntSub(dpstSeqNo) > 0) {
				returnFlag = -1;
			}
			//}
		}

		return returnFlag;
	}
	
	/**
	 * 입급취소를 처리한다.
	 * @param dpstSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processDepositCancelMain(String dpstSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception {
		int returnFlag = 1;
		
		logger.debug("========================================");
		logger.debug(" processDepositCancelMain Start...  ");
		logger.debug("========================================");
		
		returnFlag = depositCancelService.processDepositCancel(dpstSeqNo, cnclResnTxt, inptMenuId, workId);
		
		logger.debug("========================================");
		logger.debug(" processDepositCancelMain End...  ");
		logger.debug("========================================");
		
		return returnFlag;
	}
	
}
