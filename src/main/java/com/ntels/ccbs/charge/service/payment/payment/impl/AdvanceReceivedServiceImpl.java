package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billing.BillingStatisticsVO;
import com.ntels.ccbs.charge.domain.payment.advanceReceived.AdvanceReceivedVO;
import com.ntels.ccbs.charge.mapper.payment.payment.AdvanceReceivedMapper;
import com.ntels.ccbs.charge.service.batchprocmng.ambgtransmng.AmbgTransService;
import com.ntels.ccbs.charge.service.batchprocmng.prepaytransmng.PrepayTransService;
import com.ntels.ccbs.charge.service.payment.payment.AdvanceReceivedService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.consts.Consts.AMBG_REPL_TP;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.StringUtil;

@Service
public class AdvanceReceivedServiceImpl implements AdvanceReceivedService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private AdvanceReceivedMapper advanceReceivedMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private PrepayTransService prepayTransService;

	@Autowired
	private AmbgTransService ambgTransService;


	@Override
	public int getPrepayOccCount(AdvanceReceivedVO advanceReceivedVO) {
		return advanceReceivedMapper.getPrepayOccCount(advanceReceivedVO);
	}

	@Override
	public List<AdvanceReceivedVO> getPrepayOccList(AdvanceReceivedVO advanceReceivedVO, int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return advanceReceivedMapper.getPrepayOccList(advanceReceivedVO, start, end);
	}

	@Override
	public int getBillInvoiceCount(BillingStatisticsVO billingStatisticsVO) {
		return advanceReceivedMapper.getBillInvoiceCount(billingStatisticsVO);
	}

	@Override
	public List<BillingStatisticsVO> getBillInvoiceList(BillingStatisticsVO billingStatisticsVO, int page, int perPage) {
		int start = 0;
		int end = 0;

		start = (page - 1) * perPage;
		end = perPage;

		return advanceReceivedMapper.getBillInvoiceList(billingStatisticsVO, start, end);
	}

	@Override
	public int getRefundAppliedCnt(String seqNo) {
		return advanceReceivedMapper.getRefundAppliedCnt(seqNo);
	}

	@Override
	public int insertAction(BillingStatisticsVO billingStatisticsVO) {

		int result = -1;

		SessionUser session = CommonUtil.getSessionManager();
		String userId = session.getUserId();
		String userNm = session.getUserName();
		String seqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_TRNS_NO, 10);

		billingStatisticsVO.setRegrId(userId);
		billingStatisticsVO.setRegDate(DateUtil.sysdate());
		billingStatisticsVO.setChgrId(userId);
		billingStatisticsVO.setChgDate(DateUtil.sysdate());
		billingStatisticsVO.setAppntId(userId);
		billingStatisticsVO.setAppntNm(userNm);
		billingStatisticsVO.setTransApplNo(seqNo);
		billingStatisticsVO.setDcsnProcStat(Consts.DCSN_PROC_STAT.APPLIED); // 결제처리상태 BL00018 : 신청 = "01"

		int cnt = advanceReceivedMapper.insertAction(billingStatisticsVO);

		if (cnt > 0) {
			String transApplCl = billingStatisticsVO.getTransApplCl();

			try {
				if (Consts.TRANS_APPL_CL.PRE_PAY.equals(transApplCl)) {
					String typeCd = Consts.AMBG_REPL_TP.PRE_PAY_REP_RECEIPT;

					if (!StringUtil.isEmpty(billingStatisticsVO.getTransTp()) && "02".equals(billingStatisticsVO.getTransTp())) {
						typeCd = Consts.AMBG_REPL_TP.PRE_PAY_PYM_ACNT;
					}

					result = prepayTransService.processPrepayTrans(seqNo, typeCd, billingStatisticsVO.getInptMenuId(), userId);
				} else if (Consts.TRANS_APPL_CL.AMBG.equals(transApplCl)) {
					result = ambgTransService.processAmbgTrans(seqNo, AMBG_REPL_TP.AMBG_REP_RECEIPT, billingStatisticsVO.getInptMenuId(), userId);
				}
			} catch (Exception e) {
				throw new ServiceException("MSG.M10.MSG00005"); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
			}
		}
		return result;
	}
}
