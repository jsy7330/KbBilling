package com.ntels.ccbs.charge.service.batchprocmng.refundmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.BillListVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptDtlVO;
import com.ntels.ccbs.charge.domain.batchprocmng.paymentmng.ReceiptVO;
import com.ntels.ccbs.charge.domain.batchprocmng.refundmng.RefundApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.refundmng.RefundOrgVO;
import com.ntels.ccbs.charge.domain.batchprocmng.refundmng.RefundVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.assrtransmng.AssrTransMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.paymentmng.PaymentMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.receiptcnclmng.PaymentCancelMapper;
import com.ntels.ccbs.charge.mapper.batchprocmng.refundmng.RefundMapper;
import com.ntels.ccbs.charge.service.batchprocmng.billupdmng.BillMastService;
import com.ntels.ccbs.charge.service.batchprocmng.refundmng.RefundService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.StringUtil;

@Service
public class RefundServiceImpl implements RefundService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RefundMapper refundMapper;
	
	@Autowired
	private AssrTransMapper assrTransMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
	private PaymentCancelMapper paymentCancelMapper;

	@Autowired
	private BillMastService billMastService;
	
	/**
	 * 환불 승인을 처리한다.
	 * @param pymAcntId, rfndSeqNo, procResn, pgmId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processRefundConfirm(String pymAcntId, String rfndSeqNo, String pgmId, String workId) throws Exception {
		int resultFlag = 1;
		
		// 환불신청 리스트를 조회한다.
		logger.debug("====================================");
		logger.debug("  getRfndApplList Process...  ");
		logger.debug("====================================");
		
		List<RefundApplVO> refundApplList = new ArrayList<RefundApplVO>();
		
		refundApplList = refundMapper.getRefundApplList(pymAcntId, rfndSeqNo);
		
		int loopCnt = refundApplList.size();
		
		for(int i=0; i<loopCnt; i++) {
			RefundApplVO refundApplInfo = new RefundApplVO();
			
			refundApplInfo = refundApplList.get(i);
			
			// 환불 관리 조직 정보 가져오기
			String rfndAmtCl = refundApplInfo.getRfndOccAmtCl();
			String rfndTgtSeqNo = refundApplInfo.getRfndOccTgtSeqNo();
			double rfndApplAmt = refundApplInfo.getRfndApplAmt();

			RefundOrgVO refundOrgVO = new RefundOrgVO();
			
			refundOrgVO = refundMapper.getRefundOrgInfo(rfndAmtCl, rfndTgtSeqNo);
			
			if("03".equals(rfndAmtCl)) {
				String assrPymSeqNo = refundOrgVO.getAssrPymSeqNo();
				logger.debug("====================================");
				logger.debug("  MinusAssrReceipt Process...  ");
				logger.debug("====================================");
				// 환불된 금액만큼 보증금 차감 처리
				boolean assrMinusResult = processMinusAssrReceipt(assrPymSeqNo, rfndApplAmt, pgmId, workId);
			
				if(assrMinusResult == false) {
					logger.debug("FAIL PROCESS MINUS ASSR RECEIPT");
					logger.debug("환불금액 보증금 차감 처리 실패");
					throw new Exception("FAIL PROCESS MINUS ASSR RECEIPT");
				}
			}
			
			// 환불 내역 생성
			boolean rfndInsRes = true;
			rfndInsRes = insertRefund(refundApplInfo, refundOrgVO, pgmId, workId);
			
			if(rfndInsRes == false) {
				resultFlag = -1;
				throw new Exception("FAIL INSERT RFND");
			}
			
			// 선수/불명/보증금 잔액에 환불 금액 반영
			boolean amtUpdRes = true;
			amtUpdRes = updateRefundAmt(refundApplInfo, pgmId, workId);
			
			if(amtUpdRes == false) {
				resultFlag = -1;
				throw new Exception("FAIL UPDATE RFND AMT");
			}
			
			// 환불 신청 내역 환불 승인 처리 - 서비스 호출전에 UPDATE 처리
			/*boolean cnfmUpdRes = true;
			cnfmUpdRes = updateRefundAppl(refundApplInfo, pgmId, workId);
			
			if(cnfmUpdRes == false) {
				resultFlag = -1;
				throw new Exception("FAIL UPDATE RFND APPL");
			}*/
		}

		return resultFlag;
	}

	/**
	 * 환불 내역 생성
	 * @param rfndApplInfo, pgmId, workId
	 * @return 
	 */
	private boolean insertRefund(RefundApplVO refundApplInfo, RefundOrgVO refundOrgInfo, String pgmId, String workId) {
		boolean result = true;
	
		// 환불 내역을 등록한다.
		logger.debug("====================================");
		logger.debug("  insertRefund Process...  ");
		logger.debug("====================================");

		RefundVO refundVO = new RefundVO();
	
		/* jes_20180310 start */
		String mngCnterOrgId = "";
		String mngBrOrgId = "";
		if(refundOrgInfo != null && !StringUtil.isEmpty(refundOrgInfo.getMngCnterOrgId())) {
			mngCnterOrgId = refundOrgInfo.getMngCnterOrgId();
		}
		if(refundOrgInfo != null && !StringUtil.isEmpty(refundOrgInfo.getMngBrOrgId())) {
			mngBrOrgId = refundOrgInfo.getMngBrOrgId();
		}		
		/* jes_20180310 end */
		
		refundVO.setRfndSeqNo(refundApplInfo.getRfndSeqNo());
		refundVO.setBnkCd(refundApplInfo.getBnkCd());
		refundVO.setAcntNo(refundApplInfo.getAcntNo());
		refundVO.setAcntOwnerNm(refundApplInfo.getAcntOwnerNm());
		refundVO.setAppntTelNo(refundApplInfo.getAppntTelNo());
		refundVO.setRfndAmt(refundApplInfo.getRfndApplAmt());
		refundVO.setProcPsnId(workId);
		refundVO.setProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		refundVO.setCustRfndDt(DateUtil.getDateStringYYYYMMDD(0));
//		refundVO.setMngCnterOrgId(refundOrgInfo.getMngCnterOrgId());
//		refundVO.setMngCnterOrgId(refundOrgInfo.getMngBrOrgId());
		refundVO.setMngCnterOrgId(mngCnterOrgId);
		refundVO.setMngBrOrgId(mngBrOrgId);
		refundVO.setErpSndYn("N");
		refundVO.setInptMenuId(pgmId);
		refundVO.setRegDate(DateUtil.sysdate());
		refundVO.setRfndOccAmtCl(refundApplInfo.getRfndOccAmtCl());
		refundVO.setRfndOccTgtSeqNo(refundApplInfo.getRfndOccTgtSeqNo());
		
		int insCnt = refundMapper.insertRefund(refundVO);
	
		if(insCnt <= 0) {
			logger.debug("FAIL INSERT TBLPY_RFND");
			logger.debug("환불 내역 생성 실패");
			result = false;
		}
	
		return result;
	}
	
	/**
	 * 선수/불명/보증금 잔액에 환불 금액 반영
	 * @param rfndApplInfo, pgmId, workId
	 * @return 
	 */
	private boolean updateRefundAmt(RefundApplVO refundApplInfo, String pgmId, String workId) {
		boolean result = true;
		
		// 선수/불명/보증금 잔액에 환불 금액을 반영한다.
		logger.debug("====================================");
		logger.debug("  updateRefundAmt Process...  ");
		logger.debug("====================================");
		
		String rfndOccAmtCl = refundApplInfo.getRfndOccAmtCl();
		
		refundApplInfo.setRegrId(workId);
		refundApplInfo.setRegDate(DateUtil.sysdate());
		
		int updCnt = 0;
		if("01".equals(rfndOccAmtCl)) {	// 선수금
			updCnt = refundMapper.updatePrepayOccRfndAmt(refundApplInfo);
		} else if ("02".equals(rfndOccAmtCl)) {	// 불명금
			updCnt = refundMapper.updateAmbgOccRfndAmt(refundApplInfo);
		} else if ("03".equals(rfndOccAmtCl)) {	// 보증금
			updCnt = refundMapper.updateAssrOccRfndAmt(refundApplInfo);
		}
		
		if(updCnt <= 0) {
			logger.debug("FAIL UPDATE REFUND AMT");
			logger.debug("환불 금액 반영 실패");
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 환불 신청내역 환불 승인 처리 - 서비스 호출전에 UPDATE 처리
	 * @param rfndApplInfo, pgmId, workId
	 * @return 
	 */
	/*private boolean updateRefundAppl(RefundApplVO refundApplInfo, String pgmId, String workId) {
		boolean result = true;
		
		// 환불 신청내역에 환불 승인을 처리한다.
		logger.debug("====================================");
		logger.debug("  updateRefundAppl Process...  ");
		logger.debug("====================================");
		
		refundApplInfo.setDcsnProcStat("04");
		refundApplInfo.setApprId(workId);
		refundApplInfo.setApprDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		refundApplInfo.setRfndCmplYn("Y");
		refundApplInfo.setRfndCmplDt(DateUtil.getDateStringYYYYMMDD(0));
		refundApplInfo.setProcPsnId(workId);
		refundApplInfo.setRegrId(workId);
		refundApplInfo.setRegDate(DateUtil.sysdate());
		
		int updCnt = refundMapper.updateRefundAppl(refundApplInfo);
		
		if(updCnt <= 0) {
			logger.debug("FAIL UPDATE TBLPY_RFND_APPL");
			logger.debug("환불 신청 내역 승인 정보 반영 실패");
			result = false;
		}

		return result;
	}*/
	
	/**
	 * 보증금 환불로 인한 보증금 차감 처리
	 * @param assrPymSeqNo, pgmId, workId
	 * @return boolean
	 */
	private boolean processMinusAssrReceipt(String assrPymSeqNo, double rfndApplAmt, String pgmId, String workId) {
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
			billListVO.setRcptAmt(rfndApplAmt);
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
			receiptDtlVO.setRcptAmt(rfndApplAmt * (-1));
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
