package com.ntels.ccbs.charge.service.charge.batch;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchJobMngVO;
import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;

public interface BatchGroupService {

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getBatchGroupPaging
	 * 2. ClassName : BatchGroupService
	 * 3. Comment   : 배치 그룹 리스트 페이징 정보 생성
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 27. 오전 9:58:29
	 * </PRE>
	 *   @return BatchGroupVO
	 *   @param batchGroupVO
	 *   @return
	 */
	BatchGroupVO getBatchGroupPaging(BatchGroupVO batchGroupVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getBatchGroupList
	 * 2. ClassName : BatchGroupService
	 * 3. Comment   : 배치 그룹 리스트 조회
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 27. 오전 9:58:35
	 * </PRE>
	 *   @return List<BatchGroupVO>
	 *   @param batchGroup
	 *   @return
	 */
	List<BatchGroupVO> getBatchGroupList(BatchGroupVO batchGroup);

	/**
	 * <PRE>
	 * 1. MethodName: insertBatchGroup
	 * 2. ClassName : BatchGroupService
	 * 3. Comment   : 신규 배치 그룹 등록
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 27. 오전 9:58:11
	 * </PRE>
	 *   @return void
	 *   @param batchGroupVO
	 */
	
	void insertBatchGroup(BatchGroupVO batchGroupVO);

	/**
	 * <PRE>
	 * 1. MethodName: updateBatchGroup
	 * 2. ClassName : BatchGroupService
	 * 3. Comment   : 배치그룹 정보 수정
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 27. 오전 10:10:10
	 * </PRE>
	 *   @return void
	 *   @param batchGroupVO
	 */
	
	void updateBatchGroup(BatchGroupVO batchGroupVO);

	/**
	 * <PRE>
	 * 1. MethodName: deleteBatchGroup
	 * 2. ClassName : BatchGroupService
	 * 3. Comment   : 배치그룹 삭제
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 27. 오전 10:10:15
	 * </PRE>
	 *   @return void
	 *   @param batchGroupVO
	 */
	
	void deleteBatchGroup(BatchGroupVO batchGroupVO);

	void updatefinishInfoMng(BatchJobMngVO batchJobMngVO);

	Map<String, Object> getChargeList(List<Map<String, Object>> soAuthList, BatchJobMngVO batchJobMngVO);
}
