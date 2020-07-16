package com.ntels.ccbs.charge.service.batchprocmng.prepaytransmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.DepositVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.PrepayTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommBalVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.paymentmng.PaymentMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.prepaytransmng.PrepayTransMapper;
import com.ntels.ccbs.charge.service.batchprocmng.billupdmng.BillMastService;
import com.ntels.ccbs.charge.service.batchprocmng.prepaytransmng.PrepayTransService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class PrepayTransServiceImpl implements PrepayTransService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PrepayTransMapper prepayTransMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
	private BillMastService billMastService;
	
	/**
	 * 선수 일반대체 적용을 처리한다.
	 * @param transApplNo, replTp, pgmId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processPrepayTrans(String transApplNo, String replTp, String pgmId, String workId) throws Exception {
		int resultFlag = 1;
		
		// 대체 신청 리스트를 조회한다.
		logger.debug("=====================================");
		logger.debug("  getPrepayTransApplList Process...  ");
		logger.debug("=====================================");
		
		List<TransCommApplVO> transApplList = new ArrayList<TransCommApplVO>();
		
		transApplList = prepayTransMapper.getTransApplList(transApplNo, "01");
		
		int loopCnt = transApplList.size();
		
		for(int i=0; i<loopCnt; i++) {
			TransCommApplVO transApplInfo = new TransCommApplVO();
			
			transApplInfo = transApplList.get(i);
			
			// 대체처리 대상 선수금 번호, 납부계정ID, BILL_SEQ_NO 등 가져오기
			String prepayApplTgtNo	= transApplInfo.getTransApplTgtNo();
			String pymAcntId		= transApplInfo.getPymAcntId();
			String billSeqNo		= transApplInfo.getBillSeqNo();
			String useYymm			= transApplInfo.getUseYymm();
			String svcCmpsId		= transApplInfo.getSvcCmpsId();
			String prodCmpsId		= transApplInfo.getProdCmpsId();
			String chrgItmCd		= transApplInfo.getChrgItmCd();
			
			// 대체신청금액을 가져온다.
			double transApplAmt		= transApplInfo.getTransApplAmt();
			double calBalAmt		= 0;
			
			// 선수 잔액 List 를 조회한다.
			logger.debug("====================================");
			logger.debug("  getPrepayTransBalList Process...  ");
			logger.debug("====================================");
			List<TransCommBalVO> prepayBalList = new ArrayList<TransCommBalVO>();
			
			prepayBalList = prepayTransMapper.getPrepayTransBalList(pymAcntId, prepayApplTgtNo);
			
			int loopCnt1 = prepayBalList.size();

			for(int j=0;j<loopCnt1;j++) {
				TransCommBalVO prepayBalInfo = new TransCommBalVO();
				
				prepayBalInfo = prepayBalList.get(j);
				
				String prepayOccSeqNo	= prepayBalInfo.getTransTgtOccSeqNo();
				double prepayBalAmt		= prepayBalInfo.getTransBal();
				String dpstSeqNo		= prepayBalInfo.getDpstSeqNo();
				
				// 선수잔액과 대체신청금액을 비교한다.
				if(transApplAmt < prepayBalAmt) {
					calBalAmt = transApplAmt;
				} else {
					calBalAmt = prepayBalAmt;
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
					
					double aplyAmt = 0.0;	// 선수적용금액
					double billAmt = billInfo.getBillAmt();
					
					// 선수 적용 금액 설정
					if(calBalAmt >= billAmt) {
						aplyAmt = billAmt;
					} else {
						aplyAmt = calBalAmt;
					}
					calBalAmt = calBalAmt - aplyAmt;
					
					// billInfo 수납금액 값 설정
					billInfo.setRcptAmt(aplyAmt);
					
					// 선수대체일련번호 가져오기
					String prepayTransSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_PRTR_NO, 10);
					
					// 선수대체 이력 생성을 위한 컬럼값 설정
					PrepayTransHistVO prepayTransHistVO = new PrepayTransHistVO();
					prepayTransHistVO = setPrepayTransHist(prepayTransSeqNo, prepayOccSeqNo, replTp, calBalAmt, billInfo, pgmId, workId);
					
					// 선수대체 이력 등록
					logger.debug("====================================");
					logger.debug("  insertPrepayTransHist Process...  ");
					logger.debug("====================================");
					int insCnt1 = prepayTransMapper.insertPrepayTransHist(prepayTransHistVO);
					
					if (insCnt1 <= 0) {
						logger.debug("FAIL INSERT TBLPY_PREPAY_TRANS_HIST");
						logger.debug("선수금 대체 이력 등록 실패");
						throw new Exception("FAIL INSERT TBLPY_PREPAY_TRANS_HIST");
					}
					
					// 수납상세 생성을 위한 컬럼값 설정
					ReceiptDtlVO receiptDtlVO = new ReceiptDtlVO();
					receiptDtlVO = setReceiptDtl(billInfo, pymSeqNo, prepayTransSeqNo, pgmId, workId);
					
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
					
					// 선수금 잔액 변경을 위한 컬럼값 설정
					String procStat = "02";
					
					// 선수금 잔액 Update를 위한 컬럼값 설정
					PrepayOccVO prepayOccVO = new PrepayOccVO();
					prepayOccVO = setPrepayOcc(procStat, prepayOccSeqNo, aplyAmt, pgmId, workId);
					
					// 선수금 잔액 Update
					logger.debug("====================================");
					logger.debug("  updatePrepayOcc Process...  ");
					logger.debug("====================================");
					int updCnt1 = prepayTransMapper.updatePrepayOccBalAmt(prepayOccVO);
					
					if (updCnt1 <= 0) {
						logger.debug("FAIL UPDATE TBLPY_PREPAY_OCC");
						logger.debug("선수 발생 내역 잔액 변경 실패");
						throw new Exception("FAIL UPDATE TBLPY_PREPAY_OCC");
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
					billMastService.billMastUpdate(billInfo.getSoId(), billInfo.getBillYymm(), billInfo.getBillSeqNo(), billInfo.getPymAcntId(), workId);
					
					// 선수금을 전부 반영했거나 청구내역 마지막 Record 일 경우 값 설정
					if(calBalAmt == 0 || k==loopCnt2-1) {
						
						// 수납내역 컬럼 값 설정
						receiptVO = setReceipt(pymSeqNo, dpstSeqNo, billInfo, pymAcntId, pgmId, workId);
						
						// 선수금을 전부 반영하면 청구내역이 남아있어도 Exit
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
			}
			
			// 대체 신청 내역에 승인 정보 반영
			logger.debug("====================================");
			logger.debug("  updateTransAppl Process...  ");
			logger.debug("====================================");
			
			transApplInfo.setProcDt(DateUtil.getDateStringYYYYMMDD(0));
			transApplInfo.setDcsnProcStat("04");
			transApplInfo.setRegrId(workId);
			transApplInfo.setRegDate(DateUtil.sysdate());
			
			int updCnt3 = prepayTransMapper.updateTransAppl(transApplInfo);
			                                                                          
			if (updCnt3 <= 0) {
				logger.debug("FAIL UPDATE TBLPY_TRANS_APPL");
				logger.debug("대체 신청 내역 승인 변경 실패");
				throw new Exception("FAIL UPDATE TBLPY_TRANS_APPL");
			}
		}

		return resultFlag;
	}
	
	/**
	 * 선수대체 이력 생성을 위한 컬럼값 설정
	 * @param transSeqNo, occSeqNo, replTp, calBalAmt, billInfo, pgmId, workId
	 * @return PrepayTransHistVO
	 */
	private PrepayTransHistVO setPrepayTransHist(String transSeqNo, String occSeqNo, String replTp, double calBalAmt, BillListVO billInfo,
												String pgmId, String workId) {
		
		PrepayTransHistVO transHistInfo = new PrepayTransHistVO();
		
		transHistInfo.setSoId(billInfo.getSoId());
		transHistInfo.setPrepayTransSeqNo(transSeqNo);
		transHistInfo.setPrepayOccSeqNo(occSeqNo);
		transHistInfo.setTransProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		transHistInfo.setPrepayReplTp(replTp);
		transHistInfo.setTransProcAmt(billInfo.getRcptAmt());		
		if("01".equals(replTp)) {
			transHistInfo.setProcMemo("정기청구 선수대체 적용");
		} else if("02".equals(replTp)) {
			transHistInfo.setProcMemo("수납 대체");
		} else if("03".equals(replTp)) {
			transHistInfo.setProcMemo("보증금 대체");
		} else if("04".equals(replTp)) {
			transHistInfo.setProcMemo("납부자 대체");
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
		receiptDtlInfo.setPrepayTransSeqNo(transSeqNo);
		receiptDtlInfo.setInptMenuId(pgmId);
		receiptDtlInfo.setRegrId(workId);
		receiptDtlInfo.setRegDate(DateUtil.sysdate());
				
		return receiptDtlInfo;
	}
	
	/**
	 * 선수금 잔액 변경을 위한 컬럼값 설정
	 * @param procStat, occSeqNo, transAmt, pgmId, workId
	 * @return PrepayOccVO
	 */
	private PrepayOccVO setPrepayOcc(String procStat, String occSeqNo, double transAmt, String pgmId, String workId) {
		PrepayOccVO prepayOccInfo = new PrepayOccVO();
		
		prepayOccInfo.setPrepayOccSeqNo(occSeqNo);
		prepayOccInfo.setPrepayProcStat(procStat);
		prepayOccInfo.setPrepayTransAmt(transAmt);
		prepayOccInfo.setTransDt(DateUtil.getDateStringYYYYMMDD(0));
		prepayOccInfo.setRegrId(workId);
		prepayOccInfo.setRegDate(DateUtil.sysdate());
				
		return prepayOccInfo;
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
		receiptInfo.setTransDt("");
		receiptInfo.setDpstProcDt(DateUtil.getDateStringYYYYMMDD(0));
		receiptInfo.setDpstCl("");
		receiptInfo.setPayTp("02");	//결제타입 : 선수금 대체 수납
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
	 * 선수 보증금대체 적용을 처리한다.
	 * @param transApplNo, replTp, pgmId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processPrepayAssrTrans(String transApplNo, String replTp, String pgmId, String workId) throws Exception {
		int resultFlag = 1;
		
		// 대체 신청 리스트를 조회한다.
		logger.debug("=====================================");
		logger.debug("  getPrepayTransApplList Process...  ");
		logger.debug("=====================================");
		
		List<TransCommApplVO> transApplList = new ArrayList<TransCommApplVO>();
		
		transApplList = prepayTransMapper.getTransApplList(transApplNo, "01");
		
		int loopCnt = transApplList.size();
		
		for(int i=0; i<loopCnt; i++) {
			TransCommApplVO transApplInfo = new TransCommApplVO();
			
			transApplInfo = transApplList.get(i);
			
			// 대체처리 대상 선수금 번호, 납부계정ID, BILL_SEQ_NO 등 가져오기
			String prepayApplTgtNo	= transApplInfo.getTransApplTgtNo();
			String pymAcntId		= transApplInfo.getPymAcntId();
			String billSeqNo		= transApplInfo.getBillSeqNo();
			String useYymm			= transApplInfo.getUseYymm();
			String svcCmpsId		= transApplInfo.getSvcCmpsId();
			String prodCmpsId		= transApplInfo.getProdCmpsId();
			String chrgItmCd		= transApplInfo.getChrgItmCd();
			
			// 대체신청금액을 가져온다.
			double transApplAmt		= transApplInfo.getTransApplAmt();
			double calBalAmt		= 0;
			
			// 선수 잔액 List 를 조회한다.
			logger.debug("====================================");
			logger.debug("  getPrepayTransBalList Process...  ");
			logger.debug("====================================");
			List<TransCommBalVO> prepayBalList = new ArrayList<TransCommBalVO>();
			
			prepayBalList = prepayTransMapper.getPrepayTransBalList(pymAcntId, prepayApplTgtNo);
			
			int loopCnt1 = prepayBalList.size();

			for(int j=0;j<loopCnt1;j++) {
				TransCommBalVO prepayBalInfo = new TransCommBalVO();
				
				prepayBalInfo = prepayBalList.get(j);
				
				String prepayOccSeqNo	= prepayBalInfo.getTransTgtOccSeqNo();
				double prepayBalAmt		= prepayBalInfo.getTransBal();
				String dpstSeqNo		= prepayBalInfo.getDpstSeqNo();
				
				// 선수잔액과 대체신청금액을 비교한다.
				if(transApplAmt < prepayBalAmt) {
					calBalAmt = transApplAmt;
				} else {
					calBalAmt = prepayBalAmt;
				}
				
				// 미납내역이 있는 청구내역을 조회한다.
				logger.debug("====================================");
				logger.debug("  getUnRcptBillList Process...  ");
				logger.debug("====================================");
				
				List<BillListVO> billList = new ArrayList<BillListVO>();
				
				billList = prepayTransMapper.getUnRcptBillList(pymAcntId, billSeqNo, useYymm, svcCmpsId, prodCmpsId, chrgItmCd, "02");
				
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
					
					double aplyAmt = 0.0;	// 선수적용금액
					double billAmt = billInfo.getBillAmt();
					
					// 선수 적용 금액 설정
					if(calBalAmt >= billAmt) {
						aplyAmt = billAmt;
					} else {
						aplyAmt = calBalAmt;
					}
					calBalAmt = calBalAmt - aplyAmt;
					
					// billInfo 수납금액 값 설정
					billInfo.setRcptAmt(aplyAmt);
					
					// 선수대체일련번호 가져오기
					String prepayTransSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_PRTR_NO, 10);
					
					// 선수대체 이력 생성을 위한 컬럼값 설정
					PrepayTransHistVO prepayTransHistVO = new PrepayTransHistVO();
					prepayTransHistVO = setPrepayTransHist(prepayTransSeqNo, prepayOccSeqNo, replTp, calBalAmt, billInfo, pgmId, workId);
					
					// 선수대체 이력 등록
					logger.debug("====================================");
					logger.debug("  insertPrepayTransHist Process...  ");
					logger.debug("====================================");
					int insCnt1 = prepayTransMapper.insertPrepayTransHist(prepayTransHistVO);
					
					if (insCnt1 <= 0) {
						logger.debug("FAIL INSERT TBLPY_PREPAY_TRANS_HIST");
						logger.debug("선수금 대체 이력 등록 실패");
						throw new Exception("FAIL INSERT TBLPY_PREPAY_TRANS_HIST");
					}
					
					// 수납상세 생성을 위한 컬럼값 설정
					ReceiptDtlVO receiptDtlVO = new ReceiptDtlVO();
					receiptDtlVO = setReceiptDtl(billInfo, pymSeqNo, prepayTransSeqNo, pgmId, workId);
					
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
					
					// 선수금 잔액 변경을 위한 컬럼값 설정
					String procStat = "02";
					
					// 선수금 잔액 Update를 위한 컬럼값 설정
					PrepayOccVO prepayOccVO = new PrepayOccVO();
					prepayOccVO = setPrepayOcc(procStat, prepayOccSeqNo, aplyAmt, pgmId, workId);
					
					// 선수금 잔액 Update
					logger.debug("====================================");
					logger.debug("  updatePrepayOcc Process...  ");
					logger.debug("====================================");
					int updCnt1 = prepayTransMapper.updatePrepayOccBalAmt(prepayOccVO);
					
					if (updCnt1 <= 0) {
						logger.debug("FAIL UPDATE TBLPY_PREPAY_OCC");
						logger.debug("선수 발생 내역 잔액 변경 실패");
						throw new Exception("FAIL UPDATE TBLPY_PREPAY_OCC");
					}
					
					// 보증금 발생내역 생성 - [추가개발:추후 확인 필요]
					String rateItmTypCd = billInfo.getRateItmTypCd();
					String assrOccSeqNo = "";
					if ("CT111".equals(rateItmTypCd)) {
						assrOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_ASSR_NO, 10);
						boolean assrInsRes = insertPrepayTransAssrOcc(assrOccSeqNo, pymSeqNo, pymAcntId, prepayOccSeqNo, dpstSeqNo, pgmId, workId);
						if(assrInsRes == false) {
							resultFlag = -1;
							throw new Exception("FAIL INSERT TBLPY_ASSR_OCC FOR PREPAY TRANS");
						}
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
					
					// 선수금을 전부 반영했거나 청구내역 마지막 Record 일 경우 값 설정
					if(calBalAmt == 0 || k==loopCnt2-1) {
						
						// 수납내역 컬럼 값 설정
						receiptVO = setReceipt(pymSeqNo, dpstSeqNo, billInfo, pymAcntId, pgmId, workId);
						
						// 선수금을 전부 반영하면 청구내역이 남아있어도 Exit
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
			}
			
			// 대체 신청 내역에 승인 정보 반영
			logger.debug("====================================");
			logger.debug("  updateTransAppl Process...  ");
			logger.debug("====================================");
			
			transApplInfo.setProcDt(DateUtil.getDateStringYYYYMMDD(0));
			transApplInfo.setDcsnProcStat("04");
			transApplInfo.setRegrId(workId);
			transApplInfo.setRegDate(DateUtil.sysdate());
			
			int updCnt3 = prepayTransMapper.updateTransAppl(transApplInfo);
			                                                                          
			if (updCnt3 <= 0) {
				logger.debug("FAIL UPDATE TBLPY_TRANS_APPL");
				logger.debug("대체 신청 내역 승인 변경 실패");
				throw new Exception("FAIL UPDATE TBLPY_TRANS_APPL");
			}
		}
		
		return resultFlag;
	}
	
	/**
	 * 보증금 내역 생성
	 * @param assrOccSeqNo, pymSeqNo, pymAcntId, pymSeqNo, prepayOccSeqNo, inptMenuId, workId
	 * @return 
	 */
	private boolean insertPrepayTransAssrOcc(String assrOccSeqNo, String pymSeqNo, String pymAcntId, String prepayOccSeqNo, String dpstSeqNo, String inptMenuId, String workId) {
		boolean result = true;
		
		logger.debug("===========================================");
		logger.debug("  insertPrepayTransAssrOcc Process...  ");
		logger.debug("===========================================");
		
		AssrOccVO assrOccVO = new AssrOccVO();
		
		assrOccVO.setAssrOccSeqNo(assrOccSeqNo);
		assrOccVO.setPymSeqNo(pymSeqNo);
		assrOccVO.setPymAcntId(pymAcntId);
		assrOccVO.setAssrOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		assrOccVO.setDpstTpSeqNo(prepayOccSeqNo);
		assrOccVO.setDpstSeqNo(dpstSeqNo);
		assrOccVO.setAssrProcStat("01");	//보증 처리 상태 : 보증금발생
		assrOccVO.setAssrTransAmt(0);
		assrOccVO.setTransCmplYn("N");
		assrOccVO.setCnclYn("N");
		assrOccVO.setCnclDttm("");
		assrOccVO.setTransDt("");
		assrOccVO.setErpSndYn("N");
		assrOccVO.setInptMenuId(inptMenuId);
		assrOccVO.setRegrId(workId);
		assrOccVO.setRegDate(DateUtil.sysdate());
		
		int insCnt = prepayTransMapper.insertPrepayTransAssrOcc(assrOccVO);
		
		if(insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_ASSR_OCC");
			logger.debug("보증금 발생내역 생성 실패");
			result = false;
		}
		
		return result;
	}
	
}
