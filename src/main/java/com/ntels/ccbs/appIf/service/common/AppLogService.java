package com.ntels.ccbs.appIf.service.common;

import com.ntels.ccbs.appIf.domain.common.IfLogVO;


/**
 * 
 * <PRE>
 * 1. ClassName: AppLogService
 * 2. FileName : AppLogService.java
 * 3. Package  : com.ntels.ccbs.appIf.service.common
 * 4. Comment  : 연동 로그를 기록한다.
 * 5. 작성자   : jhkim
 * 6. 작성일   : 2017. 3. 15. 오전 9:58:35
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     jhkim :    2017. 3. 15.    : 신규개발
 * </PRE>
 */
public interface AppLogService {
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertAppLog
	 * 2. ClassName : AppLogService
	 * 3. Comment   : 연동 로그 기록
	 * 4. 작성자    : jhkim 
	 * 5. 작성일    : 2017. 3. 15. 오전 10:00:57
	 * </PRE>
	 *   @return int
	 *   @param appLog
	 *   @return
	 */
	int nsSaveInsertLog(IfLogVO appLog);
	
}
