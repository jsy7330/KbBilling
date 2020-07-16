package com.ntels.ccbs.appIf.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.appIf.domain.common.IfLogVO;
import com.ntels.ccbs.appIf.mapper.common.AppLogMapper;
import com.ntels.ccbs.appIf.service.common.AppLogService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class AppLogServiceImpl implements AppLogService {

	@Autowired
	private AppLogMapper appLogMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public int nsSaveInsertLog(IfLogVO ifLog) {
		// TODO Auto-generated method stub
		
		String seq = sequenceService.createNewSequence(Consts.SEQ_CODE.SEQ_TSYIF_REQUEST_LOG, 10);
		ifLog.setSeq(seq);
		
		return appLogMapper.insertAppLog(ifLog); 
	}
	
	
}
