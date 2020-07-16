package com.ntels.ccbs.system.mapper.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.service.MaskRule;

@Component
public interface MaskInterceptorMapper {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : MaskInterceptorMapper
	 * 3. Comment   : 마스크 처리 대상 SO ID조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 9. 오후 3:23:59
	 * </PRE>
	 *   @return List<String> SO ID 리스트
	 */
	List<String> getSoList();
	
	/**
	 * <PRE>
	 * 1. MethodName: getMaskRule
	 * 2. ClassName : MaskInterceptorMapper
	 * 3. Comment   : 마스크 처리 정보 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 9. 오후 3:25:09
	 * </PRE>
	 *   @return List<MaskRule> 해당 SO의 처리 정보 조회
	 *   @param soId 사업ID
	 */
	List<MaskRule> getMaskRule(@Param("soId") String soId);
	


}