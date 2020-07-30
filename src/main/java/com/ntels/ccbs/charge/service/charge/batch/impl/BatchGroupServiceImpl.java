package com.ntels.ccbs.charge.service.charge.batch.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchJobMngVO;
import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;
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

	@Override
	public Map<String, Object> getChargeList(List<Map<String, Object>> soAuthList, BatchJobMngVO batchJobMngVO){
		
		Map<String,Object> chargeInfo = new HashMap<String,Object>();
		Integer totalCount = batchGroupMapper.finishInfoListCount(soAuthList,batchJobMngVO);
		/*
		   	page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			chargeInfo.put("chargeList", new ArrayList<Map<String,Object>>());
			chargeInfo.put("totalCount", totalCount);
			chargeInfo.put("totalPages", new Integer(0));
			chargeInfo.put("page", new Integer(1));
		}else{
			int endIndex = batchJobMngVO.getRows();
			int startIndex = (batchJobMngVO.getPage()-1) * batchJobMngVO.getRows();
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<Map<String,Object>> finishInfo = batchGroupMapper.finishInfoList(soAuthList,batchJobMngVO,end,start);
			
			chargeInfo.put("finishInfo", finishInfo); 
			chargeInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)batchJobMngVO.getRows()));
			chargeInfo.put("totalPages", totalPages);
			chargeInfo.put("page", new Integer(batchJobMngVO.getPage()));
		}
		
		 
		return chargeInfo;
	}
	
	@Override
	public void updatefinishInfoMng(BatchJobMngVO batchJobMngVO) {
		batchGroupMapper.updatefinishInfoMng(batchJobMngVO);
	}
	
}
