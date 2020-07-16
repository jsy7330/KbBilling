package com.ntels.ccbs.charge.service.charge.batch.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchWorkMapVO;
import com.ntels.ccbs.charge.mapper.charge.batch.BatchWorkMapMapper;
import com.ntels.ccbs.charge.service.charge.batch.BatchWorkMapService;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.nisf.util.message.MessageUtil;

@Service
public class BatchWorkMapServiceImpl implements BatchWorkMapService {

	@Autowired
	private BatchWorkMapMapper batchWorkMapMapper;
	
	private void checkBatchGroup(BatchGroupVO batchGroupVO) {
		if (StringUtils.isEmpty(batchGroupVO.getBatGrpId()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M06.LAB00121") };
			throw new ServiceException("MSG.M13.MSG00025", args);
		}
	}
	
	@Override
	public List<BatchWorkMapVO> getBatchWorkMapByBatchGroup(BatchGroupVO batchGroupVO) {
		checkBatchGroup(batchGroupVO);
		return batchWorkMapMapper.getBatchWorkMapList(batchGroupVO);
	}
	
	@Override
	public void deleteByBatchGroup(BatchGroupVO batchGroupVO) {
		checkBatchGroup(batchGroupVO);
		batchWorkMapMapper.deleteByBatGrpId(batchGroupVO);
	}
	
	@Override
	public void insertBatchWorkMap(BatchGroupVO batchGroupVO) {
		checkBatchGroup(batchGroupVO);
		
		for (BatchWorkMapVO batchWorkMapVO : batchGroupVO.getBatchWorkMapList()) {
			batchWorkMapVO.setBatGrpId(batchGroupVO.getBatGrpId());
			batchWorkMapVO.setRegrId(batchGroupVO.getChgrId());
			batchWorkMapVO.setChgrId(batchGroupVO.getChgrId());
			batchWorkMapMapper.insertBatchWorkMap(batchWorkMapVO);
		}
	}
	
}
