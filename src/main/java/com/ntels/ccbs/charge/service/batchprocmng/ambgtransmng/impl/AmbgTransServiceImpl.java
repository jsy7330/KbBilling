package com.ntels.ccbs.charge.service.batchprocmng.ambgtransmng.impl;

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
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommBalVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.ambgtransmng.AmbgTransMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.paymentmng.PaymentMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.prepaytransmng.PrepayTransMapper;
import com.ntels.ccbs.charge.service.batchprocmng.ambgtransmng.AmbgTransService;
import com.ntels.ccbs.charge.service.batchprocmng.billupdmng.BillMastService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class AmbgTransServiceImpl implements AmbgTransService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AmbgTransMapper ambgTransMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private PrepayTransMapper prepayTransMapper;
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
	private BillMastService billMastService;
	
	/**
	 * 불명금 대체 적용을 처리한다.
	 * @param transApplNo, replTp, pgmId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processAmbgTrans(String transApplNo, String replTp, String pgmId, String workId) throws Exception {
		int resultFlag = 1;
		
		// 대체 신청 리스트를 조회한다.
		logger.debug("====================================");
		logger.debug("  getAmbgTransApplList Process...  ");
		logger.debug("====================================");
		
		List<TransCommApplVO> transApplList = new ArrayList<TransCommApplVO>();
		
		transApplList = prepayTransMapper.getTransApplList(transApplNo, "02");
		
		int loopCnt = transApplList.size();
		
		for(int i=0; i<loopCnt; i++) {
			TransCommApplVO transApplInfo = new TransCommApplVO();
			
			transApplInfo = transApplList.get(i);
			
			// 대체처리 대상 불명금 번호, 납부계정ID, BILL_SEQ_NO 등 가져오기
			String ambgApplTgtNo	= transApplInfo.getTransApplTgtNo();
			String pymAcntId		= transApplInfo.getPymAcntId();
			String billSeqNo		= transApplInfo.getBillSeqNo();
			String useYymm			= transApplInfo.getUseYymm();
			String svcCmpsId		= transApplInfo.getSvcCmpsId();
			String prodCmpsId		= transApplInfo.getProdCmpsId();			
			String chrgItmCd		= transApplInfo.getChrgItmCd();
			
			// 대체신청금액을 가져온다.
			double transApplAmt		= transApplInfo.getTransApplAmt();
			double calBalAmt		= 0;
			
			// 불명금 잔액 List 를 조회한다.
			logger.debug("====================================");
			logger.debug("  getAmbgTransBalList Process...  ");
			logger.debug("====================================");
			List<TransCommBalVO> ambgBalList = new ArrayList<TransCommBalVO>();
			
			ambgBalList = ambgTransMapper.getAmbgTransBalList(pymAcntId, ambgApplTgtNo);
			
			int loopCnt1 = ambgBalList.size();

			for(int j=0;j<loopCnt1;j++) {
				TransCommBalVO ambgBalInfo = new TransCommBalVO();
				
				ambgBalInfo = ambgBalList.get(j);
				
				String ambgOccSeqNo	= ambgBalInfo.getTransTgtOccSeqNo();
				double ambgBalAmt	= ambgBalInfo.getTransBal();
				String dpstSeqNo	= ambgBalInfo.getDpstSeqNo();
				
				// 불명잔액과 대체신청금액을 비교한다.
				if(transApplAmt < ambgBalAmt) {
					calBalAmt = transApplAmt;
				} else {
					calBalAmt = ambgBalAmt;
				}
				
				// 미납내역이 있는 청구내역을 조회한다.
				logger.debug("====================================");
				logger.debug("  getUnRcptBillList Process...  ");
				logger.debug("====================================");
				
				List<BillListVO> billList = new ArrayList<BillListVO>();
				
				billList = prepayTransMapper.getUnRcptBillList(pymAcntId, billSeqNo, useYymm, svcCmpsId, prodCmpsId, chrgItmCd, "01");
				
				int loopCnt2 = billList.size();
				
				// 수납내역 생성을 위한 저장변수 설정
				ReceiptVO receiptVO = new ReceiptVO();
				
				String pymSeqNo = "";
				
				// 청구내역이 있을 경우만 수납번호 가져오기 
				if(loopCnt2 > 0) {
					pymSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_RCPT_NO, 10);
				}
				
				for(int k=0;k<loopCnt2;k++) {
					BillListVO billInfo = new BillListVO();
					
					billInfo = billList.get(k);
					
					double aplyAmt = 0.0;	// 대체적용금액
					double billAmt = billInfo.getBillAmt();
					
					// 대체 적용 금액 설정
					if(calBalAmt >= billAmt) {
						aplyAmt = billAmt;
					} else {
						aplyAmt = calBalAmt;
					}
					calBalAmt = calBalAmt - aplyAmt;
					transApplAmt = transApplAmt - aplyAmt;
					
					// billInfo 수납금액 값 설정
					billInfo.setRcptAmt(aplyAmt);
					
					// 불명금 대체일련번호 가져오기
					String ambgTransSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_AMTR_NO, 10);
					
					// 불명금 대체 이력 생성을 위한 컬럼값 설정
					AmbgTransHistVO ambgTransHistVO = new AmbgTransHistVO();
					ambgTransHistVO = setAmbgTransHist(ambgTransSeqNo, ambgOccSeqNo, replTp, calBalAmt, billInfo, pgmId, workId);
					
					// 불명금 대체 이력 등록
					logger.debug("====================================");
					logger.debug("  insertAmbgTransHist Process...  ");
					logger.debug("====================================");
					int insCnt1 = ambgTransMapper.insertAmbgTransHist(ambgTransHistVO);
					
					if (insCnt1 <= 0) {
						logger.debug("FAIL INSERT TBLPY_AMBG_TRANS_HIST");
						logger.debug("불명금 대체 이력 등록 실패");
						throw new Exception("FAIL INSERT TBLPY_AMBG_TRANS_HIST");
					}
					
					// 수납상세 생성을 위한 컬럼값 설정
					ReceiptDtlVO receiptDtlVO = new ReceiptDtlVO();
					receiptDtlVO = setReceiptDtl(billInfo, pymSeqNo, ambgTransSeqNo, pgmId, workId);
					
					// 수납 상세 내역 등록
					logger.debug("====================================");
					logger.debug("  insertReceiptDtl Process...  ");
					logger.debug("====================================");
					
					int insCnt2 = paymentMapper.insertReceiptDtl(receiptDtlVO);
					
					if (insCnt2 <= 0) {
						logger.debug("FAIL INSERT TBLPY_RCPT_DTL");
						logger.debug("수납 상세 내역 등록 실패");
						throw new Exception("FAIL INSERT TBLPY_RCPT_DTL");
					}
					
					// 불명금 잔액 변경을 위한 컬럼값 설정
					String procStat = "02";
					
					// 불명금 잔액 Update를 위한 컬럼값 설정
					AmbgOccVO ambgOccVO = new AmbgOccVO();
					ambgOccVO = setAmbgOcc(procStat, ambgOccSeqNo, aplyAmt, pymAcntId, pgmId, workId);
					
					// 불명금 잔액 Update
					logger.debug("====================================");
					logger.debug("  updateAmbgOccBalAmt Process...  ");
					logger.debug("====================================");
					int updCnt1 = ambgTransMapper.updateAmbgOccBalAmt(ambgOccVO);
					
					if (updCnt1 <= 0) {
						logger.debug("FAIL UPDATE TBLPY_AMBG_OCC");
						logger.debug("불명금 발생 내역 변경 실패");
						throw new Exception("FAIL UPDATE TBLPY_AMBG_OCC");
					}
					
					// 청구내역 Update
					logger.debug("====================================");
					logger.debug("  updateBillData Process...  ");
					logger.debug("====================================");
					billInfo.setLastRcptDt(DateUtil.getDateStringYYYYMMDD(0));
					billInfo.setChgrId(workId);
					billInfo.setChgDate(DateUtil.sysdate());
					
					int updCnt2 = paymentMapper.updateBillData(billInfo);
					
					if (updCnt2 <= 0) {
						logger.debug("FAIL UPDATE TBLIV_BILL");
						logger.debug("청구 내역 변경 실패");
						throw new Exception("FAIL UPDATE TBLIV_BILL");
					}
					
					// 청구 TBLIV_BILL_MAST 수납금액, 최종수납일 반영
					billMastService.billMastUpdate("00", billInfo.getBillYymm(), billInfo.getBillSeqNo(), billInfo.getPymAcntId(), workId);
					
					// 불명금을 전부 반영했거나 청구내역 마지막 Record 일 경우 값 설정
					if(calBalAmt == 0 || k==loopCnt2-1) {
						
						// 수납내역 컬럼 값 설정
						receiptVO = setReceipt(pymSeqNo, dpstSeqNo, billInfo, pymAcntId, pgmId, workId);
						
						// 불명금을 전부 반영하면 청구내역이 남아있어도 Exit
						if (calBalAmt == 0) break;
					}
				}
				
				if(pymSeqNo.length() > 0) {
					// 수납 내역 등록
					logger.debug("====================================");
					logger.debug("  insertRcpt Process...  ");
					logger.debug("====================================");
				 
					int insCnt3 = prepayTransMapper.insertTransReceipt(receiptVO);
				
					if (insCnt3 <= 0) {
						logger.debug("FAIL INSERT TBLPY_RCPT");
						logger.debug("수납내역  등록 실패");
						throw new RuntimeException("FAIL INSERT TBLPY_RCPT");
					}
				}
				
				// 입금정보에 납부계정ID를 UPDATE를 위한 항목값 설정
				DepositVO depositVO = new DepositVO();
				depositVO = setDeposit(dpstSeqNo, pymAcntId, pgmId, workId);
				
				// 입금내역 납부계정 ID UPDATE
				logger.debug("====================================");
				logger.debug("  updateDepositAcntId Process...  ");
				logger.debug("====================================");
				
				int updCnt3 = ambgTransMapper.updateDepositAcntId(depositVO);
				
				if (updCnt3 <= 0) {
					logger.debug("FAIL UPDATE TBLPY_DPST[PYM_ACNT_ID]");
					logger.debug("입금내역 납부계정ID 변경 실패");
					throw new Exception("FAIL UPDATE TBLPY_DPST[PYM_ACNT_ID]");
				}
			}
			
			// 뷸명금 신청금액에서 대체후에 잔액이 남은 경우 선수금을 발생시킨다.
			if(transApplAmt > 0) {
				// 선수금 발생내역 등록
				logger.debug("====================================");
				logger.debug("  insertPrepayOcc Process...  ");
				logger.debug("====================================");
				
				// 선수내역 발생을 위한 컬럼값 설정
				PrepayOccVO prepayOccVO = new PrepayOccVO();
				
				String prepayOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_PRPY_NO, 10);
				prepayOccVO = setPrepayOcc(prepayOccSeqNo, ambgApplTgtNo, pymAcntId, transApplAmt, pgmId, workId);
				
				int insCnt4 = ambgTransMapper.insertPrepayOccFromAmbg(prepayOccVO);
				
				if (insCnt4 <= 0) {
					logger.debug("FAIL INSERT TBLPY_PREPAY_OCC");
					logger.debug("선수내역  등록 실패");
					throw new RuntimeException("FAIL INSERT TBLPY_PREPAY_OCC");
				}
				
				// 선수금을 발생시켰으므로 불명금 잔액을 다시 한번 UPDATE 한다.
				AmbgOccVO ambgOccFinishVO = new AmbgOccVO();
				ambgOccFinishVO = setAmbgOcc("02", ambgApplTgtNo, transApplAmt, pymAcntId, pgmId, workId);
				
				logger.debug("=====================================");
				logger.debug("  updateAmbgOccBalAmt Process #2...  ");
				logger.debug("=====================================");
				int updCnt4 = ambgTransMapper.updateAmbgOccBalAmt(ambgOccFinishVO);
				
				if (updCnt4 <= 0) {
					logger.debug("FAIL UPDATE TBLPY_AMBG_OCC #2");
					logger.debug("불명금 발생 내역 변경 실패 #2");
					throw new Exception("FAIL UPDATE TBLPY_AMBG_OCC #2");
				}
			}
						
			// 대체 신청 내역에 승인 정보 반영
			logger.debug("====================================");
			logger.debug("  updateTransAppl Process...  ");
			logger.debug("====================================");
			
			transApplInfo.setProcDt(DateUtil.getDateStringYYYYMMDD(0));
			transApplInfo.setDcsnProcStat("04");
			transApplInfo.setRegrId(workId);
			transApplInfo.setRegDate(DateUtil.sysdate());
			
			int updCnt5 = prepayTransMapper.updateTransAppl(transApplInfo);
			                                                                          
			if (updCnt5 <= 0) {
				logger.debug("FAIL UPDATE TBLPY_TRANS_APPL");
				logger.debug("대체 신청 내역 승인 변경 실패");
				throw new Exception("FAIL UPDATE TBLPY_TRANS_APPL");
			}
		}

		return resultFlag;
	}
	
	/**
	 * 불명금 대체 이력 생성을 위한 컬럼값 설정
	 * @param transSeqNo, occSeqNo, replTp, calBalAmt, billInfo, pgmId, workId
	 * @return AmbgTransHistVO
	 */
	private AmbgTransHistVO setAmbgTransHist(String transSeqNo, String occSeqNo, String replTp, double calBalAmt, BillListVO billInfo,
											String pgmId, String workId) {
		
		AmbgTransHistVO transHistInfo = new AmbgTransHistVO();
		
		transHistInfo.setAmbgTransSeqNo(transSeqNo);
		transHistInfo.setAmbgOccSeqNo(occSeqNo);
		transHistInfo.setTransProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		transHistInfo.setAmbgReplTp(replTp);
		transHistInfo.setTransProcAmt(billInfo.getRcptAmt());		
		if("01".equals(replTp)) {
			transHistInfo.setProcMemo("수납 대체");
		} else if("02".equals(replTp)) {
			transHistInfo.setProcMemo("환불 대체");
		} else if("03".equals(replTp)) {
			transHistInfo.setProcMemo("잡이익 대체");
		} else {
			transHistInfo.setProcMemo("");
		}
		transHistInfo.setApprReqrId(workId);
		transHistInfo.setApprReqDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		transHistInfo.setDcsnProcStat("04");	// [추후확인필요]결제요청 처리상태 - 승인(완료)
		transHistInfo.setApprrId(workId);
		transHistInfo.setApprDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		transHistInfo.setCnclYn("N");
		transHistInfo.setCnclDttm("");
		transHistInfo.setBalAmt(calBalAmt);
		transHistInfo.setErpSndYn("N");
		transHistInfo.setInptMenuId(pgmId);
		transHistInfo.setRegrId(workId);
		transHistInfo.setRegDate(DateUtil.sysdate());
		
		return transHistInfo;
	}
	
	/**
	 * 수납상세 생성을 위한 컬럼값 설정
	 * @param billInfo, pymSeqNo, transSeqNo, pgmId, workId
	 * @return ReceiptDtlVO
	 */
	private ReceiptDtlVO setReceiptDtl(BillListVO billInfo, String pymSeqNo, String transSeqNo, String pgmId, String workId) {
		ReceiptDtlVO receiptDtlInfo = new ReceiptDtlVO();
		
		// 청구정보 데이터 입금상세 내역으로 복사
		CUtil.copyObjectValue(billInfo, receiptDtlInfo);
		
		receiptDtlInfo.setPymSeqNo(pymSeqNo);
		receiptDtlInfo.setPrePymAmt(0.0);	// [추후확인필요]컬럼 용도 확인 필요
		receiptDtlInfo.setPreSoId(billInfo.getSoId());
		receiptDtlInfo.setAmbgTransSeqNo(transSeqNo);
		receiptDtlInfo.setInptMenuId(pgmId);
		receiptDtlInfo.setRegrId(workId);
		receiptDtlInfo.setRegDate(DateUtil.sysdate());
				
		return receiptDtlInfo;
	}
	
	/**
	 * 불명금 잔액 변경을 위한 컬럼값 설정
	 * @param procStat, occSeqNo, transAmt, pgmId, workId
	 * @return NBlpy00AmbgOccVO
	 */
	private AmbgOccVO setAmbgOcc(String procStat, String occSeqNo, double transAmt, String pymAcntId, String pgmId, String workId) {
		AmbgOccVO ambgOccInfo = new AmbgOccVO();
		
		ambgOccInfo.setAmbgOccSeqNo(occSeqNo);
		ambgOccInfo.setAmbgProcStat(procStat);
		ambgOccInfo.setAmbgTransAmt(transAmt);
		ambgOccInfo.setPymAcntId(pymAcntId);
		ambgOccInfo.setTransDt(DateUtil.getDateStringYYYYMMDD(0));
		ambgOccInfo.setRegrId(workId);
		ambgOccInfo.setRegDate(DateUtil.sysdate());
				
		return ambgOccInfo;
	}

	/**
	 * 수납내역 생성을 위한 컬럼값 설정
	 * @param pymSeqNo, dpstSeqNo, billInfo, pymAcntId, pgmId, workId
	 * @return ReceiptVO
	 */
	private ReceiptVO setReceipt(String pymSeqNo, String dpstSeqNo, BillListVO billInfo, String pymAcntId, String pgmId, String workId) {
		ReceiptVO receiptInfo = new ReceiptVO();
		
		receiptInfo.setPymSeqNo(pymSeqNo);
		receiptInfo.setDpstSeqNo(dpstSeqNo);
		receiptInfo.setBillSeqNo(billInfo.getBillSeqNo());
		receiptInfo.setBillYymm(billInfo.getBillYymm());
		receiptInfo.setBillCycl(billInfo.getBillCycl());
		receiptInfo.setBillDt(billInfo.getBillDt());
		receiptInfo.setSoId(billInfo.getSoId());
		receiptInfo.setPymAcntId(pymAcntId);
		receiptInfo.setPayProcDt(DateUtil.getDateStringYYYYMMDD(0));
		receiptInfo.setDpstDt("");
		receiptInfo.setTrnsDt("");
		receiptInfo.setDpstProcDt(DateUtil.getDateStringYYYYMMDD(0));
		receiptInfo.setDpstCl("");
		receiptInfo.setPayTp("03");	//결제타입 : 불명금 대체 수납
		receiptInfo.setPrepayOccSeqNo("");
		receiptInfo.setPrepdCtrtId("");
		receiptInfo.setCnclYn("N");
		receiptInfo.setRcptOpetrId(workId);
		receiptInfo.setErpSndYn("N");
		receiptInfo.setInptMenuId(pgmId);
		receiptInfo.setRegrId(workId);
		receiptInfo.setRegDate(DateUtil.sysdate());
				
		return receiptInfo;
	}

	/**
	 * 불명금 처리후에 입금내역 납부계정 반영을 위한 컬럼값 세팅
	 * @param dpstSeqNo, pymAcntId, pgmId, workId
	 * @return DepositVO
	 */
	private DepositVO setDeposit(String dpstSeqNo, String pymAcntId, String pgmId, String workId) {
		DepositVO depositInfo = new DepositVO();
		
		depositInfo.setDpstSeqNo(dpstSeqNo);
		depositInfo.setPymAcntId(pymAcntId);
		depositInfo.setRegrId(workId);
		depositInfo.setRegDate(DateUtil.sysdate());
				
		return depositInfo;
	}
	
	/**
	 * 불명금 처리후에 잔액이 남은 경우 선수금 생성을 위한 컬럼값 설정
	 * @param prepayOccSeqNo, ambgApplTgtNo, pymAcntId, calBalAmt, pgmId, workId
	 * @return NBlpy00AmbgOccVO
	 */
	private PrepayOccVO setPrepayOcc(String prepayOccSeqNo, String ambgApplTgtNo, String pymAcntId, double calBalAmt, String pgmId, String workId) {
		PrepayOccVO prepayOccInfo = new PrepayOccVO();
		
		prepayOccInfo.setPrepayOccSeqNo(prepayOccSeqNo);
		prepayOccInfo.setPymAcntId(pymAcntId);
		prepayOccInfo.setPrepayOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		prepayOccInfo.setPrepayOccTp("02");		//선수 발생 타입 : 불명금대체
		prepayOccInfo.setPrepayOccResn("03");	//선수 발생 사유 : 이체수납에의한과납
		prepayOccInfo.setPrepayProcStat("01");	//선수 처리 상태 : 선수금발생
		prepayOccInfo.setPrepayOccAmt(calBalAmt);
		prepayOccInfo.setPrepayTransAmt(0.0);
		prepayOccInfo.setTransCmplYn("N");
		prepayOccInfo.setCnclYn("N");
		prepayOccInfo.setCnclDttm("");
		prepayOccInfo.setCnclResn("");
		prepayOccInfo.setTransDt("");
		prepayOccInfo.setTransDt("");
		prepayOccInfo.setErpSndYn("N");
		prepayOccInfo.setInptMenuId(pgmId);
		prepayOccInfo.setRegrId(workId);
		prepayOccInfo.setRegDate(DateUtil.sysdate());
		prepayOccInfo.setPrepayOccTgtSeqNo(ambgApplTgtNo);
				
		return prepayOccInfo;
	}
	
}
