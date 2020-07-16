package com.ntels.ccbs.charge.mapper.charge.batch;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchWorkMapVO;

@Component
public interface BatchWorkMapMapper {

	int getBatchWorkMapCount(BatchGroupVO batchGroupVO);
	
	List<BatchWorkMapVO> getBatchWorkMapList(BatchGroupVO batchGroupVO);
	
	int deleteByBatGrpId(BatchGroupVO batchGroupVO);
	
	int insertBatchWorkMap(BatchWorkMapVO batchWorkMapVO);
	
}
