package com.ntels.ccbs.charge.service.charge.batch;

import java.util.List;

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchProgramVO;

public interface BatchProgramService {

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getBatchProgramPaging
	 * 2. ClassName : BatchProgramService
	 * 3. Comment   : 배치프로그램 리스트 페이징 정보 생성
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 24. 오후 4:52:47
	 * </PRE>
	 *   @return BatchProgramVO
	 *   @param batchProgramVO
	 *   @return
	 */
	BatchProgramVO getBatchProgramPaging(BatchProgramVO batchProgramVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getBatchProgramList
	 * 2. ClassName : BatchProgramService
	 * 3. Comment   : 배치프로그램 리스트 검색
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 24. 오후 4:52:44
	 * </PRE>
	 *   @return List<BatchProgramVO>
	 *   @param batchProgramVO
	 *   @return
	 */
	List<BatchProgramVO> getBatchProgramList(BatchProgramVO batchProgramVO);

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertBatchProgram
	 * 2. ClassName : BatchProgramService
	 * 3. Comment   : 신규 배치프로그램 등록
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 24. 오후 4:52:41
	 * </PRE>
	 *   @return void
	 *   @param batchProgramVO
	 */
	void insertBatchProgram(BatchProgramVO batchProgramVO);

	/**
	 * <PRE>
	 * 1. MethodName: updateBatchProgram
	 * 2. ClassName : BatchProgramService
	 * 3. Comment   : 배치프로그램 내용 수정
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 24. 오후 4:52:18
	 * </PRE>
	 *   @return void
	 *   @param batchProgramVO
	 */
	void updateBatchProgram(BatchProgramVO batchProgramVO);

	/**
	 * <PRE>
	 * 1. MethodName: deleteBatchProgram
	 * 2. ClassName : BatchProgramService
	 * 3. Comment   : 배치프로그램 삭제
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 24. 오후 4:56:37
	 * </PRE>
	 *   @return void
	 *   @param batchProgramVO
	 */
	void deleteBatchProgram(BatchProgramVO batchProgramVO);

	/**
	 * <PRE>
	 * 1. MethodName: getBatchProgramForBatGrp
	 * 2. ClassName : BatchProgramService
	 * 3. Comment   : 배치 그룹 등록을 위한 프로그램 목록 조회
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2017. 3. 27. 오전 10:34:16
	 * </PRE>
	 *   @return List<BatchProgramVO>
	 *   @param batchGroupVO
	 *   @return
	 */
	List<BatchProgramVO> getBatchProgramForBatGrp(BatchGroupVO batchGroupVO);

}
