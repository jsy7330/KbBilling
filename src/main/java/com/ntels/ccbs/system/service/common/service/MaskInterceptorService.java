package com.ntels.ccbs.system.service.common.service;

import java.util.Map;

import com.ntels.ccbs.system.domain.common.service.MaskRule;



/**
 * <PRE>
 * 1. ClassName: MaskInterceptorService
 * 2. FileName : MaskInterceptorService.java
 * 3. Package  : com.ntels.ccbs.system.service.common
 * 4. Comment  : 마스킹 처리를 위한 룰 조회
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 3. 9. 오후 3:11:13
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 3. 9.    : 신규개발
 * </PRE>
 */
public interface MaskInterceptorService {

	/**
	 * <PRE>
	 * 1. MethodName: getMaskRuleList
	 * 2. ClassName : MaskInterceptorService
	 * 3. Comment   : 사업자의 마스킹 룰 조회
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 9. 오후 3:11:28
	 * </PRE>
	 *   @return Map<String,MaskRule> 해당 사업자의 마스킹 룰
	 *   @param soId 사업ID
	 *   @return
	 */
	public Map<String,MaskRule> getMaskRuleList(String soId); 

}
 

