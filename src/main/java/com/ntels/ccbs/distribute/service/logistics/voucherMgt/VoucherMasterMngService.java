package com.ntels.ccbs.distribute.service.logistics.voucherMgt;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VoucherMasterVO;

/**
 * 
 * <PRE>
 * 1. ClassName: VoucherMasterMngService
 * 2. FileName : VoucherMasterMngService.java
 * 3. Package  : com.ntels.ccbs.distribute.service.logistics.voucherMgt
 * 4. Comment  : 바우처마스터관리 서비스
 * 5. 작성자   : Cashyalla
 * 6. 작성일   : 2016. 8. 19. 오후 1:30:35
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Cashyalla :    2016. 8. 19.    : 신규개발
 * </PRE>
 */
public interface VoucherMasterMngService {

	/**
	 * 
	 * <PRE>
	 * 1. MethodName: vissueListCount
	 * 2. ClassName : VoucherMasterMngService
	 * 3. Comment   : 조회대상 제품 수
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 1:46:41
	 * </PRE>
	 *   @return Integer
	 *   @param vissueVO
	 *   @return
	 */
	Integer vissueListCount(VissueVO vissueVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getVissueList
	 * 2. ClassName : VoucherMasterMngService
	 * 3. Comment   : 바우쳐발행팝업 제품조회
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 1:30:38
	 * </PRE>
	 *   @return List<VissueVO>
	 *   @param vissueVO
	 *   @return
	 */
	List<VissueVO> getVissueList(VissueVO vissueVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getVeqtInfoCount
	 * 2. ClassName : VoucherMasterMngService
	 * 3. Comment   : 바우쳐마스터 정보의 수를 조회한다.
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 3:48:01
	 * </PRE>
	 *   @return Integer
	 *   @param vissueVO
	 *   @return
	 */
	Integer getVeqtInfoCount(VissueVO vissueVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getVeqtInfoList
	 * 2. ClassName : VoucherMasterMngService
	 * 3. Comment   :
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 3:48:41
	 * </PRE>
	 *   @return List<VoucherMaster>
	 *   @param vissueVO
	 *   @return
	 */
	List<VoucherMasterVO> getVeqtInfoList(VissueVO vissueVO);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getSaleProcessPopVeqtCount
	 * 2. ClassName : VoucherMasterMngService
	 * 3. Comment   : 바우쳐팝업 정보의 수를 조회한다.(바우쳐마스터관리/판매처리)
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 4:29:14
	 * </PRE>
	 *   @return Integer
	 *   @param voucherMaster
	 *   @return
	 */
	Integer getSaleProcessPopVeqtCount(VoucherMasterVO voucherMaster);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getSalesProcessPopVeqtList
	 * 2. ClassName : VoucherMasterMngService
	 * 3. Comment   : 바우쳐팝업 정보를 조회한다.(바우쳐마스터관리/판매처리)
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 19. 오후 4:31:08
	 * </PRE>
	 *   @return List<VoucherMaster>
	 *   @param voucherMaster
	 *   @return
	 */
	List<VoucherMasterVO> getSalesProcessPopVeqtList(VoucherMasterVO voucherMaster);
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: saveSalesProcessVeqtInfo
	 * 2. ClassName : VoucherMasterMngService
	 * 3. Comment   : 바우쳐판매처리정보 저장
	 * 4. 작성자    : Cashyalla
	 * 5. 작성일    : 2016. 8. 22. 오전 9:08:32
	 * </PRE>
	 *   @return void
	 *   @param voucherMasterList
	 */
	void saveSalesProcessVeqtInfo(List<VoucherMasterVO> voucherMasterList);
	
}
