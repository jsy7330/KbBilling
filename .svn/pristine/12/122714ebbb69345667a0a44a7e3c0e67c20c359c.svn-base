package com.ntels.ccbs.charge.service.charge.batch.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchProgramVO;
import com.ntels.ccbs.charge.mapper.charge.batch.BatchProgramMapper;
import com.ntels.ccbs.charge.service.charge.batch.BatchProgramService;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.nisf.util.message.MessageUtil;

@Service
public class BatchProgramServiceImpl implements BatchProgramService {

	@Autowired
	private BatchProgramMapper batchProgramMapper;
	
	@Override
	public BatchProgramVO getBatchProgramPaging(BatchProgramVO batchProgramVO) {
		
		int totalCount = batchProgramMapper.getBatchProgramCount(batchProgramVO);
		batchProgramVO.setTotalCount(totalCount);
		
		return batchProgramVO;
		
	}
	
	@Override
	public List<BatchProgramVO> getBatchProgramList(BatchProgramVO batchProgramVO) {
		return batchProgramMapper.getBatchProgramList(batchProgramVO);
	}
	
	private void checkEditBatchProgram(BatchProgramVO batchProgramVO) {
		if (StringUtils.isEmpty(batchProgramVO.getBatPgmId()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M13.LAB00035") };
			throw new ServiceException("MSG.M13.MSG00025", args);
		}
		
		if (StringUtils.isEmpty(batchProgramVO.getPgmNm()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M13.LAB00022") };
			throw new ServiceException("MSG.M13.MSG00025", args);			
		}
		
		if (StringUtils.isEmpty(batchProgramVO.getExecObj()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M13.LAB00022") };
			throw new ServiceException("MSG.M13.MSG00025", args);			
		}
		
		if (StringUtils.isEmpty(batchProgramVO.getPkgNm()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M13.LAB00014") };
			throw new ServiceException("MSG.M13.MSG00025", args);			
		}
		
		if (StringUtils.isEmpty(batchProgramVO.getClsStpCl()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M05.LAB00056") };
			throw new ServiceException("MSG.M13.MSG00025", args);			
		}

		// 마감설정이 "마감설정안함"외의 경우에는 체크 해야함.
		if (batchProgramVO.getClsStpCl().equals("9") == false) {
			if (StringUtils.isEmpty(batchProgramVO.getClsTskCl()) == true) {
				String[] args = new String[] { MessageUtil.getMessage("LAB.M05.LAB00057") };
				throw new ServiceException("MSG.M13.MSG00025", args);			
			}	
		}
		
		if (StringUtils.isEmpty(batchProgramVO.getPgmCt()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M13.LAB00038") };
			throw new ServiceException("MSG.M13.MSG00025", args);			
		}
	}
	
	private void checkExistsBatPgmId(BatchProgramVO batchProgramVO) {
		if (batchProgramMapper.getBatPgmIdCount(batchProgramVO) <= 0) {
			throw new ServiceException("MSG.M09.MSG00062");
		}
	}
	
	@Override
	public void insertBatchProgram(BatchProgramVO batchProgramVO) {
		
		checkEditBatchProgram(batchProgramVO);
		
		int pgmIdCnt = batchProgramMapper.getBatPgmIdCount(batchProgramVO);
		
		if (pgmIdCnt > 0) {
			throw new ServiceException("MSG.M15.MSG00024");
		}
		
		Date now = new Date();
		batchProgramVO.setChgDate(now);
		batchProgramVO.setRegDate(now);
		
		batchProgramMapper.insertBatchProgram(batchProgramVO);
		
	}
	
	@Override
	public void updateBatchProgram(BatchProgramVO batchProgramVO) {
		checkEditBatchProgram(batchProgramVO);
		checkExistsBatPgmId(batchProgramVO);
		
		batchProgramVO.setChgDate(new Date());
		
		batchProgramMapper.updateBatchProgram(batchProgramVO);
	}
	
	@Override
	public void deleteBatchProgram(BatchProgramVO batchProgramVO) {
		if (StringUtils.isEmpty(batchProgramVO.getBatPgmId()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M13.LAB00035") };
			throw new ServiceException("MSG.M13.MSG00025", args);
		}
		
		checkExistsBatPgmId(batchProgramVO);
		
		batchProgramMapper.deleteBatchProgram(batchProgramVO);
	}
	
	@Override
	public List<BatchProgramVO> getBatchProgramForBatGrp(BatchGroupVO batchGroupVO) {
		return batchProgramMapper.getBatchProgramForBatGrp(batchGroupVO);
	}
	
}
