package com.ntels.ccbs.charge.mapper.charge.batch;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchProgramVO;

@Component
public interface BatchProgramMapper {

	int getBatPgmIdCount(BatchProgramVO batchProgramVO);
	
	int getBatchProgramCount(BatchProgramVO batchProgramVO);
	
	List<BatchProgramVO> getBatchProgramList(BatchProgramVO batchProgramVO);
	
	int insertBatchProgram(BatchProgramVO batchProgramVO);
	
	int updateBatchProgram(BatchProgramVO batchProgramVO);
	
	int deleteBatchProgram(BatchProgramVO batchProgramVO);
	
	List<BatchProgramVO> getBatchProgramForBatGrp(BatchGroupVO batchGroupVO);
	
}
