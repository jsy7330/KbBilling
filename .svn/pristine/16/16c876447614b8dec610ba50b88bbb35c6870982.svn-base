package com.ntels.ccbs.charge.service.charge.batch.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.mapper.charge.batch.BatchGroupMapper;
import com.ntels.ccbs.charge.service.charge.batch.BatchGroupService;
import com.ntels.ccbs.charge.service.charge.batch.BatchWorkMapService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;

@Service
public class BatchGroupServiceImpl implements BatchGroupService {

	@Autowired
	private BatchGroupMapper batchGroupMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private BatchWorkMapService batchWorkMapService;
	
	@Override
	public BatchGroupVO getBatchGroupPaging(BatchGroupVO batchGroupVO) {
		
		int totalCount = batchGroupMapper.getBatchGroupCount(batchGroupVO);
		batchGroupVO.setTotalCount(totalCount);
		
		return batchGroupVO;
	}
	
	@Override
	public List<BatchGroupVO> getBatchGroupList(BatchGroupVO batchGroup) {
		return batchGroupMapper.getBatchGroupList(batchGroup);
	}
	
	private void checkEditBatchGroup(BatchGroupVO batchGroupVO) {
		
		if (StringUtils.isEmpty(batchGroupVO.getBatGrpNm()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M06.LAB00122") };
			throw new ServiceException("MSG.M13.MSG00025", args);
		}
		
		if (StringUtils.isEmpty(batchGroupVO.getBatProcTp()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M06.LAB00124") };
			throw new ServiceException("MSG.M13.MSG00025", args);
		}
		
		if (StringUtils.isEmpty(batchGroupVO.getBatGrpTp()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M06.LAB00123") };
			throw new ServiceException("MSG.M13.MSG00025", args);
		}
		
	}
	
	private void checkBatGrpId(BatchGroupVO batchGroupVO) {
		if (StringUtils.isEmpty(batchGroupVO.getBatGrpId()) == true) {
			String[] args = new String[] { MessageUtil.getMessage("LAB.M06.LAB00121") };
			throw new ServiceException("MSG.M13.MSG00025", args);
		}
		
		if (batchGroupMapper.getBatGrpIdCount(batchGroupVO) <= 0) {
			throw new ServiceException("MSG.M09.MSG00063");
		}
	}
	
	@Override
	public void insertBatchGroup(BatchGroupVO batchGroupVO) {
		checkEditBatchGroup(batchGroupVO);
		int seq = sequenceService.createNewSequence(Consts.SEQ_CODE.BAT_GRP_SEQ_ID);
		
		String batGrpId = String.format("%05d", seq);
		
		batchGroupVO.setBatGrpId(batGrpId);
		
		Date now = new Date();
		batchGroupVO.setRegDate(now);
		batchGroupVO.setChgDate(now);
		
		batchGroupMapper.insertBatchGroup(batchGroupVO);
		
		// BatchWorkMap 리스트 등록
		batchWorkMapService.insertBatchWorkMap(batchGroupVO);
	}
	
	@Override
	public void updateBatchGroup(BatchGroupVO batchGroupVO) {
		checkEditBatchGroup(batchGroupVO);
		checkBatGrpId(batchGroupVO);
		
		// 워크 맵 리스트 삭제 후 재등록
		batchWorkMapService.deleteByBatchGroup(batchGroupVO);
		batchWorkMapService.insertBatchWorkMap(batchGroupVO);
		
		// 변경사항 저장
		batchGroupMapper.updateBatchGroup(batchGroupVO);
	}
	
	@Override
	public void deleteBatchGroup(BatchGroupVO batchGroupVO) {
		checkBatGrpId(batchGroupVO);
		
		// BatchWorkMap에서 삭제
		batchWorkMapService.deleteByBatchGroup(batchGroupVO);
		
		// 배치그룹 삭제
		batchGroupMapper.deleteBatchGroup(batchGroupVO);
	}
	
}
