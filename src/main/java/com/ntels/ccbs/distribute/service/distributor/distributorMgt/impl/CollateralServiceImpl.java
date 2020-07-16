package com.ntels.ccbs.distribute.service.distributor.distributorMgt.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.CollateralVO;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.CollateralMapper;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.CollateralService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class CollateralServiceImpl implements CollateralService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CollateralMapper collateralMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<CollateralVO> collateralList(CollateralVO collateralVO,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return collateralMapper.collateralList(collateralVO, start, end);
	}

	@Override
	public int collateralCount(CollateralVO collateralVO) {
		return collateralMapper.collateralCount(collateralVO);
	}

	@Override
	public int insertCollInfo(CollateralVO collateralVO, SessionUser session) {
		
		Integer collOrg = sequenceService.createNewSubSequence("TDNDI_COLL_INFO", "COLL_ORG", collateralVO.getOrgId());
		collateralVO.setCollOrg(String.valueOf(collOrg));
		
		int count = collateralMapper.insertCollInfo(collateralVO);
		
		collateralVO.setLoanKndCd("10");		//단말여신
		collateralVO.setLoanTpCd("10");			//일반여신
		collateralVO.setLoanStpAmt(collateralVO.getLoanAmt());	//단말여신금액
		
		/* 단말여신 */
		int loanInfoCount = collateralMapper.loanInfoCount(collateralVO);
		if(loanInfoCount > 0){
			collateralMapper.updateLoanInfo(collateralVO);
		}else{
			collateralMapper.insertLoanInfo(collateralVO);
		}
		
		Integer loanOrd = sequenceService.createNewSubSequence("TDNDI_LOAN_DETAIL_INFO", "LOAN_ORD", collateralVO.getOrgId());
		collateralVO.setLoanOrd(String.valueOf(loanOrd));
		collateralVO.setLoanApprrId(session.getUserId());
		collateralVO.setLoanApprrNm(session.getUserName());
		collateralVO.setLoanStpResn(collateralVO.getCollOrg() + "|" + collateralVO.getLoanStpResn());
		collateralVO.setSysdate(DateUtil.getDateStringYYYYMMDD(0));
		
		collateralMapper.insertLoanDetailInfo(collateralVO);
		collateralMapper.updateLoanInfo2(collateralVO);
		
		/*요금여신 */
		collateralVO.setLoanKndCd("20");		//요금여신
		if(loanInfoCount > 0){
			collateralMapper.updateLoanInfo(collateralVO);
		}else{
			collateralMapper.insertLoanInfo(collateralVO);
		}
		collateralMapper.insertLoanDetailInfo(collateralVO);
		collateralMapper.updateLoanInfo2(collateralVO);
		
		return count;
	}

	@Override
	public CollateralVO selectOrgInfo(CollateralVO collateralVO) {
		return collateralMapper.selectOrgInfo(collateralVO);
	}

	@Override
	public CollateralVO selectCollInfo(CollateralVO collateralVO) {
		return collateralMapper.selectCollInfo(collateralVO);
	}

	@Override
	public int updateCollInfo(CollateralVO collateralVO, SessionUser session) {
		
		int count = collateralMapper.updateCollInfo(collateralVO);
		
		collateralVO.setLoanKndCd("10");		//단말여신
		collateralVO.setLoanTpCd("10");			//일반여신
		collateralVO.setLoanStpAmt(collateralVO.getLoanAmt());	//단말여신금액
		collateralVO.setLoanStpResn(collateralVO.getCollOrg() + "|" + collateralVO.getLoanStpResn());
		collateralVO.setSysdate(DateUtil.getDateStringYYYYMMDD(0));
		
		collateralMapper.updateLoanDetailInfo(collateralVO);
		collateralMapper.updateLoanInfo2(collateralVO);
		
		collateralVO.setLoanKndCd("20");		//요금여신
		collateralMapper.updateLoanDetailInfo(collateralVO);
		collateralMapper.updateLoanInfo2(collateralVO);
		
		return count;
	}

	@Override
	public int deleteCollInfo(CollateralVO collateralVO, SessionUser session) {

		int count = collateralMapper.deleteCollInfo(collateralVO);
		
		collateralVO.setLoanKndCd("10");		//단말여신
		collateralVO.setLoanTpCd("10");			//일반여신
		collateralVO.setLoanStpAmt(collateralVO.getLoanAmt());	//단말여신금액
		collateralVO.setLoanStpResn(collateralVO.getCollOrg() + "|" + collateralVO.getLoanStpResn());
		collateralVO.setSysdate(DateUtil.getDateStringYYYYMMDD(0));
		
		collateralMapper.deleteLoanDetailInfo(collateralVO);
		collateralMapper.updateLoanInfo2(collateralVO);
		
		collateralVO.setLoanKndCd("20");		//요금여신
		collateralMapper.deleteLoanDetailInfo(collateralVO);
		collateralMapper.updateLoanInfo2(collateralVO);
		
		return count;
	}
	
	
	
}
