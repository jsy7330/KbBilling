package com.ntels.ccbs.product.service.service.serviceMgt.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.service.serviceMgt.WorkDefMngVO;
import com.ntels.ccbs.product.mapper.service.serviceMgt.WorkDefMngMapper;
import com.ntels.ccbs.product.service.service.serviceMgt.WorkDefMngService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class WorkDefMngServiceImpl implements WorkDefMngService {
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** the mapper. */
	@Autowired
	private WorkDefMngMapper workDefMngMapper;		
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<WorkDefMngVO> workDefMngListAction(WorkDefMngVO workDefMngVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return workDefMngMapper.workDefMngListAction(workDefMngVO, start, end);
	}
	
	@Override
	public int workDefMngListCount(WorkDefMngVO workDefMngVO) {
		return workDefMngMapper.workDefMngListCount(workDefMngVO);
	}

	@Override
	public int insertWorkDefMngInfo(WorkDefMngVO workDefMngVO) {	
		
		workDefMngVO.setWrkId(sequenceService.createNewSequence(Consts.SEQ_CODE.PD_TPMPD_WRK, "W", 10));
		workDefMngVO.setRegDate(DateUtil.sysdate());
		workDefMngVO.setChgDate(DateUtil.sysdate());
				
		int count = workDefMngMapper.insertWorkDefMngInfo(workDefMngVO);
		return count;
	}

	@Override
	public int updateWorkDefMngInfo(WorkDefMngVO workDefMngVO) {
		workDefMngVO.setChgDate(DateUtil.sysdate());
				
		int count = workDefMngMapper.updateWorkDefMngInfo(workDefMngVO);
		return count;
	}

	@Override
	public int deleteWorkDefMngInfo(WorkDefMngVO workDefMngVO) {
				
		int count = workDefMngMapper.deleteWorkDefMngInfo(workDefMngVO);
		return count;
	}
}
