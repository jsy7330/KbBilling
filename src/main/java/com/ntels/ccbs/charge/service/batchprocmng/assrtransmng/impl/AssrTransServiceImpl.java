package com.ntels.ccbs.charge.service.batchprocmng.assrtransmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrOccVO;
import com.ntels.ccbs.charge.domain.batchprocmng.assrtransmng.AssrTransHistVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.prepaytransmng.TransCommBalVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.assrtransmng.AssrTransMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.paymentmng.PaymentMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.prepaytransmng.PrepayTransMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.receiptcnclmng.PaymentCancelMapper;
import com.ntels.ccbs.charge.service.batchprocmng.assrtransmng.AssrTransService;
import com.ntels.ccbs.charge.service.batchprocmng.billupdmng.BillMastService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class AssrTransServiceImpl implements AssrTransService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AssrTransMapper assrTransMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private PrepayTransMapper prepayTransMapper;
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
	private PaymentCancelMapper paymentCancelMapper;

	@Autowired
	private BillMastService billMastService;
	
	/**
	 * 보증금 대체 적용을 처리한다.
	 * @param transApplNo, replTp, procResn, pgmId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processAssrTrans(String transApplNo, String replTp, String procResn, String pgmId, String workId) throws Exception {
		int resultFlag = 1;
		
		// 대체 신청 리스트를 조회한다.
		logger.debug("====================================");
		logger.debug("  getAssrTransApplList Process...  ");
		logger.debug("====================================");
		
		List<TransCommApplVO> transApplList = new ArrayList<TransCommApplVO>();
		
		transApplList = prepayTransMapper.getTransApplList(transApplNo, "03");
				
		int loopCnt = transApplList.size();
		
		for(int i=0; i<loopCnt; i++) {
			TransCommApplVO transApplInfo = new TransCommApplVO();
			
			transApplInfo = transApplList.get(i);
			
			// 대체처리 대상 보증금 번호, 납부계정ID, BILL_SEQ_NO 등 가져오기
			String assrApplTgtNo	= transApplInfo.getTransApplTgtNo();
			String pymAcntId		= transApplInfo.getPymAcntId();
			String billSeqNo		= transApplInfo.getBillSeqNo();
			String useYymm			= transApplInfo.getUseYymm();
			String svcCmpsId		= transApplInfo.getSvcCmpsId();
			String prodCmpsId		= transApplInfo.getProdCmpsId();			
			String chrgItmCd		= transApplInfo.getChrgItmCd();
			
			// 대체신청금액을 가져온다.
			double transApplAmt		= transApplInfo.getTransApplAmt();
			double calBalAmt		= 0;
			
			// 보증금 잔액 List 를 조회한다.
			logger.debug("====================================");
			logger.debug("  getAssrTransBalList Process...  ");
			logger.debug("====================================");
			List<TransCommBalVO> assrBalList = new ArrayList<TransCommBalVO>();
			
			assrBalList = assrTransMapper.getAssrTransBalList(pymAcntId, assrApplTgtNo);
			
			int loopCnt1 = assrBalList.size();

			if(loopCnt1 == 0) {
				assrBalList = assrTransMapper.getAssrTransBalListRetry(pymAcntId, assrApplTgtNo);
				loopCnt1 = assrBalList.size();
			}

			for(int j=0;j<loopCnt1;j++) {
				TransCommBalVO assrBalInfo = new TransCommBalVO();
				
				assrBalInfo = assrBalList.get(j);
				
				String assrOccSeqNo	= assrBalInfo.getTransTgtOccSeqNo();
				double assrBalAmt	= assrBalInfo.getTransBal();
				String assrPymSeqNo = assrBalInfo.getAssrPymSeqNo();
				String dpstSeqNo	= assrBalInfo.getDpstSeqNo();
				
				logger.debug("====================================");
				logger.debug("  MinusAssrReceipt Process...  ");
				logger.debug("====================================");
				// 대체된 금액만큼 보증금 차감 처리
				boolean assrMinusResult = processMinusAssrReceipt(assrPymSeqNo, transApplAmt, pgmId, workId);
				
				if(assrMinusResult == false) {
					logger.debug("FAIL PROCESS MINUS ASSR RECEIPT");
					logger.debug("대체금액 보증금 차감 처리 실패");
					throw new Exception("FAIL PROCESS MINUS ASSR RECEIPT");
				}
				
				// 보증잔액과 대체신청금액을 비교한다.
				if(transApplAmt < assrBalAmt) {
					calBalAmt = transApplAmt;
				} else {
					calBalAmt = assrBalAmt;
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
					
					// billInfo 수납금액 값 설정
					billInfo.setRcptAmt(aplyAmt);
					
					// 보증금 대체일련번호 가져오기
					String assrTransSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_ASTR_NO, 10);
					
					// 보증금 대체 이력 생성을 위한 컬럼값 설정
					AssrTransHistVO assrTransHistVO = new AssrTransHistVO();
					assrTransHistVO = setAssrTransHist(assrTransSeqNo, assrOccSeqNo, replTp, calBalAmt, billInfo, pgmId, workId);
					
					// 보증금 대체 이력 등록
					logger.debug("====================================");
					logger.debug("  insertAssrTransHist Process...  ");
					logger.debug("====================================");
					int insCnt1 = assrTransMapper.insertAssrTransHist(assrTransHistVO);
					
					if (insCnt1 <= 0) {
						logger.debug("FAIL INSERT TBLPY_ASSR_TRANS_HIST");
						logger.debug("보증금 대체 이력 등록 실패");
						throw new Exception("FAIL INSERT TBLPY_ASSR_TRANS_HIST");
					}
					
					// 수납상세 생성을 위한 컬럼값 설정
					ReceiptDtlVO receiptDtlVO = new ReceiptDtlVO();
					receiptDtlVO = setReceiptDtl(billInfo, pymSeqNo, assrTransSeqNo, pgmId, workId);
					
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
					
					// 보증금 잔액 변경을 위한 컬럼값 설정
					String procStat = "02";
					
					// 보증금 잔액 Update를 위한 컬럼값 설정
					AssrOccVO assrOccVO = new AssrOccVO();
					assrOccVO = setAssrOcc(procStat, assrOccSeqNo, aplyAmt, pgmId, workId);
					
					// 보증금 잔액 Update
					logger.debug("====================================");
					logger.debug("  updateAssrOccBalAmt Process...  ");
					logger.debug("====================================");
					int updCnt1 = assrTransMapper.updateAssrOccBalAmt(assrOccVO);
					
					if (updCnt1 <= 0) {
						logger.debug("FAIL UPDATE TBLPY_ASSR_OCC");
						logger.debug("보증금 발생 내역 변경 실패");
						throw new Exception("FAIL UPDATE TBLPY_ASSR_OCC");
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
					
					// 보증금을 전부 반영했거나 청구내역 마지막 Record 일 경우 값 설정
					if(calBalAmt == 0 || k==loopCnt2-1) {
						
						// 수납내역 컬럼 값 설정
						receiptVO = setReceipt(pymSeqNo, dpstSeqNo, billInfo, pymAcntId, pgmId, workId);
						
						// 보증금을 전부 반영하면 청구내역이 남아있어도 Exit
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
			transApplInfo.setProcResn(procResn);
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
	 * 보증금 대체 이력 생성을 위한 컬럼값 설정
	 * @param transSeqNo, occSeqNo, replTp, transSeqNo, billInfo, pgmId, workId
	 * @return AssrTransHistVO
	 */
	private AssrTransHistVO setAssrTransHist(String transSeqNo, String occSeqNo, String replTp, double calBalAmt, BillListVO billInfo,
											String pgmId, String workId) {
		
		AssrTransHistVO transHistInfo = new AssrTransHistVO();
		
		transHistInfo.setAssrTransSeqNo(transSeqNo);
		transHistInfo.setAssrOccSeqNo(occSeqNo);
		transHistInfo.setTransProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		transHistInfo.setAssrReplTp(replTp);
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
		receiptDtlInfo.setAssrTransSeqNo(transSeqNo);
		receiptDtlInfo.setInptMenuId(pgmId);
		receiptDtlInfo.setRegrId(workId);
		receiptDtlInfo.setRegDate(DateUtil.sysdate());
				
		return receiptDtlInfo;
	}
	
	/**
	 * 보증금 잔액 변경을 위한 컬럼값 설정
	 * @param procStat, occSeqNo, transAmt, pgmId, workId
	 * @return AssrOccVO
	 */
	private AssrOccVO setAssrOcc(String procStat, String occSeqNo, double transAmt, String pgmId, String workId) {
		AssrOccVO assrOccInfo = new AssrOccVO();
		
		assrOccInfo.setAssrOccSeqNo(occSeqNo);
		assrOccInfo.setAssrProcStat(procStat);
		assrOccInfo.setAssrTransAmt(transAmt);
		assrOccInfo.setTransDt(DateUtil.getDateStringYYYYMMDD(0));
		assrOccInfo.setRegrId(workId);
		assrOccInfo.setRegDate(DateUtil.sysdate());
				
		return assrOccInfo;
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
		receiptInfo.setPayTp("04");	//결제타입 : 보증금 대체 수납
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
	 * 보증금 대체로 인한 보증금 차감 처리
	 * @param assrPymSeqNo, pgmId, workId
	 * @return boolean
	 */
	private boolean processMinusAssrReceipt(String assrPymSeqNo, double transApplAmt, String pgmId, String workId) {
		boolean result = true;
		
		// 보증금 수납취소처리 대상을 조회한다.
		BillListVO assrBillInfoVO = new BillListVO();
		
		assrBillInfoVO = assrTransMapper.getCnclAssrRcptInfo(assrPymSeqNo);
		
		if(assrBillInfoVO == null) {
			logger.debug("NO DATA FOUND");
			logger.debug("NO DATA-보증금 수납액");
			result = false;
		} else {
			// 청구금액 취소 반영
			BillListVO billListVO = new BillListVO();
			billListVO.setRcptAmt(transApplAmt);
			billListVO.setPymSeqNo(assrPymSeqNo);
			billListVO.setBillSeqNo(assrBillInfoVO.getBillSeqNo());
			billListVO.setUseYymm(assrBillInfoVO.getUseYymm());
			billListVO.setSvcCmpsId(assrBillInfoVO.getSvcCmpsId());
			billListVO.setProdCmpsId(assrBillInfoVO.getProdCmpsId());
			billListVO.setChrgItmCd(assrBillInfoVO.getChrgItmCd());
			billListVO.setChgrId(workId);
			billListVO.setChgDate(DateUtil.sysdate());
					
			int updCnt1 = paymentCancelMapper.updateBillCnclAmt(billListVO);
					
			if(updCnt1 <= 0) {
				logger.debug("FAIL UPDATE TBLIV_BILL");
				logger.debug("대체금액 보증금 차감 처리 실패");
				result = false;
			}
			
			// 청구 TBLIV_BILL_MAST 수납금액, 최종수납일 반영
			try {
				billMastService.billMastUpdate("00", assrBillInfoVO.getBillYymm(), assrBillInfoVO.getBillSeqNo(), assrBillInfoVO.getPymAcntId(), workId);
			} catch (Exception e){
				result = false;
			}
			
			// 수납 상세내역 생성 (-) 수납금, 결제 타입:05
			ReceiptDtlVO receiptDtlVO = new ReceiptDtlVO();
			
			String pymSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_RCPT_NO, 10);
			
			CUtil.copyObjectValue(assrBillInfoVO, receiptDtlVO);
			
			receiptDtlVO.setPymSeqNo(pymSeqNo);
			receiptDtlVO.setRcptAmt(transApplAmt * (-1));
			receiptDtlVO.setPrePymAmt(0.0);					// [추후확인필요]컬럼 용도 확인 필요
			receiptDtlVO.setPreSoId(assrBillInfoVO.getSoId());	// [추후확인필요]컬럼 용도 확인 필요
			receiptDtlVO.setInptMenuId(pgmId);
			receiptDtlVO.setRegrId(workId);
			receiptDtlVO.setRegDate(DateUtil.sysdate());
					
			int insCnt1 = paymentMapper.insertReceiptDtl(receiptDtlVO);
			
			if(insCnt1 <= 0) {
				logger.debug("FAIL INSERT TBLPY_RCPT_DTL");
				logger.debug("보증금 대체로 인한 보증금 차감 수납 상세 내역 생성 실패");
				result = false;
			}
			
			// 수납 내역 생성
			ReceiptVO receiptVO = new ReceiptVO();
			receiptVO.setPymSeqNo(pymSeqNo);
			receiptVO.setDpstSeqNo("");
			receiptVO.setBillSeqNo(assrBillInfoVO.getBillSeqNo());
			receiptVO.setBillYymm(assrBillInfoVO.getBillYymm());
			receiptVO.setBillCycl(assrBillInfoVO.getBillCycl());
			receiptVO.setBillDt(assrBillInfoVO.getBillDt());
			receiptVO.setSoId(assrBillInfoVO.getSoId());
			receiptVO.setPymAcntId(assrBillInfoVO.getPymAcntId());
			receiptVO.setPayProcDt(DateUtil.getDateStringYYYYMMDD(0));
			receiptVO.setPayTp("05");
			receiptVO.setPrepayOccSeqNo("");
			receiptVO.setPrepdCtrtId("");
			receiptVO.setCnclYn("N");
			receiptVO.setRcptOpetrId(workId);
			receiptVO.setBddbtRcptYn("N");
			receiptVO.setErpSndYn("N");
			receiptVO.setInptMenuId(pgmId);
			receiptVO.setRegrId(workId);
			receiptVO.setRegDate(DateUtil.sysdate());
							
			int insCnt2 = assrTransMapper.insertAssrMinusReceipt(receiptVO);
			
			if(insCnt2 <= 0) {
				logger.debug("FAIL INSERT TBLPY_RCPT");
				logger.debug("보증금 대체로 인한 보증금 차감 수납 내역 생성 실패");
				result = false;
			}
			
		}
		
		return result;		
	}
	
}
