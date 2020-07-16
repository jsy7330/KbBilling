package com.ntels.ccbs.distribute.mapper.logistics.voucherMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.UpdProcHistVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VeqtVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VoucherMasterVO;

public interface VoucherMasterMngMapper {

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: vissueListCount
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 조회대상 제품 수
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 1:46:09
	 * </PRE>
	 *   @return Integer
	 *   @param vissueVO
	 *   @return
	 */
	Integer vissueListCount(@Param("vissueVO") VissueVO vissueVO); 
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getVissueList
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 바우쳐발행팝업 제품조회
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 2:03:28
	 * </PRE>
	 *   @return List<VissueVO>
	 *   @param vissueVO
	 *   @param start
	 *   @param end
	 *   @return
	 */
	List<VissueVO> getVissueList(@Param("vissueVO") VissueVO vissueVO, @Param("start") int start, @Param("end") int end);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getVeqtInfoCount
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 바우쳐마스터 정보의 수를 조회한다.
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 3:45:26
	 * </PRE>
	 *   @return Integer
	 *   @param vissueVO
	 *   @return
	 */
	Integer getVeqtInfoCount(@Param("vissueVO") VissueVO vissueVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getVeqtInfoList
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 바우쳐마스터 정보를 조회한다.
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 3:45:31
	 * </PRE>
	 *   @return List<VoucherMaster>
	 *   @param vissueVO
	 *   @param start
	 *   @param end
	 *   @return
	 */
	List<VoucherMasterVO> getVeqtInfoList(@Param("vissueVO") VissueVO vissueVO, @Param("start") int start, @Param("end") int end);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getSaleProcessPopVeqtCount
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 바우쳐팝업 정보의 수를 조회한다.(바우쳐마스터관리/판매처리)
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 4:29:14
	 * </PRE>
	 *   @return Integer
	 *   @param voucherMaster
	 *   @return
	 */
	Integer getSaleProcessPopVeqtCount(@Param("voucherMaster") VoucherMasterVO voucherMaster);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getSalesProcessPopVeqtList
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 바우쳐팝업 정보를 조회한다.(바우쳐마스터관리/판매처리)
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 4:30:00
	 * </PRE>
	 *   @return List<VoucherMaster>
	 *   @param voucherMaster
	 *   @param start
	 *   @param end
	 *   @return
	 */
	List<VoucherMasterVO> getSalesProcessPopVeqtList(@Param("voucherMaster") VoucherMasterVO voucherMaster, @Param("start") int start, @Param("end") int end);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: updateVeqtInfoVoStatLgst
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 바우쳐마스터 정보를 저장한다.(바우쳐상태, 물류처리상태)
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 22. 오전 10:52:31
	 * </PRE>
	 *   @return int
	 *   @param voucherMaster
	 *   @return
	 */
	int updateVeqtInfoVoStatLgst(@Param("voucherMaster") VoucherMasterVO voucherMaster);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getMaxCrtSeqNo
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : CRT_SEQ_NO의 신규발급을 위한 기존 값 조회
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 22. 오전 11:05:29
	 * </PRE>
	 *   @return String
	 *   @param veqtVO
	 *   @return
	 */
	String getMaxCrtSeqNo(@Param("veqtVO") VeqtVO veqtVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getVeqtTransInfoInit_voSeqNo
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 바우쳐이력을 조회한다.(바우쳐마스터 해당 바우쳐일련번호 등록)
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 22. 오전 11:12:19
	 * </PRE>
	 *   @return List<VeqtVO>
	 *   @param veqtVO
	 *   @return
	 */
	List<VeqtVO> getVeqtTransInfoInit_voSeqNo(@Param("veqtVO") VeqtVO veqtVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: addVeqtTransInfoInit_voSeqNo
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 바우쳐이력을 등록한다.(바우쳐마스터 해당 바우쳐일련번호 등록)
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 22. 오전 11:12:44
	 * </PRE>
	 *   @return int
	 *   @param veqtVO
	 *   @return
	 */
	int addVeqtTransInfoInit_voSeqNo(@Param("veqtVO") VeqtVO veqtVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertUpdProcHistInfo
	 * 2. ClassName : VoucherMasterMngMapper
	 * 3. Comment   : 단말기 상태수정이력 등록
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 22. 오전 11:38:13
	 * </PRE>
	 *   @return int
	 *   @param updProcHistVO
	 *   @return
	 */
	int insertUpdProcHistInfo(@Param("updProcHistVO") UpdProcHistVO updProcHistVO);
	
}
