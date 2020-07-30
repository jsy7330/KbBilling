package com.ntels.ccbs.charge.mapper.charge.batch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchJobMngVO;

@Component
public interface BatchGroupMapper {

	int getBatGrpIdCount(BatchGroupVO batchGroupVO);
	
	int getBatchGroupCount(BatchGroupVO batchGroupVO);
	
	List<BatchGroupVO> getBatchGroupList(BatchGroupVO batchGroupVO);
	
	int insertBatchGroup(BatchGroupVO batchGroupVO);
	
	int updateBatchGroup(BatchGroupVO batchGroupVO);
	
	int deleteBatchGroup(BatchGroupVO batchGroupVO);

	void updatefinishInfoMng(@Param("batchJobMngVO")BatchJobMngVO batchJobMngVO);

	Integer finishInfoListCount(@Param("soAuthList")List<Map<String, Object>> soAuthList,@Param("batchJobMngVO")BatchJobMngVO batchJobMngVO);

	List<Map<String, Object>> finishInfoList(@Param("soAuthList")List<Map<String, Object>> soAuthList, @Param("batchJobMngVO")BatchJobMngVO batchJobMngVO,
			@Param("end")String end,@Param("start") String start);
	
}
