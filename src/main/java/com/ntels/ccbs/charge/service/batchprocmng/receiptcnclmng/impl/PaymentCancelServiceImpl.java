package com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.ambgtransmng.AmbgTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.receiptcnclmng.PaymentCancelDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.receiptcnclmng.PaymentCancelVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.receiptcnclmng.PaymentCancelMapper;
import com.ntels.ccbs.charge.service.batchprocmng.billupdmng.BillMastService;
import com.ntels.ccbs.charge.service.batchprocmng.receiptcnclmng.PaymentCancelService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class PaymentCancelServiceImpl implements PaymentCancelService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private PaymentCancelMapper paymentCancelMapper;

	@Autowired
	private BillMastService billMastService;	
	
	/**
	 * 수납 취소를 처리한다.
	 * @param pymSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processReceiptCancel(String pymSeqNo, String cnclResnTxt, String inptMenuId, String workId) throws Exception {
		int resultFlag = 1;

		// 수납 상세내역을 조회한다.
		logger.debug("====================================");
		logger.debug("  getReceiptCnclList Process...  ");
		logger.debug("====================================");

		List<ReceiptDtlVO> rcptDtlList = new ArrayList<ReceiptDtlVO>();

		rcptDtlList = paymentCancelMapper.getReceiptCnclList(pymSeqNo);

		int loopCnt = rcptDtlList.size();
		String dpstSeqNo = "";
		String mngCnterOrgId = "";
		String pymAcntId = "";
		String payTp = "";
		String prepayOccSeqNo = "";
		double prepayBal = 0.0;

		for (int i = 0; i < loopCnt; i++) {
			ReceiptDtlVO rcptDtlInfo = new ReceiptDtlVO();

			rcptDtlInfo = rcptDtlList.get(i);

			// 입금내역 UPDATE 를 위한 입금번호 가져오기
			dpstSeqNo = rcptDtlInfo.getDpstSeqNo();
			mngCnterOrgId = rcptDtlInfo.getMngCnterOrgId();
			pymAcntId = rcptDtlInfo.getPymAcntId();
			payTp = rcptDtlInfo.getPayTp();
			prepayOccSeqNo = rcptDtlInfo.getPrepayOccSeqNo();
			prepayBal = rcptDtlInfo.getPrepayBal();

			String prepayTransSeqNo = rcptDtlInfo.getPrepayTransSeqNo();

			if (prepayTransSeqNo != null && prepayTransSeqNo.length() > 0) {
				// 선수 대체이력 취소 처리
				boolean prepayResult = processPrepayTransCancel(rcptDtlInfo, workId);

				if (prepayResult == false) {
					resultFlag = -1;
					throw new Exception("FAIL PROCESS PREPAY TRANS CANCEL");
				}
			}

			String ambgTransSeqNo = rcptDtlInfo.getAmbgTransSeqNo();

			if (ambgTransSeqNo != null && ambgTransSeqNo.length() > 0) {
				// 불명 대체이력 취소 처리
				boolean ambgResult = processAmbgTransCancel(rcptDtlInfo, workId);

				if (ambgResult == false) {
					resultFlag = -1;
					throw new Exception("FAIL PROCESS AMBG TRANS CANCEL");
				}
			}

			String assrTransSeqNo = rcptDtlInfo.getAssrTransSeqNo();

			if (assrTransSeqNo != null && assrTransSeqNo.length() > 0) {
				// 보증금 대체이력 취소 처리
				boolean assrResult = processAssrTransCancel(rcptDtlInfo, workId);

				if (assrResult == false) {
					resultFlag = -1;
					throw new Exception("FAIL PROCESS ASSR TRANS CANCEL");
				}
			}

			// 청구내역에 취소금액 반영
			boolean billResult = processChangeBillCnclAmt(pymSeqNo, rcptDtlInfo, workId);

			if (billResult == false) {
				resultFlag = -1;
				throw new Exception("FAIL PROCESS CHANGE BILL CANCEL AMT");
			}

			// 청구 TBLIV_BILL_MAST 수납금액, 최종수납일 반영
			billMastService.billMastUpdate(rcptDtlInfo.getSoId(), rcptDtlInfo.getBillYymm(), rcptDtlInfo.getBillSeqNo(), rcptDtlInfo.getPymAcntId(), workId);

			// 수납 취소 상세 내역 생성
			boolean dtlResult = insertReceiptCancelDtl(rcptDtlInfo, inptMenuId, workId);

			if (dtlResult == false) {
				resultFlag = -1;
				throw new Exception("FAIL INSERT RECEIPT CANCEL DTL");
			}

		}

		// 수납 취소 내역 생성
		boolean mstResult = insertReceiptCancel(pymSeqNo, cnclResnTxt, inptMenuId, workId);
		if (mstResult == false) {
			resultFlag = -1;
			throw new Exception("FAIL INSERT RECEIPT CANCEL");
		}

		// 보증금 발생 내역 취소 정보 반영 - 선수대체로 인해 발생한 보증금 취소
		if ("02".equals(payTp)) {
			boolean assrResult = updateAssrOccCancelInfo(pymSeqNo, workId);
			if (assrResult == false) {
				resultFlag = -1;
				throw new Exception("FAIL UPDATE ASSR OCC CANCEL INFO");
			}
		}

		// 불영금 대체 수납으로 발생한 선수금이 있는 건에 대한 수납취소 처리시 선수잔액만큼 불명금 잔액을 증가시킨다.
		if ("03".equals(payTp) && !"".equals(prepayOccSeqNo)) {
			boolean ambgUpdResult = updateAmbgOccPrepayAmt(prepayOccSeqNo, prepayBal, workId);

			if (ambgUpdResult == false) {
				resultFlag = -1;
				throw new Exception("FAIL UPDATE AMBG OCC PREPAY AMT");
			}
		}

		// 결제 타입이 정상입금수납과 보증금 대체 수납일 경우 취소금액 선수금 발생
		// [추후 확인 필요]-선수금, 불명금, 보증금 잔액 UPDATE 하는 대신 불명금 전체를 취소시키고 선수금을 발생시킨다???
		String prepayTgtYn = "N";
		String newPrepayOccSeqNo = "";

		if ("01".equals(payTp) || "04".equals(payTp)) {
			newPrepayOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_PRPY_NO, 10);

			boolean prepayResult = insertPrepayOcc(newPrepayOccSeqNo, pymAcntId, pymSeqNo, mngCnterOrgId, inptMenuId, workId);

			if (prepayResult == false) {
				resultFlag = -1;
				throw new Exception("FAIL INSERT TBLPY_PREPAY_OCC");
			} else {
				prepayTgtYn = "Y";
			}
		}

		// 수납 내역 취소 정보 반영
		boolean rcpResult = updateReceiptCancelInfo(pymSeqNo, newPrepayOccSeqNo, workId);

		if (rcpResult == false) {
			resultFlag = -1;
			throw new Exception("FAIL UPDATE RECEIPT CANCEL INFO");
		}

		// 결제 타입이 정상입금수납일 경우 입금 내역 취소 정보 반영
		if ("01".equals(payTp)) {
			boolean dpsResult = updateDepositCancelInfo(dpstSeqNo, workId);

			if (dpsResult == false) {
				resultFlag = -1;
				throw new Exception("FAIL UPDATE DEPOSIT CANCEL INFO");
			}
		}

		return resultFlag;
	}

	/**
	 * 선수대체 이력 취소, 선수 잔액 조정
	 * @param receiptDtlVO, workId
	 * @return 
	 */
	private boolean processPrepayTransCancel(ReceiptDtlVO receiptDtlVO, String workId) {
		boolean result = true;

		logger.debug("========================================");
		logger.debug("  processPrepayTransCancel Process...  ");
		logger.debug("========================================");

		PrepayTransHistVO prepayTransHistVO = new PrepayTransHistVO();
		prepayTransHistVO.setPrepayTransSeqNo(receiptDtlVO.getPrepayTransSeqNo());
		prepayTransHistVO.setCnclYn("Y");
		prepayTransHistVO.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		prepayTransHistVO.setRegrId(workId);
		prepayTransHistVO.setRegDate(DateUtil.sysdate());

		int updCnt1 = paymentCancelMapper.updatePrepayTransCncl(prepayTransHistVO);

		if (updCnt1 <= 0) {
			logger.debug("FAIL UPDATE TBLPY_PREPAY_TRANS_HIST");
			logger.debug("선수금 대체내역  취소 처리 실패");
			result = false;
		}

		// [추후 확인 필요]-선수금 잔액 UPDATE 하는 대신 선수금 전체를 취소시키고 선수금을 발생시킨다???
		PrepayOccVO prepayOccVO = new PrepayOccVO();
		prepayOccVO.setPrepayTransSeqNo(receiptDtlVO.getPrepayTransSeqNo());
		prepayOccVO.setPrepayTransAmt(receiptDtlVO.getRcptAmt());
		prepayOccVO.setRegrId(workId);
		prepayOccVO.setRegDate(DateUtil.sysdate());

		int updCnt2 = paymentCancelMapper.updatePrepayOccCnclAmt(prepayOccVO);

		if (updCnt2 <= 0) {
			logger.debug("FAIL UPDATE TBLPY_PREPAY_OCC");
			logger.debug("선수금 잔액  취소반영 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 불명대체 이력 취소, 불명 잔액 조정
	 * @param receiptDtlVO, workId
	 * @return 
	 */
	private boolean processAmbgTransCancel(ReceiptDtlVO receiptDtlVO, String workId) {
		boolean result = true;

		// 불명 대체이력 취소 처리
		logger.debug("========================================");
		logger.debug("  processAmbgTransCancel Process...  ");
		logger.debug("========================================");

		AmbgTransHistVO ambgTransHistVO = new AmbgTransHistVO();
		ambgTransHistVO.setAmbgTransSeqNo(receiptDtlVO.getAmbgTransSeqNo());
		ambgTransHistVO.setCnclYn("Y");
		ambgTransHistVO.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		ambgTransHistVO.setRegrId(workId);
		ambgTransHistVO.setRegDate(DateUtil.sysdate());

		int updCnt1 = paymentCancelMapper.updateAmbgTransCncl(ambgTransHistVO);

		if (updCnt1 <= 0) {
			logger.debug("FAIL UPDATE TBLPY_AMBG_TRANS_HIST");
			logger.debug("불명금 대체내역 취소 처리 실패");
			result = false;
		}

		// [추후 확인 필요]-불명금 잔액 UPDATE 하는 대신 불명금 전체를 취소시키고 선수금을 발생시킨다???
		AmbgOccVO ambgOccVO = new AmbgOccVO();
		ambgOccVO.setAmbgTransSeqNo(receiptDtlVO.getAmbgTransSeqNo());
		ambgOccVO.setAmbgTransAmt(receiptDtlVO.getRcptAmt());
		ambgOccVO.setRegrId(workId);
		ambgOccVO.setRegDate(DateUtil.sysdate());

		int updCnt2 = paymentCancelMapper.updateAmbgOccCnclAmt(ambgOccVO);

		if (updCnt2 <= 0) {
			logger.debug("FAIL UPDATE TBLPY_AMBG_OCC");
			logger.debug("불명금 잔액  취소반영 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 보증대체 이력 취소, 보증금 잔액 조정
	 * @param receiptDtlVO, workId
	 * @return 
	 */
	private boolean processAssrTransCancel(ReceiptDtlVO receiptDtlVO, String workId) {
		boolean result = true;

		// 불명 대체이력 취소 처리
		logger.debug("========================================");
		logger.debug("  processAssrTransCancel Process...  ");
		logger.debug("========================================");

		AssrTransHistVO assrTransHistVO = new AssrTransHistVO();
		assrTransHistVO.setAssrTransSeqNo(receiptDtlVO.getAssrTransSeqNo());
		assrTransHistVO.setCnclYn("Y");
		assrTransHistVO.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		assrTransHistVO.setRegrId(workId);
		assrTransHistVO.setRegDate(DateUtil.sysdate());

		int updCnt1 = paymentCancelMapper.updateAssrTransCncl(assrTransHistVO);

		if (updCnt1 <= 0) {
			logger.debug("FAIL UPDATE TBLPY_ASSR_TRANS_HIST");
			logger.debug("보증금 대체내역 취소 처리 실패");
			result = false;
		}

		//보증금 잔액을 원복하지 말고 취소된 금액만큼 선수금을 발생시키도록 한다.
		/*AssrOccVO assrOccVO = new AssrOccVO();
		assrOccVO.setAssrTransSeqNo(receiptDtlVO.getAssrTransSeqNo());
		assrOccVO.setAssrTransAmt(receiptDtlVO.getRcptAmt());
		assrOccVO.setRegrId(workId);
		assrOccVO.setRegDate(DateUtil.sysdate());
		
		int updCnt2 = paymentCancelMapper.updateAssrOccCnclAmt(assrOccVO);
		
		if(updCnt2 <= 0) {
			logger.debug("FAIL UPDATE TBLPY_ASSR_OCC");
			logger.debug("보증금 잔액  취소반영 실패");
			result = false;
		}*/

		return result;
	}

	/**
	 * 청구내역에 취소금액 반영
	 * @param pymSeqNo, receiptDtlVO, workId
	 * @return 
	 */
	private boolean processChangeBillCnclAmt(String pymSeqNo, ReceiptDtlVO receiptDtlVO, String workId) {
		boolean result = true;

		// 청구내역 취소금액 반영
		logger.debug("=======================================");
		logger.debug("  processChangeBillCnclAmt Process...  ");
		logger.debug("=======================================");

		BillListVO billListVO = new BillListVO();
		billListVO.setRcptAmt(receiptDtlVO.getRcptAmt());
		billListVO.setPymSeqNo(pymSeqNo);
		billListVO.setBillSeqNo(receiptDtlVO.getBillSeqNo());
		billListVO.setUseYymm(receiptDtlVO.getUseYymm());
		billListVO.setSvcCmpsId(receiptDtlVO.getSvcCmpsId());
		billListVO.setProdCmpsId(receiptDtlVO.getProdCmpsId());
		billListVO.setChrgItmCd(receiptDtlVO.getChrgItmCd());
		billListVO.setChgrId(workId);
		billListVO.setChgDate(DateUtil.sysdate());

		int updCnt = paymentCancelMapper.updateBillCnclAmt(billListVO);

		if (updCnt <= 0) {
			logger.debug("FAIL UPDATE TBLIV_BILL");
			logger.debug("청구 취소금액 반영 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 수납취소 상세내역 생성
	 * @param receiptDtlVO, inptMenuId, workId
	 * @return 
	 */
	private boolean insertReceiptCancelDtl(ReceiptDtlVO receiptDtlVO, String inptMenuId, String workId) {
		boolean result = true;

		logger.debug("=====================================");
		logger.debug("  insertReceiptCancelDtl Process...  ");
		logger.debug("=====================================");

		PaymentCancelDtlVO receiptCancelDtlVO = new PaymentCancelDtlVO();

		CUtil.copyObjectValue(receiptDtlVO, receiptCancelDtlVO);

		receiptCancelDtlVO.setInptMenuId(inptMenuId);
		receiptCancelDtlVO.setRegrId(workId);
		receiptCancelDtlVO.setRegDate(DateUtil.sysdate());

		int insCnt = paymentCancelMapper.insertReceiptCnclDtl(receiptCancelDtlVO);

		if (insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_RCPT_CNCL_DTL");
			logger.debug("수납취소 상세내역 생성 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 수납 취소내역 생성
	 * @param pymSeqNo, cnclResnTxt, inptMenuId, workId
	 * @return 
	 */
	private boolean insertReceiptCancel(String pymSeqNo, String cnclResnTxt, String inptMenuId, String workId) {
		boolean result = true;

		logger.debug("====================================");
		logger.debug("  insertReceiptCancel Process...  ");
		logger.debug("====================================");

		PaymentCancelVO receiptCancelVO = new PaymentCancelVO();
		receiptCancelVO.setPymSeqNo(pymSeqNo);
		receiptCancelVO.setCnclDt(DateUtil.getDateStringYYYYMMDD(0));
		receiptCancelVO.setCnclEmpId(workId);
		receiptCancelVO.setCnclResn(cnclResnTxt);
		receiptCancelVO.setInptMenuId(inptMenuId);
		receiptCancelVO.setRegrId(workId);
		receiptCancelVO.setRegDate(DateUtil.sysdate());

		int insCnt = paymentCancelMapper.insertReceiptCncl(receiptCancelVO);

		if (insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_RCPT_CNCL");
			logger.debug("수납 취소내역 생성 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 보증금 발생 내역에 취소정보 반영
	 * @param pymSeqNo, workId
	 * @return 
	 */
	private boolean updateAssrOccCancelInfo(String pymSeqNo, String workId) {
		boolean result = true;

		logger.debug("======================================");
		logger.debug("  updateAssrOccCancelInfo Process...  ");
		logger.debug("======================================");

		AssrOccVO assrOccVO = new AssrOccVO();

		assrOccVO.setCnclYn("Y");
		assrOccVO.setCnclDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		assrOccVO.setRegrId(workId);
		assrOccVO.setRegDate(DateUtil.sysdate());
		assrOccVO.setPymSeqNo(pymSeqNo);

		int updCnt = paymentCancelMapper.updateAssrOccCncl(assrOccVO);

		if (updCnt < 0) {
			logger.debug("FAIL UPDATE TBLPY_ASSR_OCC");
			logger.debug("보증금 발생내역 취소 정보 반영 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 불명금으로 인해 발생한 선수금 내역의 잔액만큼 불명금 잔액을 증가시킨다.
	 * @param prepayOccSeqNo, prepayBal, workId
	 * @return 
	 */
	private boolean updateAmbgOccPrepayAmt(String prepayOccSeqNo, double prepayBal, String workId) {
		boolean result = true;

		logger.debug("=====================================");
		logger.debug("  updateAmbgOccPrepayAmt Process...  ");
		logger.debug("=====================================");

		PrepayOccVO prepayOccVO = new PrepayOccVO();

		prepayOccVO.setPrepayOccSeqNo(prepayOccSeqNo);
		prepayOccVO.setPrepayBal(prepayBal);
		prepayOccVO.setRegrId(workId);
		prepayOccVO.setRegDate(DateUtil.sysdate());

		int updCnt = paymentCancelMapper.updateAmbgOccPrepayAmt(prepayOccVO);

		if (updCnt < 0) {
			logger.debug("FAIL UPDATE TBLPY_ASSR_OCC");
			logger.debug("보증금 발생내역 선수금 취소 잔액  반영 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 수납 취소 금액만큼 선수금 내역 생성
	 * @param prepayOccSeqNo, pymAcntId, pymSeqNo, inptMenuId, workId
	 * @return 
	 */
	private boolean insertPrepayOcc(String prepayOccSeqNo, String pymAcntId, String pymSeqNo, String mngCnterOrgId, String inptMenuId, String workId) {
		boolean result = true;

		logger.debug("====================================");
		logger.debug("  insertPrepayOcc Process...  ");
		logger.debug("====================================");

		PrepayOccVO prepayOccVO = new PrepayOccVO();
		prepayOccVO.setPrepayOccSeqNo(prepayOccSeqNo);
		prepayOccVO.setPymAcntId(pymAcntId);
		prepayOccVO.setPrepayOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		prepayOccVO.setPrepayOccTp("03"); // 선수발생타입 : 수납취소
		prepayOccVO.setPrepayOccResn("05"); // 선수발생사유 : 수납취소(고객요청)
		prepayOccVO.setPrepayOccTgtSeqNo(pymSeqNo);
		prepayOccVO.setPrepayProcStat("01"); // 선수처리상태 : 선수금발생
		prepayOccVO.setCnclYn("N");
		prepayOccVO.setCnclDttm("");
		prepayOccVO.setTransDt("");
		prepayOccVO.setMngCnterOrgId(mngCnterOrgId);
		prepayOccVO.setErpSndYn("N");
		prepayOccVO.setInptMenuId(inptMenuId);
		prepayOccVO.setRegrId(workId);
		prepayOccVO.setRegDate(DateUtil.sysdate());

		int insCnt = paymentCancelMapper.insertPrepayOcc(prepayOccVO);

		if (insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_PREPAY_OCC");
			logger.debug("수납 취소에 따른 선수금 내역 생성 실패");
			result = false;
		}

		return result;

	}

	/**
	 * 수납내역에 취소정보 반영
	 * @param pymSeqNo, workId
	 * @return 
	 */
	private boolean updateReceiptCancelInfo(String pymSeqNo, String prepayOccSeqNo, String workId) {
		boolean result = true;

		logger.debug("======================================");
		logger.debug("  updateReceiptCancelInfo Process...  ");
		logger.debug("======================================");

		ReceiptVO receiptVO = new ReceiptVO();
		receiptVO.setPymSeqNo(pymSeqNo);
		receiptVO.setCnclYn("Y");
		receiptVO.setPrepayOccSeqNo(prepayOccSeqNo);
		receiptVO.setRegrId(workId);
		receiptVO.setRegDate(DateUtil.sysdate());

		int updCnt = paymentCancelMapper.updateReceiptCncl(receiptVO);

		if (updCnt <= 0) {
			logger.debug("FAIL UPDATE TBLPY_RCPT");
			logger.debug("수납 내역 취소 정보 반영 실패");
			result = false;
		}

		return result;
	}

	/**
	 * 입금내역에 취소정보 반영
	 * @param dpstSeqNo, prepayTgtYn, prepayOccSeqNo, workId
	 * @return 
	 */
	private boolean updateDepositCancelInfo(String dpstSeqNo, String workId) {
		boolean result = true;

		logger.debug("====================================");
		logger.debug("  updateDpstCnclInfo Process...  ");
		logger.debug("====================================");

		DepositVO depositVO = new DepositVO();
		depositVO.setDpstSeqNo(dpstSeqNo);
		depositVO.setPayProcYn("N");
		depositVO.setPayProcDt("");
		depositVO.setPayCnclYn("Y");
		depositVO.setRegrId(workId);
		depositVO.setRegDate(DateUtil.sysdate());

		int updCnt = paymentCancelMapper.updateDepositCncl(depositVO);

		if (updCnt <= 0) {
			logger.debug("FAIL UPDATE TBLPY_DPST");
			logger.debug("입금 내역 취소 정보 반영 실패");
			result = false;
		}

		return result;
	}
}
