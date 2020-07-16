package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.payment.payment.NewSinglePaymentVO;
import com.ntels.ccbs.charge.mapper.payment.payment.NewSinglePaymentMapper;
import com.ntels.ccbs.charge.service.batchprocmng.paymentmng.EachDepositService;
import com.ntels.ccbs.charge.service.payment.payment.NewSinglePaymentService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class NewSinglePaymentServiceImpl implements NewSinglePaymentService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private NewSinglePaymentMapper newSinglePaymentMapper;

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private EachDepositService eachDepositService;

	@Override
	public List<NewSinglePaymentVO> getAnonyReceiptSubList(NewSinglePaymentVO newSinglePaymentVO) {
		return newSinglePaymentMapper.getAnonyReceiptSubList(newSinglePaymentVO);
	}

	@Override
	public List<NewSinglePaymentVO> getMyReceiptList(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getMyReceiptList(newSinglePaymentVO);
	}

	@Override
	public int getAnonyReceiptSubListCount(NewSinglePaymentVO newSinglePaymentVO) {
		return newSinglePaymentMapper.getAnonyReceiptSubListCount(newSinglePaymentVO);
	}

	@Override
	public int getMyReceiptListCount(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getMyReceiptListCount(newSinglePaymentVO);
	}

	@Override
	public List<NewSinglePaymentVO> getBillInfo(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getBillInfo(newSinglePaymentVO);
	}

	@Override
	public int getBillInfoCount(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getBillInfoCount(newSinglePaymentVO);
	}

	@Override
	public List<NewSinglePaymentVO> getPermitOrg(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getPermitOrg(newSinglePaymentVO);
	}

	@Override
	public int getPermitOrgCount(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getPermitOrgCount(newSinglePaymentVO);
	}

	@Override
	public List<NewSinglePaymentVO> getLoanAvlAmt(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getLoanAvlAmt(newSinglePaymentVO);
	}

	@Override
	public int getLoanAvlAmtCount(NewSinglePaymentVO newSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		newSinglePaymentVO.setProcDt(today);
		return newSinglePaymentMapper.getLoanAvlAmtCount(newSinglePaymentVO);
	}

	@Override
	public List<Map<String, Object>> listAnonyReceiptSubExcel(NewSinglePaymentVO newSinglePaymentVO) {
		return newSinglePaymentMapper.listAnonyReceiptSubExcel(newSinglePaymentVO);
	}

	@Override
	public List<Map<String, Object>> listMyReceiptExcel(String soId, String usrId, String lngTyp) {
		String procDt = DateUtil.getDateStringYYYYMMDD(0);
		return newSinglePaymentMapper.listMyReceiptExcel(soId, usrId, lngTyp, procDt);
	}

	@Override
	public int insertDeposit(NewSinglePaymentVO newSinglePaymentVO) {
		int result = -1;

		String eachDpstSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.PY_EACH_NO, 10);
		SessionUser session = CommonUtil.getSessionManager();
		String userId = session.getUserId();

		newSinglePaymentVO.setRegrId(userId);
		newSinglePaymentVO.setRegDate(DateUtil.sysdate());
		newSinglePaymentVO.setChgrId(userId);
		newSinglePaymentVO.setChgDate(DateUtil.sysdate());
		newSinglePaymentVO.setEachDpstSeq(eachDpstSeq);

		// insert tbl_each_dpst
		int cnt = newSinglePaymentMapper.insertEachDpst(newSinglePaymentVO);

		if (cnt > 0) {
			try {
				result = eachDepositService.processEachDeposit(eachDpstSeq, newSinglePaymentVO.getInptMenuId(), userId);
			} catch (Exception e) {
				throw new ServiceException("MSG.M10.MSG00005"); // MSG.M10.MSG00005=처리에 실패했습니다. 관리자에게 문의해 주세요.
			}
		}
		return result;
	}
}
