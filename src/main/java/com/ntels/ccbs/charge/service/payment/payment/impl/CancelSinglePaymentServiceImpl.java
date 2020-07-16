package com.ntels.ccbs.charge.service.payment.payment.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.payment.payment.CancelSinglePaymentVO;
import com.ntels.ccbs.charge.mapper.payment.payment.CancelSinglePaymentMapper;
import com.ntels.ccbs.charge.service.payment.payment.CancelSinglePaymentService;
import com.ntels.ccbs.common.util.DateUtil;

@Service
public class CancelSinglePaymentServiceImpl implements CancelSinglePaymentService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private CancelSinglePaymentMapper cancelSinglePaymentMapper;

	@Override
	public List<CancelSinglePaymentVO> getCaseByCancelList(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		return cancelSinglePaymentMapper.getCaseByCancelList(cancdlSinglePaymentVO);
	}

	@Override
	public int getCaseByCancelListCount(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		return cancelSinglePaymentMapper.getCaseByCancelListCount(cancdlSinglePaymentVO);
	}
	
	@Override
	public List<CancelSinglePaymentVO> getPermitOrg(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		cancdlSinglePaymentVO.setProcDt(today);
		return cancelSinglePaymentMapper.getPermitOrg(cancdlSinglePaymentVO);
	}

	@Override
	public int getPermitOrgCount(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		cancdlSinglePaymentVO.setProcDt(today);
		return cancelSinglePaymentMapper.getPermitOrgCount(cancdlSinglePaymentVO);
	}
	
	@Override
	public List<CancelSinglePaymentVO> getLoanAvlAmt(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		cancdlSinglePaymentVO.setProcDt(today);
		return cancelSinglePaymentMapper.getLoanAvlAmt(cancdlSinglePaymentVO);
	}

	@Override
	public int getLoanAvlAmtCount(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		cancdlSinglePaymentVO.setProcDt(today);
		return cancelSinglePaymentMapper.getLoanAvlAmtCount(cancdlSinglePaymentVO);
	}
	
	@Override
	public List<CancelSinglePaymentVO> getRcptCnclCnt(CancelSinglePaymentVO cancdlSinglePaymentVO) {
		return cancelSinglePaymentMapper.getRcptCnclCnt(cancdlSinglePaymentVO);
	}
	
	@Override
	public List<Map<String,Object>> getCaseByCancelListExcel(CancelSinglePaymentVO cancdlSinglePaymentVO, String lngTyp) {
		return cancelSinglePaymentMapper.getCaseByCancelListExcel(cancdlSinglePaymentVO, lngTyp);
	}
}
