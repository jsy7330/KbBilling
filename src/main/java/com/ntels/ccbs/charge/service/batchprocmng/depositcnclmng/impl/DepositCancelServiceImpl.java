package com.ntels.ccbs.charge.service.batchprocmng.depositcnclmng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.depositcnclmng.DepositCancelVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.depositcnclmng.DepositCancelMapper;
import com.ntels.ccbs.charge.service.batchprocmng.depositcnclmng.DepositCancelService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.StringUtil;

@Service
public class DepositCancelServiceImpl implements DepositCancelService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DepositCancelMapper depositCancelMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	/**
	 * 입금 취소를 처리한다.
	 * @param dpstSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processDepositCancel(String dpstSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception {
		int resultFlag = 1;
	
		logger.debug("====================================");
		logger.debug("  getDepositCnclList Process...  ");
		logger.debug("====================================");
		
		// 입금 취소 처리 대상 입금내역을 조회한다.
		DepositVO depositInfo = new DepositVO();
		
		depositInfo = depositCancelMapper.getDepositCnclList(dpstSeqNo);
		
		String dpstDt 		= depositInfo.getDpstDt();
		String trnsDt 		= depositInfo.getTrnsDt();
		String dpstProcDt	= depositInfo.getDpstProcDt();
		String dpstCl 		= depositInfo.getDpstCl();
		String ambgOccSeqNo	= depositInfo.getAmbgOccSeqNo();
		
		// ERP 전송여부를 CHECK 한다.
		logger.debug("====================================");
		logger.debug("  getDepositErpSndCheck Process...  ");
		logger.debug("====================================");
		int erpSndChk = depositCancelMapper.getDepositErpSndCheck(trnsDt);
		
		if(!StringUtil.isEmpty(ambgOccSeqNo)) {
			// 불명금 ERP 전송여부를 CHECK 한다.
			logger.debug("====================================");
			logger.debug("  getAmbgErpSndCheck Process...  ");
			logger.debug("====================================");
			int ambgErpSndChk = depositCancelMapper.getAmbgErpSndCheck(ambgOccSeqNo);
			
			if(ambgErpSndChk > 0) {				
				resultFlag = -1;
				logger.error("FAIL CHECK AMBG ERP SEND");
				logger.error("ERP 전송이 완료된 불명금 내역 입금취소 불가");
				throw new ServiceException("MSG.M15.MSG00145"); //MSG.M15.MSG00145=ERP 전송이 완료된 불명금 내역은 입금취소 불가합니다.
			} else {
				// 불명금 발생내역을 취소 처리한다.
				logger.debug("====================================");
				logger.debug("  updateAmbgOccCncl Process...  ");
				logger.debug("====================================");
			
				AmbgOccVO ambgOccVO = new AmbgOccVO();
			
				ambgOccVO.setCnclYn("Y");
				ambgOccVO.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
				ambgOccVO.setRegrId(workId);
				ambgOccVO.setRegDate(DateUtil.sysdate());
				ambgOccVO.setDpstTpSeqNo(dpstSeqNo);
			
				int updCnt1 = depositCancelMapper.updateAmbgOccCncl(ambgOccVO);
			
				if(updCnt1 < 0) {
					resultFlag = -1;
					logger.error("FAIL UPDATE TBLPY_AMBG_OCC");
					logger.error("불명금 발생내역 취소 처리 실패");					
					throw new Exception("FAIL UPDATE TBLPY_AMBG_OCC");
				}
			}
		}
		
		// 보증금 발생내역을 취소 처리한다. - 수납취소에서 처리하는 것으로 변경
		/*logger.debug("====================================");
		logger.debug("  updateAssrOccCncl Process...  ");
		logger.debug("====================================");
		
		AssrOccVO assrOccVO = new AssrOccVO();
		
		assrOccVO.setCnclYn("Y");
		assrOccVO.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		assrOccVO.setRegrId(workId);
		assrOccVO.setRegDate(DateUtil.sysdate());
		assrOccVO.setDpstTpSeqNo(dpstSeqNo);
		
		int updCnt2 = depositCancelMapper.updateAssrOccCncl(assrOccVO);
		
		if(updCnt2 < 0) {
			resultFlag = -1;
			logger.error("FAIL UPDATE TBLPY_ASSR_OCC");
			logger.error("보증금 발생내역 취소 처리 실패");			
			throw new Exception("FAIL UPDATE TBLPY_ASSR_OCC");
		}*/
		
		// 선수금 발생내역을 취소 처리한다.
		logger.debug("======================================");
		logger.debug("  updatePrepayOccCnclInfo Process...  ");
		logger.debug("======================================");
				
		PrepayOccVO prepayOccVO = new PrepayOccVO();
				
		prepayOccVO.setCnclYn("Y");
		prepayOccVO.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		prepayOccVO.setCnclResn(cnclResnTxt);
		prepayOccVO.setRegrId(workId);
		prepayOccVO.setRegDate(DateUtil.sysdate());
		prepayOccVO.setDpstSeqNo(dpstSeqNo);
		prepayOccVO.setPrepayOccTgtSeqNo(ambgOccSeqNo);
				
		int updCnt3 = depositCancelMapper.updatePrepayOccCncl(prepayOccVO);
				
		if(updCnt3 < 0) {
			resultFlag = -1;
			logger.error("FAIL UPDATE TBLPY_PREPAY_OCC");
			logger.error("선수금 발생내역 취소 처리 실패");			
			throw new Exception("FAIL UPDATE TBLPY_PREPAY_OCC");
		}
		
		// ERP로 전송된 입금내역에 한해서 선수금을 불명금으로 발생시킨다. - 신용카드는 제외
		String newAmbgOccSeqNo = "";
		String newAmbgTgtYn = "N";
		if(erpSndChk > 0 && !"04".equals(dpstCl)) {
			// 입금 취소내역을 불명금으로 발생 시킨다.
			logger.debug("=======================================");
			logger.debug("  insertAmbgOccForDpstCncl Process...  ");
			logger.debug("=======================================");
		
			AmbgOccVO ambgOccInfo = new AmbgOccVO();
		
			newAmbgOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_AMBG_NO, 10);
			ambgOccInfo.setAmbgOccSeqNo(newAmbgOccSeqNo);
			ambgOccInfo.setAmbgOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			ambgOccInfo.setAmbgOccTp("02");
			ambgOccInfo.setAmbgOccResn("03");
			ambgOccInfo.setAmbgProcStat("01");
			ambgOccInfo.setTransCmplYn("N");
			ambgOccInfo.setCnclYn("N");
			ambgOccInfo.setCnclDttm("");
			ambgOccInfo.setTransDt("");
			ambgOccInfo.setMngBrOrgId("");
			ambgOccInfo.setErpSndYn("N");
			ambgOccInfo.setDpstTpSeqNo(dpstSeqNo);
			ambgOccInfo.setInptMenuId(inptMenuId);
			ambgOccInfo.setRegrId(workId);
			ambgOccInfo.setRegDate(DateUtil.sysdate());
			newAmbgTgtYn = "Y";
		
			int insCnt1 = depositCancelMapper.insertAmbgOccForDpstCncl(ambgOccInfo);
		
			if(insCnt1 < 0) {
				resultFlag = -1;
				logger.error("FAIL INSERT TBLPY_AMBG_OCC FOR DPST CNCL");
				logger.error("입금 취소내역 불명금 발생 실패");				
				throw new Exception("FAIL INSERT TBLPY_AMBG_OCC FOR DPST CNCL");
			}
		}
				
		// 입금 취소내역을 등록한다.
		logger.debug("====================================");
		logger.debug("  insertDepositCncl Process...  ");
		logger.debug("====================================");
		
		DepositCancelVO depositCancelVO = new DepositCancelVO();

		depositCancelVO.setDpstSeqNo(dpstSeqNo);
		depositCancelVO.setAmbgTgtYn(newAmbgTgtYn);
		depositCancelVO.setCnclDt(DateUtil.getDateStringYYYYMMDD(0));
		depositCancelVO.setCnclEmpId(workId);
		depositCancelVO.setCnclResn(cnclResnTxt);
		depositCancelVO.setCashRcptIssYn("N");
		depositCancelVO.setErpSndYn("N");
		depositCancelVO.setInptMenuId(inptMenuId);
		depositCancelVO.setRegrId(workId);
		depositCancelVO.setRegDate(DateUtil.sysdate());
		
		int insCnt2 = depositCancelMapper.insertDepositCncl(depositCancelVO);
		
		if(insCnt2 < 0) {
			resultFlag = -1;
			logger.error("FAIL INSERT TBLPY_DPST_CNCL");
			logger.error("입금 취소내역 생성 실패");			
			throw new Exception("FAIL INSERT TBLPY_DPST_CNCL");
		}
		
		// 입금내역을 취소 처리한다.
		logger.debug("====================================");
		logger.debug("  updateDepositCncl Process...  ");
		logger.debug("====================================");
				
		DepositVO depositVO = new DepositVO();

		depositVO.setDpstSeqNo(dpstSeqNo);
		depositVO.setAmbgOccSeqNo(newAmbgOccSeqNo);
		depositVO.setAmbgTgtYn(newAmbgTgtYn);
		depositVO.setCnclYn("Y");
		depositVO.setRegrId(workId);
		depositVO.setRegDate(DateUtil.sysdate());
				
		int updCnt4 = depositCancelMapper.updateDepositCncl(depositVO);
				
		if(updCnt4 < 0) {
			resultFlag = -1;
			logger.error("FAIL UPDATE TBLPY_DPST");
			logger.error("입금내역 취소 처리 실패");			
			throw new Exception("FAIL UPDATE TBLPY_DPST");
		}
		
		return resultFlag;
	}
}
